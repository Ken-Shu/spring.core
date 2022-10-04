package spring.core.session06;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session06.entity.Emp;
import spring.core.session06.entity.Job;
import spring.core.session06.templat.EmpDao;

public class TestEmpDao5 {
	
	@Test
	public void test() {
		//使用 xml 配置
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		EmpDao empDao = ctx.getBean("empDao" , EmpDao.class);
		// 單筆查詢測試
		Emp emp = empDao.getEmpById(1,true);
		System.out.println(emp);
//		Job job = empDao.getJobById(3);
//		System.out.println(job);
		
		Job job2 = empDao.getJobById(5, true);
		System.out.println(job2);
		
	}
}
