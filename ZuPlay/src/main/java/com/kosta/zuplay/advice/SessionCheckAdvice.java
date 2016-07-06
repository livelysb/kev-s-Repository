package com.kosta.zuplay.advice;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.kosta.zuplay.exception.LoginException;

@Component
@Aspect
public class SessionCheckAdvice {

	//@Pointcut("args(javax.servlet.http.HttpSession,..)")
	@Pointcut("execution(public * com.kosta.zuplay.controller.*Controller.*(javax.servlet.http.HttpSession,..))")
	public void pointCut() {}

	@Before("pointCut()")
	public void before(JoinPoint point) throws LoginException {
		Object obj = point.getArgs()[0];
		HttpSession session = null;
		if (obj instanceof HttpSession) {
			session = (HttpSession) obj;
			if (session.getAttribute("playerNickname") == null) {
				try {
					System.out.println("로그인");
					throw new LoginException();
				} catch (LoginException e) {
					  throw new LoginException();
				}
			}
		}
	}
}
