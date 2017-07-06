package com.creditharmony.fortune.contract.web;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.excel.util.ExportExcel;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.contract.constant.Constant;
import com.creditharmony.fortune.contract.service.ContractManager;
import com.creditharmony.fortune.contract.service.ContractStatisticsManager;
import com.creditharmony.fortune.contract.view.ContractStatisticsExcelView;
import com.creditharmony.fortune.contract.view.ContractStatisticsParamView;
import com.creditharmony.fortune.contract.view.ContractStatisticsView;

/**
 * 合同统计
 * 
 * @Class Name ContractStatisticsList
 * @author 李放
 * @Create In 2015年12月11日
 */
@Controller
@RequestMapping("${adminPath}/contract")
public class ContractStatisticsList extends BaseController {

	@Autowired
	private ContractStatisticsManager contractStatisticsManager;
	
	@Autowired
	private ContractManager contractManager;

	/**
	 * 统计列表 
	 * 2016年1月8日 
	 * @param contractStatistics
	 * @param page
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/statisticsList")
	public String statisticsList(
			@ModelAttribute("contractStatistics") ContractStatisticsParamView contractStatistics,HttpServletRequest request, HttpServletResponse response
			, Model m) {
		
		
		if(contractStatistics.getStart() == null || null == contractStatistics.getEnd()){
			  Calendar date = Calendar.getInstance();
			  Calendar s = Calendar.getInstance();
			  s.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), 1);
			  Date d=s.getTime();
			  contractStatistics.setStart(d);
			  contractStatistics.setEnd(new Date());
		}
		Page<ContractStatisticsView> pageview = contractStatisticsManager
				.listStatisticsData(new Page<ContractStatisticsView>(request,response), contractStatistics);
		m.addAttribute("page", pageview).addAttribute("contractStatistics",
				contractStatistics);
		return "contract/statistics";
	}
	
	/**
	 * 导出统计数据
	 * @param contractStatistics 查询条件
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/statisticsList/exportOrders")
	public String exportOrders(
			@ModelAttribute("contractStatistics") ContractStatisticsParamView contractStatistics,
			HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			StringBuffer fileName = new StringBuffer(
					Constant.CONTRACT_CHANGE_STATISTICS_PREFIX);
			fileName.append(DateUtils.getDate(Constant.DATE_YYYYMMDDMMSS))
					.append(Constant.CONTRACT_EXCEL_SUFFIX);
			List<ContractStatisticsExcelView> list = contractStatisticsManager
					.listStatisticsExcelData(contractStatistics);
			ExportExcel exl = new ExportExcel(
					Constant.CONTRACT_CHANGE_STATISTICS_PREFIX,
					ContractStatisticsExcelView.class);
			exl.setDataList(list);
			exl.write(response, fileName.toString());
			exl.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出excel失败！失败信息：" + e.getMessage());
		}
		return null;
	}
	
}