package com.creditharmony.fortune.look.approve.constants;

import java.util.HashMap;
import java.util.Map;

import com.creditharmony.core.fortune.type.LendState;

/**
 * 审批关常量
 * @Class Name ApproveLendStateConstant
 * @author 朱杰
 * @Create In 2016年6月18日
 */
public class ApproveLendStateConstant {

	
	
	
	public static Map<String,String[]> approveLendState = new HashMap<String,String[]>();
	;
	/**
	 * 读取配置文件
	 */
	static{
		/**
		 * 待审批
		 */
		approveLendState.put(LendState.DCJSP.value, new String[]{
				LendState.JZHDKH.value,
				LendState.DFJSC.value,
				LendState.DCJSP.value,
				LendState.JZHKHSB.value,
				LendState.WJHCZ.value,
				LendState.WJHCSB.value

		});
		
		/**
		 * 审批通过
		 */
		approveLendState.put(LendState.SPTG.value, new String[]{
				LendState.SPTG.value,
				LendState.CX.value,
				LendState.KHFQ.value,
				LendState.DHK.value,
				LendState.HKCLZ.value,
				LendState.HKCG.value,
				LendState.HKSB.value
		});
		/**
		 * 审批不通过
		 */
		approveLendState.put(LendState.SPBTG.value, new String[]{
				LendState.SPBTG.value
		});
	}

}
