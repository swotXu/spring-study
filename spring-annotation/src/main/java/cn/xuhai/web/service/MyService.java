package cn.xuhai.web.service;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.xuhai.web.dao.MyDao;

@Service
public class MyService {

//	@Qualifier("myDao")
//	@Autowired(required=false)
//	@Resource(name="myDao2")
	@Inject
	private MyDao myDao;
	
	@Override
	public String toString() {
		return "MyService [myDao=" + myDao + "]";
	}
}
