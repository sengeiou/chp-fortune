<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf}/js/delivery/tripleCustomerDeliveryHistoryList.js" type="text/javascript"></script>
<title>三网客户归属变更查询</title>
</head>
<body>
<div class="body_top body_r">
     <div class="box1 mb10">
       <form:form id="searchForm" modelAttribute="intDeliveryEx" action="${ctx}/delivery/tripleCustomer/history/list" method="post">
          <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                <td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178" id="loginName" name="loginName" value="${intDeliveryEx.loginName}"/></td>
                <td><label class="lab">交割时间：</label>
                  <input type="text" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}'})" id="startTime" name="startTime" value="${intDeliveryEx.startTime}"/> -
                  <input type="text" class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" id="endTime" name="endTime" value="${intDeliveryEx.endTime}"/>
                </td>
                <td><label class="lab">新理财经理工号：</label><input type="text" class="cf_input_text178" id="newEmpCode" name="newEmpCode" value="${intDeliveryEx.newEmpCode}"></td>
            </tr>
            <tr>
                <td><label class="lab">新理财经理：</label><input type="text" class="cf_input_text178" id="newEmpName" name="newEmpName" value="${intDeliveryEx.newEmpName}"></td>
                <td><label class="lab">营业部：</label>
                    <sys:orgTree id="newOrgId" name="newOrgId" value="${intDeliveryEx.newOrgId}" labelName="newOrgName" labelValue="${intDeliveryEx.newOrgName}"/>
                </td>
                <td><label class="lab">客户编号：</label><input type="text" class="cf_input_text178" id="customerCode" name="customerCode" value="${intDeliveryEx.customerCode}"></td>
            </tr>
            <tr id='T1' style='display:none;'>
                <td>
                    <label class="lab">客户平台：</label>
                    <select class="select78" id="osType" name="osType" multiple="true">
                        <c:forEach items="${dicts.tz_os_type}" var="item">
                             <option value="${item.value }" <c:if test="${fns:multiOption(intDeliveryEx.osType,item.value)}">selected</c:if>>${item.label }</option>
                        </c:forEach>
                    </select>
                </td>
                <td><label class="lab">变更原因：</label>
                <select class="select78" id="deliveryType" name="deliveryType" multiple="true">
                    <c:forEach items="${dicts.tz_delivery_type}" var="item">
                    	<c:if test="${item.value!=2 and item.value!=4}">
	                         <option value="${item.value }" <c:if test="${fns:multiOption(intDeliveryEx.deliveryType,item.value)}">selected</c:if>>${item.label}</option>
                    	</c:if>
                    </c:forEach>
                </select>
                <td><label class="lab">原理财经理工号：</label><input type="text" class="cf_input_text178" id="empCode" name="empCode" value="${intDeliveryEx.empCode}"></td>
            </tr>
            <tr id='T1' style='display:none;'>
                <td><label class="lab">原理财经理：</label><input type="text" class="cf_input_text178" id="empName" name="empName" value="${intDeliveryEx.empName}"></td>
                <td><label class="lab">原营业部：</label>
                    <sys:orgTree id="orgId" name="orgId" value="${intDeliveryEx.orgId}" labelName="orgName" labelValue="${intDeliveryEx.orgName}"/>
                </td>
            </tr>
        </table>
       <input type="hidden" name="difference" id="difference"/>
        <div class="tright pr30 pb5 pt10">
             <input type="submit" class="btn cf_btn-primary" value="搜索" />
             <input type="reset" class="btn cf_btn-primary"  value="清除" />
             <div class="xiala"  id="showMore" onclick="javascript:show();"></div>
        </div>  
      </form:form>
	 </div> 
	 <div class="mb10">
	 	<auth:hasPermission key="cf:customerdeliveryhistory:exportexcel">
    		<button class="btn btn_sc ml10 outExcel" difference="excel" type="button">导出Excel</button>
    	</auth:hasPermission>
	 	<auth:hasPermission key="cf:customerdeliveryhistory:exportcardexcel">
    		<button class="btn btn_sc ml10 outExcel" difference="cardExcel"  type="button">导出证件号Excel</button>
    	</auth:hasPermission>
    	&nbsp;&nbsp;&nbsp;总笔数：${page.count}笔
     </div>
    <sys:message content="${message}"/>
    <div class="box5">
    <table id="tableList" class="table table-striped table-bordered table-condensed data-list-table">
		<thead>
		  <tr>
		    <th>客户编号</th>
		    <th>客户姓名</th>
		    <th>营业部 </th>
		    <th>团队经理</th>
		    <th>新理财经理</th>
		    <th>新理财经理工号</th>
		    <th>原营业部</th>
		    <th>原团队经理</th>
		    <th>原理财经理</th>
		    <th>原理财经理工号</th>
		    <th>客户平台</th>
		    <th>变更时间</th>
		    <th>操作人</th>
		    <th>变更原因</th>
		  </tr>
		</thead>
		<tbody>
		  <c:forEach items="${page.list}" var="item">
		        <tr>
		          <td>${item.customerCode}</td>
		          <!-- 屏蔽客户姓名/手机号/身份证号 -->
		          <td>***</td>
		          <%-- <td>${item.loginName}</td> --%>
		          <td>${item.newOrgName}</td>
		          <td>${item.newTeamManagerName}</td>
		          <td>${item.newEmpName}</td>
		          <td>${item.newEmpCode}</td>
		          <td>${item.orgName}</td>
		          <td>${item.teamManagerName}</td>
		          <td>${item.empName}</td>
		          <td>${item.empCode}</td>
		          <td>${fns:dictName(dicts.tz_os_type,item.osType,'*')}</td>
		          <td>${fns:getFormatDate(item.delDate,"yyyy-MM-dd HH:mm")}</td>
		          <c:if test="${item.changeReason=='手动交割'}">
			          <td>${item.operator}</td>
		          </c:if>
		          <c:if test="${item.changeReason!='手动交割'}">
			          <td></td>
		          </c:if>
		          <td>${item.changeReason}</td>
		        </tr>
		    </c:forEach>
		</tbody>
	</table>
	</div>
	<div class="pagination">${page}</div>
	 <script type="text/javascript">
		$("#tableList").wrap('<div id="content-body"><div id="reportTableDiv"></div></div>');
		$('#tableList').bootstrapTable({
		method: 'get',
		cache: false,
		height: $(window).height()-220,
		
		pageSize: 20,
		pageNumber:1
		});
		$(window).resize(function () {
		$('#tableList').bootstrapTable('resetView');
		});
	</script>
</div>
</body>
</html>