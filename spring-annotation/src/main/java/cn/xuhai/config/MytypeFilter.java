package cn.xuhai.config;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

public class MytypeFilter implements TypeFilter {

	@Override
	/**
	 * metadataReader ��ȡ���ĵ�ǰ�����Ϣ
	 * metadataReaderFactory	���Ի�ȡ�������κ������Ϣ
	 */
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
		// TODO Auto-generated method stub
		// ��ȡ��ǰ��ע����Ϣ
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		// ��ȡ��ǰ����ɨ��������Ϣ
		ClassMetadata classMetadata = metadataReader.getClassMetadata();
		// ��ȡ��ǰ����Դ�������Դ��
		Resource resource = metadataReader.getResource();
		
		String className = classMetadata.getClassName();
		System.out.println("---->" + className);
		
		return className.contains("er");
	}

}
