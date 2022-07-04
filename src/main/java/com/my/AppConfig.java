package com.my;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class AppConfig {

	@Autowired
	Manager manager;
	
	public AppConfig() {
		System.out.println("AppConfig......");
	}
	
	@Around("execution(* com.my.board2.*.*(..))")
	public int around(ProceedingJoinPoint jp) {
		int obj = 0;
		if(manager.loginCheck()) {
			try {
				obj = (Integer)jp.proceed();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}else {
			obj = -1;
		}
		return obj;
	}
}
