package cn.xuhai.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Boss {

	private Car car;
	
	@Autowired
	public Boss(Car car) {
		super();
		this.car = car;
		System.out.println("boss---�вι�����");
	}
	
	public Car getCar() {
		return car;
	}
	
//	@Autowired //ʹ�÷������������ioc������ȡ
	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "Boss [car=" + car + "]";
	}
	
}
