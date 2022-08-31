package spring.core.session01;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.conf.SpringConfig;
import spring.core.session01.beans.Hello;

// 測試日期 : 2022/08/30
// 測試人員 : Ken
public class TestHello {
	@Test
	public void test() {
		//傳統測法 要先建立 Hello 物件才能使用
		Hello hello = new Hello();
		System.out.println(hello);
		
		// 利用 Spring 來取的所管理的 Hello 物件
		// xml 定義: conf/applicationContext.xml
		// ApplicationContext 應用程式環境 = Spring 環境
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//只有在定義一個Hello <bean> 配置下才可以使用
		Hello h2 = ctx.getBean(Hello.class);
		System.out.println(h2);
		
		Hello h3 = ctx.getBean("hello", Hello.class);
		System.out.println(h3);
		
		//Java 配置 : spring.core.SpringConfig.java
		ApplicationContext ctx2 = new AnnotationConfigApplicationContext(SpringConfig.class);
		Hello h4 = ctx2.getBean(Hello.class);
		Hello h5 = ctx2.getBean("hello",Hello.class);
		System.out.println(h4);
		System.out.println(h5);
	}
}
