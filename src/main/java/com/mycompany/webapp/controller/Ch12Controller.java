package com.mycompany.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller //자동 객체 생성, 클라이언트 요청 처리
@RequestMapping("/ch12")
@Log4j2
public class Ch12Controller {
	@RequestMapping("/content") //메소드 이름과 mapping()안에 들어가는 이름이 꼭 같을 필요는 없다.
	public String content(){ //뷰 이름이 string 타입이기 때문에 리턴타입 string으로 지정
		log.info("실행");
		return "ch12/content";
	}
	
	@RequestMapping("/fileList")
	public String fileList() {
		log.info("실행");
		return "ch12FileListView";
	}
	
	@RequestMapping("/fileDownload")
	public String fileDownload(@ModelAttribute("fileName") String fileName,
			@ModelAttribute("userAgent") @RequestHeader("user-Agent") String userAgent) {
		log.info("실행");
		//model.addAttribute(fileName)
		return "ch12FileListView";
	}
}
