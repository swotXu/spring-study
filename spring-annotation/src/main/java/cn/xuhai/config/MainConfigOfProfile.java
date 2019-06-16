package cn.xuhai.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.xuhai.bean.Color;

/**
 * Profile��
 * 		SpringΪ�����ṩ�Ŀ��Ը��ݵ�ǰ��������̬�ļ�����л�һϵ������Ĺ���
 * 
 * �������������Ի��������ɻ���
 * ����Դ�л�
 * 
 * @Profile��ָ��������ĸ�����������²��ܱ�ע�ᵽ�����С�
 * 1�����˻�����ʶ��bean��ֻ����������������ʱ�����ע�ᵽ������Ĭ����default
 * 2��д���������ϣ�ֻ���ڵ�ǰ�����²ż��ش��ࡣ
 * 3��û��ע������ʶ��bean���κλ����¶�����
 * @author Administrator
 *
 */
@Profile("test")
@PropertySource(value={"classpath:dbconfig.properties"}, ignoreResourceNotFound = true)
@Configuration
public class MainConfigOfProfile implements EmbeddedValueResolverAware{
	@Value("${db.user}")
	private String user;
	
	private String driverClass;
	
//	@Profile("default")
	@Bean
	public Color color() {
		return new Color();
	}
	
	@Profile("test")
	@Bean
	public DataSource dataSourceTest(@Value("${db.password}")String psw) throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(user);
		dataSource.setPassword(psw);
		dataSource.setJdbcUrl("jdbc:mysql://192.168.1.30:3306/test");
		dataSource.setDriverClass(driverClass);
		return dataSource;
	}
	@Profile("dev")
	@Bean
	public DataSource dataSourceDev(@Value("${db.password}")String psw) throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(user);
		dataSource.setPassword(psw);
		dataSource.setJdbcUrl("jdbc:mysql://192.168.1.30:3306/dbkg_clgl");
		dataSource.setDriverClass(driverClass);
		return dataSource;
	}
	@Profile("prod")
	@Bean
	public DataSource dataSourceProd(@Value("${db.password}")String psw) throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(user);
		dataSource.setPassword(psw);
		dataSource.setJdbcUrl("jdbc:mysql://192.168.1.30:3306/jsyc");
		dataSource.setDriverClass(driverClass);
		return dataSource;
	}
	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		driverClass = resolver.resolveStringValue("${db.driverClass}");
		System.out.println("====== "+driverClass);
	}
	@Override
	public String toString() {
		return "MainConfigOfProfile [user=" + user + ", driverClass=" + driverClass + "]";
	}
	
}
