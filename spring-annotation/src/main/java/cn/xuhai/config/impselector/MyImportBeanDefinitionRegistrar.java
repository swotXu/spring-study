package cn.xuhai.config.impselector;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import cn.xuhai.bean.DefRegBean;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	/**
	 * AnnotationMetadata:当前标注@Import注解的类的所有注解信息
	 * BeanDefinitionRegistry: BeanDefinition注册类
	 * BeanDefinitionRegistry.registerBeanDefinition 手动注册
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		boolean red = registry.containsBeanDefinition("cn.xuhai.bean.Red");
		boolean blue = registry.containsBeanDefinition("cn.xuhai.bean.Blue");
		if(red && blue) {
			// 指定bean名
			RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(DefRegBean.class);
			registry.registerBeanDefinition("defRegBean", rootBeanDefinition);
		}
	}

}
