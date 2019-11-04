package com.swotxu.demo3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swotxu.demo3.test01.dao.DBWorldUserMapper;
import com.swotxu.demo3.test01.entity.UserTest01;
import com.swotxu.demo3.test02.dao.DBNetctossRoleMapper;

@Service
public class TestService {
	@Autowired
	private DBWorldUserMapper dBWorldUserMapper;
	@Autowired
	private DBNetctossRoleMapper dBNetctossRoleMapper;
	
//	@Transactional
	public String insert01(String username, Integer age) {
		System.out.println("insert01 开始");
		UserTest01 user = new UserTest01(username, age);
		int insert = dBWorldUserMapper.insert(user);
		return "success"+insert;
	}
	
//	@Transactional
	public String insert02(String name) {
		System.out.println("insert02 开始");
		int insert = dBNetctossRoleMapper.insert(name);
		return "success"+insert;
	}
	
	@Transactional
	public String insert(String name, Integer age) {
		String insert01 = insert01(name,age);
		System.out.println(insert01);
		
		String insert02 = insert02(name);
		System.out.println(insert02);
		
		int i = 1/0;
		
		return "success";
	}
}
