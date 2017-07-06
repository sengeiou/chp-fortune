package com.creditharmony.fortune.deduct.service;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.deduct.TaskService;
import com.creditharmony.core.deduct.bean.in.DeductReq;
import com.creditharmony.core.deduct.bean.out.DeResult;
import com.creditharmony.core.deduct.type.DeductFlagType;
import com.creditharmony.core.deduct.type.DeductWays;
import com.creditharmony.core.deduct.type.ResultType;
import com.creditharmony.core.fortune.type.DeductDataSendState;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.dao.FortuneDeductReqDao;
import com.creditharmony.fortune.deduct.entity.FortuneDeductReq;
import com.creditharmony.fortune.deduct.entity.ext.BaseExportModel;
import com.creditharmony.fortune.platform.service.PlatformGotoRuleManager;
import com.creditharmony.fortune.utils.OptionUtil;

/**
 * 划扣
 * @Class Name DeductBespokeManager
 * @author 韩龙
 * @Create In 2016年2月1日
 */
@Service
public class DeductReqManager extends CoreManager<FortuneDeductReqDao, FortuneDeductReq>{
	
	@Autowired
	private LoanApplyDao loanApplyDao;
	@Autowired
	private PlatformGotoRuleManager platformGotoRuleManager;
	
	/**
	 * 划扣记录保存
	 * 2016年4月8日
	 * By 韩龙
	 * @param deductReq
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false,
			propagation=Propagation.REQUIRES_NEW)
	public void addDeductReq(DeductReq deductReq, FortuneDeductReq fortuneDeductReq){
		logger.debug("划扣划扣记录保存------开始");
		String requestId = TaskService.getRequestId();
		String batId = TaskService.getRequestId();
		deductReq.setRequestId(requestId);		
		deductReq.setBatId(batId);
		BeanUtils.copyProperties(deductReq, fortuneDeductReq);
		fortuneDeductReq.setStatus(DeductDataSendState.FSCG.value);
		fortuneDeductReq.setFailedTimes(0);
		super.dao.insert(fortuneDeductReq);
		logger.debug("划扣划扣记录保存------结束");
	}
	
	/**
	 * 发送划扣
	 * 2016年4月8日
	 * By 韩龙
	 * @param commit
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String commit(DeductReq recReq, FortuneDeductReq req,
			DeResult deResult, String lendCode) {
		String message = "";
		if (ResultType.ADD_SUCCESS.getCode().equals(deResult.getReCode())) {
			req.setStatus(DeductDataSendState.COMMITCG.value);
			super.dao.update(req);
			deResult = TaskService.commit(recReq);
			if (!ResultType.ADD_SUCCESS.getCode().equals(deResult.getReCode())) {
				req.setStatus(DeductDataSendState.COMMITSB.value);
				super.dao.update(req);
				TaskService.rollBack(recReq);
				message = message + "业务ID【" + lendCode + "】发送第三方平台失败<br/>";
			}
		} else {
			message = message + "业务ID【" + lendCode + "】发送第三方平台失败<br/>";
			updateStatus(recReq, req, DeductDataSendState.COMMITSB.value);
			logger.debug("updateStatus结束");
		}
		return message;
	}
	
	/**
	 * 更新状态
	 * 2016年4月11日
	 * By 韩龙
	 * @param deductReq
	 * @param req
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void updateStatus(DeductReq deductReq,FortuneDeductReq req,String status){
		logger.debug("更新状态-------开始");
		logger.debug("更新" + DeductDataSendState.getDeductDataSendState(status));
		req.setStatus(status);
		super.dao.update(req);
		if(status.equals(DeductDataSendState.COMMITSB.value) 
				|| status.equals(DeductDataSendState.FSSB.value)){
			TaskService.rollBack(deductReq);
			logger.debug("发送出错信息回滚成功");
		}
		logger.debug("更新状态-------结束");
	}
	
	/**
	 * 拼装划扣请求对象
	 * 2016年4月24日
	 * By 朱杰
	 * @param baseExportModel
	 * @param actualDeductMoney 实际划扣金额
	 * @param type 备注参数
	 * @return
	 */
	public DeductReq setDeductReqInfo(BaseExportModel baseExportModel,String actualDeductMoney,String type){
		// 划扣请求对象
		DeductReq recReq = new DeductReq();
		LoanApply loanApply = new LoanApply();
		loanApply.setApplyCode(baseExportModel.getLendCode());
		loanApply = loanApplyDao.get(loanApply);
		String temp = platformGotoRuleManager.getDeductRule(DeductUtils
				.isNull(loanApply.getDeductTypeId()), DeductUtils
				.isNull(baseExportModel.getAccountAddrProvince()),
				DeductUtils.isNull(baseExportModel.getAccountBank()));
		String rule = DeductUtils.getDeductRule(temp);
		recReq.setBusinessId(DeductUtils.isNull(baseExportModel
				.getLendCode()));
		recReq.setSysId(DeductWays.CF_01.getCode());
		recReq.setDeductFlag(DeductFlagType.COLLECTION.getCode());
		recReq.setRule(DeductUtils.isNull(rule));
		recReq.setBankId(DeductUtils.isNull(baseExportModel
				.getAccountBank()));
		recReq.setAccountType(DeductUtils.isNull(baseExportModel.getAccountCardOrBooklet()));
		recReq.setBankProv(OptionUtil.getProvinceLabel(DeductUtils
				.isNull(baseExportModel.getAccountAddrProvince())));
		recReq.setBankCity(OptionUtil.getCityLabel(DeductUtils
				.isNull(baseExportModel.getAccountAddrCity())));
		recReq.setBankName(DeductUtils.isNull(baseExportModel
				.getAccountBranch()));
		recReq.setAccountNo(DeductUtils.isNull(baseExportModel
				.getAccountNo()));
		recReq.setAccountName(DeductUtils.isNull(baseExportModel
				.getAccountName()));
		recReq.setIdType(DeductUtils.isNull((baseExportModel.getDictCustomerCertType())));
		recReq.setIdNo(DeductUtils.isNull(baseExportModel
				.getCustomerCertNum()));
		recReq.setMobile(DeductUtils.isNull(baseExportModel
				.getCustomerMobilephone()));
		if(actualDeductMoney!=null){
			// 出借金额减去成功金额就是要划扣的金额
			BigDecimal deductMoney = loanApply.getLendMoney()
					.subtract(new BigDecimal(DeductUtils.isNullMoney(actualDeductMoney)));
			recReq.setAmount(deductMoney);
		}		
		recReq.setRemarks(baseExportModel.getLendCode()+type);
		
		return recReq;
	}
}

