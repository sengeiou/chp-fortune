package com.creditharmony.fortune.back.money.confirm.facade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.creditharmony.bpm.utils.SpringUtil;
import com.creditharmony.common.util.ListUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BackResult;
import com.creditharmony.core.excel.util.ImportExcel;
import com.creditharmony.core.fortune.type.BackMoneyPlat;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyListDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.back.money.confirm.service.ConfirmManager;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.template.entity.backmoney.ExecuteTl1stImportModel;
import com.creditharmony.fortune.template.entity.backmoney.ExecuteZjImportModel;
import com.creditharmony.fortune.template.entity.backmoney.FyImportModel;
import com.creditharmony.fortune.utils.StringExUtil;

/**
 * 回款确认不带事务Service
 * @Class Name BmConfirmForeachManager
 * @author 陈广鹏
 * @Create In 2016年4月28日
 */
@Service
public class BmConfirmForeachManager extends CoreManager<BackMoneyListDao, ListItemView> {
	
	@Autowired
	private BmManager bmManager;
	@Autowired
	private ConfirmManager confirmManager;
	
	/**
	 * 批量回款确认
	 * 2016年1月29日
	 * By 陈广鹏
	 * @param view
	 */
	public String multiConfirm(ListItemView view) {
		String rtn = "操作成功";
		String rtnLendCode = "";
		List<BackMoneyPool> dataList = bmManager.getDataList(view);
		if (ListUtils.isEmptyList(dataList)) {
			rtn = "页面数据已过期，请刷新页面后再次操作";
		}
		
		ResultView resultView = new ResultView();
		resultView.setCheckExamine(view.getCheckExamine());
		resultView.setCheckExaminetype(view.getCheckExaminetype());
		resultView.setCheckReason(view.getCheckReason());
		resultView.setBackDay(view.getBackDay());
		for (BackMoneyPool bmp : dataList) {
			try {
				resultView.setBackmId(bmp.getBackmId());
				resultView.setLendCode(bmp.getLendCode());
				resultView.setVerTime(bmp.getVerTime());
				confirmManager.confirm(resultView);
			} catch (Exception e) {
				FortuneException forException = new FortuneException();
				forException.setLoanCode(bmp.getLendCode());
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_CONFIRM.getCode());
				forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
				forException.setRemark("批量回款确认时处理失败");
				
				forException.preInsert();
				FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
				forDao.insert(forException);
				
				logger.debug(e.getMessage());
				logger.error("回款ID为" + bmp.getBackmId() + "的回款处理失败，原因：" + e.getMessage());
				rtnLendCode += bmp.getLendCode() + ",";
			}
		}
		
		if (StringUtils.isNotEmpty(rtnLendCode)) {
			rtn = StringExUtil.trimLast(rtnLendCode, ",");
			rtn += "处理失败";
		}
		return rtn;
	}
	
	/**
	 * 处理回款确认结果
	 * 2016年4月28日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public String confirm(ResultView view) {
		String result = "";
		try {
			confirmManager.confirm(view);
		} catch (Exception e) {
			FortuneException forException = new FortuneException();
			forException.setLoanCode(view.getLendCode());
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setNode(FortuneLogNode.MONEY_CONFIRM.getCode());
			forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
			forException.setRemark("回款确认处理失败");
			
			forException.preInsert();
			FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
			forDao.insert(forException);
			
			result = e.getMessage();
		}
		return result;
	}
	
	
	/**
	 * 批量退回到待回款列表
	 * 2016年3月4日
	 * By 陈广鹏
	 * @param view
	 * @return 
	 */
	public String multiBacktoExecute(ListItemView view) {
		String rtn = "操作成功";
		String rtnLendCode = "";
		List<BackMoneyPool> dataList = bmManager.getDataList(view);
		if (ListUtils.isEmptyList(dataList)) {
			rtn = "页面数据已过期，请刷新页面后再次操作";
		}
		
		ListItemView lv = new ListItemView();
		for (BackMoneyPool bmp : dataList) {
			lv.setBackmId(bmp.getBackmId());
			lv.setLendCode(bmp.getLendCode());
			lv.setVerTime(bmp.getVerTime());
			try {
				confirmManager.backtoExecute(lv);
			} catch (Exception e) {
				FortuneException forException = new FortuneException();
				forException.setLoanCode(view.getLendCode());
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_CONFIRM.getCode());
				forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
				forException.setRemark("批量退回到待回款处理失败");
				
				forException.preInsert();
				FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
				forDao.insert(forException);
				
				rtnLendCode += bmp.getLendCode() + ",";
			}
		}
		
		if (StringUtils.isNotEmpty(rtnLendCode)) {
			rtn = StringExUtil.trimLast(rtnLendCode, ",");
			rtn += "处理失败";
		}
		
		return rtn;
	}
	
	/**
	 * 退回到待回款列表
	 * 2016年5月3日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public String backtoExecute(ListItemView view){
		String result = "SUCCESS";
		try {
			confirmManager.backtoExecute(view);
		} catch (Exception e) {
			FortuneException forException = new FortuneException();
			forException.setLoanCode(view.getLendCode());
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setNode(FortuneLogNode.MONEY_CONFIRM.getCode());
			forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
			forException.setRemark("退回到待回款处理失败");
			
			forException.preInsert();
			FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
			forDao.insert(forException);
			
			result = e.getMessage();
		}
		return result;
	}
	
	/**
	 * 回款确认列表，上传回盘结果处理
	 * 2016年5月9日
	 * By 陈广鹏
	 * @param file
	 * @param view
	 * @return 成功更新数据条数
	 */
	public String confirmUpload(MultipartFile file, ListItemView view){
		String rtnMsg = "";
		try {
			if (BackMoneyPlat.FYPT.value.equals(view.getPlatformId())) {
				// 富友上传
				ImportExcel ie = new ImportExcel(file, 0, 0);
				List<FyImportModel> list = ie.getDataList(FyImportModel.class);
				rtnMsg = fy2ndUpload(list);
			}  else if (BackMoneyPlat.ZJPT.value.equals(view.getPlatformId())) {
				// 中金上传
				ImportExcel ie = new ImportExcel(file, 1, 0);
				List<ExecuteZjImportModel> list = ie.getDataList(ExecuteZjImportModel.class);
				rtnMsg = zj2ndUpload(list);
			} else if (BackMoneyPlat.TL.value.equals(view.getPlatformId())) {
				// 通联上传
				ImportExcel ie = new ImportExcel(file, 0, 0);
				List<ExecuteTl1stImportModel> list = ie.getDataList(ExecuteTl1stImportModel.class);
				rtnMsg = tl2ndUpload(list);
			}
		} catch (Exception e) {
			FortuneException forException = new FortuneException();
			forException.setLoanCode(view.getLendCode());
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setNode(FortuneLogNode.MONEY_CONFIRM.getCode());
			forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
			forException.setRemark("回款确认上传回盘结果处理失败");
			
			forException.preInsert();
			FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
			forDao.insert(forException);
			
			rtnMsg = "请检查上传的数据是否填写有误";
		}
		
		return rtnMsg;
	}
	
	/**
	 * 回款确认列表，富友平台上传回盘结果
	 * 2016年2月4日
	 * By 陈广鹏
	 * @param list
	 */
	public String fy2ndUpload(List<FyImportModel> list) {
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
					model.setFinalResult(BackResult.FAIL.value);
					model.setBackRemark(im.getBackRemark());
					model.setAmount(model.getAmount()+(im.getAmount()));
				}
			} else {
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
				confirmManager.fy2ndUpload(map, lendCode);
				count++;
			} catch (Exception e) {
				rtnLendCode += lendCode + ",";
				
				FortuneException forException = new FortuneException();
				forException.setLoanCode(lendCode);
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_CONFIRM.getCode());
				forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
				forException.setRemark("回款确认列表富友上传回盘结果处理失败");
				
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
	 * 回款确认列表，中金平台上传回盘结果
	 * 2016年2月4日
	 * By 陈广鹏
	 * @param list
	 */
	public String zj2ndUpload(List<ExecuteZjImportModel> list) {
		int count = 0;
		String rtnLendCode = "";
		String rtn = "";
		if (ListUtils.isEmptyList(list)) {
			rtn = "请检查上传的数据是否填写有误";
		}
		
		for (ExecuteZjImportModel im : list) {
			String lendCode = im.getRemark();
			if (StringUtils.isBlank(lendCode)) {
				continue;
			} else {
				lendCode = lendCode.split("_")[0];
			}
			try {
				confirmManager.zj2ndUpload(im, lendCode);
				count++;
			} catch (Exception e) {
				rtnLendCode += lendCode + ",";
				
				FortuneException forException = new FortuneException();
				forException.setLoanCode(lendCode);
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_CONFIRM.getCode());
				forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
				forException.setRemark("回款确认列表中金上传回盘结果处理失败");
				
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
	 * 回款确认列表，通联平台上传回盘结果
	 * 2016年2月4日
	 * By 陈广鹏
	 * @param list
	 */
	public String tl2ndUpload(List<ExecuteTl1stImportModel> list) {
		int count = 0;
		String rtnLendCode = "";
		String rtn = "";
		if (ListUtils.isEmptyList(list)) {
			rtn = "请检查上传的数据是否填写有误";
		}
		
		// 设置出借编号
		for (ExecuteTl1stImportModel im : list) {
			String lendCode = im.getRemark().split("_")[0];
			im.setLendCode(lendCode);
		}
		
		// 同一出借编号回款成功的数据归结为一条数据
		Map<String, ExecuteTl1stImportModel> map = new HashMap<String, ExecuteTl1stImportModel>();
		for (ExecuteTl1stImportModel im : list) {
			if (StringUtils.isEmpty(im.getLendCode())) {
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
				confirmManager.tl2ndUpload(map, lendCode);
				count ++;
			} catch (Exception e) {
				rtnLendCode += lendCode + ",";
				
				FortuneException forException = new FortuneException();
				forException.setLoanCode(lendCode);
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_CONFIRM.getCode());
				forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
				forException.setRemark("回款确认列表通联上传回盘结果处理失败");
				
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

}
