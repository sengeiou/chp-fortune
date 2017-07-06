package com.creditharmony.fortune.back.money.applyconfirm.service;

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
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.BackType;
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
import com.creditharmony.fortune.back.money.common.dao.InterestRateConfigDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyLog;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.common.entity.InterestRateConfig;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.back.money.utils.BmUtils;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.redemption.utils.RedeemUtils;
import com.creditharmony.fortune.template.entity.backmoney.ApplyConfirmExportModel;

/**
 * 回款申请确认Service
 * @Class Name ApplyConfirmManager
 * @author 陈广鹏
 * @Create In 2016年4月13日
 */
@Service
public class ApplyConfirmManager extends CoreManager<BackMoneyListDao, ListItemView> {
	
	@Autowired
	private BackMoneyPoolDao poolDao;
	@Autowired
	private BackMoneyLogDao logDao;
	@Autowired
	private BmManager bmManager;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	private InterestRateConfigDao   InterestDao; 
	@Autowired
	private BackReturnInterestCommonService  backReturnInterestCommonService;
	
	/**
	 * 处理回款申请确认结果 
	 * 2015年12月3日 
	 * By 陈广鹏
	 * @param view
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void applyConfirm(ResultView view) {
		String userId = UserUtils.getUserId();
		Date date = new Date();
		
		// 1.更新回款信息
		BackMoneyPool pool = new BackMoneyPool();
		pool.setBackmId(view.getBackmId());
		pool.setVerTime(view.getVerTime());
		pool.setBackMoneyRemarks(view.getBackMoneyRemarks());
		

		ListItemView listItem = new ListItemView();
		listItem.setBackmId(view.getBackmId());
		List<ListItemView> suplementList = bmManager.getSuplementList(listItem);
		ListItemView listItemView = suplementList.get(0);
		
		Date finalLinitDate = listItemView.getFinalLinitDate();
		
		//回款日期需要重新推理
		Date backMoneyDay = view.getBackDay();
		// 获取债权转让日期
		Date transferDay = BmUtils.getDebtTransferDate(backMoneyDay);
		if (finalLinitDate.after(transferDay)) {
			transferDay = finalLinitDate;
		}
//		String format = new SimpleDateFormat("yyyy-MM-dd").format(transferDay);
		pool.setDebtTransferDate(transferDay);
		pool.preUpdate();
		
		BackMoneyLog log = new BackMoneyLog();
		log.setCheckExaminetype(view.getCheckExaminetype());
		log.setBackmId(view.getBackmId());
		log.setCheckById(userId);
		log.setCheckTime(date);
		log.preInsert();
		
		if (YoN.SHI.value.equals(view.getCheckExaminetype())){
			// 通过，设置回款状态，实际回款金额
			pool.setDictBackMoneyError("");
			if (ObjectHelper.isEmpty(view.getBackActualbackMoney())) {
				view.setBackActualbackMoney(BigDecimal.valueOf(listItemView.getBackActualbackMoney()));
			}
			pool.setBackActualbackMoney(view.getBackActualbackMoney());
			if (!ObjectHelper.isEmpty(view.getBackDay())) {
				pool.setBackMoneyDay(view.getBackDay());
			}
			if (YoN.SHI.value.equals(view.getIsSupplemented())) {
				// 选择补息
				pool.setDictBackStatus(BackState.HKBX.value);
				// 提前赎回的数据留在当前列表
				if(BackType.TQSH.value.equals(listItemView.getBackMoneyType())){
					return;
				}
				pool.setSupplementedMoney(view.getBackActualbackMoney());
				// 获取补息天数
				int interestDays = BmUtils.getDays(finalLinitDate, transferDay);
				// 回款日期是否在到期日的三个工作日内
				boolean in3Workdays = BmUtils.in3Workdays(finalLinitDate, backMoneyDay);
				
				// 实际回款金额
				BigDecimal actualbackMoney = view.getBackActualbackMoney();
				if (interestDays > 0 && !in3Workdays) {
					// 补息天数>0，且回款日期不在到期日期后三个工作日内
					BigDecimal supplementedMoney = BigDecimal.ZERO;
					//获取到期所在年的天数
					int yearDays = RedeemUtils.getYearDays(finalLinitDate);
					//到期后已回息总金额
					BigDecimal  arriveBackInterestMoney = BigDecimal.ZERO;
					//o.5利率到期后已回息总金额
					BigDecimal  arriveBackInterestMoney05 = BigDecimal.ZERO;
					//非首期o.5利率到期后已回息金额
					BigDecimal arriveBackInterestMoney05F = BigDecimal.ZERO;
					
					//到期后未回息总金额
					BigDecimal  NoArriveBackInterestMoney = BigDecimal.ZERO;
					//o.5利率到期后未回息总金额
					BigDecimal  NoArriveBackInterestMoney05 = BigDecimal.ZERO;
					//非首期o.5利率到期后未回息金额
					BigDecimal NoArriveBackInterestMoney05F = BigDecimal.ZERO;
					
					//判断是否进行到期回息操作
					boolean  expireBackOperation = false;
					Map<String ,Object> map = new HashMap<String ,Object>();
					map.put("lendCode", listItemView.getLendCode());
					Map<String, Object> returnBackSumMoney = backReturnInterestCommonService.selectReturnBackSumMoney(map);
					if(!returnBackSumMoney.isEmpty()){
						//调用方法查询
						arriveBackInterestMoney = new BigDecimal(returnBackSumMoney.get("summoney")!=null?returnBackSumMoney.get("summoney").toString():"0");
					}
					if(arriveBackInterestMoney!=BigDecimal.ZERO && !arriveBackInterestMoney.equals(new BigDecimal("0") )){
						expireBackOperation = true;
					}
					
					//判断是否优先回款
					if(PriorityBack.FOU.value.equals(listItemView.getPriorityBack())){
						
						// 补息后回款金额=实际回款金额*补息年化收益率/（到期日所在年天数）*补息天数+实际回款金额；
						Map<String,Object> hashMap = new  HashMap<String, Object>();
						hashMap.put("productCode",listItemView.getProductCode());
						hashMap.put("applyLendDay", listItemView.getApplyLendDay());
						hashMap.put("applyLendMoney", listItemView.getApplyLendMoney());
						hashMap.put("xinhebaoType", listItemView.getXinhebaoType());
						InterestRateConfig properRate = InterestDao.getProperRate(hashMap);
						BigDecimal profitRate = BigDecimal.ZERO;
						if (!ObjectHelper.isEmpty(properRate)) {
							profitRate = properRate.getProfitRate();
						}
						//补息金额
						supplementedMoney=actualbackMoney.multiply(profitRate)
								.divide(BigDecimal.valueOf(100))
								.multiply(BigDecimal.valueOf(interestDays))
								.divide(BigDecimal.valueOf(yearDays), 10, BigDecimal.ROUND_HALF_UP)
								.add(actualbackMoney);
						if(expireBackOperation){
							supplementedMoney=supplementedMoney.subtract(arriveBackInterestMoney);
						}
					}else if(PriorityBack.SHI.value.equals(listItemView.getPriorityBack())){
						//原月利率的回息总收益
						BigDecimal original = BigDecimal.ZERO;
						// 0.5%月利率的回息总收益
						BigDecimal backearnings05 = BigDecimal.ZERO;
						BigDecimal Nobackearnings05 = BigDecimal.ZERO;
						//需扣除金额
						BigDecimal deduct = BigDecimal.ZERO;
						
						//到期后再回息需扣除金额
						BigDecimal expireBackDeduct = BigDecimal.ZERO;
						/*//最后一个账单日比债转日大时扣除金额 以后优化
						BigDecimal lastdyBackInterestMoney05 = BigDecimal.ZERO;*/
						
						//账单日
						int applyBillday = listItemView.getApplyBillday();
						
						//计算回息期数	债转日,到期日,账单日 来推算应回息期数
						int interestNum = BmUtils.interestNum(transferDay, finalLinitDate, applyBillday);
						
						//最后一个账单日   transferDay  债转日,到期日,账单日 来推算最后一个账单日
						Date finallyDay = BmUtils.finallyDate(transferDay, finalLinitDate, applyBillday);
//						String format2 = new SimpleDateFormat("yyyy-MM-dd").format(finallyDay);
						//到期后给客户回息的次数
						int	N = Integer.parseInt(returnBackSumMoney.get("countnum")!=null?returnBackSumMoney.get("countnum").toString():"0");
						int M = interestNum-N;	//M为应给客户回息，但实际未操作回息的次数
						//获取纳入统计的第一个本期账单日
						Date  currentbillday = (Date) (returnBackSumMoney.get("mincurrentbillday")!=null?returnBackSumMoney.get("mincurrentbillday"):null);
						//获取纳入统计的最后一个本期账单日
						Date  maxCurrentbillday = (Date) (returnBackSumMoney.get("maxcurrentbillday")!=null?returnBackSumMoney.get("maxcurrentbillday"):null);
						//判断是否为首期
						boolean ifFirst = BmUtils.ifFirst(currentbillday, finalLinitDate, applyBillday);
						
						//已到期第一次回息日期-到期日
						int arriveInterestDays = BmUtils.lendDay(listItemView.getFinalLinitDate(), listItemView.getApplyBillday())-1;
//						int arriveInterestDays = BmUtils.getSubtractDay(listItemView.getFinalLinitDate(), currentbillday);
						arriveInterestDays = arriveInterestDays < 0 ? 0 : arriveInterestDays;
						//到期日所在账单日周期天数
						int arriveApplyBillday = BmUtils.getCycleDays(listItemView.getFinalLinitDate(), listItemView.getApplyBillday());
						
						if(interestNum==N&&N!=0){
							//首期  到期后再回息
							arriveBackInterestMoney05 = BigDecimal.valueOf(listItemView.getBackMoney())
									.multiply(BigDecimal.valueOf(0.005))
									.multiply(BigDecimal.valueOf(arriveInterestDays))
									.divide(BigDecimal.valueOf(arriveApplyBillday),10, BigDecimal.ROUND_HALF_UP);
							
							arriveBackInterestMoney05F = BigDecimal.valueOf(listItemView.getBackMoney())
									.multiply(BigDecimal.valueOf(0.005))
									.multiply(BigDecimal.valueOf(N-1));
							arriveBackInterestMoney05=arriveBackInterestMoney05.add(arriveBackInterestMoney05F);
						}else{
							if(ifFirst){
								//首期  到期后再回息
								arriveBackInterestMoney05 = BigDecimal.valueOf(listItemView.getBackMoney())
										.multiply(BigDecimal.valueOf(0.005))
										.multiply(BigDecimal.valueOf(arriveInterestDays))
										.divide(BigDecimal.valueOf(arriveApplyBillday),10, BigDecimal.ROUND_HALF_UP);
								
								arriveBackInterestMoney05F = BigDecimal.valueOf(listItemView.getBackMoney())
										.multiply(BigDecimal.valueOf(0.005))
										.multiply(BigDecimal.valueOf(N-1));
								arriveBackInterestMoney05=arriveBackInterestMoney05.add(arriveBackInterestMoney05F);
							}else{
								arriveBackInterestMoney05F = BigDecimal.valueOf(listItemView.getBackMoney())
										.multiply(BigDecimal.valueOf(0.005))
										.multiply(BigDecimal.valueOf(N));
								arriveBackInterestMoney05=arriveBackInterestMoney05F;
							}
						}
						//到期后再回息需扣除金额
						expireBackDeduct = arriveBackInterestMoney.subtract(arriveBackInterestMoney05);
						
						//到期日所在月的天数
						int  monthnum = RedeemUtils.getMonthDays(listItemView.getFinalLinitDate());

						//待回息 ,待回息确认退回至待回息（回息失败） ,待回息确认 , 已回息 ,待回息退回,待回息确认退回,已回息退回 统计这几个节点的数据
						ResultView  resultview=InterestDao.countInterest(view.getLendCode());
						if(null!=resultview.getSunInterestMoney()){
							original=resultview.getSunInterestMoney();
						}
						
						//账单日所在周期天数
						int  applyBilldays = BmUtils.getCycleDays(listItemView.getApplyLendDay(), listItemView.getApplyBillday());
						
						//出借日
						int  lendDay=BmUtils.lendDay(listItemView.getApplyLendDay(), listItemView.getApplyBillday());
						
						if(ProductCode.YXT.value.equals(listItemView.getProductCode() )){
							/*
							 * 月息通
							 * ①原月利率的回息总收益=12期的回息金额
							 * ②0.5%月利率的回息总收益=按照0.5%月利率计算12期的回息金额
							 * ③需扣除金额=原月利率的回息总收益-0.5%月利率的回息总收益
							 * ④实际回款金额=本金*0.5%/到期日所在月的天数*（到期日-最后一个账单日的天数）+本金
							 * ⑤补息后回款金额=实际回款金额*0.5%/到期日所在月的天数*补息天数+实际回款金额-需扣除金额
							 */
							//首期
							backearnings05 = BigDecimal.valueOf(listItemView.getApplyLendMoney())
									.multiply(BigDecimal.valueOf(0.005))
									.multiply(BigDecimal.valueOf(lendDay))
									.divide(BigDecimal.valueOf(applyBilldays),10, BigDecimal.ROUND_HALF_UP);
							
							//非首期
							Nobackearnings05 =	BigDecimal.valueOf(listItemView.getApplyLendMoney())
									.multiply(BigDecimal.valueOf(0.005))
									.multiply(BigDecimal.valueOf(11));
							
							backearnings05=backearnings05.add(Nobackearnings05);
							deduct=original.subtract(backearnings05);
							
							//true  进行到期回息操作
							if(expireBackOperation){
								//债权转让日-最后一个账单日
								interestDays = BmUtils.getSubtractDay(finallyDay ,transferDay);
								interestDays = interestDays < 0 ? 0 : interestDays;
								
								/*//最后一个账单日大于债转日 特殊处理  以后优化
								if(maxCurrentbillday.compareTo(transferDay)>0){
									interestDays = 0;
								}*/
								
								//补息金额
								supplementedMoney=actualbackMoney.multiply(BigDecimal.valueOf(0.005))
										.multiply(BigDecimal.valueOf(interestDays))
										.divide(BigDecimal.valueOf(monthnum), 10, BigDecimal.ROUND_HALF_UP)
										.add(actualbackMoney)
										.subtract(deduct)
										.subtract(expireBackDeduct);
								if(M>0){
									if(!ifFirst){
										//首期  到期未再回息
										NoArriveBackInterestMoney05 = BigDecimal.valueOf(listItemView.getBackMoney())
												.multiply(BigDecimal.valueOf(0.005))
												.multiply(BigDecimal.valueOf(arriveInterestDays))
												.divide(BigDecimal.valueOf(arriveApplyBillday),10, BigDecimal.ROUND_HALF_UP);
										
										NoArriveBackInterestMoney05F = BigDecimal.valueOf(listItemView.getBackMoney())
												.multiply(BigDecimal.valueOf(0.005))
												.multiply(BigDecimal.valueOf(M-1));
										NoArriveBackInterestMoney=NoArriveBackInterestMoney05.add(NoArriveBackInterestMoney05F);
									}else{
										NoArriveBackInterestMoney05F = BigDecimal.valueOf(listItemView.getBackMoney())
												.multiply(BigDecimal.valueOf(0.005))
												.multiply(BigDecimal.valueOf(M));
										NoArriveBackInterestMoney=NoArriveBackInterestMoney05F;
									}
								}
								supplementedMoney = supplementedMoney.add(NoArriveBackInterestMoney);
								
								/*//最后一个账单日大于债转日 特殊处理  以后优化 
								
								if(maxCurrentbillday.compareTo(transferDay)>0){
									//根据之后一个账单日推上一个账单日
									Date upBillDate=BmUtils.lastDay(maxCurrentbillday,applyBillday);
									//最后一个账单日和上一个账单日的周期天数
									int upLastDay = BmUtils.getSubtractDay(upBillDate, maxCurrentbillday);
									//最后一个账单日 减去债转日  以后优化
									int subtractDay = BmUtils.getSubtractDay(transferDay ,maxCurrentbillday);
									lastdyBackInterestMoney05 = BigDecimal.valueOf(listItemView.getBackMoney())
											.multiply(BigDecimal.valueOf(0.005))
											.multiply(BigDecimal.valueOf(subtractDay))
											.divide(BigDecimal.valueOf(upLastDay),10, BigDecimal.ROUND_HALF_UP);
									supplementedMoney = supplementedMoney.subtract(lastdyBackInterestMoney05);
								}
								
								*/
							}else{
								//补息金额
								supplementedMoney=actualbackMoney.multiply(BigDecimal.valueOf(0.005))
										.multiply(BigDecimal.valueOf(interestDays))
										.divide(BigDecimal.valueOf(monthnum), 10, BigDecimal.ROUND_HALF_UP)
										.add(actualbackMoney)
										.subtract(deduct);
							}
						}else if(ProductCode.XHYZ.value.equals(listItemView.getProductCode() )){
							/*
							 * 信和月增					
							 * ①原月利率的回息总收益=24期的回息金额
							 * ②0.5%月利率的回息总收益=按照0.5%月利率计算24期的回息金额
							 * ③需扣除金额=原月利率的回息总收益-0.5%月利率的回息总收益
							 * ④实际回款金额=本金*0.5%/到期日所在月的天数*（到期日-最后一个账单日的天数）+本金
							 * ⑤补息后回款金额=实际回款金额*0.5%/到期日所在月的天数*补息天数+实际回款金额-需扣除金额		*/
							//首期
							backearnings05=BigDecimal.valueOf(listItemView.getApplyLendMoney())
									.multiply(BigDecimal.valueOf(0.005))
									.divide(BigDecimal.valueOf(applyBilldays))
									.multiply(BigDecimal.valueOf(lendDay));
							
							//非首期
							Nobackearnings05 =	BigDecimal.valueOf(listItemView.getApplyLendMoney())
									.multiply(BigDecimal.valueOf(0.005))
									.multiply(BigDecimal.valueOf(23));
							backearnings05=backearnings05.add(Nobackearnings05);
							
							//需扣除金额
							deduct=original.subtract(backearnings05);
							
							//true  进不行到期回息操作
							if(expireBackOperation){
								//债权转让日-最后一个账单日
								interestDays = BmUtils.getSubtractDay(finallyDay ,transferDay);
								interestDays = interestDays < 0 ? 0 : interestDays;
								/*//最后一个账单日大于债转日 特殊处理  以后优化
								if(maxCurrentbillday.compareTo(transferDay)>0){
									interestDays = 0;
								}*/
								//补息金额
								supplementedMoney=actualbackMoney.multiply(BigDecimal.valueOf(0.005))
										.multiply(BigDecimal.valueOf(interestDays))
										.divide(BigDecimal.valueOf(monthnum), 10, BigDecimal.ROUND_HALF_UP)
										.add(actualbackMoney)
										.subtract(deduct)
										.subtract(expireBackDeduct);
								if(M>0){
									if(!ifFirst){
										//首期  到期未再回息
										NoArriveBackInterestMoney05 = BigDecimal.valueOf(listItemView.getBackMoney())
												.multiply(BigDecimal.valueOf(0.005))
												.multiply(BigDecimal.valueOf(arriveInterestDays))
												.divide(BigDecimal.valueOf(arriveApplyBillday),10, BigDecimal.ROUND_HALF_UP);
										
										NoArriveBackInterestMoney05F = BigDecimal.valueOf(listItemView.getBackMoney())
												.multiply(BigDecimal.valueOf(0.005))
												.multiply(BigDecimal.valueOf(M-1));
										NoArriveBackInterestMoney=NoArriveBackInterestMoney05.add(NoArriveBackInterestMoney05F);
									}else{
										NoArriveBackInterestMoney05F = BigDecimal.valueOf(listItemView.getBackMoney())
												.multiply(BigDecimal.valueOf(0.005))
												.multiply(BigDecimal.valueOf(M));
										NoArriveBackInterestMoney=NoArriveBackInterestMoney05F;
									}
								}
								supplementedMoney = supplementedMoney.add(NoArriveBackInterestMoney);
								/*//最后一个账单日大于债转日 特殊处理  以后优化 
								
								if(maxCurrentbillday.compareTo(transferDay)>0){
									//根据之后一个账单日推上一个账单日
									Date upBillDate=BmUtils.lastDay(maxCurrentbillday,applyBillday);
									//最后一个账单日和上一个账单日的周期天数
									int upLastDay = BmUtils.getSubtractDay(upBillDate, maxCurrentbillday);
									//最后一个账单日 减去债转日  以后优化
									int subtractDay = BmUtils.getSubtractDay(transferDay ,maxCurrentbillday);
									lastdyBackInterestMoney05 = BigDecimal.valueOf(listItemView.getBackMoney())
											.multiply(BigDecimal.valueOf(0.005))
											.multiply(BigDecimal.valueOf(subtractDay))
											.divide(BigDecimal.valueOf(upLastDay),10, BigDecimal.ROUND_HALF_UP);
									supplementedMoney = supplementedMoney.subtract(lastdyBackInterestMoney05);
								}
								
								*/
							}else{
								//补息金额
								supplementedMoney=actualbackMoney.multiply(BigDecimal.valueOf(0.005))
										.multiply(BigDecimal.valueOf(interestDays))
										.divide(BigDecimal.valueOf(monthnum), 10, BigDecimal.ROUND_HALF_UP)
										.add(actualbackMoney)
										.subtract(deduct);
							}
						}else if(ProductCode.XHT.value.equals(listItemView.getProductCode()) && expireBackOperation){
							/*
							 * 信和通(进行到期后每月回息)
							 * ①实际回款金额=本金*0.5%*出借期限+本金
							 * 到期后回息总金额=应回款金额*月利率/到期日所在账单日周期天数*（已到期第一次回息日期-到期日）+（N-1）*应回款金额*月利率；（注：N为到期后给客户回息的次数；）
							 * 0.5%月利率到期后回息总金额=按照0.5%月利率计算N期的回息金额
							 * 到期后再回息需扣除金额=到期后回息总金额-0.5%月利率到期后回息总金额
							 * 补息后回款金额=实际回款金额*0.5%/到期日所在月的天数*（债权转让日-最后一个账单日）+实际回款金额-到期后再回息需扣除金额
							 */
							//债权转让日-最后一个账单日
							interestDays = BmUtils.getSubtractDay(finallyDay ,transferDay);
							interestDays = interestDays < 0 ? 0 : interestDays;
							/*//最后一个账单日大于债转日 特殊处理  以后优化
							if(maxCurrentbillday.compareTo(transferDay)>0){
								interestDays = 0;
							}*/
							//补息金额
							supplementedMoney=actualbackMoney.multiply(BigDecimal.valueOf(0.005))
									.multiply(BigDecimal.valueOf(interestDays))
									.divide(BigDecimal.valueOf(monthnum), 10, BigDecimal.ROUND_HALF_UP)
									.add(actualbackMoney)
									.subtract(expireBackDeduct);
							if(M>0){
								if(!ifFirst){
									//首期  到期未再回息
									NoArriveBackInterestMoney05 = BigDecimal.valueOf(listItemView.getBackMoney())
											.multiply(BigDecimal.valueOf(0.005))
											.multiply(BigDecimal.valueOf(arriveInterestDays))
											.divide(BigDecimal.valueOf(arriveApplyBillday),10, BigDecimal.ROUND_HALF_UP);
									
									NoArriveBackInterestMoney05F = BigDecimal.valueOf(listItemView.getBackMoney())
											.multiply(BigDecimal.valueOf(0.005))
											.multiply(BigDecimal.valueOf(M-1));
									NoArriveBackInterestMoney=NoArriveBackInterestMoney05.add(NoArriveBackInterestMoney05F);
								}else{
									NoArriveBackInterestMoney05F = BigDecimal.valueOf(listItemView.getBackMoney())
											.multiply(BigDecimal.valueOf(0.005))
											.multiply(BigDecimal.valueOf(M));
									NoArriveBackInterestMoney=NoArriveBackInterestMoney05F;
								}
							}
							supplementedMoney = supplementedMoney.add(NoArriveBackInterestMoney);
							/*//最后一个账单日大于债转日 特殊处理  以后优化 
							
							if(maxCurrentbillday.compareTo(transferDay)>0){
								//根据之后一个账单日推上一个账单日
								Date upBillDate=BmUtils.lastDay(maxCurrentbillday,applyBillday);
								//最后一个账单日和上一个账单日的周期天数
								int upLastDay = BmUtils.getSubtractDay(upBillDate, maxCurrentbillday);
								//最后一个账单日 减去债转日  以后优化
								int subtractDay = BmUtils.getSubtractDay(transferDay ,maxCurrentbillday);
								lastdyBackInterestMoney05 = BigDecimal.valueOf(listItemView.getBackMoney())
										.multiply(BigDecimal.valueOf(0.005))
										.multiply(BigDecimal.valueOf(subtractDay))
										.divide(BigDecimal.valueOf(upLastDay),10, BigDecimal.ROUND_HALF_UP);
								supplementedMoney = supplementedMoney.subtract(lastdyBackInterestMoney05);
							}
							
							*/
						}else{
							/*
							 * 非月息通  信和月增 信和通
							 * 实际回款金额=本金*0.5%*出借期限+本金
							 * 补息后回款金额=实际回款金额*0.5%/到期日所在月的天数*补息天数+实际回款金额
							 */
							//补息金额
							supplementedMoney=actualbackMoney.multiply(BigDecimal.valueOf(0.005))
									.multiply(BigDecimal.valueOf(interestDays))
									.divide(BigDecimal.valueOf(monthnum), 10, BigDecimal.ROUND_HALF_UP)
									.add(actualbackMoney);
						}
					}		
					pool.setSupplementedMoney(supplementedMoney);
					pool.setIsSupplemented(YoN.SHI.value);
					pool.setSupplementedDays(new BigDecimal(interestDays));
				} else {
					pool.setSupplementedMoney(actualbackMoney);
					pool.setIsSupplemented(YoN.FOU.value);
					pool.setSupplementedDays(BigDecimal.ZERO);
				}
				log.setDictBackmStatus(BackState.HKBX.value);
			} else {
				// 选择不补息，数据正常流转
				pool.setDictBackStatus(BackState.DHKSP.value);
				pool.setBackActualbackMoney(view.getBackActualbackMoney());
				pool.setSupplementedMoney(view.getBackActualbackMoney());
				pool.setIsSupplemented(YoN.FOU.value);

				log.setDictBackmStatus(BackState.DHKSP.value);
			}
		} else if (YoN.FOU.value.equals(view.getCheckExaminetype())) {
			// 不通过
			// 回款状态
			pool.setDictBackStatus(BackState.DHKSQQRTH.value);
			// 退回原因
			pool.setDictBackMoneyError(view.getCheckExamine());
			
			log.setDictBackmStatus(BackState.DHKSQQRTH.value);
			log.setCheckExamine(view.getCheckExamine());
			log.setCheckReason(view.getCheckReason());
		} 
		int count = poolDao.update(pool);

		if (count==0) {
			BmUtils.throwDataOutOfDateException();
		}

		// 2.增加审批记录
		logDao.insert(log);
		
		// 3.全程留痕
		pool.setLendCode(view.getLendCode());
		bmManager.insertCheck(pool, log);
		
		// 处理完毕，必须重置view中实际回款金额字段
		view.setBackActualbackMoney(null);
	}
	
	/**
	 * 上传回款金额单条处理
	 * 2016年6月21日
	 * By 陈广鹏
	 * @param model
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void uploadBackMoneySingle(ApplyConfirmExportModel model) {
		if (ObjectHelper.isEmpty(model.getLendCode())) {
			return;
		}
		BackMoneyPool bmp = poolDao.getByLendCode(model.getLendCode());
		if (bmp==null || !BmConstant.APPLY_CONFIRM_STATUS_LIST.contains(bmp.getDictBackStatus())) {
			// 【没有对应数据】或【数据不再回款申请确认列表】不操作更新
			throw new ServiceException("回款申请确认列表中没有对应数据");
		}
		BackMoneyPool pool = new BackMoneyPool();
		pool.setLendCode(model.getLendCode());
		pool.setBackActualbackMoney(new BigDecimal(model.getBackActualbackMoney()));
		pool.preUpdate();
		
		poolDao.updateByLendCode(pool);
		
		// 全程留痕
		String lendCode = model.getLendCode();
		String operatorInfo = "实际回款金额由[" + bmp.getBackActualbackMoney().setScale(2, RoundingMode.HALF_UP) 
				+ "]更新为[" + pool.getBackActualbackMoney().setScale(2, RoundingMode.HALF_UP)+"]";
		String operatorType = "上传回款金额";
		String operateAffiliated = bmp.getBackmId();
		FortuneLogNode fortuneLogNode = FortuneLogNode.MONEY_APPLYCONFIRM;
		checkManager.addCheck(lendCode, operatorInfo, operatorType, operateAffiliated, fortuneLogNode);
	}
	
}
