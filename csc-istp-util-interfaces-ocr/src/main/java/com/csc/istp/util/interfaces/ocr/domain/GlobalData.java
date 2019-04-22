package com.csc.istp.util.interfaces.ocr.domain;

import java.io.Serializable;

public class GlobalData implements Serializable {

	private static final long serialVersionUID = 1L;
	public static int TIDCARD2 = 0x11; /* 二代证 */
	public static int TIDCARDBACK = 0x14; /* 二代证背面 */

	public static int T_SET_HEADIMG = 0x0002;// 设置是否要截取人头像信息
	public static int T_SET_HEADIMGBUFMODE = 0x0006;// 设置人头像模式 0= 原始形式(便于android
													// ios直接加载)
													// 1=BASE64加密形式(便于sdk网络传输)

}
