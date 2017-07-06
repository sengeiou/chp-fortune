package com.creditharmony.fortune.app.webservice.ocr.server;

import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * OCR一期财富接口服务
 * @author 王俊杰
 * @date 2016-4-8
 */
@WebService
public interface IOcrServer {
	
	/**
	 * 财富登录
	 * @author 王俊杰
	 * @date 2016-4-8
	 * @param jsonStr
	 * @return
	 */
	@WebMethod
	String login(String jsonStr);
	
	/**
	 * 开户行名称列表查询
	 * @author 王俊杰
	 * @date 2016-4-8
	 * @return
	 */
	String getAccountBankList();
	
	/**
	 * 手机号有效性校验
	 * @author 王俊杰
	 * @date 2016-4-8
	 * @param mobilephone
	 * @return
	 */
	@WebMethod
	String checkMobilephone(String mobilephone);
	
	/**
	 * 客户信息录入
	 * @author 周怀富
	 * @date 2016-4-8
	 * @param jsonStr
	 * @return
	 */
	@WebMethod
	String saveCustomerInfoNew(String jsonStr);
	
	/**
	 * 字典列表查询
	 * @author 周怀富
	 * @date 2016-4-8
	 * @param typeCode
	 * @return
	 */
	@WebMethod
	String getLendDictionaryList(String jsonStr);
	
	/**
	 * 银行卡省市区列表查询
	 * @author 周怀富
	 * @date 2016-4-8
	 * @param jsonStr
	 * @return
	 */
	@WebMethod
	String getBankAddressList(String jsonStr);
	
	/**
	 * 补录身份证信息查询
	 * @author 张虎
	 * @date 2016-4-8
	 * @param jsonStr
	 * @return
	 */
	@WebMethod
	String getCertInfo(String jsonStr);
	
	/**
	 * 补录身份证信息保存
	 * @author 张虎
	 * @date 2016-4-8
	 * @param jsonStr
	 * @return
	 */
	@WebMethod
	String saveCertInfo(String jsonStr);
	
	/**
	 * 补录银行卡信息查询
	 * @author 张虎
	 * @date 2016-4-8
	 * @param jsonStr
	 * @return
	 */
	@WebMethod
	String getBankInfo(String jsonStr);
	
	/**
	 * 补录银行卡信息保存
	 * @author 南金陵
	 * @date 2016-4-8
	 * @param jsonStr
	 * @return
	 */
	@WebMethod
	String saveBankInfoNew(String jsonStr);
	
	/**
	 * 投资模式列表查询
	 * @author 南金陵
	 * @date 2016-4-8
	 * @param jsonStr
	 * @return
	 */
	@WebMethod
	String getInvestmentProductList();
	
	/**
	 * 修改登录密码
	 * @author 南金陵
	 * @date 2016-4-8
	 * @param jsonStr
	 * @return
	 */
	@WebMethod
	String modifyPassword(String jsonStr);
	
	/**
	 * 获取最新版本
	 * @author 王俊杰
	 * @date 2016-4-8
	 * @return
	 */
	String getNewVersion();
	
	/**
	 * OCR上传图片到CE系统
	 * @author 王俊杰
	 * @date 2016-5-6
	 * @param handler
	 * @return
	 */
	String uploadFile2CE(DataHandler handler);
}
