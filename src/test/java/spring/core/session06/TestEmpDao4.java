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

public class TestEmpDao4 {
	
	@Test
	public void test() {
		//使用 xml 配置
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		EmpDao empDao = ctx.getBean("empDao" , EmpDao.class);
		List<Job> jobs = empDao.queryJobs();
		//emps.forEach(System.out::println);
		
		// 想要印出 每個員工有哪些工作
		jobs.forEach(j -> System.out.printf("姓名 : %s  工作 : %s\n",j.getJname(),j.getEmp().getEname() == null? "":j.getEmp().getEname()));
		
	}
}
