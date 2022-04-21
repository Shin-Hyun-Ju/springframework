package com.mycompany.webapp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
public class Ch15Aspect6Around {
	@Around("execution(public * com.mycompany.webapp.controller.Ch15Controller.around*(..))")
	public Object method(ProceedingJoinPoint joinPoint) throws Throwable { //return타입 object, 메소드에서 뭘 리턴할지 모르기 때문에 
		//전처리(핵심 코드 이전에 실행할 코드)
		log.info("전처리 실행");
		
		//얘를 기점으로 위에는 전처리, 아래로 전처리
		Object result = joinPoint.proceed(); //around*(..)(핵심코드)를 실행 
		
		//후처리(핵심 코드 이후에 실행할 코드)
		log.info("후처리 실행");
		return null;
	}
}
