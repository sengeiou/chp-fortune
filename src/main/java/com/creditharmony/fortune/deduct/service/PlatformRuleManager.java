package com.creditharmony.fortune.deduct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.PlateformRuleState;
import com.creditharmony.core.mybatis.paginator.domain.Order;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.deduct.dao.PlatformRuleDao;
import com.creditharmony.fortune.deduct.entity.PlatformRule;

/**
 * 三方平台规则表Service
 * 
 * @Class Name PlatformRuleManager
 * @author 陈广鹏
 * @Create In 2016年2月15日
 */
@Service
public class PlatformRuleManager extends CoreManager<PlatformRuleDao, PlatformRule> {
	
	@Autowired
	private PlatformRuleDao ruleDao;

	/**
	 * 查询平台规则分页列表 
	 * 2016年2月15日 
	 * By 陈广鹏
	 * @param page
	 * @param rule
	 * @return
	 */
	public Page<PlatformRule> findPlatformRuleList(Page<PlatformRule> page,
			PlatformRule rule) {
		String sortString = "create_time.desc";
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize(), Order.formString(sortString));
		PageList<PlatformRule> dataList = (PageList<PlatformRule>) ruleDao.findList(rule, pageBounds);
		PageUtil.convertPage(dataList, page);
		return page;
	}

	/**
	 * 启用规则
	 * 2016年2月16日
	 * By 陈广鹏
	 * @param rule
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void startUse(PlatformRule rule) {
		if (ObjectHelper.isEmpty(rule.getId())) {
			return;
		}
		String[] ids = rule.getId().split(",");
		PlatformRule r = new PlatformRule();
		for (String id : ids) {
			PlatformRule ru = ruleDao.get(id);
			if (PlateformRuleState.TY.value.equals(ru.getStatus())) {
				r.setId(id);
				r.setStatus(PlateformRuleState.QY.value);
				ruleDao.update(r);
			}
		}
	}
	
	/**
	 * 停用规则
	 * 2016年2月16日
	 * By 陈广鹏
	 * @param rule
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void stopUse(PlatformRule rule) {
		if (ObjectHelper.isEmpty(rule.getId())) {
			return;
		}
		String[] ids = rule.getId().split(",");
		PlatformRule r = new PlatformRule();
		for (String id : ids) {
			PlatformRule ru = ruleDao.get(id);
			if (PlateformRuleState.QY.value.equals(ru.getStatus())) {
				r.setId(id);
				r.setStatus(PlateformRuleState.TY.value);
				ruleDao.update(r);
			}
		}
	}

}
