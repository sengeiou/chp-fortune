package com.creditharmony.fortune.back.money.approve.facade;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.adapter.bean.in.dirswitch.DjrSwitchChangeAmountInBean;
import com.creditharmony.adapter.bean.out.dirswitch.DjrSwitchChangeAmountOutBean;
import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.adapter.constant.ServiceType;
import com.creditharmony.adapter.core.client.ClientPoxy;
import com.creditharmony.bpm.utils.SpringUtil;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.FortuneChannelFlag;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.FortuneSwitchApproveStatus;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.money.approve.service.ApprovalManager;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyListDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.service.LoanApplyManager;
import com.creditharmony.fortune.utils.StringExUtil;

/**
 * 回款审批不带事务Service
 * @Class Name BmApprovalForeachManager
 * @author 陈广鹏
 * @Create In 2016年4月28日
 */
@Service
public class BmApprovalForeachManager extends CoreManager<BackMoneyListDao, ListItemView>{

	@Autowired
	private ApprovalManager approvalManager;
	@Autowired
	private BmManager bmManager;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	private LoanApplyManager loanApplyManager;
	
	/**
	 * 批量回款审批
	 * 2016年2月1日
	 * By 陈广鹏
	 * @param view
	 */
	public String multiApproval(ListItemView view) {
		String rtn = "审批成功";
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
			String backmId = bmp.getBackmId(); 
			resultView.setBackmId(backmId);
			resultView.setLendCode(bmp.getLendCode());
			resultView.setVerTime(bmp.getVerTime());
			try{
				if (bmManager.checkLendIsInTransfer(resultView.getLendCode())) {
					transferCheck += "出借 "+resultView.getLendCode()+" 正在被内转中，请完成内转后再进行回款操作<br/>";
				} else {
					if (YoN.SHI.value.equals(view.getCheckExaminetype()) && FortuneChannelFlag.DJR.value.equals(bmp.getDictFortunechannelflag()) && FortuneSwitchApproveStatus.SPCG.value.equals(bmp.getSwitchApproveStatus())) {
						String resMsg = changeAmountToDJR(bmp.getLendCode(), bmp.getBackActualbackMoney());
						if (StringUtils.isNotEmpty(resMsg)) {
							transferCheck += "出借" + bmp.getLendCode() + "实际回款金额接口错误，错误信息为：" + resMsg + "<br/>";
						} else {
							approvalManager.applyApproval(resultView);
						}
					} else {
						approvalManager.applyApproval(resultView);
					}
				}
			}catch(Exception e){
				FortuneException forException = new FortuneException();
				forException.setLoanCode(bmp.getLendCode());
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_APPROVE.getCode());
				forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
				forException.setRemark("批量回款审批时处理失败");
				
				forException.preInsert();
				FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
				forDao.insert(forException);
				
				logger.debug(e.getMessage());
				logger.error("回款ID为" + bmp.getBackmId() + "的回款处理失败，原因：" + e.getMessage());
				rtnLendCode += bmp.getLendCode()+",";
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
	 * 提交审批结果
	 * 2016年4月28日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public String applyApproval(ResultView view) {
		String str = "";
		
		try {
			if (bmManager.checkLendIsInTransfer(view.getLendCode())) {
				if(bmManager.checkNZorZZ(view.getLendCode())){
					str = "出借 "+view.getLendCode()+" 正在被自转中，请完成自转后再进行回款操作";
				}else{
					str = "出借 "+view.getLendCode()+" 正在被内转中，请完成内转后再进行回款操作";
				}
			} else {
				LoanApply loanApply = new LoanApply();
				loanApply.setApplyCode(view.getLendCode());
				LoanApply la = loanApplyManager.get(loanApply);
				if (YoN.SHI.value.equals(view.getCheckExaminetype()) && FortuneChannelFlag.DJR.value.equals(view.getDictFortunechannelflag()) && FortuneSwitchApproveStatus.SPCG.value.equals(la.getSwitchApproveStatus())) {
					str = changeAmountToDJR(view.getLendCode(), view.getBackActualbackMoney());
					if (StringUtils.isNotEmpty(str)) {
						str = "出借" + view.getLendCode() + "实际回款金额接口错误，错误信息为：" + str;
					} else {
						approvalManager.applyApproval(view);
					}
				} else {
					approvalManager.applyApproval(view);
				}
			}
		} catch (Exception e) {
			FortuneException forException = new FortuneException();
			forException.setLoanCode(view.getLendCode());
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setNode(FortuneLogNode.MONEY_APPROVE.getCode());
			forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
			forException.setRemark("回款审批处理失败");
			
			forException.preInsert();
			FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
			forDao.insert(forException);
			
			str = e.getMessage();
		}
		return str;
	}

	public String changeAmountToDJR(String lendCode, BigDecimal backMoney) {
		String result = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		String sn = df.format(new Date()).toString() + randomNumStr(3);

		DjrSwitchChangeAmountInBean inParam = new DjrSwitchChangeAmountInBean();
		inParam.setSn(sn);
		inParam.setLendCode(lendCode);
		inParam.setApplyDeductMoney(backMoney != null ? String.valueOf(backMoney) : "0");

		ClientPoxy service = new ClientPoxy(ServiceType.Type.DJRSWITCH_ChangeAmount_SERVICE);
		DjrSwitchChangeAmountOutBean outParam = (DjrSwitchChangeAmountOutBean) service.callService(inParam);
		String djrResult = "成功!";
		if (!ReturnConstant.SUCCESS.equals(outParam.getRetCode())) {
			result = outParam.getRetMsg();
			djrResult = "失败! 详情为：" + outParam.getRetMsg();
		}
		checkManager.addCheckDjr(lendCode, "发送实际回款金额到大金融" + djrResult, "回款审批", "201612161601_djr", UserUtils.getUser(UserUtils.getUserId()).getName());
		return result;
	}

	/**
	 * 随机产生指定长度的数据字符串
	 * 
	 * @param length指定长度
	 * @return 结果
	 */
	public static final String randomNumStr(int length) {
		if (length < 1) {
			return null;
		}
		Random numGen = new Random();
		char[] numbers = ("0123456789").toCharArray();
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbers[numGen.nextInt(10)];
		}
		return new String(randBuffer);
	}
}