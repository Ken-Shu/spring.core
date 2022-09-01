package spring.core.session02;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session02.beans.material.Paper;
import spring.core.session02.beans.material.PaperRedB3;

public class TestPaper {
	
	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//		Paper p1 = ctx.getBean("paper",Paper.class);
//		System.out.println(p1);
		
		Paper p2 = ctx.getBean("paper2",Paper.class);
		System.out.println(p2);
		
		PaperRedB3 prb = ctx.getBean("paper3",PaperRedB3.class);
		System.out.println(prb);
		
		Paper p3 = ctx.getBean("paper4",Paper.class);
		System.out.println(p3);
	}
}
