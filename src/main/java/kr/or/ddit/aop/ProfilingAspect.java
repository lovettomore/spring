package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ProfilingAspect {

	private static final Logger logger = LoggerFactory.getLogger(ProfilingAspect.class);
	
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy(){}
	
	
	public void before(JoinPoint joinPoint) {
		logger.debug("ProfilingAspect method before");
	}
	
	public void after(JoinPoint joinPoint) {
		logger.debug("ProfilingAspect method after");
	}
	
	@Around("dummy()") /* dummy메소드를 기준으로 실행 해라 */
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		//Object result = null;
		
		//실행전
		long start = System.currentTimeMillis();
        logger.debug("ProfilingAspect around method before : {}", (start));
		
		//실행
		Object[] methodArgs = proceedingJoinPoint.getArgs();
		Object returnObj = proceedingJoinPoint.proceed(methodArgs);
//		logger.debug("method name : {}", proceedingJoinPoint.proceed());
		
		
		//실행후
		long end = System.currentTimeMillis();
		logger.debug("ProfilingAspect around method after : {}", (end));
		logger.debug("method name proseeding time : {}", (end-start)+"ms");
		
		return returnObj;
		
	}
	
}
