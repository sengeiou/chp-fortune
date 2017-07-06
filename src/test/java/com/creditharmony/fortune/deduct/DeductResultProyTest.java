/**
 * @Probject Name: trunk_chp-fortune
 * @Path: com.creditharmony.fortune.deductDeductResultProyTest.java
 * @Create By 韩龙
 * @Create In 2016年3月29日 下午1:57:05
 */
package com.creditharmony.fortune.deduct;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditharmony.core.deduct.bean.out.FortuneDeductResult;
import com.creditharmony.core.deduct.type.DeductWays;
import com.creditharmony.fortune.deduct.proxy.DeductResultProxy;

/**
 * @Class Name DeductResultProyTest
 * @author 韩龙
 * @Create In 2016年3月29日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-context.xml","classpath:test-mvc.xml","classpath:test-core.xml","classpath:applicationContext-ruleManage.xml"})
public class DeductResultProyTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private DeductResultProxy drp;
	
//	@Autowired
//	private DeductManager deductManager;
	
	@Test
	public void test(){
		FortuneDeductResult de = new FortuneDeductResult();
		double dd = 50000;
		de.setConfirmOpinion("成功");
		de.setDeductFailMoney("0");
		de.setDeductResultCode("0000");
		de.setDeductSucceedMoney(String.valueOf(dd));
		de.setDeductSysIdType(DeductWays.CF_01.getCode());
		de.setLendCode("85100000000632218-015");
		de.setUnDeductMoney("0");
		de.setDeductTime("2016-03-29 14:08:12");
		//drp.execute(de);
	}

	
	public static void main(String[] args) {
		
	}
}
