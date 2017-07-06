<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
<!--
	//$("#askType").select2();
	//$("[name=askProduct]").select2();
	curDate = new Date();
	year = curDate.getFullYear();
	month = curDate.getMonth() + 1;
	day = curDate.getDate();
	if(month < 10){
		month = "0"+month;
	}
	if(day<10){
		day = "0"+day;
	}
	$( "#askDate" ).val(year+'-'+month+'-'+day);
//-->
</script>
<div class="box1 mb10">
	<form id="addAdvisoryForm" action="${ctx}/customer/investment/addAdvisory" method="post" class="form-horizontal">
		<input type="hidden" id="custCode" name="custCode" value="${advisory.custCode }">
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td>
					<label class="lab"><span class='red'>*</span>沟通日期：</label>
					<input type="text" id="askDate" name="askDate" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate cf_input_text178" required>
			    	
				</td>
				<td colspan="2">
					<label class="lab"><span class='red'></span>沟通时间：</label>
					<input type="text" id="askBeginDate" name="askBeginDate" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate cf_input_text178"> -
					<input type="text" id="askEndDate" name="askEndDate"  value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate cf_input_text178" from-checkDate="#askBeginDate">
				  
				</td>
			</tr>
			<tr>
				<td>
					<label class="lab"><span class='red'>*</span>沟通方式：</label>
					<select id="askType" name="askType" class='select78' select_required="1">
						<option value="">请选择</option>
						<c:forEach items="${dicts.tz_communication_mode }" var="item">
							<option value="${item.value }">
								<c:if test="${advisory.askType==item.value }">selected</c:if>${item.label }
							</option>
						</c:forEach>
					</select>
				  
				</td>
				<td>
					<label class="lab"><span class='red'></span>意向模式：</label>
					<sys:productselect name="askProduct" value="" cssClass='select78'/>
				   
				</td>
				<td>
					<label class="lab"><span class='red'></span>下次联系方式：</label>
					<input type="text" id="askNextType" name="askNextType" class='cf_input_text178' maxlength="50">
			    
				</td>
			</tr>
			<tr>
				<td>
					<label class="lab"><span class='red'></span>下次沟通日期：</label>
					<input type="text" id="askNextDate" name="askNextDate" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-{%d}'})" class="Wdate cf_input_text178" from-checkDate="#askDate">
			    
				</td>
				<td>
					<label class="lab"><span class='red'></span>意向金额：</label>
					<input type="text" id="askInputMoney" name="askInputMoney" value="" min="1" class='cf_input_text178' maxlength="10" digits="1">
			    	
				</td>
				<td>
					<label class="lab"><span class='red'></span>出资日期：</label>
					<input type="text" id="askInputDate" name="askInputDate" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-{%d}'})" class="Wdate cf_input_text178">
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<label class="lab"><span class='red'></span>备注：</label>
				    <textarea id="askDes" cols="10" rows="4" name="askDes" class='textarea_refuse' maxlength="500"></textarea>
				   
				</td>
			</tr>
		</table>
<!-- 		<div class="form-actions"> -->
<!-- 			<input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="保 存"/>&nbsp; -->
<!-- 			<input id="btnCancel" class="btn cf_btn-primary" type="button" value="关 闭" onclick="history.go(-1)"/> -->
<!-- 		</div> -->
	</form>
</div>