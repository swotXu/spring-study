package com.swotxu.demo3.test02.entity;

import org.springframework.stereotype.Component;

@Component
public class RoleTest02 {
	private Integer id;
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "RoleTest02 [id=" + id + ", name=" + name + "]";
	}
}
