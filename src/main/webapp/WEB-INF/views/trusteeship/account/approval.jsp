<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<form action="${ctx}/trusteeship/account/changeStatus" method="post" >
		<input type="hidden" name="lendCode" value="${lendCode }"/>
		<input type="hidden" name="customerCode" value="${customerCode }"/>
		<input type="hidden" name="verTime" value="${verTime }"/>
	<div class="box1 mb10">
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td>
					<label class="lab">开户状态：</label>
					<input type="radio" name="chgStatus" value="1" required>开户成功&nbsp;&nbsp;
					<input type="radio" name="chgStatus" value="2">开户失败及退回
				</td>
			</tr>
			<tr>
				<td>
					<label class="lab">失败的原因：</label>
					<textarea class='textarea_refuse' name="trusteeshipRetMsg"></textarea>
				</td>
			</tr>
		</table>
	</div>
      	<div class="tright pr30">
        	<input type="button" id="subChgStatus" value="确定" class="btn cf_btn-primary"/>      	
      	</div>
	</form>

