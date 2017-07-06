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
<title>变更申请列表</title>
<script src="${ctxWebInf}/js/contract/changeApplyList.js" type="text/javascript" ></script>
<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/common/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/contract/searchFormCookie.js"></script>
</head>
<body class="body_r">
    	<form:form id="searchForm" modelAttribute="contractInformation" action="${ctx}/contract/changeApplyList" method="post" >
        <input id="menuId" type="hidden" value="d989c49074044ee2b23dc33d74965749"/>
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
        <div class="box1 mb10">
            <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
        	<td>
        		<label class='lab'>营业部：</label>
        		<sys:orgTree id="org"  name="nameId" value="${contractInformation.nameId}" labelName="name"  labelValue="${contractInformation.name}" />
				
        	</td>
        	<td>
        		<label class='lab'>合同版本：</label>
        		<select class="select78" id="contVersion" name="contVersion" value="${contractInformation.contVersion}">
				    <option value="">请选择</option>
					<c:forEach items="${fns:getDictList('tz_contract_vesion')}" var="item">
						 <option value="${item.value}" <c:if test="${contractInformation.contVersion==item.value}">selected</c:if>>${item.label}</option>
					</c:forEach>
				</select>
        	</td>
        	<td>
        		<label class='lab'>合同编号：</label>
        		<input type="text" name="contCode" id="contCode" class="cf_input_text178" value="${contractInformation.contCode}"/>
        	</td>
            </tr>
            <tr>
        	<td>
        		<label class='lab'>合同状态：</label>
				<select class="select78" id="dictContStatus" name="dictContStatus" value="${contractInformation.dictContStatus}">
				    <option value="">请选择</option>
					<c:forEach items="${fns:getDictList('tz_contract_state')}" var="item">
						 <option value="${item.value}" <c:if test="${contractInformation.dictContStatus==item.value}">selected</c:if>>${item.label}</option>
					</c:forEach>
				</select>
        	</td>
        	<td>
        		<label class='lab'>使用日期：</label>
        		<input type="text" name="contUseDay" id="contUseDay" class="Wdate input_text70" onfocus="WdatePicker()" value="${fns:getFormatDate(contractInformation.contUseDay,'yyyy-MM-dd')}"/> -
        		<input type="text" name="contUseDayEnd" id="contUseDayEnd" class="Wdate input_text70" onfocus="WdatePicker()" value="${fns:getFormatDate(contractInformation.contUseDayEnd,'yyyy-MM-dd')}"/>
        	</td>
        	<td>
        		<label class='lab'>合同归属：</label>
				<select class="select78" id="dictContBelong" name="dictContBelong">
					<option value="">请选择</option>
					<c:forEach items="${fns:getDictList('tz_contract_owner')}" var="item">
						 <option value="${item.value }" <c:if test="${contractInformation.dictContBelong==item.value}">selected</c:if>>${item.label }</option>
					</c:forEach>
				</select>
        	</td>
        </tr>
        <tr id='T1' style='display:none;'>
        	<td>
        		<label class='lab'>归档状态：</label>
        		<form:select id="dictContFileStatus" path="dictContFileStatus" class="select78" value="${contractInformation.dictContFileStatus}">
        		    <form:option value="">请选择</form:option>
					<form:options items="${fns:getDictList('tz_filed_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
        	</td>
        	<td>
        		<label class='lab'>生成日期：</label>
        		<input type="text" name="contApplyDayStart" id="contApplyDayStart" class="Wdate input_text70" onfocus="WdatePicker()" value="${fns:getFormatDate(contractInformation.contApplyDayStart,'yyyy-MM-dd')}"/> -
        		<input type="text" name="contApplyDayEnd" id="contApplyDayEnd" class="Wdate input_text70" onfocus="WdatePicker()" value="${fns:getFormatDate(contractInformation.contApplyDayEnd,'yyyy-MM-dd')}"/>
        	</td>
        	<td>
        		<label class='lab'>合同类型：</label>
        		<select class="select78" id="contractType" name="contractType" value="${contractInformation.contractType}">
				    <option value="">请选择</option>
				    <option value="1" <c:if test="${contractInformation.contractType==1}">selected</c:if>>合同</option>
				    <option value="2" <c:if test="${contractInformation.contractType==2}">selected</c:if>>协议</option>
					<c:forEach items="${fns:getDictList('tz_contract_type')}" var="item">
						 <option value="${item.value}" <c:if test="${contractInformation.contractType==item.value}">selected</c:if>>${item.label}</option>
					</c:forEach>
				</select>
        	</td>
        </tr>
        </table>
	       	<div class="tright pr30 pb5 pt10">
			  <input id="search" class="btn cf_btn-primary" type="submit" value="搜索" onclick="javascript:window.searchForm.submit();" />
			  <input id="btnSubmit" class="btn cf_btn-primary" type="button" value="清除" opt="clean"  />
			  <div class="xiala"  id="showMore" onclick="javascript:show();">	</div>	
			</div>
	   </form:form>	
      </div>

      <sys:message content="${message}"/>
      <p class='mb10 ml10'>
      <auth:hasPermission key="cf:contractlook:exportexcel">
	  	<input type="button" value="导出Excel" opt="export" class='btn btn_sc ml10'>
	  </auth:hasPermission>
	  <auth:hasPermission key="cf:contractlook:intoexcel">
	  	<input type="button" value="导入Excel" opt="import" class='btn btn_sc'>
	  </auth:hasPermission>
	  <!--   <input type="button" value="合同分配" opt="distribute" class='btn btn_sc'>
	  <input type="button" value="合同调回" opt="recall" class='btn btn_sc'>
	  <input type="button" value="合同调拨" opt="transfer" class='btn btn_sc'> -->
	  <span>已勾选数量：<span id="checkCount">0</span></span>
	  
	  </p>
      <table class="table table-striped table-bordered table-condensed data-list-table">
        <thead>
        <tr>
            <th><input type="checkbox" opt="all">全选</th>
            <th>合同编号</th>
            <th>合同版本</th>
            <th>生成日期</th>
            <th>使用日期</th>
            <th>合同归属</th>
            <th>营业部</th>
            <th>归档状态</th>
            <th>合同状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <c:forEach items="${page.list}" var="item">
        	<tr>
	            <td><input type="checkbox" onclick="checkOne(this)" opt="records" data-id="${item.contCode}" data-belong="${item.dictContBelong}" data-cs="${item.dictContStatus}" data-changeApply="${item.changeApply}"></td>
	            <td>${item.contCode}</td>
	            <td>
	            	<c:choose>
	            		<c:when test="${item.contractType eq '2' }">
	            			协议${fns:dictName(dicts.tz_contract_vesion, item.contVersion, '-') }
	            		</c:when>
	            		<c:otherwise>
	            			${fns:dictName(dicts.tz_contract_vesion, item.contVersion, '-') }
	            		</c:otherwise>
	            	</c:choose>
	            </td>
				<td>${fns:getFormatDate(item.applyDay,'yyyy-MM-dd')}</td>
	            <td>${fns:getFormatDate(item.contUseDay,'yyyy-MM-dd')}</td>
	            <td>${item.userName}</td>
	            <td>${item.name}</td>
	            <td>${fns:dictName(dicts.tz_filed_state, item.dictContFileStatus, '-') }</td>
	            <td>${fns:dictName(dicts.tz_contract_state, item.dictContStatus, '-') }</td>
	            <td class="tcenter">
		            <c:if test="${item.dictContStatus==0 or item.dictContStatus == 8  }">  <!-- 库存、审批退回 、 -->
		            	<c:if test="${not fn:contains(autoContVersion, item.contVersion) }">  <!--contCode 包含C的是无纸化，取非为纸质  -->
			            	<auth:hasPermission key="cf:contractlook:transact2">
			            		<input type="button" value="办理"  class='cf_btn_edit' onclick="window.location.href='${ctx}/contract/changeApply?contCode=${item.contCode}';">
			            	</auth:hasPermission>
		            	</c:if>
			    	</c:if>
			    	<auth:hasPermission key="cf:contractlook:transact1">
				    	<input type="button" value="详细"  class='cf_btn_edit' onclick="openDetail('${item.contCode}')">
				    </auth:hasPermission>
				    <auth:hasPermission key="cf:contractlook:log">
				    	<input type="button" value="全程留痕" class='cf_btn_edit' onclick="fullTraceBtn('${item.contCode}')">
				    </auth:hasPermission>
		    	</td>
		    </tr>
	    </c:forEach>
    </table>
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
						<button type="button" class="btn cf_btn-primary" id="submitBtn">提交</button>
						<button type="button" class="btn cf_btn-primary" id="subClose">关闭</button>
					</div>
				</div>
			</div>
		</div>
</body>
</html>