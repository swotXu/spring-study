package com.swotxu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

// jpa
@EnableJpaRepositories("com.swotxu.demo2.dao")
@EntityScan("com.swotxu.demo2.entity")
// mybatis
@MapperScan("com.swotxu.demo2.dao")
// spring
@ComponentScan("com.swotxu")
@EnableAutoConfiguration
//@SpringApplicationConfiguration
public class SpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplication.class, args);
	}
}
