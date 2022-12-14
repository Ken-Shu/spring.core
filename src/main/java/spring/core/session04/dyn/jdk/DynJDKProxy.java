package spring.core.session04.dyn.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

//JDK 版 動態代理 (for all)
public class DynJDKProxy{
	// 代理的物件
	private Object object;
	
	//建構子注入要代理的實體
	public DynJDKProxy(Object object) {
		this.object = object;
	}

	//取得代理物件
	public Object getProxy() {
		Object proxyobj = null;
		// 1. 類別載入器
		ClassLoader loader = object.getClass().getClassLoader();
		// 2. 代理物件所實作的介面
		Class[] interfaces = object.getClass().getInterfaces();
		// 3. 處理代理的實現
		//public Object invoke(Object proxy, Method method, Object[] args)
		InvocationHandler invocationHandler = (proxy ,method, args) ->{
			Object result = null;
			// Before 前置通知
			// System.out.printf(" Before 前置通知 : %s 準備開始計算 參數 : %s\n",method.getName(),Arrays.toString(args));
			MyLogger.before(method, args);
			try {
				//執行代理物件的商業方法
				result = method.invoke(object, args);
			} catch (Exception e) {
				// Exception 例外異常通知
				// System.out.printf("例外異常通知 %s 異常 : %s\n",method.getName(),e);
				MyLogger.throwing(method, e);
			}finally {
				// End : 後置通知
				// System.out.printf("End 後置通知 %s 方法計算完成 \n",method.getName());
				MyLogger.end(method);
			}
			
			return result;
		};
		
		// 得到代理物件的實體
		proxyobj = Proxy.newProxyInstance(loader, interfaces, invocationHandler);
		return proxyobj;
	}

}
