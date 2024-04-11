package com.mycompany.springframework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(Ch01Controller.class);
	@RequestMapping("/")
	public String index() {
		LOGGER.info("LOGGER를 사용하여 home.index()실행");
		return "home";
	}
}
