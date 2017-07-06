package com.creditharmony.fortune.back.interest.excute.service;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.bean.out.jzh.JzhTransferBuOutInfo;
import com.creditharmony.core.common.type.BackResult;
import com.creditharmony.core.fortune.type.BackInterestPlat;
import com.creditharmony.core.fortune.type.BacksmsState;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.moneyaccount.entity.MoneyAccountInfo;
import com.creditharmony.fortune.back.interest.common.dao.BackInterestCommonDao;
import com.creditharmony.fortune.back.interest.common.service.BackInterestCommonService;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.LineGoldBackInterestObj;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.sync.data.remote.FyMoneyAccountService;
import com.creditharmony.fortune.utils.AppPropertiesUtil;

/**
 * 线上-金账户回息
 * @Class Name OnLineGoldAccountBackInterest 
 * @author 李志伟
 * @Create In 2016年4月11日
 */
@Service
public class OnLineGoldAccountBackInterestManager {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BackInterestCommonService backInterestCommonService;
	@Autowired
	private FyMoneyAccountService fyMoneyAccountService;
	@Autowired
	private BackInterestCommonDao backInterestCommonDao;
	
	/**
	 * 线上回息-金账户发送单条数据
	 * 2016年5月15日
	 * by 李志伟
	 * @param gdBkin
	 * @param userId
	 * @param userName
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String sendDataForEach(LineGoldBackInterestObj gdBkin, String userId, String userName){
		
		BackInterestPool pool = new BackInterestPool();
		pool.setBackiId(gdBkin.getBackiId());
		pool.setVerState(StaticMethodUtil.getExcuteBackInterestStatus());
		pool.setVerTime(gdBkin.getVerTime());
		BackInterestPool fud = backInterestCommonDao.forUpdate(pool);
		if(null == fud){
			return gdBkin.getLendCode()+":已被其他人操作过<br/>";
		}
		String str = "";
		MoneyAccountInfo mai = new MoneyAccountInfo();
		BackInterestPool bip = new BackInterestPool();
		// 流水号
		mai.setMchntTxnSsn(gdBkin.getBackiId());
		// 标识
		mai.setFlag("JzhTransferBuInfo");
		String date = StaticMethodUtil.parseDate(gdBkin.getCurrentBillday(), GlobalConstant.yyyyMMdd);
		// 备注
		mai.setRem(date+""+gdBkin.getProductName()+"回息_"+gdBkin.getBackiId());
		// 金账户付款账户
		mai.setOutCustNo(AppPropertiesUtil.getString("jzh_fk_account"));
		// 收款登录账户
		mai.setInCustNo(gdBkin.getPosAccount());
		// 划拨金额
		mai.setAmt(gdBkin.getBackRealMoney().multiply(new BigDecimal("100")).toBigInteger().toString());
		// 发送数据
		JzhTransferBuOutInfo bi = goldAccountSendData(mai);
		if(bi.getRetCode().equals("0000")){
			
			bip = goldAccountReturnData(bi, bip, mai);
			bip.setBackMoneyDay(new Date());
			bip.setVerState(StaticMethodUtil.getExcuteBackInterestStatus());
			bip.setVerTime(gdBkin.getVerTime());
			bip.setModifyBy(userId);
			
			Check hfs = StaticMethodUtil.madeHistory(userId, userName, gdBkin.getLendCode(), GlobalConstant.COMMIT,  FortuneLogNode.INTEREST_EXCUTE.getName(), gdBkin.getBackiId(), GlobalConstant.DHX_LIST+""+GlobalConstant.JZH + "" + GlobalConstant.ONLINE + ";" + GlobalConstant.SEND_MONEY + mai.getAmt() + ";"+ GlobalConstant.UPDATE_ST + BacksmsState.getBacksmsState(BacksmsState.DHXQR.value));
			try {
				
				backInterestCommonService.lineSubmit(bip, hfs);
			} catch (Exception e) {
				str+= "[出借编号]:"+gdBkin.getLendCode()+"线上金账户回息成功但是数据库操作失败</br>"+e.getMessage();
				backInterestCommonService.addExceptionMesgs(bip.getBackiId(), e, str, FortuneLogNode.INTEREST_EXCUTE.getCode());
			}
		}else{
			str +=  "【出借编号】:"+gdBkin.getLendCode()+":"+bi.getRetCode()+bi.getRetMsg();		
		}
		return str;
	}
	
	/**
	 * 调用金账户线上回息接口
	 * 2016年4月12日
	 * by 李志伟
	 * @param mai
	 * @return
	 */
	public JzhTransferBuOutInfo goldAccountSendData(MoneyAccountInfo mai){
		
		JzhTransferBuOutInfo bi  = (JzhTransferBuOutInfo)fyMoneyAccountService.chooseInterface(mai);
		return bi;
	}
	
	/**
	 * 金账户线上回息返回数据
	 * 2016年4月12日
	 * by 李志伟
	 * @param bi
	 * @param bip
	 * @param mai
	 */
	public BackInterestPool goldAccountReturnData(JzhTransferBuOutInfo bi, BackInterestPool bip, MoneyAccountInfo mai){
		
		// 设置流水号
		bip.setBackiId(bi.getMchntTxnSsn());
		// 设置响应消息
		bip.setBackResult(BackResult.SUCESS.value);
		bip.setPlatformId(BackInterestPlat.JZH.value);
		bip.setBackMoneyStatus(BacksmsState.DHXQR.value);
		bip.setModifyTime(new Date());
		if(bi.getRetCode().equals(GlobalConstant.RETURN_NO)){// 成功
			
			// 设置响应码
			bip.setBackResult(BackResult.SUCESS.value);
			bip.setSuccessMoney(new BigDecimal(mai.getAmt()));
			bip.setFailMoney(BigDecimal.ZERO);
			bip.setBackDay(new Date());
		}else{// 失败
			
			bip.setBackResult(BackResult.FAIL.value);
			bip.setBackDay(new Date());
			bip.setSuccessMoney(BigDecimal.ZERO);
			bip.setFailMoney(new BigDecimal(mai.getAmt()));
		}
		return bip;
	}
}
