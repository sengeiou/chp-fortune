package com.creditharmony.fortune.cutoff.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.DeleteFlagType;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.ConfigStatus;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.dao.OrgDao;
import com.creditharmony.core.users.entity.Org;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.cutoff.dao.CutOffDao;
import com.creditharmony.fortune.cutoff.entity.CutOff;

/**
 * 截单管理类
 * 
 * @Class Name CutOffManager
 * @author 周树华
 * @Create In 2015年2月2日
 */
@Service
@Transactional(value = "fortuneTransactionManager", readOnly = true)
public class CutOffManager  extends CoreManager<CutOffDao, CutOff>  {
	
	@Autowired
	private OrgDao orgDao;
	public Page<CutOff> findList(Page<CutOff> page, CutOff cutOff) {
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
        PageList<CutOff> pageList = (PageList<CutOff>)dao.findList(cutOff, pageBounds);
        int offset = pageBounds.getOffset();
        int totalCount = pageList.getPaginator().getTotalCount();
        if(offset >= totalCount){
      	  int totalPages = pageList.getPaginator().getTotalPages();
      	  pageBounds.setPage(totalPages);
      	  pageList = (PageList<CutOff>)dao.findList(cutOff, pageBounds);
        }
        PageUtil.convertPage(pageList, page);
        return page;
	}
	
	@Transactional(readOnly=false)
	public void saveList(List<CutOff> list) {
		for(CutOff cutOff:list){
			super.save(cutOff);
		}
	}
	
	public List<String> findOrgIdList() {
        return dao.findOrgIdList();
	}
	
	/**
	 * 跟新
	 * 2016年3月5日
	 * By 周俊
	 */
	@Transactional(readOnly=false)
	public void updateEdit(CutOff cutOff){
		dao.updateEdit(cutOff);
	}
	
	/**
	 * 获得当前截单信息
	 * 2016年2月24日
	 * By 周俊
	 * @param applyDeductDayParam 计划划扣日期
	 * @return
	 */
	public void findCutOffInfo(String applyDeductDayParam,String applyDate){
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);		
		String orgId = user.getDepartment().getId();
		Org org = new Org();
		org.setId(orgId);
		org.setDelFlag(DeleteFlagType.NORMAL);
		List<Org> list = orgDao.selOrg(org);
		if (ArrayHelper.isNotEmpty(list)) {
			CutOff cutOff = new CutOff();
			cutOff.setDelFlag(ConfigStatus.QY.value);
			cutOff.setOrgId(list.get(0).getCode());
			cutOff = dao.get(cutOff);
			if (!ObjectHelper.isEmpty(cutOff)) {
				// 获得当前营业部的截单时间
				String endTime = cutOff.getEndTime();
				// 计划划扣日期与当前日期相比
				String newDate = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
				if (StringUtils.isNotBlank(applyDate)) {
					if (!newDate.equals(applyDate)&&applyDeductDayParam.equals(newDate)) {
						if (DateUtils.dateAfter(new Date(),DateUtils.parseDate(newDate +" "+ endTime))) {// 上传附件的时间在截单时间之后
							throw new ServiceException("你上传附件的时间在截单时间" + endTime
									+ "之后,你可以修改划扣日期继续提交申请");
						}
					}
				}else if (applyDeductDayParam.equals(newDate)) {
					// 上传该出借申请附件的时间与截单时间的判断
					if (DateUtils.dateAfter(new Date(),DateUtils.parseDate(newDate +" "+ endTime))) {// 上传附件的时间在截单时间之后
						throw new ServiceException("你上传附件的时间在截单时间" + endTime
								+ "之后,你可以修改划扣日期继续提交申请");
					}
				}
			}
		}
	}
	/*
	public String findCutOffInfo(String applyDeductDayParam){
		Date applyDeductDay = DateUtils.parseDate(applyDeductDayParam);
		List<UserRoleOrgEx> list = RoleOrgUtil.findOrgType(FortuneOrgType.STORE.name,null);
		if(!ArrayHelper.isNotEmpty(list)){
			return "获取营业部失败";
		}
		String orgId = list.get(0).getOrgId();
		CutOff cutOff = new CutOff();
		cutOff.setOrgId(orgId);
		cutOff = dao.get(cutOff);
		// 获得当前营业部的截单时间
		String endTime = cutOff.getEndTime();
		// 计划划扣日期与当前日期相比
		String dateString = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
		if (!applyDeductDayParam.equals(dateString)) {
			return null;
		}
		if (applyDeductDayParam.equals(dateString)) {
			String info;
			// 上传该出借申请附件的时间与截单时间的判断
			if(DateUtils.dateAfter(new Date(),DateUtils.parseDate(dateString+endTime))){// 上传附件的时间在截单时间之后
				info = "你上传附件的时间在截单时间之后,你可以修改划扣日期继续提交申请";
			}else {
				info = null;
			}
			return info;
		}
		
		return null;
	}*/
	
}
