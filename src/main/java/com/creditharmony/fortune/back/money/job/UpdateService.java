package com.creditharmony.fortune.back.money.job;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.fortune.type.XinhebaoType;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.back.money.job.dao.BackMoneyFixDao;
import com.creditharmony.fortune.back.money.job.entity.ModifyData;
import com.creditharmony.fortune.back.money.job.entity.ProductProfit;
import com.creditharmony.fortune.back.money.job.entity.ex.ProductProfitEx;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.redemption.utils.RedeemUtils;
import com.creditharmony.fortune.utils.BackMoneyUtil;

@Service
@Transactional(value = "fortuneTransactionManager", readOnly = true)
public class UpdateService {
	
	private Logger logger = LoggerFactory.getLogger(UpdateService.class); 
	
	@Autowired
	private BackMoneyFixDao fixDao;
	
	/**
	 * 按照正常规则计算，更新回款金额
	 * 2016年12月22日
	 * By 陈广鹏
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void updateBackMoney() {

		BigDecimal factor = BigDecimal.ZERO;
		Date divideDay = DateUtils.parseDate(RedeemConstant.DEVIDE_DATE); 
		Date date20160801 = DateUtils.parseDate(BmConstant.YXT_DATE); 
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("modifyTime", new Date());
		map.put("divideDay", divideDay);
		
		// YYY("80", "月邮赢"),无数据，不处理
		// JXY("89", "金信盈"),无数据，不处理
		// JXB("90", "金信宝"),无数据，不处理
		// YMY("86", "月满盈"),无数据，不处理

		// NNY("85", "年年盈"),回款金额=出借金额*11%+出借金额
		map.put("productCode", ProductCode.NNY.value);
		factor = BigDecimal.valueOf(11);
		map.put("factor", factor);
		int count = fixDao.updateByFactor(map);
		logger.info("年年盈更新回款金额 " + count + " 条");
		
		// XHBA("91", "信和宝A"),回款金额=出借金额*14%+出借金额
		map.put("productCode", ProductCode.XHBA.value);
		factor = BigDecimal.valueOf(14);
		map.put("factor", factor);
		count = fixDao.updateByFactor(map);
		logger.info("信和宝A更新回款金额 " + count + " 条");
		
		// XHBB("92", "信和宝B"),回款金额=出借金额*29.78%+出借金额
		map.put("productCode", ProductCode.XHBB.value);
		factor = BigDecimal.valueOf(29.78);
		map.put("factor", factor);
		count = fixDao.updateByFactor(map);
		logger.info("信和宝B更新回款金额 " + count + " 条");
		
		// XHBC("93", "信和宝C"),回款金额=出借金额*13.6%/2+出借金额
		map.put("productCode", ProductCode.XHBC.value);
		factor = BigDecimal.valueOf(6.8);
		map.put("factor", factor);
		count = fixDao.updateByFactor(map);
		logger.info("信和宝C更新回款金额 " + count + " 条");
		
		// XHNJ("67", "信和年聚")，回款金额=出借金额*12%*2+出借金额
		map.put("productCode", ProductCode.XHNJ.value);
		factor = BigDecimal.valueOf(24);
		map.put("factor", factor);
		count = fixDao.updateByFactor(map);
		logger.info("信和年聚更新回款金额 " + count + " 条");
		
		// XHT("81", "信和通"),回款金额=出借金额*年化收益率+出借金额
		// 年化收益率：2016-01-05前，12.68%；后10%
		map.put("productCode", ProductCode.XHT.value);
		factor = BigDecimal.valueOf(12.68);
		map.put("factor", factor);
		map.put("before", "before");
		count = fixDao.updateByFactor(map);
		logger.info("2016-01-05前信和通，更新回款金额 " + count + " 条");
		map.remove("before");
		
		factor = BigDecimal.valueOf(10);
		map.put("factor", factor);
		map.put("after", "after");
		count = fixDao.updateByFactor(map);
		logger.info("2016-01-05后信和通，更新回款金额 " + count + " 条");
		map.remove("after");
		
		// JDY("83", "季度盈"),回款金额=出借金额*年化收益率/12*出借期限（3个月，保留百分比3位小数）+出借金额
		// 年化收益率：2016-01-05前，8%；后7%
		map.put("productCode", ProductCode.JDY.value);
		factor = BigDecimal.valueOf(8).divide(BigDecimal.valueOf(4), 15, BigDecimal.ROUND_HALF_UP);
		map.put("factor", factor);
		map.put("before", "before");
		count = fixDao.updateByFactor(map);
		logger.info("2016-01-05前季度盈，更新回款金额 " + count + " 条");
		map.remove("before");

		map.put("productCode", ProductCode.JDY.value);
		factor = BigDecimal.valueOf(7).divide(BigDecimal.valueOf(4), 15, BigDecimal.ROUND_HALF_UP);
		map.put("factor", factor);
		map.put("after", "after");
		count = fixDao.updateByFactor(map);
		logger.info("2016-01-05后季度盈，更新回款金额 " + count + " 条");
		map.remove("after");
		
		// SJY("84", "双季盈"),出借金额*年化收益率/12*出借期限（6个月，保留百分比3位小数）+出借金额
		// 年化收益率：2016-01-05前，9%；后8%
		map.put("productCode", ProductCode.SJY.value);
		factor = BigDecimal.valueOf(9).divide(BigDecimal.valueOf(2), 15, BigDecimal.ROUND_HALF_UP);
		map.put("factor", factor);
		map.put("before", "before");
		count = fixDao.updateByFactor(map);
		logger.info("2016-01-05前双季盈，更新回款金额 " + count + " 条");
		map.remove("before");

		map.put("productCode", ProductCode.SJY.value);
		factor = BigDecimal.valueOf(8).divide(BigDecimal.valueOf(2), 15, BigDecimal.ROUND_HALF_UP);
		map.put("factor", factor);
		map.put("after", "after");
		count = fixDao.updateByFactor(map);
		logger.info("2016-01-05后双季盈，更新回款金额 " + count + " 条");
		map.remove("after");
		
		// NNJ("88", "年年金"),回款金额=出借金额*年化收益率+出借金额
		// 年化收益率：2016-01-05前，8%；后7%
		map.put("productCode", ProductCode.NNJ.value);
		factor = BigDecimal.valueOf(8);
		map.put("factor", factor);
		map.put("before", "before");
		count = fixDao.updateByFactor(map);
		logger.info("2016-01-05前年年金，更新回款金额 " + count + " 条");
		map.remove("before");

		map.put("productCode", ProductCode.NNJ.value);
		factor = BigDecimal.valueOf(7);
		map.put("factor", factor);
		map.put("after", "after");
		count = fixDao.updateByFactor(map);
		logger.info("2016-01-05后年年金，更新回款金额 " + count + " 条");
		map.remove("after");
		
		// XHB("87", "信和宝"),
		// （12个月返息）：回款金额=出借金额*14%+出借金额；
		map.put("productCode", ProductCode.XHB.value);
		factor = BigDecimal.valueOf(14);
		map.put("factor", factor);
		map.put("xhbType", XinhebaoType.XHB12.value);
		count = fixDao.updateByFactor(map);
		logger.info("信和宝（12个月返息）更新回款金额 " + count + " 条");
		map.remove("xhbType");
		
		// （24个月返息）：回款金额=出借金额*29.78%+出借金额
		map.put("productCode", ProductCode.XHB.value);
		factor = BigDecimal.valueOf(29.78);
		map.put("factor", factor);
		map.put("xhbType", XinhebaoType.XHB24.value);
		count = fixDao.updateByFactor(map);
		map.remove("xhbType");
		logger.info("信和宝（24个月返息）更新回款金额 " + count + " 条");
		
		// YXT("82", "月息通"),
		ProductProfitEx searchYXT = new ProductProfitEx();
		searchYXT.setProductCode(ProductCode.YXT.value);
		searchYXT.setStartDay(DateUtils.parseDate("2016-01-05"));
		searchYXT.setCloseDay(DateUtils.parseDate("2016-07-31"));
		List<ModifyData> dataList = fixDao.getDataList(searchYXT);
		Date applyLendDay = new Date();
		String interestDays = "0";
		int cycleDays = 0; // 出借日所在账单日周期天数
		BigDecimal backMoney = BigDecimal.ZERO;
		BigDecimal applyLendMoney = BigDecimal.ZERO;
		count = 0;
		for (ModifyData data : dataList) {
			applyLendDay = data.getApplyLendDay();
			applyLendMoney = data.getApplyLendMoney();
			if (divideDay.after(applyLendDay)) {
				// 2016-01-05 之前的数据不处理
				continue;
			} else {
				if (data.getFinalLinitDate() == null) {
					continue;
				}
				// 2016-01-05 之前后的数据
				cycleDays = RedeemUtils.getCycleDays(applyLendDay, data.getApplyBillday());
				if (date20160801.after(applyLendDay)) {
					// 2016-08-01前，月利率1%，回款金额=出借金额*月利率/出借日所在账单日周期天数*未回息天数+出借金额
					interestDays = BackMoneyUtil.getDaysByLastdayAndBackday(data.getApplyBillday(), data.getFinalLinitDate());
					backMoney = applyLendMoney.multiply(BigDecimal.valueOf(0.01))
							.divide(BigDecimal.valueOf(cycleDays),15, BigDecimal.ROUND_HALF_UP)
							.multiply(new BigDecimal(interestDays))
							.add(applyLendMoney);
					BackMoneyPool pool = new BackMoneyPool();
					pool.setLendCode(data.getLendCode());
					pool.setBackMoney(backMoney);
					pool.setModifyTime(new Date());
					int i = fixDao.updateByLendCode(pool);
					count +=i;
				} else {
					// 回款金额=出借金额*加息后年化收益-12期回息金额+出借金额
					// 12期回息金额=出借金额*月利率/出借日所在账单日周期天数*回息天数+11*出借金额*月利率
					// 收益调整已覆盖这部分数据，这里不计算
				}
			}
		}
		logger.info("月息通更新回款金额 " + count + " 条");
		
		
		// XHYZ("66", "信和月增"),
		ProductProfitEx searchXHYZ = new ProductProfitEx();
		searchXHYZ.setProductCode(ProductCode.XHYZ.value);
		searchXHYZ.setStartDay(DateUtils.parseDate("2016-01-05"));
		searchXHYZ.setCloseDay(DateUtils.parseDate("2016-07-31"));
		dataList = fixDao.getDataList(searchXHYZ);
		
		count = 0;
		for (ModifyData data : dataList) {
			applyLendDay = data.getApplyLendDay();
			applyLendMoney = data.getApplyLendMoney();
			// 2016-01-05 之前无数据，只考虑之后的数据
			cycleDays = RedeemUtils.getCycleDays(applyLendDay, data.getApplyBillday());
			if (date20160801.after(applyLendDay)) {
				// 2016-08-01前，月利率1.083%，回款金额=出借金额*月利率/出借日所在账单日周期天数*未回息天数+出借金额
				interestDays = BackMoneyUtil.getDaysByLastdayAndBackday(data.getApplyBillday(), data.getFinalLinitDate());
				backMoney = applyLendMoney.multiply(BigDecimal.valueOf(0.01083))
						.divide(BigDecimal.valueOf(cycleDays),15, BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(interestDays))
						.add(applyLendMoney);
				BackMoneyPool pool = new BackMoneyPool();
				pool.setLendCode(data.getLendCode());
				pool.setBackMoney(backMoney);
				pool.setModifyTime(new Date());
				int i = fixDao.updateByLendCode(pool);
				count +=i;
			} else {
				// 回款金额=出借金额*加息后年化收益率*2-24期回息金额+出借金额
				// 24期回息金额=出借金额*月利率/出借日所在账单日周期天数*回息天数+23*出借金额*月利率
				// 收益调整已覆盖这部分数据，这里不计算
			}
		}
		logger.info("信和月增更新回款金额 " + count + " 条");
	}
	
	/**
	 * 按照收益调整更新回款金额
	 * 2016年12月22日
	 * By 陈广鹏
	 * @param profit
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void updateByProfitConfig(ProductProfit profit){

		Date divideDay = DateUtils.parseDate(RedeemConstant.DEVIDE_DATE); 
		Date date20160801 = DateUtils.parseDate(BmConstant.YXT_DATE);
		BigDecimal yearRate = profit.getProductYearRate();
		if (yearRate ==null) {
			yearRate = BigDecimal.ZERO;
		}
		BigDecimal monthRate = profit.getProductRate();
		String productCode = profit.getProductCode();
		
		// 计算因子转换
		if (productCode.equals(ProductCode.JDY.value)) {
			// 季度盈处理
			profit.setProductRate(monthRate.divide(BigDecimal.valueOf(4),15, BigDecimal.ROUND_HALF_UP));
		} else if (productCode.equals(ProductCode.SJY.value)) {
			// 双季盈处理
			profit.setProductRate(monthRate.divide(BigDecimal.valueOf(2),15, BigDecimal.ROUND_HALF_UP));
		} else if (productCode.equals(ProductCode.XHNJ.value)) {
			// 信和年聚处理
			profit.setProductRate(monthRate.multiply(BigDecimal.valueOf(2)));
		}

		logger.info(ProductCode.parseByValue(productCode).name+" 更新 start ：收益调整ID: "+profit.getId());
		int count = 0;
		if (productCode.equals(ProductCode.YXT.value)) {
			// 月息通更新
			ProductProfitEx searchYXT = new ProductProfitEx();
			searchYXT.setProductCode(ProductCode.YXT.value);
			searchYXT.setProductMoneyLowe(profit.getProductMoneyLowe());
			searchYXT.setProductMoneyUpper(profit.getProductMoneyUpper());
			searchYXT.setStartDay(profit.getStartDay());
			searchYXT.setCloseDay(profit.getCloseDay());
			List<ModifyData> dataList = fixDao.getDataList(searchYXT);
			
			for (ModifyData data : dataList) {
				Date applyLendDay = data.getApplyLendDay();
				BigDecimal applyLendMoney = data.getApplyLendMoney();
				if (divideDay.after(applyLendDay)) {
					// 2016-01-05 之前的数据不处理
					continue;
				} else {
					if (data.getFinalLinitDate() == null) {
						continue;
					}
					// 2016-01-05 之前后的数据
					int cycleDays = RedeemUtils.getCycleDays(applyLendDay, data.getApplyBillday());
					if (date20160801.after(applyLendDay)) {
						// 2016-08-01前，回款金额=出借金额*月利率/出借日所在账单日周期天数*未回息天数+出借金额
						String interestDays = BackMoneyUtil.getDaysByLastdayAndBackday(data.getApplyBillday(), data.getFinalLinitDate());
						BigDecimal backMoney = applyLendMoney
								.multiply(profit.getProductRate())
								.divide(BigDecimal.valueOf(100),15, BigDecimal.ROUND_HALF_UP)
								.divide(BigDecimal.valueOf(cycleDays),15, BigDecimal.ROUND_HALF_UP)
								.multiply(new BigDecimal(interestDays))
								.add(applyLendMoney);
						BackMoneyPool pool = new BackMoneyPool();
						pool.setLendCode(data.getLendCode());
						pool.setBackMoney(backMoney);
						pool.setModifyTime(new Date());
						int i = fixDao.updateByLendCode(pool);
						count += i;
					} else {
						// 12期回息金额=出借金额*月利率/出借日所在账单日周期天数*回息天数+11*出借金额*月利率
						int interestDays = BackMoneyUtil.getDaysByFirstdayAndBackday(data.getApplyBillday(), applyLendDay);
						BigDecimal interestOf12 = applyLendMoney
								.multiply(monthRate)
								.divide(BigDecimal.valueOf(100),15, BigDecimal.ROUND_HALF_UP)
								.divide(BigDecimal.valueOf(cycleDays),15, BigDecimal.ROUND_HALF_UP)
								.multiply(BigDecimal.valueOf(interestDays))
								.add(applyLendMoney.multiply(monthRate)
										.divide(BigDecimal.valueOf(100),15, BigDecimal.ROUND_HALF_UP)
										.multiply(BigDecimal.valueOf(11)));
						
						// 回款金额=出借金额*加息后年化收益-12期回息金额+出借金额
						BigDecimal backMoney = applyLendMoney
								.multiply(yearRate)
								.divide(BigDecimal.valueOf(100),15, BigDecimal.ROUND_HALF_UP)
								.subtract(interestOf12)
								.add(applyLendMoney);
						
						BackMoneyPool pool = new BackMoneyPool();
						pool.setLendCode(data.getLendCode());
						pool.setBackMoney(backMoney);
						pool.setModifyTime(new Date());
						int i = fixDao.updateByLendCode(pool);
						count += i;
					}
				}
			}
		} else if (productCode.equals(ProductCode.XHYZ.value)) {
			// 信和月增更新
			ProductProfitEx searchXHYZ = new ProductProfitEx();
			searchXHYZ.setProductCode(ProductCode.XHYZ.value);
			searchXHYZ.setProductMoneyLowe(profit.getProductMoneyLowe());
			searchXHYZ.setProductMoneyUpper(profit.getProductMoneyUpper());
			searchXHYZ.setStartDay(profit.getStartDay());
			searchXHYZ.setCloseDay(profit.getCloseDay());
			List<ModifyData> dataList = fixDao.getDataList(searchXHYZ);
			
			for (ModifyData data : dataList) {
				Date applyLendDay = data.getApplyLendDay();
				BigDecimal applyLendMoney = data.getApplyLendMoney();
				// 2016-01-05 之前无数据，只考虑之后的数据
				int cycleDays = RedeemUtils.getCycleDays(applyLendDay, data.getApplyBillday());
				if (date20160801.after(applyLendDay)) {
					// 2016-08-01前，回款金额=出借金额*月利率/出借日所在账单日周期天数*未回息天数+出借金额
					String interestDays = BackMoneyUtil.getDaysByLastdayAndBackday(data.getApplyBillday(), data.getFinalLinitDate());
					BigDecimal backMoney = applyLendMoney
							.multiply(profit.getProductRate())
							.divide(BigDecimal.valueOf(100),15, BigDecimal.ROUND_HALF_UP)
							.divide(BigDecimal.valueOf(cycleDays),15, BigDecimal.ROUND_HALF_UP)
							.multiply(new BigDecimal(interestDays))
							.add(applyLendMoney);
					BackMoneyPool pool = new BackMoneyPool();
					pool.setLendCode(data.getLendCode());
					pool.setBackMoney(backMoney);
					pool.setModifyTime(new Date());
					int i = fixDao.updateByLendCode(pool);
					count += i;
				} else {
					// 24期回息金额=出借金额*月利率/出借日所在账单日周期天数*回息天数+23*出借金额*月利率
					int interestDays = BackMoneyUtil.getDaysByFirstdayAndBackday(data.getApplyBillday(), applyLendDay);
					BigDecimal interestOf24 = applyLendMoney.multiply(monthRate)
							.divide(BigDecimal.valueOf(100),15, BigDecimal.ROUND_HALF_UP)
							.divide(BigDecimal.valueOf(cycleDays),15, BigDecimal.ROUND_HALF_UP)
							.multiply(BigDecimal.valueOf(interestDays))
							.add(applyLendMoney.multiply(BigDecimal.valueOf(23))
									.multiply(monthRate)
									.divide(BigDecimal.valueOf(100),15, BigDecimal.ROUND_HALF_UP));
					
					// 回款金额=出借金额*加息后年化收益率*2-24期回息金额+出借金额
					BigDecimal backMoney = applyLendMoney.multiply(yearRate)
							.divide(BigDecimal.valueOf(100),15, BigDecimal.ROUND_HALF_UP)
							.multiply(BigDecimal.valueOf(2))
							.subtract(interestOf24).add(applyLendMoney);
					
					BackMoneyPool pool = new BackMoneyPool();
					pool.setLendCode(data.getLendCode());
					pool.setBackMoney(backMoney);
					pool.setModifyTime(new Date());
					int i = fixDao.updateByLendCode(pool);
					count += i;
				}
			}
		} else {
			// 其它产品更新
			profit.setModifyTime(new Date());
			count = fixDao.updateByProfitConfig(profit);
		}
		logger.info("根据收益调整信息，更新回款金额 " + count + " 条，收益调整ID: "+profit.getId());
	}

	/**
	 * 根据id获取收益调整信息
	 * 2016年12月27日
	 * By 陈广鹏
	 * @param productProfitId
	 * @return
	 */
	public ProductProfit getProductProfit(String productProfitId) {
		ProductProfit profit = fixDao.get(productProfitId);
		return profit;
	}

	// 获取所有启动状态收益调整信息
	public List<ProductProfit> getProductProfitList() {
		List<ProductProfit> list = fixDao.findList(null);
		return list;
	}

	/**
	 * 获取执行标识
	 * 2016年12月27日
	 * By 陈广鹏
	 * @return
	 */
	public Check getCheckFlag() {
		return fixDao.getCheckFlag();
	}
	
	/**
	 * 更新执行标识
	 * 2016年12月27日
	 * By 陈广鹏
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public int updateCheckFlag() {
		return fixDao.updateCheckFlag();
	}

}
