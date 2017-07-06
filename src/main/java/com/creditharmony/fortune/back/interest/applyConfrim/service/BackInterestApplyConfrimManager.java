package com.creditharmony.fortune.back.interest.applyConfrim.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.excel.util.ImportExcel;
import com.creditharmony.core.fortune.type.BacksmsState;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.interest.applyConfrim.dao.BackInterestApplyConfrimDao;
import com.creditharmony.fortune.back.interest.applyConfrim.facade.BackInterestApplyConfrimForEachManager;
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
import com.creditharmony.fortune.customer.entity.Product;
import com.creditharmony.fortune.template.entity.backInterest.UploadMoney;

/**
 * 回息申请确认
 * @Class Name BackInterestApplyConfirmManager 
 * @author 李志伟
 * @Create In 2015年12月2日
 */
@Service
public class BackInterestApplyConfrimManager extends CoreManager<BackInterestApplyConfrimDao, BackInterestPool> {
	
	@Autowired
	private BackInterestCommonService backInterestCommonService;
	@Autowired
	private BackInterestApplyConfrimForEachManager backInterestApplyConfrimForEachManager;
	
	/**
	 * 回息申请确认列表初始化
	 * 2016年4月9日
	 * by 李志伟
	 * @param so
	 * @param request
	 * @param response
	 */
	public BackInterestBeanView loadBackInterestApplyConfrimList(SearchObject so,
			HttpServletRequest request, HttpServletResponse response) {
		
		Page<BackInterestListView> page = new Page<BackInterestListView>(request, response);
		Map<String, Object> map = new HashMap<String, Object>();
		BackInterestBeanView bibv = new BackInterestBeanView();
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getBackInterestApplyConfrimStatus());
		//gaoxu  2017-3-25 13:08:01  区别到期回息与非到期回息
		map.put("notinterestReturn", "1");
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		page = backInterestCommonService.loadListAndFind(page,map);
		
		
		String listTotalMoney = GlobalConstant.MONEY;
		String pageTotalMoney = GlobalConstant.MONEY;
		if(page.getList().size() > 0){
			listTotalMoney = backInterestCommonService.selectSumMoney(map);
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
	 * 上传回息金额
	 * 2016年1月20日
	 * by 李志伟
	 * @param list
	 * @return 
	 */
	public String uploadMoney(MultipartHttpServletRequest files) {
		
		List<UploadMoney> list = new ArrayList<UploadMoney>();
		String mesg = "";
		try {
			MultipartFile file = files.getFile("file");
			ImportExcel ie = new ImportExcel(file, 0, 0);
			list = ie.getDataList(UploadMoney.class);
			
			if (null == list || list.size()==0 ){
				return "上传文件数据为空······";
			}
		} catch (Exception e) {
			logger.error("更新失败:"+e.getMessage(), e);
			e.printStackTrace();
			return "文件格式错误或Excel版本低······</br>"+e.getMessage();
		}
		
		mesg += backInterestApplyConfrimForEachManager.uploadMoney(list);
		if(mesg.equals("")){
			return "true";
		}
		return mesg;
	}
	
	/**
	 * 提交回息申请确认
	 * 2015年12月2日
	 * by 李志伟
	 * @param bip 回息信息对象
	 */
	public String goSubmit(BackInterestPool bp) {
		
		BackInterestPool bip = new BackInterestPool();
		BackInterestLog bil = StaticMethodUtil.madeApproval(bp, BacksmsState.DHXSQQRTH.value, BacksmsState.DHXSP.value);
		
		String hisMesg = "";
		if(bp.getCheckExaminetype().equals(YoN.SHI.value)){// 只有申请确认通过，才提交填写的金额
			bip.setBackRealMoney(bp.getBackRealMoney());
			hisMesg += GlobalConstant.UPDATE_MN + bip.getBackRealMoney()+";";
		}

		bip.setBackiId(bp.getBackiId());
		bip.setBackMoneyDay(bp.getBackMoneyDay());
		bip.setBackMoneyStatus(bil.getDictBackiStatus());
		bip.preUpdate();
		bip.setReturnReason(bil.getCheckExamine());
		
		bil.setId(IdGen.uuid());
		bil.setBackiId(bp.getBackiId());
		// 排他条件
		bip.setLendCode(bp.getLendCode());
		bip.setVerTime(bp.getVerTime());
		bip.setVerState(StaticMethodUtil.getBackInterestApplyConfrimStatus());
	
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		Check hf = StaticMethodUtil.madeHistory(userId, userName, bip.getLendCode(), bil.getType(), FortuneLogNode.INTEREST_APPLYCONFRIM.getName(), bip.getBackiId(), GlobalConstant.DHXSQQR_PAGE+""+GlobalConstant.FRIST+""+GlobalConstant.COMMIT+";"+ (("").equals(hisMesg)?"":hisMesg) +GlobalConstant.UPDATE_ST + BacksmsState.getBacksmsState(bil.getDictBackiStatus()));
		String mesg = "";
		try {
			mesg += backInterestCommonService.doSubmit(bip,bil,hf);
		} catch (Exception e) {
			
			e.printStackTrace();
			logger.error("[出借编号]"+bip.getLendCode()+"[提交回息申请确认失败]",e);
			mesg +="[出借编号]"+bip.getLendCode()+"[提交回息申请确认失败]</br>"+e.getMessage();
			backInterestCommonService.addExceptionMesgs(bip.getBackiId(), e, "[回息编号]"+bip.getBackiId()+"[提交回息申请确认失败]", FortuneLogNode.INTEREST_APPLY.getCode());
		}
		if(mesg.equals("")){
			return "true";
		}
		return mesg;
	}
	
	/**
	 * 待回息申请确认导出
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
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getBackInterestApplyConfrimStatus());
		List<String> payType = StaticMethodUtil.getPayType(YoN.FOU.value);
		map.put("payType", payType);
		
		String fileName = ExportConstant.APPLY_CONFRIM_EXPORT+""+DateUtils.date2Str(new Date(), GlobalConstant.YYYY_MM_DD);
		String sqlName = ExportConstant.apply_id;
		String sheetName = "sheet";
		String exportFlag =	ExportConstant.applyFlag;
		
		ExportBackInterestHelper.exportData(map, resp, fileName, sqlName, sheetName, exportFlag, StaticMethodUtil.getType("0"));
	}
	
	/**
	 * 待回息申请确认列表待金账户回息明细
	 * 2016年4月16日
	 * by 李志伟
	 * @param resp
	 * @param req
	 * @param so
	 */
	public void goldAccountExportExl(HttpServletResponse resp,
			HttpServletRequest req, SearchObject so) {

		Map<String, Object> map = new HashMap<String, Object>();
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getBackInterestApplyConfrimStatus());
		
		List<String> payType = StaticMethodUtil.getPayType(YoN.SHI.value);
		map.put("payType", payType);
		
		String fileName = ExportConstant.APPLY_Jzh_EXPORT+""+DateUtils.date2Str(new Date(), GlobalConstant.YYYY_MM_DD);
		String sqlNameSpace = ExportConstant.acc_apply_id;
		String sheetName = "sheet";
		String exportFlag =	ExportConstant.applyAccFlag;
		ExportBackInterestHelper.exportData(map, resp, fileName, sqlNameSpace, sheetName, exportFlag, StaticMethodUtil.getType("0"));
	}
}