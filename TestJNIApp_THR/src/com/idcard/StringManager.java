package com.idcard;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StringManager {
	public  String headimg ;
	public  String cardimage ;
	public  String data ;
	public  String IDC_NAME				;/* 姓名*/
	public  String IDC_SEX				;/* 性别*/
	public  String IDC_FOLK				;/* 民族*/
	public  String IDC_BIRTHDAY			;/* 出生日期*/
	public  String IDC_ADDRESS			;/* 地址*/
	public  String IDC_NUM				;/* 号码*/
	public  String IDC_ISSUE			;/* 签发机关*/
	public  String IDC_PERIOD			;/* 有效期限*/
	
	public  String LPR_NUM				;/* 车牌号码*/
	public  String LPR_PLATECOLOR		;/* 车牌颜色*/
	//行驶证
	public  String DP_PLATENO			;/* 号牌号码*/
	public  String DP_TYPE				;/* 车辆类型*/
	public  String DP_OWNER				;/* 所有人*/
	public  String DP_ADDRESS			;/* 住址*/
	public  String DP_USECHARACTER		;/* 使用性质*/
	public  String DP_MODEL				;/* 品牌号码*/
	public  String DP_VIN				;/* 车辆识别代号*/
	public  String DP_ENGINENO			;/* 发动机号码*/
	public  String DP_REGISTER_DATE		;/* 注册日期*/
	public  String DP_ISSUE_DATE		;/* 发证日期*/

	//驾驶证
	public  String DL_NUM				;/* 号码*/
	public  String DL_NAME				;/* 姓名*/
	public  String DL_SEX				;/* 性别*/
	public  String DL_COUNTRY			;/* 国籍*/
	public  String DL_ADDRESS			;/* 地址*/
	public  String DL_BIRTHDAY			;/* 出生日期*/
	public  String DL_ISSUE_DATE		;/*初次领证日期*/
	public  String DL_CLASS				;/*准驾车型*/
	public  String DL_VALIDFROM			;/*有效起始日期*/
	public  String DL_VALIDFOR			;/* 有效期限*/

	//火车票
	public  String TIC_START			;/* 起始站*/
	public  String TIC_NUM				;/* 车次*/
	public  String TIC_END				;/* 终点站*/
	public  String TIC_TIME				;/* 发车时间*/
	public  String TIC_SEAT				;/* 座位号*/
	public  String TIC_NAME				;/* 姓名*/

	//   营业执照
	public  String BLIC_CODE			;/* 统一社会信用代码*/
	public  String BLIC_NAME			; /*名称*/
	public  String BLIC_TYPE			; /*类型*/
	public  String BLIC_ADDR			; /*住所*/
	public  String BLIC_PERSON			; /*法定代表人*/
	public  String BLIC_CAPTIAL			; /*注册资本*/
	public  String BLIC_DATE			; /*成立日期*/
	public  String BLIC_PERIOD			; /*营业期限*/
	public  String BLIC_ISSUE			; /*发证日期*/

	/*社保卡字段*/
	public  String SSC_NAME			;/*姓名*/
	public  String SSC_NUM			;/*身份证号*/
	public  String SSC_SHORTNUM		;/*卡号*/
	public  String SSC_PERIOD		;/*有效期限*/
	public  String SSC_BANKNUM		;/*银行卡号*/

	/*护照字段*/
	public  String PAS_PASNO			;/*护照号*/
	public  String PAS_NAME				;/*姓名*/
	public  String PAS_SEX				;/*性别*/
	public  String PAS_IDCARDNUM		;/*身份证号码*/
	public  String PAS_BIRTH			;/*生日*/
	public  String PAS_PLACE_BIRTH		;/*出生地址*/
	public  String PAS_DATE_ISSUE		;/*签发日期*/
	public  String PAS_DATE_EXPIRY		;/*有效日期*/
	public  String PAS_PLACE_ISSUE		;/*签发地址*/
	public  String PAS_NATION_NAME		;/*国籍和姓名监督码*/
	public  String PAS_MACHINE_RCODE	;/*护照号+国籍代码+生日代码（YYMMDD）+性别（M/F）+护照有效期（YYMMDD）+校验码 监督码*/

	public  String HSL_NAME			;
	public  String HSL_NUM			;
	public  String HSL_DATE			;
	public  String HSL_FIGURE		;
	public  String HSL_FIGURE_SUM	;

	//人民币冠字号
	public  String RMB_NUM				; /*人民币冠字号*/

	/*银行卡字段*/
	public  String TBANK_NUM			;//获取银行卡号
	public  String TBANK_NAME			;//获取银行卡开户行
	public  String TBANK_ORGCODE		;//获取银行机构代码
	public  String TBANK_CLASS			;//获取卡种
	public  String TBANK_CARD_NAME		;//获取卡名


	public static byte [] Decode(byte[] Data,int DataByte,int []len)
	{
		//解码表
		byte [] strDecode = new byte[DataByte];
		byte []DecodeTable =
				{
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						62, // '+'
						0, 0, 0,
						63, // '/'
						52, 53, 54, 55, 56, 57, 58, 59, 60, 61, // '0'-'9'
						0, 0, 0, 0, 0, 0, 0,
						0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
						13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, // 'A'-'Z'
						0, 0, 0, 0, 0, 0,
						26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38,
						39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, // 'a'-'z'
				};
		int nValue;
		int i= 0;
		int j =0;
		int k =0;
		len[0]=0;
		while (i < DataByte)
		{
			if (Data[j] != '\r' && Data[j]!='\n')
			{
				nValue = DecodeTable[Data[j++]] << 18;
				nValue += DecodeTable[Data[j++]] << 12;
				strDecode[k++]=(byte) ((nValue & 0x00FF0000) >> 16);
				//OutByte++;
				if (Data[j] != '=')
				{
					nValue += DecodeTable[Data[j++]] << 6;
					strDecode[k++]=(byte) ((nValue & 0x0000FF00) >> 8);
					//OutByte++;
					if (Data[j] != '=')
					{
						nValue += DecodeTable[Data[j++]];

						strDecode[k++]=(byte) (nValue & 0x000000FF);
						//OutByte++;
					}
				}
				i += 4;
			}
			else// 回车换行,跳过
			{
				j++;
				i++;
			}
		}
		len[0] = k;
		return strDecode;
	}
	public static int SaveJPGFile(String path,byte [] buffer)
	{
		if (buffer == null || buffer.length <= 0) {
			return 0;
		}
		File img = new File(path);
		try {
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(img));
			int [] len = new int[1];
			int lennnn = buffer.length;
			byte [] jpg = StringManager.Decode(buffer, buffer.length,len);
			//int len = jpg.length;
			//out.write(jpg);
			out.write(jpg, 0, len[0]);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	public String getdata() {
		return data;
	}
	public void setdata(String headimg) {
		this.data = headimg;
	}
	
	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getCardimage() {
		return cardimage;
	}

	public void setCardimage(String cardimage) {
		this.cardimage = cardimage;
	}

	public String getNAME() {
		return IDC_NAME;
	}

	public void setNAME(String NAME) {
		this.IDC_NAME = NAME;
	}

	public String getSEX() {
		return IDC_SEX;
	}

	public void setSEX(String SEX) {
		this.IDC_SEX = SEX;
	}

	public String getFOLK() {
		return IDC_FOLK;
	}

	public void setFOLK(String FOLK) {
		this.IDC_FOLK = FOLK;
	}

	public String getBIRTHDAY() {
		return IDC_BIRTHDAY;
	}

	public void setBIRTHDAY(String BIRTHDAY) {
		this.IDC_BIRTHDAY = BIRTHDAY;
	}

	public String getADDRESS() {
		return IDC_ADDRESS;
	}

	public void setADDRESS(String ADDRESS) {
		this.IDC_ADDRESS = ADDRESS;
	}

	public String getNUM() {
		return IDC_NUM;
	}

	public void setNUM(String NUM) {
		this.IDC_NUM = NUM;
	}

	public String getISSUE() {
		return IDC_ISSUE;
	}

	public void setISSUE(String ISSUE) {
		this.IDC_ISSUE = ISSUE;
	}

	public String getPERIOD() {
		return IDC_PERIOD;
	}

	public void setPERIOD(String PERIOD) {
		this.IDC_PERIOD = PERIOD;
	}

	public String getLPR_NUM() {
		return LPR_NUM;
	}

	public void setLPR_NUM(String LPR_NUM) {
		this.LPR_NUM = LPR_NUM;
	}

	public String getLPR_PLATECOLOR() {
		return LPR_PLATECOLOR;
	}

	public void setLPR_PLATECOLOR(String LPR_PLATECOLOR) {
		this.LPR_PLATECOLOR = LPR_PLATECOLOR;
	}

	public String getDP_PLATENO() {
		return DP_PLATENO;
	}

	public void setDP_PLATENO(String DP_PLATENO) {
		this.DP_PLATENO = DP_PLATENO;
	}

	public String getDP_TYPE() {
		return DP_TYPE;
	}

	public void setDP_TYPE(String DP_TYPE) {
		this.DP_TYPE = DP_TYPE;
	}

	public String getDP_OWNER() {
		return DP_OWNER;
	}

	public void setDP_OWNER(String DP_OWNER) {
		this.DP_OWNER = DP_OWNER;
	}

	public String getDP_ADDRESS() {
		return DP_ADDRESS;
	}

	public void setDP_ADDRESS(String DP_ADDRESS) {
		this.DP_ADDRESS = DP_ADDRESS;
	}

	public String getDP_USECHARACTER() {
		return DP_USECHARACTER;
	}

	public void setDP_USECHARACTER(String DP_USECHARACTER) {
		this.DP_USECHARACTER = DP_USECHARACTER;
	}

	public String getDP_MODEL() {
		return DP_MODEL;
	}

	public void setDP_MODEL(String DP_MODEL) {
		this.DP_MODEL = DP_MODEL;
	}

	public String getDP_VIN() {
		return DP_VIN;
	}

	public void setDP_VIN(String DP_VIN) {
		this.DP_VIN = DP_VIN;
	}

	public String getDP_ENGINENO() {
		return DP_ENGINENO;
	}

	public void setDP_ENGINENO(String DP_ENGINENO) {
		this.DP_ENGINENO = DP_ENGINENO;
	}

	public String getDP_REGISTER_DATE() {
		return DP_REGISTER_DATE;
	}

	public void setDP_REGISTER_DATE(String DP_REGISTER_DATE) {
		this.DP_REGISTER_DATE = DP_REGISTER_DATE;
	}

	public String getDP_ISSUE_DATE() {
		return DP_ISSUE_DATE;
	}

	public void setDP_ISSUE_DATE(String DP_ISSUE_DATE) {
		this.DP_ISSUE_DATE = DP_ISSUE_DATE;
	}

	public String getDL_NUM() {
		return DL_NUM;
	}

	public void setDL_NUM(String DL_NUM) {
		this.DL_NUM = DL_NUM;
	}

	public String getDL_NAME() {
		return DL_NAME;
	}

	public void setDL_NAME(String DL_NAME) {
		this.DL_NAME = DL_NAME;
	}

	public String getDL_SEX() {
		return DL_SEX;
	}

	public void setDL_SEX(String DL_SEX) {
		this.DL_SEX = DL_SEX;
	}

	public String getDL_COUNTRY() {
		return DL_COUNTRY;
	}

	public void setDL_COUNTRY(String DL_COUNTRY) {
		this.DL_COUNTRY = DL_COUNTRY;
	}

	public String getDL_ADDRESS() {
		return DL_ADDRESS;
	}

	public void setDL_ADDRESS(String DL_ADDRESS) {
		this.DL_ADDRESS = DL_ADDRESS;
	}

	public String getDL_BIRTHDAY() {
		return DL_BIRTHDAY;
	}

	public void setDL_BIRTHDAY(String DL_BIRTHDAY) {
		this.DL_BIRTHDAY = DL_BIRTHDAY;
	}

	public String getDL_ISSUE_DATE() {
		return DL_ISSUE_DATE;
	}

	public void setDL_ISSUE_DATE(String DL_ISSUE_DATE) {
		this.DL_ISSUE_DATE = DL_ISSUE_DATE;
	}

	public String getDL_CLASS() {
		return DL_CLASS;
	}

	public void setDL_CLASS(String DL_CLASS) {
		this.DL_CLASS = DL_CLASS;
	}

	public String getDL_VALIDFROM() {
		return DL_VALIDFROM;
	}

	public void setDL_VALIDFROM(String DL_VALIDFROM) {
		this.DL_VALIDFROM = DL_VALIDFROM;
	}

	public String getDL_VALIDFOR() {
		return DL_VALIDFOR;
	}

	public void setDL_VALIDFOR(String DL_VALIDFOR) {
		this.DL_VALIDFOR = DL_VALIDFOR;
	}

	public String getTIC_START() {
		return TIC_START;
	}

	public void setTIC_START(String TIC_START) {
		this.TIC_START = TIC_START;
	}

	public String getTIC_NUM() {
		return TIC_NUM;
	}

	public void setTIC_NUM(String TIC_NUM) {
		this.TIC_NUM = TIC_NUM;
	}

	public String getTIC_END() {
		return TIC_END;
	}

	public void setTIC_END(String TIC_END) {
		this.TIC_END = TIC_END;
	}

	public String getTIC_TIME() {
		return TIC_TIME;
	}

	public void setTIC_TIME(String TIC_TIME) {
		this.TIC_TIME = TIC_TIME;
	}

	public String getTIC_SEAT() {
		return TIC_SEAT;
	}

	public void setTIC_SEAT(String TIC_SEAT) {
		this.TIC_SEAT = TIC_SEAT;
	}

	public String getTIC_NAME() {
		return TIC_NAME;
	}

	public void setTIC_NAME(String TIC_NAME) {
		this.TIC_NAME = TIC_NAME;
	}

	public String getBLIC_CODE() {
		return BLIC_CODE;
	}

	public void setBLIC_CODE(String BLIC_CODE) {
		this.BLIC_CODE = BLIC_CODE;
	}

	public String getBLIC_NAME() {
		return BLIC_NAME;
	}

	public void setBLIC_NAME(String BLIC_NAME) {
		this.BLIC_NAME = BLIC_NAME;
	}

	public String getBLIC_TYPE() {
		return BLIC_TYPE;
	}

	public void setBLIC_TYPE(String BLIC_TYPE) {
		this.BLIC_TYPE = BLIC_TYPE;
	}

	public String getBLIC_ADDR() {
		return BLIC_ADDR;
	}

	public void setBLIC_ADDR(String BLIC_ADDR) {
		this.BLIC_ADDR = BLIC_ADDR;
	}

	public String getBLIC_PERSON() {
		return BLIC_PERSON;
	}

	public void setBLIC_PERSON(String BLIC_PERSON) {
		this.BLIC_PERSON = BLIC_PERSON;
	}

	public String getBLIC_CAPTIAL() {
		return BLIC_CAPTIAL;
	}

	public void setBLIC_CAPTIAL(String BLIC_CAPTIAL) {
		this.BLIC_CAPTIAL = BLIC_CAPTIAL;
	}

	public String getBLIC_DATE() {
		return BLIC_DATE;
	}

	public void setBLIC_DATE(String BLIC_DATE) {
		this.BLIC_DATE = BLIC_DATE;
	}

	public String getBLIC_PERIOD() {
		return BLIC_PERIOD;
	}

	public void setBLIC_PERIOD(String BLIC_PERIOD) {
		this.BLIC_PERIOD = BLIC_PERIOD;
	}

	public String getBLIC_ISSUE() {
		return BLIC_ISSUE;
	}

	public void setBLIC_ISSUE(String BLIC_ISSUE) {
		this.BLIC_ISSUE = BLIC_ISSUE;
	}

	public String getSSC_NAME() {
		return SSC_NAME;
	}

	public void setSSC_NAME(String SSC_NAME) {
		this.SSC_NAME = SSC_NAME;
	}

	public String getSSC_NUM() {
		return SSC_NUM;
	}

	public void setSSC_NUM(String SSC_NUM) {
		this.SSC_NUM = SSC_NUM;
	}

	public String getSSC_SHORTNUM() {
		return SSC_SHORTNUM;
	}

	public void setSSC_SHORTNUM(String SSC_SHORTNUM) {
		this.SSC_SHORTNUM = SSC_SHORTNUM;
	}

	public String getSSC_PERIOD() {
		return SSC_PERIOD;
	}

	public void setSSC_PERIOD(String SSC_PERIOD) {
		this.SSC_PERIOD = SSC_PERIOD;
	}

	public String getSSC_BANKNUM() {
		return SSC_BANKNUM;
	}

	public void setSSC_BANKNUM(String SSC_BANKNUM) {
		this.SSC_BANKNUM = SSC_BANKNUM;
	}

	public String getPAS_PASNO() {
		return PAS_PASNO;
	}

	public void setPAS_PASNO(String PAS_PASNO) {
		this.PAS_PASNO = PAS_PASNO;
	}

	public String getPAS_NAME() {
		return PAS_NAME;
	}

	public void setPAS_NAME(String PAS_NAME) {
		this.PAS_NAME = PAS_NAME;
	}

	public String getPAS_SEX() {
		return PAS_SEX;
	}

	public void setPAS_SEX(String PAS_SEX) {
		this.PAS_SEX = PAS_SEX;
	}

	public String getPAS_IDCARDNUM() {
		return PAS_IDCARDNUM;
	}

	public void setPAS_IDCARDNUM(String PAS_IDCARDNUM) {
		this.PAS_IDCARDNUM = PAS_IDCARDNUM;
	}

	public String getPAS_BIRTH() {
		return PAS_BIRTH;
	}

	public void setPAS_BIRTH(String PAS_BIRTH) {
		this.PAS_BIRTH = PAS_BIRTH;
	}

	public String getPAS_PLACE_BIRTH() {
		return PAS_PLACE_BIRTH;
	}

	public void setPAS_PLACE_BIRTH(String PAS_PLACE_BIRTH) {
		this.PAS_PLACE_BIRTH = PAS_PLACE_BIRTH;
	}

	public String getPAS_DATE_ISSUE() {
		return PAS_DATE_ISSUE;
	}

	public void setPAS_DATE_ISSUE(String PAS_DATE_ISSUE) {
		this.PAS_DATE_ISSUE = PAS_DATE_ISSUE;
	}

	public String getPAS_DATE_EXPIRY() {
		return PAS_DATE_EXPIRY;
	}

	public void setPAS_DATE_EXPIRY(String PAS_DATE_EXPIRY) {
		this.PAS_DATE_EXPIRY = PAS_DATE_EXPIRY;
	}

	public String getPAS_PLACE_ISSUE() {
		return PAS_PLACE_ISSUE;
	}

	public void setPAS_PLACE_ISSUE(String PAS_PLACE_ISSUE) {
		this.PAS_PLACE_ISSUE = PAS_PLACE_ISSUE;
	}

	public String getPAS_NATION_NAME() {
		return PAS_NATION_NAME;
	}

	public void setPAS_NATION_NAME(String PAS_NATION_NAME) {
		this.PAS_NATION_NAME = PAS_NATION_NAME;
	}

	public String getPAS_MACHINE_RCODE() {
		return PAS_MACHINE_RCODE;
	}

	public void setPAS_MACHINE_RCODE(String PAS_MACHINE_RCODE) {
		this.PAS_MACHINE_RCODE = PAS_MACHINE_RCODE;
	}

	public String getHSL_NAME() {
		return HSL_NAME;
	}

	public void setHSL_NAME(String HSL_NAME) {
		this.HSL_NAME = HSL_NAME;
	}

	public String getHSL_NUM() {
		return HSL_NUM;
	}

	public void setHSL_NUM(String HSL_NUM) {
		this.HSL_NUM = HSL_NUM;
	}

	public String getHSL_DATE() {
		return HSL_DATE;
	}

	public void setHSL_DATE(String HSL_DATE) {
		this.HSL_DATE = HSL_DATE;
	}

	public String getHSL_FIGURE() {
		return HSL_FIGURE;
	}

	public void setHSL_FIGURE(String HSL_FIGURE) {
		this.HSL_FIGURE = HSL_FIGURE;
	}

	public String getHSL_FIGURE_SUM() {
		return HSL_FIGURE_SUM;
	}

	public void setHSL_FIGURE_SUM(String HSL_FIGURE_SUM) {
		this.HSL_FIGURE_SUM = HSL_FIGURE_SUM;
	}

	public String getRMB_NUM() {
		return RMB_NUM;
	}

	public void setRMB_NUM(String RMB_NUM) {
		this.RMB_NUM = RMB_NUM;
	}

	public String getTBANK_NUM() {
		return TBANK_NUM;
	}

	public void setTBANK_NUM(String TBANK_NUM) {
		this.TBANK_NUM = TBANK_NUM;
	}

	public String getTBANK_NAME() {
		return TBANK_NAME;
	}

	public void setTBANK_NAME(String TBANK_NAME) {
		this.TBANK_NAME = TBANK_NAME;
	}

	public String getTBANK_ORGCODE() {
		return TBANK_ORGCODE;
	}

	public void setTBANK_ORGCODE(String TBANK_ORGCODE) {
		this.TBANK_ORGCODE = TBANK_ORGCODE;
	}

	public String getTBANK_CLASS() {
		return TBANK_CLASS;
	}

	public void setTBANK_CLASS(String TBANK_CLASS) {
		this.TBANK_CLASS = TBANK_CLASS;
	}

	public String getTBANK_CARD_NAME() {
		return TBANK_CARD_NAME;
	}

	public void setTBANK_CARD_NAME(String TBANK_CARD_NAME) {
		this.TBANK_CARD_NAME = TBANK_CARD_NAME;
	}
}
