
package com.creditharmony.fortune.creditor.matching.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.common.type.EmailState;
import com.creditharmony.core.fortune.type.BilltakenMode;
import com.creditharmony.core.fortune.type.FileType;
import com.creditharmony.core.fortune.type.FilecpState;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.MailTemplateType;
import com.creditharmony.core.fortune.type.MatchingStatus;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.dm.filenet.CEContextHelper;
import com.creditharmony.dm.filenet.CERequestContext;
import com.creditharmony.fortune.borrow.entity.CreditorTrade;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.common.dao.AttachmentDao;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.common.entity.ReturnMsg;
import com.creditharmony.fortune.common.service.FortuneExceptionService;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.creditor.matching.constant.Constant;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDao;
import com.creditharmony.fortune.creditor.matching.entity.ext.CreditorTradeEx;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;
import com.creditharmony.fortune.creditor.matching.service.CreditorAidManager;
import com.creditharmony.fortune.creditor.matching.service.CreditorSendManager;
import com.creditharmony.fortune.creditor.matching.service.MatchingCreditorManager;
import com.creditharmony.fortune.creditor.matching.utils.ExportExcelUtilsOfSend;
import com.creditharmony.fortune.creditor.matching.view.MatchingCreditorView;
import com.creditharmony.fortune.deduct.common.QueryFilter;
import com.creditharmony.fortune.mail.entity.EmailInfo;
import com.creditharmony.fortune.mail.entity.EmailTemplate;
import com.creditharmony.fortune.mail.manager.EmailInfoManager;
import com.creditharmony.fortune.utils.FileUtil;
import com.creditharmony.fortune.utils.FortuneDictUtil;
import com.creditharmony.fortune.utils.MailUtil;

/**
 * 债权发送
 * @Class Name CreditorSendController
 * @author 胡体勇
 * @Create In 2015年12月9日
 */
@Controller
@RequestMapping(value = "${adminPath}/creditor/creditorSend")
public class CreditorSendController extends BaseController {
	
	@Autowired
	private CreditorSendManager creditorSendManager;
	
	@Autowired
	 private MatchingCreditorDao matchingCreditorDao;
	
	@Autowired
	private MatchingCreditorManager matchingCreditorManager;
	
	@Autowired
	private AttachmentDao attachmentDao;
	
	@Autowired
	private EmailInfoManager emailInfoManager;
	
	@Autowired
	private CreditorAidManager creditorAidManager;
	
	@Autowired
	private FortuneExceptionService fortuneExceptionService;
	
	/**
	 * 债权发送列表
	 * 2015年12月9日
	 * By 胡体勇
	 * @param creditorTradeEx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"list",""})
	public String findList(CreditorTradeEx creditorTradeEx, HttpServletRequest request,
			HttpServletResponse response, Model model){
		//页面检索条件复制
		CreditorTradeEx search = (CreditorTradeEx)SerializationUtils.clone(creditorTradeEx);
		// 分页查询
		Page<CreditorTradeEx> page = new Page<CreditorTradeEx>(request,response);
		page = creditorSendManager.findPage(page, search);
		CreditorTradeEx cte = creditorSendManager.countMoney(creditorTradeEx);
		// 设置页面列表显示
		model.addAttribute("page", page);
		model.addAttribute("creditorTradeEx",creditorTradeEx);
		if(cte == null){
			model.addAttribute("totalMoney","");
		}else{
			model.addAttribute("totalMoney", cte.getTotalMoney());
		}
		// 获取页面字典项值
		String[] types = {"tz_pay_type","tz_finish_state","tz_settle_status","tz_bill_state","com_email_state","tz_taken_mode","tz_filecp_state","tz_bill_day","tz_matching_flag"};
		FortuneDictUtil.addDicts(model, types);
		return "/creditor/creditorSendList";
	}
	
	/**
	 * 
	 * 2015年12月21日
	 * By 胡体勇
	 * @param creditorTradeEx
	 * @param model
	 * @return
	 */
	@RequestMapping(value="showDialog")
	public String showDialog(CreditorTradeEx creditorTradeEx,Model model){
		model.addAttribute("creditorTradeEx",creditorTradeEx);
		return "/creditor/creditorSendShowModalDialog";
	}
	
	/**
	 * 批量发送协议
	 * 2015年12月23日
	 * By 胡体勇
	 * @param ids
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="sendProtocol")
	@ResponseBody
	public String sendProtocol(String ids,HttpServletRequest request,
			HttpServletResponse response){
		String[] id = ids.split(";");
		StringBuffer mesg = new StringBuffer();
    	for(int j=0;j<id.length;j++){
    		String[] str = id[j].split(",");
    		try{
	    		 Map<String,Object> map = new HashMap<String,Object>();
				 map.put("attaTableId", str[0]);
				 map.put("attaFileType", "pdf");
				// 获取附件文件列表
				List<Attachment> list = creditorSendManager.batchDownload(map);	
				if(list!=null && list.size()>0){
					Attachment attachment = list.get(0);
					String returnStr = sendEmail(attachment.getAttaTableId(),attachment.getAttaFilepath());
					if(returnStr != null){
						mesg.append(returnStr).append("<br>");
					}
				}else{
					mesg.append("业务编号【"+str[0]+"】债权文件没有合成，请请重合成后再发送协议").append("<br>");
				}
    		}catch(Exception e){
    			logger.error("批量发送协议："+e.getLocalizedMessage(),e);
    			mesg.append("批量发送协议出错，错误信息为:"+e.getMessage()).append("<br>");
    			FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, str[0],
						FortuneLogNode.CREDITOR_SEND.getCode(), FortuneLogLevel.YELLOW.value, 
						"业务编号【"+str[0]+"】发送债权协议出错"));
    		}
			
    	}
		return jsonMapper.toJson(mesg.length()>0 ? mesg.toString() : Constant.OK);
	}
	
	/**
	 * 撤销债权
	 * 2015年12月24日
	 * By 胡体勇
	 */
	@RequestMapping(value="cancelCreditor")
	@ResponseBody
	public String cancelCreditor(CreditorTrade creditorTrade,HttpServletRequest request,
			HttpServletResponse response){
		int result = 0;
		try{
			result = creditorSendManager.cancelCreditor(creditorTrade);
		}catch(Exception e){
			FortuneException forException = new FortuneException();
			forException.setLoanCode(creditorTrade.getMatchingId());
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setNode(FortuneLogNode.BORROW_CANCEL.getCode());
			forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
			forException.setRemark("债权协议加盖作废章失败");
			fortuneExceptionService.save(forException);
			
			return jsonMapper.toJson(new ReturnMsg(e.getMessage()));
		}
		return jsonMapper.toJson(new ReturnMsg(Constant.OK));
		}
	
	
	/**
	 * 导出excel表
	 * 2016年4月29日
	 * By 高士芳
	 * @param creditorTrade
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="outExcel")
	public void outExcel(CreditorTradeEx search,HttpServletRequest request,
			HttpServletResponse response){
			//设置债权匹配状态为"已推荐"
		    search.setMatchingStatus(MatchingStatus.YTJ.value);
			//判断页面是通过搜索栏导出，还是通过复选框导出
			search = creditorSendManager.judgeIds(search);
			String fileName = Constant.CREDITOR_SEND_TITLE+"_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			String namespace = "com.creditharmony.fortune.creditor.matching.dao.CreditorSendDao.findList";
			ExportExcelUtilsOfSend.exportData(search, response, namespace, fileName);	
	}
	
	/**
	 * 批量下载协议word/pdf
	 * 2015年12月26日
	 * By 胡体勇
	 */
	@RequestMapping(value="batchDownload")
	public void batchDownload(CreditorTradeEx creditorTradeEx,HttpServletRequest request,
			HttpServletResponse response){
		// 获取页面参数
		QueryFilter filter = new QueryFilter(request);
		String ids = (String) filter.getFilters().get("ids");
		String attaFileType = (String) filter.getFilters().get("attaFileType");
		 List<String> attaTableIds = new ArrayList<String>();
		// 解析页面传过来的ids从中分离出待推荐Id
		if(ids != null){
			 String[] str = ids.split(";");
			 for(int i=0;i<str.length;i++){
				 String[] s = str[i].split(",");
				 attaTableIds.add(s[0]);
			 }
			
			 Map<String,Object> map = new HashMap<String,Object>();
			 map.put("attaTableIds", attaTableIds);
			 map.put("attaFileType", attaFileType);
			// 获取附件文件列表
			List<Attachment> list = creditorSendManager.batchDownload(map);	
			/*if(list.size() == 0){
				return jsonMapper.toJson(Constant.NG);
			}else{*/
				// 压缩文件并下载
				FileUtil.zipFileDownload(FileType.ZRSR.getName(),response,list);
			//}
		}
		
	}
	
	/**
	 * 单个下载协议
	 * 2015年12月26日
	 * By 胡体勇
	 */
	@RequestMapping(value="fileDownload")
	public void fileDownload(HttpServletRequest request,
			HttpServletResponse response){
		// 获取页面参数
		QueryFilter filter = new QueryFilter(request);
		String matchingId = (String) filter.getFilters().get("matchingId");
		String attaFileType = (String) filter.getFilters().get("attaFileType");
		if(matchingId != null){
		    Map<String,Object> map = new HashMap<String,Object>();
			map.put("matchingId",matchingId);
			map.put("attaFileType", attaFileType);
			// 获取附件文件列表
			List<Attachment> list = creditorSendManager.batchDownload(map);	 
			/*if(list.size() == 0){
				return jsonMapper.toJson(Constant.NG);
			}else{// 文件下载
*/				FileUtil.zipFileDownload(FileType.ZRSR.getName(),response,list);
			//}
		}
		
	}
	
	/**
	 * 预览协议
	 * 2015年12月26日
	 * By 胡体勇
	 */
	@RequestMapping(value="filePreview")
    public String preview(CreditorTradeEx creditorTradeEx,HttpServletRequest request,RedirectAttributes redirectAttributes,
			HttpServletResponse response){
		// 获取页面参数
		QueryFilter filter = new QueryFilter(request);
		String matchingId = (String) filter.getFilters().get("matchingId");
		String attaFileType = (String) filter.getFilters().get("attaFileType");
		if(matchingId != null){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("matchingId",matchingId);
			map.put("attaFileType", attaFileType);
			// 获取附件文件列表
			List<Attachment> list = creditorSendManager.batchDownload(map);	 
			if(list.size() == 0){
				// 从session中获取参数
				CERequestContext ceContext = CEContextHelper.getCEContext(request);
				MatchingCreditorView mv = matchingCreditorDao.getMatchingCreditorViewByMatchingId(matchingId);
				matchingCreditorManager.makeFileNoSendEmail(mv, ceContext,null,null);
				/*try {
					Thread.sleep(4*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				List<Attachment> list1 = creditorSendManager.batchDownload(map);	
				FileUtil.fileDownloadby(response, list1.get(0), "preview");*/
				addMessage(redirectAttributes, "文件出现异常,正在重新合成");
				return "redirect:" + adminPath + "/creditor/creditorSend/list";
			}else{
				// 文件下载
				FileUtil.fileDownloadby(response, list.get(0), "preview");
			}
		}
		return null;
	}
	
	/**
	 * 文件合成
	 * 2016年1月11日
	 * By 胡体勇
	 * @param creditorTradeEx
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="synthesisFile")
	@ResponseBody
	public String synthesisFile(CreditorTradeEx creditorTradeEx,HttpServletRequest request,
			HttpServletResponse response){
		// 从session中获取参数
		CERequestContext ceContext = CEContextHelper.getCEContext(request);
		// 获取页面参数
		QueryFilter filter = new QueryFilter(request);
		String matchingId = (String) filter.getFilters().get("matchingId");
		if(StringUtils.isBlank(matchingId)){
			return jsonMapper.toJson(Constant.NG);
		}
		// 文件状态设置成未合成
		MatchingCreditorEx mc = matchingCreditorDao.selectByPrimaryKey(matchingId);
		mc.setDictMatchingFileStatus(FilecpState.WHC.value);
		// 更新待推荐债权表
		matchingCreditorDao.updateByPrimaryKeySelective(mc);
		// 查出附件文件
		Attachment attachment = new Attachment();
		attachment.setAttaFileOwner("t_tz_matching_creditor");
		attachment.setAttaTableId(matchingId);
		
		MatchingCreditorView mv = matchingCreditorDao.getMatchingCreditorViewByMatchingId(matchingId);
		matchingCreditorManager.makeFileNoSendEmail(mv, ceContext,attachment,Constant.FILE_MAKE_FROM_HC);
		return jsonMapper.toJson(Constant.OK);
	}
	
	/**
	 *文件批量合成 
	 * 2016年1月11日
	 * By 胡体勇
	 * @param creditorTradeEx 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="batchSynthesisFile")
	@ResponseBody
	public String batchSynthesisFile(CreditorTradeEx creditorTradeEx,HttpServletRequest request,
			HttpServletResponse response){
		// 从session中获取参数
		// 获取页面参数
		QueryFilter filter = new QueryFilter(request);
		String ids = (String) filter.getFilters().get("ids");
		String[] matchingIds = ids.split(";");
		
		for(int i = 0;i<matchingIds.length;i++){
			String[] str = matchingIds[i].split(",");
			try{
				String matchingId = str[0];
				if(StringUtils.isBlank(matchingId)){
					logger.debug("批量合成文件待推荐id为空，不能合成文件");
					continue;
				}
				// 从session中获取参数
				CERequestContext ceContext = CEContextHelper.getCEContext(request);
				// 查出附件文件
				Attachment attachment = new Attachment();
				attachment.setAttaFileOwner("t_tz_matching_creditor");
				attachment.setAttaTableId(matchingId);
				attachment.setDictAttaFlag(FileConstants.FILE_TYPE_SR);
				List<Attachment> attachmentLst =  attachmentDao.findList(attachment);
				MatchingCreditorView mv = matchingCreditorDao.getMatchingCreditorViewByMatchingId(matchingId);
				// 判断是否生成过债权协议
				if(attachmentLst!=null && attachmentLst.size()>0){
					// 文件状态设置成未合成
					MatchingCreditorEx mc = matchingCreditorDao.selectByPrimaryKey(matchingId);
					mc.setDictMatchingFileStatus(FilecpState.WHC.value);
					// 更新待推荐债权表
					matchingCreditorDao.updateByPrimaryKeySelective(mc);
					// 制作文件
					matchingCreditorManager.makeFileNoSendEmail(mv, ceContext,attachment,Constant.FILE_MAKE_FROM_HC);
				}else{
					matchingCreditorManager.makeFileNoSendEmail(mv, ceContext,attachment,null);
				}
			}catch(Exception e){
				logger.error("批量合成文件:"+e.getMessage(),e);
    			FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, str[0],
						FortuneLogNode.CREDITOR_SEND.getCode(), FortuneLogLevel.YELLOW.value, 
						"业务编号【"+str[0]+"】批量合成债权文件出错："+e.getMessage()));
			}
		}
		return jsonMapper.toJson(Constant.OK);
	}
	
	/**
	 * 邮件发送
	 * 2016年4月7日
	 * By 胡体勇
	 * @param matchingId
	 * @param attId
	 */
	private String sendEmail(String matchingId ,String attId){
		// 获取待推荐债权表
		MatchingCreditorView mv = creditorAidManager.getMatchingCreditorViewByMatchingId(matchingId);
		// 获取
		Map<String,String> billMap = creditorSendManager.getCollectionMethod(mv.getLendCode());
		if(billMap!= null && BilltakenMode.XJ.value.equals(billMap.get("loan_bill_recv"))){
			return "业务编号【"+mv.getLendCode()+"】发送失败，失败信息为账单收取方式【信件】";
		}
		// 获取邮件模板
		EmailTemplate tenmplate = emailInfoManager.getEmailTemplateByType(mv.getMatchingFirstdayFlag());
		String content = tenmplate.getTemplateContent().replace("time", DateUtils.formatDate(mv.getApplyDeductDay(), "yyyy-MM-dd"));
		MailUtil.sendMail(mv.getCustomerEmail(), attId, tenmplate.getDescription(), content);
		// 删除email表中待发送状态的记录
		EmailInfo emailInfo = new EmailInfo();
		emailInfo.setCreditId(matchingId);
		emailInfo.setEmailTemplateId(MailTemplateType.FSQ.value + "," + MailTemplateType.SQ.value);
		emailInfo.setEmailSendStatus(EmailState.DFS.value);
		emailInfoManager.delete(emailInfo);
		// 设置文件合成成功
		// 获取待推荐债权表
		MatchingCreditorEx mc = matchingCreditorDao.selectByPrimaryKey(matchingId);
		mc.setDictMatchingFileStatus(FilecpState.HCCG.value);
		mc.setDictMatchingFilesendStatus(EmailState.FSCG.value);
		// 更新待推荐债权表
		matchingCreditorDao.updateByPrimaryKeySelective(mc);
		//如果当前时间大于出借到期日期，释放债权主要用于漏批释放债权
	    Date today = new Date();
	    if(mv.getApplyExpireDay() != null && mv.getApplyExpireDay().compareTo(today)<0 && !(mv.getProductCode().equals(ProductCode.YMY.value))){
	    	creditorSendManager.cancelMRCreditor(mc);
	    }
	    return null;	
	}
}
