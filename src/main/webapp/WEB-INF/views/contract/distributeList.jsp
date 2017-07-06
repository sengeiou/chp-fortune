<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<style type="text/css">
.form-search .ul-form li label{
width:130px;
}
</style>
<meta name="decorator" content="default"/>
<title>合同派发列表</title>
<script src="${ctxWebInf}/js/contract/distributeList.js" type="text/javascript" ></script>	
<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
</head>
<body class="body_r">
      <div class='box1 mb10'>
    	<form:form id="searchForm" modelAttribute="contractDistribute" action="${ctx}/contract/distributeList" method="post" >
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
	    <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
           <tr>
        	 <td>
        		<label class='lab'>营业部：</label>
        		<input type="text" name="orgName" id="orgName" class="cf_input_text178" value="${contractDistribute.orgName}" <c:if test="${contractDistribute.staff == '1'}">readonly="value"</c:if>/>
        	 </td>
        	 <td>
        		<label class='lab'>合同起始编号：</label>
        		<input type="text" name="distStartNo" id="distStartNo" class="cf_input_text178" value="${contractDistribute.distStartNo}"/>
        	 </td>
        	 <td>
        		<label class='lab'>合同版本：</label>
			    <select class="select78" id="contVersion" name="contVersion" value="${contractDistribute.contVersion}">
				    <option value="">请选择</option>
					<c:forEach items="${fns:getDictList('tz_contract_vesion')}" var="item">
						 <option value="${item.value}" <c:if test="${contractDistribute.contVersion==item.value}">selected</c:if>>${item.label}</option>
					</c:forEach>
				</select>
        	 </td>
           </tr>
           <tr>
            <td>
            <label class='lab'>物流编号：</label>
        		<input type="text" name="distExpressNo" id="distExpressNo" class="cf_input_text178" value="${contractDistribute.distExpressNo}"/>
            </td>
            <td>
            <label class='lab'>派发状态：</label>
				<select class="select78" id="distStatus" name="distStatus" value="${contractDistribute.distStatus}">
				    <option value="">请选择</option>
					<c:forEach items="${fns:getDictList('tz_dispath_status')}" var="item">
						 <option value="${item.value}" <c:if test="${contractDistribute.distStatus==item.value}">selected</c:if>>${item.label}</option>
					</c:forEach>
				</select>
            </td>
            <td>
            </td>
           </tr>
        </table>     		
      </form:form>
           <div class="tright pr30 pt5">
			<input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="搜索" onclick="javascript:window.searchForm.submit();"/>
			<input id="btnSubmit" class="btn cf_btn-primary" type="button" value="清除" opt="clean"/>
			</div>
      </div>
      <sys:message content="${message}"/>
      <div class='mb10'>
	  <input type="button" value="导出Excel" opt="export" class='btn btn_sc ml10'/>
	  <input type="button" value="导入Excel" opt="import" class='btn btn_sc'/>
	  </div>
<!-- 	  <input type="button" value="合同派发" opt="distribute"/> -->
      <table class="table table-striped table-bordered table-condensed data-list-table">
        <thead>
        <tr>
            <th><input type="checkbox" opt="all">全选</th>
            <th>营业部</th>
<!-- 			<th>物流编号</th> -->
<!--             <th>起始编号</th> -->
<!--             <th>终止编号</th> -->
			<th>合同数量（箱）</th>
            <th>合同申请数量（套）</th>
            <th>已派发数量（套）</th>
            <th>合同版本</th>
            <th>申请日期</th>
            <th>派发日期</th>
            <th>派发状态</th>
            <th>签收状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <c:forEach items="${page.list}" var="item">
        	<tr>
	            <td><input type="checkbox"  opt="records" data-id="${item.id}"></td>
	            <td>${item.orgName}</td>
<%-- 	            <td>${item.distExpressNo}</td> --%>
<%-- 	            <td>${item.distStartNo}</td> --%>
<%-- 				<td>${item.distEndNo}</td> --%>
				<td>${item.distBox}</td>
	            <td>${item.applyNo}</td>
	            <td>${item.distContractNo}</td>
	            <td>${fns:dictName(dicts.tz_contract_vesion, item.contVersion, '-')}</td>
	            <td>${fns:getFormatDate(item.applyDay,'yyyy-MM-dd')}</td>
	            <td>${fns:getFormatDate(item.distDate,'yyyy-MM-dd')}</td>
	            <td>${fns:dictName(dicts.tz_dispath_status, item.distStatus, '-')}</td>
	            <td>${fns:dictName(dicts.tz_singn_state, item.signStatus, '-')}</td>
	            <td class="tcenter">
<%-- 	               <input type="button" value="办理" onclick="goPage('${ctx}/contract/distributeContract?id=${item.id}');"> --%>
	               <input type="button" class='cf_btn_edit' value="办理" opt="showDis" data-id="${item.contractId}">
		    	</td>
		    </tr>
	    </c:forEach>
    </table>
	<div class="pagination">${page}</div>
	<form id="upload"  action="${ctx}/contract/distribute/importExcel" enctype="multipart/form-data" method="post">
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
								<button type="button" class="btn cf_btn-primary" id="subClose">提交</button>
							</div>
						</div>
					</div>
				</div>
	</form>
</body>
</html>