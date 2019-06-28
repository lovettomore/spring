package kr.or.ddit.exception.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {

	@ExceptionHandler({ArithmeticException.class}) //이 예외가 발생했을 때 실행
	public String handleException() {
		return "exception";
	}
}
