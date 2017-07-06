package com.creditharmony.fortune.customer.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.common.util.IdcardUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.service.AdvisoryManager;
import com.creditharmony.fortune.customer.service.ChangeTracesManager;
import com.creditharmony.fortune.customer.service.CustomerManager;
import com.creditharmony.fortune.customer.service.LoanApplyManager;
import com.creditharmony.fortune.customer.view.CustomerView;
import com.creditharmony.fortune.verify.manager.CustomerVerifyManager;

/**
 * 客户管理类
 * 
 * @Class Name CustomerController
 * @author 孙凯文
 * @Create In 2015年12月2日
 */
@Controller
@RequestMapping("${adminPath}/customer/customerAidController")
public class CustomerAidController extends BaseController {

	@Autowired
	private CustomerManager customerService;
	@Autowired
	private AdvisoryManager advisorService;
	@Autowired
	private LoanApplyManager loanApplyManager;
	@Autowired
	private ChangeTracesManager changeTracesManager;
	@Autowired
	private CustomerVerifyManager customerVerifyManager;
	
	/**
	 * 检验手机号码是否存在 2016年1月19日
	 * 
	 * @author 孙凯文
	 * @param phone
	 * @return
	 */
	@RequestMapping("ajaxPhoneExsit")
	@ResponseBody
	public String ajaxPhoneExsit(String phone) {
		Customer customer = new Customer();
		customer.setCustMobilephone(phone);
		customer = customerService.getCustomer(customer);
		Map<String, String> map = new HashMap<String, String>();
		if (null != customer) {
			map.put("result", BooleanType.FALSE);
			map.put("message", "手机号[" + phone + "]已经存在");
		} else {
			map.put("result", BooleanType.TRUE);
		}
		return jsonMapper.toJson(map);
	}

	/**
	 * 校验手机号码
	 * 2016年3月9日
	 * By 刘雄武
	 * @param recaddView
	 * @return
	 */
	@ResponseBody
	@RequestMapping("check")
	public String check(@ModelAttribute CustomerView recaddView) {
		String results = customerService.checkCustomer(recaddView);
		// return "redirect:" + adminPath + "/customer/investment";
		return results;
	}
	
	/**
	 * 身份证号校验
	 * 2016年3月9日
	 * By 肖长伟
	 * @param cardId
	 * @return   checkRs：0正常、1错误、01已存在
	 */
	@RequestMapping("checkCardId")
	@ResponseBody
	public String checkCardId(String cardId, String cardType, String custCode) {
		Map<String, String> rsMap = new HashMap<String, String>();
		if (StringUtils.isNotBlank(cardId) && StringUtils.isNotBlank(cardType)) { //不为空
			boolean checkRs = IdcardUtils.validateCard(cardId);  //校验是否正确
			if (checkRs == true) { 	//正确，然后检验是否已存在
				//TODO --校验财富中存在此身份证
				Customer customerParam = new Customer();
				customerParam.setCustCertType(cardType);
				customerParam.setCustCertNum(cardId);
				customerParam.setCustCode(custCode);
				int certCnt = customerService.getCustomerCertNumCnt(customerParam);
				if (certCnt > 0) {  //已存在: 查出数据，并且传递客户编号不=查出的客户编号
					rsMap.put("result", "true");
					rsMap.put("checkRs", "01");
					rsMap.put("msg", "已经存在");
					return  jsonMapper.toJson(rsMap);
				} else {  // 完全正确
					rsMap.put("result", "true");
					rsMap.put("checkRs", "0");
					rsMap.put("msg", "");
					return  jsonMapper.toJson(rsMap);
				}
			}  else {  //不正确
				rsMap.put("result", "true");
				rsMap.put("checkRs", "1");
				rsMap.put("msg", "");
				return  jsonMapper.toJson(rsMap);
			}
			
		} else {  //为空
			rsMap.put("result", "true");
			rsMap.put("checkRs", "1");
			rsMap.put("msg", "不能为空");
		}
		return jsonMapper.toJson(rsMap);
	}
	
}
