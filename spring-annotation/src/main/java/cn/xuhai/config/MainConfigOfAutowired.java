package cn.xuhai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import cn.xuhai.web.dao.MyDao;

/**
 * 自动装配
 * 		Spring利用依赖注入(DI),完成对IOC容器中各个组件的依赖关系赋值。
 * 
 * 1）@Autowired [Spring原生注解] 自动注入:
 * 		a.默认优先按照类型去容器中找对应的组件：context.getBean(MyDao.class);找到就赋值
 * 		b.如果找到多个相同类型的组件，再将属性名作为组件的id去容器中查找：context.getBean("myDao");
 * 		c.@Qualifier("myDao")，使用@Qualifier明确指定装配的组件ID，而不是使用属性名
 * 		d.自动装配默认一定要将属性赋值好，没有就会报错；
 * 		  	可使用@Autowired(required=false)。
 * 		e.@Primary,让Spring进行自动装配的时候，默认使用首选的bean
 * 		  	也可继续使用@Qualifier指定需要装配的bean的名字
 * 		MyService{
 * 			@Autowired
 * 			MyDao myDao;
 * 		}
 * 2）@Resource(JSR250规范) 和 @Inject(JSR330规范)[Java规范注解] 自动注入:
 * 		@Resource：可以和@Autowired 一样实现自动装配功能，默认是按照属性名装配。
 * 					没能支持@Primary和@Autowired(required=false)功能
 * 		@Inject：需要导入 javax.inject包，和Autowired的功能一样。但没有required=false功能
 * 
 * 区别：@Autowired：spring定义的；@Resource和@Inject：Java规范
 * 
 * AutowiredAnnotationBeanPostProcessor：解析完成自动装配功能的
 * 
 * 3)@Autowired：构造器，参数，方法，属性都能使用
 * 		a.标注在方法位置
 * 		b.标注在构造器位置
 * 		c.标注在参数位置
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
