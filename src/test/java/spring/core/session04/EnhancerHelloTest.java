package spring.core.session04;

import org.junit.Test;

import spring.core.session04.dyn.cglib.EnhancerHello;
import spring.core.session04.dyn.cglib.Hello;

public class EnhancerHelloTest {
	
	@Test
	public void test() {
		Hello h1 = new Hello();
		System.out.println(h1.sayHello("John"));
		
		// 增強版
		Hello h2 = new EnhancerHello();
		System.out.println(h2.sayHello("John"));
	}
}
