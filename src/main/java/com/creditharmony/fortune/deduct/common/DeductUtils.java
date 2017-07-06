package com.creditharmony.fortune.deduct.common;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.deduct.bean.in.DeductReq;
import com.creditharmony.core.deduct.type.DeductFlagType;
import com.creditharmony.core.deduct.type.DeductWays;
import com.creditharmony.core.fortune.type.BackMoneyPlat;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.deduct.entity.DeductBespoke;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.entity.ext.DeductPoolEx;
import com.creditharmony.fortune.deduct.entity.ext.FyImportDeductResult;
import com.creditharmony.fortune.deduct.entity.ext.HylImportDeductResult;
import com.creditharmony.fortune.deduct.entity.ext.TonglianImportDeductResult;
import com.creditharmony.fortune.deduct.entity.ext.ZhjinImportDeductResult;
import com.creditharmony.fortune.utils.FortuneDictUtil;
import com.google.common.collect.Lists;

/**
 * 划扣工具类
 * @Class Name DeductUtils
 * @author 韩龙
 * @Create In 2016年2月1日
 */
public class DeductUtils {
	/**
	 * 初始化logger对象
	 */
	protected static Logger logger = LoggerFactory.getLogger(DeductUtils.class);
	
	public enum DeductEnum {
		// 富有
		FY_JYCG("商户已复核,交易成功"),
		FY_JYSB("商户已复核,交易失败"),
		FY_WFHJYWFS("商户未复核,交易未发送"),
		FY_YFHJYWFS("商户已复核,交易未发送"),
		FY_YFHJYFSZ("商户已复核,交易发送中"),
		FY_YFHJYYFS("商户已复核,交易已发送"),
		// 好易联
		HYL_CG("成功"),
		HYL_SB("失败"),
		HYL_CLZ("处理中"),
		// 中金
		ZJ_CLZ("20-正在处理"),
		ZJ_CG("30-代收成功"),
		// 通联
		TL_SB("处理失败"),
		TL_CLZ("处理中"),
		TL_CG("处理成功");
		public final String value;
		private DeductEnum(String value){
			this.value=value;
		}
	}
	
	/**
	 * PDF或word枚举
	 * @Class Name TheDayEnum
	 * @author 韩龙
	 * @Create In 2016年3月23日
	 */
	public enum SendAttachmentFileType {
		// 分天划扣
		PDF("pdf"),
		// 单笔划扣
		WORD("word");
		public final String value;
		private SendAttachmentFileType(String value){
			this.value=value;
		}
	}
	
	/**
	 * 设置当日是否跳转，是枚举
	 * @Class Name TheDayEnum
	 * @author 韩龙
	 * @Create In 2016年3月23日
	 */
	public enum ThedayJump {
		// 是
		YES("0"),
		// 否
		NO("1");
		public final String value;
		private ThedayJump(String value){
			this.value=value;
		}
	}
	
	/**
	 * 分天划扣标识
	 * @Class Name TheDayEnum
	 * @author 韩龙
	 * @Create In 2016年3月23日
	 */
	public enum TheDayEnum {
		// 分天划扣
		FYHK("1"),
		// 单笔划扣
		DBHK("0");
		public final String value;
		private TheDayEnum(String value){
			this.value=value;
		}
	}
	
	/**
	 * 判断不为空，为防空指针 
	 * 2016年1月31日 
	 * By 韩龙
	 * @param str
	 * @return
	 */
	public static String isNull(String str) {
		if (str != null)
			return str;
		return "";
	}
	
	/**
	 * 判断金额是否为空
	 * 2016年3月25日
	 * By 韩龙
	 * @param str
	 * @return
	 */
	public static String isNullMoney(String str){
		if(str == null || "".equals(str)){
			return "0";
		}
		return str;
	}
	
	/**
	 * 判断金额是否为空
	 * 2016年3月25日
	 * By 韩龙
	 * @param str
	 * @return
	 */
	public static BigDecimal isNullBigDecimal(BigDecimal str){
		if(str == null){
			return new BigDecimal("0");
		}
		return str;
	}

	/**
	 * 封装集合 2016年2月19日 By 韩龙
	 * 
	 * @param str
	 * @return
	 */
	public static List<String> assembleStr(String str) {
		List<String> list = Lists.newArrayList();
		if (str != null && !"".equals(str)) {
			String[] code = str.split(",");
			list = Arrays.asList(code);
		}
		return list;
	}

	/**
	 * 截取城市
	 * 2016年5月12日
	 * By 来智辉
	 * @param str
	 * @return
	 */
	public static String anreNamedep(String str){
		if (str != null && !"".equals(str)) {
			String[] code = str.split("_");
			return code[1].toString();
		}
		return null;
	}
	
	
	/**
	 * 封装查询集合 2016年2月20日 By 韩龙
	 * 
	 * @param str
	 * @return
	 */
	public static void assembleDeductPoolEx(DeductPoolEx deductPoolEx) {
		// 银行集合
		deductPoolEx.setAccountBankList(assembleStr(deductPoolEx
				.getAccountBank()));
		// 支付平台集合
		deductPoolEx.setApplyPayList(assembleStr(deductPoolEx.getApplyPay()));
		// 划扣平台集合
		deductPoolEx.setDictApplyDeductTypeList(assembleStr(deductPoolEx
				.getDictApplyDeductType()));
		// 产品集合
		deductPoolEx.setProductCodeList(assembleStr(deductPoolEx
				.getProductCode()));
		// 营业部
		deductPoolEx.setCheckNodeList(assembleStr(deductPoolEx.getCheckNode()));
	}

	/**
	 * 根据日期算出下个工作日 2016年2月22日 By 韩龙
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static Date getNextWorkingDay(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(date));
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week = c.get(Calendar.DAY_OF_WEEK);
		System.out.println("今天是星期" + (week - 1));
		if ((week - 1) == 5) {
			c.add(Calendar.DAY_OF_MONTH, +3);
		} else if ((week - 1) == 6) {
			c.add(Calendar.DAY_OF_MONTH, +2);
		} else {
			c.add(Calendar.DAY_OF_MONTH, +1);
		}
		System.out.println(sdf.format(date));
		return c.getTime();
	}
	
	/**
	 * 获取数据字典
	 * 2016年3月26日
	 * By 韩龙
	 * @param model
	 */
	public static void getDictList(Model model){
		FortuneDictUtil.addDicts(model, new String[]{"tz_pay_type","jk_deduct_plat","tz_trust_state","tz_lend_state"
				,"tz_deduct_plat","tz_open_bank","com_email_state","tz_deduct_state","tz_filecp_state","com_email_state",
				"com_deduct_type","jk_prof_type","sex","tz_bill_state","tz_bill_day","com_card_type","tz_protocol_version","tz_back_state"});
	}
	
//	 public static void main(String[] args) {
//		 String str = new BigDecimal("-100").add(new BigDecimal(200)).toString();
//		 System.out.println(str);
//	 }

	/**
	 * 计算可跳转平台的总限额 2016年3月9日 By 韩龙
	 * 
	 * @param rule
	 * @return
	 */
	public static BigDecimal getMoney(String rule) {
		BigDecimal money = new BigDecimal("0");
		if (rule == null || "".equals(rule)) {
			return null;
		}

		String[] tem = rule.split(",");
		for (String string : tem) {
			String[] str = string.split(":");
			money = money.add(new BigDecimal(str[2]));
		}
		return money;
	}

	/**
	 * 获取划扣规则 2016年3月9日 By 韩龙
	 * 
	 * @param temp
	 * @return
	 */
	public static String getDeductRule(String temp) {
		StringBuffer rule = new StringBuffer();
		if (temp == null || "".equals(temp)) {
			return null;
		}
		/**
		 * 数据结构为：1:0:59012,2:1:32987,0:0:421323,3:1:430982 第一位平台id
		 * 第二位划扣方式：实时：0，批量：1 第三位单日限额
		 */
		String[] rules = temp.split(",");
		for (String string : rules) {
			String[] str = string.split(":");
			rule.append(str[0]).append(":").append(str[1]).append(",");
		}
		String rules1 = rule.substring(0, rule.length() - 1);
		return rules1;
	}

	/**
	 * 富有划扣结果导出去重 2016年3月15日 
	 * By 韩龙
	 * @param resultList
	 * @return
	 */
	public static Map<String, DeductPool> fuYouResultRepeat(
			List<FyImportDeductResult> resultList) {
		boolean b1 = false;
		Map<String, DeductPool> disMap = new HashMap<String, DeductPool>();
		for (FyImportDeductResult fyImportDeductResult : resultList) {
			if(fyImportDeductResult.getNumberEnterprises() == null 
					|| "".equals(fyImportDeductResult.getNumberEnterprises())){
				continue;
			}
			// 划扣状态
			String status = fyImportDeductResult.getTradingStatus();
			// 出借编号
			String lendCode = fyImportDeductResult.getNumberEnterprises().split("_")[1];
			if(lendCode == null || "".equals(lendCode)){
				continue;
			}
			if (!disMap.containsKey(lendCode)) {
				DeductPool dp = new DeductPool();
				dp.setApplyCode(lendCode);
				// 判断是否成功
				if (status.equals(DeductEnum.FY_JYCG.value)) {
					// 设置成功状态
					dp.setDictDeductStatus(DeductState.HKCG.value);
					// 设置成功金额
					dp.setActualDeductMoney(new BigDecimal(DeductUtils.isNullMoney(fyImportDeductResult.getDeductMoney())).toString());
				}else{
					// 设置失败状态
					dp.setDictDeductStatus(DeductState.HKSB.value);
					// 设置失败金额
					dp.setFailMoney(new BigDecimal(DeductUtils.isNullMoney(fyImportDeductResult.getDeductMoney())).toString());
					b1 = true;
				}
				// 交易时间
				dp.setDealTime(DateUtils.formatDate(fyImportDeductResult.getTransactionCommitTime(), "yyyy-MM-dd"));
				// 原因
				dp.setFailReason(fyImportDeductResult.getReturnPostscript());
				disMap.put(lendCode, dp);
			} else {
				
				DeductPool dp = disMap.get(lendCode);
				// 判断是否成功
				if (status.equals(DeductEnum.FY_JYCG.value) 
						/*&& dp.getDictDeductStatus().equals(DeductState.HKCG.value)*/) {
					// 设置成功金额
					dp.setActualDeductMoney(new BigDecimal(DeductUtils.isNullMoney(fyImportDeductResult.getDeductMoney()))
						.add(new BigDecimal(DeductUtils.isNullMoney(dp.getActualDeductMoney()))).toString());
				}else{
					// 设置失败状态
					dp.setDictDeductStatus(DeductState.HKSB.value);
					// 设置失败金额
					dp.setFailMoney(new BigDecimal(DeductUtils.isNullMoney(fyImportDeductResult.getDeductMoney()))
					.add(new BigDecimal(DeductUtils.isNullMoney(dp.getFailMoney()))).toString());
					// 交易时间
					dp.setDealTime(DateUtils.formatDate(fyImportDeductResult.getTransactionCommitTime(), "yyyy-MM-dd"));
					// 原因
					dp.setFailReason(fyImportDeductResult.getReturnPostscript());
					b1 = true;
				}
				if(b1){
					dp.setDictDeductStatus(DeductState.HKSB.value);
				}
				disMap.put(lendCode, dp);
			}
		}
		return disMap;
	}
	
	/**
	 * 好易联划扣结果导出去重 2016年3月15日 
	 * By 韩龙
	 * @param resultList
	 * @return
	 */
	public static Map<String, DeductPool> hylResultRepeat(
			List<HylImportDeductResult> resultList) {
		boolean b1 = false;
		Map<String, DeductPool> disMap = new HashMap<String, DeductPool>();
		for (HylImportDeductResult hylImportDeductResult : resultList) {
			if(hylImportDeductResult.getRemark() == null || "".equals(hylImportDeductResult.getRemark())){
				continue;
			}
			// 划扣状态
			String status = hylImportDeductResult.getReturnResult();
			// 出借编号
			String lendCode = hylImportDeductResult.getRemark().split("_")[1];
			if(lendCode == null || "".equals(lendCode)){
				continue;
			}
			if (!disMap.containsKey(lendCode)) {
				DeductPool dp = new DeductPool();
				dp.setApplyCode(lendCode);
				// 判断是否成功
				if (status.equals(DeductEnum.HYL_CG.value)) {
					// 设置成功状态
					dp.setDictDeductStatus(DeductState.HKCG.value);
					// 设置成功金额
					dp.setActualDeductMoney(new BigDecimal(DeductUtils.isNullMoney(hylImportDeductResult.getDeductMoney())).toString());
				}else{
					// 设置失败状态
					dp.setDictDeductStatus(DeductState.HKSB.value);
					// 设置失败金额
					dp.setFailMoney(new BigDecimal(DeductUtils.isNullMoney(hylImportDeductResult.getDeductMoney())).toString());
					b1 = true;
				}
				// 交易时间
				dp.setDealTime(DateUtils.formatDate(hylImportDeductResult.getSubmissionTime(), "yyyy/MM/dd HH:mm:ss"));
				// 原因
				dp.setFailReason(hylImportDeductResult.getReason());
				disMap.put(lendCode, dp);
			} else {
				
				DeductPool dp = disMap.get(lendCode);
				// 判断是否成功
				if (status.equals(DeductEnum.HYL_CG.value) 
						/*&& dp.getDictDeductStatus().equals(DeductState.HKCG.value)*/) {
					// 设置成功金额
					dp.setActualDeductMoney(new BigDecimal(DeductUtils.isNullMoney(hylImportDeductResult.getDeductMoney()))
						.add(new BigDecimal(DeductUtils.isNullMoney(dp.getActualDeductMoney()))).toString());
				}else{
					// 设置失败状态
					dp.setDictDeductStatus(DeductState.HKSB.value);
					// 设置失败金额
					dp.setFailMoney(new BigDecimal(DeductUtils.isNullMoney(hylImportDeductResult.getDeductMoney()))
						.add(new BigDecimal(DeductUtils.isNullMoney(dp.getFailMoney()))).toString());
					// 交易时间
					dp.setDealTime(DateUtils.formatDate(hylImportDeductResult.getSubmissionTime(), "yyyy/MM/dd HH:mm:ss"));
					// 原因
					dp.setFailReason(hylImportDeductResult.getReason());
					b1 = true;
				}
				
				if(b1){
					dp.setDictDeductStatus(DeductState.HKSB.value);
				}
				disMap.put(lendCode, dp);
			}
		}
		return disMap;
	}
	
	/**
	 * 中金划扣结果导出去重 2016年3月15日 
	 * By 韩龙
	 * @param resultList
	 * @return
	 */
	public static Map<String, DeductPool> zhJinResultRepeat(
			List<ZhjinImportDeductResult> resultList) {
		boolean b1 = false;
		Map<String, DeductPool> disMap = new HashMap<String, DeductPool>();
		for (ZhjinImportDeductResult zhjinImportDeductResult : resultList) {
			
			if(zhjinImportDeductResult.getRemark() == null 
					|| "".equals(zhjinImportDeductResult.getRemark())){
				continue;
			}
			// 划扣状态
			String status = zhjinImportDeductResult.getTradingStatus();
			// 出借编号
			String lendCode = zhjinImportDeductResult.getRemark().split("_")[1];
			
			if (!disMap.containsKey(lendCode)) {
				DeductPool dp = new DeductPool();
				dp.setApplyCode(lendCode);
				// 判断是否成功
				if (status.equals(DeductEnum.ZJ_CG.value)) {
					// 设置成功状态
					dp.setDictDeductStatus(DeductState.HKCG.value);
					// 设置成功金额
					dp.setActualDeductMoney(new BigDecimal(DeductUtils.isNullMoney(zhjinImportDeductResult.getDeductMoney())).toString());
				}else{
					// 设置失败状态
					dp.setDictDeductStatus(DeductState.HKSB.value);
					// 设置失败金额
					dp.setFailMoney(new BigDecimal(DeductUtils.isNullMoney(zhjinImportDeductResult.getDeductMoney())).toString());
					b1 = true;
				}
				// 交易时间
				dp.setDealTime(DateUtils.formatDate(zhjinImportDeductResult.getTimeOfBankCollection(), "yyyy/MM/dd HH:mm:ss"));
				// 原因
				dp.setFailReason(zhjinImportDeductResult.getBankResponseMessage());
				disMap.put(lendCode, dp);
			} else {
				
				DeductPool dp = disMap.get(lendCode);
				// 判断是否成功
				if (status.equals(DeductEnum.ZJ_CG.value) 
						/*&& dp.getDictDeductStatus().equals(DeductState.HKCG.value)*/) {
					// 设置成功金额
					dp.setActualDeductMoney(new BigDecimal(DeductUtils.isNullMoney(zhjinImportDeductResult.getDeductMoney()))
						.add(new BigDecimal(DeductUtils.isNullMoney(dp.getActualDeductMoney()))).toString());
				}else{
					// 设置失败状态
					dp.setDictDeductStatus(DeductState.HKSB.value);
					// 设置失败金额
					dp.setFailMoney(new BigDecimal(DeductUtils.isNullMoney(zhjinImportDeductResult.getDeductMoney()))
						.add(new BigDecimal(DeductUtils.isNullMoney(dp.getFailMoney()))).toString());
					// 交易时间
					dp.setDealTime(DateUtils.formatDate(zhjinImportDeductResult.getTimeOfBankCollection(), "yyyy/MM/dd HH:mm:ss"));
					// 原因
					dp.setFailReason(zhjinImportDeductResult.getBankResponseMessage());
					b1 = true;
				}
				
				if(b1){
					dp.setDictDeductStatus(DeductState.HKSB.value);
				}
				disMap.put(lendCode, dp);
			}
		}
		return disMap;
	}
	
	/**
	 * 通联划扣结果导出去重 
	 * 2016年3月15日 
	 * By 韩龙
	 * @param resultList
	 * @return
	 */
	public static Map<String, DeductPool> tongLianResultRepeat(
			List<TonglianImportDeductResult> resultList) {
		boolean b1 = false;
		Map<String, DeductPool> disMap = new HashMap<String, DeductPool>();
		for (TonglianImportDeductResult tonglianImportDeductResult : resultList) {
			
			if(tonglianImportDeductResult.getRemark() == null 
					|| "".equals(tonglianImportDeductResult.getRemark())){
				continue;
			}
			// 划扣状态
			String status = tonglianImportDeductResult.getTradingStatus();
			// 出借编号
			String lendCode = tonglianImportDeductResult.getRemark().split("_")[1];
			
			if (!disMap.containsKey(lendCode)) {
				DeductPool dp = new DeductPool();
				dp.setApplyCode(lendCode);
				// 判断是否成功
				if (status.equals(DeductEnum.TL_CG.value)) {
					// 设置成功状态
					dp.setDictDeductStatus(DeductState.HKCG.value);
					// 设置成功金额
					dp.setActualDeductMoney(new BigDecimal(DeductUtils.isNullMoney(tonglianImportDeductResult.getDeductMoney())).toString());
				}else{
					b1 = true;
					// 设置失败状态
					dp.setDictDeductStatus(DeductState.HKSB.value);
					// 设置失败金额
					dp.setFailMoney(new BigDecimal(DeductUtils.isNullMoney(tonglianImportDeductResult.getDeductMoney())).toString());
				}
				// 交易时间
				dp.setDealTime(tonglianImportDeductResult.getFinishTime());
				// 原因
				dp.setFailReason(tonglianImportDeductResult.getReason());
				disMap.put(lendCode, dp);
			} else {
				
				DeductPool dp = disMap.get(lendCode);
				// 判断是否成功
				if (status.equals(DeductEnum.TL_CG.value) 
						/*&& dp.getDictDeductStatus().equals(DeductState.HKCG.value)*/) {
					// 设置成功金额
					dp.setActualDeductMoney(new BigDecimal(DeductUtils.isNullMoney(tonglianImportDeductResult.getDeductMoney()))
						.add(new BigDecimal(DeductUtils.isNullMoney(dp.getActualDeductMoney()))).toString());
				}else{
					// 设置失败状态
					dp.setDictDeductStatus(DeductState.HKSB.value);
					// 设置失败金额
					dp.setFailMoney(new BigDecimal(DeductUtils.isNullMoney(tonglianImportDeductResult.getDeductMoney()))
						.add(new BigDecimal(DeductUtils.isNullMoney(dp.getFailMoney()))).toString());
					// 交易时间
					dp.setDealTime(tonglianImportDeductResult.getFinishTime());
					// 原因
					dp.setFailReason(tonglianImportDeductResult.getReason());
					b1 = true;
				}
				if(b1){
					dp.setDictDeductStatus(DeductState.HKSB.value);
				}
				disMap.put(lendCode, dp);
			}
		}
		return disMap;
	}
	
	
	/**
	 * 组装请求对象-预约划扣用
	 * 2016年2月1日
	 * By 韩龙
	 * @param deductBespoke
	 * @return
	 */
	public static DeductReq header(DeductBespoke deductBespoke){
		logger.debug("组装预约划扣请求对象---->开始");
		DeductReq deductReq = new DeductReq();
		deductReq.setSysId(DeductWays.CF_01.getCode());
		// 判断是否分天划扣
		if(deductBespoke.getDayDeductId() !=null){
			logger.debug("组装分天预约划扣请求对象");
			deductReq.setRequestId(deductBespoke.getDayDeductId());
			deductReq.setRefId(deductBespoke.getDayDeductId());
		}else{
			logger.debug("组装单天预约划扣请求对象");
			deductReq.setRequestId(DeductUtils.isNull(deductBespoke.getLendCode()));
		}
		deductReq.setAmount(deductBespoke.getBespokeDeductMoney());
		deductReq.setBusinessId(DeductUtils.isNull(deductBespoke.getLendCode()));
		deductReq.setRule(DeductUtils.isNull(deductBespoke.getDictDeductRule()));
		deductReq.setDeductFlag(DeductFlagType.COLLECTION.getCode());
//		deductReq.setAccountName(DeductUtils.isNull(deductBespoke.getDictDeductRule()));
		deductReq.setBankId(DeductUtils.isNull(deductBespoke.getBankNo()+ ""));
		deductReq.setBankProv(DeductUtils.isNull(deductBespoke.getAccountAddrprovince()));
		deductReq.setBankCity(DeductUtils.isNull(deductBespoke.getAccountAddrcity()));
		deductReq.setBankName(DeductUtils.isNull(deductBespoke.getAccountBranch()));
		deductReq.setAccountNo(DeductUtils.isNull(deductBespoke.getAccountNo()));
		deductReq.setAccountName(DeductUtils.isNull(deductBespoke.getAccountName()));
		deductReq.setIdType(DeductUtils.isNull(deductBespoke.getDictCustomerCertType()));
		deductReq.setIdNo(DeductUtils.isNull(deductBespoke.getCustomerCertNum()));
		deductReq.setMobile(DeductUtils.isNull(deductBespoke.getMobilephone()));
		deductReq.setAccountType(DeductUtils.isNull(deductBespoke.getAccountType()));
		logger.debug("组装预约划扣请求对象---->结束");
		return deductReq;
	}
	
	/**
	 * 平台金额校验
	 * 
	 * 2016年4月24日
	 * By 朱杰 
	 * @param platformId 平台id
	 * @param dr 组装后的划扣请求
	 * @return
	 */
	public static String platformCheck(String platformId,DeductReq dr,String lendCode){
		String message="";
		if (BackMoneyPlat.TL.value.equals(platformId)) {
			// 通联平台回款时需判断
			if (GlobalConstant.TL_Amount.compareTo(dr.getAmount()) < 0) {
				String amount = StringUtils.doNumFormat(GlobalConstant.TL_Amount, "#,##0");
				// 回款金额大于300W时，不处理
				message = message + "出借【" + lendCode
						+ "】的划拨金额大于"+amount+"元，请选择其他平台进行代付操作。<br/>";
			}
			if (OpenBank.GFYH.value.equals(dr.getBankId())) {
				// 客户收款行为广发银行时，不处理
				message = message + "出借【" + lendCode
						+ "】的收款银行为广发银行，目前通联平台不支持该银行代付。<br/>";
			}
		} else if (BackMoneyPlat.KL.value.equals(platformId)) {
			// 卡联平台回款时需判断
			if (GlobalConstant.KA_LIAN_LIMIT.compareTo(dr.getAmount()) < 0) {
				String amount = StringUtils.doNumFormat(GlobalConstant.KA_LIAN_LIMIT, "#,##0");
				// 回款金额大于20W时，不处理
				message = message + "出借【" + lendCode
						+ "】的划拨金额大于"+amount+"元，请选择其他平台进行代付操作。<br/>";
			}
			if (StringUtils.isEmpty(dr.getBranchCode())) {
				message = message + "出借【" + lendCode
						+ "】的收款支行信息匹配失败，卡联平台无法进行代付操作。<br/>";
			}
		}
		return message;
	}
	
	/**
	 * 此方法用于获取applycode
	 * 2016年4月26日
	 * By 刘雄武
	 * @param codes
	 * @return
	 */
	public static String[] getCodes(String codes) {
		if(codes == null){
			return new String[]{};
		}
		String[] strAll = new String[codes.split(",").length];
		String[] str = codes.split(",");
		for (int i = 0; i < str.length; i++) {
			strAll[i] = str[i].split("_")[0];
		}
		return strAll;
	}
}
