<%@ page contentType="text/html;charset=UTF-8" %> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>我的客户列表</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
	<script src="${ctxWebInf}/js/customer/customerTransfer.js" type="text/javascript"></script>
	<script src="${ctxWebInf}/js/customer/customerList.js" type="text/javascript"></script>
	<script src="${ctxWebInf}/js/customer/uploadexcel.js" type="text/javascript"></script>
</head>
<body>
	<div class="body_r">
	<div class="box1 mb10">
	<form id="searchForm" action="${ctx}/customer/investment/list?menuId=${menuId}" method="post" >
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="menuId" name="menuId" type="hidden" value="${param.menuId}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<sys:message content="${message}"/>
		 <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
			    <td>
					<label class='lab'>客户姓名：</label>
					<input type="text" name="custName" maxlength="50" class="cf_input_text178" value="${customer.custName }"/>
			    </td>
			    <td>
					<label class='lab'>客户编号：</label>
					<input type="text" name="custCode" maxlength="50" class="cf_input_text178" value="${customer.custCode }"/>
				</td>
				<td>
					<label class='lab'>联系电话：</label>
					<input type="text" name="custMobilephone" maxlength="11" class="cf_input_text178" value="${customer.custMobilephone }"/>
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
					<select id="custState" name="custState" class="select78">
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
						<c:forEach items="${dicts.tz_invest_state}" var="item">
							<option value="${item.value }" <c:if test="${customer.custLending==item.value }">selected</c:if>>${item.label }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<label class='lab'>理财经理名字：</label>
					<input type="text" name="managerName" maxlength="50" class="cf_input_text178" value="${customer.managerName }"/>
				</td>
				<td>
					<label class='lab'>城市：</label>
					<sys:cityselect name="addrCity" value="${customer.addrCity}" multiple="true" />
				</td>
				<td>
				</td>
			</tr>
			</table>
			<div class="tright pr30 pb5 pt10">
			    <input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="搜索" />
			    <input id="btnSubmit" class="btn cf_btn-primary" type="reset" value="清除" />
			</div>
	</form>
	</div>
	<!-- 增加修改在职状态的按钮和上传excel 按钮   -->
	<auth:hasPermission key="cf:customer:uploadexcel">
	   <input type="button" id="uploadexcel" class="btn btn_sc ml10 mb10" value="上传EXCEL"  onclick="javascript:uploadexcel();" />
	</auth:hasPermission>
	<auth:hasPermission key="cf:customer:updateWorkingState">
	   <input type="button" id = "updateWorkingState"  class="btn btn_sc ml10 mb10" value="修改在职状态" onclick="javascript:updateWorkinfState();" />
	</auth:hasPermission>
	
	<auth:hasPermission key="cf:customer:add">
	   <input class='btn btn_sc ml10 mb10' type="button" id="addCustomer" value="新增客户" onclick="goPage('${ctx}/customer/investment/goAdd')">
	</auth:hasPermission>	
	<auth:hasPermission key="cf:customer:investmentdetail">
   		<input type="button" id = "exportCustomerDetail"  class="btn btn_sc ml10 mb10" value="导出明细"/>
   	</auth:hasPermission>
	<sys:message content="${content }"></sys:message>
	<sys:columnCtrl pageToken="customer_customerList"></sys:columnCtrl>
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
<!-- 				<th>电销理财专员</th> -->
				<th>开发团队</th>
				<th>客户来源</th>
				<th>创建日期</th>
				<th>数据来源</th>
				<th>开户状态</th>
				<th>投资状态</th>
				<th>转赠状态</th>
				<!-- <th>预计进入公共池时间</th> -->
				<th>操作</th>
			</tr>
		</thead>
		<c:forEach items="${page.list}" var="item" varStatus="sta">
			<tr>
				<!-- 屏蔽客户姓名/手机号/身份证号 -->
				<%-- <td>${item.custName}</td> --%>
				<td>***</td>
				<td>					
					<c:choose>
						<c:when test="${not empty item.oldCustomerCode }">
							${item.oldCustomerCode}
							<c:set value="${item.oldCustomerCode }" var="customerCode"></c:set>
						</c:when>
						<c:otherwise>
							${item.custCode}
							<c:set value="${item.custCode }" var="customerCode"></c:set>
						</c:otherwise>
					</c:choose>
				</td>
				<td>${fns:getProvinceLabel(item.provinceId) }</td>
				<td>${fns:getCityLabel(item.cityId) }</td>
				<td>${item.storesName }</td>
				<td>${item.teamManagerName }</td>
				<td>${item.managerName }</td>
<%-- 				<td>${item.teleManagerName }</td> --%>
				<td>${item.teamName }</td>
				<td>
					${fns:dictName(dicts.tz_customer_src,item.dictCustSource,'-') }
				</td>
				<td>${fns:getFormatDate(item.createTime ,'yyyy-MM-dd')}</td>
				<td>
					${fns:dictName(dicts.tz_data_source,item.dataTerminal,'-') }
				</td>
				<td>
					${fns:dictName(dicts.tz_customer_state,item.custState,'-') }
				</td>
				<td>
					${fns:dictName(dicts.tz_invest_state,item.custLending,'-') }
				</td>
				<td>${fns:dictName(dicts.tz_transfer_state,item.transferState,'-') }</td>
				<%-- <td>${fns:getFormatDate(item.preIntoPoolTime ,'yyyy-MM-dd')}</td> --%>
				<td>
					<c:if test="${item.transferState!=1}">
						<input type="button"   class='cf_btn_edit' value="办理" onclick="goPageWithParams('${ctx}/customer/investment/handle?custCode=${item.custCode }');">
						<c:if test="${isShow}">
							<input type="button" class='cf_btn_edit' id="customerTransfer" value="转赠" onclick="Totranser('${customerCode}')">
						</c:if>
						<c:if test="${item.custState==7 or item.custState==6}">
							<input type="button" class='cf_btn_edit' id="makingFile" value="合成申请书" onclick="_makingFile(${item.custCode})">
						</c:if>
					</c:if>
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
					<h4 class="modal-title">理财经理信息（注：只有理财经理和工号对应上才能查询出结果）</h4>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
					<input type="button" value="关闭" class="btn cf_btn-primary" id="subClose"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>