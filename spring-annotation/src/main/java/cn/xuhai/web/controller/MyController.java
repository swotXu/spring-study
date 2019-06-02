package cn.xuhai.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.xuhai.web.service.MyService;

@Controller
public class MyController {
	
	@Autowired
	private MyService myService;
	
	public void print() {
		System.out.println(myService);
	}
}
