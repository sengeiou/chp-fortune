package com.creditharmony.fortune.customer.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.users.entity.ProvinceCity;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.utils.OptionUtil;

/**
 * 省市联动的Controller
 * @Class Name ProvinceCityController
 * @author 周俊
 * @Create In 2015年12月30日
 */
@Controller
@RequestMapping("${adminPath}/provinceCity")
public class ProvinceCityController extends BaseController{
	
	/**
	 * 根据省获得城市
	 * 2015年12月30日
	 * By 周俊
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/getCity")
	@ResponseBody
	public String findCity(String provinceId){
		List<ProvinceCity> cityList = new ArrayList<ProvinceCity>();
		if(StringUtils.isNotEmpty(provinceId)){
			cityList = OptionUtil.getCityByProvinceId(provinceId);
			
		}
		return jsonMapper.toJson(cityList); 
	}
	
	/**
	 * 根据省获得城市(富友)
	 * 
	 * 2016年3月7日
	 * By 朱杰
	 * @param provinceId
	 * @return
	 */
	@RequestMapping("/getFyCity")
	@ResponseBody
	public String findFyCity(String provinceId){
		List<ProvinceCity> cityList = new ArrayList<ProvinceCity>();
		if(StringUtils.isNotEmpty(provinceId)){
			cityList = OptionUtil.findFYCity(provinceId, null);
			
		}
		return jsonMapper.toJson(cityList); 
	}
	
	/**
	 * 根据市获得县
	 * 2015年12月30日
	 * By 周俊
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/getDistrict")
	@ResponseBody
	public String findDistrict(String cityId){
		List<ProvinceCity> districtList = new ArrayList<ProvinceCity>();
		if(StringUtils.isNotEmpty(cityId)){
			districtList = OptionUtil.getDistrictByCityId(cityId);
		}
		return jsonMapper.toJson(districtList);
	}
}
