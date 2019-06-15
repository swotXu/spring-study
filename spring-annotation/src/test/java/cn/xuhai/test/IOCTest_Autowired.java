package cn.xuhai.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.xuhai.bean.Blue;
import cn.xuhai.bean.Boss;
import cn.xuhai.bean.Car;
import cn.xuhai.config.MainConfigOfAutowired;
import cn.xuhai.web.dao.MyDao;
import cn.xuhai.web.service.MyService;

public class IOCTest_Autowired {

	public AnnotationConfigApplicationContext context;
	@Before
	public void befor() {
		context = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
		System.out.println("容器创建完成。。");
	}
	@Test
	public void test01() {
		MyService bean = context.getBean(MyService.class);
		System.out.println(bean);
		
		MyDao bean2 = context.getBean(MyDao.class);
		System.out.println(bean2);
		//关闭容器
		context.close();
	}
	
	@Test
	public void test02() {
		Boss boss = context.getBean(Boss.class);
		System.out.println(boss);
		
		Car car = context.getBean(Car.class);

		System.out.println(boss.getCar() == car);
		//关闭容器
		context.close();
	}

}
