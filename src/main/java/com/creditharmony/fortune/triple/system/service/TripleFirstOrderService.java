
package com.creditharmony.fortune.triple.system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.adapter.service.triple.TripleFirstOrderBaseService;
import com.creditharmony.adapter.service.triple.bean.TripleFirstOrderInBean;
import com.creditharmony.adapter.service.triple.bean.TripleFirstOrderOutBean;
import com.creditharmony.fortune.triple.system.dao.TripleNewCustomerDao;
import com.creditharmony.fortune.triple.system.facade.TripleFirstOrderFacade;

/**
 * 客户首单接口调用
 * @Class Name TripleFirstOrderService
 * @author 胡体勇
 * @Create In 2016年1月22日
 */
@Service
public class TripleFirstOrderService extends TripleFirstOrderBaseService {
	
	/** 日志对象*/
	protected Logger logger = LoggerFactory.getLogger(TripleFirstOrderService.class);
	@Autowired
	private TripleNewCustomerDao tripleNewCustomerDao;// 三网客户查询
	@Autowired
	private TripleFirstOrderFacade tripleFirstOrderFacade;
	@Autowired
	private InfoGroupService infoGroupService;
	@Autowired
	private TripleCommonService tripleCommonService;
	@Autowired
	private TripleFirstOrderServiceImp tripleFirstOrderServiceImp;
	
	/**
	 * 客户首单操作
	 * (non-Javadoc)
	 * @see com.creditharmony.adapter.service.TripleFirstOrderBaseService#doExec(com.creditharmony.adapter.service.bean.TripleFirstOrderBean)
	 * @param paramBean
	 */
	@Override
	public TripleFirstOrderOutBean doExec(TripleFirstOrderInBean paramBean) {
		 // 消息确认返回
	     TripleFirstOrderOutBean out = new TripleFirstOrderOutBean();
		try {
		     boolean b = this.firstOrder(paramBean);
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
	 * 
	 * 2016年3月11日
	 * By 胡体勇
	 * @param paramBean
	 * @return
	 */
    public boolean firstOrder(TripleFirstOrderInBean paramBean){
		try {
			return tripleFirstOrderServiceImp.firstOrder(paramBean);
		} catch (Exception e) {
			logger.error("手机号为："+paramBean.getPhone()+"证件号为："+paramBean.getCardId()+"进行三网首单验证时出现异常！",e);
			return false;
		}
    }
}
