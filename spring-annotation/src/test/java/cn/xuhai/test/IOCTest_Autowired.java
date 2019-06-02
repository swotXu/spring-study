package cn.xuhai.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

}
