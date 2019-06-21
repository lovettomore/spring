package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	public void before(JoinPoint joinPoint) {
		logger.debug("LoggingAspect before method");
	}
	
	public void after(JoinPoint joinPoint) {
		logger.debug("LoggingAspect after method");
	}
	
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		//business logic 실행 전
		logger.debug("LoggingAspect around method before");
		
		
		//business logic 실행
		logger.debug("method name : {}", proceedingJoinPoint.getSignature().getName());
		//proceedingJoinPoint.getSignature().getName();
		Object[] methodArgs = proceedingJoinPoint.getArgs();
		Object returnObj = proceedingJoinPoint.proceed(methodArgs);
		
		//business logic 실행 후
		logger.debug("LoggingAspect around method after");
		
		return returnObj;
	}
}
