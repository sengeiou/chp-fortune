
package com.creditharmony.fortune.delivery.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.delivery.constant.Constant;
import com.creditharmony.fortune.delivery.dao.AchievementDeliveryDao;
import com.creditharmony.fortune.delivery.dao.CustomerDeliveryDao;
import com.creditharmony.fortune.delivery.entity.ext.DeliveryEx;
import com.creditharmony.fortune.triple.system.service.TripleInvestSuccService;

/**
 * 业绩交割
 * @Class Name AchievementDeliveryManger
 * @author 胡体勇
 * @Create In 2015年12月1日
 */
@Service
public class AchievementDeliveryManager extends CoreManager<AchievementDeliveryDao, DeliveryEx> {
	
	@Autowired
    private CustomerDeliveryDao customerDeliveryDao;
	
	@Autowired
	private TripleInvestSuccService tripleInvestSuccService ;
	
	/**
	 * 
	 * 2015年12月7日
	 * By 胡体勇
	 * @param page
	 * @param deliveryEx
	 * @return
	 */
	public Page<DeliveryEx> findPage(Page<DeliveryEx> page,DeliveryEx deliveryEx){
		// 设置查询条件
		deliveryEx.setOrgType(FortuneOrgType.STORE.key);
		deliveryEx.setManagerRole(FortuneRole.FINANCING_MANAGER.id);
		deliveryEx.setTeamManagerRole(FortuneRole.TEAM_MANAGER.id);
		if(StringUtils.isNotEmpty(deliveryEx.getProductCode())){
			String[] str = deliveryEx.getProductCode().split(",");
			deliveryEx.setProductCodeList(Arrays.asList(str));
		}
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		PageList<DeliveryEx> pageList = (PageList<DeliveryEx>) super.dao.findList(deliveryEx,pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 业绩交割
	 * 2015年12月8日
	 * By 胡体勇
	 * @param custCode
	 * @param lendCode
	 * @param fManagerCode
	 * @param nfManagerCode
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public int achievementDelivery(Map<String,String> map){
		int result = 0;
		// 设置业绩交割信息
		DeliveryEx deliveryEx = new DeliveryEx();
		deliveryEx.setDelId(IdGen.uuid());
		//deliveryEx.setDelType(Constant.DELIVERY_DELTYPE_ACHIEVEMENTDELIVERY);
		//deliveryEx.setDictDelStatus(Constant.DELIVERY_DELSTATUS_DELIVERYED);
		deliveryEx.setCustCode(map.get("custCode"));
		deliveryEx.setLendCode(map.get("lendCode"));
		deliveryEx.setfManagerCode(map.get("fManagerId"));
		deliveryEx.setNfManagerCode(map.get("nfManagerId"));
		String date = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		Timestamp time = Timestamp.valueOf(date); // 获取当前时间，并转为timestamp格式
		deliveryEx.setDelDate(time);
		deliveryEx.setCreateBy(UserUtils.getUserId());
		deliveryEx.setCreateTime(time);
		deliveryEx.setModifyBy(UserUtils.getUserId());
		deliveryEx.setModifyTime(time);
		//deliveryEx.setIsDelivery(Constant.DELIVERY_DELSTATUS_DELIVERYED);
		result = this.dao.updateLendApply(deliveryEx);
		customerDeliveryDao.insertDelivery(deliveryEx);
		tripleInvestSuccService.investSucc(map.get("custCode"), map.get("lendCode"), "D");
		return result;
	}
	
	/**
	 * 批量业绩交割
	 * 2015年12月8日
	 * By 胡体勇
	 * @param code
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public int batchAchievementDelivery(String code){
		int result = 0;
		String[] total = code.split(";");// 根据";"分离出客户对应的信息集合
		for(int i = 0;i<total.length;i++){
			if(StringUtils.isNotEmpty(total[i])){
				String[] info = total[i].split(",",Constant.ARRAY_SIZE_FOUR);// 根据","分离出客户对应理财经理、新理财经理及交割时间
				Map<String,String> map = new HashMap<String,String>();
				map.put("custCode", info[0]);
				map.put("lendCode", info[1]);
				map.put("fManagerId", info[2]);
				map.put("nfManagerId", info[3]);
				this.achievementDelivery(map);
			}
			result++;
		}
		return result;
	}
	
	/**
     * 根据用户id查询用户组织机构信息
     * 2016年1月7日
     * By 胡体勇
     * @param id
     * @return
     */
    public DeliveryEx findInfoById(String id){
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("id", id);
    	map.put("orgType",FortuneOrgType.STORE.key);
		map.put("managerRole", FortuneRole.FINANCING_MANAGER.id);
		map.put("teamManagerRole", FortuneRole.TEAM_MANAGER.id);
		return super.dao.findInfoById(map);
    }
    
    /**
     * 统计页面显示总金额
     * 2016年1月18日
     * By 胡体勇
     * @param deliveryEx
     * @return
     */
    public DeliveryEx countMoney(DeliveryEx deliveryEx){
    	// 设置查询条件
		deliveryEx.setOrgType(FortuneOrgType.STORE.key);
		deliveryEx.setManagerRole(FortuneRole.FINANCING_MANAGER.id);
		deliveryEx.setTeamManagerRole(FortuneRole.TEAM_MANAGER.id);
    	if(StringUtils.isNotEmpty(deliveryEx.getProductCode())){
			String[] str = deliveryEx.getProductCode().split(",");
			deliveryEx.setProductCodeList(Arrays.asList(str));
		}
    	return super.dao.countMoney(deliveryEx);
    }
}
