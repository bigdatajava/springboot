package com.itmayiedu.service;

import com.itmayiedu.dao.MemberDao;
import com.itmayiedu.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	private MemberDao dao;


	public Member queryUser() {

		return dao.findByName("chao1000");

	}

}
