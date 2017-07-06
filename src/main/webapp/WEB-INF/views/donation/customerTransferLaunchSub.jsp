<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

    <table id="lendTable" class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th>理财经理编号</th>
            <th>理财经理姓名</th>
            <th>团队经理编号</th>
            <th>团队经理姓名</th>
            <th>所属营业部</th>
            <th>操作</th>
        </tr>
        </thead>
        <c:forEach items="${page.list}" var="item" varStatus="statu">
            <tr>
				<td>${item.managerCodeNew}</td>
				<td>${item.managerNameNew}</td>
				<td>${item.teamManagerCode}</td>
				<td>${item.teamManagerName}</td>
				<td>${item.departmentNameNew}</td>
		        <td><input id="btnSubmit" class="btn cf_btn-primary" type="submit"  value="办理"/></td>	
			</tr>
			<input type="hidden"  value="${item.managerId}" name="CTManagerinfo.managerId"></input>
			<input type="hidden"  value="${item.departmentId}" name="CTManagerinfo.departmentId"></input>
			<input type="hidden"  value="${item.managerCodeNew}" name="CTManagerinfo.managerCodeNew"></input>
			<input type="hidden"  value="${item.managerNameNew}" name="CTManagerinfo.managerNameNew"></input>
			<input type="hidden"  value="${item.departmentNameNew}" name="CTManagerinfo.departmentNameNew"></input>
        </c:forEach>
    </table>
    <div>
    <c:if test="${remind==0}">
    <h3 style="color: red;text-align: center;">没有该理财经理信息！</h3>	
    </c:if>
    </div>
   