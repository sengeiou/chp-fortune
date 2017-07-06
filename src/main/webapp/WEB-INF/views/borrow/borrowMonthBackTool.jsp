<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<div class="box1 mb10">
		<form targer="_blank" id="subSearchForm" action="${ctx}/borrowMonth/borrowMonthBackTool" method="post" >
			<input type="hidden" name="creditMonId" id="creditMonId" value="${borrowMonthBackToolView.creditMonId }"/>
			<input type="hidden" name="borrowMonthVerTime" value="${borrowMonthBackToolView.borrowMonthVerTime }">
			<input type="hidden" name="creditValueId" value="${borrowMonthBackToolView.creditValueId }"/>
			<input type="hidden" name="borrowVerTime" value="${borrowMonthBackToolView.borrowVerTime }"/>
			<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
			<td>
				<label class='lab'>可用债权价值：</label>
				<input type="text" id="cv"  name ="loanAvailabeValue" value="${borrowMonthBackToolView.loanAvailabeValue }"" readonly="true" htmlEscape="false" 
					maxlength="50" class="cf_input_text178" />
			</td>
			<td>
				<label class='lab'>回池金额：</label>
				<input id="back" type='text' name="backToolMoney" htmlEscape="false" maxlength="50"
				 	class="cf_input_text178" number='1' greaterThan="0"   required/>
			</td>
			<td>
				<label class='lab'>剩余可用债权价值：</label>
				<input id = "surplus" type='text' name="surplusBorrowCreditValue" 
					htmlEscape="false" maxlength="50" class="cf_input_text178" readonly="true"/>
			</td>
			</tr>
			</table>
		</form>
        <div class="tright pr30">
		    <input type="button" value="回池" class="btn cf_btn-primary backTool" />
		    <input type="reset" value="取消"  class="btn cf_btn-primary qx"/>
  		</div>
  	</div>
