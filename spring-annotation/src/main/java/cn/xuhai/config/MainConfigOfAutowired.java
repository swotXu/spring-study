package cn.xuhai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import cn.xuhai.web.dao.MyDao;

/**
 * �Զ�װ��
 * 		Spring��������ע��(DI),��ɶ�IOC�����и��������������ϵ��ֵ��
 * 
 * 1��@Autowired [Springԭ��ע��] �Զ�ע��:
 * 		a.Ĭ�����Ȱ�������ȥ�������Ҷ�Ӧ�������context.getBean(MyDao.class);�ҵ��͸�ֵ
 * 		b.����ҵ������ͬ���͵�������ٽ���������Ϊ�����idȥ�����в��ң�context.getBean("myDao");
 * 		c.@Qualifier("myDao")��ʹ��@Qualifier��ȷָ��װ������ID��������ʹ��������
 * 		d.�Զ�װ��Ĭ��һ��Ҫ�����Ը�ֵ�ã�û�оͻᱨ��
 * 		  	��ʹ��@Autowired(required=false)��
 * 		e.@Primary,��Spring�����Զ�װ���ʱ��Ĭ��ʹ����ѡ��bean
 * 		  	Ҳ�ɼ���ʹ��@Qualifierָ����Ҫװ���bean������
 * 		MyService{
 * 			@Autowired
 * 			MyDao myDao;
 * 		}
 * 2��@Resource(JSR250�淶) �� @Inject(JSR330�淶)[Java�淶ע��] �Զ�ע��:
 * 		@Resource�����Ժ�@Autowired һ��ʵ���Զ�װ�书�ܣ�Ĭ���ǰ���������װ�䡣
 * 					û��֧��@Primary��@Autowired(required=false)����
 * 		@Inject����Ҫ���� javax.inject������Autowired�Ĺ���һ������û��required=false����
 * 
 * ����@Autowired��spring����ģ�@Resource��@Inject��Java�淶
 * 
 * AutowiredAnnotationBeanPostProcessor����������Զ�װ�书�ܵ�
 * 
 * 3)@Autowired�������������������������Զ���ʹ��
 * 		a.��ע�ڷ���λ��
 * 		b.��ע�ڹ�����λ��
 * 		c.��ע�ڲ���λ��
 * @author apink
 */
@ComponentScan({"cn.xuhai.web.controller","cn.xuhai.web.service","cn.xuhai.web.dao","cn.xuhai.bean"})
@Configuration
public class MainConfigOfAutowired {
	
	@Primary
	@Bean(name = "myDao2")
	public MyDao myDao() {
		MyDao myDao = new MyDao();
		myDao.setLable("2");
		return myDao;
	}
}
