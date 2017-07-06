package com.creditharmony.fortune.change.lender.query.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.LendchgType;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.fortune.change.lender.apply.dao.LenderChangeDao;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerEx;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerExSearch;
import com.creditharmony.fortune.change.lender.apply.view.LenderQueryView;
import com.creditharmony.fortune.change.lender.excel.CompareChange;
import com.creditharmony.fortune.change.lender.excel.ExportField;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.template.entity.LenderChangerOutExcel;
import com.google.common.collect.Lists;

/**
 * 出借人信息变更查询manager类
 * @Class Name LenderChangeApplyApply
 * @author 郭才林
 * @Create In 2016年4月12日
 */
@Service
public class LenderChangeQueryManager extends CoreManager<LenderChangeDao, CustomerEx> {
	
	@Autowired
	private LenderChangeDao lcDao;
	
	@Autowired
	private CheckManager checkManager;
	

	/**
	 * 出借人信息变更查询
	 * 2015年12月11日
	 * By 郭才林
	 * @param query
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public Page<CustomerExSearch> queryList(Page<CustomerExSearch> page, LenderQueryView query) {
	
		String dataRights = DataScopeUtil.getDataScope("a", SystemFlag.FORTUNE.value);
		if(StringUtils.isNotEmpty(dataRights)){
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			query.setSqlMap(sqlMap);
		}
		query.setDictChangeType(LendchgType.LENDER_CHG.value);
		query.setChangeTypePhone(LendchgType.TRUSTESSHIP_PHONE_CHA.value);
		query.setChangeTypeAccOff(LendchgType.TRUSTESSHIP_CANCELLATION.value);
		PageBounds pageBounds=new PageBounds(page.getPageNo(), page.getPageSize());
		pageBounds.setCountBy("applyId");
		PageList<CustomerExSearch> pageList = (PageList<CustomerExSearch>)this.lcDao.queryList(query,pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 导出列表
	 * 2016年3月1日
	 * By 刘雄武
	 * @param customerEx
	 * @return
	 */
	public List<LenderChangerOutExcel> findListExportModel(LenderQueryView query,
			HttpServletResponse response) {
		List<LenderChangerOutExcel> lenderChangerOutExcelList=Lists.newArrayList();
		if(query == null){
			query = new LenderQueryView();
		}
		query.setDictChangeType(LendchgType.LENDER_CHG.value);
		query.setChangeTypePhone(LendchgType.TRUSTESSHIP_PHONE_CHA.value);
		query.setChangeTypeAccOff(LendchgType.TRUSTESSHIP_CANCELLATION.value);
		
		String dataRights = DataScopeUtil.getDataScope("a", SystemFlag.FORTUNE.value);
		if(StringUtils.isNotEmpty(dataRights)){
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			query.setSqlMap(sqlMap);
		}
		
		CompareChange compareChange = new CompareChange();
		compareChange.exportLenderData(query, response, ExportField.lenderChange_title,
				ExportField.lenderChange_sql, "出借人信息变更");
//		List<CustomerExSearch> lenderExcel = lcDao.queryExport(query);
		// 检索出列表
		/*List<LenderChangeExcel> lenderChangeExcelList = new ArrayList<LenderChangeExcel>();

		int ExcelLength = lenderExcel.size();
		for (int i = 0; i < ExcelLength; i++) {
			int j = 0;
			LenderInitView after = (LenderInitView) JsonMapper.fromJsonString(lenderExcel.get(i).getLenderAfter(), LenderInitView.class);
			LenderInitView begin = (LenderInitView) JsonMapper.fromJsonString(lenderExcel.get(i).getLenderBegin(), LenderInitView.class);
				if(!begin.getCustomer().getCustMobilephone().equals(after.getCustomer().getCustMobilephone())){
					LenderChangeExcel lenderExcelmodelA = new LenderChangeExcel();
					lenderExcelmodelA.setDictApprovalStartDate(lenderExcel.get(i).getDictApprovalStartDate());
					lenderExcelmodelA.setCustName(lenderExcel.get(i).getCustName());
					lenderExcelmodelA.setCustCode(lenderExcel.get(i).getCustCode());
					lenderExcelmodelA.setManagerName(lenderExcel.get(i).getManagerName());
					lenderExcelmodelA.setDepartmentName(lenderExcel.get(i).getDepartmentName());
					lenderExcelmodelA.setLenderAfter("客户手机号码：******");// 根据需求将客户手机号码改为*标记
					lenderChangeExcelList.add(lenderExcelmodelA);
				}
				if(!begin.getCustomer().getCustCertOrg().equals(after.getCustomer().getCustCertOrg())){	
					LenderChangeExcel lenderExcelmodelB = new LenderChangeExcel();
					lenderExcelmodelB.setDictApprovalStartDate(lenderExcel.get(i).getDictApprovalStartDate());
					lenderExcelmodelB.setCustName(lenderExcel.get(i).getCustName());
					lenderExcelmodelB.setCustCode(lenderExcel.get(i).getCustCode());
					lenderExcelmodelB.setManagerName(lenderExcel.get(i).getManagerName());
					lenderExcelmodelB.setDepartmentName(lenderExcel.get(i).getDepartmentName());
					lenderExcelmodelB.setLenderAfter("发证机关："+after.getCustomer().getCustCertOrg());
					lenderChangeExcelList.add(lenderExcelmodelB);
				}
				if(!begin.getCustomer().getCustEmail().equals(after.getCustomer().getCustEmail())){	
					LenderChangeExcel lenderExcelmodelC = new LenderChangeExcel();
					lenderExcelmodelC.setDictApprovalStartDate(lenderExcel.get(i).getDictApprovalStartDate());
					lenderExcelmodelC.setCustName(lenderExcel.get(i).getCustName());
					lenderExcelmodelC.setCustCode(lenderExcel.get(i).getCustCode());
					lenderExcelmodelC.setManagerName(lenderExcel.get(i).getManagerName());
					lenderExcelmodelC.setDepartmentName(lenderExcel.get(i).getDepartmentName());
					lenderExcelmodelC.setLenderAfter("客户邮箱地址：**@**.com");// 根据客户需求将邮箱用*代替
					lenderChangeExcelList.add(lenderExcelmodelC);
				}
				if(begin.getCustomer().getAddress()!=null){
					if((!begin.getCustomer().getAddress().getAddrProvince().equals(after.getCustomer().getAddress().getAddrProvince()))
							||(!begin.getCustomer().getAddress().getAddrCity().equals(after.getCustomer().getAddress().getAddrCity()))
							||(!begin.getCustomer().getAddress().getAddrDistrict().equals(after.getCustomer().getAddress().getAddrDistrict()))){
						String addrs = "邮编："+after.getCustomer().getAddress().getAddrPostalCode()+" 省市："+
						OptionUtil.getProvinceLabel(after.getCustomer().getAddress().getAddrProvince())+" "+
						OptionUtil.getCityLabel(after.getCustomer().getAddress().getAddrCity())+" "+
						OptionUtil.getDistrictLabel(after.getCustomer().getAddress().getAddrDistrict());

						LenderChangeExcel lenderExcelmodelD = new LenderChangeExcel();
						lenderExcelmodelD.setDictApprovalStartDate(lenderExcel.get(i).getDictApprovalStartDate());
						lenderExcelmodelD.setCustName(lenderExcel.get(i).getCustName());
						lenderExcelmodelD.setCustCode(lenderExcel.get(i).getCustCode());
						lenderExcelmodelD.setManagerName(lenderExcel.get(i).getManagerName());
						lenderExcelmodelD.setDepartmentName(lenderExcel.get(i).getDepartmentName());
						lenderExcelmodelD.setLenderAfter(addrs);
						lenderChangeExcelList.add(lenderExcelmodelD);
					}
				}else{
					String addrs = "邮编："+after.getCustomer().getAddress().getAddrPostalCode()+" 省市："+
							OptionUtil.getProvinceLabel(after.getCustomer().getAddress().getAddrProvince())+" "+
							OptionUtil.getCityLabel(after.getCustomer().getAddress().getAddrCity())+" "+
							OptionUtil.getDistrictLabel(after.getCustomer().getAddress().getAddrDistrict());

					LenderChangeExcel lenderExcelmodelE = new LenderChangeExcel();
					lenderExcelmodelE.setDictApprovalStartDate(lenderExcel.get(i).getDictApprovalStartDate());
					lenderExcelmodelE.setCustName(lenderExcel.get(i).getCustName());
					lenderExcelmodelE.setCustCode(lenderExcel.get(i).getCustCode());
					lenderExcelmodelE.setManagerName(lenderExcel.get(i).getManagerName());
					lenderExcelmodelE.setDepartmentName(lenderExcel.get(i).getDepartmentName());
					lenderExcelmodelE.setLenderAfter(addrs);
					lenderChangeExcelList.add(lenderExcelmodelE);
				}
				if(!begin.getEmer().getEmerName().equals(after.getEmer().getEmerName())){
					LenderChangeExcel lenderExcelmodelF = new LenderChangeExcel();
					lenderExcelmodelF.setDictApprovalStartDate(lenderExcel.get(i).getDictApprovalStartDate());
					lenderExcelmodelF.setCustName(lenderExcel.get(i).getCustName());
					lenderExcelmodelF.setCustCode(lenderExcel.get(i).getCustCode());
					lenderExcelmodelF.setManagerName(lenderExcel.get(i).getManagerName());
					lenderExcelmodelF.setDepartmentName(lenderExcel.get(i).getDepartmentName());
					lenderExcelmodelF.setLenderAfter("紧急联系人姓名："+after.getEmer().getEmerName());
					lenderChangeExcelList.add(lenderExcelmodelF);
				}
				if(!begin.getEmer().getEmerSex().equals(after.getEmer().getEmerSex())){
					LenderChangeExcel lenderExcelmodelG = new LenderChangeExcel();
					lenderExcelmodelG.setDictApprovalStartDate(lenderExcel.get(i).getDictApprovalStartDate());
					lenderExcelmodelG.setCustName(lenderExcel.get(i).getCustName());
					lenderExcelmodelG.setCustCode(lenderExcel.get(i).getCustCode());
					lenderExcelmodelG.setManagerName(lenderExcel.get(i).getManagerName());
					lenderExcelmodelG.setDepartmentName(lenderExcel.get(i).getDepartmentName());
					lenderExcelmodelG.setLenderAfter(DictUtils.getDictLabel(after.getEmer().getEmerSex(), "sex", ""));
					lenderChangeExcelList.add(lenderExcelmodelG);
				}
				if(!begin.getEmer().getEmerMobilephone().equals(after.getEmer().getEmerMobilephone())){
					LenderChangeExcel lenderExcelmodelH = new LenderChangeExcel();
					lenderExcelmodelH.setDictApprovalStartDate(lenderExcel.get(i).getDictApprovalStartDate());
					lenderExcelmodelH.setCustName(lenderExcel.get(i).getCustName());
					lenderExcelmodelH.setCustCode(lenderExcel.get(i).getCustCode());
					lenderExcelmodelH.setManagerName(lenderExcel.get(i).getManagerName());
					lenderExcelmodelH.setDepartmentName(lenderExcel.get(i).getDepartmentName());
					lenderExcelmodelH.setLenderAfter("紧急联系人手机号码：******");
					lenderChangeExcelList.add(lenderExcelmodelH);
				}
				if(!begin.getEmer().getEmerRelationship().equals(after.getEmer().getEmerRelationship())){
					LenderChangeExcel lenderExcelmodelI = new LenderChangeExcel();
					lenderExcelmodelI.setDictApprovalStartDate(lenderExcel.get(i).getDictApprovalStartDate());
					lenderExcelmodelI.setCustName(lenderExcel.get(i).getCustName());
					lenderExcelmodelI.setCustCode(lenderExcel.get(i).getCustCode());
					lenderExcelmodelI.setManagerName(lenderExcel.get(i).getManagerName());
					lenderExcelmodelI.setDepartmentName(lenderExcel.get(i).getDepartmentName());
					lenderExcelmodelI.setLenderAfter(after.getEmer().getEmerRelationship());
					lenderChangeExcelList.add(lenderExcelmodelI);
				}
				if(begin.getEmer().getAddress()!=null){
					if((!begin.getEmer().getAddress().getAddrProvince().equals(after.getEmer().getAddress().getAddrProvince()))
							||(!begin.getEmer().getAddress().getAddrCity().equals(after.getEmer().getAddress().getAddrCity()))
							||(!begin.getEmer().getAddress().getAddrDistrict().equals(after.getEmer().getAddress().getAddrDistrict()))){
						String emeraddrs = "通讯地址："+after.getEmer().getAddress().getAddr()+" 省市："+
								OptionUtil.getProvinceLabel(after.getEmer().getAddress().getAddrProvince())+" "+
								OptionUtil.getCityLabel(after.getEmer().getAddress().getAddrCity())+" "+
								OptionUtil.getDistrictLabel(after.getEmer().getAddress().getAddrDistrict());
						
						LenderChangeExcel lenderExcelmodelJ = new LenderChangeExcel();
						lenderExcelmodelJ.setDictApprovalStartDate(lenderExcel.get(i).getDictApprovalStartDate());
						lenderExcelmodelJ.setCustName(lenderExcel.get(i).getCustName());
						lenderExcelmodelJ.setCustCode(lenderExcel.get(i).getCustCode());
						lenderExcelmodelJ.setManagerName(lenderExcel.get(i).getManagerName());
						lenderExcelmodelJ.setDepartmentName(lenderExcel.get(i).getDepartmentName());
						lenderExcelmodelJ.setLenderAfter(emeraddrs);
						lenderChangeExcelList.add(lenderExcelmodelJ);
					}
				}else{
					String emeraddrs = "通讯地址："+after.getEmer().getAddress().getAddr()+" 省市："+
							OptionUtil.getProvinceLabel(after.getEmer().getAddress().getAddrProvince())+" "+
							OptionUtil.getCityLabel(after.getEmer().getAddress().getAddrCity())+" "+
							OptionUtil.getDistrictLabel(after.getEmer().getAddress().getAddrDistrict());
					
					LenderChangeExcel lenderExcelmodelK = new LenderChangeExcel();
					lenderExcelmodelK.setDictApprovalStartDate(lenderExcel.get(i).getDictApprovalStartDate());
					lenderExcelmodelK.setCustName(lenderExcel.get(i).getCustName());
					lenderExcelmodelK.setCustCode(lenderExcel.get(i).getCustCode());
					lenderExcelmodelK.setManagerName(lenderExcel.get(i).getManagerName());
					lenderExcelmodelK.setDepartmentName(lenderExcel.get(i).getDepartmentName());
					lenderExcelmodelK.setLenderAfter(emeraddrs);
					lenderChangeExcelList.add(lenderExcelmodelK);
				}
				
				if(!begin.getLoanInfo().getLoanBillRecv().equals(after.getLoanInfo().getLoanBillRecv())){
					LenderChangeExcel lenderExcelmodelL = new LenderChangeExcel();
					lenderExcelmodelL.setDictApprovalStartDate(lenderExcel.get(i).getDictApprovalStartDate());
					lenderExcelmodelL.setCustName(lenderExcel.get(i).getCustName());
					lenderExcelmodelL.setCustCode(lenderExcel.get(i).getCustCode());
					lenderExcelmodelL.setManagerName(lenderExcel.get(i).getManagerName());
					lenderExcelmodelL.setDepartmentName(lenderExcel.get(i).getDepartmentName());	
					lenderExcelmodelL.setLenderAfter(DictUtils.getDictLabel(after.getLoanInfo().getLoanBillRecv(), "tz_billtaken_mode", ""));
					lenderChangeExcelList.add(lenderExcelmodelL);
				}
		}
		
		// 判断是否有记录
		if(lenderExcel == null){
			return lenderChangerOutExcelList;
		}
		// 转换为导出excel模板
		for (LenderChangeExcel deductPoolEx : lenderChangeExcelList) {
			LenderChangerOutExcel lenderChangerOutExcel = new LenderChangerOutExcel();
			// copy属性
			BeanUtils.copyProperties(deductPoolEx, lenderChangerOutExcel);
			lenderChangerOutExcelList.add(lenderChangerOutExcel);
		}*/
		return lenderChangerOutExcelList;
	}
}
