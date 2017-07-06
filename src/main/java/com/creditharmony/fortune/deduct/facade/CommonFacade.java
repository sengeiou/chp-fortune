package com.creditharmony.fortune.deduct.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.deduct.service.DeductFileManager;
import com.creditharmony.fortune.deduct.service.DeductManager;

@Service
public class CommonFacade {

	/**
	 * 初始化logger对象
	 */
	protected Logger logger = LoggerFactory.getLogger(CommonFacade.class);
	
	@Autowired
	private DeductFileManager deductFileManager;
	
	@Autowired
	private DeductManager deductManager;
	
	/**
	 * 制作文件信息
	 * 2016年4月15日
	 * By 韩龙
	 * @param lendCode
	 */
	public void makeFileInfo(String lendCode) {
		try {
			// 文件制作
			deductFileManager.makeFile(lendCode);
			logger.debug("文件制作成功");
		} catch (Exception e) {
			logger.debug("文件制作失败");
			FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, lendCode, FortuneLogNode.DEDUCT_BALANCE.getCode(),
					FortuneLogLevel.YELLOW.value, e.getMessage()));
		}
	}
	
	/**
	 * 发送短信信息
	 * 2016年4月15日
	 * By 韩龙
	 * @param lendCode
	 * @param dp
	 */
	public void smsInfo(String lendCode, String status,LoanApply loanApply) {
		try {
			// 发短信
			deductManager.sendSms(lendCode,status,loanApply);
			logger.debug("发送邮件成功");
		} catch (Exception e) {
			logger.error("发送邮件失败",e.getMessage());
			FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, lendCode, FortuneLogNode.DEDUCT_BALANCE.getCode(),
					FortuneLogLevel.YELLOW.value, e.getMessage()));
		}
	}
	
}
