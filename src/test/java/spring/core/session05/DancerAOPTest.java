package spring.core.session05;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session05.aop_lab.Performance;

public class DancerAOPTest {

	@Test
	public void test() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AOPConfig.class);
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("aop-config.xml");
		Performance dancer = ctx.getBean("dancer",Performance.class);
		dancer.perform();
	}
}
