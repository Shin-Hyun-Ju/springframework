package com.mycompany.webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j2;

//모든 컨트롤러의 예외를 처리하여 준다.
@Component
@ControllerAdvice
@Log4j2
public class Ch10ExceptionHandler {
	@ExceptionHandler(NullPointerException.class)
	public String handleNullPointerException(NullPointerException e) {
		log.info("실행: " + e.getMessage());
		return "ch10/500_null";
	}

	@ExceptionHandler(ClassCastException.class)
	public String handleNullPointerException(ClassCastException e) {
		log.info("실행: " + e.getMessage());
		return "ch10/500_cast";
	}

	@ExceptionHandler(Ch10SoldOutException.class)
	public String handleCh10SoldOutException(Ch10SoldOutException e) {
		log.info("실행: " + e.getMessage());
		return "ch10/soldout";
	}

	@ExceptionHandler(Exception.class) // 구체적인 예외가 있으면 먼저 실행되고 정의되어 있지 않은 예외가 작성되어있을 경우에는 이것을 실행
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleCh10SoldOutException(Exception e) {
		log.info("실행: " + e.getMessage());
		return "ch10/500";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class) // 구체적인 예외가 있으면 먼저 실행되고 정의되어 있지 않은 예외가 작성되어있을 경우에는 이것을 실행
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleCh10SoldOutException(NoHandlerFoundException e) {
		log.info("실행: " + e.getMessage());
		return "ch10/404";
	}
}
