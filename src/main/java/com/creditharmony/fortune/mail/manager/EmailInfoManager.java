package com.creditharmony.fortune.mail.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.mail.dao.EmailInfoDao;
import com.creditharmony.fortune.mail.entity.EmailInfo;
import com.creditharmony.fortune.mail.entity.EmailTemplate;
/**
 * 邮件操作
 * @Class Name SmsManager
 * @author 朱杰
 * @Create In 2016年3月6日
 */

@Service
@Transactional(readOnly = true, value = "fortuneTransactionManager")
public class EmailInfoManager extends CoreManager<EmailInfoDao, EmailInfo>{
	
	@Autowired
	protected EmailInfoDao emailInfoDao;
	
	/**
	 * 邮件模板履历获取
	 * 
	 * 2015年12月22日
	 * By 朱杰
	 * @param page
	 * @param search
	 * @return
	 */
	@Transactional(readOnly = false, value = "fortuneTransactionManager")
	public EmailTemplate getEmailTemplateByType(String templateType){
		return super.dao.getEmailTemplateByType(templateType);
	}
	
	
}


