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
		String pattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}";
		if(mpassword == null || mpassword.equals("")) {
			errors.rejectValue("mpassword", null, "비밀번호는 반드시 입력되어야 합니다.");
		} else if(mpassword.length() < 8 || mpassword.length() > 12) {
			errors.rejectValue("mpassword", null, "비밀번호는 8자 이상 12자 이하이여야 합니다.");
		} else if(!Pattern.matches(pattern, mpassword)) {
			errors.rejectValue("mpassword", null, "비밀번호는 알파벳 대소문자 및 숫자를 포함해야 합니다.");
		}
		
		// 이메일 검사 
		String memail = signUpForm.getMemail();
		pattern = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";
		if(memail == null || memail.equals("")) {
			errors.rejectValue("memail", null, "이메일은 반드시 입력되어야 합니다.");
		} else if(!Pattern.matches(pattern, memail)) {
			errors.rejectValue("memail", null, "이메일의 형식은 xxx@xxx.com입니다.");
		}
		
		// 전화번호 검사
		String mphone = signUpForm.getMphone();
		pattern = "^(?:(010-\\d{4})|(01[1|6|7|8|9]-\\d{3,4}))-(\\d{4})";
		if(mphone == null || mphone.equals("")) {
			errors.rejectValue("mphone", null, "전화번호를 반드시 입력해주세요.");
		} else if(!Pattern.matches(pattern, mphone)) {
			errors.rejectValue("mphone", null, "전화번호 형식은 \'012-345(6)-7890\'입니다.");
		}
	}

}
