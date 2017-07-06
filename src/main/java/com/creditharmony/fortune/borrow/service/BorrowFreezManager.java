package com.creditharmony.fortune.borrow.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.CreditState;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.fortune.borrow.dao.BorrowFreezeDao;
import com.creditharmony.fortune.borrow.entity.ex.BorrowFreezeEx;
import com.creditharmony.fortune.borrow.entity.ex.BorrowFreezeLookEx;
import com.creditharmony.fortune.borrow.utils.ReckonUtils;
import com.creditharmony.fortune.borrow.view.BorrowView;
import com.creditharmony.fortune.template.entity.BorrowFreezeExcel;

/**
 * 债权冻结
 * @Class Name BorrowFreezManager
 * @author 周俊
 * @Create In 2015年12月10日
 */
@Service
public class BorrowFreezManager {
	
	@Autowired
	private BorrowFreezeDao borrowFreezeDao;
	
	/**
	 * 查询债权冻结列表
	 * 2016年2月16日
	 * By 周俊
	 * @param borrowView
	 * @param page
	 * @return
	 */
	public Page<BorrowFreezeEx> findBorrowFreeze(BorrowView borrowView,Page<BorrowFreezeEx> page){
		Map<String, Object> map = objectToMap(borrowView);
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		pageBounds.setCountBy("creditValueId");
		PageList<BorrowFreezeEx> list = (PageList<BorrowFreezeEx>) borrowFreezeDao.findBorrowFreeze(map, pageBounds);
		for (BorrowFreezeEx borrowFreezeEx : list) {
			if (ObjectHelper.isEmpty(borrowFreezeEx.getLoanMonthsSurplus())) {
				borrowFreezeEx.setLoanMonthsSurplus(0);
			}
		}
		PageUtil.convertPage(list, page);
		return page;
	}
	
	/**
	 * 查询总金额
	 * 2016年2月16日
	 * By 周俊
	 * @param borrowView
	 * @return
	 */
	@Transactional(readOnly = true)
	public BigDecimal findCountMoney(BorrowView borrowView){
		Map<String, Object> map = objectToMap(borrowView);
		BigDecimal money = borrowFreezeDao.findAllMoney(map);
		return money;
	}
	
	/**
	 * 被冻结债权的查看
	 * 2015年12月31日
	 * By 周俊
	 * @param code
	 * @param page
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<BorrowFreezeLookEx> borrowFreezeLook(String code,Page<BorrowFreezeLookEx> page){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("code", code);
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		PageList<BorrowFreezeLookEx> list = (PageList<BorrowFreezeLookEx>) borrowFreezeDao.borrowFreezeLook(map,pageBounds);
		PageUtil.convertPage(list,page);
		return page;
	}
	
	/**
	 * 导出Excel
	 * 2015年12月24日
	 * By 周俊
	 * @param map
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<BorrowFreezeExcel> outExcel(BorrowView borrowView){
		Map<String, Object> map = objectToMap(borrowView);
		List<BorrowFreezeEx> outExcel = borrowFreezeDao.outExcel(map);
		List<BorrowFreezeExcel> list = new ArrayList<BorrowFreezeExcel>();
		if (!ArrayHelper.isNotEmpty(outExcel)) {
			return list;
		}
		for (BorrowFreezeEx borrowFreezeEx : outExcel) {
			borrowFreezeEx.setRatio(ReckonUtils.percentage(borrowFreezeEx.getLoanCreditValue(),borrowFreezeEx.getLoanQuota() ));
			if (borrowFreezeEx.getLoanMonthsSurplus()==null) {
				borrowFreezeEx.setLoanMonthsSurplus(0);
			}
			BorrowFreezeExcel borrowFreezeExcel = new BorrowFreezeExcel();
			try {
				BeanUtils.copyProperties(borrowFreezeExcel, borrowFreezeEx);
				borrowFreezeExcel.setLoanMonths(StringUtils.toString(borrowFreezeEx.getLoanMonths()));
				borrowFreezeExcel.setLoanMonthsSurplus(StringUtils.toString(borrowFreezeEx.getLoanMonthsSurplus()));
				borrowFreezeExcel.setLoanBakcmoneyDay(StringUtils.toString(borrowFreezeEx.getLoanBackmoneyDay()));
				list.add(borrowFreezeExcel);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return list;
	}
	
	/**
	 * 将对象转成map
	 * 2015年12月28日
	 * By 周俊
	 * @param borrowMonthableView
	 * @return
	 */
	public Map<String, Object> objectToMap(BorrowView borrowView){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("borrowFreeFlag", CreditState.DJ.value);// 债权状态
		map.put("borrowerName",borrowView.getBorrowerName());// 借款人
		if (StringHelper.isNotEmpty(borrowView.getBorrowerJob())) {
			
			map.put("borrowerJob",borrowView.getBorrowerJob().split(","));// 借款人职业
		}
		if (!ObjectHelper.isEmpty(borrowView.getBorrowCreditValueFrom())) {
			
			map.put("borrowCreditValueFrom",String.valueOf(borrowView.getBorrowCreditValueFrom())); // 可用金额
		}
		if (!ObjectHelper.isEmpty(borrowView.getBorrowCreditValueTo())) {
			
			map.put("borrowCreditValueTo",String.valueOf(borrowView.getBorrowCreditValueTo())); // 可用金额
		}
		if (!ObjectHelper.isEmpty(borrowView.getBorrowMonthsSurplusFrom())) {
			
			map.put("borrowMonthsSurplusFrom", String.valueOf(borrowView.getBorrowMonthsSurplusFrom()));// 可用期数
		}
		
		if (!ObjectHelper.isEmpty(borrowView.getBorrowMonthsSurplusTo())) {
			
			map.put("borrowMonthsSurplusTo",String.valueOf(borrowView.getBorrowMonthsSurplusTo()));// 可用期数
		}
		if (!ObjectHelper.isEmpty(borrowView.getBorrowMonthRate())) {
			
			map.put("borrowMonthRate",String.valueOf(borrowView.getBorrowMonthRate()));// 月利率
		
		}
		map.put("borrowBackmoneyFirdayFrom",borrowView.getBorrowBackmoneyFirdayFrom());// 首次还款日
		map.put("borrowBackmoneyFirdayTo",borrowView.getBorrowBackmoneyFirdayTo());// 首次还款日
		map.put("loanFreezeDayFrom", borrowView.getLoanFreezeDayFrom()); //冻结日开始
		map.put("loanFreezeDayTo", borrowView.getLoanFreezeDayTo()); //冻结日结束
		if (StringHelper.isNotEmpty(borrowView.getBorrowTrusteeFlag())) {
			
			map.put("borrowTrusteeFlag",borrowView.getBorrowTrusteeFlag().split(","));// 债权标识
		}
		if (StringHelper.isNotEmpty(borrowView.getBorrowType())) {
			
			map.put("borrowType",borrowView.getBorrowType().split(","));// 借款类型
		}
		if (StringHelper.isNotEmpty(borrowView.getBorrowBakcmoneyDay())) {
			
			map.put("borrowBakcmoneyDay",borrowView.getBorrowBakcmoneyDay().split(","));// 还款日
		}
		return map;
	}
}
