package com.mycompany.webapp.aspect;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
public class Ch15Aspect8Around {
	@Around("@annotation(com.mycompany.webapp.aspect.Ch15Aspect8LoginCheck)")
	public Object method(ProceedingJoinPoint joinPoint) throws Throwable {
		
		//Request 객체 만들어서 view에 내용 전달하기!
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		HttpSession session = request.getSession();
		
		String mid = (String) session.getAttribute("sessionMid"); //object return하기 때문에 타입 변환

		if(mid == null) { //로그인이 안되어있다면 json보내기
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "fail"); //json을 통해 result에 fail이란 값을 담아 보내줌 왜냐, 지금 session에 sessionMid가 없기 때문
			String json = jsonObject.toString();
			
			HttpServletResponse response = sra.getResponse();
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.println(json);
			pw.flush();
			pw.close();
			return null;
		}else {
			//핵심 코드
			Object result = joinPoint.proceed();
			return result;
		}
		

		
	}
}
