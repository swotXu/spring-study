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
 * Profile：
 * 		Spring为我们提供的可以根据当前环境，动态的激活和切换一系列组件的功能
 * 
 * 开发环境、测试环境、生成环境
 * 数据源切换
 * 
 * @Profile：指定组件在哪个环境的情况下才能被注册到容器中。
 * 1）加了环境标识的bean，只有这个环境被激活的时候才能注册到容器。默认是default
 * 2）写在配置类上，只有在当前环境下才加载此类。
 * 3）没标注环境标识的bean在任何环境下都加载
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
