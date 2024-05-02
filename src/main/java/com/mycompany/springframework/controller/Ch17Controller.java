package com.mycompany.springframework.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch17")
public class Ch17Controller {
	// ch17_security 태그에 있는 폼 또는 url들 만들어보자.
	@GetMapping("/loginForm")
	public String loginForm() {
		
		return "ch17/loginForm";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/admin/page")
	public String adminpage() {
		return "ch17/admin/page";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/manager/page")
	public String managerpage() {
		return "ch17/admin/registerProduct";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/user/page")
	public String userpage() {
		return "ch17/admin/registerProduct";
	}
}
