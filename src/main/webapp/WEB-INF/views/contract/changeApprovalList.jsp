<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>变更审核列表</title>
<script src="${ctxWebInf}/js/contract/changeApprovalList.js" type="text/javascript" ></script>
<script type="text/javascript" src="${ctxWebInf}/js/common/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/contract/searchFormCookie.js"></script>	
</head>
<body class="body_r">
         <div class="box1 mb10"> 
    	<form:form id="searchForm" modelAttribute="contractChange" action="${ctx}/contract/changeApprovalList" method="post" >
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="menuId"  type="hidden" value="f905e27741b347a48613ebb1d0ad84df"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
       
             <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
             <tr>
        	<td>
        		<label class='lab'>营业部：</label>
        		<input type="text" name="name" id="name" class="cf_input_text178" value="${contractChange.name}" <c:if test="${contractChange.staff == '1'}">readonly="value"</c:if>/>
        	</td>
        	<td>
        		<label class='lab'>变更申请日期：</label>
        		<input type="text" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})" value="${fns:getFormatDate(contractChange.startDate,'yyyy-MM-dd')}" id="startDate" name="startDate"/> -
                <input type="text" class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" value="${fns:getFormatDate(contractChange.endDate,'yyyy-MM-dd')}" id="endDate" name="endDate"/>
        	</td>
        	<td>
        		<label class='lab'>合同版本：</label>
            	<select class="select78" id="contVersion" name="contVersion" value="${contractInformation.contVersion}">
				    <option value="">请选择</option>
					<c:forEach items="${fns:getDictList('tz_contract_vesion')}" var="item">
						 <option value="${item.value}" <c:if test="${contractChange.contVersion==item.value}">selected</c:if>>${item.label}</option>
					</c:forEach>
				</select>
        	</td>
          </tr>
          <tr>
            <c:if test="${contractChange.staff != '1'}">
	        	<td>
	        	
	        		<label class='lab'>变更类型：</label>
	        		<select class="select78" id="dictChangeType" name="dictChangeType" value="${contractChange.dictChangeType}">
		                    <option value="">请选择</option>
							<c:forEach items="${fns:getDictList('tz_change_type')}" var="item">
							     <%-- <c:if test="${contractChange.dictChangeTypeValue != item.value}">
								    <option value="${item.value}" <c:if test="${contractChange.dictChangeType==item.value}">selected</c:if>>${item.label}</option>
							     </c:if> --%>
							     <c:if test="${item.value==2}">
							      <option value="${item.value}" <c:if test="${contractChange.dictChangeType==item.value}">selected</c:if>>${item.label}</option>
								</c:if>
							</c:forEach>
					 </select>
	        	</td>
        	 </c:if>
        	<td>
        		<label class='lab'>合同编号：</label>
        		<input type="text" name="contCode" id="contCode" class="cf_input_text178" value="${contractChange.contCode}"/>
        	</td>
        	 <td>
        		<label class='lab'>审核状态：</label>
				<select class="select78" id="dictChangeStatus" name="dictChangeStatus" value="${contractChange.dictChangeStatus}">
				    <option value="">请选择</option>
					<c:forEach items="${fns:getDictList('tz_appaly_state')}" var="item">
						 <option value="${item.value}" <c:if test="${contractChange.dictChangeStatus==item.value}">selected</c:if>>${item.label}</option>
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
      </div>
      <sys:message content="${message}"/>
     
	  <p class='mb10 ml10'>
	  	  <auth:hasPermission key="cf:statuschangapproval:exportexcel">
	          <input type="button" value="导出Excel" opt="export" class='btn btn_sc ml10'>
	      </auth:hasPermission>
	  </p> 
      <table class="table table-striped table-bordered table-condensed data-list-table">
        <thead>
        <tr>
            <th><input type="checkbox" opt="all"   id="conCheckAll">全选</th>
            <th>营业部</th>
			<th>合同编号</th>
            <th>使用日期</th>
            <th>变更申请日期</th>
			<th>合同版本</th>
            <th>变更类型</th>
            <th>合同生成日期</th>
            <th>合同状态</th>
            <th>审核状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <c:forEach items="${page.list}" var="item">
        	<tr>
	            <td><input type="checkbox" opt="orders"  data-id="${item.id}"  name="idcheck"></td>
	            <td>${item.name}</td>
	            <td>${item.contCode}</td>
	            <td>${fns:getFormatDate(item.contUseDay,"yyyy-MM-dd")}</td>
	            <td>${fns:getFormatDate(item.changeDay,"yyyy-MM-dd")}</td>
	            <td>${fns:dictName(dicts.tz_contract_vesion, item.contVersion, '-') }</td>
	            <td>${fns:dictName(dicts.tz_change_type, item.dictChangeType, '-') }</td>
	            <td>${fns:getFormatDate(item.applyDay,"yyyy-MM-dd")}</td>
	            <td>${fns:dictName(dicts.tz_contract_state, item.dictContStatus, '-') }</td>
	            <td>${fns:dictName(dicts.tz_appaly_state, item.dictChangeStatus, '-') }</td>
	            <td class="tcenter">
	            	<c:if test="${item.dictChangeStatus == 0 }">
		            	<auth:hasPermission key="cf:statuschangapproval:transact">
		                	<input type="button" value="办理" class='cf_btn_edit' onclick="goPage('${ctx}/contract/changeApproval?id=${item.id}');">
		                </auth:hasPermission>
	                </c:if>
		    	</td>
		    </tr>
	    </c:forEach>
    </table>
	<div class="pagination">${page}</div>
</body>
</html>