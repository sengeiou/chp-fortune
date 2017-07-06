package com.creditharmony.fortune.donation.apply.dao;

import java.util.List;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.donation.apply.entity.CustomerManagerinfo;
import com.creditharmony.fortune.donation.apply.entity.CustomerQueryView;
import com.creditharmony.fortune.donation.apply.entity.Customertransfe;

/**
 * 转赠dao类
 * @Class Name CustomertransferDao
 * @author 刘雄武
 * @Create In 2016年3月3日
 */
@FortuneBatisDao
public interface CustomertransferDao extends CrudDao<Customertransfe> {

	/**
	 * 获取转赠客户信息
	 * 2016年3月3日
	 * By 刘雄武
	 * @param customer
	 * @return
	 */
	public List<Customertransfe> getCustomerInfo(String  custMobilephone);
	
	/**
	 * 根据客户编号获取理财经理营业部信息
	 * 2016年3月4日
	 * By 刘雄武
	 * @param custCode
	 * @return
	 */
	public CustomerManagerinfo getCustomerManagerbyCode(String custCode);
	
	/**
	 * 根据理财经理工号和姓名查询理财经理信息
	 * 2016年3月5日
	 * By 刘雄武
	 * @param query
	 * @return
	 */
	public PageList<CustomerManagerinfo> getManagerinfobyCode(CustomerQueryView query,PageBounds pageBounds);
	
	/**
	 * 修改客户转赠状态
	 * 2016年3月7日
	 * By 刘雄武
	 * @return
	 */
	public void updatecustomerinfo(CustomerManagerinfo CustomerM);
	
	/**
	 * 交割表保存数据
	 * 2016年3月7日
	 * By 刘雄武
	 * @param CustomerM
	 */
	public void savecustomertransferInfo(CustomerManagerinfo CustomerM);

	/**
	 * 根据交割ID获取交割表数据
	 * 2016年3月7日
	 * By 刘雄武
	 * @param applyId
	 * @return
	 */
	public CustomerManagerinfo getDeliveryinfobyID(String applyId);
	
	/**
	 * 根据交割ID修改交割表状态
	 * 2016年3月7日
	 * By 刘雄武
	 * @param applyId
	 * @return
	 */
	public void updatedelivery(CustomerManagerinfo CustomerM);
	
	/**
	 * 审批完成更新客户表信息
	 * 2016年3月7日
	 * By 刘雄武
	 * @param CustomerM
	 */
	public void updatecustomer(CustomerManagerinfo CustomerM);
	
	/**
	 * 获取验证出借申请信息
	 * 2016年3月7日
	 * By 刘雄武
	 * @param custCode
	 * @return
	 */
	public List<Customertransfe> getCustomerInfocheck(Customertransfe customertransfe);
	
	/**
	 * 根据理财经理ID查询理财经理工号
	 * 2016年3月9日
	 * By 刘雄武
	 * @param CustomerM
	 * @return
	 */
	public CustomerManagerinfo getManagerinfobyID(CustomerManagerinfo CustomerM);
	
	/**
	 * 根据理财经理工号查询理财经理ID
	 * 2016年3月17日
	 * By 刘雄武
	 * @param CustomerM
	 * @return
	 */
	public List<CustomerManagerinfo> getManagerinfobyCodeT(CustomerManagerinfo CustomerM);
}