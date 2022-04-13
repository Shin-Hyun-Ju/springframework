
package com.mycompany.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch02")
@Log4j2
public class Ch02Controller {
	@RequestMapping("/content")
	public String content() {
		log.info("실행");
		return "ch02/content";
	}
	
	//url만 맞으면 다 실행되게끔 해줄 수 있음. << 잘 사용 안함.
	
	
	//@RequestMapping(value="/method", method=RequestMethod.GET)	//요청 방식을 지정할 수 있다. 요청별로 따로 처리. 단순히 정보 받을 때 GET사용
	@GetMapping("/method")
	public String method1() {
		log.info("메소드1 실행");
		return "ch02/content";
	}
	
	//@RequestMapping(value="/method", method=RequestMethod.POST) //client 가 정보 주면서 처리해달라고 할때, ex)회원가입
	@PostMapping("/method")
	public String method2() {
		log.info("메소드2 실행");
		return "ch02/content";
	}
	
	//@RequestMapping(value="/method", method=RequestMethod.PUT) //수정할때 사용. post도 사용하지만, post는 새로 만들 때 사용하고 put은 기존의 데이터를 새로 보내는 데이터로 변경할때 사용
	@PutMapping("/method")
	public String method3() {
		log.info("메소드3 실행");
		return "ch02/content";
	}
	
	//@RequestMapping(value="/method", method=RequestMethod.DELETE) //삭제
	@DeleteMapping("/method")
	public String method4() {
		log.info("메소드4 실행");
		return "ch02/content";
	}
}
