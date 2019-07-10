package cn.xuhai.tx;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.aop.framework.autoproxy.InfrastructureAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 声明式事务
 * 
 * 环境搭建：
 * 1、导入相关依赖（数据源、数据库驱动、Spring-jdbc模块）
 * 2、配置数据源、JdbcTemplate(spring提供的简化数据库操作工具)操作数据
 * 3、给方法加上标注 @Transactional 声明式
 * 
 * 事务
 * 4、@EnableTransactionManagement 开启基于注解的事务管理
 * 5、配置事务管理器来控制事务  PlatformTransactionManager对象
 * 
 * 
 * 原理：
 * 1、@EnableTransactionManagement 利用 TransactionManagementConfigurationSelector 给容器中导入组件
 * 		导入两个组件
 * 		AutoProxyRegistrar
 * 		ProxyTransactionManagementConfiguration
 * 2、AutoProxyRegistrar：给容器中注册一个 InfrastructureAdvisorAutoProxyCreator 组件
 * 		InfrastructureAdvisorAutoProxyCreator 后置处理器 分析其具体功能
 * 		利用后置处理器机制，在对象创建以后，包装对象，返回一个代理对象（增强器），代理对象执行方法利用拦截器链进行调用。
 * 
 * 3、ProxyTransactionManagementConfiguration 作用
 * 		a、给容器中注册事务增强器
 * 			1、事务增强器要用事务注解的信息：AnnotationTransactionAttributeSource 解析事务注解
 * 			2、事务拦截器：
 * 				TransactionInterceptor：保存了事务属性信息，事务管理器
 * 				它是一个MethodInterceptor：在目标方法执行时，执行拦截器链。
 * 					a、先获取事务相关属性
 * 					b、再获取PlatformTransactionManager，如果事先没指定任何TransactionManager,最终会按照类型获取
 * 					c、执行目标方法，
 * 						如果异常，获得到事务管理器，利用事务管理器回归
 * 						如果正常，利用事务管理器提交事务
 * 
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
