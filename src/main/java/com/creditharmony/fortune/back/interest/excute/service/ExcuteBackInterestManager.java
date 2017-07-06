package com.creditharmony.fortune.back.interest.excute.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.PropertyUtil;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BackResult;
import com.creditharmony.core.deduct.bean.out.FortuneDeductResult;
import com.creditharmony.core.excel.util.ImportExcel;
import com.creditharmony.core.fortune.type.BackInterestPlat;
import com.creditharmony.core.fortune.type.BackMoneyPlat;
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
import com.creditharmony.fortune.back.interest.entity.LineBackInterestObj;
import com.creditharmony.fortune.back.interest.entity.LineGoldBackInterestObj;
import com.creditharmony.fortune.back.interest.entity.PlatformMsg;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.back.interest.excute.dao.ExcuteBackInterestDao;
import com.creditharmony.fortune.back.interest.excute.facade.ExcuteBackInterestForEachManager;
import com.creditharmony.fortune.back.interest.util.ExportBackInterestHelper;
import com.creditharmony.fortune.back.interest.util.FileNameMadeFactory;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.back.interest.view.BackInterestBeanView;
import com.creditharmony.fortune.back.interest.view.BackInterestListView;
import com.creditharmony.fortune.common.dao.CheckDao;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.customer.entity.Product;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.template.entity.backInterest.ChinaFinanceUpload;
import com.creditharmony.fortune.template.entity.backInterest.CommunicationsUploadFirst;
import com.creditharmony.fortune.template.entity.backInterest.CommuntcationExport;
import com.creditharmony.fortune.template.entity.backInterest.FuYouUpload;
import com.creditharmony.fortune.template.entity.backInterest.FyExport;
import com.creditharmony.fortune.template.entity.backInterest.GoldAccountUpload;
import com.creditharmony.fortune.template.entity.backInterest.NetBankUpload;

/**
 * 执行回息
 * @Class Name ExcuteBackInterestManager 
 * @author 李志伟
 * @Create In 2015年12月23日
 */
@Service
public class ExcuteBackInterestManager{

	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ExcuteBackInterestDao excuteBackInterestDao;
	@Autowired
	private BackInterestCommonDao backInterestCommonDao;
	@Autowired
	private ExcuteBackInterestForEachManager excuteBackInterestForEachManager;
	@Autowired
	private BackInterestCommonService backInterestCommonService;
	@Autowired
	private CheckDao checkDao;
	
	/**
	 * 初始化执行回息列表
	 * 2016年4月11日
	 * by 李志伟
	 * @param so
	 * @param request
	 * @param response
	 * @return
	 */
	public BackInterestBeanView loadExcuteBackInterestList(SearchObject so,
			HttpServletRequest request, HttpServletResponse response) {
		
		Page<BackInterestListView> page = new Page<BackInterestListView>(request, response);
		BackInterestBeanView bibv = new BackInterestBeanView();
		Map<String,Object> map = new HashMap<String, Object>();
		
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getExcuteBackInterestStatus());
		List<String> payType = StaticMethodUtil.getPayType(so.getTrusteeshipFlag());
		map.put("payType", payType);
		
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
		bibv.setSo(so);
		bibv.setProductList(list);
		bibv.setPage(page);
		bibv.setListTotalMoney(listTotalMoney);
		bibv.setPageTotalMoney(pageTotalMoney);
		return bibv;
	}
	
	/**
	 * 进入执行回息审核页
	 * 2015年12月2日
	 * by 李志伟
	 * @param code
	 * @return
	 */
	public BackInterestBeanView goExcuteBackInterestPage(String code) {
		
		DetailsPage deta = backInterestCommonDao.findMesgByCode(code);
		List<PlatformMsg> platFormlist = backInterestCommonService.searchThirdPlat();
		BackInterestBeanView bibv = new BackInterestBeanView();
		bibv.setDetailsPage(deta);
		bibv.setPlatformMesglist(platFormlist);
		return bibv;
	}
	
	/**
	 * 提交执行回息
	 * 2016年1月5日
	 * by 李志伟
	 * @param bip
	 */
	public String goSubmit(BackInterestPool bbp) {
		
		String mesg = "";
		BackInterestPool bip = new BackInterestPool();
		bip.setBackiId(bbp.getBackiId());
		
		// 退回重放标志
		if(bbp.getCheckExaminetype().equals(YoN.FOU.value)){
			bip.setRebackFlag(YoN.SHI.value);
		}else{
			if(StringUtils.isNotEmpty(bbp.getPlatformId())){
				String[] bankMesg = bbp.getPlatformId().split("_");
				if(bankMesg.length == 2){
					bip.setPlatformId(bankMesg[0]);
					bip.setBackBank(bankMesg[1]);
				}else{
					bip.setPlatformId(bankMesg[0]);
				}
			}else{
				mesg = mesg+"中间人信息没有选择</br>";
				return mesg;
			}
		}
		bip.setLendCode(bbp.getLendCode());
		
		BackInterestLog bil = StaticMethodUtil.madeApproval(bbp, BacksmsState.DHXTH.value, BacksmsState.DHXQR.value);
		bil.setId(IdGen.uuid());
		bil.setBackiId(bbp.getBackiId());
		bip.setBackMoneyStatus(bil.getDictBackiStatus());
		bip.setReturnReason(bil.getCheckExamine());
		bip.setBackMoneyDay(new Date());
		bip.preUpdate();
		// 排他
		bip.setVerState(StaticMethodUtil.getExcuteBackInterestStatus());
		bip.setVerTime(bbp.getVerTime());
		
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		
		Check hf = StaticMethodUtil.madeHistory(userId, userName, bip.getLendCode(), bil.getType(), FortuneLogNode.INTEREST_EXCUTE.getName(), bip.getBackiId(), GlobalConstant.DHX_PAGE+""+GlobalConstant.FRIST +""+ bil.getType()+";"+GlobalConstant.UPDATE_ST + BacksmsState.getBacksmsState(bil.getDictBackiStatus()));
		try {
			mesg += backInterestCommonService.doSubmit(bip, bil, hf);
		} catch (Exception e) {
			logger.error("提交执行回息失败", e);
			e.printStackTrace();
			logger.debug(e.getMessage());
			mesg = mesg+"[出借编号]"+bip.getLendCode()+"[提交回息执行回息失败]</br>"+e.getMessage();
			backInterestCommonService.addExceptionMesgs(bip.getBackiId(), e, mesg, FortuneLogNode.INTEREST_EXCUTE.getCode());
		}
		if(mesg.equals("")){
			return "true";
		}
		return mesg;
	}
	
	/**
	 * 执行回息导出
	 * 2016年4月18日
	 * by 李志伟
	 * @param so
	 * @param req
	 * @param resp
	 */
	public void export(SearchObject so, HttpServletRequest req, HttpServletResponse resp){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getExcuteBackInterestStatus());
		
		// 获取平台
		String pid = so.getExportPlat();
		// 导出类型(.excel,.txt)
		String exportType = StaticMethodUtil.getType(so.getExportType());
		
		// 富友导出
		if(pid.equals(BackInterestPlat.FYPT.value)) {
			
			map.put("payType", StaticMethodUtil.getPayType(YoN.FOU.value));
			
			try {
				
				FyExport fe = new FyExport();
				fe.exportData(map, resp);
			} catch (Exception e) {
				logger.error("执行回息富友导出数据失败", e);
				e.printStackTrace();
			}
		
		} else if(pid.equals(BackInterestPlat.WY.value)) {// 网银导出
			
			List<String> payType = StaticMethodUtil.getPayType(YoN.FOU.value);
			map.put("payType", payType);
			
			String fileName = ExportConstant.EXCUTE_EXPORT+""+DateUtils.date2Str(new Date(), GlobalConstant.YYYY_MM_DD);
			String sqlNameSpace = ExportConstant.excute_id;
			String sheetName = "sheet";
			String exportFlag =	ExportConstant.excuteFlag;
			ExportBackInterestHelper.exportData(map, resp, fileName, sqlNameSpace, sheetName, exportFlag, exportType);
			
			
		} else if(pid.equals(BackInterestPlat.TL.value)) {// 通联导出
			
			map.put("payType", StaticMethodUtil.getPayType(YoN.FOU.value));
			
			try {
				CommuntcationExport ce = new CommuntcationExport();
				ce.exportData(map, resp);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("执行回息通联导出数据失败", e);
			}
			
		}else if(pid.equals(BackInterestPlat.JZH.value)){// 金账户导出
			
			List<String> payType = StaticMethodUtil.getPayType(YoN.SHI.value);
			map.put("payType", payType);	
			
			String fileName = ExportConstant.EXCUTE_JZH_EXPORT+""+DateUtils.date2Str(new Date(), GlobalConstant.YYYY_MM_DD);
			String sqlNameSpace = ExportConstant.acc_excute_id;
			String sheetName = "sheet";
			String exportFlag =	ExportConstant.excuteAccFlag;
			ExportBackInterestHelper.exportData(map, resp, fileName, sqlNameSpace, sheetName, exportFlag, exportType);
		
		}else if(pid.equals(BackInterestPlat.ZJPT.value)){// 中金导出
			
			map.put("payType", StaticMethodUtil.getPayType(YoN.FOU.value));
			
			Properties property = PropertyUtil.getProperties("fileNameNo.properties");
			String value = property.getProperty("zhongJin_platForm_No");
			String currentTime = DateUtils.getDate("yyyyMMdd");
			String date = property.getProperty("zhongJin_time");
			int s = Integer.valueOf(value).intValue();
			
			if(!date.equals(currentTime)){
				value="1";
				s = 1;
				PropertyUtil.updateProperties("fileNameNo.properties", "zhongJin_time", currentTime);
			}
			FileNameMadeFactory fnmf = new FileNameMadeFactory(ExportConstant.ZJ_EXPORT, value);
			
			String fileName = fnmf.zjAndFyMadeFileName();
			String sqlNameSpace = ExportConstant.zj_id;
			String sheetName = "sheet";
			String exportFlag =	ExportConstant.zjFlag;
			try {
				ExportBackInterestHelper.exportData(map, resp, fileName, sqlNameSpace, sheetName, exportFlag, exportType);
			}catch(Exception e){
				
				e.printStackTrace();
				logger.error("执行回息中金导出数据失败", e);
			}finally{
				//重新写入配置文件
				PropertyUtil.updateProperties("fileNameNo.properties", "zhongJin_platForm_No", String.valueOf(++s));
			}
		}else if(pid.equals(BackInterestPlat.KL.value)){// 卡联支付导出
			/*map.put("payType", StaticMethodUtil.getPayType(YoN.FOU.value));
			CardPaymentExport cpe = new CardPaymentExport();
			cpe.exportData(map, resp);*/
			
		}
	}
	
	/**
	 * 线上回息结果数据更新
	 * 2016年2月3日
	 * By 赵红江
	 * @param fortuneDeductResult
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public boolean updateBackInterestResult(FortuneDeductResult fortuneDeductResult){
		logger.debug("回息结果更新..........");
		boolean result=false;
		BackInterestPool bmp = new BackInterestPool();
		try {
			// 回息申请Id
			String backId = DeductUtils.isNull(fortuneDeductResult
					.getLendCode());
			bmp.setModifyBy("MQ");
			bmp.setModifyTime(new Date());
			bmp.setBackiId(backId);
			bmp.setBackMoneyDay(new Date());
			// 回息平台
			String plateFormId = DeductUtils.isNull(fortuneDeductResult
					.getPlateformId());
			if (!plateFormId.isEmpty()) {
				bmp.setPlatformId(plateFormId);
			}
			
			// 回息成功金额
			String successMony = DeductUtils.isNull(fortuneDeductResult.getDeductSucceedMoney());
			if (StringUtils.isNotEmpty(successMony)) {
				BigDecimal bd = new BigDecimal(Double.valueOf(successMony));
				int t = bd.compareTo(BigDecimal.ZERO);
				bd = (t==1?bd:BigDecimal.ZERO);
				bmp.setSuccessMoney(bd);
			}
			
			// 回息失败金额
			String failMoney = DeductUtils.isNull(fortuneDeductResult
					.getDeductFailMoney());
			if (StringUtils.isNotEmpty(failMoney)) {
				BigDecimal bd = new BigDecimal(Double.valueOf(failMoney));
				int t = bd.compareTo(BigDecimal.ZERO);
				bd = (t==1?bd:BigDecimal.ZERO);
				bmp.setFailMoney(bd);	
			}
			
			// 回盘日期
			String backday = DeductUtils.isNull(fortuneDeductResult
					.getDeductTime());
			Date par = StaticMethodUtil.parseDay(backday, GlobalConstant.yyyy_mm_dd);
			
			// 失败原因
			String fileReason = DeductUtils.isNull(fortuneDeductResult
					.getConfirmOpinion());
			
			// 回盘结果
			String backResult = DeductUtils.isNull(fortuneDeductResult.getDeductResultCode());
			if (backResult.equals(GlobalConstant.RETURN_NO)) {
				bmp.setBackResult(BackResult.SUCESS.value);// 回盘结果：1：成功；2：失败
			} else {
				bmp.setBackResult(BackResult.FAIL.value);// 回盘结果
				bmp.setBackReason(fileReason); 
			}

			if (StringUtils.isNotEmpty(backday)) {
				// 回盘时间
				bmp.setBackDay(par);
			}
			
			String userId = "MQ";
			String userName = "MQ";
			Check mh = StaticMethodUtil.madeHistory(userId, userName, backInterestCommonDao.findMesgByCode(bmp.getBackiId()).getLendCode(), 
					GlobalConstant.COMMIT,  FortuneLogNode.INTEREST_EXCUTE.getName(), bmp.getBackiId(), GlobalConstant.MQ_GIVE + GlobalConstant.RESULT);
			// 回息申请表更新
			backInterestCommonDao.updateBackResult(bmp);
			checkDao.insertSelective(mh);
			result = true;
		} catch (Exception e) {
			logger.error(
					"线上回息MQ推送结果更新失败，回息申请Id为：" + fortuneDeductResult.getLendCode()+";失败信息"+e.getMessage(), e);
			backInterestCommonService.addExceptionMesg(bmp.getBackiId(), e, "线上回息MQ推送结果失败", FortuneLogNode.INTEREST_EXCUTE.getCode());
			return result;
		}
		return result;
	}
	
	/**
	 * 线上回息(非金账户、金账户)
	 * 2016年4月11日
	 * by 李志伟
	 * @param so
	 * @param p
	 * @return
	 */
	public String getLineBackInterestData(SearchObject so, BackInterestPool p){
		
		Map<String,Object> map = new HashMap<String, Object>();
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getExcuteBackInterestStatus());
		
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		
		String errorMesg = "";
		if(!p.getPlatformId().equals(BackInterestPlat.JZH.value)){// 非金账户回息
			
			List<String> payType = StaticMethodUtil.getPayType(so.getTrusteeshipFlag());
			map.put("payType", payType);
			
			List<LineBackInterestObj> list = new ArrayList<LineBackInterestObj>();
			list = excuteBackInterestDao.findList(map);
			errorMesg += excuteBackInterestForEachManager.noTranscationBackInterest(list, p);
			
		}else if(p.getPlatformId().equals(BackInterestPlat.JZH.value)){// 金账户回息
			
			List<String> payType = StaticMethodUtil.getPayType(so.getTrusteeshipFlag());
			map.put("payType", payType);
			
			// 金账户回息(数据)
			List<LineGoldBackInterestObj> goldBackInterstList = new ArrayList<LineGoldBackInterestObj>();
			goldBackInterstList = excuteBackInterestDao.findGoldAccList(map);
			errorMesg += excuteBackInterestForEachManager.goldAccountBackInterest(goldBackInterstList);
		}
		if(errorMesg.equals("")){
			return "true";
		}
		return errorMesg;
	}

	/**
	 * 待回息上传回盘结果
	 * 2016年4月19日
	 * by 李志伟
	 * @param file
	 * @param req
	 * @param rep
	 * @return
	 */
	public String uploadFile(MultipartHttpServletRequest file,
			HttpServletRequest req, HttpServletResponse rep) {
		
		MultipartFile files = file.getFile("file");
		String platformId = file.getParameter("platformId");
		String message = "";
		
		if (BackMoneyPlat.FYPT.value.equals(platformId)) {// 富友上传
			
			List<FuYouUpload> list = new ArrayList<FuYouUpload>();
			List<BackInterestPool> newList = new ArrayList<BackInterestPool>();

			try {
				ImportExcel ie = new ImportExcel(files, 0, 0);
				list = ie.getDataList(FuYouUpload.class);
				if (null == list || list.size() == 0) {
					return "富友上传文件中数据为空······";
				}
			} catch (Exception e) {
				logger.error("待回息列表富友上传文件失败", e);
				backInterestCommonService.addExceptionMesgs("", e, "上传文件读取失败", FortuneLogNode.INTEREST_EXCUTE.getCode());
				return "上传文件格式有误或Excel版本太低······";
			}
			message += excuteBackInterestForEachManager.fuYouUpload(list, newList, message);
		
		}else if (BackMoneyPlat.JZH.value.equals(platformId)) {// 金账户上传
			
			List<GoldAccountUpload> list = new ArrayList<GoldAccountUpload>();
			try {
				
				ImportExcel ie = new ImportExcel(files, 0, 0);
				list = ie.getDataList(GoldAccountUpload.class);
				if (null == list || list.size() == 0) {
					return "金账户上传文件中数据为空······";
				}	
			} catch (Exception e) {
				
				this.logger.error("待回息列表金账户上传文件失败",e);
				backInterestCommonService.addExceptionMesgs("", e, "金账户上传文件读取失败", FortuneLogNode.INTEREST_EXCUTE.getCode());
				return "上传文件格式有误······";
			}
			
			message += excuteBackInterestForEachManager.goldAccountUpload(list);
		
		}else if (BackMoneyPlat.WY.value.equals(platformId)) {// 网银上传
			List<NetBankUpload> list = new ArrayList<NetBankUpload>();
			try {
				
				ImportExcel ie = new ImportExcel(files, 0, 0);
				list = ie.getDataList(NetBankUpload.class);
				if (null == list || list.size() == 0) {
					return "待回息列表网银上传文件中数据为空······";
				}
			} catch (Exception e) {
				
				this.logger.error("待回息列表上传网银回盘结果失败", e);
				backInterestCommonService.addExceptionMesgs("", e, "网银上传读取文件失败", FortuneLogNode.INTEREST_EXCUTE.getCode());
				return "上传文件数据格式有误或者EXCEL文件版本太低";
			}
			message += excuteBackInterestForEachManager.netBankUpload(list,file);
			
		}else if (BackMoneyPlat.ZJPT.value.equals(platformId)){// 中金上传
			
			List<ChinaFinanceUpload> list = new ArrayList<ChinaFinanceUpload>();
			try {
				ImportExcel ie = new ImportExcel(files, 1, 0);
				list = ie.getDataList(ChinaFinanceUpload.class);
				list.remove(list.size()-GlobalConstant.GR);
				if (null == list || list.size() == 0) {
					return "待回息列表中金上传文件数据为空······";
				}
				
			} catch (Exception e) {
				
				this.logger.error("待回息列表上传中金回盘结果失败", e);
				e.printStackTrace();
				backInterestCommonService.addExceptionMesgs("", e, "中金上传文件读取失败", FortuneLogNode.INTEREST_EXCUTE.getCode());
				return "上传文件数据格式有误或EXCEL文件版本太低······";
			}
				
			message += excuteBackInterestForEachManager.chinaFinanceUpload(list);
			
		}else if (platformId.equals(BackInterestPlat.TL.value)){// 通联模板（一）上传
		
			List<CommunicationsUploadFirst> list = new ArrayList<CommunicationsUploadFirst>();
			List<BackInterestPool> newList = new ArrayList<BackInterestPool>();
			try {
				
				ImportExcel ie = new ImportExcel(files, 0, 0);
				list = ie.getDataList(CommunicationsUploadFirst.class);
				if (null == list || list.size() == 0) {
					return "待回息列表通联上传文件数据为空······";
				}
				
			} catch (Exception e) {
				
				this.logger.error("待回息列表上传通联回盘结果失败", e);
				e.printStackTrace();
				backInterestCommonService.addExceptionMesgs("", e, "通联上传文件读取失败", FortuneLogNode.INTEREST_EXCUTE.getCode());
				return "上传文件数据格式有误或EXCEL文件版本太低······";
			}
			
			message += excuteBackInterestForEachManager.communicationsUploadFirst(list, newList, message);
		}else if(platformId.equals(BackInterestPlat.KL.value)){
			
			List<Object> list = new ArrayList<Object>();
			try {
				ImportExcel ie = new ImportExcel(files, 1, 0);
				list = ie.getDataList(Object.class);
				if (null == list || list.size() == 0) {
					return "待回息列表卡联上传文件数据为空······";
				}
			} catch (Exception e) {
				
				this.logger.error("待回息列表上传卡联回盘结果失败", e);
				e.printStackTrace();
				backInterestCommonService.addExceptionMesgs("", e, "卡联上传文件读取失败", FortuneLogNode.INTEREST_EXCUTE.getCode());
				return "上传文件数据格式有误或EXCEL文件版本太低······";
			}
			message += excuteBackInterestForEachManager.uploadKaLianResult(list);
		}
		if(("").equals(message)){
			return "true";
		}
		return message;
	}
}