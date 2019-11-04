//package com.swotxu.demo3.datasource;
//
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//@Configuration // 注册到springboot容器中
//@MapperScan(basePackages = "com.swotxu.demo3.test01", sqlSessionFactoryRef = "test1SqlSessionFactory")
//public class DataSource1Config {
//	/**
//	 * @methodDesc: 功能描述:(配置test1数据库)
//	 */
//	@Bean(name = "test1DataSource")
//	@Primary
//	@ConfigurationProperties(prefix = "spring.datasource.test1")
//	public DataSource testDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//
//	/**
//	 * @methodDesc: 功能描述:(test1 sql会话工厂)
//	 */
//	@Bean(name = "test1SqlSessionFactory")
//	@Primary
//	public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource)
//			throws Exception {
//		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//		bean.setDataSource(dataSource);
////		bean.setMapperLocations(
////				new PathMatchingResourcePatternResolver().getResources("classpath:mapping/*Mapper.xml"));
//		return bean.getObject();
//	}
//
//	/**
//	 * @methodDesc: 功能描述:(test1 事物管理)
//	 */
//	@Bean(name = "test1TransactionManager")
//	@Primary
//	public DataSourceTransactionManager testTransactionManager(@Qualifier("test1DataSource") DataSource dataSource) {
//		return new DataSourceTransactionManager(dataSource);
//	}
//
//	@Bean(name = "test1SqlSessionTemplate")
//	public SqlSessionTemplate testSqlSessionTemplate(
//			@Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//		return new SqlSessionTemplate(sqlSessionFactory);
//	}
//
//}
//
