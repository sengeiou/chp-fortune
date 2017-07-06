package com.creditharmony.fortune.back.money.supplement.facade;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.creditharmony.bpm.utils.SpringUtil;
import com.creditharmony.common.util.ListUtils;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.excel.util.ImportExcel;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyListDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.back.money.supplement.service.BmSupplementManager;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.template.entity.backmoney.ApplyConfirmExportModel;
import com.creditharmony.fortune.utils.StringExUtil;

@Service
public class BmSupplementForeachManager extends CoreManager<BackMoneyListDao, ListItemView> {
	
	@Autowired
	private BmSupplementManager supplementManager;
	@Autowired
	private BmManager bmManager;

	/**
	 * 回款补息
	 * 2016年11月8日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public String supplement(ResultView view) {
		String msg = "SUCCESS";
		
		try {
			supplementManager.supplement(view);
		} catch (Exception e) {
			logger.error(e.getMessage());
			
			FortuneException forException = new FortuneException();
			forException.setLoanCode(view.getLendCode());
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setNode(FortuneLogNode.MONEY_SUPPLEMENT.getCode());
			forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
			forException.setRemark("回款补息处理失败");
			
			forException.preInsert();
			FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
			forDao.insert(forException);
			
			msg = e.getMessage();
		}
		
		return msg;
	}

	/**
	 * 批量回款补息确认
	 * 2016年11月8日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public String multiSupplement(ListItemView view) {
		String rtn = "操作成功";//返回内容
		String rtnLendCode = "";
		String transferCheck = "";
		
		List<BackMoneyPool> dataList = bmManager.getDataList(view);
		if (ObjectHelper.isEmpty(dataList)) {
			rtn = "页面数据已过期，请刷新页面后再次操作";
		}
		
		ResultView resultView = new ResultView();
		resultView.setCheckExamine(view.getCheckExamine());
		resultView.setCheckExaminetype(view.getCheckExaminetype());
		resultView.setCheckReason(view.getCheckReason());
		
		for (BackMoneyPool bmp : dataList) {
			try {
				resultView.setBackmId(bmp.getBackmId());
				resultView.setLendCode(bmp.getLendCode());
				resultView.setVerTime(bmp.getVerTime());
				supplementManager.supplement(resultView);
			} catch (Exception e) {
				FortuneException forException = new FortuneException();
				forException.setLoanCode(view.getLendCode());
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_SUPPLEMENT.getCode());
				forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
				forException.setRemark("批量回款补息确认失败");
				
				forException.preInsert();
				FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
				forDao.insert(forException);
				
				logger.debug(e.getMessage());
				logger.error("回款ID为" + bmp.getBackmId() + "的回款处理失败，原因：" + e.getMessage());
				rtnLendCode += bmp.getLendCode() +",";
			}
		}
		
		if (StringUtils.isNotEmpty(rtnLendCode)) {
			rtnLendCode = StringExUtil.trimLast(rtnLendCode, ",");
			rtnLendCode += "处理失败";
		}
		if (StringUtils.isNotEmpty(transferCheck) || StringUtils.isNotEmpty(rtnLendCode)) {
			rtn = transferCheck + rtnLendCode;
		}
		return rtn;
	}

	/**
	 * 上传补息后回款金额，识别数据到内存
	 * 2016年11月8日
	 * By 陈广鹏
	 * @param file
	 * @return
	 */
	public String uploadSupplementedMoney(MultipartFile file) {
		String rtn = "处理成功";
		try {
			ImportExcel ie = new ImportExcel(file, 0, 0);
			List<ApplyConfirmExportModel> list = ie.getDataList(ApplyConfirmExportModel.class);
			rtn = uploadSupplementedMoneyList(list);
		} catch (Exception e) {
			FortuneException forException = new FortuneException();
			forException.setLoanCode(null);
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setNode(FortuneLogNode.MONEY_SUPPLEMENT.getCode());
			forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
			forException.setRemark("回款补息，上传补息后回款金额，文件处理失败");
			
			forException.preInsert();
			FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
			forDao.insert(forException);
			
			logger.error("上传补息后回款金额，文件读取失败" + e.getMessage(), e);
		}
		return rtn;
	}

	/**
	 * 上传补息后回款金额，循环dataList
	 * 2016年11月8日
	 * By 陈广鹏
	 * @param list
	 * @return
	 */
	private String uploadSupplementedMoneyList(List<ApplyConfirmExportModel> list) {
		int count = 0;
		String rtn = "处理成功";
		String rtnLendCode = "";
		if (ListUtils.isEmptyList(list)) {
			return "请检查上传的数据是否填写有误";
		}
		
		for (ApplyConfirmExportModel model : list) {
			try {
				supplementManager.uploadSupplementedMoneySingle(model);
				count++;
			} catch (Exception e) {
				rtnLendCode += model.getLendCode() + ",";
				
				FortuneException forException = new FortuneException();
				forException.setLoanCode(model.getLendCode());
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_SUPPLEMENT.getCode());
				forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
				forException.setRemark("回款补息，上传补息后回款金额，文件处理失败");
				
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
	 * 批量修改债转日
	 * 2016年11月10日
	 * By 陈广鹏
	 * @param itemView
	 * @return
	 */
	public String modifyDebtTransferDate(ListItemView itemView) {
		String rtn = "处理成功";
		String rtnLendCode = "";
		
		// 债转日
		Date debtTransferDate = itemView.getDebtTransferDate();
		itemView.setStatusList(BmConstant.SUPPLEMENT_STATUS_LIST);
		List<ListItemView> dataList = bmManager.getSuplementList(itemView);
		for (ListItemView view : dataList) {
			view.setDebtTransferDate(debtTransferDate);
			try {
				supplementManager.modifyDebtTransferDate(view);
			} catch (Exception e) {
				FortuneException forException = new FortuneException();
				forException.setLoanCode(view.getLendCode());
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_SUPPLEMENT.getCode());
				forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
				forException.setRemark("批量修改债转日失败");
				
				forException.preInsert();
				FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
				forDao.insert(forException);
				
				logger.debug(e.getMessage());
				logger.error("回款ID为" + view.getBackmId() + "的回款处理失败，原因：" + e.getMessage());
				rtnLendCode += view.getLendCode() +",";
			}
		}
		if (StringUtils.isNotEmpty(rtnLendCode)) {
			rtnLendCode = StringExUtil.trimLast(rtnLendCode, ",");
			rtnLendCode += "处理失败";
			rtn = rtnLendCode;
		}
		return rtn;
	} 

}
