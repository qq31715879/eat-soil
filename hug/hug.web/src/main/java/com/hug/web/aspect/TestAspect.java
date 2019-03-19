package com.hug.web.aspect;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.hug.web.annotation.MyAnnotation;
import com.hug.web.annotation.MyAnnotation2;

@Component
public class TestAspect {
	

	public void testAspect(String demo){
		System.out.println(demo);
	}
	
	@MyAnnotation
	public void demo(){
		demo2();
	}
	
	@MyAnnotation2
	public void demo2(){
	}
	
	public void call(){
		System.out.println("call");
	}
	
	public void update1(){
		System.out.println("update1");
	}
	
	public void update2(){
		System.out.println("update2");
	}
	
	public void update3(){
		System.out.println("update3");
	}
	
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("spring/spring-mvc-kael.xml");
		TestAspect aspect = ioc.getBean(TestAspect.class);
//		aspect.testAspect("demo");
		aspect.update1();
//		aspect.demo();
//		aspect.demo2();
//		aspect.call();
	}
}
