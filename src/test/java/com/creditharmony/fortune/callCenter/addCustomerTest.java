package com.creditharmony.fortune.callCenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditharmony.adapter.service.csh.bean.Csh_SaveCustomerByCFInBean;
import com.creditharmony.fortune.callcenter.web.CallCenterCustomerAddWS;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-context.xml"})

public class addCustomerTest {
	
	@Autowired
	private CallCenterCustomerAddWS customerWS;
	
	@Test
	public void testAddCustomer(){
				
		Csh_SaveCustomerByCFInBean customer = new Csh_SaveCustomerByCFInBean();
		customer.setCustomerName("TestName");
		customer.setCustomerSex("男");
		customer.setCustomerMobilePhone("13566669999");
		customer.setCustomerPhone("010-666999");
		customer.setCustomerLiveProvince("北京");
		customer.setCustomerLiveCity("海淀区");
		
				
		customerWS.doExec(customer);
	}
	

}
