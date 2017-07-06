package com.creditharmony.fortune.lend.apply.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.dict.util.DictUtils;
import com.creditharmony.core.fortune.type.DeleteFlag;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.entity.ProvinceCity;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.change.lend.apply.entity.BranchBankCode;
import com.creditharmony.fortune.contract.entity.Contract;
import com.creditharmony.fortune.contract.service.ContractManager;
import com.creditharmony.fortune.customer.dao.CustomerAccountDao;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.CustomerAccount;
import com.creditharmony.fortune.customer.entity.Product;
import com.creditharmony.fortune.customer.service.AccountManager;
import com.creditharmony.fortune.lend.apply.entity.AccountView;
import com.creditharmony.fortune.lend.apply.manager.LendApplyManager;
import com.creditharmony.fortune.utils.OptionUtil;

/**
 * 出借申请辅助类
 * 
 * @Class Name LendApplyAidController
 * @author 朱杰
 * @Create In 2016年4月10日
 */
@Controller
@RequestMapping("${adminPath}/lend/lendApplyAid")
public class LendApplyAidController extends BaseController {
	@Autowired
	private AccountManager accountManager;
	@Autowired
	private LendApplyManager lendApplyManager;
	@Autowired
	private ContractManager contractManager;
	@Autowired
	private LoanApplyDao loanApplyDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CustomerAccountDao accountDao;

	/**
	 * 银行操作方法 2016年1月18日
	 * 
	 * @param view
	 * @return
	 */
	@RequestMapping("/bank")
	@ResponseBody
	public String bankOperate(@RequestBody AccountView view) {
		if ("add".equals(view.getOperating())) {
			// 添加银行
			accountManager.save(view.getAccount());
			
			// 如果添加的是金账户银行，则需要更新客户信息表金账户银行字段
			if("true".equals(view.getTrusteeFlag())){
				Customer customer = new Customer();
				customer.setCustCode(view.getAccount().getCustomerCode());
				customer = customerDao.getCustomer(customer);
				// 删除原有的金账户账号
				accountDao.delete(customer.getTrusteeshipAccountId());
				//更新客户的金账户信息 Id
				customerDao.updateTrusteeAccountId(view.getAccount().getCustomerCode(), 	view.getAccount().getId());
			}
			
		} else if ("update".equals(view.getOperating())) {
			// 修改银行
			accountManager.update(view.getAccount());
		} else {
			// 刪除银行 (逻辑删除)
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("bankId", view.getAccount().getId());
			params.put("isDel", DeleteFlag.DELETED.value);
			accountManager.logicDelete(params);
		}
		Map<String, String> result = new HashMap<String, String>();
		result.put("result", BooleanType.TRUE);
		result.put("id", view.getAccount().getId());
		return JsonMapper.toJsonString(result);
	}

	/**
	 * 获取客户的账户信息（金账户除外）
	 * 2016年4月18日
	 * By 肖长伟
	 * @param customerCode
	 * @return
	 */
	@RequestMapping("/getBankSelectPage")
	public String getBankSelectPage(String customerCode, Model model) {
		
		Customer customer = new Customer();
		customer.setCustCode(customerCode);
		customer = customerDao.getCustomerbyCode(customer);
		model.addAttribute("customer", customer);
		
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> canModList = new ArrayList<String>();
		canModList.add(LendState.SPBTG.value);
		canModList.add(LendState.CX.value);
		canModList.add(LendState.KHFQ.value);
		canModList.add(LendState.JZHKHSB.value);
		params.put("canModList", canModList);
		List<String> notModList = new ArrayList<String>();
		notModList.add(LendState.JZHDKH.value);
		notModList.add(LendState.DFJSC.value);
		notModList.add(LendState.DCJSP.value);
		notModList.add(LendState.SPTG.value);
		notModList.add(LendState.DHK.value);
		notModList.add(LendState.HKCLZ.value);
		notModList.add(LendState.HKCG.value);
		notModList.add(LendState.HKSB.value);
		notModList.add(LendState.JZHXH.value);
		notModList.add(LendState.WJHCZ.value);
		notModList.add(LendState.WJHCSB.value);
		params.put("notModList", notModList);
		params.put("customerCode", customerCode);
		params.put("isDel", DeleteFlag.NOT_DELETED.value);
		List<CustomerAccount> banks = accountDao.findListByCustomerCode(params);
		model.addAttribute("bankList", banks);
		model.addAttribute("provinceList", OptionUtil.getProvinceList());
		
		//刨除金账户
		String goldAccountId = customer.getTrusteeshipAccountId();
		if (StringUtils.isNotBlank(goldAccountId)) {
			for (Iterator<CustomerAccount> iterator = banks.iterator(); iterator.hasNext();) {
				CustomerAccount itemAccount = iterator.next();
				if (goldAccountId.equals(itemAccount.getId())) {
					iterator.remove();
				}
			}
		}
		
		//市、县
		List<List<ProvinceCity>> cityList = new ArrayList<List<ProvinceCity>>();
		List<List<ProvinceCity>> districtList = new ArrayList<List<ProvinceCity>>();
		
		if (ArrayHelper.isNotEmpty(banks)) {   //设置银行的 省、市、县信息
			for (CustomerAccount item : banks) {
				cityList.add(OptionUtil.getCityByProvinceId(item.getAccountAddrProvince()));
				districtList.add(OptionUtil.getDistrictByCityId(item.getAccountAddrCity()));
			}
		}
		model.addAttribute("cityList", cityList);
		model.addAttribute("districtList", districtList);
		return "lend/bank/lendApplyBank";
	}
	
	/**
	 * 获取金账户页面
	 * 2016年4月20日
	 * By 肖长伟
	 * @param customerCode
	 * @param model
	 * @return
	 */
	@RequestMapping("/getGoldAccountManagePage")
	public String getGoldAccountManagePage(String customerCode, Model model) {
		Customer customer = new Customer();
		customer.setCustCode(customerCode);
		customer = customerDao.getCustomerbyCode(customer);
		model.addAttribute("customer", customer);
		
		List<CustomerAccount> bankList = new ArrayList<CustomerAccount>();
		String goldAccountId = customer.getTrusteeshipAccountId();
		if (StringUtils.isNotBlank(goldAccountId)) {
			bankList.add(accountDao.get(goldAccountId));
		}
		
		model.addAttribute("bankList", bankList);
		model.addAttribute("provinceList", OptionUtil.findFYProvince(null));
		
		//市
		List<List<ProvinceCity>> cityList = new ArrayList<List<ProvinceCity>>();
		
		if (ArrayHelper.isNotEmpty(bankList)) {   //设置银行的 省、市、县信息
			for (CustomerAccount item : bankList) {
				cityList.add(OptionUtil.findFYCity(item.getAccountAddrProvince(), null));
			}
		}
		if (bankList.size() == 0) {
			model.addAttribute("isAdd", "1");
			bankList.add(new CustomerAccount());
		} else {
			model.addAttribute("isAdd", "0");
		}
		model.addAttribute("cityList", cityList);
				
		return "lend/bank/lendApplyGoldBank";
	}
	
	/**
	 * 检验银行是否可操作 2016年1月26日
	 * 
	 * @param bankId
	 * @return
	 */
	@RequestMapping("checkBankUsed")
	@ResponseBody
	public String checkBankUsed(String bankId) {
		Map<String, String> result = new HashMap<String, String>();
		int count = accountManager.countStatus(bankId, "");  //不需要判断状态，只要引用了，都不能删
		if (count > 0) {
			result.put("result", BooleanType.FALSE);
			result.put("message", "有未完结的出借关联此银行卡，不能操作");
		} else {
			result.put("result", BooleanType.TRUE);
		}
		return JsonMapper.toJsonString(result);
	}
	
	/**
	 * 检验是否存在银行卡
	 * 2016年3月9日
	 * By 肖长伟
	 * @param cardNo
	 * @return
	 */
	@RequestMapping("checkExistBankCard")
	@ResponseBody
	public String checkExistBankCard(String cardNo, String custCode, String isTrustAccountAdd, String accountId) {
		Map<String, String> result = new HashMap<String, String>();
		
		//获取客户金账户信息
		Customer customer = new Customer();
		customer.setCustCode(custCode);
		customer = customerDao.getCustomerbyCode(customer);
		String trustAccountId = customer.getTrusteeshipAccountId();
		//获取客户账户List，根据cardNo、custCode 查找
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountNo", cardNo);
		params.put("customerCode", custCode);
		List<CustomerAccount> customerAccountListDB = accountManager.getAccountByCardNo(params);
		int count = customerAccountListDB.size();
		if (count > 2) { //重复了
			result.put("isExist", BooleanType.TRUE);  //存在
			return JsonMapper.toJsonString(result);
		} else if (count > 1) { //存在2条，
			//判断是否有一条是其本身（修改）
			for (CustomerAccount item : customerAccountListDB) {
				if(item.getId().equals(accountId)) { //修改其本身信息
					result.put("isExist", BooleanType.FALSE);  //不存在
					return JsonMapper.toJsonString(result);
				}
			}
			result.put("isExist", BooleanType.TRUE);  //存在
			return JsonMapper.toJsonString(result);
		} else if(count == 1) {  //存在1条
			
			//判断是否为金账户，若是金账户，允许再加一条
			if (StringUtils.isNotEmpty(trustAccountId)) { //有金账户
				String accntId = customerAccountListDB.get(0).getId();
				if (trustAccountId.equals(accntId)) { //是金账户， 可以加
					result.put("isExist", BooleanType.FALSE);  //不存在
					return JsonMapper.toJsonString(result);
				} else { //不是金账户，不能再加
					if (accntId.equals(accountId)) { //修改本身
						result.put("isExist", BooleanType.FALSE);  //不存在
						return JsonMapper.toJsonString(result);
					} else {
						result.put("isExist", BooleanType.TRUE);  //存在
						return JsonMapper.toJsonString(result);
					}
				}
			} else { //没有金账户
				//是否添加金账户
				if ("1".equals(isTrustAccountAdd)) { //添加金账户
					result.put("isExist", BooleanType.FALSE);  //不存在
					return JsonMapper.toJsonString(result);
				} else { //不是添加金账户
					
					if (StringUtils.isNotBlank(accountId) && accountId.equals(customerAccountListDB.get(0).getId())) { //参数accountId 与数据查出的id相同，修改本身
						result.put("isExist", BooleanType.FALSE);  //不存在
						return JsonMapper.toJsonString(result);
					} else {
						result.put("isExist", BooleanType.TRUE);  //存在
						return JsonMapper.toJsonString(result);
					}
				}
			}
		} else { //不存在此卡号
			result.put("isExist", BooleanType.FALSE);  //不存在
			return JsonMapper.toJsonString(result);
		}
	}
	
	/**
	 * 
	 * 2016年4月19日
	 * By 肖长伟
	 * @param custCode
	 * @param accountId
	 * @return
	 */
	@RequestMapping("isBankAccountValidate")
	@ResponseBody
	public String isBankAccountValidate(String accountId) {
		Map<String, String> result = new HashMap<String, String>();
		CustomerAccount account = accountManager.get(accountId);
		if (account == null) { //不存在
			result.put("isExist", BooleanType.FALSE);  //不存在
		} else { 	//存在
			result.put("isExist", BooleanType.TRUE);  //不存在
		}
		return JsonMapper.toJsonString(result);
	}
	
	@RequestMapping("generateContractNumber")
	@ResponseBody
	public String generateContractNumber(String productCode) {
		String contractNumber = lendApplyManager.getContractCode(productCode);
		Map<String, String> result = new HashMap<String, String>();
		if (contractNumber == null) { //不存在
			result.put("contractNumber", contractNumber);  //不存在
		} else { 	//存在
			result.put("contractNumber", contractNumber);  //存在
		}
		return JsonMapper.toJsonString(result);
	}
	
	/**
	 * 校验合同编号 2016年1月18日
	 * 
	 * @param contractCode
	 * @return
	 */
	@RequestMapping("getContractVersion")
	@ResponseBody
	public String ajaxContractVersion(String contractCode) {
		Map<String, String> result = new HashMap<String, String>();
		if ("1234567".equals(contractCode)) {
			result.put("result", BooleanType.TRUE);
			result.put("version_name", "1.4");
			return JsonMapper.toJsonString(result);
		}
		Contract contract = contractManager.getContract(contractCode);
		if(null != contract){
			result.put("result", BooleanType.TRUE);
			result.put("version_name", contract.getContVersion());
			return JsonMapper.toJsonString(result);
		} else {
			result.put("result", BooleanType.FALSE);
			result.put("message", "请输入合法的合同编号");
			return JsonMapper.toJsonString(result);
		}/* else if (ContractState.YFP.value.equals(contract.getDictContStatus())) {
			// 合同状态为已分配
			if (UserUtils.getUser().getId()
					.equals(contract.getContBelongEmpid())) {
				// 合同归属为当前登录人
				result.put("result", BooleanType.TRUE);
				result.put("version", contract.getContVersion());
			} else {
				// 合同归属不是当前登录人
				result.put("result", BooleanType.FALSE);
				result.put("message", "合同编号[" + contractCode + "]不属于当前登录人");
			}

			return JsonMapper.toJsonString(result);
		} else {
			//ContractState.initContractState();
			result.put("result", BooleanType.FALSE);
			result.put(
					"message",
					"合同编号["
							+ contractCode
							+ "]不能使用，状态为:"
							+ ContractState.getContractState(contract
									.getDictContStatus()));
			return JsonMapper.toJsonString(result);
		}*/

	}

	/**
	 * 获取产品销售折扣率和版本 2016年1月27日
	 * 
	 * @param productCode
	 * @param lendDate
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("getProductDiscountrateAndVersion")
	@ResponseBody
	public String getProductDiscountrateAndVersion(String productCode, String lendDate) {
		Map<String, String> result = new HashMap<String, String>();
		//获取产品销售折扣率
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productCode", productCode);
		map.put("lendDay", DateUtils.parseDate(lendDate));
		List<Map> list = loanApplyDao.getProductDiscountRate(map);
		String discountRate = null;
		if (ArrayHelper.isNotEmpty(list)) {
			Map m = list.get(0);
			Object realProductRate = m.get("real_discountrate");
			if (null != realProductRate && !"".equals(realProductRate)) {
				discountRate = realProductRate.toString();
			} else {
				discountRate = m.get("discountrate").toString();
			}
		} else {
			Product product = loanApplyDao.getProduct(productCode);
			if (null != product) {
				discountRate = product.getProductDiscountrate().toString();
			}
		}
		result.put("rate", discountRate);
		//获取产品版本
		String version = loanApplyDao.getProductVersion(productCode);
		StringBuffer buf = new StringBuffer();
		if(null != version && !"".equals(version)){
			String[] versionArr = version.split(",");
			for(String code : versionArr){
				String label = DictUtils.getDictLabel(code, "tz_contract_vesion", "");
				if(!"".equals(label)){
					buf.append(code + "," + label);
					buf.append(";");
				}
			}
		}
		if(buf.length() >= 1){
			buf.deleteCharAt(buf.length() - 1);
		}
		
		result.put("p_version", buf.toString());
		return jsonMapper.toJson(result);
	}

	/**
	 * ajax动态获得计划出借日期
	 * 2016年3月2日
	 * By 周俊
	 * @param platId 平台id
	 * @param bankId 银行id
	 * @param deductDate 计划划扣日期
	 * @return
	 */
	@RequestMapping("/ajaxGetlendDate")
	@ResponseBody
	public String ajaxGetlendDate(String platId,String bankId,Date deductDate,String provinceCode,String lendMoney){
		Map<String, Object> applyDeduct = null;
		try {
			applyDeduct = lendApplyManager.ajaxApplyDeduct(platId, provinceCode, bankId, deductDate, Double.valueOf(lendMoney.replaceAll(",","")));
			applyDeduct.put("exception",null);
		} catch (Exception e) {
			applyDeduct = new HashMap<String, Object>();
			applyDeduct.put("exception", e.getMessage());
		}
		return jsonMapper.toJson(applyDeduct);
	}
	
	/**
	 * 选择银行支行编码弹出层
	 * 2016年8月5日
	 * @param bankName
	 * @param branchName
	 * @return 
	 */
	@RequestMapping("/openBranchCodeDiv")
	public String openBranchCodeDiv(HttpServletRequest request, HttpServletResponse response, 
			String bankName, String branchName, String province, String city, String district, Model model){
		
		Page<BranchBankCode> page = new Page<BranchBankCode>(request, response);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bankName", bankName);
		params.put("branchName", branchName);
		params.put("province", province);
		params.put("city", city);
		params.put("district", district);
		page = accountManager.selectBranchCode(page, params);

		page.setFuncName("branchBankCodePaging");
		
		model.addAttribute("params", params);
		model.addAttribute("page", page);
		return "lendChange/openBranchCodeDiv";
	}
	
	/**
	 * 选择银行支行编码弹出层
	 * 2016年8月5日
	 * @param bankName
	 * @param branchName
	 * @return 
	 */
	@RequestMapping("/openBranchCodeSubList")
	public String openBranchCodeSubList(HttpServletRequest request, HttpServletResponse response, 
			String bankName, String branchName, String province, String city, String district, Model model){
		Page<BranchBankCode> page = new Page<BranchBankCode>(request, response);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bankName", bankName);
		params.put("branchName", branchName);
		params.put("province", province);
		params.put("city", city);
		params.put("district", district);
		page = accountManager.selectBranchCode(page, params);
		
		page.setFuncName("branchBankCodePaging");

		model.addAttribute("params", params);
		model.addAttribute("page", page);
		return "lendChange/openBranchCode";
	}
}
