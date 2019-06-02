package cn.xuhai.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.xuhai.config.MainConfigOfLifeCycle;

public class IOCTest_LifeCycle {

	public AnnotationConfigApplicationContext context1;
	@Before
	public void befor() {
		context1 = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
		System.out.println("容器创建完成。。");
	}
	@Test
	public void test01() {
		Object bean = context1.getBean("car");
		System.out.println(bean);
		context1.close();
	}
	@Test
	public void test02() {
//		Object bean = context1.getBean("cat");
//		System.out.println(bean);
		//关闭容器
		context1.close();
	}
}
