<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:if test="${borrowMonthable.dictLoanType ne '1' and borrowMonthable.loanAvailabeValue ne '0.00000'}">
 	<div class="tright pr30 mt10">
 		<button class="btn btn_sc backTool mb10" >回池</button>
 	</div>
 </c:if>
<div>
	<table
		class="table table-striped table-bordered table-condensed data-list-table" width="100%">
		<thead>
			<tr>
				<th>序号</th>
				<th>出借人</th>
				<th>出借编号</th>
				<th>出借日期</th>
				<th>出借产品</th>
				<th>匹配金额</th>
				<th>匹配天数</th>
				<th>债权持有比例</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list }" var="item" varStatus="s">
				<tr>
					<td>${s.index+1}</td>
					<!-- 屏蔽客户姓名/手机号/身份证号 -->
					<td>***</td>
					<%-- <td>${item.customerName }</td> --%>
					<td>${item.applyCode }</td>
					<td>${fns:getFormatDate(item.applyLendDate,"yyyy-MM-dd")}</td>
					<td>${item.loanProduct }</td>
					<td>${fns:getFormatNumber(item.tradeMateMoney,'￥#,##0.00')}</td>
					<td>${item.loanMonths }</td>
					<td>${fns:getFormatNumber(item.borrowPercent,'0.00%')}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div class = "pagination">${page }</div>

