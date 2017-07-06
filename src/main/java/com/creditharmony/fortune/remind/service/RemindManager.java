package com.creditharmony.fortune.remind.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.SmsRemindType;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.fortune.remind.dao.RemindDao;
import com.creditharmony.fortune.remind.entity.ext.SmsSendListEx;
import com.creditharmony.fortune.remind.view.SmsSendListView;
import com.google.common.collect.Lists;

/**
 * 消息提醒Manager
 * @Class Name RemindManager
 * @author 韩龙
 * @Create In 2015年11月27日
 */
@Service
public class RemindManager extends CoreManager<RemindDao, SmsSendListEx>{
	
	/**
	 * 参数对象分页查询
	 */
	public Page<SmsSendListEx> findPage(Page<SmsSendListEx> page,SmsSendListEx smsSendListEx){
		   String dataRights = DataScopeUtil.getDataScope("a", SystemFlag.FORTUNE.value);
				if(StringUtils.isNotEmpty(dataRights)){
					Map<String, String> sqlMap = new HashMap<String, String>();
					sqlMap.put("dataRights", dataRights);
					smsSendListEx.setSqlMap(sqlMap);
				} 
		PageBounds pageBounds=new PageBounds(page.getPageNo(), page.getPageSize());
		PageList<SmsSendListEx> pageList = (PageList<SmsSendListEx>)super.dao.findList(smsSendListEx,pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * Map分页查询
	 * 2015年12月28日
	 * By 韩龙
	 * @param page
	 * @param map
	 * @return
	 */
	public Page<SmsSendListEx> findPage(Page<SmsSendListEx> page,Map<String,Object> map){
		PageBounds pageBounds=new PageBounds(page.getPageNo(), page.getPageSize());
		PageList<SmsSendListEx> pageList = (PageList<SmsSendListEx>)super.dao.findList(map,pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}

	/**
	 * 导出参数查询
	 * 2015年12月28日
	 * By 韩龙
	 * @param map
	 * @return
	 */
	public List<SmsSendListEx> findList(Map<String,Object> map){
		List<SmsSendListEx> pageList = super.dao.findList(map);
		return pageList;
	}
	
	/**
	 * 前10天到期详细数据查询
	 * 2015年12月8日
	 * By 韩龙
	 * @param loanCode
	 * @param remindType
	 * @return
	 */
	public SmsSendListEx getRemindDetail(String loanCode,String remindType){
		logger.info("RemindServic.getRemindDetail:前提10天到期详细数据查询");
		SmsSendListView smsSendListView=new SmsSendListView();
		// 设置检索条件
		Map<String,Object> parameter=new HashMap<String,Object>();
		parameter.put("loanCode", loanCode);
		SmsSendListEx smsSendListExt = dao.getRemindDetail(parameter);
		logger.info("检索内容为："+smsSendListExt);
		if(smsSendListExt==null){
			return new SmsSendListEx();
		}
//		BeanUtils.copyProperties(smsSendListExt, smsSendListView);
		// 消息提醒-->消息到期提醒-->内部转账对应出借列表
		if(remindType!=null && remindType.
				equals(SmsRemindType.DQTX.value)){
			parameter.put("loanCode", smsSendListExt.getfApplyCode());
			SmsSendListEx smsSendListEx = dao.getRemindDetail(parameter);
			if(smsSendListEx!=null){
				List<SmsSendListEx>list=Lists.newArrayList();
				list.add(smsSendListEx);
				smsSendListView.setSmsSendListEx(list);
			}
		}
		return smsSendListExt;
	}
	
	/**
	 * 出借金额计算
	 * 2016年2月15日
	 * By 韩龙
	 * @param parameter
	 * @return
	 */
	public Map<String,String> getTotalLendMoney(SmsSendListEx smsSendListEx){
		return dao.getTotalLendMoney(smsSendListEx);
	}
	
	/**
	 * 参数对象分页查询（三个月内到期提醒）
	 * 2016年10月12日
	 * By liusl
	 * @param page
	 * @param smsSendListEx
	 * @return
	 */
	public Page<SmsSendListEx> findThreeMonthPage(Page<SmsSendListEx> page,SmsSendListEx smsSendListEx){
		   String dataRights = DataScopeUtil.getDataScope("a", SystemFlag.FORTUNE.value);
				if(StringUtils.isNotEmpty(dataRights)){
					Map<String, String> sqlMap = new HashMap<String, String>();
					sqlMap.put("dataRights", dataRights);
					smsSendListEx.setSqlMap(sqlMap);
				} 
		PageBounds pageBounds=new PageBounds(page.getPageNo(), page.getPageSize());
		PageList<SmsSendListEx> pageList = (PageList<SmsSendListEx>)super.dao.findThreeMonthPage(smsSendListEx,pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 出借金额计算（三个月内到期提醒）
	 * 2016年10月12日
	 * By liusl
	 * @param smsSendListEx
	 * @return
	 */
	public Map<String,String> getTotalLendMoneyNew(SmsSendListEx smsSendListEx){
		return dao.getTotalLendMoneyNew(smsSendListEx);
	}
}
