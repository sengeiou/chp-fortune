package com.creditharmony.fortune.redemption.history.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mybatis.paginator.domain.Order;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.redemption.common.dao.RedemptionHistoryDao;
import com.creditharmony.fortune.redemption.common.entity.ext.Redemptionex;
import com.creditharmony.fortune.redemption.common.view.RedemptionApplyEntity;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.template.entity.redemption.RedemptionApplyExportModel;
import com.creditharmony.fortune.template.entity.redemption.RedemptionApprovalExportModel;
import com.google.common.collect.Lists;

/**
 * 描述：赎回已办service
 * @Class Name RedemptionHistoryManager
 * @author 陈广鹏
 * @Create In 2015年12月1日
 */
@Service
@Transactional(value = "fortuneTransactionManager",readOnly = true)
public class RedemptionHistoryManager extends CoreManager<RedemptionHistoryDao, Redemptionex>{
	
	@Autowired
	private RedemptionHistoryDao historyDao;

	/**
	 * 根据出借编号获取单条赎回信息
	 * 2015年12月1日
	 * By 陈广鹏
	 * @param lendCode
	 * @return
	 */
	public RedemptionApplyEntity getRedemptionByLendCode(String lendCode) {
		
		RedemptionApplyEntity entity = historyDao.getRedemptionByLendCode(lendCode);
		return entity;
	}
	
	/**
	 * 获取审批已办分页数据
	 * 2015年12月9日
	 * By 陈广鹏
	 * @param page
	 * @param redemptionex
	 * @return
	 */
	public Page<Redemptionex> findApprovalList(Page<Redemptionex> page,
			Redemptionex redemptionex) {
		String sortString = RedeemConstant.HISTORY_ORDER_RULE;
		redemptionex.setUserId(UserUtils.getUserId()); //获取登录用户自己的已办
		
		String city = redemptionex.getAddrCity();
		if (!ObjectHelper.isEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			redemptionex.setAddrCity(c);
		}
		
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize(), Order.formString(sortString ));
		pageBounds.setCountBy("redemption_id");
		PageList<Redemptionex> dataList = (PageList<Redemptionex>) historyDao.findApprovalList(redemptionex, pageBounds);
		PageUtil.convertPage(dataList, page);
		
		redemptionex.setAddrCity(city);
		return page;
	}

	/**
	 * 获取申请已办分页数据
	 * 2015年12月22日
	 * By 陈广鹏
	 * @param page
	 * @param redemptionex
	 * @return
	 */
	public Page<Redemptionex> findApplyList(Page<Redemptionex> page,
			Redemptionex redemptionex) {
		String sortString = RedeemConstant.HISTORY_ORDER_RULE;
		redemptionex.setUserId(UserUtils.getUserId()); //获取登录用户自己的已办
		
		String city = redemptionex.getAddrCity();
		if (!ObjectHelper.isEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			redemptionex.setAddrCity(c);
		}
		
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize(), Order.formString(sortString ));
		pageBounds.setCountBy("redemption_id");
		PageList<Redemptionex> dataList = (PageList<Redemptionex>) historyDao.findApplyList(redemptionex, pageBounds);
		PageUtil.convertPage(dataList, page);
		redemptionex.setAddrCity(city);
		return page;
	}

	/**
	 * 获取申请查看分页数据
	 * 2015年12月22日
	 * By 陈广鹏
	 * @param page
	 * @param redemptionex
	 * @return
	 */
	public Page<Redemptionex> findApplyCheck(Page<Redemptionex> page,
			Redemptionex redemptionex) {
		
				// 配置数据权限
				String dataRights = DataScopeUtil.getDataScope("loan", SystemFlag.FORTUNE.value);
				if(StringUtils.isNotEmpty(dataRights)){
					Map<String, String> sqlMap = new HashMap<String, String>();
					sqlMap.put("dataRights", dataRights);
					redemptionex.setSqlMap(sqlMap);
				}
				
		String sortString = RedeemConstant.HISTORY_ORDER_RULE;
		
		String city = redemptionex.getAddrCity();
		if (!ObjectHelper.isEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			redemptionex.setAddrCity(c);
		}
		
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize(), Order.formString(sortString ));
		pageBounds.setCountBy("redemption_id");
		PageList<Redemptionex> dataList = (PageList<Redemptionex>) historyDao.findApplyCheck(redemptionex, pageBounds);
		PageUtil.convertPage(dataList, page);
		redemptionex.setAddrCity(city);
		return page;
	}

	/**
	 * 获取审批查看分页数据
	 * 2015年12月22日
	 * By 陈广鹏
	 * @param page
	 * @param redemptionex
	 * @return
	 */
	public Page<Redemptionex> findApprovalCheck(Page<Redemptionex> page,
			Redemptionex redemptionex) {
		
		// 配置数据权限
		String dataRights = DataScopeUtil.getDataScope("loan", SystemFlag.FORTUNE.value);
		if(StringUtils.isNotEmpty(dataRights)){
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			redemptionex.setSqlMap(sqlMap);
		}
		String sortString = RedeemConstant.APPROVAL_ORDER_RULE;
		
		String city = redemptionex.getAddrCity();
		if (!ObjectHelper.isEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			redemptionex.setAddrCity(c);
		}
		
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize(), Order.formString(sortString ));
		pageBounds.setCountBy("redemption_id");
		PageList<Redemptionex> dataList = (PageList<Redemptionex>) historyDao.findApprovalCheck(redemptionex, pageBounds);
		PageUtil.convertPage(dataList, page);
		redemptionex.setAddrCity(city);
		return page;
	}

	/**
	 * 获取申请查看列表导出数据
	 * 2015年12月23日
	 * By 陈广鹏
	 * @param redemptionex
	 * @return
	 */
	public List<RedemptionApplyExportModel> findApplyExportList(Redemptionex redemptionex) {
		List<RedemptionApplyExportModel> modelList = Lists.newArrayList();
		if(redemptionex==null){
			redemptionex=new Redemptionex();
		}
		String city = redemptionex.getAddrCity();
		if (!ObjectHelper.isEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			redemptionex.setAddrCity(c);
		}
		// 检索出列表
		List<Redemptionex> list = historyDao.findApplyCheck(redemptionex);
		if (list==null) {
			return modelList;
		}
		// 转换为导出excel模板
		for (Redemptionex redeem : list) {
			RedemptionApplyExportModel model = new RedemptionApplyExportModel();
			BeanUtils.copyProperties(redeem, model);
			modelList.add(model);
		}
		redemptionex.setAddrCity(city);
		return modelList;
	}
	
	/**
	 * 获取审批查看列表导出数据
	 * 2015年12月23日
	 * By 陈广鹏
	 * @param redemptionex
	 * @return
	 */
	public List<RedemptionApprovalExportModel> findApprovalExportList(Redemptionex redemptionex) {
		List<RedemptionApprovalExportModel> modelList = Lists.newArrayList();
		if(redemptionex==null){
			redemptionex=new Redemptionex();
		}
		String city = redemptionex.getAddrCity();
		if (!ObjectHelper.isEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			redemptionex.setAddrCity(c);
		}
		// 检索出列表
		List<Redemptionex> list = historyDao.findApprovalCheck(redemptionex);
		if (list==null) {
			return modelList;
		}
		// 转换为导出excel模板
		for (Redemptionex redeem : list) {
			RedemptionApprovalExportModel model = new RedemptionApprovalExportModel();
			BeanUtils.copyProperties(redeem, model);
			modelList.add(model);
		}
		redemptionex.setAddrCity(city);
		return modelList;
	}
	
}
