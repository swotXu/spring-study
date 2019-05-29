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


@Conditional({WindowsCondition.class})	// ���㵱ǰ����������������õ�����bean����Ч
@Configuration //���嵱ǰ��Ϊ������
@Import({Color.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})	// ���������idĬ���������ȫ����
public class MainConfig2 {

	/**
	 * @Scope : prototype singleton request session
	 */
//	@Scope("prototype")
	@Lazy
	@Bean(name="person") // ��������ע��һ��bean������Ϊ����ֵ���ͣ�idΪ������
	public Person person01() {
		System.out.println("��������ע��person");
		return new Person("����", 20);
	}
	
	/**
	 * @Conditional({Condition})	  ע��bean������һ��������ע�ᣩspring�ײ����ʹ�õ�ע��
	 */
	@Conditional({WindowsCondition.class})
	@Bean(name="bill")
	public Person person02() {
		System.out.println("��������ע��person");
		return new Person("Bill Gates", 25);
	}
	@Conditional({LinuxCondition.class})
	@Bean(name="linus")
	public Person person03() {
		System.out.println("��������ע��person");
		return new Person("linus", 48);
	}
	
	/**
	 * ��������ע�������
	 * 1����ɨ��+�����עע�⣨@Controller/@Service/@Repostiory/@Component��
	 * 2��@Bean[����ĵ���������������]
	 * 3��@Import[���ٸ������е������]
	 * 		a��@Import:Ҫ�������class
	 * 		b��ImportSelector:�ӿڣ�������Ҫ�����ȫ���޶�������
	 * 		c��ImportBeanDefinitionRegistrar:�ֶ�ע�ᵽbean
	 */
}
