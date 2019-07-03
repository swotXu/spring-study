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
import cn.xuhai.tx.TxConfig;
import cn.xuhai.tx.service.UserService;

public class IOCTest_tx {

	public AnnotationConfigApplicationContext context;
	@Before
	public void befor() {
		context = new AnnotationConfigApplicationContext(TxConfig.class);
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
		UserService service = context.getBean(UserService.class);
		service.insert();
		
		System.out.println("=======容器关闭=======");
		context.close();
	}

}
