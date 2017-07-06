package com.creditharmony.fortune.sms.dao;

import java.util.Map;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.sms.entity.SmsHis;
import com.creditharmony.fortune.sms.entity.SmsSendList;
import com.creditharmony.fortune.sms.entity.SmsTemplate;

/**
 * 短信操作dao
 * @Class Name SmsDao
 * @author 朱杰
 * @Create In 2016年3月4日
 */
@FortuneBatisDao
public interface SmsDao extends CrudDao<SmsSendList> {
	
	/**
	 * 获取短信待发送信息
	 * 2016年2月4日
	 * By 韩龙
	 * @param parameter
	 * @return
	 */
	public SmsSendList getSmsSend(Map<String, Object> parameter);
	
	/**
	 * 插入待发送短信
	 * 2016年2月4日
	 * By 韩龙
	 * @param smsSendList
	 * @return
	 */
	public int insert(SmsSendList smsSendList);
	
	/**
	 * 根据模板code获取短信模板
	 * 2016年1月20日
	 * By 陈广鹏
	 * @param templateCode
	 * @return
	 */
	public SmsTemplate getSmsTemplate(String templateCode);

	/**
	 * 插入短信发送记录
	 * 2016年1月20日
	 * By 陈广鹏
	 * @param his
	 */
	public void insertSmsHis(SmsHis his);
	
}