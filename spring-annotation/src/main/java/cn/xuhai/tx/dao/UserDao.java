package cn.xuhai.tx.dao;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insert() {
		String sql = "INSERT INTO xh_user(username, age) VALUES(?, ?)";
		
		String uname = UUID.randomUUID().toString().substring(0, 5);
		int age = new Random().nextInt(100);
		
		int update = jdbcTemplate.update(sql, uname, age);
		return update;
	}
}
