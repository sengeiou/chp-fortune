<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借人信息变更申请列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxWebInf}/js/lenderChange/lenderChangeSearch.js"></script>
	<script type="text/javascript" src="${ctxWebInf}/js/lenderChange/lenderChangeQueryList.js"></script>
</head>
<body class="body_r">
	<form:form id="searchForm" modelAttribute="query" action="${ctx}/lenderChange/query/queryList" method="post"  class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<div class="box1 mb10">
		    <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		    <tr>
			<td>
				<label class='lab'>客户姓名：</label>
				<input type="text" name="custName" value="${query.custName}"  class="cf_input_text178" />
			</td>
			<td><label class='lab'>城市：</label>
				<sys:cityselect name="addrCity" value="${query.addrCity}" multiple="true"  />
			</td>
			
			<td><label class='lab'>客户编号：</label>
				<input type="text" name="custCode" value="${query.custCode}"  class="cf_input_text178" />
			</td>
			</tr>
			<tr>
			<td><label class='lab'>变更状态：</label>
				<select name="dictChangeState" >
                		<option value="">请选择</option>
                      <c:forEach items="${dicts.tz_lenderchg_state}" var ="d" >
                	    <option  value="${d.value }" <c:if test="${d.value eq query.dictChangeState}">
									selected
								</c:if> >${d.label }</option>
                      </c:forEach>
                </select>
			</td>
			<td>
			    	<label class="lab">审核通过日期：</label>
			    	<input type="text" name="dictApprovalStartDate" id="dictApprovalStartDate" 
			    		value="${fns:getFormatDate(query.dictApprovalStartDate,'yyyy-MM-dd')}" 
			    		class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'dictApprovalEndDate\',{d:-1});}'})"> -
			    	<input type="text" name="dictApprovalEndDate" id="dictApprovalEndDate" 
			    		value="${fns:getFormatDate(query.dictApprovalEndDate,'yyyy-MM-dd')}" 
			    		class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'dictApprovalStartDate\',{});}'})">
			</td>
			<td>
                	<label class="lab">营业部：</label>
                	<sys:orgTree id="org"  name="orgCode" value="${query.orgCode}" labelName="orgName"  labelValue="${query.orgName}" />
            </td>
			</tr>
			</table>
			<div class="tright pr30 pb5 pt10">
			  <input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="搜索" />
			  <input id="btnSubmit" class="btn cf_btn-primary" type="reset" value="清除" />
			</div>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<p class="mb10">
		<auth:hasPermission key="cf:lenderchanglook:exportexcel">
	  		<input type="button" class="btn btn_sc ml10" id="expExcel"  value="导出excel" />
	  	</auth:hasPermission>
	</p>
	<table id="lenderTable" class="table table-striped table-bordered table-condensed data-list-table">
		<thead>
			<tr>
			    <th><input type="checkbox" class="ml10 mr10 checkAll" id="checkAll">全选</th>
				<th>客户姓名</th>
				<th>客户编号</th>
				<th>省份</th>
				<th>城市</th>
				<th>营业部</th>
				<th>团队经理</th>
				<th>理财经理</th>
				<th>开发团队</th>
				<th>客户来源</th>
				<th>变更状态</th>
				<th>创建日期</th>
				<th>审核通过日期</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item">
			<tr>
			 <td><input type="checkbox" value="${item.applyId}"  ></td>
			 	<!-- 屏蔽客户姓名/手机号/身份证号 -->
				<td>***</td>
				<%-- <td>${item.custName}</td> --%>
				<td>${item.custCode}</td>
				<td>${fns:getProvinceLabel(item.addrProvince)}</td>
				<td>${fns:getCityLabel(item.addrCity)}</td>
				<td>${item.departmentName}</td>
				<td>${item.teamManagerName}</td>
				<td>${item.managerName}</td>
				<td>${item.teamName}</td>
				<td>${fns:dictName(dicts.tz_customer_src,item.dictCustSource,'-')}</td>
				<td>${fns:dictName(dicts.tz_lenderchg_state,item.dictChangeState,'-')}</td>
				<td>${fns:getFormatDate(item.createTime,"yyyy-MM-dd HH:mm:ss")}</td>
				<td>${fns:getFormatDate(item.dictApprovalStartDate,"yyyy-MM-dd HH:mm:ss")}</td>
			    <td>
			         <!-- <a href="${ctx}/lenderChange/openFinish?applyId=${item.applyId}">详细</a> -->
			         <auth:hasPermission key="cf:lenderchanglook:transact">
			         	<a href="${ctx}/bpm/flowController/openForm?applyId=${item.applyId}&dealType=1&managerId=${item.managerId}">详细</a>
			         </auth:hasPermission>
				</td>
		
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>