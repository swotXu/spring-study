package cn.xuhai.web.dao;

import org.springframework.stereotype.Repository;

//名字默认首字母小写
@Repository
public class MyDao {

	private String lable = "1";

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	@Override
	public String toString() {
		return "MyDao [lable=" + lable + "]";
	}
	
}
