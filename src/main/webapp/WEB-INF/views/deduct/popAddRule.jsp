<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="pop_content">
	<form method="post" id="addForm">
		<table class="f14 table4" cellpadding="0" cellspacing="0" border="0"
			width="100%">
			<tr>
				<td>
					<label class='lab'>划扣平台：</label> 
					<select name="dictDeductPlatformId" class="cf_input_text178">
						<option value="">请选择</option>
						<c:forEach items="${fns:getDictList('tz_deduct_plat')}" var="item">
							<option value="${item.value}">${item.label}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<label class="lab">开户银行：</label> 
					<select name="dictBankId" class="cf_input_text178 ">
						<option value="">请选择</option>
						<c:forEach items="${fns:getDictList('tz_open_bank') }" var="item">
							<option value="${item.value}">
								${item.label}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<label class="lab">拆分基数：</label>
					<input type="text" name="singleLimitMoney"/>
				</td>
			</tr>
			<tr>
				<td>
					<label class="lab">每日限额：</label>
					<input type="text" name="dayLimitMoney"/>
				</td>
			</tr>
			<tr>
				<td>
					<label class="lab">是否发送第一笔：</label> 
					<c:forEach items="${fns:getDictList('tz_yes_no')}" var='item'>
						<input type="radio" name="isFirst" value="${item.value}">${item.label}
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>
					<label class="lab">限额类型：</label> 
					<c:forEach items="${fns:getDictList('tz_deductinter_type')}" var='item'>
						<input type="radio" name="dictDeductInterfaceType" value="${item.value}">${item.label}
					</c:forEach>
				</td>
			</tr>
		</table>
	</form>
</div>
