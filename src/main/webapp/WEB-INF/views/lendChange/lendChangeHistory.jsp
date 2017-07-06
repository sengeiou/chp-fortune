<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>出借信息变更历史列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxWebInf}/js/lendChange/lendChange_common.js"></script>
	<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
</head>
<body>
<div class="body_r">
    <div>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
      </div>
    <sys:message content="${message}"></sys:message>
    <div class='box5'>
    <table id="lendTable" class="table table-striped table-bordered table-condensed data-list-table lendChangeHistoryTable">
        <thead>
        <tr>
            <th>客户姓名</th>
            <th>客户编号</th>
            <th>出借编号</th>
            <th>省份</th>
            <th>城市</th>
            <th>营业部</th>
            <th>团队经理</th>
            <th>理财经理</th>
            <th>开发团队</th>
            <th>托管状态</th>
			<th>出借状态</th>
            <th>客户状态</th>
            <th>客户来源</th>
            <th>创建日期</th>
            <th>操作</th>
            <shiro:hasPermission name="apply:consult:edit">
					<th>操作</th>
			</shiro:hasPermission>
        </tr>
        </thead>
        <c:forEach items="${page.list}" var="item">
            <tr>
            	<!-- 屏蔽客户姓名/手机号/身份证号 -->
				<td>***</td>
				<%-- <td>${item.custName}</td> --%>
				<td>${item.custCode}</td>
				<td>${item.applyCode}</td>
				<td>${fns:getProvinceLabel(item.addrProvince)}</td>
				<td>${fns:getCityLabel(item.addrCity)}</td>
				<td>${item.departmentName}</td>
				<td>${item.teamManagerName}</td>
				<td>${item.managerName}</td>
				<td>${item.teamName}</td>
				<td>${fns:dictName(dicts.tz_trust_state,item.applyHostedState,'-')}</td>
				<td>${fns:dictName(dicts.tz_lend_state,item.applyState,'-')}</td>
				<td>${fns:dictName(dicts.tz_customer_state,item.custState,'-')}</td>
				<td>${fns:dictName(dicts.tz_customer_src,item.dictCustSource,'-')}</td>
				<td><fmt:formatDate value="${item.flowStartTime}" type="both"/> </td>
			    <td>
			         <a href="javascript:void(0)" onclick="openHistoryDetails('${item.applyId}')">详细</a> 
			        
				</td>
		
			</tr>
        </c:forEach>
    </table>
    </div>
    </div>
   <div class="pagination">${page}</div>
    <div class='tright pr30'>
    <input id="btnCancel"  class="btn cf_btn-primary" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
   <div id="modal-sub" class="modal fade subwindow">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">出借信息变更历史</h4>
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


