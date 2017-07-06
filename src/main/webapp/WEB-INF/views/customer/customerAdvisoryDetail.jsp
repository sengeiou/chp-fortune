<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="box1 mb10">
	<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td>
				<label class="lab"><span class='red'>*</span>沟通日期：</label>
				<input type="text" id="askDate" name="askDate" value="<fmt:formatDate value="${advisory.askDate }" pattern="yyyy-MM-dd"/>" disabled="disabled" class="cf_input_text178">
			</td>
			<td colspan="2">
				<label class="lab"><span class='red'></span>沟通时间：</label>
				<input type="text" id="askBeginDate" name="askBeginDate" value="<fmt:formatDate value="${advisory.askBeginDate }" pattern="yyyy-MM-dd HH:mm:ss"/>" disabled="disabled" class="Wdate cf_input_text178"> -
				<input type="text" id="askEndDate" name="askEndDate"  value="<fmt:formatDate value="${advisory.askEndDate }" pattern="yyyy-MM-dd HH:mm:ss"/>" disabled="disabled" class="Wdate cf_input_text178">   
			</td>
		</tr>
		<tr>
			<td>
				<label class="lab"><span class='red'>*</span>沟通方式：</label>
				<input type="text" id="askType" name="askType" value="${fns:dictName(dicts.tz_communication_mode,advisory.askType,'') }" disabled="disabled" class="cf_input_text178">
			</td>
			<td>
				<label class="lab"><span class='red'></span>意向模式：</label>
				<input type="text" id="askProduct" name="askProduct" value="${fns:getProductLabel(advisory.askProduct) }" disabled="disabled" class="cf_input_text178">
			</td>
			<td>
				<label class="lab"><span class='red'></span>下次联系方式：</label>
				<input type="text" id="askNextType" name="askNextType" value="${advisory.askNextType }" disabled="disabled" class="cf_input_text178">
			</td>
		</tr>
		<tr>
			<td>
				<label class="lab"><span class='red'></span>下次沟通日期：</label>
				<input type="text" id="askNextDate" name="askNextDate" value="<fmt:formatDate value="${advisory.askNextDate }" pattern="yyyy-MM-dd"/>" disabled="disabled" class="cf_input_text178">
			</td>
			<td>
				<label class="lab"><span class='red'></span>意向金额：</label>
				<input type="text" id="askInputMoney" name="askInputMoney" value="<fmt:formatNumber pattern="#,##0.00" value="${advisory.askInputMoney }"></fmt:formatNumber>" disabled="disabled" class="cf_input_text178">
			</td>
			<td>
				<label class="lab"><span class='red'></span>出资日期：</label>
				<input type="text" id="askInputDate" name="askInputDate" value="<fmt:formatDate value="${advisory.askInputDate }" pattern="yyyy-MM-dd"/>" disabled="disabled" class="cf_input_text178">
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<label class="lab"><span class='red'></span>备注：</label>
				<textarea id="askDes" cols="10" rows="4" name="askDes" disabled="disabled" class='textarea_refuse'>${advisory.askDes }</textarea>
			</td>
		</tr>
	</table>
</div>
