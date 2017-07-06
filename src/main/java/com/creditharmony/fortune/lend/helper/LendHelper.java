package com.creditharmony.fortune.lend.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.creditharmony.bpm.utils.SpringUtil;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.TransferState;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.workflow.entity.TransferRelation;

/**
 * 出借申请辅助类
 * 
 * @Class Name LendHelper
 * @author 朱杰
 * @Create In 2016年4月11日
 */
public class LendHelper {
	private static Logger logger = LoggerFactory.getLogger(LendHelper.class);
	
	
	/**
	 * 将页面可修改字段覆盖数据库字段
	 * 
	 * 2016年4月11日
	 * By 朱杰
	 * @param source
	 * @param target
	 */
	public static void updatePageProperties(LoanApply source, LoanApply target) {
		// 申请日期
		target.setApplyDate(source.getApplyDate());
		// 计划划扣日期
		target.setDeductDate(source.getDeductDate());
		// 计划出借日期
		target.setLendDate(source.getLendDate());
		// 出借模式
		target.setProductCode(source.getProductCode());
		// 付款方式
		target.setPayType(source.getPayType());
		// 划扣平台
		target.setDeductTypeId(source.getDeductTypeId());
		// 计划出借金额
		target.setLendMoney(source.getLendMoney());
		// 划扣金额
		target.setDeductMoney(source.getDeductMoney());
		// 内部转账金额
		target.setTransferMoney(source.getTransferMoney());
		// 协议版本
		target.setProtocoEdition(source.getProtocoEdition());
		// 合同编号
		target.setContractNumber(source.getContractNumber());
		// 销售折扣率
		target.setSalesDiscount(source.getSalesDiscount());
		// 备注
		target.setRemark(source.getRemark());
		// 信和宝类型
		target.setXinhebaoType(source.getXinhebaoType());
		// 内转出借编号
		target.setTransferLendId(source.getTransferLendId());
	}
	
	/**
	 * 内转出借信息辅助类
	 * 内部转账以及成功内部转账时处理
	 * 
	 * 2016年4月11日
	 * By 朱杰
	 * @param transferInfo 内转的信息
	 * @param applyCode 出借编号
	 * @param payType 划扣方式
	 */
	public static List<TransferRelation> settransferInfo(String transferInfo,
				String applyCode, String payType){
		
		List<TransferRelation> list = new ArrayList<TransferRelation>();

		if (null != transferInfo
				&& transferInfo.length() > 0
				&& (PayMent.NBZZ.value.equals(payType) || PayMent.CGBFNZ.value
						.equals(payType) || PayMent.ZZ.value
						.equals(payType)) ) {
			String[] transferArray = transferInfo.split(";");
			String code = null;
			String transferMoney = null;
			String realBackMoney = null;
			String backType = null;
			
			LoanApplyDao dao = SpringUtil.getCtx().getBean(LoanApplyDao.class);
			
			for (int i = 0; i < transferArray.length; i++) {
				String singleInfo = transferArray[i];
				String[] singleArray = singleInfo.split(",");
				code = singleArray[0];
				transferMoney = singleArray[1];
				realBackMoney = singleArray[2];

				LoanApply lendApply = dao.getLoanApplyByCode(code);
				// 该笔出借总共已经被内转多少金额
				BigDecimal hasTransferedMoney = dao.getSurplusAmount(code);
				BigDecimal lendMoney = lendApply.getLendMoney();
				
				BigDecimal transferMoney0 = new BigDecimal(transferMoney);
				BigDecimal realBackMoney0 = new BigDecimal(realBackMoney);
				BigDecimal totalTransferMoney = transferMoney0.add(hasTransferedMoney);
				if (PayMent.NBZZ.value.equals(payType)) {
					if (totalTransferMoney.compareTo(lendMoney) == 0) {
						backType = BackType.QBBJNBZZ.value;
					} else {
						backType = BackType.BFBJNBZZ.value;
					}
					logger.info(code + ",已经被内转的金额为hasTransferedMoney=" + totalTransferMoney + ",出借金额为lendMoney=" + lendMoney + ",totalTransferMoney.compareTo(lendMoney) == " + totalTransferMoney.compareTo(lendMoney));
				} else if (PayMent.CGBFNZ.value.equals(payType)) {
					backType = BackType.BFBJNBZZ.value;
				}
	
				TransferRelation relation = new TransferRelation();
				relation.setLendCodeParent(code);
				relation.setLendCodeChilde(applyCode);
				relation.setBackMoney(realBackMoney0.subtract(transferMoney0));
				relation.setTransferMoney(transferMoney0);
				relation.setBackMoneyType(backType);
				relation.setTransferState(TransferState.DSH.getKey());
	
				relation.preInsert();
				list.add(relation);
			}
		}
		return list;
	}
}
