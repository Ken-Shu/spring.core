package spring.core.session02;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session02.beans.Author;

public class TestAuthor {
	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Author author1 = ctx.getBean("author1", Author.class);
		System.out.println(author1);
		//進行手動注入
		author1.setName("Ken");
		author1.setSex('M');
		author1.setAge(18);
		System.out.println(author1);
		
		//Author2 在配置檔中 已經有預設注入資料(利用方法注入)
		Author author2 = ctx.getBean("author2",Author.class);
		System.out.println(author2);
		
		//Author3 在配置檔中 利用建構子注入
		Author author3 = ctx.getBean("author3",Author.class);
		System.out.println(author3);
		
		//Author4 在配置檔中 利用 p: 簡易版的方法注入
		Author author4 = ctx.getBean("author4",Author.class);
		System.out.println(author4);
		
		//Author5 在配置檔中 利用 c: 簡易版的建構子注入
		Author author5 = ctx.getBean("author5",Author.class);
		System.out.println(author5);
	}
}
