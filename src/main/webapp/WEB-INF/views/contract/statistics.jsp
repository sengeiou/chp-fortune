<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<style type="text/css">
.form-search .ul-form li label{
width:130px;
}
</style>
<meta name="decorator" content="default"/>
<title>合同统计</title>
<script src="${ctxWebInf}/js/contract/statistics.js" type="text/javascript" ></script>
</head>
<body>
        <div class="box1 mb10">
        
    	<form:form id="searchForm" modelAttribute="contractStatistics" action="${ctx}/contract/statisticsList" method="post" >
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
         <tr>
        	<td>
        		<label class='lab'>营业部：</label>
        		<input type="hidden" name="contStoresId" id="contStoresId" class="cf_input_text178" value="${contractStatistics.contStoresId}"/>
        		<input type="text" name="orgName" id="orgName" class="cf_input_text178" value="${contractStatistics.orgName}"/>
        	</td>
        	<td>
        		<label class='lab'>统计日期：</label>
        		<input type="text" name="start" id="start" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'end\')}'})" value="${fns:getFormatDate(contractStatistics.start,'yyyy-MM-dd')}"/> -
        		<input type="text" name="end" id="end" class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start\')}'})" value="${fns:getFormatDate(contractStatistics.end,'yyyy-MM-dd')}"/>
        	</td>
        	<td>
        		<label class='lab'>合同版本：</label>
        		   <select class="select78" id="contVersion" name="contVersion">
				    <option value="">请选择</option>
					<c:forEach items="${fns:getDictList('tz_contract_vesion')}" var="item">
						 <option value="${item.value}" <c:if test="${contractStatistics.contVersion==item.value}">selected</c:if>>${item.label}</option>
					</c:forEach>
					
				</select>
        	</td>
        </tr>
	  </table>
	  	<div class='tright pr30 pt10'>
      		<input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="搜索" onclick="javascript:window.searchForm.submit();"/>
			<input id="btnSubmit" class="btn cf_btn-primary" type="button" value="清除" opt="clean"/>
		</div>
	  </form:form>
      </div>
      <sys:message content="${message}"/>
	  <p class='mb10'>
	  	<auth:hasPermission key="cf:contractcount:exportexcel">
	  		<input type="button" value="导出Excel" opt="export" class='btn btn_sc ml10'>
	  	</auth:hasPermission>
	  </p>
      <table class="table table-striped table-bordered table-condensed data-list-table">
        <thead>
        <tr>
            <th>营业部</th>
            <th>合同生成量</th>
            <th>有效使用量</th>
            <th>作废量</th>
            <th>合同版本</th>
            <th>起始时间</th>
            <th>终止时间</th>
        </tr>
        </thead>
        <c:forEach items="${page.list}" var="item">
        	<tr>
	            <td>${item.orgName}</td>
	            <td>${item.createCount}</td>
				<td>${item.validNum}</td>
	            <td>${item.uselessNum}</td>
	            <td>
	            <c:choose>
	            		<c:when test="${item.contractType eq '2' }">
	            			协议${fns:getDictLabel(item.contVersion,'tz_contract_vesion','-')}
	            		</c:when>
	            		<c:otherwise>
	            			${fns:getDictLabel(item.contVersion,'tz_contract_vesion','-')}
	            		</c:otherwise>
	            	</c:choose>
	            </td>
	            <td>${fns:getFormatDate(contractStatistics.start,'yyyy-MM-dd')}</td>
	            <td>${fns:getFormatDate(contractStatistics.end,'yyyy-MM-dd')}</td>
		    </tr>
	    </c:forEach>
    </table>
	<div class="pagination">${page}</div>
</body>
</html>