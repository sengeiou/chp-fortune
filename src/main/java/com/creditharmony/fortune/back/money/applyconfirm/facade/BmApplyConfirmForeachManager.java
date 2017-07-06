package com.creditharmony.fortune.back.money.applyconfirm.facade;

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
import com.creditharmony.fortune.back.money.applyconfirm.service.ApplyConfirmManager;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyListDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.template.entity.backmoney.ApplyConfirmExportModel;
import com.creditharmony.fortune.utils.StringExUtil;

/**
 * 回款申请确认不带事务Service
 * @Class Name BmApplyConfirmForeachManager
 * @author 陈广鹏
 * @Create In 2016年4月27日
 */
@Service
public class BmApplyConfirmForeachManager extends CoreManager<BackMoneyListDao, ListItemView>{
	
	@Autowired
	private BmManager bmManager;
	@Autowired
	private ApplyConfirmManager applyConfirmManager;

	/**
	 * 批量申请确认
	 * 2015年12月16日
	 * By 陈广鹏
	 * @param view
	 */
	public String multiApplyConfirm(ListItemView view) {
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
		resultView.setBackDay(view.getBackDay());
		resultView.setIsSupplemented(view.getIsSupplemented());
		for (BackMoneyPool bmp : dataList) {
			try {
				resultView.setBackmId(bmp.getBackmId());
				resultView.setLendCode(bmp.getLendCode());
				resultView.setVerTime(bmp.getVerTime());
				
				if (bmManager.checkLendIsInTransfer(resultView.getLendCode())) {
					transferCheck += "出借 "+resultView.getLendCode()+" 正在被内转中，请完成内转后再进行回款操作<br/>";
				} else {
					applyConfirmManager.applyConfirm(resultView);
				}

			} catch (Exception e) {
				FortuneException forException = new FortuneException();
				forException.setLoanCode(resultView.getLendCode());
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_APPLYCONFIRM.getCode());
				forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
				forException.setRemark("批量回款申请处理时失败");
				
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
	 * 处理回款申请确认结果
	 * 2015年12月3日
	 * By 陈广鹏
	 * @param view
	 */
	public String applyConfirm(ResultView view) {
		String message = "";
		try {
			if (bmManager.checkLendIsInTransfer(view.getLendCode())) {
				message = "出借 "+view.getLendCode()+" 正在被内转中，请完成内转后再进行回款操作";
			} else {
				applyConfirmManager.applyConfirm(view);
			}
		} catch (Exception e) {
			FortuneException forException = new FortuneException();
			forException.setLoanCode(view.getLendCode());
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setNode(FortuneLogNode.MONEY_APPLYCONFIRM.getCode());
			forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
			forException.setRemark("回款申请确认失败");
			
			forException.preInsert();
			FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
			forDao.insert(forException);
			
			message = e.getMessage();
		}
		return message;
	}

	/**
	 * 上传回款金额
	 * 2016年5月4日
	 * By 陈广鹏
	 * @param list
	 * @param map
	 */
	public String upLoadBackMoneyList(MultipartFile file) {
		String rtn = "";
		try {
			ImportExcel ie = new ImportExcel(file, 0, 0);
			List<ApplyConfirmExportModel> list = ie.getDataList(ApplyConfirmExportModel.class);
			rtn = upLoadBackMoney(list);
		} catch (Exception e) {
			FortuneException forException = new FortuneException();
			forException.setLoanCode(null);
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setNode(FortuneLogNode.MONEY_APPLYCONFIRM.getCode());
			forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
			forException.setRemark("回款申请确认，上传回款金额，文件处理失败");
			
			forException.preInsert();
			FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
			forDao.insert(forException);
			
			logger.error("上传回款金额，文件读取失败" + e.getMessage(), e);
		}
		return rtn;
	}
	
	/**
	 * 回款申请确认列表，上传回款金额
	 * 2016年1月6日
	 * By 陈广鹏
	 * @param list
	 */
	public String upLoadBackMoney(List<ApplyConfirmExportModel> list) {
		int count = 0;
		String rtn = "处理成功";
		String rtnLendCode = "";
		if (ListUtils.isEmptyList(list)) {
			return "请检查上传的数据是否填写有误";
		}
		
		for (ApplyConfirmExportModel model : list) {
			try {
				applyConfirmManager.uploadBackMoneySingle(model);
				count++;
			} catch (Exception e) {
				rtnLendCode += model.getLendCode() + ",";
				
				FortuneException forException = new FortuneException();
				forException.setLoanCode(model.getLendCode());
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_APPLYCONFIRM.getCode());
				forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
				forException.setRemark("回款申请确认，上传回款金额，文件处理失败");
				
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
