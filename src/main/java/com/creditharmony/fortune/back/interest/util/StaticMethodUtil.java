package com.creditharmony.fortune.back.interest.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.common.type.BackResult;
import com.creditharmony.core.fortune.type.BackInterestPlat;
import com.creditharmony.core.fortune.type.BacksmsReason;
import com.creditharmony.core.fortune.type.BacksmsState;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.interest.entity.BackInterestLog;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.LineBackInterestObj;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.back.interest.view.BackInterestListView;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.entity.Product;
import com.creditharmony.fortune.utils.FileUtil;

/**
 * Class tools
 * @Class Name StaticMethodUtil 
 * @author Mr.Robin
 * @Create In 2016年2月2日
 */
public class StaticMethodUtil {
	
	/**
	 * 日志对象
	 */
	protected static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	/**
	 * 过滤空字符串
	 * 2016年4月21日
	 * by 李志伟
	 * @param str
	 * @return
	 */
	public static String isNull(String str) {
		if (str != null){
			return str;
		}
		return "";
	}
	
	/**
	 * 省市过滤方法
	 * 2016年3月22日
	 * by 李志伟
	 * @param str
	 * @return
	 */
	public static String spiltProvince(String str){
		
		boolean b = str.endsWith("省");
		boolean c = str.endsWith("市");
		boolean d = str.endsWith("自治区");
		boolean e = str.endsWith("地区");
		if(b){
			str = str.substring(0, str.length()-1);
		}else if(c){
			str = str.substring(0, str.length()-1);
		}else if(d){
			str = str.substring(0, str.length()-3);
		}else if(e){
			str = str.substring(0, str.length()-2);
		}else{
			
		}
		return str;
	}
	
	/**
	 * 数组过滤空字符串方法
	 * 2016年3月22日
	 * by 李志伟
	 * @param str
	 * @return
	 */
	public static String[] filterNullStr(String[] str){
		
		List<String> list = new ArrayList<String>();
		for (int i = 0; i <= str.length-1; i++) {
			String s = str[i];
			if(s!=null && !("").equals(s)){
				list.add(s);
			}
		}
		return list.toArray(new String[list.size()]);
	}
	
	/**
	 * 序号生成器
	 * 2016年3月22日
	 * by 李志伟
	 * @param index
	 * @param ptMc
	 * @return
	 */
	public static String serNoMade(String index){
		 index = index.trim();
		 if(index.length() == 1){
			 index = "0000" + index;
		 }else if(index.length() == 2){
			 index = "000" + index;
		 }else if(index.length() == 3){
			 index = "00" + index;
		 }else if(index.length() == 4){
			 index = "0" + index;
		 }else if(index.length() > 4){
            return index;
		 }
		 return index;
	}
	
	/**
	 * 校验金额的正确性(包含小数位的校验)
	 * 2016年4月7日
	 * by 李志伟
	 * @param s
	 * @return
	 */
	public static String  parttenMethod(String s){
		
		String money = String.valueOf(s);
		// 校验传入的值后两位是不是已两位小数结尾以及是不是首位已1到9开始
		String numFormat = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$"; 
		String meg  ="";
		try {
			Pattern pattern = Pattern.compile(numFormat);
			Matcher matcher = pattern.matcher(money);
			boolean mat = matcher.matches();
			if(!mat){
				meg =  meg+"金额小数位有误</br>";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			meg = meg+"上传金额格式错误</br>";
		}
		return meg;
	}
	
	/**
	 * 不带小数位的金额校验
	 * 2016年5月27日
	 * by 李志伟
	 * @return
	 */
	public static String parttenMethodNoPoint(Double s){
		
		String mesg  = "";
		String money = String.valueOf(s);
		// 校验传入的值后两位是不是已两位小数结尾以及是不是首位已1到9开始
		String numFormat = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0})?$"; 
		String meg ="";
		try {
			Pattern pattern = Pattern.compile(numFormat);
			Matcher matcher = pattern.matcher(money);
			boolean mat = matcher.matches();
			if(!mat){
				meg =  meg+"金额小数位有误</br>";
			}
		} catch (Exception e) {
			e.printStackTrace();
			meg = meg+"上传金额格式错误</br>";
		}
		
		return mesg;
	}
	/**
	 * 检索条件封装工具类
	 * 2016年4月8日
	 * by 李志伟
	 * @param so
	 * @param map
	 * @return
	 */
	public static void spiltUtils(SearchObject so, Map<String, Object> map){
		
		// 客户编号
		if(so.getCustomerCode() != null && !so.getCustomerCode().equals("")){
			List<String> custCode = Arrays.asList(so.getCustomerCode().split(","));
			map.put("custCode", custCode);
		}
		
		// 客户姓名
		if(so.getCustomerName() != null && !so.getCustomerName().equals("")){
			List<String> custName = Arrays.asList(so.getCustomerName().split(","));
			map.put("custName", custName);
		}
		
		//出借编号
		if(so.getLendCode() != null && !so.getLendCode().equals("")){
			List<String> lenCode = Arrays.asList(so.getLendCode().split(","));
			map.put("lenCode", lenCode);
		}
		
		
		// 营业部
		if(so.getOrgId() !=null && !so.getOrgId().equals("")){
			List<String> orgId = Arrays.asList(so.getOrgId().split(","));
			map.put("orgId", orgId);
		}
		
		// 账单日
		if(so.getApplyBillday() !=null && !so.getApplyBillday().equals("")){
			String[] str = so.getApplyBillday().split(",");
			List<Integer> aBd = new ArrayList<Integer>();
			for (int i = 0; i < str.length; i++) {
				aBd.add(Integer.valueOf(str[i]));
			}
			map.put("aBd", aBd);
		}
		
		// 付款方式
		if(so.getApplyPay() !=null && !so.getApplyPay().equals("")){
			List<String> pay = Arrays.asList(so.getApplyPay().split(","));
			map.put("pay", pay);
		}
		
		// 出借产品
		if(so.getProductCode() !=null && !so.getProductCode().equals("")){
			List<String> prod = Arrays.asList(so.getProductCode().split(","));
			map.put("prod", prod);
		}
		
		// 回息状态
		if(so.getBackMoneyStatus() !=null && !so.getBackMoneyStatus().equals("")){
			List<String> status = Arrays.asList(so.getBackMoneyStatus().split(","));
			map.put("status", status);
		}
		
		// 回息平台
		if(so.getPlatformCode() !=null && !so.getPlatformCode().equals("")){
			List<String> plat = Arrays.asList(so.getPlatformCode().split(","));
			map.put("plat", plat);
		}
		
		// 卡或折
		if(so.getDictValue() !=null && !so.getDictValue().equals("")){
			List<String> cardOrBooklet = Arrays.asList(so.getDictValue().split(","));
			map.put("cardOrBooklet", cardOrBooklet);
		}
		
		// 开户行
		if(so.getAccountBank() != null && !so.getAccountBank().equals("")){
			List<String> accBank = Arrays.asList(so.getAccountBank().split(","));
			map.put("accBank", accBank);
		}
		
		// 平台标识
		if(so.getPlatFlag() != null && !so.getPlatFlag().equals("")){
			List<String> platFlag = Arrays.asList(so.getPlatFlag().split(","));
			map.put("platFlag", platFlag);
		}
		
		// 合同版本号
		if(so.getApplyAgreementEdition() != null && !so.getApplyAgreementEdition().equals("")){
			List<String> edition  = Arrays.asList(so.getApplyAgreementEdition().split(","));
			map.put("edition", edition);
		}
		
		// 回盘结果
		if(null != so.getBackResult() && !("").equals(so.getBackResult())){
			List<String> result = Arrays.asList(so.getBackResult().split(","));
			map.put("result", result);
		}
		// 是否回息  gaoxu  2017-3-23 13:20:13
		if(null != so.getIsInterest() && !("").equals(so.getIsInterest())){
			List<String> IsInterest = Arrays.asList(so.getIsInterest().split(","));
			map.put("isInterest", IsInterest);
		}
	}
	
	/**
	 * 当前页面总金额计算
	 * 2016年4月8日
	 * by 李志伟
	 * @param list
	 * @return
	 */
	public static String totalMoney(List<BackInterestListView> list){
		
		BigDecimal bd = new BigDecimal(0.0);
		for (BackInterestListView bp : list) {
			if(bp.getBackRealMoney() != null && !(bp.getBackRealMoney().toString()).equals("")){
				BigDecimal dec = bp.getBackRealMoney().setScale(2, BigDecimal.ROUND_HALF_UP);
				bd = bd.add(dec);
			}
		}
		String str = bd.toString();
		return str;
	}
	
	/**
	 * 生成历史留痕
	 * 2016年4月9日
	 * by 李志伟
	 * @param userId  用户ID
	 * @param userName 用户名
	 * @param lendCode	出借编号
	 * @param type	操作类型
	 * @param node	系统模块节点
	 * @param dataCode	数据ID
	 * @param detail	操作内容
	 * @return
	 */
	public static Check madeHistory(String userId, String userName, String lendCode, String type, String node, String dataCode, String detail){
		
		Date date = new Date();
		Check check = new Check();
		check.setId(IdGen.uuid());
		check.setCreateBy(userId);
		check.setCreateTime(date);
		check.setOperateAffiliated(dataCode);
		check.setOperateNode(node);
		check.setApplyCode(lendCode);
		check.setOperator(userName);
		check.setOperateInfo(detail);
		check.setOperateType(type);
		check.setOperateTime(date);
		return check;
	}
	
	/**
	 * 生成审批信息
	 * 2016年4月9日
	 * by 李志伟
	 * @param bip
	 * @param returnStatus 退回状态
	 * @param submitStatus 提交状态
	 * @return
	 */
	public static BackInterestLog madeApproval(BackInterestPool bip, String returnStatus, String submitStatus){
		
		String type = "";
		String state = "";
		String checkExamine = "";
		if(bip.getCheckExaminetype().equals(YoN.SHI.value)){// 审批通过
			
			type = GlobalConstant.COMMIT;
			state = submitStatus;
		}else{// 审批不通过
			
			type = GlobalConstant.RETURN;
			state = returnStatus;
			if(null != bip.getTextAre() && !("").equals(bip.getTextAre())){
				checkExamine = bip.getTextAre();
			}else{
				checkExamine = BacksmsReason.getBacksmsReason(bip.getCheckExamine());
			}
		}
		BackInterestLog bil = ApprovalMesgUtil.generateMes(state, bip.getCheckExaminetype(), checkExamine);
		bil.setType(type);
		return bil;
	}
	
	/**
	 * 获取导出文件类型
	 * 2016年4月21日
	 * by 李志伟
	 * @return
	 */
	public static String getType(String exportType){
		if(exportType.equals("0")){
			return ".xlsx";
		}else{
			return ".txt";
		}
	}
	
	/**
	 * 获取条件
	 * 2016年4月9日
	 * by 李志伟
	 * @param map
	 */
	public static void getCondition(SearchObject so, Map<String, Object> map, List<String> status){
		
		if(null != so.getBackiId() && !("").equals(so.getBackiId())){
			List<String> codes = Arrays.asList(so.getBackiId().split(","));
			map.put("codes", codes);
		}else{
			StaticMethodUtil.spiltUtils(so, map);
			// 设置查询数据的默认值
			if(null == so.getBackMoneyStatus() || ("").equals(so.getBackMoneyStatus())){
				map.put("status", status);
			}
		}
		map.put("process", BackResult.DELLING.value);
		
		
		
		
		map.put("so", so);
	}
	
	/**
	 * 获取付款方式
	 * 2016年4月14日
	 * by 李志伟
	 * @param flag
	 * @return
	 */
	public static List<String> getPayType(String flag){
		
		List<String> payType = new ArrayList<String>();
		// 判断是否页面上是需要金账户数据还是非金账户数据
		if(flag.equals(YoN.FOU.value)){
			
			payType.add(PayMent.CGBFNZ.value);
			payType.add(PayMent.HK.value);
			payType.add(PayMent.NBZZHHK.value);
			payType.add(PayMent.NBZZ.value);
			payType.add(PayMent.SHNZ.value);
			
		}else{
			payType.add(PayMent.ZJTG.value);
		}
		return payType;
	}
	
	/**
	 * 获取待回息申请列表数据初始化默认条件
	 * 2016年4月11日
	 * by 李志伟
	 * @return
	 */
	public static List<String> getBackInterestApplyStatus(){
		
		List<String> status = new ArrayList<String>();
		status.add(BacksmsState.DHXSQ.value);
		status.add(BacksmsState.DHXSQQRTH.value);
		return status;
	}
	
	/**
	 * 获取待回息申请确认列表中默认值回息状态
	 * 2016年4月9日
	 * by 李志伟
	 * @return
	 */
	public static List<String> getBackInterestApplyConfrimStatus(){
		
		List<String> status = new ArrayList<String>();
		status.add(BacksmsState.DHXSQQR.value);
		status.add(BacksmsState.DHXSPTH.value);
		status.add(BacksmsState.DHXTH.value);
		status.add(BacksmsState.DHXQRTH.value);
		status.add(BacksmsState.YHXTH.value);
		return status;
	}
	
	/**
	 * 获取待回息审批回息状态
	 * 2016年4月11日
	 * by 李志伟
	 * @return
	 */
	public static List<String> getBackInterestApprovalStatus() {
		List<String> status = new ArrayList<String>();
		status.add(BacksmsState.DHXSP.value);
		return status;
	}
	

	/**
	 * 获取待回息列表中默认值回息状态
	 * 2016年4月9日
	 * by 李志伟
	 * @return
	 */
	public static List<String> getExcuteBackInterestStatus(){
		
		List<String> status = new ArrayList<String>();
		status.add(BacksmsState.DHX.value);
		status.add(BacksmsState.HXSB.value);
		return status;
	}
	
	/**
	 * 回息确认列表默认值
	 * 2016年4月11日
	 * by 李志伟
	 * @return
	 */
	public static List<String> getBackInterestConfrimStatus() {
		List<String> status = new ArrayList<String>();
		status.add(BacksmsState.DHXQR.value);
		return status;
	}
	
	/**
	 * 已回息列表初始化条件
	 * 2016年4月11日
	 * by 李志伟
	 * @return
	 */
	public static List<String> getFinishBackInterestStatus() {
		List<String> status = new ArrayList<String>();
		status.add(BacksmsState.YHX.value);
		return status;
	}
	
	/**
	 * 设置下一期回息日期
	 * 2016年4月29日
	 * by 李志伟
	 * @param bbi
	 * @param la
	 * @return
	 */
	public static LoanApply setNextBackInterestDay(BackInterestPool bbi, LoanApply la){
		
		LoanApply loap = new LoanApply();
		 // 下面的就是到期日期减去7个月
	    Calendar cal = Calendar.getInstance();
		cal.setTime(la.getExpireDate());
		cal.add(Calendar.MONTH, -7);
		
		// 本期到期日
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(bbi.getMatchingExpireDay());
		
		// 比较日期大小
		SimpleDateFormat sf = new SimpleDateFormat(GlobalConstant.YYYY_MM_DD);
		try {
			int i = sf.parse(sf.format(cal.getTime())).compareTo(sf.parse(sf.format(cal1.getTime())));
			if(i == GlobalConstant.GR || i==GlobalConstant.EQ){// 本期到期日<到期日期-7个月，更新出借申请表的本期回息状态=待回息，本期回息日期=本期回息日期+6个月
				
				// 本期回息日期增加6个月
				Calendar cad = Calendar.getInstance();
				cad.setTime(la.getMatchingBackinterestDay());
				cad.add(Calendar.MONTH, 6);
				
				// 下期回息日期
				Date time = cad.getTime();
				loap.setMatchingBackinterestDay(time);
				loap.setMatchingBackinterestStatus(BacksmsState.DHX.value);
				loap.setApplyCode(bbi.getLendCode());
				loap.preUpdate();
			}else{
				loap = null;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("日期转换有误", e);
			logger.debug("日期转换失败" ,e);
		}
		return loap;
	}
	
	/**
	 * 字符串类型日期转DATE类型日期
	 * 2016年5月5日
	 * by 李志伟
	 * @param date
	 * @param format 需要格式化的格式
	 * @return
	 * @throws ParseException 
	 */
	public static Date parseDay(String date, String format){
		
		SimpleDateFormat sf = new SimpleDateFormat(format);
		Date parse = null;
		try {
			parse = sf.parse(date);
		} catch (ParseException e) {
			logger.error("日期转换失败", e);
			e.printStackTrace();
		}
		return parse;
	}
	
	/**
	 * 日期转换成字符串
	 * 2016年6月6日
	 * by 李志伟
	 * @param date
	 * @param format
	 * @return
	 */
	public static String parseDate(Date date, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String mat = sdf.format(date);
		return mat;
	}
	
	/**
	 * 需要回息的产品
	 */
	public static List<Product> getProductList(){
		
		List<Product> list = new ArrayList<Product>();
		Product xhb = new Product();
		xhb.setProductCode(ProductCode.XHB.value);
		xhb.setProductName("信和宝");
		list.add(xhb);
		
		Product yxt = new Product();
		yxt.setProductCode(ProductCode.YXT.value);
		yxt.setProductName("月息通");
		list.add(yxt);
		
		Product xhba = new Product();
		xhba.setProductCode(ProductCode.XHBA.value);
		xhba.setProductName("信和宝A");
		list.add(xhba);
		
		Product xhbc = new Product();
		xhbc.setProductCode(ProductCode.XHBC.value);
		xhbc.setProductName("信和宝C");
		list.add(xhbc);
		
		Product xhyz = new Product();
		xhyz.setProductCode(ProductCode.XHYZ.value);
		xhyz.setProductName("信和月增");
		list.add(xhyz);
		return list;
	}
	
	/**
	 * 线上回息非金账户回息信息验证
	 * 2016年6月7日
	 * by 李志伟
	 * @param eb
	 * @return
	 */
	public static String validates(LineBackInterestObj eb){
		if (BackInterestPlat.TL.value.equals(eb.getPlatformId())) {
			// 通联平台回款时需判断
			if (GlobalConstant.TL_Amount.compareTo(eb.getBackRealMoney()) < 0) {

				// 回款金额大于100W时，不处理
				return "出借【" + eb.getLendCode()
						+ "】的回息金额大于300万，请选择其他回息平台进行操作。<br/>";
			}
			if (OpenBank.GFYH.value.equals(eb.getAccountBank())) {
				// 客户收款行为广发银行时，不处理
				return "出借【" + eb.getLendCode()
						+ "】的收款银行为广发银行，目前通联平台不支持该银行回息。<br/>";
			}
		}else if(BackInterestPlat.KL.value.equals(eb.getPlatformId())){
			// 卡联平台回息是需判断
			if(GlobalConstant.KA_LIAN_LIMIT.compareTo(eb.getBackRealMoney()) < 0){
				// 回息金额大于20W，不处理
				return "出借{"+eb.getLendCode()+"}的回息金额大于20万，请选择其他回息平台进行操作。<br/>";
			}
		}
		return "";
	}
}