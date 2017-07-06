package com.creditharmony.fortune.back.interest.finish.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.BacksmsState;
import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.interest.common.dao.BackInterestCommonDao;
import com.creditharmony.fortune.back.interest.common.service.BackInterestCommonService;
import com.creditharmony.fortune.back.interest.contants.ExportConstant;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.interest.entity.BackInterestLog;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.back.interest.util.ExportBackInterestHelper;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.back.interest.view.BackInterestBeanView;
import com.creditharmony.fortune.back.interest.view.BackInterestListView;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.entity.Product;

/**
 * 回息已办
 * @Class Name FinishBackInterestManager 
 * @author 李志伟
 * @Create In 2015年12月11日
 */
@Service
public class FinishBackInterestManager{
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BackInterestCommonDao backInterestCommonDao;
	@Autowired
	private BackInterestCommonService backInterestCommonService;
	
	/**
	 * 初始化已回息列表
	 * 2016年4月11日
	 * by 李志伟
	 * @param so
	 * @param request
	 * @param response
	 * @return
	 */
	public BackInterestBeanView loadFinishBackInterestList(SearchObject so,
			HttpServletRequest request, HttpServletResponse response) {

		Page<BackInterestListView> page = new Page<BackInterestListView>(request, response);
		BackInterestBeanView bibv = new BackInterestBeanView();
		Map<String,Object> map = new HashMap<String, Object>();
		
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getFinishBackInterestStatus());
		
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		page = backInterestCommonService.loadListAndFind(page,map);
		
		String listTotalMoney = GlobalConstant.MONEY;
		String pageTotalMoney = GlobalConstant.MONEY;
		if(page.getList().size() > 0){
			// 列表中回息总金额
			listTotalMoney = backInterestCommonService.selectSumMoney(map);
			// 当前页回息总金额
			pageTotalMoney = StaticMethodUtil.totalMoney(page.getList());
		}
		List<Product> list = backInterestCommonService.findProductSelect();
		so.setAddrCity(city);
		bibv.setListTotalMoney(listTotalMoney);
		bibv.setPage(page);
		bibv.setPageTotalMoney(pageTotalMoney);
		bibv.setProductList(list);
		bibv.setSo(so);
		return bibv;
	}
	
	/**
	 * 已回息列表导出Excel
	 * 2016年1月8日
	 * by 李志伟
	 * @param map
	 * @return
	 */
	public void exportExl(HttpServletResponse resp, HttpServletRequest req, SearchObject so) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getFinishBackInterestStatus());

		String fileName = ExportConstant.FINISH_EXPORT+""+DateUtils.date2Str(new Date(), GlobalConstant.YYYY_MM_DD);
		String sqlNameSpace = ExportConstant.finish_id;
		String sheetName = "sheet";
		String exportFlag =	ExportConstant.finishFlag;
		ExportBackInterestHelper.exportData(map, resp, fileName, sqlNameSpace, sheetName, exportFlag, ".xlsx");
	}
	
	/**
	 * 已回息退回
	 * 2016年1月25日
	 * by 李志伟
	 * @param bip
	 */
	public String goBack(BackInterestPool bp) {
		
		BackInterestPool bip = new BackInterestPool();
		
		bip.setBackiId(bp.getBackiId());
		bp.setCheckExaminetype(YoN.FOU.value);
		// 排他
		bip.setVerState(StaticMethodUtil.getFinishBackInterestStatus());
		bip.setVerTime(bp.getVerTime());
		
		// 审批信息
		BackInterestLog bil = StaticMethodUtil.madeApproval(bp, BacksmsState.YHXTH.value, "");
		bil.setId(IdGen.uuid());
		bil.setBackiId(bp.getBackiId());
		
		// 回息变更信息
		bip.setRebackFlag(YoN.SHI.value);
		bip.setReturnReason(bil.getCheckExamine());
		bip.setBackMoneyStatus(BacksmsState.YHXTH.value);
		bip.preUpdate();
		
		LoanApply la = backInterestCommonService.getLoanApplyMesg(bp.getLendCode());
		String mesg = "";
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		try {
			
			// 如果这笔出借的状态为已完结，需要把状态更改成未完结
			if(la.getApplyEndStatus().equals(FinishState.YWJ.value)){
				LoanApply le = new LoanApply();
				le.setApplyCode(bp.getLendCode());
				le.setApplyEndStatus(FinishState.WWJ.value);
				le.preUpdate();

				// 历史留痕
				Check hf = StaticMethodUtil.madeHistory(userId, userName, bp.getLendCode(), bil.getType(), FortuneLogNode.INTEREST_FINISH.getName(), bip.getBackiId(), GlobalConstant.YHX_PAGE+""+GlobalConstant.FRIST+""+bil.getType()+";"+ GlobalConstant.LEND_STATE_UPDATE +";" + GlobalConstant.UPDATE_ST + BacksmsState.getBacksmsState(bil.getDictBackiStatus()));
				mesg += backInterestCommonService.doSubmit(bip, bil, hf, le);
			}else{

				// 历史留痕
				Check hf = StaticMethodUtil.madeHistory(userId, userName, bp.getLendCode(), bil.getType(), FortuneLogNode.INTEREST_FINISH.getName(), bip.getBackiId(), GlobalConstant.YHX_PAGE+""+GlobalConstant.FRIST+""+bil.getType()+";"+ GlobalConstant.UPDATE_ST + BacksmsState.getBacksmsState(bil.getDictBackiStatus()));
				mesg += backInterestCommonService.doSubmit(bip, bil, hf);
			}
		} catch (Exception e) {
			
			logger.error(e.getMessage(),"已回息退回失败");
			e.printStackTrace();
			mesg += "[出借编号]"+bp.getLendCode()+"[已回息退回失败]</br>"+e.getMessage();
			backInterestCommonService.addExceptionMesgs(bip.getBackiId(), e, "[回息编号]"+bip.getBackiId()+"[已回息退回失败]", FortuneLogNode.INTEREST_FINISH.getCode());
		}
		if(mesg.equals("")){
			return "true";
		}
		return mesg;
	}
}