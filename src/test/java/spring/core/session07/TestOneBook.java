package spring.core.session07;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session07.tx.controller.BookController;
import spring.core.session07.tx.exception.InsufficientAmount;
import spring.core.session07.tx.exception.InsufficientQuantity;

public class TestOneBook {

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		BookController bookController = ctx.getBean("bookController",BookController.class);
		try {
			bookController.buyOneBook(1, 1); // wid = 1  bid = 1
		} catch (InsufficientAmount | InsufficientQuantity e) {
			System.out.println(e);
		}
		
	}
}
