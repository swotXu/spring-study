package cn.xuhai.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Dog implements ApplicationContextAware{

	private ApplicationContext applicationContext;
	
	public Dog() {
		System.out.println("dog constructor..");
	}
	@PostConstruct
	public void init() {
		System.out.println("dog init(@PostConstruct)..");
	}
	@PreDestroy
	public void detory() {
		System.out.println("dog detory(@PreDestroy)..");
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
