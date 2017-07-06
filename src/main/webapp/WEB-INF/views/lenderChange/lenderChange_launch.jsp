<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借人信息变更申请</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxWebInf}/js/lenderChange/lenderChangeFileInit.js"></script>
	<script type="text/javascript" src="${ctxWebInf}/js/lenderChange/lenderChange_launch.js"></script>
	<script type="text/javascript" src="${ctxWebInf}/js/lenderChange/lenderChange_phone_email.js"></script>
</head>
<body >
<div class="body_r">
<form:form id="searchForm" commandName="workItem" action="${ctx}/lenderChange/apply/launchFlow" method="post" >
	  <sys:message content="${message}"/>	
	 <div class="title">
        <div class="l"><h2 class="f14 ml10">客户信息</h2></div>
		<div class="r">
            <input type='button' class="btn btn_sc ml10" value="下载模板" onclick="templates('${workItem.bv.customer.custCode}')">
            <c:if test="${workItem.bv.customer.isGoldAccount eq '1'}">
            	<input type='button' class="btn btn_sc ml10" value="下载金账户销户模板" onclick="templatesGoldCancel()">
            </c:if>
            <input type="button" class="btn btn_sc" onclick="goLenderChangeHistory('${workItem.bv.customer.custCode}','${ctx}')" value="变更历史"/>
        </div>
    <div class=" pb5 pt10 pr30">
    </div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
            	<!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td width="31%"><label class="lab"><span class="red">*</span>客户姓名：</label><input type="text" name="customer.custName" readonly="readonly" value="***" class="cf_input_text178"></td>
                <%-- <td width="31%"><label class="lab"><span class="red">*</span>客户姓名：</label><input type="text" name="customer.custName" readonly="readonly" value="${workItem.bv.customer.custName}" class="cf_input_text178"></td> --%>
                <td width="31%"><label class="lab">英文名称：</label><input type="text" class="cf_input_text178" name="customer.custEname" value="***" alphabet="1"></td>
                <%-- <td width="31%"><label class="lab">英文名称：</label><input type="text" class="cf_input_text178" name="customer.custEname" value="${workItem.bv.customer.custEname}" alphabet="1"></td> --%>
                <td><label class="lab">性别：</label><input type="radio" name="customer.dictCustSex" readonly="readonly" value="1" disabled="false"  <c:out value="${workItem.bv.customer.dictCustSex==1?'checked':'' }"/> />男&nbsp;&nbsp;&nbsp;&nbsp;<input name="customer.dictCustSex" type="radio" disabled="false" value="2" <c:out value="${workItem.bv.customer.dictCustSex==2?'checked':'' }"/> />女
                </td>
            </tr>
            <tr>
                <td>
                <!-- 屏蔽客户姓名/手机号/身份证号 -->
                <label class="lab"><span class="red">*</span>移动电话：</label>
                <input type="text" required mobilenum2="1" name="customer.custMobilephone" value="***" class="cf_input_text178"  onchange="isChanger('1')">
                <%-- <input type="text" required mobilenum="1" name="customer.custMobilephone" value="${workItem.bv.customer.custMobilephone}" class="cf_input_text178"  onchange="isChanger('1')"> --%>
                <input type="button" style="display: none" id="phoneCheck" class="" value="手机号校验" onclick="checkPhone()" >
                </td>
                <td><label class="lab">固定电话：</label><input type="text" class="cf_input_text178" name="customer.custPhone"  telephonenum2="1" value="***"></td>
                <%-- <td><label class="lab">固定电话：</label><input type="text" class="cf_input_text178" name="customer.custPhone"  telephonenum="1" value="${workItem.bv.customer.custPhone}"></td> --%>
                <td><label class="lab">婚姻状况：</label>
               <select name="customer.custMarriage">
				     <option value="">请选择</option>
                      <c:forEach items="${workItem.bv.dicts.tz_marital_state}" var ="d" >
                	     <c:choose>
						  <c:when test="${d.value eq  workItem.bv.customer.custMarriage}">
							<option selected="selected" value="${d.value}">${d.label}</option>
						  </c:when>
						<c:otherwise>
							<option value="${d.value}">${d.label}</option>
						</c:otherwise>
			             </c:choose>
                      </c:forEach>
               <select>
                </td>
            </tr>
            <tr id="phoneVerify" style="display: none">
                <td colspan="3">
                <label class="lab"><span class="red">*</span><input id="btnSendCodePhone" type="button" value="发送验证码" onclick="sendMessage('1')" />
                </label>
                <input id="phonePin" type="text" class="cf_input_text178" />   
                </td>
            </tr>
            <tr>
                <td>
                <label class="lab">
                <span class="red">*</span>发证机关：</label>
                <input type="text" name="customer.custCertOrg" required  value="${workItem.bv.customer.custCertOrg}" class="cf_input_text178" />
                </td>            
                <td><label class="lab">签发日期：</label><input type="text"  name="customer.custCertIssuedate" class="cf_input_text178 Wdate"
				value="<fmt:formatDate value="${workItem.bv.customer.custCertIssuedate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" name="customer.custCertIssuedate" ></td>
				<td><label class="lab">长期有效：</label><input type="checkbox" name="customer.custCertPermanent" id="custCertPermanent" value="1" class=""  <c:out value="${workItem.bv.customer.custCertPermanent==1?'checked':'' }"/> /></td>
            </tr>
			<tr>
                <td >
					<label class="lab"><span class="red">*</span>电子邮箱：</label><input type="text"  name="customer.custEmail" value="${workItem.bv.customer.custEmail}" class="cf_input_text178" required email="1"  onchange="isChanger('2')"/>
				</td>
                <td >
					<label class="lab" >通讯地址：</label><input type="text" name="customer.address.addr" class="cf_input_text178"  value="${workItem.bv.customer.address.addr}">
                <td >
					<label class="lab" >邮政编码：</label><input type="text" name="customer.address.addrPostalCode" class="cf_input_text178" value="${workItem.bv.customer.address.addrPostalCode}"  postnum="1">
				</td>
            </tr>
            <tr id="emailVerify" style="display: none">
                <td colspan="3">
                <label class="lab"><span class="red">*</span><input id="btnSendCodeEmail" type="button" value="验证码" onclick="sendMessage('2')" /></label>
                <input id="emailPin" type="text"  class="cf_input_text178" />   
                </td>
            </tr>
			<tr>
				<td colspan='3'><label class="lab"><span class="red">*</span>所在地区：</label>
				<select class="select7801 mr3401"  name="customer.address.addrProvince" id="addrProvince" select_required="1">
				<option value="">请选择</option>
				<c:forEach items="${workItem.bv.provinceList}" var="item">
					       <option value="${item.id}" <c:if test="${item.id eq workItem.bv.customer.address.addrProvince}">
	                					selected
	                				</c:if>
	                		>${item.areaName}</option>
				</c:forEach>	
				</select>
				<select class="select7801 mr3401" name="customer.address.addrCity" id="addrCity" select_required="1">
				<option value="">请选择</option>
				<c:forEach items="${fns:getCityByProvinceId(workItem.bv.customer.address.addrProvince)}" var="item">
					       <option value="${item.id}" <c:if test="${item.id eq workItem.bv.customer.address.addrCity}">
	                					selected
	                				</c:if>
	                				>${item.areaName}</option>
				</c:forEach>
				</select>
				<select class="select7801 mr3401" name="customer.address.addrDistrict" id="addrDistrict" select_required="1">
				<option value="">请选择</option>
				<c:forEach items="${fns:getDistrictByCityId(workItem.bv.customer.address.addrCity)}" var="item">
					       <option value="${item.id}" 
					       <c:if test="${item.id eq workItem.bv.customer.address.addrDistrict}">
	                					selected
	                				</c:if>
	                				>${item.areaName}</option>
				</c:forEach>
				</select>
			</td>	
			</tr>
        </table>
    </div>
    <div class='title'>
    <h2 class="f14 ml10">紧急联系人信息</h2>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
            	<!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td width="31%"><label class="lab"><span class="red">*</span>中文姓名：</label><input type="text" name="emer.emerName" required chinese2="1" value="***" class="cf_input_text178"></td>
                <%-- <td width="31%"><label class="lab"><span class="red">*</span>中文姓名：</label><input type="text" name="emer.emerName" required chinese="1" value="${workItem.bv.emer.emerName}" class="cf_input_text178"></td> --%>
                <td width="31%"><label class="lab">英文名称：</label><input type="text" name="emer.emerEname" value="***"  class="cf_input_text178"  alphabet2="1"></td>
                <%-- <td width="31%"><label class="lab">英文名称：</label><input type="text" name="emer.emerEname" value="${workItem.bv.emer.emerEname}"  class="cf_input_text178"  alphabet="1"></td> --%>
                <td><label class="lab"><span class="red">*</span>性别：</label><input type="radio" required name="emer.emerSex" value="1"  <c:out value="${workItem.bv.emer.emerSex==1?'checked':'' }"/> />男&nbsp;&nbsp;&nbsp;&nbsp;<input name="emer.emerSex" required type="radio" value="2"  <c:out value="${workItem.bv.emer.emerSex==2?'checked':'' }"/> />女
                </td>      
            </tr>
            <tr>
            	<!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td><label class="lab"><span class="red">*</span>移动电话：</label><input type="text" required mobilenum2="1" name="emer.emerMobilephone" value="***"  class="cf_input_text178" ></td>
                <%-- <td><label class="lab"><span class="red">*</span>移动电话：</label><input type="text" required mobilenum="1" name="emer.emerMobilephone" value="${workItem.bv.emer.emerMobilephone}"  class="cf_input_text178" ></td> --%>
                <td><label class="lab">固定电话：</label><input type="text" name="emer.emerPhone" value="***"  telephonenum2="1"  class="cf_input_text178"></td>
                <%-- <td><label class="lab">固定电话：</label><input type="text" name="emer.emerPhone" value="${workItem.bv.emer.emerPhone}"  telephonenum="1"  class="cf_input_text178"></td> --%>
                <td><label class="lab">电子邮箱：</label><input type="text"  email="1" name="emer.emerEmail" value="${workItem.bv.emer.emerEmail}" class="cf_input_text178"></td>
            </tr>
            
            <tr>
                <td>
					<label class="lab"><span class="red">*</span>与出借人关系：</label><input type="text" name="emer.emerRelationship" required chinese="1" value="${workItem.bv.emer.emerRelationship}" class="cf_input_text178">
                </td>
                <td><label class="lab"><span class="red">*</span>通讯地址：</label><input type="text" required name="emer.address.addr" value="${workItem.bv.emer.address.addr}" class="cf_input_text178"></td>
                <td><label class="lab">邮政编码：</label><input type="text"  postnum="1" class="cf_input_text178" name="emer.address.addrPostalCode"" value="${workItem.bv.emer.address.addrPostalCode}"></td>
            </tr>
             <tr>
                <td colspan='3'>
					<label class="lab"><span class="red">*</span>所在地区：</label>
					<select class="select7801 mr3401" name="emer.address.addrProvince" id="emerProvince" select_required="1">
					<option value="">请选择</option>
					<c:forEach items="${workItem.bv.provinceList}" var="elem">
					       <option  value="${elem.id}" <c:if test="${elem.id eq workItem.bv.emer.address.addrProvince}">
	                					selected
	                				</c:if>
	                				>${elem.areaName}</option>
					</c:forEach>
					</select>
					<select class="select7801 mr3401" name="emer.address.addrCity" id="emerCity" select_required="1">
					<c:forEach items="${fns:getCityByProvinceId(workItem.bv.emer.address.addrProvince)}" var="item">
					<option value="">请选择</option>
					       <option  value="${item.id}" <c:if test="${item.id eq workItem.bv.emer.address.addrCity}">
	                					selected
	                				</c:if>
	                				>${item.areaName}</option>
				   </c:forEach>
					</select>
					<select class="select7801 mr3401" name="emer.address.addrDistrict" id="emerDistrict" select_required="1">
					<option value="">请选择</option>
					<c:forEach items="${fns:getDistrictByCityId(workItem.bv.emer.address.addrCity)}" var="item">
					       <option  value="${item.id}" <c:if test="${item.id eq workItem.bv.emer.address.addrDistrict}">
	                					selected
	                				</c:if>
	                				>${item.areaName}</option>
				   </c:forEach>
					</select>
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
                <td width="31%">
                <label class="lab">
                <span class="red">*</span>账单收取方式：</label>
                <select class="select7801 mr3401" name="loanInfo.loanBillRecv"  select_required="1">
					<c:forEach items="${workItem.bv.dicts.tz_billtaken_mode}" var="d">
					<c:if test="${d.value != '1' }">
						<option value="${d.value }" <c:if test="${workItem.bv.loanInfo.loanBillRecv==d.value}">selected</c:if>>${d.label }</option>
					</c:if>		   
					</c:forEach>
				</select>
                </td>
                <td width="31%"><label class="lab"><span class="red">*</span>是否签订委托协议：</label><input type="radio" name="loanInfo.loanSign" required disabled="false" class="ml10 mr6" name="loanInfo.loanSign" value="1" <c:out value="${workItem.bv.loanInfo.loanSign==1?'checked':'' }"/> />是<input type="radio" name='loanInfo.loanSign' required disabled="disabled" class="ml10 mr6" name='loanSign' value="2"  <c:out value="${workItem.bv.loanInfo.loanSign==2?'checked':'' }"/> />否</td>
                <td><label class="lab">签订协议日期：</label><input type="text" name="agreemrntSignDate" readonly="readonly" class="cf_input_text178 Wdate"
				value="<fmt:formatDate value="${workItem.bv.loanInfo.agreemrntSignDate}" pattern="yyyy-MM-dd"/>"  ></td>
            </tr>
            <tr>
                <td width="31%"><label class="lab"><span class="red">*</span>委托协议种类：</label><input type="text" name="loanInfo.loanAgreementType" readonly="readonly" value="${fns:dictName(workItem.bv.dicts.tz_protocol_type,workItem.bv.loanInfo.loanAgreementType,'-')}" class="cf_input_text178"></td>
                <td width="31%"><label class="lab"><span class="red">*</span>委托协议版本号：</label><input type="text" name="loanInfo.loanAgreementEdition" readonly="readonly" value="${fns:dictName(workItem.bv.dicts.tz_protocol_version,workItem.bv.loanInfo.loanAgreementEdition,'-')}"  class="cf_input_text178"></td>     
            </tr>    
        </table>
    </div>
    <c:if test="${workItem.bv.customer.trusteeshipNo ne null&&workItem.bv.customer.trusteeshipNo ne ''}">
    <div class="title">
	    <div class="l"><h2 class="f14 ml10 lenderChange_launchFileClass">金账户信息</h2></div>
	 </div>
	 <div class='box6'>
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td width="31%">
					<label class="lab"><span class="red">*</span>金账户账号：</label>
					<input type="text" name="customer.trusteeshipNo" readonly="readonly" value="${workItem.bv.customer.trusteeshipNo}" class="cf_input_text178">
				</td>
                <td width="31%">
                	<label class="lab"><span class="red">*</span>托管状态：</label>
                	<input type="text" name="customer.applyHostedStatus" readonly="readonly"  value="${fns:dictName(workItem.bv.dicts.tz_trust_state,workItem.bv.customer.applyHostedStatus,'-')}"  class="cf_input_text178">
                </td>     
                <td width="31%">
                    <div id="accountOff">
                    <c:if test="${workItem.bv.customer.isGoldAccount eq '1'}">
               		<label class="lab"><span class="red">*</span>金账户销户：</label>
               		<input type="checkbox" name="customer.changerTypeVal"  value="6">
               		</c:if>
               		</div>
                </td>
			</tr>
		</table>
		</div>
    </c:if>
	<div class="title">
	        <div class="l"><h2 class="f14 ml10 lenderChange_launchFileClass">附件</h2></div>
	    </div>
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td>
					<sys:attachment></sys:attachment>
				</td>
			</tr>
		</table>
	<div class="tright pr30 mb30">
			<shiro:hasPermission name="apply:consult:edit"></shiro:hasPermission>
			<!-- <input id="" class="btn cf_btn-primary" type="button" onclick="sqsPreview()" value="预览申请书"/> -->
			<input id="btnSubmit" class="btn cf_btn-primary" type="button"  value="提交"/>
<!-- 			<input id="btnCancel" class="btn cf_btn-primary" type="button" value="返 回" onclick="self.location=document.referrer;"/> -->
			<input id="btnCancel" class="btn cf_btn-primary" type="button" value="返 回" onclick="window.history.back()"/>
	</div>
	<input type="hidden" name="customer.managerId" value="${workItem.bv.customer.managerId}"/>
	<input type="hidden" name="customer.custCode" id="customerCode" value="${workItem.bv.customer.custCode}"/>
	<input type="hidden" name="customer.id" value="${workItem.bv.customer.id}"/>
	<input type="hidden" name="customer.teamEmp" value="${workItem.bv.customer.teamEmp}"/>
	<input type="hidden" name="customer.addrProvince" value="${workItem.bv.customer.addrProvince}"/>
	<input type="hidden" name="customer.addrCity" value="${workItem.bv.customer.addrCity}"/>
	<input type="hidden" name="customer.managerName" value="${workItem.bv.customer.managerName}"/>
	<input type="hidden" name="customer.departmentName" value="${workItem.bv.customer.departmentName}"/>
	<input type="hidden" name="customer.teamManagerName" value="${workItem.bv.customer.teamManagerName}"/>
	<input type="hidden" name="customer.teamName" value="${workItem.bv.customer.teamName}"/>
	<input type="hidden" name="customer.dictCustSource" value="${workItem.bv.customer.dictCustSource}"/>
	<input type="hidden" name="customer.custCertNum" value="${workItem.bv.customer.custCertNum}"/>
	<input type="hidden" name="customer.address.id" value="${workItem.bv.customer.address.id}"></input>
    <input type="hidden" name="emer.address.id"  value="${workItem.bv.emer.address.id}"></input>
    <input type="hidden" name="loanInfo.id" value="${workItem.bv.loanInfo.id}" ></input>
	<input type="hidden" name="emer.id" value="${workItem.bv.emer.id}"></input>
	<input type="hidden" name="flowCode" value="${workItem.flowCode}" ></input>
    <input type="hidden" name="flowName" value="${workItem.flowName}" ></input>
    <input type="hidden" name="flowType" value="${workItem.flowType}" ></input>
    <input type="hidden" name="stepName" value="${workItem.stepName}" ></input>
    <input type="hidden"  value="${workItem.bv.customer.custMobilephone}" name="customer.custMobilephoneChanger"></input>
    <input type="hidden" id="emailInit" value="${workItem.bv.customer.custEmail}" ></input>
    <input type="hidden" value="${workItem.bv.customer.changerTypeVal}" name="changerTypeVal"/>
    <input type="hidden" name="dictChangeType" value="${workItem.bv.customer.changerTypeVal}" />  
    <input type="hidden" name="customer.custCertType" value="${workItem.bv.customer.custCertType}"/>
    <input type="hidden" id="pageType" value='lenderChange_launch'/>
</form:form>
</div> 
</body>
</html>