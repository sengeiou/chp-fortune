<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借人信息变更业务对接员审批</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.spanRed{color:red}
	</style>
	<script type="text/javascript" src="${ctxWebInf}/js/lenderChange/lenderChangeFileInit.js" ></script>
	<script type="text/javascript" src="${ctxWebInf}/js/lenderChange/lenderChangeApprove.js" ></script>
</head>
<body>
<br/>
<sys:message content="${message}"/>	
<div class='title'>
<h2 class="f14 ml10">客户信息</h2>
</div>
	<div class="body_r">
    <div class="box6">
    <sys:message content="${message}"/>
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
            	<!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td width="31%"><label class="lab"><span class="red">*</span>客户姓名：</label><input type="text" class="cf_input_text178" name="custName" value="***"></td>
                <%-- <td width="31%"><label class="lab"><span class="red">*</span>客户姓名：</label><input type="text" class="cf_input_text178" name="custName" value="${workItem.bv.lenderBegin.customer.custName}"></td> --%>
                <td width="31%"><label class="lab">出生日期：</label><input type="text" name="custBirthday" class="cf_input_text178" value="<fmt:formatDate value='${workItem.bv.lenderBegin.customer.custBirthday}' pattern='yyyy-MM-dd'/>"></td>
                <td><label class="lab">性别：</label>
                <input type="radio" name="dictCustSex" value="1"  <c:out value="${workItem.bv.lenderBegin.customer.dictCustSex==1?'checked':'' }"/> />男&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  name="dictCustSex" value="2" <c:out value="${workItem.bv.lenderBegin.customer.dictCustSex==2?'checked':'' }"/> />女
                </td>
            </tr>
             <tr>
                <td width="31%"><label class="lab"><span class="red">*</span>首次联系时间：</label><input type="text" name="custFirstContactdate" class="cf_input_text178"  value="<fmt:formatDate value='${workItem.bv.lenderBegin.customer.custFirstContactdate}' pattern='yyyy-MM-dd'/>"></td>
                <td width="31%"><label class="lab">证件类型：</label><input type="text" name="custCertType" class="cf_input_text178" value="${fns:dictName(workItem.bv.dicts.com_certificate_type,workItem.bv.lenderBegin.customer.custCertType,'-')}">&nbsp;</td>
                <td><label class="lab">证件号码：</label>
                	<!-- 屏蔽客户姓名/手机号/身份证号 -->
               		<input type="text" class="cf_input_text178" value="***">
               		<%-- <input type="text" class="cf_input_text178" value="${workItem.bv.lenderBegin.customer.custCertNum}"> --%>
                </td>
            </tr>
       
            <tr>
                <td><label class="lab"><span class="red">*</span>职业：</label><input type="text" name="custOccupation" class="cf_input_text178" value="${fns:dictName(workItem.bv.dicts.tz_prof,workItem.bv.lenderBegin.customer.custOccupation,'-') }"></td>
                <td><label class="lab">所属行业：</label><input type="text" name="custIndustry" class="cf_input_text178" value="${workItem.bv.lenderBegin.customer.custIndustry}"></td>
                <td><label class="lab">工作单位：</label><input type="text" name="custUnitname" class="cf_input_text178" value="${workItem.bv.lenderBegin.customer.custUnitname}"></td>
            </tr> 
             <tr>
                <td><label class="lab">学历：</label><input type="text" name="custEducation" class="cf_input_text178" value="${workItem.bv.lenderBegin.customer.custEducation}"></td>
                <td><label class="lab">单位规模：</label><input type="text" name="custUnitScale" class="cf_input_text178" value="${fns:dictName(workItem.bv.dicts.tz_unit_size,workItem.bv.lenderBegin.customer.custUnitScale,'-') }"></td>
                <td><label class="lab">工作年限：</label><input type="text" name="custWorkExperience" class="cf_input_text178" value="${workItem.bv.lenderBegin.customer.custWorkExperience}"></td>
            </tr>
            <tr>
                <td><label class="lab">职务：</label><input type="text" name="custPost" class="cf_input_text178" value="${workItem.bv.lenderBegin.customer.custPost}"></td>
                <td><label class="lab">客户来源：</label><input type="text" name="dictCustSource" class="cf_input_text178" value="${fns:dictName(workItem.bv.dicts.tz_customer_src,workItem.bv.lenderBegin.customer.dictCustSource,'-')}"></td>
                <td><label class="lab">客户传真：</label><input type="text" name="custFax" class="cf_input_text178"  value="${workItem.bv.lenderBegin.customer.custFax}"></td>
            </tr>
        </table>
    </div>
    <div class='title'>
	 <h2 class="f14 ml10">信息变更</h2>
	 </div>
    <div class='box6'>
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td style="width:50%;">
					<table style="width:100%;text-align: content: ;" id="chengeTable">
                        <tr>
							<td class='tcenter' style="width:25%;" colspan="2">
								<label class="lab"><span class='red'>信息变更之前：</span></label>
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td class='tcenter' style="width:25%;" colspan="2">
								<label class="lab"><span class="red">信息变更之后：</span><br /></label>
							</td>
						</tr>
                        <tr><td colspan="5" style='height:0px;'><p  style='width:100%;border-bottom:1px solid #e5c4a1;'></p></td></tr>
						<tr> 
							<td style="width:25%;">
								<label class="lab"><span class="red">*</span>英文名称：</label><input type="text" name="cb_custEname" value="***" class="cf_input_text178">
								<%-- <label class="lab"><span class="red">*</span>英文名称：</label><input type="text" name="cb_custEname" value="${workItem.bv.lenderBegin.customer.custEname}" class="cf_input_text178"> --%>
							</td>
							<!-- 屏蔽客户姓名/手机号/身份证号 -->
							<td style="width:25%;">
								<label class="lab"><span class="red">*</span>移动电话：</label><input type="text" name="cb_custMobilephone" value="***" class="cf_input_text178">
								<%-- <label class="lab"><span class="red">*</span>移动电话：</label><input type="text" name="cb_custMobilephone" value="${workItem.bv.lenderBegin.customer.custMobilephone}" class="cf_input_text178"> --%>
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td style="width:25%;">
								<label class="lab"><span class="red">*</span>英文名称：</label><input type="text" name="ca_custEname" value="***" class="cf_input_text178">
								<%-- <label class="lab"><span class="red">*</span>英文名称：</label><input type="text" name="ca_custEname" value="${workItem.bv.lenderAfter.customer.custEname}" class="cf_input_text178"> --%>
							</td>
							<td style="width:25%;">
								<label class="lab"><span class="red">*</span>移动电话：</label><input type="text" name="ca_custMobilephone" value="***" class="cf_input_text178">
								<%-- <label class="lab"><span class="red">*</span>移动电话：</label><input type="text" name="ca_custMobilephone" value="${workItem.bv.lenderAfter.customer.custMobilephone}" class="cf_input_text178"> --%>
							</td>
						</tr>
						<tr>
							<td>
								<!-- 屏蔽客户姓名/手机号/身份证号 -->
								<label class="lab"><span class="red">*</span>固定电话：</label><input type="text" name="cb_custPhone" value="***" class="cf_input_text178">
								<%-- <label class="lab"><span class="red">*</span>固定电话：</label><input type="text" name="cb_custPhone" value="${workItem.bv.lenderBegin.customer.custPhone}" class="cf_input_text178"> --%>
							</td>
							<td>
								<label class="lab"><span class="red">*</span>电子邮箱：</label><input type="text" name="cb_custEmail" value="${workItem.bv.lenderBegin.customer.custEmail}" class="cf_input_text178">
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td>
								<!-- 屏蔽客户姓名/手机号/身份证号 -->
								<label class="lab"><span class="red">*</span>固定电话：</label><input type="text" name="ca_custPhone" value="***" class="cf_input_text178">
								<%-- <label class="lab"><span class="red">*</span>固定电话：</label><input type="text" name="ca_custPhone" value="${workItem.bv.lenderAfter.customer.custPhone}" class="cf_input_text178"> --%>
							</td>
							<td>
								<label class="lab"><span class="red">*</span>电子邮箱：</label><input type="text" name="ca_custEmail" value="${workItem.bv.lenderAfter.customer.custEmail}" class="cf_input_text178">
							</td>
						</tr>
						<tr>
							<td>
								<label class="lab"><span class="red">*</span>发证机关：</label><input type="text" name="cb_custCertOrg" class="cf_input_text178" value="${workItem.bv.lenderBegin.customer.custCertOrg}"></td>
                			<td>
                				<label class="lab">签发日期：</label><input type="text" name="cb_custCertIssuedate" class="cf_input_text178" value="<fmt:formatDate value='${workItem.bv.lenderBegin.customer.custCertIssuedate}'/>">
               				</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td>
								<label class="lab"><span class="red">*</span>发证机关：</label><input type="text" name="ca_custCertOrg" class="cf_input_text178" value="${workItem.bv.lenderAfter.customer.custCertOrg}"></td>
                			<td>
                				<label class="lab">签发日期：</label><input type="text" name="ca_custCertIssuedate" class="cf_input_text178" value="<fmt:formatDate value='${workItem.bv.lenderAfter.customer.custCertIssuedate}'/>">
               				</td>
						</tr>
						<tr>
							<td>
								<label class="lab">
								 	长期： 
								</label>
								 <input type="checkbox" name="cb_custCertPermanent" value="1" <c:out value="${workItem.bv.lenderBegin.customer.custCertPermanent==1?'checked':'' }"/>   class="ml10 mr6">
               			 	</td>
                			<td>
                				<label class="lab">婚姻状况：</label>
                				<input type="text" value="${fns:dictName(workItem.bv.dicts.tz_marital_state,workItem.bv.lenderBegin.customer.custMarriage,'-')}" class="cf_input_text178">
                			</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td>
								<label class="lab">
								长期：
								</label>
								 <input type="checkbox" name="ca_custCertPermanent" value="1" <c:out value="${workItem.bv.lenderAfter.customer.custCertPermanent==1?'checked':'' }"/>> 			 
               			 	</td>
                			<td>
                				<label class="lab">婚姻状况：</label>
                				<input type="text" value="${fns:dictName(workItem.bv.dicts.tz_marital_state,workItem.bv.lenderAfter.customer.custMarriage,'-')}" class="cf_input_text178">
                			</td>
						</tr>
						<tr>
							<td>
								<label class="lab"><span class="red">*</span>邮政编码：</label><input type="text" name="cb_cusAddrPostalCode" value="${workItem.bv.lenderBegin.customer.address.addrPostalCode}"  class="cf_input_text178" >
							</td>
							<td>
							    <label class="lab" >通讯地址：</label><input type="text" name="cb_cusAddrAddr" class="cf_input_text178" value="${workItem.bv.lenderBegin.customer.address.addr}" >
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td>
								<label class="lab"><span class="red">*</span>邮政编码：</label><input type="text" name="ca_cusAddrPostalCode" value="${workItem.bv.lenderAfter.customer.address.addrPostalCode}" class="cf_input_text178" >
							</td>
							<td>
							    <label class="lab" >通讯地址：</label><input type="text" name="ca_cusAddrAddr" class="cf_input_text178" value="${workItem.bv.lenderAfter.customer.address.addr}" >
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<label class="lab"><span class="red">*</span>所在地区：</label>
								<input type="text" readonly="readonly" class="cf_input_text178" 
								value="${fns:getProvinceLabel(workItem.bv.lenderBegin.customer.address.addrProvince)}&nbsp;${fns:getCityLabel(workItem.bv.lenderBegin.customer.address.addrCity)}&nbsp;${fns:getDistrictLabel(workItem.bv.lenderBegin.customer.address.addrDistrict)}" >                            	
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td colspan="2">
								<label class="lab"><span class="red">*</span>所在地区：</label>
								<input type="text" readonly="readonly"  class="cf_input_text178"
								value="${fns:getProvinceLabel(workItem.bv.lenderAfter.customer.address.addrProvince)}&nbsp;${fns:getCityLabel(workItem.bv.lenderAfter.customer.address.addrCity)}&nbsp;${fns:getDistrictLabel(workItem.bv.lenderAfter.customer.address.addrDistrict)}" >                           	
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<!-- 屏蔽客户姓名/手机号/身份证号 -->
								<label class="lab"><span class="red">*</span>紧急联系人：</label>
								<input type="text" readonly="readonly"  
								value="***" class="cf_input_text178"/>
								<%-- <input type="text" readonly="readonly"  
								value="${workItem.bv.lenderBegin.emer.emerName}" class="cf_input_text178"/> --%>
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td colspan="2">
								<!-- 屏蔽客户姓名/手机号/身份证号 -->
								<label class="lab"><span class="red">*</span>紧急联系人：</label>
								<input type="text" readonly="readonly"  
								value="***" class="cf_input_text178"/>
								<%-- <input type="text" readonly="readonly"  
								value="${workItem.bv.lenderAfter.emer.emerName}" class="cf_input_text178"/> --%>
							</td>
						</tr>
						<tr>
							<td>
								<label class="lab"><span class="red">*</span>联系人英文名称：</label><input name="cb_emerName" type="text" value="***" class="cf_input_text178">
								<%-- <label class="lab"><span class="red">*</span>联系人英文名称：</label><input name="cb_emerName" type="text" value="${workItem.bv.lenderBegin.emer.emerEname}" class="cf_input_text178"> --%>
							</td>
							<td>
								<label class="lab"><span class="red">*</span>联系人移动电话：</label><input name="cb_emerMobilephone" type="text" value="***" class="cf_input_text178">
								<%-- <label class="lab"><span class="red">*</span>联系人移动电话：</label><input name="cb_emerMobilephone" type="text" value="${workItem.bv.lenderBegin.emer.emerMobilephone}" class="cf_input_text178"> --%>
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td>
								<label class="lab"><span class="red">*</span>联系人英文名称：</label><input name="ca_emerName" type="text" value="${workItem.bv.lenderAfter.emer.emerEname}" class="cf_input_text178">
							</td>
							<td>
								<label class="lab"><span class="red">*</span>联系人移动电话：</label><input name="ca_emerMobilephone"  type="text" value="***" class="cf_input_text178">
								<%-- <label class="lab"><span class="red">*</span>联系人移动电话：</label><input name="ca_emerMobilephone"  type="text" value="${workItem.bv.lenderAfter.emer.emerMobilephone}" class="cf_input_text178"> --%>
							</td>
						</tr>
						<tr>
							<td>
								<label class="lab"><span class="red">*</span>与出借人关系：</label><input name="cb_emerRelationship" type="text" value="${workItem.bv.lenderBegin.emer.emerRelationship}" class="cf_input_text178">
							</td>
							<td>
								<!-- 屏蔽客户姓名/手机号/身份证号 -->
								<label class="lab"><span class="red">*</span>联系人固定电话：</label><input name="cb_emerPhone" type="text" value="***" class="cf_input_text178">
								<%-- <label class="lab"><span class="red">*</span>联系人固定电话：</label><input name="cb_emerPhone" type="text" value="${workItem.bv.lenderBegin.emer.emerPhone}" class="cf_input_text178"> --%>
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td>
								<label class="lab"><span class="red">*</span>与出借人关系：</label><input  name="ca_emerRelationship"  type="text" value="${workItem.bv.lenderAfter.emer.emerRelationship}" class="cf_input_text178">
							</td>
							<td>
								<!-- 屏蔽客户姓名/手机号/身份证号 -->
								<label class="lab"><span class="red">*</span>联系人固定电话：</label><input name="ca_emerPhone" type="text" value="***" class="cf_input_text178">
								<%-- <label class="lab"><span class="red">*</span>联系人固定电话：</label><input name="ca_emerPhone" type="text" value="${workItem.bv.lenderAfter.emer.emerPhone}" class="cf_input_text178"> --%>
							</td>
						</tr>
						<tr>
							<td>
								<label class="lab"><span class="red">*</span>邮政编码：</label><input type="text" name="cb_cusAddrPostalCode" value="${workItem.bv.lenderBegin.emer.address.addrPostalCode}"  class="cf_input_text178" >
							</td>
							<td>
							    <label class="lab" >通讯地址：</label><input type="text" name="cb_cusAddrAddr" class="cf_input_text178" value="${workItem.bv.lenderBegin.emer.address.addr}" class="cf_input_text178">
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td>
								<label class="lab"><span class="red">*</span>邮政编码：</label><input type="text" name="ca_cusAddrPostalCode" value="${workItem.bv.lenderAfter.emer.address.addrPostalCode}" class="cf_input_text178" >
							</td>
							<td>
							    <label class="lab" >通讯地址：</label><input type="text" name="ca_cusAddrAddr" class="cf_input_text178" value="${workItem.bv.lenderAfter.emer.address.addr}" class="cf_input_text178">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<label class="lab"><span class="red">*</span>联系人所在地区：</label>
								<input type="text" readonly="readonly"  class="cf_input_text178"
								value="${fns:getProvinceLabel(workItem.bv.lenderBegin.emer.address.addrProvince)}&nbsp;${fns:getCityLabel(workItem.bv.lenderBegin.emer.address.addrCity)}&nbsp;${fns:getDistrictLabel(workItem.bv.lenderBegin.emer.address.addrDistrict)}" >                          	
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td colspan="2">
								<label class="lab"><span class="red">*</span>联系人所在地区：</label>
								<input type="text" readonly="readonly"  class="cf_input_text178"
								value="${fns:getProvinceLabel(workItem.bv.lenderAfter.emer.address.addrProvince)}&nbsp;${fns:getCityLabel(workItem.bv.lenderAfter.emer.address.addrCity)}&nbsp;${fns:getDistrictLabel(workItem.bv.lenderAfter.emer.address.addrDistrict)}" >
							</td>
						</tr>	
						 <tr>
							<td>
                            	<label class="lab"><span class="red">*</span>联系人性别：</label><input type="radio" value="1"  name="cb_emerSex" style="width: 20px;"  <c:out value="${workItem.bv.lenderBegin.emer.emerSex==1?'checked':'' }"/> />男<input type="radio"  name="cb_emerSex" value="2" style="width: 20px;"  <c:out value="${workItem.bv.lenderBegin.emer.emerSex==2?'checked':'' }"/> />女
							</td>
							<td>
								<label class="lab"><span class="red">*</span>账单收取方式：</label><input type="text"  name="ca_loanBillRecv" class="cf_input_text178"  value="${fns:dictName(workItem.bv.dicts.tz_billtaken_mode,workItem.bv.lenderBegin.loanInfo.loanBillRecv,'-')}">
							</td>
								<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td>
                            	<label class="lab"><span class="red">*</span>联系人性别：</label><input type="radio" value="1"   name="ca_emerSex" style="width: 20px;" <c:out value="${workItem.bv.lenderAfter.emer.emerSex==1?'checked':'' }"/> /><span>男</span><input type="radio"  name="ca_emerSex" value="2" style="width: 20px;" <c:out value="${workItem.bv.lenderAfter.emer.emerSex==2?'checked':'' }"/> /><span>女</span>
							</td>
							<td>
								<label class="lab"><span class="red">*</span>账单收取方式：</label><input type="text"  name="ca_loanBillRecv" class="cf_input_text178"  value="${fns:dictName(workItem.bv.dicts.tz_billtaken_mode,workItem.bv.lenderAfter.loanInfo.loanBillRecv,'-')}">
							</td>
						</tr>	 
					</table>
				</td>				      
            </tr>
        </table>
    </div>
    <div class='title'>
     <h2 class="f14 ml10">出借信息</h2>
     </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td width="31%"><label class="lab"><span class="red">*</span>是否签订委托协议：</label><input type="radio" class="ml10 mr6" name='loanSign' value="1" <c:out value="${workItem.bv.lenderBegin.loanInfo.loanSign==1?'checked':'' }"/> />是<input type="radio" class="ml10 mr6" name='loanSign' value="2" <c:out value="${workItem.bv.lenderBegin.loanInfo.loanSign==2?'checked':'' }"/> />否</td>
                <td><label class="lab">签订协议日期：</label><input type="text" name="agreemrntSignDate" class="cf_input_text178" value="<fmt:formatDate value='${workItem.bv.lenderBegin.loanInfo.agreemrntSignDate}'/>"></td>
            </tr>
             <tr>
                <td width="31%"><label class="lab"><span class="red">*</span>委托协议种类：</label><input type="text" name="loanAgreementType" class="cf_input_text178" value="${fns:dictName(workItem.bv.dicts.tz_protocol_type,workItem.bv.lenderBegin.loanInfo.loanAgreementType,'-')}"></td>
                <td width="31%"><label class="lab"><span class="red">*</span>委托协议版本号：</label><input type="text" name="loanAgreementEdition" class="cf_input_text178" value="${fns:dictName(workItem.bv.dicts.tz_protocol_version,workItem.bv.lenderBegin.loanInfo.loanAgreementEdition,'-')}"></td>
            </tr>
        </table>
    </div>
    <c:if test="${workItem.bv.lenderBegin.customer.trusteeshipNo ne null&&workItem.bv.lenderBegin.customer.trusteeshipNo ne ''}">
    <div class="title">
	    <div class="l"><h2 class="f14 ml10 lenderChange_launchFileClass">金账户信息</h2></div>
	    </div>
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td width="31%">
					<label class="lab"><span class="red">*</span>金账户账号：</label>
					<input type="text" readonly="readonly" value="${workItem.bv.lenderBegin.customer.trusteeshipNo}" class="cf_input_text178">
				</td>
                <td width="31%">
                	<label class="lab"><span class="red">*</span>托管状态：</label>
                	<input type="text"  readonly="readonly"  value="${fns:dictName(workItem.bv.dicts.tz_trust_state,workItem.bv.lenderAfter.customer.applyHostedStatus,'-')}"  class="cf_input_text178">
                </td>     
                <td width="31%">
                	<c:if test="${workItem.bv.changInfo.dictChangeType eq 6}">
                		<span class="red">*金账户销户：</span>
                		<input type="checkbox" disabled="disabled" value="6" <c:out value="${workItem.bv.lenderAfter.customer.changerTypeVal==6?'checked':'' }"/>> 
                	</c:if>		 		
                </td>
			</tr>
		</table>
    </div>
    </c:if>
    	<div class="title">
	        <div class="l"><h5 class="f14 ml10">附件</h5></div>
	    </div>
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td>
					<sys:attachment></sys:attachment>
				</td>
			</tr>
		</table>
    <form:form id="searchForm" commandName="workItem" action="${ctx}/lenderChangeApprove/review/review" method="post" >
    <div class='title'>
     <h2 class="f14 ml10">审批信息</h2>
     </div>
      <div class="box6">
        <table class="table1">
            <tr>
                <td style="width:30%;"><label class="lab" ><span class="red">*</span>门店经理审批意见：</label><textarea class="textarea_refuse" readonly="readonly">${workItem.bv.changeLog.auditCheckExamine}</textarea></td>
            </tr>
            <tr>
                <td ><label class="lab" ><span class="red">*</span>门店经理审批结果：</label><input type="radio" name="cb_auditResult" value="1" <c:out value="${workItem.bv.changeLog.auditResult==1?'checked':'' }"/> />&nbsp;通过&nbsp;<input type="radio" name="cb_auditResult" value="2" <c:out value="${workItem.bv.changeLog.auditResult==2?'checked':'' }"/> />&nbsp;不通过</td>
            </tr>
        </table>
        <table class="table1" cellpadding="0" cellspacing="0" border="0">
             <c:if test="${workItem.bv.changInfoRes.auditCheckExamine!=null}">
             <tr>
                <td><label class="lab">数据管理部退回意见：</label><textarea class="textarea_refuse" readonly="readonly">${workItem.bv.changInfoRes.auditCheckExamine}</textarea></td>
            </tr>
            </c:if>
             <tr>
                <td><label class="lab"><span class="red">*</span>业务对接员审批意见：</label><textarea class="textarea_refuse" id="auditCheckExamine"  name="auditCheckExamine"></textarea></td>
            </tr>
            <tr>
                <td><label class="lab"><span class="red">*</span>业务对接员审批结果：</label><input type="radio" required name="auditResult" value="1"/>&nbsp;通过&nbsp;<input type="radio" required name="auditResult" value="2"/>&nbsp;不通过</td>
            </tr>
        </table>
    </div>
</div>
	<div class="tright pr30">
			<shiro:hasPermission name="apply:consult:edit"></shiro:hasPermission>
			<input id="btnSubmit" class="btn cf_btn-primary" type="button" value="提交"/>
<!-- 			<input id="btnCancel" class="btn cf_btn-primary" type="button" value="返 回" onclick="self.location=document.referrer;"/> -->
			<input id="btnCancel" class="btn cf_btn-primary" type="button" value="返 回" onclick="window.history.back()"/>
	</div>
	<div id="divHideen">
	 	<input type="hidden"  value="${workItem.flowCode}" name="flowCode"></input>
        <input type="hidden"  value="${workItem.flowName}" name="flowName"></input>
        <input type="hidden"  value="${workItem.flowId}" name="flowId"></input>
        <input type="hidden"  value="${workItem.stepName}" name="stepName"></input>
        <input type="hidden"  value="${workItem.wobNum}" name="wobNum"></input>
        <input type="hidden"  value="${workItem.token}" name="token"></input>
        <input type="hidden"  value="${workItem.bv.changInfo.applyId}" name="applyId"></input>
        <input type="hidden"  value="${workItem.bv.changInfo.id}" name="changeId"></input>
        <input type="hidden"  value="${workItem.bv.lenderBegin.customer.custCode}" name="custCode"></input>
        <input type="hidden"  value="${workItem.bv.lenderBegin.customer.custName}" name="custName"></input>
		<input type="hidden"  value="${workItem.bv.lenderBegin.customer.id}" name="id"></input>
		<input type="hidden"  value="${workItem.bv.lenderAfter.customer.custMobilephone}" name="custMobilephoneChanger"></input>
		<input type="hidden"  value="${workItem.bv.lenderBegin.customer.custMobilephone}" name="custMobilephone"></input>
		<input type="hidden"  value="${workItem.bv.changInfo.dictChangeType}" name="changerTypeVal"/>
	</div>
</form:form>
</body>
</html>