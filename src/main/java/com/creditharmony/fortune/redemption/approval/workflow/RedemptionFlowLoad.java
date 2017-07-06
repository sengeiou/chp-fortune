package com.creditharmony.fortune.redemption.approval.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.BusinessLoadCallBack;
import com.creditharmony.bpm.frame.face.base.BaseService;
import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.fortune.redemption.common.dao.RedemptionDao;
import com.creditharmony.fortune.redemption.common.view.RedemptionApplyEntity;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.redemption.utils.RedeemUtils;

/**
 * 描述：初始化审批页面数据
 * @Class Name RedemptionFlowLoad
 * @author 陈广鹏
 * @Create In 2015年11月30日
 */
@Service("load_cf_redemptionFlow")
public class RedemptionFlowLoad extends BaseService implements
		BusinessLoadCallBack {
	
	@Autowired
	private RedemptionDao reDao;

	/**
	 * 描述：初始化审批页面数据 
	 * 2015年12月10日 
	 * By 陈广鹏
	 * @param applyId
	 * @param stepName
	 * @return entity
	 */
	@Override
	public BaseBusinessView load(String applyId, String stepName) {
		
		RedemptionApplyEntity entity = reDao.getRedemptionByApplyId(applyId);
		boolean allowRedeemPart = RedeemUtils.allowRedeemPart(
				entity.getApplyLendMoneyd(), entity.getApplyLendDay(),
				entity.getApplyPay(), entity.getProductCode(),
				entity.getApplyAgreementEdition());
		if (allowRedeemPart) {
			entity.setRedeemPartFlag("1");
		} else {
			entity.setRedeemPartFlag("0");
		}
		if (RedeemConstant.SPECIAL_FLOW_EDITION.contains(entity.getApplyAgreementEdition())) {
			// 特殊合同版本不显示如下字段
			entity.setRedemptionBMoney(null);
			entity.setRedemptionDeMoney(null);
			entity.setResidualAmount(null);
		}
		return entity;
	}

}
