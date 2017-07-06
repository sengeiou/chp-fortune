package com.creditharmony.fortune.triple.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.adapter.service.triple.TripleCallBackBaseService;
import com.creditharmony.adapter.service.triple.bean.TripleCallBackInBean;
import com.creditharmony.adapter.service.triple.bean.TripleCallBackOutBean;
import com.creditharmony.core.fortune.type.OsType;
import com.creditharmony.fortune.triple.system.constant.Constant;
import com.creditharmony.fortune.triple.system.dao.TripleEmployeeSyncDao;
import com.creditharmony.fortune.triple.system.dao.TripleLoanAskSyncDao;
import com.creditharmony.fortune.triple.system.dao.TripleNewCustomerDao;
import com.creditharmony.fortune.triple.system.dao.TripleOrgSyncDao;
import com.creditharmony.fortune.triple.system.dao.TripleRoleSyncDao;
import com.creditharmony.fortune.triple.system.dao.TripleUserSyncDao;
import com.creditharmony.fortune.triple.system.entity.IntCustomerBean;
import com.creditharmony.fortune.triple.system.entity.IntEmployeeBean;
import com.creditharmony.fortune.triple.system.entity.IntLoanAskBean;
import com.creditharmony.fortune.triple.system.entity.IntLogBean;
import com.creditharmony.fortune.triple.system.entity.IntOrgBean;
import com.creditharmony.fortune.triple.system.entity.IntRoleBean;

/**
 * 三网发送信息返回确认消息
 * @Class Name TripleCallBackService
 * @author 胡体勇
 * @Create In 2016年3月8日
 */
@Service
public class TripleCallBackService extends TripleCallBackBaseService {
	
	/** 日志对象*/
	protected Logger logger = LoggerFactory.getLogger(TripleCallBackService.class);
	@Autowired
	private TripleEmployeeSyncDao tripleEmployeeSyncDao;
	@Autowired
	private TripleOrgSyncDao tripleOrgSyncDao;
	@Autowired
	private TripleRoleSyncDao tripleRoleSyncDao;
	@Autowired
	private TripleLoanAskSyncDao tripleLoanAskSyncDao;
	@Autowired
	private TripleNewCustomerDao tripleNewCustomerDao;
	
	@Autowired
	private TripleUserSyncDao tripleUserSyncDao ;
	
	@Autowired
	private TripleCommonService tripleCommonService;
	
	/* (non-Javadoc)
	 * @see com.creditharmony.adapter.service.triple.TripleCallBackBaseService#doExec(com.creditharmony.adapter.service.triple.bean.TripleCallBackBean)
	 */
	@Override
	public TripleCallBackOutBean doExec(TripleCallBackInBean paramBean) {
		
		logger.info("【三网开始接收sendType为"+paramBean.getFromSys()+" TypeId为"+paramBean.getMsgTypeId()+"的回执，时间是"+ new Date() +"】");
		try{
			tripleCommonService.insertLog(paramBean, Constant.SYSNCNUM_CALLBACK, Constant.SEND_L);
		}catch(Exception e){
			logger.info("【三网回执日志插入异常，"+ e.getMessage() +"】");
		}
        
		// 设置返回消息信息
		TripleCallBackOutBean outBean = new TripleCallBackOutBean();
		if("A010".equals(paramBean.getMsgTypeId())){
			if(OsType.JX.value.equals(paramBean.getFromSys())){
				// A010:组织机构信息同步
				for(int i = 0;i<paramBean.getCallBackList().size();i++){
					IntOrgBean bean = new IntOrgBean();
					bean.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
					bean.setSendStatus(paramBean.getCallBackList().get(i).getState());
					try {
						this.updateOrgSendStatus(bean);
						outBean.setRetCode(ReturnConstant.SUCCESS);
					} catch (Exception e) {
						logger.error("回执处理异常", e);
						outBean.setRetCode(ReturnConstant.ERROR);
						outBean.setRetMsg(e.getMessage());
					}
				}
			}else if(OsType.CX.value.equals(paramBean.getFromSys())){
				// A010:组织机构信息同步
				for(int i = 0;i<paramBean.getCallBackList().size();i++){
					if("1".equals(paramBean.getCallBackList().get(i).getState())){
						IntOrgBean bean = new IntOrgBean();
						bean.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
						bean.setSendStatus(paramBean.getCallBackList().get(i).getState());
						try {
							this.updateOrgSendStatus(bean);
							outBean.setRetCode(ReturnConstant.SUCCESS);
						} catch (Exception e) {
							logger.error("回执处理异常", e);
							outBean.setRetCode(ReturnConstant.ERROR);
							outBean.setRetMsg(e.getMessage());
						}
					}else{
						try {
							IntOrgBean bean = new IntOrgBean();
							bean.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
							bean.setSendStatus(paramBean.getCallBackList().get(i).getState());
							IntLogBean log = new IntLogBean();
						    log.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
						    List<IntLogBean> logList = tripleNewCustomerDao.countSendNum(log);
						    if(logList.size() > 5){
						    	this.updateOrgSendStatus(bean);
						    }
							outBean.setRetCode(ReturnConstant.SUCCESS);
						} catch (Exception e) {
							logger.error("回执处理异常", e);
							outBean.setRetCode(ReturnConstant.ERROR);
							outBean.setRetMsg(e.getMessage());
						}
					}
				}
			}else if(OsType.XHLT.value.equals(paramBean.getFromSys())){
				// A010:组织机构信息同步
				for(int i = 0;i<paramBean.getCallBackList().size();i++){
					if("1".equals(paramBean.getCallBackList().get(i).getState())){
						IntOrgBean bean = new IntOrgBean();
						bean.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
						bean.setSendStatus(paramBean.getCallBackList().get(i).getState());
						try {
							this.updateOrgSendStatus(bean);
							outBean.setRetCode(ReturnConstant.SUCCESS);
						} catch (Exception e) {
							logger.error("回执处理异常", e);
							outBean.setRetCode(ReturnConstant.ERROR);
							outBean.setRetMsg(e.getMessage());
						}
					}else{
						try {
							IntOrgBean bean = new IntOrgBean();
							bean.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
							bean.setSendStatus(paramBean.getCallBackList().get(i).getState());
							IntLogBean log = new IntLogBean();
						    log.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
						    List<IntLogBean> logList = tripleNewCustomerDao.countSendNum(log);
						    if(logList.size() > 5){
						    	this.updateOrgSendStatus(bean);
						    }
							outBean.setRetCode(ReturnConstant.SUCCESS);
						} catch (Exception e) {
							logger.error("回执处理异常", e);
							outBean.setRetCode(ReturnConstant.ERROR);
							outBean.setRetMsg(e.getMessage());
						}
					}
				}
			}else if(OsType.DJR.value.equals(paramBean.getFromSys())){
				// A010:组织机构信息同步
				for(int i = 0;i<paramBean.getCallBackList().size();i++){
					if("1".equals(paramBean.getCallBackList().get(i).getState())){
						IntOrgBean bean = new IntOrgBean();
						bean.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
						bean.setSendStatus(paramBean.getCallBackList().get(i).getState());
						try {
							this.updateOrgSendStatus(bean);
							outBean.setRetCode(ReturnConstant.SUCCESS);
						} catch (Exception e) {
							logger.error("回执处理异常", e);
							outBean.setRetCode(ReturnConstant.ERROR);
							outBean.setRetMsg(e.getMessage());
						}
					}else{
						try {
							IntOrgBean bean = new IntOrgBean();
							bean.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
							bean.setSendStatus(paramBean.getCallBackList().get(i).getState());
							IntLogBean log = new IntLogBean();
						    log.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
						    List<IntLogBean> logList = tripleNewCustomerDao.countSendNum(log);
						    if(logList.size() > 5){
						    	this.updateOrgSendStatus(bean);
						    }
							outBean.setRetCode(ReturnConstant.SUCCESS);
						} catch (Exception e) {
							logger.error("回执处理异常", e);
							outBean.setRetCode(ReturnConstant.ERROR);
							outBean.setRetMsg(e.getMessage());
						}
					}
				}
			}
		}else if("A011".equals(paramBean.getMsgTypeId())){
			if(OsType.JX.value.equals(paramBean.getFromSys())){
				// A011:理财经理信息同步
				for(int i = 0;i<paramBean.getCallBackList().size();i++){
					IntEmployeeBean bean = new IntEmployeeBean();
					bean.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
					bean.setSendStatus(paramBean.getCallBackList().get(i).getState());
					try {
						this.updateEmployeeSendStatus(bean);
						outBean.setRetCode(ReturnConstant.SUCCESS);
					} catch (Exception e) {
						logger.error("回执处理异常", e);
						outBean.setRetCode(ReturnConstant.ERROR);
						outBean.setRetMsg(e.getMessage());
					}
				}
			} else if(OsType.CX.value.equals(paramBean.getFromSys())){
				// A011:用户信息同步
				for(int i = 0;i<paramBean.getCallBackList().size();i++){
					IntEmployeeBean bean = new IntEmployeeBean();
					bean.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
					bean.setSendStatus(paramBean.getCallBackList().get(i).getState());
					try {
						this.updateUserSendStatus(bean);
						outBean.setRetCode(ReturnConstant.SUCCESS);
					} catch (Exception e) {
						logger.error("回执处理异常", e);
						outBean.setRetCode(ReturnConstant.ERROR);
						outBean.setRetMsg(e.getMessage());
					}
				}
			} else if(OsType.XHLT.value.equals(paramBean.getFromSys())){
				// A011:理财经理信息同步
				for(int i = 0;i<paramBean.getCallBackList().size();i++){
					if("1".equals(paramBean.getCallBackList().get(i).getState())){
						IntEmployeeBean bean = new IntEmployeeBean();
						bean.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
						bean.setSendStatus(paramBean.getCallBackList().get(i).getState());
						try {
							this.updateEmployeeSendStatus(bean);
							outBean.setRetCode(ReturnConstant.SUCCESS);
						} catch (Exception e) {
							logger.error("回执处理异常", e);
							outBean.setRetCode(ReturnConstant.ERROR);
							outBean.setRetMsg(e.getMessage());
						}
					}else{
						try {
							IntEmployeeBean bean = new IntEmployeeBean();
							bean.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
							bean.setSendStatus(paramBean.getCallBackList().get(i).getState());
							IntLogBean log = new IntLogBean();
						    log.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
						    List<IntLogBean> logList = tripleNewCustomerDao.countSendNum(log);
						    if(logList.size() > 5){
						    	this.updateEmployeeSendStatus(bean);
						    }
							outBean.setRetCode(ReturnConstant.SUCCESS);
						} catch (Exception e) {
							logger.error("回执处理异常", e);
							outBean.setRetCode(ReturnConstant.ERROR);
							outBean.setRetMsg(e.getMessage());
						}
					}
				}
			}else if(OsType.DJR.value.equals(paramBean.getFromSys())){
				// A011:理财经理信息同步
				for(int i = 0;i<paramBean.getCallBackList().size();i++){
					if("1".equals(paramBean.getCallBackList().get(i).getState())){
						IntEmployeeBean bean = new IntEmployeeBean();
						bean.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
						bean.setSendStatus(paramBean.getCallBackList().get(i).getState());
						try {
							this.updateEmployeeSendStatus(bean);
							outBean.setRetCode(ReturnConstant.SUCCESS);
						} catch (Exception e) {
							logger.error("回执处理异常", e);
							outBean.setRetCode(ReturnConstant.ERROR);
							outBean.setRetMsg(e.getMessage());
						}
					}else{
						try {
							IntEmployeeBean bean = new IntEmployeeBean();
							bean.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
							bean.setSendStatus(paramBean.getCallBackList().get(i).getState());
							IntLogBean log = new IntLogBean();
						    log.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
						    List<IntLogBean> logList = tripleNewCustomerDao.countSendNum(log);
						    if(logList.size() > 5){
						    	this.updateEmployeeSendStatus(bean);
						    }
							outBean.setRetCode(ReturnConstant.SUCCESS);
						} catch (Exception e) {
							logger.error("回执处理异常", e);
							outBean.setRetCode(ReturnConstant.ERROR);
							outBean.setRetMsg(e.getMessage());
						}
					}
				}
			}
		}else if("A014".equals(paramBean.getMsgTypeId())){
			if(OsType.JX.value.equals(paramBean.getFromSys())){
				// A014:客户理财经理变更
				List sucList=new ArrayList();
				List failList=new ArrayList();
				for(int i = 0;i<paramBean.getCallBackList().size();i++){
					IntCustomerBean bean = new IntCustomerBean();
					bean.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
					bean.setSendStatus(paramBean.getCallBackList().get(i).getState());
					try {
						if("0".equals(paramBean.getCallBackList().get(i).getState())){
							//this.updateSendStatusDao(bean);
							sucList.add(bean);
						}else if("1".equals(paramBean.getCallBackList().get(i).getState())){
							IntLogBean log = new IntLogBean();
						    log.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
						    List<IntLogBean> logList = tripleNewCustomerDao.countSendNum(log);
						    if(logList.size() > 5){
						    	bean.setSendStatus("0");
						    	//this.updateSendStatusDao(bean);
						    	sucList.add(bean);
						    }else{
						    	//this.updateSendStatusDao(bean);
						    	failList.add(bean);
						    }
						}
						
						if(sucList.size()>0&&sucList.size()%500==0||failList.size()>0&&failList.size()%500==0||i==paramBean.getCallBackList().size()-1){
							if(sucList.size()>0){
								tripleNewCustomerDao.updateSendStatusSucBatch(sucList);
								sucList.clear();
							}
							if(failList.size()>0){
								tripleNewCustomerDao.updateSendStatusFailBatch(failList);
								failList.clear();
							}
						}
						outBean.setRetCode(ReturnConstant.SUCCESS);
					} catch (Exception e) {
						logger.error("回执处理异常", e);
						outBean.setRetCode(ReturnConstant.ERROR);
						outBean.setRetMsg(e.getMessage());
					}
				}
				
				
			} else if(OsType.CX.value.equals(paramBean.getFromSys())){
				// A014:客户理财经理变更
				List sucList=new ArrayList();
				List failList=new ArrayList();
				for(int i = 0;i<paramBean.getCallBackList().size();i++){
					if("1".equals(paramBean.getCallBackList().get(i).getState())){
						IntCustomerBean bean = new IntCustomerBean();
						bean.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
						try {
							IntLogBean log = new IntLogBean();
						    log.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
						    List<IntLogBean> logList = tripleNewCustomerDao.countSendNum(log);
						    if(logList.size() > 5){
						    	bean.setSendStatus("0");
						    	//this.updateSendStatusDao(bean);
						    	sucList.add(bean);
						    }else{
								bean.setSendStatus(paramBean.getCallBackList().get(i).getState());
						    	//this.updateSendStatusDao(bean);
						    	failList.add(bean);
						    }
							outBean.setRetCode(ReturnConstant.SUCCESS);
						} catch (Exception e) {
							logger.error("回执处理异常", e);
							outBean.setRetCode(ReturnConstant.ERROR);
							outBean.setRetMsg(e.getMessage());
						}
					}else{
						try {
							IntCustomerBean bean = new IntCustomerBean();
							bean.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
							bean.setSendStatus(paramBean.getCallBackList().get(i).getState());
							IntLogBean log = new IntLogBean();
						    log.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
						    List<IntLogBean> logList = tripleNewCustomerDao.countSendNum(log);
						    if(logList.size() > 5){
						    	//this.updateSendStatusDao(bean);
						    	sucList.add(bean);
						    }
							outBean.setRetCode(ReturnConstant.SUCCESS);
						} catch (Exception e) {
							logger.error("回执处理异常", e);
							outBean.setRetCode(ReturnConstant.ERROR);
							outBean.setRetMsg(e.getMessage());
						}
					}
					//执行更新
					if(sucList.size()>0&&sucList.size()%500==0||failList.size()>0&&failList.size()%500==0||i==paramBean.getCallBackList().size()-1){
						if(sucList.size()>0){
							tripleNewCustomerDao.updateSendStatusSucBatch(sucList);
							sucList.clear();
						}
						if(failList.size()>0){
							tripleNewCustomerDao.updateSendStatusFailBatch(failList);
							failList.clear();
						}
					}
				}
			} else if(OsType.DJR.value.equals(paramBean.getFromSys())||OsType.ZCJ.value.equals(paramBean.getFromSys())){
				// A014:客户理财经理变更
				// A014:客户理财经理变更
				List sucList=new ArrayList();
				List failList=new ArrayList();
				for(int i = 0;i<paramBean.getCallBackList().size();i++){
					if("1".equals(paramBean.getCallBackList().get(i).getState())){
						IntCustomerBean bean = new IntCustomerBean();
						bean.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
						try {
							IntLogBean log = new IntLogBean();
						    log.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
						    List<IntLogBean> logList = tripleNewCustomerDao.countSendNum(log);
						    if(logList.size() > 5){
						    	bean.setSendStatus("0");
						    	//this.updateSendStatusDao(bean);
						    	sucList.add(bean);
						    }else{
								bean.setSendStatus(paramBean.getCallBackList().get(i).getState());
						    	//this.updateSendStatusDao(bean);
								failList.add(bean);
						    }
							outBean.setRetCode(ReturnConstant.SUCCESS);
						} catch (Exception e) {
							logger.error("回执处理异常", e);
							outBean.setRetCode(ReturnConstant.ERROR);
							outBean.setRetMsg(e.getMessage());
						}
					}else{
						try {
							IntCustomerBean bean = new IntCustomerBean();
							bean.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
							bean.setSendStatus(paramBean.getCallBackList().get(i).getState());
							IntLogBean log = new IntLogBean();
						    log.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
						    List<IntLogBean> logList = tripleNewCustomerDao.countSendNum(log);
						    if(logList.size() > 5){
						    	//this.updateSendStatusDao(bean);
						    	sucList.add(bean);
						    }
							outBean.setRetCode(ReturnConstant.SUCCESS);
						} catch (Exception e) {
							logger.error("回执处理异常", e);
							outBean.setRetCode(ReturnConstant.ERROR);
							outBean.setRetMsg(e.getMessage());
						}
					}
					
					//执行更新
					if(sucList.size()>0&&sucList.size()%500==0||failList.size()>0&&failList.size()%500==0||i==paramBean.getCallBackList().size()-1){
						if(sucList.size()>0){
							tripleNewCustomerDao.updateSendStatusSucBatch(sucList);
							sucList.clear();
						}
						if(failList.size()>0){
							tripleNewCustomerDao.updateSendStatusFailBatch(failList);
							failList.clear();
						}
					}
				}
			}
			
		}else if("A016".equals(paramBean.getMsgTypeId())){
			if(OsType.CX.value.equals(paramBean.getFromSys())){
				// A016:创新角色信息同步
				for(int i = 0;i<paramBean.getCallBackList().size();i++){
					IntRoleBean bean = new IntRoleBean();
					bean.setUniqueNum(paramBean.getCallBackList().get(i).getUniqueNum());
					bean.setSendStatus(paramBean.getCallBackList().get(i).getState());
					try {
						this.updateRoleSendStatus(bean);
						outBean.setRetCode(ReturnConstant.SUCCESS);
					} catch (Exception e) {
						logger.error("回执处理异常", e);
						outBean.setRetCode(ReturnConstant.ERROR);
						outBean.setRetMsg(e.getMessage());
					}
				}
			}
		}
		logger.info("【三网结束接收sendType为"+paramBean.getFromSys()+" TypeId为"+paramBean.getMsgTypeId()+"的回执，时间是"+ new Date() +"】");
		return outBean;
	}
	
	/**
	 * 客户理财经理变更修改发送状态
	 * 2016年4月21日
	 * By 胡体勇
	 * @param bean
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
    public int updateSendStatusDao(IntCustomerBean bean){
		return tripleNewCustomerDao.updateSendStatus(bean);
    }
	
	/**
	 * 组织机构信息同步修改发送状态
	 * 2016年4月21日
	 * By 胡体勇
	 * @param bean
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int updateOrgSendStatus(IntOrgBean bean){
		return tripleOrgSyncDao.updateSendStatus(bean);
	}
	/**
	 * 客户沟通记录信息同步修改发送状态
	 * 2016年7月21日
	 * By 张新民
	 * @param bean
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int updateLoanAdkSendStatus(IntLoanAskBean bean){
		return tripleLoanAskSyncDao.updateSendStatus(bean);
	}
	/**
	 * 角色同步修改发送状态
	 * 2016年7月21日
	 * By 张新民
	 * @param bean
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int updateRoleSendStatus(IntRoleBean bean){
		return tripleRoleSyncDao.updateSendStatus(bean);
	}
	
	/**
	 * 
	 * 2016年4月21日
	 * By 胡体勇
	 * @param bean
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int updateEmployeeSendStatus(IntEmployeeBean bean){
		return tripleEmployeeSyncDao.updateSendStatus(bean);
	}
	
	/**
	 * 
	 * 2016年8月3日
	 * By 胡体勇
	 * @param bean
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int updateUserSendStatus(IntEmployeeBean bean){
		return tripleUserSyncDao.updateSendStatus(bean);
	}
	
  }
