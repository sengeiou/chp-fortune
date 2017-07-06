package com.creditharmony.fortune.tripleTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditharmony.fortune.triple.system.service.TripleChangePhoneService;
import com.creditharmony.fortune.triple.system.service.TripleFirstOrderService;
import com.creditharmony.fortune.triple.system.service.TripleInvestSuccService;
import com.creditharmony.fortune.triple.system.service.TripleNewCustomerService;

/**
 * @Class Name tripleTest
 * @author 胡体勇
 * @Create In 2016年4月6日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring-context*.xml", "classpath:applicationContext-ruleManage.xml", "classpath*:/spring-adapter-server.xml", "classpath*:/spring-mvc*.xml" })
public class TripleTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	private TripleNewCustomerService tripleNewCustomerService;
	@Autowired
	private TripleChangePhoneService tripleChangePhoneService;
	@Autowired
	private TripleFirstOrderService tripleFirstOrderService;
	@Autowired
	private TripleInvestSuccService tripleInvestSuccService;
	
//	@Test
//	public void test(){
//		System.out.println("aaa");
//	}
	@Test
	public void testInserInvest(){
		tripleInvestSuccService.investSucc("K200210135874", "82A100035788-004", "A");
	}

}
