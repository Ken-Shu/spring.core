package spring.core.session06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session06.entity.Emp;
import spring.core.session06.templat.EmpDao;

public class TestEmpDao10_BatchDelete {
	
	@Test
	public void test() {
		//使用 xml 配置
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		EmpDao empDao = ctx.getBean("empDao" , EmpDao.class);
		// 刪除 eid = 11 的紀錄
		int rowcount = empDao.deleteEmpById(11);
		if(rowcount == 0) {
			System.out.println(" 刪除失敗 或 無資料可供刪除");
			return;
		}else {
			System.out.println("刪除成功");
			System.out.println(rowcount);
		}
		
	
	}
}
