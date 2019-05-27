package cn.xuhai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import cn.xuhai.bean.Person;
import cn.xuhai.web.service.MyService;

@Configuration
@ComponentScan(value="cn.xuhai"
	,includeFilters= {
//			@Filter(type=FilterType.ANNOTATION,value= {Repository.class}),
//			@Filter(type=FilterType.ASSIGNABLE_TYPE,value= {MyService.class}),
			@Filter(type=FilterType.CUSTOM,value= {MytypeFilter.class})
			}
	,useDefaultFilters=false
)
public class MainConfig {

	@Bean(name="person") // 给容器中注册一个bean。类型为返回值类型，id为方法名
	public Person person01() {
		return new Person("李四", 20);
	}
}
