package com.mycompany.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller	 //같은 이름을 갖고있는 것 있으면 안됨. 얘가 없으면 객체로 만들어지지 않음.
@RequestMapping("/ch13")
@Log4j2
public class Ch13Controller {
	
	public Ch13Controller() {
		log.info("실행"); //생성자가 호출되면 객체가 만들어졌다는 뜻 <init>은 생성자가 실행되었다는 뜻이다.
	}
	
	@RequestMapping("/content") //메소드 이름과 mapping()안에 들어가는 이름이 꼭 같을 필요는 없다.
	public String content(){ //뷰 이름이 string 타입이기 때문에 리턴타입 string으로 지정
		log.info("실행");
		return "ch13/content";
	}
}