package com.mycompany.springframework.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.dao.mybatis.Ch13BoardDao;
import com.mycompany.springframework.dto.Ch13Board;
import com.mycompany.springframework.dto.Ch13Pager;
import com.mycompany.springframework.service.Ch13Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch13")
public class Ch13Controller {
	// 게시물 쓰기 목적
	@Autowired
	private Ch13BoardDao boardDao;
	
	// url 요청을 받았을 때 바로 DB에 정보 전달
	/*@GetMapping("/writeBoard")
	public String writeBoard() {
		// DTO 객체를 만들어주고,
		// 더미 파일 만들기
		for(int i=4; i<10000; i++) {
			Ch13Board board = new Ch13Board();
			board.setBtitle("제목" + i);
			board.setBcontent("내용" + i);
			board.setMid("user");
			boardDao.insert(board);
		}

		return "redirect:/";
	}*/
	
	// 
	@GetMapping("/writeBoardForm")
	public String writeBoardForm(Model model) {
		model.addAttribute("chNum", "ch13");
		return "ch13/writeBoardForm";
	}
	
	/*@PostMapping("/writeBoard")
	public String writeBoard(Ch13Board board) {
		board.setMid("user");
//		boardDao.insert(board);
		int rowNum = boardDao.insert(board);
		log.info("rowNum: " + rowNum + ", bno: " + board.getBno()); // 상품 주문에서 사용할 수 있을 것.
		// 주문 아이템 테이블(=장바구니)에서도 사용할 수 있다 (주문번호) 
		return "redirect:/";
	}*/
	
	// ppt 131p
	@Autowired
	private Ch13Service service;
	
	@PostMapping("/writeBoard")
	public String writeBoard(Ch13Board board) {
		// 요청 데이터의 유효성 검사
		log.info("original filename: " + board.getBattach().getOriginalFilename());
		log.info("filetype: " + board.getBattach().getContentType());
		
		// 첨부 파일이 있는지 여부 조사
		if(board.getBattach() != null && !board.getBattach().isEmpty()) {
			// DTO 추가 설정
			board.setBattachoname(board.getBattach().getOriginalFilename());
			board.setBattachtype(board.getBattach().getContentType());
			try {
				board.setBattachdata(board.getBattach().getBytes());
			} catch (Exception e) {}			
		}
		
		// 로그인된 사용자 아이디 설정
		board.setMid("user");

		// 비지니스 로직 처리를 서비스로 위임
		service.boardWrite(board);
		return "redirect:/ch13/boardList";
	}
	
	// 게시물 목록
	/*@GetMapping("/boardList")
	public String boardList(@RequestParam(defaultValue="1") int pageNo,
			Model model) {
		// 사용자가 제일 처음에는 페이지의 번호를 입력하지 않는다. --> 사용자에 의해 요청한 페이지가 아닌 것을 보여주어야 한다(초기화면)
		int rowsPagingTarget = service.getTotalRows();
		Ch13Pager pager = new Ch13Pager(10, 10, rowsPagingTarget, pageNo); // 페이지 객체 만들기
		// 페이징 정보를 얻어서 서비스쪽으로 넘기고  
		List<Ch13Board> boardList = service.getBoardList(pager);
		// jsp에서 값을 넘겨 이용하자.
		model.addAttribute("pager", pager);
		model.addAttribute("boardList", boardList);
		return "ch13/boardList";
	}*/
	
	// 세션 기능 추가
	@GetMapping("/boardList")
	public String boardList(String pageNo, Model model, HttpSession session) {
		// pageNo를 받지 못했을 경우
		if(pageNo == null) {
			pageNo = (String) session.getAttribute("pageNo");
			// 세션에 저장되어 있지 않을 경우 "1"로 강제 세팅
			if(pageNo == null) {
				pageNo = "1";
			}
		}
		// 세션에 pageNo 저장
		session.setAttribute("pageNo", pageNo);
		// 문자열을 정수로 변환
		int intPageNo = Integer.parseInt(pageNo);
		
		// Pager 객체 생성
		// 사용자가 제일 처음에는 페이지의 번호를 입력하지 않는다. --> 사용자에 의해 요청한 페이지가 아닌 것을 보여주어야 한다(초기화면)
		int rowsPagingTarget = service.getTotalRows();
		Ch13Pager pager = new Ch13Pager(10, 10, rowsPagingTarget, intPageNo); // 페이지 객체 만들기
		
		// 페이징 정보를 얻어서 서비스쪽으로 넘기고  Service에서 게시물 목록 요청
		List<Ch13Board> boardList = service.getBoardList(pager);
		
		// jsp에서 사용할 수 있도록 설정.
		model.addAttribute("pager", pager);
		model.addAttribute("boardList", boardList);
		return "ch13/boardList";
	}
	
	// 게시물 상세보기 매핑 메소드
	@GetMapping("/detailBoard")
	public String detailBoard(int bno, Model model) {
		Ch13Board board = service.getBoard(bno); // 해당 게시물 번호의 정보를 board 객체 변수로 받음
		model.addAttribute("board", board);
		return "ch13/detailBoard";
	}
	
	// 게시물 첨부파일 보기 메소드
	@GetMapping("/attachDownload")
	public void attachDownload(int bno, HttpServletResponse response) throws Exception {
		// 다운로드할 데이터를 준비
		Ch13Board board = service.getBoard(bno);
		byte[] data = service.getAttachData(bno);
		
		// 응답 헤더 구성
		response.setContentType(board.getBattachtype()); // void메소드. 직접 응답을 생성해서 값을 반환
		String fileName = new String(board.getBattachoname().getBytes("UTF-8"), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		
		// 응답 본문에 파일 데이터 출력
		OutputStream os = response.getOutputStream();
		os.write(data);
		os.flush();
		os.close();
		}
	
	// 게시물 수정 화면 매핑
	@GetMapping("/updateBoardForm")
	public String updateBoardForm(int bno, Model model) {
		Ch13Board board = service.getBoard(bno); // 해당 게시물 번호의 정보를 board 객체 변수로 받음
		model.addAttribute("board", board);
		return "ch13/updateBoardForm";
	}
	
	// 게시물 수정한 내용을 업데이트
	@PostMapping("/updateBoard")
	public String updateBoard(Ch13Board board) {
		// 첨부 파일이 있는지 여부 조사
		if(board.getBattach() != null && !board.getBattach().isEmpty()) {
			// DTO 추가 설정
			board.setBattachoname(board.getBattach().getOriginalFilename());
			board.setBattachtype(board.getBattach().getContentType());
			try {
				board.setBattachdata(board.getBattach().getBytes());
			} catch (Exception e) {}			
		}

		// 비지니스 로직 처리를 서비스로 위임
		service.updateBoard(board);
		
		return "redirect:/ch13/detailBoard?bno=" + board.getBno();
	}
	
	// 게시물 삭제 메소드
	@GetMapping("/deleteBoard")
	public String deleteBoard(int bno) {
		service.removeBoard(bno);
		return "redirect:/ch13/boardList";
	}
	
}
