
package com.creditharmony.fortune.delivery.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.delivery.constant.Constant;
import com.creditharmony.fortune.delivery.dao.CustomerDeliveryDao;
import com.creditharmony.fortune.delivery.entity.ext.DeliveryEx;
import com.creditharmony.fortune.template.entity.CustomerDeliveryExt;

/**
 * 客户交割
 * @Class Name CustomerDeliveryManager
 * @author 胡体勇
 * @Create In 2015年12月2日
 */
@Service
public class CustomerDeliveryManager extends CoreManager<CustomerDeliveryDao,DeliveryEx>{
	
	@Autowired
	private CustomerDao customerDao;
	
	/**
	 * 分页查询
	 * 2015年12月2日
	 * By 胡体勇
	 * @param page
	 * @param deliveryEx
	 * @return
	 */
	public Page<DeliveryEx> findPage(Page<DeliveryEx> page,DeliveryEx deliveryEx){
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		// 设置查询条件
		deliveryEx.setOrgType(FortuneOrgType.STORE.key);
		deliveryEx.setManagerRole(FortuneRole.FINANCING_MANAGER.id);
		deliveryEx.setTeamManagerRole(FortuneRole.TEAM_MANAGER.id);
		if(StringUtils.isNotEmpty(deliveryEx.getOrgCode())){
			String[] ids = deliveryEx.getOrgCode().split(",");
			deliveryEx.setOrgId(ids);
		}
		PageList<DeliveryEx> pageList = (PageList<DeliveryEx>) super.dao.findList(deliveryEx,pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 交割提醒列表显示
	 * 2015年12月2日
	 * By 胡体勇
	 * @param page
	 * @param deliveryEx
	 * @return
	 */
	public Page<DeliveryEx> deliveryRemindList(Page<DeliveryEx> page,DeliveryEx deliveryEx){
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		// 设置查询条件
		deliveryEx.setOrgType(FortuneOrgType.STORE.key);
		deliveryEx.setManagerRole(FortuneRole.FINANCING_MANAGER.id);
		deliveryEx.setTeamManagerRole(FortuneRole.TEAM_MANAGER.id);
		if(StringUtils.isNotEmpty(deliveryEx.getOrgCode())){
			String[] ids = deliveryEx.getOrgCode().split(",");
			deliveryEx.setOrgId(ids);
		}
		PageList<DeliveryEx> pageList = (PageList<DeliveryEx>) super.dao.deliveryRemindList(deliveryEx,pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	 
	/**
	 * 根据理财经理工号查询团队经理及营业部信息
	 * 2015年12月2日
	 * By hutiyong
	 * @param fmanagerCode
	 * @return
	 */
	public List<DeliveryEx> findInfoByCode(String code){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code",code);
		map.put("orgType",FortuneOrgType.STORE.key);
		map.put("managerRole", FortuneRole.FINANCING_MANAGER.id);
		map.put("teamManagerRole", FortuneRole.TEAM_MANAGER.id);
		return super.dao.findInfoByCode(map);
	}
	 
	/**
	 * 客户交割
	 * 2015年12月7日
	 * By 胡体勇
	 * @param map
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public int custDelivery(Map<String,String> map){
		
		int result = 0;
		// 设置客户交割信息
		DeliveryEx deliveryEx = new DeliveryEx();
		deliveryEx.setDelId(IdGen.uuid());
		//deliveryEx.setDelType(Constant.DELIVERY_DELTYPE_CUSTOMERDELIVERY);
		deliveryEx.setCustCode(map.get("custCode"));
		deliveryEx.setfManagerCode(map.get("fManagerCode"));
		deliveryEx.setNfManagerCode(map.get("nfManagerCode"));
		// 获取当前时间，并转为timestamp格式
		String date = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		Timestamp time = Timestamp.valueOf(date);
		deliveryEx.setCreateTime(time);
		deliveryEx.setCreateBy(UserUtils.getUserId());
		deliveryEx.setModifyBy(UserUtils.getUserId());
		deliveryEx.setModifyTime(time);
		
		// 判断当待交割时间为空时设置交割状态为立刻交割否则设置为待交割
		if(StringUtils.isEmpty(map.get("toDelDate"))){
			if(isBigTime(map.get("delDate"))){
				deliveryEx.setDelDate(time);
				//deliveryEx.setIsDelivery(Constant.DELIVERY_DELSTATUS_DELIVERYED);
				//deliveryEx.setDictDelStatus(Constant.DELIVERY_DELSTATUS_DELIVERYED);
				// 修改客户表相关信息
				super.dao.updateCustomer(deliveryEx);
				// 插入交割表交割数据
				super.dao.insertDelivery(deliveryEx);
				result ++;
			}else if(StringUtils.isEmpty(map.get("delDate"))){
				deliveryEx.setDelDate(time);
				//deliveryEx.setIsDelivery(Constant.DELIVERY_DELSTATUS_DELIVERYED);
				//deliveryEx.setDictDelStatus(Constant.DELIVERY_DELSTATUS_DELIVERYED);
				// 修改客户表相关信息
				super.dao.updateCustomer(deliveryEx);
				// 插入交割表交割数据
				super.dao.insertDelivery(deliveryEx);
				result ++;
			}else if(isBigTime(map.get("delDate")) == false){
				deliveryEx.setDelDate(time);
				//deliveryEx.setIsDelivery(Constant.DELIVERY_DELSTATUS_DELIVERYED);
				//deliveryEx.setDictDelStatus(Constant.DELIVERY_DELSTATUS_DELIVERYED);
				deliveryEx.setDelId(map.get("delId"));
				// 修改客户表相关信息
				super.dao.updateCustomer(deliveryEx);
				// 修改交割表信息
				super.dao.updateDelivery(deliveryEx);
				result ++;
			}
		}else{
			deliveryEx.setDelDate(Timestamp.valueOf(map.get("toDelDate")));
			//deliveryEx.setDictDelStatus(Constant.DELIVERY_DELSTATUS_TODELIVERY);
			//deliveryEx.setIsDelivery(Constant.DELIVERY_DELSTATUS_TODELIVERY);
			// 插入交割表交割数据
			super.dao.insertDelivery(deliveryEx);
			// 在插入待交割数据时修改客户表的交割标识
			super.dao.updateCustomerIsDelivery(deliveryEx);
			result ++;
		}
		return result;
	}
	
	/**
	 * 判断交割时间和当前时间的大小
	 * 2015年12月8日
	 * By 胡体勇
	 * @param delDate
	 * @return
	 */
	public boolean isBigTime(String delDate){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = null;
		Date d2 = null;
		if(StringUtils.isEmpty(delDate)){
			return false;
		}else{
			try {
				   d1 = df.parse(delDate);
				   d2 = df.parse(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			if(d1.getTime()<d2.getTime()){
				return true;
			}else{
				return false;
			}
		}
	}
	 
	/**
	 * 批量客户交割
	 * 2015年12月4日
	 * By 胡体勇
	 * @param code
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public int batchCustomerDelivery(String code){
		int result = 0;
		// 根据";"分离出客户对应的信息集合
		String[] total = code.split(";");
		for(int i = 0;i<total.length;i++){
			if(StringUtils.isNotEmpty(total[i])){
				// 根据","分离出客户对应理财经理、新理财经理及交割时间
				String[] info = total[i].split(",",Constant.ARRAY_SIZE_SIX);
				Map<String,String> map = new HashMap<String,String>(); 
				map.put("custCode", info[0]);
				map.put("fManagerCode", info[1]);
				map.put("nfManagerCode", info[2]);
				map.put("delDate", info[3]);
				map.put("toDelDate", info[4]);
				map.put("delId", info[5]);
				this.custDelivery(map);
			}
			result++;
		}
		return result;
	}
	
	/**
	 * 导出excel表格
	 * 2016年1月8日
	 * By 胡体勇
	 * @param deliveryEx
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = true)
    public List<CustomerDeliveryExt> outExcel(DeliveryEx deliveryEx){
    	List<CustomerDeliveryExt> excelList = new ArrayList<CustomerDeliveryExt>();
    	deliveryEx.setOrgType(FortuneOrgType.STORE.key);
		deliveryEx.setManagerRole(FortuneRole.FINANCING_MANAGER.id);
		deliveryEx.setTeamManagerRole(FortuneRole.TEAM_MANAGER.id);
		if(!StringUtils.isEmpty(deliveryEx.getId())){
			String[] str = deliveryEx.getId().split(",");
			deliveryEx.setCode(Arrays.asList(str));
		}
    	// 获取需要导出的数据
    	List<DeliveryEx> list = super.dao.findList(deliveryEx, new PageBounds());
    	if(ArrayHelper.isNotEmpty(list)){
    		for(int i = 0;i<list.size();i++){
    			CustomerDeliveryExt ext = new CustomerDeliveryExt();
    			// 将查询出的数据复制到模板对象
				BeanUtils.copyProperties(list.get(i),ext);
				excelList.add(ext);
    		}
    	}
		return excelList;
    }
	
	/**
	 * 导出excel表格
	 * 2016年1月8日
	 * By 胡体勇
	 * @param deliveryEx
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = true)
    public List<CustomerDeliveryExt> outRemindExcel(DeliveryEx deliveryEx){
    	List<CustomerDeliveryExt> excelList = new ArrayList<CustomerDeliveryExt>();
    	deliveryEx.setOrgType(FortuneOrgType.STORE.key);
		deliveryEx.setManagerRole(FortuneRole.FINANCING_MANAGER.id);
		deliveryEx.setTeamManagerRole(FortuneRole.TEAM_MANAGER.id);
    	// 获取需要导出的数据
    	List<DeliveryEx> list = super.dao.deliveryRemindList(deliveryEx, new PageBounds());
    	if(ArrayHelper.isNotEmpty(list)){
    		for(int i = 0;i<list.size();i++){
    			CustomerDeliveryExt ext = new CustomerDeliveryExt();
    			// 将查询出的数据复制到模板对象
				BeanUtils.copyProperties(list.get(i),ext);
				excelList.add(ext);
    		}
    	}
		return excelList;
    }
	
	/**
	 * 导入客户交割信息时写入相应表信息
	 * 2016年1月11日
	 * By 胡体勇
	 * @param customerDeliveryExt
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
    public int importExcel(List<CustomerDeliveryExt> list){
		int result = 0;
		for(int i = 0;i<list.size();i++){
			if(StringUtils.isNotEmpty(list.get(i).getCustCode())){
				// 判断导入的客户是否已存在在数据库中，如果存在则不导入
				int cout = super.dao.countCustomer(list.get(i).getCustCode());
				// 设置查询对应理财经理code下对应的id查询条件
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("code",list.get(i).getFManagerCode());
				map.put("orgType",FortuneOrgType.STORE.key);
				map.put("managerRole", FortuneRole.FINANCING_MANAGER.id);
				map.put("teamManagerRole", FortuneRole.TEAM_MANAGER.id);
				if(cout > 0){
					continue;
				}else{
					// 查询对应理财经理code下对应的id
					List<DeliveryEx> li = super.dao.findInfoByCode(map);
					if(li.size() > 0){
						// 设置插入用户表信息
						Customer cust = new Customer();
						cust.setId(IdGen.uuid());
						cust.setCustName(list.get(i).getCustName());
						cust.setCustCode(list.get(i).getCustCode());
						cust.setManagerCode(li.get(0).getNfManagerId());
						cust.setCreateBy(UserUtils.getUserId());
						cust.setCreateTime(new Date());
						customerDao.insert(cust);
						result++;
					}else{
						continue;
					}
				}
			}else{
				continue;
			}
		}
		return result;
    }
	
	/**
	 * 批量获取理财经理对应信息
	 * 2016年1月12日
	 * By 胡体勇
	 * @param ids
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = true)
	public List<DeliveryEx> loadManagerInfo(String ids){
		List<DeliveryEx> list = new ArrayList<DeliveryEx>();
		String[] str = ids.split(",");
		for(int i = 0;i<str.length;i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("code",str[i]);
			map.put("orgType",FortuneOrgType.STORE.key);
			map.put("managerRole", FortuneRole.FINANCING_MANAGER.id);
			map.put("teamManagerRole", FortuneRole.TEAM_MANAGER.id);
			List<DeliveryEx> result = super.dao.findInfoByCode(map);
			if(result.size() > 0){
				list.add(result.get(0));
			}
		}
		return list;
	}
}
