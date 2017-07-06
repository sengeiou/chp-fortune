<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript" src="${ctxWebInf}/js/customerPool/customerPoolAdvisoryList.js" charset="utf-8"></script>
<p class='tright pr30'>
<input id="btnAddAdvisory" class="btn btn_sc" type="button" value="新增"/></p>
<form id="advisoryForm" action="" method="post">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<input id="custCode" name="custCode" type="hidden" value="${custCode}"/>
	<input id="advisory" name="pageName" type="hidden" value="${pageName}"/>
</form>
<table id="contentTable"
	class="table table-striped table-bordered table-condensed data-list-table">
	<thead>
		<tr>
			<th>沟通日期</th>
			<th>沟通方式</th>
			<th>下次联系方式</th>
			<th>下次沟通时间</th>
			<th>意向模式</th>
			<th>意向金额</th>
			<th>出资日期</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${fn:length(page.list)>0 }">
			<c:forEach items="${page.list}" var="item">
				<tr>
					<td>${fns:getFormatDate(item.askDate ,'yyyy-MM-dd')}</td>
					<td>
						${fns:dictName(dicts.tz_communication_mode, item.askType, '-') }
					</td>
					<td>${item.askNextType }</td>
					<td>${fns:getFormatDate(item.askNextDate ,'yyyy-MM-dd')}</td>
					<td>
						${fns:getProductLabel(item.askProduct) }
					</td>
					<td>${fns:getFormatNumber(item.askInputMoney ,'￥#,##0.00')}</td>
					<td>${fns:getFormatDate(item.askInputDate ,'yyyy-MM-dd')}</td>
					<td>${item.askDes }</td>
					<td>
						<input id="advisoryDetail" class="cf_btn_edit advisoryDetail" type="button" value="详细" advisoryId="${item.id }">
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
<div class="pagination">${page}</div>
