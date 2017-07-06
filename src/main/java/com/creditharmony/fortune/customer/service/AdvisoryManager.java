package com.creditharmony.fortune.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.customer.dao.AdvisoryDao;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.entity.Advisory;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.triple.system.constant.Constant;
import com.creditharmony.fortune.triple.system.dao.TripleLoanAskSyncDao;
import com.creditharmony.fortune.triple.system.entity.IntLoanAskBean;
import com.creditharmony.fortune.users.dao.UserInfoDao;
import com.creditharmony.fortune.users.entity.UserInfo;
import com.creditharmony.fortune.utils.SerialNum;

/**
 * @Class Name AdvisoryManager
 * @author 孙凯文
 * @Create In 2015年12月2日
 */
@Service
@Transactional(readOnly = true)
public class AdvisoryManager extends CoreManager<AdvisoryDao, Advisory> {
	@Autowired
	private TripleLoanAskSyncDao  tripleLoanAskDao;
	@Autowired
	private UserInfoDao  userInfoDao;
	@Autowired
	private CustomerDao  customerDao;

	/**
	 * 2015年12月2日
	 * By 孙凯文
	 * @param page
	 * @param entity
	 * @return
	 */
	public Page<Advisory> findPage(Page<Advisory> page, Advisory entity) {
		entity.setPage(page);
		return page.setList(super.dao.findPage(entity));
	}
	
	/**
	 * 2016年7月24日
	 * By 张新民
	 * @param page
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false)
	public void saveAdvisory(Advisory advisory) {
		save(advisory);
		UserInfo userInfo = userInfoDao.get(advisory.getManagerId());
//		Customer c=new Customer();c.setCustCode(advisory.getCustCode());
//		List<Customer> cusList = customerDao.getCusByCode(c) ;
		
		IntLoanAskBean intLoanAskBean = getLoanAskBean(advisory);
		if(null!=userInfo){
			intLoanAskBean.setManagerCode(userInfo.getUserCode());
			intLoanAskBean.setManagerName(userInfo.getName());
		}
		//如果不为空则设置客户的id
//		if(cusList!=null&&cusList.size()!=0){
//			intLoanAskBean.setCustomerCode(cusList.get(0).getId());
//		}
		tripleLoanAskDao.insertIntLoanAsk(intLoanAskBean);
	}
	
	/**
	 * 根据advisory对象生成IntLoanAskBean实例
	 * @param advisory
	 * 2016年7月24日
	 * By 张新民
	 * @return
	 */
	private  IntLoanAskBean getLoanAskBean(Advisory advisory){
		IntLoanAskBean intLoanAskBean = new  IntLoanAskBean();
		intLoanAskBean.setId(IdGen.uuid());
		intLoanAskBean.setAskBeginDay(advisory.getAskBeginDate());
		intLoanAskBean.setAskDay(advisory.getAskDate());
		intLoanAskBean.setAskDes(advisory.getAskDes());
		intLoanAskBean.setAskEndDay(advisory.getAskEndDate());
		intLoanAskBean.setAskId(advisory.getId());
		intLoanAskBean.setAskInputDay(advisory.getAskInputDate());
		intLoanAskBean.setAskInputMoney(advisory.getAskInputMoney());
		intLoanAskBean.setAskNextDay(advisory.getAskNextDate());
		intLoanAskBean.setAskNextType(advisory.getAskNextType());
		intLoanAskBean.setAskProduct(advisory.getAskProduct());
		intLoanAskBean.setCreateBy(advisory.getCreateBy());
		intLoanAskBean.setCreateTime(advisory.getCreateTime());
		intLoanAskBean.setCustomerCode(advisory.getCustCode());
		intLoanAskBean.setDictAskType(advisory.getAskType());
		intLoanAskBean.setModifyBy(advisory.getModifyBy());
		intLoanAskBean.setModifyTime(advisory.getModifyTime());
		intLoanAskBean.setManagerId(advisory.getManagerId());
		intLoanAskBean.setSendStatus(Constant.SEND_STATUS_FAIL);
		intLoanAskBean.setSendType(Constant.SYSNCNUM_KHGTJLTB);
		intLoanAskBean.setOperation(Constant.OPERATION_TYPE_C);
		intLoanAskBean.setUniqueNum(SerialNum.getSerialNum());
		return intLoanAskBean;
	}

}
