package com.creditharmony.fortune.contract.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.contract.constant.Constant;
import com.creditharmony.fortune.contract.entity.Contract;
import com.creditharmony.fortune.contract.service.ContractChangeManager;
import com.creditharmony.fortune.contract.service.ContractManager;
import com.creditharmony.fortune.contract.util.SunIASUtil;

/**
 * 合同映像插件操作类
 * 
 * @Class Name ContractSunIASController
 * @author 王鹏飞
 * @Create In 2016年1月21日
 */
@Controller
@RequestMapping("${adminPath}/contract")
public class ContractSunIASController extends BaseController {

	@Autowired
	private ContractManager contractManager;

	@Autowired
	private ContractChangeManager contractChangeManager;

	/**
	 * 获取SUNIAS请求路径
	 * 
	 * @param contCode 合同编号
	 * @return 服务请求url
	 */
	@RequestMapping(value = "/getContractSunIASURL")
	@ResponseBody
	public String getContractSunIASURL(String contCode) {

		Map<String, Object> ct = new HashMap<String, Object>();

		if (StringUtils.isEmpty(contCode)) {
			ct.put("result", false);
			String t = jsonMapper.toJson(ct);
			return t;
		}
		Contract contract = contractManager.getContract(contCode);
		if (contract == null) {
			ct.put("result", false);
			String t = jsonMapper.toJson(ct);
			return t;
		}
		// 添加索引
		if (StringUtils.isEmpty(contract.getContFilepath())) {
			contract.preUpdate();
			contract.setContFilepath(IdGen.uuid() + ":"
					+ DateUtils.getDate(Constant.DATE_YYYYMMDD));
			contractManager.updateContFilepath(contract);
		}
		ct.put("result", true);
		ct.put("url", SunIASUtil.INSTANCE.getRequstURL(
				contract.getContFilepath(), Constant.CONTRACT_MODULE));
		String t = jsonMapper.toJson(ct);
		return t;
	}

}