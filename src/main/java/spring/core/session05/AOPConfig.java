package spring.core.session05;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration //此為Java 配置檔
@ComponentScan //啟用元件掃描 預設會掃描 AOPConfig.class 所在目錄與其子目錄的Java程式檔
//@ComponentScan(basePackages = { 自訂要掃描的目錄
//		"spring.core.session05",
//		"spring.core.session04"
//})
@EnableAspectJAutoProxy
public class AOPConfig {
	
}
