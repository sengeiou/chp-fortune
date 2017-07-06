package com.creditharmony.fortune.lend.finish.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.adapter.service.djrswitch.DjrSwitchRechargeBaseService;
import com.creditharmony.adapter.service.djrswitch.bean.DjrSwitchRechargeInParam;
import com.creditharmony.adapter.service.djrswitch.bean.DjrSwitchRechargeOutParam;
import com.creditharmony.core.fortune.type.FortuneChannelFlag;
import com.creditharmony.core.fortune.type.FortuneSwitchApproveStatus;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyPoolDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.lend.finish.dao.LendFinishDao;

/**
 * 大金融充值成功，将消息发送CHP(充值成功)
 * @author 陈晓强
 */
@Service
@Transactional(value = "fortuneTransactionManager")
public class DjrSwitchRechargeService extends DjrSwitchRechargeBaseService {

	@Autowired
	private LendFinishDao lendFinishDao;
	@Autowired
	private BackMoneyPoolDao backMoneyPoolDao;
	@Autowired
	private CheckManager checkManager;

	@Override
	public DjrSwitchRechargeOutParam doExec(DjrSwitchRechargeInParam paramBean) {
		DjrSwitchRechargeOutParam outParam = new DjrSwitchRechargeOutParam();
		try {
			BackMoneyPool backMoneyPool = new BackMoneyPool();
			backMoneyPool.setLendCode(paramBean.getLendCode());
			backMoneyPool.setDictFortunechannelflag(FortuneChannelFlag.DJR.value);
			backMoneyPoolDao.updateByLendCode(backMoneyPool);

			LoanApply loanApply = new LoanApply();
			loanApply.setApplyCode(paramBean.getLendCode());
			loanApply.setSwitchApproveStatus(FortuneSwitchApproveStatus.ZTCG.value);
			int count = lendFinishDao.updateSwitchApproveStatus(loanApply);
			if (count > 0) {
				outParam.setRetCode(ReturnConstant.SUCCESS);
			} else {
				outParam.setRetCode(ReturnConstant.FAIL);
			}
			checkManager.addCheckDjr(paramBean.getLendCode(), "充值完成,状态改为:转投成功", "审核", "201612161601_djr", "大金融");
		} catch (Exception e) {
			outParam.setRetCode(ReturnConstant.ERROR);
			outParam.setRetMsg("修改充值状态异常：" + e);
			checkManager.addCheckDjr(paramBean.getLendCode(), "充值失败,异常信息为：" + e.getMessage(), "审核", "201612161601_djr", "大金融");
		}
		return outParam;
	}
}