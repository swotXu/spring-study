package com.swotxu.demo2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swotxu.demo2.entity.User;
import com.swotxu.demo2.entity.UserJpa;
import com.swotxu.demo2.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	// http://127.0.0.1:8080/createUser
	// jdbcTemplate
	@RequestMapping("/createUser")
	public String createUser(String name,Integer age) {
		userService.createUser(name, age);
		return "success";
	}
	
	// http://127.0.0.1:8080/findUserById?id=1
	// jdbcTemplate
	@RequestMapping("/findUserById")
	public String findUserById(Integer id) {
		return userService.findUserById(id);
	}
	
	// http://127.0.0.1:8080/findId?id=1
	// mapper.xml
	@RequestMapping("/findId")
	public User findId(Integer id) {
		return userService.findId(id);
	}
	
	// http://127.0.0.1:8080/findAll
	// mybatis-annotation
	@RequestMapping("/findAll")
	public List<User> findAll() {
		return userService.findAll();
	}
	
	// http://127.0.0.1:8080/findAllJpa
	// jpa
	@RequestMapping("/findAllJpa")
	public List<UserJpa> findAllJpa() {
		return userService.findAllJpa();
	}
}
