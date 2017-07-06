package com.creditharmony.fortune.back.money.apply.facade;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.PriorityBackState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.back.money.apply.service.ApplyManager;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyListDao;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyPoolDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.back.priority.common.view.PriorityDetailItem;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.redemption.constant.RedeemState;
import com.creditharmony.fortune.template.entity.backmoney.ChannelImportModelNew;
import com.creditharmony.fortune.utils.StringExUtil;

/**
 * 回款申请Service
 * @Class Name ApplyManager
 * @author 陈广鹏
 * @Create In 2016年4月13日
 */
@Service
public class BmApplyForeachManager extends CoreManager<BackMoneyListDao, ListItemView>{
	
	@Autowired
	private BmManager bmManager;
	@Autowired
	private ApplyManager applyManager;
	@Autowired
	private BackMoneyPoolDao poolDao;
	
	/**
	 * 批量回款申请
	 * 2016年3月3日
	 * By 陈广鹏
	 * @param view
	 */
	public String multiApply(ListItemView view) {
		StringBuffer rtn = new StringBuffer();
		
		List<BackMoneyPool> dataList = bmManager.getDataList(view);
		if (ObjectHelper.isEmpty(dataList)) {
			rtn = new StringBuffer("页面数据已过期，请刷新页面后再次操作");
			return rtn.toString();
		}
		
		ResultView resultView = new ResultView();
		
		ListItemView tempView = new ListItemView();
		for (BackMoneyPool bmp : dataList) {
			resultView.setBackmId(bmp.getBackmId());
			resultView.setVerTime(bmp.getVerTime());
			resultView.setLendCode(bmp.getLendCode());
			tempView.setBackmId(bmp.getBackmId());
			try{
				String checkStr = dataExamine(tempView);
				if (YoN.SHI.value.equals(checkStr)) {
					applyManager.apply(resultView);
				} else {
					// 校验不通过，返回提示信息
					rtn.append(checkStr).append("<br/>");
				}
			}catch(Exception e){
				FortuneException forException = new FortuneException();
				forException.setLoanCode(bmp.getLendCode());
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_APPLY.getCode());
				forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
				forException.setRemark("批量回款申请处理时失败");
				
				forException.preInsert();
				FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
				forDao.insert(forException);
				
				logger.debug(e.getMessage());
				logger.error("回款ID为" + bmp.getBackmId() + "的回款处理失败，原因：" + e.getMessage());
				rtn.append("出借【").append(bmp.getLendCode()).append("】处理失败<br/>");
			}			
		}
		if (StringUtils.isEmpty(rtn.toString())) {
			return "批量操作成功";
		} else {
			return rtn.toString();
		}
	}
	
	/**
	 * 发起回款申请 
	 * 2015年12月3日 
	 * By 陈广鹏
	 * @param view
	 */
	public String apply(ResultView view) {
		String message = "";
		try {
			applyManager.apply(view);
		} catch (Exception e) {
			FortuneException forException = new FortuneException();
			forException.setLoanCode(view.getLendCode());
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setNode(FortuneLogNode.MONEY_APPLY.getCode());
			forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
			forException.setRemark("回款申请失败");
			
			forException.preInsert();
			FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
			forDao.insert(forException);
			
			message = e.getMessage();
		}
		return message;
	}
	
	/**
	 * 数据校验，验证是否可以提交回款申请
	 * 2016年3月4日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public String dataExamine(ListItemView view) {
		List<String> ids = new ArrayList<String>();
		if (!ObjectHelper.isEmpty(view.getBackmId())) {
			ids = Arrays.asList(view.getBackmId().split(","));
		}
		
		//已发起优先回款申请，【状态】是“待审核”或“未通过”的不能提交到【我的待办-回款申请确认】列表中
		for (String backmId : ids) {
			PriorityDetailItem item = poolDao.searchPriorityItem(backmId);
			if(item != null){
				if(PriorityBackState.DSP.value.equals(item.getPriorityBackState())){
					return "出借 "+item.getLendCode() + "正在被进行优先回款申请, 目前优先回款状态为待审批, 请完成优先回款流程再进行操作<br/>";
				}
				if(PriorityBackState.SPWTG.value.equals(item.getPriorityBackState())){
					return "出借 "+item.getLendCode() + "正在被进行优先回款申请, 目前优先回款状态为审批未通过, 请完成优先回款流程再进行操作<br/>";
				}
			}
			BackMoneyPool pool = poolDao.getByBackmId(backmId);
			if (BackType.TQSH.value.equals(pool.getBackMoneyType())) {
				String status = poolDao.getRedeemStatus(pool.getLendCode());
				if (RedeemState.DTS.value.equals(status)) {
					return "出借 "+pool.getLendCode() + "无法提交出借回款申请";
				}
			}
			if (bmManager.checkLendIsInTransfer(pool.getLendCode())) {
				if(bmManager.checkNZorZZ(pool.getLendCode())){
					return "出借 "+pool.getLendCode()+" 正在被自转中，请完成自转后再进行回款操作";
				}else{
					return "出借 "+pool.getLendCode()+" 正在被内转中，请完成内转后再进行回款操作";
				}
			}
		}
		return YoN.SHI.value;
	}
	
	/**
	 * 更新渠道标识
	 * 2016年6月20日
	 * By 陈广鹏
	 * @param file
	 * @param view2 
	 * @return
	 */
	public String updateChannel(MultipartFile file, ListItemView view2) {
		int count = 0;
		String rtnLendCode = "";
		String rtn = "操作成功";
//		List<ChannelImportModel> list = null;
		//更换上传的模板
		List<ChannelImportModelNew> list = null;
		try {
			ImportExcel ie = new ImportExcel(file, 0, 0);
//			list = ie.getDataList(ChannelImportModel.class);
			list = ie.getDataList(ChannelImportModelNew.class);
		} catch (Exception e) {
			FortuneException forException = new FortuneException();
			forException.setLoanCode(null);
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setNode(FortuneLogNode.MONEY_APPLY.getCode());
			forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
			forException.setRemark("回款上传更新渠道标识文件识别失败");
			
			forException.preInsert();
			FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
			forDao.insert(forException);
			
			rtn = "文件识别失败";
		}
		
		if (ListUtils.isEmptyList(list)) {
			rtn = "请检查上传的数据是否填写有误";
		} else {
			ListItemView view = new ListItemView();
			view.setStatusList(BmConstant.APPLY_STATUS_LIST);
			view.setListFlag(view2.getListFlag());
//			for (ChannelImportModel im : list) {
			for (ChannelImportModelNew im : list) {
				if (StringUtils.isEmpty(im.getLendCode())) {
					continue;
				}
				view.setLendCode(im.getLendCode());
				view.setApplyLendDay(im.getApplyLendDay());
				view.setFinalLinitDate(im.getApplyExpireDay());
				
				try {
					applyManager.updateChannel(view);
					count++;
				} catch (Exception e) {
					String lendCode = im.getLendCode();
					rtnLendCode += lendCode + ",";
					
					FortuneException forException = new FortuneException();
					forException.setLoanCode(lendCode);
					forException.setMessage(e.getMessage());
					forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
					forException.setNode(FortuneLogNode.MONEY_APPLY.getCode());
					forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
					forException.setRemark("回款上传更新渠道标识处理失败");
					
					forException.preInsert();
					FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
					forDao.insert(forException);
				}
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
