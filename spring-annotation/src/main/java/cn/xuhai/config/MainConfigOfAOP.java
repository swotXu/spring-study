package cn.xuhai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

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
 * 	4.finishBeanFactoryInitialization(beanFactory);完成BeanFactory化工作，创建剩下的单实例Bean
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
 * AnnotationAwareAspectJAutoProxyCreator【InstantiationAwareBeanPostProcessor】的作用：
 * 1.在每一个bean创建前调用 postProcessBeforeInstantiation()
 * 		关心MathCalculator 和 LogAspect 的创建
 * 		a.判断当前bean是否在advisedBeans中(保存了所有需要增强的bean)
 * 		b.判断当前bean是否是基础类型 Advice.class、Advisor.class、AopInfrastructureBean.class;
 * 		 或者是否是切面（@Aspect）
 * 		c.是否需要跳过
 * 			1.获取候选的增强器（切面里面的通知方法）【List<Advisor> candidateAdvisors】
 * 				每一个封装的通知方法的增强器是InstantiationModelAwarePointcutAdvisor
 * 				判断每一个增强器是否是AspectJPointcutAdvisor类型，返回true
 * 			2.都返回false
 * 2.创建对象
 * 3.bean创建后调用 postProcessAfterInitialization()
 * 		return wrapIfNecessary(bean, beanName, cacheKey);//如果需要的话  包装 
 * 		a.获取当前bean的所有增强器（通知方法） Object[] specificInterceptors
 * 			1、找到候选的所有增强器（找哪些通知方法是需要切入当前bean方法的）
 * 			2、获取到能在bean使用的增强器
 * 			3、给增强器排序
 * 		b.保存当前bean在advisedBeans中，并创建当前bean的代理对象
 * 			1、获取所有增强器（通知方法）
 * 			2、保存到proxyFactory
 * 			3、创建代理对象：Spring自动决定
 * 				new JdkDynamicAopProxy(config);	// jdk动态代理
 * 				new ObjenesisCglibAopProxy(config); // cglib动态代理
 * 		c.给容器中返回当前组件使用cglib增强了的代理对象
 * 		d.以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程
 * 4.执行目标方法
 * 		容器中保存了组件的代理对象（cglib增强后的对象），这个对象保存了详细信息（比如增加器，目标对象，xxx）
 * 		a.进入CglibAopProxy.intercept()方法，拦截目标方法的执行
 * 		b.根据ProxyFactory对象获取将要执行的目标拦截器链
 * 			List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 * 			1、保存所有拦截器 -》5个； 一个默认的ExposeInvocationInterceptor 和 四个增强器
 * 				List<Object> interceptorList = new ArrayList<Object>(config.getAdvisors().length);
 * 			2、遍历所有的增强器，将其转为Interceptor[] = registry.getInterceptors(advisor);对象
 * 			3、将增强器转为List<MethodInterceptor>；
 * 				如果是MethodInterceptor直接加入集合
 * 				如果不是，使用AdvisorAdapter适配器转为MethodInterceptor
 * 				最后返回MethodInterceptor[] -> Interceptor[]
 * 
 * 		c.如果没有拦截器链，直接执行目标方法
 * 			拦截器链（每一个通知方法又被包装为方法拦截器，利用MethodInterceptor机制）
 * 		d.如果有拦截器链，将执行的目标对象，目标方法，目标参数，拦截器链等信息传入创建一个CglibMethodInvocation对象，并执行proceed();
 * 			Object retVal = new CglibMethodInvocation(proxy, target, method, args, targetClass, chain, methodProxy).proceed();
 * 		e.拦截器链的触发过程
 * 			1、如果没有拦截器执行目标方法，或者拦截器的索引和拦截器数组-1的大小一样（执行到了最后一个拦截器）执行目标方法
 * 			2、链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截器等待下一个拦截器执行完成后执行；
 * 				拦截器的机制，保证通知方法与目标方法的执行顺序。
 * 
 * 总结：
 * 	1、@EnableAspectJAutoProxy 开启AOP功能。
 * 	2、@EnableAspectJAutoProxy给容器中注入组件：AnnotationAwareAspectJAutoProxyCreator【InstantiationAwareBeanPostProcessor】
 * 	3、AnnotationAwareAspectJAutoProxyCreator是一个后置处理器
 * 	4、容器的创建流程：
 *		a、registerBeanPostProcessors 注册后置处理器，创建AnnotationAwareAspectJAutoProxyCreator对象
 *		b、finishBeanFactoryInitialization 初始化剩下的单实例bean	
 *			1、创建业务逻辑组件和切面组件
 *			2、AnnotationAwareAspectJAutoProxyCreator拦截组件的创建过程
 *			3、在组件创建完之后，判断组件是否需要增强
 *				是：切面的通知方法，包装成增强器（Advisor），给业务逻辑对象创建代理对象（Cglib）
 *	5、执行目标方法：
 *		1、代理对象执行目标方法
 *		2、CglibAopProxy.intercept()方法 拦截
 *			a、得到目标方法的拦截器链（增强器包装成拦截器MethodInterceptor）
 *			b、利用拦截器的链式机制，依次进入每一个拦截器进行执行
 *			c、执行效果：
 *				正常执行：前置-》目标方法-》后置通知-》返回通知
 *				异常执行：前置-》目标方法-》后置通知-》异常通知
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
