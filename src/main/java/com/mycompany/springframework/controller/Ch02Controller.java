package com.mycompany.springframework.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch02")
public class Ch02Controller {

		@GetMapping("/getMethod")
		public String getMethod(String chNum, String bkind, String bno, Model model) {
			// 요청 처리 코드
			log.info("getMethod() 실행 ");
			log.info("chNum: " + chNum);

			model.addAttribute("chNum", chNum);
			return "ch02/getMethod";
		}
		
		@RequestMapping(value="/getMethodAjax", method=RequestMethod.GET)
		public String getMethodAjax(String chNum, String bkind, String bno, Model model) {
			// 요청 처리 코드
			log.info("getMethodAjax() 실행 ");
			log.info("chNum: " + chNum);
			log.info("bkind: " + bkind);
			log.info("bno: " + bno);
			
			model.addAttribute("chNum", chNum);
			return "ch02/getMethodAjax";
		}
		
		@RequestMapping("/postMethod")
		public String postMethod(String chNum, String mid, String mpassword, Model model) {
			// 요청 처리 코드
			log.info("postMethod() 실행 ");
			log.info("chNum: " + chNum);
			log.info("mid: " + mid);
			log.info("mpassword: " + mpassword);
			
			model.addAttribute("chNum", chNum);
			return "ch02/postMethod";
		}
		
/*		@RequestMapping("/postMethodAjax")
		public String postMethodAjax(String mid, String mpassword, Model model) {
			// 요청 처리 코드
			log.info("postMethodAjax() 실행 ");
			log.info("mid: " + mid);
			log.info("mpassword: " + mpassword);
			return "ch02/postMethodAjax";
		}*/
		
		
		/*@RequestMapping("/postMethodAjax")
		public void postMethodAjax(String mid, String mpassword, HttpServletResponse response) throws IOException {
			// 요청 처리 코드
			log.info("postMethodAjax() 실행 ");
			log.info("mid: " + mid);
			log.info("mpassword: " + mpassword);

			Map<String, String> map = new HashMap<>();
			map.put("spring", "12345");  // 데이터 베이스가 있었으면 데에터 베이스로 확인
			map.put("summer", "67890");
			
			String result = "";
			if(map.containsKey(mid)) {
				if(map.get("mid").equals(mpassword)) {
					// 로그인 성공
					result = "{\"result\":\"success\"}";
				} else {
					// 비밀번호가 틀린 경우
					result = "{\"result\":\"fail\", \"reason\":\"wrongMpassword\"}";
				}
			} else {
				// 아이디가 존재하지 않는 경우
				result = "{\"result\":\"fail\", \"reason\":\"wrongMid\"}";
			}
			
			// 직접 응답을 생성해서 브라우저로 보냄
			PrintWriter pw = response.getWriter();
			response.setContentType("application/json; charset=UTF-8");
			pw.println(result);
			pw.flush();
			pw.close();
		}*/
		
		   @PostMapping("/postMethodAjax")
		   public void postMethodAjax(String mid, String mpassword, HttpServletResponse response) throws IOException {
		      //요청 처리 코드
		  log.info("postMethodAjax() 실행");
		  log.info("mid: " + mid);
		  log.info("mpassword: " + mpassword);
		  
		  Map<String, String> map = new HashMap<>();
		  map.put("spring", "12345");
		  map.put("summer", "67890");
		  
		  /*String result = "";
		  if(map.containsKey(mid)) {
		     if(map.get(mid).equals(mpassword)) {
				        //로그인 성공
				result = "{\"result\":\"success\"}";
			} else {
				    //비빌번호가 틀린 경우
				result = "{\"result\":\"fail\", \"reason\":\"wrongMpassword\"}";
				     }
				  } else {
				     //아이디가 존재하지 않는 경우
				 result = "{\"result\":\"fail\", \"reason\":\"wrongMid\"}";;
			}*/
		  
		  // JSON 라이브러리를 pom.xml에 추가하여 다운로드하여 사용
		  // 위의 코드보다 훨씬 간편하게 사용할 수 있다.
		  JSONObject result = new JSONObject();
		  if(map.containsKey(mid)) {
			     if(map.get(mid).equals(mpassword)) {
					        //로그인 성공
					result = result.put("result", "success");
				} else {
					    //비빌번호가 틀린 경우
					result.put("result", "fail");
					result.put("reason", "wrongMpassword");
					     }
					  } else {
					     //아이디가 존재하지 않는 경우
					 result.put("result", "fail");
					 result.put("reason", "wrongMid");
				}
		  
		  //직접 응답을 생성해서 브라우저로 보냄
		  PrintWriter pw = response.getWriter();
		  response.setContentType("application/json; charset=UTF-8");
		      pw.println(result.toString());
		      pw.flush();
		      pw.close();
		   }
		
	
}
