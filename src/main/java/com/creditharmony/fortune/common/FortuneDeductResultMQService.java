/**
 * @Probject Name: chp-adapter-web-djr
 * @Path: tianzt.service.implChangeCustomerEmpInfoService.java
 * @Create By yourname
 * @Create In 2016年1月26日 上午9:39:03
 */
package com.creditharmony.fortune.common;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.creditharmony.core.deduct.bean.out.FortuneDeductResult;
import com.creditharmony.fortune.deduct.proxy.DeductResultProxy;

/**
 * @Class Name DeductResultMQService
 * @author 施大勇
 * @Create In 2016年02月23日
 */
@Service
public class FortuneDeductResultMQService implements MessageListener {
	/** 日志. */
	private static final Logger logger = LoggerFactory.getLogger(FortuneDeductResultMQService.class);

	/**
	 * 取得MQ信息
	 */
	@Override
	public void onMessage(Message msgParam) {
		final TextMessage mapMessage = (TextMessage) msgParam;
		try {
			long sendTime = mapMessage.getJMSTimestamp();
			// 发送方ID
			String fromSys = mapMessage.getStringProperty("FromSys");
			// 发送消息ID
			String msgTypeID = mapMessage.getStringProperty("MsgTypeID2");
			// 发送消息名
			String msgTypeName = mapMessage.getStringProperty("MsgTypeName2");
			// 消息主体
			String jsonMsg = mapMessage.getText();
			logger.debug("【财富系统】划扣结果返回：" + sendTime);
			logger.debug("FromSys : " + fromSys);
			logger.debug("MsgTypeID2 : " + msgTypeID);
			logger.debug("MsgTypeName2 : " + msgTypeName);
			logger.debug("JsonMsg : " + jsonMsg);
			List<FortuneDeductResult> retList = JSONArray.parseArray(jsonMsg, FortuneDeductResult.class);
			System.out.println(retList.size());
			// 调用财富天系统回盘处理
			DeductResultProxy deductResultProxy = new DeductResultProxy();
			logger.debug("调用财富天系统回盘处理------->开始");
			deductResultProxy.executeBatch(retList);
			logger.debug("调用财富天系统回盘处理------->结束");
		} catch (JMSException e) {
			logger.debug("ChangeCustomerEmpInfoService ------- ERROR");
			e.printStackTrace();
		}
	}
}
