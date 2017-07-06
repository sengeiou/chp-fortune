<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxWebInf}/js/backInterest/backInterestCommon.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/backInterest/backInterestApproval.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
<title>待回息审批</title>
</head>
<body>
	<div class="body_r">
		<div class="title">
			<div class="l">
				<h2 class="f14 ml10">回息信息</h2>
			</div>
			<div class="r">
				<input type="button" class="btn btn_sc ml10" value="全程留痕" onclick="fullMark('${dtp.lendCode}')"/>
			</div>
		</div>
		<form id="applyForm"  action="" type="post">
			<div class="box6">
				<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
					<input type="hidden" class="input-medium" name="backiId" value="${dtp.backiId}" />
					<input type="hidden" id="ld" name="lendCode" value="${dtp.lendCode}"/>
					<input type="hidden" id="verTime" name="verTime" value="${dtp.verTime}"/>
					<tr>
						<!-- 屏蔽客户姓名/手机号/身份证号 -->
						<td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178" name="customerName" value="***" disabled="true"></td>
						<%-- <td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178" name="customerName" value="${dtp.customerName}" disabled="true"></td> --%>
						<td><label class="lab">客户编号：</label><input type="text" class="cf_input_text178" name="customerCode" value="${dtp.customerCode}" disabled="true"></td>
					</tr>
					<tr>
						<td><label class="lab">当期账单日：</label><input type="text" class="cf_input_text178" name="currentBillday" value="<fmt:formatDate value='${dtp.currentBillday}'  pattern='yyyy-MM-dd'/>" disabled="true"></td>
						<td><label class="lab">营业部：</label><input type="text" class="cf_input_text178" name="orgName" value="${dtp.orgName}" disabled="true"></td>
					</tr>
					<tr>
						<td><label class="lab">初始出借日期：</label><input type="text" class="cf_input_text178" name="applyLendDay" value="<fmt:formatDate value="${dtp.applyLendDay}" pattern="yyyy-MM-dd"/>" disabled="true"></td>
						<td><label class="lab">初始出借金额：</label><input type="text" class="cf_input_text178" name="applyLendMoney" value="<fmt:formatNumber value='${dtp.applyLendMoney}' type='currency' pattern='##0.00'/>" disabled="true"></td>
					</tr>
					<tr>
						<td><label class="lab">当期应回金额：</label><input type="text" class="cf_input_text178" name="backMoney" value="<fmt:formatNumber value='${dtp.backMoney}' type='currency' pattern='##0.00'/>" disabled="true"></td>
						<td><label class="lab">实际回息金额：</label><input type="text" class="cf_input_text178" name="backRealMoney" value="<fmt:formatNumber value='${dtp.backRealMoney}' type='currency' pattern='##0.00'/>" disabled="true">(￥<fmt:formatNumber value="${dtp.backRealMoney}" type="currency" pattern="##0.00"/>)</td>
					</tr>
				</table>
			</div>
			<div class="title">
				<div class="l">
					<h2 class="f14 ml10">审批信息</h2>
				</div>
			</div>
			<div class="box6">
				<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<td colspan="2"><label class="lab">
							<span class="red">*</span>审批结果：</label>
								<input id="rar" name="checkExaminetype" type="radio" value="1" class="ml10 mr6" onchange="javascript:showTextArea()" required/>通过
								<input id="rar" name="checkExaminetype" value="2" type="radio" class="ml10 mr6" onchange="javascript:showTextArea()"/>不通过
						</td>
						<td>
							<div class="backReason" style="display: none">
								<label class="lab"><span class="red">*</span>回息退回原因：</label> 
									<select class="select180" id="cemine" name="checkExamine" onchange="javascript:showTextArea();" select_required="1">
										<option value="">请选择</option>
										<c:forEach items="${fns:getDictList('tz_backsms_reason')}" var='item'>
											<option value="${item.value }">${item.label }</option>
										</c:forEach>
									</select><textarea id="tar" name="textAre" style="display: none;" textarea_required="1" required></textarea>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<div class="tright pr30 mt20">
			<input type="button" value="提交" class="btn cf_btn-primary" onclick="javascript:approval('backInterestApproval/goSubmit','backInterestApproval/loadBackInterestApprovalList');"/>
			<input type="button" value="返回" class="btn cf_btn-primary" onclick="javascript:history.back(-1)" />
		</div>
	</div>
	<div id="modal-sub" class="modal fade subwindow" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">${lendCode}的历史留痕</h4>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
					<input type="button" value="关闭"  class="btn cf_btn-primary" id="subClose"/>
				</div>
			</div>
		</div>		
	</div>
</body>
</html>
