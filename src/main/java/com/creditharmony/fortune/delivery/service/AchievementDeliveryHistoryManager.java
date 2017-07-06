
package com.creditharmony.fortune.delivery.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.DeliveryType;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.fortune.delivery.dao.AchievementDeliveryHistoryDao;
import com.creditharmony.fortune.delivery.entity.ext.DeliveryEx;
import com.creditharmony.fortune.template.entity.AchievementDeliveryHistoryExt;
/**
 *业绩交割历史查询 
 * @Class Name DeliveryAchievementHistoryService
 * @Author 胡体勇
 * @Create 2015年11月23日
 */
@Service
public class AchievementDeliveryHistoryManager extends CoreManager<AchievementDeliveryHistoryDao,DeliveryEx>{
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
		deliveryEx.setDelType(DeliveryType.YWJG.value);
		deliveryEx.setOrgType(FortuneOrgType.STORE.key);
		deliveryEx.setManagerRole(FortuneRole.FINANCING_MANAGER.id);
		deliveryEx.setTeamManagerRole(FortuneRole.TEAM_MANAGER.id);
		if(StringUtils.isNotEmpty(deliveryEx.getnOrgCode())){
			String[] ids = deliveryEx.getnOrgCode().split(",");
			deliveryEx.setOrgId(ids);
		}
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		pageBounds.setCountBy("del_id");
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
    public List<AchievementDeliveryHistoryExt> outExcel(DeliveryEx deliveryEx){
    	deliveryEx.setDelType(DeliveryType.YWJG.value);
		deliveryEx.setOrgType(FortuneOrgType.STORE.key);
		deliveryEx.setManagerRole(FortuneRole.FINANCING_MANAGER.id);
		deliveryEx.setTeamManagerRole(FortuneRole.TEAM_MANAGER.id);
    	List<AchievementDeliveryHistoryExt> excelList = new ArrayList<AchievementDeliveryHistoryExt>();
    	List<DeliveryEx> list = super.dao.findList(deliveryEx, new PageBounds());
    	if(ArrayHelper.isNotEmpty(list)){
    		for(int i = 0;i<list.size();i++){
    			AchievementDeliveryHistoryExt ext = new AchievementDeliveryHistoryExt();
    			// 将查询出的数据复制到模板对象
    			// 屏蔽客户姓名/手机号/身份证号 
    			list.get(i).setCustName("***");
				BeanUtils.copyProperties(list.get(i),ext);
				excelList.add(ext);
    		}
    	}
		return excelList;
    }
}
