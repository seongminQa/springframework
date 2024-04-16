package com.mycompany.springframework.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch05")
public class Ch05Controller {
	/*@GetMapping("/header")
	public String header(Model model,
			@RequestHeader("User-Agent") String userAgent) {
		log.info("실행");
		log.info("User.Agent" + userAgent);
		
		// 브라우저의 종류  알아내기
		if(userAgent.contains("Edg")) {
			model.addAttribute("browser", "Edge");
		} else {
			model.addAttribute("browser", "Chrome");
		}
		
		model.addAttribute("chNum", "ch05");
		return "ch05/header";
	}*/
	
	@GetMapping("/header")
	public String header(Model model, HttpServletRequest request) {
		log.info("실행");
		
		// HttpServletRequest을 사용하는 것이 더 많은 정보를 얻을 수 있다.
		String userAgent = request.getHeader("user-Agent");
		
		// 브라우저의 종류  알아내기
		if(userAgent.contains("Edg")) {
			model.addAttribute("browser", "Edge");
		} else {
			model.addAttribute("browser", "Chrome");
		}
		
		// 브라우저가 실행하는 컴퓨터의 ip주소
		String clientIp = request.getRemoteAddr();
		model.addAttribute("clientIp", clientIp);
		
		model.addAttribute("chNum", "ch05");
		return "ch05/header";
	}
	
	@GetMapping("/createCookie")
	public String createCookie(Model model, HttpServletResponse response) {
		// Cookie 생성
		Cookie cookie = new Cookie("useremail", "summer@naver.com");
		
		// Cookie를 응답 HTTP에 포함시키기
		response.addCookie(cookie);  // 쿠키 생성
		
		model.addAttribute("chNum", "ch05");
		return "redirect:/";
	}
	
	@GetMapping("/readCookie")
	public String readCookie(@CookieValue("useremail") String userEmail, Model model) {
		log.info("userEmail: " + userEmail);
		
		model.addAttribute("chNum", "ch05");
		model.addAttribute("user", userEmail);
		return "ch05/cookie";
	}// 쿠키가 존재하지 않는다면 이 메소드는 실행되지 않는다.
	
}
