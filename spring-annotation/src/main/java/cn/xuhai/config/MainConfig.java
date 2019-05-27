package cn.xuhai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import cn.xuhai.bean.Person;
import cn.xuhai.web.service.MyService;

@Configuration //���嵱ǰ��Ϊ������
@ComponentScan(value="cn.xuhai"
	,includeFilters= {
			@Filter(type=FilterType.ANNOTATION,value= {Repository.class}),
			@Filter(type=FilterType.ASSIGNABLE_TYPE,value= {MyService.class}),
			@Filter(type=FilterType.CUSTOM,value= {MytypeFilter.class})
			}
	,useDefaultFilters=false
)	// ��ɨ��
public class MainConfig {

	/**
	 * @Scope : prototype singleton request session
	 */
	@Scope
	@Bean(name="person") // ��������ע��һ��bean������Ϊ����ֵ���ͣ�idΪ������
	public Person person01() {
		System.out.println("��������ע��person");
		return new Person("����", 20);
	}
}
