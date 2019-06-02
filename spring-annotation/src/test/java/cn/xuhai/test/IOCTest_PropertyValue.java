package cn.xuhai.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import cn.xuhai.bean.Person;
import cn.xuhai.config.MainConfigOfPropertyValue;

public class IOCTest_PropertyValue {

	public AnnotationConfigApplicationContext context;
	@Before
	public void befor() {
		context = new AnnotationConfigApplicationContext(MainConfigOfPropertyValue.class);
		System.out.println("容器创建完成。。");
	}
	private void printBeans(AnnotationConfigApplicationContext context) {
		String[] names = context.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
	}
	@Test
	public void test01() {
		printBeans(context);
		
		System.out.println("==============");
		Person person = context.getBean(Person.class);
		System.out.println(person);
		
		ConfigurableEnvironment environment = context.getEnvironment();
		String property = environment.getProperty("person.nickName");
		System.out.println(property);
		
		context.close();
	}

}
