package spring.core.session03;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session03.beans.Student;

public class TestStudent {
	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Student s1 = ctx.getBean("student1",Student.class);
		Student s2 = ctx.getBean("student2",Student.class);
		
		Student[] students = {s1,s2};
		Arrays.stream(students).forEach(System.out::println);
		
		//每一個學生修了幾學分
		int student1_credit = s1.getClazzs().stream().mapToInt(s -> s.getCredit()).sum();
		System.out.println("s1 credit = " + student1_credit);
		int student2_credit = s2.getClazzs().stream().mapToInt(s -> s.getCredit()).sum();
		System.out.println("s2 credit = " + student2_credit);
		
		
		//學生們總共修了幾學分
		int sum = Arrays.stream(students).mapToInt(s -> s.getClazzs().stream().mapToInt(c -> c.getCredit()).sum()).sum();
		System.out.println("All credit = " + sum);
		
	}
}
