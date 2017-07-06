package com.creditharmony.fortune.lend.finish.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.adapter.bean.in.dirswitch.DjrSwitchSendUserInBean;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.customer.entity.LoanApply;

/**
 * @Class Name LoanApplyDao
 * @author 孙凯文
 * @Create In 2015年12月2日
 */
@FortuneBatisDao
public interface LendFinishDao extends CrudDao<LoanApply> {

	/**
	 * 获取出借列表 2015年12月30日
	 * 
	 * @author 孙凯文
	 * @param map
	 * @return
	 */
	public PageList<LoanApply> findLendApply(Map<String, Object> map, PageBounds pb);

	/**
	 * 获取转投大金融审批列表
	 */
	public PageList<LoanApply> findLendApplyToDJRChk(Map<String, Object> map, PageBounds pb);
	public PageList<LoanApply> getLendApplyToDJRChkExcel(Map<String, Object> map, PageBounds pb);

	/**
	 * 获取出借列表(用于出借申请中，内部转账列表查询)
	 * 
	 * @param customerCode
	 * @return List<LoanApply>
	 */
	public List<LoanApply> getLoanApplyList(Map<String, Object> params);
	
	/**
	 * 新增出借申请-付款方式为 自转的选择项列表
	 * @author xurongsheng
	 * @date 2017年2月4日 上午10:42:04
	 * @param params
	 * @return
	 */
	public List<LoanApply> getLoanApplyList4ZZ(Map<String, Object> params);

	public String findLendApplyTotalMoney(Map<String, Object> map);

	public DjrSwitchSendUserInBean getLendApplyDJR(String lendCode);

	public int updateSwitchApproveStatus(LoanApply loanApply);
	
	public String getLendApplySwitchApproveStatus(String lendCode);
}
