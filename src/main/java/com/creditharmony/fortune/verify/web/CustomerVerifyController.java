package com.creditharmony.fortune.verify.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.fortune.type.MailTemplateType;
import com.creditharmony.core.fortune.type.SmsType;
import com.creditharmony.core.fortune.type.VerifyPinType;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.service.CustomerManager;
import com.creditharmony.fortune.mail.entity.EmailTemplate;
import com.creditharmony.fortune.mail.manager.EmailInfoManager;
import com.creditharmony.fortune.sms.manager.SmsManager;
import com.creditharmony.fortune.utils.MailUtil;
import com.creditharmony.fortune.utils.SmsUtil;
import com.creditharmony.fortune.verify.manager.CustomerVerifyManager;

/**
 * 验证码校验
 * @Class Name CustomerVerifyController
 * @author 朱杰
 * @Create In 2016年3月6日
 */
@Controller
@RequestMapping(value = "${adminPath}/verify")
public class CustomerVerifyController extends BaseController {
	
	@Autowired
	private SmsManager smsManager;
	@Autowired
	private CustomerManager customerManager;
	@Autowired
	private CustomerVerifyManager customerVerifyManager;
	@Autowired
	private EmailInfoManager emailInfoManager;

	/**
	 * 发送验证
	 * 2016年3月6日
	 * By 朱杰
	 * @param verifyPinType 验证码类型
	 * @param customerId 客户ID
	 * @param value 验证内容
	 * @return
	 */
	@RequestMapping(value = { "sendVerify" })
	@ResponseBody
	public String sendVerify( String verifyPinType, String customerId,String value) {
		
		try {
			
			if(StringUtils.isBlank(value) || StringUtils.isBlank(customerId)){
				return jsonMapper.toJson("操作失败");
			}
			Customer cust=new Customer();
			cust.setCustCode(customerId);
			Customer customer = customerManager.getCustomer(cust);
			if(customer == null){
				//查询不到客户信息
				return jsonMapper.toJson("操作失败");
			}
			if(VerifyPinType.SMS.value.equals(verifyPinType)){
				
				//手机验证
				String pin = this.makePin();
				//发送验证码
				String content = smsManager.getSmsTemplate(SmsType.SMS_VERIFY.value).getTemplateContent();
				content=content.replace("{#pin#}", pin);
				SmsUtil.sendSms(value, content);
				
				//更新验证码
				customerVerifyManager.updateVerifyInfo(verifyPinType, customerId, pin);
				
			}else if(VerifyPinType.EMAIL.value.equals(verifyPinType)){
				//邮箱验证
				String pin = this.makePin();
				//发送验证码
				//邮箱模板内容获取
				EmailTemplate tenmplate = emailInfoManager.getEmailTemplateByType(MailTemplateType.YZ.value);
				String content = tenmplate.getTemplateContent();
				content=content.replace("{#pin#}", pin);
				MailUtil.sendMail(value, null, "邮箱验证", content);
				//更新验证码
				customerVerifyManager.updateVerifyInfo(verifyPinType, customerId, pin);
			}else{
				jsonMapper.toJson("操作失败");
			}
			
		} catch (Exception e) {
			
			return jsonMapper.toJson("操作失败");
		}
		
		return jsonMapper.toJson("");
	}

	/**
	 * 生成验证码
	 * 2016年3月6日
	 * By 朱杰
	 * @return
	 */
	private String makePin(){
		
		return customerVerifyManager.makePin();
	}
	
	/**
	 * 验证码对比
	 * 
	 * 2016年3月6日
	 * By 朱杰
	 * @param verifyPinType 验证码类型
	 * @param customerId 客户id
	 * @param pin 验证码
	 */
	@RequestMapping({ "checkPin" })
	@ResponseBody
	public String checkPin(String verifyPinType, String customerId, String pin){
		   return jsonMapper.toJson(customerVerifyManager.checkPin( verifyPinType, customerId, pin));
		
	}
}