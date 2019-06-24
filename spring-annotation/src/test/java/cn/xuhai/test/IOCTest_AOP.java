package cn.xuhai.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.sun.org.apache.bcel.internal.util.ClassPath;

import cn.xuhai.aop.MathCalculator;
import cn.xuhai.bean.Person;
import cn.xuhai.config.MainConfigOfAOP;
import cn.xuhai.config.MainConfigOfPropertyValue;

public class IOCTest_AOP {

	public AnnotationConfigApplicationContext context;
	@Before
	public void befor() {
		context = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
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
		MathCalculator math = context.getBean(MathCalculator.class);
		int div = math.div(5, 0);
		System.out.println(div);
		
		context.close();
	}

}
