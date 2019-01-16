package com.itmayiedu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MemberService {


	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void createUser(String name, Integer age) {
		jdbcTemplate.update("insert into student values(null,?,?);", name, age);
	}

}
