package com.blog.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {

	private  static Logger  logger =LoggerFactory.getLogger(TimeAspect.class);

	@Around("execution(* com.icil.esolution.controller.*.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

		long start = new Date().getTime();

		Object object = pjp.proceed();

		String costTime= (new Date().getTime() - start)+"";
		String methodName = pjp.getSignature().getName();
		logger.info("*************** Run the  method --> {} total  cost  time  is {}  ms********************",methodName,costTime);

		return object;
	}
}
