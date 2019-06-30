package cn.xuhai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

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
 * 	4.finishBeanFactoryInitialization(beanFactory);���BeanFactory������������ʣ�µĵ�ʵ��Bean
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
 * AnnotationAwareAspectJAutoProxyCreator��InstantiationAwareBeanPostProcessor�������ã�
 * 1.��ÿһ��bean����ǰ���� postProcessBeforeInstantiation()
 * 		����MathCalculator �� LogAspect �Ĵ���
 * 		a.�жϵ�ǰbean�Ƿ���advisedBeans��(������������Ҫ��ǿ��bean)
 * 		b.�жϵ�ǰbean�Ƿ��ǻ������� Advice.class��Advisor.class��AopInfrastructureBean.class;
 * 		 �����Ƿ������棨@Aspect��
 * 		c.�Ƿ���Ҫ����
 * 			1.��ȡ��ѡ����ǿ�������������֪ͨ��������List<Advisor> candidateAdvisors��
 * 				ÿһ����װ��֪ͨ��������ǿ����InstantiationModelAwarePointcutAdvisor
 * 				�ж�ÿһ����ǿ���Ƿ���AspectJPointcutAdvisor���ͣ�����true
 * 			2.������false
 * 2.��������
 * 3.bean��������� postProcessAfterInitialization()
 * 		return wrapIfNecessary(bean, beanName, cacheKey);//�����Ҫ�Ļ�  ��װ 
 * 		a.��ȡ��ǰbean��������ǿ����֪ͨ������ Object[] specificInterceptors
 * 			1���ҵ���ѡ��������ǿ��������Щ֪ͨ��������Ҫ���뵱ǰbean�����ģ�
 * 			2����ȡ������beanʹ�õ���ǿ��
 * 			3������ǿ������
 * 		b.���浱ǰbean��advisedBeans�У���������ǰbean�Ĵ������
 * 			1����ȡ������ǿ����֪ͨ������
 * 			2�����浽proxyFactory
 * 			3�������������Spring�Զ�����
 * 				new JdkDynamicAopProxy(config);	// jdk��̬����
 * 				new ObjenesisCglibAopProxy(config); // cglib��̬����
 * 		c.�������з��ص�ǰ���ʹ��cglib��ǿ�˵Ĵ������
 * 		d.�Ժ������л�ȡ���ľ����������Ĵ������ִ��Ŀ�귽����ʱ�򣬴������ͻ�ִ��֪ͨ����������
 * 4.ִ��Ŀ�귽��
 * 		�����б���������Ĵ������cglib��ǿ��Ķ��󣩣�������󱣴�����ϸ��Ϣ��������������Ŀ�����xxx��
 * 		a.����CglibAopProxy.intercept()����������Ŀ�귽����ִ��
 * 		b.����ProxyFactory�����ȡ��Ҫִ�е�Ŀ����������
 * 			List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 * 			1���������������� -��5���� һ��Ĭ�ϵ�ExposeInvocationInterceptor �� �ĸ���ǿ��
 * 				List<Object> interceptorList = new ArrayList<Object>(config.getAdvisors().length);
 * 			2���������е���ǿ��������תΪInterceptor[] = registry.getInterceptors(advisor);����
 * 			3������ǿ��תΪList<MethodInterceptor>��
 * 				�����MethodInterceptorֱ�Ӽ��뼯��
 * 				������ǣ�ʹ��AdvisorAdapter������תΪMethodInterceptor
 * 				��󷵻�MethodInterceptor[] -> Interceptor[]
 * 
 * 		c.���û������������ֱ��ִ��Ŀ�귽��
 * 			����������ÿһ��֪ͨ�����ֱ���װΪ����������������MethodInterceptor���ƣ�
 * 		d.�����������������ִ�е�Ŀ�����Ŀ�귽����Ŀ�������������������Ϣ���봴��һ��CglibMethodInvocation���󣬲�ִ��proceed();
 * 			Object retVal = new CglibMethodInvocation(proxy, target, method, args, targetClass, chain, methodProxy).proceed();
 * 		e.���������Ĵ�������
 * 			1�����û��������ִ��Ŀ�귽��������������������������������-1�Ĵ�Сһ����ִ�е������һ����������ִ��Ŀ�귽��
 * 			2����ʽ��ȡÿһ����������������ִ��invoke������ÿһ���������ȴ���һ��������ִ����ɺ�ִ�У�
 * 				�������Ļ��ƣ���֤֪ͨ������Ŀ�귽����ִ��˳��
 * 
 * �ܽ᣺
 * 	1��@EnableAspectJAutoProxy ����AOP���ܡ�
 * 	2��@EnableAspectJAutoProxy��������ע�������AnnotationAwareAspectJAutoProxyCreator��InstantiationAwareBeanPostProcessor��
 * 	3��AnnotationAwareAspectJAutoProxyCreator��һ�����ô�����
 * 	4�������Ĵ������̣�
 *		a��registerBeanPostProcessors ע����ô�����������AnnotationAwareAspectJAutoProxyCreator����
 *		b��finishBeanFactoryInitialization ��ʼ��ʣ�µĵ�ʵ��bean	
 *			1������ҵ���߼�������������
 *			2��AnnotationAwareAspectJAutoProxyCreator��������Ĵ�������
 *			3�������������֮���ж�����Ƿ���Ҫ��ǿ
 *				�ǣ������֪ͨ��������װ����ǿ����Advisor������ҵ���߼����󴴽��������Cglib��
 *	5��ִ��Ŀ�귽����
 *		1���������ִ��Ŀ�귽��
 *		2��CglibAopProxy.intercept()���� ����
 *			a���õ�Ŀ�귽����������������ǿ����װ��������MethodInterceptor��
 *			b����������������ʽ���ƣ����ν���ÿһ������������ִ��
 *			c��ִ��Ч����
 *				����ִ�У�ǰ��-��Ŀ�귽��-������֪ͨ-������֪ͨ
 *				�쳣ִ�У�ǰ��-��Ŀ�귽��-������֪ͨ-���쳣֪ͨ
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
