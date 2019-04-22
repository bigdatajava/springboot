package com.csc.istp.util.interfaces.ocr.domain;

import java.io.Serializable;

public class IdCard implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;/* 姓名*/
	private String sex;/* 性别*/
	private String folk;/* 民族*/
	private String birthday;/* 出生日期*/
	private String address;/* 地址*/
	private String cardnum;/* 号码*/
	private String issue;/* 签发机关*/
	private String period;/* 有效期限*/
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getFolk() {
		return folk;
	}
	public void setFolk(String folk) {
		this.folk = folk;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCardnum() {
		return cardnum;
	}
	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}

}
