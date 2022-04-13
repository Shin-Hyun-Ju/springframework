package com.mycompany.webapp.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Ch04Dto;
import com.mycompany.webapp.dto.Ch04Member;
import com.mycompany.webapp.validator.Ch04MemberEmailValidator;
import com.mycompany.webapp.validator.Ch04MemberIdValidator;
import com.mycompany.webapp.validator.Ch04MemberLoginFormValidator;
import com.mycompany.webapp.validator.Ch04MemberPasswordValidator;
import com.mycompany.webapp.validator.Ch04MemberTelValidator;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch04")
@Log4j2
public class Ch04Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch04/content";
	}

	@PostMapping("/method1")
	public String method1(Ch04Dto dto) {
		log.info(dto.toString());
		return "ch04/content";
	}

	// DTO와 유효성 검사기를 연결
	@InitBinder("joinForm") // 얘도 첫자를 소문자로 해서 ch04Member와 valid의 객체를 연결시켜 준다.
	public void bindCh04MemberJoinFormValidator(WebDataBinder binder) {
		// binder.setValidator(new Ch04MemberJoinFormValidator());
		binder.addValidators(new Ch04MemberIdValidator(), new Ch04MemberPasswordValidator(),
				new Ch04MemberEmailValidator(), new Ch04MemberTelValidator());
	}

	@PostMapping("/join")
	// Ch04Member 객체는 이 메소드 안에서만 사용하는게 아니고 jsp에서도 ch04Member라는 이름으로 참조된다.
	// ModelAttribute를 사용하면 변수를 지정해줄 수 있다.
	public String join(@ModelAttribute("joinForm") @Valid Ch04Member member, Errors errors) { // @Valid를 작성해 넣어야 유효성 검사를
																								// 하라는 지시, errors라는 객체가
																								// 매개변수에 들어온다.
		log.info("실행");

		// 유효성 검사 확인
		if (errors.hasErrors()) { // errors에 에러 정보 가지고 있는지 아닌지!
			// 다시 입력 폼으로 돌아가기
			return "ch04/content";
		}

		// 회원 가입 처리
		// ...

		// 홈페이지로 이동
		return "redirect:/";
	}

	@InitBinder("loginForm")
	public void bindCh04MemberLoginFormValidator(WebDataBinder binder) {
		//binder.setValidator(new Ch04MemberLoginFormValidator());
		binder.addValidators(new Ch04MemberIdValidator(),
         new Ch04MemberPasswordValidator());
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") @Valid Ch04Member member, Errors errors) {
		log.info("실행");

		// 유효성 검사 확인
		if (errors.hasErrors()) {
			// 다시 입력 폼으로 돌아가기
			return "ch04/content";
		}

		// 홈페이지로 이동
		return "redirect:/";
	}
}
