<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>门店专员初审</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxWebInf}/js/lendChange/lendChangeFileInit.js"></script>
	<script type="text/javascript" src="${ctxWebInf}/js/lendChange/lendChangeHistory.js"></script>
</head>
<body>
<br/>
<div class="body_r">
    <div class="title">
    <h4 class="f14 ml10">客户基本信息</h4>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
            	<!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td><label class="lab"><span class="red">*</span>客户姓名：</label><input type="text" value="***" readonly="readonly" class="cf_input_text178"></td>
                <%-- <td><label class="lab"><span class="red">*</span>客户姓名：</label><input type="text" value="${workItem.bv.changeBegin.lendloanapply.custName}" readonly="readonly" class="cf_input_text178"></td> --%>
                <td><label class="lab"><span class="red">*</span>客户编号：</label><input type="text" value="${workItem.bv.changeBegin.lendloanapply.custCode}" readonly="readonly" class="cf_input_text178"></td>
                <td><label class="lab"><span class="red">*</span>出借编号：</label><input type="text" value="${workItem.bv.changeBegin.lendloanapply.applyCode}" readonly="readonly" class="cf_input_text178"></td>
            </tr>
           <c:if test="${workItem.bv.changeBegin.lendloanapply.trusteeshipNo ne null&&workItem.bv.changeBegin.lendloanapply.trusteeshipNo ne ''}">
				<tr>
		           	<td><label class="lab"><span class="red">*</span>金账户账号：</label><input  type="text" readonly="readonly"  value="${workItem.bv.changeBegin.lendloanapply.trusteeshipNo}" class="cf_input_text178"></td>
		        	<td colspan="2"><label class="lab"><span class="red">*</span>托管状态:</label><input  type="text" readonly="readonly" value="${fns:dictName(workItem.bv.dicts.tz_trust_state,workItem.bv.changeAfter.lendloanapply.applyHostedState,'-')}" class="cf_input_text178"></td>
		        </tr>
			</c:if>
        </table>	
    </div>
    <c:if test="${workItem.bv.changeBegin.lendloanapply.applyPay!=4}">
    <div class="title">
    <h4 class="f14 ml10">回款银行</h4>
    </div>
    <div class="box6"> 
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td style="width:50%;">
					<table id="chengeTable" style="width:100%;">
                      <tr>
							<td class='tcenter' style="width:25%;" colspan="2">
								<label class="lab"><span class='red'>变更前：</span></label>
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td class='tcenter' style="width:25%;" colspan="2">
								<label class="lab"><span class="red">变更后：</span><br /></label>
							</td>
						</tr>
                        <tr><td colspan="5" style='height:0px;'><p  style='width:100%;border-bottom:1px solid #e5c4a1;'></p></td></tr>
						<tr>
							<td style="width:25%;">
								<label class="lab"><span class="red">*</span>开户行：</label><input type="text" value="${fns:dictName(workItem.bv.dicts.tz_open_bank,workItem.bv.changeBegin.customerAccount.accountBankId,'-')}"  readonly="readonly" class="cf_input_text178">
							</td>
							<td style="width:25%;">
								<label class="lab"><span class="red">*</span>银行卡所在城市：</label><input type="text" value="${fns:getProvinceLabel(workItem.bv.changeBegin.customerAccount.accountAddrProvince)}&nbsp;${fns:getCityLabel(workItem.bv.changeBegin.customerAccount.accountAddrCity)}&nbsp;${fns:getDistrictLabel(workItem.bv.changeBegin.customerAccount.accountAddrDistrict)}" readonly="readonly" class="cf_input_text178">
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td style="width:25%;">
								<label class="lab"><span class="red">*</span>开户行：</label><input type="text" value="${fns:dictName(workItem.bv.dicts.tz_open_bank,workItem.bv.changeAfter.countAfter.accountBankId,'-')}" readonly="readonly" class="cf_input_text178">
							</td>
							<td style="width:25%;">
								<label class="lab"><span class="red">*</span>银行卡所在城市：</label><input type="text" value="${fns:getProvinceLabel(workItem.bv.changeAfter.countAfter.accountAddrProvince)}&nbsp;${fns:getCityLabel(workItem.bv.changeAfter.countAfter.accountAddrCity)}&nbsp;${fns:getDistrictLabel(workItem.bv.changeAfter.countAfter.accountAddrDistrict)}" readonly="readonly" class="cf_input_text178">
							</td>
						</tr>
						<tr>
							<td>
								<label class="lab"><span class="red">*</span>卡或折：</label><input type="text" value="${fns:dictName(workItem.bv.dicts.com_card_type,workItem.bv.changeBegin.customerAccount.accountCardOrBooklet,'-')}" readonly="readonly" class="cf_input_text178">
							</td>
							<td>
								<label class="lab"><span class="red">*</span>具体支行：</label><input type="text" value="${workItem.bv.changeBegin.customerAccount.accountBranch}" readonly="readonly" class="cf_input_text178">
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td>
								<label class="lab"><span class="red">*</span>卡或折：</label><input type="text" value="${fns:dictName(workItem.bv.dicts.com_card_type,workItem.bv.changeAfter.countAfter.accountCardOrBooklet,'-')}" readonly="readonly" class="cf_input_text178">
							</td>
							<td>
								<label class="lab"><span class="red">*</span>具体支行：</label><input type="text" value="${workItem.bv.changeAfter.countAfter.accountBranch}" readonly="readonly" class="cf_input_text178">
							</td>
						</tr>
						<tr>
							<td>
								<label class="lab"><span class="red">*</span>开户姓名：</label><input type="text" value="${workItem.bv.changeBegin.customerAccount.accountName}" readonly="readonly" class="cf_input_text178">
							</td>
							<td>
								<label class="lab"><span class="red">*</span>银行账号：</label><input type="text" value="${workItem.bv.changeBegin.customerAccount.accountNo}" readonly="readonly" class="cf_input_text178">
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td>
								<label class="lab"><span class="red">*</span>开户姓名：</label><input type="text" value="${workItem.bv.changeAfter.countAfter.accountName}" readonly="readonly" class="cf_input_text178">
							</td>
							<td>
								<label class="lab"><span class="red">*</span>银行账号：</label><input type="text" value="${workItem.bv.changeAfter.countAfter.accountNo}" readonly="readonly" class="cf_input_text178">
							</td>
						</tr>
					</table>
				</td>				      
            </tr>
        </table>
    </div>
    </c:if>
     <c:if test="${workItem.bv.changeBegin.lendloanapply.applyPay==4}">
    <div class="title">
    <h4 class="f14 ml10">金账户银行信息</h4>
    </div>
    <div class="box6"> 
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td style="width:50%;">
					<table id="chengeTable" style="width:100%;">
                      <tr>
							<td class='tcenter' style="width:25%;" colspan="2">
								<label class="lab"><span class='red'>变更前：</span></label>
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td class='tcenter' style="width:25%;" colspan="2">
								<label class="lab"><span class="red">变更后：</span><br /></label>
							</td>
						</tr>
                        <tr><td colspan="5" style='height:0px;'><p  style='width:100%;border-bottom:1px solid #e5c4a1;'></p></td></tr>
						<tr>
							<td style="width:25%;">
								<label class="lab"><span class="red">*</span>开户行：</label><input type="text" value="${fns:dictName(workItem.bv.dicts.tz_open_bank,workItem.bv.changeBegin.customerAccount.accountBankId,'-')}"  readonly="readonly" class="cf_input_text178">
							</td>
							<td style="width:25%;">
								<label class="lab"><span class="red">*</span>银行卡所在城市：</label><input type="text" value="${fns:getLabel(workItem.bv.changeBegin.customerAccount.accountAddrProvince)}&nbsp;${fns:getLabel(workItem.bv.changeBegin.customerAccount.accountAddrCity)}&nbsp;${fns:getDistrictLabel(workItem.bv.changeBegin.customerAccount.accountAddrDistrict)}" readonly="readonly" class="cf_input_text178">
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td style="width:25%;">
								<label class="lab"><span class="red">*</span>开户行：</label><input type="text" value="${fns:dictName(workItem.bv.dicts.tz_open_bank,workItem.bv.changeAfter.countAfter.accountBankId,'-')}" readonly="readonly" class="cf_input_text178">
							</td>
							<td style="width:25%;">
								<label class="lab"><span class="red">*</span>银行卡所在城市：</label><input type="text" value="${fns:getLabel(workItem.bv.changeAfter.countAfter.accountAddrProvince)}&nbsp;${fns:getLabel(workItem.bv.changeAfter.countAfter.accountAddrCity)}&nbsp;${fns:getDistrictLabel(workItem.bv.changeAfter.countAfter.accountAddrDistrict)}" readonly="readonly" class="cf_input_text178">
							</td>
						</tr>
						<tr>
							<td>
								<label class="lab"><span class="red">*</span>卡或折：</label><input type="text" value="${fns:dictName(workItem.bv.dicts.com_card_type,workItem.bv.changeBegin.customerAccount.accountCardOrBooklet,'-')}" readonly="readonly" class="cf_input_text178">
							</td>
							<td>
								<label class="lab"><span class="red">*</span>具体支行：</label><input type="text" value="${workItem.bv.changeBegin.customerAccount.accountBranch}" readonly="readonly" class="cf_input_text178">
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td>
								<label class="lab"><span class="red">*</span>卡或折：</label><input type="text" value="${fns:dictName(workItem.bv.dicts.com_card_type,workItem.bv.changeAfter.countAfter.accountCardOrBooklet,'-')}" readonly="readonly" class="cf_input_text178">
							</td>
							<td>
								<label class="lab"><span class="red">*</span>具体支行：</label><input type="text" value="${workItem.bv.changeAfter.countAfter.accountBranch}" readonly="readonly" class="cf_input_text178">
							</td>
						</tr>
						<tr>
							<td>
								<label class="lab"><span class="red">*</span>开户姓名：</label><input type="text" value="${workItem.bv.changeBegin.customerAccount.accountName}" readonly="readonly" class="cf_input_text178">
							</td>
							<td>
								<label class="lab"><span class="red">*</span>银行账号：</label><input type="text" value="${workItem.bv.changeBegin.customerAccount.accountNo}" readonly="readonly" class="cf_input_text178">
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td>
								<label class="lab"><span class="red">*</span>开户姓名：</label><input type="text" value="${workItem.bv.changeAfter.countAfter.accountName}" readonly="readonly" class="cf_input_text178">
							</td>
							<td>
								<label class="lab"><span class="red">*</span>银行账号：</label><input type="text" value="${workItem.bv.changeAfter.countAfter.accountNo}" readonly="readonly" class="cf_input_text178">
							</td>
						</tr>
					</table>
				</td>				      
            </tr>
        </table>
    </div>
    </c:if>
    <div class="title">
    <h4 class="f14 ml10">出借信息</h4>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab"><span class="red">*</span>出借申请日期：</label><input type="text" value="${workItem.bv.changeBegin.lendloanapply.applyDate}" readonly="readonly" class="cf_input_text178"></td>
                <td><label class="lab"><span class="red">*</span>计划划扣日期：</label><input type="text" value="${workItem.bv.changeBegin.lendloanapply.applyDeductDate}" readonly="readonly" class="cf_input_text178"></td>
                <td><label class="lab"><span class="red">*</span>计划出借日期：</label><input type="text" value="${workItem.bv.changeBegin.lendloanapply.applyLendDate}" readonly="readonly" class="cf_input_text178"></td>
            </tr>
            <tr>
                <td><label class="lab"><span class="red">*</span>付款方式：</label><input type="text" value="${fns:dictName(workItem.bv.dicts.tz_pay_type,workItem.bv.changeBegin.lendloanapply.applyPay,'-')}" readonly="readonly" class="cf_input_text178"></td>
                <td><label class="lab"><span class="red">*</span>协议版本号：</label><input type="text" value="${fns:dictName(workItem.bv.dicts.tz_contract_vesion,workItem.bv.changeBegin.lendloanapply.applyAgreemenEdition,'-')}" readonly="readonly" class="cf_input_text178"></td>
                <td><label class="lab"><span class="red">*</span>计划出借金额：</label><input type="text" value="${fns:getFormatNumber(workItem.bv.changeBegin.lendloanapply.applyLendMoney,'#0.00')}" readonly="readonly" class="cf_input_text178"></td>
            </tr>
            <tr>
                <td><label class="lab"><span class="red">*</span>出借模式：</label><input type="text" value="${workItem.bv.changeBegin.lendloanapply.productName}" readonly="readonly" class="cf_input_text178"></td>
                <td><label class="lab"><span class="red">*</span>合同编号：</label><input type="text" value="${workItem.bv.changeBegin.lendloanapply.applyContractNo}" readonly="readonly" class="cf_input_text178"></td>
                <td><label class="lab"><span class="red">*</span>账单日：</label><input type="text" value="${workItem.bv.changeBegin.lendloanapply.billDay}" readonly="readonly" class="cf_input_text178"></td>
                
				<td style="display:none;"><label class="lab"><span class="red">*</span>销售折扣率（%）：</label><input type="text" value="${workItem.bv.changeBegin.lendloanapply.applySalesDiscount}" readonly="readonly" class="cf_input_text178"></td>
            </tr>
        </table>
    </div>
    <div class="box6">
    	<div class="title">
	        <div class="l"><h4 class="f14 ml10 lendChangeApprove0FileClass">附件</h4></div>
	    </div>
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td>
					<sys:attachment></sys:attachment>
				</td>
			</tr>
		</table>
    </div>
    <form:form id="searchForm" commandName="workItem" action="${ctx}/lendChangeApprove/dispatch" method="post" >
		 <sys:message content="${message}"/>
		 <div class="title">	
          <h4 class="f14 ml10">审批信息</h4>
          </div>
    <div class="box6">
        <table class="table1">
       		<c:if test="${workItem.bv.changInfoRiew.auditResult==2}">
             <tr>
                <td><label class="lab">业务对接员退回意见：</label><textarea class="textarea_refuse" readonly="readonly">${workItem.bv.changInfoRiew.auditCheckExamine}</textarea></td>
            </tr>
            </c:if>
        	<c:if test="${workItem.bv.changeLog.auditResult==2}">
             <tr>
                <td><label class="lab">门店经理历史退回意见：</label><textarea class="textarea_refuse" readonly="readonly">${workItem.bv.changeLog.auditCheckExamine}</textarea></td>
            </tr>
            </c:if>
            <tr>
                <td><label class="lab"><span class="red">*</span>门店经理审批意见：</label><textarea name="auditCheckExamine" id="auditCheckExamine"   class="textarea_refuse"></textarea></td>
            </tr>
            <tr>
                <td><label class="lab"><span class="red">*</span>门店经理审批结果：</label><input type="radio" id="auditResult" required name="auditResult" value="1" />&nbsp;通过&nbsp;<input type="radio" id="auditResult" required name="auditResult" value="2" />&nbsp;不通过</td>
            </tr>
        </table>
    </div>            
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
        <input type="hidden"  value="${workItem.bv.changeInfo.applyId}" name="applyId"></input>
        <input type="hidden"  value="${workItem.bv.changeInfo.changeId}" name="changeId"></input>
        <input type="hidden"  value="${workItem.bv.changeBegin.lendloanapply.applyCode}" name="applyCode"></input>
		<input type="hidden"  value="${workItem.bv.changeBegin.lendloanapply.id}" name="id"></input>
		<input type="hidden"  value="${workItem.bv.changeInfo.dictChangeType}" name="changerTypeVal"/>
	</div>	
    </form:form> 
</body>
</html>