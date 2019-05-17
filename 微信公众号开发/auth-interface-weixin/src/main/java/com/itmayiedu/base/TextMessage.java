package com.itmayiedu.base;

import lombok.Getter;
import lombok.Setter;


public class TextMessage extends BaseMessage {

	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
