package com.creditharmony.fortune.deduct.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.adapter.bean.in.thirdpay.ProtocolLibraryInfo;
import com.creditharmony.core.moneyaccount.entity.MoneyAccountInfo;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.deduct.common.DeductExportExcel;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.entity.ext.AttachmentEx;
import com.creditharmony.fortune.deduct.entity.ext.BaseExportModel;
import com.creditharmony.fortune.deduct.entity.ext.CreditorTradeEx;
import com.creditharmony.fortune.deduct.entity.ext.DeductPoolEx;
import com.creditharmony.fortune.deduct.entity.ext.TemplateExportModelEx;
import com.creditharmony.fortune.template.entity.DeductFailExportModel;
import com.creditharmony.fortune.template.entity.DeductFailProtocolExportModel;
import com.creditharmony.fortune.template.entity.DeductPoolExportModel;
import com.creditharmony.fortune.template.entity.DeductSuccessExportGoldModel;
import com.creditharmony.fortune.template.entity.DeductSuccessExportModel;
import com.creditharmony.fortune.template.entity.DeductSuccessPayExportModel;
import com.creditharmony.fortune.template.entity.GoldAccounExportModel;

/**
 * 划扣申请dao
 * @Class Name DeductApplyDao
 * @author 韩龙
 * @Create In 2015年11月27日
 */
@FortuneBatisDao
public interface DeductApplyDao extends CrudDao<DeductPool> {

	/**
	 * 带条件分页查询
	 * 2015年12月28日
	 * By 韩龙
	 * @param entity
	 * @param pageBounds
	 * @return
	 */
	public List<DeductPoolEx> findList(DeductPoolEx entity, PageBounds pageBounds);
	
	/**
	 * 带条件分页查询
	 * 2015年12月28日
	 * By 韩龙
	 * @param pageBounds
	 * @param map
	 * @return
	 */
	//public List<DeductPoolEx> findList(PageBounds pageBounds, Map<String,Object> map);

	/**
	 * 带条件查询
	 * 2015年11月27日
	 * By 韩龙
	 * @param entity
	 * @return
	 */
	public List<DeductPoolEx> findList(DeductPoolEx entity);
	
	/**
	 * 根据code出借编号查询出附件表
	 * 		相应的债权文件信息
	 * 2015年11月27日
	 * By 韩龙
	 * @param map
	 * @return
	 */
	public AttachmentEx getAttachmentEx(Map<String,Object> map);
	
	/**
	 * 根据出借编号检索办理详细信息
	 * 2015年11月27日
	 * By 韩龙
	 * @param map
	 * @return
	 */
	public DeductPoolEx getDeductPoolExt(Map<String,Object> map);
	
	/**
	 * 根据出借编号检索债权推荐列表
	 * 2015年11月27日
	 * By 韩龙
	 * @param applyCode
	 * @return
	 */
	public List<CreditorTradeEx> getListOfClaims(String applyCode);
	
	/**
	 * 根据债权id检索可用债权表
	 * 2015年11月27日
	 * By 韩龙
	 * @param id
	 * @return
	 */
	public CreditorTradeEx getBorrow(String id);
	
	/**
	 * 根据债权id检索月满盈可用债权池
	 * 2015年11月27日
	 * By 韩龙
	 * @param id
	 * @return
	 */
	public CreditorTradeEx getBorrowMonthable(String id);
	
	/**
	 * 获取划扣申请对象
	 * 2015年12月8日
	 * By 韩龙
	 * @param deductPool
	 * @return
	 */
	public DeductPool getDeductPool(DeductPool deductPool);
	
	/**
	 * 获取附件文件列表
	 * 2015年12月9日
	 * By 韩龙
	 * @param map
	 * @return
	 */
	public List<Attachment> getAttachment(Map<String,Object> map);
	
	/**
	 * 线下划扣-->第三方导出划扣
	 * 2015年12月17日
	 * By 韩龙
	 * @param map
	 * @return
	 */
	public List<TemplateExportModelEx> getTemplateExportModel(Map<String,Object> map);
	
	/**
	 * 线下划扣-->第三方导出划扣
	 * 2015年12月17日
	 * By 韩龙
	 * @param map
	 * @return
	 */
	public List<DeductExportExcel> getDeductExportExcel(Map<String,Object> map);
	
	/**
	 * 线下划扣-->协议库对接
	 * 2015年12月22日
	 * By 韩龙
	 * @param filter
	 * @return
	 */
	public List<ProtocolLibraryInfo> getProtocolLibraryInfo(Map<String,Object> filter);
	
	/**
	 * 划扣失败-->协议库导出
	 * 2015年12月22日
	 * By 韩龙
	 * @param filter
	 * @return
	 */
	public List<DeductFailProtocolExportModel> getDeductFailProtocolExportModel(Map<String,Object> filter);
	
	/**
	 * 划扣失败-->划扣失败列表
	 * 2015年12月23日
	 * By 韩龙
	 * @param filter
	 * @return
	 */
	public List<DeductFailExportModel> getDeductFailExportModel(Map<String,Object> filter);
	
	/**
	 * 已划扣-->导出回访表
	 * 2015年12月24日
	 * By 韩龙
	 * @param filter
	 * @return
	 */
	public List<DeductSuccessPayExportModel> getDeductSuccessPayExportModel(Map<String,Object> filter);

	/**
	 * 获取债权收益
	 * 2016年1月5日
	 * By 韩龙
	 * @param map
	 * @return getDeductSumMoney
	 */
	public Map<String, Object> getLoanphase(Map<String,Object> map);
	
	/**
	 * 当前条件下的出借金额与划扣金额
	 * 2016年2月15日
	 * By 韩龙
	 * @param deductPoolEx
	 * @return
	 */
	public Map<String,String> getDeductSumMoney(DeductPoolEx deductPoolEx);

	/**
	 * 导出金帐户
	 * 2016年2月22日
	 * By 韩龙
	 * @param deductPoolExt
	 * @return
	 */
	public List<GoldAccounExportModel> getGoldAccounExportModel(
			DeductPoolEx deductPoolExt);

	/**
	 * 线上划扣
	 * 2016年2月25日
	 * By 韩龙
	 * @param map
	 * @return
	 */
	public BaseExportModel getBaseExportModel(Map<String, Object> map);

	/**
	 * 获取收款确认书
	 * 2016年2月27日
	 * By 韩龙
	 * @param lendCode
	 * @return
	 */
	public Map<String, Object> getByLendCode(Map<String, Object> map);

	/**
	 * 获取金帐户信息
	 * 2016年2月29日
	 * By 韩龙
	 * @param map
	 * @return
	 */
	public MoneyAccountInfo getMoneyAccountInfo(Map<String, Object> map);

	/**
	 * 已出借列表excel导出
	 * 2016年3月8日
	 * By 韩龙
	 * @param filter
	 * @return
	 */
	public List<DeductSuccessExportModel> getDeductSuccessExportModel(
			Map<String, Object> filter);

	/**
	 * 划扣审批
	 * 2016年3月8日
	 * By 韩龙
	 * @param deductPoolExt
	 * @return
	 */
	public List<DeductPoolExportModel> getDeductPoolExportModel(
			DeductPoolEx deductPoolExt);

	/**
	 * 已出借列表金帐户导出
	 * 2016年3月14日
	 * By 韩龙
	 * @param filter
	 * @return
	 */
	public List<DeductSuccessExportGoldModel> getDeductSuccessExportGoldModel(
			Map<String, Object> filter);

	/**
	 * 更据出借编号获取客户编号
	 * 2016年4月8日
	 * By 郭才林
	 * @param applyCode
	 * @return
	 */
	public LoanApply getCustCodeByApplyCode(String applyCode);
	
	/**
	 * 查询加锁
	 * 2016年4月28日
	 * By 韩龙
	 * @param deductPool
	 * @return
	 */
	public DeductPool getForUpdate(DeductPool deductPool);
}