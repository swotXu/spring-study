package cn.xuhai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

import cn.xuhai.bean.Color;
import cn.xuhai.bean.Person;
import cn.xuhai.config.condition.LinuxCondition;
import cn.xuhai.config.condition.WindowsCondition;
import cn.xuhai.config.impselector.MyImportBeanDefinitionRegistrar;
import cn.xuhai.config.impselector.MyImportSelector;


@Conditional({WindowsCondition.class})	// 满足当前条件，这个类中配置的所有bean才生效
@Configuration //定义当前类为配置类
@Import({Color.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})	// 导入组件，id默认是组件的全类名
public class MainConfig2 {

	/**
	 * @Scope : prototype singleton request session
	 */
//	@Scope("prototype")
	@Lazy
	@Bean(name="person") // 给容器中注册一个bean。类型为返回值类型，id为方法名
	public Person person01() {
		System.out.println("给容器中注册person");
		return new Person("李四", 20);
	}
	
	/**
	 * @Conditional({Condition})	  注册bean（按照一定条件才注册）spring底层大量使用的注解
	 */
	@Conditional({WindowsCondition.class})
	@Bean(name="bill")
	public Person person02() {
		System.out.println("给容器中注册person");
		return new Person("Bill Gates", 25);
	}
	@Conditional({LinuxCondition.class})
	@Bean(name="linus")
	public Person person03() {
		System.out.println("给容器中注册person");
		return new Person("linus", 48);
	}
	
	/**
	 * 给容器中注册组件：
	 * 1）包扫描+组件标注注解（@Controller/@Service/@Repostiory/@Component）
	 * 2）@Bean[导入的第三方包里面的组件]
	 * 3）@Import[快速给容器中导入组件]
	 * 		a、@Import:要导入的类class
	 * 		b、ImportSelector:接口，返回需要导入的全类限定名数组
	 * 		c、ImportBeanDefinitionRegistrar:手动注册到bean
	 */
}
