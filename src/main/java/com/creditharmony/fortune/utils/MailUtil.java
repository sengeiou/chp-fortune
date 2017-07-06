package com.creditharmony.fortune.utils;

import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.creditharmony.adapter.bean.in.mail.MailInfo;
import com.creditharmony.adapter.constant.ServiceType;
import com.creditharmony.adapter.core.client.ClientPoxy;
import com.creditharmony.common.util.DateUtils;

/**
 * 邮件发送工具类
 * 
 * @Class Name MailUtil
 * @author 朱杰
 * @Create In 2016年3月6日
 */
public class MailUtil {
	
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
	 * 实时发送邮件
	 * 2016年3月6日
	 * By 朱杰
	 * @param recp 收件人地址
	 * @param attach 附件
	 * @param subject 主题
	 * @param content 邮件内容
	 */
	public static final void sendMail(String recp,String attach,String subject,String content){
		ClientPoxy service = new ClientPoxy(ServiceType.Type.CONFIRM_MAIL);
		// 发送邮件
		MailInfo mailInfo = new MailInfo();
		// 收件人地址
		String[] toAddrArray = {recp};
		mailInfo.setToAddrArray(toAddrArray);
		// 邮件附件
		if(StringUtils.isNotBlank(attach)){
			String[] attachURLArray = {attach};
			mailInfo.setDocIdArray(attachURLArray);
			// chp3.0附件
			if (attach.startsWith("{")) {
				mailInfo.setDocType("1");
			} else {
				mailInfo.setDocType("2");
			}
		}		
		// 邮件主题
		mailInfo.setSubject(subject);
		// 邮件内容
		mailInfo.setContent(content);
		//发送
		service.callService(mailInfo);
	}
	
}
