package com.qianfeng.sms;

import com.google.gson.Gson;
import com.qianfeng.sms.common.Config;
import com.qianfeng.sms.common.HttpUtil;
import com.qianfeng.sms.common.SMSResponseCode;
import com.qianfeng.sms.common.SMSResult;

import java.net.URLEncoder;

/**
 * 验证码通知短信接口
 * 
 * @ClassName: IndustrySMS
 * @Description: 验证码通知短信接口
 *
 */
public class IndustrySMS {

	public static void main(String[] args){
		SMSResult result = IndustrySMS.send("13916142343", "999999");
		if (SMSResponseCode.SUCCESS.equals(result.getRespCode())){
			System.out.println("成功");
		}else {
			System.out.println(result.getRespCode()+":"+result.getRespDesc());
		}
	}
	private static String operation = "/industrySMS/sendSMS";
	private static String accountSid = Config.ACCOUNT_SID;

	public static SMSResult send(String to, String code){
		String smsContent = "【留仙科技】您的验证码为"+code+"，请于5分钟内正确输入，如非本人操作，请忽略此短信。";
		String tmpSmsContent = null;
		try{
			tmpSmsContent = URLEncoder.encode(smsContent, "UTF-8");
		}catch(Exception e){
			//这种做法是大忌！
			e.printStackTrace();
		}
		String url = Config.BASE_URL + operation;
		String body = "accountSid=" + accountSid + "&to=" + to + "&smsContent=" + tmpSmsContent
				+ HttpUtil.createCommonParam();
		// 提交请求
		String result = HttpUtil.post(url, body);
		//json -> object
		Gson gson = new Gson();
		return gson.fromJson(result,SMSResult.class);
	}

}
