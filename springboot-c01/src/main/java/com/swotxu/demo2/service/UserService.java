package com.swotxu.demo2.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.swotxu.demo2.dao.UserJpaDao;
import com.swotxu.demo2.dao.UserMapper;
import com.swotxu.demo2.entity.User;
import com.swotxu.demo2.entity.UserJpa;

@Service
public class UserService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserJpaDao userJpaDao;
	
	
	public void createUser(String name, Integer age) {
		System.out.println("createUser 开始");
		jdbcTemplate.update("insert into xh_user values(null,?,?)", name, age);
		System.out.println("createUser 成功");
	}
	
	public String findUserById(Integer id) {
		System.out.println("findUserById 开始");
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList("select * from xh_user where id=?",id);
		System.out.println("findUserById 成功");
		System.out.println(queryForList);
		
		String username = (String) queryForList.get(0).get("username");
		int age = (Integer)queryForList.get(0).get("age");
		String result = username + "," + age;
		System.out.println(result);
		return result;
	}
	
	public User findId(Integer id) {
		System.out.println("findId-mybatis 开始" + id);
		User user = userMapper.findId(id);
		System.out.println("findId-mybatis 成功");
		System.out.println(user);
		return user;
	}
	
	public List<User> findAll() {
		System.out.println("findAll-mybatis 开始");
		List<User> users = userMapper.findAll();
		System.out.println("findAll-mybatis 成功");
		System.out.println(users);
		return users;
	}
	
	public List<UserJpa> findAllJpa() {
		System.out.println("findAllJpa-Jpa 开始");
		List<UserJpa> users = userJpaDao.findAll();
		System.out.println("findAllJpa-Jpa 成功");
		System.out.println(users);
		return users;
	}
}
