<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>客户开户</title>
<meta name="decorator" content="default" />
<script src="${ctxWebInf }/js/customer/transferLend_launch.js" type="text/javascript"></script>
<script src="${ctxWebInf }/js/customer/transferLend_phone_email.js" type="text/javascript"></script>
<script src="${ctxWebInf }/js/common/jquery.validate.extend.js" type="text/javascript"></script>
</head>
<body>
	<form id="inputForm" method="post" action="${ctx}/customer/investment/transferLend" >
		<div class="title">
	        <div class="l"><h2 class="f14 ml10">客户信息</h2></div>
	    </div>
	    <div class='box6'>
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td>
					<label class="lab"><span class='red'>*</span>客户姓名：</label>
					<input id="custName" name="customer.custName" value="${customer.custName}" type="text" class="cf_input_text178">
				</td>
				<td style="display: none;">
					<label class="lab"><span class='red'></span>真实姓名：</label>
					<input id="custRealname" name="customer.custRealname" value="${customer.custRealname}" type="text" maxlength="50"  chinese="1" class="cf_input_text178">
				</td>
				<td>
					<label class="lab"><span class='red'>*</span>性别：</label>
					<c:forEach items="${fns:getDictList('sex') }" var="item">
						<input id = "sex" type="radio" name="customer_dictCustSex" value="${item.value }" <c:if test="${customer.dictCustSex==item.value}">checked=checked</c:if> disabled="disabled">${item.label }
					</c:forEach>
				</td>
				<td>
					<label class="lab">国籍：</label>
					<input type="text" class="cf_input_text178">
				</td>
			</tr>
			<tr>
				<td>
					<label class="lab"><span class="red">*</span>证件类型：</label><select id="custCertType" name="customer.custCertType" select_required='1' class="cf_input_text178">
						<option value="" >请选择</option>
						<c:forEach items="${fns:getDictList('com_certificate_type') }" var="item">
							<c:if test="${item.value==0 || item.value==1 || item.value==2 || item.value==3 || item.value==5}">
								<option value="${item.value }" <c:if test="${customer.custCertType==item.value || item.value == fns:getDictValue('身份证', 'com_certificate_type', '1') }">selected</c:if>>${item.label }</option>
							</c:if>
						</c:forEach>
				    </select>
				    <span id="butSpan" style="display:show;">
				    	<input type="file" id="uploadfile" name="file" style='width:170px;'/>
				    	<input type="hidden" id="uploadfileHiddrn"/>
				    	<input type="button" id="uploadbutton" value="上传证件图片" class='btn btn_sc'/>
				    </span>
				</td>
				<td>
					<label class="lab"><span class='red'>*</span>证件号码：</label>
					<input type="text" id="custCertNum" name="customer.custCertNum" value="${customer.custCertNum }" required maxlength="50" class="cf_input_text178">
				</td>
				<td>
					<label class="lab"><span class='red'>*</span>签发机关：</label>
					<input type="text" id="custCertOrg" name="customer.custCertOrg" value="${customer.custCertOrg }" required maxlength="100" class="cf_input_text178">
				</td>
			</tr>
			<tr>
				<td>
					<label class="lab"><span class='red'>*</span>签发日期：</label>
					<input type="text" id="custCertIssuedate" name="customer.custCertIssuedate" value='<fmt:formatDate value="${customer.custCertIssuedate }" pattern="yyyy-MM-dd"/>' onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:new Date()})" class="Wdate cf_input_text178" required>
				</td>
				<c:if test="${empty customer.custCertPermanent }">
					<td>
						<label class="lab"><span class='red'>*</span>失效日期：</label>
						<input type="text" id="custCertFailuredate" name="customer.custCertFailuredate" value="<fmt:formatDate value="${customer.custCertFailuredate }" pattern="yyyy-MM-dd"/>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate cf_input_text178" from-checkDate="#custCertIssuedate" required>
					</td>
				</c:if>
				<td>
					<label class="lab">长期有效：</label>
					<input type="checkbox" id="custCertPermanent" name="customer.custCertPermanent" value="1" <c:if test="${customer.custCertPermanent=='1'}">checked=checked</c:if>>
				</td>
			</tr>
			<tr>
				<td>
					<label class="lab"><span class='red'>*</span>出生日期：</label>
					<input id="custBirthday" name="customer.custBirthday" value="<fmt:formatDate value="${customer.custBirthday }" pattern="yyyy-MM-dd"/>" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate cf_input_text178" required>
				</td>
				<td>
					<label class="lab"><span class='red'></span>学历：</label>
					<input id="custEducation" name="customer.custEducation" value="${customer.custEducation }" type="text" maxlength="20" class="cf_input_text178">
				</td>
				<td>
					<label class="lab"><span class='red'></span>婚姻状况：</label>
					<select id="custMarriage" name="customer.custMarriage" class="cf_input_text178">
						<option value="">请选择</option>
						<c:forEach items="${fns:getDictList('tz_marital_state') }" var="item">
							<option value="${item.value }" <c:if test="${customer.custMarriage==item.value}">selected</c:if>>${item.label }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<label class="lab"><span class='red'></span>英文姓名：</label>
					<input id="custEname" name="customer.custEname" value="${customer.custEname }" type="text" english="1" maxlength="50" class="cf_input_text178">
				</td>
				<td>
					<label class="lab"><span class='red'></span>母亲姓氏：</label>
					<input id="custMotherName" name="customer.custMotherName" value="${customer.custMotherName }" type="text" maxlength="50" class="cf_input_text178">
				</td>
				<td>
					<label class="lab"><span class='red'>*</span>客户来源：</label>
					<select id="dictCustSource" name="customer_dictCustSource" disabled="disabled" class="cf_input_text178">
						<c:forEach items="${fns:getDictList('tz_customer_src') }" var="item">
							<option value="${item.value }" <c:if test="${customer.dictCustSource==item.value}">selected</c:if>>${item.label }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<label class="lab"><span class='red'>*</span>职业：</label>
					<select id="custOccupation" name="customer.custOccupation" class="cf_input_text178" select_required="1">
						<option value="">请选择</option>
						<c:forEach items="${fns:getDictList('tz_prof') }" var="item">
							<option value="${item.value }" <c:if test="${customer.custOccupation==item.value}">selected</c:if>>${item.label }</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<label class="lab"><span class='red'></span>单位规模：</label>
					<select id="custUnitScale" name="customer.custUnitScale" class="cf_input_text178" >
						<option value="">请选择</option>
						<c:forEach items="${fns:getDictList('tz_unit_size') }" var="item">
							<option value="${item.value }" <c:if test="${customer.custUnitScale==item.value}">selected</c:if>>${item.label }</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<label class="lab"><span class='red'></span>工作年限：</label>
					<input id="custWorkExperience" name="customer.custWorkExperience" value="${customer.custWorkExperience }" type="text"  maxlength="20" class="cf_input_text178">
			    </td>
			</tr>
			<tr>
				<td>
					<label class="lab">工作单位：</label>
					<input id="custUnitname" name="customer.custUnitname" value="${customer.custUnitname }" type="text" disabled="disabled" class="cf_input_text178">
				</td>
				<td>
					<label class="lab">行业：</label>
					<input id="custIndustry" name="customer.custIndustry" value="${customer.custIndustry }" type="text" disabled="disabled" class="cf_input_text178">
				</td>
				<td>
					<label class="lab">职务：</label>
					<input id="custPost" name="customer.custPost" value="${customer.custPost }" type="text" disabled="disabled" class="cf_input_text178">
				</td>
			</tr>
			<tr>
				<td>
					<label class="lab"><span class='red'>*</span>移动电话：</label>
					<input id="custMobilephone" name="customer.custMobilephone" type="text" value="${customer.custMobilephone }" disabled="disabled" class="cf_input_text178">
				</td>
				<td>
					<label class="lab"><span class='red'></span>固定电话：</label>
					<input id="custPhone" name="customer.custPhone" type="text" value="${customer.custPhone }" disabled="disabled" class="cf_input_text178">
				</td>
				<td>
					<label class="lab"><span class='red'>*</span>电子邮箱：</label>
					<input id="custEmail" name="customer.custEmail" type="text" value="${customer.custEmail }" class="cf_input_text178" required>
				</td>
			</tr>
			<tr>
			<td>
				 <label class="lab"><span class="red">*</span><input id="btnSendCodePhone" type="button" value="验证码" onclick="sendMessage('1')" class='btn btn_sc'/>
                </label>
                <!-- <input id="phonePin" type="text" name="phonePin" class="cf_input_text178" /> -->
                <input id="phonePin" type="text" name="phonePin" class="cf_input_text178" required/>
			</td>
			<td></td>
			<td>
				<label class="lab"><span class="red">*</span><input id="btnSendCodeEmail" type="button" value="验证码" onclick="sendMessage('2')" class='btn btn_sc'/></label>
	            <!-- <input id="emailPin" type="text" name="emailPin"  class="cf_input_text178" />   -->
	            <input id="emailPin" type="text" name="emailPin"  class="cf_input_text178" required/>  
			</td>
			</tr>
			<tr>
				<td>
					<label class="lab"><span class='red'>*</span>首次联系时间：</label>
					<input id="custFirstContactdate" name="customer.custFirstContactdate" type="text" value="<fmt:formatDate value="${customer.custFirstContactdate }" pattern="yyyy-MM-dd"/>" disabled="disabled" class="Wdate cf_input_text178">
				</td>
				<td>
					<label class="lab">传真：</label>
					<input id="custFax" name="customer.custFax" type="text" value="${customer.custFax }" disabled="disabled" class="cf_input_text178">
				</td>
				<td>
					<label class="lab"><span class='red'>*</span>地址：</label>
					<input id="customer_addr" name="customer.address.addr" type="text" value="${customer.address.addr }" disabled="disabled" class="cf_input_text178">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label class="lab"><span class='red'>*</span>所在城市：</label>
					<input id="customer_addrProvince" name="customer.address.addrProvince" type="text" value="${fns:getProvinceLabel(customer.address.addrProvince) }" disabled="disabled" class="cf_input_text178">
					<input id="customer_addrCity" name="customer.address.addrCity" type="text" value="${fns:getCityLabel(customer.address.addrCity) }" disabled="disabled" class="cf_input_text178">
					<input id="customer_addrDistrict" name="customer.address.addrDistrict" type="text" value="${fns:getDistrictLabel(customer.address.addrDistrict) }" disabled="disabled" class="cf_input_text178">
				</td>
				<td>
					<label class="lab">邮编：</label>
					<input id="customer_addrPostalCode" name="customer.address.addrPostalCode" type="text" value="${customer.address.addrPostalCode }" disabled="disabled" class="cf_input_text178">
				</td>
			</tr>
		</table>
		</div>
		<div class="title">
	        <div class="l"><h2 class="f14 ml10">紧急联系人信息</h2></div>
	    </div>
	    <div class='box6'>
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
				<td>
					<label class="lab"><span class='red'>*</span>中文姓名：</label>
					<input id="emerName" name="emergencyContact.emerName" value="${emergencyContact.emerName }" type="text" required chinese="1" maxlength="50" class="cf_input_text178">
				</td>
				<td>
					<label class="lab"><span class='red'></span>英文姓名：</label>
					<input id="emerEname" name="emergencyContact.emerEname" value="${emergencyContact.emerEname }" type="text"  english="1" maxlength="50" class="cf_input_text178">
				</td>
				<td>
					<label class="lab"><span class='red'>*</span>性别：</label>
					<c:forEach items="${fns:getDictList('sex') }" var="item">
						<input type="radio" name="emergencyContact.emerSex" value="${item.value }" <c:if test="${emergencyContact.emerSex==item.value}">checked=checked</c:if> required>${item.label }
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>
					<label class="lab"><span class='red'></span>证件类型：</label>
					<select id="emerType" name="emergencyContact.emerType" class="cf_input_text178">
						<option value="">请选择</option>
						<c:forEach items="${fns:getDictList('com_certificate_type') }" var="item">
							<c:if test="${item.value==0 || item.value==1 || item.value==2 || item.value==3 || item.value==5}">
								<option value="${item.value }" <c:if test="${emergencyContact.emerType==item.value}">selected</c:if>>${item.label }</option>
							</c:if>
						</c:forEach>
					</select>
				</td>
				<td>
					<label class="lab"><span class='red'></span>证件号码：</label>
					<input id="emerCertNum" name="emergencyContact.emerCertNum" value="${emergencyContact.emerCertNum }" type="text"  maxlength="50" class="cf_input_text178">
				</td>
				<td>
					<label class="lab"><span class='red'>*</span>移动电话：</label>
					<input id="emerMobilephone" name="emergencyContact.emerMobilephone" value="${emergencyContact.emerMobilephone }" type="text" required mobilenum="1" maxlength="11" class="cf_input_text178">
				</td>
			</tr>
			<tr>
				<td>
					<label class="lab"><span class='red'>*</span>出生日期：</label>
					<input id="emerBirthday" name="emergencyContact.emerBirthday" value="<fmt:formatDate value="${emergencyContact.emerBirthday }" pattern="yyyy-MM-dd"/>" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:new Date()})" class="Wdate cf_input_text178" required>
				</td>
				<td>
					<label class="lab"><span class='red'>*</span>关系：</label>
					<input id="emerRelationship" name="emergencyContact.emerRelationship" value="${emergencyContact.emerRelationship }" type="text" required maxlength="50" class="cf_input_text178">
				</td>
				<td>
					<label class="lab"><span class='red'></span>固定电话：</label>
					<input id="emerPhone" name="emergencyContact.emerPhone" value="${emergencyContact.emerPhone }" type="text"  digits="1" maxlength="20" class="cf_input_text178">
				</td>
			</tr>
			<tr>
				<td>
					<label class="lab">电子邮箱：</label>
					<input id="emerEmail" name="emergencyContact.emerEmail" value="${emergencyContact.emerEmail }" type="text" maxlength="50" email='1' class="cf_input_text178"></td>
				<td>
					<label class="lab"><span class='red'>*</span>地址：</label>
					<input id="emer_addr" name="emergencyContact.address.addr" value="${emergencyContact.address.addr }" type="text" required maxlength="100" class="cf_input_text178">
				</td>
				<td>
					<label class="lab"><span class='red'></span>邮编：</label>
					<input id="emer_addrPostalCode" name="emergencyContact.address.addrPostalCode" value="${emergencyContact.address.addrPostalCode }" type="text"  postnum="1" maxlength="50" class="cf_input_text178">
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<label class="lab"><span class='red'>*</span>所在城市：</label>
					<select id="emer_addrProvince" name="emergencyContact.address.addrProvince" select_required="1" class="cf_input_text178">
						<option value="">请选择</option>
						<c:forEach items="${provinceList }" var="item">
						   <option value="${item.id}" <c:if test="${item.id==emergencyContact.address.addrProvince }">selected=selected</c:if>>${item.areaName }</option>
						</c:forEach>
					</select>
					<select id="emer_addrCity" name="emergencyContact.address.addrCity" select_required="1" class="cf_input_text178">
						<option value="">请选择</option>
						<c:forEach items="${cityList }" var="item">
						   <option value="${item.id}" <c:if test="${item.id==emergencyContact.address.addrCity }">selected=selected</c:if>>${item.areaName }</option>
						</c:forEach>
					</select>
					<select id="emer_addrDistrict" name="emergencyContact.address.addrDistrict" select_required="1" class="cf_input_text178">
						<option value="">请选择</option>
						<c:forEach items="${districtList }" var="item">
						   <option value="${item.id}" <c:if test="${item.id==emergencyContact.address.addrDistrict }">selected=selected</c:if>>${item.areaName }</option>
						</c:forEach>
					</select>
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
					<select id="loanBillRecv" name="loanConfiguration.loanBillRecv"  class="cf_input_text178">
						<c:forEach items="${fns:getDictList('tz_billtaken_mode') }" var="item">
							<c:if test="${item.value != '1' }">
								<option value="${item.value }" <c:if test="${loanConfiguration.loanBillRecv==item.value}">selected</c:if>>${item.label }</option>
							</c:if>
						</c:forEach>
					</select>
				</td>
				<td>
					<label class="lab"><span class='red'></span>签订委托协议：</label>
					<select id="loanSign" name="loanConfiguration.loanSign"  class="cf_input_text178">
						<option value="">请选择</option>
						<c:forEach items="${fns:getDictList('tz_yes_no') }" var="item">
							<option value="${item.value }" <c:if test="${loanConfiguration.loanSign==item.value}">selected</c:if>>${item.label }</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<label class="lab"><span class='red'></span>委托协议种类：</label>
					<select id="loanAgreementType" name="loanConfiguration.loanAgreementType"  class="cf_input_text178">
						<option value="">请选择</option>
						<c:forEach items="${fns:getDictList('tz_protocol_type') }" var="item">
							<option value="${item.value }" <c:if test="${loanConfiguration.loanAgreementType==item.value}">selected</c:if>>${item.label }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<label class="lab"><span class='red'></span>委托协议版本：</label>
					<select id="loanAgreementEdition" name="loanConfiguration.loanAgreementEdition"  class="cf_input_text178">
						<option value="">请选择</option>
						<c:forEach items="${fns:getDictList('tz_protocol_version') }" var="item">
							<option value="${item.value }" <c:if test="${loanConfiguration.loanAgreementEdition==item.value}">selected</c:if>>${item.label }</option>
						</c:forEach>
					</select>
				</td>
				<td colspan="2">
					<label class="lab"><span class='red'></span>签订协议日期：</label>
					<input type="text" id="agreemrntSignDate" name="loanConfiguration.agreemrntSignDate" value="<fmt:formatDate value="${loanConfiguration.agreemrntSignDate}" pattern="yyyy-MM-dd"/>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate cf_input_text178" >
				</td>
			</tr>
		</table>
		</div>
		<input type="hidden" name="customer.id" value="${customer.id}">
		<input type="hidden" name="customer.custCode" value="${customer.custCode}">
		<input type="hidden" name="emergencyContact.custCode" value="${emergencyContact.custCode}">
		<input type="hidden" name="emergencyContact.address.id" value="${emergencyContact.address.id}">
		<input type="hidden" name="loanConfiguration.custCode" value="${loanConfiguration.custCode}">
		<div class="tright pr30">
			<!-- <input id="btnPreview" class="btn cf_btn-primary" type="button" value="预览申请书" /> -->
			<input id="btnSubmit" class="btn cf_btn-primary" type="button" value="开户" />
			<input id="btnCancel" class="btn cf_btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
		</div>
	</form>
</body>
</html>