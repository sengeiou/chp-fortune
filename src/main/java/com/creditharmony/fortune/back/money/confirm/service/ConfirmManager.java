package com.creditharmony.fortune.back.money.confirm.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BackResult;
import com.creditharmony.core.fortune.type.BackMoneyPlat;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.InvestState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyListDao;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyLogDao;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyPoolDao;
import com.creditharmony.fortune.back.money.common.dao.DetailDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyLog;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.back.money.utils.BmUtils;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.template.entity.backmoney.ExecuteTl1stImportModel;
import com.creditharmony.fortune.template.entity.backmoney.ExecuteZjImportModel;
import com.creditharmony.fortune.template.entity.backmoney.FyImportModel;

/**
 * 回款确认Service
 * @Class Name ConfirmManager
 * @author 陈广鹏
 * @Create In 2016年4月13日
 */
@Service
public class ConfirmManager extends CoreManager<BackMoneyListDao, ListItemView>{
	
	@Autowired
	private BackMoneyPoolDao poolDao;
	@Autowired
	private BackMoneyLogDao logDao;
	@Autowired
	private DetailDao detailDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private BmManager bmManager;
	@Autowired
	private CheckManager checkManager;

	
	/**
	 * 处理回款确认结果
	 * 2015年12月4日
	 * By 陈广鹏
	 * @param view
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void confirm(ResultView view) {
		String userId = UserUtils.getUserId();
		Date date = new Date();
		
		// 1.更新回款信息
		BackMoneyPool pool = new BackMoneyPool();
		pool.setBackmId(view.getBackmId());
		pool.setLendCode(view.getLendCode());
		pool.setVerTime(view.getVerTime());
		pool.setBackMoneyRemarks(view.getBackMoneyRemarks());
		pool.preUpdate();
		
		BackMoneyLog log = new BackMoneyLog();
		log.setBackmId(view.getBackmId());
		log.setCheckExaminetype(view.getCheckExaminetype());
		log.setCheckById(userId);
		log.setCheckTime(date);
		log.preInsert();
		
		if (YoN.SHI.value.equals(view.getCheckExaminetype())) {
			// 通过
			pool.setDictBackStatus(BackState.YHK.value);
			pool.setRebackFlag(YoN.FOU.value);
			pool.setDictBackMoneyError("");
			if (null != view.getBackDay()) {
				pool.setBackMoneyDay(view.getBackDay());
			}
			
			log.setDictBackmStatus(BackState.YHK.value);
			log.setCheckExamine("");
			log.setCheckReason("");
			
			// 更新出借申请表完结状态
			LoanApply loanApply = new LoanApply();
			loanApply.setApplyCode(view.getLendCode());
			loanApply.setApplyEndStatus(FinishState.YWJ.value);
			loanApply.preUpdate();
			
			detailDao.updateLoanApply(loanApply);
			
			// 更新客户出借状态
			LoanApply la = detailDao.getLoanApply(loanApply);
			la.setApplyEndStatus(FinishState.WWJ.value);
			int count = detailDao.countLendingApply(la);
			if (count<1) {
				// 如果未完成出借数量小于1，客户出借状态更新为已投
				Customer customer = new Customer();
				customer.setCustCode(la.getCustCode());
				customer.setCustLending(InvestState.YT.value);
				customer.preUpdate();
				customerDao.updateApplyLending(customer);
			}
		} else if (YoN.FOU.value.equals(view.getCheckExaminetype())) {
			// 不通过
			pool.setDictBackStatus(BackState.DHKQRTH.value);
			pool.setDictBackMoneyError(view.getCheckExamine());
			pool.setRebackFlag(YoN.SHI.value);
			pool.setSuccessMoney(BigDecimal.ZERO);
			pool.setFailMoney(BigDecimal.ZERO);
			
			BackMoneyPool p = detailDao.getBackMoneyPoolById(pool.getBackmId());
			if (YoN.SHI.value.equals(p.getIsSupplemented())) {
				// 补息数据流转，交换金额字段
				pool.setBackActualbackMoney(p.getSupplementedMoney());
				pool.setSupplementedMoney(p.getBackActualbackMoney());
			}
			
			log.setDictBackmStatus(BackState.DHKQRTH.value);
			log.setCheckExamine(view.getCheckExamine());
			log.setCheckReason(view.getCheckReason());
		} else {
			return;
		}
		
		if (!BackState.DHKQR.value.endsWith(poolDao.getStatus(pool.getBackmId()))) {
			// 不是待回款确认状态的数据，不允许进行回款确认操作
			return;
		}
		int count = poolDao.update(pool);
		if (count==0) {
			BmUtils.throwDataOutOfDateException();
		}
		
		// 2.增加审批记录
		logDao.insert(log);
		// 3.全程留痕
		bmManager.insertCheck(pool, log);
	}

	
	/**
	 * 待回款确认数据退回到待回款列表
	 * 2016年2月17日
	 * By 陈广鹏
	 * @param view
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void backtoExecute(ListItemView view) {
		// 更新回款池
		BackMoneyPool pool = new BackMoneyPool();
		pool.setBackmId(view.getBackmId());
		pool.setDictBackStatus(BackState.BFHKCG.value);
		pool.setRebackFlag(YoN.SHI.value);
		pool.setBackReason("");
		pool.setVerTime(view.getVerTime());
		pool.preUpdate();
		int count = poolDao.update(pool);
		if (count == 0) {
			BmUtils.throwDataOutOfDateException();
		}
		
		// 增加审批记录
		BackMoneyLog log = new BackMoneyLog();
		log.setBackmId(pool.getBackmId());
		log.setDictBackmStatus(BackState.BFHKCG.value);
		log.setCheckById(UserUtils.getUserId());
		log.setCheckTime(new Date());
		log.preInsert();
		logDao.insert(log);
		
		if (StringUtils.isBlank(view.getLendCode())) {
			BackMoneyPool bmp = poolDao.getByBackmId(pool.getBackmId());
			view.setLendCode(bmp.getLendCode());
		}
		
		// 增加留痕
		checkManager.addCheck(view.getLendCode(), "退回到待回款列表", "退回到待回款", pool.getBackmId(), FortuneLogNode.MONEY_CONFIRM);
	}

	/**
	 * 回款确认列表，富友平台上传回盘结果，单条处理更新数据库
	 * 2016年5月13日
	 * By 陈广鹏
	 * @param map
	 * @param lendCode
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void fy2ndUpload(Map<String, FyImportModel> map, String lendCode) {
		BackMoneyPool pool = new BackMoneyPool();
		BackMoneyPool bmp = poolDao.getByLendCode(lendCode);
		if (bmp==null || !BackState.DHKQR.value.equals(bmp.getDictBackStatus())) {
			// 【没有对应数据】或【数据不在待回款确认列表】不更新数据
			return;
		}
		FyImportModel model = map.get(lendCode);
		pool.setLendCode(lendCode);
		
		if (BackResult.SUCESS.value.equals(model.getFinalResult())) {
			pool.setBackResult(BackResult.SUCESS.value);
			pool.setBackReason("");
			pool.setSuccessMoney(bmp.getBackActualbackMoney());
			pool.setFailMoney(BigDecimal.ZERO);
		} else {
			pool.setBackResult(BackResult.FAIL.value);
			pool.setBackReason(model.getBackRemark());
			pool.setFailMoney(BigDecimal.valueOf(model.getAmount()));
		}
		pool.setBackDay(DateUtils.parseDate(model.getTradeTime()));
		pool.preUpdate();
		String backResult = poolDao.getBackResult(lendCode);
		if (pool.getBackResult().equals(backResult)) {
			// 状态相同，不更新
			return;
		}
		poolDao.updateByLendCode(pool);
		
		// 全程留痕
		String operatorInfo = "上传富友平台回盘结果，更新为[" + BackResult.backResultMap.get(backResult) +"]";
		String operatorType = "上传回盘结果";
		String operateAffiliated = bmp.getBackmId();
		FortuneLogNode fortuneLogNode = FortuneLogNode.MONEY_CONFIRM;
		checkManager.addCheck(lendCode, operatorInfo, operatorType, operateAffiliated, fortuneLogNode);
	}
	
	/**
	 * 回款确认列表，中金平台上传回盘结果，单条处理，更新数据库
	 * 2016年5月13日
	 * By 陈广鹏
	 * @param im
	 * @param lendCode
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void zj2ndUpload(ExecuteZjImportModel im, String lendCode) {
		BackMoneyPool pool = new BackMoneyPool();
		pool.setLendCode(lendCode);
		BackMoneyPool bmp = poolDao.getByLendCode(lendCode);
		if (bmp==null || !BackState.DHKQR.value.equals(bmp.getDictBackStatus())) {
			// 【没有对应数据】或【数据不在待回款确认列表】不更新数据
			return;
		}
		if (BmConstant.ZJ_SUCCESS.equals(im.getTradeStatus())) {
			pool.setBackResult(BackResult.SUCESS.value);
			pool.setBackReason("");
			pool.setSuccessMoney(bmp.getBackActualbackMoney());
			pool.setFailMoney(BigDecimal.ZERO);
		} else if (BmConstant.ZJ_DEALING.equals(im.getTradeStatus())
				|| BmConstant.ZJ_DEALING_2.equals(im.getTradeStatus())) {
			pool.setBackResult(BackResult.DELLING.value);
			pool.setBackReason("");
		} else if (BmConstant.ZJ_FAIL.equals(im.getTradeStatus())) {
			pool.setBackResult(BackResult.FAIL.value);
			pool.setBackReason(im.getBankResponseMsg());
			pool.setFailMoney(bmp.getBackActualbackMoney());
		} else {
			return;
		}
		pool.setBackDay(im.getBankPayTime());
		pool.setPlatformId(BackMoneyPlat.ZJPT.value);
		pool.preUpdate();
		String backResult = poolDao.getBackResult(lendCode);
		if (pool.getBackResult().equals(backResult)) {
			// 状态相同，不更新
			return;
		}
		poolDao.updateByLendCode(pool);
		
		// 全程留痕
		String operatorInfo = "上传中金平台回盘结果，更新为[" + BackResult.backResultMap.get(backResult) +"]";
		String operatorType = "上传回盘结果";
		String operateAffiliated = bmp.getBackmId();
		FortuneLogNode fortuneLogNode = FortuneLogNode.MONEY_CONFIRM;
		checkManager.addCheck(lendCode, operatorInfo, operatorType, operateAffiliated, fortuneLogNode);
		
	}

	/**
	 * 回款确认列表，通联平台上传回盘结果，单条处理，更新数据库
	 * 2016年5月13日
	 * By 陈广鹏
	 * @param map
	 * @param lendCode
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void tl2ndUpload(Map<String, ExecuteTl1stImportModel> map,
			String lendCode) {
		BackMoneyPool pool = new BackMoneyPool();
		BackMoneyPool bmp = poolDao.getByLendCode(lendCode);
		if (bmp==null || !BackState.DHKQR.value.equals(bmp.getDictBackStatus())) {
			// 【没有对应数据】或【数据不在待回款确认列表】不更新数据
			return;
		}
		ExecuteTl1stImportModel im = map.get(lendCode);
		pool.setLendCode(lendCode);
		pool.setBackResult(im.getFinalResult());
		
		if (BackResult.SUCESS.value.equals(im.getFinalResult())) {
			pool.setBackReason("");
			pool.setSuccessMoney(bmp.getBackActualbackMoney());
			pool.setFailMoney(BigDecimal.ZERO);
		} else if (BackResult.DELLING.value.equals(im.getFinalResult())) {
			pool.setBackReason("");
			pool.setSuccessMoney(null);
			pool.setFailMoney(null);
		} else {
			pool.setBackReason(im.getReason());
			pool.setFailMoney(BigDecimal.valueOf(im.getMoney()));
		}
		pool.setBackDay(im.getFinishDay());
		pool.preUpdate();
		String backResult = poolDao.getBackResult(lendCode);
		if (pool.getBackResult().equals(backResult)) {
			// 状态相同，不更新
			return;
		}
		poolDao.updateByLendCode(pool);
		
		// 全程留痕
		String operatorInfo = "上传通联平台回盘结果，更新为[" + BackResult.backResultMap.get(backResult) +"]";
		String operatorType = "上传回盘结果";
		String operateAffiliated = bmp.getBackmId();
		FortuneLogNode fortuneLogNode = FortuneLogNode.MONEY_CONFIRM;
		checkManager.addCheck(lendCode, operatorInfo, operatorType, operateAffiliated, fortuneLogNode);
	}
	
	/**
	 * 回款确认列表，回盘结果为【处理中】的数据不允许操作
	 * 2016年3月28日
	 * By 陈广鹏
	 * @param view
	 * @return 校验通过返回SUCCESS；不通过返回对应出借编号
	 */
	public String confirmListCheck(ListItemView view){
		String result = "SUCCESS";
		List<BackMoneyPool> dataList = bmManager.getDataList(view);
		for (BackMoneyPool bmp : dataList) {
			if (BackResult.DELLING.value.equals(bmp.getBackResult())) {
				result = "出借"+ bmp.getLendCode() + "正在回款处理中，不允许进行操作";
				break;
			}
		}
		if (ObjectHelper.isEmpty(dataList)) {
			result = "页面数据已过期，请刷新页面后再次操作";
		}
		
		return result;
	}

}
