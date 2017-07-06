package com.creditharmony.fortune.delivery.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.delivery.constant.Constant;
import com.creditharmony.fortune.delivery.dao.TripleSearchExcelInfoErrorDao;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;

/**
 * @Class Name TripleSearchExcelInfoErrorManager
 * @author 胡体勇
 * @Create In 2016年3月2日
 */
@Service
public class TripleSearchExcelInfoErrorManager extends CoreManager<TripleSearchExcelInfoErrorDao,IntDeliveryEx>{
    
	/**
	 * 分页查询
	 * 2015年12月2日
	 * By 胡体勇
	 * @param page
	 * @param intDeliveryEx
	 * @return
	 */
    public Page<IntDeliveryEx> findPage(Page<IntDeliveryEx> page,IntDeliveryEx intDeliveryEx){
		intDeliveryEx.setDeliveryType(Constant.DELIVERY_TYPE_ERROR);
		if(!StringUtils.isEmpty(intDeliveryEx.getOsType())){
			String[] types = intDeliveryEx.getOsType().split(",");
			intDeliveryEx.setOsTypeList(Arrays.asList(types));
		}
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		PageList<IntDeliveryEx> pageList = (PageList<IntDeliveryEx>) super.dao.findList(intDeliveryEx,pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
    
    /**
     * 导表
     * 2016年5月19日
     * By 胡体勇
     * @param intDeliveryEx
     * @return
     */
    public IntDeliveryEx outExcel(IntDeliveryEx intDeliveryEx){
    	
    	intDeliveryEx.setDeliveryType(Constant.DELIVERY_TYPE_ERROR);
		if(!StringUtils.isEmpty(intDeliveryEx.getOsType())){
			String[] types = intDeliveryEx.getOsType().split(",");
			intDeliveryEx.setOsTypeList(Arrays.asList(types));
		}
		return intDeliveryEx;
    }
}
