package com.itmayiedu.test02.service;

import com.itmayiedu.test01.dao.User1Mapper;
import com.itmayiedu.test02.dao.User2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class User2Service {

	@Autowired
	private User2Mapper dao2;



	@Transactional(transactionManager = "test1TransactionManager")
	public Integer addUser2(String name,Integer age) {

		Integer i = null;

			i = dao2.addUser(name, age);
			int s=1/0;

		return i;
	}


}
