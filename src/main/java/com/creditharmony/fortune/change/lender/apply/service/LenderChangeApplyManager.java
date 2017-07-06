package com.creditharmony.fortune.change.lender.apply.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.common.type.CertificateType;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.dict.util.DictUtils;
import com.creditharmony.core.excel.util.ExportExcel;
import com.creditharmony.core.fortune.entity.CaCustomerSign;
import com.creditharmony.core.fortune.type.CASignType;
import com.creditharmony.core.fortune.type.CustomerState;
import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.LendchgType;
import com.creditharmony.core.fortune.type.LenderchgState;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.ReportType;
import com.creditharmony.core.fortune.type.TrustState;
import com.creditharmony.core.fortune.type.VerifyPinType;
import com.creditharmony.core.loan.type.YESNO;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.core.users.entity.ProvinceCity;
import com.creditharmony.core.users.entity.Role;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.core.users.type.UserStatus;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.dm.bean.DocumentBean;
import com.creditharmony.dm.service.DmService;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.change.lend.apply.dao.LendChangeDao;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApply;
import com.creditharmony.fortune.change.lender.apply.dao.LenderChangeDao;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeInfo;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerEx;
import com.creditharmony.fortune.change.lender.apply.entity.ext.EmergencyContactEx;
import com.creditharmony.fortune.change.lender.apply.view.LenderInitView;
import com.creditharmony.fortune.change.lender.apply.view.LenderLoadView;
import com.creditharmony.fortune.change.lender.apply.view.LenderQueryView;
import com.creditharmony.fortune.common.CallInterface;
import com.creditharmony.fortune.common.FileManagement;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.common.service.AttachmentManager;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.customer.entity.Address;
import com.creditharmony.fortune.customer.entity.CustomerAccount;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.entity.FortuneUser;
import com.creditharmony.fortune.customer.entity.LoanConfiguration;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.deduct.common.Constant;
import com.creditharmony.fortune.triple.system.service.TripleChangePhoneService;
import com.creditharmony.fortune.trusteeship.entity.TrusteeshipExportModel;
import com.creditharmony.fortune.utils.AttachmentUtil;
import com.creditharmony.fortune.utils.FortuneDictUtil;
import com.creditharmony.fortune.utils.OptionUtil;
import com.creditharmony.fortune.verify.manager.CustomerVerifyManager;

/**
 * 出借人信息变更申请manager类
 * @Class Name LenderChangeApplyApply
 * @author 郭才林
 * @Create In 2016年4月12日
 */
@Service
public class LenderChangeApplyManager extends CoreManager<LenderChangeDao, CustomerEx> {
	
	@Autowired
	private LenderChangeDao lcDao;
	@Autowired
	private CheckManager checkManager;
	@Resource
	private LendChangeDao lecDao;
	@Autowired
	protected AttachmentManager attachmentService;
	@Autowired
	private CustomerVerifyManager customerVerifyManager;
	// 三网接口交割客户
	@Resource
	TripleChangePhoneService tipleChangePhoneService;
	@Autowired
	private FortuneExceptionDao fortuneExceptionDao;
	
	
	/**
	 * 初始化申请表单
	 * 2015年12月3日
	 * By 郭才林
	 * @param customerCode
	 * @return
	 */
	public LenderInitView getLaunchForm(String customerCode) {

		return lcDao.getLaunchForm(customerCode);
	}

	/**
	 * 保存变更信息
	 * 2015年12月2日
	 * By 郭才林
	 * @param view
	 */
	public void saveLenderChangeInfo(LenderInitView view) {
		
		LenderChangeInfo change = new LenderChangeInfo();
		LenderInitView beginview  = (LenderInitView) this.getCustInfo(view.getCustomer().getCustCode());
		beginview.setApplyId(view.getApplyId());
		beginview.setProvinceList(null);// 置空所有不需要的省市
		beginview.setDicts(null);
		view.getCustomer().setApplyHostedStatus(beginview.getCustomer().getApplyHostedStatus());
		//屏蔽客户姓名/手机号/身份证号
		if("***".equals(view.getCustomer().getCustName())){
			view.getCustomer().setCustName(beginview.getCustomer().getCustName());
		}
		if("***".equals(view.getCustomer().getCustEname())){
			view.getCustomer().setCustEname(beginview.getCustomer().getCustEname());
		}
		if("***".equals(view.getCustomer().getCustMobilephone())){
			view.getCustomer().setCustMobilephone(beginview.getCustomer().getCustMobilephone());
		}
		if("***".equals(view.getCustomer().getCustPhone())){
			view.getCustomer().setCustPhone(beginview.getCustomer().getCustPhone());
		}
		
		if("***".equals(view.getEmer().getEmerName())){
			view.getEmer().setEmerName(beginview.getEmer().getEmerName());
		}
		if("***".equals(view.getEmer().getEmerEname())){
			view.getEmer().setEmerEname(beginview.getEmer().getEmerEname());
		}
		if("***".equals(view.getEmer().getEmerMobilephone())){
			view.getEmer().setEmerMobilephone(beginview.getEmer().getEmerMobilephone());
		}
		if("***".equals(view.getEmer().getEmerPhone())){
			view.getEmer().setEmerPhone(beginview.getEmer().getEmerPhone());
		}
	
		change.preInsert();
		change.setChangeCode(view.getCustomer().getCustCode());
		change.setApplyId(view.getApplyId());
		change.setDictChangeState(LenderchgState.DMDSH.value);
		if(LendchgType.TRUSTESSHIP_CANCELLATION.value.equals(view.getCustomer().getChangerTypeVal())){
			// 金账户销户	
			change.setDictChangeType(LendchgType.TRUSTESSHIP_CANCELLATION.value);
			CustomerEx cust=new CustomerEx();
			cust.preUpdate();
			cust.setCustCode(change.getChangeCode());
			cust.setApplyHostedStatus(TrustState.XHZ.value);
			updateApplyHostedStatus(cust);// 托管状态变成销户中
			view.getCustomer().setApplyHostedStatus(TrustState.XHZ.value);
			changeAccountOffFile(beginview, change.getId());
		}else if(view.getCustomer().getTrusteeshipNo()!=null&&!view.getCustomer().getCustMobilephone().equals(view.getCustomer().getCustMobilephoneChanger())){
			// 金账户手机号变更	
			change.setDictChangeType(LendchgType.TRUSTESSHIP_PHONE_CHA.value);
			changePhoneFile(view,change.getId());
		}else{
			// 出借人信息变更
			change.setDictChangeType(LendchgType.LENDER_CHG.value);
		}
		// 变更前json信息
		String changeBegin = JsonMapper.toJsonString(beginview);
		// 变更后json信息
		String changeAfter = JsonMapper.toJsonString(view);
		change.setChangeBegin(changeBegin);
		change.setChangeAfter(changeAfter);
		lcDao.saveLenderChangeInfo(change);
		//LendchgType.initLendchgState();
		//LenderchgState.initLenderchgState();
		String operInfo=LendchgType.getLendchgType(LendchgType.LENDER_CHG.value)+
				"提交申请";
		checkManager.addCheck(view.getApplyId(),operInfo
				, GlobalConstant.COMMIT);
		AttachmentUtil.updateAndDeleteAttchment(view.getAddAttachmentId(),view.getDeleteAttachmentId(),view.getCustomer().getCustCode(), change.getId(), "t_tz_changer");
	
		// 手机号变更 验证码更新
		if(!view.getCustomer().getCustMobilephone().equals(beginview.getCustomer().getCustMobilephone())){
			String pin=customerVerifyManager.makePin();
			customerVerifyManager.updateVerifyInfo(VerifyPinType.SMS.value, view.getCustomer().getCustCode(), pin);
		}
		// 邮箱变更验证码更新
		if(!view.getCustomer().getCustEmail().equals(beginview.getCustomer().getCustEmail())){
			String pin=customerVerifyManager.makePin();
			customerVerifyManager.updateVerifyInfo(VerifyPinType.EMAIL.value, view.getCustomer().getCustCode(), pin);
		}
		
		//sqsCa(view, change.getId(), FileConstants.FILE_TYPE_CJRBG);
		// 将附件重temp目录下保存在正确的路径下
		DmService dmService = DmService.getInstance();
		List<String> addAttachmentIdList = view.getAddAttachmentId();
		for (String addAttachmentId : addAttachmentIdList) {
			Attachment attachment = attachmentService.get(addAttachmentId);
			if(attachment!=null){
				String attaFilepath = attachment.getAttaFilepath();
				if (StringUtils.isNotBlank(attaFilepath)) {
					dmService.moveDocument(attaFilepath,view.getCustomer().getCustCode()+"/"+FileConstants.KHXI,DmService.BUSI_TYPE_FORTUNE);
				}
			}			
		}
	}
	
	/**
	 * 电子签章
	 * 2016年3月30日
	 * By 郭才林
	 * @param view
	 */
	@Transactional(value = "fortuneTransactionManager" , readOnly = false)
	public void sqsCa(final LenderInitView view,final String changeId,final String lendchgType) {
		
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("rehttpUrl", sqsPreview(view));
		hashMap.put("fileName",ReportType.CJR_BG.getName()+".pdf");
		list.add(hashMap);
		
		FileManagement fileManager = new FileManagement();
		
		CaCustomerSign param = new CaCustomerSign(
				view.getCustomer().getCustName(),
				"客户签署:",
				view.getCustomer().getCustCertNum());
		fileManager.before(list, CASignType.CJRBG.value,param, view.getCustomer().getCustCode(),"客户信息" ,new CallInterface(){

			@SuppressWarnings("rawtypes")
			public void method(Map<String, Object> map) {
				String result =map.get("bool").toString();
				if(result.equals("true")){
					Attachment att=new Attachment();
					att.preInsert();
					att.setAttaId(att.getId());
					att.setAttaFilepath(((List)map.get("docId")).get(0).toString());
					att.setAttaFilename(ReportType.CJR_BG.getName()+DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+".pdf");// 文件名
					att.setAttaNewfilename(ReportType.CJR_BG.getName()+DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+".pdf");// 新文件名
					att.setLoanCode(view.getCustomer().getCustCode());
					att.setAttaFileOwner("t_tz_changer");
					att.setDictAttaFlag(lendchgType);
					att.setAttaTableId(changeId);
					attachmentService.saveFile(att);
				}else{
					try {
						throw new Exception("生成客户信息变更书错误，请重试");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}			
			}
			
		});
		fileManager.run();		
	}
	
	
	/**
	 * 整合申请书内容
	 * 2016年3月29日
	 * By 郭才林
	 * @param model
	 * @param type
	 * @param str
	 * @param response
	 * @throws IOException
	 */
	public String sqsPreview(LenderInitView view) {
		
		LenderInitView after=(LenderInitView)this.getCustInfo(view.getCustomer().getCustCode());
		String url=	Constant.getProperties.get("template_cpt_url");
		String typeStr=Constant.getProperties.get(ReportType.CJR_BG.getCode());
		String cust_name=view.getCustomer().getCustName(); //客户姓名：
		String cust_cert_num=view.getCustomer().getCustCertNum();// 证件号码:
		String self_type_1=""; //变更个人资料：(1：联系方式(包括电话,地址,邮箱)/2：身份证信息/3：工作信息/4：收取每期债权文件的方式)
		String self_type_2="";
		String self_change="";//		变更个人资料变更为: 
		String emergency_change	="";
		String other_type="";
		String other_change="";
		String emergency_type_1="";//紧急联系人资料：(1：联系方式(包括电话,地址,邮箱)/2：身份证信息/3：工作信息/4：收取每期债权文件的方式)
		String emergency_type_2="";
		String emergency_type_3="";
		String emergency_type_4="";
		User u= UserUtils.get(view.getCustomer().getManagerId());
		String manager="";
		if(u!=null){
			manager=u.getName();
		}
		String dept_leader="";
		if(!after.getCustomer().getCustCertOrg().equals(view.getCustomer().getCustCertOrg())){
			self_change+="证件发证机关："+view.getCustomer().getCustCertOrg()+";";
			self_type_2="1";
			
		}
		if(!after.getCustomer().getCustMarriage().equals(view.getCustomer().getCustMarriage())){
			other_change+="客户婚姻状况："+DictUtils.getDictLabel(view.getCustomer().getCustMarriage(), "tz_marital_state", "")+";";
			other_type+="客户婚姻状况;";
			
		}
		
		if(!after.getCustomer().getCustEname().equals(view.getCustomer().getCustEname())){
			other_change+="客户英文名称："+view.getCustomer().getCustEname()+";";
			other_type+="客户英文名称;";			
		}
		if(!after.getCustomer().getCustPhone().equals(view.getCustomer().getCustPhone())){
			self_change+="固定电话："+view.getCustomer().getCustPhone()+";";
			self_type_1="1";
		}
		if(!after.getCustomer().getCustMobilephone().equals(view.getCustomer().getCustMobilephone())){
			self_change+="移动电话："+view.getCustomer().getCustMobilephone()+";";
			self_type_1="1";
		}
		if(!after.getCustomer().getCustEmail().equals(view.getCustomer().getCustEmail())){
			self_change+="电子邮箱："+view.getCustomer().getCustEmail()+";";
			self_type_1="1";
		}
		if(!after.getCustomer().getAddress().getAddrDistrict().equals(view.getCustomer().getAddress().getAddrDistrict())){
			self_change+="地址："+OptionUtil.getProvinceLabel(view.getCustomer().getAddress().getAddrProvince())+
					OptionUtil.getCityLabel(view.getCustomer().getAddress().getAddrCity())+
					OptionUtil.getDistrictLabel(view.getCustomer().getAddress().getAddrDistrict());
			self_type_1="1";
		}
		
		//紧急联系人信息发生变更
		if(!after.getEmer().getEmerName().equals(view.getEmer().getEmerName())){
			emergency_change+="姓名："+view.getEmer().getEmerName()+";";
			emergency_type_1="1";
		}
		if(!after.getEmer().getEmerMobilephone().equals(view.getEmer().getEmerMobilephone())){
			emergency_change+="移动电话："+view.getEmer().getEmerMobilephone()+";";
			emergency_type_2="1";
		}
		if(!after.getEmer().getEmerEmail().equals(view.getEmer().getEmerEmail())){
			emergency_change+="电子邮箱："+view.getEmer().getEmerEmail()+";";
			emergency_type_2="1";
		}
		if(!after.getEmer().getAddress().getAddrDistrict().equals(view.getEmer().getAddress().getAddrDistrict())){
			emergency_change+="地址："+OptionUtil.getProvinceLabel(view.getEmer().getAddress().getAddrProvince())+
					OptionUtil.getCityLabel(view.getEmer().getAddress().getAddrCity())+
					OptionUtil.getDistrictLabel(view.getEmer().getAddress().getAddrDistrict());
			emergency_type_2="1";
		}
		
		FortuneOrg org = null;
		List<FortuneOrg> orgList = null;
		List<FortuneUser> members = null;
		org = RelationShipUtil.getTeamOrg(view.getCustomer().getManagerId());
		if (null != org) {
			orgList = RelationShipUtil.getHigherOrg(org.getId(), Arrays
					.asList(new String[] { FortuneOrgType.STORE.key }));
			if(orgList.size()>0){
				members = RelationShipUtil
						.getOrgMember(
								orgList.get(0).getId(),
								Arrays.asList(new String[] { FortuneRole.STORE_MANAGER.id }),UserStatus.ON);			
				if(members.size()>0){
					dept_leader=members.get(0).getName();
				}			
			}
		}
		
		StringBuffer sb=new StringBuffer(url);
		sb.append(typeStr);
		sb.append("&cust_name="+cust_name);
		sb.append("&cust_cert_num="+cust_cert_num);
		sb.append("&self_type_1="+self_type_1);
		sb.append("&self_type_2="+self_type_2);
		sb.append("&self_change="+self_change);
		sb.append("&emergency_type_1="+emergency_type_1);
		sb.append("&emergency_type_2="+emergency_type_2);
		sb.append("&emergency_type_3="+emergency_type_3);
		sb.append("&emergency_type_4="+emergency_type_4);
		sb.append("&emergency_change="+emergency_change);
		sb.append("&other_type="+other_type);
		sb.append("&manager="+manager);
		sb.append("&dept_leader="+dept_leader);		
		sb.append("&other_change="+other_change);
		sb.append("&other_type="+other_type);		
		sb.append("&format=pdf");
		
		return sb.toString();
	}
	
	/**
	 * 手机号变更附件
	 * 2016年3月2日
	 * By 郭才林
	 * @param view
	 * @param changeId
	 */
	public String changePhoneFile(LenderInitView view,String changeId){
		
			
		LendLoanApply lendLoanApply= lecDao.getLendByCustCode(view.getCustomer().getCustCode());// 获取出借编号
		if(lendLoanApply==null){
			return "";
		}
		// 回款银行
		CustomerAccount rePay=lecDao.getCustomerReyAccount(lendLoanApply.getApplyCode());
		if(rePay!=null){
			
			TrusteeshipExportModel changeBagin=new TrusteeshipExportModel();
			changeBagin.setAccountAddrProvince(OptionUtil.getLable(rePay.getAccountAddrProvince()));
			changeBagin.setAccountAddrCity(OptionUtil.getLable(rePay.getAccountAddrCity()));
			
			changeBagin.setCustCertNum(view.getCustomer().getCustCertNum());
			changeBagin.setCustMobilephone(view.getCustomer().getCustMobilephoneChanger());
			//CertificateType.initCertificateType();
			changeBagin.setCustCertType(CertificateType.parseByCode(view.getCustomer().getCustCertType()).getName());
			changeBagin.setAccountBankId(DictUtils.getDictLabel(rePay.getAccountBankId(), "tz_open_bank", "")+rePay.getAccountBranch());
			changeBagin.setAccountNo(rePay.getAccountNo());
			changeBagin.setAccountName(rePay.getAccountName());
			changeBagin.setTrusteeshipNo(view.getCustomer().getTrusteeshipNo());
			changeBagin.setChangeState("变更前");
			
			TrusteeshipExportModel changeAfter=new TrusteeshipExportModel();
			changeAfter.setAccountAddrProvince(OptionUtil.getLable(rePay.getAccountAddrProvince()));
			changeAfter.setAccountAddrCity(OptionUtil.getLable(rePay.getAccountAddrCity()));
			changeAfter.setCustCertNum(view.getCustomer().getCustCertNum());
			changeAfter.setCustMobilephone(view.getCustomer().getCustMobilephone());
			changeAfter.setCustCertType(CertificateType.parseByCode(view.getCustomer().getCustCertType()).getName());
			changeAfter.setAccountBankId(DictUtils.getDictLabel(rePay.getAccountBankId(), "tz_open_bank", "")+rePay.getAccountBranch());
			changeAfter.setAccountNo(rePay.getAccountNo());
			changeAfter.setAccountName(rePay.getAccountName());
			changeAfter.setTrusteeshipNo(view.getCustomer().getTrusteeshipNo());
			changeAfter.setChangeState("变更后");
				
			List<TrusteeshipExportModel> dataList=new ArrayList<TrusteeshipExportModel>();
			dataList.add(changeBagin);
			dataList.add(changeAfter);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmdd");
			Date date = new Date();
			String s =  sdf.format(date);
			//LendchgType.initLendchgState();
			String fileName=view.getCustomer().getCustName()+"_"+LendchgType.getLendchgType(LendchgType.TRUSTESSHIP_PHONE_CHA.value)+s+".xlsx";
			ExportExcel exportExcel = new ExportExcel("", TrusteeshipExportModel.class);
			addStyle(changeAfter, changeBagin, exportExcel, 3, 1);
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
				att.setLoanCode(view.getCustomer().getCustCode());
				att.setAttaFileOwner("t_tz_changer");
				att.setDictAttaFlag(LendchgType.TRUSTESSHIP_PHONE_CHA.value);
				att.setAttaTableId(changeId);;
				attachmentService.saveFile(att);
			
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		return "";
		
	}
	
	/**
	 * 销户附件
	 * 2016年3月2日
	 * By 郭才林
	 * @param view
	 * @param changeId
	 */
	public void changeAccountOffFile(LenderInitView view,String changeId){
		
			
		LendLoanApply lendLoanApply= lecDao.getLendByCustCode(view.getCustomer().getCustCode());// 获取出借编号
		// 回款银行
		if(lendLoanApply!=null){
			CustomerAccount rePay=lecDao.getCustomerReyAccount(lendLoanApply.getApplyCode());
			TrusteeshipExportModel changeBagin=new TrusteeshipExportModel();
			changeBagin.setAccountAddrProvince(OptionUtil.getLable(rePay.getAccountAddrProvince()));
			changeBagin.setAccountAddrCity(OptionUtil.getLable(rePay.getAccountAddrCity()));
			changeBagin.setCustCertNum(view.getCustomer().getCustCertNum());
			changeBagin.setCustMobilephone(view.getCustomer().getCustMobilephone());
			//CertificateType.initCertificateType();
			//changeBagin.setCustCertType(CertificateType.parseByCode(view.getCustomer().getCustCertType()).getName());
			changeBagin.setAccountBankId(DictUtils.getDictLabel(rePay.getAccountBankId(), "tz_open_bank", "")+rePay.getAccountBranch());
			changeBagin.setAccountNo(rePay.getAccountNo());
			changeBagin.setAccountName(rePay.getAccountName());
			changeBagin.setTrusteeshipNo(view.getCustomer().getTrusteeshipNo());
			List<TrusteeshipExportModel> dataList=new ArrayList<TrusteeshipExportModel>();
			dataList.add(changeBagin);
			//LendchgType.initLendchgState();
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmdd");
			Date date = new Date();
			String s =  sdf.format(date);
			String fileName=view.getCustomer().getCustName()+"-"+LendchgType.getLendchgType(LendchgType.TRUSTESSHIP_CANCELLATION.value)+s+".xlsx";
			ExportExcel exportExcel = new ExportExcel("", TrusteeshipExportModel.class);
			
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
				att.setLoanCode(view.getCustomer().getCustCode());
				att.setAttaFileOwner("t_tz_changer");
				att.setDictAttaFlag(LendchgType.TRUSTESSHIP_CANCELLATION.value);
				att.setAttaTableId(changeId);
				attachmentService.saveFile(att);
			
				
			} catch (Exception e) {
			
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * 更新托管状态
	 * 2016年3月1日
	 * By 郭才林
	 * @param view
	 */
	@Transactional(value = "fortuneTransactionManager" , readOnly = false)
	public void updateApplyHostedStatus(CustomerEx cust){
		
		lcDao.updateApplyHostedStatus(cust);
	}
	
	/**
	 * execl内容不同高亮显示
	 * 2016年3月3日
	 * By 郭才林
	 */
	public static void addStyle(Object after, Object begin, ExportExcel exportExcel, int startRow, int startCell) {

		try {

			Field[] fs = after.getClass().getDeclaredFields();
			for (int i = 1; i < fs.length; i++) {
				
				Field f = fs[i];
				f.setAccessible(true); //设置些属性是可以访问的  
				Method method = after.getClass().getDeclaredMethod("get" + toUpperCaseFirstOne(f.getName()));
				Object o = method.invoke(after);
				String s = "";
				if (o != null) {
					s = (String) o;
				}

				Method m = begin.getClass().getDeclaredMethod(method.getName());
				Object o1 = m.invoke(begin);
				String s1 = "";
				if (o1 != null) {
					s1 = (String) o1;
				}
				if (!s.equals(s1)) {

					if (i-1 >= startCell) {
						exportExcel.setCellBackGroundColor(startRow, i-1, IndexedColors.YELLOW.getIndex());
					}
				}
			}
		} catch (Exception e) {
			
		}

	}
	
	/**
	 * 出借人变更申请表单信息
	 * 2015年12月8日
	 * By 郭才林
	 * @param customerCode
	 * @return
	 */
	public BaseBusinessView getCustInfo(String customerCode) {

		LenderInitView view = new LenderInitView();
		CustomerEx customer = this.get(customerCode);// 根据客户编号获取客户信息	
		// 营业部
		FortuneOrg store = RelationShipUtil.getStoresOrg(UserUtils.getUserId());
		if(store!=null){
			//营业部
			customer.setDepartmentName(store.getId());
		}
		
		
		EmergencyContactEx emer = this.getLenderEmer(customer.getCustCode()); // 根据客户编号获取紧急联系人信息
		// 如果紧急联系人不为空则获取紧急人地址
		if (emer != null) {
			emer.setAddress(this.getLenderAddr(emer.getAddId()));// 设置紧急联系人地址信息
		}
		LoanConfiguration loanInfo = this.getLenderLoanInfo(customerCode);// 根据客户编号获取出借信息
		view.setLoanInfo(loanInfo);
		customer.setAddress(this.getLenderAddr(customer.getAddrId()));// 设置客户地址信息
		
		LendLoanApply loanapp=new LendLoanApply();
		loanapp.setCustCode(customerCode);
		// 判断是否可以销户
		String off=isAccountOff(loanapp);
		int r=0;
		if(off!=null||!"".equals(off)){
			r=Integer.parseInt(off);
		}
		if(r==0&&TrustState.KHCG.value.equals(customer.getApplyHostedStatus())){
			customer.setIsGoldAccount(YESNO.YES.getCode());
		}
		view.setCustomer(customer);// 设置客户信息
		view.setEmer(emer);// 设置紧急人联系信息
		List<ProvinceCity> provinceList = OptionUtil.getProvinceList();
        view.setProvinceList(provinceList);// 城市级联
        
        Map<String, List<Dict>> dicts = FortuneDictUtil.getDictMap(new String[]{"tz_marital_state","tz_billtaken_mode","tz_protocol_type","tz_protocol_version","tz_trust_state"});
        view.setDicts(dicts);
		return view;
	}
	
	/**
	 * 获取地址信息
	 * 2015年12月2日
	 * By 郭才林
	 * @param addrId
	 * @return
	 */
	public Address getLenderAddr(String addrId) {

		return lcDao.getLenderAddr(addrId);
	}
	
	/**
	 * 判断是否可以金账户注销
	 * 2016年2月29日
	 * By 郭才林
	 * @param applyId
	 * @return
	 */
	public String isAccountOff(LendLoanApply apply) {
		
		// 未完结
		apply.setDictApplyEndState(FinishState.WWJ.value);
		// 资金托管
		apply.setApplyPay(PayMent.ZJTG.value);
		return lcDao.isAccountOff(apply);
	}

	/**
	 * 获取出借信息
	 * 2015年12月2日
	 * By 郭才林
	 * @param custCode
	 * @return
	 */
	public LoanConfiguration getLenderLoanInfo(String custCode) {

		return lcDao.getLenderLoanInfo(custCode);
	}

	/**
	 * 获取紧急联系人信息
	 * 2015年12月2日
	 * By 郭才林
	 * @param cusCode
	 * @return
	 */
	public EmergencyContactEx getLenderEmer(String cusCode) {

		return lcDao.getLenderEmer(cusCode);
	}
	
	
	/**
	 * 获取客户变更历史
	 * 2015年12月1日
	 * By 郭才林
	 * @param page 
	 * @param changeInfo
	 */
	public Page<CustomerEx> getLenderChangeHistory(Page<CustomerEx> page,LenderChangeInfo changeInfo) {
		
		PageBounds pageBounds=new PageBounds(page.getPageNo(), page.getPageSize());
		changeInfo.setDictChangeState(LenderchgState.SHTG.value);
		changeInfo.setDictChangeType(LendchgType.LENDER_CHG.value);
		changeInfo.setChangeTypePhone(LendchgType.TRUSTESSHIP_PHONE_CHA.value);
		changeInfo.setChangeTypeAccOff(LendchgType.TRUSTESSHIP_CANCELLATION.value);
		pageBounds.setFilterOrderBy(BooleanType.FALSE);
		pageBounds.setCountBy("applyId");
		PageList<CustomerEx> pageList = (PageList<CustomerEx>)this.lcDao.getLenderChangeHistory(changeInfo,pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 首字母大寫
	 * 2016年3月3日
	 * By 郭才林
	 * @param str
	 * @return
	 */
	public static String toUpperCaseFirstOne(String str) {
		return new StringBuilder().append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();

	}
	
	/**
	 * 获取历史变更信息（变更前、变更后）
	 * 2015年12月8日
	 * By 郭才林
	 * @param applyId
	 * @return
	 */
	public LenderLoadView historyDetail(String applyId) {
		
		LenderLoadView view = new LenderLoadView();
		LenderChangeInfo changeInfo = this.getChangeInfo(applyId);
		// 判断变更信息是否为空，空则返回空对象
		if(changeInfo==null){
			return view;
		}
		LenderInitView after = (LenderInitView) JsonMapper.fromJsonString(changeInfo.getChangeAfter(), LenderInitView.class);
		LenderInitView begin = (LenderInitView) JsonMapper.fromJsonString(changeInfo.getChangeBegin(), LenderInitView.class);
		view.setLenderBegin(begin);
		view.setLenderAfter(after);
		return view;
	}
	
	/**
	 * 获取变更信息
	 * 2015年12月2日
	 * By 郭才林
	 * @param applyId
	 * @return
	 */
	public LenderChangeInfo getChangeInfo(String applyId) {

		return lcDao.getChangeInfo(applyId);
	}
	
	/**
	 * 获取客户信息变更申请列表
	 * 2015年12月9日
	 * By 郭才林
	 * @param query
	 * @param pageBounds
	 * @return
	 */
	public Page<CustomerEx> findPage(Page<CustomerEx> page,LenderQueryView query){
		
			String dataRights = DataScopeUtil.getDataScope("a", SystemFlag.FORTUNE.value);
			if(StringUtils.isNotEmpty(dataRights)){
				Map<String, String> sqlMap = new HashMap<String, String>();
				sqlMap.put("dataRights", dataRights);
				query.setSqlMap(sqlMap);
			}
		
		PageBounds pageBounds=new PageBounds(page.getPageNo(), page.getPageSize());
		query.setDictCustomerState(CustomerState.YKH.value);
		query.setDictChangeState(LenderchgState.SHTG.value);
		query.setManagerId(UserUtils.getUserId());
		query.setApplyHostedStatus(TrustState.KHSB.value);
		pageBounds.setFilterOrderBy(BooleanType.FALSE);
		pageBounds.setCountBy("custCode");
		PageList<CustomerEx> pageList = (PageList<CustomerEx>)this.lcDao.findList(query,pageBounds);
		List<Role> orgcodetype = UserUtils.getUser().getRoleList();
		for (Role role : orgcodetype) {
			if(role.getId().equals(FortuneRole.FINANCING_MANAGER.id)){
				query.setRoleFlag("1");  // 处理各种角色登录办理按钮显示问题
			}
		}
		PageUtil.convertPage(pageList, page);
		List<CustomerEx> list = page.getList();
		FortuneOrg org = null;
		List<FortuneOrg> orgList = null;
		List<FortuneUser> members = null;
		if (ArrayHelper.isNotEmpty(list)) {
			for (CustomerEx c : list) {
//				org = RelationShipUtil.getUserOrg(c.getManagerId(),
//						FortuneOrgType.TEAM.key);
				org = RelationShipUtil.getTeamOrg(c.getManagerId());
				if(c.getTrusteeshipNo()==null||"".equals(c.getTrusteeshipNo())){
					c.setIsGoldAccount(YESNO.NO.toString());
				}else{
					c.setIsGoldAccount(YESNO.YES.toString());
				}
			}
		}
		return page;
	}
	
	/**
	 * 更新变更步骤
	 * 2015年12月10日
	 * By 郭才林
	 * @param changeInfo
	 */
	@Transactional(value = "fortuneTransactionManager" , readOnly = false)
	public void updateChangeState(LenderChangeInfo changeInfo){
		
		lcDao.updateChangeState(changeInfo);
	}
	
	/**
	 * 下载出借人信息变更书
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
			logger.error("下载"+filename+"失败",e);
			FortuneException forException = new FortuneException();
			forException.setLoanCode(null);
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setNode(FortuneLogNode.LENDER_CHANGE_APPLY.getCode());
			forException.setImportantLevel(FortuneLogLevel.BLUE.value);
			forException.setRemark("下载"+filename+"失败");

			forException.preInsert();
			fortuneExceptionDao.insert(forException);
		}		
		
	}
	
	
}
