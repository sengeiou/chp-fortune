package com.creditharmony.fortune.donation.apply.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.CustomerState;
import com.creditharmony.core.fortune.type.DeliveryType;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.dao.UserRoleOrgDao;
import com.creditharmony.core.users.entity.UserRoleOrg;
import com.creditharmony.core.users.type.UserStatus;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.change.lender.apply.view.LenderInitView;
import com.creditharmony.fortune.common.service.IntKeyLogService;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.entity.FortuneUser;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.donation.apply.dao.CustomertransferDao;
import com.creditharmony.fortune.donation.apply.entity.CustomerManagerinfo;
import com.creditharmony.fortune.donation.apply.entity.CustomerQueryView;
import com.creditharmony.fortune.donation.apply.entity.Customertransfe;
import com.creditharmony.fortune.donation.apply.entity.CustomertransferLaunchView;
import com.creditharmony.fortune.triple.system.entity.IntCard;
import com.creditharmony.fortune.triple.system.service.TripleCheckInfoService;

/**
 * 转赠申请servise
 * @Class Name CustomerTransferManager
 * @author 刘雄武
 * @Create In 2016年4月19日
 */
@Service
public class CustomerTransferManager extends CoreManager<CustomertransferDao, Customertransfe>{

	@Resource
	private CustomertransferDao ctdao;
	@Resource
	private CustomerDao customerDao;
	// 三网接口验证
	@Resource
	TripleCheckInfoService tripleCheckInfoService;
	@Autowired
	private UserRoleOrgDao userRoleOrgDao;
	@Autowired
	private IntKeyLogService intKeyLogService;

	/**
	 * 根据客户编号获取客户理财经理信息
	 * 2016年3月4日
	 * By 刘雄武
	 * @param customert
	 * @return
	 */
	public List<Customertransfe> getCustomerInfo(String  empCode){
	   List<Customertransfe>  customertinfo= ctdao.getCustomerInfo(empCode);
       for (Customertransfe c : customertinfo) {
    	   if(FortuneRole.FINANCING_MANAGER.id.equals(c.getRoleId())){
    		   // 理财经理的场合
    		   FortuneOrg team = RelationShipUtil.getTeamOrg(c.getManagerId());
    		   if(team!=null){
    			   List<String> role = new ArrayList<String>();
    			   role.add(FortuneRole.TEAM_MANAGER.id);
    			   List<FortuneUser> l = RelationShipUtil.getOrgMember(
    					   team.getId(), role, UserStatus.ON);
    			   if(l.size() > 0){
    				   c.setTeamManagerCode(l.get(0).getCode());
    				   c.setTeamManagerName(l.get(0).getName());
    			   }
    		   }
    		   // 营业部
    		   FortuneOrg org=RelationShipUtil.getStoresOrg(c.getManagerId());
			   if(org!=null){
				   c.setDepartmentName(org.getName());
			   }
    	   }
			
		}
		return customertinfo;
	}
	
	/**
	 * 监测是否符合转赠条件
	 * 2016年3月4日
	 * By 刘雄武
	 * @param custCode
	 * @return
	 */
	public String checkCustomerTransfer(String custCode) {
		CustomerManagerinfo cManagerinfo = ctdao.getCustomerManagerbyCode(custCode);
		//是否成单
		List<IntCard> cards = tripleCheckInfoService.checkCardIsSingle(cManagerinfo.getCustMobilephone());
		if (ArrayHelper.isNotEmpty(cards)) {
			return "IS_SINGLE";
		}

		//是否有审核人
		return tripleCheckInfoService.checkHaveAuditor(cManagerinfo, custCode);
	}
	
	/**
	 * 根据客户编号获取理财经理营业部信息
	 * 2016年3月4日
	 * By 刘雄武
	 * @param custCode
	 * @return
	 */
	public BaseBusinessView getCustomerManagerbyCode(String custCode){
		CustomerManagerinfo CManagerinfo = ctdao.getCustomerManagerbyCode(custCode);
		FortuneOrg org = RelationShipUtil.getCityOrg(CManagerinfo.getOldmanagerId());
		if(org!=null){
			CManagerinfo.setBranchCompanyId(org.getId());
		}
		FortuneOrg orgd = RelationShipUtil.getDistrictOrg(CManagerinfo.getOldmanagerId());
		if(orgd!=null){
			CManagerinfo.setDistrictCompanyId(orgd.getId());
		}
		CustomertransferLaunchView view = new CustomertransferLaunchView();
		view.setCTManagerinfo(CManagerinfo);
		return view;
	}
	
	/**
	 * 根据工号姓名等查询理财经理信息
	 * 2016年3月5日
	 * By 刘雄武
	 * @param query
	 * @return
	 */
	public Page<CustomerManagerinfo> getManagerinfobyCode(Page<CustomerManagerinfo> page,CustomerQueryView query){
		
		PageBounds pageBounds=new PageBounds(page.getPageNo(), page.getPageSize());
		pageBounds.setFilterOrderBy(BooleanType.FALSE);
		pageBounds.setCountBy("managerId");
		PageList<CustomerManagerinfo> CManagerinfo = ctdao.getManagerinfobyCode(query,pageBounds);
		PageUtil.convertPage(CManagerinfo, page);
		return page;
	}
	
	/**
	 * 转赠申请发起保存数据
	 * 2016年3月7日
	 * By 刘雄武
	 * @param view
	 */
	public void savecustomertransferInfo(CustomertransferLaunchView view){
		CustomerManagerinfo manager = view.getCTManagerinfo();
		FortuneOrg teamNew = RelationShipUtil.getTeamOrg(manager.getManagerId());
		FortuneOrg teamOld = RelationShipUtil.getTeamOrg(manager.getOldmanagerId());
		if(teamNew!=null){
			manager.setTeamCodeNew(teamNew.getId());
		}
		if(teamOld!=null){
			manager.setTeamCodeOld(teamOld.getId());
		}
		manager.setCreateBy(UserUtils.getUserId());
		manager.setCreateTime(new Date());
		manager.setModifyBy(UserUtils.getUserId());
		manager.setModifyTime(new Date());
		manager.setTransferState("1");// 转赠状态为“审批中”
		manager.setCdictdeltype("3");// 交割类型为“转赠”
		manager.setApplyId(view.getApplyId());
		manager.preInsert();
		ctdao.savecustomertransferInfo(manager);
		manager.preUpdate();
		ctdao.updatecustomerinfo(manager);

		getApproveInfofirstUser(manager);
	}

	private void getApproveInfofirstUser(CustomerManagerinfo manager) {
		// 获取初审人名单并记录日志
		String remark = manager.getCustCode() + " 客户的转赠流程发起成功，";
		List<String> orgs = new ArrayList<String>();
		orgs.add(manager.getOlddepartmentId());
		List<UserRoleOrg> list = userRoleOrgDao.checkHaveAuditor(orgs, "f9a04014fd3d4feb99917e37384ba015");
		if (ArrayHelper.isNotEmpty(list)) {
			remark += "营业部初审人如下：";
			for (UserRoleOrg userRoleOrg : list) {
				remark += "<" + userRoleOrg.getUserId() + ">,";
			}
		} else {
			remark += "无营业部初审人";
		}
		intKeyLogService.log(null, DeliveryType.ZZ.value, remark);
	}

	/**
	 * 核验手机号码变更
	 * 2016年3月9日
	 * By 刘雄武
	 * @param customer
	 * @return
	 */
	public String checkCustomer(LenderInitView view) {
		String result = "0";
		String resultM = "0";
		String resultN = "0";
	    Customer cust = new Customer();
		cust.setCustMobilephone(view.getCustomer().getCustMobilephone());
		List<Customer> custer = customerDao.getCustomerbyphone(cust);
		if(!view.getCustomer().getCustMobilephone().equals(view.getCustomer().getCustMobilephoneChanger())){
			if(custer.size()>=1){  // 判断是否存在该电话号码客户
				for (Customer c : custer) {
					if(!c.getManagerCode().equals(UserUtils.getUserId())){
						 resultN = "1";
					}
				}
				if(!resultN.equals("1")){  // 判断是理财经理是否是自己	
					for (Customer c : custer) {
						if(!c.getCustState().equals(CustomerState.ZXGT.value)){
							resultM = "1";
						}
					}
				}else{
					result = "3";
					return result;
				}
				if(!resultM.equals("1")){  // 判断是否咨询状态
					result = "1";
					return result;	
				}else{
					result = "2";
					return result;
				}
			}else{
			   result = "4";
			   return result;	
			}
		}else{
			result = "4";
			return result;
		}
	}
	
}
