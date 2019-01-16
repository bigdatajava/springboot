package com.itmayiedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.service.MemberService;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

/**
 */
@RestController
public class MemberController {
	@Autowired
	private MemberService memberService;


	@RequestMapping("/async")
	public String addMemberAndEmail() throws InterruptedException {
		System.out.println("1");

		String result = memberService.addMemberAndEmail();

		Thread.sleep(500);

		System.out.println("4");
		return "result:" + result;
	}

}
