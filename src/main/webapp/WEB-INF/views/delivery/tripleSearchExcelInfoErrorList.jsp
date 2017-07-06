<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf}/js/delivery/tripleDeliveryError.js" type="text/javascript"></script>
<title>三网客户交割失败查询</title>
</head>
<body>
<div class="body_top body_r">
     <div class="box1 mb10">
       <form:form id="searchForm" modelAttribute="intDeliveryEx" action="${ctx}/delivery/tripleCustomer/errorList/list" method="post">
          <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                <td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178" id="loginName" name="loginName" value="${intDeliveryEx.loginName}"/></td>
                <td><label class="lab">操作时间：</label>
                  <input type="text" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}'})" id="startTime" name="startTime" value="${intDeliveryEx.startTime}"/> -
                  <input type="text" class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" id="endTime" name="endTime" value="${intDeliveryEx.endTime}"/>
                </td>
                <td><label class="lab">批次号：</label><input type="text" class="cf_input_text178" id="batchNumber" name="batchNumber" value="${intDeliveryEx.batchNumber}"></td>
            </tr>
            <tr>
                 <td><label class="lab">客户编号：</label><input type="text" class="cf_input_text178" id="customerCode" name="customerCode" value="${intDeliveryEx.customerCode}"></td>
                  <td>
                    <label class="lab">客户平台：</label>
                    <select class="select78" id="osType" name="osType" multiple="true">
                        <c:forEach items="${dicts.tz_os_type}" var="item">
                             <option value="${item.value }" <c:if test="${intDeliveryEx.osType==item.value}">selected</c:if>>${item.label }</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            
        </table>
       
        <div class="tright pr30">
             <input type="submit" class="btn cf_btn-primary" value="搜索" />
             <input type="reset" class="btn cf_btn-primary"  value="清除" />
        </div>
      </form:form>
	 </div> 
	 <div class="mb10">
	 <auth:hasPermission key="cf:tripleSearchExcelInfoErrorList:exportexcel">
        <button class="btn btn_sc ml10" id="outExcel" type="button">导出Excel</button>
     </auth:hasPermission>
    	&nbsp;&nbsp;&nbsp;总笔数：${page.count}笔
     </div>
    <div class="box5" style="padding-right:10px;height:100%;">
    <table id="tableList" class="table table-striped table-bordered table-condensed data-list-table">
		<thead>
		  <tr>
		    <th>导入批次号</th>
		    <th>客户编号</th>
		    <th>客户姓名 </th>
		    <th>理财经理工号</th>
		    <th>理财经理</th>
		    <th>营业部</th>
		    <th>理财经理工号（新）</th>
		    <th>理财经理（新）</th>
		    <th>客户平台</th>
		    <th>操作时间</th>
		    <th>备注</th>
		  </tr>
		</thead>
		<tbody>
		  <c:forEach items="${page.list}" var="item">
		        <tr>
		          <td>${item.batchNumber}</td>
		          <td>${item.customerCode }</td>
		          <!-- 屏蔽客户姓名/手机号/身份证号 -->
		          <td>***</td>
		          <%-- <td>${item.loginName}</td> --%>
		          <td>${item.empCode}</td>
		          <td>${item.empName}</td>
		          <td>${item.orgName}</td>
		          <td>${item.newEmpCode}</td> 
		          <td>${item.newEmpName}</td>
		          <td>${fns:dictName(dicts.tz_os_type,item.osType,'*')}</td>
		          <td>${fns:getFormatDate(item.createTime,"yyyy-MM-dd HH:mm")}</td>
		          <td class='red'>${item.remark}</td>
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