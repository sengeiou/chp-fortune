<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<table class="table table-striped table-bordered table-condensed dataTable data-list-table" width="100%">
	<thead>
	<tr>
		<th><input type="checkbox" class=" ml10 mr10 checkAll">全选</th>
		<th>序号</th>
		<th>名称</th>
		<th>账单类型</th>
		<th>本期推荐金额</th>
		<th>投资比例：下限比例</th>
		<th>说明</th>
		<th>状态</th>
		<th>操作</th>
	</tr>
	</thead>
	<c:forEach items="${list}" var="rule" varStatus="s">
		<tr>
			<td><input type="checkbox" value="${rule.id}" name='select[]'></td>
			<td>${s.index+1}</td>
			<td>${rule.ruleName}</td>
			<td>${fns:dictName(dicts.tz_bill_state,rule.billType,'-')}</td>
			<td>
				${not empty rule.lowerLimit ? rule.lowerLimit : 0}&nbsp;-&nbsp;
				${not empty rule.lowerLimit ? rule.upperLimit : 999999999}
			</td>
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
			<td id="userFlag" <c:if test="${fns:dictName(dicts.com_use_flag,rule.useFlag,'-')=='启用'}">style="font-weight: bold;color: black;"</c:if>>${fns:dictName(dicts.com_use_flag,rule.useFlag,'-')}</td>
			<td>
				<auth:hasPermission key="cf:macthingconfig:transact">
					<a href="#" class='cf_btn_edit' onclick="javascript:changeStatus('${rule.id }');">
					<c:choose>
						<c:when test="${rule.useFlag eq '0'}">启用</c:when>
						<c:otherwise>停用</c:otherwise>
					</c:choose>
		            </a>
				</auth:hasPermission>
			</td>
		</tr>
	</c:forEach>
</table>