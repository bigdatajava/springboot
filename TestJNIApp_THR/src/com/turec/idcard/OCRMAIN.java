package com.turec.idcard;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.idcard.CBInterface;
import com.idcard.Demo;

public class OCRMAIN{
	public static void main(String[] args) throws IOException {
		OCRInter ocrInter = new OCRInter();
		ocrInter.TestTHR();
	}
}
