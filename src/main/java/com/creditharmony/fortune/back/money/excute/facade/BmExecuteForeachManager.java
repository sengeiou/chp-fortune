package com.creditharmony.fortune.back.money.excute.facade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.creditharmony.bpm.utils.SpringUtil;
import com.creditharmony.common.util.ListUtils;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.core.common.type.BackResult;
import com.creditharmony.core.excel.util.ImportExcel;
import com.creditharmony.core.fortune.type.BackMoneyPlat;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyListDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.common.entity.ext.ExportBean;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.back.money.excute.service.ExecuteManager;
import com.creditharmony.fortune.back.money.excute.service.OnlineManager;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.common.entity.ReturnMsg;
import com.creditharmony.fortune.constants.ThreadPoolConstant;
import com.creditharmony.fortune.template.entity.backmoney.ExecuteJzhImportModel;
import com.creditharmony.fortune.template.entity.backmoney.ExecuteTl1stImportModel;
import com.creditharmony.fortune.template.entity.backmoney.ExecuteWyModel;
import com.creditharmony.fortune.template.entity.backmoney.ExecuteZjImportModel;
import com.creditharmony.fortune.template.entity.backmoney.FyImportModel;
import com.creditharmony.fortune.utils.StringExUtil;

/**
 * 执行回款不带事务Service
 * @Class Name BmExecuteForeachManager
 * @author 陈广鹏
 * @Create In 2016年4月28日
 */
@Service
public class BmExecuteForeachManager extends CoreManager<BackMoneyListDao, ListItemView> {

	@Autowired
	private BackMoneyListDao listDao;
	@Autowired
	private BmManager bmManager;
	@Autowired
	private ExecuteManager executeManager;
	@Autowired
	private OnlineManager onlineManager;

	private final ExecutorService executor = Executors.newFixedThreadPool(ThreadPoolConstant.FORTUNE_POOL_NUM);

	/**
	 * 批量回款
	 * 2015年12月17日
	 * By 陈广鹏
	 * @param view
	 */
	public String multiExecute(ListItemView view) {
		String rtnMsg = "";
		List<BackMoneyPool> dataList = bmManager.getDataList(view);
		
		ResultView resultView = new ResultView();
		resultView.setCheckExaminetype(view.getCheckExaminetype());
		resultView.setCheckExamine(view.getCheckExamine());
		resultView.setCheckReason(view.getCheckReason());
		resultView.setPlatformId(view.getPlatformId());
		
		for (BackMoneyPool bmp : dataList) {
			try{
				resultView.setBackmId(bmp.getBackmId());
				resultView.setLendCode(bmp.getLendCode());
				resultView.setVerTime(bmp.getVerTime());
				
				executeManager.execute(resultView);
			}catch(Exception e){
				FortuneException forException = new FortuneException();
				forException.setLoanCode(view.getLendCode());
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_EXCUTE.getCode());
				forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
				forException.setRemark("批量执行回款时处理失败");
				
				forException.preInsert();
				FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
				forDao.insert(forException);
				
				rtnMsg += bmp.getLendCode()+",";
			}
		}
		return StringExUtil.trimLast(rtnMsg, ",");
	}
	
	/**
	 * 处理执行回款结果
	 * 2016年4月28日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public String execute(ResultView view){
		String result = "";
		try{
			executeManager.execute(view);
		}catch(Exception e){
			FortuneException forException = new FortuneException();
			forException.setLoanCode(view.getLendCode());
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setNode(FortuneLogNode.MONEY_EXCUTE.getCode());
			forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
			forException.setRemark("执行回款处理失败");
			
			forException.preInsert();
			FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
			forDao.insert(forException);
			
			result = e.getMessage();
		}
		return result;
	}
	
	/**
	 * 执行回款上传
	 * 2016年5月4日
	 * By 陈广鹏
	 * @param file
	 * @param view
	 * @param count
	 * @return
	 */
	public String executeUpload(MultipartFile file, ListItemView view) {
		String rtnMsg = "";
		try {
			if (BackMoneyPlat.FYPT.value.equals(view.getPlatformId())) {
				// 富友上传
				ImportExcel ie = new ImportExcel(file, 0, 0);
				List<FyImportModel> list = ie.getDataList(FyImportModel.class);

				rtnMsg = fyUpload(list);
			} else if (BackMoneyPlat.JZH.value.equals(view.getPlatformId())) {
				// 金账户上传
				ImportExcel ie = new ImportExcel(file, 0, 0);
				List<ExecuteJzhImportModel> list = ie
						.getDataList(ExecuteJzhImportModel.class);
				rtnMsg = jzhUpload(list);
			} else if (BackMoneyPlat.WY.value.equals(view.getPlatformId())) {
				// 网银上传
				ImportExcel ie = new ImportExcel(file, 0, 0);
				List<ExecuteWyModel> list = ie
						.getDataList(ExecuteWyModel.class);
				rtnMsg = wyUploadNew(list);
			} else if (BackMoneyPlat.ZJPT.value.equals(view.getPlatformId())) {
				// 中金上传
				ImportExcel ie = new ImportExcel(file, 1, 0);
				List<ExecuteZjImportModel> list = ie
						.getDataList(ExecuteZjImportModel.class);
				rtnMsg = zjUpload(list);
			} else if (BackMoneyPlat.TL.value.equals(view.getPlatformId())) {
				// 通联上传
				ImportExcel ie = new ImportExcel(file, 0, 0);
				List<ExecuteTl1stImportModel> list = ie
						.getDataList(ExecuteTl1stImportModel.class);
				rtnMsg = tlUpload(list);
			}
		} catch (Exception e) {
			FortuneException forException = new FortuneException();
			forException.setLoanCode(null);
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setNode(FortuneLogNode.MONEY_EXCUTE.getCode());
			forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
			forException.setRemark("线下回款上传文件处理失败");
			
			forException.preInsert();
			FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
			forDao.insert(forException);
			rtnMsg = "请检查上传的数据是否填写有误";
		}
		return rtnMsg;
	}
	
	/**
	 * 富友上传处理
	 * 2015年12月29日
	 * By 陈广鹏
	 * @param list
	 */
	public String fyUpload(List<FyImportModel> list) {
		int count = 0;
		String rtnLendCode = "";
		String rtn = "";
		if (ListUtils.isEmptyList(list)) {
			rtn = "请检查上传的数据是否填写有误";
		}
		
		// 同一出借编号回款成功的数据归结为一条数据
		Map<String, FyImportModel> map = new HashMap<String, FyImportModel>();
		for (FyImportModel im : list) {
			String lendCode = im.getRemark();
			if (StringUtils.isBlank(lendCode)) {
				continue;
			} else {
				lendCode = lendCode.split("_")[0];
			}
			
			FyImportModel model = map.get(lendCode);
			if (null != model) {
				// 重复出借编号，且回款失败，回款金额累加
				if (!BmConstant.FY_SUCCESS.equals(im.getSendBack())){
					model.setAmount(model.getAmount()+(im.getAmount()));
					model.setFinalResult(BackResult.FAIL.value);
					model.setBackRemark(im.getBackRemark());
				}
			}else {
				if (BmConstant.FY_SUCCESS.equals(im.getSendBack())){
					im.setFinalResult(BackResult.SUCESS.value);
					im.setAmount(0.0);
				} else {
					im.setFinalResult(BackResult.FAIL.value);
				}
				map.put(lendCode, im);
			}
		}
		
		for (String lendCode : map.keySet()) {
			try {
				executeManager.fyUpload(map, lendCode);
				count++;
			} catch (Exception e) {
				rtnLendCode += lendCode + ",";
				e.printStackTrace();
				
				FortuneException forException = new FortuneException();
				forException.setLoanCode(lendCode);
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_EXCUTE.getCode());
				forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
				forException.setRemark("执行回款列富友上传处理失败");
				
				forException.preInsert();
				FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
				forDao.insert(forException);
			}
		}
		
		if (count==0) {
			rtn = "请检查上传的数据是否填写有误";
		}
		if (StringUtils.isNotEmpty(rtnLendCode)) {
			rtn = StringExUtil.trimLast(rtnLendCode, ",");
			rtn += "上传处理失败";
		}
		
		return rtn;
	}
	
	/**
	 * 执行回款：金账户上传
	 * 2016年1月7日
	 * By 陈广鹏
	 * @param list
	 */
	public String jzhUpload(List<ExecuteJzhImportModel> list) {
		int count = 0;
		String rtnLendCode = "";
		String rtn = "";
		if (ListUtils.isEmptyList(list)) {
			rtn = "请检查上传的数据是否填写有误";
		}
		for (ExecuteJzhImportModel im : list) {
			String lendCode = im.getRemark().split("_")[0];
			if (StringUtils.isBlank(lendCode)) {
				continue;
			}
			try {
				executeManager.jzhUpload(im,lendCode);
				count ++;
			} catch (Exception e) {
				rtnLendCode += lendCode + ",";
				e.printStackTrace();
				
				FortuneException forException = new FortuneException();
				forException.setLoanCode(lendCode);
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_EXCUTE.getCode());
				forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
				forException.setRemark("执行回款列金账户上传处理失败");
				
				forException.preInsert();
				FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
				forDao.insert(forException);
			}
		}
		
		if (count==0) {
			rtn = "请检查上传的数据是否填写有误";
		}
		if (StringUtils.isNotEmpty(rtnLendCode)) {
			rtn = StringExUtil.trimLast(rtnLendCode, ",");
			rtn += "上传处理失败";
		}
		return rtn;
	}

	/**
	 * 执行回款：网银上传，上传文件中只包含回款完成的数据
	 * 2016年1月7日
	 * By 陈广鹏
	 * @param list
	 */
	public String wyUpload(List<ExecuteWyModel> list) {
		int count = 0;
		String rtnLendCode = "";
		String rtn = "";
		if (ListUtils.isEmptyList(list)) {
			rtn = "请检查上传的数据是否填写有误";
		}
		for (ExecuteWyModel im : list) {
			String lendCode = im.getLendCode();
			if (StringUtils.isBlank(lendCode)) {
				continue;
			}
			try {
				executeManager.wyUpload(im);
				count ++;
			} catch (Exception e) {
				rtnLendCode += lendCode + ",";
				e.printStackTrace();
				
				FortuneException forException = new FortuneException();
				forException.setLoanCode(lendCode);
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_EXCUTE.getCode());
				forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
				forException.setRemark("执行回款列网银上传处理失败");
				
				forException.preInsert();
				FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
				forDao.insert(forException);
			}
		}
		if (count==0) {
			rtn = "请检查上传的数据是否填写有误";
		}
		if (StringUtils.isNotEmpty(rtnLendCode)) {
			rtn = StringExUtil.trimLast(rtnLendCode, ",");
			rtn += "上传处理失败";
		}
		return rtn;
	}
	
	/**
	 * 网银上传，可以处理拆分后上传的情况
	 * 2017年2月28日
	 * By 陈广鹏
	 * @param list
	 * @return
	 */
	public String wyUploadNew(List<ExecuteWyModel> list) {
		int count = 0;
		String rtnLendCode = "";
		String rtn = "";
		if (ListUtils.isEmptyList(list)) {
			rtn = "请检查上传的数据是否填写有误";
		}
		// 拆分数据合并
		Map<String, ExecuteWyModel> map = new HashMap<String, ExecuteWyModel>();
		for (ExecuteWyModel im : list) {
			String lendCode = im.getLendCode();
			if (StringUtils.isBlank(lendCode)) {
				continue;
			}
			ExecuteWyModel model = map.get(lendCode);
			if (null != model) {
				model.setSuceessMoney(model.getSuceessMoney()+im.getBackActualbackMoney());
			} else {
				im.setSuceessMoney(im.getBackActualbackMoney());
				map.put(lendCode, im);
			}
		}
		
		// 处理数据
		for (String lendCode : map.keySet()) {
			ExecuteWyModel im = map.get(lendCode);
			try {
				executeManager.wyUploadNew(im);
				count ++;
			} catch (Exception e) {
				rtnLendCode += lendCode + ",";
				e.printStackTrace();
				
				FortuneException forException = new FortuneException();
				forException.setLoanCode(lendCode);
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_EXCUTE.getCode());
				forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
				forException.setRemark("执行回款列网银上传处理失败");
				
				forException.preInsert();
				FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
				forDao.insert(forException);
			}
		}
		if (count==0) {
			rtn = "请检查上传的数据是否填写有误";
		}
		if (StringUtils.isNotEmpty(rtnLendCode)) {
			rtn = StringExUtil.trimLast(rtnLendCode, ",");
			rtn += "上传处理失败";
		}
		return rtn;
	}

	/**
	 * 执行回款：中金上传
	 * 2016年1月27日
	 * By 陈广鹏
	 * @param list
	 * @return 
	 */
	public String zjUpload(List<ExecuteZjImportModel> list) {
		int count = 0;
		String rtnLendCode = "";
		String rtn = "";
		if (ListUtils.isEmptyList(list)) {
			rtn = "请检查上传的数据是否填写有误";
		}
		for (ExecuteZjImportModel im : list) {
			String remark = im.getRemark();
			if (ObjectHelper.isEmpty(remark) || remark.split("_").length<2) {
				continue;
			}
			String lendCode = remark.split("_")[0];
			try {
				executeManager.zjUpload(im, lendCode);
				count ++;
			} catch (Exception e) {
				rtnLendCode += lendCode + ",";
				e.printStackTrace();
				
				FortuneException forException = new FortuneException();
				forException.setLoanCode(lendCode);
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_EXCUTE.getCode());
				forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
				forException.setRemark("执行回款列表中金上传处理失败");
				
				forException.preInsert();
				FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
				forDao.insert(forException);
			}
		}
		if (count==0) {
			rtn = "请检查上传的数据是否填写有误";
		}
		if (StringUtils.isNotEmpty(rtnLendCode)) {
			rtn = StringExUtil.trimLast(rtnLendCode, ",");
			rtn += "上传处理失败";
		}
		return rtn;
	}
	
	/**
	 * 执行回款：通联上传
	 * 2016年2月3日
	 * By 陈广鹏
	 * @param list
	 */
	public String tlUpload(List<ExecuteTl1stImportModel> list) {
		int count = 0;
		String rtnLendCode = "";
		String rtn = "";
		if (ListUtils.isEmptyList(list)) {
			rtn = "请检查上传的数据是否填写有误";
		}
		
		// 设置出借编号
		for (ExecuteTl1stImportModel im : list) {
			String remark = im.getRemark();
			if (ObjectHelper.isEmpty(remark)) {
				continue;
			}
			String lendCode = im.getRemark().split("_")[0];
			im.setLendCode(lendCode);
		}
		
		// 同一出借编号回款成功的数据归结为一条数据
		Map<String, ExecuteTl1stImportModel> map = new HashMap<String, ExecuteTl1stImportModel>();
		for (ExecuteTl1stImportModel im : list) {
			if (ObjectHelper.isEmpty(im.getLendCode())) {
				continue;
			}
			
			ExecuteTl1stImportModel model = map.get(im.getLendCode());
			if (null != model) {
				// 已经处理过的【处理中】数据保持状态不变
				if (BackResult.DELLING.value.equals(model.getFinalResult())) {
					continue;
				}
				if (BmConstant.TL_DEALING.equals(im.getDealStatus())) {
					im.setFinalResult(BackResult.DELLING.value);
				} else if (!BmConstant.TL_SUCCESS.equals(im.getDealStatus())){
					// 重复出借编号，且回款失败，回款金额累加
					model.setMoney(model.getMoney()+(im.getMoney()));
					model.setFinalResult(BackResult.FAIL.value);
					model.setReason(im.getReason());
				}
			}else {
				if (BmConstant.TL_SUCCESS.equals(im.getDealStatus())){
					im.setFinalResult(BackResult.SUCESS.value);
					im.setMoney(0.0);
				} else if (BmConstant.TL_DEALING.equals(im.getDealStatus())) {
					im.setFinalResult(BackResult.DELLING.value);
				} else {
					im.setFinalResult(BackResult.FAIL.value);
				}
				map.put(im.getLendCode(), im);
			}
		}
		
		for (String lendCode : map.keySet()) {
			try {
				executeManager.tlUpload(map, lendCode);
				count ++;
			} catch (Exception e) {
				rtnLendCode += lendCode + ",";
				e.printStackTrace();
				
				FortuneException forException = new FortuneException();
				forException.setLoanCode(lendCode);
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_EXCUTE.getCode());
				forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
				forException.setRemark("执行回款列金账户上传处理失败");
				
				forException.preInsert();
				FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
				forDao.insert(forException);
			}
		}
		if (count==0) {
			rtn = "请检查上传的数据是否填写有误";
		}
		if (StringUtils.isNotEmpty(rtnLendCode)) {
			rtn = StringExUtil.trimLast(rtnLendCode, ",");
			rtn += "上传处理失败";
		}
		return rtn;
	}
	
	/**
	 * 金账户线上回款
	 * 2016年3月5日
	 * By 陈广鹏
	 * @param view
	 */
	public int onlineJZHBack(ListItemView view,ReturnMsg rtn){
		int success = 0;
		StringBuffer message = new StringBuffer();
		List<ExportBean> list =new ArrayList<ExportBean>();
		String backmId = view.getBackmId();
		if(StringUtils.isNotBlank(backmId)){
			// 有勾选
			String[] applys = backmId.split(",");
			List<String> codes = null;
			if (applys.length > 0) {
				codes = Arrays.asList(applys);
			}
			view.setBackmIds(codes);
		}
		User currentUser = view.getCurrentUser();
		list = listDao.findBackMoneyDataList(view);
		if(list != null && list.size() > 0){
			CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
			int callCount = list.size();
			for (final ExportBean eb : list) {
				eb.setCurrentUser(currentUser);
				try {
					completionService.submit(new Callable<String>() {
						
						@Override
						public String call() throws Exception {
							StringBuffer rtnMessage = new StringBuffer();
							try{
								String result = onlineManager.jzhExcute(eb);
								//接口调用成功，但是回款失败，返回记录
								if(StringUtils.isNotEmpty(result)){
									rtnMessage = rtnMessage.append("出借编号").append(eb.getLendCode())
											.append(": ").append(result).append("<br/>");
								}
							}catch(Exception e){
								FortuneException forException = new FortuneException();
								forException.setLoanCode(eb.getLendCode());
								forException.setMessage(e.getMessage());
								forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
								forException.setNode(FortuneLogNode.MONEY_EXCUTE.getCode());
								forException.setImportantLevel(FortuneLogLevel.RED.value);
								forException.setRemark("金账户线上回款失败");
								
								forException.preInsert();
								FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
								forDao.insert(forException);
								
								rtnMessage = rtnMessage.append("出借编号").append(eb.getLendCode())
										.append(": ").append(e.getMessage()).append("<br/>");
							}
							return rtnMessage.toString();
						}
					});
					success++;
				} catch (Exception e) {
					logger.error(eb.getLendCode() + "金账户回款失败",e);
					//此处提交线程发生异常，提交线程数减去1
					message = message.append(eb.getLendCode()).append("金账户回款失败<br>");
				}
			}
			// 组装返回结果
			for(int i =0; i< callCount; i++){
				try{
					Future<String> future = completionService.take();
					message = message.append(future.get());
				}catch(Exception e){
					logger.error("completionService.take()失败",e);
				}				
			}
		} else {
			message = message.append("页面数据已过期，请刷新页面后再次操作");
		}
		//错误消息返回
		rtn.setMessage(message.toString());
		//返回成功条数
		return success;
	}
	
	/**
	 * 线上回款(中金、通联)
	 * 2016年2月3日
	 * By 赵红江
	 * @param view
	 */
	public Map<String, String> onlineBack(ListItemView view){
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "");
		map.put("number","0");
		StringBuffer message = new StringBuffer();
		int success = 0;
		List<ExportBean> list =null;
		
		String backmId = view.getBackmId();
		if(StringUtils.isNotBlank(backmId)){
			// 有勾选
			String[] applys = backmId.split(",");
			List<String> codes = null;
			if (applys.length > 0) {
				codes = Arrays.asList(applys);
			}
			view.setBackmIds(codes);
		}
		User currentUser = view.getCurrentUser();
		list = listDao.findBackMoneyDataList(view);
		if(list != null && list.size() > 0){
			CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
			int callCount = list.size();
			for (final ExportBean eb : list) {
				eb.setPlatformId(view.getPlatformId());
				eb.setInterfaceType(view.getInterfaceType());
				eb.setCurrentUser(currentUser);
				try {
					completionService.submit(new Callable<String>(){

						@Override
						public String call() throws Exception {
							String rtn = "";
							try{
								rtn = onlineManager.deductAndUpdatebackmInfo(eb);
							}catch(Exception e){
								FortuneException forException = new FortuneException();
								forException.setLoanCode(eb.getLendCode());
								forException.setMessage(e.getMessage());
								forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
								forException.setNode(FortuneLogNode.MONEY_EXCUTE.getCode());
								forException.setImportantLevel(FortuneLogLevel.RED.value);
								forException.setRemark("线上回款(非金账户)失败");
								
								forException.preInsert();
								FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
								forDao.insert(forException);
								
								rtn = "出借【" + eb.getLendCode() + "】发送第三方平台失败："+e.getMessage()+"<br/>";
							}	
							return rtn;
						}
						
					});
				success ++;
				} catch (Exception e) {
					logger.error(eb.getLendCode() + "非金账户线上回款失败",e);
					//此处提交线程发生异常，提交线程数减去1
					message = message.append(eb.getLendCode()).append("非金账户线上回款失败<br>");
				}
			}
			// 组装返回结果
			for(int i =0; i< callCount; i++){
				try{
					Future<String> future = completionService.take();
					message = message.append(future.get());
				}catch(Exception e){
					logger.error("completionService.take()失败",e);
				}				
			}
			map.put("message", message.toString());
			map.put("number", String.valueOf(success));
		} else {
			map.put("message", "页面数据已过期，请刷新页面后再次操作");
		}
		
		return map;
	}

}
