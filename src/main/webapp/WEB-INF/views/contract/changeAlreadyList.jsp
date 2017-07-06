<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>变更申请已办</title>
<script src="${ctxWebInf}/js/contract/changeAlreadyList.js" type="text/javascript" ></script>	
</head>
<body class="body_r">

    	<form:form id="searchForm" modelAttribute="contractChange" action="${ctx}/contract/changeAlreadyList" method="post" >
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
        <div class="box1 mb10">
            <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
        	<tr>
        	    <td>
        		<label class='lab'>营业部：</label>
        		<input type="hidden" name="changeInStoresId" id="changeInStoresId" class="cf_input_text178" value="${contractChange.changeInStoresId}"/>
        		<input type="text" name="name" id="name" value="${contractChange.name}" class='cf_input_text178'>
        		</td>
                <td>
        		<label class='lab'>变更日期：</label>
        		<input type="text" name="changeDay" id="changeDay" class="cf_input_text178" onfocus="WdatePicker()" value="${fns:getFormatDate(contractChange.changeDay,'yyyy-MM-dd')}"/>
        	    </td>
        	    <td>
        		<label class='lab'>合同编号：</label>
        		<input type="text" name="contCode" id="contCode" class="cf_input_text178" value="${contractChange.contCode}"/>
        	    </td>
            </tr>
        	<tr>
        	    <td>
        		<label class='lab'>合同版本：</label>
        		<select class="select78" id="contVersion" name="contVersion" value="${contractChange.contVersion}">
        		         <option value="">请选择</option>
						<c:forEach items="${fns:getDictList('tz_contract_vesion')}" var="item">
							 <option value="${item.value}" <c:if test="${contractChange.contVersion==item.value}">selected</c:if>>${item.label}</option>
						</c:forEach>
				</select>
        	   </td>
        	   <td>
        		<label class='lab'>变更类型：</label>
				<select class="select78" id="dictChangeType" name="dictChangeType"  value="${contractChange.dictChangeType}">
	                    <option value="">请选择</option>
						<c:forEach items="${fns:getDictList('tz_change_type')}" var="item">
							 <option value="${item.value}" <c:if test="${contractChange.dictChangeType==item.value}">selected</c:if>>${item.label}</option>
						</c:forEach>
				 </select>
        	</tr>
        	<tr id='T1' style='display:none;'>
        	   <td>
        		<label class='lab'>审核状态：</label>
				<select class="select78" id="dictChangeStatus" name="dictChangeStatus" value="${dictChangeStatus.dictChangeType}">
				    <option value="">请选择</option>
					<c:forEach items="${fns:getDictList('tz_appaly_state')}" var="item">
						 <option value="${item.value}" <c:if test="${dictChangeStatus.dictChangeType==item.value}">selected</c:if>>${item.label}</option>
					</c:forEach>
				</select>
        	  </td>
        </tr>
        </table>	
        </form:form>
            
        	<div class="tright pr30 pb5 pt10">
			  <input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="搜索" onclick="javascript:window.searchForm.submit();" />
			  <input id="btnSubmit" class="btn cf_btn-primary" type="button" value="清除" opt="clean" />
			  <div class="xiala"  id="showMore" onclick="javascript:show();"></div>
			</div>
		
      </div>
      <sys:message content="${message}"/>
      <p class='mb10'>
          <input type="button" value="导出Excel" opt="export" class='btn btn_sc ml10'>
      </p>
	  
      <table class="table table-striped table-bordered table-condensed data-list-table">
        <thead>
        <tr>
            <th><input type="checkbox" opt="all">全选</th>
            <th>门店</th>
			<th>合同编号</th>
            <th>合同版本</th>
            <th>变更日期</th>
			<th>变更类型</th>
            <th>审核状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <c:forEach items="${page.list}" var="item">
        	<tr>
	            <td><input type="checkbox" opt="orders" data-id="${item.id}"></td>
	            <td>${item.name}</td>
	            <td>${item.contCode}</td>
	            <td>${fns:dictName(dicts.tz_contract_vesion, item.contVersion, '-') }</td>
				<td>${fns:getFormatDate(item.changeDay,'yyyy-MM-dd')}</td>
	            <td>${fns:dictName(dicts.tz_change_type, item.dictChangeType, '-') }</td>
	            <td>${fns:dictName(dicts.tz_appaly_state, item.dictChangeStatus, '-') }</td>
	            <td class="tcenter">
	               <input type="button" value="办理" class='cf_btn_edit' onclick="window.location.href='${ctx}/contract/changeAlready?id=${item.id}'">
		    	</td>
		    </tr>
	    </c:forEach>
    </table>
	<div class="pagination">${page}</div>
</body>
</html>