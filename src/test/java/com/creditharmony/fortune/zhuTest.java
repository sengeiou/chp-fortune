package com.creditharmony.fortune;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.creditharmony.fortune.customer.entity.LoanApply;

public class zhuTest {
	@Test
	public void test() {
		double ss=1461237768.99089;
		Date d=new Date((long)ss*1000);
		String s="【信和财富】尊敬的{#Name#}您好，现确认您本期出借的{#custom_text_4#}元已划扣成功，感谢您的配合！祝您生活愉快！详询4000901199";
		s=s.replace("{#Name#}", "你好").replace("{#custom_text_4#}", "123");
		//finance.preInsert();
		/*String tt=this.makePin();
		String tt2=this.makePin();*/
		String r="";
	}
	@Test
	public void makePin(){
		LoanApply lendApply = new LoanApply();
		LoanApply lendApply1 = new LoanApply();
		lendApply.setApplyDate(new Date());
		lendApply.setLendMoney(new BigDecimal("1"));
		BeanUtils.copyProperties(lendApply, lendApply1);
		String tt="1";
	}

}
