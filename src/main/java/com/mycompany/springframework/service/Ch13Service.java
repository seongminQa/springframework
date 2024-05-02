package com.mycompany.springframework.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.mybatis.Ch13BoardDao;
import com.mycompany.springframework.dto.Ch13Board;
import com.mycompany.springframework.dto.Ch13Pager;

import lombok.extern.slf4j.Slf4j;

@Service // 관리객체
@Slf4j
public class Ch13Service {
	@Resource // @Autowired 받아도 됨.
	private Ch13BoardDao boardDao; // Dao 주입받기
	
	public void boardWrite(Ch13Board board) {
		// 비즈니스 로직 처리...
		int rowNum = boardDao.insert(board);
		log.info("rowNum: " + rowNum + ", bno: " + board.getBno());
	}
	
	// 페이지 당 리스트를 보여주는 메소드
	public List<Ch13Board> getBoardList(Ch13Pager pager) {
		List<Ch13Board> boardList = boardDao.selectByPage(pager);
		return boardList;
	}
	
	// 리스틀의 총 행의 수를 반환하는 메소드
	public int getTotalRows() {
		int totalRows = boardDao.count();
		return totalRows;
	}

	// 게시물 객체를 bno로 구분하기?
	public Ch13Board getBoard(int bno) {
		Ch13Board board = boardDao.selectByBno(bno);
		return board;
	}

	// 게시물 첨부파일 보기 메소드
	public byte[] getAttachData(int bno) {
		Ch13Board board = boardDao.selectAttachData(bno);
		return board.getBattachdata();
	}
	
	public void updateBoard(Ch13Board board) {
		int rowNum = boardDao.update(board);
	}

	public void removeBoard(int bno) {
		// int rowNum = boardDao.deleteByBno(bno); // rowNum은 삭제된 행수를 얻고싶을 때 넣어주고, 필요없으면 안써도 된다.
		boardDao.deleteByBno(bno);
	}
}