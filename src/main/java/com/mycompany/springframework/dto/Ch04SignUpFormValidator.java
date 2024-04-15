package com.mycompany.springframework.dto;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch04SignUpFormValidator implements Validator {@Override
	public boolean supports(Class<?> clazz) {
		log.info("로그인 supports() 실행");
		return Ch04SignUpForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Ch04SignUpForm signUpForm = (Ch04SignUpForm) target;
		
		// 아이디 검사
		String mid = signUpForm.getMid();
		if(mid == null || mid.equals("")) {
			errors.rejectValue("mid", null, "아이디는 반드시 입력되어야 합니다.");
		} else if(mid.length() < 6 || mid.length() > 12) {
			errors.rejectValue("mid", null, "아이디는 6자 이상 12자 이하이여야 합니다.");
		}
		
		// 비밀번호 검사
		String mpassword = signUpForm.getMpassword();
		String pattern = "/^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}$/";
		if(mpassword == null || mpassword.equals("")) {
			errors.rejectValue("mpassword", null, "비밀번호는 반드시 입력되어야 합니다.");
		} else if(mpassword.length() < 8 || mpassword.length() > 12) {
			errors.rejectValue("mpassword", null, "비밀번호는 8자 이상 12자 이하이여야 합니다.");
		} else if(Pattern.matches(pattern, mpassword)) {
			errors.rejectValue("mpassword", null, "비밀번호는 알파벳 대소문자 및 숫자를 포함해야 합니다.");
		}
	}

}
