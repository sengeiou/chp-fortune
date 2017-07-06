package com.creditharmony.fortune.change.lender.apply.workflow.initdata;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.InitViewData;
import com.creditharmony.bpm.frame.face.base.BaseService;
import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.fortune.change.lender.apply.service.LenderChangeApplyManager;

/**
 * 初始化申请表单
 * @Class Name LaunchLenderChangeInit
 * @author 郭才林
 * @Create In 2015年12月2日
 */
@Service("init_cf_lenderChange_applyForm")
public class LaunchLenderChangeInit extends BaseService implements InitViewData
{
  @Autowired
  private LenderChangeApplyManager applyService;


  /**
   * 初始化业务数据（出借人信息变更申请表单）
   * 2015年12月2日
   * By 郭才林
   * @param arg
   * @return
   */
  @SuppressWarnings("rawtypes")
  public BaseBusinessView initViewData(Map arg)
  {	
	  
	  String[] arr= (String[])arg.get("custCode");
	  String customerCode=arr[0];
	  return applyService.getCustInfo(customerCode);// 根据客户编号查询客户信息
  }
  
  
}