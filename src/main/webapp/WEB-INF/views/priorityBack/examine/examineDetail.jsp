<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/priorityBack/examine/examineConfirm.js"></script>
<title>优先回款审批详情页</title>
</head>
<body>
<div>
    <div class="title">
        <div class="l"><h2 class="f14 ml10">客户信息</h2></div>
        <%-- <div class="r">
            <button class="btn btn_sc ml10" id="fullTraceBtn" onclick="showFullTrace('${view.lendCode}')">全程留痕</button>
        </div> --%>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
            	<!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178" disabled="disabled" value="***"></td>
                <%-- <td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178" disabled="disabled" value="${view.customerName }"></td> --%>
                <td><label class="lab">客户编号：</label><input type="text" class="cf_input_text178" disabled="disabled" value="${view.customerCode }"></td>
				<td><label class="lab">证件号码：</label><input type="text" class="cf_input_text178" disabled="disabled" value="***"></td>
				<%-- <td><label class="lab">证件号码：</label><input type="text" class="cf_input_text178" disabled="disabled" value="${view.customerCertNum }"></td> --%>
            </tr>
        </table>
    </div>
	<div class="title">
        <div class="l"><h2 class="f14 ml10">回收资金银行账户</h2></div>
    </div>
    <div class='box6'>
        <table class="table1">
            <tr>
                <td><label class="lab">开户行：</label>
                	<input type="text" class="cf_input_text178" disabled="disabled" value="${fns:dictName(dicts.tz_open_bank,view.accountBank,'') }"></td>
                <td><label class="lab">银行卡所在城市：</label>
                	<select class="select7801" disabled="disabled">
	                	<option>${view.accountAddrprovince}</option></select>
	                	<select disabled="disabled" class="city-select select7801">
	                	<option>${view.accountAddrcity}</option></select>
	                	<select disabled="disabled" class="select7801">
	                	<option>${view.accountAddrdistrict}</option></select>
                </td>
				<td><label class="lab">卡/折：</label> 
					<select class="select78 mr34" id='accountCardOrBooklet' name='accountCardOrBooklet' disabled="disabled">
						<option value="">请选择</option>
						<c:forEach items="${dicts.com_card_type}" var='item'>
							<option value="${item.value }" <c:if test="${view.accountCardOrBooklet==item.value }">selected</c:if>>
								${item.label }
							</option>
						</c:forEach>
					</select></td>
            </tr>
            <tr>
                <td><label class="lab">具体支行：</label><input type="text" class="cf_input_text178" disabled="disabled" value="${view.accountBranch }"></td>
                <td><label class="lab">开户姓名：</label><input type="text" class="cf_input_text178" disabled="disabled" value="${view.accountName }"></td>
				<td><label class="lab">回款账户：</label><input type="text" class="cf_input_text178" disabled="disabled" value="${view.accountNo }"></td>
            </tr>
        </table>
    </div>
	<div class="title">
        <div class="l"><h2 class="f14 ml10">出借信息</h2></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab">出借编号：</label><input type="text" id="lendCode" class="cf_input_text178" disabled="disabled" value="${view.lendCode }"></td>
				<td><label class="lab">计划划扣日期：</label><input type="text" class="cf_input_text178" disabled="disabled" value="${view.applyDeductDay }"></td>
				<td><label class="lab">计划出借日期：</label><input type="text" class="cf_input_text178" disabled="disabled" value="${view.applyLendDay }"></td>
            </tr>
            <tr>
			    <td><label class="lab">合同编号：</label><input type="text" class="cf_input_text178" disabled="disabled" value="${view.applyContractNo }"></td> 
				<td><label class="lab">付款方式：</label><select class='select78 mr34' disabled="disabled"><option>${fns:dictName(dicts.tz_pay_type,view.applyPay,'-') }</option></select></td>
			    <td><label class="lab">计划出借金额：</label>
			    	<input type="text" class="cf_input_text178" disabled="disabled" value="<fmt:formatNumber value="${view.applyLendMoney }" type="currency" pattern="￥#,##0.00"/>"></td>
            </tr>
            <tr>
				<td><label class="lab">资金出借及回收方式：</label><select class='select78 mr34' disabled="disabled"><option>${view.productName }</option></select></td>
				<td><label class="lab">协议版本号：</label><select class='select78 mr34' disabled="disabled"><option>${fns:dictName(dicts.tz_contract_vesion,view.applyAgreementEdition,'') }</option></select></td>
            </tr>
        </table>
    </div>
	<div class="title">
        <div class="l"><h2 class="f14 ml10">附件</h2></div>
    </div>
    <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td>
					<sys:attachment></sys:attachment>
				</td>
			</tr>
		</table>
	<div class="title">
        <div class="l"><h2 class="f14 ml10">审批信息</h2></div>
    </div>
    <div class="box6">
        <form action="${ctx}/myPriority/examine/examineConfirm"  method="post">
	        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
	            <tr>
	                <td colspan="2"><label class="lab"><span class="red">*</span>审批意见：</label>
	                	 <textarea rows="4" cols="40" name="checkExamine">${view.checkExamine}</textarea>
					</td>
				</tr>
				<tr>
					<td><label class="lab"><span class="red">*</span>审批结果：</label>
	                	<input name="checkExaminetype" type="radio" class="ml10 mr6" value="1" <c:if test="${view.checkExaminetype==1}">checked</c:if>>通过
	                	<input name="checkExaminetype" type="radio" class="ml10 mr6" value="2" <c:if test="${view.checkExaminetype==2}">checked</c:if>>不通过</td>
	            </tr>
	            <input type="hidden" name="priorityId" id="priorityId" value="${view.priorityId }"/>
        		<input type="hidden" name="lendCode" value="${view.lendCode }"/>
        		<input type="hidden" name="applyBy" value="${view.applyBy }"/>
        		<input type="hidden" name="applyTime" value="${view.applyTime }"/>
        		<input type="hidden" name="createBy" value="${view.createBy }"/>
        		<input type="hidden" name="createTime" value="${view.createTime }"/>
        		<input type="hidden" value="${view.id }" name="id" id="id">
	        </table>
        </form>
    </div>
    <div class="tright mt20 pr30 mb30">
    	<input type="button" class="btn cf_btn-primary" onclick="confirmSubmit();" value="保存">
    	<input type="button" class="btn cf_btn-primary" onclick="window.history.back()" value="返回">
    </div>
</div>
<div id="modal-sub" class="modal fade subwindow" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">全程留痕</h4>
			</div>
			<div class="modal-body">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn cf_btn-primary" id="subClose">关闭</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>