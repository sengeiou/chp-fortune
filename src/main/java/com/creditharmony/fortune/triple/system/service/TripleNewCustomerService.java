
package com.creditharmony.fortune.triple.system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.adapter.service.triple.TripleNewCustomerBaseService;
import com.creditharmony.adapter.service.triple.bean.TripleNewCustomerInBean;
import com.creditharmony.adapter.service.triple.bean.TripleNewCustomerOutBean;
import com.creditharmony.fortune.common.util.BeanUtil;
import com.creditharmony.fortune.triple.system.constant.Constant;
import com.creditharmony.fortune.triple.system.entity.IntCard;
import com.creditharmony.fortune.triple.system.entity.IntCustomerBean;
import com.creditharmony.fortune.triple.system.entity.IntPhone;

/**
 * 三网新增客户接口调用
 * @Class Name TripleNewCustomerService
 * @author 胡体勇
 * @Create In 2016年1月22日
 */
@Service
public class TripleNewCustomerService extends TripleNewCustomerBaseService {
	
	/**日志对象*/
	protected Logger logger = LoggerFactory.getLogger(TripleNewCustomerService.class);
	@Autowired
	private TripleNewCustomerServiceImp tripleNewCustomerServiceImp;
	@Autowired
	private TripleCommonService tripleCommonService;
	
	
	/**
	 * 三网推送的客户信息
	 *  (non-Javadoc)
	 * @see com.creditharmony.adapter.service.TripleNewCustomerBaseService#doExec(com.creditharmony.adapter.service.bean.TripleNewCustomerBean)
	 *@param paramBean
	 */
	@Override
	public TripleNewCustomerOutBean doExec(TripleNewCustomerInBean paramBean) {
		// 设置返回确认消息信息
		TripleNewCustomerOutBean out = new TripleNewCustomerOutBean();
		
		if(Constant.OPERATION_TYPE_C.equals(paramBean.getOperation())){
			try {
					// 当推送的客户的数据类型为注册时进行的操作
					IntPhone outPhone = this.registerCustomer(BeanUtil.conv2IntCustomerBean(paramBean,null));
					if(outPhone != null){
						// 接收消息处理成功返回成功代码
						out.setRetCode(ReturnConstant.SUCCESS);
					}else{
						out.setRetCode(ReturnConstant.ERROR);
					}
					// 插入日志表
					tripleCommonService.insertLog(paramBean, Constant.SYSNCNUM_XZKHXXTB, Constant.SEND_L);
					return out;
			} catch (Exception e) {
				out.setRetCode(ReturnConstant.ERROR);
				out.setRetMsg(e.getMessage());
				return out;
			}
			
		}else if(Constant.OPERATION_TYPE_M.equals(paramBean.getOperation())){
			try {
				// 当推送的客户的数据类型为身份认证时进行的操作
				IntCard in = this.authenticateCustomer(BeanUtil.conv2IntCustomerBean(paramBean,null));
				if(in != null){
					// 接收消息处理成功返回成功代码
					out.setRetCode(ReturnConstant.SUCCESS);
				}else{
					out.setRetCode(ReturnConstant.ERROR);
				}
				// 插入日志表
				tripleCommonService.insertLog(paramBean, Constant.SYSNCNUM_XZKHXXTB, Constant.SEND_L);
				return out;
			} catch (Exception e) {
				out.setRetCode(ReturnConstant.ERROR);
				out.setRetMsg(e.getMessage());
				return out;
			}
			
		}else if(Constant.OPERATION_TYPE_V.equals(paramBean.getOperation())){
			try {
				// 当推送的客户的数据类型为无理财经理添加理财经理时进行的操作
				boolean b = this.addCustomerManager(paramBean);
				if(b == true){
					// 接收消息处理成功返回成功代码
					out.setRetCode(ReturnConstant.SUCCESS);
				}else{
					out.setRetCode(ReturnConstant.ERROR);
				}
				// 插入日志表
				tripleCommonService.insertLog(paramBean, Constant.SYSNCNUM_XZKHXXTB,Constant.SEND_L);
				return out;
			} catch (Exception e) {
				out.setRetCode(ReturnConstant.ERROR);
				out.setRetMsg(e.getMessage());
				return out;
			}
			
		}
		return out;
	}
	
	/**
	 * 客户操作类型为注册接口实现
	 * 2016年1月23日
	 * By 胡体勇
	 * @param paramBean
	 * @return 
	 */
	public IntPhone registerCustomer(IntCustomerBean paramBean){
		// 用于返回信息
		IntPhone backInfo = new IntPhone();
		backInfo.setPhone(paramBean.getNewPhone());
		try {
			backInfo = tripleNewCustomerServiceImp.registerCustomer(paramBean);
			return backInfo;
		} catch (Exception e) {
			logger.error("手机号为："+paramBean.getNewPhone()+"注册时在三网验证异常！",e);
			// 插入日志表
			tripleCommonService.insertLog(paramBean, Constant.SYSNCNUM_XZKHXXTB, Constant.SEND_L);
			return backInfo;
		}
    }
	
	/**
	 * 客户操作类型为身份认证
	 * 2016年1月23日
	 * By 胡体勇
	 * @param paramBean
	 * @return 
	 */
	public IntCard authenticateCustomer(IntCustomerBean paramBean){
		// 设置返回信息
		IntCard c = new IntCard();
		c.setCardId(paramBean.getCardId());
		try {
			c = tripleNewCustomerServiceImp.authenticateCustomer(paramBean);
			return c;
		} catch (Exception e) {
			logger.error("证件号为："+paramBean.getCardId()+"的证件号验证异常！",e);
			// 插入日志表
			tripleCommonService.insertLog(paramBean, Constant.SYSNCNUM_XZKHXXTB, Constant.SEND_L);
			return c;
		}
	}
	
	/**
	 * 客户操作类型为无理财经理添加理财经理
	 * 2016年1月23日
	 * By 胡体勇
	 * @param paramBean
	 * @return 
	 */
	public boolean addCustomerManager(TripleNewCustomerInBean paramBean){
		try {
			return tripleNewCustomerServiceImp.addCustomerManager(paramBean);
		} catch (Exception e) {
			logger.error("手机号为"+paramBean.getPhone()+"的客户添加理财经理异常！",e);
			// 插入日志表
			tripleCommonService.insertLog(paramBean, Constant.SYSNCNUM_XZKHXXTB, Constant.SEND_L);
			return false;
		}
	}
		
	
}
