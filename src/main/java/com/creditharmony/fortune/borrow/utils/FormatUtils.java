package com.creditharmony.fortune.borrow.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.dict.util.DictUtils;
import com.creditharmony.fortune.template.entity.BorrowImportExcel;


/**
 * 格式化的utils
 * @Class Name LoanIdcardUtils
 * @author 周俊
 * @Create In 2015年12月29日
 */
public class FormatUtils {
	
	/**
	 * 重写取字典
	 * 2016年6月25日
	 * By 周俊
	 * @param model
	 * @param types
	 */
	public static void addDicts(Model model,String[] types) {
		Map<String, List<Dict>> map = new HashMap<String, List<Dict>>();
		for (String type : types) {
			List<Dict> dictList = DictUtils.getDictList(type);
			if (type.equals("jk_prof_type")) {
				Dict dict = new Dict();
				dict.setValue("9");
				dict.setLabel("抵押车");
				dict.setType(type);
				dictList.add(dict);
			}
			map.put(type,dictList);
		}
		model.addAttribute("dicts",map);
	}
	
	
	
	/**
	 * 格式化身份证号
	 * 2015年12月29日
	 * By 周俊
	 * @param loanIdcard
	 * @return
	 */
	public static String formatLoanIdcard(String loanIdcard){
		String item = "";
		for (int i = 0; i < loanIdcard.length(); i++) {
			if (i<=1||i>loanIdcard.length()-5) {
				item = item+ loanIdcard.charAt(i);
			}else {
				item = item + "*";
			}
		}
		return item;
	}
	
	/**
	 * 手机账号
	 * 2016年4月25日
	 * By 韩龙
	 * @param phoneNo
	 * @return
	 */
	public static String formatPhone(String phoneNo){
		String item = "";
		for(int i = 0;i<phoneNo.length();i++){
			if(i<=2 || i>phoneNo.length()-5){
				item = item+ phoneNo.charAt(i);
			}else{
				item = item + "*";
			}
		}
		return item;
	}
	
	/**
	 * 格式化小数
	 * 2016年1月22日
	 * By 周俊
	 * @param number
	 * @return
	 */
	public static String formatNumber(String number){
		return com.creditharmony.fortune.utils.FormatUtils.getFormatNumber(BigDecimal.valueOf(StringUtils.toDouble(number)),"0.00");
	}
	
	/**
	 * 格式化小数,如果尾数都是0保留一位0,否则去掉所有的0
	 * 2016年1月22日
	 * By 周俊
	 * @param number
	 * @return
	 */
	public static BigDecimal formatNumber(BigDecimal number){
		Double doubleNum = StringUtils.toDouble(number);
		return BigDecimal.valueOf(doubleNum);
	}
	
	/**
	 * 格式化小数,如果尾数都是0保留一位0,否则去掉所有的0
	 * 2016年1月22日
	 * By 周俊
	 * @param number
	 * @return
	 */
	public static String formatNumberRate(String number){
		Double doubleNum = StringUtils.toDouble(number);
		return String.valueOf(doubleNum);
	}
	/**
	 * 返回消息的模板
	 * 2016年4月23日
	 * By 周俊
	 * @param loanName
	 * @param loanIdcard
	 * @param modifyTime
	 * @param modifyName
	 * @return
	 */
	public static String messageTemplates(String loanName,String loanIdcard,Date modifyTime,String modifyName){
		if (StringUtils.isNotBlank(loanName)&& StringUtils.isNotBlank(loanIdcard)&&!ObjectHelper.isEmpty(modifyTime)&&StringUtils.isNotBlank(modifyName)) {
			return "身份证号" + formatLoanIdcard(loanIdcard) + "的借款人" + loanName
					+ "在"
					+ DateUtils.formatDate(modifyTime, "yyyy-MM-dd HH:mm:dd ")
					+ "被" + modifyName + "修改\r\n";
		}else if (!ObjectHelper.isEmpty(modifyTime)&&StringUtils.isNotBlank(modifyName)) {
			return "数据在"+ DateUtils.formatDate(modifyTime, "yyyy-MM-dd HH:mm:dd ")
					+ "被" + modifyName + "修改\r\n";
		}else{
			return "身份证号" + formatLoanIdcard(loanIdcard) + "的借款人" + loanName+"被修改,请重新刷新\r\n";
		}
	}
	
	/**
	 * 返回消息的模板
	 * 2016年4月23日
	 * By 周俊
	 * @param loanName
	 * @param loanIdcard
	 * @param modifyTime
	 * @param modifyName
	 * @return
	 */
	public static String messageTemplates(String loanName,String loanIdcard){
			return "获取身份证号" + formatLoanIdcard(loanIdcard) + "的借款人" + loanName+"债权信息失败,请核对后提交\r\n";
	}
	
	/**
	 * 检查对象的属性是否赋值
	 * 2016年5月18日
	 * By 周俊
	 * @param object
	 * @return
	 */
	public static boolean checkObject(Object object){
		try {
			Field[] fields = BorrowImportExcel.class.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
					if (field.getType().equals(String.class) && !"serialVersionUID".equals(field.getName())) {
						String property = (String) field.get(object);
						if (StringUtils.isNotBlank(property)) {
							return true;
						}
					}else {
						if (!"serialVersionUID".equals(field.getName())) {
							Object property =  field.get(object);
							if (!ObjectHelper.isEmpty(property)) {
								return true;
							}
						}
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}