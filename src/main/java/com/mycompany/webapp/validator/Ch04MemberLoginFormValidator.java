package com.mycompany.webapp.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.webapp.dto.Ch04Member;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Ch04MemberLoginFormValidator implements Validator{

	//유효성 검사가 가능한 객체인지 조사
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		boolean result = Ch04Member.class.isAssignableFrom(clazz);
		return result;
	}
	
	//유효성 검사
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Ch04Member member = (Ch04Member) target;
		
		// mid 검사
		if (member.getMid() == null || member.getMid().trim().equals("")) {
			errors.rejectValue("mid", null, "mid는 필수 입력 사항입니다.");
		} else {
			if (member.getMid().length() < 4) {
				errors.rejectValue("mid", null, "mid는 4자 이상입니다."); //mid에서 오류가 생성되었고 에러 코드는 null이고 마지막에는 에러 메세지르 이야기해준다. 
			}
		}

		// mpassword 검사
		if (member.getMpassword() == null || member.getMid().trim().equals("")) {
			errors.rejectValue("mpassword", null, "mpassowrd는 필수 입력 사항입니다.");
		} else {
			if (member.getMpassword().length() < 4) {
				errors.rejectValue("mpassword", null, "mpassword는 8자 이상입니다.");
			}
		}
	}

}
