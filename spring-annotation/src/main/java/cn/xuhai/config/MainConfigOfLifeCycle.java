package cn.xuhai.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import cn.xuhai.bean.Car;
/**
 * bean���������ڣ�����--��ʼ��--����
 * ��������bean���������ڣ�
 * �����Զ����ʼ�������ٷ�����
 * 
 * ���죨���󴴽���
 * 		��ʵ��������������ʱ��������
 * 		��ʵ������ÿ�λ�ȡ��ʱ�򴴽�
 * 
 * BeanPostProcessor.postProcessBeforeInitialization()
 * ��ʼ����
 * 		���󴴽���ɣ�����ֵ�ã����ó�ʼ����������
 * BeanPostProcessor.postProcessAfterInitialization()
 * 
 * ���٣�
 * 		��ʵ���������ر�ʱ����	
 * 		��ʵ������������������bean������������ٷ���
 * 
 * 
 * BeanPsotProcessorԭ��
 * populateBean(beanName, mbd, instanceWrapper); // ��bean�������Ը�ֵ
 * initializeBean
 * {
 * 	�����õ����������е�BeanPostProcessor������ִ��beforeInitialization
 * 	һ����������null������forѭ��������ִ�к�ߵ�BeanPostProcessor.postProcessBeforeInitialization
 * 	wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 * 	invokeInitMethods(beanName, wrappedBean, mbd); //ִ���Զ����ʼ��
 * 	wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 * }
 * 
 * 
 * ָ����ʼ�������ٷ�����
 * 1��ͨ��@Beanָ����init-method �� destroy-method
 * 2��ͨ����beanʵ��InitializingBean�������ʼ���߼�����DisposableBean�������߼���
 * 3����ʹ��JSR250�淶�е�ע��
 * 		@PostConstruct ��bean������ɲ������Ը�ֵ��ɣ�ִ�г�ʼ���������ڷ�����
 * 		@ProDestroy	����������bean֮ǰִ�С������ڷ�����
 * 4) BeanPostProcessor(�ӿ�): bean�ĺ��ô�����
 * 		��bean��ʼ��ǰ�����һЩ������
 * 		postProcessBeforeInitialization���ڳ�ʼ��֮ǰ����
 * 		postProcessAfterInitialization���ڳ�ʼ��֮����
 * 
 * Spring�ײ�� BeanPostProcessor ��ʹ��
 * 		bean��ֵ��ע�����������@Autowired,��������ע�⹦��
 * 
 * 
 * @author apink
 */
@ComponentScan("cn.xuhai.bean")
@Configuration
public class MainConfigOfLifeCycle {

//	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Bean(initMethod="init",destroyMethod="detory")
	public Car car() {
		return new Car();
	}
}
