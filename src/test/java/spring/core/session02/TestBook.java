package spring.core.session02;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session02.beans.Book;



public class TestBook {
	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Book b1 = ctx.getBean("book2",Book.class);
		System.out.println(b1);
		
		Book b2 = ctx.getBean("book3",Book.class);
		System.out.println(b2);
		
		Book b3 = ctx.getBean("book4",Book.class);
		System.out.println(b3);
		
		Book b4 = ctx.getBean("book5",Book.class);
		System.out.println(b4);
		
		Book book6 = ctx.getBean("book6",Book.class);
		System.out.println(book6);
	}
}
