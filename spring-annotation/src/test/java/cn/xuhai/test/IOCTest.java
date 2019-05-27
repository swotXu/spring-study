package cn.xuhai.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.xuhai.config.MainConfig;

public class IOCTest {

	@Test
	public void test1() {
		ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
		String[] names = context.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
	}
}
