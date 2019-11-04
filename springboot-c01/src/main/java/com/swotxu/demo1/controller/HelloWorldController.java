package com.swotxu.demo1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@RequestMapping("/index")
	public String index() {
		return "sucess";
	}
	
	@RequestMapping("/getMap")
	public Map<String, Object> getMap() {
		Map<String,Object> map = new HashMap<>();
		map.put("code", 200);
		map.put("msg", "成功！");
		return map;
	}
	
//	public static void main(String[] args) {
//		SpringApplication.run(HelloWorldController.class, args);
//	}
}
