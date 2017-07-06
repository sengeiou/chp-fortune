package com.creditharmony.fortune.verify.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.fortune.type.VerifyPinType;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.utils.AppPropertiesUtil;
import com.creditharmony.fortune.verify.dao.CustomerVerifyDao;
import com.creditharmony.fortune.verify.entity.CustomerVerify;

/**
 * 验证码操作
 * 
 * @Class Name CustomerVerifyManager
 * @author 朱杰
 * @Create In 2016年3月6日
 */
@Service
public class CustomerVerifyManager extends
		CoreManager<CustomerVerifyDao, CustomerVerify> {
	

	/**
	 * 更新验证码信息
	 * 2016年3月6日
	 * By 朱杰
	 * @param verifyPinType 验证码类型
	 * @param customerId 客户id
	 * @param pin 验证码
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void updateVerifyInfo(String verifyPinType,String customerId,String pin) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("customerId", customerId);
		param.put("verifyType", verifyPinType);
		CustomerVerify customerVerify = super.dao.selectByParams(param);
		if(customerVerify == null){
			//插入操作
			CustomerVerify insert = new CustomerVerify();
			//客户ID
			insert.setCustomerId(customerId);
			insert.setVerifyType(verifyPinType);
			insert.setVerifyPin(pin);
			insert.preInsert();
			super.dao.insert(insert);
		}else{
			//更新验证码
			customerVerify.setVerifyPin(pin);
			customerVerify.preUpdate();
			//同时更新创建人和创建时间
			customerVerify.setCreateBy(customerVerify.getModifyBy());
			customerVerify.setCreateTime(new Date());
			super.dao.update(customerVerify);
		}
	}
	
	/**
	 * 验证码对比
	 * 
	 * 2016年3月6日
	 * By 朱杰
	 * @param verifyPinType 验证码类型
	 * @param customerId 客户id
	 * @param pin 验证码
	 */
	public String checkPin(String verifyPinType,String customerId,String pin){
		if("ZZZZ".equals(pin) && "false".equals(AppPropertiesUtil.getString("verify_pin"))){
			return YoN.SHI.value;
		}
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("customerId", customerId);
		param.put("verifyType", verifyPinType);
		CustomerVerify customerVerify = super.dao.selectByParams(param);
		if(customerVerify!=null){
			Date d=customerVerify.getModifyTime();
			Date now=new Date();
			long result=(now.getTime()-d.getTime())/60000 ;
			if(VerifyPinType.EMAIL.value.equals(verifyPinType)){
				
				if(result>30){
					return YoN.FOU.value;
				}
			}else if(VerifyPinType.SMS.value.equals(verifyPinType)){
				if(result>15){
					return YoN.FOU.value;
				}
			}
		}
		
		if(customerVerify != null && customerVerify.getVerifyPin().equals(pin)){
			return YoN.SHI.value;
		}else{
			return YoN.FOU.value;
		}
	}

	/**
	 * 验证码生成
	 * 2016年3月8日
	 * By 郭才林
	 * @return
	 */
	public String makePin() {
		Random rd = new Random();
		String n = "";
		int getNum;
		do {
			// 产生数字0-9的随机数
			getNum = Math.abs(rd.nextInt(Integer.MAX_VALUE)) % 10 + 48;
			char num1 = (char) getNum;
			String dn = Character.toString(num1);
			n += dn;
		} while (n.length() < 6);
		return n;
	}
	
}
