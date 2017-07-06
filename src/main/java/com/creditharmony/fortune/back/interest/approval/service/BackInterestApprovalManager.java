package com.creditharmony.fortune.back.interest.approval.service;

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
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.interest.common.dao.BackInterestCommonDao;
import com.creditharmony.fortune.back.interest.common.service.BackInterestCommonService;
import com.creditharmony.fortune.back.interest.contants.ExportConstant;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.interest.entity.BackInterestLog;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.DetailsPage;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.back.interest.entity.TelphoneMessage;
import com.creditharmony.fortune.back.interest.util.ExportBackInterestHelper;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.back.interest.view.BackInterestBeanView;
import com.creditharmony.fortune.back.interest.view.BackInterestListView;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.customer.entity.Product;

/**
 * 回息审批
 * @Class Name BackInterestApplyApprovalManager 
 * @author 李志伟
 * @Create In 2015年12月4日
 */
@Service
public class BackInterestApprovalManager {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BackInterestCommonDao backInterestCommonDao;
	@Autowired
	private BackInterestCommonService backInterestCommonService;
	
	/**
	 * 回息审批列表初始化
	 * 2016年4月11日
	 * by 李志伟
	 * @param so
	 * @param request
	 * @param response
	 * @return
	 */
	public BackInterestBeanView loadBackInterestApplylist(SearchObject so, HttpServletRequest request,
			HttpServletResponse response){
		
		Page<BackInterestListView> page = new Page<BackInterestListView>(request, response);
		BackInterestBeanView bb = new BackInterestBeanView();
		Map<String,Object> map = new HashMap<String, Object>();
		
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getBackInterestApprovalStatus());
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
		// 回息产品下拉框
		List<Product> productList = backInterestCommonService.findProductSelect();
		so.setAddrCity(city);
		bb.setSo(so);
		bb.setPage(page);
		bb.setListTotalMoney(listTotalMoney);
		bb.setPageTotalMoney(pageTotalMoney);
		bb.setProductList(productList);
		
		return bb;
	}
	
	/**
	 * 回息审批提交
	 * 2016年4月11日
	 * by 李志伟
	 * @param bip
	 * @return
	 */
	public String goSubmit(BackInterestPool bip) {
		
		BackInterestPool bpp = new BackInterestPool();
		Check hf = new Check();
		BackInterestLog bil = StaticMethodUtil.madeApproval(bip, BacksmsState.DHXSPTH.value, BacksmsState.DHX.value);
		
		bpp.setBackiId(bip.getBackiId());
		bpp.setLendCode(bip.getLendCode());
		bpp.setBackMoneyStatus(bil.getDictBackiStatus());
		bpp.setReturnReason(bil.getCheckExamine());
		bpp.preUpdate();
		
		// 排他条件
		bpp.setVerTime(bip.getVerTime());
		bpp.setVerState(StaticMethodUtil.getBackInterestApprovalStatus());
		
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		hf = StaticMethodUtil.madeHistory(userId, userName, bip.getLendCode(), bil.getType(), FortuneLogNode.INTEREST_APPROVAL.getName(), bpp.getBackiId(), GlobalConstant.DHXSP_PAGE+""+GlobalConstant.FRIST+""+ bil.getType()+";"+GlobalConstant.UPDATE_ST + BacksmsState.getBacksmsState(bil.getDictBackiStatus()));
		bil.setId(IdGen.uuid());
		bil.setBackiId(bip.getBackiId());
		
		String mesg = "";
		try {
			
			DetailsPage dp = backInterestCommonService.findMesgByCode(bpp.getBackiId());
			/*
			 *  如果短信状态(退回重放标识)为空或者短信状态的值为2，那么证明此数据没有发送过短信，不包括人为修改数据库值。
			 *  如果退回重放标识的值为1，不发送短信
			 *  只有审批通过以后发送短信，切记就是付款方式为资金托管的回息数据不发送短信
			 */
			if((StringUtils.isEmpty(dp.getSmsStatus()) || !dp.getSmsStatus().equals(YoN.SHI.value))
						&& bip.getCheckExaminetype().equals(YoN.SHI.value)
						&& !dp.getApplyPay().equals(PayMent.ZJTG.value)){
				
				TelphoneMessage tel = backInterestCommonService.getCustomerMesg(bip.getBackiId());
				mesg += backInterestCommonService.doSubmit(bpp, bil, hf, tel);
			}else{
				
				mesg += backInterestCommonService.doSubmit(bpp, bil, hf);
			}
			
		} catch (Exception e) {
			
			logger.error("[回息审批详情提交失败]"+bip.getBackiId(), e);
			mesg += "[出借编号]"+bip.getLendCode()+"[回息审批提交失败]</br>"+e.getMessage();
			e.printStackTrace();
			backInterestCommonService.addExceptionMesgs(bpp.getBackiId(), e, "[回息编号]"+bpp.getBackiId()+"[回息审批提交失败]", FortuneLogNode.INTEREST_APPLY.getCode());
		}
		if(mesg.equals("")){
			return "true";
		}
		return mesg;
	}
	
	/**
	 * 回息审批列表导出EXCEL
	 * 2016年1月7日
	 * by 李志伟
	 * @param resp
	 * @param req
	 * @param so
	 */
	public void exportExl(HttpServletResponse resp, HttpServletRequest req, SearchObject so) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getBackInterestApprovalStatus());
		
		String fileName = ExportConstant.APPROVAL_EXPORT+""+DateUtils.date2Str(new Date(), GlobalConstant.YYYY_MM_DD);
		String sqlName = ExportConstant.approval_id;
		String sheetName = "sheet";
		String exportFlag =	ExportConstant.approvalFlag;
		
		ExportBackInterestHelper.exportData(map, resp, fileName, sqlName, sheetName, exportFlag, StaticMethodUtil.getType("0"));
		
	}
}