<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<form targer="_blank" id="allotForm" action="${ctx}/borrow/borrowAllot" method="post" >
	<input type="hidden" id="creditValueId" name="creditValueId" value="${borrowAllotView.creditValueId }">
	<input type="hidden" name="borrowVerTime" value="${borrowAllotView.borrowVerTime }">
	<input type="hidden" name="creditMonId" value="${borrowAllotView.creditMonId }">
	<input type="hidden" name="borrowMonthVerTime" value="${borrowAllotView.borrowMonthVerTime }">
	<table class="table1" cellpadding="0" cellspacing="0" border="0"
		width="100%">
		<tr>
			<td>
				<label class='lab'>可用债权价值：</label> 
				<input id="cv" type="text" name = "borrowCreditValue" value="${borrowAllotView.borrowCreditValue }" readonly="true" htmlEscape="false"
				maxlength="50" class="input-medium" />
			</td>
			<td>
				<label class='lab'>分配金额：</label> 
				<input id="allot" type="text" name = "allotMoney" htmlEscape="false" maxlength="50"
				class="input-medium" number="1" greaterThan="0" required/></td>
			<td>
				<label class='lab'>剩余价值：</label> 
				<input id="surplus" type="text" name = "surplusMoney" readonly="true" htmlEscape="false"
				maxlength="50" class="input-medium" />
			</td>
		</tr>
	</table>
	<div class="tright pr30 mb10">
	     <input type="button" value="分配" class="btn btn_sc allot" />
	     <input type="reset" value="取消"  class="btn btn_sc qx"/>
	</div>
</form>
