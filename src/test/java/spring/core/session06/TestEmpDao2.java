package spring.core.session06;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session06.entity.Emp;
import spring.core.session06.templat.EmpDao;

public class TestEmpDao2 {
	
	@Test
	public void test() {
		//使用 xml 配置
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		EmpDao empDao = ctx.getBean("empDao" , EmpDao.class);
		List<Emp> emps = empDao.queryAll3();
		//emps.forEach(System.out::println);
		
		emps.stream().filter(a -> a.getAge()>25).forEach(System.out::println);
		
	}
}
