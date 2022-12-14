package spring.core.session03.beans;

import java.util.Random;

import org.springframework.beans.factory.FactoryBean;

public class CarFactory implements FactoryBean<Car>{

	@Override
	public Car getObject() throws Exception {
		Random r = new Random();
		Car car = new Car();
		car.setName("BMW");
		car.setPrice(r.nextInt(600_0000));
		return car;
	}

	@Override
	public Class<?> getObjectType() {
		
		return Car.class;
	}

	@Override
	public boolean isSingleton() {
		return false; //false 代表支援 Prototype
	}
}
