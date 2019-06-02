package cn.xuhai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import cn.xuhai.bean.Person;

//使用 @PropertySource 读取外部配置文件，添加到运行环境变量中,加载完外部配置文件后使用${}取出配置文件的值
@PropertySource(value= {"classpath:/person.properties"})
@Configuration
public class MainConfigOfPropertyValue {

	@Bean
	public Person person() {
		return new Person();
	}
}
