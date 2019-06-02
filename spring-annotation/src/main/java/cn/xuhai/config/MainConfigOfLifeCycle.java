package cn.xuhai.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import cn.xuhai.bean.Car;
/**
 * bean的生命周期：创建--初始化--销毁
 * 容器管理bean的生命周期；
 * 可以自定义初始化和销毁方法。
 * 
 * 构造（对象创建）
 * 		单实例：在容器启动时创建对象
 * 		多实例：在每次获取的时候创建
 * 
 * BeanPostProcessor.postProcessBeforeInitialization()
 * 初始化：
 * 		对象创建完成，并赋值好，调用初始化方法。。
 * BeanPostProcessor.postProcessAfterInitialization()
 * 
 * 销毁：
 * 		单实例：容器关闭时调用	
 * 		多实例：容器不会管理这个bean，不会调用销毁方法
 * 
 * 
 * BeanPsotProcessor原理
 * populateBean(beanName, mbd, instanceWrapper); // 给bean经行属性赋值
 * initializeBean
 * {
 * 	遍历得到容器中所有的BeanPostProcessor：挨个执行beforeInitialization
 * 	一但方法返回null，跳出for循环，不会执行后边的BeanPostProcessor.postProcessBeforeInitialization
 * 	wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 * 	invokeInitMethods(beanName, wrappedBean, mbd); //执行自定义初始化
 * 	wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 * }
 * 
 * 
 * 指定初始化和销毁方法；
 * 1）通过@Bean指定：init-method 和 destroy-method
 * 2）通过让bean实现InitializingBean（定义初始化逻辑），DisposableBean（销毁逻辑）
 * 3）可使用JSR250规范中的注解
 * 		@PostConstruct 在bean创建完成并且属性赋值完成，执行初始化。作用在方法上
 * 		@ProDestroy	在容器销毁bean之前执行。作用在方法上
 * 4) BeanPostProcessor(接口): bean的后置处理器
 * 		在bean初始化前后进行一些处理工作
 * 		postProcessBeforeInitialization：在初始化之前工作
 * 		postProcessAfterInitialization：在初始化之后工作
 * 
 * Spring底层对 BeanPostProcessor 的使用
 * 		bean赋值，注入其他组件，@Autowired,生命周期注解功能
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
