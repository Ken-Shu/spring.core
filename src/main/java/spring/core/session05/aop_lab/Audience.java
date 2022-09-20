package spring.core.session05.aop_lab;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect //觀眾 :切面程式
public class Audience {
	
	@Pointcut(value = "execution(* spring.core.session05.aop_lab.Dancer.*(..))")
	public void pt() {}
	
	@Before(value = "pt()")
	public void closeCellPhone(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		System.out.printf("(前置通知@Before) %s : 觀眾關手機\n" , name);
	}
	
	@Before(value = "pt()")
	public void takeSeats(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		System.out.printf("(前置通知@Before) %s : 觀眾找座位\n" , name);
	}
	
	
	@After(value = "pt()")
	public void applause(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		System.out.printf("(後置通知@After) %s : 觀眾拍手鼓掌\n" , name);
	}
	
	@AfterReturning(value = "pt()")
	public void exit(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		System.out.printf("(返回通知@AfterReturning) %s : 觀眾離場\n" , name);
	}
	
	@AfterThrowing(value = "pt()")
	public void supries(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		System.out.printf("(例外通知@AfterThrowing) %s : 觀眾驚訝\n" , name);
	}
	
}
