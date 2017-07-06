package com.creditharmony.fortune.redemption.apply.workflow;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.InitViewData;
import com.creditharmony.bpm.frame.face.base.BaseService;
import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.fortune.redemption.common.dao.RedemptionDao;
import com.creditharmony.fortune.redemption.common.view.RedemptionApplyEntity;
import com.creditharmony.fortune.redemption.utils.RedeemUtils;

/**
 * 描述：初始化流程发起（申请）页面
 * @Class Name RedemptionFlowInit
 * @author 陈广鹏
 * @Create In 2015年11月30日
 */
@Service("init_cf_redemptionFlow")
public class RedemptionFlowInit extends BaseService implements InitViewData {

	@Autowired
	private RedemptionDao reDao;

	/**
	 * 描述：初始化赎回申请页面 
	 * 2015年12月10日 
	 * By 陈广鹏
	 * @param map
	 * @return entity
	 */
	@Override
	public BaseBusinessView initViewData(@SuppressWarnings("rawtypes") Map map) {
		String[] lendCodes = (String[]) map.get("lendCode");
		String lendCode = "";

		if (lendCodes != null) {
			lendCode = lendCodes[0];
		}
		
		RedemptionApplyEntity entity = reDao.getRedemptionByLendCode(lendCode);
		boolean allowRedeemPart = RedeemUtils.allowRedeemPart(
				entity.getApplyLendMoneyd(), entity.getApplyLendDay(),
				entity.getApplyPay(), entity.getProductCode(),
				entity.getApplyAgreementEdition());
		if (allowRedeemPart) {
			entity.setRedeemPartFlag("1");
		} else {
			entity.setRedeemPartFlag("0");
		}
		return entity;
	}

}
