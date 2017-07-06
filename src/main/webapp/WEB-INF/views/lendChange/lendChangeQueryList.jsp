<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借信息变更查询列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxWebInf}/js/lendChange/lendChangeSearch.js"></script>
	<script type="text/javascript" src="${ctxWebInf}/js/lendChange/lendChangeQueryList.js"></script>
</head>
<body>
<div class="body_r">
    <div class="box1 mb10">
    <form:form id="searchForm" modelAttribute="queryView" action="${ctx}/lendChangeFinish/queryList" method="post" >
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
       <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab">客户编号：</label>
                	<input type="text" name="custCode" value="${queryView.custCode}"  class="cf_input_text178" />
                </td>
                <td>
                	<label class="lab">客户姓名：</label>
                	<input type="text" name="custName" value="${queryView.custName}"  class="cf_input_text178" />
                </td>
                <td>
                <label class="lab">托管状态：</label>
                	<select class="select78" name="applyHostedState">
                	    <option value="">请选择</option>
                      <c:forEach items="${dicts.tz_trust_state}" var ="d" >
                	    <option value="${d.value }" <c:if test="${d.value eq queryView.applyHostedState}">
									selected
								</c:if>>${d.label }</option>
                      </c:forEach>
                    </select>
                	<!-- <label class="lab">客户状态：</label>
					<select class="select78" name="custState">
				     <option value="">请选择</option>
                      <c:forEach items="${fns:getDictList('tz_customer_state')}" var ="d" >
                	    <option value="${d.value }" <c:if test="${d.value eq queryView.custState}">
									selected
								</c:if> >${d.label }</option>
                      </c:forEach>
                     <select>   -->
                </td>
            </tr>
            <tr>
                <td>
                	<label class="lab">出借编号：</label>
				 	<input type="text" class="cf_input_text178" id="lendCode" name="lendCode" value="${queryView.lendCode}">
<!--                 	<label class="lab">联系电话：</label> -->
<%--                 	<input type="text" name="custMobilephone" value="${queryView.custMobilephone}" onkeyup="this.value=this.value.replace(/\D/g,'')"  class="cf_input_text178" /> --%>
                </td>
                <td>
                	<label class="lab">理财经理：</label>
                	<input type="text" name="managerName" value="${queryView.managerName}"  class="cf_input_text178" />
                <td>
                	<label class="lab">营业部：</label>
                	<sys:orgTree id="org"  name="orgCode" value="${queryView.orgCode}" labelName="orgName"  labelValue="${queryView.orgName}" />
                </td>
            </tr>
            <tr id="T1" style="display:none;">
                <td>
                	<label class="lab">城      市：</label>
                	<sys:cityselect name="addId" value="${queryView.addId}" multiple="true" />
                <td>
                	<label class="lab">审核通过日期：</label>
			    	<input type="text" name="dictApprovalStartDate" id="dictApprovalStartDate" 
			    		value="${fns:getFormatDate(queryView.dictApprovalStartDate,'yyyy-MM-dd')}" 
			    		class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'dictApprovalEndDate\',{d:-1});}'})"> -
			    	<input type="text" name="dictApprovalEndDate" id="dictApprovalEndDate" 
			    		value="${fns:getFormatDate(queryView.dictApprovalEndDate,'yyyy-MM-dd')}" 
			    		class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'dictApprovalStartDate\',{});}'})">
                </td>	
				<td>
				<label class="lab">变更状态：</label>
                	<select class="select78" name="dictChangeState">
                	    <option value="">请选择</option>
                      <c:forEach items="${dicts.tz_lenderchg_state}" var ="d" >
                	    <option value="${d.value }" <c:if test="${d.value eq queryView.dictChangeState}">
									selected
								</c:if> >${d.label }</option>
                      </c:forEach>
                    <select>
                </td>
			</tr>
<!-- 			<tr id="T2" style="display:none;"> -->
<!-- 				 <td><label class="lab">出借编号：</label> -->
<%-- 				 	 <input type="text" class="cf_input_text178" id="lendCode" name="lendCode" value="${queryView.lendCode}"> --%>
<!-- 				 </td> -->
<!-- 			</tr>	 -->
			</table>
			<div class="tright pr30 pb5 pt10 pb5" >
				<input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="搜索" />
			    <input id="btnreset" class="btn cf_btn-primary" type="reset" value="清除" />
			    <div class="xiala" id="showMore"  onclick="javascript:show();"></div>	
            </div>
            </div>
            </form:form>
    <sys:message content="${message}"></sys:message>
    <div class='box5'>
    <p class="mb10">
    	<auth:hasPermission key="cf:lendchanglook:exportexcel">
			<button class="btn btn_sc ml10" id="expExcel">导出excel</button>
		</auth:hasPermission>
	</p>
    <table id="lendTable" class="table table-striped table-bordered table-condensed data-list-table">
        <thead>
        <tr>
            <th><input type="checkbox" class="ml10 mr10 checkAll" id="checkAll">全选</th>
            <th>客户姓名</th>
            <th>客户编号</th>
            <th>出借编号</th>
            <th>省份</th>
            <th>城市</th>
            <th>营业部</th>
            <th>团队经理</th>
            <th>理财经理</th>
            <th>开发团队</th>
            <th>托管状态</th>
			<th>出借状态</th>
            <th>客户状态</th>
            <th>客户来源</th>
            <th>变更状态</th>
            <th>创建日期</th>
            <th>审核通过日期</th>
            <th>操作</th>
        </tr>
        </thead>
        <c:forEach items="${page.list}" var="item">
            <tr>
                <td><input type="checkbox" value="${item.applyId}" ></td>
                <!-- 屏蔽客户姓名/手机号/身份证号 -->
				<td>***</td>
				<%-- <td>${item.custName}</td> --%>
				<td>${item.custCode}</td>
				<td>${item.applyCode}</td>
				<td>${fns:getProvinceLabel(item.addrProvince)}</td>
				<td>${fns:getCityLabel(item.addrCity)}</td>
				<td>${item.departmentName}</td>
				<td>${item.teamManagerName}</td>
				<td>${item.managerName}</td>
				<td>${item.teamName}</td>
				<td>${fns:dictName(dicts.tz_trust_state,item.applyHostedState,'-')}</td>
				<td>${fns:dictName(dicts.tz_lend_state,item.applyState,'-')}</td>
				<td>${fns:dictName(dicts.tz_customer_state,item.custState,'-')}</td>
				<td>${fns:dictName(dicts.tz_customer_src,item.dictCustSource,'-')}</td>
				<td>${fns:dictName(dicts.tz_lenderchg_state,item.dictChangeState,'-')}</td>
				<td>${fns:getFormatDate(item.createTime,"yyyy-MM-dd HH:mm:ss")} </td>
				<td>${fns:getFormatDate(item.dictApprovalStartDate,"yyyy-MM-dd HH:mm:ss")}</td>
			    <td>
			        <!-- <a href="${ctx}/lendChange/openFinish?applyId=${item.applyId}">详细</a> -->
			        <auth:hasPermission key="cf:lendchanglook:transact">
			        	<a href="${ctx}/bpm/flowController/openForm?applyId=${item.applyId}&dealType=1&managerName=${item.managerName}">详细</a>
			        </auth:hasPermission>
			    </td>
		
			</tr>
        </c:forEach>
    </table>
    </div>
    </div>
   <div class="pagination">${page}</div>
</body>
</html>