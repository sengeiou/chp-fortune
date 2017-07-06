package com.creditharmony.fortune.change.lend.finish.manager;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.LendchgType;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.change.lend.apply.dao.LendChangeDao;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApply;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApplyEx;
import com.creditharmony.fortune.change.lend.apply.entity.LendQueryView;
import com.creditharmony.fortune.change.lender.excel.CompareChange;
import com.creditharmony.fortune.change.lender.excel.ExportField;
import com.creditharmony.fortune.template.entity.LendChangeOutExcel;
import com.google.common.collect.Lists;

/**
 * 出借信息修改service层
 * 
 * @Class Name LendChangeManager
 * @author 刘雄武
 * @Create In 2015年12月1日
 */
@Service
public class LendChangeFinishManager extends
		CoreManager<LendChangeDao, LendLoanApplyEx> {
	@Resource
	private LendChangeDao lecDao;

	/**
	 * 获取申请信息变更已办 2015年12月28日 By 刘雄武
	 * 
	 * @param page
	 * @param queryView
	 * @return
	 */
	public Page<LendLoanApply> getLendChangeFinish(Page<LendLoanApply> page,
			LendQueryView queryView) {
		PageBounds pageBounds = new PageBounds(page.getPageNo(),
				page.getPageSize());
		// queryView.setDictChangeState(LenderchgState.SHTG.value);
		queryView.setDictChangeType(LendchgType.LEND_CHG.value);
		queryView.setAccountType(LendchgType.TRUSTESSHIP_CARD_CHA.value);
		queryView.setCreateBy(UserUtils.getUserId());
		pageBounds.setCountBy("applyId");
		PageList<LendLoanApply> pageList = (PageList<LendLoanApply>) lecDao
				.getLendChangeFinish(queryView, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}

	/**
	 * 获取出借信息查询数据 2015年12月15日 By 刘雄武
	 * 
	 * @param page
	 * @param queryView
	 * @return
	 */
	public Page<LendLoanApply> queryList(Page<LendLoanApply> page,
			LendQueryView Query) {

		String dataRights = DataScopeUtil.getDataScope("ta",
				SystemFlag.FORTUNE.value);
		if (StringUtils.isNotEmpty(dataRights)) {
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			Query.setSqlMap(sqlMap);
		}
		PageBounds pageBounds = new PageBounds(page.getPageNo(),
				page.getPageSize());
		pageBounds.setCountBy("applyId");
		PageList<LendLoanApply> pageList = (PageList<LendLoanApply>) this.lecDao
				.queryList(Query, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}

	/**
	 * 出借信息变更查询导出Excel 2016年3月2日 By 刘雄武
	 * 
	 * @param query
	 * @return
	 */
	public List<LendChangeOutExcel> findListExportModel(LendQueryView query,
			HttpServletResponse response) {
		List<LendChangeOutExcel> lendChangeOutExcelList = Lists.newArrayList();
		if (query == null) {
			query = new LendQueryView();
		}
		query.setDictChangeType(LendchgType.LEND_CHG.value);
		query.setAccountType(LendchgType.TRUSTESSHIP_CARD_CHA.value);
		String dataRights = DataScopeUtil.getDataScope("ta",
				SystemFlag.FORTUNE.value);
		if (StringUtils.isNotEmpty(dataRights)) {
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			query.setSqlMap(sqlMap);
		}
		CompareChange compareChange = new CompareChange();
		compareChange.exportLendData(query, response,
				ExportField.lendChange_title, ExportField.lendChange_sql,
				"出借信息变更");

		// List<LendLoanExSearch> lendExcel = lecDao.queryExport(query);

		/*
		 * // 检索出列表 List<LendChangeExcel> lendChangeExcelList = new
		 * ArrayList<LendChangeExcel>();
		 * 
		 * int ExcelLength = lendExcel.size(); for (int i = 0; i < ExcelLength;
		 * i++) { LendLaunchView after = (LendLaunchView)
		 * JsonMapper.fromJsonString(lendExcel.get(i).getLendAfter(),
		 * LendLaunchView.class); LendLaunchView begin = (LendLaunchView)
		 * JsonMapper.fromJsonString(lendExcel.get(i).getLendBegin(),
		 * LendLaunchView.class); LendChangeExcel lendExcelmodel = new
		 * LendChangeExcel();
		 * lendExcelmodel.setDictApprovalStartDate(lendExcel.get
		 * (i).getDictApprovalStartDate());
		 * lendExcelmodel.setCustName(lendExcel.get(i).getCustName());
		 * lendExcelmodel.setApplyCode(lendExcel.get(i).getApplyCode());
		 * lendExcelmodel.setApplyLendDate(lendExcel.get(i).getApplyLendDate());
		 * lendExcelmodel
		 * .setApplyLendMoney(formatNum(lendExcel.get(i).getApplyLendMoney()));
		 * lendExcelmodel.setProductName(lendExcel.get(i).getProductName());
		 * lendExcelmodel.setApplyBillday(lendExcel.get(i).getApplyBillday());
		 * lendExcelmodel
		 * .setChangeBeginAccountBankId(DictUtils.getDictLabel(begin
		 * .getPayAccount().getAccountBankId(), "tz_open_bank", ""));
		 * lendExcelmodel
		 * .setChangeBeginAccountBranch(begin.getPayAccount().getAccountBranch
		 * ());
		 * lendExcelmodel.setChangeBeginAccountNo(begin.getPayAccount().getAccountNo
		 * ());
		 * lendExcelmodel.setChangeAfterAccountBankId(DictUtils.getDictLabel
		 * (after.getCountAfter().getAccountBankId(), "tz_open_bank", ""));
		 * lendExcelmodel
		 * .setChangeAfterAccountBranch(after.getCountAfter().getAccountBranch
		 * ());
		 * lendExcelmodel.setChangeAfterAccountNo(after.getCountAfter().getAccountNo
		 * ());
		 * lendExcelmodel.setManagerName(lendExcel.get(i).getManagerName());
		 * lendExcelmodel
		 * .setDepartmentName(lendExcel.get(i).getDepartmentName());
		 * lendChangeExcelList.add(lendExcelmodel); } // 判断是否有记录 if(lendExcel ==
		 * null){ return lendChangeOutExcelList; } // 转换为导出excel模板 for
		 * (LendChangeExcel deductPoolEx : lendChangeExcelList) {
		 * LendChangeOutExcel lendChangeOutExcel = new LendChangeOutExcel(); //
		 * copy属性 BeanUtils.copyProperties(deductPoolEx, lendChangeOutExcel);
		 * lendChangeOutExcelList.add(lendChangeOutExcel); }
		 */
		return lendChangeOutExcelList;
	}

	/**
	 * 格式化4位小数 2016年3月24日 By 郭才林
	 * 
	 * @return
	 */
	public String formatNum(String n) {

		if (n == null || "".equals(n)) {
			return "";
		}
		BigDecimal bd = new BigDecimal(n);
		return bd.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}

}
