
package com.creditharmony.fortune.triple.system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.adapter.service.triple.TripleChangePhoneBaseService;
import com.creditharmony.adapter.service.triple.bean.TripleChangePhoneInBean;
import com.creditharmony.adapter.service.triple.bean.TripleChangePhoneOutBean;
import com.creditharmony.fortune.delivery.service.TripleCustomerDeliveryManager;
import com.creditharmony.fortune.triple.system.dao.TripleNewCustomerDao;
import com.creditharmony.fortune.triple.system.entity.IntPhone;

/**
 * 手机号变更接口调用
 * @Class Name TripleChangePhoneService
 * @author 胡体勇
 * @Create In 2016年1月22日
 */
@Service
public class TripleChangePhoneService extends TripleChangePhoneBaseService {
	
	/** 日志对象*/
	protected Logger logger = LoggerFactory.getLogger(TripleChangePhoneService.class);
	@Autowired
	private TripleNewCustomerDao tripleNewCustomerDao;// 三网客户查询
	@Autowired
	private TripleFirstOrderService tripleFirstOrderService;
	@Autowired
	private TripleCommonService tripleCommonService;
	@Autowired
	private TripleCustomerDeliveryManager tripleCustomerDeliveryManager;
	@Autowired
	private InfoGroupService infoGroupService;
	@Autowired
	private TripleChangePhoneServiceImp tripleChangePhoneServiceImp;
	
	/**
	 * 手机号变更业务接口实现
	 * (non-Javadoc)
	 * @see com.creditharmony.adapter.service.TripleChangePhoneBaseService#doExec(com.creditharmony.adapter.service.bean.TripleChangePhoneBean)
	 * @param paramBean
	 */
	@Override
	public TripleChangePhoneOutBean doExec(TripleChangePhoneInBean paramBean) {
		// 设置返回确认消息信息
		TripleChangePhoneOutBean out = new TripleChangePhoneOutBean();
		try {
			boolean b = this.changePhone(paramBean);
			if(b == true){
				out.setRetCode(ReturnConstant.SUCCESS);
			}else{
				out.setRetCode(ReturnConstant.ERROR);
			}
			return out;
		} catch (Exception e) {
			out.setRetCode(ReturnConstant.ERROR);
			out.setRetMsg(e.getMessage());
			return out;
		}
	}
	
	/**
	 * 手机号变更
	 * 2016年2月26日
	 * By 胡体勇
	 * @param paramBean
	 * @return
	 */
	public boolean changePhone(TripleChangePhoneInBean paramBean){
		try {
			return tripleChangePhoneServiceImp.changePhone(paramBean);
		} catch (Exception e) {
			logger.error("手机号为："+paramBean.getOldPhone()+"修改手机号失败！",e);
			return false;
		}
	}
	
	/**
	 * chp 手机号发生变更
	 * 2016年3月9日
	 * By 胡体勇
	 */
	public void chpChangerPhone(IntPhone intPhone){
	    TripleChangePhoneInBean bean = new TripleChangePhoneInBean();
	    bean.setNewPhone(intPhone.getNewPhone());
	    bean.setOldPhone(intPhone.getPhone());
	    bean.setOsType(intPhone.getOsType());
	    bean.setEmpCode(intPhone.getEmpCode());
	    bean.setCardId(intPhone.getCardId());
		this.changePhone(bean);
	}
	
}
