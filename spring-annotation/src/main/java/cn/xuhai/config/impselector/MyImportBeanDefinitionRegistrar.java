package cn.xuhai.config.impselector;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import cn.xuhai.bean.DefRegBean;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	/**
	 * AnnotationMetadata:��ǰ��ע@Importע����������ע����Ϣ
	 * BeanDefinitionRegistry: BeanDefinitionע����
	 * BeanDefinitionRegistry.registerBeanDefinition �ֶ�ע��
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		boolean red = registry.containsBeanDefinition("cn.xuhai.bean.Red");
		boolean blue = registry.containsBeanDefinition("cn.xuhai.bean.Blue");
		if(red && blue) {
			// ָ��bean��
			RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(DefRegBean.class);
			registry.registerBeanDefinition("defRegBean", rootBeanDefinition);
		}
	}

}
