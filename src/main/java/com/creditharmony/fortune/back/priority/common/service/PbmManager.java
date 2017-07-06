package com.creditharmony.fortune.back.priority.common.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.EffectiveFlag;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.PriorityBack;
import com.creditharmony.core.mybatis.paginator.domain.Order;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.money.common.view.ItemView;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.priority.common.dao.PriorityBackMoneyDao;
import com.creditharmony.fortune.back.priority.common.dao.PriorityBackMoneyListDao;
import com.creditharmony.fortune.back.priority.common.dao.PriorityDetailDao;
import com.creditharmony.fortune.back.priority.common.view.PriorityDetailItem;
import com.creditharmony.fortune.back.priority.common.view.PriorityListItemView;
import com.creditharmony.fortune.back.priority.constant.PbmConstant;
import com.creditharmony.fortune.common.entity.Attachment;

/**
 * 优先回款共通Service
 * @Class Name PbmManager
 * @author 郭强
 * @Create In 2017年3月27日
 */
@Service
public class PbmManager extends CoreManager<PriorityBackMoneyListDao, PriorityListItemView>{
	
	@Autowired
	private PriorityBackMoneyListDao listDao;
	@Autowired
	private PriorityDetailDao detailDao;
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
		String sortString = PbmConstant.BACK_MONEY_DAY_ASC;
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize(), Order.formString(sortString));
		pageBounds.setCountBy("priorityId");
		//获取数据权限
		String dataRights = DataScopeUtil.getDataScope("a", SystemFlag.FORTUNE.value);
		logger.info("加载出借申请查看列表，所赋予权限是：" + dataRights);
		if(StringUtils.isNotEmpty(dataRights)){
			view.setDataRights(dataRights);
		}
		String userId = UserUtils.getUserId();
		view.setUserId(userId);
		PageList<PriorityListItemView> dataList = (PageList<PriorityListItemView>) listDao.findByParams(view, pageBounds);
		PageUtil.convertPage(dataList, page);
		return page;
	}

	/**
	 * 查询优先回款申请详情
	 * 2017年3月28日
	 * By 郭强
	 * @param priorityId
	 * @return
	 */
	public PriorityDetailItem getDetail(String priorityId) {
		
		return detailDao.getDetail(priorityId);
	}

	/**
	 * 获取要操作的数据
	 * @param view
	 * @return
	 */
	public List<PriorityDetailItem> getPriortyList(PriorityListItemView view) {
		
		return detailDao.getPriortyList(view);
	}

	/**
	 * 获取附件
	 */
	public List<Attachment> findFileList(String code, String tableId,
			String tableName) {
		// TODO Auto-generated method stub
		Map<String , Object>  map = new HashMap<String, Object>();
		map.put("loanCode", code);
		map.put("attaTableId", Arrays.asList(tableId.split(",")));
		map.put("attaFileOwner",  Arrays.asList(tableName.split(",")));
		map.put("isDiscard", EffectiveFlag.yx.value);
		return detailDao.findFileList(map);
	}

	/**
	 * 修改回款表, 将优先回款状态和 实际回款金额修改
	 * @param priorityId
	 */
	public void updateRepealBackMoney(String priorityId) {
		// TODO Auto-generated method stub
		ListItemView item = priorityBackMoneyDao.searchBackMoney(priorityId);
		ItemView itemView = new ItemView();
		itemView.setPriorityBack(PriorityBack.FOU.value);
		itemView.setBackActualbackMoney(item.getBackMoney());
		itemView.setLendCode(item.getLendCode());
		priorityBackMoneyDao.updateBackMoney(itemView);
	}

}
