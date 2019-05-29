package cn.xuhai.config.impselector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector{

	/**
	 * AnnotationMetadata:��ǰ��ע@Importע����������ע����Ϣ
	 */
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		
		return new String[]{"cn.xuhai.bean.Blue","cn.xuhai.bean.Red"};
	}

}
