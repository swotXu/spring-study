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
 * ����ʽ����
 * 
 * �������
 * 1�������������������Դ�����ݿ�������Spring-jdbcģ�飩
 * 2����������Դ��JdbcTemplate(spring�ṩ�ļ����ݿ��������)��������
 * 3�����������ϱ�ע @Transactional ����ʽ����
 * 4��@EnableTransactionManagement ��������ע����������
 * 5�������������������������  PlatformTransactionManager����
 * 
 * @author apink
 *
 */
@EnableTransactionManagement
@ComponentScan("cn.xuhai.tx")
@Configuration
public class TxConfig {
	// ��������
	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("dreamsoft");
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/world");
		return dataSource;
	}
	// ����ģ�����
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}
	// ��������������
	@Bean
	public PlatformTransactionManager platformTransactionManager() throws PropertyVetoException {
		return new DataSourceTransactionManager(dataSource());
	}

}
