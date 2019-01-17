package com.itmayiedu.controller;

import com.itmayiedu.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.service.MemberService;

import java.util.Random;

/**
 */
@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/jdbc")
	public Member addMemberAndEmail() throws InterruptedException {

		return memberService.queryUser();

	}


}
