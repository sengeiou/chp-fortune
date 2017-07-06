<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript" src="${ctxWebInf}/js/customer/customerChangeTracesList.js" charset="utf-8""></script>
<form id="changeTracesForm" action="" method="post">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<input id="custCode" name="custCode" type="hidden" value="${custCode}"/>
	<input id="advisory" name="pageName" type="hidden" value="${pageName}"/>
</form>
<p class='tright pr30'>
<input id="_back" class="btn btn_sc" type="button" value="返回" onclick="window.history.back(-1);"/>
</p>
<table id="contentTable"
	class="table table-striped table-bordered table-condensed">
	<thead>
		<tr>
			<th>客户编号</th>
			<th>客户名称</th>
			<th>移动电话</th>
			<th>固定电话</th>
			<th>电子邮箱</th>
			<th>客户来源</th>
			<th>省份|城市|地区</th>
			<th>操作时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${fn:length(list)>0 }">
			<c:forEach items="${list}" var="item">
				<tr>
					<td>${item.changeAfterCustomer.custCode }</td>
					<!-- 屏蔽客户姓名/手机号/身份证号 -->
					<td>***</td>
					<%-- <td>${item.changeAfterCustomer.custName}</td> --%>
					<td>***</td>
					<%-- <td>${item.changeAfterCustomer.custMobilephone }</td> --%>
					<td>***</td>
					<%-- <td>${item.changeAfterCustomer.custPhone }</td> --%>
					<td>${item.changeAfterCustomer.custEmail }</td>
					<td>
						${fns:dictName(dicts.tz_customer_src,item.changeAfterCustomer.dictCustSource,'-') }
					</td>
					<td>${fns:getProvinceLabel(item.changeAfterCustomer.addrProvince) }|${fns:getCityLabel(item.changeAfterCustomer.addrCity) }|${fns:getDistrictLabel(item.changeAfterCustomer.addrDistrict) }</td>
					<td>${fns:getFormatDate(item.createTime ,'yyyy-MM-dd')}</td>
					<td>
						<input id="changeTracesDetail" class="changeTracesDetail cf_btn_edit" type="button" value="详细" changeTracesId="${item.id }">
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
<div class="pagination">${page}</div>