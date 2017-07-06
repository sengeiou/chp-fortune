package com.creditharmony.fortune.back.interest.excute.facade;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BackResult;
import com.creditharmony.core.fortune.type.BackInterestPlat;
import com.creditharmony.core.fortune.type.BackMoneyPlat;
import com.creditharmony.core.fortune.type.BacksmsState;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.app.webservice.ocr.utils.StringUtil;
import com.creditharmony.fortune.back.interest.common.dao.BackInterestCommonDao;
import com.creditharmony.fortune.back.interest.common.service.BackInterestCommonService;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.interest.entity.BackInterestLog;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.LineBackInterestObj;
import com.creditharmony.fortune.back.interest.entity.LineGoldBackInterestObj;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.back.interest.excute.service.OnLineGoldAccountBackInterestManager;
import com.creditharmony.fortune.back.interest.excute.service.OnLineNoTranscationBackInterestManager;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.back.interest.util.UploadFileCheck;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.constants.ThreadPoolConstant;
import com.creditharmony.fortune.template.entity.backInterest.ChinaFinanceUpload;
import com.creditharmony.fortune.template.entity.backInterest.CommunicationsUploadFirst;
import com.creditharmony.fortune.template.entity.backInterest.FuYouUpload;
import com.creditharmony.fortune.template.entity.backInterest.GoldAccountUpload;
import com.creditharmony.fortune.template.entity.backInterest.NetBankUpload;

/**
 * 执行回息
 * @Class Name ExcuteBackInterestForEachManager 
 * @author 李志伟
 * @Create In 2016年4月29日
 */
@Service
public class ExcuteBackInterestForEachManager {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BackInterestCommonDao backInterestCommonDao;
	@Autowired
	private BackInterestCommonService backInterestCommonService;
	@Autowired
	private OnLineGoldAccountBackInterestManager onLineGoldAccountBackInterest;
	@Autowired
	private OnLineNoTranscationBackInterestManager onLineNoTranscationBackInterestManager;
	
	private final ExecutorService executor = Executors.newFixedThreadPool(ThreadPoolConstant.FORTUNE_POOL_NUM);
	
	/**
	 * new fuyou Upload
	 * 2016年5月7日
	 * by 李志伟
	 * @param list
	 * @return
	 */
	public String fuYouUpload(List<FuYouUpload> list, List<BackInterestPool> newList, String mesg){	
		
		BackInterestPool pool = new BackInterestPool();
		BackInterestPool pol = new BackInterestPool();// 用来传递参数查询使用
		String lendCode = "";//　出借编号
		String currentBillday = "";// 账单日期
		int i = 0;
		BigDecimal successMoney = new BigDecimal(BigDecimal.ZERO.doubleValue());// 成功金额
		BigDecimal failMoney = new BigDecimal(BigDecimal.ZERO.doubleValue());// 失败金额
		String backResult = "";// 回盘结果
		String backReason = "";// 错误原因
		Date backDay = null;// 回盘时间
		Iterator<FuYouUpload> it = list.iterator();
		while (it.hasNext()) {
			
			FuYouUpload im = it.next();
			boolean bl = UploadFileCheck.fyUploadFileDataCheck(im);
			if(bl){
				it.remove();
				continue;
			}
			
			try {
				
				if(null != im.getBackiId() && !("").equals(im.getBackiId())){
					
					if(i==0){
						lendCode = im.getBackiId().split("回息")[0];
						currentBillday = im.getBackiId().split("回息")[1];
						++i;
					}
					String id = im.getBackiId().split("回息")[0];// 出借编号
					String day = im.getBackiId().split("回息")[1];// 账单日期
					
					/**
					 * 以下比较,因为一个出借编号有多笔回息，所以另加一个账单日期进行辅助比较。
					 * 才能真正确定一条数据。
					 */
					if(lendCode.equals(id) && day.equals(currentBillday)){
						backDay = StaticMethodUtil.parseDay(im.getTradeTime(), GlobalConstant.yyyy_mm_dd);						
						if(im.getSendBack().equals(GlobalConstant.REFUND_YES)){// 是退票，回盘结果为失败
							if(("").equals(backResult) || GlobalConstant.REFUND_NO.equals(backResult)){
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
							mesg += "[企业流水号]"+im.getBackiId()+"[富友上传“是否退票”不支持此回盘结果]</br>";
							it.remove();
							continue;
						}
					}
				}else{
					mesg += "[交易流水号]"+im.getTradeFlow()+"[的企业流水号为空]</br>";
					continue;
				}
			}catch (ArrayIndexOutOfBoundsException e){
				
				logger.error("富友上传唯一识别码有误,角标越界", e);
				e.printStackTrace();
				mesg += "[交易流水号:]"+im.getTradeFlow()+"[企业流水号有误]</br>"+e.getMessage();
				it.remove();
				
			}catch (Exception e) {
				logger.error("上传数据有误", e);
				e.printStackTrace();
				mesg += "[交易流水号]"+im.getTradeFlow()+"[读取失败]</br>"+e.getMessage();
				it.remove();
			}
		}
		
		pol.setLendCode(lendCode);
		pol.setCurrentBillday(StaticMethodUtil.parseDay(currentBillday, GlobalConstant.YYYY_MM_DD));
		
		pol = backInterestCommonDao.getBackInterestObject(pol);
		
		pool.setBackiId(pol.getBackiId());
		pool.setVerTime(pol.getVerTime());
		pool.setVerState(StaticMethodUtil.getExcuteBackInterestStatus());
		pool.setLendCode(lendCode);
		pool.setSuccessMoney(successMoney);
		pool.setFailMoney(failMoney);
		pool.setBackResult(backResult);
		pool.setBackReason(backReason);
		pool.setBackMoneyStatus(BacksmsState.DHXQR.value);
		pool.setBackDay(backDay);
		pool.setBackMoneyDay(new Date());
		pool.setPlatformId(BackInterestPlat.FYPT.value);
		newList.add(pool);
		
		if(list.size()>0){
			mesg += fuYouUpload(list, newList, mesg);
		}else{
			mesg +=  fyUpload(newList, mesg);
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
			Check hi = StaticMethodUtil.madeHistory(userId, userName, bip.getLendCode(), GlobalConstant.UPD, FortuneLogNode.INTEREST_EXCUTE.getName(),
					bip.getBackiId(), GlobalConstant.FY_DHX_RESULT+";"+ GlobalConstant.BACK_RESULT+ BackResult.getBackResult(bip.getBackResult())+";"
							+ GlobalConstant.SUCCESS_MONEY+ bip.getSuccessMoney()+";" + GlobalConstant.FAIL_MONEY + bip.getFailMoney() + ";" + 
								GlobalConstant.UPDATE_ST + BacksmsState.getBacksmsState(bip.getBackMoneyStatus()));
			
			try {
				mesg += backInterestCommonService.doUpload(bip, hi);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("[待回息列表富友上传回盘结果失败:"+bip.getBackiId()+"]", e);
				mesg += "[出借编号]"+bip.getLendCode()+"[上传富友回盘结果失败]</br>"+e.getMessage();
				backInterestCommonService.addExceptionMesgs(bip.getBackiId(), e, "[回息编号]"+bip.getBackiId()+"[上传富友回盘结果失败]", FortuneLogNode.INTEREST_EXCUTE.getCode());
			}
		}
		return mesg;
	}

	/**
	 * 金账户上传
	 * 2016年1月15日
	 * by 李志伟
	 * @param list
	 */
	public String goldAccountUpload(List<GoldAccountUpload> list){
		
		String mesg = "";
		String lendCode = "";// 出借编号
		String currentBillday = "";// 账单日期
		BackInterestPool pool = new BackInterestPool();// 更新数据使用对象
		BackInterestPool pol = new BackInterestPool();// 封装查询数据参数
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		for (GoldAccountUpload im : list) {
			
			boolean boo = UploadFileCheck.jzhUploadFileDataCheck(im);
			if(boo){
				continue;
			}
			
			if(null != im.getBackiId() && !("").equals(im.getBackiId())){
			
				try {
					
					lendCode = im.getBackiId().split("回息")[0];
					currentBillday = im.getBackiId().split("回息")[1];
					String s = StaticMethodUtil.parttenMethod(im.getBackRealMoney());
					if(!("").equals(s)){
						mesg= mesg+"[序号]"+im.getNum()+"[上传金额格式有误]</br>";
						continue;
					}
					
					pol.setLendCode(lendCode);
					pol.setCurrentBillday(StaticMethodUtil.parseDay(currentBillday, GlobalConstant.YYYY_MM_DD));
					pol = backInterestCommonDao.getBackInterestObject(pol);
					
					pool.setBackiId(pol.getBackiId());
					pool.setBackDay(new Date());
					pool.setPlatformId(BackMoneyPlat.JZH.value);
					pool.setBackMoneyStatus(BacksmsState.DHXQR.value);
					pool.setLendCode(lendCode);
					pool.preUpdate();
					
					if(GlobalConstant.RETURN_NO.equals(im.getReturnNo())){
						pool.setSuccessMoney(new BigDecimal(im.getBackRealMoney()));
						pool.setFailMoney(new BigDecimal("0"));
						pool.setBackResult(BackResult.SUCESS.value);
					}else{
						pool.setFailMoney(new BigDecimal(im.getBackRealMoney()));
						pool.setSuccessMoney(new BigDecimal("0"));
						pool.setBackResult(BackResult.FAIL.value);
					}
					pool.setVerState(StaticMethodUtil.getExcuteBackInterestStatus());
					pool.setVerTime(pol.getVerTime());
					pool.setBackMoneyDay(new Date());
					
					Check hi = StaticMethodUtil.madeHistory(userId, userName, lendCode, GlobalConstant.UPD, FortuneLogNode.INTEREST_EXCUTE.getName(), 
							pool.getBackiId(), GlobalConstant.JZH_DHX_RESULT+";"+ GlobalConstant.BACK_RESULT + BackResult.getBackResult(pool.getBackResult()) 
								+ ";" + GlobalConstant.SUCCESS_MONEY + (pol.getBackRealMoney().subtract(pool.getFailMoney())) + ";" + GlobalConstant.FAIL_MONEY+ pool.getFailMoney() + ";" 
									+ GlobalConstant.UPDATE_ST + BacksmsState.getBacksmsState(BacksmsState.DHXQR.value));
					mesg += backInterestCommonService.doUpload(pool, hi);
					
				}catch (ArrayIndexOutOfBoundsException e){
					
					logger.error("待回息列表金账户上传唯一识别码有误,角标越界", e);
					e.printStackTrace();
					mesg += "[序号]"+im.getNum() +"[的备注有误]</br>"+e.getMessage();
				} catch(Exception e){
					
					logger.error("待回息列表金账户上传失败", e);
					mesg +="[序号]"+im.getNum()+"上传失败</br>"+e.getMessage();
					backInterestCommonService.addExceptionMesgs(pool.getBackiId(), e, "[回息编号]"+pool.getBackiId()+"[上传金账户回盘结果失败]", FortuneLogNode.INTEREST_EXCUTE.getCode());
					e.printStackTrace();
				}
			}else{
				mesg += "[待回息列表上传金账户回盘结果数据中存在备注为空的数据]序号:"+ im.getNum()+"</br>";
			}
		}
		return mesg;
	}
	
	/**
	 * 网银上传
	 * 2016年1月18日
	 * by 李志伟
	 * @param list
	 */
	public String netBankUpload(List<NetBankUpload> list,MultipartHttpServletRequest file){
		
		BackInterestPool pool = new BackInterestPool();
		String lendCode = "";// 出借编号
		String currentBillday = null;// 账单日期
		String mesg = "";
		BackInterestPool pol = new BackInterestPool();// 回息信息
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		for (NetBankUpload im : list) {
			
			boolean wy = UploadFileCheck.wyUploadFileDataCheck(im);
			if(wy){
				continue;
			}
			if(null != im.getBackiId() && !("").equals(im.getBackiId())){
				
				try {
					String str = StaticMethodUtil.parttenMethod(im.getBackRealMoney());
					if(StringUtils.isNotEmpty(str)){
						mesg = mesg+"[出借编号]"+im.getLendCode()+"[上传金额有误]</br>";
						continue;
					}
					lendCode = im.getBackiId().split("回息")[0];
					currentBillday = im.getBackiId().split("回息")[1];
					if(BackMoneyPlat.WY.value.equals(file.getParameter("platformId"))){
						lendCode = im.getLendCode();
						currentBillday = im.getApplyBillday();
					}
					pol.setLendCode(lendCode);
					pol.setCurrentBillday(StaticMethodUtil.parseDay(currentBillday, GlobalConstant.YYYY_MM_DD));
					pol = backInterestCommonDao.getBackInterestObject(pol);
					pool.setBackiId(pol.getBackiId());
					pool.setLendCode(lendCode);
					pool.setBackMoneyStatus(BacksmsState.DHXQR.value);
					pool.setBackDay(new Date());
					pool.setPlatformId(BackMoneyPlat.WY.value);
					pool.setSuccessMoney(new BigDecimal(im.getBackRealMoney()));
					pool.setFailMoney(new BigDecimal(BigDecimal.ZERO.doubleValue()));
					pool.setBackResult(BackResult.SUCESS.value);
					//pool.setBackMoneyDay(new Date());
					pool.setBackMoneyDay(im.getLoanDate());
					pool.preUpdate();
					pool.setVerState(StaticMethodUtil.getExcuteBackInterestStatus());
					pool.setVerTime(pol.getVerTime());
					
					Check hi = StaticMethodUtil.madeHistory(userId, userName, lendCode, GlobalConstant.UPD, FortuneLogNode.INTEREST_EXCUTE.getName(), 
							pool.getBackiId(), GlobalConstant.WY_DHX_RESULT + ";" + GlobalConstant.BACK_RESULT+BackResult.getBackResult(pool.getBackResult()) + ";"
									+ GlobalConstant.SUCCESS_MONEY+(pol.getBackRealMoney().subtract(pool.getFailMoney())) + ";" + GlobalConstant.FAIL_MONEY+ pool.getFailMoney() + ";" 
										+ GlobalConstant.UPDATE_ST + BacksmsState.getBacksmsState(BacksmsState.DHXQR.value));
	 				
					mesg += backInterestCommonService.doUpload(pool, hi);
	 				
				} catch (ArrayIndexOutOfBoundsException e){
					
					logger.error("待回息列表金账户上传唯一识别码有误,角标越界", e);
					e.printStackTrace();
					mesg += "[出借编号]"+im.getLendCode() +"[的备注有误]</br>"+e.getMessage();
				} catch (Exception e) {
					
					this.logger.error("待回息列表金账户上传回盘结果失败",e);
					e.printStackTrace();
					mesg += "[出借编号]"+im.getLendCode()+"[上传失败]</br>"+e.getMessage();
					backInterestCommonService.addExceptionMesgs(pool.getBackiId(), e, "[回息编号]"+pool.getBackiId()+"[上传金账户回盘结果失败]", FortuneLogNode.INTEREST_EXCUTE.getCode());
				}
			}else{
				mesg += "[出借编号]"+im.getLendCode() +"[的备注为空]</br>";
			}
		}
		return mesg;
	}
	
	/**
	 * 中金上传
	 * 2016年2月17日
	 * by 李志伟
	 * @param list
	 */
	public String chinaFinanceUpload(List<ChinaFinanceUpload> list){

		BackInterestPool pool = new BackInterestPool();
		String lendCode = "";// 出借编号
		String currentBillday = "";// 账单日期
		BackInterestPool pol = new BackInterestPool();// 原回息信息
		String mesg = "";
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		for (ChinaFinanceUpload im : list) {
			
			boolean zj = UploadFileCheck.zjUploadFileDataCheck(im);
			if(zj){
				continue;
			}
			if(null != im.getBackiId() && !im.getBackiId().equals("")){
			
				try {
					lendCode = im.getBackiId().split("回息")[0];
					currentBillday = im.getBackiId().split("回息")[1];
					pol.setLendCode(lendCode);
					pol.setCurrentBillday(StaticMethodUtil.parseDay(currentBillday, GlobalConstant.YYYY_MM_DD));
					pol = backInterestCommonDao.getBackInterestObject(pol);
					if(StringUtils.isNotBlank(im.getBackRealMoney())){
						String s = StaticMethodUtil.parttenMethod(im.getBackRealMoney().replace(",", ""));
						if(!s.equals("")){
							mesg += "[序号]"+im.getNo()+":"+s;
							continue;
						}
					}else{
						mesg += "[序号]"+im.getNo()+":上传的结算金额格式错误或者数据为空<br>";
						continue;
					}
					pool.setBackiId(pol.getBackiId());
					
					if(im.getTradingStatus().equals(GlobalConstant.PAYSUCCESS)){// 代付成功
						
						pool.setSuccessMoney(new BigDecimal(im.getBackRealMoney().replace(",", "")));
						pool.setBackResult(BackResult.SUCESS.value);
						pool.setFailMoney(BigDecimal.ZERO);
					}else if(im.getTradingStatus().equals(GlobalConstant.PROCESSING)
							|| GlobalConstant.CHULIZHONG.equals(im.getTradingStatus())){// 正在处理
						
						pool.setBackResult(BackResult.DELLING.value);
					}else if(im.getTradingStatus().equals(GlobalConstant.PAYFAIL)){// 代付失败
						
						pool.setSuccessMoney(BigDecimal.ZERO);
						pool.setFailMoney(new BigDecimal(im.getBackRealMoney().replace(",", "")));
						pool.setBackResult(BackResult.FAIL.value);
						pool.setBackReason(im.getBankMessage());
					}else{
						mesg = mesg+"[备注]"+lendCode+"[不支持此回盘结果,只支持成功、失败、处理中结果]</br>";
						continue;
					}
					pool.setLendCode(lendCode);
					pool.setBackMoneyStatus(BacksmsState.DHXQR.value);
					pool.setBackDay(StaticMethodUtil.parseDay(im.getBankPayTime(), GlobalConstant.yyyy_mm_dd));
					pool.setPlatformId(BackMoneyPlat.ZJPT.value);
					pool.preUpdate();
					pool.setBackMoneyDay(new Date());
					pool.setVerState(StaticMethodUtil.getExcuteBackInterestStatus());
					pool.setVerTime(pol.getVerTime());
					
					Check hi = StaticMethodUtil.madeHistory(userId, userName, lendCode, GlobalConstant.UPD, FortuneLogNode.INTEREST_EXCUTE.getName(), 
							pool.getBackiId(), GlobalConstant.ZJ_DHX_RESULT+ ";" + GlobalConstant.BACK_RESULT + BackResult.getBackResult(pool.getBackResult()) + ";" 
									+ GlobalConstant.SUCCESS_MONEY + (pol.getBackRealMoney().subtract(pool.getFailMoney())) + ";" + GlobalConstant.FAIL_MONEY + pool.getFailMoney() + ";" 
										+ GlobalConstant.UPDATE_ST + BacksmsState.getBacksmsState(BacksmsState.DHXQR.value));
					
					mesg += backInterestCommonService.doUpload(pool, hi);
				} catch (ArrayIndexOutOfBoundsException e){
					
					logger.error("待回息列表中金上传备注有误,角标越界", e);
					e.printStackTrace();
					mesg += "[序号]"+im.getNo() +"[的备注有误]</br>"+e.getMessage();
				} catch (Exception e) {
					
					e.printStackTrace();
					this.logger.error("待回息列表上传中金回盘结果失败", e);
					mesg += "[序号]"+im.getNo()+"[上传失败]</br>"+e.getMessage();
					backInterestCommonService.addExceptionMesgs(pool.getBackiId(), e, "[回息编号]"+pool.getBackiId()+"[上传中金回盘结果失败]", FortuneLogNode.INTEREST_EXCUTE.getCode());
				}
			}else{
				mesg += "[序号]"+im.getNo()+"[的备注为空]</br>";
			}
		}
		return mesg;
	}
	
	/**
	 * 通联上传
	 * 2016年2月18日
	 * by 李志伟
	 * @param list
	 * @param newList
	 * @param mesg
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
		String s = "";// 循环使用记录值
		String day = "";// 循环使用记录值
		Iterator<CommunicationsUploadFirst> it = list.iterator();
		while(it.hasNext()){
			
			CommunicationsUploadFirst im = it.next();
			try {
				boolean tl = UploadFileCheck.tlUploadFileDataCheck(im);
				if(tl){// 此处扫除上传中的恶意空值
					it.remove();
					continue;
				}
				
				if(null != im.getBackiId() && !("").equals(im.getBackiId())){// 备注为空，不进行任何处理
				
					String str = StaticMethodUtil.parttenMethod(im.getBackRealMoney());
					if(!str.equals("")){// 校验上传金额格式
						mesg += "备注:"+im.getBackiId()+":"+str+"</br>";
						it.remove();
						continue;
					}
					
					s = im.getBackiId().split("回息")[0];// 获取每一个对象出借编号
					day = im.getBackiId().split("回息")[1];// 获取每一个对象的账单日期
					if(x==1){// 每次循环的一个基本点
						lendCode= im.getBackiId().split("回息")[0];
						currentBillday = im.getBackiId().split("回息")[1]; 
						++x;
					}
					if(s.equals(lendCode) && day.equals(currentBillday)){// 为了寻找当前文件中属于同一笔回息的数据，进行整合
						
						date = StaticMethodUtil.parseDay(im.getFinishTime(), GlobalConstant.yyyy_mm_dd);
						if(im.getProcessingState().equals(GlobalConstant.ChuLiChengGong)){// 处理成功
							successMoney=successMoney.add(new BigDecimal(im.getBackRealMoney()));
							if(!state.equals(BackResult.DELLING.value) && !state.equals(BackResult.FAIL.value)
									|| state.equals("")){
								state = BackResult.SUCESS.value;
							}
						}else if(im.getProcessingState().equals(GlobalConstant.ChuLiZhong)){// 处理中
							// 如果拆分中的数据有一条是处理中，那么这一笔都是处理中
							state = BackResult.DELLING.value;
							
						}else if(im.getProcessingState().equals(GlobalConstant.ChuLiShiBai)){// 处理失败
							
							failMoney=failMoney.add(new BigDecimal(im.getBackRealMoney()));
							backReason = im.getBackReason();
							if(!state.equals(BackResult.DELLING.value) || state.equals("")){
								state = BackResult.FAIL.value;
							}
						}else{
							
							mesg = mesg+"[文件序号]"+im.getFileNo()+"[不支持此回盘结果,只支持成功、失败、处理中结果]</br>";
							it.remove();
							continue;
						}
						it.remove();
					}
				}else{
					mesg += "[文件序号]"+im.getFileNo()+"[备注为空]</br>";
					it.remove();
					continue;
				}
			}catch (ArrayIndexOutOfBoundsException e){
				
				logger.error("待回息列表通联上传备注有误,角标越界", e);
				e.printStackTrace();
				mesg += "[文件序号]"+im.getFileNo() +"[的备注有误]</br>"+e.getMessage();
				it.remove();
			
			} catch(Exception e){
				logger.error("待回息列表上传通联回盘结果失败", e);
				e.printStackTrace();
				mesg = mesg+"[文件序号:]"+im.getFileNo()+"[上传失败]</br>";
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
		bip.setSuccessMoney(successMoney);
		bip.setFailMoney(failMoney);
 		bip.setBackDay(date);
		bip.setBackMoneyStatus(BacksmsState.DHXQR.value);
		bip.setVerTime(pol.getVerTime());
		bip.setVerState(StaticMethodUtil.getExcuteBackInterestStatus());
		bip.setBackMoneyDay(new Date());
		bip.setBackRealMoney(pol.getBackRealMoney());
		bip.setBackResult(state);
		bip.setBackReason(backReason);
		
		newList.add(bip);
		
		if(list.size()>0){
			mesg = communicationsUploadFirst(list, newList, mesg);
		}else{
			mesg = uploadMoney(mesg, newList);
		}
		return mesg;
	}
	
	/**
	 * 上传通联回盘结果
	 * 2016年4月20日
	 * by 李志伟
	 * @param mesg
	 * @param newList
	 * @return
	 */
	public String uploadMoney(String mesg, List<BackInterestPool> newList){
	
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
 		for (BackInterestPool bip : newList) {
 			
 			bip.preUpdate();
 			Check ch = StaticMethodUtil.madeHistory(userId, userName, bip.getLendCode(), GlobalConstant.UPD, FortuneLogNode.INTEREST_EXCUTE.getName(), 
 					bip.getBackiId(), GlobalConstant.TL_DHX_RESULT + ";" + GlobalConstant.BACK_RESULT + BackResult.getBackResult(bip.getBackResult()) + ";" 
 							+ GlobalConstant.SUCCESS_MONEY + (bip.getBackRealMoney().subtract(bip.getFailMoney())) + ";" + GlobalConstant.FAIL_MONEY+bip.getFailMoney()+ ";" 
 								+ GlobalConstant.UPDATE_ST + BacksmsState.getBacksmsState(BacksmsState.DHXQR.value));
			try {
				mesg += backInterestCommonService.doUpload(bip, ch);
			} catch (Exception e) {
				
				mesg += "[备注:]"+bip.getLendCode()+"上传失败</br>"+e.getMessage();
				e.printStackTrace();
				logger.error("待回息列表通联上传回盘结果失败", e);
				backInterestCommonService.addExceptionMesgs(bip.getBackiId(), e, "[回息编号]"+bip.getBackiId()+"[上传通联回盘结果失败]", FortuneLogNode.INTEREST_EXCUTE.getCode());
			}
		}
		return mesg;
	}
	
	/**
	 * 批量执行回息
	 * 2016年1月20日
	 * by 李志伟
	 * @param bip
	 * @param so
	 */
	public String bacthExcete(BackInterestPool bip, SearchObject so) {
		
		String errorMesg = "";
		List<BackInterestPool> backiIdAndLendCodeList = new ArrayList<BackInterestPool>();
		Map<String, Object> map = new HashMap<String, Object>();
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getExcuteBackInterestStatus());
		if(bip.getCheckExaminetype().equals(YoN.SHI.value)){
			if(StringUtils.isNotEmpty(bip.getPlatformId())){
				String[] bankMesg = bip.getPlatformId().split("_");
				if(bankMesg.length == 2){
					bip.setPlatformId(bankMesg[0]);
					bip.setBackBank(bankMesg[1]);
				}else{
					bip.setPlatformId(bankMesg[0]);
				}
			}else{
				errorMesg = errorMesg+"没有选择中间人信息</br>";
				return errorMesg;
			}
		}
		backiIdAndLendCodeList = backInterestCommonDao.findBackiIdAndLendCode(map);
		BackInterestLog bil = StaticMethodUtil.madeApproval(bip, BacksmsState.DHXTH.value, BacksmsState.DHXQR.value);
		
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		
		for (BackInterestPool bbi : backiIdAndLendCodeList) {
			
			// 排他
			bbi.setVerState(StaticMethodUtil.getExcuteBackInterestStatus());
			// 退回重放标志
			if(bip.getCheckExaminetype().equals(YoN.FOU.value)){
				bbi.setRebackFlag(YoN.SHI.value);
			}
			// 生成历史留痕
			Check ch = StaticMethodUtil.madeHistory(userId, userName, bbi.getLendCode(), bil.getType(), FortuneLogNode.INTEREST_EXCUTE.getName(), bbi.getBackiId(), GlobalConstant.DHX_LIST+""+GlobalConstant.BRANCH+""+bil.getType()+";"+
					GlobalConstant.UPDATE_ST + BacksmsState.getBacksmsState(bil.getDictBackiStatus()));
			
			bil.setBackiId(bbi.getBackiId());
			bil.setId(IdGen.uuid());
			bbi.setBackMoneyStatus(bil.getDictBackiStatus());
			bbi.setReturnReason(bil.getCheckExamine());
			bbi.setPlatformId(bip.getPlatformId());
			bbi.setBackBank(bip.getBackBank());
			bbi.setBackMoneyDay(new Date());
			bbi.preUpdate();
			try {
				errorMesg += backInterestCommonService.doSubmit(bbi, bil, ch);
			
			} catch (Exception e) {
				errorMesg += "【出借编号】："+bbi.getLendCode()+"批量操作失败:<br/>"+e.getMessage();
				e.printStackTrace();
				logger.error("批量执行回息失败:"+e.getMessage(), e);
				backInterestCommonService.addExceptionMesgs(bbi.getBackiId(), e, "[回息编号]"+bbi.getBackiId()+"[批量执行回息失败]", FortuneLogNode.INTEREST_EXCUTE.getCode());
				
			}
		}
		if(errorMesg.equals("")){
			return "true";
		}
		return errorMesg;
	}
	
	/**
	 * 金账户线上回息__入口
	 * 2016年4月11日
	 * by 李志伟
	 * @param goldBackInterstList
	 * @return
	 */
	public String goldAccountBackInterest(List<LineGoldBackInterestObj> goldBackInterstList){

		if(goldBackInterstList != null && goldBackInterstList.size() > 0){
			
			String str = "";
			CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
			int callCount = goldBackInterstList.size();
			final String userId = UserUtils.getUserId();
			final String userName = UserUtils.getUser(userId).getName();
			for(final LineGoldBackInterestObj lo : goldBackInterstList) {
				
				try {
					completionService.submit(new Callable<String>() {

						public String call(){
							
							// 单条处理结果，勿进行多条处理结果拼接
							String mesg = "";
							try {
								
								mesg = onLineGoldAccountBackInterest.sendDataForEach(lo, userId, userName);
							} catch (Exception e) {
								
								logger.error("线上金账户回息失败", e);
								backInterestCommonService.addExceptionMesgs(lo.getBackiId(), e, "线上金账户回息失败", FortuneLogNode.INTEREST_EXCUTE.getCode());
								return "[出借编号]"+lo.getLendCode()+"[线上回息失败]</br>"+e.getMessage();
							}
							return mesg;
						}
					});
					
				} catch (Exception e) {
					logger.error("线上金账户回息失败", e);
					backInterestCommonService.addExceptionMesgs(lo.getBackiId(), e, "线上金账户回息失败", FortuneLogNode.INTEREST_EXCUTE.getCode());
					return "[出借编号]"+lo.getLendCode()+"[线上回息失败]</br>"+e.getMessage();
				}			
			}
			
			// 组装返回结果
			for(int i =0; i< callCount; i++){
				try{
					Future<String> future = completionService.take();
					str += future.get();
				}catch(Exception e){
					logger.error("completionService.take()失败",e);
				}				
			}
			
			if(StringUtil.isEmpty(str)){
				return "true";
			}else{
				return str;
			}
		}else{
			return "没有可进行金账户线上回息的数据·······";
		}
	}
	
	/**
	 * (非金账户)线上回息_入口
	 * 2016年2月15日
	 * by 李志伟
	 * @param view
	 * @param list
	 * @return
	 */
	public String noTranscationBackInterest(List<LineBackInterestObj> list, BackInterestPool view){
		
		String errorMessage = "";
		CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
		int callCount = list.size();
		final String userId = UserUtils.getUserId();
		final String userName = UserUtils.getUser(userId).getName();
		
		for (final LineBackInterestObj eb : list) {
			
			eb.setPlatformId(view.getPlatformId());
			try {
			
				completionService.submit(new Callable<String>() {

					public String call(){

						// 每条处理的返回结果
						String mesg = "";
						
						try {
							mesg = onLineNoTranscationBackInterestManager.noTranscationBackInterest(eb, userId, userName);
							
						}catch (Exception e) {
							logger.error("更新回息结果出错;出借编号为：" + eb.getLendCode(), e);
							backInterestCommonService.addExceptionMesgs(eb.getBackiId(), e, "线上非金账户回息失败", FortuneLogNode.INTEREST_EXCUTE.getCode());
							return  "[出借编号]" + eb.getLendCode() + "的回息调用回息接口异常。<br/>";
						}
						return mesg;
					}
					
				});
			} catch (Exception e) {
				
				logger.error(BackInterestPlat.getBackInterestPlat(eb.getPlatformId())+"线上回息发送失败;出借编号为：" + eb.getLendCode(), e);
				backInterestCommonService.addExceptionMesgs(eb.getBackiId(), e, "线上非金账户回息失败", FortuneLogNode.INTEREST_EXCUTE.getCode());
				errorMessage += "[出借编号]" + eb.getLendCode() + "的回息调用回息接口异常。<br/>";
			}
					
		}
		
		// 组装返回结果
		for(int i =0; i< callCount; i++){
			try{
				Future<String> future = completionService.take();
				errorMessage += future.get();
			}catch(Exception e){
				logger.error("completionService.take()失败",e);
			}				
		}
		return errorMessage;
	}
	
	/**
	 * 上传卡联回盘结果
	 * 2016年8月3日
	 * by Mr
	 * @param list
	 * @return
	 */
	public String uploadKaLianResult(List<Object> list) {
		return "";
	}
}