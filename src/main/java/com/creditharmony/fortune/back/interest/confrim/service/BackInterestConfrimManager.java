package com.creditharmony.fortune.back.interest.confrim.service;

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
import com.creditharmony.core.fortune.type.BackInterestPlat;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.BacksmsState;
import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.interest.common.dao.BackInterestCommonDao;
import com.creditharmony.fortune.back.interest.common.service.BackInterestCommonService;
import com.creditharmony.fortune.back.interest.confrim.dao.BackInterestConfrimDao;
import com.creditharmony.fortune.back.interest.confrim.facade.BackInterestConfrimForEachManager;
import com.creditharmony.fortune.back.interest.contants.ExportConstant;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.interest.entity.BackInterestLog;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.DetailsPage;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.back.interest.util.ExportBackInterestHelper;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.back.interest.view.BackInterestBeanView;
import com.creditharmony.fortune.back.interest.view.BackInterestListView;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.entity.Product;
import com.creditharmony.fortune.template.entity.backInterest.ChinaFinanceUpload;
import com.creditharmony.fortune.template.entity.backInterest.CommunicationsUploadFirst;
import com.creditharmony.fortune.template.entity.backInterest.FuYouUpload;

/**
 * 待回息确认
 * @Class Name BackInterestConfrimManager 
 * @author 李志伟
 * @Create In 2015年12月23日
 */
@Service
public class BackInterestConfrimManager extends CoreManager<BackInterestConfrimDao, BackInterestPool> {
	
	@Autowired
	private BackInterestConfrimDao backInterestConfrimDao;
	@Autowired
	private BackInterestCommonDao backInterestCommonDao;
	@Autowired
	private BackInterestCommonService backInterestCommonService;
	@Autowired
	private BackInterestConfrimForEachManager backInterestConfrimForEachManager;
	
	/**
	 * 回息确认列表初始化
	 * 2016年4月11日
	 * by 李志伟
	 * @param so
	 * @param request
	 * @param response
	 * @return
	 */
	public BackInterestBeanView loadBackInterestConfrimList(SearchObject so,
			HttpServletRequest request, HttpServletResponse response) {

		Page<BackInterestListView> page = new Page<BackInterestListView>(request, response);
		BackInterestBeanView bibv = new BackInterestBeanView();
		Map<String,Object> map = new HashMap<String, Object>();
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getBackInterestConfrimStatus());
		
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
	 * 提交回息确认
	 * 2015年12月2日
	 * by 李志伟
	 * @param bip 回息信息对象
	 * @param bil 回息审批记录对象
	 */
	public String goSubmit(BackInterestPool bbi) {
		
		BackInterestPool  bip = new BackInterestPool();
		LoanApply la = null;
		BackInterestLog bil = StaticMethodUtil.madeApproval(bbi, BacksmsState.DHXQRTH.value, BacksmsState.YHX.value);
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
	
		Check hf = StaticMethodUtil.madeHistory(userId, userName, bbi.getLendCode(), bil.getType(), FortuneLogNode.INTEREST_CONFRIM.getName(), bip.getBackiId(), GlobalConstant.DHXQR_PAGE+""+GlobalConstant.FRIST+""+bil.getType()+";"+GlobalConstant.UPDATE_ST + BacksmsState.getBacksmsState(bil.getDictBackiStatus()));
		bil.setId(IdGen.uuid());
		bil.setBackiId(bbi.getBackiId());
		bip.setBackiId(bbi.getBackiId());
		bip.setLendCode(bbi.getLendCode());
		bip.setBackMoneyStatus(bil.getDictBackiStatus());
		bip.setReturnReason(bil.getCheckExamine());
		bip.setBackMoneyDay(bbi.getBackMoneyDay());
		bip.preUpdate();
		
		// 排他
		bip.setVerTime(bbi.getVerTime());
		bip.setVerState(StaticMethodUtil.getBackInterestConfrimStatus());
		
		String mesg = "";
		try {
			
			if(bbi.getCheckExaminetype().equals(YoN.FOU.value)){
				bip.setRebackFlag(YoN.SHI.value);
			}else{
				/* 回息确认通过后如果本笔出借对应的产品为信和宝C时，就需要更新回息日期
				本期回息日期=本期回息日期+6个月*/
				LoanApply lend = backInterestCommonService.getLoanApplyMesg(bbi.getLendCode());
				if(lend.getProductCode().equals(ProductCode.XHBC.value)){
					
					DetailsPage dp = backInterestCommonService.findMesgByCode(bip.getBackiId());
					bbi.setMatchingExpireDay(dp.getMatchingExpireDay());
					la = StaticMethodUtil.setNextBackInterestDay(bbi, la);
				}
				BackMoneyPool money = backInterestCommonService.getBackMoneyMesg(bbi.getLendCode());
				if(null != money && StringUtils.isNotEmpty(money.getDictBackStatus())){
					if(money.getDictBackStatus().equals(BackState.YHK.value)){
						if(null != la){
							la.setApplyEndStatus(FinishState.YWJ.value);
							la.setApplyCode(bip.getLendCode());
							la.preUpdate();
						}else{
							la = new LoanApply();
							la.setApplyEndStatus(FinishState.YWJ.value);
							la.setApplyCode(bip.getLendCode());
							la.preUpdate();
						}
					}
				}
			}
			if(null != la){
				mesg += backInterestCommonService.doSubmit(bip, bil, hf, la);
			}else{
				mesg += backInterestCommonService.doSubmit(bip, bil, hf);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[出借编号]"+bbi.getLendCode()+"[回息确认提交失败]", e);
			mesg += "[出借编号]"+bbi.getLendCode()+"[回息确认提交失败]</br>"+e.getMessage();
		}
		if(("").equals(mesg)){
			return "true";
		}
		return mesg;
	}
	
	/**
	 * 回息确认列表导出
	 * 2016年1月8日
	 * by 李志伟
	 * @param map
	 * @return
	 */
	public void exportExl(SearchObject so, HttpServletResponse resp, HttpServletRequest req) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		StaticMethodUtil.getCondition(so, map,  StaticMethodUtil.getBackInterestConfrimStatus());
		
		String fileName = ExportConstant.CONFRIM_EXPORT+""+DateUtils.date2Str(new Date(), GlobalConstant.YYYY_MM_DD);
		String sqlNameSpace = ExportConstant.confrim_id;
		String sheetName = "sheet";
		String exportFlag =	ExportConstant.confrimFlag;
		ExportBackInterestHelper.exportData(map, resp, fileName, sqlNameSpace, sheetName, exportFlag, StaticMethodUtil.getType("0"));
		
	}
	
	/**
	 * 待回息确认单次退回至待回息列表
	 * 2016年4月1日
	 * by 李志伟
	 * @param pool
	 */
	public String backOne(BackInterestPool pool) {
		
		BackInterestPool bip = new BackInterestPool();
		pool.setCheckExaminetype(YoN.FOU.value);
		BackInterestLog bil = StaticMethodUtil.madeApproval(pool, BacksmsState.HXSB.value, "");
		bil.setId(IdGen.uuid());
		bil.setBackiId(pool.getBackiId());
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		
		Check hf = StaticMethodUtil.madeHistory(userId, userName, pool.getLendCode(), GlobalConstant.BACK, FortuneLogNode.INTEREST_CONFRIM.getName(), bip.getBackiId(), GlobalConstant.DHXQR_PAGE+""+GlobalConstant.FRIST+""+
		GlobalConstant.BACK+""+GlobalConstant.DAO+""+ GlobalConstant.DHX_LIST +","+GlobalConstant.UPDATE_ST+""+BacksmsState.getBacksmsState(bil.getDictBackiStatus()));
		
		bip.setBackiId(pool.getBackiId());
		bip.setLendCode(pool.getLendCode());
		bip.setRebackFlag(YoN.SHI.value);
		bip.setBackMoneyStatus(bil.getDictBackiStatus());
		bip.setReturnReason(bil.getCheckExamine());
		bip.preUpdate();
		
		// 排他
		bip.setVerState(StaticMethodUtil.getBackInterestConfrimStatus());
		bip.setVerTime(pool.getVerTime());
		
		String mesg = "";
		try {
			mesg += backInterestCommonService.doSubmit(bip, bil, hf);
		} catch (Exception e) {
			mesg = mesg + "[出借编号]"+pool.getLendCode()+"[提交失败]</br>"+e.getMessage();
			logger.error("[出借编号]"+pool.getLendCode()+"[提交失败]", e);
			e.printStackTrace();
			backInterestCommonService.addExceptionMesgs(bip.getBackiId(), e, mesg, FortuneLogNode.INTEREST_CONFRIM.getCode());
		}
		if(mesg.equals("")){
			return "true";
		}
		return mesg;
	}
	
	/**
	 * 回息确认列表上传回盘结果
	 * 2016年4月27日
	 * by 李志伟
	 * @param files
	 * @return
	 */
	public String uploadResult(MultipartHttpServletRequest files) {
		
		MultipartFile file = files.getFile("file");
		String platformId = files.getParameter("platformId");
		String rtnMsg="";
		if(platformId.equals(BackInterestPlat.FYPT.value)){// 富友上传
			
			List<FuYouUpload> list = new ArrayList<FuYouUpload>();
			List<BackInterestPool> newList = new ArrayList<BackInterestPool>();
			try {
				
				ImportExcel ie = new ImportExcel(file, 0, 0);
				list = ie.getDataList(FuYouUpload.class);
				if (null == list || list.size() == 0) {
					return "待回息确认列表富友上传文件中数据为空······";
				}
			} catch (Exception e) {
				
				logger.error("待回息确认富有上传文件读取失败", e);
				e.printStackTrace();
				backInterestCommonService.addExceptionMesgs("", e, "待回息确认富友上传文件读取失败", FortuneLogNode.INTEREST_CONFRIM.getCode());
				return "富友上传文件格式错误或者EXCEL版本太低</br>"+e.getMessage();
			}
			rtnMsg += backInterestConfrimForEachManager.fuYouUpload(list, newList, rtnMsg);
			
		}else if(platformId.equals(BackInterestPlat.ZJPT.value)){// 中金上传
			
			List<ChinaFinanceUpload> list = new ArrayList<ChinaFinanceUpload>();
			try {
				
				ImportExcel ie = new ImportExcel(file, 1, 0);
				list = ie.getDataList(ChinaFinanceUpload.class);
				list.remove(list.size()-GlobalConstant.GR);
				if (null == list || list.size() == 0) {
					return "待回息确认列表中金上传文件中数据为空······";
				}
			} catch (Exception e) {
				
				logger.error("待回息确认中金上传文件读取失败", e);
				e.printStackTrace();
				backInterestCommonService.addExceptionMesgs("", e, "待回息确认中金上传文件读取失败", FortuneLogNode.INTEREST_CONFRIM.getCode());
				return "中金上传文件数据有误或EXCEL版本太低</br>"+e.getMessage();
			}
			
			rtnMsg += backInterestConfrimForEachManager.uploadZJResult(list);
			
		}else if(platformId.equals(BackInterestPlat.TL.value)){// 通联上传
			
			List<CommunicationsUploadFirst> list  = new ArrayList<CommunicationsUploadFirst>();
			List<BackInterestPool> newList  = new ArrayList<BackInterestPool>();
			try {
				
				ImportExcel ie = new ImportExcel(file, 0, 0);
				list = ie.getDataList(CommunicationsUploadFirst.class);
				if (null == list || list.size() == 0) {
					return "待回息确认列表通联上传文件中数据为空······";
				}
			} catch (Exception e) {
				
				logger.error("待回息确认列表通联上传文件失败", e);
				backInterestCommonService.addExceptionMesgs("", e, "通联上传文件读取失败", FortuneLogNode.INTEREST_CONFRIM.getCode());
				return "通联上传文件格式错误或EXCEL版本太低</br>"+e.getMessage();
			}
			rtnMsg += backInterestConfrimForEachManager.communicationsUploadFirst(list, newList, rtnMsg);
			
		}else{
			return "此上传不支持此平台";
		}
		if(rtnMsg.equals("")){
			return "true";
		}
		return rtnMsg;
	}
}