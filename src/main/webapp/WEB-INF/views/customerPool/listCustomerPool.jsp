<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
   <script type="text/javascript" src="${ctxWebInf}/js/customerPool/listCustomerPool.js"> </script>
	<title>公共池列表</title>
	<meta name="decorator" content="default" />
</head>
<body>
	<div class="body_r">
    <div class="box1 mb10">
    <form id="searchForm" action="${ctx}/customerPoolController/listCustomerPool" method="post" >
      		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
          <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
             <tr>
                <td>
                	<label class="lab">客户姓名：</label><input type="text"  class="cf_input_text178" name="customerName"  value="${customerPool.customerName}">
                </td>
                <td>
                	<label class="lab">联系电话：</label><input type="text" class="cf_input_text178"  name="customerTel"  value="${customerPool.customerTel}">
                </td>
                <td>
                	<label class="lab">身份证号：</label><input type="text" class="cf_input_text178"  name="card"  value="${customerPool.card}">
                </td>
            </tr>  
             <tr>
                <td>
                <label class="lab">所在城市：</label>
					<sys:cityselect id="addrCity" name="cityIdTemp" value="${customerPool.cityIdTemp}"  />
                </td>
            </tr>       
        </table>
         <div class="tright pr30">
        	<input type="submit" id="search" value="搜索"  class="btn cf_btn-primary"/>
        	<input type="reset" value="清除" class="btn cf_btn-primary"/>
        </div>
        </form>
      </div>        
      <p class="mb10">
      	<auth:hasPermission key="cf:elecpool:exportexcel">
       		<input type="button"  id ="exportCustomerPool" class="btn btn_sc ml10" value="导出Excel"/>
       	</auth:hasPermission>
      </p>
    <table class="table table-striped table-bordered table-condensed data-list-table">
    <thead>
        <tr>
            <th><input type='checkbox'  id="checkAll" class="checkAll"/></th>
            <th>客户编号</th>
            <th>客户姓名</th>
            <th>省份</th>
            <th>城市</th>
            <th>营业部</th>
            <th>联系电话</th>
            <th>证件号码</th>
            <th>进入公共池时间</th>
            <th>出借咨询录入时间</th>
            <th>最后编辑时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <c:forEach items="${page.list}" var="item">
	        <tr>
	            <td><input type="checkbox"  name="id" class="borrowMonthableListCheckbox" value="${item.id }" /></td>
	            <td>${item.customerCode}</td>
	            <!-- 屏蔽客户姓名/手机号/身份证号 -->
	            <td>***</td>
	            <%-- <td>${item.customerName}</td> --%>
	            <td>${item.provinceName}</td>
	            <td>${item.cityName}</td>
	            <td>${item.salesDeptName}</td>
	            <td>***</td>
	            <%-- <td>${item.customerTel}</td> --%>
	            <td>***</td>
	            <%-- <td>${item.card}</td> --%>
	            <td> ${fns:getFormatDate(item.createTime,'yyyy-MM-dd')}</td>  
	            <td>${fns:getFormatDate(item.lendInputDate,'yyyy-MM-dd')} </td>
	            <td>${fns:getFormatDate(item.modifyTime,'yyyy-MM-dd')}</td>
	            <td>
	            	<auth:hasPermission key="cf:elecpool:transact">
	            		<a href="${ctx}/customerPoolController/handle?id=${item.id}&custCode=${item.customerCode}" >办理</a>
	            	</auth:hasPermission>
	               <%--  <a href="${ctx}/customerPoolController/distributePage?id=${item.id}" >分配</a>  
	                <a href="${ctx}/customerPoolController/advisoryList?custCode=${item.customerCode}" >留痕</a> --%>
	            </td>
	        </tr>
        </c:forEach>
    </table>
      <div class="pagination">${page}</div>
  </div>   
</body>

</html>