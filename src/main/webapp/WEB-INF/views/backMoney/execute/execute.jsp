<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/backMoney/backMoneyCommon.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/backMoney/execute.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
<title>执行回款</title>
</head>
<body>
<div class="body_r">
    <div class="title">
        <div class="l"><h2 class="f14 ml10">客户信息</h2></div>
        <div class="r">
            <button class="btn btn_sc ml10" id="fullTraceBtn" onclick="showFullTrace('${view.lendCode}')">全程留痕</button>
        </div>
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
        <div class="l"><h2 class="f14 ml10">收款银行信息</h2></div>
    </div>
    <div class='box6'>
        <table class="table1">
            <tr>
                <td><label class="lab">开户行：</label>
                	<input type="text" class="cf_input_text178" disabled="disabled" value="${fns:dictName(dicts.tz_open_bank,view.accountBank,'') }"></td>
                <td><label class="lab">银行卡所在城市：</label>
                	<select class="select7801" disabled="disabled">
	                	<option>${view.accountAddrprovince}</option></select>
	                <select disabled="disabled" class="city-select">
	                	<option>${view.accountAddrcity}</option></select>
	                <select disabled="disabled">
	                	<option>${view.accountAddrdistrict}</option>
                	</select>
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
                <td><label class="lab">出借编号：</label><input type="text" class="cf_input_text178" disabled="disabled" value="${view.lendCode }"></td>
                <td><label class="lab">出借申请日期：</label><input type="text" class="cf_input_text178" disabled="disabled" value="${view.applyDay }"></td>
				<td><label class="lab">计划划扣日期：</label><input type="text" class="cf_input_text178" disabled="disabled" value="${view.applyDeductDay }"></td>
				
            </tr>
            <tr>
			    <td><label class="lab">计划出借日期：</label><input type="text" class="cf_input_text178" disabled="disabled" value="${view.applyLendDay }"></td>
				<td><label class="lab">付款方式：</label><select class='select78 mr34' disabled="disabled"><option>${fns:dictName(dicts.tz_pay_type,view.applyPay,'-') }</option></select></td>
				<td><label class="lab">销售折扣率（%）：</label><input type="text" class="cf_input_text178" disabled="disabled" value="${view.applySalesDiscount }"></td>
                
            </tr>
            <tr>
			    <td><label class="lab">计划出借金额：</label>
			    	<input type="text" class="cf_input_text178" disabled="disabled" value="<fmt:formatNumber value="${view.applyLendMoney }" type="currency" pattern="￥#,##0.00"/>"></td>
				<td><label class="lab">出借产品：</label><select class='select78 mr34' disabled="disabled"><option>${view.productName }</option></select></td>
				<td><label class="lab">协议版本号：</label><select class='select78 mr34' disabled="disabled"><option>${fns:dictName(dicts.tz_contract_vesion,view.applyAgreementEdition,'') }</option></select></td>
            </tr>
			<tr>
			    
				<td><label class="lab">合同编号：</label><input type="text" class="cf_input_text178" disabled="disabled" value="${view.applyContractNo }"></td>
				<td><label class="lab">在职状态：</label><input type="text" class="cf_input_text178" disabled="disabled" value="${fns:dictName(dicts.tz_working_state,view.workingState,'') }"></td>
                <td><label class="lab">备注：</label><input type="textarea" class="textarea_min" disabled="disabled" value="${view.applyRemarks }"></td>
            </tr>
        </table>
    </div>
	<form action="${ctx}/myTodo/backMoney/execute" method="post">
	<div class="title">
        <div class="l"><h2 class="f14 ml10">回款信息</h2></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		    <tr>
                <td><label class="lab">应回金额：</label>
                	<input type="text" class="cf_input_text178" readonly="readonly" value="<fmt:formatNumber value="${view.backMoney }" type="currency" pattern="￥#,##0.00"/>"></td>
                <td><label class="lab">实际回款金额：</label>
                	<input type="text" class="cf_input_text178" readonly="readonly" value="<fmt:formatNumber value="${view.backActualbackMoney }" type="currency" pattern="￥#,##0.00"/>">
                	(<fmt:formatNumber value="${view.backActualbackMoney }" type="currency" pattern="￥#,##0.00"/>)</td>
            </tr>
            <tr>
                <td><label class="lab">到期日：</label><input type="text" class="cf_input_text178" disabled="disabled" value="${view.finalLinitDate }"></td>
                <td><label class="lab">备注：</label><input type="textarea" class="textarea_min" name="backMoneyRemarks" value="${view.backMoneyRemarks }" maxlength='500'></td>
            </tr>
        </table>
    </div>
    <div class="title">
        <div class="l"><h2 class="f14 ml10">中间人信息</h2></div>
    </div>
	<div class="box6">
        <table class="table table-striped table-bordered table-condensed" width="100%">
		    <thead>
		    <tr>
			    <th></th>
				<th>中间人</th>
				<th>证件号码</th>
				<th>开户行</th>
				<th>银行账号</th>
			</tr>
			</thead>
			<c:forEach items="${platformList}" var="item">
				<tr>
				    <td><input type="radio" class="ml10 mr6" name="platformId" value="${item.id }" ></td>
					<!-- 屏蔽客户姓名/手机号/身份证号 -->
					<td>${item.name }</td>
					<td>${item.certNo }</td>
					<td>${fns:dictName(dicts.tz_open_bank,item.bank,item.bank) }</td>
					<td>${item.bankcode }</td>
				</tr>
			</c:forEach>
        </table>
    </div>
    <div class="title">
        <div class="l"><h2 class="f14 ml10">审批信息</h2></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab"><span class="red">*</span>审批结果：</label>
                	<input name="checkExaminetype" type="radio" class="ml10 mr6" value="1" onclick="addReasonChoice();">通过
                	<input name="checkExaminetype" type="radio" class="ml10 mr6" value="2" onclick="addReasonChoice();">不通过</td>
                <td id="TcheckExamine" style="display: none;" colspan="2"><label class="lab">退回原因：</label> 
                	<select class="select180 mr34" id='checkExamine' name='checkExamine' onchange="addReason();">
						<option value="">请选择</option>
						<c:forEach items="${dicts.tz_back_reason}" var='item'>
							<option value="${item.value }">
								${item.label }
							</option>
						</c:forEach>
					</select>
					<input type="textarea" id="checkReason" class="textarea_min" name="checkReason" style="display: none;" maxlength='500'>
				</td>
            </tr>
        </table>
        <input type="hidden" name="backmId" value="${view.backmId }"/>
        <input type="hidden" name="lendCode" value="${view.lendCode }"/>
        <input type="hidden" name="verTime" value="${view.verTime }"/>
    </div>
    <div class="tright mt20 pr30">
    	<input type="button" class="btn cf_btn-primary" onclick="executeSubmit();" value="提交">
    	<input type="button" class="btn cf_btn-primary"  onclick="window.history.back()" value="返回">
    </div>
	</form>
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