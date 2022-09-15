package spring.core.session04.sta.proxy;

public class Man implements Person{

	@Override
	public void work() {
		//業務邏輯
		double d = 10/0;
		System.out.println(d); //引發錯誤
		System.out.println("去 Google 上班");
		
		
	}	
}
