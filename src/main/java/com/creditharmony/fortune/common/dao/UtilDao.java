package com.creditharmony.fortune.common.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.core.users.entity.ProvinceCity;
import com.creditharmony.fortune.customer.entity.Product;

/**
 * 共通内容管理-Dao
 * 
 * @Class Name UtilDao
 * @author 朱杰
 * @Create In 2015年12月29日
 */
@FortuneBatisDao
public interface UtilDao {

	/**
	 * 城市数据源获取
	 * 
	 * 2015年12月29日
	 * By 朱杰
	 * @param map
	 * @return
	 */
	public List<ProvinceCity> findProvinceCityOption(Map<String, Object> map);
	
	/**
	 * 产品数据源获取
	 * 
	 * 2015年12月29日
	 * By 朱杰
	 * @param map
	 * @return
	 */
	public List<Product> findProductOption(Map<String, Object> map);
	
	/**
	 * 省数据源获取
	 * 
	 * 2015年12月29日
	 * By 朱杰
	 * @param map
	 * @return
	 */
	public List<Product> findProvinceOption();
	
	/**
	 * 省数据源获取
	 * 
	 * 2015年12月29日
	 * By 朱杰
	 * @param map
	 * @return
	 */
	public List<ProvinceCity> findProvinceOption(Map<String, Object> map);
	
	/**
	 * 城市数据源获取
	 * 
	 * 2015年12月29日
	 * By 朱杰
	 * @param map
	 * @return
	 */
	public List<ProvinceCity> findCityOption(Map<String, Object> map);
	
	/**
	 * 区数据源获取
	 * 
	 * 2015年12月31日
	 * By 朱杰
	 * @param map
	 * @return
	 */
	public List<ProvinceCity> findDistrictOption(Map<String, Object> map);
	
	public List<ProvinceCity> findFYProvince(Map<String, Object> map);
	
	public List<ProvinceCity> findFYCity(Map<String, Object> map);
	
	public String getLabel(String id);
	
	/**
	 * 根据区域name，type，parentId查询区域所在code
	 * 2016年4月13日
	 * By yourname
	 * @param name
	 * @param areaType
	 * @param pareant_id
	 * @return
	 */
	public String getAreaCodeByName(Map<String, Object> map);
	
	/**
	 * 获取产品详细信息
	 * 2016年4月14日
	 * By yourname
	 * @return
	 */
	public List<Product> getProductList(Map<String, Object> map);
}
