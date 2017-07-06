package com.creditharmony.fortune.back.money.supplement.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.BackReason;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.PriorityBack;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.interest.common.service.BackReturnInterestCommonService;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyListDao;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyLogDao;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyPoolDao;
import com.creditharmony.fortune.back.money.common.dao.DetailDao;
import com.creditharmony.fortune.back.money.common.dao.InterestRateConfigDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyLog;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.back.money.utils.BmUtils;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.redemption.utils.RedeemUtils;
import com.creditharmony.fortune.template.entity.backmoney.ApplyConfirmExportModel;

@Service
public class BmSupplementManager extends CoreManager<BackMoneyListDao, ListItemView> {

	@Autowired
	private BackMoneyPoolDao poolDao;
	@Autowired
	private BackMoneyLogDao logDao;
	@Autowired
	private BackMoneyListDao bmDao;
	@Autowired
	private DetailDao detailDao;
	@Autowired
	private BmManager bmManager;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	private InterestRateConfigDao   InterestDao;
	@Autowired
	private BackReturnInterestCommonService  backReturnInterestCommonService;

	/**
	 * 回款补息
	 * 2016年11月8日
	 * By 陈广鹏
	 * @param view
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void supplement(ResultView view) {
		String userId = UserUtils.getUserId();
		Date date = new Date();
		
		// 1.更新回款信息
		BackMoneyPool pool = new BackMoneyPool();
		pool.setBackmId(view.getBackmId());
		pool.setVerTime(view.getVerTime());
		pool.setBackMoneyRemarks(view.getBackMoneyRemarks());
		pool.preUpdate();
		
		BackMoneyLog log = new BackMoneyLog();
		log.setCheckExaminetype(view.getCheckExaminetype());
		log.setBackmId(view.getBackmId());
		log.setCheckById(userId);
		log.setCheckTime(date);
		log.preInsert();
		
		if (YoN.SHI.value.equals(view.getCheckExaminetype())) {
			// 通过，设置回款状态，补息后回款金额、回款日期，债权转让日期
			pool.setDictBackStatus(BackState.DHKSP.value);
			pool.setDictBackMoneyError("");
			// 补息后回款金额
			if (!ObjectHelper.isEmpty(view.getSupplementedMoney())) {
				pool.setSupplementedMoney(view.getSupplementedMoney());
			}
			// 债权转让日期
			if (!ObjectHelper.isEmpty(view.getDebtTransferDate())) {
				Date debtTransferDate = view.getDebtTransferDate();
				pool.setDebtTransferDate(debtTransferDate);
				BackMoneyPool tmp = poolDao.getByBackmId(view.getBackmId());
				int days = BmUtils.getDays(tmp.getFinalLinitDate(), debtTransferDate);
				if (days>0) {
					// 补息天数大于0
					pool.setIsSupplemented(YoN.SHI.value);
					pool.setSupplementedDays(new BigDecimal(days));
				}
			}
			
			// 数据流转，交换金额字段
			BackMoneyPool p = detailDao.getBackMoneyPoolById(pool.getBackmId());
			if (!ObjectHelper.isEmpty(view.getSupplementedMoney())) {
				// 页面有传入，取页面值
				pool.setBackActualbackMoney(view.getSupplementedMoney());
			} else {
				pool.setBackActualbackMoney(p.getSupplementedMoney());
			}
			pool.setSupplementedMoney(p.getBackActualbackMoney());
			
			log.setDictBackmStatus(BackState.DHKSP.value);
		} else if (YoN.FOU.value.equals(view.getCheckExaminetype())) {
			// 不通过
			// 回款状态
			pool.setDictBackStatus(BackState.HKBXTH.value);
			// 退回原因
			pool.setDictBackMoneyError(view.getCheckExamine());
			
			log.setDictBackmStatus(BackState.DHKSQQRTH.value);
			log.setCheckExamine(view.getCheckExamine());
			log.setCheckReason(view.getCheckReason());
		} else {
			return;
		}
		int count = poolDao.update(pool);
		if (count==0) {
			BmUtils.throwDataOutOfDateException();
		}

		// 2.增加审批记录
		logDao.insert(log);
		
		// 3.全程留痕
		String lendCode = view.getLendCode();
		String str = BackState.backStateMap.get(pool.getDictBackStatus());
		if (YoN.FOU.value.equals(log.getCheckExaminetype())) {
			if (BackReason.QT.value.equals(log.getCheckExamine())) {
				str = str + "，原因：" + log.getCheckReason();
			}else {
				str = str + "，原因：" + BackReason.backReasonMap.get(log.getCheckExamine());
			}
		}
		String operatorInfo = "回款状态变更为[" + str + "]";
		String operatorType = "回款补息";
		String operateAffiliated = pool.getBackmId();
		FortuneLogNode fortuneLogNode = FortuneLogNode.MONEY_SUPPLEMENT;
		checkManager.addCheck(lendCode, operatorInfo, operatorType, operateAffiliated, fortuneLogNode);
	}

	/**
	 * 上传补息后回款金额，单条处理
	 * 2016年11月8日
	 * By 陈广鹏
	 * @param model
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void uploadSupplementedMoneySingle(ApplyConfirmExportModel model) {
		if (ObjectHelper.isEmpty(model.getLendCode())) {
			return;
		}
		BackMoneyPool bmp = poolDao.getByLendCode(model.getLendCode());
		if (bmp==null || !BmConstant.SUPPLEMENT_STATUS_LIST.contains(bmp.getDictBackStatus())) {
			// 【没有对应数据】或【数据不在回款补息列表】不操作更新
			throw new ServiceException("回款补息列表中没有对应数据");
		}
		BackMoneyPool pool = new BackMoneyPool();
		pool.setLendCode(model.getLendCode());
		
		pool.setSupplementedMoney(new BigDecimal(model.getSupplementedMoney()));
		pool.preUpdate();
		
		poolDao.updateByLendCode(pool);
		
		// 全程留痕
		String lendCode = model.getLendCode();
		String operatorInfo = "补息后回款金额由[" + bmp.getSupplementedMoney().setScale(2, RoundingMode.HALF_UP) 
				+ "]更新为[" + pool.getSupplementedMoney().setScale(2, RoundingMode.HALF_UP)+"]";
		String operatorType = "上传回款金额";
		String operateAffiliated = bmp.getBackmId();
		FortuneLogNode fortuneLogNode = FortuneLogNode.MONEY_SUPPLEMENT;
		checkManager.addCheck(lendCode, operatorInfo, operatorType, operateAffiliated, fortuneLogNode);
	}

	/**
	 * 计算补息后回款金额
	 * 2016年11月9日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public ResultView calculateSupplement(ResultView view) {
		ListItemView seachView = new ListItemView(); 
		seachView.setBackmId(view.getBackmId());
		List<ListItemView> dataList = bmManager.getSuplementList(seachView);
		
		ListItemView pool = dataList.get(0);
		Date finalLinitDate = pool.getFinalLinitDate();
		Date debtTransferDate = view.getDebtTransferDate();
		
		// 补息天数
		int subDays = BmUtils.getDays(finalLinitDate, debtTransferDate);
		subDays = subDays < 0 ? 0 : subDays;
		
		// 到期日所在月天数
		// 实际回款金额
		BigDecimal backActualbackMoney = BigDecimal.valueOf(pool.getBackActualbackMoney());
		// 补息后回款金额
		BigDecimal supplementMoney = BigDecimal.ZERO;
		//到期后回息总金额
		BigDecimal  arriveBackInterestMoney = BigDecimal.ZERO;
		//判断是否进行到期回息操作
		boolean  expireBackOperation = false;
		Map<String ,Object> map = new HashMap<String ,Object>();
		map.put("lendCode", pool.getLendCode());
		Map<String, Object> returnBackSumMoney = backReturnInterestCommonService.selectReturnBackSumMoney(map);
		if(!returnBackSumMoney.isEmpty()){
			//调用方法查询
			arriveBackInterestMoney = new BigDecimal(returnBackSumMoney.get("summoney")!=null?returnBackSumMoney.get("summoney").toString():"0");
		}
		if(arriveBackInterestMoney!=BigDecimal.ZERO && !arriveBackInterestMoney.equals(new BigDecimal("0") )){
			expireBackOperation = true;
		}
		
		//判断是否优先回款
		if(PriorityBack.FOU.value.equals(pool.getPriorityBack())){
			int yearDays = RedeemUtils.getYearDays(finalLinitDate);
			
			// 公式：补息后回款金额=实际回款金额*补息年化收益率/（到期日所在年天数）*补息天数+实际回款金额
			BigDecimal supplementRate = bmManager.getSupplementRate(pool);
			supplementMoney = backActualbackMoney.multiply(supplementRate)
					.divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP)
					.multiply(BigDecimal.valueOf(subDays))
					.divide(BigDecimal.valueOf(yearDays), 5, BigDecimal.ROUND_HALF_UP)
					.add(backActualbackMoney);
			if(expireBackOperation){
				supplementMoney=supplementMoney.subtract(arriveBackInterestMoney);
			}
		}else {
			//原月利率的回息总收益
			BigDecimal original = BigDecimal.ZERO;
			// 0.5%月利率的回息总收益
			BigDecimal backearnings05 = BigDecimal.ZERO;
			BigDecimal Nobackearnings05 = BigDecimal.ZERO;
			//需扣除金额
			BigDecimal deduct = BigDecimal.ZERO;

			//o.5利率到期后回息总金额
			BigDecimal  arriveBackInterestMoney05 = BigDecimal.ZERO;
			//非首期
			BigDecimal arriveBackInterestMoney05F = BigDecimal.ZERO;
			
			//到期后未回息总金额
			BigDecimal  NoArriveBackInterestMoney = BigDecimal.ZERO;
			//o.5利率到期后未回息总金额
			BigDecimal  NoArriveBackInterestMoney05 = BigDecimal.ZERO;
			//非首期o.5利率到期后未回息金额
			BigDecimal NoArriveBackInterestMoney05F = BigDecimal.ZERO;
			
			//到期后再回息需扣除金额
			BigDecimal expireBackDeduct = BigDecimal.ZERO;
			//到期后给客户回息的次数
			int	N = Integer.parseInt(returnBackSumMoney.get("countnum")!=null?returnBackSumMoney.get("countnum").toString():"0");
			
			//账单日
			int applyBillday = pool.getApplyBillday();
			//计算回息期数	债转日,到期日,账单日 来推算应回息期数
			int interestNum = BmUtils.interestNum(debtTransferDate, finalLinitDate, applyBillday);
			
			//最后一个账单日   transferDay  债转日,到期日,账单日 来推算最后一个账单日
			Date finallyDay = BmUtils.finallyDate(debtTransferDate, finalLinitDate, applyBillday);
			
			int M = interestNum-N;	//M为应给客户回息，但实际未操作回息的次数
			//获取纳入统计的第一个本期账单日
			Date  currentbillday = (Date) (returnBackSumMoney.get("mincurrentbillday")!=null?returnBackSumMoney.get("mincurrentbillday"):null);
			//判断是否为首期
			boolean ifFirst = BmUtils.ifFirst(currentbillday, finalLinitDate, applyBillday);
			
			//已到期第一次回息日期-到期日
			int arriveInterestDays = BmUtils.lendDay(pool.getFinalLinitDate(), pool.getApplyBillday())-1;
//			int arriveInterestDays = BmUtils.getSubtractDay(pool.getFinalLinitDate(), currentbillday);
			arriveInterestDays = arriveInterestDays < 0 ? 0 : arriveInterestDays;
			//到期日所在账单日周期天数
			int arriveApplyBillday = BmUtils.getCycleDays(pool.getFinalLinitDate(), pool.getApplyBillday());
			if(interestNum==N&&N!=0){
				//首期  到期后再回息
				arriveBackInterestMoney05 = BigDecimal.valueOf(pool.getBackMoney())
						.multiply(BigDecimal.valueOf(0.005))
						.multiply(BigDecimal.valueOf(arriveInterestDays))
						.divide(BigDecimal.valueOf(arriveApplyBillday),10, BigDecimal.ROUND_HALF_UP);
				
				arriveBackInterestMoney05F = BigDecimal.valueOf(pool.getBackMoney())
						.multiply(BigDecimal.valueOf(0.005))
						.multiply(BigDecimal.valueOf(N-1));
				arriveBackInterestMoney05=arriveBackInterestMoney05.add(arriveBackInterestMoney05F);
			}else{
				if(ifFirst){
					//首期  到期后再回息
					arriveBackInterestMoney05 = BigDecimal.valueOf(pool.getBackMoney())
							.multiply(BigDecimal.valueOf(0.005))
							.multiply(BigDecimal.valueOf(arriveInterestDays))
							.divide(BigDecimal.valueOf(arriveApplyBillday),10, BigDecimal.ROUND_HALF_UP);
					
					arriveBackInterestMoney05F = BigDecimal.valueOf(pool.getBackMoney())
							.multiply(BigDecimal.valueOf(0.005))
							.multiply(BigDecimal.valueOf(N-1));
					arriveBackInterestMoney05=arriveBackInterestMoney05.add(arriveBackInterestMoney05F);
				}else{
					arriveBackInterestMoney05F = BigDecimal.valueOf(pool.getBackMoney())
							.multiply(BigDecimal.valueOf(0.005))
							.multiply(BigDecimal.valueOf(N));
					arriveBackInterestMoney05=arriveBackInterestMoney05F;
				}
			}
			//到期后再回息需扣除金额
			expireBackDeduct = arriveBackInterestMoney.subtract(arriveBackInterestMoney05);
			
			
			//到期日所在月的天数
			int  monthnum = RedeemUtils.getMonthDays(pool.getFinalLinitDate());
			//待回息 ,待回息确认退回至待回息（回息失败） ,待回息确认 , 已回息 ,待回息退回,待回息确认退回,已回息退回 统计这几个节点的数据
			ResultView  resultview=InterestDao.countInterest(pool.getLendCode());
			if(null!=resultview.getSunInterestMoney()){
				original=resultview.getSunInterestMoney();
			}
			
			//账单日所在周期天数
			int  applyBilldayS = BmUtils.getCycleDays(pool.getApplyLendDay(), pool.getApplyBillday());
			
			//出借日
			int  lendDay=BmUtils.lendDay(pool.getApplyLendDay(), pool.getApplyBillday());
			
			if(ProductCode.YXT.value.equals(pool.getProductCode() )){
				/*
				 * 月息通
				 * ①原月利率的回息总收益=12期的回息金额
				 * ②0.5%月利率的回息总收益=按照0.5%月利率计算12期的回息金额
				 * ③需扣除金额=原月利率的回息总收益-0.5%月利率的回息总收益
				 * ④实际回款金额=本金*0.5%/到期日所在月的天数*（到期日-最后一个账单日的天数）+本金
				 * ⑤补息后回款金额=实际回款金额*0.5%/到期日所在月的天数*补息天数+实际回款金额-需扣除金额
				 */
				//首期
				backearnings05=BigDecimal.valueOf(pool.getApplyLendMoney())
						.multiply(BigDecimal.valueOf(0.005))
						.multiply(BigDecimal.valueOf(lendDay))
						.divide(BigDecimal.valueOf(applyBilldayS),10, BigDecimal.ROUND_HALF_UP);
				
				//非首期
				Nobackearnings05=	BigDecimal.valueOf(pool.getApplyLendMoney())
						.multiply(BigDecimal.valueOf(0.005))
						.multiply(BigDecimal.valueOf(11));
				
				backearnings05=backearnings05.add(Nobackearnings05);
				deduct=original.subtract(backearnings05);
				
				//true  进行到期回息操作
				if(expireBackOperation){
					//债权转让日-最后一个账单日
					subDays = BmUtils.getSubtractDay(finallyDay, debtTransferDate);
					subDays = subDays < 0 ? 0 : subDays;
					//补息金额
					supplementMoney=backActualbackMoney.multiply(BigDecimal.valueOf(0.005))
							.multiply(BigDecimal.valueOf(subDays))
							.divide(BigDecimal.valueOf(monthnum), 10, BigDecimal.ROUND_HALF_UP)
							.add(backActualbackMoney)
							.subtract(deduct)
							.subtract(expireBackDeduct);
					if(M>0){
						if(!ifFirst){
							//首期  到期未再回息
							NoArriveBackInterestMoney05 = BigDecimal.valueOf(pool.getBackMoney())
									.multiply(BigDecimal.valueOf(0.005))
									.multiply(BigDecimal.valueOf(arriveInterestDays))
									.divide(BigDecimal.valueOf(arriveApplyBillday),10, BigDecimal.ROUND_HALF_UP);
							
							NoArriveBackInterestMoney05F = BigDecimal.valueOf(pool.getBackMoney())
									.multiply(BigDecimal.valueOf(0.005))
									.multiply(BigDecimal.valueOf(M-1));
							NoArriveBackInterestMoney=NoArriveBackInterestMoney05.add(NoArriveBackInterestMoney05F);
						}else{
							NoArriveBackInterestMoney05F = BigDecimal.valueOf(pool.getBackMoney())
									.multiply(BigDecimal.valueOf(0.005))
									.multiply(BigDecimal.valueOf(M));
							NoArriveBackInterestMoney=NoArriveBackInterestMoney05F;
						}
					}
					supplementMoney = supplementMoney.add(NoArriveBackInterestMoney);
				}else{
					//补息金额
					supplementMoney=backActualbackMoney.multiply(BigDecimal.valueOf(0.005))
							.multiply(BigDecimal.valueOf(subDays))
							.divide(BigDecimal.valueOf(monthnum), 10, BigDecimal.ROUND_HALF_UP)
							.add(backActualbackMoney)
							.subtract(deduct);
				}
				
			}else if(ProductCode.XHYZ.value.equals(pool.getProductCode() )){
				/*
				 * 信和月增					
				 * ①原月利率的回息总收益=24期的回息金额
				 * ②0.5%月利率的回息总收益=按照0.5%月利率计算24期的回息金额
				 * ③需扣除金额=原月利率的回息总收益-0.5%月利率的回息总收益
				 * ④实际回款金额=本金*0.5%/到期日所在月的天数*（到期日-最后一个账单日的天数）+本金
				 * ⑤补息后回款金额=实际回款金额*0.5%/到期日所在月的天数*补息天数+实际回款金额-需扣除金额		*/
				//首期
				backearnings05=BigDecimal.valueOf(pool.getApplyLendMoney())
						.multiply(BigDecimal.valueOf(0.005))
						.divide(BigDecimal.valueOf(applyBilldayS))
						.multiply(BigDecimal.valueOf(lendDay));
				
				//非首期
				Nobackearnings05 =	BigDecimal.valueOf(pool.getApplyLendMoney())
						.multiply(BigDecimal.valueOf(0.005))
						.multiply(BigDecimal.valueOf(23));
				backearnings05=backearnings05.add(Nobackearnings05);
				
				//需扣除金额
				deduct=original.subtract(backearnings05);
				
				//true  进不行到期回息操作
				if(expireBackOperation){
					//债权转让日-最后一个账单日
					subDays = BmUtils.getSubtractDay(finallyDay, debtTransferDate);
					subDays = subDays < 0 ? 0 : subDays;
					//补息金额
					supplementMoney=backActualbackMoney.multiply(BigDecimal.valueOf(0.005))
							.multiply(BigDecimal.valueOf(subDays))
							.divide(BigDecimal.valueOf(monthnum), 10, BigDecimal.ROUND_HALF_UP)
							.add(backActualbackMoney)
							.subtract(deduct)
							.subtract(expireBackDeduct);
					if(M>0){
						if(!ifFirst){
							//首期  到期未再回息
							NoArriveBackInterestMoney05 = BigDecimal.valueOf(pool.getBackMoney())
									.multiply(BigDecimal.valueOf(0.005))
									.multiply(BigDecimal.valueOf(arriveInterestDays))
									.divide(BigDecimal.valueOf(arriveApplyBillday),10, BigDecimal.ROUND_HALF_UP);
							
							NoArriveBackInterestMoney05F = BigDecimal.valueOf(pool.getBackMoney())
									.multiply(BigDecimal.valueOf(0.005))
									.multiply(BigDecimal.valueOf(M-1));
							NoArriveBackInterestMoney=NoArriveBackInterestMoney05.add(NoArriveBackInterestMoney05F);
						}else{
							NoArriveBackInterestMoney05F = BigDecimal.valueOf(pool.getBackMoney())
									.multiply(BigDecimal.valueOf(0.005))
									.multiply(BigDecimal.valueOf(M));
							NoArriveBackInterestMoney=NoArriveBackInterestMoney05F;
						}
					}
					supplementMoney = supplementMoney.add(NoArriveBackInterestMoney);
				}else{
					//补息金额
					supplementMoney=backActualbackMoney.multiply(BigDecimal.valueOf(0.005))
							.multiply(BigDecimal.valueOf(subDays))
							.divide(BigDecimal.valueOf(monthnum), 10, BigDecimal.ROUND_HALF_UP)
							.add(backActualbackMoney)
							.subtract(deduct);
				}
			}else if(ProductCode.XHT.value.equals(pool.getProductCode()) && expireBackOperation){
				/*
				 * 信和通(进行到期后每月回息)
				 * ①实际回款金额=本金*0.5%*出借期限+本金
				 * 到期后回息总金额=应回款金额*月利率/到期日所在账单日周期天数*（已到期第一次回息日期-到期日）+（N-1）*应回款金额*月利率；（注：N为到期后给客户回息的次数；）
				 * 0.5%月利率到期后回息总金额=按照0.5%月利率计算N期的回息金额
				 * 到期后再回息需扣除金额=到期后回息总金额-0.5%月利率到期后回息总金额
				 * 补息后回款金额=实际回款金额*0.5%/到期日所在月的天数*（债权转让日-最后一个账单日）+实际回款金额-到期后再回息需扣除金额
				 */
				//债权转让日-最后一个账单日
				subDays = BmUtils.getSubtractDay(finallyDay, debtTransferDate);
				subDays = subDays < 0 ? 0 : subDays;
				//补息金额
				supplementMoney=backActualbackMoney.multiply(BigDecimal.valueOf(0.005))
						.multiply(BigDecimal.valueOf(subDays))
						.divide(BigDecimal.valueOf(monthnum), 10, BigDecimal.ROUND_HALF_UP)
						.add(backActualbackMoney)
						.subtract(expireBackDeduct);
				if(M>0){
					if(!ifFirst){
						//首期  到期未再回息
						NoArriveBackInterestMoney05 = BigDecimal.valueOf(pool.getBackMoney())
								.multiply(BigDecimal.valueOf(0.005))
								.multiply(BigDecimal.valueOf(arriveInterestDays))
								.divide(BigDecimal.valueOf(arriveApplyBillday),10, BigDecimal.ROUND_HALF_UP);
						
						NoArriveBackInterestMoney05F = BigDecimal.valueOf(pool.getBackMoney())
								.multiply(BigDecimal.valueOf(0.005))
								.multiply(BigDecimal.valueOf(M-1));
						NoArriveBackInterestMoney=NoArriveBackInterestMoney05.add(NoArriveBackInterestMoney05F);
					}else{
						NoArriveBackInterestMoney05F = BigDecimal.valueOf(pool.getBackMoney())
								.multiply(BigDecimal.valueOf(0.005))
								.multiply(BigDecimal.valueOf(M));
						NoArriveBackInterestMoney=NoArriveBackInterestMoney05F;
					}
				}
				supplementMoney = supplementMoney.add(NoArriveBackInterestMoney);
			}else{
				/*
				 * 非月息通  信和月增
				 * 实际回款金额=本金*0.5%*出借期限+本金
				 * 补息后回款金额=实际回款金额*0.5%/到期日所在月的天数*补息天数+实际回款金额
				 */
				//补息金额
				supplementMoney=backActualbackMoney.multiply(BigDecimal.valueOf(0.005))
						.multiply(BigDecimal.valueOf(subDays))
						.divide(BigDecimal.valueOf(monthnum), 10, BigDecimal.ROUND_HALF_UP)
						.add(backActualbackMoney);
			}
			
		}			

		ResultView result = new ResultView();
		result.setSupplementedMoney(supplementMoney);
		result.setSupplementDays(subDays);
		return result;
	}

	/**
	 * 修改债转日
	 * 2016年11月10日
	 * By 陈广鹏
	 * @param view
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void modifyDebtTransferDate(ListItemView view) {
		BackMoneyPool pool = new BackMoneyPool();
		pool.setBackmId(view.getBackmId());
		pool.setLendCode(view.getLendCode());
		pool.setVerTime(view.getVerTime());
		
		Date finalLinitDate = view.getFinalLinitDate(); //到期日
		Date debtTransferDate = view.getDebtTransferDate(); // 债转日
		// 补息天数
		int subDays = BmUtils.getDays(finalLinitDate, debtTransferDate);
		if (subDays <1 ) {
			return;
		}
		// 实际回款金额
		BigDecimal backActualbackMoney = BigDecimal.valueOf(view.getBackActualbackMoney());
		// 补息后回款金额
		BigDecimal supplementMoney = BigDecimal.ZERO;
		
		//到期后回息总金额
		BigDecimal  arriveBackInterestMoney = BigDecimal.ZERO;
		
		//判断是否进行到期回息操作
		boolean  expireBackOperation = false;
		Map<String ,Object> map = new HashMap<String ,Object>();
		map.put("lendCode", pool.getLendCode());
		Map<String, Object> returnBackSumMoney = backReturnInterestCommonService.selectReturnBackSumMoney(map);
		if(!returnBackSumMoney.isEmpty()){
			//调用方法查询
			arriveBackInterestMoney = new BigDecimal(returnBackSumMoney.get("summoney")!=null?returnBackSumMoney.get("summoney").toString():"0");
		}
		if(arriveBackInterestMoney!=BigDecimal.ZERO && !arriveBackInterestMoney.equals(new BigDecimal("0") )){
			expireBackOperation = true;
		}
		
		//判断是否优先回款
		if(PriorityBack.FOU.value.equals(view.getPriorityBack())){
		
			// 公式：补息后回款金额=实际回款金额*补息年化收益率/（到期日所在年天数）*补息天数+实际回款金额
			int yearDays = RedeemUtils.getYearDays(finalLinitDate);
			BigDecimal supplementRate = bmManager.getSupplementRate(view);
			supplementMoney = backActualbackMoney.multiply(supplementRate)
					.divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP)
					.multiply(BigDecimal.valueOf(subDays))
					.divide(BigDecimal.valueOf(yearDays), 5, BigDecimal.ROUND_HALF_UP)
					.add(backActualbackMoney);
			if(expireBackOperation){
				supplementMoney=supplementMoney.subtract(arriveBackInterestMoney);
			}
		}else{
			//原月利率的回息总收益
			BigDecimal original = BigDecimal.ZERO;
			// 0.5%月利率的回息总收益
			BigDecimal backearnings05 = BigDecimal.ZERO;
			BigDecimal Nobackearnings05 = BigDecimal.ZERO;
			//需扣除金额
			BigDecimal deduct = BigDecimal.ZERO;
			
			//o.5利率到期后回息总金额
			BigDecimal  arriveBackInterestMoney05 = BigDecimal.ZERO;
			//非首期
			BigDecimal arriveBackInterestMoney05F = BigDecimal.ZERO;
			
			//到期后未回息总金额
			BigDecimal  NoArriveBackInterestMoney = BigDecimal.ZERO;
			//o.5利率到期后未回息总金额
			BigDecimal  NoArriveBackInterestMoney05 = BigDecimal.ZERO;
			//非首期o.5利率到期后未回息金额
			BigDecimal NoArriveBackInterestMoney05F = BigDecimal.ZERO;
			
			//到期后再回息需扣除金额
			BigDecimal expireBackDeduct = BigDecimal.ZERO;
			//到期后给客户回息的次数
			int	N = Integer.parseInt(returnBackSumMoney.get("countnum")!=null?returnBackSumMoney.get("countnum").toString():"0");
			
			//账单日
			int applyBillday = view.getApplyBillday();
			//计算回息期数	债转日,到期日,账单日 来推算应回息期数
			int interestNum = BmUtils.interestNum(debtTransferDate, finalLinitDate, applyBillday);
			
			//最后一个账单日   transferDay  债转日,到期日,账单日 来推算最后一个账单日
			Date finallyDay = BmUtils.finallyDate(debtTransferDate, finalLinitDate, applyBillday);
			
			int M = interestNum-N;	//M为应给客户回息，但实际未操作回息的次数
			//获取纳入统计的第一个本期账单日
			Date  currentbillday = (Date) (returnBackSumMoney.get("mincurrentbillday")!=null?returnBackSumMoney.get("mincurrentbillday"):null);
			//判断是否为首期
			boolean ifFirst = BmUtils.ifFirst(currentbillday, finalLinitDate, applyBillday);
			//已到期第一次回息日期-到期日
			int arriveInterestDays = BmUtils.lendDay(view.getFinalLinitDate(), view.getApplyBillday())-1;
			//到期日所在账单日周期天数
			int arriveApplyBillday = BmUtils.getCycleDays(view.getFinalLinitDate(), view.getApplyBillday());
			
			if(interestNum==N&&N!=0){
				//首期  到期后再回息
				arriveBackInterestMoney05 = BigDecimal.valueOf(view.getBackMoney())
						.multiply(BigDecimal.valueOf(0.005))
						.multiply(BigDecimal.valueOf(arriveInterestDays))
						.divide(BigDecimal.valueOf(arriveApplyBillday),10, BigDecimal.ROUND_HALF_UP);
				
				arriveBackInterestMoney05F = BigDecimal.valueOf(view.getBackMoney())
						.multiply(BigDecimal.valueOf(0.005))
						.multiply(BigDecimal.valueOf(N-1));
				arriveBackInterestMoney05=arriveBackInterestMoney05.add(arriveBackInterestMoney05F);
			}else{
				if(ifFirst){
					//首期  到期后再回息
					arriveBackInterestMoney05 = BigDecimal.valueOf(view.getBackMoney())
							.multiply(BigDecimal.valueOf(0.005))
							.multiply(BigDecimal.valueOf(arriveInterestDays))
							.divide(BigDecimal.valueOf(arriveApplyBillday),10, BigDecimal.ROUND_HALF_UP);
					
					arriveBackInterestMoney05F = BigDecimal.valueOf(view.getBackMoney())
							.multiply(BigDecimal.valueOf(0.005))
							.multiply(BigDecimal.valueOf(N-1));
					arriveBackInterestMoney05=arriveBackInterestMoney05.add(arriveBackInterestMoney05F);
				}else{
					arriveBackInterestMoney05F = BigDecimal.valueOf(view.getBackMoney())
							.multiply(BigDecimal.valueOf(0.005))
							.multiply(BigDecimal.valueOf(N));
					arriveBackInterestMoney05=arriveBackInterestMoney05F;
				}
			}
	
			//到期后再回息需扣除金额
			expireBackDeduct = arriveBackInterestMoney.subtract(arriveBackInterestMoney05);
			//到期日所在月的天数
			int  monthnum = RedeemUtils.getMonthDays(view.getFinalLinitDate());
			//待回息 ,待回息确认退回至待回息（回息失败） ,待回息确认 , 已回息 获取这四个节点的数据
			ResultView  resultview=InterestDao.countInterest(view.getLendCode());
			if(null!=resultview.getSunInterestMoney()){
				original=resultview.getSunInterestMoney();
			}
			//账单日所在周期天数
			int  applyBilldayS = BmUtils.getCycleDays(view.getApplyLendDay(), view.getApplyBillday());
			
			//出借日
			int  lendDay=BmUtils.lendDay(view.getApplyLendDay(), view.getApplyBillday());
			
			if(ProductCode.YXT.value.equals(view.getProductCode() )){
				/*
				 * 月息通
				 * ①原月利率的回息总收益=12期的回息金额
				 * ②0.5%月利率的回息总收益=按照0.5%月利率计算12期的回息金额
				 * ③需扣除金额=原月利率的回息总收益-0.5%月利率的回息总收益
				 * ④实际回款金额=本金*0.5%/到期日所在月的天数*（到期日-最后一个账单日的天数）+本金
				 * ⑤补息后回款金额=实际回款金额*0.5%/到期日所在月的天数*补息天数+实际回款金额-需扣除金额
				 */
				//首期
				backearnings05=BigDecimal.valueOf(view.getApplyLendMoney())
						.multiply(BigDecimal.valueOf(0.005))
						.multiply(BigDecimal.valueOf(lendDay))
						.divide(BigDecimal.valueOf(applyBilldayS),10, BigDecimal.ROUND_HALF_UP);
				
				//非首期
				Nobackearnings05=	BigDecimal.valueOf(view.getApplyLendMoney())
						.multiply(BigDecimal.valueOf(0.005))
						.multiply(BigDecimal.valueOf(11));
				
				backearnings05=backearnings05.add(Nobackearnings05);
				deduct=original.subtract(backearnings05);
				
				//true  进行到期回息操作
				if(expireBackOperation){
					//债权转让日-最后一个账单日
					subDays = BmUtils.getSubtractDay(finallyDay, debtTransferDate);
					subDays = subDays < 0 ? 0 : subDays;
					//补息金额
					supplementMoney=backActualbackMoney.multiply(BigDecimal.valueOf(0.005))
							.multiply(BigDecimal.valueOf(subDays))
							.divide(BigDecimal.valueOf(monthnum), 10, BigDecimal.ROUND_HALF_UP)
							.add(backActualbackMoney)
							.subtract(deduct)
							.subtract(expireBackDeduct);
					if(M>0){
						if(ifFirst){
							//首期  到期后再回息
							NoArriveBackInterestMoney05 = BigDecimal.valueOf(view.getBackMoney())
									.multiply(BigDecimal.valueOf(0.005))
									.multiply(BigDecimal.valueOf(arriveInterestDays))
									.divide(BigDecimal.valueOf(arriveApplyBillday),10, BigDecimal.ROUND_HALF_UP);
							
							NoArriveBackInterestMoney05F = BigDecimal.valueOf(view.getBackMoney())
									.multiply(BigDecimal.valueOf(0.005))
									.multiply(BigDecimal.valueOf(M-1));
							NoArriveBackInterestMoney=NoArriveBackInterestMoney05.add(NoArriveBackInterestMoney05F);
						}else{
							NoArriveBackInterestMoney05F = BigDecimal.valueOf(view.getBackMoney())
									.multiply(BigDecimal.valueOf(0.005))
									.multiply(BigDecimal.valueOf(M));
							NoArriveBackInterestMoney=NoArriveBackInterestMoney05F;
						}
					}
					supplementMoney = supplementMoney.add(NoArriveBackInterestMoney);
				}else{
					//补息金额
					supplementMoney=backActualbackMoney.multiply(BigDecimal.valueOf(0.005))
							.multiply(BigDecimal.valueOf(subDays))
							.divide(BigDecimal.valueOf(monthnum), 10, BigDecimal.ROUND_HALF_UP)
							.add(backActualbackMoney)
							.subtract(deduct);
				}
				
			}else if(ProductCode.XHYZ.value.equals(view.getProductCode() )){
				/*
				 * 信和月增					
				 * ①原月利率的回息总收益=24期的回息金额
				 * ②0.5%月利率的回息总收益=按照0.5%月利率计算24期的回息金额
				 * ③需扣除金额=原月利率的回息总收益-0.5%月利率的回息总收益
				 * ④实际回款金额=本金*0.5%/到期日所在月的天数*（到期日-最后一个账单日的天数）+本金
				 * ⑤补息后回款金额=实际回款金额*0.5%/到期日所在月的天数*补息天数+实际回款金额-需扣除金额		*/
				//首期
				backearnings05=BigDecimal.valueOf(view.getApplyLendMoney())
						.multiply(BigDecimal.valueOf(0.005))
						.divide(BigDecimal.valueOf(applyBilldayS))
						.multiply(BigDecimal.valueOf(lendDay));
				
				//非首期
				Nobackearnings05 =	BigDecimal.valueOf(view.getApplyLendMoney())
						.multiply(BigDecimal.valueOf(0.005))
						.multiply(BigDecimal.valueOf(23));
				backearnings05=backearnings05.add(Nobackearnings05);
				
				//true  进不行到期回息操作
				if(expireBackOperation){
					//债权转让日-最后一个账单日
					subDays = BmUtils.getSubtractDay(finallyDay, debtTransferDate);
					subDays = subDays < 0 ? 0 : subDays;
					//补息金额
					supplementMoney=backActualbackMoney.multiply(BigDecimal.valueOf(0.005))
							.multiply(BigDecimal.valueOf(subDays))
							.divide(BigDecimal.valueOf(monthnum), 10, BigDecimal.ROUND_HALF_UP)
							.add(backActualbackMoney)
							.subtract(deduct)
							.subtract(expireBackDeduct);
					if(M>0){
						if(ifFirst){
							//首期  到期后再回息
							NoArriveBackInterestMoney05 = BigDecimal.valueOf(view.getBackMoney())
									.multiply(BigDecimal.valueOf(0.005))
									.multiply(BigDecimal.valueOf(arriveInterestDays))
									.divide(BigDecimal.valueOf(arriveApplyBillday),10, BigDecimal.ROUND_HALF_UP);
							
							NoArriveBackInterestMoney05F = BigDecimal.valueOf(view.getBackMoney())
									.multiply(BigDecimal.valueOf(0.005))
									.multiply(BigDecimal.valueOf(M-1));
							NoArriveBackInterestMoney=NoArriveBackInterestMoney05.add(NoArriveBackInterestMoney05F);
						}else{
							NoArriveBackInterestMoney05F = BigDecimal.valueOf(view.getBackMoney())
									.multiply(BigDecimal.valueOf(0.005))
									.multiply(BigDecimal.valueOf(M));
							NoArriveBackInterestMoney=NoArriveBackInterestMoney05F;
						}
					}
					supplementMoney = supplementMoney.add(NoArriveBackInterestMoney);
				}else{
					//补息金额
					supplementMoney=backActualbackMoney.multiply(BigDecimal.valueOf(0.005))
							.multiply(BigDecimal.valueOf(subDays))
							.divide(BigDecimal.valueOf(monthnum), 10, BigDecimal.ROUND_HALF_UP)
							.add(backActualbackMoney)
							.subtract(deduct);
				}
			}else if(ProductCode.XHT.value.equals(view.getProductCode()) && expireBackOperation){
				/*
				 * 信和通(进行到期后每月回息)
				 * ①实际回款金额=本金*0.5%*出借期限+本金
				 * 到期后回息总金额=应回款金额*月利率/到期日所在账单日周期天数*（已到期第一次回息日期-到期日）+（N-1）*应回款金额*月利率；（注：N为到期后给客户回息的次数；）
				 * 0.5%月利率到期后回息总金额=按照0.5%月利率计算N期的回息金额
				 * 到期后再回息需扣除金额=到期后回息总金额-0.5%月利率到期后回息总金额
				 * 补息后回款金额=实际回款金额*0.5%/到期日所在月的天数*（债权转让日-最后一个账单日）+实际回款金额-到期后再回息需扣除金额
				 */
				//债权转让日-最后一个账单日
				subDays = BmUtils.getSubtractDay(finallyDay, debtTransferDate);
				subDays = subDays < 0 ? 0 : subDays;
				//补息金额
				supplementMoney=backActualbackMoney.multiply(BigDecimal.valueOf(0.005))
						.multiply(BigDecimal.valueOf(subDays))
						.divide(BigDecimal.valueOf(monthnum), 10, BigDecimal.ROUND_HALF_UP)
						.add(backActualbackMoney)
						.subtract(expireBackDeduct);
				if(M>0){
					if(ifFirst){
						//首期  到期后再回息
						NoArriveBackInterestMoney05 = BigDecimal.valueOf(view.getBackMoney())
								.multiply(BigDecimal.valueOf(0.005))
								.multiply(BigDecimal.valueOf(arriveInterestDays))
								.divide(BigDecimal.valueOf(arriveApplyBillday),10, BigDecimal.ROUND_HALF_UP);
						
						NoArriveBackInterestMoney05F = BigDecimal.valueOf(view.getBackMoney())
								.multiply(BigDecimal.valueOf(0.005))
								.multiply(BigDecimal.valueOf(M-1));
						NoArriveBackInterestMoney=NoArriveBackInterestMoney05.add(NoArriveBackInterestMoney05F);
					}else{
						NoArriveBackInterestMoney05F = BigDecimal.valueOf(view.getBackMoney())
								.multiply(BigDecimal.valueOf(0.005))
								.multiply(BigDecimal.valueOf(M));
						NoArriveBackInterestMoney=NoArriveBackInterestMoney05F;
					}
				}
				supplementMoney = supplementMoney.add(NoArriveBackInterestMoney);
			}else{
				/*
				 * 非月息通  信和月增
				 * 实际回款金额=本金*0.5%*出借期限+本金
				 * 补息后回款金额=实际回款金额*0.5%/到期日所在月的天数*补息天数+实际回款金额
				 */
				//补息金额
				supplementMoney=backActualbackMoney.multiply(BigDecimal.valueOf(0.005))
						.multiply(BigDecimal.valueOf(subDays))
						.divide(BigDecimal.valueOf(monthnum), 10, BigDecimal.ROUND_HALF_UP)
						.add(backActualbackMoney);
			}
		}
		
		pool.setIsSupplemented(YoN.SHI.value);
		pool.setSupplementedMoney(supplementMoney);
		pool.setSupplementedDays(new BigDecimal(subDays));
		pool.setDebtTransferDate(debtTransferDate);
		
		int count = poolDao.updateByLendCode(pool);
		if (count<1) {
			BmUtils.throwDataOutOfDateException();
		}
		
		String amount = StringUtils.doNumFormat((supplementMoney), RedeemConstant.MONEY_FORMAT);
		String operatorInfo = "补息后回款金额变更为[" + amount + "]元";
		String operatorType = "批量回款补息";
		String operateAffiliated = pool.getBackmId();
		FortuneLogNode fortuneLogNode = FortuneLogNode.MONEY_SUPPLEMENT;
		checkManager.addCheck(view.getLendCode(), operatorInfo, operatorType, operateAffiliated, fortuneLogNode);
	}
	

}
