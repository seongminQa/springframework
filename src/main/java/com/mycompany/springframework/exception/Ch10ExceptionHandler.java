package com.mycompany.springframework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@ControllerAdvice  // @Service나 @Repository가 붙어있는 것이 아니기 때문에 디스패처 쪽에서 검색됨.
@Component
public class Ch10ExceptionHandler {  // 디스패처 안에서 컨테이너로 객체가 저장..?
	
	// 자바에서 정해져 있는 예외로 처리
	@ExceptionHandler(NullPointerException.class)
	public String handleNullPointerException() {
		// 예외 처리 내용 작성
		log.info("실행");
		return "ch10/error500_null";
	}
	
	// 사용자 정의 예외처리
	@ExceptionHandler(Ch10CustomException.class)
	public String handleCh10CustomException(Ch10CustomException e, Model model) {
		// 예외 처리 내용 작성
		log.info("실행");
		
		model.addAttribute("message", e.getMessage());
		
		return "ch10/error500_custom";
	}
	
	/*// 위 예외를 제외한 500을 발생시키는 모든 예외를 처리 1
	@ExceptionHandler // 이 어노테이션은 예외가 발생했을 때 실행시켜주는 구문이다.
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handle500Exception(Exception e) {
		
		return "ch10/error500";
	}*/
	
	// 위 예외를 제외한 500을 발생시키는 모든 예외를 처리 2
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handle500Exception() {
		
		return "ch10/error500";
	}
	
	// 404 예외 처리
	@ExceptionHandler(NoHandlerFoundException.class) // NoHandlerFoundException : 컨트롤러를 못찾았다는 뜻
	@ResponseStatus(HttpStatus.NOT_FOUND) // 해당 Http를 못찾은 상태이다.
	public String handle404() {
		
		return "ch10/error404";
	}
}
