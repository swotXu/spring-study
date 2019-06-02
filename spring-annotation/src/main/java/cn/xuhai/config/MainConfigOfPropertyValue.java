package cn.xuhai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import cn.xuhai.bean.Person;

//ʹ�� @PropertySource ��ȡ�ⲿ�����ļ�����ӵ����л���������,�������ⲿ�����ļ���ʹ��${}ȡ�������ļ���ֵ
@PropertySource(value= {"classpath:/person.properties"})
@Configuration
public class MainConfigOfPropertyValue {

	@Bean
	public Person person() {
		return new Person();
	}
}
