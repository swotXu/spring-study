package com.swotxu.dome1.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@RequestMapping("/indexController")
	public String index(@ModelAttribute("dataAdvice") Map<String, Object> map
			,@ModelAttribute("_date1")Date date1
			,@ModelAttribute("_date2")Date date2) {
		System.out.println("切面传值 @ModelAttribute");
		System.out.println(map);
		
		System.out.println("切面格式化 @InitBinder");
		System.out.println(date1);
		System.out.println(date2);
		System.out.println(date1.compareTo(date2));
		
		int i = 1/0;
		return "indexController";
	}
}
