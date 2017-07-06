package com.creditharmony.fortune.triple.system.facade;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.utils.SpringUtil;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.triple.system.dao.TripleNewCustomerDao;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.triple.system.service.TripleCheckInfoService;
import com.creditharmony.fortune.triple.system.service.TripleInvestSuccService;

/**
 * @Class Name SendTripleInfo
 * @author 胡体勇
 * @Create In 2016年5月16日
 */
@Service
public class SendTripleInfoFacade {
	
	/**日志对象*/
	protected Logger logger = LoggerFactory.getLogger(SendTripleInfoFacade.class);
	@Autowired
	private TripleCheckInfoService tripleCheckInfoService;
	
	@Autowired
	private TripleInvestSuccService tripleInvestSuccService ;
	
	@Autowired
	private TripleNewCustomerDao dao;
    
	/**
	 * 划扣成功验证三网首单
	 * 2016年5月16日
	 * By 胡体勇
	 * @param customerCode
	 */
	public void tripleInfo(String customerCode, String lendCode){
		
		try {
			tripleInvestSuccService.investSucc(customerCode, lendCode, "I");
		} catch (Exception e) {
			logger.error("客户编号为："+customerCode+"的客户发送CRM成单信息失败！",e);
			FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
			FortuneException forException = new FortuneException();
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setCreateBy("往CRM系统发成单信息");
			forException.setCreateTime(new Date());
			forException.setId(IdGen.uuid());
			forException.setModifyBy("往CRM系统发成单信息");
			forException.setModifyTime(new Date());
			forDao.insert(forException);
		}
		
		// 三网首单校
		List<IntDeliveryEx> exList = this.findLendApply(customerCode);
		if (exList.size() == 1) {
			try {
				tripleCheckInfoService.sendTripleInfo(customerCode);
			} catch (Exception e) {
				logger.error("客户编号为："+customerCode+"的客户发送三网首单验证失败！",e);
				FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(
						FortuneExceptionDao.class);
				FortuneException forException = new FortuneException();
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setCreateBy("三网首单验证");
				forException.setCreateTime(new Date());
				forException.setId(IdGen.uuid());
				forException.setModifyBy("三网首单验证");
				forException.setModifyTime(new Date());
				forDao.insert(forException);
			}
		}else{
			logger.debug( "客户编号为：" +customerCode+ "的客户之前已经做过首单！" );
		}
	}
	
	/**
	 * 根据出借编号查询出借成功次数
	 * 2016年6月15日
	 * By 胡体勇
	 * @param lendCode
	 * @return
	 */
	public List<IntDeliveryEx> findLendApply(String customerCode) {
		IntDeliveryEx ex = new IntDeliveryEx();
		ex.setCustomerCode(customerCode);
		return dao.findLendApply(ex);
	}

	/**
	 * 根据手机号查询EmpCode 
	 * 2016年9月12日 
	 * By 陈晓强
	 * @param phone
	 * @return
	 */
	public String getEmpCode(String phone) {
		String res = "";
		if (StringUtils.isNotEmpty(phone)) {
			List<String> empCodes = dao.findEmpCodeByPhone(StringUtils.trim(phone));
			if (ArrayHelper.isNotEmpty(empCodes)) {
				res = empCodes.get(0);
			}
		}
		return res;
	}
}
