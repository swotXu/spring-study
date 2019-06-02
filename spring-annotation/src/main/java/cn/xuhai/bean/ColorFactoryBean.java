package cn.xuhai.bean;

import org.springframework.beans.factory.FactoryBean;
/**
 * 创建一个spring定义的FactoryBean
 * @author apink
 */
public class ColorFactoryBean implements FactoryBean<Color> {

	// 返回一个Color对象，这个对象会添加进容器
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
	//是否单例？ true-单例 false-多例
	@Override
	public boolean isSingleton() {
		return false;
	}

}
