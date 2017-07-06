<%@ page contentType="text/html;charset=UTF-8" %> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>出借列表</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<div class="body_r">
	    <div class="box1 mb10">
	<form id="searchForm" action="${ctx}/lend/apply/list?menuId=${menuId}" method="post" >
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		
		 <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
			    <td>
					<label class='lab'>客户姓名：</label>
					<input type="text" name="custName" class="cf_input_text178" value="${customer.custName }"/>
			    </td>
			    <td>
					<label class='lab'>客户编号：</label>
					<input type="text" name="custCode" class="cf_input_text178" value="${customer.custCode }"/>
				</td>
				<td>
					<label class='lab'>联系电话：</label>
					<input type="text" name="custMobilephone" class="cf_input_text178" value='${customer.custMobilephone }'>
				</td>
			</tr>
			<tr>
				<td>
					<label class='lab'>客户来源：</label>
					<select id="dictCustSource" name="dictCustSource" class="select78">
						<option value="">请选择</option>
						<c:forEach items="${dicts.tz_customer_src }" var="item">
							<option value="${item.value }" <c:if test="${customer.dictCustSource==item.value }">selected</c:if>>${item.label }</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<label class='lab'>开户状态：</label>
					<select id="custState" name="custState" class="select78" disabled="disabled">
						<option value="">请选择</option>
						<c:forEach items="${dicts.tz_customer_state }" var="item">
							<option value="${item.value }" <c:if test="${customer.custState==item.value }">selected</c:if>>${item.label }</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<label class='lab'>投资状态：</label>
					<select id="custLending" name="custLending" class="select78">
						<option value="">请选择</option>
						<c:forEach items="${dicts.tz_invest_state }" var="item">
							<option value="${item.value }" <c:if test="${customer.custLending==item.value }">selected</c:if>>${item.label }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr id='T1' style='display:none;'>
				<td>
					<label class='lab'>营业部：</label>
<%-- 					<input type="text" name="storesName" class="cf_input_text178" value="${customer.storesName}"/> --%>
					<sys:orgTree id="org"  name="storesId" value="${customer.storesId}" labelName="storesName"  labelValue="${customer.storesName}" />
				</td>
				<td>
					<label class='lab'>托管状态：</label>
					<select id="applyHostedStatus" name="applyHostedStatus" class="select78">
						<option value="">请选择</option>
						<c:forEach items="${dicts.tz_trust_state }" var="item">
							<option value="${item.value }" <c:if test="${customer.applyHostedStatus==item.value }">selected</c:if>>${item.label }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			</table>
			<div class="tright pr30 pt10 pb5">
			    <input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="搜索" />
			    <input id="btnSubmit" class="btn cf_btn-primary" type="reset" value="清除" />
			     <div class="xiala"  id="showMore" onclick="javascript:show();"></div>	
			</div>
			
	</form>
	</div>
	<sys:message content="${content }"></sys:message>
	<sys:columnCtrl pageToken="customer_workflow_lendApplyList"></sys:columnCtrl>
	<div class='box5'>
	<table id="contentTable" class="table table-striped table-bordered table-condensed data-list-table">
		<thead>
		<tr>
			<th>客户姓名</th>
			<th>客户编号</th>
			<th>省份</th>
			<th>城市</th>
			<th>营业部</th>
			<th>团队经理</th>
			<th>理财经理</th>
			<th>开发团队</th>
			<th>客户来源</th>
			<th>创建日期</th>
			<th>数据来源</th>
			<th>开户状态</th>
			<th>投资状态</th>
			<th>托管状态</th>
			<th>操作</th>
		</tr>
		</thead>
		<c:forEach items="${page.list}" var="item">
			<tr>
				<!-- 屏蔽客户姓名/手机号/身份证号 -->
				<td>***</td>
				<%-- <td>${item.custName}</td> --%>
				<td>${item.custCode}</td>
				<td>${fns:getProvinceLabel(item.provinceId) }</td>
				<td>${fns:getCityLabel(item.cityId) }</td>
				<td>${item.storesName }</td>
				<td>${item.teamManagerName }</td>
				<td>${item.managerName }</td>
				<td>${item.teamName }</td>
				<td>${fns:dictName(dicts.tz_customer_src,item.dictCustSource,'-') }
				</td>
				<td>${fns:getFormatDate(item.createTime ,'yyyy-MM-dd')}</td>
				<td>${fns:dictName(dicts.tz_data_source,item.dataTerminal,'-') }</td>
				<td>${fns:dictName(dicts.tz_customer_state,item.custState,'-') }</td>
				<td>
					${fns:dictName(dicts.tz_invest_state,item.custLending,'-') }
				</td>
				<td>
					${fns:dictName(dicts.tz_trust_state,item.applyHostedStatus,'-') }
				</td>
				<td>
					<input type="button" value="办理" class='cf_btn_edit' onclick="goPage('${ctx}/bpm/flowController/openLaunchForm?flowCode=lendApply&customerCode=${item.custCode }');">
				</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>