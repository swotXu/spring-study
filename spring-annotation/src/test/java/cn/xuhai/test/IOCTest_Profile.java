package cn.xuhai.test;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.xuhai.config.MainConfigOfProfile;

public class IOCTest_Profile {

	public AnnotationConfigApplicationContext context;
	
	@Before
	public void befor() {
		context = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);
		System.out.println("����������ɡ���");
	}
	public void print(AnnotationConfigApplicationContext context) {
		String[] names = context.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
	}
	
	//1.ʹ�������ж�̬�����������������ز��� -Dspring.profiles.active=dev
	@Test
	public void print() {
		String[] names = context.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
	}
	//2.���뷽ʽ�����
	@Test
	public void test03() {
		//1.����ApplicationContext
		AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext();
		//2.������Ҫ����Ļ���
		acac.getEnvironment().setActiveProfiles("dev");
		//3.ע����������
		acac.register(MainConfigOfProfile.class);
		//4.ˢ������
		acac.refresh();
		
		print(acac);
	}
	
	@Test
	public void test01() {
		MainConfigOfProfile bean = context.getBean(MainConfigOfProfile.class);
		System.out.println(bean);
		ComboPooledDataSource test = (ComboPooledDataSource) context.getBean("dataSourceTest");
		System.out.println(test.getUser());
		System.out.println(test.getPassword());
	}
	@Test
	public void test02() {
		String[] beanNamesForType = context.getBeanNamesForType(DataSource.class);
		for (String name : beanNamesForType) {
			System.out.println(name);
		}
		
		context.close();
	}
}
