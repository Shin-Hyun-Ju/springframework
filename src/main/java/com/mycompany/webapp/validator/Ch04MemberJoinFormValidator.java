package com.mycompany.webapp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.webapp.dto.Ch04Member;

import lombok.extern.log4j.Log4j2;

//스프링 프레임 워크에서 작성하는 방법이 정해져 있다.
//특정 클래스에 대해서만 유효성 검사를 할 수 있다. ex) Ch04Member
@Log4j2
public class Ch04MemberJoinFormValidator implements Validator {

	// 유효성 검사가 가능한 객체인지를 조사
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		log.info("실행");
		boolean result = Ch04Member.class.isAssignableFrom(clazz); // assign 대입 -> clazz 가 Ch04Member 클래스형 객체에 대입 가능?
		return result;
	}

	// 유효성 검사
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		log.info("실행");
		Ch04Member member = (Ch04Member) target;

		// mid 검사
		if (member.getMid() == null || member.getMid().trim().equals("")) {
			errors.rejectValue("mid", "errors.mid.required", "mid는 필수 입력 사항입니다.");
		} else {
			if (member.getMid().length() < 4) {
				errors.rejectValue("mid", "errors.mid.minlength",new Object[] {4} ,"mid는 4자 이상입니다."); //mid에서 오류가 생성되었고 에러 코드는 null이고 마지막에는 디폴트값 작성. 
			}
		}
		//에러가 작성되어 있는 경우 디폴트 메세지는 출력되지 않는다.
		
		
		// mpassword 검사
		if (member.getMpassword() == null || member.getMid().trim().equals("")) {
			errors.rejectValue("mpassword", "errors.mpassword.required", "mpassowrd는 필수 입력 사항입니다.");
		} else {
			if (member.getMpassword().length() < 4) {
				errors.rejectValue("mpassword", "errors.mpassword.minlength",new Object[] {4} , "mpassword는 8자 이상입니다.");
			}
		}
		
		// memail 검사
		if (member.getMemail() == null || member.getMemail().trim().equals("")) {
			errors.rejectValue("memail","errors.memail.required", "memail는 필수 입력 사항입니다.");
		} else {
			String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(member.getMemail());
			if(!matcher.matches()) {
				errors.rejectValue("memail","errors.memail.invalid", "memail은 이메일 형식이 아닙니다.");
			}
		}
		
		//mtel 검사
	      if(member.getMtel() == null || member.getMtel().trim().equals("")) {
	         errors.rejectValue("mtel", "errors.mtel.required", "mtel은 필수 입력 사항입니다.");
	      } else {
	         String regex = "^\\d{3}-\\d{3,4}-\\d{4}$";
	         Pattern pattern = Pattern.compile(regex);
	         Matcher matcher = pattern.matcher(member.getMtel());
	         if(!matcher.matches()) {
	            errors.rejectValue("mtel", "errors.mtel.invalid", "mtel은 전화번호 형식이 아닙니다.");
	         }
	      }
	}

}
