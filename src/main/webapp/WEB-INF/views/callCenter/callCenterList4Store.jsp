<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
   <script type="text/javascript" src="${ctxWebInf}/js/callCenter/callCenterList4Store.js"> </script>
   <script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
	<title>呼叫中心客户列表</title>
	<meta name="decorator" content="default" />
</head>
<body>
	<div class="body_r">
    <div class="box1 mb10">
    <form id="searchForm" action="${ctx}/callCenterController/callCenterList4Store" method="post" >
      		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
          <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
             <tr>
                <td>
                	<label class="lab">客户姓名：</label><input type="text"  class="cf_input_text178" name="customerName"  value="${customer.customerName}">
                </td>
                <td>
                	<label class="lab">手机号码：</label><input type="text" class="cf_input_text178"  name="customerMobilephone"  value="${customer.customerMobilephone}">
                </td>
                <td>
                	<label class="lab">身份证号：</label><input type="text" class="cf_input_text178"  name="customerCertNum"  value="${customer.customerCertNum}">
                </td>
            </tr>  
        </table>
         <div class="tright pr30">
        	<input type="submit" id="search" value="搜索"  class="btn cf_btn-primary"/>
        	<input type="reset" value="清除" class="btn cf_btn-primary"/>
        </div>
        </form>
      </div>        
    <table class="table table-striped table-bordered table-condensed data-list-table">
    <thead>
        <tr>
            <th><input type='checkbox'  id="checkAll" class="checkAll"/></th>
            <th>客户姓名</th>
            <th>性别</th>
            <th>手机号</th>
            <th>固定电话</th>
            <th>省份</th>
            <th>城市</th>
            <th>状态</th>
            <th>添加时间</th>
            <th>理财经理</th>
            <th>操作</th>
        </tr>
        </thead>
        <c:forEach items="${page.list}" var="item">
	        <tr>
	            <td><input type="checkbox"  name="id" class="borrowMonthableListCheckbox" value="${item.id }" /></td>
	            <!-- 屏蔽客户姓名/手机号/身份证号 -->
				<td>***</td>
	            <%-- <td>${item.customerName}</td> --%>
	            <td>${item.dictCustomerSexName}</td>
	            <!-- 屏蔽客户姓名/手机号/身份证号 -->
				<td>***</td>
	            <%-- <td>${item.customerMobilephone}</td> --%>
	            <!-- 屏蔽客户姓名/手机号/身份证号 -->
				<td>***</td>
	            <%-- <td>${item.customerTel}</td> --%>
	            <td>${item.provinceName}</td>
	            <td>${item.cityName}</td>
	            <td>
	            		<c:choose>
	            			<c:when test="${item.status == '0'}">新增</c:when>
	            			<c:when test="${item.status == '1'}">在城市</c:when>
	            			<c:when test="${item.status == '2'}">在门店</c:when>
	            			<c:when test="${item.status == '3'}">在团队</c:when>
	            			<c:when test="${item.status == '4'}">分配理财经理</c:when>
	            		</c:choose>
	            </td>
	            <td>${fns:getFormatDate(item.createTime,'yyyy-MM-dd')}</td>  
	            <td>${item.managerName}</td>
	            <td>
	            	<a href="javascript:void(0);"  custId="${item.id }"  class="distributeClass" >分配理财经理</a>
	            </td>
	        </tr>
        </c:forEach>
    </table>
</div>
      <div class="pagination">${page}</div>
      
      <div id="modal-sub" class="modal fade subwindow">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
					<input type="button" value="提交" class="btn cf_btn-primary" id="subSubmit"/>
					<input type="button" value="关闭" class="btn cf_btn-primary" id="subClose"/>
				</div>
			</div>
		</div>
	</div>
</body>

</html>