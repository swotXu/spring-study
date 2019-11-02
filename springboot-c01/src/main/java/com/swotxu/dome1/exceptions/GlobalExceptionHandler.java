package com.swotxu.dome1.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public Map<String, Object> resuleError(Exception e) {
		Map<String,Object> map = new HashMap<>();
		map.put("code", 500);
		map.put("msg", "系统错误！");
		map.put("erroSackt", e.getMessage());
		return map;
	}
	
	@ModelAttribute("dataAdvice")
	public Map<String, Object> resuleData(Model model) {
		Map<String,Object> map = model.asMap();
		map.put("code", 233);
		map.put("msg", "测试切面传递数据！");
		return map;
	}
	
	@InitBinder("_date1")
	public void resuleFormatter1(WebDataBinder binder) {
		binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
	}
	@InitBinder("_date2")
	public void resuleFormatter2(WebDataBinder binder) {
		binder.addCustomFormatter(new DateFormatter("yyyy/MM/dd"));
	}
}
