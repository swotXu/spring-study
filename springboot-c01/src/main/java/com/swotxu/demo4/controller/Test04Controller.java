package com.swotxu.demo4.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swotxu.demo4.service.Test04Service;

@RestController
public class Test04Controller {
	private static Logger logger = Logger.getLogger(Test04Controller.class);
	
	@Value("${com.swotxu.user}")
	private String name;
	
	@Autowired
	private Test04Service test04Service;
	
	public Test04Service getTest04Service() {
		return test04Service;
	}
	public void setTest04Service(Test04Service test04Service) {
		this.test04Service = test04Service;
	}

	@RequestMapping("/getMsg")
	public String getMsg() {
		logger.info("====== Controller.getMsg() ======  1");
		String msg = test04Service.getMsg();
		logger.info("Controller.result === " + msg);
		logger.info("====== Controller.getMsg() ======  2");
		return "success";
	}
	
	@RequestMapping("/getValue")
	public String getValue() {
		logger.info("====== Controller.getValue() ======");
		logger.info(name);
		return name;
	}
}
