package spring.core.session05.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AdviceName;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component 	//可被Spring管理的元件
@Aspect 	//切面程式
public class MyLoggerAspect {
	
	//建立一個切入點的方法
	@Pointcut(value = "execution(* spring.core.session05.aop.MathCalcImpl.*(..))")
	public void pt() {}
	
	@Pointcut(value = "execution(* spring.core.session05.aop.MathCalcImpl.div(..))")
	public void pt2() {}
	
	/*
	
	//前置通知 Advice : 執行連接點之前所要執行的程式
	//@Before(value = "execution(public Integer spring.core.session05.aop.MathCalcImpl.add(Integer, Integer))") //切入點表達式 Spring EL : execution(..)
	//@Before(value = "execution(public Integer spring.core.session05.aop.MathCalcImpl.*(Integer, Integer))")
	//@Before(value = "execution(public Integer spring.core.session05.aop.MathCalcImpl.*(..))") // *號表示任意方法 .. 表示方法中有 0~N個參數
	//@Before(value = "execution(* spring.core.session05.aop.MathCalcImpl.*(..))") // 第一個 * 號 表示任意修飾字 及 任意回傳型別
	//@Before(value = "execution(* spring.core.session05.aop.*.*(..))") // // *.* 表示任意(類).任意(方法)
	//@Before(value = "execution(* *(..))") // 全部通通攔截
	@Before(value = "pt()")
	//@Before(value = "pt() && !pt2()") // 滿足 pt() 原則 AND 不滿足 pt2() 原則
	public void beforeAdvice(JoinPoint joinPoint) { //joinPoint 連接點
		String methodName = joinPoint.getSignature().getName(); //getSignature 取的方法簽章 .getName 取的方法簽章的名稱
		Object[] args = joinPoint.getArgs();
		System.out.printf("前置通知(@Before)  方法名稱 = %s  方法參數 : %s\n",methodName,Arrays.toString(args));
	}
	
	// 後置通知 Advice : 執行連接點呼叫完畢之後所要執行的程式, 在 SpringAOP 的機制上是被配置在finally區段
	// (不論目標方法是否有例外發生 後置通知都會執行) 
	// 無法在後置通知目標方法中得到回傳值
	@After(value = "pt()")
	public void afterAdvice(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();	
		System.out.printf("後置通知(@After)  方法名稱 = %s \n",methodName);
	}
	
	// 返回通知 Advice 可以得到目標方法的回傳值
	// 不過若目標方法發生例外 則返回通知不會執行
	@AfterReturning(value = "pt()" , returning = "result") // 設定將目標方法的回傳值放到 result 的變數中
	public void afterRetruningAdvice(JoinPoint joinPoint , Object result) { // result 取得目標方法的回傳值 (必須搭配上方 returning = "result" 的設定)		
		String methodName = joinPoint.getSignature().getName();
		System.out.printf("返回通知(@AfterReturning)  方法名稱 = %s  目標方法回傳值 : %s\n",methodName,result);	
	}
	
	// 異常通知 Advice 可以捕獲目標方法因執行錯誤所引發的例外資料
	
	@AfterThrowing(value = "pt()", throwing = "ex") // 設定將目標方法所拋出的錯誤資料放到 ex 的變數中
	public void afterThrowingAdvice(JoinPoint joinPoint, Throwable ex) { // 搭配 throwing= "ex"
		String methodName = joinPoint.getSignature().getName();
		System.out.printf("異常通知(@AfterThrowing)  方法名稱 = %s  發生例外 : %s\n",methodName,ex);	
	}
	
	*/
	
	// 環繞通知 Around Advice (功能強)
	// 使用前注意 : 啟用環繞通知時 建議先將上述通知全部關閉 比較容易閱讀執行結果
	// 環繞通知 可以改變前端所收到的結果 也可以決定是否要執行目標方法
	@Around(value = "pt()")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		String methodName = proceedingJoinPoint.getSignature().getName();
		Object[] args = proceedingJoinPoint.getArgs();
		Object result = null;
		
		//前置通知
		System.out.printf("(環繞)前置通知(@Around)  方法名稱 = %s  方法參數 : %s\n",methodName,Arrays.toString(args));
		
		try {
			//目標方法的業務邏輯(一定要加入)
			result = proceedingJoinPoint.proceed();
			// 返回通知
			System.out.printf("(環繞)返回通知(@Around)  方法名稱 = %s  目標方法回傳值 : %s\n",methodName,result);
		} catch (Throwable ex) {
			result = 0;
			// 異常通知
			System.out.printf("(環繞)異常通知(@Around)  方法名稱 = %s  發生例外 : %s\n",methodName,ex);
		}finally {
			// 後置通知
			System.out.printf("(環繞)後置通知(@Around) 方法名稱 = %s \n",methodName);
		}
		
		
		return result;
	}
	
}