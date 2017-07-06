package com.creditharmony.fortune.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.cache.OrgCache;
import com.creditharmony.core.cache.ProvinceCityCache;
import com.creditharmony.core.cache.UserCache;
import com.creditharmony.core.fortune.type.AreaType;
import com.creditharmony.core.fortune.type.ConfigStatus;
import com.creditharmony.core.users.entity.Org;
import com.creditharmony.core.users.entity.ProvinceCity;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.fortune.common.dao.UtilDao;
import com.creditharmony.fortune.customer.entity.Product;

/**
 * 城市工具类
 * 
 * @Class Name OptionUtil
 * @author 朱杰
 * @Create In 2015年12月29日
 */
public class OptionUtil {

	private static UtilDao utilDao = SpringContextHolder.getBean(UtilDao.class);

	/**
	 * 城市数据源获取provinceId_cityId类型
	 * 
	 * 2015年12月29日 By 朱杰
	 * 
	 * @return
	 */
	public static List<ProvinceCity> getProvinceCityList() {
		// TODO provinceCityMap从缓存取数据
		// Map<String, List<ProvinceCity>> provinceCityMap = Maps.newHashMap();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("province_type", AreaType.PROVINCE.value);
		param.put("city_type", AreaType.CITY.value);
		return utilDao.findProvinceCityOption(param);
	}

	/**
	 * 产品数据源获取
	 * 
	 * 2015年12月29日 By 朱杰
	 * 
	 * @return
	 */
	public static List<Product> getProductList() {
		// TODO dictMap从缓存取数据
		// Map<String, List<ProvinceCity>> dictMap = Maps.newHashMap();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("productStatus", ConfigStatus.QY.value);
		return utilDao.findProductOption(param);
	}

	public static List<Product> getFullProductList() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("productStatus", ConfigStatus.QY.value);
		return utilDao.getProductList(param);
	}
	
	
	/**
	 * 根据产品Code获取产品名
	 * 
	 * 2015年12月29日 By 朱杰
	 * 
	 * @param productCode
	 * @return
	 */
	public static String getProductLabel(String productCode) {
		if (StringUtils.isNotEmpty(productCode)) {
			// TODO 缓存中获取产品名
			List<Product> list = OptionUtil.getProductList();
			for (Product product : list) {
				if (productCode.equals(product.getProductCode())) {
					return product.getProductName();
				}
			}
		}
		return "";
	}

	/**
	 * 获取省数据源
	 * 
	 * 2015年12月29日 By 朱杰
	 * 
	 * @return
	 */
	public static List<ProvinceCity> getProvinceList() {
		
		return ProvinceCityCache.getInstance().getListByType(AreaType.PROVINCE.value);
	}

	/**
	 * 根据省id获取省名
	 * 
	 * 2015年12月29日 By 朱杰
	 * 
	 * @param provinceId
	 *            省id
	 * @return
	 */
	public static String getProvinceLabel(String provinceId) {
		if (StringUtils.isNotEmpty(provinceId)) {
			ProvinceCity province = ProvinceCityCache.getInstance().get(provinceId);
			if(province==null){
				return "";
			}
			return province.getAreaName();
		}
		return "";
	}

	/**
	 * 获取城市数据源
	 * 
	 * 2015年12月29日 By 朱杰
	 * 
	 * @return
	 */
	public static List<ProvinceCity> getCityList() {
		return ProvinceCityCache.getInstance().getListByType(AreaType.CITY.value);
	}

	/**
	 * 根据城市id获取城市名
	 * 
	 * 2015年12月29日 By 朱杰
	 * 
	 * @param cityId
	 *            城市id
	 * @return
	 */
	public static String getCityLabel(String cityId) {
		if (StringUtils.isNotEmpty(cityId)) {
			ProvinceCity city = ProvinceCityCache.getInstance().get(cityId);
			if(city==null){
				return "";
			}
			return city.getAreaName();
		}
		return "";
	}

	/**
	 * 获取区数据源
	 * 
	 * 2015年12月29日 By 朱杰
	 * 
	 * @return
	 */
	public static List<ProvinceCity> getDistrictList() {
		
		return ProvinceCityCache.getInstance().getListByType(AreaType.DISTRICT.value);
	}

	/**
	 * 根据区id获取区名
	 * 
	 * 2015年12月31日 By 朱杰
	 * 
	 * @param districtId
	 * @return
	 */
	public static String getDistrictLabel(String districtId) {
		if (StringUtils.isNotEmpty(districtId)) {
			ProvinceCity district = ProvinceCityCache.getInstance().get(districtId);
			if(district==null){
				return "";
			}
			return district.getAreaName();
		}
		return "";
	}

	/**
	 * 根据省ID获取该省所有城市 2015年12月31日 By 朱杰
	 * 
	 * @param provinceId
	 * @return
	 */
	public static List<ProvinceCity> getCityByProvinceId(String provinceId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("city_type", AreaType.CITY.value);
		param.put("province_id", provinceId);
		return utilDao.findCityOption(param);
	}

	/**
	 * 根据市ID获取该市所有区
	 * 
	 * 2015年12月31日 By 朱杰
	 * 
	 * @param cityId
	 * @return
	 */
	public static List<ProvinceCity> getDistrictByCityId(String cityId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("district_type", AreaType.DISTRICT.value);
		param.put("city_id", cityId);
		return utilDao.findDistrictOption(param);
	}

	/**
	 * 根据机构id获取机构名 2016年1月23日 By 朱杰
	 * 
	 * @param orgId
	 * @return
	 */
	public static String getOrgNameById(String orgId) {
		if (StringUtils.isNotEmpty(orgId)) {
			// 缓存中取得机构信息
			Org org = OrgCache.getInstance().get(orgId);
			if (null == org) {
				return "";
			}
			if (StringUtils.isNotEmpty(org.getId())) {
				return org.getName();
			}
		}
		return "";
	}

	/**
	 * 获取用户名称 2016年1月26日 By 朱杰
	 * 
	 * @param userId
	 * @return
	 */
	public static String getUserNameById(String userId) {
		if (StringUtils.isNotEmpty(userId)) {
			// 缓存中获取
			User user = UserCache.getInstance().get(userId);
			if (null == user) {
				return "";
			}
			if (StringUtils.isNotEmpty(user.getId())) {
				return user.getName();
			}
		}
		return "";
	}

	public static List<ProvinceCity> findFYProvince(String provinceId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("province_type", AreaType.PROVINCE.value);
		param.put("province_id", provinceId);
		return utilDao.findFYProvince(param);
	}

	public static List<ProvinceCity> findFYCity(String provinceId,
			String city) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("city_type", AreaType.CITY.value);
		param.put("province_id", provinceId);
		param.put("city_id", city);
		return utilDao.findFYCity(param);
	}
	
	public static String getLable(String id){
		return utilDao.getLabel(id);
	}

	public static String getCityCodeByName(String name, String pareant_id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("area_type", "2");
		params.put("parent_id", pareant_id);
		params.put("area_name", name);
		return utilDao.getAreaCodeByName(params);
	}

	public static String getProvinceCodeByName(String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("area_type", "1");
		params.put("parent_id", "0");
		params.put("area_name", name);
		return utilDao.getAreaCodeByName(params);
	}

	public static String getDistrictCodeByName(String name, String pareant_id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("area_type", "3");
		params.put("area_name", name);
		params.put("parent_id", pareant_id);
		return utilDao.getAreaCodeByName(params);
	}
	
}
