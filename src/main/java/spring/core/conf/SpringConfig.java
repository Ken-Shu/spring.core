package spring.core.conf;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import spring.core.session01.beans.Hello;
import spring.core.session01.beans.Lotto;
import spring.core.session01.beans.Lucky;

// Spring Java 配置 可以用來取代 conf/applicationContext.xml 配置
// 宣告此類是一個配置定義
@Configuration
public class SpringConfig {
	
	// <bean id="hello" class="spring.core.session01.beans.Hello"/>
	@Bean(name = "hello")
	public Hello getHello() {
		Hello hello = new Hello();
		return hello;
	}
	@Bean(name = {"lotto","lotto2"})
	public Lotto getLotto() {
		return new Lotto();
	}
	@Bean(name = {"lucky","lucky2"})
	@Scope(SCOPE_PROTOTYPE)
	public Lucky getLucky() {
		return new Lucky();
	}
}
