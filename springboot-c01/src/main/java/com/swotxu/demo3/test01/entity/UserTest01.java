package com.swotxu.demo3.test01.entity;

import org.springframework.stereotype.Component;

@Component
public class UserTest01 {
	private Integer id;
	private String username;
	private Integer age;
	
	public UserTest01() {
	}
	public UserTest01(String username, Integer age) {
		super();
		this.username = username;
		this.age = age;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", age=" + age + "]";
	}
}
