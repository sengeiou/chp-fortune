<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf}/js/common/autocomplete.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/delivery/tripleCustomerDelivery.js" type="text/javascript"></script>
<title>三网客户交割</title>
</head>
<body>
<div class="body_top body_r">
  <div class="box1 mb10"> 
  <form:form id="searchForm" modelAttribute="intDeliveryEx" action="${ctx}/delivery/tripleCustomer/list" method="post">
    <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				
                <td style=""><label class="lab" style="">客户编号：</label><input type="text" class="cf_input_text178" id="customerCode" name="customerCode" value="${intDeliveryEx.customerCode}"/></td>
                <td style=""><label class="lab" style="">客户姓名：</label><input type="text" class="cf_input_text178" id="loginName" name="loginName" value="${intDeliveryEx.loginName}"/></td>
                <td style=""><label class="lab" style="">理财经理工号：</label><input type="text" class="cf_input_text178" id="empCode" name="empCode" value="${intDeliveryEx.empCode}"/></td>
            </tr>
            <tr>
                <td style="width:33%;"><label class="lab">理财经理：</label><input type="text" class="cf_input_text178" id="empName" name="empName" value="${intDeliveryEx.empName}"/></td>
                 <td><label class="lab">营业部：</label>
                	 <sys:orgTree id="org" name="orgId" value="${intDeliveryEx.orgId}" labelName="orgName" labelValue="${intDeliveryEx.orgName}" />
                 </td>
                 <td>
                    <label class="lab">客户平台：</label>
                    <select class="select78" id="osType" name="osType" multiple="true">
                        <c:forEach items="${dicts.tz_os_type}" var="item">
                             <option value="${item.value }" <c:if test="${intDeliveryEx.osType==item.value}">selected</c:if>>${item.label }</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <div class="tright pr30">
                <input type="submit" id = "search" class="btn cf_btn-primary" value="搜索" />
                <input type="reset" id = "reset" class="btn cf_btn-primary" value="清除"/>
         </div>
      </form:form>
    </div>
		<div class="mb10">
			<auth:hasPermission key="cf:customerdelivery:batchcustomerdelivery">
	    	    <button class="btn btn_sc ml10" id="delivery">批量客户交割</button>&nbsp;
	    	</auth:hasPermission>
	    	<auth:hasPermission key="cf:customerdelivery:exportexcel">
	    	    <button class="btn btn_sc" id="outExcel" type="button">导出Excel</button>&nbsp;
	    	</auth:hasPermission>
	    	<auth:hasPermission key="cf:customerdelivery:uploaddelivery">
	    	    <button class="btn btn_sc" id="upload">上传交割</button>&nbsp;
	    	</auth:hasPermission>
	    	&nbsp;&nbsp;总笔数：${page.count}条
        </div>
        
        <form:form id="form">
        <div class="box5" style="padding-right:10px;height:100%;">
            <table  id="tableList" class="table table-striped table-bordered table-condensed data-list-table">
              <thead>
                <tr>
                  <th><input type="checkbox" id="checkAll" class=" checkAll"/></th>
                  <th>客户编号</th>
                  <th>客户姓名</th>
                  <th>理财经理工号</th>
                  <th>理财经理</th>
                  <th>营业部</th>
                  <th>系统类型</th>
                  <th>成单状态</th>
                  <th>理财经理工号（新）</th>
                  <th>理财经理（新）</th>
                  <th>团队经理工号（新）</th>
                  <th>团队经理（新）</th>
                  <th>营业部（新）</th>
                  <th>交割时间</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${page.list}" var="item" varStatus="status">
                  <tr>
                    <td><input type="checkbox" name="checkbox" value="${status.count},${item.orderStatus},${item.phone},${item.empCode},${item.loginName},${item.osType},${item.cardType},${item.cardId},${item.customerCode}"/></td>
                    <td>${item.customerCode}</td>
                    <!-- 屏蔽客户姓名/手机号/身份证号 -->
                    <td>***</td>
                    <%-- <td>${item.loginName}</td> --%>
                    <td class="empCode${status.count}">${item.empCode}</td>
                    <td class="empName${status.count}">${item.empName}</td>
                    <td class="orgName${status.count}">${item.orgName}</td>
                    <td>${fns:dictName(dicts.tz_os_type,item.osType,'*')}</td>
                    <td>${fns:dictName(dicts.tz_order_status,item.orderStatus,'未成单')}</td>
                    <td>
                        <span class='red'>*</span>
                        <input type="text" style="width:70px" class="autoComplete select78_24 newEmpCode" id="newEmpCode${status.count}" onkeyup="getCount('${status.count}');" ondblclick="getCount('${status.count}');" value="${item.newEmpCode}" required>
                    </td>
                    <td><input type="text" style="width:70px" class="select78_24 newEmpName" id="newEmpName${status.count}" value="${item.newEmpName}" readonly=""></td>
                    <td><input type="text" style="width:70px" class="select78_24 newTeamManagerCode" id="newTeamManagerCode${status.count}" value="${item.newTeamManagerCode}" readonly=""></td>
                    <td><input type="text" style="width:70px" class="select78_24 newTeamMangerName" id="newTeamManagerName${status.count}" value="${item.newTeamManagerName}" readonly=""></td>
                    <td><input type="text" style="width:70px" class="select78_24 newOrgName" id="newOrgName${status.count}" value="${item.newOrgName}" readonly=""></td>
                    <td class="delDate${status.count}">${fns:getFormatDate(item.delDate,'yyyy-MM-dd HH:mm')}</td>
                    <td>
                    	<auth:hasPermission key="cf:customerdelivery:transact">
                        	<input type="button" class="cf_btn_edit" value="业绩交割" onclick="goPage('${item.customerCode}','${item.delDate}','${item.loginName}','${item.empCode}','${item.phone}');"/>
                        </auth:hasPermission>
                    </td> 
                  </tr>   
                </c:forEach>
              </tbody>
            </table>
        </div>
        </form:form>
        <div class="pagination">${page}</div>
</div>
   
    <script type="text/javascript">
			$("#tableList").wrap('<div id="content-body"><div id="reportTableDiv"></div></div>');
			$("#tableList").bootstrapTable({
			method: 'get',
			cache: false,
			height: $(window).height()-220,
			
			pageSize: 20,
			pageNumber:1
			});
			$(window).resize(function () {
			$("#tableList").bootstrapTable('resetView');
			});
		</script> 
		
		
	<div id="modal-sub" class="modal fade subwindow">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">交割文件上传</h4>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn cf_btn-primary" id="subClose">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>
