package com.creditharmony.fortune.deduct.approve.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.deduct.approve.service.DeductApproveManager;
import com.creditharmony.fortune.deduct.dao.DeductApplyDao;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.entity.ext.DeductPoolEx;

/**
 * 划扣业务Service
 * 
 * @author 韩龙
 * @Create In 2015年11月20日
 * @version 3.0
 */
@Service
public class DeductApproveFacade extends CoreManager<DeductApplyDao, DeductPool> {
	
	@Autowired
	private DeductApproveManager deductApproveManager;

	
	/**
	 * 批量操作
	 * 2016年4月19日
	 * By 韩龙
	 * @param deductPoolEx
	 * @param ids
	 * @param message
	 */
	public void batch(DeductPoolEx deductPoolEx, String ids,
			StringBuffer message) {
		if(ids != null && !"".equals(ids)){
			String[] applyCodes = ids.split(",");
			for (String applyCode : applyCodes) {
				update(message, applyCode);
			}
		}else{
			List<DeductPoolEx> dateList = deductApproveManager.findList(deductPoolEx);
			if(dateList != null && dateList.size() > 0){
				for (DeductPoolEx ex : dateList) {
					update(message, ex.getApplyCode());
				}
			}
		}
	}
	
	/**
	 * 更新状态
	 * 2016年4月19日
	 * By 韩龙
	 * @param message
	 * @param applyCode
	 */
	private void update(StringBuffer message, String applyCode) {
		DeductPool dp = new DeductPool();
		String applyCodevertime[] = applyCode.split("_");
		String lendCode = applyCodevertime[0];
		dp.setApplyCode(lendCode);
		dp.setVerTime(applyCodevertime[1]);
//		dp.setApplyCode(applyCode);
		dp.setDictDeductStatus(DeductState.DHKJS.value);
		try {
			// 更新状态为出借审批
			deductApproveManager.batchDeductApplyUpdate(dp);
		} catch (Exception e) {
			this.logger.error("批量操作出错,出错信息为:"+e.getMessage(), e);
			message.append("出借编号【"+lendCode+"】批量操作失败,失败信息为:"+e.getMessage())
			.append("</br>");
			FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, lendCode, 
					FortuneLogNode.DEDUCT_APPROVE.getCode(),FortuneLogLevel.YELLOW.value, 
					"出借编号【"+lendCode+"】批量操作失败"));
		}
	}
}
