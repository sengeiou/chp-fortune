<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>合同申请已办</title>
<script src="${ctxWebInf}/js/contract/applyAlreadyList.js" type="text/javascript" ></script>	
</head>
<body class="body_r">
     <div class="box1 mb10">
     <form:form id="searchForm" modelAttribute="contractApplyView" action="${ctx}/contract/applyAlreadyList" method="post" >
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>  
         <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
        	<tr>
        	    <td>
        		<label class="lab">营业部：</label>
        		<input type="text" id="orgName" class="cf_input_text178" name="orgName"  value="${contractApplyView.orgName}" <c:if test="${contractApplyView.staff == '1'}">readonly="value"</c:if>/>
        	    </td>
            <td>
        		<label class="lab">审核日期：</label>
        		<input type="text" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endCheckDate\')}'})" value="${fns:getFormatDate(contractApplyView.startCheckDate,'yyyy-MM-dd')}" id="startCheckDate" name="startCheckDate"/> -
                <input type="text" class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startCheckDate\')}'})" value="${fns:getFormatDate(contractApplyView.endCheckDate,'yyyy-MM-dd')}" id="endCheckDate" name="endCheckDate"/>
        	</td>
			<td>
        		<label class="lab">合同版本：</label>
				<select class="select78" id="contVersion" name="contVersion">
				    <option value="">请选择</option>
					<c:forEach items="${fns:getDictList('tz_contract_vesion')}" var="item">
						 <option value="${item.value}" <c:if test="${contractApplyView.contVersion==item.value}">selected</c:if>>${item.label}</option>
					</c:forEach>
				</select>
        	</td>
            <tr>
                <td>
        		<label class="lab">申请日期：</label>
        		<input type="text" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})" value="${fns:getFormatDate(contractApplyView.startDate,'yyyy-MM-dd')}" id="startDate" name="startDate"/> -
                <input type="text" class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" value="${fns:getFormatDate(contractApplyView.endDate,'yyyy-MM-dd')}" id="endDate" name="endDate"/>
        	    </td>
			    <td>
        		<label class="lab">派发日期：</label>
        		<input type="text" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDistDate\')}'})" value="${fns:getFormatDate(contractApplyView.startDistDate,'yyyy-MM-dd')}" id="startDistDate" name="startDistDate"/> -
                <input type="text" class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDistDate\')}'})" value="${fns:getFormatDate(contractApplyView.endDistDate,'yyyy-MM-dd')}" id="endDistDate" name="endDistDate"/>
        	    </td>
                <td>
        		<label class="lab">审核状态：</label>
				<select class="select78" id="applyStatus" name="applyStatus">
				    <option value="">请选择</option>
					<c:forEach items="${fns:getDictList('tz_appaly_state')}" var="item">
						 <option value="${item.value}" <c:if test="${contractApplyView.applyStatus==item.value}">selected</c:if>>${item.label}</option>
					</c:forEach>
				</select>
        	    </td>
		     </tr>
		   </table>
			<div class="tright pr30 pt10">
			  <input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="搜索" onclick="javascript:window.searchForm.submit();" />
			  <input id="btnSubmit" class="btn cf_btn-primary" type="button" value="清除" opt="clean"/>
			</div>
		</form:form>
		<sys:message content="${message}"/>
		</div>
		<input type="button" value="新建合同申请" opt="applyContract" class='btn btn_sc ml10 mb10'>
		<input type="button" value="导出Excel" opt="export" class='btn btn_sc mb10 '>
        <table class="table table-striped table-bordered table-condensed data-list-table">
	        <thead>
	        <tr>
	            <th><input type="checkbox" opt="all">全选</th>
	            <th>营业部</th>
				<th>上月使用量</th>
	            <th>现有库存</th>
	            <th>申请数量</th>
				<th>派发数量</th>
	            <th>合同版本</th>
	            <th>申请日期</th>
				<th>审核日期</th>
				<th>派发日期</th>
	            <th>审核状态</th>
	            <th>操作</th>
	        </tr>
	        </thead>
         <c:forEach items="${page.list}" var="item">
        <tr>
            <td><input type="checkbox" opt="records" data-id="${item.contractId}"></td>
            <td>${item.orgName}</td>
            <td>${item.applyAlreadyuse}</td>
            <td>${item.applyInventory}</td>
			<td>${item.applyNo}</td>
			<td>${item.distContractNo}</td>
            <td>${fns:dictName(dicts.tz_contract_vesion, item.contVersion, '-')}</td>
            <td>${fns:getFormatDate(item.applyDay,'yyyy-MM-dd')}</td>
			<td>${fns:getFormatDate(item.applyCheckDate,'yyyy-MM-dd')}</td>
			<td>${fns:getFormatDate(item.distDate,'yyyy-MM-dd')}</td>
            <td>${fns:dictName(dicts.tz_appaly_state, item.applyStatus, '-')}</td>
            <td class="tcenter">
            	<input type="button" value="办理"  class='cf_btn_edit' onclick="goPage('${ctx}/contract/applyAlready?contractId=${item.contractId}');">
		    </td>
        </tr>
        </c:forEach>
    </table>
    <div class="pagination">${page}</div>
</body>
</html>