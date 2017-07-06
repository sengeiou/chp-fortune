<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<meta name="decorator" content="default"/>
<title>可用债权配置列表</title>
<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/obligatory/obligatory.js"></script>
 
 
</head>
<body>
<div class="body_new">
	<form id="searchForm" action="${ctx}/creditLocation/loadCreditLocationList" method="post">
		<div class="box1 mb10">
	        <input id="pageNo" name="pageNo" type="hidden" value="${olv.page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${olv.page.pageSize}"/>
	        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
	            <tr>
	                <td><label class="lab">借款人：</label><input id="ln" type="text" class="cf_input_text178" name="configLoanName" value="${olv.coo.configLoanName}"></td>
	                <%-- <td><label class="lab">借款人：</label><input id="ln" type="text" class="cf_input_text178" name="configLoanName" value="${olv.coo.configLoanName}"></td> --%>
	                <td><label class="lab">借款人证件号：</label><input id="lc" type="text" class="cf_input_text178" name="configZdr" value="${olv.coo.configZdr}"></td>
	                <%-- <td><label class="lab">借款人证件号：</label><input id="lc" type="text" class="cf_input_text178" name="configZdr" value="${olv.coo.configZdr}"></td> --%>
	                <td><label class="lab">月利率：</label><input type="text" class="cf_input_text178" name="loanMonthRate" value="${olv.loanMonthRate}" number='1' greaterThan="0"></td>
	            </tr>
	            <tr>
	            	<td> 
					    <label class="lab">还款日：</label>
						<select name="loanBackmoneyDay" class="select78 mr34" multiple="multiple">
                            <c:forEach items="${dicts.tz_repay_day }" var="item">
                               <option value="${item.value }" <c:if test="${fns:multiOption(olv.loanBackmoneyDay,item.value)}">selected</c:if>>${item.label }</option>
                            </c:forEach>
                        </select>
                     </td>
                     <td>
	                <label class="lab">账单类型：</label>
	                <select class="select78 mr34" multiple="multiple" name="configType">
	          			<c:forEach items="${dicts.tz_bill_state}" var="item">
	                		<option value="${item.value }" 
	                		<c:if test="${fns:multiOption(olv.coo.configType,item.value)}">selected</c:if>>${item.label }</option>
	                    </c:forEach>
	                </select>
                </td>
	            </tr>
	        </table>
	        <div class="tright pr30">
	             <input type="submit" class="btn cf_btn-primary" value="搜索" onclick="javascript:window.searchForm.submit();"/>
	             <input type="reset" value="清除"  class="btn cf_btn-primary"/></div>
		 </div>
    </form>
    <p class="mb10">
    	<auth:hasPermission key="cf:borrowconfig:add">
		    <input type="button" class="btn btn_sc ml10" value="增加"  onclick="window.location.href='${ctx}/creditLocation/goAdd'"/>
		</auth:hasPermission>
		<auth:hasPermission key="cf:borrowconfig:del">
		    <input type="button" value="删除" class="btn btn_sc" onclick="javascript:optDelete()"/>
		</auth:hasPermission>
    </p> 
    <div class='box5'>
    <form id="creditList">
    <table class="table table-striped table-bordered table-condensed data-list-table" width="100%">
        <thead>
        <tr>
			<th><span>
	    			<input type="checkbox" id="checkAll" class="checkAll">
	   		</span></th>
            <th>序号</th>
            <th>借款人</th>
            <th>借款人证件号</th>
            <th>操作</th>
        </tr>
        </thead>
        <c:forEach items="${olv.page.list}" var="item" varStatus="index" >
        <tr>
			<td><input type="checkbox" value="${item.id}" name="ids"/></td>
            <td>${index.count}</td>
            <!-- 屏蔽客户姓名/手机号/身份证号 -->
            <td>***</td>
            <%-- <td>${item.configLoanName}</td> --%>
            <td>***</td>
            <%-- <td>${item.configZdr}</td> --%>
            <td>
            	<auth:hasPermission key="cf:borrowconfig:transact">
            		<input type="button" class="cf_btn_edit" value="办理" onclick="window.location.href='${ctx}/creditLocation/goEdit?loanCode=${item.id}'"/>
            	</auth:hasPermission>
            </td>
        </tr>
        </c:forEach>
    </table>
    </div>
   	</form>
	<div class="pagination">${olv.page}</div>
</body>
</html>
