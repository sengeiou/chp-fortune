package com.creditharmony.fortune.back.interest.confrim.facade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BackResult;
import com.creditharmony.core.fortune.type.BackMoneyPlat;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.BacksmsState;
import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.interest.common.dao.BackInterestCommonDao;
import com.creditharmony.fortune.back.interest.common.service.BackInterestCommonService;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.interest.entity.BackInterestLog;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.back.interest.util.UploadFileCheck;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.template.entity.backInterest.ChinaFinanceUpload;
import com.creditharmony.fortune.template.entity.backInterest.CommunicationsUploadFirst;
import com.creditharmony.fortune.template.entity.backInterest.FuYouUpload;

/**
 * 批量回息确认
 * @Class Name BackInterestConfrimForEachManager 
 * @author 李志伟
 * @Create In 2016年4月29日
 */
@Service
public class BackInterestConfrimForEachManager {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BackInterestCommonService backInterestCommonService;
	@Autowired
	private BackInterestCommonDao backInterestCommonDao;
	
	/**
	 * 批量回息确认
	 * 2015年12月7日
	 * by 李志伟
	 * @param bip
	 * @param so
	 */
	public String bacthConfrim(BackInterestPool bip, SearchObject so) {
		
		String errorMesg = "";
		LoanApply loan = null;
		
		List<BackInterestPool> backiIdAndLendCodeList = new ArrayList<BackInterestPool>();
		Map<String,Object> map = new HashMap<String, Object>();
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}	
		StaticMethodUtil.getCondition(so, map,  StaticMethodUtil.getBackInterestConfrimStatus());
		backiIdAndLendCodeList = backInterestCommonDao.findBackiIdAndLendCode(map);
		BackInterestLog bil = StaticMethodUtil.madeApproval(bip, BacksmsState.DHXQRTH.value, BacksmsState.YHX.value);
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		
		for (BackInterestPool bbi : backiIdAndLendCodeList) {
			 
			try {
				// 排他条件
				bbi.setVerState(StaticMethodUtil.getBackInterestConfrimStatus());
				if(bip.getCheckExaminetype().equals(YoN.FOU.value)){
					bbi.setRebackFlag(YoN.SHI.value);
				}else{
					/* 回息确认通过后如果本笔出借对应的产品为信和宝C时，
					本期回息日期=本期回息日期+6个月*/
					if(bbi.getProductCode().equals(ProductCode.XHBC.value)){
						LoanApply la = backInterestCommonService.getLoanApplyMesg(bbi.getLendCode());
						loan = StaticMethodUtil.setNextBackInterestDay(bbi, la);
					}
					BackMoneyPool money = backInterestCommonService.getBackMoneyMesg(bbi.getLendCode());
					if(null != money && StringUtils.isNotEmpty(money.getDictBackStatus())){
						if(money.getDictBackStatus().equals(BackState.YHK.value)){
							if(null != loan){
								loan.setApplyEndStatus(FinishState.YWJ.value);
								loan.setApplyCode(bbi.getLendCode());
								loan.preUpdate();
							}else{
								loan = new LoanApply();
								loan.setApplyEndStatus(FinishState.YWJ.value);
								loan.setApplyCode(bbi.getLendCode());
								loan.preUpdate();
							}
						}
					}
				}
				// 生成历史留痕
				Check ch = StaticMethodUtil.madeHistory(userId, userName, bbi.getLendCode(), bil.getType(), FortuneLogNode.INTEREST_CONFRIM.getName(), bbi.getBackiId(),
						GlobalConstant.DHXQR_LIST+""+ GlobalConstant.BRANCH+""+bil.getType()+";"+GlobalConstant.UPDATE_ST + BacksmsState.getBacksmsState(bil.getDictBackiStatus()));
				
				bil.setBackiId(bbi.getBackiId());
				bil.setId(IdGen.uuid());
				
				bbi.setBackMoneyStatus(bil.getDictBackiStatus());
				bbi.setBackMoneyDay(bip.getBackMoneyDay());
				bbi.setReturnReason(bil.getCheckExamine());
				bbi.preUpdate();
			
				if(null != loan){
					errorMesg += backInterestCommonService.doSubmit(bbi, bil, ch, loan);
				}else{
					errorMesg += backInterestCommonService.doSubmit(bbi, bil, ch);
				}
			
			} catch (Exception e) {
				errorMesg += "[出借编号]："+bbi.getLendCode()+"[批量提交失败]<br/>";
				e.printStackTrace();
				logger.error("待回息确认批量提交失败:"+e.getMessage(), e);
				backInterestCommonService.addExceptionMesgs(bbi.getBackiId(), e, "[回息编号]"+bbi.getBackiId()+"[回息确认提交失败]", FortuneLogNode.INTEREST_CONFRIM.getCode());
			}
		}
		if(("").equals(errorMesg)){
			return "true";
		}
		return errorMesg;
	}
	
	/**
	 * 上传中金回盘结果
	 * 2016年4月27日
	 * by 李志伟
	 * @param list
	 * @return
	 */
	public String uploadZJResult(List<ChinaFinanceUpload> list) {
		
		BackInterestPool pool = new BackInterestPool();
		BackInterestPool pol = new BackInterestPool();
		String lendCode = "";
		String currentBillday = "";
		String mesg = "";
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		for (ChinaFinanceUpload im : list) {
			
			boolean zj = UploadFileCheck.zjUploadFileDataCheck(im);
			if(zj){
				continue;
			}
			if(null != im.getBackiId() && !("").equals(im.getBackiId())){
			
				try {
					lendCode = im.getBackiId().split("回息")[0];
					currentBillday = im.getBackiId().split("回息")[1];
					pol.setLendCode(lendCode);
					pol.setCurrentBillday(StaticMethodUtil.parseDay(currentBillday, GlobalConstant.YYYY_MM_DD));
					pol = backInterestCommonDao.getBackInterestObject(pol);
					if(StringUtils.isNotBlank(im.getBackRealMoney())){
						String s = StaticMethodUtil.parttenMethod(im.getBackRealMoney().replace(",", ""));
						if(!s.equals("")){
							mesg += "[序号]"+im.getNo()+""+s;
							continue;
						}
					}else{
						mesg += "[序号]"+im.getNo()+"上传的结算金额格式错误或者数据为空<br>";
						continue;
					}
					pool.setBackiId(pol.getBackiId());
					pool.setBackRealMoney(pol.getBackRealMoney());
					
					if(im.getTradingStatus().equals(GlobalConstant.PAYSUCCESS)){// 代付成功
						
						pool.setSuccessMoney(new BigDecimal(im.getBackRealMoney().replace(",", "")));
						pool.setBackResult(BackResult.SUCESS.value);
						pool.setFailMoney(BigDecimal.ZERO);
					}else if(im.getTradingStatus().equals(GlobalConstant.PROCESSING)
							|| BmConstant.ZJ_DEALING_2.equals(im.getTradingStatus())){// 正在处理
						
						pool.setBackResult(BackResult.DELLING.value);
					}else if(im.getTradingStatus().equals(GlobalConstant.PAYFAIL)){// 代付失败
						
						pool.setSuccessMoney(BigDecimal.ZERO);
						pool.setFailMoney(new BigDecimal(im.getBackRealMoney().replace(",", "")));
						pool.setBackResult(BackResult.FAIL.value);
						pool.setBackReason(im.getBankMessage());
					}else{
						mesg += "[备注]"+im.getBackiId()+"[不支持此回盘结果,只支持成功、失败、处理中结果]</br>";
						continue;
					}
					pool.setBackDay(StaticMethodUtil.parseDay(im.getBankPayTime(), GlobalConstant.yyyy_mm_dd));
					pool.preUpdate();
					pool.setVerState(StaticMethodUtil.getBackInterestConfrimStatus());
					pool.setVerTime(pol.getVerTime());

					Check hi = StaticMethodUtil.madeHistory(userId, userName, lendCode, GlobalConstant.UPD, FortuneLogNode.INTEREST_CONFRIM.getName(), 
						pool.getBackiId(), GlobalConstant.ZJ_DHXQR_RESULT + ";" + GlobalConstant.BACK_RESULT+BackResult.getBackResult(pool.getBackResult())+ ";" 
							+ GlobalConstant.SUCCESS_MONEY + (pol.getBackRealMoney().subtract(pool.getFailMoney())) + ";" + GlobalConstant.FAIL_MONEY + pool.getFailMoney());
					
					mesg += backInterestCommonService.doUpload(pool, hi);
				
				} catch (ArrayIndexOutOfBoundsException e){
					
					logger.error("待回息确认列表中金上传唯一识别码有误,角标越界", e);
					e.printStackTrace();
					mesg = mesg+"[序号:]"+im.getNo()+"[备注有误]</br>"+e.getMessage();
					
				} catch (Exception e) {
					
					e.printStackTrace();
					this.logger.error("待回息确认列表上传中金回盘结果失败",e);
					mesg += "[序号]"+im.getNo()+"[上传失败]</br>"+e.getMessage();
					backInterestCommonService.addExceptionMesgs(pool.getBackiId(), e, "[序号]"+im.getNo()+"[上传中金回盘结果失败]", FortuneLogNode.INTEREST_CONFRIM.getCode());
				}
			}
		}
		return mesg;
	}

	/**
	 * new fuyou Upload
	 * 2016年5月7日
	 * by 李志伟
	 * @param list
	 * @param newList
	 * @param mesg
	 * @return
	 */
	public String fuYouUpload(List<FuYouUpload> list, List<BackInterestPool> newList, String mesg){	
		
		BackInterestPool pool = new BackInterestPool();
		BackInterestPool pol = new BackInterestPool();
		
		String lendCode = "";//　出借编号
		String currentBillday = "";// 账单日期
		int i = 0;
		BigDecimal successMoney = new BigDecimal(0.0);// 成功金额
		BigDecimal failMoney = new BigDecimal(0.0);// 失败金额
		String backResult = "";// 回盘结果
		String backReason = "";// 错误原因
		Date backDay = null;// 回盘时间
		Iterator<FuYouUpload> it = list.iterator();
		while (it.hasNext()) {
			
			FuYouUpload im = it.next();
			boolean fy = UploadFileCheck.fyUploadFileDataCheck(im);
			if(fy){// 扫除上传恶意空数据
				it.remove();
				continue;
			}
			
			try {
				if(null != im.getBackiId() && !("").equals(im.getBackiId())){
					if(i==0){// 一个基本点，整合同一笔回息的数据
						lendCode = im.getBackiId().split("回息")[0];
						currentBillday = im.getBackiId().split("回息")[1];
						++i;
					}
					String id = im.getBackiId().split("回息")[0];
					String day = im.getBackiId().split("回息")[1];
					
					if(lendCode.equals(id) && currentBillday.equals(day)){
						
						backDay = StaticMethodUtil.parseDay(im.getTradeTime(), GlobalConstant.yyyy_mm_dd);
						if(im.getSendBack().equals(GlobalConstant.REFUND_YES)){// 是退票，回盘结果为失败
							if(("").equals(backResult) || BackResult.FAIL.value.equals(backResult)){
								backResult = BackResult.FAIL.value;
							}
							failMoney = failMoney.add(new BigDecimal(im.getAmount()));
							backReason = backReason + im.getBackRemark();
							it.remove();
							
						}else if(im.getSendBack().equals(GlobalConstant.REFUND_NO)){// 否退票，回盘结果为成功
							if(("").equals(backResult) || !backResult.equals(BackResult.FAIL.value)){
								backResult = BackResult.SUCESS.value;
							}
								successMoney = successMoney.add(new BigDecimal(im.getAmount()));
								it.remove();
						}else{// 回盘结果有误
							mesg+= "[企业流水号]"+im.getBackiId()+"[富有上传是否退票不支持此回盘结果]";
							it.remove();
						}
					}
				}else{
					mesg += "[交易流水号:]"+im.getTradeFlow()+"[企业流水号为空]</br>";
					it.remove();
					continue;
				}
			}catch (ArrayIndexOutOfBoundsException e){
				
				logger.error("待回息确认列表富有上传唯一识别码有误,角标越界", e);
				e.printStackTrace();
				mesg += "[交易流水号:]"+im.getTradeFlow()+"[企业流水号有误]</br>"+e.getMessage();
				it.remove();
				
			}catch (Exception e) {
				logger.error("待回息确认列表上传富友回盘结果失败", e);
				e.printStackTrace();
				mesg = mesg + "[交易流水号]"+im.getTradeFlow()+"[上传失败]</br>"+e.getMessage();
				it.remove();
			}
		}
		
		pol.setLendCode(lendCode);
		pol.setCurrentBillday(StaticMethodUtil.parseDay(currentBillday, GlobalConstant.YYYY_MM_DD));
		pol = backInterestCommonDao.getBackInterestObject(pol);
		pool.setBackiId(pol.getBackiId());
		pool.setBackRealMoney(pol.getBackRealMoney());
		pool.setLendCode(lendCode);
		pool.setSuccessMoney(successMoney);
		pool.setFailMoney(failMoney);
		pool.setBackResult(backResult);
		pool.setBackReason(backReason);
		pool.setBackDay(backDay);
		pool.setVerState(StaticMethodUtil.getBackInterestConfrimStatus());
		pool.setVerTime(pol.getVerTime());
		newList.add(pool);
		
		if(list.size()>0){
			mesg = fuYouUpload(list, newList, mesg);
		}else{
			mesg =  fyUpload(newList, mesg);
		}
		return mesg;
	}
	
	/**
	 * 富友上传文件
	 * 2016年5月7日
	 * by 李志伟
	 * @param newList
	 * @param mesg
	 * @return
	 */
	public String fyUpload(List<BackInterestPool> newList, String mesg) {
		
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		for (BackInterestPool bip : newList) {
			
			bip.preUpdate();
			Check hi = StaticMethodUtil.madeHistory(userId, userName, bip.getLendCode(), GlobalConstant.UPD, FortuneLogNode.INTEREST_CONFRIM.getName(), 
					bip.getBackiId(), GlobalConstant.FY_DHXQR_RESULT + ";" + GlobalConstant.BACK_RESULT + BackResult.getBackResult(bip.getBackResult()) + ";" 
							+ GlobalConstant.SUCCESS_MONEY + (bip.getBackRealMoney().subtract(bip.getFailMoney())) + ";" + GlobalConstant.FAIL_MONEY + bip.getFailMoney());
			
			try {
				mesg += backInterestCommonService.doUpload(bip, hi);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("[待回息确认列表富友上传回盘结果失败:"+bip.getBackiId()+"]", e);
				mesg+="[出借编号]"+bip.getLendCode()+"[上传富友回盘结果失败]"+e.getMessage();
				backInterestCommonService.addExceptionMesgs(bip.getBackiId(), e, "[回息编号]"+bip.getBackiId()+"[上传富友回盘结果失败]", FortuneLogNode.INTEREST_CONFRIM.getCode());
			}
		}
		return mesg;
	}

	/**
	 * 通联上传 - 2/整合数据
	 * 2016年2月18日
	 * by 李志伟
	 * @param list	上传的数据
	 * @param newList 整合以后的数据
	 */
	public String communicationsUploadFirst(List<CommunicationsUploadFirst> list, List<BackInterestPool> newList, String mesg){
		
		BackInterestPool bip = new BackInterestPool();
		BackInterestPool pol = new BackInterestPool();
		BigDecimal successMoney = new BigDecimal("0"); // 成功金额
		BigDecimal failMoney = new BigDecimal("0");	// 失败金额
		String lendCode = "";// 出借编号
		String currentBillday = "";// 账单日期
		String state = "";// 最终回盘结果
		Date date = null;// 回盘时间
		String backReason = "";// 失败原因
		int x = 1;// 为获取初始值
		String s = "";// 循环赋值使用
		String day = "";// 循环赋值使用
		Iterator<CommunicationsUploadFirst> it = list.iterator();
		while(it.hasNext()){
			
			CommunicationsUploadFirst im = it.next();
			try {
				
				boolean tl = UploadFileCheck.tlUploadFileDataCheck(im);
				if(tl){// 扫除恶意上传空数据
					it.remove();
					continue;
				}
				
				if(null != im.getBackiId() && !("").equals(im.getBackiId())){
				
					mesg += StaticMethodUtil.parttenMethod(im.getBackRealMoney());
					s = im.getBackiId().split("回息")[0];// 获取每一个对象的唯一标识
					day = im.getBackiId().split("回息")[1];// 获取每一个对象的唯一标识
					
					if(x==1){// 一个基本点，整合同一笔回息的数据
						lendCode = im.getBackiId().split("回息")[0];// 获取出借编号
						currentBillday = im.getBackiId().split("回息")[1];// 获取账单日期
						++x;
					}
					if(s.equals(lendCode) && day.equals(currentBillday)){// 在list中找出相同出借编号和账单日的数据
						
						date = StaticMethodUtil.parseDay(im.getFinishTime(), GlobalConstant.yyyy_mm_dd);;
						if(im.getProcessingState().equals(GlobalConstant.ChuLiChengGong)){// 处理成功
							successMoney=successMoney.add(new BigDecimal(im.getBackRealMoney()));
							if(!state.equals(BackResult.DELLING.value) && !state.equals(BackResult.FAIL.value)
									|| state.equals("")){
								state = GlobalConstant.ChuLiChengGong;
							}
						}else if(im.getProcessingState().equals(GlobalConstant.ChuLiZhong)){// 处理中
							state = BackResult.DELLING.value;
						}else if(im.getProcessingState().equals(GlobalConstant.ChuLiShiBai)){// 处理失败
							failMoney=failMoney.add(new BigDecimal(im.getBackRealMoney()));
							backReason = im.getBackReason();
							if(!state.equals(GlobalConstant.ChuLiZhong)){
								state = BackResult.FAIL.value;
							}
						}else{
							mesg += "[备注]"+im.getBackiId()+"[不支持此回盘结果,只支持成功、失败、处理中结果]</br>";
							continue;
						}
						it.remove();
					}
				}else{
					mesg += "[文件序号]"+im.getFileNo()+"[备注为空]</br>";
					it.remove();
				}
			}catch (ArrayIndexOutOfBoundsException e){
				
				logger.error("通联上传唯一识别码有误,角标越界", e);
				e.printStackTrace();
				mesg += "[文件序号]"+im.getFileNo()+"[备注有误]</br>"+e.getMessage();
				it.remove();
				
			}catch(Exception e){
				
				e.printStackTrace();
				logger.error("待回息确认列表通联上传失败", e);
				mesg += "[文件序号]"+im.getFileNo()+"上传失败</br>"+e.getMessage();
				it.remove();
			}
		}
		
		pol.setLendCode(lendCode);
		pol.setCurrentBillday(StaticMethodUtil.parseDay(currentBillday, GlobalConstant.YYYY_MM_DD));
		pol = backInterestCommonDao.getBackInterestObject(pol);
		x=1;
		bip.setBackiId(pol.getBackiId());
		bip.setLendCode(lendCode);
		bip.setPlatformId(BackMoneyPlat.TL.value);
		bip.setBackRealMoney(pol.getBackRealMoney());
		bip.setSuccessMoney(successMoney);
		bip.setFailMoney(failMoney);
		bip.setBackResult(state);
 		bip.setBackDay(date);
 		bip.setBackReason(backReason);
 		bip.setVerState(StaticMethodUtil.getBackInterestConfrimStatus());
 		bip.setVerTime(pol.getVerTime());
		
		newList.add(bip);
		
		if(null != list && list.size()>0){
			mesg = communicationsUploadFirst(list, newList, mesg);
		}else{
			mesg = uploadTLResult(mesg, newList);
		}
		return mesg;
	}
	
	/**
	 * 上传通联回盘结果—3
	 * 2016年4月20日
	 * by 李志伟
	 * @param mesg
	 * @param newList
	 * @return
	 */
	public String uploadTLResult(String mesg, List<BackInterestPool> newList){
		
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
 		for (BackInterestPool bip : newList) {
			
 			try {
				bip.preUpdate();
				Check ch = StaticMethodUtil.madeHistory(userId, userName, bip.getLendCode(), GlobalConstant.UPD, FortuneLogNode.INTEREST_CONFRIM.getName(), bip.getBackiId(), GlobalConstant.TL_DHXQR_RESULT+ ";" + GlobalConstant.BACK_RESULT + BackResult.getBackResult(bip.getBackResult()) + ";" 
						+ GlobalConstant.SUCCESS_MONEY + (bip.getBackRealMoney().subtract(bip.getFailMoney())) + ";" + GlobalConstant.FAIL_MONEY + bip.getFailMoney());
				
				mesg += backInterestCommonService.doUpload(bip, ch);
			} catch (Exception e) {
				
				mesg += "[备注]"+bip.getLendCode()+"上传失败</br>"+e.getMessage();
				backInterestCommonService.addExceptionMesgs(bip.getBackiId(), e, "[回息编号]"+bip.getBackiId()+"[上传通联回盘结果失败]", FortuneLogNode.INTEREST_CONFRIM.getCode());
				e.printStackTrace();
				logger.error("待回息确认列表通联上传回盘结果失败", e);
			}
		}
		return mesg;
	}
	
	/**
	 * 批量退回至执行回息列表
	 * 2016年2月18日
	 * by 李志伟
	 * @param map
	 */
	public String returnExcuteInterest(SearchObject so, BackInterestPool bip) {
		
		List<BackInterestPool> backiIdAndLendCodeList = new ArrayList<BackInterestPool>();
		Map<String,Object> map = new HashMap<String, Object>();
		
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		StaticMethodUtil.getCondition(so, map,  StaticMethodUtil.getBackInterestConfrimStatus());
		backiIdAndLendCodeList = backInterestCommonDao.findBackiIdAndLendCode(map);
		
		String errorMesg = "";
		bip.setCheckExaminetype(YoN.FOU.value);
		BackInterestLog bil = StaticMethodUtil.madeApproval(bip, BacksmsState.HXSB.value, ""); 
		
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		for (BackInterestPool bbi : backiIdAndLendCodeList) {
			
			// 排他
			bbi.setVerState(StaticMethodUtil.getBackInterestConfrimStatus());
			bbi.setRebackFlag(YoN.SHI.value);
			// 生成历史留痕
			Check ch = StaticMethodUtil.madeHistory(userId, userName, bbi.getLendCode(), bil.getType(), FortuneLogNode.INTEREST_CONFRIM.getName(), bbi.getBackiId(),
					GlobalConstant.DHXQR_LIST + "" + GlobalConstant.BRANCH + ""+ bil.getType()+""+GlobalConstant.DAO+""+ GlobalConstant.DHX_LIST +";"+GlobalConstant.UPDATE_ST + BacksmsState.getBacksmsState(bil.getDictBackiStatus()));
			
			bil.setBackiId(bbi.getBackiId());
			bil.setId(IdGen.uuid());
			bbi.setBackMoneyStatus(bil.getDictBackiStatus());
			bbi.setReturnReason(bil.getCheckExamine());
			bbi.preUpdate();
			try {
				errorMesg += backInterestCommonService.doSubmit(bbi, bil, ch);
			
			} catch (Exception e) {
				errorMesg = "[出借编号:]"+bbi.getLendCode()+"批量提交失败<br/>"+e.getMessage();
				e.printStackTrace();
				logger.error("批量退回至执行回息失败", e);
				backInterestCommonService.addExceptionMesgs(bbi.getBackiId(), e, "[回息编号]"+bip.getBackiId()+"[批量退回至待回息失败]", FortuneLogNode.INTEREST_CONFRIM.getCode());
			}
		}
		if(("").equals(errorMesg)){
			return "true";
		}
		return errorMesg;
	}
	
	/**
	 * 查询存在提前赎回，且已回款完成的数据的出借编号
	 * 2017年3月1日
	 * @param so
	 * @param resp
	 * @param req
	 * @return
	 */
	public String searchAheadBackids(Map<String,Object> map) {
		map.put("backState",BackState.YHK.value);
		map.put("backType",BackType.TQSH.value);
		List<String> backidlist=backInterestCommonDao.searchAheadBackids(map);
		StringBuffer sb = new StringBuffer();
		if(!backidlist.isEmpty()){
			for (String string : backidlist) {
				sb.append("<br/>"+string);
			}
		}
		return sb.toString();
	}
	/**
	 * 查询存在提前赎回，且已回款完成的数据的出借编号
	 * 2017年3月1日
	 * @param so
	 * @param resp
	 * @param req
	 * @return
	 */
	public String searchAheadBackidsFrom(Map<String,Object> map) {
		//存在提前赎回，且已回款完成的数据
		map.put("backState",BackState.YHK.value);
		map.put("backType",BackType.TQSH.value);
		List<String> backidlist1=backInterestCommonDao.searchAheadBackidsFrom(map);
		StringBuffer  sb = new StringBuffer();
		if(!backidlist1.isEmpty()){
			for (String string : backidlist1) {
				sb.append("<br/>"+string);
			}
		}
		return sb.toString();
	}
}