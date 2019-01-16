package com.itmayiedu.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
public class MemberService {

	// 添加用户的时候 ，会去发送邮件

	// 思路： 使用AOP技术在运行时 创建一个单独线程进行执行
	@Async // 相当于这个方法重新开辟了单独线程进行执行
	public String addMemberAndEmail() {
		System.out.println("2");
		try {
		Thread.sleep(5000);
		} catch (Exception e) {

		 }
		 System.out.println("3");

		return "itmayiedu";
	}
}
