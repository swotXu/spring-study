package com.swotxu.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspController {
	
	@RequestMapping("/indexJsp")
	public String index() {
		
		return "indexJsp";
	}
}
