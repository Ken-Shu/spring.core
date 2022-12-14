package spring.core.session05;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import spring.core.session05.aop_lab.Introducer;

@Configuration //此為Java 配置檔
@ComponentScan //啟用元件掃描 預設會掃描 AOPConfig.class 所在目錄與其子目錄的Java程式檔
//@ComponentScan(basePackages = { 自訂要掃描的目錄
//		"spring.core.session05",
//		"spring.core.session04"
//})
@EnableAspectJAutoProxy
public class AOPConfig {
	@Bean
	public Introducer getIntroducer() { // 配置經理人
		return new Introducer();
	}
}
