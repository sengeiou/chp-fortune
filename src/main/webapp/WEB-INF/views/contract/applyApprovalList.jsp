<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>合同申请审核列表</title>
<script src="${ctxWebInf}/js/contract/applyApprovalList.js" type="text/javascript" ></script>	
</head>
<body class="body_r">
         <div class="box1 mb10">
    	<form:form id="searchForm" modelAttribute="contractApplyView" action="${ctx}/contract/applyApprovalList" method="post">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
       
           <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
           <tr>
        	<td>
        		<label class='lab'>营业部：</label>
        		<input type="text" name="orgName" id="orgName" name="orgName" class="cf_input_text178" value="${contractApplyView.orgName}">
        	</td>
        	<td>
        		<label class='lab'>申请日期：</label>
        		<input type="text"  id="startDate" name = "startDate" value="${fns:getFormatDate(contractApplyView.startDate,'yyyy-MM-dd')}" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})"/> -
        		<input type="text"  id="endDate" name ="endDate" value="${fns:getFormatDate(contractApplyView.endDate,'yyyy-MM-dd')}" class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})"/>
        	</td>
        	<td>
        		<label class='lab'>合同版本：</label>
				<select class="select78" id="contVersion" name="contVersion">
				    <option value="">请选择</option>
					<c:forEach items="${fns:getDictList('tz_contract_vesion')}" var="item">
						 <option value="${item.value}" <c:if test="${contractApplyView.contVersion==item.value}">selected</c:if>>${item.label}</option>
					</c:forEach>
				</select>
        	</td>
        </tr>
            <tr>
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
        	<div class="tright pr30">
			  <input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="搜索" onclick="javascript:window.searchForm.submit();" />
			  <input id="btnSubmit" class="btn cf_btn-primary" type="button" value="清除" opt="clean"/>
			</div>
			
      </form:form>
      </div>
      <sys:message content="${message}"/>
	  <input type="button" value="导出Excel" opt="export" class='btn btn_sc ml10 mb10'>
	  <input type="button" value="批量通过" opt="pass" class='btn btn_sc mb10'>
	  <input type="button" value="批量不通过" opt="reject" class='btn btn_sc mb10'>
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
	            <td>${fns:getFormatDate(item.applyDay,"yyyy-MM-dd")}</td>
	            <td>${fns:dictName(dicts.tz_appaly_state, item.applyStatus, '-')}</td>
	            <td class="tcenter">
	               <input type="button" class='cf_btn_edit' value="办理" onclick="goPage('${ctx}/contract/contractApply/getInfo?contractId=${item.contractId}');">
		    	</td>
		    </tr>
	    </c:forEach>
    </table>
	<div class="pagination">${page}</div>
</body>
</html>