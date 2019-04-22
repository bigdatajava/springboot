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

class OCREngine3 extends Thread {
    public String pathString;
    public Demo engineDemo;
    public int id;
    public int i = 0;
    public int num1 = 0;
    private CBInterface callBack = null;
    private int tEngineType = GlobalData.TUNCERTAIN;

    public OCREngine3(int tEngineType, Demo demo, String paString) {
        // TODO Auto-generated constructor stub
        this.pathString = paString;
        this.engineDemo = demo;
        this.tEngineType = tEngineType;
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
                pImgBuff = GetImgByte(pathString);
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
                strResult = strResult.replace("\u0000", "");
                GsonBuilder gsonbuilder = new GsonBuilder();
                gsonbuilder.serializeNulls();
                Gson gson = gsonbuilder.create();
                TOCR tocr = gson.fromJson(strResult, TOCR.class);
                for (int i = 0; i < tocr.getData().size(); i++) {
                System.out.println("号码:" + tocr.getData().get(i).getDL_NUM() + "\r\n"
                        + "姓名:" + tocr.getData().get(i).getDL_NAME() + "\r\n"
                        + "性别:" + tocr.getData().get(i).getDL_SEX() + "\r\n"
                        + "国籍:" + tocr.getData().get(i).getDL_COUNTRY() + "\r\n"
                        + "地址:" + tocr.getData().get(i).getDL_ADDRESS() + "\r\n"
                        + "出生日期:" + tocr.getData().get(i).getDL_BIRTHDAY() + "\r\n"
                        + "准驾车型:" + tocr.getData().get(i).getDL_CLASS() + "\r\n"
                        + "有效期限:" + tocr.getData().get(i).getDL_VALIDFOR() + "\r\n"
                        + "初次领证日期:" + tocr.getData().get(i).getDL_ISSUE_DATE() + "\r\n"
                        + "有效起始日期:" + tocr.getData().get(i).getDL_VALIDFROM() + "\r\n");
                }
            } else {
                System.out.print("buf == null\n");
            }
        }
//    	System.out.println("线程"+ id + "结束\n");
    }
}