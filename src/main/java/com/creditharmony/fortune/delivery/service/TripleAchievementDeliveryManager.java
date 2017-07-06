
package com.creditharmony.fortune.delivery.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.DeliveryType;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.entity.FortuneUser;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.delivery.constant.Constant;
import com.creditharmony.fortune.delivery.dao.CustomerDeliveryDao;
import com.creditharmony.fortune.delivery.dao.TripleAchievementDeliveryDao;
import com.creditharmony.fortune.delivery.dao.TripleCustomerDeliveryDao;
import com.creditharmony.fortune.delivery.entity.ext.DeliveryEx;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.triple.system.service.TripleInvestSuccService;

/**
 * 业绩交割
 * @Class Name TripleAchievementDeliveryManager
 * @author 胡体勇
 * @Create In 2016年2月23日
 */
@Service
public class TripleAchievementDeliveryManager extends CoreManager<TripleAchievementDeliveryDao, IntDeliveryEx>{
	
	 @Autowired
     private TripleCustomerDeliveryDao tripleCustomerDeliveryDao;
	 @Autowired
	 private CustomerDeliveryDao customerDeliveryDao;
	 @Autowired
	 private TripleCustomerDeliveryManager manager;
	 
	 @Autowired
	 private TripleInvestSuccService tripleInvestSuccService ;
	
	/**
	 * 三网客户业绩交割
	 * 2016年2月23日
	 * By 胡体勇
	 * @param page
	 * @param intDeliveryEx
	 * @return
	 */
	public Page<IntDeliveryEx> findPage(Page<IntDeliveryEx> page,IntDeliveryEx intDeliveryEx){
		// 设置查询条件
		intDeliveryEx.setOrgType(FortuneOrgType.STORE.key);
		intDeliveryEx.setManagerRole(FortuneRole.FINANCING_MANAGER.id);
		intDeliveryEx.setTeamManagerRole(FortuneRole.TEAM_MANAGER.id);
		if(StringUtils.isNotEmpty(intDeliveryEx.getProductCode())){
			String[] str = intDeliveryEx.getProductCode().split(",");
			intDeliveryEx.setProductCodeList(Arrays.asList(str));
		}
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		pageBounds.setCountBy("lendCode");
		PageList<IntDeliveryEx> pageList = (PageList<IntDeliveryEx>) super.dao.findList(intDeliveryEx,pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 统计总金额
	 * 2016年2月24日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @return
	 */
	public IntDeliveryEx countMoney(IntDeliveryEx intDeliveryEx){
		// 设置查询条件
		intDeliveryEx.setOrgType(FortuneOrgType.STORE.key);
		intDeliveryEx.setManagerRole(FortuneRole.FINANCING_MANAGER.id);
		intDeliveryEx.setTeamManagerRole(FortuneRole.TEAM_MANAGER.id);
		if(StringUtils.isNotEmpty(intDeliveryEx.getProductCode())){
			String[] str = intDeliveryEx.getProductCode().split(",");
			intDeliveryEx.setProductCodeList(Arrays.asList(str));
		}
		return super.dao.countMoney(intDeliveryEx);
	}
	
	/**
	 * 查询理财经理信息
	 * 2016年4月23日
	 * By 胡体勇
	 * @param empCode
	 * @return
	 */
	public List<IntDeliveryEx> findManager(String empCode){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgType",FortuneOrgType.STORE.key);
		map.put("managerRole", FortuneRole.FINANCING_MANAGER.id);
		map.put("teamManagerRole", FortuneRole.TEAM_MANAGER.id);
		map.put("code",empCode);
		return tripleCustomerDeliveryDao.findEmpInfoByCode(map);
	}
	
	/**
	 * 修改业绩对应的理财经理
	 * 2016年4月23日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public int updateAchievementDao(IntDeliveryEx intDeliveryEx){
		return super.dao.updateAchievement(intDeliveryEx);
	}
	
	/**
	 * 插入chp交割表
	 * 2016年4月28日
	 * By 胡体勇
	 * @param d
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public int insertDeliveryDao(DeliveryEx d){
		return customerDeliveryDao.insertDelivery(d);
	}
	
	/**
	 * 批量业绩交割
	 * 2016年2月24日
	 * By 胡体勇
	 * @param code
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public int batchTripleAchievementDelivery(String code){	//三网系统交割
		int result = 0;
		User userInfo = UserUtils.getUser();
		// 解析客户业绩信息
		String[] codes = code.split(";");
		IntDeliveryEx ide = new IntDeliveryEx();
		for(int i = 0;i<codes.length;i++){
			// 财富交割表实体类
			DeliveryEx d = new DeliveryEx();
			// 分离提取信息
			String[] str = codes[i].split(",",Constant.ARRAY_SIZE_SEVEN);
			// 查询新理财经理对应信息
			List<IntDeliveryEx> newEmpInfo = findManager(str[2]);
			if(ArrayHelper.isNotEmpty(newEmpInfo)){
				// 理财经理id
				ide.setNewEmpCode(newEmpInfo.get(0).getNewEmpId());
				// 团队经理id
				ide.setNewTeamManagerCode(newEmpInfo.get(0).getNewTeamManagerId());
				// 营业部经理
				List<String> strList2 = new ArrayList<String>();
				strList2.add(FortuneRole.STORE_MANAGER.id);
				 List<FortuneUser> listUser2 = RelationShipUtil.getOrgMember(newEmpInfo.get(0).getNewOrgId(),strList2,"1");
				 if(listUser2.size() > 0){
					 // 营业部经理id
					 ide.setBussManager(listUser2.get(0).getId());;
				 }
				// 团队id
				ide.setTeamId(newEmpInfo.get(0).getNewTeamId());
				// 营业部id
				ide.setNewOrgId(newEmpInfo.get(0).getNewOrgId());
				// 支公司id
				FortuneOrg org =  RelationShipUtil.getCityOrg(newEmpInfo.get(0).getNewEmpId());
				if(org != null){
					ide.setCityOrg(org.getId());
					List<String> strList = new ArrayList<String>();
					strList.add(FortuneRole.CITY_MANAGER.id);
					 List<FortuneUser> listUser = RelationShipUtil.getOrgMember(org.getId(),strList,"1");
					 if(listUser.size() > 0){
						 // 支公司经理id
						 ide.setCityManager(listUser.get(0).getId());
					 }
				}
				
				d.setnTeamManagerCode(newEmpInfo.get(0).getNewTeamManagerId());
				d.setnOrgCode(newEmpInfo.get(0).getNewOrgId());
				d.setNfManagerCode(newEmpInfo.get(0).getNewEmpId());
			}
			ide.setLendCode(str[0]);
			ide.setVerTime(str[6]);
			ide.setModifyBy(UserUtils.getUserId());
			ide.setModifyTime(new Date());
			int msg = updateAchievementDao(ide);
			if(msg > 0){
				result++;
			}
			// 查询旧理财经理对应信息
			List<IntDeliveryEx> empInfo = manager.findStopEmpInfoByCode(str[1]);
			if(ArrayHelper.isNotEmpty(empInfo)){
				d.setTeamManagerCode(empInfo.get(0).getNewTeamManagerId());
				d.setOrgCode(empInfo.get(0).getNewOrgId());
				d.setfManagerCode(empInfo.get(0).getNewEmpId());
			}
			d.setCustCode(str[5]);
			d.setLendCode(str[0]);
			d.setDelId(IdGen.uuid());
			d.setDelType(DeliveryType.YWJG.value);
			String date = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
			Timestamp time = Timestamp.valueOf(date); // 获取当前时间，并转为timestamp格式
			d.setDelDate(time);
			d.setCreateBy(userInfo.getName());
			d.setModifyBy(userInfo.getName());
			d.setModifyTime(time);
			insertDeliveryDao(d);
			tripleInvestSuccService.investSucc(str[5], str[0], "D");
		}
		return result;
	}
}
