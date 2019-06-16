package cn.xuhai.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringValueResolver;

@Repository
public class Red implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware{

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		System.out.println("�����ioc��"+applicationContext);
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("��ǰbean���ƣ�" + name);
	}

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		String resolveStringValue = resolver.resolveStringValue("���${os.name},����#{19*17}");
		System.out.println("�������ַ�����"+resolveStringValue);
	}

}
