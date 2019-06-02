package cn.xuhai.bean;

import org.springframework.beans.factory.FactoryBean;
/**
 * ����һ��spring�����FactoryBean
 * @author apink
 */
public class ColorFactoryBean implements FactoryBean<Color> {

	// ����һ��Color��������������ӽ�����
	@Override
	public Color getObject() throws Exception {
		System.out.println("getObject()");
		return new Color();
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Color.class;
	}
	//�Ƿ����� true-���� false-����
	@Override
	public boolean isSingleton() {
		return false;
	}

}
