package spring.core.session05.aop;

import org.springframework.stereotype.Component;

@Component
public class MathCalcImpl implements MathCalc{

	//前置通知
	@Override
	public Integer add(Integer x, Integer y) {
		Integer result = x+y;
		return result;
	}
	//後置通知
	

	@Override
	public Integer div(Integer x, Integer y) {
		Integer result = x/y;
		return result;
	}

}
