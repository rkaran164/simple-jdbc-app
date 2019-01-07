package io.jdbc.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import io.jdbc.app.entity.User;

@Service
public class UserService {
	
	@Autowired
	JdbcTemplate db;
	
	public String insertUser(String name) {
		String sql = "insert into users values('Raj')";
		db.execute(sql);
		return name;
	}
}
