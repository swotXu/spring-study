package cn.xuhai.config;

import org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import cn.xuhai.aop.LogAspects;
import cn.xuhai.aop.MathCalculator;

/**
 * AOP������̬����
 * 		ָ���ڳ��������ڼ䶯̬�Ľ�ĳ�δ������뵽ָ������ָ��λ�ý������еı�̷�ʽ��
 * 
 * 1.����aopģ�飺Spring AOP (spring-aspects)
 * 2.����ҵ����(MathCalculator)����ҵ���߼�����ǰ���쳣ʱ��ӡ��־
 * 3.������־������(LogAspects)��������Ҫ��֪����ҵ���������״̬
 * 	֪ͨ������
 * 		ǰ��֪ͨ(@Before)������֪ͨ(@After)������֪ͨ(@AfterReturning)���쳣֪ͨ(@AfterThrowing)
 * 		����֪ͨ(@Around)����̬�����ֶ��ƽ�Ŀ�귽�����У�joinPoint.procced()ִ��Ŀ�귽��)
 * 4.���������Ŀ�귽����ע��ʱ�ε����У�֪ͨע�⣩
 * 5.���������ҵ��Ŀ���߼��඼���뵽������
 * 6.�������spring�ĸ���������(����ע��@Aspect)
 * 7.���������м� @EnableAspectJAutoProxy ����������ע���aopģʽ��
 * 		��Spring���кܶ�@EnableXXXע�⡣
 * 
 * AOPԭ��������������ע��ʲô�����������ʲôʱ������������ʲô����
 * 		@EnableAspectJAutoProxy
 * 1.@EnableAspectJAutoProxy ��ʲô��
 * 		@Import(AspectJAutoProxyRegistrar.class)�������е��� AspectJAutoProxyRegistrar 	
 * 			AspectJAutoProxyRegistrar�Զ����������ע��bean
 * 			internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator
 * 		��������ע��һ��AnnotationAwareAspectJAutoProxyCreator��bean
 * 
 * 2.AnnotationAwareAspectJAutoProxyCreator��
 * 		|-��AspectJAwareAdvisorAutoProxyCreator
 * 			|-��AbstractAdvisorAutoProxyCreator
 * 				|-��AbstractAutoProxyCreator implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 * 					��עbean���ô��������ܡ��Զ�װ��BeanFactory
 * 
 * ��ͼ��ϵ
 * 1��.AbstractAutoProxyCreator ���෽����
 *  BeanFactory�ӿ�ʵ�֣�
 * 		AbstractAutoProxyCreator.setBeanFactory()
 *  BeanPostProcessor�ӿ�ʵ�֣�
 * 		AbstractAutoProxyCreator.postProcessBeforeInstantiation()
 * 		AbstractAutoProxyCreator.postProcessAfterInitialization()
 * 
 * 2��.AbstractAdvisorAutoProxyCreator ���෽����
 * 		AbstractAdvisorAutoProxyCreator.setBeanFactory() -�� initBeanFactory()
 * 
 * 3��.AspectJAwareAdvisorAutoProxyCreator ���෽��
 * 
 * 4��.AnnotationAwareAspectJAutoProxyCreator ����
 * 		AnnotationAwareAspectJAutoProxyCreator.initBeanFactory()
 * 		
 * 
 * ���̣�
 * 	1.����ioc������new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
 * 	2.ע�������࣬����refresh()ˢ������
 * 	3.registerBeanPostProcessors(beanFactory);ע��bean�ĺ��ô�����������bean�Ĵ���
 * 		a.�Ȼ�ȡioc�����Ѷ������Ҫ�������������BeanPostProcessor
 * 			String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);
 * 		b.�������мӱ��BeanPostProcessor
 * 			beanFactory.addBeanPostProcessor(new BeanPostProcessorChecker(beanFactory, beanProcessorTargetCount));
 * 		c.����ע��ʵ����PriorityOrdered��BeanPostProcessor
 * 		d.�ٸ�������ע��ʵ����Ordered�ӿڵ�BeanpostProcessor
 * 		e.ע��ûʵ�����ȼ��ӿڵ�BeanPostProcessor
 * 		f.ע��BeanPostProcessor,ʵ���Ͼ��Ǵ���BeanPostProcessor���󣬱�����������
 * 			����internalAutoProxyCreator��BeanPostProcessor��AnnotationAwareAspectJAutoProxyCreator��
 * 			1������Bean��ʵ��
 * 			2��populateBean����bean�ĸ������Ը�ֵ
 * 			3��initializeBean����ʼ��bean
 * 				1��invokeAwareMethods()������Aware�ӿڵķ����ص�
 * 				2��applyBeanPostProcessorsBeforeInitialization()��Ӧ�ú��ô�������PostProcessorsBeforeInitialization����
 * 				3��invokeInitMethods()��ִ���Զ���ĳ�ʼ������
 * 				4��applyBeanPostProcessorsAfterInitialization()��ִ�д�������postProcessAfterInitialization����
 * 			4��BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)�����ɹ���--�� aspectJAdvisorsBuilder
 * 		g.��BeanPostProcessorע�ᵽBeanFactory��
 * 			beanFactory.addBeanPostProcessor(postProcessor);
 * =======�����Ǵ�����ע��AnnotationAwareAspectJAutoProxyCreator�Ĺ���=======
 * 
 * 
 * 			AnnotationAwareAspectJAutoProxyCreator =�� InstantiationAwareBeanPostProcessor
 * 	4.finishBeanFactoryInitialization(beanFactory);���BeanFactory��ʼ������������ʣ�µĵ�ʵ��Bean
 * 		a.������ȡ���������е�Bean(beanDefinitionNames)�����δ�������
 * 			getBean(beanName) -> doGetBean() -> getSingleton() -> createBean()
 * 		b.����bean
 * 			��AnnotationAwareAspectJAutoProxyCreator������bean����֮ǰ����һ�����أ�InstantiationAwareBeanPostProcessor�����postProcessBeforeInstantiation();��
 * 			1���ȴӻ����л�ȡ��ǰbean�����û��ȡ�����򴴽���ֻҪ�����õ�bean���ᱻ��������
 * 			2��createBean();����bean��
 * 				AnnotationAwareAspectJAutoProxyCreator�����κ�bean����֮ǰ�����Է���bean��ʵ��
 * 				��BeanPostProcessor����Bean���󴴽���ɳ�ʼ��ǰ����õġ�
 * 				��InstantiationAwareBeanPostProcessor���ڴ���beanʵ��֮ǰ�ȳ����ú��ô��������ض���ġ�
 * 				1)resolveBeforeInstantiation(beanName, mbd);
 * 					ϣ�����ô������ڴ��ܷ���һ�������������ܷ��ش�������ʹ�ã�������ܷ��ؾͼ���
 * 					a.���ô������ȳ��Է��ض���
 * 						bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 * 						�õ����к��ô������������InstantiationAwareBeanPostProcessor��
 * 						��ִ��postProcessBeforeInstantiation(beanClass, beanName);
 * 						if (bean != null) {
							bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
						}
 * 				2)doCreateBean(beanName, mbd, args);������ȥ����һ��beanʵ������3.f����һ��
 * 
 * 
 * @author apink
 *
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {

	@Bean
	public MathCalculator mathCalculator() {
		return new MathCalculator();
	}
	
	@Bean
	public LogAspects logAspects() {
		return new LogAspects();
	}
}
