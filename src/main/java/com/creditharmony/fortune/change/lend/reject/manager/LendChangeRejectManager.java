package com.creditharmony.fortune.change.lend.reject.manager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.CertificateType;
import com.creditharmony.core.dict.util.DictUtils;
import com.creditharmony.core.excel.util.ExportExcel;
import com.creditharmony.core.fortune.type.LendchgType;
import com.creditharmony.core.fortune.type.LenderchgState;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.dm.bean.DocumentBean;
import com.creditharmony.dm.service.DmService;
import com.creditharmony.fortune.change.lend.apply.dao.LendChangeDao;
import com.creditharmony.fortune.change.lend.apply.entity.LendChangeInfo;
import com.creditharmony.fortune.change.lend.apply.entity.LendLaunchView;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApply;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApplyEx;
import com.creditharmony.fortune.change.lender.apply.service.LenderChangeApplyManager;
import com.creditharmony.fortune.change.lender.reject.service.LenderChangeRejectManager;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.service.AttachmentManager;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.entity.CustomerAccount;
import com.creditharmony.fortune.trusteeship.entity.TrusteeshipExportModel;
import com.creditharmony.fortune.utils.AttachmentUtil;
import com.creditharmony.fortune.utils.OptionUtil;

/**
 * 出借信息修改service层
 * @Class Name LendChangeManager
 * @author 刘雄武
 * @Create In 2015年12月1日
 */
@Service
public class LendChangeRejectManager extends CoreManager<LendChangeDao, LendLoanApplyEx> {
	@Resource
	private LendChangeDao lecDao;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	protected AttachmentManager attachmentService;
	@Autowired
	protected LenderChangeRejectManager lenderChangeManager;
	
	/**
	 * 重新发起流程保存数据
	 * 2015年12月16日
	 * By 刘雄武
	 * @param view
	 */
	public void updateChangeInfo(LendLaunchView view){
		String changeID=view.getLendchangeinfo().getChangeId();
		//String operName="出借信息变更重新发起";
		LendChangeInfo change = lecDao.getChangeInfoById( view.getLendchangeinfo().getChangeId());
		// 变更后json信息
		CustomerAccount payAccount = lecDao.getCustomerPayAccount(view.getLendloanapply().getApplyCode());
		if(view.getCountAfter().getId().equals(payAccount.getId())){ // 判断汇款和付款为同一银行
			view.setCountAfter(payAccount);
		}
		String changeAfter = JsonMapper.toJsonString(view);
		change.setChangeAfter(changeAfter);
		change.setDictChangeState(LenderchgState.DMDSH.value);//重新发起流程保存变更状态
		change.setChangeId(changeID);
		List<String> delList=view.getDeleteAttachmentId();
		// 重新生成银行卡附件
		if(LendchgType.TRUSTESSHIP_CARD_CHA.value.equals(change.getDictChangeType())){
			
			@SuppressWarnings("unchecked")
			Map<String, Object> p=new HashedMap();
			List<String> ids=new ArrayList<String>();
			
			if("".equals(change.getApplyId())||change.getApplyId()!=null){
				if(delList==null){
					delList=new ArrayList<String>();
				}
				ids.add(change.getApplyId());
				p.put("ids", ids);
				p.put("AttaFileOwner", "t_tz_changer");
				p.put("dictAttaFlag", "bg_"+LendchgType.TRUSTESSHIP_CARD_CHA.value);
				//operName = LendchgType.TRUSTESSHIP_CARD_CHA.value;
				List<Attachment> list = lenderChangeManager.getAttachmentByApplyIds(p);
				for (Attachment attachment : list) {
					delList.add(attachment.getAttaId());
				}
			}
			BaseBusinessView begin = this.getLendLoanApplybefore(view.getLendloanapply().getApplyCode());
			LendLaunchView v=(LendLaunchView)begin;
			changeCardFile(view, changeID, v);
		}
		lecDao.updateChangeInfo(change);
		AttachmentUtil.updateAndDeleteAttchment(view.getAddAttachmentId(), delList,view.getLendloanapply().getApplyCode(),changeID,"t_tz_changer");
		// 全程留痕
				String operInfo=LendchgType.getLendchgType(LendchgType.LEND_CHG.value)+
						"[重新发起]";
				checkManager.addCheck(change.getApplyId(),operInfo
						, "提交");
		// 将附件重temp目录下保存在正确的路径下
		DmService dmService = DmService.getInstance();
		List<String> addAttachmentIdList = view.getAddAttachmentId();
		if(addAttachmentIdList!=null){
			for (String addAttachmentId : addAttachmentIdList) {
				Attachment attachment = attachmentService.get(addAttachmentId);
				if(attachment!=null){
					String attaFilepath = attachment.getAttaFilepath();
					if(StringUtils.isNotBlank(attaFilepath)){
						dmService.moveDocument(attaFilepath,view.getLendloanapply().getCustCode()+"/"+view.getLendloanapply().getApplyCode(),DmService.BUSI_TYPE_FORTUNE);
					}
				}				
			}	
		}
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
    
}
