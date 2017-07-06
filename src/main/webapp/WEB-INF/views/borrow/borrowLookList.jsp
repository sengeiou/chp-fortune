<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="box5">
	<div>
		<form action="${ctx}/borrow/borrowLookList" method="post" id="borrowLookForm">
			<input id="borrowLookPageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="borrowLookPageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
			<input id="creditValueId" type="hidden" name="creditValueId" value="${creditValueId}">
		</form>
	</div>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed data-list-table">
		<thead>
			<tr>
				<th>出借编号</th>
				<th>出借人姓名</th>
				<th>出借人编号</th>
				<th>初始出借日期</th>
				<th>当期出借日</th>
				<th>匹配金额</th>
				<th>剩余资金</th>
				<th>月还本金</th>
				<th>月还利息</th>
				<th>首期月利息</th>
				<th>初始期数</th>
				<th>剩余期数</th>
			</tr>
		</thead>
		<c:forEach items="${page.list }" var="item">
			<tr>
				<td>${item.lendCode }</td>
				<!-- 屏蔽客户姓名/手机号/身份证号 -->
				<td>***</td>
				<%-- <td>${item.customerName }</td> --%>
				<td>${item.customerTrue }</td>
				<td>${fns:getFormatDate(item.initLendDate,"yyyy-MM-dd")}</td>
				<td>${fns:getFormatDate(item.matchingInterestStart,"yyyy-MM-dd")}</td>
				<td>${fns:getFormatNumber(item.tradeMateMoney,'￥#,##0.00')}</td>
				<td>${fns:getFormatNumber(item.phasePrincipalSurplus,'￥#,##0.00')}</td>
				<td>${fns:getFormatNumber(item.monthCapitalPayactual,'￥#,##0.00')}</td>
				<td>${fns:getFormatNumber(item.monthInterest,'￥#,##0.00')}</td>
				<td>${fns:getFormatNumber(item.firstMonthInterest,'￥#,##0.00')}</td>
				<td>${item.initMonths }</td>
				<td>${item.surplusMonths }</td>
			</tr>
		</c:forEach>
	</table>
</div>
 <div class="pagination">${page}</div>