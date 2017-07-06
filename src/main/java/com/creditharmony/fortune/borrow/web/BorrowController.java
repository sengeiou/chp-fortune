package com.creditharmony.fortune.borrow.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.adapter.bean.in.djrcreditor.DjrSendCreditorFortuneInBean;
import com.creditharmony.adapter.bean.in.djrcreditor.DjrSendCreditorFortuneSubInBean;
import com.creditharmony.adapter.bean.out.djrcreditor.DjrSendCreditorOutBean;
import com.creditharmony.adapter.core.client.ClientPoxy;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.CookieUtils;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.config.Global;
import com.creditharmony.core.excel.util.ImportExcel;
import com.creditharmony.core.fortune.type.FileType;
import com.creditharmony.core.fortune.type.Node;
import com.creditharmony.core.fortune.type.PushBorrowStatus;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.borrow.entity.BorrowPush;
import com.creditharmony.fortune.borrow.entity.ex.BorrowLookEx;
import com.creditharmony.fortune.borrow.facade.BorrowFacade;
import com.creditharmony.fortune.borrow.service.BorrowManager;
import com.creditharmony.fortune.borrow.service.BorrowMonthManager;
import com.creditharmony.fortune.borrow.utils.BorrowExportor;
import com.creditharmony.fortune.borrow.utils.ExportExcelUtils;
import com.creditharmony.fortune.borrow.utils.ListOrderByUtil;
import com.creditharmony.fortune.borrow.view.BorrowAllotView;
import com.creditharmony.fortune.borrow.view.BorrowView;
import com.creditharmony.fortune.constants.BorrowConstant;
import com.creditharmony.fortune.constants.ThreadPoolConstant;
import com.creditharmony.fortune.template.entity.BorrowImportExcel;
import com.creditharmony.fortune.utils.FormatUtils;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 可用债权
 * @Class Name BorrowController
 * @author 周俊
 * @Create In 2015年12月2日
 */
@Controller
@RequestMapping("${adminPath}/borrow")
public class BorrowController extends BaseController{

	@Autowired
	private BorrowManager borrowManager;
	@Autowired
	private BorrowMonthManager borrowMonthManager;
	@Autowired
	private BorrowFacade borrowFacade;
	
	private final ExecutorService executor = Executors.newFixedThreadPool(ThreadPoolConstant.FORTUNE_POOL_NUM);
	
	
	
	/**
	 * 向大金融推送债权
	 * 2016-12-15 16:20:19
	 * By 高旭
	 * @return
	 */
	 private Map<String, Object> pushBorrowDJRUpdateThread(JsonMapper jsonMapper,Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		  logger.info("pushBorrowDJRUpdate 向大金融推送债权");
		  Map<String, Object>  returnMap = new HashMap<String, Object>();
	  	  String returnMessage = "";
		  //推送成功条数
		  int successCount = 0;
		  //推送成功金额
		  BigDecimal successMoney = BigDecimal.ZERO;
		  //推送失败条数
		  int failCount = 0;
		  //推送失败金额
		  BigDecimal failMoney = BigDecimal.ZERO;
		  //按照borrowId 为条件查询数据库里的borrow
		  List<Borrow> borrowByCreditValueIdsList = borrowManager.findBorrowByDjrByMap(map);

		  if(!borrowByCreditValueIdsList.isEmpty()){
			logger.info("pushBorrowDJRUpdate  borrow本次查出多少条操作记录  borrowByCreditValueIdsList==="+borrowByCreditValueIdsList.size()+"条");
			List<List<Borrow>> splitListBorrows = ListOrderByUtil.split(borrowByCreditValueIdsList, 800);
			long starTime=System.currentTimeMillis();
			CompletionService<Map<String, Object>> completionService = new ExecutorCompletionService<Map<String, Object>>(executor);
			
			for(final List<Borrow> splitBorrow :splitListBorrows){
				completionService.submit(new Callable<Map<String, Object>>() {

					@Override
					public Map<String, Object> call() throws Exception {

					  	/**
						 * jsonMapper对象
						 */
						JsonMapper jsonMapper = JsonMapper.nonDefaultMapper();
						
						logger.info("pushBorrowDJRUpdate  splitBorrow分包之后的List==="+splitBorrow.size());
						//每次操作的金钱成功
						BigDecimal batchMoneySuccess  = BigDecimal.ZERO;
						//每次操作的条数成功
						int batchCountSuccess  = 0;
						//每次操作的金钱失败
						BigDecimal batchMoneyFail  = BigDecimal.ZERO;
						//每次操作的条数失败
						int batchCountFail  = 0;
						//每次操作的金钱失败
						BigDecimal batchMoney  = BigDecimal.ZERO;
						//每次操作的条数失败
						int batchCount  = 0;
						//发送大金融的List
						List<DjrSendCreditorFortuneSubInBean>  creditorFortunes =new ArrayList<DjrSendCreditorFortuneSubInBean>();
						DjrSendCreditorFortuneSubInBean sub= null;
						//组装推送记录List
						List<String> borrowPushs = new ArrayList<String>();
						//得到系统当前时间
						Date date = new Date();
						String systemDate  = DateUtils.formatDate(date,"yyyy-MM-dd");
						//批次号
						String batchNo = IdGen.uuid();
						for (Borrow borrow :splitBorrow) {
							sub = new DjrSendCreditorFortuneSubInBean();
							//组装大金融推送对象
							borrowManager.installDjrSend(borrow.getBorrowPushId(), sub, systemDate, borrow);
							//组装债权发送大金融数据成List
							creditorFortunes.add(sub);
							//组装推送记录
							borrowPushs.add(borrow.getBorrowPushId());
							
							batchMoney = batchMoney.add(new BigDecimal(com.creditharmony.fortune.utils.FormatUtils.getFormatNumber(borrow.getLoanCreditValue(), "0.00")));
							batchCount= batchCount+1;
						}
						logger.info("pushBorrowDJRUpdate  发送大金融数据creditorFortunes之前===="+creditorFortunes.size()+"条数"/*，list==="+jsonMapper.toJson(creditorFortunes)*/);
						logger.info("pushBorrowDJRUpdate  添加推送大金融记录表数据borrowPushs之前 只要是pushborrowid 用于更新批次号===="+borrowPushs.size()+"条数"/*，list==="+jsonMapper.toJson(borrowPushs)*/);
						try {
							boolean updateAndPushDjr = borrowManager.updateAndPushBorrowDjr(jsonMapper,creditorFortunes, date, batchNo, borrowPushs);
					    	if(updateAndPushDjr){
					    		batchMoneySuccess = batchMoney;
					    		batchCountSuccess = batchCount;
					    	}else{
					    		batchMoneyFail = batchMoney;
					    		batchCountFail = batchCount;
					    	}
						} catch (Exception e) {
						  	e.printStackTrace();
						    //更新推送大金融债权记录推送失败
					    	//int updateBorrowPushStatus = updateBorrowPushStatusOrIsUpdate(date, batchNo,PushBorrowStatus.FAIL.value,PushBorrowStatus.NEW.value,"","1");
						  	batchMoneyFail = batchMoney;
				    		batchCountFail = batchCount;
						}
						Map<String, Object> callReturnMap = new HashMap<String,Object>();
						callReturnMap.put("batchMoneySuccess", batchMoneySuccess);
						callReturnMap.put("batchCountSuccess", batchCountSuccess);
						callReturnMap.put("batchMoneyFail", batchMoneyFail);
						callReturnMap.put("batchCountFail", batchCountFail);
						return callReturnMap;
					}
					
				});
				
			} 	
			// 组装返回结果
			for(int i =0; i< splitListBorrows.size(); i++){
				try{
					Future<Map<String, Object>> future = completionService.take();
					Map<String, Object> callMap = future.get();
					successCount = successCount+(int)callMap.get("batchCountSuccess");
					successMoney = successMoney.add(new BigDecimal(callMap.get("batchMoneySuccess").toString()));
					failCount = failCount+(int)callMap.get("batchCountFail");
					failMoney = failMoney.add(new BigDecimal(callMap.get("batchMoneyFail").toString()));
				}catch(Exception e){
					logger.error("completionService.take()失败",e);
				}				
			}
			long endTime=System.currentTimeMillis();
			logger.info("pushBorrowDJRUpdate   本次推送调用大金融一共耗时==="+(endTime-starTime)/1000+"s");
		  }else{
			  logger.info("本次操作pushBorrowDJRUpdate  borrowByCreditValueIdsList为空");
			  returnMessage= "无可操作数据";
		  }
	  logger.info("此次一共推送成功："+successCount+"条记录"+successMoney+"元");
	  if(failCount<=0){
		  returnMessage = "本次推送到大金融成功"+successCount+"条，"+com.creditharmony.fortune.utils.FormatUtils.getFormatNumber(successMoney,"#,##0.00")+"元数据";
	  }else{
		  returnMessage = "本次推送到大金融成功"+successCount+"条，"+com.creditharmony.fortune.utils.FormatUtils.getFormatNumber(successMoney,"#,##0.00")+"元数据，失败"
				  +failCount+"条，"+com.creditharmony.fortune.utils.FormatUtils.getFormatNumber(failMoney,"#,##0.00")+"元数据.";
	  }
	  returnMap.put("returnMessage", returnMessage);
	  return returnMap;
	}
	
	
	
	
	/**
	 * 准备释放债权从债权记录表释放到可用债权
	 * 2016-12-13 11:25:11
	 * By 高旭
	 * @param model
	 * @param borrowView
	 * @param request
	 * @param response
	 * @return
	 */ 
	@RequestMapping("/checkReleaseBorrow")
	@ResponseBody
	public String checkReleaseBorrow(Model model,BorrowView borrowView,HttpServletRequest request,HttpServletResponse response){
		logger.info("准备释放债权从债权记录表释放到可用债权");
		//返回页面 message   和   可用推到大金融  条数 和 金钱
		Map<String,Object> messageMap = new HashMap<String,Object>();
		String message = "";
		try {
			//页面条件 从组
			Map<String, Object> map = borrowManager.objectToMapByPushBorrow(borrowView);
			//处理债权id成list
			List<String> creditValueIdList = new ArrayList<String>();
			//页面传入  borrowid  和   可用债权金额
			List<String> borrowInfoList = borrowView.getCreditValueIdList();
			if (ArrayHelper.isNotEmpty(borrowInfoList)) {
				for (String borrowInfo : borrowInfoList) {
					try {
						//borrowId
						creditValueIdList.add(borrowInfo.split(":")[0]);
					} catch (Exception e) {
						e.printStackTrace();
						creditValueIdList.add("");
						messageMap.put("code", "9999");
						message = "系统错误，联系管理员";
						messageMap.put("message", message);
						return jsonMapper.toJson(messageMap);
					}
				}
				//组装borrowId成List
				map.put("creditValueIdList", creditValueIdList);
				logger.info("checkReleaseBorrow   组装pushborrowId成List   pushborrowId==="+creditValueIdList.size()+"条数,");
			  }
			logger.info("checkReleaseBorrow   页面Map==="+ jsonMapper.toJson(map));
			Map<String, Object> getBorrowPushMoneyAndCount =  borrowManager.getBorrowPushMoneyAndCount(map);
			logger.info("checkReleaseBorrow getBorrowPushMoneyAndCount页面查询条件查询出来的结果数 和  金钱==="+jsonMapper.toJson(getBorrowPushMoneyAndCount));
			Map<String, Object> getBorrowPushMoneyAndCountNot =  borrowManager.getBorrowPushMoneyAndCountNot(map);
			logger.info("checkReleaseBorrow getBorrowPushMoneyAndCountNot页面查询条件查询出来的结果数 和  金钱  不可释放的==="+jsonMapper.toJson(getBorrowPushMoneyAndCountNot));
			Map<String, Object> getBorrowPushMoneyAndCountIs =  borrowManager.getBorrowPushMoneyAndCountIs(map);
			logger.info("checkReleaseBorrow getBorrowPushMoneyAndCountIs页面查询条件查询出来的结果数 和  金钱  可释放的==="+jsonMapper.toJson(getBorrowPushMoneyAndCountIs));
			
			int checkCount = getBorrowPushMoneyAndCount.get("checkcount")!=null?Integer.valueOf(getBorrowPushMoneyAndCount.get("checkcount").toString()):0;
			String checkMoney = getBorrowPushMoneyAndCount.get("checkmoney")!=null?getBorrowPushMoneyAndCount.get("checkmoney").toString():"0";
			String checkMoneyOneByOne = getBorrowPushMoneyAndCount.get("checkmoneyonebyone")!=null?getBorrowPushMoneyAndCount.get("checkmoneyonebyone").toString():"0";
			
			int checkCountNot = getBorrowPushMoneyAndCountNot.get("checkcountnot")!=null?Integer.valueOf(getBorrowPushMoneyAndCountNot.get("checkcountnot").toString()):0;
			String checkMoneyNot = getBorrowPushMoneyAndCountNot.get("checkmoneynot")!=null?getBorrowPushMoneyAndCountNot.get("checkmoneynot").toString():"0";
			String checkMoneyOneByOneNot = getBorrowPushMoneyAndCountNot.get("checkmoneyonebyonenot")!=null?getBorrowPushMoneyAndCountNot.get("checkmoneyonebyonenot").toString():"0";
			
			int checkCountIs = getBorrowPushMoneyAndCountIs.get("checkcountis")!=null?Integer.valueOf(getBorrowPushMoneyAndCountIs.get("checkcountis").toString()):0;
			String checkMoneyIs = getBorrowPushMoneyAndCountIs.get("checkmoneyis")!=null?getBorrowPushMoneyAndCountIs.get("checkmoneyis").toString():"0";
			String checkMoneyOneByOneIs = getBorrowPushMoneyAndCountIs.get("checkmoneyonebyoneis")!=null?getBorrowPushMoneyAndCountIs.get("checkmoneyonebyoneis").toString():"0";
			
			if((checkCount+"").equals(borrowView.getPushHiddenCount().replaceAll(",", ""))
					&&checkMoney.equals(borrowView.getPushHiddenMoney().replaceAll(",", ""))
					//&&checkMoneyOneByOne.equals(borrowView.getPushHiddenMoneyOneByOne().replaceAll(",", ""))
					){
				if(checkCountNot>0){
					message = "请确认："+FormatUtils.getFormatNumber(new BigDecimal(checkMoneyOneByOneNot), "#,##0.00")+"元"+checkCountNot+"笔债权不可释放，"+FormatUtils.getFormatNumber(new BigDecimal(checkMoneyOneByOneIs), "#,##0.00")+"元"+checkCountIs+"笔债权将全部释放，请确认？”";
					messageMap.put("code", "0000");
					messageMap.put("message", message);
				}else{
					message = "请确认："+FormatUtils.getFormatNumber(new BigDecimal(checkMoneyOneByOneIs), "#,##0.00")+"元"+checkCountIs+"笔债权可以全部释放，请确认?";
					messageMap.put("code", "0000");
					messageMap.put("message", message);
				}
			}else{
				message = "总金额"+FormatUtils.getFormatNumber(new BigDecimal(checkMoneyOneByOneNot), "#,##0.00")+"元，总笔数"+checkMoneyNot+"笔债权不可释放，请重试!";
				messageMap.put("code", "9999");
				messageMap.put("message", message);
			}

		return jsonMapper.toJson(messageMap);
		} catch (Exception e1) {
			e1.printStackTrace();
			messageMap.put("code", "9999");
			message = "系统错误，联系管理员";
			messageMap.put("message", message);
			return jsonMapper.toJson(messageMap);
		}
	}
	
	/**
	 * 
	 * 真正释放债权到到可用债权列表
	 * By 高旭
	 * @param model
	 * @param borrowView
	 * @param request
	 * @param response
	 * @return
	 */ 
	@RequestMapping("/updatePushBorrowadd")
	@ResponseBody
	public String updatePushBorrowadd(Model model,BorrowView borrowView,HttpServletRequest request,HttpServletResponse response){
		logger.info("updatePushBorrowadd 真正释放债权到到可用债权列表");
		//返回页面 message   和   可用推到大金融  条数 和 金钱
		Map<String,Object> messageMap = new HashMap<String,Object>();
		String message = "";
		try {
			//页面条件 从组
			Map<String, Object> map = borrowManager.objectToMapByPushBorrow(borrowView);
			//处理债权id成list
			List<String> creditValueIdList = new ArrayList<String>();
			//页面传入  borrowid  和   可用债权金额
			List<String> borrowInfoList = borrowView.getCreditValueIdList();
			if (ArrayHelper.isNotEmpty(borrowInfoList)) {
				for (String borrowInfo : borrowInfoList) {
					try {
						//borrowId
						creditValueIdList.add(borrowInfo.split(":")[0]);
					} catch (Exception e) {
						e.printStackTrace();
						creditValueIdList.add("");
						message = "系统错误";
						return jsonMapper.toJson(message);
					}
				}
				//组装borrowId成List
				map.put("creditValueIdList", creditValueIdList);
				logger.info("updatePushBorrowadd   组装PsuhborrowId成List   creditValueIdList==="+creditValueIdList.size()+"条数,");
			  }
			  logger.info("updatePushBorrowadd   页面Map==="+ jsonMapper.toJson(map));
			  map.put("date", new Date());
	 		  Map<String, Object> updateBorrowAddAndIsUpdate = borrowManager.updateBorrowAddAndIsUpdate(map);
	 		  logger.info("updatePushBorrowadd   释放成功返回updateBorrowAddAndIsUpdate==="+ jsonMapper.toJson(updateBorrowAddAndIsUpdate));
			  messageMap.put("code", "0000");
			  message =FormatUtils.getFormatNumber(new BigDecimal((updateBorrowAddAndIsUpdate.get("loan_credit_value")!=null?updateBorrowAddAndIsUpdate.get("loan_credit_value").toString():"0")), "#,##0.00")+"元"+(updateBorrowAddAndIsUpdate.get("count")!=null?updateBorrowAddAndIsUpdate.get("count").toString():0)+"笔债权释放成功！";
			  messageMap.put("message", message);
			  return jsonMapper.toJson(messageMap);
		} catch (Exception e1) {
			e1.printStackTrace();
			messageMap.put("code", "9999");
			message = "系统错误，联系管理员";
			messageMap.put("message", message);
			return jsonMapper.toJson(messageMap);
		}
	}

	
	
	
	
	 /**
	 * 向大金融推送债权
	 * 2016-12-15 16:20:19
	 * By 高旭
	 * @return
	 */
	 private Map<String, Object> pushBorrowDJRUpdate(JsonMapper jsonMapper,Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		  logger.info("pushBorrowDJRUpdate 向大金融推送债权");
		  Map<String, Object>  returnMap = new HashMap<String, Object>();
	  	  String returnMessage = "";
		  //推送成功条数
		  int successCount = 0;
		  //推送成功金额
		  BigDecimal successMoney = BigDecimal.ZERO;
		  //推送失败条数
		  int failCount = 0;
		  //推送失败金额
		  BigDecimal failMoney = BigDecimal.ZERO;
		  //按照borrowId 为条件查询数据库里的borrow
		  List<Borrow> borrowByCreditValueIdsList = borrowManager.findBorrowByDjrByMap(map);
		  //循环组装大金融参数  和   组装   记录表参数
		  List<DjrSendCreditorFortuneSubInBean> creditorFortunes=null;
		  List<String> borrowPushs = null;
		  if(!borrowByCreditValueIdsList.isEmpty()){
			logger.info("pushBorrowDJRUpdate  borrow本次查出多少条操作记录  borrowByCreditValueIdsList==="+borrowByCreditValueIdsList.size()+"条");
			List<List<Borrow>> splitListBorrows = ListOrderByUtil.split(borrowByCreditValueIdsList, 800);
			long starTime=System.currentTimeMillis();
			
			for(List<Borrow> splitBorrow :splitListBorrows){
				logger.info("pushBorrowDJRUpdate  splitBorrow分包之后的List==="+jsonMapper.toJson(splitBorrow.size()));
				//每次操作的金钱
				BigDecimal batchMoney  = BigDecimal.ZERO;
				//每次操作的条数
				int batchCount  = 0;
				//发送大金融的List
				creditorFortunes =new ArrayList<DjrSendCreditorFortuneSubInBean>();
				DjrSendCreditorFortuneSubInBean sub= null;
				//组装推送记录List
				borrowPushs = new ArrayList<String>();
				//得到系统当前时间
				Date date = new Date();
				String systemDate  = DateUtils.formatDate(date,"yyyy-MM-dd");
				//批次号
				String batchNo = IdGen.uuid();
				for (Borrow borrow :splitBorrow) {
					sub = new DjrSendCreditorFortuneSubInBean();
					//组装大金融推送对象
					borrowManager.installDjrSend(borrow.getBorrowPushId(), sub, systemDate, borrow);
					//组装债权发送大金融数据成List
					creditorFortunes.add(sub);
					//组装推送记录
					borrowPushs.add(borrow.getBorrowPushId());
					
					batchMoney = batchMoney.add(new BigDecimal(com.creditharmony.fortune.utils.FormatUtils.getFormatNumber(borrow.getLoanCreditValue(), "0.00")));
					batchCount= batchCount+1;
				}
				logger.info("pushBorrowDJRUpdate  发送大金融数据creditorFortunes之前===="+creditorFortunes.size()+"条数"/*，list==="+jsonMapper.toJson(creditorFortunes)*/);
				logger.info("pushBorrowDJRUpdate  添加推送大金融记录表数据borrowPushs之前 只要是pushborrowid 用于更新批次号===="+borrowPushs.size()+"条数"/*，list==="+jsonMapper.toJson(borrowPushs)*/);
				try {
					boolean updateAndPushDjr = borrowManager.updateAndPushBorrowDjr(jsonMapper,creditorFortunes, date, batchNo, borrowPushs);
			    	if(updateAndPushDjr){
			    		 successCount =successCount+batchCount;
					     successMoney = successMoney.add(batchMoney);
			    	}else{
			    		failCount =failCount+batchCount;
				    	failMoney = failMoney.add(batchMoney);
			    	}
				} catch (Exception e) {
				  	e.printStackTrace();
				    //更新推送大金融债权记录推送失败
			    	//int updateBorrowPushStatus = updateBorrowPushStatusOrIsUpdate(date, batchNo,PushBorrowStatus.FAIL.value,PushBorrowStatus.NEW.value,"","1");
				  	failCount =failCount+batchCount;
			    	failMoney = failMoney.add(batchMoney);
				}
				batchMoney  = BigDecimal.ZERO;
				batchCount = 0;
			} 
			long endTime=System.currentTimeMillis();
			logger.info("pushBorrowDJRUpdate   本次推送调用大金融一共耗时==="+(endTime-starTime)/1000+"s");
		  }else{
			  logger.info("本次操作pushBorrowDJRUpdate  borrowByCreditValueIdsList为空");
			  returnMessage= "无可操作数据";
		  }
	  logger.info("此次一共推送成功："+successCount+"条记录"+successMoney+"元");
	  if(failCount<=0){
		  returnMessage = "本次推送到大金融成功"+successCount+"条，"+com.creditharmony.fortune.utils.FormatUtils.getFormatNumber(successMoney,"#,##0.00")+"元数据";
	  }else{
		  returnMessage = "本次推送到大金融成功"+successCount+"条，"+com.creditharmony.fortune.utils.FormatUtils.getFormatNumber(successMoney,"#,##0.00")+"元数据，失败"
				  +failCount+"条，"+com.creditharmony.fortune.utils.FormatUtils.getFormatNumber(failMoney,"#,##0.00")+"元数据.";
	  }
	  returnMap.put("returnMessage", returnMessage);
	  return returnMap;
	}
		
	
	
	/**
	 * 真正推送大金融
	 * 2016-12-13 11:25:11
	 * By 高旭
	 * @param model
	 * @param borrowView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/pushBorrowListDJR")
	@ResponseBody
	public String pushBorrowListDJR(Model model,BorrowView borrowView,HttpServletRequest request,HttpServletResponse response){
		logger.info("真正推送大金融");
		//返回页面 message   和   可用推到大金融  条数 和 金钱
		Map<String,Object> messageMap = new HashMap<String,Object>();
		//页面条件 从组
		String message = "";
		try {
			Map<String, Object> map = borrowManager.objectToMapByPushBorrow(borrowView);
			//处理债权id成list
			List<String> creditValueIdList = new ArrayList<String>();
			//页面传入  borrowid  和   可用债权金额
			List<String> borrowInfoList = borrowView.getCreditValueIdList();
			if (ArrayHelper.isNotEmpty(borrowInfoList)) {
				for (String borrowInfo : borrowInfoList) {
					try {
						//borrowId
						creditValueIdList.add(borrowInfo.split(":")[0]);
					} catch (Exception e) {
						e.printStackTrace();
						creditValueIdList.add("");
						messageMap.put("code", "9999");
						message = "系统错误，联系管理员";
						messageMap.put("message", message);
						return jsonMapper.toJson(messageMap);
					}
				}
				//组装borrowId成List
				map.put("creditValueIdList", creditValueIdList);
				logger.info("pushBorrowListDJR   组装pusborrowId成List   creditValueId==="+creditValueIdList.size()+"条数");
			  }
			logger.info("pushBorrowListDJR:页面参数Map==="+jsonMapper.toJson(map));
			Map<String, Object>  pushBorrow = pushBorrowDJRUpdateThread(jsonMapper,map,request,response);
			logger.info("pushBorrowListDJR:推送成功返回pushBorrowMap==="+jsonMapper.toJson(pushBorrow));
			if(!pushBorrow.isEmpty()&&pushBorrow.get("returnMessage")!=null){
				messageMap.put("code", "0000");
				message = pushBorrow.get("returnMessage").toString();
				messageMap.put("message", message);
				return jsonMapper.toJson(messageMap);
			}else{
				messageMap.put("code", "9999");
				message = "推送失败,请重试";
				messageMap.put("message", message);
				return jsonMapper.toJson(messageMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			messageMap.put("code", "9999");
			message = "系统错误，联系管理员";
			messageMap.put("message", message);
			return jsonMapper.toJson(messageMap);
		}
		
	}
	
	/**
	 * 检查推送大金融记录表真被推送大金融数据
	 * 2016-12-13 11:25:11
	 * By 高旭
	 * @param model
	 * @param borrowView
	 * @param request
	 * @param response
	 * @return
	 */ 
	@RequestMapping("/checkPushBorrowList")
	@ResponseBody
	public String checkPushBorrowList(Model model,BorrowView borrowView,HttpServletRequest request,HttpServletResponse response){
		logger.info("checkPushBorrowList 检查推送大金融记录表真被推送大金融数据");
		//返回页面 message   和   可用推到大金融  条数 和 金钱
		Map<String,Object> messageMap = new HashMap<String,Object>();
		String message = "";
		try {
			//页面条件 从组
			Map<String, Object> map = borrowManager.objectToMapByPushBorrow(borrowView);
			//处理债权id成list
			List<String> creditValueIdList = new ArrayList<String>();
			//页面传入  borrowid  和   可用债权金额
			List<String> borrowInfoList = borrowView.getCreditValueIdList();
			if (ArrayHelper.isNotEmpty(borrowInfoList)) {
				for (String borrowInfo : borrowInfoList) {
					try {
						//borrowId
						creditValueIdList.add(borrowInfo.split(":")[0]);
					} catch (Exception e) {
						e.printStackTrace();
						creditValueIdList.add("");
						messageMap.put("code", "9999");
						message = "系统错误，联系管理员";
						messageMap.put("message", message);
						return jsonMapper.toJson(messageMap);
					}
				}
				//组装borrowId成List
				map.put("creditValueIdList", creditValueIdList);
				logger.info("checkPushBorrowList   组装pushborrowId成List   creditValueIdList==="+creditValueIdList.size()+"条数,");
			  }
			
			logger.info("checkPushBorrowList 页面查询条件map==="+jsonMapper.toJson(map));
			Map<String, Object> getBorrowPushMoneyAndCount =  borrowManager.getBorrowPushMoneyAndCount(map);
			logger.info("checkPushBorrowList getBorrowPushMoneyAndCount页面查询条件查询出来的结果数 和  金钱==="+jsonMapper.toJson(getBorrowPushMoneyAndCount));
			Map<String, Object> getBorrowPushMoneyAndCountNot =  borrowManager.getBorrowPushMoneyAndCountNot(map);
			logger.info("checkPushBorrowList getBorrowPushMoneyAndCount页面查询条件查询出来的结果数 和  金钱 不可推送大金融==="+jsonMapper.toJson(getBorrowPushMoneyAndCountNot));
			Map<String, Object> getBorrowPushMoneyAndCountIs =  borrowManager.getBorrowPushMoneyAndCountIs(map);
			logger.info("checkPushBorrowList getBorrowPushMoneyAndCount页面查询条件查询出来的结果数 和  金钱 可推送大金融==="+jsonMapper.toJson(getBorrowPushMoneyAndCountIs));
			int checkCount = getBorrowPushMoneyAndCount.get("checkcount")!=null?Integer.valueOf(getBorrowPushMoneyAndCount.get("checkcount").toString()):0;
			String checkMoney = getBorrowPushMoneyAndCount.get("checkmoney")!=null?getBorrowPushMoneyAndCount.get("checkmoney").toString():"0";
			String checkMoneyOneByOne = getBorrowPushMoneyAndCount.get("checkmoneyonebyone")!=null?getBorrowPushMoneyAndCount.get("checkmoneyonebyone").toString():"0";
			
			int checkCountNot = getBorrowPushMoneyAndCountNot.get("checkcountnot")!=null?Integer.valueOf(getBorrowPushMoneyAndCountNot.get("checkcountnot").toString()):0;
			String checkMoneyNot = getBorrowPushMoneyAndCountNot.get("checkmoneynot")!=null?getBorrowPushMoneyAndCountNot.get("checkmoneynot").toString():"0";
			String checkMoneyOneByOneNot = getBorrowPushMoneyAndCountNot.get("checkmoneyonebyonenot")!=null?getBorrowPushMoneyAndCountNot.get("checkmoneyonebyonenot").toString():"0";
			
			int checkCountIs = getBorrowPushMoneyAndCountIs.get("checkcountis")!=null?Integer.valueOf(getBorrowPushMoneyAndCountIs.get("checkcountis").toString()):0;
			String checkMoneyIs = getBorrowPushMoneyAndCountIs.get("checkmoneyis")!=null?getBorrowPushMoneyAndCountIs.get("checkmoneyis").toString():"0";
			String checkMoneyOneByOneIs = getBorrowPushMoneyAndCountIs.get("checkmoneyonebyoneis")!=null?getBorrowPushMoneyAndCountIs.get("checkmoneyonebyoneis").toString():"0";
			
			if((checkCount+"").equals(borrowView.getPushHiddenCount().replaceAll(",", ""))
					&&checkMoney.equals(borrowView.getPushHiddenMoney().replaceAll(",", ""))
					//&&checkMoneyOneByOne.equals(borrowView.getPushHiddenMoneyOneByOne().replaceAll(",", ""))
					){			
				if(checkCountIs<=0){
					message = "总金额"+FormatUtils.getFormatNumber(new BigDecimal(checkMoneyOneByOneNot), "#,##0.00")+"元，总笔数"+checkCountNot+"笔债权不可推送，请重试!";
					messageMap.put("code", "9999");
					messageMap.put("message", message);
				}else if(checkCountNot>0){
					message = "请确认："+FormatUtils.getFormatNumber(new BigDecimal(checkMoneyOneByOneNot), "#,##0.00")+"元"+checkCountNot+"笔债权不可推送，"+FormatUtils.getFormatNumber(new BigDecimal(checkMoneyOneByOneIs), "#,##0.00")+"元"+checkCountIs+"笔债权将全部推送，请确认？”";
					messageMap.put("code", "0000");
					messageMap.put("message", message);	
				}else{
					message = "请确认："+FormatUtils.getFormatNumber(new BigDecimal(checkMoneyOneByOneIs), "#,##0.00")+"元"+checkCountIs+"笔债权可以全部推送，请确认?";
					messageMap.put("code", "0000");
					messageMap.put("message", message);
				}	
			}else{
				message = "总金额"+FormatUtils.getFormatNumber(new BigDecimal(checkMoneyOneByOneNot), "#,##0.00")+"元，总笔数"+checkCountNot+"笔债权不可推送，请重试!";
				messageMap.put("code", "9999");
				messageMap.put("message", message);
			}
		return jsonMapper.toJson(messageMap);
		} catch (Exception e1) {
			e1.printStackTrace();
			messageMap.put("code", "9999");
			message = "系统错误，联系管理员";
			messageMap.put("message", message);
			return jsonMapper.toJson(messageMap);
		}
	}
	
	
	/**
	 * 插入到推送大金融记录表
	 * 2016-12-13 11:25:11
	 * By 高旭
	 * @param model
	 * @param borrowView
	 * @param request
	 * @param response
	 * @return
	 */ 
	@RequestMapping("/insertCheckPushBorrow")
	@ResponseBody
	public String insertCheckPushBorrow(Model model,BorrowView borrowView,HttpServletRequest request,HttpServletResponse response){
		logger.info("insertCheckPushBorrow 插入到推送大金融记录表");
		//返回页面 message   和   可用推到大金融  条数 和 金钱
		Map<String,Object> messageMap = new HashMap<String,Object>();
		String message = "";
		try {
			//页面条件 从组
			Map<String, Object> map = borrowManager.objectToMapByPushBorrow(borrowView);
			//处理债权id成list
			List<String> creditValueIdList = new ArrayList<String>();
			//页面传入  borrowid  和   可用债权金额
			List<String> borrowInfoList = borrowView.getCreditValueIdList();
			if (ArrayHelper.isNotEmpty(borrowInfoList)) {
				for (String borrowInfo : borrowInfoList) {
					try {
						//borrowId
						creditValueIdList.add(borrowInfo.split(":")[0]);
					} catch (Exception e) {
						e.printStackTrace();
						creditValueIdList.add("");
						message = "系统错误";
						return jsonMapper.toJson(message);
					}
				}
				//组装borrowId成List
				map.put("creditValueIdList", creditValueIdList);
				logger.info("insertCheckPushBorrow   组装borrowId成List   creditValueIdList==="+creditValueIdList.size()+"条数,");
			  }
			logger.info("insertCheckPushBorrow 插入到推送大金融记录表    页面查询条件map==="+jsonMapper.toJson(map));
			List<Borrow> borrowByCreditValueIdsList = borrowManager.findBorrowByDjr(map);
			  if(!borrowByCreditValueIdsList.isEmpty()){
				  logger.info("insertCheckPushBorrow 插入到推送大金融记录表 borrowByCreditValueIdsList==="+borrowByCreditValueIdsList.size()+"条数");
				  //本次操作金额
				  BigDecimal batchMoney  = BigDecimal.ZERO;
				  // 本次操作条数
				  int batchCount  = 0;
				  //得到系统当前时间
				  Date date = new Date();
				  String systemDate  = DateUtils.formatDate(date,"yyyy-MM-dd");	
				  String batchNo = IdGen.uuid();
				  map.put("date", date);
				  map.put("batchNo", batchNo);
				  map.put("pushPlatForm", "DJR");
				  map.put("status", PushBorrowStatus.NEW.value);
				  map.put("createBy", UserUtils.getUserId());
				  map.put("IsUpdate", "0");
				  //勾选还是批量  1勾选  0批量
		 		  if(map.get("creditValueIdList")!=null){
		 			 map.put("oneOrBeath", "1");
		 		  }else{
		 			 map.put("oneOrBeath", "0");
		 		  }
		 		  logger.info("insertCheckPushBorrow 插入到推送大金融记录表 添加参数Map==="+jsonMapper.toJson(map));
		 		  borrowManager.insertAndUpdate(map, date, batchNo);
				  messageMap.put("code", "0000");
				  message = "操作成功";
				  messageMap.put("message", message);
				  }else{
					  logger.info("本次操作borrowList  borrowByCreditValueIdsList为空");
					  messageMap.put("code", "9999");
					  message = "无可操作数据，请重新筛选";
					  messageMap.put("message", message);
				  }
		return jsonMapper.toJson(messageMap);
		} catch (Exception e1) {
			e1.printStackTrace();
			messageMap.put("code", "9999");
			message = "系统错误，联系管理员";
			messageMap.put("message", message);
			return jsonMapper.toJson(messageMap);
		}
	}



	
	
	

	/**
	 * 数据校验推送大金融记录表准备
	 * 2016-12-13 11:25:11
	 * By 高旭
	 * @param model
	 * @param borrowView
	 * @param request
	 * @param response
	 * @return
	 */ 
	@RequestMapping("/prepareCheckPushBorrow")
	@ResponseBody
	public String prepareCheckPushBorrow(Model model,BorrowView borrowView,HttpServletRequest request,HttpServletResponse response){
		logger.info("prepareCheckPushBorrow 数据校验推送大金融记录表准备");
		//返回页面 message   和   可用推到大金融  条数 和 金钱
		Map<String,Object> messageMap = new HashMap<String,Object>();
		String message = "";
		try {
			//页面条件 从组
			Map<String, Object> map = borrowManager.objectToMapByPushBorrow(borrowView);
			//处理债权id成list
			List<String> creditValueIdList = new ArrayList<String>();
			//页面传入  borrowid  和   可用债权金额
			List<String> borrowInfoList = borrowView.getCreditValueIdList();
			if (ArrayHelper.isNotEmpty(borrowInfoList)) {
				for (String borrowInfo : borrowInfoList) {
					try {
						//borrowId
						creditValueIdList.add(borrowInfo.split(":")[0]);
					} catch (Exception e) {
						e.printStackTrace();
						creditValueIdList.add("");
						message = "系统错误";
						return jsonMapper.toJson(message);
					}
				}
				//组装borrowId成List
				map.put("creditValueIdList", creditValueIdList);
				logger.info("prepareCheckPushBorrow   组装borrowId成List   creditValueIdList==="+creditValueIdList.size()+"条数,");
			  }
			logger.info("prepareCheckPushBorrow   页面参数Map"+jsonMapper.toJson(map));
			
			Map<String, Object> borrowMapMoneyAndCount =  borrowManager.getBorrowMoneyAndCount(map);
			logger.info("prepareCheckPushBorrow 页面参数Map查询  borrowMapMoneyAndCount"+jsonMapper.toJson(borrowMapMoneyAndCount));
			String checkCount = borrowMapMoneyAndCount.get("checkcount")!=null?borrowMapMoneyAndCount.get("checkcount").toString():"0";
			String checkMoney = borrowMapMoneyAndCount.get("checkmoney")!=null?borrowMapMoneyAndCount.get("checkmoney").toString():"0";
			String checkMoneyOneByOne = borrowMapMoneyAndCount.get("checkmoneyonebyone")!=null?borrowMapMoneyAndCount.get("checkmoneyonebyone").toString():"0";
			if(checkMoneyOneByOne.equals(borrowView.getPushHiddenMoneyOneByOne().replaceAll(",", "")) &&
				checkMoney.equals(borrowView.getPushHiddenMoney().replaceAll(",", ""))
				&&checkCount.equals(borrowView.getPushHiddenCount().replaceAll(",", ""))
					){
				
				Map<String, Object> borrowMapMoneyAndCountIsNotNull =  borrowManager.getBorrowMoneyAndCountContractNoIsNotNull(map);
				logger.info("prepareCheckPushBorrow 页面参数Map查询  borrowMapMoneyAndCountIsNotNull 可插入债权推送记录表数据"+jsonMapper.toJson(borrowMapMoneyAndCountIsNotNull));
				String checkCountIsNotNull = borrowMapMoneyAndCountIsNotNull.get("checkcountisnotnull")!=null?borrowMapMoneyAndCountIsNotNull.get("checkcountisnotnull").toString():"0";
				String checkMoneyIsNotNull = borrowMapMoneyAndCountIsNotNull.get("checkmoneyisnotnull")!=null?borrowMapMoneyAndCountIsNotNull.get("checkmoneyisnotnull").toString():"0";
				String checkMoneyOneByOneIsNotNull = borrowMapMoneyAndCountIsNotNull.get("checkmoneyonebyoneisnotnull")!=null?borrowMapMoneyAndCountIsNotNull.get("checkmoneyonebyoneisnotnull").toString():"0";
				
				String checkCountIsNull = String.valueOf(Integer.valueOf(checkCount)-Integer.valueOf(checkCountIsNotNull));
				String checkMoneyOneByOneIsNull = new BigDecimal(checkMoneyOneByOne).subtract(new BigDecimal(checkMoneyOneByOneIsNotNull)).toString();
				logger.info("prepareCheckPushBorrow 页面参数Map查询  borrowMapMoneyAndCountIsNull 不可插入债权推送记录表数据 总金额："+checkMoneyOneByOneIsNull+"元，总条数："+checkCountIsNull+"条数");
				
				if(Integer.valueOf(checkCountIsNotNull)<=0){
					//Map<String, Object> borrowMapMoneyAndCountIsNull =  borrowManager.getBorrowMoneyAndCountContractNoIsNull(map);
					
					//String checkCountIsNull = borrowMapMoneyAndCountIsNull.get("checkcountisnull")!=null?borrowMapMoneyAndCountIsNull.get("checkcountisnull").toString():"0";
					//String checkMoneyOneByOneIsNull = borrowMapMoneyAndCountIsNull.get("checkmoneyonebyoneisnull")!=null?borrowMapMoneyAndCountIsNull.get("checkmoneyonebyoneisnull").toString():"0";
					message = "总金额"+FormatUtils.getFormatNumber(new BigDecimal(checkMoneyOneByOneIsNull), "#,##0.00")+"元，总笔数"+checkCountIsNull+"笔债权不可推送!";
					messageMap.put("code", "9999");
				}else if(checkMoneyOneByOneIsNotNull.equals(borrowView.getPushHiddenMoneyOneByOne().replaceAll(",", "")) &&
						checkMoneyIsNotNull.equals(borrowView.getPushHiddenMoney().replaceAll(",", ""))
						&&checkCountIsNotNull.equals(borrowView.getPushHiddenCount().replaceAll(",", ""))
						){
					message = "请确认："+FormatUtils.getFormatNumber(new BigDecimal(checkMoneyOneByOne), "#,##0.00")+"元"+checkCount+"笔债权可以全部推送，请确认?";
					messageMap.put("code", "0000");
				}else{
					//Map<String, Object> borrowMapMoneyAndCountIsNull =  borrowManager.getBorrowMoneyAndCountContractNoIsNull(map);
					//logger.info("prepareCheckPushBorrow 页面参数Map查询  borrowMapMoneyAndCountIsNull 不可插入债权推送记录表数据"+jsonMapper.toJson(borrowMapMoneyAndCountIsNull));
					//String checkCountIsNull = borrowMapMoneyAndCountIsNull.get("checkcountisnull")!=null?borrowMapMoneyAndCountIsNull.get("checkcountisnull").toString():"0";
					//String checkMoneyOneByOneIsNull = borrowMapMoneyAndCountIsNull.get("checkmoneyonebyoneisnull")!=null?borrowMapMoneyAndCountIsNull.get("checkmoneyonebyoneisnull").toString():"0";
					message = "请确认："+FormatUtils.getFormatNumber(new BigDecimal(checkMoneyOneByOneIsNull), "#,##0.00")+"元"+checkCountIsNull+"笔债权借款人合同编号为空，"+FormatUtils.getFormatNumber(new BigDecimal(checkMoneyOneByOneIsNotNull), "#,##0.00")+"元"+checkCountIsNotNull+"笔债权将全部推送，请确认？";
					//message = "请确认："+FormatUtils.getFormatNumber(new BigDecimal(checkMoneyOneByOneIsNull), "#,##0.00")+"元"+checkCountIsNull+"笔债权借款人合同编号为空或者金额为零，"+FormatUtils.getFormatNumber(new BigDecimal(checkMoneyOneByOneIsNotNull), "#,##0.00")+"元"+checkCountIsNotNull+"笔债权将全部推送，请确认？";
					messageMap.put("code", "0000");
				}	
			}else{
				message = "总金额"+FormatUtils.getFormatNumber(new BigDecimal(checkMoneyOneByOne), "#,##0.00")+"元，总笔数"+checkCount+"笔债权发生变化，请重新刷新页面!";
			}
		messageMap.put("message", message);
		return jsonMapper.toJson(messageMap);
		} catch (Exception e1) {
			e1.printStackTrace();
			messageMap.put("code", "9999");
			message = "系统错误，联系管理员";
			messageMap.put("message", message);
			return jsonMapper.toJson(messageMap);
		}
	}
	

	
	/**
	 * 推送债权列表
	 * 2016-12-13 11:25:11
	 * By 高旭
	 * @param model
	 * @param borrowView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/pushBorrowList")
	public String pushBorrowList(Model model,BorrowView borrowView,HttpServletRequest request,HttpServletResponse response){
		Page<Borrow> page = borrowManager.findPushBorrow(new Page<Borrow>(request, response),borrowView);
		// 查询总金额数
		BigDecimal allMoney = borrowManager.findAllMoneyByPushBorrow(borrowView);
		model.addAttribute("allMoney",allMoney);
		model.addAttribute("page", page);
		model.addAttribute("borrowView",borrowView);
		String[] types ={"tz_credit_src","tz_zjtr_mark","jk_prof_type","tz_repay_day","tz_loan_distinguish","tz_push_borrow_djr"};
		FortuneDictUtil.addDicts(model, types);
		request.getSession().setAttribute("borrowView",borrowView);
		return "borrow/pushBorrowList";
	}
	
	/**
	 * 可用债权列表
	 * 2015年12月2日
	 * By 周俊
	 * @param model
	 * @param borrowView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model,BorrowView borrowView,HttpServletRequest request,HttpServletResponse response){
		Page<Borrow> page = borrowManager.findBorrow(new Page<Borrow>(request, response),borrowView);
		// 查询总金额数 先累加 最后四舍五如
		BigDecimal allMoney = borrowManager.findAllMoney(borrowView);
		//先每条四舍五入在累加
		BigDecimal allMoneyOneByOne = borrowManager.findAllMoneyOneByOne(borrowView);
		model.addAttribute("allMoneyOneByOne",allMoneyOneByOne);
		model.addAttribute("allMoney",allMoney);
		model.addAttribute("page", page);
		model.addAttribute("borrowView",borrowView);
		String[] types ={"tz_credit_src","tz_zjtr_mark","jk_prof_type","tz_repay_day","tz_loan_distinguish"};
		FortuneDictUtil.addDicts(model, types);
		request.getSession().setAttribute("borrowView",borrowView);
		return "borrow/borrowList";
	}
	
	/**
	 * 跳转到债权上传页面
	 * 2016年4月18日
	 * By 周俊
	 * @return
	 */
	@RequestMapping("/goBorrowUpload")
	public String goBorrowUpload(){
		return "/borrow/borrowUpload";
	}
	
	/**
	 * 保存上传的债权
	 * 2016年4月18日
	 * By 周俊
	 * @param file
	 * @param model
	 * @return
	 */
	@RequestMapping("/borrowUpload")
	@ResponseBody
	public String borrowUpload(MultipartFile file,Model model){
		List<BorrowImportExcel> dataList = new ArrayList<BorrowImportExcel>();
		try {
			ImportExcel importExcel = new ImportExcel(file, 0, 0);
			dataList = importExcel.getDataList(BorrowImportExcel.class);
		} catch (Exception io) {
			return "获取Excel文件异常,请核对后在提交";
		}
		try {
			Map<String, Object> infoMap = borrowManager.saveBorrowUploadDistinct(dataList);
//			return null;
			return jsonMapper.toJson(infoMap);
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	/**
	 * 查看可用债权详情
	 * 2015年12月2日
	 * By 周俊
	 * @param model
	 * @param creditValueId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = {"/goBorrowLook",""})
	public String borrowLook(Model model,String creditValueId,HttpServletRequest request,HttpServletResponse response){
		Page<BorrowLookEx> page = borrowManager.borrowLook(new Page<BorrowLookEx>(request, response),creditValueId);
		Borrow borrow = borrowManager.getBorrow(creditValueId);
		page.setFuncName("borrowLookPage");
		model.addAttribute("borrow",borrow);
		model.addAttribute("page",page);
		model.addAttribute("creditValueId", creditValueId);
		String[] types ={"tz_credit_src","jk_prof_type","tz_repay_day"};
		FortuneDictUtil.addDicts(model, types);
		return "borrow/borrowLook";
	}
	
	/**
	 * 查看可用债权匹配列表
	 * 2015年12月2日
	 * By 周俊
	 * @param model
	 * @param creditValueId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = {"/borrowLookList",""})
	public String borrowLookList(Model model,String creditValueId,HttpServletRequest request,HttpServletResponse response){
		Page<BorrowLookEx> page = borrowManager.borrowLook(new Page<BorrowLookEx>(request, response),creditValueId);
		page.setFuncName("borrowLookPage");
		model.addAttribute("page",page);
		model.addAttribute("creditValueId", creditValueId);
		return "borrow/borrowLookList";
	}
	
	/**
	 * 校验可用债权分配信息
	 * 2016年4月11日
	 * By 周俊
	 * @param creditValueId
	 * @return
	 */
	@RequestMapping("/checkBorrowAllot")
	@ResponseBody
	public String checkBorrowAllot(String creditValueId){
		Borrow borrow = borrowManager.get(creditValueId);
		return jsonMapper.toJson(borrow.getLoanCreditValue());
	}
	
	/**
	 * 跳转分配可用债权价值列表
	 * 2015年12月2日
	 * By 周俊
	 * @param model
	 * @param creditValueId
	 * @return
	 */
	@RequestMapping(value = "/goAllot")
	public String goBorrowAllot(Model model, String creditValueId){
		BorrowAllotView borrowAllotView = borrowManager.getBorrowAllot(creditValueId);
		model.addAttribute("borrowAllotView",borrowAllotView);
		return "borrow/borrowAllot";
	}
	
	/**
	 * 分配可用债权
	 * 2016年4月25日
	 * By 周俊
	 * @param borrowAllotView
	 * @return
	 */
	@RequestMapping(value = "/borrowAllot", method = RequestMethod.POST)
	@ResponseBody
	public String borrowAllot(BorrowAllotView borrowAllotView){
		try {
			borrowMonthManager.saveBorrowAllot(borrowAllotView);
		} catch (Exception e) {
			return jsonMapper.toJson(e.getMessage());
		}
		return jsonMapper.toJson(null);
	}
	
	/**
	 * 可用债权批量分配
	 * 2016年2月27日
	 * By 周俊
	 * @param batchCreditValueId
	 */
	@RequestMapping("/borrowBatchAllot")
	@ResponseBody
	public String borrowBatchAllot(String batchBorrowInfo){
		try {
			borrowFacade.batchSaveBorrowAllotAndSplit(batchBorrowInfo);
		} catch (Exception e) {
			return jsonMapper.toJson(e.getMessage());
		}
		    return jsonMapper.toJson(null);
	} 
	
	/**
	 * ajax请求借款总金额
	 * 2015年12月24日
	 * By 周俊
	 * @param creditValueId
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/ajaxMoney",method=RequestMethod.GET)
	@ResponseBody
	public String ajaxMoney(String creditValueId,HttpServletResponse response){
		BigDecimal money = borrowManager.getLoanCreditValue(creditValueId);
		return this.renderString(response, money);
	}
	
	/**
	 * 返回到可用债权列表
	 * 2016年1月26日
	 * By 周俊
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/backBorrowList")
	public String backBorrowList(HttpServletRequest request,HttpServletResponse response,Model model){
		BorrowView borrowView = (BorrowView) request.getSession().getAttribute("borrowView");
		int pageNo = 0;
		int pageSize = 0 ; 
		 try {
			 pageNo = Integer.valueOf(CookieUtils.getCookie(request,"pageNo"));
		} catch (NumberFormatException e) {
			pageNo = 1;
		}
		try {
			pageSize = Integer.valueOf(CookieUtils.getCookie(request,"pageSize"));
		} catch (NumberFormatException e) {
			pageSize = Integer.valueOf(Global.getConfig("page.pageSize"));
		}
		Page<Borrow> page = new Page<Borrow>(pageNo, pageSize);
		page = borrowManager.findBorrow(page,borrowView);
		// 查询总金额数
		BigDecimal allMoney = borrowManager.findAllMoney(borrowView);
		model.addAttribute("allMoney",allMoney);
		model.addAttribute("page", page);
		model.addAttribute("borrowView",borrowView);
		String[] types ={"tz_credit_src","tz_zjtr_mark","jk_prof_type","tz_repay_day","tz_loan_distinguish"};
		FortuneDictUtil.addDicts(model, types);
		return "borrow/borrowList";
	}
	
	/**
	 * 导出excel
	 * 2015年12月21日
	 * By 周俊
	 * @param borrowView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/outExcel")
	public void outExcel(BorrowView borrowView,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes){
		try {
			
			long startTime = new Date().getTime();
			Map<String, Object> map = borrowManager.objectToMap(borrowView);
			List<String> borrowInfoList = borrowView.getCreditValueIdList();
			if (ArrayHelper.isNotEmpty(borrowInfoList)) {
			List<String> creditValueIdList = new ArrayList<String>();
				for (String borrowInfo : borrowInfoList) {
					try {
						creditValueIdList.add(borrowInfo.split(":")[0]);
					} catch (Exception e) {
						creditValueIdList.add("");
					}
				}
				map.put("creditValueIdList", creditValueIdList);
			}
			String fileName = FileType.KYZQLB.getName()+"_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			ExportExcelUtils.exportData(map, response,BorrowConstant.BORROW_EXPORT,fileName,Node.KYZQ.value);
			long endTime = new Date().getTime();
			logger.info(("周俊0000000000000"+"数据=======")+(endTime-startTime));
			/*String fileName = FileType.KYZQLB.getName()+"_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			ExportExcel exportExcel = new ExportExcel(FileType.KYZQLB.getName(),BorrowOutExcel.class); 
			exportExcel.setDataList(list);
			exportExcel.write(response, fileName);
			exportExcel.dispose();
//			list.addAll(list);
			long startTime = new Date().getTime();
			ExcelUtil.getInstance().exportObj2ExcelByTemplate(FileType.KYZQLB.getName(), fileName, response, list,BorrowOutExcel.class , true);
			long endTime = new Date().getTime();
			logger.info(("周俊0000000000000"+"数据"+list.size()+"=======")+(endTime-startTime));
//			JxlsUtils<BorrowOutExcel> jxlsUtils = new JxlsUtils<BorrowOutExcel>();
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("borrowList",list);
//			jxlsUtils.exportExcel("borrow/borrow.xls",map ,fileName, ".xls", response);
			return null;*/
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出Excel失败! 失败信息"+e.getMessage());
		}
	}
	
	/**
	 * 导出债权Excel
	 * 2016年10月27日
	 * By 陈广鹏
	 * @param borrowView
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 */
	@RequestMapping("/exportBorrow")
	public void exportBorrow(BorrowView borrowView,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes){
		try {
			
			Map<String, Object> map = borrowManager.objectToMap(borrowView);
			List<String> borrowInfoList = borrowView.getCreditValueIdList();
			if (ArrayHelper.isNotEmpty(borrowInfoList)) {
				List<String> creditValueIdList = new ArrayList<String>();
				for (String borrowInfo : borrowInfoList) {
					try {
						creditValueIdList.add(borrowInfo.split(":")[0]);
					} catch (Exception e) {
						creditValueIdList.add("");
					}
				}
				map.put("creditValueIdList", creditValueIdList);
			}
			String filename =  DateUtils.formatDate(new Date(), "yyyy年MM月dd日")+"_导出债权";
			BorrowExportor exportor = new BorrowExportor(filename);
			exportor.exportData(map, response);
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出Excel失败! 失败信息"+e.getMessage());
		}
	}
		
}
