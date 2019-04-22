package com.csc.istp.util.interfaces.ocr.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.istp.util.interfaces.ocr.domain.GlobalData;
import com.csc.istp.util.interfaces.ocr.domain.IdCard;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OcrService {
	private static final Logger logger = LogManager.getLogger(OcrService.class);

	@Autowired
	private EnginStart tStart;

	@Autowired
	private ObjectMapper jsonMapper;

	public IdCard getIdCardByBytes(byte[] data) {

		/*
		 * 下面使用了多个try块~因为压测时 根据以往经验，为了快速定位到 “为什么返回103无数据”
		 */
		
		IdCard pojoResult = null;

		byte[] byteIdCardResult = null;
		String strIdCardResult = null;

		// 调用dll接口
		try {
			byteIdCardResult = tStart.tDemo.RECOCROFMEM(GlobalData.TIDCARD2, data, data.length);
		} catch (Exception e) {
			logger.info("OcrService报错标识处-调用dll出错");
			logger.info(e.getMessage());
		}
		
		// 处理结果
		if (byteIdCardResult == null) {
			logger.info("OcrService报错标识处-第三方dll返回值为null");
			return null;
		} else {
			// 告诉虚拟机将字节数组中的字节以“gbk”的方式将每2个字节组装成一个汉字
			try {
				strIdCardResult = new String(byteIdCardResult, "GBK");
			} catch (UnsupportedEncodingException e) {
				logger.error("OcrService报错标识处-将字节数组中的字节以“gbk”的方式将每2个字节组装成一个汉字 发生错误-error");
				logger.info(e.getMessage());
				return null;
			}

			// 序列化结果
			Map<String, Object> allJson = null;
			try {
				allJson = jsonMapper.readValue(strIdCardResult, Map.class);
			} catch (IOException e) {
				logger.error("OcrService报错标识处-json序列化pojo过程-error");
				logger.info(e.getMessage());
				return null;
			}
			List<Map<String, String>> dataJson = (List<Map<String, String>>) allJson.get("data");

			pojoResult = new IdCard();

			pojoResult.setName(dataJson.get(0).get("IDC_NAME"));
			pojoResult.setSex(dataJson.get(0).get("IDC_SEX"));
			pojoResult.setFolk(dataJson.get(0).get("IDC_FOLK"));
			pojoResult.setBirthday(dataJson.get(0).get("IDC_BIRTHDAY"));
			pojoResult.setAddress(dataJson.get(0).get("IDC_ADDRESS"));
			pojoResult.setCardnum(dataJson.get(0).get("IDC_NUM"));
			pojoResult.setIssue(dataJson.get(0).get("IDC_ISSUE"));
			pojoResult.setPeriod(dataJson.get(0).get("IDC_PERIOD"));
		}

		return pojoResult;
	}

}
