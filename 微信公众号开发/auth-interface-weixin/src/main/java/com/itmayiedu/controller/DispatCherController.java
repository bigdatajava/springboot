package com.itmayiedu.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itmayiedu.base.TextMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.util.CheckUtil;
import com.itmayiedu.util.HttpClientUtil;
import com.itmayiedu.util.XmlUtils;

import ch.qos.logback.core.joran.spi.XMLUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DispatCherController {
	private static final String REQESTURL = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=";

	// 服务器验证接口地址
	@RequestMapping(value = "/dispatCher", method = RequestMethod.GET)
	public String dispatCherGet(String signature, String timestamp, String nonce, String echostr) {
		// 1.验证参数
		boolean checkSignature = CheckUtil.checkSignature(signature, timestamp, nonce);
		// 2.参数验证成功之后，返回随机数
		if (!checkSignature) {
			return null;
		}
		return echostr;
	}

	// 微信动作推送
	@RequestMapping(value = "/dispatCher", method = RequestMethod.POST)
	public void dispatCherGet(HttpServletRequest reqest, HttpServletResponse response) throws Exception {



		reqest.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 1.将xml转换成Map格式
		System.out.println("服务开始运行。。。");
		Map<String, String> resultMap = XmlUtils.parseXml(reqest);
//		log.info("###收到微信消息####resultMap:" + resultMap.toString());
		// 2.判断消息类型
		String msgType = resultMap.get("MsgType");
		// 3.如果是文本类型，返回结果给微信服务端
		PrintWriter writer = response.getWriter();
		switch (msgType) {
		case "text":
			// 开发者微信公众号
			String toUserName = resultMap.get("ToUserName");
			// 消息来自公众号
			String fromUserName = resultMap.get("FromUserName");
			// 消息内容
			String content = resultMap.get("Content");


			String resultJson = null;
			JSONObject jsonObject = null;
			Integer resultCode = null;
			String msg = null;
			String resultContent =null;
			//自定义回复内容
			if(content.equals("刘文超")){
				resultContent = "他是来自河南周口的小伙子";
			}else if(content.equals("白素贞")){
				resultContent = "哇，白姑娘可是个小美人！要少熬夜哈";
			}else if(content.equals("刘文娟")){
				resultContent = "哇，我娟姐可是个大美人，厉害了我的姐";
			}else if(content.equals("刘文强")){
				resultContent = "他是来自河南周口的小伙";
			}else{
				//机器人内容
				resultJson = HttpClientUtil.doGet(REQESTURL + content);
				jsonObject = JSONObject.parseObject(resultJson);
				resultCode = jsonObject.getInteger("result");
				msg = null;
				if (resultCode == 0) {
					resultContent = jsonObject.getString("content");
				}else {
					resultContent = "我现在有点忙.稍后回复您!";
				}

			}
			msg = setText(resultContent, toUserName,fromUserName);
			writer.println(msg);
			break;

		default:
			break;
		}
		writer.close();
	}

	public String setText(String content, String fromUserName, String toUserName) {
		TextMessage textMessage = new TextMessage();
		textMessage.setContent(content);
		textMessage.setMsgType("text");
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setFromUserName(fromUserName);
		textMessage.setToUserName(toUserName);
		// 将实体类转换成xml格式
		String msg = XmlUtils.messageToXml(textMessage);
		return msg;
	}
}
