<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<form id="investmentForm" action="" method="post">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<input id="custCode" name="custCode" type="hidden" value="${custCode}"/>
	<input id="advisory" name="pageName" type="hidden" value="${pageName}"/>
</form>
<p class='tright pr30'>
<input id="_back" class="btn btn_sc" type="button" value="返回" onclick="window.history.back(-1);"/>
</p>
<table id="contentTable" class="table table-striped table-bordered table-condensed data-list-table">
	<thead>
		<tr>
			<th>客户姓名</th>
			<th>出借编号</th>
			<th>出借产品</th>
			<th>计划出借日期</th>
			<th>计划出借金额</th>
			<th>付款方式</th>
			<th>到期日期</th>
			<th>出借状态</th>
			<th>完结状态</th>
			<th>理财经理</th>
			<th>团队经理</th>
			<th>开发团队</th>
			<th>营业部</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${fn:length(page.list)>0 }">
			<c:forEach items="${page.list}" var="item">
				<tr>
					<!-- 屏蔽客户姓名/手机号/身份证号 -->
					<td>***</td>
					<%-- <td>${item.customer.custName }</td> --%>
					<td>${item.applyCode}</td>
					<td>${fns:getProductLabel(item.productCode) }</td>
					<td>${fns:getFormatDate(item.lendDate ,'yyyy-MM-dd')}</td>
					<td>${fns:getFormatNumber(item.lendMoney ,'￥#,##0.00')}</td>
					<td>${fns:dictName(dicts.tz_pay_type,item.payType,'-') }</td>
					<td>${fns:getFormatDate(item.expireDate ,'yyyy-MM-dd')}</td>
					<td>${fns:dictName(dicts.tz_lend_state,item.lendStatus,'-') }</td>
					<td>${fns:dictName(dicts.tz_finish_state,item.applyEndStatus,'-') }</td>
					<td>${item.managerName}</td>
					<td>${item.teamManagerName }</td>
					<td>${item.teamName }</td>
					<td>${item.storesName }</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
<div class="pagination">${page}</div>