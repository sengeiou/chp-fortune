<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/redemption/common.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/redemption/attach.js"></script>
<title>提前赎回详细</title>
</head>
<body>

<div class="body_r">
    <div class="title">
        <div class="l"><h5 class="f14 ml10">客户信息</h5></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
            	<!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178" name="customerName" width="70" value="***" disabled="disabled"/> </td>
                <%-- <td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178" name="customerName" width="70" value="${entity.customerName}" disabled="disabled"/> </td>
                 --%><td><label class="lab">客户编号：</label><input type="text" class="cf_input_text178" name="customerName" width="70" value="${entity.customerCode}" disabled="disabled"/></td>
                <td><label class="lab">证件号码：</label><input type="text" class="cf_input_text178" name="customerName" width="70" value="***" disabled="disabled"/></td>
                <%-- <td><label class="lab">证件号码：</label><input type="text" class="cf_input_text178" name="customerName" width="70" value="${entity.customerCertNum}" disabled="disabled"/></td>
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
                <td><label class="lab">开户行：</label>
                	<input type="text" class="cf_input_text178" name="accountBank" width="70" value="${fns:dictName(dicts.tz_open_bank,entity.accountBank,'') }" disabled="disabled"/></td>
                <td><label class="lab">银行卡所在城市：</label>
                	<select class="select7801" disabled="disabled"><option>${entity.accountAddrprovince}</option></select>
                	<select class="city-select" disabled="disabled"><option>${entity.accountAddrcity}</option></select>
                	<select disabled="disabled"><option>${entity.accountAddrdistrict}</option></select>
                </td>
                <td><label class="lab">卡或折：</label><input type="text" class="cf_input_text178" name="accountCardOrBooklet" width="70" value="${fns:dictName(dicts.com_card_type,entity.accountCardOrBooklet,'') }" disabled="disabled"/></td>
            </tr>
            <tr>
                <td><label class="lab">具体支行：</label>
                	<input type="text" class="cf_input_text178" name="accountBranch" width="70" value="${entity.accountBranch}" disabled="disabled"/>
                </td>
                <td><label class="lab">开户姓名：</label>
                	<input type="text" class="cf_input_text178" name="accountName" width="70" value="${entity.accountName}" disabled="disabled"/>
                </td>
                <td><label class="lab">回款账号：</label>
                	<input type="text" class="cf_input_text178" name="accountNo" width="70" value="${entity.accountNo}" disabled="disabled"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="title">
        <div class="l"><h5 class="f14 ml10">出借信息</h5></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab">出借编号：</label><input type="text" class="cf_input_text178" width="70" value="${entity.lendCode}" disabled="disabled"/</td>
                <td><label class="lab">计划划扣日期：</label>
                	<input type="text" class="cf_input_text178" value="<fmt:formatDate value="${entity.applyDeductDay}" pattern="yyyy-MM-dd"/>" disabled="disabled"/>
                </td>
				<td><label class="lab">计划出借日期：</label>
                	<input type="text" class="cf_input_text178" value="<fmt:formatDate value="${entity.applyLendDay}" pattern="yyyy-MM-dd"/>" disabled="disabled"/>
				</td>
                
            </tr>
             <tr>
                <td><label class="lab">合同编号：</label><input type="text" class="cf_input_text178" name="applyContractNo" width="70" value="${entity.applyContractNo}" disabled="disabled"/></td>
                <td><label class="lab">付款方式：</label><input type="text" class="cf_input_text178" name="applyPay" width="70" value="${fns:dictName(dicts.tz_pay_type,entity.applyPay,'-') }" disabled="disabled"/></td>
				<td><label class="lab">计划出借金额：</label><input type="text" class="cf_input_text178" name="applyLendMoney" width="70" value="<fmt:formatNumber value="${entity.applyLendMoneyd}" type="currency" pattern="￥#,##0.00"/>" disabled="disabled"/></td>
                
            </tr>
            <tr>
                <td><label class="lab">资金出借及回收方式：</label><input type="text" class="cf_input_text178" name="productName" width="70" value="${entity.productName}" disabled="disabled"/></td>
                <td><label class="lab">协议版本号：</label><input type="text" class="cf_input_text178" name="applyAgreementEdition" width="70" value="${fns:dictName(dicts.tz_contract_vesion,entity.applyAgreementEdition,'') }" disabled="disabled"/></td>
				
            </tr>
            <tr>
                <td><label class="lab">赎回金额：</label><input type="text" class="cf_input_text178" name="redemptionMoney" value="<fmt:formatNumber value="${entity.redemptionMoney}" type="currency" pattern="￥#,##0.00"/>" disabled="disabled"></td>
                <td><label class="lab">服务费：</label><input type="text" class="cf_input_text178"name="redemptionDeMoney" value="<fmt:formatNumber value="${entity.redemptionDeMoney}" type="currency" pattern="￥#,##0.00"/>" disabled="disabled"></td>
				<td><label class="lab">剩余金额：</label><input type="text" class="cf_input_text178" name="residualAmount" value="<fmt:formatNumber value="${entity.residualAmount}" type="currency" pattern="￥#,##0.00"/>" disabled="disabled"></td>
            </tr>
            <c:if test="${entity.is161Contract== '1'}">
            	<!-- 1.6.1合同版本显示内容 -->
	            <tr>
	                <td>
	                	<label class="lab"><span class='red'>*</span>是否客户回馈：</label>
	                	<select class="select78" id='feedback' name='feedback' disabled="disabled">
	                		<option value="">请选择</option>
							<c:forEach items="${fns:getDictList('tz_yes_no')}" var='item'>
								<option value="${item.value }" <c:if test="${entity.feedback==item.value}">selected</c:if>>
									${item.label }
								</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<label class="lab"><span class='red'>*</span>客户回馈金额：</label>
						<input type="text" name="feedbackMoney" class="cf_input_text178"  value="<fmt:formatNumber value="${entity.feedbackMoney}" type="currency" pattern="##0.00"/>" disabled="disabled"/>元
					</td>
					<td>
						<label class="lab"><span class='red'>*</span>回馈事项备注：</label>
						<input type="text" name="feedbackRemark" class="cf_input_text178" value="${entity.feedbackRemark}" disabled="disabled">
					</td>
	            </tr>
            </c:if>
        </table>
    </div>
	<div class="title">
        <div class="l"><h5 class="f14 ml10">回款信息</h5></div>
    </div>
     <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td>
                <c:forEach items="${fns:getDictList(entity.typeStr)}" var="item" varStatus="vs">
                	<c:if test="${vs.index ==0}"><label class="lab"><span class='red'>*</span>请选择回款期限： </label></c:if>
                	<c:if test="${vs.index !=0}"><label class="lab">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></c:if>
	                <input type="radio" name="redemptionReceType" value="${item.value }" <c:if test="${entity.redemptionReceType==item.value}">checked="checked"</c:if> disabled="disabled">${item.label}<br/>
                </c:forEach>
                </td>
            </tr>
            <tr>
                <td><label class="lab"><span class='red'>*</span>到期日期：</label>
                <input type="text" name="linitDay" class="Wdate cf_input_text178" onfocus="WdatePicker()" value="<fmt:formatDate value="${entity.linitDay}" pattern="yyyy-MM-dd"/>" disabled="disabled"/></td>
            </tr>
            <tr>
                <td><label class="lab"><span class='red'>*</span>回款日期：</label>
                <input type="text" name="backMoneyDay" class="Wdate cf_input_text178" onfocus="WdatePicker()" value="<fmt:formatDate value="${entity.backMoneyDay}" pattern="yyyy-MM-dd"/>" disabled="disabled"/></td>
            </tr>
			<tr>
				<td><label class="lab">是否特批： </label>
					
					<input type="radio" name="checkSp" <c:if test="${entity.checkSp==1}">checked="checked"</c:if> disabled="disabled">是
					<input type="radio" name="checkSp" <c:if test="${entity.checkSp==2}">checked="checked"</c:if> disabled="disabled">否</td>
            </tr>
            <c:if test="${entity.checkSp==1}">
				<tr>
					<td><label class="lab"><span class='red'>*</span>特批实际回款金额：</label>
					<input type="text" class="cf_input_text178" name="checkSpmoney" class="cf_input_text178" value="<fmt:formatNumber value="${entity.checkSpmoney}" pattern="#,##0,00"/>" disabled="disabled"></td>
	            </tr>
	            <tr>
					<td><label class="lab"><span class='red'>*</span>特批批注：</label>
					<textarea name="checkSpremarks" class='textarea_refuse' rows="3" cols="28" disabled="disabled">${entity.checkSpremarks}</textarea></td>
	            </tr>
            </c:if>
        </table>
    </div>
       <div class="title">
        <div class="l"><h5 class="f14 ml10">审批信息</h5></div>
    </div>
     <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
             <tr>
                <td><label class="lab">审批结果： </label>
                <input type="radio" name='checkExaminetype' <c:if test="${entity.checkExaminetype==1}">checked="checked"</c:if> disabled="disabled">通过　
                <input type="radio" name='checkExaminetype' <c:if test="${entity.checkExaminetype==2}">checked="checked"</c:if> disabled="disabled">退回</td>
            </tr>
            <tr>
                <td><label class="lab">退回原因：</label>
                <input name="checkExamine" type="text" class="cf_input_text178" value="${entity.checkExamine}" disabled="disabled"></td>
            </tr>
           
        </table>
    </div>
    <form action="" class="redeem_form">
     	<input type="hidden" name="lendCode" value="${entity.lendCode}"/>
     	<input type="hidden" name="redemptionId" value="${entity.redemptionId}"/>
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
    <div class="tright mt20 pr30"><button class="btn cf_btn-primary" onclick="window.history.back()">返回</button></div>

</body>
</html>