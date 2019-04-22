package com.turec.idcard;

import java.io.UnsupportedEncodingException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.idcard.CBInterface;
import com.idcard.Demo;
import com.idcard.GlobalData;
import com.idcard.StringManager;
import com.idcard.TOCR;

class OCREngine extends Thread {
    public String pathString;
    public Demo engineDemo;
    public int id;
    public int i = 0;
    public int num1 = 0;
    private CBInterface callBack = null;
    private int tEngineType = GlobalData.TUNCERTAIN;

    public OCREngine(int tEngineType, Demo demo, String paString) {
        // TODO Auto-generated constructor stub
        this.pathString = paString;//图片路径
        this.engineDemo = demo; //初始化了的demo（包含dll初始化）
        this.tEngineType = tEngineType; //身份证-二代证标识
    }

    public void SetCallBack(CBInterface cbInterface) {
        this.callBack = cbInterface;
    }

    public static byte[] GetImgByte(String path) throws IOException {
        byte[] bytes = null;
        File file = null;
        file = new File(path);

        if (file.exists()) {
            InputStream is = new FileInputStream(file);
            int length = (int) file.length();
            if (length > Integer.MAX_VALUE) {
                return null;
            }
            bytes = new byte[length];
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            if (offset < bytes.length) {
                return null;
            }
            is.close();
            return bytes;
        }
        return null;
    }

    public void run() {
//    	for (i = 0; i < 10; i++)
        {
            byte[] pImgBuff = null;
            try {
                pImgBuff = GetImgByte(pathString);//pathString:图片位置
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            if (pImgBuff == null) {
                return;
            }
            //byte [] buf = engineDemo.RECOCROFPATH(pathString);
            byte[] jsonbuf = engineDemo.RECOCROFMEM(this.tEngineType, pImgBuff, pImgBuff.length);
            if (jsonbuf != null) {
                String strResult = null;
                try {
                    strResult = new String(jsonbuf, "GBK");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
//    			System.out.print(strResult);
                strResult = strResult.replace("\u0000", "");
                
                GsonBuilder gsonbuilder = new GsonBuilder();
                gsonbuilder.serializeNulls();
                Gson gson = gsonbuilder.create();
                TOCR tocr = gson.fromJson(strResult, TOCR.class);
                for (int i = 0; i < tocr.getData().size(); i++) {
                    System.out.println("姓名:" + tocr.getData().get(i).getNAME() + "\r\n"
                            + "性别:" + tocr.getData().get(i).getSEX() + "\r\n"
                            + "民族:" + tocr.getData().get(i).getFOLK() + "\r\n"
                            + "出生:" + tocr.getData().get(i).getBIRTHDAY() + "\r\n"
                            + "地址:" + tocr.getData().get(i).getADDRESS() + "\r\n"
                            + "号码:" + tocr.getData().get(i).getNUM() + "\r\n"
                            + "签发机关:" + tocr.getData().get(i).getISSUE() + "\r\n"
                            + "有效期限:" + tocr.getData().get(i).getPERIOD() + "\r\n");					

                    StringManager.SaveJPGFile("c:/123/001.jpg", tocr.getData().get(i).getHeadimg().getBytes());// 人头像保存
				}
            } else {
                System.out.print("buf == null\n");
            }
        }
//    	System.out.println("线程"+ id + "结束\n");
    }
}