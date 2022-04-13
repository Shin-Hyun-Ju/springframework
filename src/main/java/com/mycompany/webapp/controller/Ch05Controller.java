package com.mycompany.webapp.controller;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch05")
@Log4j2
public class Ch05Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch05/content";
	}
	
	@GetMapping("/getHeaderValue")
	public String getHeaderValue(HttpServletRequest request, @RequestHeader("User-Agent") String userAgent) {
		log.info("실행");
		log.info("Client IP: " + request.getRemoteAddr());
		log.info("Request Method: " + request.getMethod());
		log.info("Contextg Path(Root): " + request.getContextPath());
		log.info("Request URI: " + request.getRequestURI());
		log.info("Request URL: " + request.getRequestURL());
		log.info("Header User-Agent: "+ request.getHeader("User-Agent"));
		log.info(userAgent);
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/createCookie")
	public String createCookie(HttpServletResponse response) {
		log.info("실행");
		
	      Cookie cookie = new Cookie("useremail", "blueskii@naver.com");
	      cookie.setDomain("localhost");    //localhost 면 전송
	      cookie.setPath("/");         //localhost/... 이면 모두 전송
	      cookie.setMaxAge(30*60);      //이 시간동안에만 전송 (초단위이기 때문에 30분)
	      cookie.setHttpOnly(true);       //JavaScript에서 못 읽게함
	      cookie.setSecure(false);       //https: 또는 http: 모두 전송
	      response.addCookie(cookie);	//
	      
	      cookie = new Cookie("userid", "spring");
	      cookie.setDomain("localhost");    //localhost 면 전송
	      cookie.setPath("/");         //localhost/... 이면 모두 전송
	      cookie.setMaxAge(30*60);      //이 시간동안에만 전송 (초단위이기 때문에 30분)
	      cookie.setHttpOnly(true);       //JavaScript에서 못 읽게함, 보통은 막는다.
	      cookie.setSecure(false);       //https://만 전송
	      response.addCookie(cookie);	//응답에 쿠키를 추가해 주는 역활 (set Cookie)
		
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/getCookie1")
	public String getCookie1(HttpServletRequest request) {
		log.info("실행");
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			String cookieValue = cookie.getValue();
			log.info(cookieName + ":" + cookieValue);
		}
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/getCookie2")
	public String getCookie2(@CookieValue String userid, @CookieValue String useremail) {
		log.info("실행");
		log.info("userid: " + userid);
		log.info("useremail: "+useremail);
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/createJsonCookie")
	public String createJsonCookie(HttpServletResponse response) throws Exception {
		//String json = "{\"userid\":\"spring\"}"; 
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userid", "홍길동");
		jsonObject.put("useremail", "spring@mycompany.com");
		String json = jsonObject.toString();
		
		log.info(json);
		json = URLEncoder.encode(json, "UTF-8");	//쿠기 값으로 {} 값이 같이 들어갈 수 없기 때문에 UTF-8로 변환??
		//한글을 UTF-8로 해석해줘~ (바꾸어줘~)
		log.info(json);
		
		Cookie cookie = new Cookie("user", json); //쿠키의 값은 모두 문자열로 저장되어 있다.
		response.addCookie(cookie);
		return "redirect:/ch05/content";
	}
	
	
	//어떻게 json에 있는 내용을 parsing 해서 읽어낼 수 있는지!!
	@GetMapping("getJsonCookie")
	public String getJsonCookie(@CookieValue String user) {
		log.info(user);
		JSONObject jsonObject = new JSONObject(user);	//자바 객체로 jsonObject가 얻어진다.
		String userid = jsonObject.getString("userid");
		String useremail = jsonObject.getString("useremail");
		log.info("username: " + userid);
		log.info("useremail: " + useremail);
		return "redirect:/ch05/content";
	}
}
