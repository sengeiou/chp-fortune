<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf}/js/common/autocomplete.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/delivery/customerDelivery.js" type="text/javascript"></script>
<title>客户交割</title>
</head>
<body>
<div class="body_top body_r">
  <div class="box1 mb10"> 
  <form:form id="searchForm" modelAttribute="deliveryEx" action="${ctx}/delivery/customer/list" method="post">
    <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<input type="hidden" id="id" name="id"/>
                <td style=""><label class="lab" style="">客户姓名：</label><input type="text" class="cf_input_text178" id="custName" name="custName" value="${deliveryEx.custName}"/></td>
                <td style=""><label class="lab" style="">客户编号：</label><input type="text" class="cf_input_text178" id="custCode" name="custCode" value="${deliveryEx.custCode}"/></td>
                <td style=""><label class="lab" style="">理财经理工号：</label><input type="text" class="cf_input_text178" id="fManagerCode" name="fManagerCode" value="${deliveryEx.fManagerCode}"/></td>
            </tr>
            <tr>
                <td style="width:33%;"><label class="lab">理财经理：</label><input type="text" class="cf_input_text178" id="fManagerName" name="fManagerName" value="${deliveryEx.fManagerName}"/></td>
                <td><label class="lab">团队经理工号：</label><input type="text" class="cf_input_text178" id="teamManagerCode" name="teamManagerCode" value="${deliveryEx.teamManagerCode}"/></td>
                <td><label class="lab">团队经理：</label><input type="text" class="cf_input_text178" id="teamManagerName" name="teamManagerName" value="${deliveryEx.teamManagerName}"/></td>
            </tr>
			<tr id="T1" style="display:none">
                <td style="width:33%;"><label class="lab">操作人工号：</label><input type="text" class="cf_input_text178" id="createBy" name="createBy" value="${deliveryEx.createBy}"/></td>
                <td><label class="lab">操作人：</label><input type="text" class="cf_input_text178" id="createByName" name="createByName" value="${deliveryEx.createByName}"/></td>
                <td><label class="lab">营业部：</label>
                	 <sys:orgTree id="orgCode" name="orgCode" value="${deliveryEx.orgCode}" labelName="orgName" labelValue="${deliveryEx.orgName}" />
                	 </td>
            </tr>
            <tr id="T2" style="display:none">
                <td><label class="lab">交割时间：</label>
                <input type="text" class="Wdate input_text70" id="startTime" name="startTime" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}'})"   value='<fmt:formatDate value="${deliveryEx.startTime }" pattern="yyyy-MM-dd"/>'/> -
                <input type="text" class="Wdate input_text70" id="endTime" name="endTime"   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" value='<fmt:formatDate value="${deliveryEx.endTime }" pattern="yyyy-MM-dd"/>'/>
                </td>
            </tr>
        </table>
        <div class="tright pr30 pb5 pt10">
                <input type="submit" id = "search" class="btn cf_btn-primary" value="搜索" />
                <input type="reset" id = "reset" class="btn cf_btn-primary" value="清除"/>
                <div class="xiala" id="showMore" onclick="javascript:show();"></div>
         </div>
      </form:form>
    </div>
		<div class="mb10">
    	     <button class="btn btn_sc ml10" id="delivery">批量客户交割</button>
    	     <button class="btn btn_sc" id="outExcel">导出Excel</button>
    	     <button class="btn btn_sc" id="upload">上传</button>
    	     <button class="btn btn_sc" id="loadManagerInfo">自动带出</button>&nbsp;&nbsp;总笔数：${page.count}条
        </div>
        
        <form:form id="form">
        <div class="box5" style="overflow-x: scroll;padding-right:10px;height:100%;">
            <table  id="contentTable" class="table table-striped table-bordered table-condensed data-list-table">
              <thead>
                <tr>
                  <th><input type="checkbox" id="checkAll" class="checkAll"/></th>
                  <th>客户姓名</th>
                  <th>客户编号</th>
                  <th>理财经理工号</th>
                  <th>理财经理</th>
                  <th>团队经理工号</th>
                  <th>团队经理</th>
                  <th>营业部</th>
                  <th>理财经理工号（新）</th>
                  <th>理财经理（新）</th>
                  <th>团队经理工号（新）</th>
                  <th>团队经理（新）</th>
                  <th>营业部（新）</th>
                  <th>操作工人号</th>
                  <th>交割时间</th>
                  <th>交割状态</th>
			      <th>生效时间</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${page.list}" var="item" varStatus="status">
                  <tr>
                    <td><input type="checkbox" name="checkbox" value="${status.count},${item.custCode},${item.fManagerId},${item.delDate}"/></td>
                    <!-- 屏蔽客户姓名/手机号/身份证号 -->
                    <td>***</td>
                    <%-- <td>${item.custName}</td> --%>
                    <td>${item.custCode}</td>
                    <td>
                        ${item.fManagerCode}
                        <input type="hidden" id="fManagerId${status.count}" value="${item.fManagerId}"/>
                    </td>
                    <td>${item.fManagerName}</td>
                    <td>${item.teamManagerCode}</td>
                    <td>${item.teamManagerName}</td>
                    <td>${item.orgName}</td>
                    <td>
                        <span class='red'>*</span>
                        <input type="text" style="width:70px" class="autoComplete select78_24" id="nfManagerCode${status.count}" onkeyup="getCount('${status.count}');" ondblclick="getCount('${status.count}');" value="${item.nfManagerCode}" required>
                        <input type="hidden" id="nfManagerId${status.count}" value="${item.nfManagerId}"/>
                    </td>
                    <td><input type="text" style="width:70px" class="select78_24" id="nfManagerName${status.count}" value="${item.nfManagerName}"></td>
                    <td><input type="text" style="width:70px" class="select78_24" id="nTeamManagerCode${status.count}" value="${item.nTeamManagerCode}"></td>
                    <td><input type="text" style="width:70px" class="select78_24" id="nTeamManagerName${status.count}" value="${item.nTeamManagerName}"></td>
                    <td><input type="text" style="width:70px" class="select78_24" id="nOrgName${status.count}" value="${item.nOrgName}"></td>
                    <td>${item.createBy}</td>
                    <td>
                        <fmt:formatDate value="${item.delDate}" pattern="yyyy-MM-dd HH:mm"/>
                        <input type="hidden" id="delId${status.count}" value="${item.delId}"/>
                    </td>
                    <td>${fns:getDictLabel(item.dictDelStatus, 'tz_delivery_status', '-')}</td>
                    <td><input type="text" class="Wdate select78_24" id="delDate${status.count}" onfocus="WdatePicker({minDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
                    <td>
                        <input type="button" class="cf_btn_edit" id="delivery${status.count}" value="交割" onclick="customerDelivery('${status.count}','${item.custCode}','${item.fManagerId}','${item.delDate}');"/>
                        <a href="${ctx}/delivery/achievement/list?custCode=${item.custCode}&custName=${item.custName}&fManagerId=${item.fManagerId}"><input type="button" class="cf_btn_edit" value="办理" /></a>
                    </td> 
                  </tr>   
                </c:forEach>
              </tbody>
            </table>
        </div>
        </form:form>
        <div class="pagination">${page}</div>
</div>
   
	<div id="modal-sub" class="modal fade subwindow">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">文件上传</h4>
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