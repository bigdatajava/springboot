package com.itmayiedu.test01.service;

import com.itmayiedu.test01.dao.User1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class User1Service {

	@Autowired
	private User1Mapper dao1;



	@Transactional(transactionManager = "test1TransactionManager")
	public Integer addUser1(String name,Integer age) {

		Integer i = null;

			i = dao1.addUser(name, age);
			int s=1/0;

		return i;
	}


}
