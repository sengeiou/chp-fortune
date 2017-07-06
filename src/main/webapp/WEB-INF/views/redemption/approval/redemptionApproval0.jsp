<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/redemption/approval.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/redemption/attach.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/redemption/common.js"></script>
<script src="${ctxWebInf}/js/common/autocomplete.js" type="text/javascript"></script>
<title>提前赎回审批</title>
</head>
<body>

<div class="body_r">
    <form id="approvalForm" action="${ctx}/myApply/redemption/dispatchFlow" method="post" class="redeem_form">
    <div class="title">
        <div class="l"><h5 class="f14 ml10">客户信息</h5></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
            	<!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td><label class="lab">客户姓名：</label>
                	<input type="text" class="cf_input_text178" name="customerName" width="70" value="***" disabled="disabled"/> </td>
                <%-- <td><label class="lab">客户姓名：</label>
                	<input type="text" class="cf_input_text178" name="customerName" width="70" value="${workItem.bv.customerName}" disabled="disabled"/> </td>
                 --%><td><label class="lab">客户编号：</label>
                	<input type="text" class="cf_input_text178" name="customerCode" width="70" value="${workItem.bv.customerCode}" disabled="disabled"/></td>
                <td><label class="lab">证件号码：</label>
                	<input type="text" class="cf_input_text178" name="customerCertNum" width="70" value="***" disabled="disabled"/></td>
                <%-- <td><label class="lab">证件号码：</label>
                	<input type="text" class="cf_input_text178" name="customerCertNum" width="70" value="${workItem.bv.customerCertNum}" disabled="disabled"/></td>
                	 --%>
            </tr>
        </table>
    </div>
    <div class="title">
        <div class="l"><h5 class="f14 ml10">回收资金银行账户</h5></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab">开户行：</label><input type="text" class="cf_input_text178" name="accountBank" width="70" value="${fns:getDictLabel(workItem.bv.accountBank,'tz_open_bank','') }" disabled="disabled"/></td>
                <td><label class="lab">银行卡所在城市：</label>
                	<select class="select7801" disabled="disabled"><option>${workItem.bv.accountAddrprovince}</option></select>
                	<select class="city-select" disabled="disabled"><option>${workItem.bv.accountAddrcity}</option></select>
                	<select disabled="disabled"><option>${workItem.bv.accountAddrdistrict}</option></select>
                </td>
                <td><label class="lab">卡或折：</label>
                	<input type="text" class="cf_input_text178" name="accountCardOrBooklet" width="70" value="${fns:getDictLabel(workItem.bv.accountCardOrBooklet,'com_card_type','') }" disabled="disabled"/></td>
            </tr>
            <tr>
                <td><label class="lab">具体支行：</label>
                	<input type="text" class="cf_input_text178" name="accountBranch" width="70" value="${workItem.bv.accountBranch}" disabled="disabled"/></td>
                <td><label class="lab">开户姓名：</label>
                	<input type="text" class="cf_input_text178" name="accountName" width="70" value="${workItem.bv.accountName}" disabled="disabled"/></td>
                <td><label class="lab">回款账号：</label>
                	<input type="text" class="cf_input_text178" name="accountNo" width="70" value="${workItem.bv.accountNo}" disabled="disabled"/></td>
            </tr>
        </table>
    </div>
    <div class="title">
        <div class="l"><h5 class="f14 ml10">出借信息</h5></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab">出借编号：</label><input type="text" class="cf_input_text178" width="70" value="${workItem.bv.lendCode}" disabled="disabled"/></td>
                <td><label class="lab">计划划扣日期：</label>
                	<input type="text" name="applyDeductDay" class="cf_input_text178" value="<fmt:formatDate value="${workItem.bv.applyDeductDay}" pattern="yyyy-MM-dd"/>" readonly="readonly">
                </td>
				<td><label class="lab">计划出借日期：</label>
					<input type="text" id="applyLendDay" name="applyLendDay" class="cf_input_text178" value="<fmt:formatDate value="${workItem.bv.applyLendDay}" pattern="yyyy-MM-dd"/>" readonly="readonly">
				</td>
            </tr>
             <tr>
                <td><label class="lab">合同编号：</label><input type="text" class="cf_input_text178" name="applyContractNo" width="70" value="${workItem.bv.applyContractNo}" disabled="disabled"/></td>
                <td><label class="lab">付款方式：</label>
                	<input type="text" class="cf_input_text178" name="applyPay" width="70" value="${fns:getDictLabel(workItem.bv.applyPay,'tz_pay_type','-') }" disabled="disabled"/></td>
				<td><label class="lab">计划出借金额：</label>
					<input type="text" class="cf_input_text178" name="applyLendMoney" width="70" value="<fmt:formatNumber value="${workItem.bv.applyLendMoneyd}" type="currency" pattern="￥#,##0.00"/>" disabled="disabled"/></td>
                
            </tr>
            <tr>
                <td><label class="lab">资金出借及回收方式：</label><input type="text" class="cf_input_text178" name="productName" width="70" value="${workItem.bv.productName}" disabled="disabled"/></td>
                <td>
	                <label class="lab">协议版本号：</label>
	                <input type="text" class="cf_input_text178" width="70" value="${fns:getDictLabel(workItem.bv.applyAgreementEdition,'tz_contract_vesion','') }" disabled="disabled"/>
	                <input type="hidden" name="applyAgreementEdition" value="${workItem.bv.applyAgreementEdition}"/>
                </td>
				<td></td>
            </tr>
            <c:if test="${workItem.bv.redemptionType=='1' }">
	            <tr>
	                <td><label class="lab">回款金额：</label>
	                	<input type="text" class="cf_input_text178" id="redemptionBMoney" name="redemptionBMoney" numberWithoutComma="1" onblur="dateCheck(this)" 
	                		value="<fmt:formatNumber value="${workItem.bv.redemptionBMoney}" type="currency" pattern="##0.00"/>">元
	                </td>
	                <td><label class="lab">服务费：</label>
	                	<input type="text" class="cf_input_text178" id="redemptionDeMoney" name="redemptionDeMoney" numberWithoutComma="1" onblur="dateCheck(this)" 
	                		value="<fmt:formatNumber value="${workItem.bv.redemptionDeMoney}" type="currency" pattern="##0.00"/>">元
	                </td>
					<td><label class="lab">剩余金额：</label>
						<input type="text" class="cf_input_text178" id="residualAmount" name="residualAmount" numberWithoutComma="1" onblur="dateCheck(this)" 
	                		value="<fmt:formatNumber value="${workItem.bv.residualAmount}" type="currency" pattern="##0.00"/>">元
					</td>
	            </tr>
            </c:if>
            <tr>
                <td><label class="lab">赎回金额：</label>
                	<input type="text" class="cf_input_text178" value="<fmt:formatNumber value="${workItem.bv.redemptionMoney}" type="currency" pattern="##0.00"/>" disabled="disabled">元
                </td>
                <td><label class="lab">赎回类型：</label>
                	<input type="text" class="cf_input_text178" value="${fns:getDictLabel(workItem.bv.redemptionType,'tz_redeem_type','') }" disabled="disabled">
                </td>
				<td></td>
            </tr>
            <c:if test="${workItem.bv.is161Contract== '1'}">
            	<!-- 1.6.1合同版本显示内容 -->
	            <tr>
	                <td>
	                	<label class="lab"><span class='red'>*</span>是否客户回馈：</label>
	                	<select class="select78" id='feedback' name='feedback' disabled="disabled">
	                		<option value="">请选择</option>
							<c:forEach items="${fns:getDictList('tz_yes_no')}" var='item'>
								<option value="${item.value }" <c:if test="${workItem.bv.feedback==item.value}">selected</c:if>>
									${item.label }
								</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<label class="lab"><span class='red'>*</span>客户回馈金额：</label>
						<input type="text" name="feedbackMoney" class="cf_input_text178"  value="<fmt:formatNumber value="${workItem.bv.feedbackMoney}" type="currency" pattern="##0.00"/>" disabled="disabled"/>元
						<input type="hidden" id="feedbackMoney" value="${workItem.bv.feedbackMoney}"/>
					</td>
					<td>
						<label class="lab"><span class='red'>*</span>回馈事项备注：</label>
						<input type="text" name="feedbackRemark" class="cf_input_text178" value="${workItem.bv.feedbackRemark}" disabled="disabled">
					</td>
	            </tr>
            </c:if>
        </table>
    </div>
	<div class="title">
        <div class="l"><h5 class="f14 ml10">回款信息</h5></div>
    </div>
     <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%" class='table table-striped table-bordered table-condensed'>
            <tr>
                <td><label class="lab">预期年化收益率：</label><fmt:formatNumber value="${workItem.bv.expectProfit}" type="currency" pattern="#0.00"/>%</td>
            </tr>
            <tr>
                <td>
                <c:forEach items="${fns:getDictList(workItem.bv.typeStr)}" var="item" varStatus="vs">
                	<c:if test="${vs.index ==0}"><label class="lab"><span class='red'>*</span>请选择回款期限： </label></c:if>
                	<c:if test="${vs.index !=0}"><label class="lab">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></c:if>
	                <input type="radio" <c:if test="${workItem.bv.redemptionReceType==item.value}">checked="checked"</c:if> disabled="disabled">${item.label}<br/>
                </c:forEach>
                </td>
            </tr>
            <c:if test="${workItem.bv.is161Contract != '1'}">
	            <tr>
	                <td><label class="lab"><span class='red'>*</span>到期日期：</label>
	                <input type="text" name="linitDay" id="linitDay" class="Wdate cf_input_text178"  
	                value="<fmt:formatDate value="${workItem.bv.linitDay}" pattern="yyyy-MM-dd"/>" readonly="readonly"/></td>
	            </tr>
            </c:if>
            <tr>
                <td><label class="lab"><span class='red'>*</span>回款日期：</label>
                <input type="text" id="backMoneyDay" name="backMoneyDay" class="Wdate cf_input_text178" value="<fmt:formatDate value="${workItem.bv.backMoneyDay}" pattern="yyyy-MM-dd"/>"/></td>
            </tr>
			<tr>
				<td><label class="lab">是否特批： </label>
					<input type="radio" name="checkSp" onclick="javascript:showSp();" value="1">是
					<input type="radio" name="checkSp" checked="checked" onclick="javascript:hideSp();" value="2">否</td>
            </tr>
			<tr id="T1" style="display:none;">
				<td><label class="lab"><span class='red'>*</span>特批实际回款金额：</label>
				<input type="text" class="cf_input_text178" id="checkSpmoney" name="checkSpmoney" numberWithoutComma="1" onblur="dateCheck(this)" class="cf_input_text178"></td>
            </tr>
            <tr id="T2" style="display:none;">
				<td>
					<label class="lab"><span class='red'>*</span>特批批注：</label>
					<textarea id="checkSpremarks" name="checkSpremarks" rows="3" cols="28" maxlength='200'></textarea>
				</td>
            </tr>
        </table>
    </div>
    <div class="title">
        <div class="l"><h5 class="f14 ml10">审批信息</h5></div>
    </div>
     <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%" class='table table-striped table-bordered table-condensed'>
        	<c:if test="${not empty workItem.bv.checkExamine}">
	            <tr>
	                <td><label class="lab">上次退回原因：</label>${workItem.bv.checkExamine}</td>
	            </tr>
        	</c:if>
             <tr>
                <td>
                	<label class="lab">审批结果： </label>
                	<input type="radio" name='checkExaminetype' value="1">通过
					<input type="radio" name='checkExaminetype' value="2">退回
                </td>
            </tr>
            <tr>
                <td><label class="lab">退回原因：</label><input id="checkExamine" name="checkExamine" type="text" class="cf_input_text178" maxlength='200'></td>
            </tr>
           
        </table>
    </div>
     	<input type="hidden"  value="${workItem.flowCode}" name="flowCode"></input>
        <input type="hidden"  value="${workItem.flowName}" name="flowName"></input>
        <input type="hidden"  value="${workItem.flowId}" name="flowId"></input>
        <input type="hidden"  value="${workItem.stepName}" name="stepName"></input>
        <input type="hidden"  value="${workItem.wobNum}" name="wobNum"></input>
        <input type="hidden"  value="${workItem.token}" name="token"></input>
     	<input type="hidden" name="applyId" value="${workItem.bv.applyId}"/>
     	<input type="hidden" name="lendCode" value="${workItem.bv.lendCode}"/>
     	<input type="hidden" name="applyBillday" value="${workItem.bv.applyBillday}"/>
     	<input type="hidden" name="orgID" value="${workItem.bv.orgID}"/>
     	<input type="hidden" name="province" value="${workItem.bv.province}"/>
     	<input type="hidden" name="city" value="${workItem.bv.city}"/>
     	<input type="hidden" name="redemptionId" value="${workItem.bv.redemptionId}"/>
     	<input type="hidden" name="redemptionReceType" value="${workItem.bv.redemptionReceType }" />
        <input type="hidden" id="redemptionType" name="redemptionType" value="${workItem.bv.redemptionType}">
        <input type="hidden" id="is161Contract" name="is161Contract" value="${workItem.bv.is161Contract}">
     	<input type="hidden" id="applyExpireDay" value="<fmt:formatDate value="${entity.applyExpireDay}" pattern="yyyy-MM-dd"/>"/>

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
    </form>
    <div class="tright pr30 mt20">
    	<input type="button" value="提交" onclick="redeemApproval();" class="btn cf_btn-primary">
    	<input type="button" value="取消" onclick="window.history.back()" class="btn cf_btn-primary">
	</div>
</div>
</body>

</html>