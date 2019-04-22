package com.idcard;

import java.io.UnsupportedEncodingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.csc.istp.util.interfaces.ocr.base.Configuration;

public class Demo {
	
	private static final Logger logger = LogManager.getLogger(Demo.class);
	static 
	{
		
		try{
			
			String path=Configuration.getProperty("ocrdllpath");
			System.load(path);
		}catch(Exception e){
			logger.info("Demo-dll-error");
		}
		logger.info("Demo - 加载dll动态链接库成功！");
	} 
	
	public Demo(){
		
		logger.info("Demo - 版本 - 初始化引擎开始！");
		int ret = Demo.Start(Byte2String(GetEngineTimeKey()));
		if (ret == 100) {
			logger.info("Demo-该版本为试用版本，时间过期，请联系技术员");
		}
		else if (ret != 1) {
			logger.info("Demo-引擎初始化失败，请联系技术员");
		}
		logger.info("Demo -  初始化引擎成功！");
	}
	
	
	public static int isBootOK = 0;
    public native static byte [] GetCopyrightInfo();
    
    public native static byte [] GetVersion();
    
    public native static byte [] GetUseTimeString();
    
    public native static byte [] GetEngineTimeKey();
    
	public native static int RECOCRBoot(String TimeKey);
	
	public native static int SetParam(int param,int val);
	
	public native static int SetParamString(int param,String val);
	
	public native static byte[] RECOCROFPATH(int typeid, String path);
	
	public native static byte[] RECOCROFMEM(int typeid, byte [] pImagebuf, int len);
	
	public native static int TerminateOCRHandle();
	
	public String Byte2String(byte[] info)
	{
		String str = null;
		if (info != null) {
			try {
				str = new String(info,"GBK");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return str;
	}
	public static int Start(String TimeKey)
	{
		if (isBootOK == 0) {
			isBootOK = RECOCRBoot(TimeKey);
		}
		return isBootOK;
	}
}
