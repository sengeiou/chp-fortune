package com.creditharmony.fortune.app.webservice.ocr.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.ConfigStatus;
import com.creditharmony.fortune.app.webservice.ocr.dao.OcrDao;
import com.creditharmony.fortune.app.webservice.ocr.entity.OcrCustomerInfo;
import com.creditharmony.fortune.app.webservice.ocr.entity.OcrInvestmentProduct;
import com.creditharmony.fortune.common.dao.UtilDao;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.customer.dao.CustomerAccountDao;
import com.creditharmony.fortune.customer.entity.CustomerAccount;
import com.creditharmony.fortune.customer.entity.Product;

/**
 * 手机app(OCR) 服务类
 * @Class Name OrcServiceHelper
 * @author 王俊杰
 * @Create In 2016年4月13日
 */
public class OrcServiceHelper {
	
	private static UtilDao utilDao = SpringContextHolder.getBean(UtilDao.class);
	private static CustomerAccountDao customerAccountDao = SpringContextHolder.getBean(CustomerAccountDao.class);
	private static OcrDao OcrDaoHelp = SpringContextHolder.getBean(OcrDao.class);
	
	
	/**
	 * 更新补录银行卡信息保存
	 * 2016年4月14日
	 * @param customer
	 */
	public static void updateCustomerAccount(OcrCustomerInfo customer) {
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setCustomerCode(customer.getCustomerId());
		customerAccount.setAccountBankId(customer.getBankId());
		customerAccount.setAccountBranch(customer.getBranch());
		customerAccount.setAccountAddrProvince(customer.getBankProvince());
		customerAccount.setAccountAddrCity(customer.getBankCity());
		customerAccount.setAccountAddrDistrict(customer.getBankDistrict());
		customerAccount.setAccountCardOrBooklet("01");
		customerAccount.setAccountName("");
		customerAccount.setAccountNo(customer.getAccountid());
		customerAccount.setLendCode("");
		
		if ("00000".equals(customer.getBankId())) {
			customerAccount.preInsert();
			customerAccountDao.insert(customerAccount);
			saveFileId2Attachment("t_tz_customer_account",customerAccount.getId(),customer.getBankPicPath(),FileConstants.FILE_TYPE_YHKH);
		} else {
			customerAccount.preUpdate();
			customerAccountDao.update(customerAccount);
			saveFileId2Attachment("t_tz_customer_account",customerAccount.getId(),customer.getBankPicPath(),FileConstants.FILE_TYPE_YHKH);
		}
	}
	
	/**
	 * 获取产品详细信息
	 * 2016年4月14日
	 * @return 产品详细信息
	 */
	public  static List<OcrInvestmentProduct> getFullProductList() {
		List<OcrInvestmentProduct> orcProductList = new ArrayList<OcrInvestmentProduct>();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("productStatus", ConfigStatus.QY.value);
		List<Product> productList  = utilDao.getProductList(param);
		if(productList != null && productList.size() >0){
			for(Product product:productList){
				OcrInvestmentProduct orcProduct = new OcrInvestmentProduct();
				orcProduct.setProductId(product.getProductCode());
				orcProduct.setProductName(product.getProductName());
				orcProduct.setInvestmentTerm(StringUtils.toString(product.getProductPeriods()));
				//出借日期1/5之前用 oldYearprofit 之后用newYearprofit
				//TODO
				//orcProduct.setAnnualisedReturn(StringUtils.toString(product.getExpectProfit()));
				orcProduct.setIntroduction(product.getRemarks());
				orcProductList.add(orcProduct);
			}
		}
		return orcProductList;
	}
	
	/**
	 * 保存图片ID到附件表
	 * @author 王俊杰
	 * @date 2016-5-9
	 * @param tableName
	 * @param tableId
	 * @param fileId
	 * @param fileFlag
	 */
	public static void saveFileId2Attachment(String tableName, String tableId, String fileId, String fileFlag){
		if (StringUtils.isEmpty(tableId) || StringUtils.isEmpty(fileId)){
			return;
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", IdGen.uuid());
		map.put("tableName", tableName);
		map.put("tableId", tableId);
		map.put("fileId", fileId);
		map.put("fileFlag", fileFlag);
		OcrDaoHelp.saveFileId2Attachment(map);
	}
}
