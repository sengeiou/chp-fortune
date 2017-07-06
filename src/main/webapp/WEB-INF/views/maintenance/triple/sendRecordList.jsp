<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class='box5'>
  <table id="contentTable" class="table table-striped table-bordered table-condensed data-list-table">
   	<thead>
   	   <tr>
   	   		<th>序号</th>
   	   		<th>客户编号</th>
   	   		<th>客户姓名</th>
   	   		<th>客户新手机号</th>
   	   		<th>客户旧手机号</th>
   	   		<th>证件类型</th>
   	   		<th>证件号码</th>
   	   		<th>系统类型</th>
   	   		<th>理财经理编号</th>
   	   		<th>理财经理</th>
   	   		<th>发送类型</th>
   	   		<th>发送时间</th>
   	   		<th>发送状态</th>
   	   </tr>
       </thead>
       <tbody>
	       <c:forEach items="${page.list }" var="item" varStatus="s">
	        <tr>
		       	<td>${item.uniqueNum}</td>
		       	<td>${item.customerCode }</td>
		       	<!-- 屏蔽客户姓名/手机号/身份证号 -->
		       	<td>***</td>
		       	<%-- <td>${item.loginName }</td> --%>
		        <td>***</td>
		        <%-- <td>${item.phone }</td> --%>
		        <td>***</td>
		        <%-- <td>${item.oldPhone }</td> --%>
				<td>${item.cardType }</td>
				<td>***</td>
				<%-- <td>${item.cardId }</td> --%>
				<td>${item.osType }</td>
				<td>${item.empCode }</td>
				<td>${fns:getUserById(item.empCode)}</td>
				<td>${item.sendType }</td>
				<td>${fns:getFormatDate(item.sendTime,"yyyy-MM-dd")}</td>
				<td>${item.sendStatus }</td>
			</tr>
	       </c:forEach>
	   </tbody>
</table>
<div class="pagination">${page}</div>
</div>