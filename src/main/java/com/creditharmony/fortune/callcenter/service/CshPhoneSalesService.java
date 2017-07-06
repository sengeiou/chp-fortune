package com.creditharmony.fortune.callcenter.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.adapter.service.csh.CshPhoneSalesBaseService;
import com.creditharmony.adapter.service.csh.bean.CshPhoneSalesDetailOutParam;
import com.creditharmony.adapter.service.csh.bean.CshPhoneSalesInParam;
import com.creditharmony.adapter.service.csh.bean.CshPhoneSalesOutParam;
import com.creditharmony.fortune.callcenter.dao.CshPhoneSalesDao;
import com.creditharmony.fortune.utils.AppPropertiesUtil;

/**
 * 电销导出数据
 * 一、	需求目的/背景：
 *		财富端CHP系统已建立公共池，并将客户放至400801账户中，电销部可查询客户的基本信息，但无法对接线上呼叫系统即无法开展销售工作。现根据业务开展需要，CHP系统对EC呼叫系统提供视图，供EC呼叫系统进行数据查询，以便销售端外呼销售。
 * 	二、	功能方案
 *		推送方式：CHP对呼叫系统提供查询视图，供查询理财经理工号为400801的客户信息
 *		推送字段为：客户姓名、客户编号、性别、省份、城市、联系电话、证件类型、证件号码、投资状态。
 *		注：电销客户推送至呼叫系统后，可在呼叫系统实现客户的分配。
 *
 * @Class Name CshPhoneSalesService
 * @author gaoxu
 * @Create 2016-8-31 09:40:55
 */
@Service
@Transactional(value = "fortuneTransactionManager")
public class CshPhoneSalesService extends CshPhoneSalesBaseService {
	private Logger logger = LoggerFactory.getLogger(CshPhoneSalesService.class);
	@Autowired
	private CshPhoneSalesDao cshPhoneSalesDao;

	@Override
	public CshPhoneSalesOutParam doExec(CshPhoneSalesInParam paramCsh_PhoneSalesInParam) {
		logger.info("开始执行电销发送客户数据");
		CshPhoneSalesOutParam csh_PhoneSalesOutParam = new  CshPhoneSalesOutParam();
		
		try {
			List<CshPhoneSalesDetailOutParam> items   = new  ArrayList<CshPhoneSalesDetailOutParam>();
			CshPhoneSalesDetailOutParam  csh_PhoneSalesDetailOutParam  = null;
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("user_code", AppPropertiesUtil.getString("user_code"));
			params.put("countNum", Integer.valueOf(AppPropertiesUtil.getString("countNum")));
			List<Map<String,String>> customerInfoByMap = cshPhoneSalesDao.getCustomerInfoByMap(params);
			logger.info("查询电销发送数据成功");
			if(customerInfoByMap!=null && !customerInfoByMap.isEmpty()){
				for(Map<String,String> customerInfo: customerInfoByMap){
					csh_PhoneSalesDetailOutParam  = new CshPhoneSalesDetailOutParam();
					csh_PhoneSalesDetailOutParam.setCustomerName(customerInfo.get("customer_name"));
					csh_PhoneSalesDetailOutParam.setCustomerCode(customerInfo.get("customer_code"));
					csh_PhoneSalesDetailOutParam.setDictCustomerSex(customerInfo.get("customer_sex_str"));
					csh_PhoneSalesDetailOutParam.setAddrProvince(customerInfo.get("addr_province"));
					csh_PhoneSalesDetailOutParam.setAddrCity(customerInfo.get("addr_city"));
					csh_PhoneSalesDetailOutParam.setCustomerMobilephone(customerInfo.get("customer_mobilephone"));
					csh_PhoneSalesDetailOutParam.setDictCustomerCertType(customerInfo.get("certTypeStr"));
					csh_PhoneSalesDetailOutParam.setCustomerCertNum(customerInfo.get("customer_cert_num"));
					csh_PhoneSalesDetailOutParam.setDictCustomerState(customerInfo.get("custome_lend_str"));
					csh_PhoneSalesDetailOutParam.setCreateTime(customerInfo.get("create_time"));
					items.add(csh_PhoneSalesDetailOutParam);
				}
				cshPhoneSalesDao.addElectricSend(params);
				logger.info("插入历史电销数据成功："+items.size()+"条数");
			}
			csh_PhoneSalesOutParam.setItems(items);
			csh_PhoneSalesOutParam.setRetCode(ReturnConstant.SUCCESS);
		} catch (Exception e) {
			csh_PhoneSalesOutParam.setRetCode(ReturnConstant.ERROR);
			csh_PhoneSalesOutParam.setRetMsg("DB操作系统例外.");
			e.printStackTrace();
		}
		return csh_PhoneSalesOutParam;
	}
	

}
