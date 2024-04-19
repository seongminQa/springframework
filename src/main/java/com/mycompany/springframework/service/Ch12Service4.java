package com.mycompany.springframework.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("service4")  // service3 이름으로 관리  // 명시적으로 준 이름으로 관리
public class Ch12Service4 {
	public Ch12Service4() {
		log.info("실행");
	}
}
