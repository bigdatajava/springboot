/*package test;

import java.io.IOException;
import java.util.Map;

import com.bean.IdCardBean;
import com.common.CommonUtil;
import com.domain.StatusBeen;
import com.jar.base.Configuration;
import com.turec.idcard.OCRReadUtil;
import com.web.ApiResult;

import sun.misc.BASE64Decoder;

public class test {
	private static BASE64Decoder decoder = new sun.misc.BASE64Decoder();  
	public static void main(String[] args) throws Exception {
		a2();
	}

	public static void a1() {
		String pathString = "D:/123/";
		String path=Configuration.getProperty("ocrdllpath");
		System.out.println(path);
		OcrService ou=new OcrService();
		try {
			Map<String,Object> r=ou.getIdCardBeanByByte(ou.GetImgByte(pathString+"1.jpg"), ou.GetImgByte(pathString+"3.jpg"));
			System.out.println(r.get("idCardBean"));
			System.out.println(r.toString());
			System.out.println(ou.GetImgByte(pathString+"1.jpg"));
			System.out.println(ou.GetImgByte(pathString+"1.jpg").toString());
			System.out.println(ou.GetImgByte(pathString+"1.jpg").toString().getBytes());
		} catch (Exception e) {
			// TODO: handle exception
		}
		}
	
	public static ApiResult a2() throws Exception {
		ApiResult apiResult = new ApiResult();
		String pathString = "D:/123/";
		String path=Configuration.getProperty("ocrdllpath");
		System.out.println(path);
		OcrService ou=new OcrService();
			byte[] a = ou.GetImgByte(pathString+"1.jpg");
			byte[] b = ou.GetImgByte(pathString+"3.jpg");
			System.out.println("a="+a);
			System.out.println("atoString="+a.toString());
			byte[] bytes = decoder.decodeBuffer(a.toString()); 
			byte[] bytes2 = decoder.decodeBuffer(b.toString()); 
			
			//Map<String,Object> r=ou.getIdCardBeanByByte(a, b);
			IdCardBean idCardBean = OcrService.getIdCardByBytes(bytes2);
			apiResult.setStatus(200);
			apiResult.setErrmsg(StatusBeen.get("200"));
			apiResult.setData(idCardBean);
			System.out.println(idCardBean.getName());
			System.out.println(idCardBean.getPeriod());
			return apiResult;
		}
	
}
*/