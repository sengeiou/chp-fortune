package com.creditharmony.fortune.back.money.history.facade;

import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.utils.SpringUtil;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyListDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.back.money.history.service.HistoryManager;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.utils.StringExUtil;

/**
 * 回款已办不带事务Service
 * @Class Name BmHistoryForeachManager
 * @author 陈广鹏
 * @Create In 2016年4月28日
 */
@Service
public class BmHistoryForeachManager extends CoreManager<BackMoneyListDao, ListItemView> {
	
	@Autowired
	private BmManager bmManager;
	@Autowired
	private HistoryManager historyManager;

	/**
	 * 批量退回
	 * 2015年12月16日
	 * By 陈广鹏
	 * @param view
	 */
	public String multiReturn(ListItemView view) {
		String rtn = "操作成功";//返回内容
		String rtnLendCode = "";
		List<BackMoneyPool> dataList = bmManager.getDataList(view);
		if (ObjectHelper.isEmpty(dataList)) {
			rtn = "页面数据已过期，请刷新页面后再次操作";
		}
		
		ResultView resultView = new ResultView();
		resultView.setCheckExamine(view.getCheckExamine());
		resultView.setCheckReason(view.getCheckReason());
		
		for (BackMoneyPool bmp : dataList) {
			resultView.setBackmId(bmp.getBackmId());
			resultView.setLendCode(bmp.getLendCode());
			resultView.setVerTime(bmp.getVerTime());
			try {
				historyManager.returnBackmoney(resultView);
			} catch (Exception e) {
				FortuneException forException = new FortuneException();
				forException.setLoanCode(bmp.getLendCode());
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_HISTORY.getCode());
				forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
				forException.setRemark("已回款批量退回处理时失败");
				
				forException.preInsert();
				FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
				forDao.insert(forException);
				
				logger.debug(e.getMessage());
				logger.error("回款ID为" + bmp.getBackmId() + "的回款处理失败，原因：" + e.getMessage());
				rtnLendCode += bmp.getLendCode() +",";
			}
		}
		if (StringUtils.isNotEmpty(rtnLendCode)) {
			rtn = StringExUtil.trimLast(rtnLendCode, ",");
			rtn += "处理失败";
		}
		return rtn;
	}
	
	/**
	 * 已回款退回
	 * 2016年4月28日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public String returnBackmoney(ResultView view) {
		String result = "";
		try {
			historyManager.returnBackmoney(view);
		} catch (Exception e) {
			FortuneException forException = new FortuneException();
			forException.setLoanCode(view.getLendCode());
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setNode(FortuneLogNode.MONEY_HISTORY.getCode());
			forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
			forException.setRemark("已回款退回处理失败");
			
			forException.preInsert();
			FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
			forDao.insert(forException);
			
			result = e.getMessage();
		}
		return result;
	}

}
