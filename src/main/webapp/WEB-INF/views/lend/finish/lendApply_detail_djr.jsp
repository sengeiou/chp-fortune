<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
	<script type="text/javascript" src="${ctxWebInf}/js/lend/finish/lendApply_detail_djr.js"></script>
	<title>出借明细</title>
</head>
<body>
<div class="">
    <div class="title pb5 pt10 pr30">
        <div class="l"><h2 class="f14 ml10">客户信息</h2></div>
         <div class="r">
            <button class="btn btn_sc ml10" id="fullTraceBtn" onclick="showFullTrace('${apply.applyCode}')">全程留痕</button>
        </div>
    </div>
    <div class="box1 mb10">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab"><span class="red">*</span>客户编号：</label>${customer.custCode}</td>
                <!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td><label class="lab"><span class="red">*</span>客户姓名：</label>***</td>
                <%-- <td><label class="lab"><span class="red">*</span>客户姓名：</label>${customer.custName}</td> --%>
                <td><label class="lab"><span class="red">*</span>客户手机号：</label>***</td> 
                <%-- <td><label class="lab"><span class="red">*</span>客户手机号：</label>${customer.custMobilephone}</td>  --%>
            </tr>
            <tr>
                <td><label class="lab"><span class="red">*</span>客户经理员工编号：</label>${customer.managerCode}</td>
                <td><label class="lab"><span class="red">*</span>客户经理姓名：</label>${customer.managerName}</td> 
            </tr>
        </table>	
    </div>
    
   
    <div class="title pb5 pt10 pr30">
        <div class="l"><h2 class="f14 ml10">出借信息</h2></div>
    </div>
    <div class="box1 mb10">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab"><span class="red">*</span>计划出借日期：</label>
                	<input type="text" class="cf_input_text178" value="<fmt:formatDate value="${apply.lendDate}" pattern="yyyy-MM-dd"/>" disabled="disabled"></td>
            	<td><label class="lab"><span class="red">*</span>到期日：</label>
                	<input type="text" class="cf_input_text178" value="<fmt:formatDate value="${apply.expireDate}" pattern="yyyy-MM-dd"/>" disabled="disabled"></td>
                 <td><label class="lab"><span class="red">*</span>出借模式：</label>
                	<input type="text" class="cf_input_text178" value="${fns:getProductLabel(apply.productCode) }" disabled="disabled"></td>
			 <tr>
			 	<td><label class="lab"><span class="red">*</span>协议版本号：</label>
					<input type="text" class="cf_input_text178" value="${fns:dictName(dicts.tz_contract_vesion,apply.protocoEdition,'') }" disabled="disabled"></td>
                <td><label class="lab" colspan="3"><span class="red">*</span>合同编号：
                	</label><input type="text" class="cf_input_text178" value="${apply.contractNumber}" disabled="disabled"></td>
               <td><label class="lab"><span class="red">*</span>付款方式：</label>
                	<input type="text" class="cf_input_text178" value="${fns:dictName(dicts.tz_pay_type,apply.applyPay,'') }" disabled="disabled">
                </td>
            </tr>
            <c:if test="${apply.productCode eq '87' }">
            	<tr style=''>
            		<td colspan="3">
            			<label class="lab"><span class="red">*</span>信和宝类型：</label>
            			<input type="radio" id='xinhebaoType' name="lendApply.xinhebaoType" <c:if test="${apply.xinhebaoType eq '1' }">checked=checked</c:if> disabled="disabled">满12个月转让一次收益
            			<input type="radio" id='xinhebaoType' name="lendApply.xinhebaoType" <c:if test="${apply.xinhebaoType eq '2' }">checked=checked</c:if> disabled="disabled">满12个月不转让收益，24个月后一起转让本息
	                </td>
	            </tr>
	        </c:if>
			<tr>
                <td><label class="lab"><span class="red">*</span>计划出借金额：</label>
                	<input type="text" class="cf_input_text178" value="<fmt:formatNumber value="${apply.lendMoney}" pattern="#,##0.00"></fmt:formatNumber>" disabled="disabled"></td>
				<td><label class="lab" colspan="3"><span class="red">*</span>出借编号：
                	</label><input type="text" class="cf_input_text178" value="${apply.applyCode}" id="applyCode" disabled="disabled"></td>
            </tr>
        </table>
	</div>
	<div class="title">
		<div class="l"><h5 class="f14 ml10">附件</h5></div>
	 </div>
	<table class="table1 lendApply_detail" cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td>
				<sys:attachment></sys:attachment>
			</td>
		</tr>
	</table>

	<c:if test="${apply.switchApproveStatus!=1 && apply.switchApproveStatus!=4}">
		<div class="title pb5 pt10 pr30">
			<div class="l"><h2 class="f14 ml10">审批信息</h2></div>
		</div>
		<div class="box1 mb10">
			<table class="table1" cellpadding="0" cellspacing="0" border="0"
				width="100%">
				<tr>
					<td>
						<label class="lab"><span class="red">*</span>审批结果：</label>
						<input id="dictCustSex" name="dictCustSex" type="radio" value="1" 
							<c:if test="${apply.switchApproveStatus==2 || apply.switchApproveStatus==5 || apply.switchApproveStatus==6}">checked=checked</c:if>
							disabled="disabled">通过 
						<input id="dictCustSex" name="dictCustSex" type="radio" value="2"
							<c:if test="${apply.switchApproveStatus==3}">checked=checked</c:if>
							disabled="disabled">不通过</td>
				</tr>
				<tr>
					<td colspan="3">
						<label class="lab">审批意见：</label>
						<textarea class="textarea_refuse" disabled="disabled">${apply.approveReason}</textarea>
					</td>
					<c:if test="${apply.switchApproveStatus==5}">
						<td colspan="3">
							<label class="lab">撤销转投原因：</label>
							<textarea class="textarea_refuse" disabled="disabled">${apply.cancelReason}</textarea>
						</td>
					</c:if>
				</tr>
			</table>
		</div>
	</c:if>
	<div class="tright pr30">
		<input id="btnCancel" class="btn cf_btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
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
