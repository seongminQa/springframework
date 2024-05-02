package com.mycompany.springframework.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch17AuthenticationSuccessHandler 
	// 로그인 성공 후 사용자가 접근하고자 했던 페이지로 이동
//	extends SavedRequestAwareAuthenticationSuccessHandler{
	// 로그인 성공 후 무조건 defaultTargetUrl로 이동
	extends SimpleUrlAuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		log.info("실행");
		// 로그인 성공 후에 이동할 Url (SimpleUrlAuthenticationSuccessHandler를 사용했을 때 디폴트 지정)
		setDefaultTargetUrl("/");
		// 기타 기본 설정을 적용하기 위해 부모 메소드 호출
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
