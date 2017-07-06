<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div>
	<form id="offLineForm"  method="post" >
		<input type="hidden" value="${lendCodes}" name="lendCodes"/>
		<input type="hidden" value="${id}" name="dayDeductId"/>
		<table id="offLineTable" class="table1">
			<tr>
				<td>
					<label class='lab'>划扣总笔数：</label> 
				</td>
				<td>
					<input class="cf_input_text178" type="text" name="deductCount" id="deductCount" value="${deductCount }">&nbsp;笔
				</td>
			</tr>
			<tr>
				<td>
					<label class='lab'>划扣总金额：</label> 
				</td>
				<td>
					<input class="cf_input_text178" type="text" name="deductCountMoney" id="deductCountMoney" value="${actualDeductMoneySum }">
					 元
				</td>
			</tr>
			<tr>
				<td>
					<label class='lab'><span class="red">*</span>预约日期：</label> 
				</td>
				<td>
					<input type="hidden"  id="nowDate" 
			    		value='<fmt:formatDate value="${nowDate}" pattern="yyyy-MM-dd"/>' 
			    		class="Wdate cf_input_text178" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyDeductDateStart\',{d:-1});}'})"> 
					<input type="text" required="required"  name="bespokeDate" id="applyDeductDateStart" 
                		value='<fmt:formatDate value="${deductPoolExt.applyDeductDateStart}" pattern="yyyy-MM-dd"/>' 
                		class="Wdate cf_input_text178" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'nowDate\',{});}'})">
				</td>				
			</tr>
			<tr>
				<td>
					<label class='lab'><span class="red">*</span>预约时间段：</label> 
				</td>
				<td>
					<input class="cf_input_text178" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="2" required="required" type="text" name="executionTimeSegment">&nbsp;<span class="red">预约时间只能是整点,例如格式12 </span>
				</td>				
			</tr>
<!-- 			<tr> -->
<!-- 				<td><input class="btn btn_sc ml10" type="button"  value="添加划扣平台" id="addhkpt"/><td> -->
<!-- 				<td><td> -->
<!-- 			</tr> -->
		</table>
	</form>
	 <sys:message content="${message}"/>
</div>

