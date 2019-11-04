package com.swotxu.demo3.test02.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.swotxu.demo3.test02.entity.RoleTest02;

public interface DBNetctossRoleMapper {

	@Select("select * from role where id=#{id}")
	RoleTest02 findByID(@Param("id") Integer id);
	
	@Select("select * from role")
	List<RoleTest02> findAll();
	
	@Insert("insert into role values(null,#{name})")
	int insert(@Param("name")String name);
}
