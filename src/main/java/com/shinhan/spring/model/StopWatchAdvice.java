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

		
		System.out.println("[메서?�� ?���? ?��?�� LoggingAdvice]" + jp.getSignature().getName());
		StopWatch watch = new StopWatch("?��?��?���?");
		watch.start();

		Object obj = jp.proceed();

		watch.stop();
		System.out.println("[메서?�� ?���? ?��?�� StopWatchAdvice]" + jp.getSignature().getName() + "#####");
		System.out.println("?��?��?��?�� ?�� 걸린 ?���?: " + watch.getTotalTimeMillis());
		return obj;
	}

}