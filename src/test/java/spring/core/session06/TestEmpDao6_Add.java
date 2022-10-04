package spring.core.session06;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session06.templat.EmpDao;

public class TestEmpDao6_Add {
	
	@Test
	public void test() {
		//使用 xml 配置
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		EmpDao empDao = ctx.getBean("empDao" , EmpDao.class);
		int rowcount1 = empDao.addOne1("X03", 90);
		System.out.println(rowcount1);
		int rowcount2 = empDao.addOne2("X02", 39);
		System.out.println(rowcount2);
		
		System.out.println("add OK!");
		
	}
}
