package com.creditharmony.fortune.triple.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.users.type.UserStatus;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.entity.FortuneUser;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.delivery.dao.TripleCustomerDeliveryDao;
import com.creditharmony.fortune.triple.system.dao.TripleNewCustomerDao;
import com.creditharmony.fortune.triple.system.entity.IntPhone;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;

/**
 * 理财经理离职自动交割
 * @Class Name AutoDeliveryCustomer
 * @author 胡体勇
 * @Create In 2016年5月3日
 */
@Service
public class AutoDeliveryCustomerService {
	
	@Autowired
	private TripleCustomerDeliveryDao tripleCustomerDeliveryDao;
	@Autowired
	private TripleNewCustomerDao tripleNewCustomerDao;

	
	
	/**
	 * 查询待分派的理财经理
	 * 2016年5月3日
	 * By 胡体勇
	 * @return
	 */
	public List<FortuneUser> findManager(String id){
		
		List<FortuneUser> fortunUser = new ArrayList<FortuneUser>();
		
		List<String> roleList = new ArrayList<String>();
		roleList.add("6450000002");
		
		// 获取理财经理的团队
		FortuneOrg team = RelationShipUtil.getTeamOrg(id);
		if(team != null){
			// 获取团队下的理财经理
			List<FortuneUser> teamUserList = RelationShipUtil.getOrgMember(team.getId(),roleList,UserStatus.ON);
			for(int i = 0;i<teamUserList.size();i++){
				if(!teamUserList.get(i).getId().equals(id)){
					if(UserStatus.ON.equals(teamUserList.get(i).getStatus())){
						FortuneUser u = new FortuneUser();
						u.setId(teamUserList.get(i).getId());
						u.setCode(teamUserList.get(i).getCode());
						u.setLoginName(teamUserList.get(i).getLoginName());
						u.setName(teamUserList.get(i).getName());
						u.setUserMobilePhone(teamUserList.get(i).getUserMobilePhone());
						// 校验理财经理是否是服务团队的理财经理
						boolean t = this.checkTeamManger(teamUserList.get(i).getId());
						if(t == true){
							fortunUser.add(u);
						}
					}
				}
			}
		}
		
		if(fortunUser.size() == 0){
			// 获取理财经理的营业部
			FortuneOrg stor = RelationShipUtil.getStoresOrg(id);
			if(stor != null){
				// 获取营业部下的理财经理
				List<FortuneUser> storUserList = RelationShipUtil.getOrgMember(stor.getId(), roleList,UserStatus.ON);
				for(int i = 0;i<storUserList.size();i++){
					if(!storUserList.get(i).getId().equals(id)){
						if(UserStatus.ON.equals(storUserList.get(i).getStatus())){
							FortuneUser u = new FortuneUser();
							u.setId(storUserList.get(i).getId());
							u.setCode(storUserList.get(i).getCode());
							u.setLoginName(storUserList.get(i).getLoginName());
							u.setName(storUserList.get(i).getName());
							u.setUserMobilePhone(storUserList.get(i).getUserMobilePhone());
							// 校验理财经理是否是服务团队的理财经理
							boolean s = this.checkTeamManger(storUserList.get(i).getId());
							if(s == true){
								fortunUser.add(u);
							}
						}
					}
				}			
			}
		}
		if(fortunUser.size() == 0){
			// 获取理财经理的支公司
			FortuneOrg city = RelationShipUtil.getCityOrg(id);
			if(city != null){
				// 获取支公司下的理财经理
				List<FortuneUser> cityUserList = RelationShipUtil.getOrgMember(city.getId(), roleList,UserStatus.ON);
				for(int i = 0;i<cityUserList.size();i++){
					if(!cityUserList.get(i).getId().equals(id)){
						if(UserStatus.ON.equals(cityUserList.get(i).getStatus())){
							FortuneUser u = new FortuneUser();
							u.setId(cityUserList.get(i).getId());
							u.setCode(cityUserList.get(i).getCode());
							u.setLoginName(cityUserList.get(i).getLoginName());
							u.setName(cityUserList.get(i).getName());
							u.setUserMobilePhone(cityUserList.get(i).getUserMobilePhone());
							// 校验理财经理是否是服务团队的理财经理
							boolean c = this.checkTeamManger(cityUserList.get(i).getId());
							if(c == true){
								fortunUser.add(u);
							}
						}
					}
				}		
			}
		}	
		return fortunUser;
	}
	
	/**
	 * 查询离职理财经理下符合条件的客户
	 * 2016年5月3日
	 * By 胡体勇
	 * @param id
	 * @return
	 */
	public List<IntDeliveryEx> findCustomer(String empCode){
		// 查询理财经理下未成单的客户
		IntDeliveryEx ex = new IntDeliveryEx();
		ex.setEmpCode(empCode);
		List<IntDeliveryEx> list = tripleCustomerDeliveryDao.searchAutoDeliveryCustomer(ex);
		return list;
	}
	
	/**
	 * 判断理财经理是否是服务团队理财经理
	 * 2016年5月12日
	 * By 胡体勇
	 * @param empId
	 * @return
	 */
	public boolean checkTeamManger(String empId){
		boolean flag = false;
		// 校验理财经理是否是服务团队的理财经理
		IntDeliveryEx ex = new IntDeliveryEx();
		ex.setEmpId(empId);
		IntDeliveryEx d = tripleCustomerDeliveryDao.findOrgName(ex);
		if(d != null){
			if(d.getOrgName().indexOf("服务团队") == -1){
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 根据手机号和系统类型修改手机号对应的理财经理
	 * 2016年5月30日
	 * By 胡体勇
	 * @param p
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager",readOnly = false)
	public int updateEmpCodeByOsTypePhoneDao(IntPhone p){
		return tripleNewCustomerDao.updateEmpCodeByOsTypePhone(p);
	}
	
	/**
	 * 修改chp客户表客户对应的理财经理
	 * 2016年5月30日
	 * By 胡体勇
	 * @param ex
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager",readOnly = false)
	public int updateCustomerEmpDao(IntDeliveryEx ex){
	    return tripleCustomerDeliveryDao.updateCustomerEmp(ex);
	}
	
	/**
	 * 插入交割表信息
	 * 2016年5月30日
	 * By 胡体勇
	 * @param d
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager",readOnly = false)
	public int insertIntDeliveryDao(IntDeliveryEx d){
		return tripleCustomerDeliveryDao.insertIntDelivery(d);
	}

}
