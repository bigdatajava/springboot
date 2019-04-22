package com.turec.idcard;

import com.idcard.CBInterface;
import com.idcard.Demo;
import com.idcard.GlobalData;

class OCRInter{

	public Demo engineDemo = new Demo();

	public void TestTHR() {
		String timeKey = "b1470ad02f8de89349ddd9e484318d98";//20190301-20190430
		String pathString = "C:/123/";
		/**说明：外部可自行设置TIMEKEY值， 也可使用引擎默认的KEY取*/
		//int ret = engineDemo.Start(timeKey);
		int ret = engineDemo.Start(engineDemo.Byte2String(engineDemo.GetEngineTimeKey()));
		if (ret == 100) {
			System.out.println("该版本为试用版本，时间过期，请联系技术员\n");
			return;
		}
		else if (ret != 1) {
			System.out.println("引擎初始化失败，请联系技术员\n");
			return;
		}
		System.out.println(engineDemo.Byte2String(engineDemo.GetCopyrightInfo()) + "\n"
				+ engineDemo.Byte2String(engineDemo.GetVersion()) +"\n"
				+  engineDemo.Byte2String(engineDemo.GetUseTimeString()));
		engineDemo.SetParam(GlobalData.T_SET_HEADIMG, 1);
		engineDemo.SetParam(GlobalData.T_SET_HEADIMGBUFMODE, 1);
//打开身份证保存区域图片的开关
//		engineDemo.SetParam(GlobalData.T_SET_CARDREGIONIMG, 1);
		OCREngine engine1 = new OCREngine(GlobalData.TIDCARD2,engineDemo, pathString + "1.jpg");
		OCREngine engine2 = new OCREngine(GlobalData.TIDCARD2,engineDemo, pathString + "2.jpg");
		OCREngine engine3 = new OCREngine(GlobalData.TIDCARD2,engineDemo, pathString + "3.jpg");
		
		OCREngine2 engine21 = new OCREngine2(GlobalData.TIDBANK,engineDemo, pathString + "5.jpg");
		OCREngine2 engine22 = new OCREngine2(GlobalData.TIDLPR,engineDemo, pathString + "5.jpg");
		OCREngine2 engine23 = new OCREngine2(GlobalData.TIDLPR,engineDemo, pathString + "5.jpg");
		
		OCREngine3 engine31 = new OCREngine3(GlobalData.TIDJSZCARD,engineDemo, pathString + "6.jpg");
		OCREngine3 engine32 = new OCREngine3(GlobalData.TIDJSZCARD,engineDemo, pathString + "6.jpg");
		OCREngine3 engine33 = new OCREngine3(GlobalData.TIDJSZCARD,engineDemo, pathString + "6.jpg");
		
		OCREngine4 engine41 = new OCREngine4(GlobalData.TIDXSZCARD,engineDemo, pathString + "7.jpg");
		OCREngine4 engine42 = new OCREngine4(GlobalData.TIDXSZCARD,engineDemo, pathString + "7.jpg");
		OCREngine4 engine43 = new OCREngine4(GlobalData.TIDXSZCARD,engineDemo, pathString + "7.jpg");
		
		engine1.start();
		engine2.start();
		engine3.start();
		
		engine21.start();
//		engine22.start();
//		engine23.start();
		
//		engine31.start();
//		engine32.start();
//		engine33.start();
//
//		engine41.start();
//		engine42.start();
//		engine43.start();
	}
	
}
