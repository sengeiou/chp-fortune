package com.creditharmony.fortune.back.interest.apply.service;

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
import com.creditharmony.fortune.back.interest.util.ApprovalMesgUtil;
import com.creditharmony.fortune.back.interest.util.ExportBackInterestHelper;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.back.interest.view.BackInterestBeanView;
import com.creditharmony.fortune.back.interest.view.BackInterestListView;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.customer.entity.Product;


/**
 * 回息申请
 * @Class Name BackInterestApplyManager 
 * @author 李志伟
 * @Create In 2015年12月2日
 */
@Service
public class BackInterestApplyManager{
	
	@Autowired
	private BackInterestCommonDao backInterestCommonDao;
	@Autowired
	private BackInterestCommonService backInterestCommonService;
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 回息申请初始化处理层
	 * 2016年4月8日
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
		
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getBackInterestApplyStatus());
		//gaoxu  2017-3-25 13:08:01  区别到期回息与非到期回息
		map.put("notinterestReturn", "1");
		page = backInterestCommonService.loadListAndFind(page,map);
		
		String listTotalMoney = GlobalConstant.MONEY;
		String pageTotalMoney = GlobalConstant.MONEY;
		if(page.getList().size() > 0){
			listTotalMoney = backInterestCommonService.selectSumMoney(map);
			pageTotalMoney = StaticMethodUtil.totalMoney(page.getList());
		}
		List<Product> list = backInterestCommonService.findProductSelect();
		so.setAddrCity(city);
		bb.setSo(so);
		bb.setPage(page);
		bb.setListTotalMoney(listTotalMoney);
		bb.setPageTotalMoney(pageTotalMoney);
		bb.setProductList(list);
		
		return bb;
	}
	
	/**
	 * 待回息申请列表金账户导出明细
	 * 2016年1月11日
	 * by 李志伟
	 * @param map
	 * @return
	 */
	public void goldAccountExportExl(HttpServletResponse resp, HttpServletRequest req, SearchObject so) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getBackInterestApplyStatus());
		//gaoxu  2017-3-25 13:08:01  区别到期回息与非到期回息
		map.put("notinterestReturn", "1");
		List<String> payType = StaticMethodUtil.getPayType(YoN.SHI.value);
		map.put("payType", payType);
		
		String fileName = ExportConstant.APPLY_Jzh_EXPORT+""+DateUtils.date2Str(new Date(), GlobalConstant.YYYY_MM_DD);
		String sqlNameSpace = ExportConstant.acc_apply_id;
		String sheetName = "sheet";
		String exportFlag =	ExportConstant.applyAccFlag;
		ExportBackInterestHelper.exportData(map, resp, fileName, sqlNameSpace, sheetName, exportFlag, StaticMethodUtil.getType("0"));
	}

	/**
	 * 提交回息申请
	 * 2016年1月5日
	 * by 李志伟
	 * @param bip
	 * @return
	 */
	public String toSubmit(DetailsPage  bip) {
		
		BackInterestPool bp = new BackInterestPool();
		bp.setBackiId(bip.getBackiId());
		bp.setPlatFlag(bip.getPlatFlag());
		bp.setBackMoneyStatus(BacksmsState.DHXSQQR.value);
		bp.preUpdate();
		// 排他条件
		bp.setLendCode(bip.getLendCode());
		bp.setVerTime(bip.getVerTime());
		bp.setVerState(StaticMethodUtil.getBackInterestApplyStatus());
		
		BackInterestLog bil = ApprovalMesgUtil.generateMes(BacksmsState.DHXSQQR.value, YoN.SHI.value, "");
		bil.setId(IdGen.uuid());
		bil.setBackiId(bip.getBackiId());
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		Check ch = StaticMethodUtil.madeHistory(userId, userName, bip.getLendCode(), GlobalConstant.COMMIT, FortuneLogNode.INTEREST_APPLY.getName(), bp.getBackiId(), GlobalConstant.DHXSQ_PAGE+""+GlobalConstant.COMMIT+";"+
		GlobalConstant.UPDATE_ST + "" + BacksmsState.getBacksmsState(bil.getDictBackiStatus()));
		String mesg = "";
		try {
			mesg += backInterestCommonService.doSubmit(bp, bil, ch);
		} catch (Exception e) {
			
			logger.error("[出借编号]"+bip.getLendCode()+"[待回息申请详情页提交失败]", e);
			e.printStackTrace();
			mesg += "[出借编号]"+bip.getLendCode()+"[回息申请提交失败]</br>"+e.getMessage();
			backInterestCommonService.addExceptionMesgs(bp.getBackiId(), e, "[回息编号]"+bp.getBackiId()+"[回息申请提交失败]", FortuneLogNode.INTEREST_APPLY.getCode());
		}
		if(mesg.equals("")){
			return "true";
		}
		return mesg;
	}
	
	/**
	 * 待回息申请导出
	 * 2016年4月15日
	 * by 李志伟
	 * @param resp
	 * @param req
	 * @param so
	 * @param flag
	 */
	public void exportExl(HttpServletResponse resp, HttpServletRequest req,
			SearchObject so) {

		Map<String, Object> map = new HashMap<String, Object>();
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getBackInterestApplyStatus());
		//gaoxu  2017-3-25 13:08:01  区别到期回息与非到期回息
		map.put("notinterestReturn", "1");
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		List<String> payType = StaticMethodUtil.getPayType(YoN.FOU.value);
		map.put("payType", payType);
		
		String fileName = ExportConstant.APPLY_EXPORT+""+DateUtils.date2Str(new Date(), GlobalConstant.YYYY_MM_DD);
		String sqlName = ExportConstant.apply_id;
		String sheetName = "sheet";
		String exportFlag =	ExportConstant.applyFlag;
		
		ExportBackInterestHelper.exportData(map, resp, fileName, sqlName, sheetName, exportFlag, StaticMethodUtil.getType("0"));
	}
}