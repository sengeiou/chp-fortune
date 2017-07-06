package com.creditharmony.fortune.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.creditharmony.adapter.bean.in.ca.Ca_AgentSignInBean;
import com.creditharmony.adapter.bean.in.ca.Ca_UnitSignInBean;
import com.creditharmony.adapter.bean.out.ca.Ca_SignOutBean;
import com.creditharmony.adapter.bean.out.ca.Ca_UnitSignOutBean;
import com.creditharmony.adapter.constant.CaKeyWordPosType;
import com.creditharmony.adapter.constant.CaUnitSignType;
import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.adapter.constant.ServiceType;
import com.creditharmony.adapter.core.client.ClientPoxy;
import com.creditharmony.core.fortune.entity.CaCustomerSign;
import com.creditharmony.core.fortune.type.CASignType;
import com.creditharmony.dm.service.DmService;

/**
 * CA操作工具类
 * 
 * @Class Name CaUtil
 * @author 朱杰
 * @Create In 2016年2月23日
 */
public class CaUtil {
	
	/**
	 * 日志对象
	 */
	protected static Logger logger = LoggerFactory.getLogger(CaUtil.class);
	
	/**
	 * CA签章,客户签名用
	 * 
	 * 2016年5月10日
	 * By 朱杰
	 * @param signType 签章文件
	 * @param pdfId
	 * @param param
	 * @return
	 */
	public static Ca_SignOutBean sign(String signType, String pdfId, CaCustomerSign param) {
		
			
		if(CASignType.CONTRACT.value.equals(signType)){
			// 出借合同
			return CaUtil.signContract(pdfId, param);
			
		}else if(CASignType.PAYMENTCONFIRMATION.value.equals(signType)){
			// 收款确认书
			return CaUtil.signPaymentConfirmation(pdfId,param);
			
		}else if(CASignType.CREDITFILE.value.equals(signType)){
			// 债权文件
			return CaUtil.signCreditFile(pdfId,param);
			
		}else if(CASignType.FYXY.value.equals(signType)){
			// 富友协议
			return CaUtil.signFyxy(pdfId, param);
		
		}else if(CASignType.ZF_CONTRACT.value.equals(signType)){
			// 出借合同作废
			return CaUtil.invalidContract(pdfId,param);
			
		}else if(CASignType.ZF_CREDITFILE.value.equals(signType)){
			// 债权文件作废
			return CaUtil.invalidCreditFile(pdfId,param);
		}
		
		return new Ca_SignOutBean();
		
	}
	
	/**
	 * 出借合同
	 * 2016年5月10日
	 * By 朱杰
	 * @param pdfId
	 * @param param
	 * @return
	 */
	private static Ca_SignOutBean signContract(String pdfId,CaCustomerSign param) {
		
		DmService dmService = DmService.getInstance();
		// 客户签字
		Ca_SignOutBean outInfo = CaUtil.signCustomer(pdfId, param, CaKeyWordPosType.RIGHT);
		
		if (ReturnConstant.SUCCESS.equals(outInfo.getRetCode())) {
			// 旧文件删除
			dmService.deleteDocument(pdfId);
			// 旧文件暂存
			pdfId = outInfo.getDocId();
			// 财富签章
			outInfo = CaUtil.signFortune(outInfo.getDocId(), "乙方：信和财富投资管理（北京）有限公司(盖章)", 
					CaUnitSignType.CF, CaKeyWordPosType.BOTTOM, "-30",param);			
			
			if (ReturnConstant.SUCCESS.equals(outInfo.getRetCode())) {				
				// 旧文件删除
				dmService.deleteDocument(pdfId);
				// 旧文件暂存
				pdfId = outInfo.getDocId();
				// 惠民签章
				outInfo = CaUtil.signFortune(outInfo.getDocId(), "信和惠民投资管理（北京）有限公司(盖章)", 
						CaUnitSignType.HM, CaKeyWordPosType.BOTTOM, "-30",param);
				
				if (ReturnConstant.SUCCESS.equals(outInfo.getRetCode())) {
					// 旧文件删除
					dmService.deleteDocument(pdfId);
					// 旧文件暂存
					pdfId = outInfo.getDocId();
					// 甲方签字
					param.setKeyword("甲方签字：");
					outInfo = CaUtil.signCustomer(outInfo.getDocId(), param, CaKeyWordPosType.RIGHT);
					
					if (ReturnConstant.SUCCESS.equals(outInfo.getRetCode())) {
						// 删除旧文件
						dmService.deleteDocument(pdfId);
					}
				}
				
			}
		}
		return outInfo;
	}
	
	/**
	 * 收款确认书
	 * 2016年5月10日
	 * By 朱杰
	 * @param pdfId
	 * @return
	 */
	private static Ca_SignOutBean signPaymentConfirmation(String pdfId,CaCustomerSign param) {
		
		DmService dmService = DmService.getInstance();
		// 夏靖签章
		Ca_SignOutBean outInfo = CaUtil.signFortune(pdfId, "签章：", 
				CaUnitSignType.XJ, CaKeyWordPosType.RIGHT, "0",param);
		
		if (ReturnConstant.SUCCESS.equals(outInfo.getRetCode())) {
			// 删除旧文件
			dmService.deleteDocument(pdfId);
			// 旧文件暂存
			pdfId = outInfo.getDocId();
			// 惠民签章
			outInfo = CaUtil.signFortune(outInfo.getDocId(), "盖章：", 
					CaUnitSignType.HM, CaKeyWordPosType.RIGHT, "0",param);
			
			if (ReturnConstant.SUCCESS.equals(outInfo.getRetCode())) {
				// 删除旧文件
				dmService.deleteDocument(pdfId);
			}
		}
		return outInfo;
	}
	
	/**
	 * 债权文件
	 * 2016年5月10日
	 * By 朱杰
	 * @param pdfId
	 * @return
	 */
	private static Ca_SignOutBean signCreditFile(String pdfId,CaCustomerSign param) {
		DmService dmService = DmService.getInstance();
		// 夏靖签章
		Ca_SignOutBean outInfo = CaUtil.signFortune(pdfId, "签章：", 
				CaUnitSignType.XJ, CaKeyWordPosType.RIGHT, "0",param);
		
		if (ReturnConstant.SUCCESS.equals(outInfo.getRetCode())) {
			// 删除旧文件
			dmService.deleteDocument(pdfId);
			// 旧文件暂存
			pdfId = outInfo.getDocId();
			// 惠民签章
			outInfo = CaUtil.signFortune(outInfo.getDocId(), "盖章：", 
					CaUnitSignType.HM, CaKeyWordPosType.RIGHT, "0",param);
			if (ReturnConstant.SUCCESS.equals(outInfo.getRetCode())) {
				// 删除旧文件
				dmService.deleteDocument(pdfId);
			}
		}
		return outInfo;
	}
	
	/**
	 * 富友协议
	 * 2016年5月10日
	 * By 朱杰
	 * @param pdfId
	 * @return
	 */
	private static Ca_SignOutBean signFyxy(String pdfId, CaCustomerSign param) {
		DmService dmService = DmService.getInstance();
		// 客户签字
		Ca_SignOutBean outInfo = CaUtil.signCustomer(pdfId, param, CaKeyWordPosType.RIGHT);
		if (ReturnConstant.SUCCESS.equals(outInfo.getRetCode())) {
			// 删除旧文件
			dmService.deleteDocument(pdfId);
		}
		return outInfo;
	}
	
	/**
	 * 出借合同作废
	 * 2016年5月10日
	 * By 朱杰
	 * @param pdfId
	 * @param param
	 * @return
	 */
	private static Ca_SignOutBean invalidContract(String pdfId,CaCustomerSign param) {
		// 作废章
		Ca_SignOutBean outInfo = signFortune(pdfId, "乙方：信和财富投资管理（北京）有限公司(盖章)", 
				CaUnitSignType.HM_CANCEL, CaKeyWordPosType.RIGHT, "-40",param);
		
		return outInfo;
	}
	
	/**
	 * 出借合同作废
	 * 2016年5月10日
	 * By 朱杰
	 * @param pdfId
	 * @param param
	 * @return
	 */
	private static Ca_SignOutBean invalidCreditFile(String pdfId,CaCustomerSign param) {
		// 作废章
		Ca_SignOutBean outInfo = signFortune(pdfId, "签章：", 
				CaUnitSignType.HM_CANCEL, CaKeyWordPosType.RIGHT, "100",param);
		
		return outInfo;
	}
	
	
	/**
	 * 公司签章（财富、惠民、夏靖）
	 * 2016年3月11日
	 * By 朱杰
	 * @param pdfId
	 * @return
	 */
	private static Ca_SignOutBean signFortune(String pdfId, String keyWord, 
			CaUnitSignType signType, CaKeyWordPosType pos, String offset,CaCustomerSign param) {
		
		Ca_UnitSignInBean inParam = new Ca_UnitSignInBean();
        inParam.setDocId(pdfId);
        
        inParam.setSubType("");
        inParam.setBatchNo("");
        if(param!=null && StringUtils.isNotEmpty(param.getSubType())){
        	inParam.setSubType(param.getSubType());
        }
        if(param!=null && StringUtils.isNotEmpty(param.getBatchNo())){
        	inParam.setBatchNo(param.getBatchNo());
        }
        inParam.setBusinessType(DmService.BUSI_TYPE_FORTUNE);        
        inParam.setUnitSignType(signType);
        inParam.setKeyWord(keyWord);
        inParam.setKeyWordPos(pos);
        inParam.setKeyWordOffset(offset);        
        ClientPoxy service = new ClientPoxy(ServiceType.Type.CA_UNIT_SIGN);
        Ca_SignOutBean outInfo = (Ca_UnitSignOutBean) service.callService(inParam);
        return outInfo;
	}
	
	/**
	 * 代理签章
	 * 2016年3月28日
	 * By 朱杰
	 */
	public static Ca_SignOutBean signCustomer(String pdfId,CaCustomerSign param,CaKeyWordPosType pos) {
		Ca_AgentSignInBean casinfo = new Ca_AgentSignInBean();
		casinfo.setSignerUserName(param.getCustName()); 
		casinfo.setSignerUserIDCard(param.getIdCard());
		casinfo.setDocId(pdfId);
		casinfo.setSubType(param.getSubType());
		casinfo.setBatchNo(param.getBatchNo());
		casinfo.setBusinessType(DmService.BUSI_TYPE_FORTUNE);
		casinfo.setKeyWord(param.getKeyword());
		casinfo.setKeyWordOffset("0");
		casinfo.setKeyWordPos(pos);

		ClientPoxy service = new ClientPoxy(ServiceType.Type.CA_AGENT_SIGN);

		Ca_SignOutBean outInfo = (Ca_SignOutBean) service.callService(casinfo);
		
		return outInfo;
	}
	
	
	 
}
