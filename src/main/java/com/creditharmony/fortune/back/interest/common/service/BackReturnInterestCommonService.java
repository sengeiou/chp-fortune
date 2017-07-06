package com.creditharmony.fortune.back.interest.common.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.bpm.utils.SpringUtil;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.common.type.BackResult;
import com.creditharmony.core.common.type.SmsState;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.BacksmsState;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.fortune.type.SmsRemindType;
import com.creditharmony.core.fortune.type.SmsType;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.fortune.back.interest.applyConfrim.dao.BackInterestApplyConfrimDao;
import com.creditharmony.fortune.back.interest.applyConfrim.dao.BackReturnInterestApplyConfrimDao;
import com.creditharmony.fortune.back.interest.common.dao.BackReturnInterestCommonDao;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.interest.entity.BackInterestLog;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.DetailsPage;
import com.creditharmony.fortune.back.interest.entity.HistoryFull;
import com.creditharmony.fortune.back.interest.entity.PlatformMsg;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.back.interest.entity.SendMessageEntity;
import com.creditharmony.fortune.back.interest.entity.TelphoneMessage;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.back.interest.view.BackInterestListView;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.common.dao.CheckDao;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.entity.Product;
import com.creditharmony.fortune.sms.dao.SmsDao;
import com.creditharmony.fortune.sms.entity.SmsTemplate;

/**
 * 回息通用
 * @Class Name BackInterestCommonService 
 * @author 李志伟
 * @Create In 2016年2月20日
 */
@Service
public class BackReturnInterestCommonService{
	
	@Autowired
	private BackReturnInterestCommonDao backReturnInterestCommonDao;
	@Autowired
	private CheckDao checkDao;
	@Autowired
	private BackReturnInterestApplyConfrimDao backReturnInterestApplyConfrimDao;
	@Autowired
	private SmsDao smsDao;
	
	/**
	 * 回息申请确认列表初始化
	 * 2016年1月21日
	 * by 李志伟
	 * @param page
	 * @param map
	 * @return
	 */
	public Page<BackInterestListView> loadListAndFind(Page<BackInterestListView> page, Map<String, Object> map){
		
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		pageBounds.setCountBy("backi_id");
		PageList<BackInterestListView> pageList  = (PageList<BackInterestListView>) backReturnInterestCommonDao.loadListAndFind(map, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 初始化出借产品下拉框
	 * 2016年2月20日
	 * by 李志伟
	 * @return
	 */
	public List<Product> findProductSelect(){
		
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("backIn", GlobalConstant.BACKIN);
		List<Product> list = backReturnInterestCommonDao.findProductSelect(map);*/
		List<Product> list = StaticMethodUtil.getProductList();
		Product xhb = new Product();
		xhb.setProductCode(ProductCode.XHT.value);
		xhb.setProductName("信和通");
		list.add(xhb);
		return list;
	}
	
	/**
	 * 去历史留痕页面
	 * 2015年12月8日
	 * by 李志伟
	 * @param code
	 * @return
	 */
	public Page<HistoryFull> toHistory(Page<HistoryFull> page, String code) {
		
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		PageList<HistoryFull> pageList = (PageList<HistoryFull>)backReturnInterestCommonDao.toHistory(code, pageBounds);
		PageUtil.convertPage(pageList, page);
		
		return page;
	}
	
	 /**
	  * 根据条件计算列表中的金额
	  * 2016年2月1日
	  * by 李志伟
	  * @param map
	  * @return
	  */
	 public String selectSumMoney(Map<String,Object> map){
		 
		 BigDecimal money = backReturnInterestCommonDao.selectSumMoney(map);
		 return money.toString();
	 }
	 
	/**
	 * 查询第三方中间人信息
	 * 2015年12月10日
	 * by 李志伟
	 * @return
	 */
	public List<PlatformMsg> searchThirdPlat() {
		
		List<PlatformMsg> list = backReturnInterestCommonDao.searchThirdPlat();
		return list;
	}

	/**
	 * 获取出借信息
	 * 2016年2月23日
	 * by 李志伟
	 * @param lendCode
	 * @return
	 */
	public LoanApply getLoanApplyMesg(String lendCode) {
		
		LoanApply la = backReturnInterestCommonDao.getLoanApplyMesg(lendCode);
		return la;
	}
	
	/**
	 * 根据iD获取客户信息
	 * 2016年3月17日
	 * by 李志伟
	 * @param backiId
	 * @return
	 */
	public TelphoneMessage getCustomerMesg(String backiId) {
		TelphoneMessage tm = backReturnInterestCommonDao.getCustomerMesg(backiId);
		return tm;
	}
	
	/**
	 * 获取短信所需要内容
	 * 2016年3月18日
	 * by 李志伟
	 * @param backiId
	 * @return
	 */
	public SendMessageEntity getMessage(String backiId){
		SendMessageEntity sms = new SendMessageEntity();
		sms = backReturnInterestCommonDao.getMessage(backiId);
		return sms;
	}
	
	
	/**
	 * 获取最近回息成功的平台
	 * 2016年3月22日
	 * by 李志伟
	 * @param backiId
	 * @return
	 */
	public String getPlatFlag(String backiId) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state_f", BacksmsState.YHX.value);
		map.put("backiId", backiId);
		String s = backReturnInterestCommonDao.getPlatFlag(map);
		return s;
	}
	
	/**
	 * 查找回息详情
	 * 2016年4月9日
	 * by 李志伟
	 * @param code
	 * @return
	 */
	public DetailsPage findMesgByCode(String code){
		DetailsPage det = backReturnInterestCommonDao.findMesgByCode(code);
		return det;
	}
	
	/**
	 * 批量单条操作回息共通方法
	 * 更新回息池，添加审批信息，全程留痕
	 * 2016年4月18日
	 * By 朱杰
	 * @param bp
	 * @param bl
	 * @param ch
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String doSubmit(BackInterestPool bp, BackInterestLog bl, Check ch){
		BackInterestPool boo = backReturnInterestCommonDao.forUpdate(bp);
		if(null == boo){// 已经被他人操作过
			return bp.getLendCode()+":已被其他人操作过<br/>";
		}
		backReturnInterestCommonDao.submit(bp);
		backReturnInterestCommonDao.addApprovalMesg(bl);
		checkDao.insertSelective(ch);
		
		/*OutputStream os = new FileOutputStream("smc.txt", true);
		Writer wi = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(wi);
		bw.write("|回息ID|-"+bp.getBackiId()+"-|审批日志ID|-"+bl.getId()+"-|历史留痕ID|-"+ch.getId());
		bw.newLine();
		bw.flush();
		
		bw.close();
		wi.close();
		os.close();
		*/
		return "";
	}
	
	/**
	 * 线上回息更新数据库
	 * 2016年6月11日
	 * by 李志伟
	 * @param bp
	 * @param bl
	 * @param ch
	 * @return
	 */
	public String lineSubmit(BackInterestPool bp, Check ch){
		backReturnInterestCommonDao.submit(bp);
		checkDao.insertSelective(ch);
		return "";
	}
	
	/**
	 * 上传回盘结果
	 * 2016年4月26日
	 * by 李志伟
	 * @param bp
	 * @param bl
	 * @param ch
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String doUpload(BackInterestPool bp, Check ch){
		BackInterestPool forU = backReturnInterestCommonDao.uploadForUpdate(bp);
		if(null == forU){
			return bp.getLendCode()+":已被其他人操作过<br/>";
		}
		backReturnInterestCommonDao.uploadResult(bp);
		checkDao.insertSelective(ch);
		return "";
	}
	
	/**
	 * 单选金额
	 * 2016年4月18日
	 * by 李志伟
	 * @param so
	 * @return
	 */
	public String sumMoney(SearchObject so) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String[] str = so.getBackiId().split(",");
		map.put("str", str);
		map.put("so", so);
		String s = selectSumMoney(map);
		return s;
	}
	
	/**
	 * 上传回息金额
	 * 2016年4月9日
	 * by 李志伟
	 * @param pool
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String uploadBackInterestMoney(BackInterestPool pool, Check  hf) {
		
		BackInterestPool fu = backReturnInterestCommonDao.forUpdate(pool);
		if(null == fu){// 已经被他人操作过
			return "[出借编号]"+pool.getLendCode()+",[序列号]"+pool.getBackiId()+":此列表无此数据或已被其他人操作过<br/>";
		}
		
		backReturnInterestApplyConfrimDao.uploadMoney(pool);
		checkDao.insertSelective(hf);
		return "";
	}
	
	
	/**
	 * 上传是否回息
	 *2017-3-24 13:18:17
	 * by 高旭
	 * @param pool
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String uploadBackInterestIsInterest(BackInterestPool pool, Check  hf) {
		
		BackInterestPool fu = backReturnInterestCommonDao.forUpdate(pool);
		if(null == fu){// 已经被他人操作过
			return "[出借编号]"+pool.getLendCode()+":此列表无此数据或已被其他人操作过<br/>";
		}
		
		backReturnInterestCommonDao.uploadIsInterest(pool);
		checkDao.insertSelective(hf);
		return "";
	}
	
	/**
	 * 回息审批提交---带短信
	 * 2016年4月28日
	 * by 李志伟
	 * @param bbi
	 * @param bil
	 * @param ch
	 * @param tel
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String doSubmit(BackInterestPool bbi, BackInterestLog bil, Check ch,
			TelphoneMessage tel) {
		
		BackInterestPool fu = backReturnInterestCommonDao.forUpdate(bbi);
		if(null == fu){// 已经被他人操作过
			return bbi.getLendCode()+":已被其他人操作过<br/>";
		}
		
		backReturnInterestCommonDao.submit(bbi);
		backReturnInterestCommonDao.addApprovalMesg(bil);
		checkDao.insertSelective(ch);
		
		BigDecimal bd = new BigDecimal(tel.getBackRealMoney()).setScale(2, BigDecimal.ROUND_HALF_UP);
		String s = bd.toString();
		if(tel.getProductCode().equals(ProductCode.YXT.value)){// 月息通发送短信
			
			SmsTemplate smsTemplate = smsDao.getSmsTemplate(SmsType.YXT_SYHK.value);
			String templateContent = smsTemplate.getTemplateContent();
			templateContent = templateContent.replace("{#Name#}", tel.getCustomerName())
			.replace("{#custom_text_4#}", s);
			
			SendMessageEntity ssl = getMessage(tel.getBackiId());
			ssl.setId(IdGen.uuid());
			ssl.setSmsId(SmsType.YXT_SYHK.value);
			ssl.setOnlyFlag(IdGen.uuid().substring(0, 17));
			ssl.setSmsMsg(templateContent);
			ssl.setSendStatus(SmsState.DFS.value);
			ssl.setDictBackStatus(BacksmsState.DHX.value);
			ssl.setAccountBankName(OpenBank.getOpenBank(ssl.getAccountBank()));
			ssl.setDictRemindType(SmsRemindType.HXDXTQ.value);
			ssl.setDictDeductStatus(LendState.HKCG.value);
			if(StringUtils.isEmpty(ssl.getCity())){
				ssl.setCity(ssl.getFyCity());
			}
			ssl.setApplyDay(new Date());
			ssl.setPushDay(new Date());
			ssl.preInsert();
			ssl.preUpdate();
			backReturnInterestCommonDao.sendMessage(ssl);
			
		}else if(tel.getProductCode().equals(ProductCode.XHBA.value) ||
				tel.getProductCode().equals(ProductCode.XHBC.value) || 
				tel.getProductCode().equals(ProductCode.XHB.value)
				){// 信和宝类型产品发送短信
			
			SmsTemplate smsTemplate = smsDao.getSmsTemplate(SmsType.XHB_SYTX.value);
			String templateContent = smsTemplate.getTemplateContent();
			
			templateContent = templateContent.replace("{#Name#}", tel.getCustomerName())
					.replace("{#custom_text_6#}", DateUtils.date2Str(tel.getApplyLendDay(), GlobalConstant.YYYY_MM_DD)).replace("{#custom_text_4#}", s);
			
			SendMessageEntity ssl = getMessage(tel.getBackiId());
			ssl.setId(IdGen.uuid());
			ssl.setSmsId(SmsType.XHB_SYTX.value);
			ssl.setOnlyFlag(IdGen.uuid().substring(0, 17));
			ssl.setSmsMsg(templateContent);
			ssl.setSendStatus(SmsState.DFS.value);
			ssl.setDictBackStatus(BacksmsState.DHX.value);
			ssl.setAccountBankName(OpenBank.getOpenBank(ssl.getAccountBank()));
			ssl.setDictRemindType(SmsRemindType.HXDXTQ.value);
			if(StringUtils.isEmpty(ssl.getCity())){
				ssl.setCity(ssl.getFyCity());
			}
			ssl.setDictDeductStatus(LendState.HKCG.value);
			ssl.setApplyDay(new Date());
			ssl.setPushDay(new Date());
			ssl.preInsert();
			ssl.preUpdate();
			backReturnInterestCommonDao.sendMessage(ssl);
			
		} else if(tel.getProductCode().equals(ProductCode.XHYZ.value)){// 信和月增
			
			SmsTemplate smsTemplate = smsDao.getSmsTemplate(SmsType.XHYZ_FXTX.value);
			String templateContent = smsTemplate.getTemplateContent();
			templateContent = templateContent.replace("{#Name#}", tel.getCustomerName())
					.replace("{#custom_text_4#}", s);
			
			SendMessageEntity ssl = getMessage(tel.getBackiId());
			ssl.setId(IdGen.uuid());
			ssl.setSmsId(SmsType.XHYZ_FXTX.value);
			ssl.setOnlyFlag(IdGen.uuid().substring(0, 17));
			ssl.setSmsMsg(templateContent);
			ssl.setSendStatus(SmsState.DFS.value);
			ssl.setDictBackStatus(BacksmsState.DHX.value);
			ssl.setAccountBankName(OpenBank.getOpenBank(ssl.getAccountBank()));
			ssl.setDictRemindType(SmsRemindType.HXDXTQ.value);
			if(StringUtils.isEmpty(ssl.getCity())){
				ssl.setCity(ssl.getFyCity());
			}
			ssl.setDictDeductStatus(LendState.HKCG.value);
			ssl.setApplyDay(new Date());
			ssl.setPushDay(new Date());
			ssl.preInsert();
			ssl.preUpdate();
			backReturnInterestCommonDao.sendMessage(ssl);
		}
		return "";
	}
	
	/**
	 * 更新回息信息以及出借信息
	 * 2016年4月29日
	 * by 李志伟
	 * @param bbi
	 * @param bil
	 * @param ch
	 * @param loan
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String doSubmit(BackInterestPool bbi, BackInterestLog bil, Check ch,
			LoanApply loan) {
		
		BackInterestPool fu = backReturnInterestCommonDao.forUpdate(bbi);
		if(null == fu){// 已经被他人操作过
			return bbi.getLendCode()+":已被其他人操作过<br/>";
		}
		backReturnInterestCommonDao.submit(bbi);
		backReturnInterestCommonDao.addApprovalMesg(bil);
		backReturnInterestCommonDao.updateLoanApplyMesg(loan);
		checkDao.insertSelective(ch);
		return "";
	}
	
	/**
	 * 添加异常信息
	 * 2016年5月6日
	 * by 李志伟
	 * @param backiId 回息ID
	 * @param e	异常对象	
	 * @param memo	信息备注
	 * @param node	模块节点
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void addExceptionMesgs(String backiId, Exception e,
			String errorMesg, String node) {
		
		FortuneException forException = new FortuneException();
		forException.setLoanCode(backiId);
		forException.setMessage(e.getMessage());
		forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
		forException.setNode(node);
		forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
		forException.setRemark(errorMesg);
		forException.preInsert();
		FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
		forDao.insert(forException);
	}
	
	/**
	 * 添加异常信息(需要自己手动执行创建者和创建时间)
	 * 2016年6月10日
	 * by 李志伟
	 * @param backiId
	 * @param e
	 * @param errorMesg
	 * @param node
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void addExceptionMesg(String backiId, Exception e,
			String errorMesg, String node) {
		
		FortuneException forException = new FortuneException();
		forException.setLoanCode(backiId);
		forException.setMessage(e.getMessage());
		forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
		forException.setNode(node);
		forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
		forException.setRemark(errorMesg);
		forException.preInsert();
		forException.setCreateBy("MQ");
		forException.setCreateTime(new Date());
		FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
		forDao.insert(forException);
	}

	/**
	 * 获取回款信息
	 * 2016年5月16日
	 * by 李志伟
	 * @param lendCode
	 * @return
	 */
	public BackMoneyPool getBackMoneyMesg(String lendCode) {

		BackMoneyPool money = backReturnInterestCommonDao.getBackMoneyMesg(lendCode);
		return money;
	}
	
	/**
	 * 查找回盘结果为待回息的有几条
	 * 2016年4月1日
	 * by 李志伟
	 * @param so
	 * @return
	 */
	public int findBackResult(BackInterestPool bip, SearchObject so) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		StaticMethodUtil.spiltUtils(so, map);
		map.put("so",so);
		map.put("processing", BackResult.DELLING.value);
		if(bip.getBackiId() != null && !bip.getBackiId().equals("")){
			List<String> codes = Arrays.asList(so.getBackiId().split(","));
			map.put("codes", codes);
		}
		if(so.getBackMoneyStatus() == null || ("").equals(so.getBackMoneyStatus())){
			List<String> status = new ArrayList<String>();
			status.add(BacksmsState.DHXQR.value);
			map.put("status", status);
		}
		int s = backReturnInterestCommonDao.findBackResult(map);
		return s;
	}
	
	/**
	 * 获取最大回款日期 
	 * 2017年3月6日
	 * 郭强
	 * @param map
	 * @return
	 */
	public Date getMaxBackMoneyDay(SearchObject so) {
		Map<String , Object> map =new HashMap<String, Object>();
		// 设置城市搜索条件
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		StaticMethodUtil.spiltUtils(so, map);
		map.put("status", StaticMethodUtil.getBackInterestConfrimStatus());
		map.put("so", so);
		if (StringUtils.isNotEmpty(so.getBackiId())) {
			map.put("codes", Arrays.asList(so.getBackiId().split(",")));
		}
		
		Date maxBackMoneyDay = backReturnInterestCommonDao.getMaxBackMoneyDay(map);
		so.setAddrCity(city);
		return maxBackMoneyDay;
	}

	/**
	 * 获取数据不同回款日期的天数
	 * 2017年3月6日
	 * 郭强
	 * @param map
	 * @return
	 */
	public int getDiffBackMoneyDays(SearchObject so) {
		Map<String , Object> map =new HashMap<String, Object>();
		// 设置城市搜索条件
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		StaticMethodUtil.spiltUtils(so, map);
		map.put("status", StaticMethodUtil.getBackInterestConfrimStatus());
		map.put("so", so);
		if (StringUtils.isNotEmpty(so.getBackiId())) {
			map.put("codes", Arrays.asList(so.getBackiId().split(",")));
		}
		int days = backReturnInterestCommonDao.getDiffBackMoneyDays(map);
		so.setAddrCity(city);
		return days;
	}
	
	
	/**
	  * 到期后回息总金额
	  * gaoxu
	  * @param map
	  * @return
	  */
	 public Map<String,Object> selectReturnBackSumMoney(Map<String,Object> map){
		 Map<String,Object> returnMap = backReturnInterestCommonDao.selectReturnBackSumMoney(map);
		 return returnMap;
	 }
}