package com.csc.istp.util.interfaces.ocr.web;

import java.io.Serializable;

public class ApiResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status; // 返回状态，0标示正确返回，-1表示错误返回，
	private String errmsg; // 返回的错误信息
	private T data; // 返回的数据集合

	/**
	 * get,set方法，相关的静态方法
	 * 
	 * @return
	 */

	public static <T> ApiResult<T> succ(T data) {
		ApiResult<T> result = new ApiResult<T>();
		result.setStatus(0);
		result.setErrmsg("成功");
		result.setData(data);
		return result;
	}
	
	public static <T> ApiResult<T> fail(int status, String errmsg) {
		ApiResult<T> result = new ApiResult<>();
		result.setStatus(status);
		result.setErrmsg(errmsg);
		return result;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}