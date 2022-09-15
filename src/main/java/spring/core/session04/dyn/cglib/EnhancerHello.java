package spring.core.session04.dyn.cglib;

//增強
//EnhancerHello 子類別 具有增強 Hello 父類別的特性
public class EnhancerHello extends Hello{

	@Override // 複寫 攔截的涵義(Intercepter)
	public String sayHello(String str) {
		String message = "請出示實聯QRcode";
		return super.sayHello(str) + message;
	}
	
}
