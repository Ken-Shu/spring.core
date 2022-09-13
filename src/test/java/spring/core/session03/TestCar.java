package spring.core.session03;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session03.beans.Car;

public class TestCar {
	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//對 carFactory 要兩台車
		Car c1 = ctx.getBean("carFactory",Car.class);
		Car c2 = ctx.getBean("carFactory",Car.class);
		System.out.println(c1);
		System.out.println(c2);
	}
}
