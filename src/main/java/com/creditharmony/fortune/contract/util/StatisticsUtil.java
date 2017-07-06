package com.creditharmony.fortune.contract.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.fortune.contract.constant.Constant;

/**
 * 合同统计工具类
 * 2016年01月14日
 * @author 王鹏飞
 *
 */
public class StatisticsUtil {
	
	/**
	 * 作废率 = 检索时间审批通过的作废合同量 / 检索时间的有效使用量 ,
	 * 当作废量 >0 并且有效使用量 =0 时；作废量设置为1
	 * @param uselessNum
	 *            作废量
	 * @param validNum
	 *            有效使用量
	 * @return
	 */
	public static BigDecimal getUselessRate(int uselessNum, int validNum) {
		float a = (float)uselessNum;
		float b = (float)validNum;
		a = a > Constant.NUM_ZERO && b == Constant.NUM_ZERO ? Constant.NUM_ONE : a;
		b = a > Constant.NUM_ZERO && b == Constant.NUM_ZERO ? Constant.NUM_ONE : b;
		if(b==0){
			
			return new BigDecimal(0);
		}
		
		BigDecimal   b1 =   new BigDecimal(a);
		BigDecimal   b2  =   new BigDecimal(b); 
		return  b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 扣款金额 = 作废量金额+遗失量金额
	 * 作废量金额 作废率 >= 5% 时 20*作废量 
	 * 遗失量金额：遗失量*200
	 * 
	 * @param uselessRate
	 *            作废率
	 * @param lossNum
	 *            遗失量
	 * @return
	 */
	public static BigDecimal getCutPayment(float uselessRate,int uselessNum, int lossNum) {
		float uselessPrice = uselessRate >= Constant.USELESS_LOWEST_POINT?Constant.USELESS_PRICE*uselessNum:0;
		float lossPrice = lossNum * Constant.LOSS_PRICE;
		return BigDecimal.valueOf(uselessPrice+lossPrice).setScale(2,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 时时进度=非库存状态/全部派发+转入-转出
	 * 
	 * @param distrNum
	 *            非库存状态合同数量 = 派发数量 - 库存数量
	 * @param storeNum
	 *            库存合同数量
	 * @param inNum
	 *            转让合同数量
	 * @param outNum
	 *            转出合同数量
	 * @return
	 */
	public static String getProgress(int distrNum, int storeNum, int inNum,
			int outNum) {
		float a = (float)(distrNum-storeNum);
		float b = (float)(distrNum + inNum - outNum);
		float progress = a/b;
		if(a == b){
			return "100%";
		}
		return  BigDecimal.valueOf(progress*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%";
	}
	
	/**
	 * 获取文件下载名称
	 * 文件命名规则  前缀+时间戳+文件格式后缀
	 * @param preffix 文件前缀
	 * @return
	 */
	public static String getDownloadFileName(String preffix){
		 StringBuffer br = new StringBuffer(preffix);
		 br.append(DateUtils.getDate(Constant.DATE_YYYYMMDDMMSS.toString())).append(Constant.CONTRACT_EXCEL_SUFFIX);
		 return br.toString();
	}
	
	/**
	 * 格式化时间
	 * 2016年4月22日
	 * By 郭才林
	 * @param a
	 * @return
	 */
	public static String getDate(String a){
		
		if(StringUtils.isEmpty(a)){
			return "";
		}
		  String date="";
	      SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);//MMM dd hh:mm:ss Z yyyy
	      SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	     
	      try {
			Date d=sdf.parse(a);
			date=sd.format(d);
		} catch (Exception e) {
		}
	       return date;
	}
	
	
	public static void main(String[] args){
		StatisticsUtil.getProgress(80, 78, 0, 0);
		System.out.println(StatisticsUtil.getUselessRate(12, 0));
//		StatisticsUtil.getProgress(80, 0, 0, 0);
//		StatisticsUtil.getProgress(80, 80, 0, 0);
		StatisticsUtil.getDownloadFileName("ddd");
	}
	
	
}
