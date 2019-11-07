package com.swotxu.demo4.service;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class Test04Service {
	private static Logger logger = Logger.getLogger(Test04Service.class);
	
	@Async
	public String getMsg() {
		logger.info("=== Service.getMsg() ===  3");
		for (int i = 1; i <= 3; i++) {
			logger.info("i: " + i);
		}
		logger.info("=== Service.getMsg() ===  4");
		return "getMsg-success";
	}
}
