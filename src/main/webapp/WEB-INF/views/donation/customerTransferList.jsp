<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<div class="body_r">
    <div class="box1 mb10">
    <h4 class="f14 ml10">该手机号码已有客户归属，请联系相应理财经理可进行转赠！</h4>
    <table  class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>理财经理</th>
		<th>理财经理工号</th>
        <th>团队经理</th>
        <th>团队经理工号</th>
        <th>营业部</th>
    </tr>
    </thead>
     <c:forEach items="${customertlist}" var="item">
    <tr>
        <td>${item.managerName}</td>
		<td>${item.managerCode}</td>
        <td>${item.teamManagerName}</td>
        <td>${item.teamManagerCode}</td>
        <td>${item.departmentName}</td>
    </tr>   
    </c:forEach>
</table>         
</div>
</div>
