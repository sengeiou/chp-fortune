<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script language="javascript">
	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
    	return false;
    }
</script>
</head>
<body>
<div class="body_top">		
	<div class="box1 mb10">
    	<form:form id="searchForm" modelAttribute="customer" action="${ctx}/customerRemind/customerRemindList" method="post">
          <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
	            <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                <td>
                	<label class="lab">客户编号：</label>
                	<input type="text" name="custCode" class="cf_input_text178" value="${customer.custCode }">
                </td>
                <td>
                	<label class="lab">客户姓名：</label>
                	<input type="text" name="custName" class="cf_input_text178" value="${customer.custName }">
                </td>
                <td>
                	<label class="lab">手机号：</label>
                	<input type="text" name="custPhone" class="cf_input_text178" value="${customer.custPhone }">
                </td>
            </tr>
            </table>
                <div class='tright pr30 mt10'>
                	<input class="btn cf_btn-primary" type="submit" value="搜索">
                	<input class="btn cf_btn-primary" type="reset" value="清除" >
                </div>
        </form:form>
	 </div>
	 <sys:message content="${message}"/>    
    <sys:columnCtrl pageToken="remind_customerRemindList"></sys:columnCtrl>
    <div class="box5">
    <table class="table table-striped table-bordered table-condensed data-list-table" width="100%">
        <thead>
        <tr>
            <th>客户姓名</th>
            <th>客户编号</th>
            <th>性别</th>
            <th>身份证号</th>
            <th>客户生日</th>
            <th>距生日天数</th>
            <th>年龄</th>
            <th>手机号</th>
        </tr>
        </thead>
     <c:forEach items="${page.list}" var="customer">
        <tr>
        	<!-- 屏蔽客户姓名/手机号/身份证号 -->
            <td>***</td>
            <%-- <td>${customer.custName }</td> --%>
            <td>${customer.custCode }</td>
            <td>${fns:dictName(dicts.sex,customer.dictCustSex,'-')}</td>
            <%-- <td>${fns:valueDesensitize(customer.managerCode,customer.custCertNum)}</td> --%>
            <td>*******</td>
            <td>${customer.custBirthday }</td>
            <td>${customer.lastBirthdayDay}</td>
            <td>${customer.custAge }</td>
            <%-- <td>${fns:valueDesensitize(customer.managerCode,customer.custPhone)}</td> --%>
            <td>*******</td>
        </tr>
     </c:forEach>
    </table>
    </div>
	<div class="pagination">${page}</div>
</div>
</body>
</html>
