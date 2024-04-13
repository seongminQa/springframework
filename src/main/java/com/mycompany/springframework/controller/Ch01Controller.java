package com.mycompany.springframework.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ch01")
@Slf4j
public class Ch01Controller {
	
	@RequestMapping("/content")
	public String content(String chNum, Model model) {
//		log.info("content() 실행");
		model.addAttribute("chNum", chNum);
		return "/ch01/content";
	}
}
