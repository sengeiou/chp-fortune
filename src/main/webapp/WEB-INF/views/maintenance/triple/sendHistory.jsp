<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
<meta name="decorator" content="default">
<script type="text/javascript" src="${ctxWebInf}/js/tripleMaintain/tripleMaintain.js"></script>
</head>
<body>
<ul id="myTabs" class="nav nav-tabs" >
<c:choose>
       <c:when test="${html eq 'managerChange'}">
		<li class="active"><a id="managerChange" href="#home" aria-controls="home" data-toggle="tab">客户理财经理变更</a></li>
	    <li><a id="orgChange" href="#home" aria-controls="home" data-toggle="tab">组织机构</a></li>
	    <li><a id="userSynchro" href="#home" aria-controls="home" data-toggle="tab">用户同步</a></li>
       </c:when>
       <c:when test="${html eq 'orgChange'}">
       	<li><a id="managerChange" href="#home" aria-controls="home" data-toggle="tab">客户理财经理变更</a></li>
	    <li class="active"><a id="orgChange" href="#home" aria-controls="home" data-toggle="tab">组织机构</a></li>
	    <li><a id="userSynchro" href="#home" aria-controls="home" data-toggle="tab">用户同步</a></li>
       </c:when>
       <c:otherwise>
       	<li><a id="managerChange" href="#home" aria-controls="home" data-toggle="tab">客户理财经理变更</a></li>
	    <li><a id="orgChange" href="#home" aria-controls="home" data-toggle="tab">组织机构</a></li>
	    <li class="active"><a id="userSynchro" href="#home" aria-controls="home" data-toggle="tab">用户同步</a></li>
       </c:otherwise>
</c:choose>
</ul>
 <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="home">
    <c:choose>
       <c:when test="${html eq 'managerChange'}">
		<form id="searchForm" action="${ctx }/maintenance/tripleSend/managerChange" method="post">
       </c:when>
       <c:when test="${html eq 'orgChange'}">
       <form id="searchForm" action="${ctx }/maintenance/tripleSend/orgChange" method="post">
       </c:when>
       <c:otherwise>
       <form id="searchForm" action="${ctx }/maintenance/tripleSend/userSynchro" method="post">
       </c:otherwise>
	</c:choose>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="box1 mb10">
			<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<td><label class="lab">发送时间：</label>
						<input type="text" name="sendTimeFrom" id ="sendTimeFrom"
							value="${fns:getFormatDate(tripleView.sendTimeFrom ,'yyyy-MM-dd')}" 
							onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'sendTimeTo\',{});}'})" class="input_text70 Wdate"> -
						<input type="text" name="sendTimeTo" id="sendTimeTo"
							value="${fns:getFormatDate(tripleView.sendTimeTo ,'yyyy-MM-dd')}" 
							onfocus="WdatePicker({minDate:'#F{$dp.$D(\'sendTimeFrom\',{});}'})" class="input_text70 Wdate">
					</td>
				</tr>
			</table>
			<div class="tright pr30 pt10">
				<input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="搜索" /> 
				<input id="btnReset" class="btn cf_btn-primary" type="reset" value="清除" />
			</div>
		</div>
		</form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>序号</th>
		<th>发送内容</th>
		<th>发送时间</th>
		<th>发送状态</th>
	</tr>
	</thead>
	<c:forEach items="${page.list}" var="item">
	<tr>
		<td>${item.uniqueNum }</td>
		<td>${item.remarks }</td>
		<td>${fns:getFormatDate(item.sendTime,"yyyy-MM-dd")}</td>
		<td>${item.sendStatus }</td>
	</tr>
	</c:forEach>
	</table>
	<div>${page}</div>
	</div>
  </div>
</body>
<!--<c:choose>
		    <c:when test="${html eq 'managerChange'}">
		    <td>
				<p>理财经理编号:${item.empCode }&nbsp&nbsp手机号:${item.newPhone }&nbsp&nbsp登录名:${item.logName }</p>
				<p>证件类型:${fns:dictName(dicts.tz_certificate_type,item.cardType,'-') }&nbsp&nbsp证件号:${item.cardId }</p>
			</td>
		    </c:when>
		    <c:when test="${html eq 'orgChange'}">
		    <td>
				<p>机构:${item.orgName }</p>
				<p>省份:${item.provinceName }&nbsp&nbsp城市:${item.cityName }&nbsp&nbsp地址:${item.orgAddr }</p>
				<p>联系人:${item.contracts }&nbsp&nbsp联系电话:${item.contractNum }</p>
			</td>
		    </c:when>
		    <c:otherwise>
		    <td>
				<p>员工编码:${item.empCode }&nbsp&nbsp员工姓名:${item.empName }&nbsp&nbsp性别:${fns:dictName(dicts.sex,item.sex,'-') }</p>
				<p>手机:${item.mobilePhone }&nbsp&nbsp邮箱:${item.mail }</p>
				<p>营业部:${fns:getOrgNameById(item.businessId)}&nbsp&nbsp团队:${fns:getOrgNameById(item.teamId)}&nbsp&nbsp职务:${item.empPosition }</p>
			</td>
		    </c:otherwise>
		</c:choose>  -->
</html>

