package com.creditharmony.fortune.change.lender.apply.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApply;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeInfo;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeLog;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerEx;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerExSearch;
import com.creditharmony.fortune.change.lender.apply.entity.ext.EmergencyContactEx;
import com.creditharmony.fortune.change.lender.apply.view.LenderInitView;
import com.creditharmony.fortune.change.lender.apply.view.LenderQueryView;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.customer.entity.Address;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.LoanConfiguration;
/**
 * 出借人信息变更dao
 * @Class Name LenderChangeDao
 * @author 郭才林
 * @Create In 2015年12月1日
 */
@FortuneBatisDao
public interface LenderChangeDao extends CrudDao<CustomerEx> {
   
	/**
	 * 获取发起表单数据
	 * 2015年12月2日
	 * By 郭才林
	 * @param customerCode
	 * @return
	 */
	public LenderInitView getLaunchForm(String customerCode);

	/**
	 * 保存申请信息
	 * 2015年12月2日
	 * By 郭才林
	 * @param changInfo
	 */
	public void saveLenderChangeInfo(LenderChangeInfo changInfo);
	
	/**
	 * 获取申请信息
	 * 2015年12月2日
	 * By 郭才林
	 * @param applyId
	 * @return
	 */
	public LenderChangeInfo getChangeInfo(String applyId);
	
	/**
	 * 保存变更记录
	 * 2015年12月2日
	 * By 郭才林
	 * @param changeLog
	 */
	public void saveLenderChangeInfo(LenderChangeLog changeLog);
	
	/**
	 * 保存生审批信息
	 * 2015年12月2日
	 * By 郭才林
	 * @param changeLog
	 */
	public void saveApproveInfo(LenderChangeLog changeLog);
  
	/**
     * 获取出借信息
     * 2015年12月2日
     * By 郭才林
     * @param custCode
     * @return
     */
	public LoanConfiguration getLenderLoanInfo(String custCode);
	
	/**
	 * 获取紧急联系人
	 * 2015年12月2日
	 * By 郭才林
	 * @param cusCode
	 * @return
	 */
	public EmergencyContactEx getLenderEmer(String cusCode);
	
	/**
	 * 获取地址信息
	 * 2015年12月2日
	 * By 郭才林
	 * @param addrId
	 * @return
	 */
	public Address getLenderAddr(String addrId);
	
	/**
	 * 获取出借人历史变更信息
	 * 2015年12月1日
	 * By 郭才林
	 * @param changeInfo
	 * @param pageBounds 
	 * @return
	 */
	
	public List<CustomerEx> getLenderChangeHistory(LenderChangeInfo changeInfo, PageBounds pageBounds);

	/**
	 * 获取出借人变更申请已办
	 * 2015年12月3日
	 * By 郭才林
	 * @param query
	 * @param pageBounds 
	 * @return
	 */
	public List<CustomerEx> getLenderChangeFinish(LenderQueryView query, PageBounds pageBounds);

	/**
	 * 更新客户信息
	 * 2015年12月3日
	 * By 郭才林
	 * @param customerEx
	 */
	public void updateCust(CustomerEx customerEx);

	/**
	 * 获取去单个审批信息
	 * 2015年12月3日
	 * By 郭才林
	 * @param applyId
	 * @return
	 */
	public LenderChangeLog getApproveInfo(LenderChangeLog changeLog);
	
	/**
	 * 查询审批信息列表
	 * 2015年12月3日
	 * By 郭才林
	 * @param applyId
	 * @return
	 */
	public List<LenderChangeLog> getApproveInfoList(LenderChangeLog changeLog);

	/**
	 * 更新紧急联系人
	 * 2015年12月4日
	 * By 郭才林
	 * @param emer
	 */
	public void updateEmer(EmergencyContactEx emer);

	/**
	 * 更改地址信息
	 * 2015年12月7日
	 * By 郭才林
	 * @param cAddr
	 * @return
	 */
	public void updateAddr(Address cAddr);

	/**
	 * 获取客户信息变更申请列表
	 * 2015年12月9日
	 * By 郭才林
	 * @param query
	 * @param pageBounds
	 * @return
	 */
	public PageList<CustomerEx> findList(LenderQueryView query, PageBounds pageBounds);

	/**
	 * 更新变更步骤
	 * 2015年12月10日
	 * By 郭才林
	 * @param changeInfo
	 */
	public void updateChangeState(LenderChangeInfo changeInfo);

	/**
	 * 出借人信息变更查询
	 * 2015年12月11日
	 * By 郭才林
	 * @param query
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public PageList<CustomerExSearch> queryList(LenderQueryView query, PageBounds pageBounds);
	
	/**
	 * 修改变更表（重新发起流程变更出借人信息）
	 * 2015年12月15日
	 * By 刘雄武
	 * @param changeInfo
	 */
	public void updateChangeInfo(LenderChangeInfo changeInfo);
    
	/**
     * 出借信息人查询根据变更ID
     * 2015年12月15日
     * By 刘雄武
     * @param changeId
     * @return
     */
   public LenderChangeInfo getChangeInfoById(String changeId);

   /**
    * 金账户销户 
    * 2016年2月28日
    * By 郭才林
    * @param cusCode
    */
   public void updateTrusteeship(CustomerEx cust);

   /**
    * 判断是否金账户是否可以销户
    * 2016年2月28日
    * By 郭才林
    * @param cusCode
    */
	public String isAccountOff(LendLoanApply apply);

	/**
	 * 更新托管状态
	 * 2016年3月1日
	 * By 郭才林
	 * @param cust
	 */
	public void updateApplyHostedStatus(CustomerEx cust); 
	
    
    /**
     * 出借人信息导出查询
     * 2016年3月1日
     * By 刘雄武
     * @param applyId
     * @return
     */
    public List<CustomerExSearch> queryExport(LenderQueryView query);

    /**
     * 更据申请id获取变更表附件
     * 2016年3月4日
     * By 郭才林
     * @param p
     * @return
     */
	public List<Attachment> getAttachmentByApplyIds(Map<String, Object> p);
	
	/**
	 * 根据客户手机号码获取所有客户信息（用于客户手机号码变更）
	 * 2016年3月16日
	 * By 刘雄武
	 * @param customer
	 * @return
	 */
	public List<Customer> findListbyphone(Customer customer);
	
	/**
	 * 根据客户手机号码删除客户信息
	 * 2016年3月16日
	 * By 刘雄武
	 * @param customer
	 * @return
	 */
	public void deletecustmoerbyphone(Customer customer);

}
