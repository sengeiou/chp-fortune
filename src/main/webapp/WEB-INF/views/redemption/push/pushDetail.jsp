<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/redemption/common.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/redemption/push.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/redemption/attach.js"></script>
<title>特殊赎回推送</title>
</head>
<body>

<div class="body_r">
    <form id="pushForm" action="${ctx}/myApply/redemption/pushRedeem" method="post" class="redeem_form">
    <div class="title">
        <div class="l"><h5 class="f14 ml10">客户信息</h5></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
            	<!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td><label class="lab">客户姓名：</label>
                	<input type="text" class="cf_input_text178" width="70" value="***" disabled="disabled"/> </td>
                <%-- <td><label class="lab">客户姓名：</label>
                	<input type="text" class="cf_input_text178" width="70" value="${entity.customerName}" disabled="disabled"/> </td>
                 --%>
                <td><label class="lab">客户编号：</label>
                	<input type="text" class="cf_input_text178" width="70" value="${entity.customerCode}" disabled="disabled"/></td>
                <td><label class="lab">证件号码：</label>
                	<input type="text" class="cf_input_text178" width="70" value="***" disabled="disabled"/></td>
            	<%-- <td><label class="lab">证件号码：</label>
                	<input type="text" class="cf_input_text178" width="70" value="${entity.customerCertNum}" disabled="disabled"/></td>
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
                <td><label class="lab">卡或折：</label>
                	<input type="text" class="cf_input_text178" name="accountCardOrBooklet" width="70" value="${fns:dictName(dicts.com_card_type,entity.accountCardOrBooklet,'') }" disabled="disabled"/></td>
            </tr>
            <tr>
                <td><label class="lab">具体支行：</label>
                	<input type="text" class="cf_input_text178" name="accountBranch" width="70" value="${entity.accountBranch}" disabled="disabled"/></td>
                <td><label class="lab">开户姓名：</label>
                	<input type="text" class="cf_input_text178" name="accountName" width="70" value="${entity.accountName}" disabled="disabled"/></td>
                <td><label class="lab">回款账号：</label>
                	<input type="text" class="cf_input_text178" name="accountNo" width="70" value="${entity.accountNo}" disabled="disabled"/></td>
            </tr>
        </table>
    </div>
    <div class="title">
        <div class="l"><h5 class="f14 ml10">出借信息</h5></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab">出借编号：</label>
                	<input type="text" class="cf_input_text178" width="70" value="${entity.lendCode}" disabled="disabled"/></td>
                <td><label class="lab">计划划扣日期：</label>
                	<input type="text" name="applyDeductDay" class="cf_input_text178" value="<fmt:formatDate value="${entity.applyDeductDay}" pattern="yyyy-MM-dd"/>" disabled="disabled">
                </td>
				<td><label class="lab">计划出借日期：</label>
					<input type="text" name="applyLendDay" class="cf_input_text178" value="<fmt:formatDate value="${entity.applyLendDay}" pattern="yyyy-MM-dd"/>" disabled="disabled">
				</td>
            </tr>
             <tr>
                <td><label class="lab">合同编号：</label>
                	<input type="text" class="cf_input_text178" name="applyContractNo" width="70" value="${entity.applyContractNo}" disabled="disabled"/></td>
                <td><label class="lab">付款方式：</label>
                	<input type="text" class="cf_input_text178" name="applyPay" width="70" value="${fns:dictName(dicts.tz_pay_type,entity.applyPay,'-') }" disabled="disabled"/></td>
				<td><label class="lab">计划出借金额：</label>
					<input type="text" class="cf_input_text178" name="applyLendMoney" width="70" value="<fmt:formatNumber value="${entity.applyLendMoneyd}" type="currency" pattern="￥#,##0.00"/>" disabled="disabled"/></td>
            </tr>
            <tr>
                <td><label class="lab">资金出借及回收方式：</label>
                	<input type="text" class="cf_input_text178" name="productName" width="70" value="${entity.productName}" disabled="disabled"/></td>
                <td>
	                <label class="lab">协议版本号：</label>
	                <input type="text" class="cf_input_text178" width="70" value="${fns:dictName(dicts.tz_contract_vesion,entity.applyAgreementEdition,'') }" disabled="disabled"/>
	                <input type="hidden" name="applyAgreementEdition" value="${entity.applyAgreementEdition}"/>
                </td>
				<td></td>
            </tr>
            <c:if test="${entity.redemptionType=='1'}">
            	<!-- 部分赎回时显示 -->
	            <tr>
	                <td><label class="lab">回款金额：</label>
	                	<input type="text" class="cf_input_text178" value="<fmt:formatNumber value="${entity.redemptionBMoney}" type="currency" pattern="##0.00"/>" disabled="disabled">元
	                </td>
	                <td><label class="lab">服务费：</label>
	                	<input type="text" class="cf_input_text178"name="redemptionDeMoney" numberWithoutComma="1" onblur="dateCheck(this)" 
	                		value="<fmt:formatNumber value="${entity.redemptionDeMoney}" type="currency" pattern="##0.00"/>" disabled="disabled">元
	                </td>
					<td><label class="lab">剩余金额：</label>
						<input type="text" class="cf_input_text178" numberWithoutComma="1" onblur="dateCheck(this)" 
							value="<fmt:formatNumber value="${entity.residualAmount}" type="currency" pattern="##0.00"/>" disabled="disabled">元
					</td>
	            </tr>
            </c:if>
            <tr>
                <td><label class="lab">赎回金额：</label>
                	<input type="text" class="cf_input_text178" value="<fmt:formatNumber value="${entity.redemptionMoney}" type="currency" pattern="##0.00"/>" disabled="disabled">元
                </td>
                <td><label class="lab">赎回类型：</label>
                	<input type="text" class="cf_input_text178" value="${fns:dictName(dicts.tz_redeem_type,entity.redemptionType,'') }" disabled="disabled">
                </td>
				<td></td>
            </tr>
        </table>
    </div>
	<div class="title">
        <div class="l"><h5 class="f14 ml10">回款信息</h5></div>
    </div>
     <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%" class='table table-striped table-bordered table-condensed'>
            <tr>
				<td><label class="lab">是否客户回馈： </label>
					<input type="radio" name="feedback" value="1" <c:if test="${entity.feedback=='1'}">checked</c:if> disabled="disabled">是
					<input type="radio" name="feedback" value="2" <c:if test="${entity.feedback=='2'}">checked</c:if> disabled="disabled">否</td>
				<td><label class="lab"><span class='red'>*</span>客户回馈金额：</label>
				<input type="text" class="cf_input_text178" value="<fmt:formatNumber value="${entity.feedbackMoney}" type="currency" pattern="#,##0.00"/>" disabled="disabled"></td>
				<td>
					<label class="lab"><span class='red'>*</span>回馈事项备注：</label>
					<textarea rows="3" cols="28" disabled="disabled">${entity.feedbackRemark}</textarea>
				</td>
            </tr>
            <tr>
                <td><label class="lab"><span class='red'>*</span>回款日期：</label>
                <input type="text" id="backMoneyDay" name="backMoneyDay" class="Wdate cf_input_text178" onfocus="WdatePicker()" 
                	value="<fmt:formatDate value="${entity.backMoneyDay}" pattern="yyyy-MM-dd"/>"/></td>
                <td>
                	<c:if test="${entity.redemptionType=='1'}">
                		<!-- 部分赎回时显示 -->
	                	<label class="lab"><span class='red'>*</span>继续出借金额：</label>
		                <input type="text" class="cf_input_text178" id="residualAmount" name="residualAmount" numberWithoutComma="1" onblur="dateCheck(this)" 
							value="<fmt:formatNumber value="${entity.residualAmount}" type="currency" pattern="##0.00"/>">元
                	</c:if>
                </td>
            </tr>
			<%-- <tr>
				<td><label class="lab">是否特批： </label>
					<input type="radio" name="checkSp" value="1" <c:if test="${entity.checkSp=='1'}">checked</c:if> disabled="disabled">是
					<input type="radio" name="checkSp" value="2" <c:if test="${entity.checkSp=='2'}">checked</c:if> disabled="disabled">否</td>
            </tr>
			<tr>
				<td><label class="lab"><span class='red'>*</span>特批实际回款金额：</label>
				<input type="text" class="cf_input_text178" disabled="disabled"></td>
            </tr>
            <tr>
				<td>
					<label class="lab"><span class='red'>*</span>特批批注：</label>
					<textarea id="checkSpremarks" name="checkSpremarks" rows="3" cols="28" disabled="disabled">${entity.checkSpremarks}</textarea>
				</td>
            </tr> 
			--%>
        </table>
    </div>
     	<input type="hidden" id="linitDay" value="<fmt:formatDate value="${entity.linitDay}" pattern="yyyy-MM-dd"/>"/>
     	<input type="hidden" id="applyExpireDay" value="<fmt:formatDate value="${entity.applyExpireDay}" pattern="yyyy-MM-dd"/>"/>
     	<input type="hidden" name="lendCode" value="${entity.lendCode}"/>
     	<input type="hidden" name="applyBillday" value="${entity.applyBillday}"/>
     	<input type="hidden" name="redemptionId" value="${entity.redemptionId}"/>
     	<input type="hidden" name="redemptionType" id="redemptionType" value="${entity.redemptionType}"/>

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
		<button onclick="pushRedeem();" class="btn cf_btn-primary">特殊赎回推送</button>
		<button onclick="window.history.back()" class="btn cf_btn-primary">取消</button>
	</div>
</div>
</body>

</html>