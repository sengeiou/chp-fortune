<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借变更重新发起</title>
	<meta name="decorator" content="default"/>
	<script src="/chp-fortune/static//jquery-plugin/jquery.json-2.4.js" ></script>
	<script type="text/javascript" src="${ctxWebInf}/js/lendChange/lendChangeFileInit.js"></script>
	<script type="text/javascript" src="${ctxWebInf}/js/lendChange/lendChange_launch.js"></script>
    <script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
</head>
<body>
     <div class="title">
        <div class="l"><h2 class="f14 ml10">客户信息</h2></div>
		<div class="r">
            <input type="button" class="btn btn_sc ml10" value="下载模板" onclick="templates('${workItem.bv.changeBegin.lendloanapply.applyCode}')" />
            <input type="button" class="btn btn_sc" onclick="goLendChangeHistory('${workItem.bv.changeBegin.lendloanapply.applyCode}','${ctx}')" value="变更历史"/>
        </div>
    </div>
<div class="body_r">
        <form:form id="searchForm" commandName="workItem" action="${ctx}/lendChangeReject/newLaunchFlow" method="post" >
		<sys:message content="${message}"/>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
            	<!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td><label class="lab"><span class="red">*</span>客户姓名：</label><input type="text" name="lendloanapply.custName" value="***" readonly="readonly" class="cf_input_text178"></td>
                <%-- <td><label class="lab"><span class="red">*</span>客户姓名：</label><input type="text" name="lendloanapply.custName" value="${workItem.bv.changeBegin.lendloanapply.custName}" readonly="readonly" class="cf_input_text178"></td> --%>
                <td><label class="lab"><span class="red">*</span>客户编号：</label><input type="text" id="customerCode" name="lendloanapply.custCode" value="${workItem.bv.changeBegin.lendloanapply.custCode}" readonly="readonly" class="cf_input_text178"></td>
                <td><label class="lab"><span class="red">*</span>出借编号：</label><input type="text" name="lendloanapply.applyCode" value="${workItem.bv.changeBegin.lendloanapply.applyCode}" readonly="readonly" class="cf_input_text178"></td>
            </tr>
            <c:if test="${workItem.bv.changeBegin.lendloanapply.trusteeshipNo ne null&&workItem.bv.changeBegin.lendloanapply.trusteeshipNo ne ''}">
				<tr>
		           	<td><label class="lab"><span class="red">*</span>金账户账号：</label><input  name="lendloanapply.trusteeshipNo" type="text" readonly="readonly"  value="${workItem.bv.changeBegin.lendloanapply.trusteeshipNo}" class="cf_input_text178"></td>
		        	<td colspan="2"><label class="lab"><span class="red">*</span>托管状态：</label><input  type="text" readonly="readonly" value="${fns:dictName(workItem.bv.dicts.tz_trust_state,workItem.bv.changeBegin.lendloanapply.applyHostedState,'-')}" class="cf_input_text178"></td>
		        </tr>
			</c:if>
        </table>	
    </div>
    <c:if test="${workItem.bv.changeBegin.lendloanapply.applyPay!=4}">
    <div class='title'>
    <h2 class="f14 ml10">银行信息</h2>
    </div>
    <div class="box6">
        <table class="table table-striped table-bordered table-condensed">
            <thead>
            <tr>
                <th>付款银行</th>
                <th>回款银行</th>
                <th>开户行</th>
                <th>具体支行</th>
                <th>开户行所在城市</th>
                <th>卡或折</th>
                <th>开户姓名</th>
                <th>银行账户</th>
           </tr>
           </thead>
           <tr>
                <td><input type="radio" name="accountPay" value="" disabled="disabled" checked="checked"/></td>
				<td><input type="radio" name="countAfter.id" value="${workItem.bv.changeBegin.payAccount.id}" /></td>
				<td>
					<select class="select78_24 " disabled="disabled">
						<c:forEach items="${workItem.bv.dicts.tz_open_bank}" var="d">
							<c:choose>
								<c:when test="${d.value eq  workItem.bv.changeBegin.payAccount.accountBankId}">
									<option selected="selected">${d.label}</option>
								</c:when>
								<c:otherwise>
									<option>${d.label}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
				<td><input type="text" readonly="readonly"  value="${workItem.bv.changeBegin.payAccount.accountBranch}"  class="select78_24 " /></td>
				<td>
					<select class="select78_24 " disabled="disabled"><option>请选择</option><option selected="selected">${fns:getProvinceLabel(workItem.bv.changeBegin.payAccount.accountAddrProvince)}</option></select>
					<select class="city-select select78_24 " disabled="disabled"><option>请选择</option><option selected="selected">${fns:getCityLabel(workItem.bv.changeBegin.payAccount.accountAddrCity)}</option></select>
					<select class="select78_24 " disabled="disabled"><option>请选择</option><option selected="selected">${fns:getDistrictLabel(workItem.bv.changeBegin.payAccount.accountAddrDistrict)}</option></select>
				</td>
				<td>
				    <select class="select78_24 " disabled="disabled">
				    <c:forEach items="${workItem.bv.dicts.com_card_type}" var="d">
							<c:choose>
								<c:when test="${d.value eq  workItem.bv.changeBegin.payAccount.accountCardOrBooklet}">
									<option selected="selected">${d.label}</option>
								</c:when>
								<c:otherwise>
									<option value="${d.value}">${d.label}</option>
								</c:otherwise>
							</c:choose>
				    </c:forEach>
				    </select>
				</td>
				<td><input type="text" readonly="readonly"  value="${workItem.bv.changeBegin.payAccount.accountName}"  class="select78_24 " /></td>
				<td><input type="text" readonly="readonly"  value="${workItem.bv.changeBegin.payAccount.accountNo}"  class="select78_24 " /></td>
				<input type="hidden" readonly="readonly"  value="${workItem.bv.changeBegin.payAccount.accountBranch}"  class="select78_24 " />
                </tr>
                <tr>
                <td><input type="radio" name="accountPay" value="" disabled="disabled" /></td>
				<td><input type="radio" name="countAfter.id" value="${workItem.bv.changeAfter.countAfter.id}" checked="checked"/></td>
				<td>
				   <select class="select78_24 " name="countAfter.accountBankId" readonly="readonly" onchange="changeReyBank(this.options[this.options.selectedIndex].value);">
				   <c:forEach items="${workItem.bv.dicts.tz_open_bank}" var="d">
							<c:choose>
								<c:when test="${d.value eq  workItem.bv.changeAfter.countAfter.accountBankId}">
									<option selected="selected" value="${d.value}">${d.label}</option>
								</c:when>
								<c:otherwise>
									<option value="${d.value}">${d.label}</option>
								</c:otherwise>
							</c:choose>
				  </c:forEach>
				  </select>
				</td>
				<td>
					<c:forEach items="${workItem.bv.dicts.tz_open_bank_kl}" var="d">
						<script type="text/javascript">
							kalianBankCodeObj['${d.value}'] = '${d.label}';
						</script>
					</c:forEach>
					<input type="text" name="countAfter.accountBranch" value="${workItem.bv.changeAfter.countAfter.accountBranch}" required checkbranch="1"  class="select78_24 " onblur="checkbranch()" />
					<!-- <a href="javascript:void(0);" class="" style="" onclick="openBranchCode(this);">&nbsp;<i class="icon-search"></i>&nbsp;</a>&nbsp;&nbsp; -->
				</td>
				<td>
					<select class="select78_24 " name="countAfter.accountAddrProvince" id="addrProvince" select_required="1" >
					<c:forEach items="${workItem.bv.changeAfter.provinceList}" var="item">
					   <c:choose>
					     <c:when test="${item.id eq workItem.bv.changeAfter.countAfter.accountAddrProvince}">
					       <option selected="selected" value="${item.id}">${item.areaName}</option>
					     </c:when>
					     <c:otherwise><option value="${item.id}">${item.areaName}</option></c:otherwise>
					   </c:choose>
					</c:forEach>	
					</select>
					<select class="city-select select78_24" name="countAfter.accountAddrCity" id="addrCity" select_required="1" >
					<c:forEach items="${fns:getCityByProvinceId(workItem.bv.changeAfter.countAfter.accountAddrProvince)}" var="item">
					   <c:choose>
					     <c:when test="${item.id eq workItem.bv.changeAfter.countAfter.accountAddrCity}">
					       <option selected="selected" value="${item.id}">${item.areaName}</option>
					     </c:when>
					     <c:otherwise><option value="${item.id}">${item.areaName}</option></c:otherwise>
					   </c:choose>
				    </c:forEach>
					</select>
					<select class="select78_24 " name="countAfter.accountAddrDistrict" id="addrDistrict" select_required="1"   >
					<c:forEach items="${fns:getDistrictByCityId(workItem.bv.changeAfter.countAfter.accountAddrCity)}" var="item">
					   <c:choose>
					     <c:when test="${item.id eq workItem.bv.changeAfter.countAfter.accountAddrDistrict}">
					       <option selected="selected" value="${item.id}">${item.areaName}</option>
					     </c:when>
					     <c:otherwise><option value="${item.id}">${item.areaName}</option></c:otherwise>
					   </c:choose>
				   </c:forEach>
				   </select>
				</td>
				<td>
				    <select class="select78_24 " name="countAfter.accountCardOrBooklet" readonly="readonly" select_required="1">
				    <c:forEach items="${workItem.bv.dicts.com_card_type}" var="d">
							<c:choose>
								<c:when test="${d.value eq  workItem.bv.changeAfter.countAfter.accountCardOrBooklet}">
									<option selected="selected" value="${d.value}">${d.label}</option>
								</c:when>
								<c:otherwise>
									<option value="${d.value}">${d.label}</option>
								</c:otherwise>
							</c:choose>
				    </c:forEach>
				    </select>
				</td>
				<td><input type="text" name="countAfter.accountName" readonly="readonly" value="${workItem.bv.changeAfter.countAfter.accountName}"  class="select78_24 " /></td>
				<td><input type="text" name="countAfter.accountNo" value="${workItem.bv.changeAfter.countAfter.accountNo}"  class="select78_24 " required digits="1" /></td>
						
           </tr>
        </table>
    </div>
    </c:if>
     <c:if test="${workItem.bv.changeBegin.lendloanapply.applyPay==4}">
    <div class='title'>
    <h2 class="f14 ml10">金账户银行信息</h2>
    </div>
    <div class="box6">
        <table class="table table-striped table-bordered table-condensed">
            <thead>
            <tr>
                <th>开户行</th>
                <th>具体支行</th>
                <th>开户行所在城市</th>
                <th>卡或折</th>
                <th>开户姓名</th>
                <th>银行账户</th>
           </tr>
           </thead>
                <tr>
				<td>
					<input type="hidden" name="countAfter.id" value="${workItem.bv.changeAfter.countAfter.id}" />
				   <select class="select78_24 " name="countAfter.accountBankId" readonly="readonly" >
				   <c:forEach items="${workItem.bv.dicts.tz_open_bank}" var="d">
							<c:choose>
								<c:when test="${d.value eq  workItem.bv.changeAfter.countAfter.accountBankId}">
									<option selected="selected" value="${d.value}">${d.label}</option>
								</c:when>
								<c:otherwise>
									<option value="${d.value}">${d.label}</option>
								</c:otherwise>
							</c:choose>
				  </c:forEach>
				  </select>
				</td>
				<td><input type="text" name="countAfter.accountBranch" value="${workItem.bv.changeAfter.countAfter.accountBranch}" required checkbranch="1"  class="select78_24 " onblur="checkbranch()" /></td>
				<td>
					<select class="select78_24 " name="countAfter.accountAddrProvince" id="addrProvince" select_required="1" >
					<c:forEach items="${workItem.bv.changeAfter.provinceList}" var="item">
					   <c:choose>
					     <c:when test="${item.areaCode eq workItem.bv.changeAfter.countAfter.accountAddrProvince}">
					       <option selected="selected" value="${item.areaCode}">${item.areaName}</option>
					     </c:when>
					     <c:otherwise><option value="${item.areaCode}">${item.areaName}</option></c:otherwise>
					   </c:choose>
					</c:forEach>	
					</select>
					<select class="city-select select78_24" name="countAfter.accountAddrCity" id="addrCity" select_required="1" >
					<c:forEach items="${fns:getFYCityList(workItem.bv.changeAfter.countAfter.accountAddrProvince,'')}" var="item">
					   <c:choose>
					     <c:when test="${item.areaCode eq workItem.bv.changeAfter.countAfter.accountAddrCity}">
					       <option selected="selected" value="${item.areaCode}">${item.areaName}</option>
					     </c:when>
					     <c:otherwise><option value="${item.areaCode}">${item.areaName}</option></c:otherwise>
					   </c:choose>
				    </c:forEach>
					</select>
				</td>
				<td>
				    <select class="select78_24 " name="countAfter.accountCardOrBooklet" readonly="readonly" select_required="1">
				    <c:forEach items="${workItem.bv.dicts.com_card_type}" var="d">
							<c:choose>
								<c:when test="${d.value eq  workItem.bv.changeAfter.countAfter.accountCardOrBooklet}">
									<option selected="selected" value="${d.value}">${d.label}</option>
								</c:when>
								<%-- <c:otherwise>
									<option value="${d.value}">${d.label}</option>
								</c:otherwise>  --%>
							</c:choose>
				    </c:forEach>
				    </select>
				</td>
				<td><input type="text" name="countAfter.accountName" readonly="readonly" value="${workItem.bv.changeAfter.countAfter.accountName}"  class="select78_24 " /></td>
				<td><input type="text" name="countAfter.accountNo" value="${workItem.bv.changeAfter.countAfter.accountNo}"  class="select78_24 " required digits="1" /></td>
						
           </tr>
        </table>
    </div>
    </c:if>
    <div class='title'>
    <h2 class="f14 ml10">出借信息</h2>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab"><span class="red">*</span>出借申请日期：</label><input type="text" name="lendloanapply.applyDate" value="${workItem.bv.changeBegin.lendloanapply.applyDate}" readonly="readonly" class="cf_input_text178"></td>
                <td><label class="lab"><span class="red">*</span>计划划扣日期：</label><input type="text" id="deductDate" name="lendloanapply.applyDeductDate" value="${workItem.bv.changeBegin.lendloanapply.applyDeductDate}" readonly="readonly" class="cf_input_text178"></td>
                <td><label class="lab"><span class="red">*</span>计划出借日期：</label><input type="text" name="lendloanapply.applyLendDate" value="${workItem.bv.changeBegin.lendloanapply.applyLendDate}" readonly="readonly" class="cf_input_text178"></td>
            </tr>
            <tr>
                <td><label class="lab"><span class="red">*</span>付款方式：</label><input type="text" name="lendloanapply.applyPay" value="${fns:dictName(workItem.bv.dicts.tz_pay_type,workItem.bv.changeBegin.lendloanapply.applyPay,'-')}" readonly="readonly" class="cf_input_text178"></td>
                <td><label class="lab"><span class="red">*</span>协议版本号：</label><input type="text" name="lendloanapply.applyAgreemenEdition" value="${fns:dictName(workItem.bv.dicts.tz_contract_vesion,workItem.bv.changeBegin.lendloanapply.applyAgreemenEdition,'-')}" readonly="readonly" class="cf_input_text178"></td>
                <td><label class="lab"><span class="red">*</span>计划出借金额：</label><input type="text" name="lendloanapply.applyLendMoney" value="${fns:getFormatNumber(workItem.bv.changeBegin.lendloanapply.applyLendMoney,'#0.0000')}" readonly="readonly" class="cf_input_text178"></td>
            </tr>
            <tr>
                <td><label class="lab"><span class="red">*</span>出借模式：</label><input type="text" name="lendloanapply.productName" value="${workItem.bv.changeBegin.lendloanapply.productName}" readonly="readonly" class="cf_input_text178"></td>
                <td><label class="lab"><span class="red">*</span>合同编号：</label><input type="text" name="lendloanapply.applyContractNo" value="${workItem.bv.changeBegin.lendloanapply.applyContractNo}" readonly="readonly" class="cf_input_text178"></td>
                
				<td style="display:none;"><label class="lab"><span class="red">*</span>销售折扣率（%）：</label><input type="text" name="lendloanapply.applySalesDiscount" value="${workItem.bv.changeBegin.lendloanapply.applySalesDiscount}" readonly="readonly" class="cf_input_text178"></td>
            </tr>
        </table>
    </div>
    <div class="title">
	        <div class="l"><h2 class="f14 ml10 lendChangeBackFormFileClass">附件</h2></div>
	    </div>
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td>
					<sys:attachment></sys:attachment>
				</td>
			</tr>
		</table>
	<div class='title'>
        <h2 class="f14 ml10">审批信息</h2>   
    </div>     
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td style="width:15%"><label class="lab" style="width:170px;"><span class="red">*</span>门店经理审批意见：</label><textarea class="textarea_refuse" readonly="readonly" >${workItem.bv.changeLog.auditCheckExamine}</textarea></td>
            </tr>
            <tr>
                <td style="width:15%"><label class="lab" style="width:170px;"><span class="red">*</span>门店经理审批结果：</label><input type="radio" name="one" disabled="disabled" <c:out value="${workItem.bv.changeLog.auditResult==1?'checked':'' }"/> />&nbsp;通过&nbsp;<input type="radio" name="one" disabled="disabled" <c:out value="${workItem.bv.changeLog.auditResult==2?'checked':'' }"/>/>&nbsp;不通过</td>
            </tr>
            <tr>
                <td style="width:15%"><label class="lab" style="width:170px;"><span class="red">*</span>业务对接专员审批意见：</label><textarea  class="textarea_refuse" readonly="readonly">${workItem.bv.changInfoRiew.auditCheckExamine}</textarea></td>
            </tr>
            <tr>
                <td style="width:15%"><label class="lab" style="width:170px;"><span class="red">*</span>对接专员审批结果：</label><input type="radio" disabled="disabled" name="auditResult"  <c:out value="${workItem.bv.changInfoRiew.auditResult==1?'checked':'' }"/> />&nbsp;通过&nbsp;<input type="radio" disabled="disabled" name="auditResult" <c:out value="${workItem.bv.changInfoRiew.auditResult==2?'checked':'' }"/> />&nbsp;不通过</td>
            </tr>
        </table>
    </div>       
</div>
    <div class="tright pr30 mb30">
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
        <input type="hidden"  value="${workItem.bv.changeInfo.applyId}" name="applyId"></input>
        <input type="hidden"  value="${workItem.bv.changeInfo.changeId}" name="lendchangeinfo.changeId"></input>
        <input type="hidden"  name="lendloanapply.managerId" value="${workItem.bv.changeBegin.lendloanapply.managerId}"/>
        <input type="hidden" name="lendloanapply.teamEmp" value="${workItem.bv.changeBegin.lendloanapply.teamEmp}"/>
        <input type="hidden" name="lendloanapply.addrProvince" value="${workItem.bv.changeBegin.lendloanapply.addrProvince}"/>
        <input type="hidden" name="lendloanapply.addrCity" value="${workItem.bv.changeBegin.lendloanapply.addrCity}"/>
        <input type="hidden" name="lendloanapply.orgName" value="${workItem.bv.changeBegin.lendloanapply.orgName}"/>
        <input type="hidden" name="lendloanapply.applyHostedState" value="${workItem.bv.changeBegin.lendloanapply.applyHostedState}"/>
        <input type="hidden" name="lendloanapply.custState" value="${workItem.bv.changeBegin.lendloanapply.custState}"/>
        <input type="hidden" name="lendloanapply.dictCustSource" value="${workItem.bv.changeBegin.lendloanapply.dictCustSource}"/>
        <input type="hidden" name="lendloanapply.applyState" value="${workItem.bv.changeBegin.lendloanapply.applyState}"/>
        <input type="hidden" name="lendloanapply.createTime" value="${workItem.bv.changeBegin.lendloanapply.createTime}"/>
		<input type="hidden" name="lendloanapply.id" value="${workItem.bv.changeBegin.lendloanapply.id}"></input>
 		<input type="hidden"  name="lendloanapply.custCertType" value="${workItem.bv.changeBegin.lendloanapply.custCertType}"/>
  		<input type="hidden" name="lendloanapply.custCertNum" value="${workItem.bv.changeBegin.lendloanapply.custCertNum}"/>
		<input type="hidden" id="pageType" value="lendChangeBackForm">
        <input type="hidden" id="branchCode" name="countAfter.bankCode" value="${workItem.bv.changeAfter.countAfter.bankCode}"/>
		<input type="hidden"  name="lendloanapply.custMobilephone" value="${workItem.bv.changeBegin.lendloanapply.custMobilephone}"/>
	</div>	
	</form:form> 
	<div id="modal-sub" class="modal fade subwindow">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">银行信息</h4>
			</div>
			<div class="modal-body">
			</div>
			<div class="modal-footer">
				<input type="button" value="带回" class="btn cf_btn-primary" id="selectedBranchCode"/>
				<input type="button" value="关闭" class="btn cf_btn-primary" id="subClose"/>
			</div>
		</div>
	</div>
</div>
</body>
</html>