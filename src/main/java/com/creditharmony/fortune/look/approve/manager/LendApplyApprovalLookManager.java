package com.creditharmony.fortune.look.approve.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.dict.util.DictUtils;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.workflow.entity.LendApplyLog;
import com.creditharmony.fortune.look.apply.entity.LendApplyLookExportApprovalPassDetailExcelEx;
import com.creditharmony.fortune.look.apply.view.LendLookListView;
import com.creditharmony.fortune.look.approve.dao.LendApplyApprovalLookDao;
import com.creditharmony.fortune.look.approve.entity.LendApprovalLookExportExcelEx;
import com.creditharmony.fortune.utils.OptionUtil;

/**
 * 出借申请审批查看
 * 
 * @Class Name LendApplyApprovalLookManager
 * @author 李志伟
 * @Create In 2015年12月23日
 */
@Service
@Transactional(value = "fortuneTransactionManager", readOnly = true)
public class LendApplyApprovalLookManager {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private LendApplyApprovalLookDao lendApplyApprovalLookDao;

	/**
	 * 出借申请审批查看列表 2016年1月21日 by 李志伟
	 * 
	 * @param page
	 * @param map
	 * @return
	 */
	public Page<LoanApply> loadLendApplyApprovalLookList(Page<LoanApply> page,
			Map<String, Object> map) {
		String dataRights = DataScopeUtil.getDataScope("a",
				SystemFlag.FORTUNE.value);
		if (StringUtils.isNotEmpty(dataRights)) {
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			map.put("sqlMap", sqlMap);
			logger.info("加载出借申请审批查看列表，所赋予权限是：" + dataRights);
		}
		PageBounds pageBounds = new PageBounds(page.getPageNo(),
				page.getPageSize());
		PageList<LoanApply> pageList = (PageList<LoanApply>) lendApplyApprovalLookDao
				.loadLendApplyApprovalLookList(map, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}

	/**
	 * 初始化出借审批查看页客户信息 2015年12月28日 by 李志伟
	 * 
	 * @param code
	 * @return
	 */
	public Customer goLendApplyApprovalPage(String code) {

		Customer cs = lendApplyApprovalLookDao.goLendApplyApprovalPage(code);
		return cs;
	}

	/**
	 * 初始化出借申请查看页出借数据 2015年12月29日 by 李志伟
	 * 
	 * @param id
	 * @return
	 */
	public LoanApply loadLendApprovalLookPage(String id) {

		LoanApply la = lendApplyApprovalLookDao.loadLendApprovalPage(id);
		return la;
	}

	/**
	 * 初始化出借审批信息 2015年12月29日 by 李志伟
	 * 
	 * @param code
	 * @return
	 */
	public LendApplyLog loadLendApprovalMesg(String code) {

		LendApplyLog lp = lendApplyApprovalLookDao.loadLendApprovalMesg(code);
		return lp;
	}

	/**
	 * 初始化银行信息 2016年1月21日 by 李志伟
	 * 
	 * @param map
	 * @return
	 */
	public List<LoanApply> loadBankMesg(Map<String, Object> map) {

		List<LoanApply> list = lendApplyApprovalLookDao.loadBanMesg(map);
		return list;
	}

	/**
	 * 出借审批查看提前赎回部分 2016年3月4日 by 李志伟
	 * 
	 * @param map
	 * @return
	 */
	public List<LendLookListView> findRedeemLendMesg(Map<String, Object> map) {
		String str = (String) map.get("code");
		String[] spt = str.split("-");
		str = spt[0];
		map.put("str", str);
		List<LendLookListView> redeemLendlist = lendApplyApprovalLookDao
				.findRedeemLendMesg(map);
		return redeemLendlist;
	}

	/**
	 * 导出Excel 2016年3月24日 By 肖长伟
	 * 
	 * @param exportListParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<LendApprovalLookExportExcelEx> getExportExcelList(
			Map<String, Object> exportListParam, Page page) {

		String dataRights = DataScopeUtil.getDataScope("app",
				SystemFlag.FORTUNE.value);
		if (StringUtils.isNotEmpty(dataRights)) {
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			exportListParam.put("sqlMap", sqlMap);
		}

		exportListParam.put("OFFICE_STAFF", FortuneRole.OFFICE_STAFF.id);
		exportListParam.put("PASS", LendState.SPTG.value);
		exportListParam.put("NOTPASS", LendState.SPBTG.value);

		exportListParam.put("pageSize", page.getPageSize());
		exportListParam.put("startRow",
				(page.getPageNo() - 1) * page.getPageSize());

		List<LendApprovalLookExportExcelEx> list = lendApplyApprovalLookDao
				.getExportExcelList(exportListParam);
		setDictLabel(list);
		return list;
	}

	/**
	 * 获取数据条数 2016年4月25日 By 肖长伟
	 * 
	 * @param exportListParam
	 * @return
	 */
	public int getExportExcelListCnt(Map<String, Object> exportListParam) {
		String dataRights = DataScopeUtil.getDataScope("app",
				SystemFlag.FORTUNE.value);
		if (StringUtils.isNotEmpty(dataRights)) {
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			exportListParam.put("sqlMap", sqlMap);
		}
		return lendApplyApprovalLookDao.getExportExcelListCnt(exportListParam);
	}

	/**
	 * 设置list中的字典数据 2016年3月24日 By 肖长伟
	 * 
	 * @param list
	 */
	private void setDictLabel(List<LendApprovalLookExportExcelEx> list) {
		// 导出excel加缓存
		Map<String, String> storeMap = new HashMap<String, String>();
		Map<String, String> errorType = new HashMap<String, String>();
		for (LendApprovalLookExportExcelEx item : list) {

			// 门店
			if (!storeMap.containsKey(item.getStoreName())) {
				storeMap.put(item.getStoreName(),
						OptionUtil.getOrgNameById(item.getStoreName()));
			}
			item.setStoreName(storeMap.get(item.getStoreName()));
			// 错误类型
			if (!errorType.containsKey(item.getErrorType())) {
				errorType.put(item.getErrorType(), DictUtils.getDictLabel(
						item.getErrorType(), "tz_error_type", ""));
			}
			item.setErrorType(errorType.get(item.getErrorType()));

		}

	}

	/**
	 * 获取通过审核的数据 2016年3月31日 By 肖长伟
	 * 
	 * @param exportListParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<LendApplyLookExportApprovalPassDetailExcelEx> getExportApprovalPassDetailExcelList(
			Map<String, Object> exportListParam, Page page) {
		String dataRights = DataScopeUtil.getDataScope("app",
				SystemFlag.FORTUNE.value);
		if (StringUtils.isNotEmpty(dataRights)) {
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			exportListParam.put("sqlMap", sqlMap);
		}

		exportListParam.put("OFFICE_STAFF", FortuneRole.OFFICE_STAFF.id);
		exportListParam.put("PASS", LendState.SPTG.value);
		exportListParam.put("NOTPASS", LendState.SPBTG.value);

		exportListParam.put("pageSize", page.getPageSize());
		exportListParam.put("startRow",
				(page.getPageNo() - 1) * page.getPageSize());

		List<LendApplyLookExportApprovalPassDetailExcelEx> list = lendApplyApprovalLookDao
				.getExportApprovalPassDetailExcelList(exportListParam);
		setDictLabel4Pass(list);
		return list;
	}

	private void setDictLabel4Pass(
			List<LendApplyLookExportApprovalPassDetailExcelEx> list) {
		// 导出excel加缓存
		Map<String, String> storeMap = new HashMap<String, String>();
		Map<String, String> payTypeMap = new HashMap<String, String>();
		Map<String, String> openBankMap = new HashMap<String, String>();
		for (LendApplyLookExportApprovalPassDetailExcelEx item : list) {

			// 门店
			if (!storeMap.containsKey(item.getStoreName())) {
				storeMap.put(item.getStoreName(),
						OptionUtil.getOrgNameById(item.getStoreName()));
			}
			item.setStoreName(storeMap.get(item.getStoreName()));

			// 付款方式 tz_pay_type
			if (!payTypeMap.containsKey(item.getPayType())) {
				payTypeMap.put(item.getPayType(), DictUtils.getDictLabel(
						item.getPayType(), "tz_pay_type", ""));
			}
			item.setPayType(payTypeMap.get(item.getPayType()));
			// 付款银行 tz_open_bank
			if (!openBankMap.containsKey(item.getOpenBank())) {
				openBankMap.put(item.getOpenBank(), DictUtils.getDictLabel(
						item.getOpenBank(), "tz_open_bank", ""));
			}
			item.setOpenBank(openBankMap.get(item.getOpenBank()));

			// 申请状态 tz_lend_state

			// 申请状态 tz_for_apply_status
			// if (! appStatusMap.containsKey(item.getLendStatus())) {
			// appStatusMap.put(item.getLendStatus(),
			// DictUtils.getDictLabel(item.getLendStatus(), "tz_lend_state", ""
			// ));
			// }
			// item.setLendStatus(appStatusMap.get(item.getLendStatus()));
			String lendStatus = item.getLendStatus();
			if (StringUtils.isNotBlank(lendStatus)) {
				if ("3".equals(lendStatus) || "10".equals(lendStatus)
						|| "11".equals(lendStatus)) {
					item.setLendStatus("通过审批");
				} else if ("12".equals(lendStatus)) {
					item.setLendStatus("划扣超时");
				} else {
					item.setLendStatus("");
				}
			}
		}

	}

	/**
	 * 出借申请审批检索列表专用于计算总金额 2016年4月19日 By 刘雄武
	 * 
	 * @param map
	 * @return
	 */
	public String loadLendApplyApprovalLookListForMoney(Map<String, Object> map) {
		String sum = lendApplyApprovalLookDao
				.loadLendApplyApprovalLookListForMoney(map);
		return sum;
	}

	/**
	 * 查询数据量,导出审批通过的出借excel 2016年4月25日 By 肖长伟
	 * 
	 * @param exportListParam
	 * @return
	 */
	public int getExportApprovalPassDetailExcelListCnt(
			Map<String, Object> exportListParam) {
		String dataRights = DataScopeUtil.getDataScope("app",
				SystemFlag.FORTUNE.value);
		if (StringUtils.isNotEmpty(dataRights)) {
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			exportListParam.put("sqlMap", sqlMap);
		}
		return lendApplyApprovalLookDao
				.getExportApprovalPassDetailExcelListCnt(exportListParam);
	}

}