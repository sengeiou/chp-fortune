<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借信息变更复审列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxWebInf}/js/lendChange/lendChangeSearch.js"></script>
	<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
</head>
<body>
<div class="body_r">
    <div class="box1 mb10">
    <form:form id="searchForm" modelAttribute="queryView" action="${ctx}/lendChangeReview/fetchTaskItemsReview" method="post" >
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
								</c:if> >${d.label }</option>
                      </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label class="lab">出借编号：</label>
                	<input type="text" name="lendCode" value="${queryView.lendCode}"  class="cf_input_text178" />
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
                	<sys:cityselect name="city" value="${queryView.city}"  />
                </td>
                <td>
                	<label class="lab">付款方式：</label>
                    <select class="select78" name="applyPay"   multiple="multiple">
                      <c:forEach items="${dicts.tz_pay_type}" var ="d" >
                	    <option value="${d.value }" <c:if test="${fns:multiOption(queryView.applyPay, d.value)}">
									selected
								</c:if> >${d.label }</option>
                      </c:forEach>
                    </select>
                    
                 </td>
				<td>
				 <label class="lab">产品类型：</label>
	            	<select class="select78" name="productCode"   multiple="multiple">
	            			<c:forEach items="${fns:productOption()}" var="item">
		                        <option value="${item.productCode }" <c:if test="${fns:multiOption(queryView.productCode, item.productCode)}">selected</c:if>>${item.productName }</option>
		                    </c:forEach>
	            	</select>
	            	
				</td>
			</tr>
			 </table>  
			<div  class="tright pr30 pt10 pb5">
				<input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="搜索" />
			    <input id="btnreset" class="btn cf_btn-primary" type="reset" value="清除"  />
			    <div class="xiala" id="showMore"  onclick="javascript:show();"></div>
		    </div>
		    </form:form>
		    </div>
    <sys:message content="${message}"></sys:message>
    <table id="lendTable" class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th>客户编号</th>
            <th>客户姓名</th>
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
            <th>账单日</th>
            <th>产品</th>
            <th>付款方式</th>
            <th>金账户</th>
            <th>创建日期</th>
            <th>操作</th>
            <shiro:hasPermission name="apply:consult:edit">
					<th>操作</th>
			</shiro:hasPermission>
        </tr>
        </thead>
        <c:forEach items="${itemList}" var="item">
            <tr>
				<td>${item.customerCode}</td>
				<!-- 屏蔽客户姓名/手机号/身份证号 -->
				<td>***</td>
				<%-- <td>${item.customerName}</td> --%>
				<td>${item.lendCode}</td>
				<td>${fns:getProvinceLabel(item.province)}</td>
				<td>${fns:getCityLabel(item.city)}</td>
				<td>${fns:getOrgNameById(item.department)}</td>
				<td>${item.teamManager}</td>
				<td>${item.financing}</td>
				<td>${fns:getOrgNameById(item.team)}</td>
				<td>${fns:dictName(dicts.tz_trust_state,item.applyHostedState,'-')}</td>
				<td>${fns:dictName(dicts.tz_lend_state,item.applyState,'-')}</td>
				<td>${fns:dictName(dicts.tz_customer_state,item.dictCustomerStatus,'-')}</td>
				<td>${fns:dictName(dicts.tz_customer_src,item.customerSource,'-')}</td>
				<td>${item.billDay}</td>
				<td>${item.productName}</td>
				<td>${fns:dictName(dicts.tz_pay_type,item.applyPay,'-')}</td>
				<td>${item.trusteeshipNo==''?'否':'是'}</td>
				<td>${fns:getFormatDate(item.applyDate,'yyyy-MM-dd HH:mm:ss')}</td>
			    <td>
			    	<auth:hasPermission key="cf:lendchangereview:transact">
			        	<a href="${ctx}/bpm/flowController/openForm?applyId=${item.applyId}&wobNum=${item.wobNum}&dealType=0&token=${item.token}">办理</a>
			        </auth:hasPermission>
			        <auth:hasPermission key="cf:lendchangereview:log">
			        	<a href="javascript:void" onclick="failList('${item.applyId }')">留痕</a>
			        </auth:hasPermission>
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
					<h4 class="modal-title"></h4>
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
<script language="javascript">
  
	  function show(){
		if(document.getElementById("T1").style.display=='none'){
			$("#showMore").attr({"src":"images/down.png"}); 
			$("#T1").removeAttr("style");
			$("#T2").removeAttr("style");
		
		}else{
			$("#showMore").attr({"src":"images/up.png"});
			$("#T1").attr("style","display:none;");
			$("#T2").attr("style","display:none;");
		}
	}


</script>
</html>