package com.creditharmony.fortune.back.priority.examine.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.PriorityBack;
import com.creditharmony.core.fortune.type.PriorityBackState;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.mybatis.paginator.domain.Order;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.money.common.view.ItemView;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.priority.common.dao.PriorityBackMoneyDao;
import com.creditharmony.fortune.back.priority.common.dao.PriorityBackMoneyListDao;
import com.creditharmony.fortune.back.priority.common.dao.PriorityDetailDao;
import com.creditharmony.fortune.back.priority.common.service.PriorityLogManager;
import com.creditharmony.fortune.back.priority.common.view.PriorityBackLog;
import com.creditharmony.fortune.back.priority.common.view.PriorityDetailItem;
import com.creditharmony.fortune.back.priority.common.view.PriorityListItemView;
import com.creditharmony.fortune.back.priority.constant.PbmConstant;
import com.creditharmony.fortune.back.priority.examine.dao.PriorityExcamineDao;
import com.creditharmony.fortune.back.priority.utils.PriDateUtils;
import com.creditharmony.fortune.common.service.CheckManager;

/**
 * 优先回款审批Service
 * @Class Name PriorityExamine
 * @author 郭强
 * @Create In 2017年3月30日
 */
@Service
public class PriorityExamine extends CoreManager<PriorityBackMoneyListDao, PriorityListItemView>{
	
	@Autowired
	private PriorityBackMoneyListDao listDao;
	@Autowired
	private PriorityDetailDao detailDao;
	@Autowired
	private PriorityExcamineDao pedao;
	@Autowired
	private PriorityLogManager priorityLogManager;
	@Autowired
	private CheckManager  checkmanager;
	@Autowired
	private PriorityBackMoneyDao priorityBackMoneyDao;
	/**
	 * 获取列表页对象 
	 * 2017年3月30日
	 * By 郭强
	 * @param page
	 * @param view
	 * @return
	 */
	public Page<PriorityListItemView> findItemList(Page<PriorityListItemView> page,
			PriorityListItemView view) {
		String  orderstr=PbmConstant.PCT;
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize(), Order.formString(orderstr));
		pageBounds.setCountBy("priorityId");
		PageList<PriorityListItemView> dataList = (PageList<PriorityListItemView>) pedao.findByParams(view, pageBounds);
		PageUtil.convertPage(dataList, page);
		return page;
	}

	/**
	 * 查询优先回款审批详情
	 * 2017年3月28日
	 * By 郭强
	 * @param priorityId
	 * @return
	 */
	public PriorityDetailItem getDetail(String priorityId) {
		
		return detailDao.getDetail(priorityId);
	}

	/**
	 *优先回款审批
	 * 2017年3月27日
	 * By 郭强
	 * @param model
	 * @param priorityId
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String examineConfirm(PriorityDetailItem view) {
		StringBuffer sb = new StringBuffer();
		Date date = new Date();
		String userId = UserUtils.getUserId();
		if(("1").equals(view.getCheckExaminetype())){
			view.setPriorityBackState(PriorityBackState.SPTG.value);
		}else if(("2").equals(view.getCheckExaminetype())){
			view.setPriorityBackState(PriorityBackState.SPWTG.value);
			view.setRebackFlag("1");
		}
		
		view.setCheckTime(date);
		
		int i = pedao.updateExamine(view);
		if(i>0){
			PriorityBackLog log = new PriorityBackLog();
			log.setPriorityBackState(view.getPriorityBackState());
			log.setPriorityId(view.getPriorityId());
			log.setCheckById(userId);
			log.setCheckTime(date);
			log.setCheckExamine(view.getCheckExamine());
			log.setCheckExaminetype(view.getCheckExaminetype());
			log.setApplyBy(view.getApplyBy());
			log.setApplyTime(view.getApplyTime());
			log.setCreateBy(view.getCreateBy());
			log.setCreateTime(view.getCreateTime());
			log.setModifyBy(userId);
			log.setModifyTime(date);
			priorityLogManager.addPriorityLog(log);
			if(("1").equals(view.getCheckExaminetype())){
				checkmanager.addCheck(view.getLendCode(), "审批通过", "优先回款审批",userId );
			}else if(("2").equals(view.getCheckExaminetype())){
				checkmanager.addCheck(view.getLendCode(), "审批未通过", "优先回款审批",userId );
			}
			//审批通过修改回款表
			if(("1").equals(view.getCheckExaminetype())){
				updateBackMoney(view.getPriorityId());
			}
		}else{
			sb.append("操作失败");
		}
		return sb.toString();
	}
	
	/**
	 * 修改回款表
	 * @param string
	 */
	private void  updateBackMoney(String string){
		/*
		 * 优先回款申请审批通过后, 我的申请-回款申请列表中的【实际回款金额】即发生变化，根据月利率0.5%进行重新计算，此时应回款金额和实际回款金额就不一致；
		 * 月息通	信和月增  实际回款金额=本金*0.5%/到期日所在月的天数*（到期日-最后一个账单日的天数）+本金
		 * 非月息通 信和月增  实际回款金额=本金*0.5%*出借期限+本金
		 */
		ListItemView  listItem = priorityBackMoneyDao.searchBackMoney(string);
		ItemView  itemView = new ItemView();
		itemView.setPriorityBack(PriorityBack.SHI.value);
		itemView.setLendCode(listItem.getLendCode());
		//本金  
		BigDecimal applyLendMoney = new BigDecimal(listItem.getApplyLendMoney());
		//实际回款金额
		BigDecimal  backActualbackMoney = BigDecimal.ZERO;
		//到期日所在月的天数
		int  datenum = PriDateUtils.getMonthDays(listItem.getFinalLinitDate());
		//到期日-最后一个账单日的天数
		int  num = PriDateUtils.datenum(listItem.getFinalLinitDate() ,listItem.getApplyBillday());
		//出借期限(产品期数)
		int  lendnum = listItem.getProductPeriods();
		if(ProductCode.YXT.value.equals(listItem.getProductCode() ) || ProductCode.XHYZ.value.equals(listItem.getProductCode() ) ){
			backActualbackMoney=applyLendMoney.multiply(BigDecimal.valueOf(0.005))
					.divide(BigDecimal.valueOf(datenum),10, BigDecimal.ROUND_HALF_UP)
					.multiply(BigDecimal.valueOf(num))
					.add(applyLendMoney).setScale(6, BigDecimal.ROUND_HALF_UP);
			itemView.setBackActualbackMoneyBig(backActualbackMoney);		
		}else{
			backActualbackMoney=applyLendMoney.multiply(BigDecimal.valueOf(0.005))
					.multiply(BigDecimal.valueOf(lendnum))
					.add(applyLendMoney).setScale(6, BigDecimal.ROUND_HALF_UP);
			itemView.setBackActualbackMoneyBig(backActualbackMoney);	
		}
		priorityBackMoneyDao.updateBackMoney(itemView);
	}
	
}
