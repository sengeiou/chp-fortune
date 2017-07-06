<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="pop_content">
	<form method="post" id="examineForm">
		<table class="f14 table4" cellpadding="0" cellspacing="0" border="0"
			width="100%">
			<tr> 
				<td width="31%"><label class="lab"><span class="red">*</span>审批结果：</label>
                	<input name="checkResult" type="radio" class="ml10 mr6" value="1">通过
                	<input name="checkResult" type="radio" class="ml10 mr6" value="2">不通过
                </td>
            </tr>
            <tr> 
				<td>
					<label class="lab">退回原因：</label> 
					<input type="text" class="cf_input_text178" name="backReason">
				</td>
            </tr>
		</table>
	</form>
</div>
