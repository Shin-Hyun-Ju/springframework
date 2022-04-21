package com.mycompany.webapp.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/*
 * INFO : com.mycompany.webapp.controller.Ch15Controller.afterReturning() - 실행
 * INFO : com.mycompany.webapp.aspect.Ch15Aspect4AfterReturning.method() - 실행
 * INFO : com.mycompany.webapp.aspect.Ch15Aspect4AfterReturning.method() - 리턴값: redirect:/ch15/content
 * INFO : com.mycompany.webapp.aspect.Ch15Aspect3After.method() - 실행
 */

@Component
@Aspect
@Log4j2
public class Ch15Aspect4AfterReturning {
	@AfterReturning( //메소드가 예외 없이 실행 되었을 때 사용
			  pointcut="execution(public * com.mycompany.webapp.controller.Ch15Controller.afterReturning*(..))",
		      returning="returnValue" //afterReturning이 리턴한 값을 받을 변수
			)
	public void method(String returnValue) {	//returning값과 매개변수 값이 같다.
		log.info("실행");
		log.info("리턴값: " + returnValue);
	}
}
