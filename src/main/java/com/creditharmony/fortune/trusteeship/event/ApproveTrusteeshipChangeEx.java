package com.creditharmony.fortune.trusteeship.event;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.core.fortune.type.LendchgType;
import com.creditharmony.fortune.change.lend.review.manager.LendChangeReviewManager;
import com.creditharmony.fortune.change.lender.approve.last.service.LenderChangeApproveLastManager;
import com.creditharmony.fortune.trusteeship.view.TrusteeshipChangeLoadView;

/**
 * 审批回调事件保存审批信息
 * @Class Name ApproveTrusteeshipChangeEx
 * @author 郭才林
 * @Create In 2015年12月2日
 */
@Service("ex_cf_trusteeshipChange_approve")
public class ApproveTrusteeshipChangeEx implements ExEvent{
    
	@Resource
	private LenderChangeApproveLastManager lastManager;
	@Resource
	private LendChangeReviewManager reviewManager;
	@Override
	public String getBeanName() {
		
		return null;
	}

	@Override
	public void invoke(WorkItemView workItem) {
		
		TrusteeshipChangeLoadView bv=(TrusteeshipChangeLoadView) workItem.getBv();
		String changerType=bv.getChangerType();
		if(LendchgType.TRUSTESSHIP_PHONE_CHA.value.equals(changerType)){
			// 金账户手机号变更修改信息更新
			lastManager.savePhoneChanger(workItem.getResponse(),bv);
		}else if(LendchgType.TRUSTESSHIP_CARD_CHA.value.equals(changerType)){
			// 金账户银行卡变更
			reviewManager.saveCardChange(workItem.getResponse(), bv);
			// 金账户销户
		}else if(LendchgType.TRUSTESSHIP_CANCELLATION.value.equals(changerType)){
			lastManager.updateTrusteeship(workItem.getResponse(),bv);
		}
		
		
	}

}
