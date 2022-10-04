package spring.core.session06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session06.entity.Emp;
import spring.core.session06.templat.EmpDao;

public class TestEmpDao8_BatchAdd {
	
	@Test
	public void test() {
		//使用 xml 配置
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		EmpDao empDao = ctx.getBean("empDao" , EmpDao.class);
		
		List<Emp> rows = new ArrayList<Emp>();
		//                 放入 ename,age 的資料
		rows.add(new Emp ("Z01",41));
		rows.add(new Emp ("Z02",91));
		rows.add(new Emp ("Z03",71));
		int [] rowcounts = empDao.batchAdd2(rows);
		System.out.println(Arrays.toString(rowcounts));
		
	}
}
