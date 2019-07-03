package cn.xuhai.tx;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 声明式事务
 * 
 * 环境搭建：
 * 1、导入相关依赖（数据源、数据库驱动、Spring-jdbc模块）
 * 2、配置数据源、JdbcTemplate(spring提供的简化数据库操作工具)操作数据
 * 3、给方法加上标注 @Transactional 声明式事务
 * 4、@EnableTransactionManagement 开启基于注解的事务管理
 * 5、配置事务管理器来控制事务  PlatformTransactionManager对象
 * 
 * @author apink
 *
 */
@EnableTransactionManagement
@ComponentScan("cn.xuhai.tx")
@Configuration
public class TxConfig {
	// 创建连接
	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("dreamsoft");
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/world");
		return dataSource;
	}
	// 创建模板对象
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}
	// 创建事务管理对象
	@Bean
	public PlatformTransactionManager platformTransactionManager() throws PropertyVetoException {
		return new DataSourceTransactionManager(dataSource());
	}

}
