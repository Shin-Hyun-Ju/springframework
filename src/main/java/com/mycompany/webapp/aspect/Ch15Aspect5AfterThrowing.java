package com.mycompany.webapp.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
public class Ch15Aspect5AfterThrowing {
	@AfterThrowing(      //메소드가 예외가 발생한다면 
			pointcut="execution(public * com.mycompany.webapp.controller.Ch15Controller.afterThrowing*(..))",
		    throwing="e" //예외 객체 전달 
		      )
	public void method(Throwable e) { //꼭 예외전달 객체 매개변수로 받을 필요 없음.
		log.info("실행");
		log.info("예외 메세지" + e.getMessage()); //e.getMessage()를 통해 이메일을 보내거나, db에 저장하는 일을 할 수 있다.
	}
}
