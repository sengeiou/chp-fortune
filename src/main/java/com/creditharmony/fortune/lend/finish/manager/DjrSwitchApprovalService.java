package com.creditharmony.fortune.lend.finish.manager;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.adapter.service.djrswitch.DjrSwitchApprovalBaseService;
import com.creditharmony.adapter.service.djrswitch.bean.DjrSwitchApprovalInParam;
import com.creditharmony.adapter.service.djrswitch.bean.DjrSwitchApprovalOutParam;
import com.creditharmony.core.fortune.type.FortuneChannelFlag;
import com.creditharmony.core.fortune.type.FortuneSwitchApproveStatus;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyPoolDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.lend.finish.dao.LendFinishDao;

/**
 * 大金融将审批结果发送CHP(审批成功 / 审批失败 / 撤销转投)
 * @author 陈晓强
 */
@Service
@Transactional(value = "fortuneTransactionManager")
public class DjrSwitchApprovalService extends DjrSwitchApprovalBaseService {

	@Autowired
	private LendFinishDao lendFinishDao;
	@Autowired
	private BackMoneyPoolDao backMoneyPoolDao;
	@Autowired
	private CheckManager checkManager;

	@Override
	public DjrSwitchApprovalOutParam doExec(DjrSwitchApprovalInParam paramBean) {
		DjrSwitchApprovalOutParam outParam = new DjrSwitchApprovalOutParam();
		LoanApply loanApply = new LoanApply();
		String lendCode = paramBean.getLendCode();
		try {
			String result = paramBean.getResult();
			loanApply.setApplyCode(lendCode);
			Date approveDate = new Date();
			if ("1".equals(result)) {
				loanApply.setSwitchApproveStatus(FortuneSwitchApproveStatus.SPCG.value);
				// 审批成功修改渠道为大金融
				editChannelFlag(lendCode, FortuneChannelFlag.DJR.value);
				// 补息天数
				LoanApply lp = lendFinishDao.get(loanApply);
				if (lp.getExpireDate() != null) {
					BackMoneyPool backMoneyPool = new BackMoneyPool();
					backMoneyPool.setLendCode(lendCode);
					if (lp.getExpireDate().getTime() > approveDate.getTime()) {
						backMoneyPool.setInterestDay("0");
					} else {
						long day = (approveDate.getTime() - lp.getExpireDate().getTime()) / (24 * 60 * 60 * 1000);
						backMoneyPool.setInterestDay(String.valueOf(day));
					}
					backMoneyPoolDao.updateByLendCode(backMoneyPool);
				}
				checkManager.addCheckDjr(lendCode, "转投大金融状态变更为：审核成功,渠道改为:大金融", "审核", "201612161601_djr", "大金融");
			} else if ("2".equals(result)) {
				// 审批失败
				loanApply.setSwitchApproveStatus(FortuneSwitchApproveStatus.SPSB.value);
				loanApply.setApproveReason(paramBean.getResultReason());
				// 审核失败修改渠道为线下
				editChannelFlag(lendCode, FortuneChannelFlag.XX.value);
				checkManager.addCheckDjr(lendCode, "转投大金融状态变更为：审核失败，原因为:" + paramBean.getResultReason() + "", "审核", "201612161601_djr", "大金融");
			} else if ("3".equals(result)) {
				// 撤销转投
				loanApply.setSwitchApproveStatus(FortuneSwitchApproveStatus.CXZT.value);
				loanApply.setCancelReason(paramBean.getResultReason());
				// 撤销转投修改渠道为线下
				editChannelFlag(lendCode, FortuneChannelFlag.XX.value);
				checkManager.addCheckDjr(lendCode, "转投大金融状态变更为：撤销转投,渠道改为:线下", "审核", "201612161601_djr", "大金融");
			}
			loanApply.setApproveDate(approveDate);
			int count = lendFinishDao.updateSwitchApproveStatus(loanApply);
			if (count > 0) {
				outParam.setRetCode(ReturnConstant.SUCCESS);
			} else {
				outParam.setRetCode(ReturnConstant.FAIL);
			}
		} catch (Exception e) {
			outParam.setRetCode(ReturnConstant.ERROR);
			outParam.setRetMsg("修改审批状态异常!" + e);
			checkManager.addCheckDjr(lendCode, "转投大金融审核失败,异常信息为：" + e.getMessage(), "审核", "201612161601_djr", "大金融");
		}
		return outParam;
	}

	private void editChannelFlag(String lendCode, String channelFlag) {
		BackMoneyPool backMoneyPool = new BackMoneyPool();
		backMoneyPool.setLendCode(lendCode);
		backMoneyPool.setDictFortunechannelflag(channelFlag);
		backMoneyPoolDao.updateByLendCode(backMoneyPool);
	}
}