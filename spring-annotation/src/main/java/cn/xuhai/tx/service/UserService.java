package cn.xuhai.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xuhai.tx.dao.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Transactional
	public void insert() {
		int insert = userDao.insert();
		System.out.println("≤Â»ÎÕÍ≥…£∫"+insert);
		int a = 1/0;
	}
}
