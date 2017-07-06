<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class='box5'>
  <table id="contentTable" class="table table-striped table-bordered table-condensed data-list-table">
   	<thead>
   	   <tr>
   	   		<th rowspan = "2">序号</th>
   	   		<th rowspan = "2">客户编号</th>
   	   		<th rowspan = "2">客户姓名</th>
   	   		<th colspan="5">交割前</th>
   	   		<th colspan="5">交割后</th>
   	   		<th rowspan = "2">交割类型</th>
   	   		<th rowspan = "2">系统类型</th>
   	   </tr>
   	   <tr>
   	   		<th>理财经理工号</th>
   	   		<th>理财经理姓名</th>
   	   		<th>团队经理工号</th>
   	   		<th>团队经理姓名</th>
   	   		<th>营业部</th>
   	   		<th>理财经理工号</th>
   	   		<th>理财经理姓名</th>
   	   		<th>团队经理工号</th>
   	   		<th>团队经理姓名</th>
   	   		<th>营业部</th>
   	   </tr>
       </thead>
       <tbody>
	       <c:forEach items="${page.list }" var="item" varStatus="s">
	        <tr>
		       	<td>${s.index+1}</td>
		       	<td>${item.customerCode }</td>
		       	<td>${item.loginName }</td>
		        <td>${item.empCode }</td>
				<td>${item.empName }</td>
				<td>${item.teamManagerCode }</td>
				<td>${item.teamManagerName }</td>
				<td>${item.orgName }</td>
				<td>${item.newEmpCode }</td>
				<td>${item.newEmpName }</td>
				<td>${item.newTeamManagerCode }</td>
				<td>${item.newTeamManagerName }</td>
				<td>${item.newOrgName }</td>
				<td>${item.deliveryType }</td>
				<td>${item.osType }</td>
			</tr>
	       </c:forEach>
	   </tbody>
</table>
<div class="pagination">${page}</div>
</div>