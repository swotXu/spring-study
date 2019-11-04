package com.swotxu.demo3.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;

@Configuration
//basePackages 最好分开配置 如果放在同一个文件夹可能会报错
@MapperScan(basePackages = "com.swotxu.demo3.test01", sqlSessionTemplateRef = "test1SqlSessionTemplate")
public class TestMyBatisConfig1 {
	// 配置数据源
	@Primary
	@Bean(name = "test1DataSource")
	public DataSource testDataSource(DBConfig1 test1Config) throws SQLException {
		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
		mysqlXaDataSource.setUrl(test1Config.getUrl());
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXaDataSource.setPassword(test1Config.getPassword());
		mysqlXaDataSource.setUser(test1Config.getUsername());
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mysqlXaDataSource);
		xaDataSource.setUniqueResourceName("test1DataSource");

		xaDataSource.setMinPoolSize(test1Config.getMinPoolSize());
		xaDataSource.setMaxPoolSize(test1Config.getMaxPoolSize());
		xaDataSource.setMaxLifetime(test1Config.getMaxLifetime());
		xaDataSource.setBorrowConnectionTimeout(test1Config.getBorrowConnectionTimeout());
		xaDataSource.setLoginTimeout(test1Config.getLoginTimeout());
		xaDataSource.setMaintenanceInterval(test1Config.getMaintenanceInterval());
		xaDataSource.setMaxIdleTime(test1Config.getMaxIdleTime());
		xaDataSource.setTestQuery(test1Config.getTestQuery());
		return xaDataSource;
	}

	@Primary
	@Bean(name = "test1SqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		return bean.getObject();
	}

	@Primary
	@Bean(name = "test1SqlSessionTemplate")
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
