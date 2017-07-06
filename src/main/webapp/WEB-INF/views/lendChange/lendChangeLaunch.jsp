<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借信息变更申请</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxWebInf}/js/lendChange/lendChangeFileInit.js"></script>
    <script type="text/javascript" src="${ctxWebInf}/js/lendChange/lendChange_launch.js"></script>
    <script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
</head>
<body>
	
<form:form id="searchForm" commandName="workItem" action="${ctx}/lendChange/launchFlow" method="post" >
		 <sys:message content="${message}"/>	
	
<div class="body_r">
	 <div class="title">
        <div class="l"><h2 class="f14 ml10">客户信息</h2></div>
		<div class="r">
            <input type="button" class="btn btn_sc ml10" value="下载模板" onclick="templates('${workItem.bv.lendloanapply.applyCode}')">
            <input type="button" class="btn btn_sc" onclick="goLendChangeHistory('${workItem.bv.lendloanapply.applyCode}','${ctx}')" value="变更历史"/>
        </div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
           <tr>
           		<!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td><label class="lab"><span class="red">*</span>客户姓名：</label><input type="text" readonly="readonly" name="lendloanapply.custName" value="***" class="cf_input_text178"></td>
                <%-- <td><label class="lab"><span class="red">*</span>客户姓名：</label><input type="text" readonly="readonly" name="lendloanapply.custName" value="${workItem.bv.lendloanapply.custName}" class="cf_input_text178"></td> --%>
                <td><label class="lab"><span class="red">*</span>客户编号：</label><input type="text" readonly="readonly" id="customerCode" name="lendloanapply.custCode" value="${workItem.bv.lendloanapply.custCode}" class="cf_input_text178"></td>
                <td><label class="lab"><span class="red">*</span>出借编号：</label><input type="text" readonly="readonly" name="lendloanapply.applyCode" value="${workItem.bv.lendloanapply.applyCode}" class="cf_input_text178"></td>
            </tr>
			<c:if test="${workItem.bv.lendloanapply.trusteeshipNo ne null&&workItem.bv.lendloanapply.trusteeshipNo ne ''}">
				<tr>
		           	<td><label class="lab"><span class="red">*</span>金账户账号：</label><input name="lendloanapply.trusteeshipNo" type="text" readonly="readonly"  value="${workItem.bv.lendloanapply.trusteeshipNo}" class="cf_input_text178"></td>
		        	<td colspan="2"><label class="lab"><span class="red">*</span>托管状态：</label><input   type="text" readonly="readonly" value="${fns:dictName(workItem.bv.dicts.tz_trust_state,workItem.bv.lendloanapply.applyHostedState,'-')}" class="cf_input_text178"></td>
		        </tr>
			</c:if>				
        </table>
    </div>
    <c:if test="${workItem.bv.lendloanapply.applyPay!=4}">
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
                <td><input type="radio" name="accountPay" value="${workItem.bv.payAccount.id}" disabled="disabled" checked="checked"/></td>
				<td><input type="radio" name="countAfter.id" value="${workItem.bv.payAccount.id}" /></td>
				<td>
					<select class="select78_24 " disabled="disabled">
						<c:forEach items="${workItem.bv.dicts.tz_open_bank}" var="d">
							<c:choose>
								<c:when test="${d.value eq  workItem.bv.payAccount.accountBankId}">
									<option selected="selected">${d.label}</option>
								</c:when>
								<c:otherwise>
									<option>${d.label}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
				<td><input type="text" readonly="readonly"  value="${workItem.bv.payAccount.accountBranch}"  class="select78_24 " /></td>
				<td>
					<select class="select78_24 " disabled="disabled"><option>请选择</option><option selected="selected">${fns:getProvinceLabel(workItem.bv.payAccount.accountAddrProvince)}</option></select>
					<select class="city-select select78_24 " disabled="disabled"><option>请选择</option><option selected="selected">${fns:getCityLabel(workItem.bv.payAccount.accountAddrCity)}</option></select>
					<select class="select78_24 " disabled="disabled"><option>请选择</option><option selected="selected">${fns:getDistrictLabel(workItem.bv.payAccount.accountAddrDistrict)}</option></select>
				</td>
				<td>
				    <select class="select78_24 " disabled="disabled">
				    <c:forEach items="${workItem.bv.dicts.com_card_type}" var="d">
							<c:choose>
								<c:when test="${d.value eq  workItem.bv.payAccount.accountCardOrBooklet}">
									<option selected="selected">${d.label}</option>
								</c:when>
								<c:otherwise>
									<option value="${d.value}">${d.label}</option>
								</c:otherwise>
							</c:choose>
				    </c:forEach>
				    </select>
				</td>
				<td><input type="text" readonly="readonly"  value="${workItem.bv.payAccount.accountName}"  class="select78_24 " /></td>
				<td><input type="text" readonly="readonly"  value="${workItem.bv.payAccount.accountNo}"  class="select78_24 " /></td>
				<input type="hidden" readonly="readonly"  value="${workItem.bv.payAccount.accountBranch}"  class="select78_24 " />
                </tr>
                <tr>
                <td><input type="radio" name="accountPay" value="${workItem.bv.reyAccount.id}" disabled="disabled" /></td>
				<td><input type="radio" name="countAfter.id" value="${workItem.bv.reyAccount.id}" checked="checked"/></td>
				<td>
				   <select class="select78_24 " name="countAfter.accountBankId" readonly="readonly" onchange="changeReyBank(this.options[this.options.selectedIndex].value);">
				   <c:forEach items="${workItem.bv.dicts.tz_open_bank}" var="d">
							<c:choose>
								<c:when test="${d.value eq  workItem.bv.reyAccount.accountBankId}">
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
					<input type="text" name="countAfter.accountBranch" value="${workItem.bv.reyAccount.accountBranch}" required checkbranch="1" class="select78_24 " onblur="checkbranch()" />
					<!-- <a href="javascript:void(0);" class="" style="" onclick="openBranchCode(this);">&nbsp;<i class="icon-search"></i>&nbsp;</a>&nbsp;&nbsp; -->
				</td>
				<td>
					<select class="select78_24 " name="countAfter.accountAddrProvince" id="addrProvince" select_required="1" >
					<c:forEach items="${workItem.bv.provinceList}" var="item">
					   <c:choose>
					     <c:when test="${item.id eq workItem.bv.reyAccount.accountAddrProvince}">
					       <option selected="selected" value="${item.id}">${item.areaName}</option>
					     </c:when>
					     <c:otherwise><option value="${item.id}">${item.areaName}</option></c:otherwise>
					   </c:choose>
					</c:forEach>	
					</select>
					<select class="city-select select78_24 " name="countAfter.accountAddrCity" id="addrCity" select_required="1" >
					<c:forEach items="${fns:getCityByProvinceId(workItem.bv.reyAccount.accountAddrProvince)}" var="item">
					   <c:choose>
					     <c:when test="${item.id eq workItem.bv.reyAccount.accountAddrCity}">
					       <option selected="selected" value="${item.id}">${item.areaName}</option>
					     </c:when>
					     <c:otherwise><option value="${item.id}">${item.areaName}</option></c:otherwise>
					   </c:choose>
				    </c:forEach>
					</select>
					<select class="select78_24 " name="countAfter.accountAddrDistrict" id="addrDistrict" select_required="1"   >
					<c:forEach items="${fns:getDistrictByCityId(workItem.bv.reyAccount.accountAddrCity)}" var="item">
					   <c:choose>
					     <c:when test="${item.id eq workItem.bv.reyAccount.accountAddrDistrict}">
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
								<c:when test="${d.value eq  workItem.bv.reyAccount.accountCardOrBooklet}">
									<option selected="selected" value="${d.value}">${d.label}</option>
								</c:when>
								<c:otherwise>
									<option value="${d.value}">${d.label}</option>
								</c:otherwise>
							</c:choose>
				    </c:forEach>
				    </select>
				</td>
				<td><input type="text" name="countAfter.accountName" readonly="readonly" value="${workItem.bv.reyAccount.accountName}"  class="select78_24 "  /></td>
				<td><input type="text" name="countAfter.accountNo" value="${workItem.bv.reyAccount.accountNo}"  class="select78_24 " required bankAccount="-1" /></td>
					
           </tr>
        </table>
    </div>
    </c:if>
    <c:if test="${workItem.bv.lendloanapply.applyPay==4}">
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
				<input type="hidden" name="countAfter.id" value="${workItem.bv.reyAccount.id}" />
				<td>
				   <select class="select78_24 " name="countAfter.accountBankId" readonly="readonly" >
				   <c:forEach items="${workItem.bv.dicts.tz_open_bank}" var="d">
							<c:choose>
								<c:when test="${d.value eq  workItem.bv.reyAccount.accountBankId}">
									<option selected="selected" value="${d.value}">${d.label}</option>
								</c:when>
								<c:otherwise>
									<option value="${d.value}">${d.label}</option>
								</c:otherwise>
							</c:choose>
				  </c:forEach>
				  </select>
				</td>
				<td><input type="text" name="countAfter.accountBranch" value="${workItem.bv.reyAccount.accountBranch}" required checkbranch="1" class="select78_24 " onblur="checkbranch()" /></td>
				<td>
					<select class="select78_24 " name="countAfter.accountAddrProvince" id="provinceItem" select_required="1" >
					<c:forEach items="${workItem.bv.provinceList}" var="item">
					   <c:choose>
					     <c:when test="${item.areaCode eq workItem.bv.reyAccount.accountAddrProvince}">
					       <option selected="selected" value="${item.areaCode}">${item.areaName}</option>
					     </c:when>
					     <c:otherwise><option value="${item.areaCode}">${item.areaName}</option></c:otherwise>
					   </c:choose>
					</c:forEach>	
					</select>
					<select class="city-select select78_24 " name="countAfter.accountAddrCity" id="addrCityItem" select_required="1" >
					<c:forEach items="${fns:getFYCityList(workItem.bv.reyAccount.accountAddrProvince,'')}" var="item">
					   <c:choose>
					     <c:when test="${item.areaCode eq workItem.bv.reyAccount.accountAddrCity}">
					       <option selected="selected" value="${item.areaCode}">${item.areaName}</option>
					     </c:when>
					     <c:otherwise><option value="${item.areaCode}">${item.areaName}</option></c:otherwise>
					   </c:choose>
				    </c:forEach>
					</select>
				</td>
				<td>
				    <select class="select78_24 " name="countAfter.accountCardOrBooklet"  select_required="1">
				    <c:forEach items="${workItem.bv.dicts.com_card_type}" var="d">
							<c:choose>
								<c:when test="${d.value eq  workItem.bv.reyAccount.accountCardOrBooklet}">
									<option selected="selected" value="${d.value}">${d.label}</option>
								</c:when>
								<%-- <c:otherwise>
									<option value="${d.value}">${d.label}</option> 
								</c:otherwise> --%>
							</c:choose>
				    </c:forEach>
				    </select>
				</td>
				<td><input type="text" name="countAfter.accountName" readonly="readonly" value="${workItem.bv.reyAccount.accountName}"  class="select78_24 "  /></td>
				<td><input type="text" name="countAfter.accountNo" value="${workItem.bv.reyAccount.accountNo}"  class="select78_24 " required bankAccount="-1" /></td>	
           </tr>
        </table>
    </div>
    </c:if>
   <div class="title">
   <h2 class="f14 ml10">出借信息</h2>
   </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab"><span class="red">*</span>出借申请日期：</label><input type="text" name="lendloanapply.applyDate" value="${workItem.bv.lendloanapply.applyDate}" readonly="readonly" class="cf_input_text178"></td>
                <td><label class="lab"><span class="red">*</span>计划划扣日期：</label><input type="text" id="deductDate" name="lendloanapply.applyDeductDate" value="${workItem.bv.lendloanapply.applyDeductDate}" readonly="readonly" class="cf_input_text178"></td>
                <td><label class="lab"><span class="red">*</span>计划出借日期：</label><input type="text" name="lendloanapply.applyLendDate" value="${workItem.bv.lendloanapply.applyLendDate}" readonly="readonly" class="cf_input_text178"></td>
            </tr>
            <tr>
                <td>
                <label class="lab">
                <span class="red">*</span>付款方式：</label>
					<input type="text"  value="${fns:dictName(workItem.bv.dicts.tz_pay_type,workItem.bv.lendloanapply.applyPay,'-')}" disabled="disabled" class="cf_input_text178">
					<input type="hidden" name="lendloanapply.applyPay" value="${workItem.bv.lendloanapply.applyPay}">
                </td>
                <td><label class="lab"><span class="red">*</span>协议版本号：</label><input type="text" name="lendloanapply.applyAgreemenEdition" value="${fns:dictName(workItem.bv.dicts.tz_contract_vesion,workItem.bv.lendloanapply.applyAgreemenEdition,'-')}" readonly="readonly" class="cf_input_text178"></td>
                <td><label class="lab"><span class="red">*</span>计划出借金额：</label><input type="text" name="lendloanapply.applyLendMoney" value="${fns:getFormatNumber(workItem.bv.lendloanapply.applyLendMoney,'#0.00')}" readonly="readonly" class="cf_input_text178"></td>
            </tr>
            <tr>
                <td><label class="lab"><span class="red">*</span>出借模式：</label><input type="text" name="lendloanapply.productName" value="${workItem.bv.lendloanapply.productName}" readonly="readonly" class="cf_input_text178"></td>
                <td><label class="lab"><span class="red">*</span>合同编号：</label><input type="text" name="lendloanapply.applyContractNo" value="${workItem.bv.lendloanapply.applyContractNo}" readonly="readonly" class="cf_input_text178"></td>
				<td style=""><label class="lab"><span class="red">*</span>销售折扣率（%）：</label><input type="text" name="lendloanapply.applySalesDiscount" value="${fns:getFormatNumber(workItem.bv.lendloanapply.applySalesDiscount,'#0.0000')}" readonly="readonly" class="cf_input_text178"></td>
            </tr>
        </table>
    </div>
    <div class="title">
	        <div class="l"><h2 class="f14 ml10 lendChangeLaunchFileClass">附件</h2></div>
	    </div>
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td>
					<sys:attachment></sys:attachment>
				</td>
			</tr>
		</table>
</div> 
	<div class="tright pr30">
			<!-- 
			<input id="" class="btn cf_btn-primary" type="button" onclick="sqsPreview()" value="预览申请书"/> -->
			<input id="btnSubmit" class="btn cf_btn-primary" type="button"  value="提交"/>
			<input id="btnCancel" class="btn cf_btn-primary" type="button" value="返 回" onclick="self.location=document.referrer;"/>
	</div>
	<div id="divHideen">
	    <input type="hidden" name="lendloanapply.productCode" value="${workItem.bv.lendloanapply.productCode}"/>
	    <input type="hidden" name="lendloanapply.applyPayCode" value="${workItem.bv.lendloanapply.applyPay}"/>	    
	    <input type="hidden" name="lendloanapply.billDay" value="${workItem.bv.lendloanapply.billDay}"/>
		<input type="hidden" name="lendloanapply.id" value="${workItem.bv.lendloanapply.id}"/>
		<input type="hidden" name="lendloanapply.managerName" value="${workItem.bv.lendloanapply.managerName}"/>
		<input type="hidden" name="lendloanapply.managerId" value="${workItem.bv.lendloanapply.managerId}"/>
		<input type="hidden" name="lendloanapply.custMobilephone" value="${workItem.bv.lendloanapply.custMobilephone}"/>
        <input type="hidden" name="lendloanapply.teamManagerName" value="${workItem.bv.lendloanapply.teamManagerName}"/>
        <input type="hidden" name="lendloanapply.addrProvince" value="${workItem.bv.lendloanapply.addrProvince}"/>
        <input type="hidden" name="lendloanapply.addrCity" value="${workItem.bv.lendloanapply.addrCity}"/>
        <input type="hidden" name="lendloanapply.departmentName" value="${workItem.bv.lendloanapply.departmentName}"/>
        <input type="hidden" name="lendloanapply.teamName" value="${workItem.bv.lendloanapply.teamName}"/>
        <input type="hidden" name="lendloanapply.applyHostedState" value="${workItem.bv.lendloanapply.applyHostedState}"/>
        <input type="hidden" name="lendloanapply.custState" value="${workItem.bv.lendloanapply.custState}"/>
        <input type="hidden" name="lendloanapply.dictCustSource" value="${workItem.bv.lendloanapply.dictCustSource}"/>
        <input type="hidden" name="lendloanapply.applyState" value="${workItem.bv.lendloanapply.applyState}"/>
        <input type="hidden" name="lendloanapply.createTime" value="${workItem.bv.lendloanapply.createTime}"/>
        <input type="hidden" name="lendloanapply.custCertNum" value="${workItem.bv.lendloanapply.custCertNum}"/>
		<input type="hidden"  value="${workItem.flowCode}" name="flowCode"></input>
		<input type="hidden"  value="${workItem.bv.lendloanapply.applyCode}" name="lendchangeinfo.changeCode"></input>
        <input type="hidden"  value="${workItem.flowName}" name="flowName"></input>
        <input type="hidden"  value="${workItem.stepName}" name="stepName"></input>
        <input type="hidden"  value="${workItem.flowType}" name="flowType"></input>
        <input type="hidden"  name="lendloanapply.custCertType" value="${workItem.bv.lendloanapply.custCertType}"/>
        <input type="hidden"  id="applyPay" value="${workItem.bv.lendloanapply.applyPay}"/>
        <input type="hidden" id="branchCode" name="countAfter.bankCode" value="${workItem.bv.reyAccount.bankCode}"/>
        <input type="hidden"  value="lendChangeLaunch" id="pageType"></input>
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