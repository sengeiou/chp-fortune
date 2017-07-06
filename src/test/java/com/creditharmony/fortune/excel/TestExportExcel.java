package com.creditharmony.fortune.excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.fortune.look.apply.dao.LendApplyLookDao;
import com.creditharmony.fortune.look.apply.entity.LendApplyLookExportLendExcelEx;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class TestExportExcel {
	@Autowired
	private LendApplyLookDao lendApplyLookDao;
	@Test
	public void testExportExcel() {
		Map<String, Object> exportListParam = new HashMap<String, Object>();
		String dataRights = DataScopeUtil.getDataScope("app", SystemFlag.FORTUNE.value);
		if(StringUtils.isNotEmpty(dataRights)){
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			exportListParam.put("sqlMap", sqlMap);
		}
//		List<LendApplyLookExportLendExcelEx> list = lendApplyLookDao.getExportLendExcelList(exportListParam);
		
//		System.out.println(list == null ? null : list.size());
	}
}
