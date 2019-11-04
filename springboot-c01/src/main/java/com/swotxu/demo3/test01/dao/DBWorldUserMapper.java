package com.swotxu.demo3.test01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.swotxu.demo3.test01.entity.UserTest01;

public interface DBWorldUserMapper {

	@Select("select * from xh_user where id=#{id}")
	UserTest01 findByID(@Param("id") Integer id);
	
	@Select("select * from xh_user")
	List<UserTest01> findAll();
	
	@Insert("insert into xh_user values(null,#{user.username},#{user.age})")
	int insert(@Param("user")UserTest01 user);
}
