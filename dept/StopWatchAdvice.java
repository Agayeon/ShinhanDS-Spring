package com.shinhan.myapp.aop.dept;

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
		// 주관심사 가기전
		
		System.out.println("[메서드 호출 전의 LoggingAdvice]" + jp.getSignature().getName());
		StopWatch watch = new StopWatch("수행시간");
		watch.start();
		// 주관심사 가기
		Object obj = jp.proceed();
		//System.out.println("주 관심사에서 return내용" + obj);
		//주관심사 다녀온 후
		watch.stop();
		System.out.println("[메서드 호출 후의 StopWatchAdvice]" + jp.getSignature().getName() + "#####");
		System.out.println("수행하는 데 걸린 시간: " + watch.getTotalTimeMillis());
		return obj;
	}

}