package spring.core.session05;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session05.aop_lab.Performance;
import spring.core.session05.aop_lab.Singer;

public class DancerAOPTest {

	@Test
	public void test() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AOPConfig.class);
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("aop-config.xml");
		Performance dancer = ctx.getBean("dancer",Performance.class);
		try {
			dancer.perform(); //舞者表演
		} catch (Exception e) { // 有意外發生
			// 舞者可以轉換跑道
			// 改行當歌手
			System.out.println("舞者跌倒 改行當歌手");
			// dancer 必須透過 經理人(Introducer) 轉換跑道-> singer
			Singer singer = (Singer)dancer;
			singer.sing();
			
		}
		
	}
}
