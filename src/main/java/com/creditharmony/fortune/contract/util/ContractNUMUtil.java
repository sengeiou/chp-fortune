package com.creditharmony.fortune.contract.util;

import java.util.ArrayList;
import java.util.List;

import com.creditharmony.fortune.contract.constant.Constant;

/**
 * 合同编号工具类
 * 2015年12月29日
 * @author 王鹏飞
 *
 */
public class ContractNUMUtil {
	
	/**
	 * 根据合同起始编号和合同终止编号生成两者之间的区间合同编号
	 * 规则：12位，88 10 4444 00 88 ,00变 ，以1逐次递增
	 * @param startNo 合同起始编号
	 * @param endNo 合同终止编号
	 * @return List<String> 合同编码集合
	 */
	public static List<String> range(String startNo,String endNo){
		
		if(startNo == null || endNo==null)
			return null;
		
		startNo = startNo.trim(); 
		endNo = endNo.trim();		
		boolean check = (startNo.length() != Constant.length || endNo.length() != Constant.length
				|| !startNo.startsWith(Constant.prefix) || !startNo.endsWith(Constant.suffix)
				|| !endNo.startsWith(Constant.prefix) || !endNo.endsWith(Constant.suffix)) ? true
				: false;
		if (check) {
			return null;
		}
		//截取自增区间字符串
		String incrStart = startNo.substring(Constant.increment_index,
				Constant.increment_last_index);
		String incrEnd = endNo.substring(Constant.increment_index,
				Constant.increment_last_index);
		//将区间字符串转换为整型
		try {
			long longIncreStart = Long.valueOf(incrStart);
			long longIncreEnd = Long.valueOf(incrEnd);
			if(longIncreStart>longIncreEnd){
				return null;
			}
			List<String> list = new ArrayList<String>();
			for(long i = longIncreStart;i<=longIncreEnd;i++){
				list.add(generateContractNO(i,startNo));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 产生合同编号
	 * @param i 自增整型
	 * @return 产生合同编号
	 */
	public static String generateContractNO(long num,String startNo){
		String str_num = String.valueOf(num);
		StringBuffer bf = new StringBuffer(Constant.prefix);
		bf.append(startNo.substring(Constant.prefix.length(), Constant.increment_index));
		int l = Constant.increment_last_index-Constant.increment_index-String.valueOf(num).length();
		for(int i = 1;i<=l;i++){
			bf.append(Constant.placeholder);
		}
		bf.append(str_num);
		bf.append(Constant.suffix);
		return bf.toString();
	}

	
	public static void main(String[] args){
		List<String> list = ContractNUMUtil.range("881044440188", "881044441288");
		for (String num : list) {
			System.out.println(num);
		}
	}
	
	
	
}
