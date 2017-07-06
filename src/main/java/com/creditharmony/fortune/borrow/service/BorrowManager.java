package com.creditharmony.fortune.borrow.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.bean.in.djrcreditor.DjrSendCreditorFortuneInBean;
import com.creditharmony.adapter.bean.in.djrcreditor.DjrSendCreditorFortuneSubInBean;
import com.creditharmony.adapter.bean.out.djrcreditor.DjrSendCreditorFortuneOutBean;
import com.creditharmony.adapter.bean.out.djrcreditor.DjrSendCreditorOutBean;
import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.adapter.constant.ServiceType;
import com.creditharmony.adapter.core.client.ClientPoxy;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.claim.dto.SyncClaim;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.CreditState;
import com.creditharmony.core.fortune.type.CreditTradestate;
import com.creditharmony.core.fortune.type.LoanDistinguish;
import com.creditharmony.core.fortune.type.Node;
import com.creditharmony.core.fortune.type.PushBorrowStatus;
import com.creditharmony.core.fortune.type.ScrapState;
import com.creditharmony.core.fortune.type.ZjtrMark;
import com.creditharmony.core.loan.type.LoanType;
import com.creditharmony.core.loan.type.ProfType;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.borrow.dao.BorrowDao;
import com.creditharmony.fortune.borrow.dao.BorrowMonthDao;
import com.creditharmony.fortune.borrow.dao.BorrowMonthableDao;
import com.creditharmony.fortune.borrow.dao.LoanphaseDao;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.borrow.entity.BorrowMonth;
import com.creditharmony.fortune.borrow.entity.BorrowMonthable;
import com.creditharmony.fortune.borrow.entity.BorrowPush;
import com.creditharmony.fortune.borrow.entity.Loanphase;
import com.creditharmony.fortune.borrow.entity.ex.BorrowLookEx;
import com.creditharmony.fortune.borrow.utils.FormatUtils;
import com.creditharmony.fortune.borrow.utils.ListOrderByUtil;
import com.creditharmony.fortune.borrow.utils.ReckonUtils;
import com.creditharmony.fortune.borrow.view.BorrowAllotView;
import com.creditharmony.fortune.borrow.view.BorrowView;
import com.creditharmony.fortune.constants.BorrowConstant;
import com.creditharmony.fortune.creditor.matching.utils.BigDecimalUtil;
import com.creditharmony.fortune.template.entity.BorrowImportExcel;


/**
 * 可用债权
 * @Class Name BorrowManager
 * @author 周俊
 * @Create In 2015年12月3日
 */
@Service
public class BorrowManager extends CoreManager<BorrowDao,Borrow>{
 
  @Autowired
  private BorrowDao borrowDao;
  @Autowired
  private LoanphaseDao loanphaseDao;
  @Autowired
  private BorrowMonthDao borrowMonthDao;
  @Autowired
  private BorrowMonthableDao borrowMonthableDao;
 
  
  /**
   * 
   * @param map
   * @param date
   * @param batchNo
   * @throws Exception
   */
  @Transactional(value = "fortuneTransactionManager", readOnly = false)
  public Map<String, Object>  updateBorrowAddAndIsUpdate(Map<String, Object> map) throws Exception {
	  Map<String, Object> returnMap = new HashMap<String, Object>();
	  List<Borrow> findPushBorrowReleases = borrowDao.findPushBorrowRelease(map);
	  //释放成功条数
	  int successCount = 0;
	  //释放成功金额
	  BigDecimal successMoney = BigDecimal.ZERO;
	  if(!findPushBorrowReleases.isEmpty()){
		  for(Borrow findPushBorrowRelease:findPushBorrowReleases){
			  Borrow borrow = borrowDao.get(findPushBorrowRelease.getCreditValueId());
			  if(isReleaseOverQuota(borrow.getLoanQuota(),borrow.getLoanCreditValue(),findPushBorrowRelease.getLoanCreditValue())){ 
				  logger.info("债权释放时,推送释放["+borrow.getBorrowPushId()+"]释放后可用债权价值>原始债权价值,所以不予释放.债权["+borrow.getCreditValueId()+"]"); 
				  //String errorStr = "<借款ID:"+borrow.getLoanCode()+",借款人:"+borrow.getLoanName()+"的释放(释放金额:"+findPushBorrowRelease.getLoanCreditValue()+").释放后可用债权价值>原始债权价值,不予释放.>"; 
				  continue; 
				  }
			  map.put("pushBorrowId", findPushBorrowRelease.getBorrowPushId());
			  borrowDao.updateBorrowAdd(map);
			  borrowDao.updatePushBorrowAddIsUpdate(map);
			  successCount= successCount+1;
			  successMoney = 	successMoney.add(findPushBorrowRelease.getLoanCreditValue()); 
			  map.remove("pushBorrowId");
		  }
		  
	  }
	  returnMap.put("loan_credit_value", successMoney);
	  returnMap.put("count", successCount);
	  return returnMap;
  }
  
  /** 
	  * 判断释放以后 可用债权价值 是否 大于 原始债权价值 
	  * gaoxu  2017-1-4 13:53:40
	  * @param loanQuota 
	  * @param loanCreditValue 
	  * @param releaseCreditValue 
	  * @return 
	  */ 
	  private boolean isReleaseOverQuota(BigDecimal loanQuota,BigDecimal loanCreditValue,BigDecimal releaseCreditValue){ 
	  return loanCreditValue.add(releaseCreditValue).compareTo(loanQuota) > 0 ; 
	  }
	  
  
  /**
   * 
   * @param map
   * @param date
   * @param batchNo
   * @throws Exception
   */
  @Transactional(value = "fortuneTransactionManager", readOnly = false)
  public void insertAndUpdate(Map<String, Object> map, Date date, String batchNo) throws Exception {
	  logger.info("insertAndUpdate 插入债权发送记录表参数Date"+DateUtils.date2Str(date, "yyyy-MM-dd HH:mm:ss"));
	  this.insertBorrowPushMap(map);
	  logger.info("insertAndUpdate 插入债权发送记录表成功");
	  //预先减去推送大金融债权价值
	  int updateBorrowMoneySubduction = this.updateBorrowMoney(date, batchNo,"subduction","0",PushBorrowStatus.NEW.value);
	  logger.info("insertAndUpdate 插入债权发送记录表更新减钱"+updateBorrowMoneySubduction+"条数");
	  //更新债权记录表已进减钱记录标记   时间  更新批次   更新推送状态   更新推送状态条件   更新减钱状态    更新减钱状态条件
	  int updateBorrowPushIsUpdate =this.updateBorrowPushStatusOrIsUpdate(date, batchNo,"",PushBorrowStatus.NEW.value,"1","0");
	  logger.info("insertAndUpdate insertAndUpdate 插入债权发送记录表更新减钱标记减钱"+updateBorrowPushIsUpdate+"条数");
  }
  
  /**
   	 * 
	 * 2016-12-24 16:46:09
	 * By 高旭
	 * @param  债权map
	 */
	public void insertBorrowPushMap(Map<String,Object> map) {
		  borrowDao.insertBorrowPushMap(map);
	}
	/**
	 * 根据债权条件 查询可用债权
	 * 2016-12-24 16:46:09
	 * By 高旭
	 * @param  债权map
	 */
	public List<Borrow> findBorrowByDjrByMap(Map<String,Object> map) {
		return borrowDao.findBorrowByDjrByMap(map);
	}
	/**
	 * 根据债权条件 查询可用债权
	 * 2016-12-24 16:46:09
	 * By 高旭
	 * @param  债权map
	 */
	public List<Borrow> findBorrowByDjr(Map<String,Object> map) {
		return borrowDao.findBorrowByDjr(map);
	}
	
  
  /**
 	 * 查总金额总条数 IsNull
 	 * 2016-12-24 14:54:04
 	 * By gaoxu
 	 * @param map
 	 * @return
 	 */
   public Map<String, Object> getBorrowMoneyAndCountContractNoIsNull(Map<String, Object> map) {
 		return borrowDao.getBorrowMoneyAndCountContractNoIsNull(map);
   }
 	
  
  
  /**
 	 * 查总金额总条数 IsNotNull
 	 * 2016-12-24 14:54:04
 	 * By gaoxu
 	 * @param map
 	 * @return
 	 */
   public Map<String, Object> getBorrowMoneyAndCountContractNoIsNotNull(Map<String, Object> map) {
 		return borrowDao.getBorrowMoneyAndCountContractNoIsNotNull(map);
   }
   /**
    * 查总金额总条数 IsNotNull
    * 2016-12-24 14:54:04
    * By gaoxu
    * @param map
    * @return
    */
   public Map<String, Object> getBorrowPushMoneyAndCountIs(Map<String, Object> map) {
	   return borrowDao.getBorrowPushMoneyAndCountIs(map);
   }
 	
  /**
	 * 查总金额总条数
	 * 2016-12-24 14:54:04
	 * By gaoxu
	 * @param map
	 * @return
	 */
  public Map<String, Object> getBorrowMoneyAndCount(Map<String, Object> map) {
		return borrowDao.getBorrowMoneyAndCount(map);
  }
  /**
   * 查总金额总条数
   * 2016-12-24 14:54:04
   * By gaoxu
   * @param map
   * @return
   */
  public Map<String, Object> getBorrowPushMoneyAndCountNot(Map<String, Object> map) {
	  return borrowDao.getBorrowPushMoneyAndCountNot(map);
  }
  /**
   * 查总金额总条数
   * 2016-12-24 14:54:04
   * By gaoxu
   * @param map
   * @return
   */
  public Map<String, Object> getBorrowPushMoneyAndCount(Map<String, Object> map) {
	  return borrowDao.getBorrowPushMoneyAndCount(map);
  }
	
	
  /**
	 * 查询总金额数
	 * 2016-12-16 10:52:05
	 * By gaoxu
	 * @param map
	 * @return
	 */
	public BigDecimal findAllMoneyOneByOne(BorrowView borrowView){
		Map<String, Object> map = objectToMap(borrowView);
		BigDecimal allMoney = borrowDao.findAllMoneyOneByOne(map);
		return allMoney;
	}
 
  /**推送大金融 和更新债权记录
   * 高旭   2016-12-26 15:09:00
   * @param creditorFortunes
   * @param date
   * @param batchNo
   * @param insertBorrowPush
   * @throws Exception
   */
  @Transactional(value = "fortuneTransactionManager", readOnly = false)
  public boolean updateAndPushBorrowDjr(JsonMapper jsonMapper,List<DjrSendCreditorFortuneSubInBean> creditorFortunes, Date date, String batchNo
		,List<String> borrowPushs) throws Exception {
	Map<String, Object> updateBorrowPushBatchNoMap = new HashMap<String, Object>();
	updateBorrowPushBatchNoMap.put("batchNo", batchNo);
	updateBorrowPushBatchNoMap.put("borrowPushs", borrowPushs);
	int updateBorrowPushBatchNo = borrowDao.updateBorrowPushBatchNo(updateBorrowPushBatchNoMap);
	logger.info("更新推送记录表更新批次号"+updateBorrowPushBatchNo+"条数据，本次批次号："+batchNo);
	//向大金融推送债权
	DjrSendCreditorFortuneOutBean pushBorrowDjr = pushBorrowDjr(creditorFortunes);
	logger.info("updateAndPushBorrowDjr pushBorrowDjr 推送大金融完毕返回结果"+jsonMapper.toJson(pushBorrowDjr));
	
	//判断传输是否正确  
	if (pushBorrowDjr!=null && ReturnConstant.SUCCESS.equals(pushBorrowDjr.getRetCode())) {
		//更新推送大金融债权记录成功
		int updateBorrowPushStatus = updateBorrowPushStatusOrIsUpdate(date, batchNo,PushBorrowStatus.SUCCESS.value,PushBorrowStatus.NEW.value,"","1");
		logger.info("更新推送成功状态"+updateBorrowPushStatus+"条数据，本次批次号："+batchNo);
		return true;
	}else{
		//更新推送大金融债权记录推送失败
		int updateBorrowPushStatus = updateBorrowPushStatusOrIsUpdate(date, batchNo,PushBorrowStatus.FAIL.value,PushBorrowStatus.NEW.value,"","1");
		if(pushBorrowDjr.getExtension()!=null&&pushBorrowDjr.getExtension().length()>0){
			List<String> list = new ArrayList<String>();
			for(String pushBorrowId:pushBorrowDjr.getExtension().split(",")){
				list.add(pushBorrowId.trim());
			}
			if(list!=null && list.size()>0){
				logger.info("推送大金融回传数据源更新重复推送数据为成功状态开始 ids"+pushBorrowDjr.getExtension());
				Map<String, Object> updateBorrowPushByID = new HashMap<String, Object>();
				updateBorrowPushByID.put("batchNo", batchNo);
				updateBorrowPushByID.put("borrowPushsId", list);
				updateBorrowPushByID.put("status", PushBorrowStatus.SUCCESS.value);
				updateBorrowPushByID.put("isUpdateRequest","1");
				updateBorrowPushByID.put("createTime",date);
				borrowDao.updatePushBorrowStatus(updateBorrowPushByID);
				logger.info("推送大金融回传数据源更新重复推送数据为成功状态结束 ids==="+pushBorrowDjr.getExtension());
			}
			
		}
		logger.info("updateAndPushBorrowDjr updateBorrowPushStatus推送失败  更新推送记录表状态条数："+updateBorrowPushStatus+"条"+"批次号==="+batchNo+"，时间==="+DateUtils.date2Str(date, "yyyy-MM-dd HH:mm:ss"));
		logger.info("本次操作推送大金融调用接口返回值不正确批次号==="+batchNo+"返回详细信息==="+pushBorrowDjr.getParam());
		return false;
	}
  }
  
  

  //向大金融推送债权信息
  public DjrSendCreditorFortuneOutBean pushBorrowDjr(List<DjrSendCreditorFortuneSubInBean> creditorFortunes)  throws Exception{
	//开启发送大金融链接
	ClientPoxy service = new ClientPoxy(ServiceType.Type.DJR_SEND_CREDITOR_FORTUNE_SERVICE);
	logger.info("开启发送大金融链接service==="+service);
	//债权信息
	DjrSendCreditorFortuneInBean inParam = new DjrSendCreditorFortuneInBean();
	//组装推送参数
	inParam.setCreditorFortunes(creditorFortunes);
	logger.info("组装大金融发送对象inParam==="+inParam);
	//发送大金融等待回传结果数值
	DjrSendCreditorFortuneOutBean outParam = (DjrSendCreditorFortuneOutBean) service.callService(inParam);
	logger.info("发送大金融成功返回对象outParam==="+outParam.getRetCode());
	return outParam;
  }

  //更新债权记录标记或者推送状态
  public int updateBorrowPushStatusOrIsUpdate(Date date, String batchNo,String status,String statusRequest,String isUpdate,String isUpdateRequest)  throws Exception{
	  logger.info("updateBorrowPushStatusOrIsUpdate更新债权记录标记或者推送状态，本次批次号==="+batchNo+"，时间==="+DateUtils.date2Str(date, "yyyy-MM-dd HH:mm:ss")+",status==="+status+",statusRequest==="+statusRequest+",isUpdate==="+isUpdate+",isUpdateRequest==="+isUpdateRequest);
	  //
	  Map<String, Object> updateBorrowPushIsUpdate = new HashMap<String, Object>();
	  //操作批次号
	  updateBorrowPushIsUpdate.put("batchNo", batchNo);
	  //更新状态标记推送成功
	  updateBorrowPushIsUpdate.put("status",  status);
	  //更新状态是否更新减去金额标记
	  updateBorrowPushIsUpdate.put("isUpdate", isUpdate);
	  //操作时间
	  updateBorrowPushIsUpdate.put("createTime", date);
	  //新插入债权推送记录表状态
	  //updateBorrowPushIsUpdate.put("statusRequest", statusRequest);
	  //新插入债权推送记录表
	  updateBorrowPushIsUpdate.put("isUpdateRequest", isUpdateRequest);
	  int updateBorrowByBorrowPush = borrowDao.updatePushBorrowStatus(updateBorrowPushIsUpdate);
	  logger.info(" updateBorrowPushStatusOrIsUpdate 更新了债权推送记录"+updateBorrowByBorrowPush+"条数据，本次批次号："+batchNo);
	  return updateBorrowByBorrowPush;
  }
  	 //预先减去推送大金融债权价值
	public int updateBorrowMoney(Date date, String batchNo,String type,String isUpdate,String status) throws Exception {
		if("addition".equals(type)){
			logger.info("updateBorrowMoney推送推送大金融债权价值失败加上债权价值 ，本次批次号==="+batchNo+"，时间==="+DateUtils.date2Str(date, "yyyy-MM-dd HH:mm:ss")+",type==="+type+",isUpdate==="+isUpdate+",status==="+status);
		}else{
			logger.info("updateBorrowMoney预先减去推送大金融债权价值 ，本次批次号："+batchNo+"时间"+DateUtils.date2Str(date, "yyyy-MM-dd HH:mm:ss")+",type==="+type+",isUpdate==="+isUpdate+",status==="+status);
		}
		//更新borrow  可用债权记录    根据批次号和插入时间   债权推送状态（成功标识）  预先减去推送金额
		Map<String, Object> updateBorrowMap = new HashMap<String, Object>();
		//操作批次号
		updateBorrowMap.put("batchNo", batchNo);
		//操作时间
		updateBorrowMap.put("createTime", date);
		//操作人
		updateBorrowMap.put("modifyBy", UserUtils.getUserId());
		//新插入债权推送记录表状态
		updateBorrowMap.put("status", status);
		//预先减债权金额
		updateBorrowMap.put("type", type);	
		//新插入债权推送记录表
		updateBorrowMap.put("isUpdate", isUpdate);
		int updateBorrowByBorrowPush = borrowDao.updateBorrowByBorrowPush(updateBorrowMap);
		logger.info("updateBorrowMoney更新了债权"+updateBorrowByBorrowPush+"条数据，本次批次号："+batchNo);
		return updateBorrowByBorrowPush;
	}
  
  	
  
  /**
   * 组装borrowPush参数
   * @param map
   * @param borrowPush
   * @param date
   * @param batchNo
   * @param borrow
   * gaoxu  2016-12-15 16:24:53
   */
	public void installBorrowPush(String dataTransferId, BorrowPush borrowPush, Date date, String batchNo,
			Borrow borrow,Map<String, Object> map) {
		//id
		borrowPush.setId(dataTransferId);
		//Borrowid
		borrowPush.setBorrowId(borrow.getCreditValueId());
		//借款id
		borrowPush.setLoanCode(borrow.getLoanCode());
		//借款人id
		borrowPush.setLoanId(borrow.getLoanId());
		//借款人姓名
		borrowPush.setLoanName(borrow.getLoanName());
		//债权来源
		borrowPush.setDictLoanType(borrow.getDictLoanType());
		//债权标识
		borrowPush.setLoanTrusteeFlag(borrow.getLoanTrusteeFlag());
		//职业情况
		borrowPush.setLoanJob(borrow.getLoanJob());
		//借款产品
		borrowPush.setLoanProduct(borrow.getLoanProduct());
		//首次还款日
		borrowPush.setLoanBackmoneyFirday(borrow.getLoanBackmoneyFirday());
		//还款日
		borrowPush.setLoanBackmoneyDay(borrow.getLoanBackmoneyDay());
		//截止还款日期
		borrowPush.setLoanBackmoneyLastday(borrow.getLoanBackmoneyLastday());
		//月利率
		borrowPush.setLoanMonthRate(borrow.getLoanMonthRate());
		//借款期数
		borrowPush.setLoanMonths(borrow.getLoanMonths());
		//可用期数
		borrowPush.setLoanMonthsSurplus(borrow.getLoanMonthsSurplus());
		//原始债权价值
		borrowPush.setLoanQuota(new BigDecimal(com.creditharmony.fortune.utils.FormatUtils.getFormatNumber(borrow.getLoanQuota(), "0.00")));
		//推送债权价值
		borrowPush.setLoanCreditValue(new BigDecimal(com.creditharmony.fortune.utils.FormatUtils.getFormatNumber(borrow.getLoanCreditValue(), "0.00")));
		//年预计债权收益
		borrowPush.setLoanValueYear(borrow.getLoanValueYear());
		//债权区分
		borrowPush.setDicLoanDistinguish(borrow.getDicLoanDistinguish());
		//推送日期
		borrowPush.setCreateTime(date);
		//推送系统 /JX,DJR
		borrowPush.setPushPlatForm("DJR");
		//推送人
		borrowPush.setCreateBy(UserUtils.getUserId());
		//批次号
		borrowPush.setBatchNo(batchNo);
		//推送时间
		borrowPush.setPushTime(date);
		//推动到大金融系统成功或者失败   1成功  2失败   新增为0
 		borrowPush.setStatus(PushBorrowStatus.NEW.value);
		//勾选还是批量  1勾选  0批量
 		if(map.get("creditValueIdList")!=null){
 			borrowPush.setOneOrBeath("1");
 		}else{
 			borrowPush.setOneOrBeath("0");
 		}
		//是否更新到  1更新减金额  0未更新 2更新加金额  这个是只大金融推送失败
		borrowPush.setIsUpdate("0");
	}


  	/**
  	 * 组装大金融参数
  	 * @param map
  	 * @param sub
  	 * @param systemDate
  	 * @param borrow
  	 * 2016-12-15 16:25:03
  	 * gaoxu
  	 */
	public void installDjrSend(String dataTransferId, DjrSendCreditorFortuneSubInBean sub, String systemDate,
			Borrow borrow) {
		/** 借款ID 借款人合同编号. */
		sub.setLoanContractNo(borrow.getLoanContractNo());
		/** 姓名 债权人姓名. */
		sub.setLoanName(borrow.getLoanName());
		/** 身份证号 借款人身份证号码. */
		sub.setLoanIdcard(borrow.getLoanIdcard());
		/** 借款用途 借款用途. */
		sub.setLoanPurpose(borrow.getLoanPurpose());
		/** 借款类型. */
		sub.setDictLoanType(borrow.getLoanProduct());
		/** 原始期限(月) 借款期数. */
		sub.setLoanMonths(borrow.getLoanMonths().toString());
		/** 原始借款开始日期 首次还款日(格式：yyyy-MM-dd). */
		sub.setLoanBackmoneyFirday(borrow.getLoanBackmoneyFirdayStr());
		/** 原始借款到期日期 截止还款日(格式：yyyy-MM-dd). */
		sub.setLoanBackmoneyLastday(borrow.getLoanBackmoneyLastdayStr());
		/** 还款方式 等额本息. */
		sub.setBackMoneyType("等额本息");
		/** 还款日. */
		sub.setLoanBackmoneyDay(borrow.getLoanBackmoneyDay().toString());
		/** 还款金额 (格式：保留两位小数). */
		sub.setBackMoney("");
		/** 债权金额(元) 原始债权价值. */
		sub.setLoanQuota(com.creditharmony.fortune.utils.FormatUtils.getFormatNumber(borrow.getLoanQuota(), "0.00"));
		/** 债权月利息(%). */
		sub.setLoanMonthRate(borrow.getLoanMonthRate().toString());
		/** 债权转入金额(元) 可用债权价值. */
		//去页面传参数结果
		sub.setLoanCreditValue(com.creditharmony.fortune.utils.FormatUtils.getFormatNumber(borrow.getLoanCreditValue(), "0.00"));
		/** 债权转入期限(月). */
		sub.setLoanBorrowMonth("");
		/** 债权转出日期. */
		sub.setLoanBorrowDate(systemDate);
		/** 债权人. */
		sub.setBorrowPerson("夏靖");
		/** 唯一标识*/
		sub.setDataTransferId(dataTransferId);
	}
  
  
  
  	/**
	 * 获取债权推送列表
	 * 2016-12-13 11:27:17
	 * By 高旭
	 * @return
	 */
	public Page<Borrow> findPushBorrow(Page<Borrow> page,BorrowView borrowView){
	  Map<String, Object> map = objectToMapByPushBorrow(borrowView);
	  PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
	  pageBounds.setCountBy("borrow_push_id");
    PageList<Borrow> pageList = (PageList<Borrow>)borrowDao.findPushBorrow(map, pageBounds);
    PageUtil.convertPage(pageList, page);
    return page;
	}
  	/**
	 * 获取可用债权列表
	 * 2015年12月2日
	 * By 周俊
	 * @return
	 */
	public Page<Borrow> findBorrow(Page<Borrow> page,BorrowView borrowView){
	  Map<String, Object> map = objectToMap(borrowView);
	  PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
	  pageBounds.setCountBy("credit_value_id");
      PageList<Borrow> pageList = (PageList<Borrow>)borrowDao.findBorrow(map, pageBounds);
      PageUtil.convertPage(pageList, page);
      return page;
	}
	
	/**
	 * 保存上传的债权
	 * 一条出错全部回滚
	 * 
	 * 2016年4月18日
	 * By 周俊
	 * @param list
	 * @return
	 * @throws Exception 
	 */
	@Transactional(value = "fortuneTransactionManager",readOnly=false)
	public void saveBorrowUpload(List<BorrowImportExcel> list) throws Exception{
		Borrow borrow = new Borrow();
		String message = "";
		if(ArrayHelper.isNotEmpty(list)){
			for (BorrowImportExcel borrowImportExcel : list) {
				if (FormatUtils.checkObject(borrowImportExcel)) {
					try {
						if(borrowImportExcel.getLoanQuota().compareTo(borrowImportExcel.getLoanCreditValue()) == -1){
							message = message + "序号"+borrowImportExcel.getIndex()+"可用债权价值错误\r\n";
							continue;
						}
						BeanUtils.copyProperties(borrow, borrowImportExcel);
						borrow.setLoanJob(ProfType.parseByName(borrowImportExcel.getLoanJob()).getCode());
						borrow.setDictLoanType(LoanType.parseByName(borrowImportExcel.getDictLoanType()).getCode());
						String loanTrusteeFlag = borrowImportExcel.getLoanTrusteeFlag();
						if (BorrowConstant.TG.equals(loanTrusteeFlag)) {
							borrow.setLoanTrusteeFlag(ZjtrMark.TG.value);
						}else if(BorrowConstant.FTG.equals(loanTrusteeFlag)){
							borrow.setLoanTrusteeFlag(ZjtrMark.FTG.value);
						}
						Integer loanMonthsSurplus = ReckonUtils.reckonLoanMonthsSurplus(borrowImportExcel.getLoanBackmoneyFirday(),borrowImportExcel.getLoanMonths(),String.valueOf(borrowImportExcel.getLoanBackmoneyDay()));
						borrow.setLoanMonthsSurplus(loanMonthsSurplus);
						borrow.setDicLoanDistinguish(LoanDistinguish.parseByName(borrowImportExcel.getDicLoanDistinguish()).getCode());
						borrow.setCreditValueId(IdGen.uuid());
						borrow.setLoanCode(IdGen.uuid());
						BigDecimal loanValueYear = BigDecimal.valueOf(Math.pow((1 + StringUtils.toDouble(BigDecimal.valueOf(borrowImportExcel.getLoanMonthRate()).divide(BigDecimal.valueOf(100)))), 12) - 1).multiply(BigDecimal.valueOf(100),MathContext.UNLIMITED);
						borrow.setLoanValueYear(loanValueYear);
						borrow.setDictLoanFreeFlag(CreditState.KY.value);
						borrow.preInsert();
						borrowDao.insert(borrow);
					} catch (Exception e) {
						throw new ServiceException("上传中存在不合理的信息,请核对后上传");
					} 
				}
				}
		}
		if(StringUtils.isNotBlank(message)){
			throw new ServiceException(message);
		}
	}
	
	
	/**
	 * 保存上传的债权
	 * 一条出错全部回滚
	 * 
	 * 2016年4月18日
	 * By 周俊
	 * @param list
	 * @return
	 * @throws Exception 
	 */
	@Transactional(value = "fortuneTransactionManager",readOnly=false)
	public Map<String, Object> saveBorrowUploadDistinct(List<BorrowImportExcel> list) throws Exception{
		Borrow borrow = new Borrow();
		Map<String, Object> infoMap = new HashMap<String, Object>();
		BigDecimal num = new BigDecimal(0);//总笔数
	    BigDecimal numValue = new BigDecimal(0);//总金额
		BigDecimal sucNum = new BigDecimal(0);//成功笔数
	    BigDecimal sucValue = new BigDecimal(0);//成功金额
	    List<String> listUsers=new ArrayList<String>();//重复债权信息
		String message = "";
		if(ArrayHelper.isNotEmpty(list)){
			for (BorrowImportExcel borrowImportExcel : list) {
				if (FormatUtils.checkObject(borrowImportExcel)) {
					try {
						if(borrowImportExcel.getLoanQuota().compareTo(borrowImportExcel.getLoanCreditValue()) == -1){
							message = message + "序号"+borrowImportExcel.getIndex()+"可用债权价值错误\r\n";
							continue;
						}
						BeanUtils.copyProperties(borrow, borrowImportExcel);
						borrow.setLoanJob(ProfType.parseByName(borrowImportExcel.getLoanJob()).getCode());
						borrow.setDictLoanType(LoanType.parseByName(borrowImportExcel.getDictLoanType()).getCode());
						String loanTrusteeFlag = borrowImportExcel.getLoanTrusteeFlag();
						if (BorrowConstant.TG.equals(loanTrusteeFlag)) {
							borrow.setLoanTrusteeFlag(ZjtrMark.TG.value);
						}else if(BorrowConstant.FTG.equals(loanTrusteeFlag)){
							borrow.setLoanTrusteeFlag(ZjtrMark.FTG.value);
						}
						Integer loanMonthsSurplus = ReckonUtils.reckonLoanMonthsSurplus(borrowImportExcel.getLoanBackmoneyFirday(),borrowImportExcel.getLoanMonths(),String.valueOf(borrowImportExcel.getLoanBackmoneyDay()));
						borrow.setLoanMonthsSurplus(loanMonthsSurplus);
						borrow.setDicLoanDistinguish(LoanDistinguish.parseByName(borrowImportExcel.getDicLoanDistinguish()).getCode());
						borrow.setCreditValueId(IdGen.uuid());
						borrow.setLoanCode(IdGen.uuid());
						BigDecimal loanValueYear = BigDecimal.valueOf(Math.pow((1 + StringUtils.toDouble(BigDecimal.valueOf(borrowImportExcel.getLoanMonthRate()).divide(BigDecimal.valueOf(100)))), 12) - 1).multiply(BigDecimal.valueOf(100),MathContext.UNLIMITED);
						borrow.setLoanValueYear(loanValueYear);
						borrow.setDictLoanFreeFlag(CreditState.KY.value);
						borrow.preInsert();
						//校验是否有重复债权 开始
						Map<String,Object> tempbo = new HashMap<String,Object>();
						tempbo.put("loanName", borrow.getLoanName());//借款人
						tempbo.put("loanIdcard", borrow.getLoanIdcard());//借款人身份证号
						tempbo.put("dictLoanType", borrow.getDictLoanType());//债权来源
						tempbo.put("loanPurpose", borrow.getLoanPurpose());//借款用途
						tempbo.put("loanProduct",borrow.getLoanProduct());//借款产品
						tempbo.put("loanJob", borrow.getLoanJob());//职业情况
						tempbo.put("loanBackmoneyFirday", borrow.getLoanBackmoneyFirday());//首次还款日
						tempbo.put("loanBackmoneyDay", borrow.getLoanBackmoneyDay());//还款日
						tempbo.put("loanMonths", borrow.getLoanMonths());//借款期数
						tempbo.put("loanMonthsSurplus", borrow.getLoanMonthsSurplus());//可用期数
						tempbo.put("loanBackmoneyLastday", borrow.getLoanBackmoneyLastday());//截止还款日期
						tempbo.put("loanMonthRate", borrow.getLoanMonthRate());//月利率
						tempbo.put("loanQuota", borrow.getLoanQuota().setScale(5,BigDecimal.ROUND_HALF_UP));//原始债权价值
						tempbo.put("loanCreditValue", borrow.getLoanCreditValue().setScale(5,BigDecimal.ROUND_HALF_UP));//可用债权价值
						tempbo.put("loanValueYear", borrow.getLoanValueYear().setScale(5,BigDecimal.ROUND_HALF_UP));//年预计债权收益
						tempbo.put("dicLoanDistinguish", borrow.getDicLoanDistinguish());//债权区分
			
						numValue=numValue.add(borrow.getLoanCreditValue().setScale(5,BigDecimal.ROUND_HALF_UP));
						num=num.add(new BigDecimal(1));
						List<Borrow> listBorrow=borrowDao.getsqppByMap(tempbo);
						if(listBorrow.size()>0){
							listUsers.add("姓名："+borrow.getLoanName()+";身份证号："+borrow.getLoanIdcard().substring(0,2)+"************"+borrow.getLoanIdcard().substring(borrow.getLoanIdcard().length()-4, borrow.getLoanIdcard().length())+";有"+listBorrow.size()+"条数据重复上传");
					    //校验是否有重复债权 结束
						}else{
							//同一个身份证号，不同的姓名，也不能上传	add 2016.11.10
							Map<String,Object> tempboOther = new HashMap<String,Object>();
							tempboOther.put("loanName", "");//借款人
							tempboOther.put("loanIdcard", borrow.getLoanIdcard());//借款人身份证号
							tempboOther.put("dictLoanType", borrow.getDictLoanType());//债权来源
							tempboOther.put("loanPurpose", borrow.getLoanPurpose());//借款用途
							tempboOther.put("loanProduct",borrow.getLoanProduct());//借款产品
							tempboOther.put("loanJob", borrow.getLoanJob());//职业情况
							tempboOther.put("loanBackmoneyFirday", borrow.getLoanBackmoneyFirday());//首次还款日
							tempboOther.put("loanBackmoneyDay", borrow.getLoanBackmoneyDay());//还款日
							tempboOther.put("loanMonths", borrow.getLoanMonths());//借款期数
							tempboOther.put("loanMonthsSurplus", borrow.getLoanMonthsSurplus());//可用期数
							tempboOther.put("loanBackmoneyLastday", borrow.getLoanBackmoneyLastday());//截止还款日期
							tempboOther.put("loanMonthRate", borrow.getLoanMonthRate());//月利率
							tempboOther.put("loanQuota", borrow.getLoanQuota().setScale(5,BigDecimal.ROUND_HALF_UP));//原始债权价值
							tempboOther.put("loanCreditValue", borrow.getLoanCreditValue().setScale(5,BigDecimal.ROUND_HALF_UP));//可用债权价值
							tempboOther.put("loanValueYear", borrow.getLoanValueYear().setScale(5,BigDecimal.ROUND_HALF_UP));//年预计债权收益
							tempboOther.put("dicLoanDistinguish", borrow.getDicLoanDistinguish());//债权区分
							List<Borrow> listBorrowOther=borrowDao.getsqppByMap(tempboOther);
							if(listBorrowOther.size()>0){
								listUsers.add("身份证号："+borrow.getLoanIdcard().substring(0,2)+"************"+borrow.getLoanIdcard().substring(borrow.getLoanIdcard().length()-4, borrow.getLoanIdcard().length())+";有"+listBorrowOther.size()+"条数据重复上传");
							}else{
								sucValue=sucValue.add(borrow.getLoanCreditValue().setScale(5,BigDecimal.ROUND_HALF_UP));	
								sucNum=sucNum.add(new BigDecimal(1));
								borrowDao.insert(borrow);
							}
						}
					
						
					} catch (Exception e) {
						throw new ServiceException("上传中存在不合理的信息,请核对后上传");
					} 
				}
				}
		}
		infoMap.put("num", num);
		infoMap.put("numValue", numValue);
		infoMap.put("sucNum", sucNum);
		infoMap.put("sucValue", sucValue);
		infoMap.put("listUsers", listUsers);
		if(StringUtils.isNotBlank(message)){
			throw new ServiceException(message);
		}
//		System.out.println(infoMap);
		    return infoMap;
	}
	
	
  /**
   * 可用债权查看
   * 2016年1月8日
   * By 周俊
   * @param page
   * @param creditCode
   * @return
   */
  public Page<BorrowLookEx> borrowLook(Page<BorrowLookEx> page ,String creditCode){
	  	// 获得首期月还利息
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("creditNode", Node.KYZQ.value);
		map.put("creditCode", creditCode);
		String[] array = {CreditTradestate.ZCWKS.value,CreditTradestate.WKSBGB.value};
		map.put("dictTradeCreditStatus", array);
		map.put("phaseDiscardStatus", ScrapState.BFQ.value);
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		PageList<BorrowLookEx> pageList = (PageList<BorrowLookEx>) borrowDao.borrowLook(map, pageBounds);
		if (ObjectHelper.isEmpty(pageList)) {
			PageUtil.convertPage(pageList, page);
			return page;
		}
		Map<String,Object> firstPhaseMap = new HashMap<String,Object>();
		for (BorrowLookEx borrowLookEx : pageList) {
			// 获取首期月还利息
			//firstPhaseMap.put("loanCode",borrowLookEx.getLoanCode());
			firstPhaseMap.put("loanCode",creditCode);
			firstPhaseMap.put("lendCode",borrowLookEx.getLendCode());
			firstPhaseMap.put("phaseDiscardStatus",ScrapState.BFQ.value );
			Loanphase loanphase = loanphaseDao.findMinPhaseNumberLoanphase(firstPhaseMap);
			if (ObjectHelper.isEmpty(loanphase)) {
				borrowLookEx.setFirstMonthInterest(new BigDecimal(BorrowConstant.NEGATIVE_ONE));
				borrowLookEx.setInitMonths(Integer.valueOf(BorrowConstant.NEGATIVE_ONE));
			}else{
				borrowLookEx.setFirstMonthInterest(loanphase.getPhaseInterestCur());
				borrowLookEx.setInitMonths(loanphase.getPhaseNumberSurplus()+1);
			}
			/*// 匹配时期数
			Integer minNum = loanphase.getPhaseNumber();
			// 当前期数
			Integer maxNum = borrowLookEx.getPhaseNumber();
			if (!ObjectHelper.isEmpty(maxNum) || !ObjectHelper.isEmpty(minNum)) {
				borrowLookEx.setInitMonths(borrowLookEx.getSurplusMonths()+(maxNum-minNum));
			}else{
				borrowLookEx.setInitMonths(BorrowConstant.NEGATIVE_ONE);
			}*/
		}
		PageUtil.convertPage(pageList, page);
		return page;
	}
  	/**
	 * 查询总金额数
	 * 2015年12月3日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public BigDecimal findAllMoneyByPushBorrow(BorrowView borrowView){
		Map<String, Object> map = objectToMapByPushBorrow(borrowView);
		BigDecimal allMoney = borrowDao.findAllMoneyByPushBorrow(map);
		return allMoney;
	}
	
	
	/**
	 * 将对象转成map
	 * 2016-12-13 11:31:04
	 * By 高旭
	 * @param borrowMonthableView
	 * @return
	 */
	public Map<String, Object> objectToMapByPushBorrow(BorrowView borrowView){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("borrowFreeFlag", CreditState.KY.value);// 债权状态
		map.put("borrowerName",borrowView.getBorrowerName());// 借款人
		if (StringHelper.isNotEmpty(borrowView.getBorrowerJob())) {
			
			map.put("borrowerJob",borrowView.getBorrowerJob().split(","));// 借款人职业
		}
		if (!ObjectHelper.isEmpty(borrowView.getBorrowCreditValueFrom())) {
			
			map.put("borrowCreditValueFrom",String.valueOf(borrowView.getBorrowCreditValueFrom()));
		}
		if (!ObjectHelper.isEmpty(borrowView.getBorrowCreditValueTo())) {
			
			map.put("borrowCreditValueTo",String.valueOf(borrowView.getBorrowCreditValueTo()));
		}
		map.put("borrowMonthsSurplusFrom", borrowView.getBorrowMonthsSurplusFrom());// 可用期数
		map.put("borrowMonthsSurplusTo", borrowView.getBorrowMonthsSurplusTo());// 可用期数
		if (!ObjectHelper.isEmpty(borrowView.getBorrowMonthRate())) {
			
			map.put("borrowMonthRate",String.valueOf(borrowView.getBorrowMonthRate()));// 月利率
		
		}
		map.put("borrowBackmoneyFirdayFrom",borrowView.getBorrowBackmoneyFirdayFrom());// 首次还款日
		map.put("borrowBackmoneyFirdayTo",borrowView.getBorrowBackmoneyFirdayTo());// 首次还款日
		if (StringHelper.isNotEmpty(borrowView.getBorrowTrusteeFlag())) {
			map.put("borrowTrusteeFlag",borrowView.getBorrowTrusteeFlag().split(","));// 债权标识
		}
		if (StringHelper.isNotEmpty(borrowView.getBorrowType())) {
			
			map.put("borrowType",borrowView.getBorrowType().split(","));// 借款类型
		}
		if (StringHelper.isNotEmpty(borrowView.getBorrowBakcmoneyDay())) {
			List<Integer> list = new ArrayList<Integer>();
			String[] split = borrowView.getBorrowBakcmoneyDay().split(",");
			for (String string : split) {
				list.add(StringUtils.toInteger(string));
			}
			map.put("borrowBakcmoneyDay",list);// 还款日
		}
		// 债权区分
		if(StringHelper.isNotEmpty(borrowView.getDicLoanDistinguish())){
			map.put("dicLoanDistinguish",borrowView.getDicLoanDistinguish().split(","));
		}
		// 债权推动状态 成功标识
		if(StringHelper.isNotEmpty(borrowView.getPushBorrowStatus())){
			map.put("pushStatus",borrowView.getPushBorrowStatus().split(","));
		}
		
		map.put("pushBorrowTimeFrom",borrowView.getPushBorrowTimeFrom());// 推送日期
		map.put("pushBorrowTimeTo",borrowView.getPushBorrowTimeTo());// 推送日期
		
		map.put("loanBackmoneyLastdayFrom",borrowView.getLoanBackmoneyLastdayFrom());// 最后还款日
		map.put("loanBackmoneyLastdayTo",borrowView.getLoanBackmoneyLastdayTo());// 最后还款日
		return map;
	}
	
  	/**
	 * 查询总金额数
	 * 2015年12月3日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public BigDecimal findAllMoney(BorrowView borrowView){
		Map<String, Object> map = objectToMap(borrowView);
		BigDecimal allMoney = borrowDao.findAllMoney(map);
		return allMoney;
	}
	
  /**
   * 获得单个借款人信息
   * 2016年1月6日
   * By 周俊
   * @param creditValueId
   * @return
   */
  public Borrow getBorrow(String creditValueId){
	  Borrow borrow = borrowDao.get(creditValueId);
	  borrow.setLoanIdcard(FormatUtils.formatLoanIdcard(borrow.getLoanIdcard()));
	  return borrow;
	  
  }
  
	/**
	 * 封装匹配页面的原始数据
	 * 2015年12月2日
	 * By 周俊
	 * @param creditValueId
	 * @return
	 */
	public BorrowAllotView getBorrowAllot(String creditValueId){
		BorrowAllotView borrowAllot = borrowDao.getBorrowAllot(creditValueId);
		return borrowAllot;
	}
	
	/**
	 * 获取匹配符合条件的可用债权
	 * 2015年12月17日
	 * By 柳慧
	 * @param ppMap 匹配条件集合
	 * @return
	 */
	public List<Borrow> getsqppByMap(Map<String, Object> ppMap) {
		return borrowDao.getsqppByMap(ppMap);
	}
	
	/**
	 * 通过实体查询可用债权集合 （分页）
	 * 2015年12月19日
	 * By 柳慧
	 * @param page
	 * @param borrow 可用债权查询条件
	 * @return
	 */
	public Page<Borrow> findBorrowByEx(Page<Borrow> page, Borrow borrow) {
		 Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = borrowToMap(borrow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(borrow.getLoanBackmoneyDays()!=null && borrow.getLoanBackmoneyDays().size()>0){
			map.put("loanBackmoneyDays", borrow.getLoanBackmoneyDays());
		}else{	//add by liusl 2016.11.18 控制错期匹配全关闭的情况，条件不能为空
			List<Integer> loanBackmoneyDaysTmp = new  ArrayList<Integer>();
			loanBackmoneyDaysTmp.add(99);
			map.put("loanBackmoneyDays", loanBackmoneyDaysTmp);
		}
		if(borrow.getPpxy()!=null && borrow.getPpxy().size()>0){
			map.put("ppxy", borrow.getPpxy());
		}
		if(borrow.getLoanJob()!=null && !"".equals(borrow.getLoanJob())){
			map.put("LoanJob", borrow.getLoanJob().split(","));
		}
		if(borrow.getDictLoanType()!=null && !"".equals(borrow.getDictLoanType())){
			map.put("dictLoanTypes", Arrays.asList(borrow.getDictLoanType().split(",")));
		}
		 PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		 pageBounds.setCountBy("credit_value_id");
	     PageList<Borrow> pageList = (PageList<Borrow>)borrowDao.findBorrowByEx(map, pageBounds);
	     PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**通过实体查询可用债权集合 （分页）
	 * 2016年11月16日
	 * By 常亚运
	 * @param page
	 * @param borrow
	 * @param ids
	 * @return
	 */
	public Page<Borrow> findBorrowByEx(Page<Borrow> page, Borrow borrow,String ids) {
		 Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = borrowToMap(borrow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(borrow.getLoanBackmoneyDays()!=null && borrow.getLoanBackmoneyDays().size()>0){
			map.put("loanBackmoneyDays", borrow.getLoanBackmoneyDays());
		}
		if(borrow.getPpxy()!=null && borrow.getPpxy().size()>0){
			map.put("ppxy", borrow.getPpxy());
		}
		if(borrow.getLoanJob()!=null && !"".equals(borrow.getLoanJob())){
			map.put("LoanJob", borrow.getLoanJob().split(","));
		}
		if(borrow.getDictLoanType()!=null && !"".equals(borrow.getDictLoanType())){
			map.put("dictLoanTypes", Arrays.asList(borrow.getDictLoanType().split(",")));
		}
		if (null != ids && !"".equals(ids)) {
			String[] id = ids.split(",");
			map.put("ids", Arrays.asList(id));
		}
		
		 PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		 pageBounds.setCountBy("credit_value_id");
	     PageList<Borrow> pageList = (PageList<Borrow>)borrowDao.findBorrowByEx(map, pageBounds);
	     PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/** 
	 * Borrow 转换成map
	 * 2016年1月14日
	 * By 柳慧
	 * @param borrow
	 * @return
	 */
	private Map<String, Object> borrowToMap(Borrow borrow) throws Exception  {
		if(borrow == null){    
            return null;    
        }   
        Map<String, Object> map = new HashMap<String, Object>();    
        Field[] declaredFields = borrow.getClass().getDeclaredFields();    
        for (Field field : declaredFields) {    
            field.setAccessible(true);  
            map.put(field.getName(), field.get(borrow));  
        }    
        return map;  

	}

	/**
	 * 通过债权ID集合 获取符合条件的可用债权集合
	 * 2015年12月21日
	 * By 柳慧
	 * @param creditValueIds 债权ID集合 
	 */
	public List<Borrow> getBorrowByCreditValueIds(List<String> creditValueIds) {
		return borrowDao.getBorrowByCreditValueIds(creditValueIds);
	}
	
	/**
	 * 根据出借编号获得借款金额
	 * 2015年12月24日
	 * By 周俊
	 * @param creditValueId
	 * @return
	 */
	public BigDecimal getLoanCreditValue(String creditValueId){
		String[] array = creditValueId.split(",");
		BigDecimal loanCreditValue = BigDecimal.ZERO;
		if (ArrayHelper.isNotNull(array)) {
			for (String  creditValueId1: array) {
				Borrow borrow = borrowDao.get(creditValueId1);
				if(borrow!=null){
					loanCreditValue = loanCreditValue.add(borrow.getLoanCreditValue());
					loanCreditValue = new BigDecimal(String.valueOf(BigDecimalUtil.round(loanCreditValue.doubleValue(), 2)));
				}
			}
			
		}
		return loanCreditValue;
	}
	
	/**
	 * 将对象转成map
	 * 2015年12月28日
	 * By 周俊
	 * @param borrowMonthableView
	 * @return
	 */
	public Map<String, Object> objectToMap(BorrowView borrowView){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("borrowFreeFlag", CreditState.KY.value);// 债权状态
		map.put("borrowerName",borrowView.getBorrowerName());// 借款人
		if (StringHelper.isNotEmpty(borrowView.getBorrowerJob())) {
			
			map.put("borrowerJob",borrowView.getBorrowerJob().split(","));// 借款人职业
		}
		if (!ObjectHelper.isEmpty(borrowView.getBorrowCreditValueFrom())) {
			
			map.put("borrowCreditValueFrom",String.valueOf(borrowView.getBorrowCreditValueFrom()));
		}
		if (!ObjectHelper.isEmpty(borrowView.getBorrowCreditValueTo())) {
			
			map.put("borrowCreditValueTo",String.valueOf(borrowView.getBorrowCreditValueTo()));
		}
		map.put("borrowMonthsSurplusFrom", borrowView.getBorrowMonthsSurplusFrom());// 可用期数
		map.put("borrowMonthsSurplusTo", borrowView.getBorrowMonthsSurplusTo());// 可用期数
		if (!ObjectHelper.isEmpty(borrowView.getBorrowMonthRate())) {
			
			map.put("borrowMonthRate",String.valueOf(borrowView.getBorrowMonthRate()));// 月利率
		
		}
		map.put("borrowBackmoneyFirdayFrom",borrowView.getBorrowBackmoneyFirdayFrom());// 首次还款日
		map.put("borrowBackmoneyFirdayTo",borrowView.getBorrowBackmoneyFirdayTo());// 首次还款日
		if (StringHelper.isNotEmpty(borrowView.getBorrowTrusteeFlag())) {
			map.put("borrowTrusteeFlag",borrowView.getBorrowTrusteeFlag().split(","));// 债权标识
		}
		if (StringHelper.isNotEmpty(borrowView.getBorrowType())) {
			
			map.put("borrowType",borrowView.getBorrowType().split(","));// 借款类型
		}
		if (StringHelper.isNotEmpty(borrowView.getBorrowBakcmoneyDay())) {
			List<Integer> list = new ArrayList<Integer>();
			String[] split = borrowView.getBorrowBakcmoneyDay().split(",");
			for (String string : split) {
				list.add(StringUtils.toInteger(string));
			}
			map.put("borrowBakcmoneyDay",list);// 还款日
		}
		// 债权区分
		if(StringHelper.isNotEmpty(borrowView.getDicLoanDistinguish())){
			map.put("dicLoanDistinguish",borrowView.getDicLoanDistinguish().split(","));
		}
		map.put("loanBackmoneyLastdayFrom",borrowView.getLoanBackmoneyLastdayFrom());// 最后还款日
		map.put("loanBackmoneyLastdayTo",borrowView.getLoanBackmoneyLastdayTo());// 最后还款日
		map.put("zqState", borrowView.getZqState());
		return map;
	}

	/**
	 * 更新
	 * 2015年12月25日
	 * By 周俊
	 * @param borrow
	 * @return
	 */
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public int update(Borrow borrow){
		return borrowDao.update(borrow);
	}
	
	/**
	 * 汇金数据提前结清同步
	 * 2016年5月9日
	 * By 周俊
	 * @param loanSync
	 */
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public void executeSyncEarlySettlement(SyncClaim loanSync){
		Borrow borrow = new Borrow(); 
		borrow.setLoanCode(loanSync.getLoanCode());
		borrow.setDictLoanFreeFlag(loanSync.getDictLoanFreeFlag());
		borrow.setLoanFreezeDay(loanSync.getLoanFreezeDay());
		borrow.setLoanModifiedDay(new Date());
		borrow.preUpdate();
		borrowDao.update(borrow);
		BorrowMonth borrowMonth = new BorrowMonth();
		borrowMonth.setLoanCode(loanSync.getLoanCode());
		borrowMonth.setDictLoanFreeFlag(loanSync.getDictLoanFreeFlag());
		borrowMonth.setLoanModifiedDay(new Date());
		borrowMonth.setLoanFreezeDay(loanSync.getLoanFreezeDay());
		borrowMonth.preUpdate();
		borrowMonthDao.update(borrowMonth);
		BorrowMonthable borrowMonthable = new BorrowMonthable();
		borrowMonthable.setLoanCode(loanSync.getLoanCode());
		borrowMonthable.setDictLoanFreeFlag(loanSync.getDictLoanFreeFlag());
		borrowMonthable.setLoanFreezeDay(loanSync.getLoanFreezeDay());
		borrowMonthable.setLoanModifiedDay(new Date());
		borrowMonthable.preUpdate();
		borrowMonthableDao.update(borrowMonthable);
		
		logger.info("提前结清通知债权推动记录表开始loanCode==="+loanSync.getLoanCode());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loanCode", loanSync.getLoanCode());
		map.put("date",new Date());
		borrowDao.updatePushBorrowEarly(map);
		logger.info("提前结清通知债权推动记录表结束loanCode==="+loanSync.getLoanCode());
	}
	
	/**
	 * 计算月满盈或月满盈可用债权回池的数量
	 * 2016年6月27日
	 * By 周俊
	 * @param creditValueId
	 * @return
	 */
	public int countBackTool(String creditValueId){
		return borrowDao.countBackTool(creditValueId);
	}
	
	/**
	 * 金信大金融数据提前结清
	 * 2016年5月9日
	 * By 周俊
	 * @param loanSync
	 */
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public void borrowFreeze(Date loanFreezeDay,String borrowInfo){
		if(StringUtils.isNotBlank(borrowInfo)){
			String[] borrowInfoArray = borrowInfo.split(":");
			if (ArrayHelper.isNotNull(borrowInfoArray)) {
				Borrow borrow = new Borrow(); 
				borrow.setCreditValueId(borrowInfoArray[0]);
				borrow.setDictLoanFreeFlag(CreditState.DJ.value);
				borrow.setLoanFreezeDay(loanFreezeDay);
				borrow.setLoanModifiedDay(new Date());
				borrow.preUpdate();
				borrow.setVerTime(borrowInfoArray[1]);
				borrowDao.update(borrow);
				if (borrowInfoArray.length==4) {
					BorrowMonth borrowMonth = new BorrowMonth();
					borrowMonth.setCreditMonId(borrowInfoArray[2]);
					borrowMonth.setDictLoanFreeFlag(CreditState.DJ.value);
					borrowMonth.setLoanModifiedDay(new Date());
					borrowMonth.setLoanFreezeDay(loanFreezeDay);
					borrowMonth.preUpdate();
					borrowMonth.setVerTime(borrowInfoArray[3]);
					borrowMonthDao.update(borrowMonth);
					BorrowMonthable borrowMonthable = new BorrowMonthable();
					borrowMonthable.setCreditMonId(borrowInfoArray[2]);
					borrowMonthable.setDictLoanFreeFlag(CreditState.DJ.value);
					borrowMonthable.setLoanFreezeDay(loanFreezeDay);
					borrowMonthable.setLoanModifiedDay(new Date());
					borrowMonthable.preUpdate();
					borrowMonthableDao.update(borrowMonthable);
				}
			}
		}
		
	}
	
	
}

