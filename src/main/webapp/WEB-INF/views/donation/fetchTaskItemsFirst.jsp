<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>转赠初审列表</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
	<script src="${ctxWebInf }/js/customertransfer/customerTransferapproval.js" type="text/javascript"></script>
</head>
<body>
<div class="body_r">
    <div class="box1 mb10">
    <form:form id="searchForm" modelAttribute="query" action="${ctx}/donationApprove/fetchTaskItemsFirst" method="post" >
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>	
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab">客户编号：</label>
                	<input type="text" name="custCode" value="${query.custCode}"  class="cf_input_text178" />
                </td>
                <td>
                	<label class="lab">客户姓名：</label>
                	<input type="text" name="custName" value="${query.custName}"  class="cf_input_text178" />
                </td>
                <td>
                	<label class="lab">理财经理工号：</label>
					<input type="text" name="managerCode" value="${query.managerCode}"  class="cf_input_text178" />
                </td>
            </tr>
            <tr>
                <td><label class="lab">理财经理姓名：</label>
                	<input type="text" name="managerName" value="${query.managerName}" class="cf_input_text178" />
                </td>
                 <td>
                	<label class="lab">营业部：</label>
				<input type="text"  value="${fns:getOrgNameById(query.departmentId)}" readonly="readonly"  class="cf_input_text178" />	
                </td>
                <td>
                </td>
            </tr>
			</table>
			<div class="tright pr30 pt10" >
				<input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="搜索" />
			    <input id="btnSubmit" class="btn cf_btn-primary" type="reset" value="清除" />
	        </div>
	        </form:form>
	        </div>
    <sys:message content="${message}"></sys:message>
    <div class='box5'>
    <table id="lendTable" class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th>客户编号</th>
            <th>客户姓名</th>
            <th>理财经理工号</th>
            <th>理财经理姓名</th>
            <th>营业部</th>
            <th>理财经理工号（新）</th>
            <th>理财经理姓名（新）</th>
            <th>营业部（新）</th>
            <th>转赠日期</th>
			<th>转赠状态</th>
            <th>操作</th>
            <shiro:hasPermission name="apply:consult:edit">
					<th>操作</th>
			</shiro:hasPermission>
        </tr>
        </thead>
        <c:forEach items="${itemList}" var="item">
			<tr>
				<td>${item.customerCode}</td>
				<!-- 屏蔽客户姓名/手机号/身份证号 -->
				<td>***</td>
				<%-- <td>${item.customerName}</td> --%>
				<td>${item.managerCode}</td>
				<td>${item.managerName}</td>
				<td>${fns:getOrgNameById(item.department)}</td>
				<td>${item.managerCodeNew}</td>
				<td>${item.managerNameNew}</td>
				<td>${fns:getOrgNameById(item.departmentNew)}</td>
				<td>${fns:getFormatDate(item.createDate,"yyyy-MM-dd HH:mm:ss")}</td>
				<td>${fns:dictName(dicts.tz_transfer_state,item.transferState,'-')}</td>
				<td>
					<auth:hasPermission key="cf:donationapprove:transact">
						<input type="button" class='cf_btn_edit' id="dispatchTool"  value="办理" onclick="launchTool('${item.applyId}','${item.wobNum}','${item.token}','${item.customerCode}',this)">
					</auth:hasPermission>
				</td>
			</tr>
        </c:forEach>
    </table>
    <div class="pagination">${page}</div>
    </div>
    </div>
    <div id="modal-sub" class="modal fade subwindow">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">审批信息</h4>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
					<input type="button" value="关闭" class="btn cf_btn-primary" id="subClose"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>