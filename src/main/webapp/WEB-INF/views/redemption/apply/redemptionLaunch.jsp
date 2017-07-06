<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/redemption/apply.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/redemption/common.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/redemption/attach.js"></script>
<title>提前赎回申请</title>
</head>
<body>

<div class="body_r">
    
    <div class="title">
        <div class="l"><h5 class="f14 ml10">客户信息</h5></div>
		<div class="tright pr30">
			<button class="btn btn_sc ml10" onclick="downloadApply('${workItem.bv.lendCode}')">下载申请表</button>
		</div>
    </div>
    <form id="applyForm" action="${ctx}/myApply/redemption/launchFlow" method="post">
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
            	<!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td><label class="lab">客户姓名：</label><input type="text" name="customerName" id="customerName" width="70" value="***" readonly="readonly" class="cf_input_text178"/> </td>
                <%-- <td><label class="lab">客户姓名：</label><input type="text" name="customerName" id="customerName" width="70" value="${workItem.bv.customerName}" readonly="readonly" class="cf_input_text178"/> </td> --%>
                <td><label class="lab">客户编号：</label><input type="text" name="customerCode" width="70" value="${workItem.bv.customerCode}" readonly="readonly" class="cf_input_text178"/></td>
                <td><label class="lab">证件号码：</label><input type="text" name="customerCertNum" width="70" value="***" readonly="readonly" class="cf_input_text178"/></td>
                <%-- <td><label class="lab">证件号码：</label><input type="text" name="customerCertNum" width="70" value="${workItem.bv.customerCertNum}" readonly="readonly" class="cf_input_text178"/></td> --%>
            </tr>
        </table>
    </div>
    <div class="title">
        <div class="l"><h5 class="f14 ml10">回收资金银行账户</h5></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab">开户行：</label><input type="text" name="accountBank" width="70" value="${fns:getDictLabel(workItem.bv.accountBank,'tz_open_bank','') }" readonly="readonly" class="cf_input_text178"/></td>
                <td><label class="lab">银行卡所在城市：</label>
                	<select class="select7801" disabled="disabled"><option>${workItem.bv.accountAddrprovince}</option></select>
                	<select class="city-select" disabled="disabled"><option>${workItem.bv.accountAddrcity}</option></select>
                	<select disabled="disabled"><option>${workItem.bv.accountAddrdistrict}</option></select>
                </td>
                <td><label class="lab">卡或折：</label><input type="text" name="accountCardOrBooklet" width="70" value="${fns:getDictLabel(workItem.bv.accountCardOrBooklet,'com_card_type','') }" readonly="readonly" class="cf_input_text178"/></td>
            </tr>
            <tr>
                <td><label class="lab">具体支行：</label><input type="text" name="accountBranch" width="70" value="${workItem.bv.accountBranch}" readonly="readonly" class="cf_input_text178"/></td>
                <td><label class="lab">开户姓名：</label><input type="text" name="accountName" width="70" value="${workItem.bv.accountName}" readonly="readonly" class="cf_input_text178"/></td>
                <td><label class="lab">回款账号：</label><input type="text" name="accountNo" width="70" value="${workItem.bv.accountNo}" readonly="readonly" class="cf_input_text178"/></td>
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
                <input type="text" class="cf_input_text178" name="lendCode" width="70" value="${workItem.bv.lendCode}" readonly="readonly"/></td>
                <td><label class="lab">计划划扣日期：</label>
                <input type="text" class="cf_input_text178" name="applyDeductDay" width="70" value="<fmt:formatDate value="${workItem.bv.applyDeductDay}" pattern="yyyy-MM-dd"/>" readonly="readonly"/></td>
				<td><label class="lab">计划出借日期：</label>
				<input type="text" class="cf_input_text178" id="applyLendDay" name="applyLendDay" width="70" value="<fmt:formatDate value="${workItem.bv.applyLendDay}" pattern="yyyy-MM-dd"/>" readonly="readonly"/></td>
            </tr>
             <tr>
                <td><label class="lab">合同编号：</label>
                <input type="text" class="cf_input_text178" name="applyContractNo" width="70" value="${workItem.bv.applyContractNo}" readonly="readonly"/></td>
                <td>
	                <label class="lab">付款方式：</label>
	                <input type="text" class="cf_input_text178" width="70" value="${fns:getDictLabel(workItem.bv.applyPay,'tz_pay_type','-') }" readonly="readonly"/>
	                <input type="hidden" class="cf_input_text178" name="applyPay" width="70" value="${workItem.bv.applyPay}"/>
                </td>
				<td><label class="lab">计划出借金额：</label>
					<input type="text" class="cf_input_text178" width="70" value="<fmt:formatNumber value="${workItem.bv.applyLendMoneyd}" type="currency" pattern="￥#,##0.00"/>" readonly="readonly"/>
					<input type="hidden" class="cf_input_text178" name="applyLendMoney" width="70" value="${workItem.bv.applyLendMoneyd}"/>
					<input type="hidden" class="cf_input_text178" id="applyLendMoneyd" name="applyLendMoneyd" width="70" value="${workItem.bv.applyLendMoneyd}"/>
				</td>
                
            </tr>
            <tr>
                <td><label class="lab">资金出借及回收方式：</label>
                	<input type="text" class="cf_input_text178" name="productName" width="70" value="${workItem.bv.productName}" readonly="readonly"/></td>
                <td>
	                <label class="lab">协议版本号：</label>
	                <input type="text" class="cf_input_text178" width="70" value="${fns:getDictLabel(workItem.bv.applyAgreementEdition,'tz_contract_vesion','') }" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td><label class="lab"><span class='red'>*</span>赎回申请日期：</label>
                	<input type="text" id="redemptionTime" name="redemptionTime" class="Wdate cf_input_text178" onfocus="WdatePicker()" value="<fmt:formatDate value='${workItem.bv.redemptionTime}' pattern='yyyy-MM-dd'/>"></td>
				<td><label class="lab"><span class='red'>*</span>赎回类型：</label>
					
					<input type="radio" name="redemptionType" value="2" checked="checked" <c:if test="${workItem.bv.redemptionType=='2'}">checked="checked"</c:if> onclick="redeemAll();">全部赎回 
					<c:if test="${workItem.bv.redeemPartFlag=='1'}">
						<input type="radio" name="redemptionType" value="1" <c:if test="${workItem.bv.redemptionType=='1'}">checked="checked"</c:if> onclick="redeemPart();">部分赎回 
						<input id="rtAmount" class="cf_input_text178" name="redemptionMoney" numberWithoutComma="1" onblur="dateCheck(this)" 
						type="text" value="<fmt:formatNumber value="${workItem.bv.redemptionMoney}" type="currency" pattern="##0.00"/>" style="display:none;">
					</c:if>
				</td>
            </tr>
            <c:if test="${workItem.bv.is161Contract== '1'}">
            	<!-- 1.6.1合同版本显示内容 -->
	            <tr>
	                <td>
	                	<label class="lab"><span class='red'>*</span>是否客户回馈：</label>
	                	<select class="select78" id='feedback' name='feedback'  onchange="noFeedback()">
	                		<option value="">请选择</option>
							<c:forEach items="${fns:getDictList('tz_yes_no')}" var='item'>
								<option value="${item.value }" <c:if test="${workItem.bv.feedback==item.value}">selected</c:if>>
									${item.label }
								</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<label class="lab"><span class='red'>*</span>客户回馈金额(必填)：</label>
						<input type="text" id="feedbackMoney" name="feedbackMoney" class="cf_input_text178" width="70" numberWithoutComma="1" onblur="dateCheck(this)"/>
					</td>
					<td>
						<label class="lab">回馈事项备注(非必填)：</label>
						<input type="text" name="feedbackRemark" class="cf_input_text178" maxlength='100'>
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
                <td><label class="lab">预期年化收益率：</label><fmt:formatNumber value="${workItem.bv.expectProfit}" type="currency" pattern="#0.00"/>%</td>
            </tr>
            <tr>
                <td>
                <c:forEach items="${fns:getDictList(workItem.bv.typeStr)}" var="item" varStatus="vs">
                	<c:if test="${vs.index ==0}"><label class="lab"><span class='red'>*</span>请选择回款期限： </label></c:if>
                	<c:if test="${vs.index !=0}"><label class="lab">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></c:if>
	                <input type="radio" name="redemptionReceType" value="${item.value }" <c:if test="${entity.redemptionReceType==item.value}">checked="checked"</c:if>>${item.label}<br/>
                </c:forEach>
                </td>
            </tr>
            <c:if test="${workItem.bv.is161Contract != '1'}">
            	<!-- 1.6.1合同版本不显示内容 -->
	            <tr>
	                <td><label class="lab"><span class='red'>*</span>到期日期：</label>
	                	<input type="text" id="linitDay" name="linitDay" class="Wdate cf_input_text178" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyLendDay\',{});}'})" value="<fmt:formatDate value='${workItem.bv.linitDay}' pattern='yyyy-MM-dd'/>"></td>
	            </tr>
            </c:if>
        </table>
    </div>
     	<input type="hidden" name="flowCode" value="${workItem.flowCode}"/>
     	<input type="hidden" name="flowName" value="${workItem.flowName}"/>
     	<input type="hidden" name="flowType" value="${workItem.flowType}"/>
     	<input type="hidden" name="stepName" value="${workItem.stepName}"/>
     	<input type="hidden" name="flowId" value="${workItem.flowId}"/>
     	<input type="hidden" name="wobNum" value="${workItem.wobNum}"/>
     	<input type="hidden" name="token" value="${workItem.token}"/>
     	<input type="hidden" name="applyBillday" value="${workItem.bv.applyBillday}"/>
     	<input type="hidden" name="orgID" value="${workItem.bv.orgID}"/>
     	<input type="hidden" name="productCode" value="${workItem.bv.productCode}"/>
     	<input type="hidden" name="province" value="${workItem.bv.province}"/>
     	<input type="hidden" name="city" value="${workItem.bv.city}"/>
     	<input type="hidden" name="applyId" value="${workItem.bv.applyId}"/>
     	<input type="hidden" id="applyAgreementEdition" name="applyAgreementEdition" value="${workItem.bv.applyAgreementEdition}"/>
     	<input type="hidden" id="is161Contract" name="is161Contract" value="${workItem.bv.is161Contract}"/>
     	<input type="hidden" id="dictApplyDeductType" name="dictApplyDeductType" value="${workItem.bv.dictApplyDeductType}"/>
     	<input type="hidden" name="backMoneyDayOfLend" value="<fmt:formatDate value='${workItem.bv.backMoneyDayOfLend}' pattern='yyyy-MM-dd'/>"/>  <!-- 回款日期，出借申请回款池中字段  -->
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
    <div class="tright mt20 pr30">
		<button id="applybtn" class="btn cf_btn-primary">提交</button>
		<button onclick="window.history.back()" class="btn cf_btn-primary">取消</button>
	</div>
</div>
</body>

</html>