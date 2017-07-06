package com.creditharmony.fortune.app.webservice.ocr.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.app.webservice.ocr.entity.BankInfoBean;
import com.creditharmony.fortune.app.webservice.ocr.entity.CertInfoBean;
import com.creditharmony.fortune.app.webservice.ocr.entity.Dictionary;
import com.creditharmony.fortune.app.webservice.ocr.entity.LoanAccountBank;
import com.creditharmony.fortune.app.webservice.ocr.entity.OcrCustomerInfo;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.Product;

/**
 * OCR财富Dao
 * @author 王俊杰
 * @Create In 2016年4月8日
 */
@FortuneBatisDao
public interface OcrDao {
	
	/**
	 * 获取开户行字典列表
	 * @author 王俊杰
	 * @date 2016-4-11
	 * @param typeCode
	 * @return
	 */
	List<LoanAccountBank> getAccountBankList(String typeCode);
	/**
	 * 财富补录身份证信息查询
	 * 2016年4月11日
	 * By zhanghu
	 * @param certInfoBean
	 * @return
	 */
	public OcrCustomerInfo getCustomerByMobile(CertInfoBean certInfoBean);

	/**
	 * 财富补录身份证信息保存
	 * 2016年4月11日
	 * By zhanghu
	 * @param customer
	 * @return
	 */
	public void updateCustomerCert(OcrCustomerInfo customer);
	
	/**
	 * 财富补录银行卡信息查询
	 * 2016年4月11日
	 * By zhanghu
	 * @param bankInfoBean
	 * @return
	 */
	public OcrCustomerInfo getCustomerByCert(BankInfoBean bankInfoBean);
	
	/**
	 * 财富补录银行卡信息新增
	 * 2016年4月13日
	 * @param customer
	 */
	public void addCustomerAccount(OcrCustomerInfo customer);
	
	/**
	 * 财富补录银行卡信息更新
	 * 2016年4月13日
	 * @param params
	 */
	public void updateCustomerAccount(OcrCustomerInfo customer);
	
	/**
	 * 获取产品详细信息 
	 * 2016年4月14日
	 * @param map
	 * @return
	 */
	public List<Product> getProductList(Map<String, Object> map);
	
	/**
	 * 根据登录名查询角色集合
	 * 2016年5月6日
	 * By 王俊杰
	 * @param loginName
	 * @return
	 */
	List<String> selectRoleIdList(String loginName);
	
	/**
	 * 根据登录名查询姓名和门店
	 * 2016年5月6日
	 * By 王俊杰
	 * @param loginName
	 * @return
	 */
	Map<String, Object> selectNameAndOrg(String loginName);
	
	/**
	 * 保存图片ID到附件表
	 * @author 王俊杰
	 * @date 2016-5-9
	 * @param tableName
	 * @param tableId
	 * @param fileId
	 */
	void saveFileId2Attachment(Map<String, String> map);
	
	/**
	 * 财富数据字典列表
	 * 2016年4月12日
	 * By 周怀富
	 * @param typeCode
	 * @return
	 */
	public List<Dictionary> getDictionaryByCode(String typeCode);
	
	/**
	 * 查询银行卡区县列表
	 * 2016年4月12日
	 * By 周怀富
	 * @param params
	 * @return
	 */
	public List<String> getBankDistrictList(Map<String, String> params);
	
	/**
	 * 查询银行卡省市区
	 * 2016年4月12日
	 * By 周怀富
	 * @param params
	 * @return
	 */
	public List<String> getBankAddressList(Map<String, String> params);
	
	/**
	 * 根据名称查询地区编码
	 * 2016年4月15日
	 * By 周怀富
	 * @param params
	 * @return
	 */
	public String getAreaCodeByName(Map<String,String> params);
    
	
	/**
	 * 根据电话获取客户
	 * 2016年4月12日
	 * By 周怀富
	 * @param mobilephone
	 * @return
	 */
	public List<Customer> getCustomerListByMobile(String mobilephone);
	
	/**
	 * 检查身份证
	 * 2016年4月15日
	 * By 周怀富
	 * @param certNo
	 * @return
	 */
	public List<Customer> getCustomerListByCert(String certNo);
	
	/**
	 * 查询手机是否被三网中其它人占用的机构
	 * 2016年5月12日
	 * By 周怀富
	 * @param userId
	 * @param phone
	 * @return
	 */
	public List<String> getOtherOrgByPhone(String phone);
}
