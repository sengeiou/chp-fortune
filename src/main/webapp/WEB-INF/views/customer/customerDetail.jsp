<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="tright">
    <input id="eliminateGoldAccount" class="btn cf_btn-primary mr10" type="button" value="金账户销户" style="display: none;"/>
    <c:if test="${customer.custState==1 or customer.custState==5 or customer.custState==2 }">
    	<input id="updateButton" class="btn cf_btn-primary mr10" type="button" value="修改"/>
    </c:if>
    <input id="saveButton" class="btn cf_btn-primary mr10" style="display:none;" type="button" value="保存"/>
    <c:if test="${customer.custState==1 or customer.custState==5}"><input id="customerTransferLend" class="btn cf_btn-primary mr10" type="button" value="开户"/></c:if>
    <c:if test="${customer.poolStatus == '2' }">  <!-- 1公共池、2分配、3退回电销  -->
    	<input id="sendBack2TeleSale" class="btn cf_btn-primary mr10" type="button" value="退回电销" />
    </c:if>
</div>
<input type="hidden" id="custCode" name="custCode" value="${customer.custCode }">
<div class="title">
    <div class="l"><h2 class="f14 ml10">客户信息</h2></div>
</div>
<div class="box6">
<form id="customerForm" action="${ctx}/customer/investment/update" method="post" class="form-horizontal">
	<input type="hidden" id="id" name="id" value="${customer.id }">
	<input type="hidden" id="custCode" name="custCode" value="${customer.custCode }">
	<input type="hidden" id="address.id" name="address.id" value="${customer.address.id }">
	<input type="hidden" id="currentCustomerPhone" name="currentCustomerPhone" value="${customer.custMobilephone }">
	<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td>
				<label class="lab"><span class='red'>*</span>客户姓名：</label>
				<!-- 屏蔽客户姓名/手机号/身份证号 -->
				<input id="custName" name="custName" updateable value="***" type="text" disabled="disabled" class="cf_input_text178" ChEn='1' maxlength="50" required='1'>
				<%-- <input id="custName" name="custName" updateable value="${customer.custName}" type="text" disabled="disabled" class="cf_input_text178" ChEn='1' maxlength="50" required='1'> --%>
			</td>
			<td style="display: none;">
				<label class="lab"><span class='red'>*</span>真实名称：</label>
				<input id="custRealname" name="custRealname" value="${customer.custRealname}" type="text" disabled="disabled" class="cf_input_text178">
			</td>
			<td>
				<label class="lab"><span class='red'>*</span>性别：</label>
				<c:forEach items="${dicts.sex }" var="item">
					<input type="radio" id="dictCustSex" name="dictCustSex" updateable required='1' value="${item.value }" <c:if test="${customer.dictCustSex==item.value}">checked=checked</c:if> disabled="disabled">${item.label }
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td>
				<label class="lab"><span class='red'>*</span>证件类型：</label>
				<select id="custCertType" name="custCertType" disabled="disabled" class="cf_input_text178">
					<c:forEach items="${dicts.com_certificate_type}" var="item">
						<option value="${item.value }" <c:if test="${customer.custCertType==item.value}">selected</c:if>>${item.label }</option>
					</c:forEach>
			    </select>
			</td>
			<td>
				<label class="lab"><span class='red'>*</span>证件号码：</label>
				<!-- 屏蔽客户姓名/手机号/身份证号 -->
				<input type="text" id="custCertNum" name="custCertNum" value="***" disabled="disabled" class="cf_input_text178">
				<%-- <input type="text" id="custCertNum" name="custCertNum" value="${customer.custCertNum }" disabled="disabled" class="cf_input_text178"> --%>
			</td>
			<td>
				<label class="lab"><span class='red'>*</span>签发机关：</label>
				<input type="text" id="custCertOrg" name="custCertOrg" value="${customer.custCertOrg }" disabled="disabled" class="cf_input_text178">
			</td>
		</tr>
		<tr>
			<td>
				<label class="lab"><span class='red'>*</span>签发日期：</label>
				<input type="text" id="custCertIssuedate" name="custCertIssuedate" value='<fmt:formatDate value="${customer.custCertIssuedate }" pattern="yyyy-MM-dd"/>' class="Wdate cf_input_text178" disabled="disabled">
			</td>
			<c:if test="${empty customer.custCertPermanent }">
				<td>
					<label class="lab"><span class='red'>*</span>失效日期：</label>
					<input type="text" id="custCertFailuredate" name="custCertFailuredate" value="<fmt:formatDate value="${customer.custCertFailuredate }" pattern="yyyy-MM-dd"/>" class="Wdate cf_input_text178" disabled="disabled">
				</td>
			</c:if>
			<td>
				<label class="lab">长期有效：</label>
				<input type="checkbox" id="custCertPermanent" name="custCertPermanent" value="1" <c:if test="${customer.custCertPermanent=='1'}">checked=checked</c:if> disabled="disabled">
			</td>
		</tr>
		<tr>
			<td>
				<label class="lab"><span class='red'>*</span>出生日期：</label>
				<input id="custBirthday" name="custBirthday" value="<fmt:formatDate value="${customer.custBirthday }" pattern="yyyy-MM-dd"/>" type="text" class="Wdate cf_input_text178" disabled="disabled">
			</td>
			<td>
				<label class="lab"><span class='red'></span>学历：</label>
				<input id="custEducation" name="custEducation" value="${customer.custEducation }" type="text" disabled="disabled" class="cf_input_text178">
			</td>
			<td>
				<label class="lab"><span class='red'></span>婚姻状况：</label>
				<select id="custMarriage" name="custMarriage" disabled="disabled" class="cf_input_text178">
					<option value="">请选择</option>
					<c:forEach items="${dicts.tz_marital_state }" var="item">
						<option value="${item.value }" <c:if test="${customer.custMarriage==item.value}">selected</c:if>>${item.label }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<label class="lab"><span class='red'></span>英文姓名：</label>
				<input id="custEname" name="custEname" value="${customer.custEname }" type="text" disabled="disabled" class="cf_input_text178">
			</td>
			<td>
				<label class="lab"><span class='red'></span>母亲姓氏：</label>
				<input id="custMotherName" name="custMotherName" value="${customer.custMotherName }" type="text" disabled="disabled" class="cf_input_text178">
			</td>
			<td>
				<label class="lab"><span class='red'>*</span>客户来源：</label>
				<select id="dictCustSource" name="dictCustSource" disabled="disabled" updateable select_required='1' class="cf_input_text178">
					<c:forEach items="${dicts.tz_customer_src }" var="item">
						<option value="${item.value }" <c:if test="${customer.dictCustSource==item.value}">selected</c:if>>${item.label }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<label class="lab"><span class='red'>*</span>职业：</label>
				<input id="custOccupation" name="custOccupation" value="${fns:dictName(dicts.tz_prof,customer.custOccupation,'-') }" type="text" disabled="disabled" class="cf_input_text178">
			</td>
			<td>
				<label class="lab"><span class='red'></span>单位规模：</label>
				<input id="custUnitScale" name="custUnitScale" value="${fns:dictName(dicts.tz_unit_size,customer.custUnitScale,'-') }" type="text" disabled="disabled" class="cf_input_text178">
			</td>
			<td>
				<label class="lab"><span class='red'></span>工作年限：</label>
				<input id="custWorkExperience" name="custWorkExperience" value="${customer.custWorkExperience }" type="text" disabled="disabled" class="cf_input_text178">
		    </td>
		</tr>
		<tr>
			<td>
				<label class="lab">工作单位：</label>
				<input id="custUnitname" name="custUnitname" updateable maxlength="50" value="${customer.custUnitname }" type="text" disabled="disabled" class="cf_input_text178">
			</td>
			<td>
				<label class="lab">行业：</label>
				<input id="custIndustry" name="custIndustry" updateable maxlength="50" value="${customer.custIndustry }" type="text" disabled="disabled" class="cf_input_text178">
			</td>
			<td>
				<label class="lab">职务：</label>
				<input id="custPost" name="custPost" updateable maxlength="50" value="${customer.custPost }" type="text" disabled="disabled" class="cf_input_text178">
			</td>
		</tr>
		<tr>
			<td>
				<label class="lab"><span class='red'>*</span>移动电话：</label>
				<!-- 屏蔽客户姓名/手机号/身份证号 -->
				<input id="custMobilephone" name="custMobilephone" type="text" maxlength="11" required='1' mobilenum="1" value="***" disabled="disabled" class="cf_input_text178">
				<%-- <input id="custMobilephone" name="custMobilephone" type="text" maxlength="11" required='1' mobilenum="1" value="${customer.custMobilephone }" disabled="disabled" class="cf_input_text178"> --%>
			</td>
			<td>
				<!-- 屏蔽客户姓名/手机号/身份证号 -->
				<label class="lab"><span class='red'></span>固定电话：</label>
				<input id="custPhone" name="custPhone" type="text" value="***" updateable maxlength="15" digits="1" disabled="disabled" class="cf_input_text178">
				<%-- <input id="custPhone" name="custPhone" type="text" value="${customer.custPhone }" updateable maxlength="15" digits="1" disabled="disabled" class="cf_input_text178">
				 --%>
			</td>
			<td>
				<label class="lab"><span class='red'>*</span>电子邮箱：</label>
				<input id="custEmail" name="custEmail" type="text" value="${customer.custEmail }" updateable maxlength="50" required='1' email='1' disabled="disabled" class="cf_input_text178">
			</td>
		</tr>
		<tr>
			<td>
				<label class="lab"><span class='red'>*</span>首次联系时间：</label>
				<input id="custFirstContactdate" name="custFirstContactdate" type="text" value="<fmt:formatDate value="${customer.custFirstContactdate }" pattern="yyyy-MM-dd"/>" disabled="disabled" class="cf_input_text178">
			</td>
			<td>
				<label class="lab">传真：</label>
				<input id="custFax" name="custFax" type="text" value="${customer.custFax }" updateable maxlength="20" disabled="disabled" class="cf_input_text178">
			</td>
			<td>
				<label class="lab"><span class='red'>*</span>地址：</label>
				<input id="customer_addr" name="address.addr" type="text" updateable maxlength="100" required='1' value="${customer.address.addr }" disabled="disabled" class="cf_input_text178">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label class="lab"><span class='red'>*</span>所在城市：</label>
				<select id="addrProvince" name="address.addrProvince" disabled="disabled" select_required="1" updateable class="cf_input_text178">
					<c:forEach items="${provinceList }" var="item">
						<option value="${item.id}" <c:if test="${item.id==customer.address.addrProvince }">selected=selected</c:if>>${item.areaName }</option>
					</c:forEach>
				</select>
				<select id="addrCity" name="address.addrCity" disabled="disabled" select_required="1" updateable class="cf_input_text178">
					<c:forEach items="${cityList }" var="item">
						<option value="${item.id}" <c:if test="${item.id==customer.address.addrCity }">selected=selected</c:if>>${item.areaName }</option>
					</c:forEach>
				</select>
				<select id="addrDistrict" name="address.addrDistrict" disabled="disabled" select_required="1" updateable class="cf_input_text178">
					<c:forEach items="${districtList }" var="item">
						<option value="${item.id}" <c:if test="${item.id==customer.address.addrDistrict }">selected=selected</c:if>>${item.areaName }</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<label class="lab">邮编：</label>
				<input id="customer_addrPostalCode" name="address.addrPostalCode" updateable maxlength="50" postnum="1" type="text" value="${customer.address.addrPostalCode }" disabled="disabled" class="cf_input_text178">
			</td>
		</tr>
		<tr>
			<td>
				<label class="lab">电销专员：</label>
				<input id="teleManagerName" name="teleManagerName" type="text" value="${customer.teleManagerName }" disabled="disabled" class="cf_input_text178">
			</td>
		</tr>
	</table>
</form>
</div>
<div class="title">
    <div class="l"><h2 class="f14 ml10">紧急联系人信息</h2></div>
</div>
<div class='box6'>
<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<!-- 屏蔽客户姓名/手机号/身份证号 -->
		<td>
			<label class="lab"><span class='red'>*</span>中文姓名：</label>
			<input id="emerName" name="emergencyContact.emerName" value="***" type="text" disabled="disabled" class="cf_input_text178">
			<%-- <input id="emerName" name="emergencyContact.emerName" value="${emergencyContact.emerName }" type="text" disabled="disabled" class="cf_input_text178"> --%>
		</td>
		<td>
			<label class="lab"><span class='red'></span>英文姓名：</label>
			<input id="emerEname" name="emergencyContact.emerEname" value="***" type="text" disabled="disabled" class="cf_input_text178">
			<%-- <input id="emerEname" name="emergencyContact.emerEname" value="${emergencyContact.emerEname }" type="text" disabled="disabled" class="cf_input_text178"> --%>
		</td>
		<td>
			<label class="lab"><span class='red'>*</span>性别：</label>
			<c:forEach items="${dicts.sex }" var="item">
				<input type="radio" name="emergencyContact.emerSex" value="${item.value }" <c:if test="${emergencyContact.emerSex==item.value}">checked=checked</c:if> disabled="disabled">${item.label }
			</c:forEach>
		</td>
	</tr>
	<tr>
		<td>
			<label class="lab"><span class='red'></span>证件类型：</label>
			<select id="emerType" name="emergencyContact.emerType" disabled="disabled" class="cf_input_text178">
				<option value="">请选择</option>
				<c:forEach items="${dicts.com_certificate_type }" var="item">
					<option value="${item.value }" <c:if test="${emergencyContact.emerType==item.value}">selected</c:if>>${item.label }</option>
				</c:forEach>
			</select>
		</td>
		<td>
			<label class="lab"><span class='red'></span>证件号码：</label>
			<!-- 屏蔽客户姓名/手机号/身份证号 -->
			<input id="emerCertNum" name="emergencyContact.emerCertNum" value="***" type="text" disabled="disabled" class="cf_input_text178">
			<%-- <input id="emerCertNum" name="emergencyContact.emerCertNum" value="${emergencyContact.emerCertNum }" type="text" disabled="disabled" class="cf_input_text178">
 --%>			
		</td>
		<td>
			<label class="lab"><span class='red'>*</span>移动电话：</label>
			<input id="emerMobilephone" name="emergencyContact.emerMobilephone" value="***" type="text" disabled="disabled" mobilenum="1" class="cf_input_text178">
			<%-- <input id="emerMobilephone" name="emergencyContact.emerMobilephone" value="${emergencyContact.emerMobilephone }" type="text" disabled="disabled" mobilenum="1" class="cf_input_text178"> --%>
		</td>
	</tr>
	<tr>
		<td>
			<label class="lab"><span class='red'>*</span>出生日期：</label>
			<input id="emerBirthday" name="emergencyContact.emerBirthday" value="<fmt:formatDate value="${emergencyContact.emerBirthday }" pattern="yyyy-MM-dd"/>" type="text" onfocus="WdatePicker()" class="Wdate cf_input_text178" disabled="disabled">
		</td>
		<td>
			<label class="lab"><span class='red'>*</span>关系：</label>
			<input id="emerRelationship" name="emergencyContact.emerRelationship" value="${emergencyContact.emerRelationship }" type="text" disabled="disabled" class="cf_input_text178">
		</td>
		<td>
			<!-- 屏蔽客户姓名/手机号/身份证号 -->
			<label class="lab"><span class='red'></span>固定电话：</label>
			<input id="emerPhone" name="emergencyContact.emerPhone" value="***" type="text" disabled="disabled" class="cf_input_text178">
			<%-- <input id="emerPhone" name="emergencyContact.emerPhone" value="${emergencyContact.emerPhone }" type="text" disabled="disabled" class="cf_input_text178"> --%>
		</td>
	</tr>
	<tr>
		<td>
			<label class="lab"><span class='red'>*</span>电子邮箱：</label>
			<input id="emerEmail" name="emergencyContact.emerEmail" value="${emergencyContact.emerEmail }" type="text" disabled="disabled" class="cf_input_text178">
		</td>
		<td>
			<label class="lab"><span class='red'>*</span>地址：</label>
			<input id="emer_addr" name="emergencyContact.address.addr" value="${emergencyContact.address.addr }" type="text" disabled="disabled" class="cf_input_text178">
		</td>
		<td>
			<label class="lab"><span class='red'></span>邮编：</label>
			<input id="emer_addrPostalCode" name="emergencyContact.address.addrPostalCode" value="${emergencyContact.address.addrPostalCode }" type="text" disabled="disabled" class="cf_input_text178">
		</td>
	</tr>
	<tr>
		<td colspan="3">
			<label class="lab"><span class='red'>*</span>所在城市：</label>
			<input type="text" value="${fns:getProvinceLabel(emergencyContact.address.addrProvince) }" disabled="disabled" class="cf_input_text178">
			<input type="text" value="${fns:getCityLabel(emergencyContact.address.addrCity) }" disabled="disabled" class="cf_input_text178">
			<input type="text" value="${fns:getDistrictLabel(emergencyContact.address.addrDistrict) }" disabled="disabled" class="cf_input_text178">
		</td>
	</tr>
</table>
</div>
<div class="title">
    <div class="l"><h2 class="f14 ml10">出借信息</h2></div>
</div>
<div class='box6'>
<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td>
			<label class="lab"><span class='red'></span>账单收取方式：</label>
			<input value="${fns:dictName(dicts.tz_billtaken_mode,loanConfiguration.loanBillRecv,'-')}" type="text" disabled="disabled" class="cf_input_text178">
		</td>
		<td>
			<label class="lab"><span class='red'></span>签订委托协议：</label>
			<input value="${fns:dictName(dicts.tz_yes_no,loanConfiguration.loanSign,'-')}" type="text" disabled="disabled" class="cf_input_text178">
		</td>
		<td>
			<label class="lab"><span class='red'></span>委托协议种类：</label>
			<input value="${fns:dictName(dicts.tz_protocol_type,loanConfiguration.loanAgreementType,'-')}" type="text" disabled="disabled" class="cf_input_text178">
		</td>
	</tr>
	<tr>
		<td>
			<label class="lab"><span class='red'></span>委托协议版本：</label>
			<input value="${fns:dictName(dicts.tz_protocol_version,loanConfiguration.loanAgreementEdition,'-')}" type="text" disabled="disabled" class="cf_input_text178">
		</td>
		<td colspan="2">
			<label class="lab"><span class='red'></span>签订协议日期：</label>
			<input value="<fmt:formatDate value="${loanConfiguration.agreemrntSignDate}" pattern="yyyy-MM-dd"/>" type="text" disabled="disabled" class="cf_input_text178">
		</td>
	</tr>
</table>
</div>
<div class='tright pr30'>
      <input id="goBack" class="btn cf_btn-primary mr10" type="button" value="返 回" onclick="history.go(-1)"/>
</div>
