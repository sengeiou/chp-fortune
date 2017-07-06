package com.creditharmony.fortune.obligatory.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.users.entity.ProvinceCity;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.common.entity.Org;
import com.creditharmony.fortune.obligatory.entity.CreditOkListObj;
import com.creditharmony.fortune.obligatory.manager.CreditLocationListManager;
import com.creditharmony.fortune.utils.OptionUtil;

/**
 * 省市营业部联动
 * @Class Name ProvinceCityOrgController 
 * @author 李志伟
 * @Create In 2016年3月8日
 */
@Controller
@RequestMapping("${adminPath}/provinceCityOrg")
public class ProvinceCityOrgController extends BaseController{
	
	@Autowired
	private CreditLocationListManager creditLocationListManager;
	/**
	 * 根据省获得城市
	 * 2015年12月30日
	 * By 周俊
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/getCitys")
	@ResponseBody
	public String getCitys(CreditOkListObj col){
		List<ProvinceCity> cityList = new ArrayList<ProvinceCity>();
		if(StringUtils.isNotEmpty(col.getConfigProvince())){
			cityList = OptionUtil.getCityByProvinceId(col.getConfigProvince());
			
		}
		return jsonMapper.toJson(cityList); 
	}
	
	/**
	 * 根据城市获得营业部
	 * 2016年3月8日
	 * by 李志伟
	 * @param id
	 * @return
	 */
	@RequestMapping("/getOrg")
	@ResponseBody
	public String findOrg(CreditOkListObj col){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Org> orgList = new ArrayList<Org>();
		if(StringUtils.isNotEmpty(col.getConfigCity())){
			List<String> prctId = Arrays.asList(col.getConfigCity().split(","));
			map.put("prctId", prctId);
			map.put("type", FortuneOrgType.STORE.key);
			orgList = creditLocationListManager.findOrgList(map);
		}
		return jsonMapper.toJson(orgList);
	}
	
	/**
	 * 根据省获得营业部
	 * 2016年3月31日
	 * by 李志伟
	 * @param col
	 * @return
	 */
	@RequestMapping(value = "/getOrgByProvince")
	@ResponseBody
	public String getOrgByProvince(CreditOkListObj col){
		List<Org> orgList = new ArrayList<Org>();
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(col.getConfigProvince())){
			map.put("provinceId", col.getConfigProvince());
			map.put("type", FortuneOrgType.STORE.key);
			orgList = creditLocationListManager.getOrgByProvince(map);
			
		}
		return jsonMapper.toJson(orgList);
	}
}
