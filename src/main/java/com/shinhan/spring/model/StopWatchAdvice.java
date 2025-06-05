package com.shinhan.spring.model;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
@Aspect
@Component
public class StopWatchAdvice {

	
	@Pointcut("within(com.shinhan.myapp.aop.dept.DeptService)")	
	public void targetMethod2() {}
	

	@Around("targetMethod2()")
	public Object invoke(ProceedingJoinPoint  jp) throws Throwable {

		
		System.out.println("[Î©îÏÑú?ìú ?ò∏Ï∂? ?†Ñ?ùò LoggingAdvice]" + jp.getSignature().getName());
		StopWatch watch = new StopWatch("?àò?ñâ?ãúÍ∞?");
		watch.start();

		Object obj = jp.proceed();

		watch.stop();
		System.out.println("[Î©îÏÑú?ìú ?ò∏Ï∂? ?õÑ?ùò StopWatchAdvice]" + jp.getSignature().getName() + "#####");
		System.out.println("?àò?ñâ?ïò?äî ?ç∞ Í±∏Î¶∞ ?ãúÍ∞?: " + watch.getTotalTimeMillis());
		return obj;
	}

}