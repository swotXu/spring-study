package com.swotxu.demo2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "xh_user")
public class UserJpa {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String username;
	
	@Column
	private Integer age;

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
		return "UserJpa [id=" + id + ", username=" + username + ", age=" + age + "]";
	}
}
