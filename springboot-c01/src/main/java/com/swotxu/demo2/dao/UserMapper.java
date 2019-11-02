package com.swotxu.demo2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.swotxu.demo2.entity.User;

@Repository
//@Mapper
public interface UserMapper {
	User findId(int id);
	
	@Select("select * from xh_user")
	List<User> findAll();
}
