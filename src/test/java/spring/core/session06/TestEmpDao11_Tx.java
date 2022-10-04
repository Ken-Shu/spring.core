package spring.core.session06;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session06.templat.EmpDao;

public class TestEmpDao11_Tx {
	
	@Test
	public void test() {
		//使用 xml 配置
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		EmpDao empDao = ctx.getBean("empDao" , EmpDao.class);
		//新增 2 筆資料
		int [] rowcount = empDao.addTwoTx("B03", 18, "B04", 19);
		if(rowcount != null) {
			System.out.println(Arrays.toString(rowcount));
		}
	
	}
}
