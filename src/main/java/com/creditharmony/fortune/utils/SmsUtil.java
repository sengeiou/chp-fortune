package com.creditharmony.fortune.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.creditharmony.adapter.bean.BaseOutInfo;
import com.creditharmony.adapter.bean.in.sms.SmsInfo;
import com.creditharmony.adapter.constant.ServiceType;
import com.creditharmony.adapter.core.client.ClientPoxy;
import com.creditharmony.common.util.DateUtils;

/**
 * 短信发送工具类
 * 
 * @Class Name SmsUtil
 * @author 陈广鹏
 * @Create In 2016年1月20日
 */
public class SmsUtil {
	
	/**
	 * 生成短信发送seqId，规则："tz" + yyyyMMdd + 9位随机字符串 
	 * 2016年1月20日 
	 * By 陈广鹏
	 * @return
	 */
	public static String getSeqId() {
		String seqId = "";
		Date date = new Date();
		seqId = DateUtils.formatDate(date, "yyyyMMddHHmmssSSS");
		// seqId = "tz" + seqId + randomString(7);
		return seqId;
	}

	/**
	 * 随机产生指定长度的字符串
	 * 
	 * @param length
	 *            指定长度
	 * @return 结果
	 */
	public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		Random strGen = new Random();
		char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")
				.toCharArray();
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[strGen.nextInt(61)];
		}
		return new String(randBuffer);
	}
	
	/**
	 * 实时发送短信
	 * 2016年3月6日
	 * By 朱杰
	 * @param phone 手机号码
	 * @param msg 发送内容
	 */
	public static final BaseOutInfo sendSms(String phone,String msg){
		ClientPoxy service = new ClientPoxy(ServiceType.Type.SEND_SMS);
		SmsInfo param = new SmsInfo();
		param.setSeqId(SmsUtil.getSeqId());
		param.setPhoneNo(phone);
		param.setContent(msg);
		// 短信息发送
		return service.callService(param);
	}
	
	/**
	 * 获取当前系统时间
	 * @param patten yyyy/MM/dd 或  "yyyy-MM-dd HH:mm:ss"
	 * @return String
	 */
	public static String nowTime(String patten) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(new Date().getTime());
		SimpleDateFormat dateFormat = new SimpleDateFormat(patten);
		return dateFormat.format(c.getTime());
	}
}
