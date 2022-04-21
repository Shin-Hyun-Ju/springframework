package com.mycompany.webapp.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
@Order(2)
public class Ch15Aspect1Before {//before 괄호 안에 포인트컷 들어감
	@Before("execution(public * com.mycompany.webapp.controller.Ch15Controller.before*(..))") 
	public void method() {
		log.info("싦행");
	}
}
