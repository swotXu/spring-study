package com.swotxu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.swotxu.demo3.datasource.DBConfig1;
import com.swotxu.demo3.datasource.DBConfig2;

// 开启注解配置扫描
@EnableConfigurationProperties(value = { DBConfig1.class, DBConfig2.class })
// jpa
@EnableJpaRepositories("com.swotxu.demo2.dao")
@EntityScan("com.swotxu.demo2.entity")
// mybatis
//@MapperScan("com.swotxu.demo2.dao")	// 使用多数据源-注释此处
// spring
//@ComponentScan("com.swotxu")
//@EnableAutoConfiguration
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplication.class, args);
	}
}
