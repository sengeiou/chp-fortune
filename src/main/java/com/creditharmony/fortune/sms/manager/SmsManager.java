package com.creditharmony.fortune.sms.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.sms.dao.SmsDao;
import com.creditharmony.fortune.sms.entity.SmsHis;
import com.creditharmony.fortune.sms.entity.SmsSendList;
import com.creditharmony.fortune.sms.entity.SmsTemplate;
/**
 * 短信操作
 * @Class Name SmsManager
 * @author 朱杰
 * @Create In 2016年3月6日
 */

@Service
@Transactional(readOnly = true, value = "fortuneTransactionManager")
public class SmsManager extends CoreManager<SmsDao, SmsSendList>{
	
	@Autowired
	protected SmsDao smsDao;
	
	/**
	 * 发送履历保存
	 * 
	 * 2015年12月22日
	 * By 朱杰
	 * @param page
	 * @param search
	 * @return
	 */
	@Transactional(readOnly = false, value = "fortuneTransactionManager")
	public void insertSmsHis(SmsHis smsHis){
		smsDao.insertSmsHis(smsHis);

	}
	
	/**
	 * 获取短信模板
	 * 2016年3月6日
	 * By 朱杰
	 * @param templateCode 模板Code
	 */
	public SmsTemplate getSmsTemplate(String templateCode){
		return smsDao.getSmsTemplate(templateCode);
	}
}


