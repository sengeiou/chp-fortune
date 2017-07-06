package com.creditharmony.fortune.back.interest.common.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.back.interest.entity.BackInterestLog;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.DetailsPage;
import com.creditharmony.fortune.back.interest.entity.HistoryFull;
import com.creditharmony.fortune.back.interest.entity.PlatformMsg;
import com.creditharmony.fortune.back.interest.entity.SendMessageEntity;
import com.creditharmony.fortune.back.interest.entity.TelphoneMessage;
import com.creditharmony.fortune.back.interest.view.BackInterestListView;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.entity.Product;
import com.creditharmony.fortune.sms.entity.SmsSendList;

/**
 * 回息通用
 * @Class Name BackInterestCommonDao 
 * @author 李志伟
 * @Create In 2016年2月20日
 */
@FortuneBatisDao
public interface BackReturnInterestCommonDao extends CrudDao<BackInterestPool>{

	/**
	 * 初始化回息列表和搜索列表
	 * 2016年2月20日
	 * by 李志伟
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public PageList<BackInterestListView> loadListAndFind(
			Map<String, Object> map, PageBounds pageBounds);
	
	/**
	 * 查询回息产品
	 * 2016年2月20日
	 * by 李志伟
	 * @param map
	 * @return
	 */
	List<Product> findProductSelect(Map<String, Object> map);
	
	/**
	 * 去历史留痕页面
	 * 2015年12月8日
	 * by 李志伟
	 * @param code
	 * @param pageBounds
	 * @return
	 */
	public List<HistoryFull> toHistory(String code, PageBounds pageBounds);

	/**
	 * 添加审批信息
	 * 2016年1月21日
	 * by 李志伟
	 * @param bil
	 */
	public void addApprovalMesg(BackInterestLog bil);
	
	/**
	 * 计算当前列表中累计回息总额
	 * 2015年11月23日
	 * @return 回息金额
	 */
	public BigDecimal selectSumMoney(Map<String, Object> map);

	/**
	 * 检索第三方平台信息
	 * 2015年12月10日
	 * by 李志伟
	 * @return
	 */
	public List<PlatformMsg> searchThirdPlat();
	
	/**
	 * 获取出借信息
	 * 2016年2月23日
	 * by 李志伟
	 * @param lendCode
	 * @return
	 */
	public LoanApply getLoanApplyMesg(String lendCode);
	
	/**
	 * 更新出借信息(主要是更新出借的完结状态)
	 * 2016年2月23日
	 * by 李志伟
	 * @param applyCode
	 */
	public void updateLoanApplyMesg(LoanApply la);
	
	/**
	 * 获取短信所需要数据
	 * 2016年3月16日
	 * by 李志伟
	 * @param list
	 * @return
	 */
	public List<SmsSendList> getSmsList(List<String> list);
	
	/**
	 * 根据ID获取客户信息
	 * 2016年3月17日
	 * by 李志伟
	 * @param backiId
	 * @return
	 */
	public TelphoneMessage getCustomerMesg(String backiId);
	
	/**
	 * 根据回息Id获取回息信息
	 * 2016年3月17日
	 * by 李志伟
	 * @param string
	 * @return
	 */
	public BackInterestPool getBackInterestMesgById(String str);
	/**
	 * 根据回息Id获取回息信息
	 * 2016年3月17日
	 * by 李志伟
	 * @param string
	 * @return
	 */
	public BackInterestPool getBackInterestMesgByPool(BackInterestPool bmp);
	
	/**
	 * 根据ID获取短信所需内容
	 * 2016年3月18日
	 * by 李志伟
	 * @param backiId
	 * @return
	 */
	public SendMessageEntity getMessage(String backiId);
	
	/**
	 * 根据Id获取回息标识
	 * 2016年3月22日
	 * by 李志伟
	 * @param map
	 * @return
	 */
	public String getPlatFlag(Map<String, Object> map);
	
	/**
	 * 查找回息数据
	 * 2016年4月11日
	 * by 李志伟
	 * @param map
	 * @return
	 */
	public List<BackInterestPool> findBackiIdAndLendCode(Map<String, Object> map);
	
	/**
	 * 提交回息数据
	 * 2016年4月11日
	 * by 李志伟
	 * @param bip
	 */
	public void submit(BackInterestPool bip);
	
	/**
	 * 查找回息详情信息
	 * 2016年4月11日
	 * by 李志伟
	 * @param code
	 * @return
	 */
	public DetailsPage findMesgByCode(String code);
	
	/**
	 * 上传回盘结果
	 * 2016年4月26日
	 * by 李志伟
	 * @param bp
	 */
	public void uploadResult(BackInterestPool bp);
	
	/**
	 * 更新前调用
	 * 2016年5月3日
	 * by 李志伟
	 * @param bp
	 * @return
	 */
	public BackInterestPool forUpdate(BackInterestPool bp);
	
	/**
	 * 线上推送更新数据
	 * 2016年5月4日
	 * by 李志伟
	 * @param bmp
	 */
	public void updateBackResult(BackInterestPool bmp);
	
	/**
	 * 获取回款信息
	 * 2016年5月16日
	 * by 李志伟
	 * @param lendCode
	 * @return
	 */
	public BackMoneyPool getBackMoneyMesg(String lendCode);
	
	/**
	 * 查询回盘结果为处理中的数据量
	 * 2016年4月1日
	 * by 李志伟
	 * @param map
	 * @return
	 */
	public int findBackResult(Map<String, Object> map);

	/**
	 * 上传前调用
	 * 2016年5月3日
	 * by 李志伟
	 * @param bp
	 * @return
	 */
	public BackInterestPool uploadForUpdate(BackInterestPool bp);
	
	/**
	 * 发送短信
	 * 2016年5月3日
	 * by 李志伟
	 * @param se
	 */
	public void sendMessage(SendMessageEntity se);
	
	/**
	 * 获取回息对象
	 * 2016年7月12日
	 * by Mr
	 * @param pol
	 * @return
	 */
	public BackInterestPool getBackInterestObject(BackInterestPool pol);
	
	/**
	 * 查询存在提前赎回，且已回款完成的数据的出借编号
	 * 2017年2月27日
	 * 郭强
	 * @param so
	 * @return
	 */
	public List<String> searchAheadBackids(Map<String, Object> map);
	public List<String> searchAheadBackidsFrom(Map<String, Object> map);

	/**
	 * 获取最大回款日期 
	 * 2017年3月6日
	 * 郭强
	 * @param map
	 * @return
	 */
	public Date getMaxBackMoneyDay(Map<String, Object> map);
	/**
	 * 获取数据不同回款日期的天数
	 * 2017年3月6日
	 * 郭强
	 * @param map
	 * @return
	 */
	public int getDiffBackMoneyDays(Map<String, Object> map);
	
	/**
	 * 上传是否回息
	 * 2017-3-24 13:19:55
	 * 	 * by 高旭
	 * @param pool
	 */
	public void uploadIsInterest(BackInterestPool pool);
	
	/**
	 * 冻结到期回息数据  回款审批通过
	 * 2017-3-24 13:19:55
	 * 	 * by 高旭
	 * @param pool
	 */
	public void updateFrozenInterest(BackInterestPool pool);
	
	/**gaoxu
	 * 计算到期后回息总金额
	 * @return 回息金额
	 */
	public Map<String, Object> selectReturnBackSumMoney(Map<String, Object> map);
}