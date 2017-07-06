package com.creditharmony.fortune.redemption.push.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.RedeemType;
import com.creditharmony.core.mybatis.paginator.domain.Order;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyPoolDao;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.redemption.common.dao.RedemptionApplyDao;
import com.creditharmony.fortune.redemption.common.dao.RedemptionDao;
import com.creditharmony.fortune.redemption.common.dao.RedemptionHistoryDao;
import com.creditharmony.fortune.redemption.common.entity.RedemptionApply;
import com.creditharmony.fortune.redemption.common.entity.ext.Redemptionex;
import com.creditharmony.fortune.redemption.common.service.RedeemManager;
import com.creditharmony.fortune.redemption.common.view.RedemptionApplyEntity;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.redemption.constant.RedeemState;
import com.creditharmony.fortune.redemption.utils.RedeemUtils;

/**
 * 赎回推送Service
 * @Class Name PushManager
 * @author 陈广鹏
 * @Create In 2016年4月14日
 */
@Service
public class RedeemPushManager extends CoreManager<RedemptionDao, Redemptionex> {
	
	@Autowired
	private RedemptionDao reDao;
	@Autowired
	private RedemptionApplyDao applyDao;
	@Autowired
	private RedemptionHistoryDao rHisDao;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	private BackMoneyPoolDao backMoneyPoolDao;
	@Autowired
	private LoanApplyDao loanApplyDao;
	@Autowired
	private RedeemManager commonManager;
	
	/**
	 * 特殊提前赎回管理列表
	 * 2016年3月1日
	 * By 陈广鹏
	 * @param page
	 * @param redemptionex
	 * @return
	 */
	public Page<Redemptionex> findPushList(Page<Redemptionex> page,
			Redemptionex redemptionex) {
		String sortString = null;
		redemptionex.setRedemptionStatus(RedeemState.DTS.value); // 筛选待推送状态
		
		String city = redemptionex.getAddrCity();
		if (null != city && city.length()>0) {
			String c = "%" + city.replace(",", "%|%") +"%";
			redemptionex.setAddrCity(c);
		}
		
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize(), Order.formString(sortString ));
		pageBounds.setCountBy("redemption_id");
		PageList<Redemptionex> dataList = (PageList<Redemptionex>) reDao.findPushList(redemptionex, pageBounds);
		PageUtil.convertPage(dataList, page);
		redemptionex.setAddrCity(city);
		return page;
	}

	/**
	 * 加载推送详情页面
	 * 2016年3月2日
	 * By 陈广鹏
	 * @param lendCode
	 * @return
	 */
	public RedemptionApplyEntity loadPushDetail(String lendCode) {
		RedemptionApplyEntity entity = reDao.getRedemptionByLendCode(lendCode);
		return entity;
	}

	/**
	 * 特殊赎回推送
	 * 2016年3月2日
	 * By 陈广鹏
	 * @param entity
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void pushRedeem(RedemptionApplyEntity entity) {
		// 更新申请表回款日期、继续出借金额
		RedemptionApply apply = applyDao.getRedemptionApplyById(entity.getRedemptionId());
		
		if (RedeemType.BFSH.value.equals(apply.getRedemptionType())) {
			// 部分赎回时
			if (entity.getResidualAmount().compareTo(new BigDecimal(RedeemConstant.LOW_LIMIT)) == -1) {
				// 继续出借金额小于5万，不推送
				return;
			}
		}
		
		apply.setBackMoneyDay(entity.getBackMoneyDay());
		apply.setLinitDay(RedeemUtils.getPreviousWorkday(entity.getBackMoneyDay()));
		apply.setRedemptionStatus(RedeemState.YTG.value);
		apply.preUpdate();
		
//		LoanApply loanApply = new LoanApply();
//		loanApply.setApplyCode(apply.getLendCode());
//		loanApply = loanApplyDao.get(loanApply);
//		
//		// 更新到期日期
//		loanApply.setExpireDate(apply.getLinitDay());
//		loanApplyDao.update(loanApply);
		
//		entity.setApplyLendDay(loanApply.getLendDate());
//		entity.setLendCode(loanApply.getApplyCode());
//		entity.setLinitDay(RedeemUtils.getPreviousWorkday(entity.getBackMoneyDay()));
//		entity.setRedemptionReceType(apply.getRedemptionReceType());
//		entity.setRedemptionType(apply.getRedemptionType());
		
//		commonManager.calculateMoney(entity, apply);
//		// 计算后覆盖剩余金额
//		apply.setResidualAmount(entity.getResidualAmount());
//		entity = rHisDao.getRedemptionByLendCode(apply.getLendCode());
//		// 两个判断，顺序不可更改
//		if (YoN.SHI.value.equals(entity.getCheckSp())) {
//			apply.setRedemptionBmoney(entity.getCheckSpmoney());
//			apply.setRedemptionDemoney(BigDecimal.ZERO);
//		}
//		if (YoN.SHI.value.equals(apply.getFeedback())) {
//			apply.setRedemptionBmoney(apply.getRedemptionBmoney().subtract(apply.getFeedbackMoney()));
//		}
		applyDao.updateRedemptionApply(apply);
		
//		 3.在回款申请表更新一条数据,回款金额为应回金额
//		BackMoneyPool pool = backMoneyPoolDao.getByLendCode(apply.getLendCode());
//		if (null != pool) {
//			pool.setBackMoney(apply.getRedemptionBmoney());
//			pool.setBackActualbackMoney(apply.getRedemptionBmoney());
//			pool.setBackMoneyType(BackType.TQSH.value);
//			pool.setFinalLinitDate(apply.getLinitDay());
//			pool.setBackMoneyDay(apply.getBackMoneyDay());
//			pool.preUpdate();
//			backMoneyPoolDao.update(pool);
//		}
		
		// 记录全程留痕
		String lendCode = apply.getLendCode();
		String operatorInfo = "特殊赎回推送";
		String operatorType = "特殊赎回推送";
		checkManager.addCheck(lendCode, operatorInfo, operatorType, entity.getRedemptionId(), FortuneLogNode.REDEMPTION_PUSH);
	}
	
	/**
	 * 批量推送
	 * 2016年3月3日
	 * By 陈广鹏
	 * @param ids 赎回申请ids
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void multiPush(String ids){
		if (ObjectHelper.isEmpty(ids)){
			return;
		}
		Date date = new Date();
		List<String> idList = Arrays.asList(ids.split(","));
		for (String id : idList) {
			RedemptionApply apply = applyDao.getRedemptionApplyById(id);
			if (RedeemType.BFSH.value.equals(apply.getRedemptionType())
					&& apply.getResidualAmount().compareTo(new BigDecimal(RedeemConstant.LOW_LIMIT)) == -1) {
				// 部分赎回时，如果继续出借金额小于5万，不推送
				continue;
			}
			if (apply.getBackMoneyDay().after(date)) {
				// 如果回款日期大于当前日期，进行推送
				apply.setRedemptionStatus(RedeemState.YTG.value);
				apply.preUpdate();
				applyDao.updateRedemptionApply(apply);
				
				// 记录全程留痕
				String lendCode = apply.getLendCode();
				String operatorInfo = "特殊赎回批量推送";
				String operatorType = "特殊赎回推送";
				checkManager.addCheck(lendCode, operatorInfo, operatorType, id, FortuneLogNode.REDEMPTION_PUSH);
			}
		}
	}

}
