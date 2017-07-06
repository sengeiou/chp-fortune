<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<table cellspacing="0" cellpadding="0" border="0"  class="table table-striped table-bordered table-condensed dataTable data-list-table" width="100%">
	<thead>
	<tr>
		<th><input type="checkbox" class=" ml10 mr10 checkAll">全选</th>
		<th>序号</th>
		<th>名称</th>
		<th>账单类型</th>
		<th>投资比例：下限比例</th>
		<th>说明</th>
		<th>默认规则</th>
		<th>状态</th>
		<th>操作</th>
	</tr>
	</thead>
	<c:forEach items="${list}" var="rule" varStatus="s">
		<tr>
			<td><input type="checkbox" value="${rule.id}" name='select[]'></td>
			<td>${s.index+1}</td>
			<td>${rule.ruleName}</td>
			<td>${fns:getDictLabel(rule.billType,'tz_bill_state','-')}</td>
			<td>
				<c:forEach items="${rule.proporti}" var="proporti" varStatus="s1">
					${proporti.proportion} : 
					<c:forEach items="${proporti.lower}" var="lower" varStatus="s2">
						<c:if test="${s2.index != 0}">/</c:if>${lower.lower}
					</c:forEach>
					<br/>
				</c:forEach>
			</td>
			<td>${rule.remark}</td>
			<td>${fns:getDictLabel(rule.defaultFlag, 'tz_yes_no', '-')}</td>
			<td>${fns:getDictLabel(rule.useFlag, 'com_use_flag', '-')}</td>
			<td>
			
				<button class='btn_default'>设为默认</button>
			
			</td>
		</tr>
	</c:forEach>
</table>