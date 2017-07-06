package com.creditharmony.fortune.change.lend.apply.manager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.common.type.CertificateType;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.dict.util.DictUtils;
import com.creditharmony.core.excel.util.ExportExcel;
import com.creditharmony.core.fortune.entity.CaCustomerSign;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.CASignType;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.LendchgType;
import com.creditharmony.core.fortune.type.LenderchgState;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.ReportType;
import com.creditharmony.core.fortune.type.TrustState;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.core.users.entity.ProvinceCity;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.dm.bean.DocumentBean;
import com.creditharmony.dm.service.DmService;
import com.creditharmony.fortune.change.lend.apply.dao.LendChangeDao;
import com.creditharmony.fortune.change.lend.apply.entity.LendChangeInfo;
import com.creditharmony.fortune.change.lend.apply.entity.LendLaunchView;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoadView;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApply;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApplyEx;
import com.creditharmony.fortune.change.lend.apply.entity.LendQueryView;
import com.creditharmony.fortune.change.lender.apply.service.LenderChangeApplyManager;
import com.creditharmony.fortune.common.CallInterface;
import com.creditharmony.fortune.common.FileManagement;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.common.service.AttachmentManager;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.entity.CustomerAccount;
import com.creditharmony.fortune.deduct.common.Constant;
import com.creditharmony.fortune.trusteeship.entity.TrusteeshipExportModel;
import com.creditharmony.fortune.utils.AttachmentUtil;
import com.creditharmony.fortune.utils.FortuneDictUtil;
import com.creditharmony.fortune.utils.OptionUtil;

/**
 * 出借信息修改service层
 * @Class Name LendChangeManager
 * @author 刘雄武
 * @Create In 2015年12月1日
 */
@Service
public class LendChangeApplyManager extends CoreManager<LendChangeDao, LendLoanApplyEx> {
	@Resource
	private LendChangeDao lecDao;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	protected AttachmentManager attachmentService;
	@Autowired
	private FortuneExceptionDao fortuneExceptionDao;
	
	/**
	 * 根据出借编号获取出借申请信息
	 * 2015年12月2日
	 * By 刘雄武
	 * @param applyCode
	 * @return
	 */
	public LendLoanApply getLoanApplyInfo(String applyCode) {
		return lecDao.getLoanApplyInfo(applyCode);
	}

	/**
	 * 保存变更信息
	 * 2015年12月9日
	 * By 刘雄武
	 * @param bv
	 */
	public void saveLendChangeInfo(LendLaunchView bv) {
		
		bv.setDicts(null);
		LendChangeInfo changeInfo = new LendChangeInfo();
		//LendchgType.initLendchgState();
		changeInfo.preInsert();
		BaseBusinessView begin = this.getLendLoanApplybefore(bv.getLendloanapply().getApplyCode());
		String json = JsonMapper.toJsonString(begin);// 变更前数据
		changeInfo.setChangeBegin(json);//　保存变更前数据
		CustomerAccount payAccount = lecDao.getCustomerPayAccount(bv.getLendloanapply().getApplyCode());
		if(bv.getCountAfter().getId().equals(payAccount.getId())){ // 判断汇款和付款为同一银行
			bv.setCountAfter(payAccount);
		}
		
		String json2 = JsonMapper.toJsonString(bv);// 变更后数据
		changeInfo.setChangeAfter(json2);//　保存变更后数据
		changeInfo.setApplyId(bv.getApplyId());
		changeInfo.setChangeCode(bv.getLendloanapply().getApplyCode());
		LendLaunchView v=(LendLaunchView) begin;
		// 金账户银行卡流程
	    String trusteeshipNo=v.getLendloanapply().getTrusteeshipNo();
	    if("".equals(trusteeshipNo)){
	    	trusteeshipNo=null;
	    }
	    String applyPay=v.getLendloanapply().getApplyPay();
	    // 金账户银行卡条件：1、是金账户2、已开户3、划扣成功4、回款之前5、付款方式为资金托管
		if(TrustState.KHCG.value.equals(v.getLendloanapply().getApplyHostedState())&&trusteeshipNo!=null&&PayMent.ZJTG.value.equals(applyPay)&&backMoneyStatus(bv.getLendloanapply())){
			changeInfo.setDictChangeType(LendchgType.TRUSTESSHIP_CARD_CHA.value);// 保存变更类型
			changeCardFile(bv, changeInfo.getId(),v);
		}else{ 
			changeInfo.setDictChangeType(LendchgType.LEND_CHG.value);// 保存变更类型
		}
		changeInfo.setDictChangeState(LenderchgState.DMDSH.value);// 保存变更状态
		lecDao.saveLendChangeInfo(changeInfo);
		
		
		bv.getLendloanapply().setBillDay(v.getLendloanapply().getBillDay());
		bv.getLendloanapply().setProductCode(v.getLendloanapply().getProductCode());
		
		
		//sqsCa(bv, changeInfo.getId(), changeInfo.getDictChangeType());
		
		// 全程留痕
		String operInfo=LendchgType.getLendchgType(LendchgType.LEND_CHG.value)+
				"提交申请";
		checkManager.addCheck(bv.getApplyId(),operInfo
						, "提交");
		AttachmentUtil.updateAndDeleteAttchment(bv.getAddAttachmentId(), bv.getDeleteAttachmentId(),bv.getLendloanapply().getApplyCode(),changeInfo.getId(),"t_tz_changer");
		// 将附件重temp目录下保存在正确的路径下
		DmService dmService = DmService.getInstance();
		List<String> addAttachmentIdList = bv.getAddAttachmentId();
		for (String addAttachmentId : addAttachmentIdList) {
			Attachment attachment = attachmentService.get(addAttachmentId);
			if(attachment!=null){
				String attaFilepath = attachment.getAttaFilepath();
				if (StringUtils.isNotBlank(attaFilepath)) {
					dmService.moveDocument(attaFilepath,bv.getLendloanapply().getCustCode()+"/"+bv.getLendloanapply().getApplyCode(),DmService.BUSI_TYPE_FORTUNE);
				}
			}			
		}
	}
	
	/**
	 * 银行卡变更附件
	 * 2016年3月2日
	 * By 郭才林
	 * @param view
	 * @param changeId
	 */
	public void changeCardFile(LendLaunchView  view,String changeId,LendLaunchView  begin){
		
		//LendchgType.initLendchgState();
		// 回款银行
		CustomerAccount rePay=begin.getCustomerAccount();
		TrusteeshipExportModel changeBagin=new TrusteeshipExportModel();
		changeBagin.setAccountAddrProvince(OptionUtil.getLable(rePay.getAccountAddrProvince()));
		changeBagin.setAccountAddrCity(OptionUtil.getLable(rePay.getAccountAddrCity()));
		
		changeBagin.setCustCertNum(view.getLendloanapply().getCustCertNum());
		changeBagin.setCustMobilephone(view.getLendloanapply().getCustMobilephone());
		//CertificateType.initCertificateType();
		changeBagin.setCustCertType(CertificateType.parseByCode(view.getLendloanapply().getCustCertType()).getName());
		changeBagin.setAccountBankId(DictUtils.getDictLabel(rePay.getAccountBankId(), "tz_open_bank", "")+rePay.getAccountBranch());
		changeBagin.setAccountNo(rePay.getAccountNo());
		changeBagin.setAccountName(rePay.getAccountName());
		changeBagin.setTrusteeshipNo(view.getLendloanapply().getTrusteeshipNo());
		changeBagin.setChangeState("变更前");
		
		CustomerAccount rePayAfter=view.getCountAfter();
		TrusteeshipExportModel changeAfter=new TrusteeshipExportModel();
		changeAfter.setAccountAddrProvince(OptionUtil.getLable(rePayAfter.getAccountAddrProvince()));
		changeAfter.setAccountAddrCity(OptionUtil.getLable(rePayAfter.getAccountAddrCity()));
		changeAfter.setCustCertNum(view.getLendloanapply().getCustCertNum());
		changeAfter.setCustMobilephone(view.getLendloanapply().getCustMobilephone());
		changeAfter.setCustCertType(CertificateType.parseByCode(view.getLendloanapply().getCustCertType()).getName());
		changeAfter.setAccountBankId(DictUtils.getDictLabel(rePayAfter.getAccountBankId(), "tz_open_bank", "")+rePayAfter.getAccountBranch());
		changeAfter.setAccountNo(rePayAfter.getAccountNo());
		changeAfter.setAccountName(rePayAfter.getAccountName());
		changeAfter.setTrusteeshipNo(view.getLendloanapply().getTrusteeshipNo());
		changeAfter.setChangeState("变更后");
		
		List<TrusteeshipExportModel> dataList=new ArrayList<TrusteeshipExportModel>();
		dataList.add(changeBagin);
		dataList.add(changeAfter);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmdd");
		Date date = new Date();
		String s =  sdf.format(date);
		String fileName=view.getLendloanapply().getCustName()+"-"+LendchgType.getLendchgType(LendchgType.TRUSTESSHIP_CARD_CHA.value)+s+".xlsx";
		ExportExcel exportExcel = new ExportExcel("", TrusteeshipExportModel.class);
		LenderChangeApplyManager.addStyle(changeAfter, changeBagin, exportExcel, 3, 1);
		// 设置导出数据源
		exportExcel.setDataList(dataList);
		OutputStream o;
		try {

			o = new ByteArrayOutputStream();
			exportExcel.write(o);
			ByteArrayOutputStream o1=(ByteArrayOutputStream) o;
			InputStream stream1 = new ByteArrayInputStream(o1.toByteArray());  
			DocumentBean docbean = null;
		    String user = UserUtils.getUserId();
			DmService dmService = DmService.getInstance();
			docbean = dmService.createDocument(fileName,stream1 , DmService.BUSI_TYPE_FORTUNE, "batchNo007", "subType007",user );
			Attachment att=new Attachment();
			att.preInsert();
			att.setAttaId(att.getId());
			att.setAttaFilepath(docbean.getDocId());
			att.setAttaFilename(fileName);// 文件名
			att.setAttaNewfilename(fileName);// 新文件名
			att.setLoanCode(view.getLendloanapply().getApplyCode());
			att.setAttaFileOwner("t_tz_changer");
			att.setDictAttaFlag("bg_"+LendchgType.TRUSTESSHIP_CARD_CHA.value);
			att.setAttaTableId(changeId);
			attachmentService.saveFile(att);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * 初始化调用方法
	 * 2015年12月7日
	 * By 刘雄武
	 * @param applyCode
	 * @return
	 */
	public BaseBusinessView getLendLoanApply(String applyCode) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		CustomerAccount payAccount = lecDao.getCustomerPayAccount(applyCode);
		CustomerAccount reyAccount = lecDao.getCustomerReyAccount(applyCode);
		if(reyAccount!=null){
			reyAccount.setId(uuid);
		}
		LendLoanApply lendloanapply = this.getLoanApplyInfo(applyCode);

		LendLaunchView view = new LendLaunchView();
		view.setLendloanapply(lendloanapply);
		view.setPayAccount(payAccount);
		view.setReyAccount(reyAccount);
		List<ProvinceCity> provinceList = null;
		if(PayMent.ZJTG.value.equals(lendloanapply.getApplyPay())){
			provinceList=OptionUtil.findFYProvince(null);
		}else{
			provinceList=OptionUtil.getProvinceList();
		}
	
        view.setProvinceList(provinceList);
        
        Map<String, List<Dict>> dicts = FortuneDictUtil.getDictMap(new String[]{"tz_trust_state","tz_open_bank","tz_open_bank_kl","com_card_type","tz_pay_type","tz_contract_vesion"});
        view.setDicts(dicts);
        
		return view;
	}

	/**
	 * 查询出借信息初始化（用于储存出借信息变更前出借人信息账户）
	 * 2015年12月8日
	 * By 刘雄武
	 * @param applyCode
	 * @return
	 */
	public BaseBusinessView getLendLoanApplybefore(String applyCode) {
		CustomerAccount cusAccountbefore = lecDao.getCustomerAccountbefore(applyCode);
		CustomerAccount payAccount = lecDao.getCustomerPayAccount(applyCode);
		LendLoanApply lendloanapply = this.getLoanApplyInfo(applyCode);
		LendLaunchView view = new LendLaunchView();
		view.setLendloanapply(lendloanapply);
		view.setCustomerAccount(cusAccountbefore);
		view.setPayAccount(payAccount);
		return view;
	}
	
	/**
	 * 获取出借信息变更历史
	 * 2015年12月10日
	 * By 刘雄武
	 * @param page
	 * @param lendloanapply
	 * @return
	 */
	public Page<LendLoanApply> getLendChangeHistory(Page<LendLoanApply> page, LendLoanApply lendloanapply) {
		PageBounds pageBounds=new PageBounds(page.getPageNo(), page.getPageSize());
		lendloanapply.setChangeType(LendchgType.LEND_CHG.value);
		lendloanapply.setAccountType(LendchgType.TRUSTESSHIP_CARD_CHA.value);
		lendloanapply.setDictChangeState(LenderchgState.SHTG.value);
		pageBounds.setFilterOrderBy(BooleanType.FALSE);
		pageBounds.setCountBy("applyCode");
		PageList<LendLoanApply> pageList = (PageList<LendLoanApply>)this.lecDao.getLendChangeHistory(lendloanapply,pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 变更历史详细
	 * 2015年12月11日
	 * By 刘雄武
	 * @param applyId
	 * @return
	 */
	public LendLoadView  historyDetail(String applyId){
		LendLoadView view = new LendLoadView();
		LendChangeInfo changeInfo = lecDao.getLendChangeInfo(applyId);
		if(changeInfo==null){
			return view;
		}
		LendLaunchView after =  (LendLaunchView) JsonMapper.fromJsonString(changeInfo.getChangeAfter(), LendLaunchView.class);
		LendLaunchView begin =  (LendLaunchView) JsonMapper.fromJsonString(changeInfo.getChangeBegin(), LendLaunchView.class);
		view.setChangeBegin(begin);
		view.setChangeAfter(after);
		
		return view;
	}
	
	/**
	 * 获取出借变更申请信息
	 * 2015年12月14日
	 * By 刘雄武
	 * @param page
	 * @param queryView
	 * @return
	 */
    public Page<LendLoanApplyEx> findPage(Page<LendLoanApplyEx> page,LendQueryView queryView){
    	
    	String dataRights = DataScopeUtil.getDataScope("tc", SystemFlag.FORTUNE.value);
		if(StringUtils.isNotEmpty(dataRights)){
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			queryView.setSqlMap(sqlMap);
		}
		PageBounds pageBounds=new PageBounds(page.getPageNo(), page.getPageSize());
		pageBounds.setFilterOrderBy(BooleanType.FALSE);
		pageBounds.setCountBy("applyCode");
		PageList<LendLoanApplyEx> pageList = (PageList<LendLoanApplyEx>)this.lecDao.findList2(queryView, pageBounds);//modify by liusl 20160926
		PageUtil.convertPage(pageList, page);
		return page;
	}
    
    /**
     * 更据出借编号获取回款状态
     * 2016年3月1日
     * By 郭才林
     * @param LendLoanApply
     * @return
     */
    public boolean  backMoneyStatus(LendLoanApply LendLoanApply){
	
    	// 划扣成功 回款之前才能银行卡变更
    	LendLoanApplyEx loanApply = lecDao.backMoneyStatus(LendLoanApply);
    	int backStatus=0;
		if(loanApply.getBackStatus()==null||"".equals(loanApply.getBackStatus())){
			loanApply.setBackStatus(backStatus+"");
		}
		backStatus=Integer.parseInt(loanApply.getBackStatus());
		if(LendState.HKCG.value.equals(loanApply.getApplyState())&&backStatus<Integer.parseInt(BackState.BFHKCG.value)){
			
			return true;
		}
		
		return false;
	}

	/**
	 * 申请书获取内容
	 * 2016年3月31日
	 * By 郭才林
	 * @param lendloanapply
	 * @param bv 
	 * @return
	 */
	public String sqsPreview(LendLaunchView bv) {
		
		LendLaunchView  after = (LendLaunchView) this.getLendLoanApplybefore(bv.getLendloanapply().getApplyCode());
		CustomerAccount account=bv.getCountAfter();
		
		String receiving_bank_before=OptionUtil.getProvinceLabel(after.getCustomerAccount().getAccountAddrProvince())+
			   OptionUtil.getCityLabel(after.getCustomerAccount().getAccountAddrCity())+
			   OptionUtil.getDistrictLabel(after.getCustomerAccount().getAccountAddrDistrict())+
			   after.getCustomerAccount().getAccountBranch();
		String receiving_bank_after=OptionUtil.getProvinceLabel(account.getAccountAddrProvince())+
				   OptionUtil.getCityLabel(account.getAccountAddrCity())+
				   OptionUtil.getDistrictLabel(account.getAccountAddrDistrict())+
				   account.getAccountBranch();
			
		
		String receiving_account_after=account.getAccountNo();
		String receiving_account_before=after.getCustomerAccount().getAccountNo();
		
		String url=	Constant.getProperties.get("template_cpt_url");
		String typeStr=Constant.getProperties.get(ReportType.CJ_BG.getCode());
		StringBuffer sb=new StringBuffer(url);
		sb.append(typeStr);
		sb.append("&receiving_bank_before="+receiving_bank_before);
		sb.append("&receiving_bank_after="+receiving_bank_after);
		sb.append("&receiving_account_after="+receiving_account_after);
		sb.append("&receiving_account_before="+receiving_account_before);
		sb.append("&format=pdf");
		
		return sb.toString();
		
	}
	
	/**
	 * 电子签章
	 * 2016年3月30日
	 * By 郭才林
	 * @param view
	 */
	public void sqsCa(final LendLaunchView bv,final String changeId,final String lendchgType) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("rehttpUrl", sqsPreview(bv));
		hashMap.put("fileName",ReportType.CJ_BG.getName()+".pdf");
		list.add(hashMap);
		
		FileManagement fileManager = new FileManagement();
		CaCustomerSign param = new CaCustomerSign(
				bv.getLendloanapply().getCustName(),
				"申请人：",
				bv.getLendloanapply().getCustCertNum());
		fileManager.before(list, CASignType.CJBG.value,param,bv.getLendloanapply().getCustCode(),bv.getLendloanapply().getApplyCode(), new CallInterface(){

			@SuppressWarnings("rawtypes")
			public void method(Map<String, Object> map) {
				String result =map.get("bool").toString();
				if(result.equals("true")){
					Attachment att=new Attachment();
					att.preInsert();
					att.setAttaId(att.getId());
					att.setAttaFilepath(((List)map.get("docId")).get(0).toString());
					att.setAttaFilename(ReportType.CJ_BG.getName()+DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+".pdf");// 文件名
					att.setAttaNewfilename(ReportType.CJ_BG.getName()+DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+".pdf");// 新文件名
					att.setLoanCode(bv.getLendloanapply().getApplyCode());
					att.setAttaFileOwner("t_tz_changer");
					att.setDictAttaFlag(lendchgType);
					att.setAttaTableId(changeId);
					attachmentService.saveFile(att);
				}else{
					try {
						throw new Exception("生成出借变更书错误，请重试");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		});
		fileManager.run();	
	}
	
	/**
	 * 下载出借信息变更书
	 * 2016年5月6日
	 * By 肖长伟
	 * @param response
	 * @param parameter
	 * @param filename
	 */
	public void downloadTemplate(HttpServletResponse response,Map<String, Object>  parameter,String filename) {
		try {
			// 设置Header信息
			response.setContentType("APPLICATION/OCTET-STREAM");  
			response.setHeader("Content-Disposition", "attachment; filename=" +  new String( filename.getBytes("gb2312"), "ISO8859-1" ));
			
			String http = Constant.getProperties.get("template_cpt_url");
			String fileurl = (String) parameter.get("templateName");
			http = http.replace("reportlet=", "reportlet="+fileurl);
			String Mosaic = (String) (parameter.get("parameter")==null ? "":parameter.get("parameter"));
			http = http + "&" +Mosaic;
			http = http + "&format=pdf";
			URL url1 = new URL(http);
			// 创建连接
			URLConnection conn = url1.openConnection();
			InputStream inStream = conn.getInputStream();
			// 文件转换成数据流输出，最后关闭输入，输出数据流
			FileCopyUtils.copy(inStream, response.getOutputStream());
		} catch (Exception e) {
			logger.error("下载出借信息变更申请表失败",e);
			FortuneException forException = new FortuneException();
			forException.setLoanCode(null);
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setNode(FortuneLogNode.LEND_CHANGE_APPLY.getCode());
			forException.setImportantLevel(FortuneLogLevel.BLUE.value);
			forException.setRemark("下载出借信息变更申请表失败");

			forException.preInsert();
			fortuneExceptionDao.insert(forException);
		}		
		
	}
	
}
