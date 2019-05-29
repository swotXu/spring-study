package cn.xuhai.config.impselector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector{

	/**
	 * AnnotationMetadata:当前标注@Import注解的类的所有注解信息
	 */
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		
		return new String[]{"cn.xuhai.bean.Blue","cn.xuhai.bean.Red"};
	}

}
