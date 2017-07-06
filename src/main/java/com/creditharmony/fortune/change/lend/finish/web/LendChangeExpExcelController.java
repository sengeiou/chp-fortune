package com.creditharmony.fortune.change.lend.finish.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.change.lend.apply.entity.LendQueryView;
import com.creditharmony.fortune.change.lend.finish.manager.LendChangeFinishManager;
import com.creditharmony.fortune.template.entity.LendChangeOutExcel;

/**
 * 出借人查询导出列表Controller类
 * @Class Name LenderChangeExpExcelController
 * @author 刘雄武
 * @Create In 2016年3月1日
 */
@Controller
@RequestMapping({ "${adminPath}/lendChangeExpExcel" })
public class LendChangeExpExcelController extends BaseController {

	@Autowired
	private LendChangeFinishManager lcService;


	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/expExcel")
	public void expExcel(HttpServletRequest request,HttpServletResponse response,
			LendQueryView query,String applyIds) {
		List<LendChangeOutExcel> list = null;
		// 选中条数导出记录
		if (applyIds != null && !"".equals(applyIds)) {
			String[] applys = applyIds.split(",");
			List<String> codes = null;
			if (applys.length > 0) {
				codes = Arrays.asList(applys);
			}
			LendQueryView ext = new LendQueryView();
			ext.setApplyIds(codes);
			// 按选中出借编号导出
			list = lcService.findListExportModel(ext,response);
		} else {
			if (query.getOrgCode() != null && !"".equals(query.getOrgCode())) {
				String[] applys = query.getOrgCode().split(",");
				List<String> codes = null;
				if (applys.length > 0) {
					codes = Arrays.asList(applys);
				}
				query.setOrgIds(codes);
				}
			// 按条件导出excel
			list = lcService.findListExportModel(query,response);
		}
		// 导出excel
//		FileUtil.exportExcel(list, LendChangeOutExcel.class,
//				"出借信息变更", response);
//		ExportExcel ee = new ExportExcel();
//		ee.LendExportData(response, ExportField.lendChange_title, "出借信息变更", list);
	}
	
	

}