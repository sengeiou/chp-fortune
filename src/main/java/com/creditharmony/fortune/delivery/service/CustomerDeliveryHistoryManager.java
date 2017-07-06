package com.creditharmony.fortune.delivery.service;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.fortune.delivery.dao.CustomerDeliveryHistoryDao;
import com.creditharmony.fortune.delivery.entity.ext.DeliveryEx;
import com.creditharmony.fortune.template.entity.CustomerDeliveryHistoryExt;

/**
 *客户交割历史查询service
 * @Class Name DeliveryService
 * @author 胡体勇
 * @Create In 2015年11月17日
 */
@Service
public class CustomerDeliveryHistoryManager extends CoreManager<CustomerDeliveryHistoryDao,DeliveryEx>{
	/**
	 * 分页查询
	 * 2015年11月27日
	 * By 胡体勇
	 * @param page
	 * @param deductPoolExt
	 * @return
	 */
	public Page<DeliveryEx> findPage(Page<DeliveryEx> page,DeliveryEx deliveryEx){
		// 设置查询参数
		//deliveryEx.setDelType(Constant.DELIVERY_DELTYPE_CUSTOMERDELIVERY);
		//deliveryEx.setDictDelStatus(Constant.DELIVERY_DELSTATUS_DELIVERYED);
		deliveryEx.setOrgType(FortuneOrgType.STORE.key);
		deliveryEx.setManagerRole(FortuneRole.FINANCING_MANAGER.id);
		deliveryEx.setTeamManagerRole(FortuneRole.TEAM_MANAGER.id);
		if(StringUtils.isNotEmpty(deliveryEx.getnOrgCode())){
			String[] ids = deliveryEx.getnOrgCode().split(",");
			deliveryEx.setOrgId(ids);
		}
		
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		PageList<DeliveryEx> pageList = (PageList<DeliveryEx>) super.dao.findList(deliveryEx,pageBounds);
		PageUtil.convertPage(pageList, page);
		
		return page;
	}
	
	/**
	 * 导出excel表格
	 * 2016年1月8日
	 * By 胡体勇
	 * @param deliveryEx
	 * @return
	 */
    public List<CustomerDeliveryHistoryExt> outExcel(DeliveryEx deliveryEx){
    	// 设置查询参数
        //deliveryEx.setDelType(Constant.DELIVERY_DELTYPE_CUSTOMERDELIVERY);
		//deliveryEx.setDictDelStatus(Constant.DELIVERY_DELSTATUS_DELIVERYED);
		deliveryEx.setOrgType(FortuneOrgType.STORE.key);
		deliveryEx.setManagerRole(FortuneRole.FINANCING_MANAGER.id);
		deliveryEx.setTeamManagerRole(FortuneRole.TEAM_MANAGER.id);
		
    	List<CustomerDeliveryHistoryExt> excelList = new ArrayList<CustomerDeliveryHistoryExt>();
    	List<DeliveryEx> list = super.dao.findList(deliveryEx, new PageBounds());
    	if(ArrayHelper.isNotEmpty(list)){
    		for(int i = 0;i<list.size();i++){
    			CustomerDeliveryHistoryExt ext = new CustomerDeliveryHistoryExt();
    			// 将查询出的数据复制到模板对象
				BeanUtils.copyProperties(list.get(i),ext);
				excelList.add(ext);
    		}
    	}
		return excelList;
    }
	
}
