package com.creditharmony.fortune.contract.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.dao.OrgDao;
import com.creditharmony.core.users.entity.Org;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.type.BaseDeptOrgType;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.contract.dao.ContractStatisticsDao;
import com.creditharmony.fortune.contract.view.ContractStatisticsExcelView;
import com.creditharmony.fortune.contract.view.ContractStatisticsParamView;
import com.creditharmony.fortune.contract.view.ContractStatisticsView;
/**
 * 合同统计
 * @Class Name ContractStatisticsManager
 * @author 李放
 * @Create In 2015年12月16日
 */
@Service
public class ContractStatisticsManager{

	@Autowired
	private ContractStatisticsDao contractStatisticsDao;
	@Autowired
	private   OrgDao  orgDao;

	/**
	 * 分页查询统计数据
	 * @param page
	 * @param contractStatistics 查询条件
	 * @return
	 */
	public Page<ContractStatisticsView> listStatisticsData(
			Page<ContractStatisticsView> page,
			ContractStatisticsParamView contractStatistics) {
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		//权限控制
		User userInfo = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		Org org = orgDao.get(userInfo.getDepartment().getId());
		if(!BaseDeptOrgType.LENDER_DEPT.key.equals(org.getType())){
			contractStatistics.setOrgId(userInfo.getDepartment().getId());
		}
		PageList<ContractStatisticsView> dataList = (PageList<ContractStatisticsView>) contractStatisticsDao.listStatisticsData(contractStatistics, pageBounds);
		PageUtil.convertPage(dataList, page);
		return page;
	}


	/**
	 * 导出统计数据
	 * @param contractStatistics 查询条件
	 * @return
	 */
	public List<ContractStatisticsExcelView> listStatisticsExcelData(
			ContractStatisticsParamView contractStatistics) {
		//权限控制
		User userInfo = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		Org org = orgDao.get(userInfo.getDepartment().getId());
		if(!BaseDeptOrgType.LENDER_DEPT.key.equals(org.getType())){
			contractStatistics.setOrgId(userInfo.getDepartment().getId());
		}
		return contractStatisticsDao.listStatisticsExcelData(contractStatistics);
	}
}
