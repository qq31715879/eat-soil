package com.hug.web.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// 使用@Component注解说明这是一个Spring Bean
@Component
// 使用@Aspect注解说明这是一个切面(Aspect)
/**
 * Aspect = Advice(做什么) + Pointcut(在哪做)
 * 
 * Advice - 执行Logging相关操作
 * 
 * Pointcut - 在方法的开始和结束处执行
 */
@Aspect
public class MyAspect {
	/**
	 * call：针对所有的调用者(caller)，即哪里调用了Pointcut表达式匹配的方法，在该方法被执行之前就会被匹配到；
	 * 而我们经常使用的execution则是针对所有的被调用方法，而不会care是谁调用的该方法
	 */
	private final static String execution = "execution(* org.kael.maven.study.aspect.TestAspect.*(..)) "
			+ "|| @annotation(org.kael.maven.study.annotation.MyAnnotation) "
			+ "|| @annotation(org.kael.maven.study.annotation.MyAnnotation2)"
			+ "|| execution(* org.kael.maven.study.*.update*(..))";
	
	@SuppressWarnings("unused")
	private final static String call = "call(* org.kael.maven.study.aspect.TestAspect.call(..))";

	// 使用@Before注解来表达切面的类型，也就是在方法执行之前会首先调用Advice
	// 在@Before注解中使用表达式execution(* *(..))来表达Pointcut的概念
	// 实现beforeLogging方法，它代表了具体的Advice
	// 使用JoinPoint参数来得到将要调用的方法信息

	// 第一个* : 任意的返回类型
	// 第二个* : 任意的方法名
	// (..) : 任意的方法参数
	// execution(* register()) - 匹配所有不接受参数，名为register的方法，不限返回类型

	// execution(int register(int,int)) -
	// 匹配接受两个int类型作为参数，名为register的方法，且返回类型为int类型

	// execution(* com.destiny1020..*AuthService.register(..) -
	// 匹配com.destiny1020子包下的类名以AuthService结尾的所有类中的register重载

	// execution(* register(*)) - 匹配接受1个不限定参数类型，名为register的方法，不限返回类型

	/**
	 * execution(* com.destiny1020.service..*.load(..))
	 * 
	 * ||
	 * 
	 * execution(* com.destiny1020.repository..*.load(..))
	 * 
	 * 它的意思很直观，即匹配service包或者repository包下的所有名为load的方法及其重载，不考虑参数数量和类型，也不考虑返回类型。
	 */

	@Before(execution)
	public void beforeLogging(JoinPoint joinPoint) {
		System.out.println("@Before " + joinPoint.getStaticPart().getSignature().toString());
	}
	
//	@Before(call)
//	public void beforeCall(JoinPoint joinPoint) {
//		System.out.println("进入 " + joinPoint.getStaticPart().getSignature().toString());
//	}

	@After(execution)
	public void afterLogging(JoinPoint joinPoint) {
		System.out.println("@After " + joinPoint.getSignature());

		// 获取调用目标方法时的参数
		for (Object arg : joinPoint.getArgs()) {
			System.out.println("@After参数 : " + arg);
		}
	}

	@Around(execution)
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		String minfo = pjp.getStaticPart().getSignature().toString();
		System.out.println("@Around " + minfo);
		try {
			return pjp.proceed();
		} catch (Throwable ex) {
			System.out.println("@Around异常 " + minfo);
			throw ex;
		}
	}

	@AfterReturning(pointcut = execution, returning = "result")
	public void returnLogging(String result) {
		System.out.println("@AfterReturning " + result);
	}

	@AfterThrowing(pointcut = execution, throwing = "iae")
	public void throwLogging(IllegalArgumentException iae) {
		System.out.println("@AfterThrowing ");
	}
}
