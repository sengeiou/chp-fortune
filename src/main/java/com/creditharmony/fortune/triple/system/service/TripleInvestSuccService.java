package com.creditharmony.fortune.triple.system.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.core.fortune.type.ForApplyStatus;
import com.creditharmony.core.fortune.type.ProductType;
import com.creditharmony.fortune.app.webservice.ocr.utils.StringUtil;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyPoolDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.entity.Product;
import com.creditharmony.fortune.lend.apply.manager.LendApplyManager;
import com.creditharmony.fortune.triple.system.constant.Constant;
import com.creditharmony.fortune.triple.system.dao.TripleInvestMapper;
import com.creditharmony.fortune.triple.system.entity.IntInvestBean;
import com.creditharmony.fortune.triple.system.facade.TripleInvestSuccFacade;
import com.creditharmony.fortune.utils.SerialNum;

@Service
public class TripleInvestSuccService {
	protected Logger logger = LoggerFactory.getLogger(TripleInvestSuccFacade.class);

	@Autowired
	private TripleInvestMapper tripleInvestMapper;

	@Autowired
	private LoanApplyDao loanApplyDao;
	
	@Autowired
	private LendApplyManager lendApplyManager;
	
	@Autowired
	private BackMoneyPoolDao backMoneyPoolDao;

	public void investSucc(String customerCode, String lendCode, String type) {
		try {
			logger.debug("【推送成单信息到CRM系统】");
			
			DecimalFormat df = new DecimalFormat("0.##");
			
			String empCode = "", empName = "" ;				//理财经理工号，理财经理姓名
			
			IntInvestBean intInvestBean = new IntInvestBean();
			
			LoanApply loanApply = new LoanApply();
			loanApply.setApplyCode(lendCode);
			loanApply = (LoanApply) this.loanApplyDao.get(loanApply);
			
			String status = loanApply.getStatus();
			if(ForApplyStatus.TQSH.value.equals(status)){
				intInvestBean.setIsAdvanced("1");	/** 提前赎回状态 */
			} else {
				intInvestBean.setIsAdvanced("2");	/** 非提前赎回状态 */
			}
			
			if(StringUtil.isEmpty(customerCode)){
				customerCode = loanApply.getCustCode();
			}
			//增加重复推送判断 如果 lendCode type osType  status 重复 则 取消推送 
			List<IntInvestBean> list =tripleInvestMapper.getInvestByIdOpOs(lendCode, type, "XH_CHP",intInvestBean.getIsAdvanced());
			if(null!=list&&list.size()>0){
				logger.info("推送到CRM的成单信息已经存在,不做重复推送。【lendCode->"+lendCode+";type->"+type+";isadvanced->"+intInvestBean.getIsAdvanced()+"】");
				return;
			}
			
			Product product = this.loanApplyDao.getProduct(loanApply.getProductCode());	/** 产品信息 */
			
			String firstOrderStatus = this.tripleInvestMapper.getFirstOrderStatus(customerCode); /** 是否为客户的首单 */
			
			if("1".equals(firstOrderStatus)){
				intInvestBean.setIsFirstOder("2");					/** 不是首单 */
			} else {
				intInvestBean.setIsFirstOder("1");					/** 是首单 */
			}
			
			intInvestBean.setOperation(type);						/** 操作类型：(I:成单， O:到期， D:交割) */
			
			intInvestBean.setOsId(loanApply.getCustCode());			/** 客户编号 */
			
			intInvestBean.setOsType("XH_CHP");						/** 系统类别 */
			
			intInvestBean.setInvCode(loanApply.getApplyCode());		/** 出借编号 */
			
			intInvestBean.setInvId( loanApply.getApplyCode() );		/**		*/
			
			if("2".equals(product.getProductTypeCode())){
				intInvestBean.setProType( ProductType.getProductType( "2" ) );	/** 产品类型 */
			} else {
				intInvestBean.setProType( ProductType.getProductType( "1" ) );	/** 产品类型 */
			}
			
			intInvestBean.setLoanType(product.getProductName());	/** 产品名称 */
			
			intInvestBean.setPeriodsNo(product.getProductPeriods() + "");	/** 产品期数 */
			
			intInvestBean.setAnnualYield( df.format(lendApplyManager.getRate(loanApply))); 	/** 年收益率 */
			
			intInvestBean.setInvMoney( df.format(loanApply.getLendMoney()));		/** 投资金额 */
			
			//增加判断如果出借的成单时间为空则证明是内转，内转查询回款申请的创建时间作为成单时间
			if(null==loanApply.getRealDeductTime()){
				BackMoneyPool bmps = backMoneyPoolDao.getByLendCode(lendCode);
				if(null!=bmps&&bmps.getCreateTime()!=null){
					intInvestBean.setCompDate(bmps.getCreateTime() );		/** 成单日期 */
				}
			}else{
				intInvestBean.setCompDate(loanApply.getRealDeductTime());		/** 成单日期 */
			}
			
			intInvestBean.setExpireDate(loanApply.getExpireDate());			/** 到期日期 **/
			
			intInvestBean.setBillDate(loanApply.getBillDay()+"");			/** 账单日 */
			
			Map<String, String> managerInfo = this.tripleInvestMapper.getManagerInfo1(loanApply.getManagerCode());
			empCode = managerInfo.get("userCode");
			empName = managerInfo.get("name");
			if(StringUtil.isEmpty(empCode) && StringUtil.isEmpty(empName)){	//对错误数据容错，第一次是和t_gl_user中的login_name匹配，如果匹配不上，再和user_code匹配
				managerInfo = this.tripleInvestMapper.getManagerInfo2(loanApply.getManagerCode());
				empCode = managerInfo.get("userCode");
				empName = managerInfo.get("name");
			}
			
			intInvestBean.setEmpCode( empCode );			/** 理财经理工号 */
			intInvestBean.setEmpName( empName );						/** 理财经理姓名 */
			
			intInvestBean.setCreateTime( loanApply.getCreateTime() );	/** 出借创建时间 */
			
			intInvestBean.setSendType(Constant.SYSNCNUM_KHCDTB);			/** 发送类别 */
			
			intInvestBean.setOperation( type );								/** 操作类型 */
			
			intInvestBean.setInfoCreateTime(new Date());					/** 消息创建时间 */
			
			intInvestBean.setSendStatus( Constant.SEND_STATUS_FAIL );		/** 消息默认设置为待发状态 */
			
			intInvestBean.setUniqueNum(SerialNum.getSerialNum());			/** 消息唯一编号 */
			
			//增加预期回款金额 张新民  20170218
			String expBackMoney = this.tripleInvestMapper.getExpBackMoney(lendCode);
			if(StringUtil.isEmpty(expBackMoney)){
				expBackMoney = "0" ;
			}
			double expBack = Double.parseDouble(expBackMoney) ;
			intInvestBean.setExpReturn(df.format(expBack));
			//增加预期回款金额（结束）张新民  20170218
			
			
			if ("O".equals(type)) {
				String realBackInterest = this.tripleInvestMapper.getRealBackInterest(lendCode);
				if(StringUtil.isEmpty(realBackInterest)){
					realBackInterest = "0" ;
				}
				double realBack = Double.parseDouble(realBackInterest) + loanApply.getLendMoney().doubleValue();
				intInvestBean.setActReturn(df.format(realBack));
			}
			
			this.tripleInvestMapper.insertIntInvest(intInvestBean);			/** 将待发成单消息插入到表中 */
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug( e.getStackTrace() + "" );
			logger.debug("【推送成单信息到CRM系统失败,失败原因：" +e.getStackTrace()+ "】");
		}
	}
}
