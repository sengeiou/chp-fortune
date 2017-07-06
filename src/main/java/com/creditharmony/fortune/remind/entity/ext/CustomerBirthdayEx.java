package com.creditharmony.fortune.remind.entity.ext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 客户生日提醒
 * 
 * @Class Name CustomerBirthdayEx
 * @author 韩龙
 * @Create In 2015年11月27日
 */
public class CustomerBirthdayEx extends DataEntity<CustomerBirthdayEx> {

	private static final long serialVersionUID = 5543975725691249838L;
	// 客户编号
	private String custCode;
	// 客户名称
	private String custName;
	// 客户真实姓名
	private String custRealname;
	// 客户移动号码
	private String custMobilephone;
	// 客户固定电话
	private String custPhone;
	// 证件号码
	private String custCertNum;
	// 出生日期
	private String custBirthday;
	// 客户年龄
	private String custAge;
	// 离下次过生日的天数
	private String lastBirthdayDay;
	// 客户性别字典码
	private String dictCustSex;
	// 理财经理code
	private String managerCode;

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustRealname() {
		return custRealname;
	}

	public void setCustRealname(String custRealname) {
		this.custRealname = custRealname;
	}

	public String getCustMobilephone() {
		return custMobilephone;
	}

	public void setCustMobilephone(String custMobilephone) {
		this.custMobilephone = custMobilephone;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public String getCustCertNum() {
		return custCertNum;
	}

	public void setCustCertNum(String custCertNum) {
		this.custCertNum = custCertNum;
	}

	public String getCustBirthday() {
		return custBirthday;
	}

	public void setCustBirthday(String custBirthday) {
		this.custBirthday = custBirthday;
	}

	public String getCustAge() {
		return custAge;
	}

	public void setCustAge(String custAge) {
		this.custAge = custAge;
	}

	public String getLastBirthdayDay() {
//		// 计算客户生日的天数
//		if (custBirthday != null) {
//			try {
//				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//				Calendar cal = Calendar.getInstance();
//				int yearNow = cal.get(Calendar.YEAR);// 年
//				// 截取客户出生月日
//				String thisbirthdaycahr = yearNow + "-"
//						+ custBirthday.substring(5);
//				// 转换成日期
//				Date thisbirthdaydate = null;
//				thisbirthdaydate = formatter.parse(thisbirthdaycahr);
//				Date date = formatter.parse(formatter.format(new Date()));
//				//日期比较
//				if (thisbirthdaydate.before(date)) {
//					Long day=(formatter.parse((yearNow+1) + "-"+ custBirthday.substring(5)).getTime()-date.getTime())/(24*60*60*1000);
// 					this.lastBirthdayDay= day+"";
//				} else {
//					Long day=(formatter.parse(yearNow + "-"+ custBirthday.substring(5)).getTime()-date.getTime())/(24*60*60*1000);
//					this.lastBirthdayDay= day+"";
//				}
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		return lastBirthdayDay;
	}

	public void setLastBirthdayDay(String lastBirthdayDay) {
		this.lastBirthdayDay = lastBirthdayDay;
	}

	public String getDictCustSex() {
		return dictCustSex;
	}

	public void setDictCustSex(String dictCustSex) {
		this.dictCustSex = dictCustSex;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

}