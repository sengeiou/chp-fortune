package com.creditharmony.fortune.redemption.common.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.utils.SpringUtil;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.ListUtils;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.core.claim.util.CreditorUtils;
import com.creditharmony.core.common.type.EmailState;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.CreditRelease;
import com.creditharmony.core.fortune.type.CreditTradestate;
import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.MailTemplateType;
import com.creditharmony.core.fortune.type.MatchingStatus;
import com.creditharmony.core.fortune.type.Node;
import com.creditharmony.core.fortune.type.OperateType;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.fortune.type.RedeemCostType;
import com.creditharmony.core.fortune.type.RedeemType;
import com.creditharmony.core.fortune.type.ScrapState;
import com.creditharmony.core.fortune.type.TransferState;
import com.creditharmony.core.type.SettleStats;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.borrow.dao.BorrowDao;
import com.creditharmony.fortune.borrow.dao.CreditorHisDao;
import com.creditharmony.fortune.borrow.dao.LoanphaseDao;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.borrow.entity.CreditorHis;
import com.creditharmony.fortune.borrow.entity.CreditorTrade;
import com.creditharmony.fortune.borrow.entity.Loanphase;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.creditor.config.rate.entity.ProductMatchingRate;
import com.creditharmony.fortune.creditor.config.rate.service.ProductMatchingRateManager;
import com.creditharmony.fortune.creditor.matching.dao.CreditorSendDao;
import com.creditharmony.fortune.creditor.matching.dao.CreditorTradeDao;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDao;
import com.creditharmony.fortune.creditor.matching.entity.MatchingCreditor;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;
import com.creditharmony.fortune.creditor.matching.service.CreditorAidManager;
import com.creditharmony.fortune.customer.CustomerConstants;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.entity.Product;
import com.creditharmony.fortune.customer.workflow.dao.TransferRelationDao;
import com.creditharmony.fortune.customer.workflow.entity.TransferRelation;
import com.creditharmony.fortune.customer.workflow.util.BillDateUtil;
import com.creditharmony.fortune.customer.workflow.util.LendCodeGenerateUtil;
import com.creditharmony.fortune.lend.apply.manager.LendApplyManager;
import com.creditharmony.fortune.redemption.common.dao.RedemptionDao;
import com.creditharmony.fortune.redemption.common.entity.RedemptionApply;
import com.creditharmony.fortune.redemption.common.view.RedemptionApplyEntity;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.redemption.utils.RedeemUtils;
import com.creditharmony.fortune.triple.system.service.TripleInvestSuccService;

/**
 * 赎回共通Service
 * 
 * @Class Name CommonManager
 * @author 陈广鹏
 * @Create In 2016年4月14日
 */
@Service
public class RedeemManager {

	@Autowired
	private RedemptionDao dao;
	@Autowired
	private LoanApplyDao loanApplyDao;
	@Autowired
	private LoanphaseDao loanphaseDao;
	@Autowired
	private BorrowDao borrowDao;
	@Autowired
	private CreditorHisDao creditorHisDao;
	@Autowired
	private TransferRelationDao relationDao;
	@Autowired
	private MatchingCreditorDao matchingCreditorDao;
	@Autowired
	private CreditorSendDao creditorSendDao;
	@Autowired
	private CreditorTradeDao creditorTradeDao;
	@Autowired
	private FlowService flowService;
	@Autowired
	private CreditorAidManager creditorAidManager;
	@Autowired
	private ProductMatchingRateManager productMatchingRateManager;
	@Autowired
	private LendApplyManager lendApplyManager;
	
	@Autowired
	private TripleInvestSuccService tripleInvestSuccService ;
	
	/**日志对象*/
	protected Logger logger = LoggerFactory.getLogger(RedeemManager.class);
	
	/**
	 * 计算应回金额、应扣金额、剩余金额，按照产品别进行 <br/>
	 * 注：如果赎回到期日早于出借日期，赎回到期日将会更新为出借日期 </p>
	 * @author xurongsheng
	 * @date 2016年8月30日 上午10:08:41
	 * @param entity
	 * @param apply
	 */
	public void calculateMoneyNews(RedemptionApplyEntity entity,
			RedemptionApply apply){
		//出借申请信息
		LoanApply la = new LoanApply();
		la.setApplyCode(entity.getLendCode());
		LoanApply loan = loanApplyDao.get(la);
		
		//产品信息
		Product product = dao.getProductByCode(loan.getProductCode());
		
		//赎回到期日早于出借日期，则赎回到期日 更新为出借日期
		if (entity.getApplyLendDay().after(entity.getLinitDay())) {
			entity.setLinitDay(entity.getApplyLendDay());
			apply.setLinitDay(entity.getLinitDay());
		}
		
		// 计算利率
		BigDecimal rate = BigDecimal.ZERO;
		// 赎回本金到期日累计资产
		BigDecimal finishTotalMoney = BigDecimal.ZERO;
		// 赎回金额
		BigDecimal redemptionMoney = apply.getRedemptionMoney();
		redemptionMoney = redemptionMoney.setScale(5, RoundingMode.HALF_UP); // 保留两位再进行计算
		// 剩余本金
		BigDecimal residualAmount = BigDecimal.ZERO;
		if (RedeemType.BFSH.value.equals(entity.getRedemptionType())) {// 部分赎回
			residualAmount = loan.getLendMoney().subtract(redemptionMoney);
		}
		// 出借日所在年天数
		int yearDays = RedeemUtils.getYearDays(entity.getApplyLendDay());
		// 计算利息的天数(默认按不回息计算，对回息产品重新计算)
		int intrestingDays = RedeemUtils.getDays(entity.getApplyLendDay(), entity.getLinitDay()) + 1;
		// 账单日
		int billDay = loan.getBillDay();
		// 最近的前一个到期日
		Date lastBillDay = RedeemUtils.getLastBillDay(entity.getLinitDay(), billDay);
		// 到期日所在账单周期天数
		int cycleDays = RedeemUtils.getCycleDays(entity.getLinitDay(), billDay);

		//月息通、信和月增
		if(ProductCode.YXT.value.equals(product.getProductCode())
				|| ProductCode.XHYZ.value.equals(product.getProductCode())){ 
			//利率
			rate = getppRate(loan.getProductCode(), loan.getLendMoney(),
					DateUtils.formatDate(loan.getLendDate(), "yyyy-MM-dd"));
			//计息天数
			if (lastBillDay.after(entity.getApplyLendDay())) {
				intrestingDays = RedeemUtils.getDays(lastBillDay, entity.getLinitDay());
			} else {
				intrestingDays = RedeemUtils.getDays(entity.getApplyLendDay(), entity.getLinitDay()) + 1;
			}
			//赎回本金到期日累计资产 = 赎回本金*该笔出借提前赎回对应的年化收益率/到期日所在账单周期天数*计息天数+赎回本金
			finishTotalMoney = redemptionMoney.multiply(rate)
					.divide(BigDecimal.valueOf(cycleDays), 15, BigDecimal.ROUND_HALF_UP)
					.multiply(BigDecimal.valueOf(intrestingDays))
					.divide(BigDecimal.valueOf(100), 15, BigDecimal.ROUND_HALF_UP)
					.add(redemptionMoney);
			
		}else if(ProductCode.XHB.value.equals(product.getProductCode()) || 
				ProductCode.XHBA.value.equals(product.getProductCode()) || 
				ProductCode.XHBB.value.equals(product.getProductCode()) || 
				ProductCode.XHBC.value.equals(product.getProductCode())){//信和宝
			//已成功回息次数
			BackInterestPool backInterestPool = new BackInterestPool();
			backInterestPool.setLendCode(entity.getLendCode());
			backInterestPool.setCurrentBillday(entity.getLinitDay());
			int backCount = dao.getBackInterestCount(backInterestPool);
			//计息起始日期
			Date nextCycleDay = null;
			if(backCount == 0){
				nextCycleDay = entity.getApplyLendDay();
			}else{
				if(ProductCode.XHB.value.equals(product.getProductCode())){
					nextCycleDay = RedeemUtils.getNextCycleDay(entity.getApplyLendDay(), backCount, 12);
				}else if(ProductCode.XHBA.value.equals(product.getProductCode())){
					nextCycleDay = RedeemUtils.getNextCycleDay(entity.getApplyLendDay(), backCount, 12);
				}else if(ProductCode.XHBB.value.equals(product.getProductCode())){
					nextCycleDay = RedeemUtils.getNextCycleDay(entity.getApplyLendDay(), backCount, 24);
				}else if(ProductCode.XHBC.value.equals(product.getProductCode())){
					nextCycleDay = RedeemUtils.getNextCycleDay(entity.getApplyLendDay(), backCount, 6);
				}else{
					nextCycleDay = entity.getApplyLendDay();
				}
			}
			
			//利率
			rate = getRateByRealLendMonths(entity.getApplyLendDay(), entity.getLinitDay(),nextCycleDay);
			
			//出借日所在年天数
			yearDays = RedeemUtils.getYearDays(nextCycleDay);
			
			// 计息天数
			intrestingDays = RedeemUtils.getDays(nextCycleDay, entity.getLinitDay()) + 1;
			
			//赎回本金到期日累计资产 = 赎回本金*该笔出借提前赎回对应的年化收益率/出借日所在年的天数*计息天数+赎回本金
			finishTotalMoney = redemptionMoney.multiply(rate)
					.divide(BigDecimal.valueOf(yearDays), 15, BigDecimal.ROUND_HALF_UP)
					.multiply(BigDecimal.valueOf(intrestingDays))
					.divide(BigDecimal.valueOf(100), 15, BigDecimal.ROUND_HALF_UP)
					.add(redemptionMoney);
		}else{
			//利率
			rate = getRateByRealLendMonths(entity.getApplyLendDay(), entity.getLinitDay(),entity.getApplyLendDay());
			
			//赎回本金到期日累计资产 = 赎回本金*该笔出借提前赎回对应的年化收益率/出借日所在年的天数*计息天数/100+赎回本金
			finishTotalMoney = redemptionMoney.multiply(rate)
					.divide(BigDecimal.valueOf(yearDays), 15, BigDecimal.ROUND_HALF_UP)
					.multiply(BigDecimal.valueOf(intrestingDays))
					.divide(BigDecimal.valueOf(100), 15, BigDecimal.ROUND_HALF_UP)
					.add(redemptionMoney);
		}
		
		// 提前赎回的服务费 = 赎回本金到期日累计资产 * 服务费比例
		BigDecimal feeRate = RedeemCostType.parseByCode(entity.getRedemptionReceType()).getValue();
		finishTotalMoney = finishTotalMoney.setScale(5, RoundingMode.HALF_UP); // 保留两位后再进行计算
		BigDecimal redemptionDemoney = finishTotalMoney.multiply(feeRate);
		apply.setRedemptionDemoney(redemptionDemoney);
		
		// 应回金额 = 赎回本金到期日累计资产 - 提前赎回的服务费
		BigDecimal redemptionBmoney = finishTotalMoney.subtract(redemptionDemoney);
		apply.setRedemptionBmoney(redemptionBmoney);
		
		// 剩余金额
		if (RedeemType.BFSH.value.equals(entity.getRedemptionType())) {
			apply.setResidualAmount(residualAmount);
		}
		
	}
	
	/**
	 * 根据实际出借月数,获取年化收益率
	 * @author xurongsheng
	 * @date 2016年8月30日 下午2:36:40
	 * @param applyLendDay	出借日期
	 * @param linitDay	提前赎回日期
	 * @param nextCycleDay 计息开始时间
	 * @return
	 */
	private BigDecimal getRateByRealLendMonths(Date applyLendDay,Date linitDay,Date nextCycleDay){
		Date standardDate = DateUtil.parseYYYYMMDDDate(RedeemConstant.STANDARD_DATE);//降息标准时间
		boolean isAfter = false; //是否为降息后
		if(applyLendDay.compareTo(standardDate) >= 0){ //降息后
			isAfter = true;
		}
		int realLendMonths = RedeemUtils.getMonths(nextCycleDay,linitDay);
		//不满一月按一月算
		if(0 == realLendMonths){
			realLendMonths = 1;
		}
		if(isAfter){
			return new BigDecimal(RedeemConstant.AFT_RATES.get(realLendMonths));
		}else{
			return new BigDecimal(RedeemConstant.PRE_RATES.get(realLendMonths));
		}
	}
	
	/**
	 * 计算应回金额、应扣金额、剩余金额，按照产品别进行 <br/>
	 * 注：如果赎回到期日早于出借日期，赎回到期日将会更新为出借日期 </p>
	 * @author xurongsheng
	 * @date 2016年9月27日 上午9:53:28
	 * @param entity
	 * @param apply
	 */
	public void calculateMoney(RedemptionApplyEntity entity,
			RedemptionApply apply) {
		calculateMoneyNews(entity,apply);
	}
	

	/**
	 * 计算应回金额、应扣金额、剩余金额，按照产品别进行 <br/>
	 * 注：如果赎回到期日早于出借日期，赎回到期日将会更新为出借日期 </p>
	 * 2015年12月31日  <br/>
	 * By 陈广鹏
	 * @param entity
	 * @param apply
	 */
	public void calculateMoneyOld(RedemptionApplyEntity entity,
			RedemptionApply apply) {
		LoanApply la = new LoanApply();
		la.setApplyCode(entity.getLendCode());
		LoanApply loan = loanApplyDao.get(la);
		Product product = dao.getProductByCode(loan.getProductCode());
		
		if (entity.getApplyLendDay().after(entity.getLinitDay())) {
			entity.setLinitDay(entity.getApplyLendDay());
			apply.setLinitDay(entity.getLinitDay());
		}

		// 计算利率
		BigDecimal rate = BigDecimal.ZERO;
		// 赎回本金到期日累计资产
		BigDecimal finishTotalMoney = BigDecimal.ZERO;
		// 计算类型
		int type = 1;
		// 赎回金额
		BigDecimal redemptionMoney = apply.getRedemptionMoney();
		redemptionMoney = redemptionMoney.setScale(5, RoundingMode.HALF_UP); // 保留两位再进行计算
		// 剩余本金
		BigDecimal residualAmount = BigDecimal.ZERO;

		// 部分赎回
		if (RedeemType.BFSH.value.equals(entity.getRedemptionType())) {
			residualAmount = loan.getLendMoney().subtract(redemptionMoney);
		}

		int yearDays = RedeemUtils.getYearDays(entity.getApplyLendDay());
		// 计算利息的天数(默认按不回息计算，对回息产品重新计算)
		int intrestingDays = RedeemUtils.getDays(entity.getApplyLendDay(), entity.getLinitDay()) + 1;
		// 最近的前一个到期日
		int billDay = loan.getBillDay();
		Date lastBillDay = RedeemUtils.getLastBillDay(entity.getLinitDay(), billDay);
		// 到期日所在账单周期天数
		int cycleDays = RedeemUtils.getCycleDays(entity.getLinitDay(), billDay);

		// 根据合同版本产品获取赎回回款类型
		type = getRedeemCalculateType(loan, product);

		// 根据type获取回款金额
		if (1 == type) {
			rate = RedeemConstant.QXL_INTEREST_RATE;
			finishTotalMoney = redemptionMoney.multiply(rate)
					.divide(BigDecimal.valueOf(yearDays), 15, BigDecimal.ROUND_HALF_UP)
					.multiply(BigDecimal.valueOf(intrestingDays))
					.divide(BigDecimal.valueOf(100), 15, BigDecimal.ROUND_HALF_UP)
					.add(redemptionMoney);
		} else if (2 == type || 5 == type) {
			// 按年利率计算
			rate = lendApplyManager.getRate(loan);
			// 如果需要回息
			BackInterestPool backInterestPool = new BackInterestPool();
			backInterestPool.setLendCode(entity.getLendCode());
			backInterestPool.setCurrentBillday(entity.getLinitDay());

			// 最后一笔回息的本期到期日
			Date lastBIDay = dao.getLastBackInterestDay(backInterestPool);
			// 计息天数
			if (ObjectHelper.isEmpty(lastBIDay)) {
				// 如果没有对应出借的回息数据，按出借日期计算。 根据出借日计算计息日时，需 +1 天
				intrestingDays = RedeemUtils.getDays(loan.getLendDate(), entity.getLinitDay()) +1;
			} else {
				intrestingDays = RedeemUtils.getDays(lastBIDay, entity.getLinitDay());
			}
			finishTotalMoney = redemptionMoney.multiply(rate)
					.divide(BigDecimal.valueOf(yearDays), 15, BigDecimal.ROUND_HALF_UP)
					.multiply(BigDecimal.valueOf(intrestingDays))
					.divide(BigDecimal.valueOf(100), 15, BigDecimal.ROUND_HALF_UP)
					.add(redemptionMoney);
		} else if (3 == type) {
			// 月息通、信和月增计算
			// 本期债权匹配信息
//			MatchingCreditor mCreditor = getCurrentMatchingCreditor(entity);

			// 利率按债权匹配月利率计算
//			String matchingInterestStart = "";
//			Integer matchingBillDay = 1;
//			if (ObjectHelper.isNotEmpty(mCreditor)) {
//				DateUtils.formatDate(mCreditor.getMatchingInterestStart(), "yyyy-MM-dd");
//				mCreditor.getMatchingBillDay();
//			}
			rate = getppRate(loan.getProductCode(), loan.getLendMoney(),
					DateUtils.formatDate(loan.getLendDate(), "yyyy-MM-dd"));
			// 计息天数
			if (lastBillDay.after(entity.getApplyLendDay())) {
				intrestingDays = RedeemUtils.getDays(lastBillDay, entity.getLinitDay());
			} else {
				intrestingDays = RedeemUtils.getDays(entity.getApplyLendDay(), entity.getLinitDay()) + 1;
			}

			finishTotalMoney = redemptionMoney.multiply(rate)
					.divide(BigDecimal.valueOf(cycleDays), 15, BigDecimal.ROUND_HALF_UP)
					.multiply(BigDecimal.valueOf(intrestingDays))
					.divide(BigDecimal.valueOf(100), 15, BigDecimal.ROUND_HALF_UP)
					.add(redemptionMoney);
			if (RedeemType.BFSH.value.equals(entity.getRedemptionType())) {
				residualAmount = residualAmount.multiply(rate)
						.divide(BigDecimal.valueOf(cycleDays), 15, BigDecimal.ROUND_HALF_UP)
						.multiply(BigDecimal.valueOf(intrestingDays))
						.divide(BigDecimal.valueOf(100), 15, BigDecimal.ROUND_HALF_UP)
						.add(residualAmount);
			}
		} else if (4 == type) {
			// 2016-01-05前的产品
			// 本期债权匹配信息
			// MatchingCreditor mCreditor = getCurrentMatchingCreditor(entity);

			// 利率按债权匹配月利率计算
			rate = getppRate(loan.getProductCode(), loan.getLendMoney(),
					DateUtils.formatDate(loan.getLendDate(), "yyyy-MM-dd"));
			// 信和通计算(月复利)，按累计资产总额计算
			int months = RedeemUtils.getMonths(loan.getLendDate(), entity.getLinitDay());
			if (lastBillDay.after(entity.getApplyLendDay())) {
				intrestingDays = RedeemUtils.getDays(lastBillDay, entity.getLinitDay());
			} else {
				intrestingDays = RedeemUtils.getDays(entity.getApplyLendDay(), entity.getLinitDay()) + 1;
			}
			// 最后一期账单日资产总额
			BigDecimal ljMoney = loan.getLendMoney();
			if (months > 0) {
				BigDecimal totalInterest = getTotalInterest(entity, lastBillDay);
				ljMoney = ljMoney.add(totalInterest);
			}
			// 赎回本金到期日前报告日累计资产总额
			BigDecimal redeemTotal = ljMoney.multiply(redemptionMoney)
					.divide(loan.getLendMoney(), 15, BigDecimal.ROUND_HALF_UP);
			// 赎回本金到期日累计资产总额
			finishTotalMoney = redeemTotal .multiply(rate)
					.divide(BigDecimal.valueOf(cycleDays), 15, BigDecimal.ROUND_HALF_UP)
					.multiply(BigDecimal.valueOf(intrestingDays))
					.divide(BigDecimal.valueOf(100), 15, BigDecimal.ROUND_HALF_UP)
					.add(redeemTotal);
			if (RedeemType.BFSH.value.equals(entity.getRedemptionType())) {
				// 剩余本金最后一期累计资产总额
				BigDecimal residualTotal = ljMoney.multiply(residualAmount)
						.divide(loan.getLendMoney(), 15, BigDecimal.ROUND_HALF_UP);
				residualAmount = residualTotal .multiply(rate)
						.divide(BigDecimal.valueOf(cycleDays), 15, BigDecimal.ROUND_HALF_UP)
						.multiply(BigDecimal.valueOf(intrestingDays))
						.divide(BigDecimal.valueOf(100), 15, BigDecimal.ROUND_HALF_UP)
						.add(residualTotal);
			}
		} else {
			FortuneException forException = new FortuneException();
			forException.setLoanCode(loan.getApplyCode());
			forException.setMessage("type = " + type + "，没有对应的赎回计算公式");
			forException.setStackTrace(forException.getMessage());
			forException.setNode(FortuneLogNode.REDEMPTION_APPROVAL.getCode());
			forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
			forException.setRemark("提前赎回回款金额计算");
			
			forException.preInsert();
			FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
			forDao.insert(forException);
			throw new ServiceException(forException.getMessage());
		}

		// 提前赎回的服务费 = 赎回本金到期日累计资产 * 服务费比例
		BigDecimal feeRate = RedeemCostType.parseByCode(entity.getRedemptionReceType()).getValue();
		finishTotalMoney = finishTotalMoney.setScale(5, RoundingMode.HALF_UP); // 保留两位后再进行计算
		BigDecimal redemptionDemoney = finishTotalMoney.multiply(feeRate);
		// 应回金额 = 赎回本金到期日累计资产 - 提前赎回的服务费
		BigDecimal redemptionBmoney = finishTotalMoney.subtract(redemptionDemoney);
		apply.setRedemptionBmoney(redemptionBmoney);
		apply.setRedemptionDemoney(redemptionDemoney);
		// 剩余金额
		if (RedeemType.BFSH.value.equals(entity.getRedemptionType())) {
			apply.setResidualAmount(residualAmount);
		}
	}

	/**
	 * 根据合同版本、产品获取赎回回款计算类型
	 * 2016年4月21日
	 * By 陈广鹏
	 * @param loan
	 * @param product
	 * @return
	 */
	private int getRedeemCalculateType(LoanApply loan, Product product) {
		int type = 1;
		// 降息时间：2016-01-05
		Date devideDate = DateUtils.parseDate(RedeemConstant.DEVIDE_DATE);
		if (RedeemConstant.FIRST_KIND_EDITION.contains(loan.getProtocoEdition())
				&& RedeemConstant.FIRST_KIND_PRODUCTS.contains(product.getProductCode())) {
			// 期限类特定合同版本，统一按年利率5.8%计算
			type = 1;
		} else if (RedeemConstant.SECOND_KIND_EDITION.contains(loan.getProtocoEdition())
				&& RedeemConstant.SECOND_KIND_PRODUCTS.contains(product.getProductCode())) {
			type = 2;
		} else if (ProductCode.YXT.value.equals(product.getProductCode())
				|| ProductCode.XHYZ.value.equals(product.getProductCode())) {
			// 所有版本的月息通、信和月增
			type = 3;
		} else if (ProductCode.XHT.value.equals(product.getProductCode())) {
			// 信和通
			if (devideDate.after(loan.getLendDate())) {
				// 2016-01-05前的产品
				type = 4;
			} else {
				// 2016-01-05后的产品
				type = 5;
			}
		} else {
			FortuneException forException = new FortuneException();
			forException.setLoanCode(loan.getApplyCode());
			forException.setMessage("根据出借产品和合同版本，没有找到对应的赎回计算公式");
			forException.setStackTrace(forException.getMessage());
			forException.setNode(FortuneLogNode.REDEMPTION_APPROVAL.getCode());
			forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
			forException.setRemark("提前赎回回款金额计算");
			
			forException.preInsert();
			FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
			forDao.insert(forException);
			throw new ServiceException("根据出借产品和合同版本，没有找到对应的赎回计算公式");
		}
		return type;
	}

	/**
	 * 部分赎回，生成新出借 
	 * 2016年3月3日 
	 * By 陈广鹏
	 * @param entity
	 * @param apply
	 */
	public void createNewLoanApply(RedemptionApplyEntity entity,
			RedemptionApply apply) {
		LoanApply loanApply = new LoanApply();
		loanApply.setApplyCode(entity.getLendCode());

		loanApply = loanApplyDao.get(loanApply);
		// 出借金额为剩余金额，信和通、月息通需考虑利息积累
		loanApply.setLendMoney(apply.getResidualAmount()); // 审批页面修改剩余金额，新出借按输入的金额进行出借
		// 内转出借编号为旧出借的出借编号
		loanApply.setTransferLendId(loanApply.getApplyCode());
		// 内转金额等于新出借的出借金额
		loanApply.setTransferMoney(loanApply.getLendMoney());
		// 生成新的出借编号
		String newLendCode = LendCodeGenerateUtil.generate(
				loanApply.getCustCode(), loanApply.getProductCode());
		loanApply.setApplyCode(newLendCode);
		// 根据赎回到期日期的下一自然日，计算账单日
		Integer newBillDate = BillDateUtil.getBillDate(BillDateUtil
				.getNextDate(entity.getLinitDay()));
		loanApply.setBillDay(newBillDate);
		// 付款方式为赎回内转
		loanApply.setPayType(PayMent.SHNZ.value);
		// 划扣金额为剩余金额
		loanApply.setDeductMoney(apply.getResidualAmount());
		// 出借状态为审批通过
		loanApply.setLendStatus(LendState.SPTG.value);
		// 完结状态为未完结
		loanApply.setApplyEndStatus(FinishState.WWJ.value);
		// 状态为null
		loanApply.setStatus(null);
		loanApply.preInsert();

		loanApplyDao.insert(loanApply);

		// 8.内转关系表增加一条数据
		TransferRelation tr = new TransferRelation();
		tr.setLendCodeParent(entity.getLendCode());
		tr.setLendCodeChilde(newLendCode);
		tr.setBackMoney(apply.getRedemptionBmoney());
		tr.setTransferMoney(loanApply.getLendMoney());
		tr.setBackMoneyType(BackType.SHNZ.value);
		tr.setTransferState(TransferState.YSH.getKey());
		tr.preInsert();
		relationDao.insert(tr);

		// 9.待债权推荐信息表增加一条数据
		Date newLendday = BillDateUtil.getNextDate(entity.getLinitDay());
		MatchingCreditorEx matchingCreditor = new MatchingCreditorEx();
		matchingCreditor.setCustomerCode(loanApply.getCustCode());
		matchingCreditor.setLendCode(loanApply.getApplyCode());
		matchingCreditor.setProductCode(loanApply.getProductCode());
		matchingCreditor.setMatchingFirstdayFlag(BillState.SQ.value);
		matchingCreditor.setMatchingBorrowQuota(loanApply.getLendMoney());
		matchingCreditor.setMatchingTotal(RedeemUtils.getMonths(newLendday,
				loanApply.getExpireDate()) + 1);
		matchingCreditor.setMatchingNow(CustomerConstants.FIRSTPHASE);
		matchingCreditor.setMatchingInterestStart(BillDateUtil
				.getNextDate(entity.getLinitDay()));
		matchingCreditor.setMatchingStatus(MatchingStatus.DTJ.value);
		matchingCreditor.setMatchingBillDay(loanApply.getBillDay());
		matchingCreditor.setCreateBy(UserUtils.getUserId());
		matchingCreditor.setCreateTime(new Date());
		matchingCreditor.setModifyBy(UserUtils.getUserId());
		matchingCreditor.setModifyTime(new Date());
		matchingCreditor.setMatchingId(IdGen.uuid());
		matchingCreditor.setMatchingEndday(loanApply.getExpireDate());
		matchingCreditor.setMatchingPayStatus(SettleStats.WJS.value);
		matchingCreditor.setMatchingExpireDay(CreditorUtils
				.getCreditDaybyLendday(newLendday));
		matchingCreditorDao.insert(matchingCreditor);
	}
	
	/**
	 * 删除非首期未发送的债权文件邮件<br/>
	 * 2016年7月6日<br/>
	 * By 陈广鹏
	 * @param lendCode 出借编号
	 * @param linitDay 赎回到期日期
	 */
	public void deleteEmail(String lendCode, Date linitDay){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lendCode", lendCode);
		map.put("emailType", MailTemplateType.FSQ.value);
		map.put("linitDay", linitDay);
		map.put("emailSendStatus", EmailState.DFS.value);
		dao.deleteEmail(map);
	}

	/**
	 * 释放债权 
	 * 2016年2月23日 
	 * By 陈广鹏
	 * @param entity
	 */
	public void releaseAllCredit(RedemptionApplyEntity entity) {
		MatchingCreditor mce = new MatchingCreditor();
		mce.setLendCode(entity.getLendCode());
		mce.setMatchingEndday(entity.getLinitDay());
		mce.preUpdate();
		dao.updateMatchingEnddayBylendCode(mce);
		
		// 待推荐债权
		MatchingCreditorEx matchingCreditorEx = new MatchingCreditorEx();
		matchingCreditorEx.setLendCode(entity.getLendCode());
		matchingCreditorEx.setMatchingExpireDay(entity.getLinitDay());
		List<MatchingCreditorEx> matchingList = matchingCreditorDao
				.findRedeemList(matchingCreditorEx);
		// 防空指针
		if (ListUtils.isEmptyList(matchingList)) {
			matchingList = new ArrayList<MatchingCreditorEx>();
		}

		String matchingPayStatus ;
		if (matchingList.size() == 1) {
			// 仅有本期债权推荐
			matchingCreditorEx = matchingList.get(0);
			matchingPayStatus = matchingCreditorEx.getMatchingPayStatus();
			List<Loanphase> list = getLoanPhaseList(matchingCreditorEx.getMatchingId());
			// 更新本期待推荐状态为撤销,出借金额为0
			matchingCreditorEx.setMatchingBorrowQuota(BigDecimal.ZERO);
			matchingCreditorEx.setMatchingStatus(MatchingStatus.CX.value);
			matchingCreditorDao.updateByPrimaryKeySelective(matchingCreditorEx);
			
			if (ListUtils.isNotEmptyList(list)) {
				// 更新可用债权和债权管理表
				updateBorrowAndCreditorHis(matchingPayStatus, list, OperateType.TQSHZQSF.value);
				// 债权交易表，按matchingId更新DictTradeCreditStatus-->出借提前到期关闭
				updateCreditorTradeStatus(matchingCreditorEx.getMatchingId(),CreditTradestate.CJTQDQGB.value);
				// 删除本期分期收益数据
				deleteLoanphase(matchingCreditorEx.getMatchingId());
				// 更新总期数为当前期数
				matchingCreditorEx.setMatchingTotal(matchingCreditorEx.getMatchingNow());
				matchingCreditorDao.updateByPrimaryKeySelective(matchingCreditorEx);
			} else {
				// 本期未结算，获取上一期数据
				MatchingCreditorEx mcSearch = new MatchingCreditorEx();
				mcSearch.setLendCode(entity.getLendCode());
				mcSearch.setMatchingExpireDay(entity.getLinitDay());
				matchingCreditorEx = matchingCreditorDao.findPreviousMatchingCreditor(mcSearch);
				if (matchingCreditorEx !=null) {
					matchingPayStatus = matchingCreditorEx.getMatchingPayStatus();
					List<Loanphase> previousList = getLoanPhaseList(matchingCreditorEx.getMatchingId());
					if (ListUtils.isNotEmptyList(previousList)) {
						// 更新可用债权和债权管理表
						updateBorrowAndCreditorHis(matchingPayStatus, previousList, OperateType.TQSHZQSF.value);
						// 债权交易表，按matchingId更新DictTradeCreditStatus-->出借提前到期关闭
						updateCreditorTradeStatus(matchingCreditorEx.getMatchingId(),CreditTradestate.CJTQDQGB.value);
						// 分期收益表中匹配MatchingId的数据，释放状态->已释放
						updateLoanphase(matchingCreditorEx.getMatchingId());
						// 更新上期总期数为当前期数
						matchingCreditorEx.setMatchingTotal(matchingCreditorEx.getMatchingNow());
						matchingCreditorDao.updateByPrimaryKeySelective(matchingCreditorEx);
					}	
				}
			}
		} else if (matchingList.size() == 2) {
			// 存在次期债权推荐的情况
			// 本期处理
			List<String>  loanCodes = new ArrayList<String>(); 
			matchingCreditorEx = matchingList.get(1);
			matchingPayStatus = matchingCreditorEx.getMatchingPayStatus();
			List<Loanphase> list = getLoanPhaseList(matchingCreditorEx.getMatchingId());
			if (ListUtils.isNotEmptyList(list)) {
				// 更新可用债权和债权管理表
				updateBorrowAndCreditorHis(matchingPayStatus, list, OperateType.TQSHZQSF.value);
				// 债权交易表，按matchingId更新DictTradeCreditStatus-->出借提前到期关闭
				updateCreditorTradeStatus(matchingCreditorEx.getMatchingId(),CreditTradestate.CJTQDQGB.value);
				// 分期收益表中匹配MatchingId的数据，释放状态->已释放
				updateLoanphase(matchingCreditorEx.getMatchingId());
				// 更新总期数为当前期数
				matchingCreditorEx.setMatchingTotal(matchingCreditorEx.getMatchingNow());
				matchingCreditorDao.updateByPrimaryKeySelective(matchingCreditorEx);
				
				for (Loanphase lp : list) {
					loanCodes.add(lp.getLoanCode());
				}
			}
			
			// 次期处理
			matchingCreditorEx = matchingList.get(0);
			// 更新本期待推荐状态为撤销,出借金额为0
			matchingCreditorEx.setMatchingBorrowQuota(BigDecimal.ZERO);
			matchingCreditorEx.setMatchingStatus(MatchingStatus.CX.value);
			matchingCreditorDao.updateByPrimaryKeySelective(matchingCreditorEx);
			
			matchingPayStatus = matchingCreditorEx.getMatchingPayStatus();
			
			//20170119 修改匹配过的出借人，不在释放次期债权
			List<Loanphase> nextList = getLoanPhaseListNew(matchingCreditorEx.getMatchingId(),loanCodes);
		//	List<Loanphase> nextList = getLoanPhaseList(matchingCreditorEx.getMatchingId());
			if (ListUtils.isNotEmptyList(nextList)) {
				// 有收益数据时
				// 更新可用债权和债权管理表
				for (Loanphase lp : nextList) {
					// 修改可用债权表
					Borrow borrow = new Borrow();
					borrow.setCreditValueId(lp.getLoanCode());
					Borrow bw = borrowDao.getByEenity(borrow);
					BigDecimal loanCreditValue = BigDecimal.ZERO;
					
					// 可用债权 += 债权交易表.交易金额
					CreditorTrade trade = creditorTradeDao.selectByPrimaryKey(lp.getPhaseMateId());
					loanCreditValue = bw.getLoanCreditValue().add(trade.getTradeMateMoney());
					
					borrow.setLoanCreditValue(loanCreditValue);
					// 修改可用债权表可用债权
					borrow.preUpdate();
					borrow.setVerTime(bw.getVerTime());
					borrowDao.update(borrow);

					// 债权管理记录，状态为撤销
					CreditorHis his = new CreditorHis();
					his.setMatchingId(lp.getMatchingId());
					his.setBeforMoney(bw.getLoanCreditValue());
					his.setAfterMoney(borrow.getLoanCreditValue());
					his.setOperateMoney(loanCreditValue.subtract(bw.getLoanCreditValue()));
					his.setOperateTime(new Date());
					his.setDictCheckNode(Node.KYZQ.value);
					his.setNodeId(bw.getCreditValueId());
					his.setOperateType(OperateType.CX.value);
					his.preInsert();
					creditorHisDao.insert(his);
				}
				// 债权交易表，按matchingId更新DictTradeCreditStatus-->未开始被关闭
				updateCreditorTradeStatus(matchingCreditorEx.getMatchingId(),CreditTradestate.WKSBGB.value);
				// 删除分期收益数据
				deleteLoanphase(matchingCreditorEx.getMatchingId());
			}
		} else {
			FortuneException forException = new FortuneException();
			forException.setLoanCode(entity.getLendCode());
			forException.setMessage("出借对应的待推荐债权匹配数据异常，无本期待推荐债权数据或存在本不应存在的待推荐债权数据");
			forException.setStackTrace(forException.getMessage());
			forException.setNode(FortuneLogNode.REDEMPTION_APPROVAL.getCode());
			forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
			forException.setRemark("提前赎回债权释放");
			
			forException.preInsert();
			FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
			forDao.insert(forException);
			throw new ServiceException("出借对应的待推荐债权匹配数据异常，无本期待推荐债权数据或存在本不应存在的待推荐债权数据");
		}
	}

	/**
	 * 获取推荐债权对应的分期收益数据
	 * 2016年5月12日
	 * By 陈广鹏
	 * @param matchingCreditorEx
	 * @return
	 */
	private List<Loanphase> getLoanPhaseList(String matchingId) {
		// 选择条件：【matchingId】【phase_discard_status=不废弃】
		Loanphase lpSearch = new Loanphase();
		lpSearch.setMatchingId(matchingId);
		lpSearch.setPhaseDiscardStatus(ScrapState.BFQ.value);
		List<Loanphase> list = loanphaseDao
				.getLoanphasebyMatchingId(lpSearch);
		return list;
	}
	
	/**
	 * 获取推荐债权对应的分期收益数据(过滤掉已释放的出借)
	 * 2017年1月19日
	 * By liusl
	 * @param matchingId
	 * @param loanCodes
	 * @return
	 */
	private List<Loanphase> getLoanPhaseListNew(String matchingId,List<String> loanCodes) {
		// 选择条件：【matchingId】【phase_discard_status=不废弃】
		Loanphase lpSearch = new Loanphase();
		lpSearch.setMatchingId(matchingId);
		lpSearch.setPhaseDiscardStatus(ScrapState.BFQ.value);
		lpSearch.setLoanCodes(loanCodes);
		List<Loanphase> list = loanphaseDao.getLoanphasebyMatchingId(lpSearch);
		return list;
	}
	
	/**
	 * 分期收益表中匹配MatchingId的数据，释放状态->已释放
	 * 2016年5月12日
	 * By 陈广鹏
	 * @param matchingId
	 */
	public void updateLoanphase(String matchingId) {
		Loanphase loanphase = new Loanphase();
		loanphase.setMatchingId(matchingId);
		loanphase.setPhaseReleaseStatus(CreditRelease.YSF.value);
		loanphase.preUpdate();
		loanphaseDao.redeemUpdateStatus(loanphase);
	}

	/**
	 * 删除对应的分期收益数据
	 * 2016年5月12日
	 * By 陈广鹏
	 * @param matchingId
	 * @return
	 */
	public Loanphase deleteLoanphase(String matchingId) {
		Loanphase loanphase = new Loanphase();
		loanphase.setMatchingId(matchingId);
		// 删除分期收益数据
		loanphaseDao.deleteByMatchingId(loanphase);
		return loanphase;
	}

	/**
	 * 更新债权交易表状态
	 * 2016年5月12日
	 * By 陈广鹏
	 * @param matchingId 债权匹配ID
	 * @param staus 要更新的状态
	 */
	public void updateCreditorTradeStatus(String matchingId, String staus) {
		CreditorTrade creditorTrade = new CreditorTrade();
		creditorTrade.setMatchingId(matchingId);
		creditorTrade.setDictTradeCreditStatus(staus);
		creditorTrade.preUpdate();
		creditorSendDao.updateTradeCreditStatus(creditorTrade);
	}

	/**
	 * 更新可用债权和债权管理表
	 * 2016年5月12日
	 * By 陈广鹏
	 * @param matchingPayStatus
	 * @param list
	 * @param operateType 债权管理要更新到的状态
	 */
	public void updateBorrowAndCreditorHis(
			String matchingPayStatus, List<Loanphase> list, String operateType) {
		for (Loanphase lp : list) {
			// 修改可用债权表
			Borrow borrow = new Borrow();
			borrow.setCreditValueId(lp.getLoanCode());
			Borrow bw = borrowDao.getByEenity(borrow);
			BigDecimal loanCreditValue = BigDecimal.ZERO;
			if (SettleStats.YJS.value.equals(matchingPayStatus)) {
				// 可用债权+=【分期收益.本期还款结束后，剩余未还本金】
				loanCreditValue = bw.getLoanCreditValue().add(
						lp.getPhasePrincipalSurplus());
			} else {
				// 可用债权+=【分期收益.本期还款结束后，剩余未还本金】+【分期收益.本期应还本金】
				loanCreditValue = bw.getLoanCreditValue()
						.add(lp.getPhasePrincipalSurplus())
						.add(lp.getPhasePrincipalCur());
			}
			
			//释放金额 + 可用债权价值 > 原始借款金额loan_quota ，则停止这次 modify 20161209
			if(loanCreditValue.compareTo(bw.getLoanQuota()) == 1 ){
				continue;
			}
			
			borrow.setLoanCreditValue(loanCreditValue);
			// 修改可用债权表可用债权
			borrow.preUpdate();
			borrow.setVerTime(bw.getVerTime());
			borrowDao.update(borrow);

			// 债权管理记录
			CreditorHis his = new CreditorHis();
			his.setMatchingId(lp.getMatchingId());
			his.setBeforMoney(bw.getLoanCreditValue());
			his.setAfterMoney(borrow.getLoanCreditValue());
			his.setOperateMoney(loanCreditValue.subtract(bw.getLoanCreditValue()));
			his.setOperateTime(new Date());
			his.setDictCheckNode(Node.KYZQ.value);
			his.setNodeId(bw.getCreditValueId());
			his.setOperateType(operateType);
			his.preInsert();
			creditorHisDao.insert(his);
		}
	}

	/**
	 * 获取前一报告日累计收益 
	 * 2016年4月13日 
	 * By 陈广鹏
	 * @param entity
	 * @param lastBillDay
	 * @return
	 */
	private BigDecimal getTotalInterest(RedemptionApplyEntity entity,
			Date lastBillDay) {
		// 上一期债权匹配信息
		MatchingCreditor mc = new MatchingCreditor();
		mc.setLendCode(entity.getLendCode());
		Date linitDay = entity.getLinitDay();
		Date searchDay = linitDay;
		if (RedeemUtils.getDays(lastBillDay, linitDay) == 0) {
			// 到期日与账单日同一天，取本期
			searchDay = linitDay;
		} else {
			// 不是同一天，取前一期
			searchDay = lastBillDay;
		}
		mc.setMatchingExpireDay(searchDay);
		// 前一报告日累计收益:分期收益表中该出借对应的，【本期到期日<=searchDay】所有本期应还利息之和
		BigDecimal totalInterest = loanphaseDao.getTotalInterest(mc);
		if (ObjectHelper.isEmpty(totalInterest)) {
			totalInterest = BigDecimal.ZERO;
		}
		return totalInterest;
	}

	/**
	 * 获取当期债权匹配信息 
	 * 2016年4月13日 
	 * By 陈广鹏
	 * @param entity
	 * @return
	 */
	public MatchingCreditor getCurrentMatchingCreditor(
			RedemptionApplyEntity entity) {
		MatchingCreditor mc = new MatchingCreditor();
		mc.setLendCode(entity.getLendCode());
		mc.setMatchingExpireDay(entity.getLinitDay());
		MatchingCreditor mCreditor = matchingCreditorDao
				.getLastMatchingCreditor(mc);
		return mCreditor;
	}

	/**
	 * 通过产品编号 获取匹配利率 
	 * 2016年4月22日
	 * By 陈广鹏
	 * @param productCode
	 * @param applyLendMoney
	 * @param applyLendDay
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private BigDecimal getppRate(String productCode, BigDecimal applyLendMoney,
			String applyLendDay) {
		BigDecimal rate = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productCode", productCode);
		map.put("lendDay", DateUtils.parseDate(applyLendDay));
		map.put("lendMoney", applyLendMoney.doubleValue());
		List<Map> result = loanApplyDao.getProductRate(map);
		// 收益调整中存在且不为空，取收益调整后的产品利率
		if (ArrayHelper.isNotEmpty(result)) {
			Map m = result.get(0);
			Object realProductRate = m.get("real_product_rate");
			if (!ObjectHelper.isEmpty(realProductRate)) {
				rate = new BigDecimal(realProductRate.toString());
			}
		} 
		// 收益调整中不存在，取匹配利率设置的利率
		if (null == rate) {
			String billType =BillState.SQ.value; // 账单类型 = 首期
			ProductMatchingRate matcingRate = productMatchingRateManager.getProductMchRate(
					productCode, applyLendMoney, applyLendDay, billType,
					1, null);
			if (matcingRate != null) {
				if (matcingRate.getMatchingRateLower() != null) {
					rate = matcingRate.getMatchingRateLower();
				}
			}
		}
		// 如果没有设置匹配利率，取产品默认匹配利率
		if (null == rate) {
			Map<String, BigDecimal> retMap = creditorAidManager
					.getProductDefaultMchRateByCode(productCode);
			if (retMap != null) {
				rate = retMap.get("lower");
			}
		}
		if (null == rate) {
			rate = BigDecimal.ZERO;
		}
		
		return rate;
	}

}
