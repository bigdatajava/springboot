package com.csc.istp.util.interfaces.ocr.web;

import java.util.Base64;
//import org.apache.commons.codec.binary.Base64;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.csc.istp.util.interfaces.ocr.domain.IdCard;
import com.csc.istp.util.interfaces.ocr.service.OcrService;

@RestController
@RequestMapping("/util/ocr")
public class OcrController {

	private static final Logger logger = LogManager.getLogger(OcrController.class);

	@Autowired
	private OcrService ocrService;

	/**
	 * 身份证识别ocr
	 */
	@RequestMapping(value = "/getOcrMessage", method = { RequestMethod.POST })
	public ApiResult<IdCard> sendMessage(@RequestBody Map<String, Object> params) throws Exception {
		logger.info("身份证识别接口调用开始");

		ApiResult<IdCard> apiResult = new ApiResult<>();
		byte[] byteImageReq = null;

		String strImageReq = (String) params.get("image");

		if (StringUtils.isBlank(strImageReq)) {

			logger.info("必填入参image不能为空");
			apiResult.setStatus(105);
			apiResult.setErrmsg("必填入参image不能为空");
			return apiResult;
		} else {

			byteImageReq = Base64.getDecoder().decode(strImageReq.getBytes());
			// byteImageReq = Base64.decodeBase64(strImageReq);
		}

		// 业务
		IdCard idCardResult = ocrService.getIdCardByBytes(byteImageReq);
		if (idCardResult == null) {
			logger.info("controller调用service返回结果为空");
			apiResult.setStatus(103);
			apiResult.setErrmsg("无数据");
			return apiResult;
		} else {
			logger.info("返回正常格式结果结束-success");
			return apiResult.succ(idCardResult);
		}
	}

}
