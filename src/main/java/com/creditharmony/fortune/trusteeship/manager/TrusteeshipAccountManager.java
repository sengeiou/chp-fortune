package com.creditharmony.fortune.trusteeship.manager;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.bean.BaseOutInfo;
import com.creditharmony.adapter.bean.in.jzh.JzhRegisterInfo;
import com.creditharmony.adapter.bean.in.thirdpay.ProtocolLibraryInfo;
import com.creditharmony.adapter.bean.out.ca.Ca_SignOutBean;
import com.creditharmony.adapter.bean.out.jzh.JzhRegisterOutInfo;
import com.creditharmony.adapter.bean.out.thirdpay.ProtocolLibraryReturnInfo;
import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.adapter.constant.ServiceType;
import com.creditharmony.adapter.core.client.ClientPoxy;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.common.type.CertificateType;
import com.creditharmony.core.common.type.SmsState;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.deduct.type.ProtocolLibraryAccountType;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.entity.CaCustomerSign;
import com.creditharmony.core.fortune.type.CASignType;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.FuYouAccountBackState;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.ReportType;
import com.creditharmony.core.fortune.type.SmsType;
import com.creditharmony.core.fortune.type.TrustState;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.dm.bean.DocumentBean;
import com.creditharmony.dm.filenet.CEContextHelper;
import com.creditharmony.dm.filenet.CEContextHolder;
import com.creditharmony.dm.filenet.CERequestContext;
import com.creditharmony.dm.service.DmService;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.common.service.AttachmentManager;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.deduct.common.Constant;
import com.creditharmony.fortune.sms.dao.SmsDao;
import com.creditharmony.fortune.sms.entity.SmsHis;
import com.creditharmony.fortune.trusteeship.dao.FyCodeDao;
import com.creditharmony.fortune.trusteeship.dao.TrusteeshipAccountDao;
import com.creditharmony.fortune.trusteeship.entity.FyCode;
import com.creditharmony.fortune.trusteeship.entity.ProtocalExportExcel;
import com.creditharmony.fortune.trusteeship.entity.TrusteeshipAccount;
import com.creditharmony.fortune.trusteeship.entity.TrusteeshipImportExcel;
import com.creditharmony.fortune.utils.CaUtil;

/**
 * 金账户开户
 * 
 * @Class Name TrusteeshipAccountManager
 * @author 朱杰
 * @Create In 2016年2月14日
 */
@Service
public class TrusteeshipAccountManager extends
		CoreManager<TrusteeshipAccountDao, TrusteeshipAccount> {
	
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private LoanApplyDao loanApplyDao;
	@Autowired
	private SmsDao smsDao;
	@Autowired
	private FyCodeDao fyCodeDao;
	@Autowired
	private AttachmentManager attachmentManager;
	@Autowired
	private FortuneExceptionDao forDao;
	
	
	/**
	 * 根据客户编号获取金账户信息
	 * 2016年4月27日
	 * By 朱杰
	 * @param customerCode
	 * @return
	 */
	public TrusteeshipAccount getAccountByCustomerCode(String customerCode){
		return dao.getAccountByCustomerCode(customerCode);
	}
	
	/**
	 * 金账户待开户列表（分页）
	 * 
	 * 2015年12月22日 By 朱杰
	 * 
	 * @param page
	 * @param search
	 * @return
	 */
	public Page<TrusteeshipAccount> getList(Page<TrusteeshipAccount> page,
			TrusteeshipAccount search) {
		PageBounds pageBounds = new PageBounds(page.getPageNo(),
				page.getPageSize());
		PageList<TrusteeshipAccount> pageList = (PageList<TrusteeshipAccount>) super.dao
				.findList(this.getParamMap(search, null), pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 金账户待开户列表
	 * 2016年3月6日
	 * By 朱杰
	 * @param customerCodeList
	 * @param lendCodeList
	 * @return
	 */
	public List<TrusteeshipAccount> getAllSelective(TrusteeshipAccount search,
			String[] customerCodes){
		return super.dao.findList(this.getParamMap(search, customerCodes));
	}
	
	/**
	 * 金账户导入 2016年2月29日
	 * 
	 * @param dataList
	 */
	public int importExcel(List<TrusteeshipImportExcel> dataList) {
		
		int updateCnt=0;
		for (TrusteeshipImportExcel row : dataList) {
			try{
				updateCnt+=this.importExcelEach(row);
			}catch(Exception e){
				logger.error("金账户导入",row);
			}
		}
		return updateCnt;
	}
	
	/**
	 * 协议库导出
	 * 2016年3月17日
	 * By 朱杰
	 * @param search
	 * @param lendCodes
	 * @return
	 */
	public List<ProtocalExportExcel> getProtocalList(TrusteeshipAccount search, String[] lendCodes) {
		return super.dao.findProtocalList(this.getParamMap(search, lendCodes));
	}


	/**
	 * 金账户开户接口参数填充 2016年2月29日 By 朱杰
	 * 
	 * @param elem
	 * @return
	 */
	public JzhRegisterInfo setRegisterInfo(TrusteeshipAccount elem) {

		JzhRegisterInfo registerInfo = new JzhRegisterInfo();
		// 流水号
		registerInfo.setMchntTxnSsn(IdGen.uuid().substring(10));
		// 客户姓名
		registerInfo.setCustNm(elem.getCustomerName());
		// 证件类型（0:身份证7：其他证件）
		registerInfo.setCertifTp(CertificateType.SFZ.getCode().equals(elem
				.getCustomerCertType()) ? "0" : "7");
		// 身份证号码/证件
		registerInfo.setCertifId(elem.getCustomerCertNum());
		// 手机号码
		registerInfo.setMobileNo(elem.getMobilephone());
		// 付款银行账号
		registerInfo.setCapAcntNo(elem.getAccountNo());
		// 开户行地区代码
		registerInfo.setCityId(elem.getAddrcityId());
		// 开户行
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("bankCode", elem.getBankId());
		FyCode bankCode = fyCodeDao.findBankCodeBySysCode(map);
		registerInfo.setParentBankId(bankCode == null ? "" : bankCode
				.getFyCode());

		return registerInfo;
	}

	/**
	 * 协议库对接 2016年2月29日 By 朱杰
	 * 
	 * @param elem
	 * @return
	 */
	public ProtocolLibraryInfo setLibraryInfo(TrusteeshipAccount elem) {
		ProtocolLibraryInfo info = new ProtocolLibraryInfo();
		// 银行代码
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bankCode", elem.getBankId());
		FyCode bankCode = fyCodeDao.findBankCodeBySysCode(map);
		info.setBankCode(bankCode == null ? "" : bankCode.getFyCode());
		// 账户名称
		info.setAccountName(elem.getAccountName());
		// 银行卡号
		info.setAcntNo(elem.getAccountNo());
		// 账户类型
		info.setAcntType(ProtocolLibraryAccountType.JJK.getCode());
		// 证件类型
		info.setCredtTp(CertificateType.SFZ.getCode().equals(elem
				.getCustomerCertType()) ? "0" : "7");
		// 证件编号
		info.setCredtNo(elem.getCustomerCertNum());
		// 银行卡绑定手机号
		info.setMobileNo(elem.getMobilephone());

		return info;
	}
	
	/**
	 * 组装参数
	 * 2016年3月17日
	 * By 朱杰
	 * @param search
	 * @param customerCodes
	 * @return
	 */
	public Map<String,Object> getParamMap(TrusteeshipAccount search,String[] customerCodes){
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(search.getCustomerName())) {
			map.put("customerName", search.getCustomerName());
		}
		if (StringUtils.isNotBlank(search.getCustomerCode())) {
			map.put("customerCode", search.getCustomerCode());
		}
		if (StringUtils.isNotBlank(search.getLendCode())) {
			map.put("lendCode", search.getLendCode());
		}
		if (StringUtils.isNotBlank(search.getCustomerCertNum())) {
			map.put("customerCertNum", search.getCustomerCertNum());
		}
		if (StringUtils.isNotBlank(search.getApplyHostedStatus())) {
			map.put("applyHostedStatus", search.getApplyHostedStatus());
		}
		if (StringUtils.isNotBlank(search.getBankId())
				&& search.getBankId().split(",").length > 0) {
			map.put("bankId", search.getBankId().split(","));
		}
		if(customerCodes!= null && customerCodes.length > 0){
			map.put("customerCodes", customerCodes);
		}
		return map;
	}
	
	/**
	 * 金账户开户 2016年2月23日 By 朱杰
	 * 
	 * @param customerCodeArr
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String openAccount(TrusteeshipAccount elem, boolean isChangeByHand) {
		//锁行 根据客户编号，更新时间
		Customer param = new Customer();
		param.setCustCode(elem.getCustomerCode());
		param.setVerTime(elem.getVerTime());
		param = customerDao.forUpdate(param);
		if(param == null){
			//已经被他人操作过
			return elem.getLendCode()+":已被其他人操作过<br/>";
		}
		String rtnMsg = "";
		// 调用金账户开户接口
		ClientPoxy serviceOpen = new ClientPoxy(
				ServiceType.Type.JZH_REGISTER);
		JzhRegisterInfo registerInfo = this.setRegisterInfo(elem);
		BaseOutInfo request = serviceOpen.callService(registerInfo);
		if (request.getRetCode().equals(FuYouAccountBackState.JYCG.value)) {
			// 开户成功,修改金账户账号，托管状态
			this.updateTrusteeship(elem, TrustState.KHCG.value,
					request.getRetCode(), request.getRetMsg(), isChangeByHand);
		} else if (request.getRetCode().equals(
				FuYouAccountBackState.XYKYZRQCGQT.value)) {
			// 协议库验证日期超过7天，则更新协议库
			ClientPoxy service = new ClientPoxy(
					ServiceType.Type.FY_SIGN_REQ);
			ProtocolLibraryInfo info = this.setLibraryInfo(elem);
			ProtocolLibraryReturnInfo plrInfo = (ProtocolLibraryReturnInfo) service
					.callService(info);
			if (plrInfo.getRetCode().equals(
					FuYouAccountBackState.JYCG.value)) {
				// 更新协议库成功，再次开户
				request = (JzhRegisterOutInfo) serviceOpen
						.callService(registerInfo);
				if (request.getRetCode().equals(
						FuYouAccountBackState.JYCG.value)) {
					// 开户成功,修改金账户账号，托管状态
					this.updateTrusteeship(elem, TrustState.KHCG.value,
							request.getRetCode(), request.getRetMsg(), isChangeByHand);
				} else {
					// 开户失败,修改返回码，返回值
					this.updateTrusteeship(elem, TrustState.KHSB.value,
							request.getRetCode(), request.getRetMsg(), isChangeByHand);
				}
			} else {
				// 开户失败，修改返回码，返回值
				this.updateTrusteeship(elem, TrustState.KHSB.value,
						request.getRetCode(), request.getRetMsg(), isChangeByHand);
			}
		} else {
			// 开户失败,修改返回码，返回值
			this.updateTrusteeship(elem, TrustState.KHSB.value,
					request.getRetCode(), request.getRetMsg(), isChangeByHand);
		}
		if (!request.getRetCode().equals(FuYouAccountBackState.JYCG.value)) {
			rtnMsg += elem.getCustomerName() + "【" + elem.getCustomerCode() + "】:" + request.getRetCode()
					+ request.getRetMsg() + "<br>";
		}
		return rtnMsg;
	}

	/**
	 * 协议库更新 2016年2月29日 By 朱杰
	 * 
	 * @param customerCodeList
	 * @param lendCodeList
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = true)
	public String exportProtocol(TrusteeshipAccount search,String[] customerCodes) {
		String rtnMsg = "";
		List<TrusteeshipAccount> list = super.dao.findList(this.getParamMap(search, customerCodes));
		for (TrusteeshipAccount elem : list) {
			ClientPoxy service = new ClientPoxy(ServiceType.Type.FY_SIGN_REQ);
			ProtocolLibraryInfo info = this.setLibraryInfo(elem);
			BaseOutInfo plrInfo = service.callService(info);
			if (!plrInfo.getRetCode().equals(FuYouAccountBackState.JYCG.value)) {
				// 协议库对接返回失败
				Customer cus = new Customer(elem.getCustomerCode());
				cus = customerDao.getCustomerbyCode(cus);
				rtnMsg += cus.getCustName() + "【" + cus.getCustCode() + "】" + ":" + plrInfo.getRetCode()
						+ plrInfo.getRetMsg() + "<br>";
			}
		}
		return rtnMsg;
	}

	
	
	/**
	 * 金账户导入单条
	 * 2016年4月21日
	 * By 朱杰
	 * @param row
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public int importExcelEach(TrusteeshipImportExcel row){
		int cnt=0;
		HashMap<String, Object> param = new HashMap<String, Object>();
		if (StringUtils.isBlank(row.getIdCard()) || StringUtils.isBlank(row.getName())) {
			return 0;
		}
		param.put("idCard", row.getIdCard());
		param.put("name", row.getName());
		List<Customer> customerList = customerDao.queryCustomer(param);
		if (ArrayHelper.isNotEmpty(customerList)) {
			for(Customer db : customerList){
				db.setTrusteeshipRetCode(row.getReturnCode());
				db.setTrusteeshipRetMsg(row.getReturnMark());
				if (FuYouAccountBackState.JYCG.value
						.equals(row.getReturnCode())) {
					db.setApplyHostedStatus(TrustState.KHCG.value);
				} else {
					db.setApplyHostedStatus(TrustState.KHSB.value);
				}
				db.setTrusteeshipNo(row.getMobilePhone());

				List<LoanApply> lendApplyList = loanApplyDao
						.loadAllLendApply(db.getCustCode());
				if (ArrayHelper.isNotEmpty(lendApplyList)) {
					for (LoanApply loanApply : lendApplyList) {
						if(
							loanApply.getPayType().equals(PayMent.ZJTG.value) 
							&& (loanApply.getLendStatus().equals(LendState.JZHDKH.value)|| loanApply.getLendStatus().equals(LendState.JZHKHSB.value))	
						){
							if (FuYouAccountBackState.JYCG.value.equals(row
									.getReturnCode())) {
								// 开户成功
								loanApply.setLendStatus(LendState.DFJSC.value);
							} else {
								// 开户失败
								loanApply.setLendStatus(LendState.JZHKHSB.value);
							}
							loanApply.preUpdate();
							loanApplyDao.update(loanApply);
						}						
					}
				}

				db.preUpdate();
				cnt = customerDao.updateTrusteeship(db);
			}
		}
		return cnt;
	}

	

	/**
	 * 金账户信息更新，成功的话，出借申请的状态变为待附件上传
	 * 
	 * 2016年3月1日 By 朱杰
	 * 
	 * @param elem
	 * @param applyHostedStatus
	 * @param retCode
	 * @param retMsg
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void updateTrusteeship(TrusteeshipAccount elem,
			String applyHostedStatus, String retCode, String retMsg, boolean isHandChange) {
		Customer customer = new Customer();
		customer.setCustCode(elem.getCustomerCode());	
		customer.setApplyHostedStatus(applyHostedStatus);
		customer.setTrusteeshipRetCode(retCode);
		customer.setTrusteeshipRetMsg(retMsg);
		customer.setVerTime(elem.getVerTime());
		customer.preUpdate();
		if (TrustState.KHCG.value.equals(applyHostedStatus)) {
			if(StringUtils.isNotBlank(elem.getMobilephone())){
				customer.setTrusteeshipNo(elem.getMobilephone());
			}else{
				Customer customerInfo = customerDao.getCustomerbyCode(customer);
				customer.setTrusteeshipNo(customerInfo.getCustMobilephone());
			}
			
		}
		int messager = customerDao.updateTrusteeship(customer);
		if(messager==0){
			throw new ServiceException("该数据更新出错，请详查是否已有人操作，请勿重复！");
		}
		
		// 查询待金账户开户的出借
		LoanApply customerLoan = new LoanApply();
		customerLoan.setCustCode(elem.getCustomerCode());
		customerLoan.setOrderBy("create_time desc");
		List<LoanApply> list = loanApplyDao.findList(customerLoan);
		
		for (LoanApply loanApply : list) {
			if(
				loanApply.getPayType().equals(PayMent.ZJTG.value) &&
				(loanApply.getLendStatus().equals(LendState.JZHDKH.value)|| loanApply.getLendStatus().equals(LendState.JZHKHSB.value))	
			){
				// 金账户待开户或者金账户开户失败的出借
				if (TrustState.KHCG.value.equals(applyHostedStatus)) {
					// 金账户开户成功则更新出借申请状态
					LoanApply update = new LoanApply();
					// 出借编号
					update.setApplyCode(loanApply.getApplyCode());
					// 出借状态
					update.setLendStatus(LendState.DFJSC.value);
					loanApplyDao.updateLendStatus(update);
				} else if(TrustState.KHSB.value.equals(applyHostedStatus)) {
					// 金账户开户成功则更新出借申请状态
					LoanApply update = new LoanApply();
					// 出借编号
					update.setApplyCode(loanApply.getApplyCode());
					// 出借状态
					if (isHandChange) {  //手动修改成失败       手动开户失败的状态为8（lend_status=8）, 
						update.setLendStatus(LendState.JZHKHSB.value);   //状态为8 金账户开户失败
					} else {  //调用金账户开户接口失败, lendStatus状态为 0  金账户待开户
						update.setLendStatus(LendState.JZHDKH.value);
					}
					loanApplyDao.updateLendStatus(update);
				}
			}
		}
		
		if(TrustState.KHCG.value.equals(applyHostedStatus)){
			// 开户成功则生成富友专用协议
			LoanApply loanApply = loanApplyDao.getLoanApplyByCode(list.get(0).getApplyCode());
			Attachment att = new Attachment();
			att.setAttaFileOwner("t_tz_loan_apply");
			att.setAttaTableId(loanApply.getId());
			
			if(!this.makeFyxy(elem.getCustomerCode(), list.get(0).getApplyCode(), elem.getMobilephone(), FortuneLogNode.TRUSTEESHIP_ACCOUNT.getCode(),att)){
				throw new ServiceException(elem.getCustomerCode()+":富友专用协议生成失败");
			}
		}
	}
	
	

	/**
	 * 保存发送履历
	 * 2016年3月6日
	 * By 朱杰
	 * @param elem 金账户信息
	 * @param content 发送内容
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void insertSmsHis(TrusteeshipAccount elem, String content){
		SmsHis smsHis = new SmsHis();
		//客户编号
		smsHis.setCustomerCode(elem.getCustomerCode());
		//客户姓名
		smsHis.setCustomerName(elem.getCustomerName());
		//借款编码
		smsHis.setLendCode(elem.getLendCode());
		//发送时间
		smsHis.setSendTime(new Date());
		//短信内容
		smsHis.setSmsMsg(content);
		//短信模板名称
		smsHis.setSmsTempletId(SmsType.JZH_KH.value);
		//发送状态
		smsHis.setSendStatus(SmsState.FSCG.value);
		smsHis.preInsert();
		smsDao.insertSmsHis(smsHis);
	}
	
	/**
	 * 富友专用协议作成
	 * 
	 * 2016年5月10日
	 * By 朱杰 
	 * @param customerCode 客户编号
	 * @param lendCode 出借编号
	 * @param node 节点：FortuneLogNode
	 * @param att 附件（AttaFileOwner,AttaTableId）
	 */
	public boolean makeFyxy(String customerCode,String lendCode, 
			String trusteeshipNo, String node, Attachment att){
		
		boolean r = false;
		if(StringUtils.isEmpty(customerCode) || StringUtils.isEmpty(lendCode)){
			return r;
		}
		try {			
			//模板地址
			String url=	Constant.getProperties.get("template_cpt_url");
			//模板类型
			String typeStr=Constant.getProperties.get(ReportType.FYXY.getCode());
			StringBuffer sb=new StringBuffer(url);
			sb.append(typeStr);
			sb.append("&customer_code="+customerCode);
			sb.append("&trusteeship_no="+trusteeshipNo);
			sb.append("&format=pdf");		
		
			//CE认证
			CERequestContext ceRequestContext = CEContextHelper.loginWithUP(
					Constant.getProperties.get("ceuser"),
					Constant.getProperties.get("cepwd"));
			CEContextHolder.setContext(ceRequestContext);
			// 创建连接
			// 构建url
			URL uri = null;
				uri = new URL(sb.toString());
				
			URLConnection conn = uri.openConnection();
			InputStream inStream = conn.getInputStream();
			DmService dmService = DmService.getInstance();
			// 把生成的文档上传到文件服务器
			DocumentBean doc = dmService.createDocument(ReportType.FYXY.getName()+lendCode+".pdf", inStream,
					DmService.BUSI_TYPE_FORTUNE, customerCode, lendCode,
					UserUtils.getUser(UserUtils.getUserId()).getName());
			if(doc!=null&&StringUtils.isNotEmpty(doc.getDocId())){
				// CA签章
				Customer cus = new Customer(customerCode);
				cus = customerDao.getCustomerbyCode(cus);
				CaCustomerSign signParam = new CaCustomerSign(cus.getCustName(), "账户开户人签字：", cus.getCustCertNum());
				signParam.setBatchNo(cus.getCustCode());
				signParam.setSubType(lendCode);
				Ca_SignOutBean out = CaUtil.sign(CASignType.FYXY.value, doc.getDocId(), signParam);
				if(ReturnConstant.SUCCESS.equals(out.getRetCode())){
					//附件表中保存出借申请书，出借状态改为待审批
					att.preInsert();
					att.setAttaId(att.getId());
					att.setAttaFilepath(out.getDocId());
					att.setAttaFilename(ReportType.FYXY.getName()+DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+".pdf");// 文件名
					att.setAttaNewfilename(ReportType.FYXY.getName()+DateUtils.getDate("yyyy-MM-dd HH:mm:ss")+".pdf");// 新文件名
					att.setLoanCode(lendCode);
					att.setDictAttaFlag(FileConstants.FILE_TYPE_FYXY);					
					attachmentManager.saveFile(att);
					
					//dmService.moveDocument(out.getDocId(), cus.getCustCode()+"/"+lendCode, DmService.BUSI_TYPE_FORTUNE);
					
					r = true;	
				}							
			}
		} catch (Exception e) {
			FortuneException forException = new FortuneException();
			forException.setLoanCode(lendCode);
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setNode(node);
			forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
			forException.setRemark("生成富友协议失败");
			
			forException.preInsert();
			forDao.insert(forException);
			
		}
		return r;
	}
}
