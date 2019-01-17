package com.itmayiedu.service;

import com.itmayiedu.test01.dao.User1Mapper;
import com.itmayiedu.test02.dao.User2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtomikosService {

	@Autowired
	private User1Mapper dao1;

	@Autowired
	private User2Mapper dao2;

	/*
	使用atomikos不用指定transactionalManager，配置文件中会自动指定统一管理使用atomikos
	 */
	@Transactional
	public Integer addUser(String name,Integer age) {

		dao1.addUser(name,age);
	//	int i=1/0;
		dao2.addUser(name, age);

		return 100;
	}


}
