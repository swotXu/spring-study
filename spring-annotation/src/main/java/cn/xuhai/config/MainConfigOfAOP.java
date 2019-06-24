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
 * AOP：【动态代理】
 * 		指能在程序运行期间动态的将某段代码切入到指定方法指定位置进行运行的编程方式；
 * 
 * 1.导入aop模块：Spring AOP (spring-aspects)
 * 2.定义业务类(MathCalculator)：在业务逻辑运行前后及异常时打印日志
 * 3.定义日志切面类(LogAspects)：方法需要感知到到业务类的运行状态
 * 	通知方法：
 * 		前置通知(@Before)、后置通知(@After)、返回通知(@AfterReturning)、异常通知(@AfterThrowing)
 * 		环绕通知(@Around)：动态代理，手动推进目标方法运行（joinPoint.procced()执行目标方法)
 * 4.给切面类的目标方法标注何时何地运行（通知注解）
 * 5.将切面类和业务目标逻辑类都加入到容器中
 * 6.必须告诉spring哪个是切面类(加上注解@Aspect)
 * 7.给配置类中加 @EnableAspectJAutoProxy 【开启基于注解的aop模式】
 * 		在Spring中有很多@EnableXXX注解。
 * 
 * AOP原理：【看给容器中注册什么组件，这个组件什么时候工作，功能是什么。】
 * 		@EnableAspectJAutoProxy
 * 1.@EnableAspectJAutoProxy 是什么？
 * 		@Import(AspectJAutoProxyRegistrar.class)：容器中导入 AspectJAutoProxyRegistrar 	
 * 			AspectJAutoProxyRegistrar自定义给容器中注册bean
 * 			internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator
 * 		给容器中注册一个AnnotationAwareAspectJAutoProxyCreator的bean
 * 
 * 2.AnnotationAwareAspectJAutoProxyCreator：
 * 		|-》AspectJAwareAdvisorAutoProxyCreator
 * 			|-》AbstractAdvisorAutoProxyCreator
 * 				|-》AbstractAutoProxyCreator implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 * 					关注bean后置处理器功能、自动装配BeanFactory
 * 
 * 类图关系
 * 1）.AbstractAutoProxyCreator 父类方法：
 *  BeanFactory接口实现：
 * 		AbstractAutoProxyCreator.setBeanFactory()
 *  BeanPostProcessor接口实现：
 * 		AbstractAutoProxyCreator.postProcessBeforeInstantiation()
 * 		AbstractAutoProxyCreator.postProcessAfterInitialization()
 * 
 * 2）.AbstractAdvisorAutoProxyCreator 父类方法：
 * 		AbstractAdvisorAutoProxyCreator.setBeanFactory() -》 initBeanFactory()
 * 
 * 3）.AspectJAwareAdvisorAutoProxyCreator 父类方法
 * 
 * 4）.AnnotationAwareAspectJAutoProxyCreator 方法
 * 		AnnotationAwareAspectJAutoProxyCreator.initBeanFactory()
 * 		
 * 
 * 流程：
 * 	1.创建ioc容器：new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
 * 	2.注册配置类，调用refresh()刷新容器
 * 	3.registerBeanPostProcessors(beanFactory);注册bean的后置处理器来拦截bean的创建
 * 		a.先获取ioc容器已定义的需要创建对象的所有BeanPostProcessor
 * 			String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);
 * 		b.给容器中加别的BeanPostProcessor
 * 			beanFactory.addBeanPostProcessor(new BeanPostProcessorChecker(beanFactory, beanProcessorTargetCount));
 * 		c.优先注册实现了PriorityOrdered的BeanPostProcessor
 * 		d.再给容器中注册实现了Ordered接口的BeanpostProcessor
 * 		e.注册没实现优先级接口的BeanPostProcessor
 * 		f.注册BeanPostProcessor,实际上就是创建BeanPostProcessor对象，保存在容器中
 * 			创建internalAutoProxyCreator的BeanPostProcessor【AnnotationAwareAspectJAutoProxyCreator】
 * 			1）创建Bean的实例
 * 			2）populateBean：给bean的各种属性赋值
 * 			3）initializeBean：初始化bean
 * 				1）invokeAwareMethods()；处理Aware接口的方法回调
 * 				2）applyBeanPostProcessorsBeforeInitialization()；应用后置处理器的PostProcessorsBeforeInitialization方法
 * 				3）invokeInitMethods()；执行自定义的初始化方法
 * 				4）applyBeanPostProcessorsAfterInitialization()；执行处理器的postProcessAfterInitialization方法
 * 			4）BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功，--》 aspectJAdvisorsBuilder
 * 		g.把BeanPostProcessor注册到BeanFactory中
 * 			beanFactory.addBeanPostProcessor(postProcessor);
 * =======以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程=======
 * 
 * 
 * 			AnnotationAwareAspectJAutoProxyCreator =》 InstantiationAwareBeanPostProcessor
 * 	4.finishBeanFactoryInitialization(beanFactory);完成BeanFactory初始化工作，创建剩下的单实例Bean
 * 		a.遍历获取容器中所有的Bean(beanDefinitionNames)，依次创建对象
 * 			getBean(beanName) -> doGetBean() -> getSingleton() -> createBean()
 * 		b.创建bean
 * 			【AnnotationAwareAspectJAutoProxyCreator在所有bean创建之前会有一个拦截，InstantiationAwareBeanPostProcessor会调用postProcessBeforeInstantiation();】
 * 			1）先从缓存中获取当前bean，如果没获取到，则创建。只要创建好的bean都会被缓存起来
 * 			2）createBean();创建bean；
 * 				AnnotationAwareAspectJAutoProxyCreator会在任何bean创建之前，尝试返回bean的实例
 * 				【BeanPostProcessor是在Bean对象创建完成初始化前后调用的】
 * 				【InstantiationAwareBeanPostProcessor是在创建bean实例之前先尝试用后置处理器返回对象的】
 * 				1)resolveBeforeInstantiation(beanName, mbd);
 * 					希望后置处理器在此能返回一个代理对象。如果能返回代理对象就使用，如果不能返回就继续
 * 					a.后置处理器先尝试返回对象
 * 						bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 * 						拿到所有后置处理器，如果是InstantiationAwareBeanPostProcessor，
 * 						就执行postProcessBeforeInstantiation(beanClass, beanName);
 * 						if (bean != null) {
							bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
						}
 * 				2)doCreateBean(beanName, mbd, args);真正的去创建一个bean实例。和3.f流程一样
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
