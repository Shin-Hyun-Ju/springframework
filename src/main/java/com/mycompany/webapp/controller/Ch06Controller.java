package com.mycompany.webapp.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch06")
@Log4j2
public class Ch06Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch06/content";
	}
	
	@GetMapping("/forward")
	public String forward() {
		return "ch06/forword";
	}
	
	@GetMapping("/redirect")
	public String redirect() {
		return "redirect:/";
	}
	
	@GetMapping("/getFragmentHtml")
	public String getFragmentHtml() {
		return "ch06/fragmentHtml";
	}
	
	//컨트롤러가 응답 처리 하면서 응답도 직접 만드는 경우!
	//controller에서 jsp로 forward 하지 않고 바로 응답을 제공하는 경우이다! >> viewname을 리턴하지 않음.
	@GetMapping("/getJson1")
	public void getJson1(HttpServletResponse response) throws Exception {
		//{"filename":"photo6.jpg"}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo2.jpg");
		String json = jsonObject.toString();
		
		response.setContentType("application/json; charset=UTF-8"); //응용프로그램에서 쓰는 json 파일이다. 한글이 포함되어 있다면 어떤 문자셋으로 인코딩 햇는지에 대한 정보도 넣어주기
		//만약 보내고자 하는 type이 html이라면 text/html; charset=UTF-8
		//아직 보낸거 아니고 데이터 타입만 지정
		
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.flush();
		pw.close();
		
		
		//return "ch06/fragmentHtml";	//jsp가 실행한 응답을 보낸다는 뜻. 이미 보냈기 때문에 생략 가능
	}
	
	//produces를 통해 헤더에 명시해 주어야 하는 type을 작성해 준다.
	@GetMapping(value="/getJson2", produces = "application/json; charset=UTF-8;")
	@ResponseBody //리턴되는 내용이 응답 본문에 들어간다.
	public String getJson2() throws Exception{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo3.jpg");
		String json = jsonObject.toString();
		return json; //응답 http에 들어가는 실제 내용
	}
	
	@GetMapping("/getJson3")
	public String getJson3() {
		return "redirect:/";
	}
}
