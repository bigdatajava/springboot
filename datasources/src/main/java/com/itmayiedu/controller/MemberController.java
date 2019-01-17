package com.itmayiedu.controller;

import com.itmayiedu.entity.User;
import com.itmayiedu.test01.dao.User1Mapper;
import com.itmayiedu.test01.service.User1Service;
import com.itmayiedu.test02.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 */
@RestController
public class MemberController {

	@Autowired
	private User1Service service1;

	@Autowired
	private User2Service service2;


	/*
	dao 1
	 */
	@RequestMapping("/dao1")
	public Integer addUser1(String name,Integer age){

		return service1.addUser1(name, age);

	}

	@RequestMapping("/dao2")
	public Integer addUser2(String name,Integer age){

		return service2.addUser2(name,age);
	}

}
