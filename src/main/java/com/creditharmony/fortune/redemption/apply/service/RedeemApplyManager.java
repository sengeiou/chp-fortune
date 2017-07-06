package com.creditharmony.fortune.redemption.apply.service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import com.creditharmony.bpm.frame.service.ApplyIdService;
import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.bpm.utils.SpringUtil;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.fortune.type.RedeemType;
import com.creditharmony.core.fortune.type.ReportType;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.mybatis.paginator.domain.Order;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.dm.service.DmService;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.common.service.AttachmentManager;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.deduct.common.Constant;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.redemption.common.dao.LoanWorkflowDao;
import com.creditharmony.fortune.redemption.common.dao.RedemptionApplyDao;
import com.creditharmony.fortune.redemption.common.dao.RedemptionDao;
import com.creditharmony.fortune.redemption.common.dao.RedemptionLogDao;
import com.creditharmony.fortune.redemption.common.entity.LoanWorkflow;
import com.creditharmony.fortune.redemption.common.entity.RedemptionApply;
import com.creditharmony.fortune.redemption.common.entity.RedemptionLog;
import com.creditharmony.fortune.redemption.common.entity.ext.Redemptionex;
import com.creditharmony.fortune.redemption.common.service.RedeemManager;
import com.creditharmony.fortune.redemption.common.view.RedemptionApplyEntity;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.redemption.constant.RedeemState;
import com.creditharmony.fortune.utils.AttachmentUtil;

/**
 * 赎回申请Service
 * @Class Name ApplyManager
 * @author 陈广鹏
 * @Create In 2016年4月14日
 */
@Service
public class RedeemApplyManager extends CoreManager<RedemptionDao, Redemptionex> {

	@Autowired
	private RedemptionDao reDao;
	@Autowired
	private RedemptionApplyDao applyDao;
	@Autowired
	private RedemptionLogDao logDao;
	@Autowired
	private LoanApplyDao loanApplyDao;
	@Autowired
	private LoanWorkflowDao workflowDao;
	@Autowired
	private FlowService flowService;
	@Autowired
	private ApplyIdService apidService;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	private RedeemManager commonManager;
	@Autowired
	private AttachmentManager attachmentManager;
	
	/**
	 * 发起流程
	 * 2015年12月31日
	 * By 陈广鹏
	 * @param redemptionApplyEntity
	 * @param workItemView
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void launchFlow(RedemptionApplyEntity redemptionApplyEntity, WorkItemView workItemView){
		
		redemptionApplyEntity.setApplyDate(new Date());
		redemptionApplyEntity.setApplyId(apidService.builderApplyId(workItemView.getFlowType()));
		workItemView.setBv(redemptionApplyEntity);
		
		flowService.launchFlow(workItemView);
	}

	/**
	 * 获取分页列表数据
	 * 2015年12月4日
	 * By 陈广鹏
	 * @param page
	 * @param redemptionex
	 * @return
	 */
	public Page<Redemptionex> findItemList(Page<Redemptionex> page,
			Redemptionex redemptionex) {
		// 配置数据权限
		String dataRights = DataScopeUtil.getDataScope("cust", SystemFlag.FORTUNE.value);
		if(StringUtils.isNotEmpty(dataRights)){
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			redemptionex.setSqlMap(sqlMap);
		}
		
		String sortString = null;
		// 设置过滤条件
		// 出借状态：划扣成功
		redemptionex.setLendStatus(LendState.HKCG.value);
		// 完结状态：未完结
		redemptionex.setDictApplyEndState(FinishState.WWJ.value);
		
		// 付款方式为赎回内转的出借在90天内，不允许提前赎回
		redemptionex.setLimitApplyPay(PayMent.SHNZ.value);
		redemptionex.setLimitDay(RedeemConstant.LIMIT_DAY);
		
		String city = redemptionex.getAddrCity();
		if (null != city && city.length()>0) {
			String c = "%" + city.replace(",", "%|%") +"%";
			redemptionex.setAddrCity(c);
		}
		
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize(), Order.formString(sortString ));
		pageBounds.setCountBy("lend_code");
		PageList<Redemptionex> dataList = (PageList<Redemptionex>) reDao.findByParams(redemptionex, pageBounds);
		PageUtil.convertPage(dataList, page);
		redemptionex.setAddrCity(city);
		return page;
	}

	/**
	 * 下载提前赎回申请表
	 * 2016年3月9日
	 * By 陈广鹏
	 * @param response
	 * @param map
	 */
	public void downloadTemplate(HttpServletResponse response, RedemptionApplyEntity entity){
		
		String lendCode = entity.getLendCode();
		String customerName=DeductUtils.isNull(entity.getCustomerName());
		LoanApply loanApply = loanApplyDao.getLoanApplyByCode(lendCode);
		Date divide = DateUtils.parseDate(RedeemConstant.DOWNLOAD_DEVIDE_DATE);
		String contractVersion = loanApply.getProtocoEdition();
		Date lendDate = loanApply.getLendDate();
		
		String cpt = ReportType.TQSH_AFTER4.getCode();
		if (divide.after(lendDate)) {
			// 出借日期在 2016-04-01 之前
			if (RedeemConstant.SPECIAL_FLOW_EDITION.contains(contractVersion)) {
				cpt = ReportType.TQSH161_BEFORE4.getCode();
			} else {
				cpt = ReportType.TQSH_BEFORE4.getCode();
			}
		} else {
			// 出借日期在 2016-04-01 之后
			if (RedeemConstant.SPECIAL_FLOW_EDITION.contains(contractVersion)) {
				cpt = ReportType.TQSH161_AFTER4.getCode();
			} else {
				cpt = ReportType.TQSH_AFTER4.getCode();
			}
		}
		
		String filecpt = Constant.getProperties.get(cpt);
		
		String filename = customerName+"提前赎回申请表.pdf";
		String format = "pdf";
		String http = Constant.getProperties.get("template_cpt_url");
		http = http +filecpt;
		http = http + "&lend_code="+lendCode;
		http = http + "&format="+format;
		try {
			// 设置Header信息
			response.setContentType("APPLICATION/OCTET-STREAM");  
			response.setHeader("Content-Disposition",
					"attachment; filename=" +  new String( filename.getBytes("gb2312"), "ISO8859-1" ));
			
			URL url1 = new URL(http);
			// 创建连接
			URLConnection conn = url1.openConnection();
			InputStream inStream = conn.getInputStream();
			// 文件转换成数据流输出，最后关闭输入，输出数据流
			FileCopyUtils.copy(inStream, response.getOutputStream());
		} catch (Exception e) {
			FortuneException forException = new FortuneException();
			forException.setLoanCode(null);
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setNode(FortuneLogNode.REDEMPTION_APPLY.getCode());
			forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
			forException.setRemark("下载提前赎回申请表失败");
			
			forException.preInsert();
			FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
			forDao.insert(forException);
		}	
	}

	/**
	 * 提交申请时验证剩余金额
	 * 2016年3月10日
	 * By 陈广鹏
	 * @param entity
	 * @return
	 */
	public String applyCheck(RedemptionApplyEntity entity) {
		String result = "剩余金额不可低于5万，请重新输入赎回金额"; // 赎回再出借金额不可低于5万
		RedemptionApplyEntity e = reDao.getRedemptionByLendCode(entity.getLendCode());
		BigDecimal residualAmount = e.getApplyLendMoneyd().subtract(entity.getRedemptionMoney());
		if (residualAmount.compareTo(new BigDecimal(RedeemConstant.LOW_LIMIT)) == -1) {
			// 低于最低出借金额时，判断
			if (RedeemConstant.CONTRACT_EDT_LIST.contains(e.getApplyAgreementEdition())) {
				result="true";
			}
			// 条件2：
			Date sd = DateUtils.parseDate(RedeemConstant.DEVIDE_DATE);
			if (sd.after(e.getApplyLendDay())) {
				if (ProductCode.NNY.value.equals(e.getProductCode())) {
					result="true";
				}
				if (ProductCode.NNJ.value.equals(e.getProductCode())) {
					result="true";
				}
			}
			// 条件3：
			if (ProductCode.NNJ.value.equals(e.getProductCode())) {
				Date NNJsd = DateUtils.parseDate("2016-01-01");
				if (NNJsd.after(e.getApplyLendDay())) {
					// 出借日期是2016-01-01以前
					if (residualAmount.compareTo(new BigDecimal("10000")) == -1) {
						// 剩余金额 < 1W
						result = "年年金部分赎回剩余金额不能小于1万，请重新输入赎回金额";
					} else {
						result = "true";
					}
				} else {
					// 出借日期是2016-01-01以后（包括2016-01-01）
					if (residualAmount.compareTo(new BigDecimal(RedeemConstant.LOW_LIMIT)) == -1) {
						// 剩余金额 < 5W
						result = "年年金部分赎回剩余金额不能小于5万，请重新输入赎回金额";
					} else {
						result = "true";
					}
				}
			}
		} else {
			result="true";
		}
		return result;
	}
	
	/**
	 * 流程发起时保存数据
	 * 2015年12月31日
	 * By 陈广鹏
	 * @param entity
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void dataSave(RedemptionApplyEntity entity) {
		
		checkApply(entity.getLendCode());
		
		// 1.赎回申请表插入一条数据
		RedemptionApply apply = fillRedemptionApply(entity);
		applyDao.insetApply(apply);

		// 2.赎回审批记录表插入一条记录
		RedemptionLog log = fillRedemptionLog(entity, apply);
		logDao.insertRedemptionLog(log);

		// 3.出借流程状态信息表插入一条数据
		LoanWorkflow workflow = fillLoanWorkflow(entity);
		workflowDao.insertWorkflow(workflow);
		
		// 附件添加删除
		AttachmentUtil.updateAndDeleteAttchment(entity.getAddAttachmentId(),
				entity.getDeleteAttachmentId(), apply.getLendCode(), apply.getRedemptionId(),
				"t_tz_redemption");
		
		// CE附件移动
		DmService dmService = DmService.getInstance();
		List<String> addAttachmentIdList = entity.getAddAttachmentId();
		if(null != addAttachmentIdList){
			for (String addAttachmentId : addAttachmentIdList) {
				Attachment attachment = attachmentManager.get(addAttachmentId);
				if(attachment!=null){
					dmService.moveDocument(attachment.getAttaFilepath(),
							entity.getCustomerCode()+"/"+apply.getLendCode(),DmService.BUSI_TYPE_FORTUNE);
				}				
			}
		}
		
		// 记录全程留痕
		String lendCode = apply.getLendCode();
		String operatorInfo = "发起提前赎回申请";
		String operatorType = "提前赎回申请";
		checkManager.addCheck(lendCode, operatorInfo, operatorType, apply.getRedemptionId(), FortuneLogNode.REDEMPTION_APPLY);
	}
	
	/**
	 * 描述：填充出借流程状态Bean 
	 * 2015年12月1日 
	 * By 陈广鹏
	 * @param entity
	 * @return
	 */
	private LoanWorkflow fillLoanWorkflow(RedemptionApplyEntity entity) {

		LoanWorkflow workflow = new LoanWorkflow();
		workflow.setApplyId(entity.getApplyId());
		workflow.setApplyType(RedeemConstant.REDT_FLOW_TYPE);
		workflow.setLendCode(entity.getLendCode());
		workflow.preInsert();
		workflow.setDictStatus(RedeemState.DSP.value);
		return workflow;
	}

	/**
	 * 描述：填充审批Log Bean 
	 * 2015年12月1日 
	 * By 陈广鹏
	 * @param itemView
	 * @param apply
	 * @return
	 */
	private RedemptionLog fillRedemptionLog(RedemptionApplyEntity entity,
			RedemptionApply apply) {

		RedemptionLog log = new RedemptionLog();// 记录表对象
		log.setApplyId(entity.getApplyId());
		log.setRedemptionId(apply.getRedemptionId());
		log.setDictRedemptionStatus(RedeemState.DSP.value);
		log.setApplyBy(UserUtils.getUserId());
		log.setApplyTime(new Date());
		log.setCheckTime(new Date());
		log.preInsert();
		return log;
	}

	/**
	 * 描述：填充赎回申请Bean 
	 * 2015年12月1日 
	 * By 陈广鹏
	 * @param entity
	 * @return
	 */
	private RedemptionApply fillRedemptionApply(RedemptionApplyEntity entity) {
		RedemptionApply apply = new RedemptionApply();// 申请表对象
		// 更新数据库
		apply.setLendCode(entity.getLendCode());
		apply.setCustomerCode(entity.getCustomerCode());
		apply.setRedemptionTime(entity.getRedemptionTime());
		apply.setRedemptionType(entity.getRedemptionType());
		apply.setRedemptionReceType(entity.getRedemptionReceType());
		apply.setLinitDay(entity.getLinitDay());
		apply.setFeedback(entity.getFeedback());
		if (YoN.FOU.value.equals(entity.getFeedback())) {
			apply.setFeedbackMoney(BigDecimal.ZERO);
		} else {
			apply.setFeedbackMoney(entity.getFeedbackMoney());
		}
		apply.setFeedbackRemark(entity.getFeedbackRemark());
		
		if (RedeemType.QBSH.value.equals(entity.getRedemptionType())) {
			// 全部赎回，则赎回金额为出借金额
			apply.setRedemptionMoney(new BigDecimal(entity.getApplyLendMoney()));
			apply.setResidualAmount(BigDecimal.ZERO);
		} else {
			apply.setRedemptionMoney(entity.getRedemptionMoney());
			apply.setResidualAmount(entity.getApplyLendMoneyd().subtract(apply.getRedemptionMoney()));
		}

		if (!RedeemConstant.SPECIAL_FLOW_EDITION.contains(entity.getApplyAgreementEdition())) {
			// 合同版本不是特殊版本时，计算
			commonManager.calculateMoney(entity, apply);
		} else {
			apply.setRedemptionBmoney(BigDecimal.ZERO);
			apply.setRedemptionDemoney(BigDecimal.ZERO);
		}
		apply.setRedemptionStatus(RedeemState.DSP.value);
		apply.setRedemptionId(IdGen.uuid());
		apply.preInsert();
		return apply;
	}
	
	private int checkApply(String lendCode){
		int count = applyDao.countApply(lendCode);
		if (count>0) {
			String msg = "出借 " +lendCode+" 已发起赎回流程，不允许再次发起赎回申请！";
			logger.error(msg);
			throw new ServiceException(msg);
		}
		return 0;
	}

}
