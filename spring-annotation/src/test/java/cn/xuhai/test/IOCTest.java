package cn.xuhai.test;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import cn.xuhai.bean.Person;
import cn.xuhai.config.MainConfig2;

public class IOCTest {
	public ApplicationContext context1;
	public ApplicationContext context2;
	@Before
	public void befor() {
//		context1 = new AnnotationConfigApplicationContext(MainConfig.class);
		context2 = new AnnotationConfigApplicationContext(MainConfig2.class);
	}

	@Test
	public void test1() {
		printBeans(context1);
	}
	@Test
	public void test2() {
		Object bean = context2.getBean("person");
		System.out.println(bean);
		Object bean2 = context2.getBean("person");
		System.out.println(bean == bean2);
	}
	
	@Test
	public void test3() {
		// 动态获取环境变量  Windows 10
		Environment environment = context2.getEnvironment();
		String property = environment.getProperty("os.name");
		System.out.println(property);
		
		String[] namesForType = context2.getBeanNamesForType(Person.class);
		for (String name : namesForType) {
			System.out.println(name);
		}
		Map<String, Person> persons = context2.getBeansOfType(Person.class);
		System.out.println(persons);
	}
	
	private void printBeans(ApplicationContext context) {
		String[] names = context.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
	}
	
	@Test
	public void testImport() {
		printBeans(context2);
		
		//工厂bean
		Object bean1 = context2.getBean("colorFactoryBean");
		Object bean2 = context2.getBean("colorFactoryBean");
		System.out.println("bean的类型："+bean1.getClass());
		System.out.println(bean1 == bean2);
		
		Object bean3 = context2.getBean("&colorFactoryBean");
		System.out.println("bean的类型："+bean3.getClass());
	}
}
