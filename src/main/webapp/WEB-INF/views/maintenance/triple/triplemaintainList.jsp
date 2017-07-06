<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctxWebInf}/js/tripleMaintain/tripleMaintain.js"></script>
<meta name="decorator" content="default" />
</head>
<body>
		<div class="box1 mb10">
		<form action="${ctx}/maintenance/tripleMaintain/list" method="post" id="searchForm">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
			<table class="table1">
				<tr>
					<td>
						<label class="lab">手机号：</label>
						<input type="text" name="phone" class="cf_input_text178" value="${intDeliveryEx.phone }">
					</td>
					<td>
						<label class="lab">证件号：</label>
						<input type="text" name="cardId" class="cf_input_text178" value="${intDeliveryEx.cardId }">
					</td>
					<td>
						<label class="lab">理财经理：</label>
						<input type="text" name="empName" class="cf_input_text178"  value="${intDeliveryEx.empName }" >
					</td>
				</tr>
				<tr>
			        <td>
					<label class="lab">数据库表：</label>
					<input type="text" name="osId" id="osId"  class="cf_input_text178"  value="${intDeliveryEx.osId }" >
				    </td>
				    <td>
					<label class="lab">唯一编号：</label>
					<input type="text" name="uniqueNum" id="uniqueNum" class="cf_input_text178"  value="${intDeliveryEx.uniqueNum }" >
				    </td>
				    <td>
					<label class="lab">发送状态：</label>
					<input type="text" name="sendStatus" id="sendStatus" class="cf_input_text178"  value="${intDeliveryEx.sendStatus }" >
				    </td>
				</tr>
			</table>
		    <div class="tright pr30 pb5 pt10">
		    	<input type="submit" id="search" value="搜索" class="btn cf_btn-primary"/>
				<input type="reset" id="formReset" value="清除" class="btn cf_btn-primary"/>
			</div>				
		</form>				
		</div>
		<p class="mb10">
			<button class="btn btn_sc ml10" id="updateUniqueNum" type="button">修改发送状态</button>
			<button class="btn btn_sc ml10" id="syncAppDate" type="button">同步APP丢失客户数据</button>
		</p>
		<div id="content-body">
		  <div id="reportTableDiv" class="span10">
		  <sys:columnCtrl pageToken="borrow_borrowList"></sys:columnCtrl>
		  <div class='box5'>
			  <table class="reportTable table table-striped table-bordered table-condensed data-list-table" >
			  <thead>
				<tr>
					<th>序号</th>
					<th>客户姓名</th>
					<th>客户编号</th>
					<th>手机号</th>
					<th>证件号</th>
					<th>对应系统</th>
					<th>理财经理</th>
					<th>理财经理编号</th>
					<th>成单时间</th>
					<th>最近操作时间</th>
					<th>操作</th>
				</tr>
				</thead>
				<c:forEach items="${page.list }" var="item" varStatus="s">
					<tr>
						<td>${s.index+1 }</td>
						<!-- 屏蔽客户姓名/手机号/身份证号 -->
						<td>***</td>
						<%-- <td>${item.loginName }</td> --%>
						<td>${item.customerCode }</td>
						<td>***</td>
						<%-- <td>${item.phone }</td> --%>
						<td>***</td>
						<%-- <td>${item.cardId }</td> --%>
						<td>${fns:dictName(dicts.tz_os_type,item.osType,'-') }</td>
						<td>${item.empName }</td>
						<td>${item.empCode }</td>
						<td>${fns:getFormatDate(item.orderTime,"yyyy-MM-dd")}</td>
						<td>${fns:getFormatDate(item.modifyTime,"yyyy-MM-dd")}</td>
						<td>
							<input type="button" value="交割履历" class="cf_btn_edit" onclick="record('${item.phone}','deliveryRecord')">
							<input type="button" value="发送履历" class="cf_btn_edit" onclick="record('${item.phone}','sendRecord')">
							<%-- <input type="button" value="操作履历" class="cf_btn_edit" onclick="goPage('${ctx}/tripleMaintain/operateRecord?id=1');"> --%>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	  </div>
	</div>
	<div class="pagination">${page}</div>
<div id="modal-sub" class="modal fade subwindow" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close closeButton" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">
			</div>
	</div>
</div>
</div>
</body>
</html>