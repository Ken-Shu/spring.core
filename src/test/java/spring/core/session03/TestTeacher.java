package spring.core.session03;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.session03.beans.Clazz;
import spring.core.session03.beans.Teacher;

public class TestTeacher {
	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Teacher t1 = ctx.getBean("teacher1" , Teacher.class);
		System.out.println(t1);
		//請印出 teacher1 的學生名子 與 該生的總學分 各是多少
		t1.getStudents().stream().forEach(p -> System.out.printf("%s  %d\n",p.getName(),p.getClazzs().stream().mapToInt(c -> c.getCredit()).sum()));
		
		//簡化配置 teacher2
		Teacher t2 = ctx.getBean("teacher2" , Teacher.class);
		System.out.println(t2);
		//請印出teahcer2 所教授的課程與學分數
		Clazz[] clazzs = {
				ctx.getBean("clazz1",Clazz.class),
				ctx.getBean("clazz2",Clazz.class),
				ctx.getBean("clazz3",Clazz.class)
		};
		t2.getSubjects()
		.forEach(name ->{
			Optional<Clazz> optclazz = Arrays.stream(clazzs)
					//.sequential() // 循序(default)
					.parallel() //平行處理提高執行效率
					.filter(cla -> cla.getName().equals(name))
					.findFirst();
			System.out.printf("科目 %s 學分 %s\n",name,optclazz.isPresent()?optclazz.get().getCredit():"None");
		});
		
		//循序 (default) 與平行處理若同時在一個 Stream 串流中發生 則系統是使用 sequential 還是 parallel ?
		//以最後設定為準
		System.out.println(
				Arrays.stream(clazzs)
				//.sequential() // 循序(default)
				.parallel() //平行處理提高執行效率
				.filter(cla -> cla.getName().equals("Java"))
				.isParallel()
				);
		
	}
}
