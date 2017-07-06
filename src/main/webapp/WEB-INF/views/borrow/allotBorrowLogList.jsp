<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class='box5 subDiv'>
  <table id="contentTable" class="table table-striped table-bordered table-condensed data-list-table">
   	<thead>
       <tr>
           <th>序号</th>
           <th>可用债权价值</th>
           <th>分配金额</th>
           <th>分配时间</th>
           <th>操作人</th>
       </tr>
       </thead>
       <tbody>
       <c:forEach items="${page.list }" var="item" varStatus="s">
        <tr>
	       	<td>${s.index+1}</td>
	       	<td>${fns:getFormatNumber(item.beforMoney,'￥#,##0.00')}</td>
	       	<td>${fns:getFormatNumber(item.operateMoney,'￥#,##0.00')}</td>
	        <td>${fns:getFormatDate(item.operateTime,"yyyy-MM-dd HH:mm:ss")}</td>
			<td>${item.operator }</td>
		</tr>
       </c:forEach>
</tbody>
</table>
<div class="pagination">${page}</div>
</div>