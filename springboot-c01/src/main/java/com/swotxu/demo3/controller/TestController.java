package com.swotxu.demo3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swotxu.demo3.service.TestService;
import com.swotxu.demo3.test01.dao.DBWorldUserMapper;
import com.swotxu.demo3.test01.entity.UserTest01;
import com.swotxu.demo3.test02.dao.DBNetctossRoleMapper;
import com.swotxu.demo3.test02.entity.RoleTest02;

@RestController
public class TestController {
	@Autowired
	private TestService testService;
	
	@Autowired
	private DBWorldUserMapper dBWorldUserMapper;
	@Autowired
	private DBNetctossRoleMapper dBNetctossRoleMapper;

	@RequestMapping("/findById01")
	public String findById01(Integer id) {
		System.out.println("findById01 开始");
		UserTest01 findByID = dBWorldUserMapper.findByID(id);
		return findByID.toString();
	}
	@RequestMapping("/findAll01")
	public String findAll01() {
		System.out.println("findAll01 开始");
		List<UserTest01> findAll = dBWorldUserMapper.findAll();
		return findAll.toString();
	}
	
	@RequestMapping("/findById02")
	public String findById02(Integer id) {
		System.out.println("findById02 开始");
		RoleTest02 findByID = dBNetctossRoleMapper.findByID(id);
		return findByID.toString();
	}
	@RequestMapping("/findAll02")
	public String findAll02() {
		System.out.println("findAll02 开始");
		List<RoleTest02> findAll = dBNetctossRoleMapper.findAll();
		return findAll.toString();
	}
	
	//----------------
	@RequestMapping("/insert01")
	public String insert01(String username, Integer age) {
		return testService.insert01(username, age);
	}
	@RequestMapping("/insert02")
	public String insert02(String name) {
		return testService.insert02(name);
	}
	@RequestMapping("/insert")
	public String insert(String name, Integer age) {
		return testService.insert(name,age);
	}
}
