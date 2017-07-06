package com.creditharmony.fortune.creditor.config.rate.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.creditor.config.rate.entity.ProductMatchingRate;

/**
 * 产品利率配置
 * @Class Name ProductMatchingRateDao
 * @author 朱杰
 * @Create In 2016年2月4日
 */
@FortuneBatisDao
public interface ProductMatchingRateDao extends CrudDao<ProductMatchingRate>{
    int deleteByIds(String[] ids);

    int insert(ProductMatchingRate record);

    int updateStatusByIds(String[] ids);
    
    List<ProductMatchingRate> findList(Map<String, Object> map,PageBounds pageBounds);
    
    ProductMatchingRate getProductMatchRate(Map<String, Object> map);

	public  Integer beforDelete(String[] deleteIds);

	public int beforSaveCheck(Map<String, Object> param);

	int updateBatchStatusByIds(Map<String, Object> map);
}