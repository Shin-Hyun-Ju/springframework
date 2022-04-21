package com.mycompany.webapp.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
public class Ch15Aspect7Around {
	@Around("@annotation(com.mycompany.webapp.aspect.Ch15Aspect7RuntimeCheck)")
	public Object method(ProceedingJoinPoint joinPoint) throws Throwable { //return타입 object, 메소드에서 뭘 리턴할지 모르기 때문에 
		//전처리(핵심 코드 이전에 실행할 코드)
		long start = System.nanoTime();
		
		//------------------------------
		
		//얘를 기점으로 위에는 전처리, 아래로 전처리
		//result에 핵심코드의 return값이 들어오게 된다.
		Object result = joinPoint.proceed(); //around*(..)(핵심코드)를 실행 할때
		//핵심 코드(메소드)이름 얻기.
		String methodName = joinPoint.getSignature().toShortString(); //joinPoint를 통해서 얻는다.
		
		
		//------------------------------
		
		//후처리(핵심 코드 이후에 실행할 코드)
		long end = System.nanoTime();
		long howLong = end-start;
		log.info(methodName+" 실행시간: "+howLong+"ns");
		
		//Request 객체 만들어서 view에 내용 전달하기!
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("methodName", methodName);
		session.setAttribute("howLong", howLong);
		return result;
	}
}
