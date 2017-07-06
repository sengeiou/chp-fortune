<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctxWebInf}/js/cutOff/cutOff.js"></script>
<meta name="decorator" content="default">
<title>门店单据和组员关系管理</title>
</head>
<body>
	<ul id="myTabs" class="nav nav-tabs" >
    <li class="active"><a href="#home" aria-controls="home" data-toggle="tab">门店截单列表</a></li>
    <li><a href="#profile" aria-controls="profile" data-toggle="tab">设置截单</a></li>
  </ul>

  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="home">
		<form:form id="searchForm" action="${ctx}/cutOff/list" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="box1 mb10">
		    <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		    <tr>
		    <td>
			<label class='lab'>门店：</label>
			<input type="text" name="orgName" style="width:260px;border-radius:4px;" value="${param.orgName }" />
		   </td>
		   <td>
			<label class='lab'>状态：</label>
           	<select class="select78" name="delFlag">
           		<option value="">请选择</option>
           		<c:forEach items="${fns:getDictList('com_use_flag')}" var="item">
                   	<option value="${item.value }" <c:if test="${cutOff.delFlag==item.value}">selected</c:if>>${item.label }</option>
                   </c:forEach>
           	</select>
           	</td>
           	<td>
           	截单时间：
           	<input id="endTime" name="endTime" type="text" readonly="readonly"  maxlength="20" class="cf_input_text178 Wdate required"
					value="${cutOff.endTime}" onclick="WdatePicker({dateFmt:'HH:mm:ss',isShowClear:false});"/>
	        </td>
	        </tr>
	</table>
	        <div class="tright pr30 pt10">
			  <input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="搜索" />
			  <input id="btnSubmit" class="btn cf_btn-primary" type="reset" value="清除" />
			</div>
	</div>
	</form:form>
	<sys:message content="${message}"/>
	<p class='mb10 ml10'>
		<auth:hasPermission key="cf:cutoff:editcutofftime">
			<button type="button" id="editButton" class="btn btn_sc ml10">修改截单时间</button>
		</auth:hasPermission>
		<auth:hasPermission key="cf:cutoff:batchdel">
			<button type="button" id="batchRemoveButton" class="btn btn_sc">批量删除</button>
		</auth:hasPermission>
		<auth:hasPermission key="cf:cutoff:start">
			<button type="button" id="delFlag_start" class="btn btn_sc">启用</button>
		</auth:hasPermission>
		<auth:hasPermission key="cf:cutoff:stop">
			<button type="button" id="delFlag_cease" class="btn btn_sc">停用</button>
		</auth:hasPermission>
	</p>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th><input type="checkbox" class="checkAll"/>全选</th>
		<th>门店</th>
		<th>截单时间</th>
		<th>设置人</th>
		<th>状态</th>
		<th>操作</th>
	</tr>
	</thead>
		<c:forEach items="${page.list }" var="item" varStatus="s">
			<tr>
				<input type="hidden" value="${item.id }" id="cutOffId">
				<td id="checkboxTd"><input type="checkbox" value="${item.id }" id="checkboxId">${s.index+1}</td>
				<td>${item.orgName }</td>
				<td>${item.endTime }</td>
				<td>${item.lastBy }</td>
				<td id="delFlagTd">${fns:dictName(dicts.com_use_flag, item.delFlag, '-')}</td>
				<td id="edit_removeTd">
					<auth:hasPermission key="cf:cutoff:edit">
						<input type="button" value="编辑" class="cf_btn_edit" onclick="toEditPage('${item.id }')">
					</auth:hasPermission>
					<auth:hasPermission key="cf:cutoff:del">
						<input type="button" id="removeButton" value="删除" class="cf_btn_edit" onclick="removeCutOff(this)">
					</auth:hasPermission>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div>${page}</div>
	</div>
    <div role="tabpanel" class="tab-pane" id="profile">
     <div class="container">
    	<form:form id="formStoresMemberSave" action="${ctx}/cutOff/save"  class="form-horizontal">
    		<div class="form-group">
				<label class="lab"><span class='red'>*</span>截单时间：</label>
				<input id="endTime" name="endTime" type="text" readonly="readonly"  maxlength="20" class="cf_input_text178 Wdate required"
					value="${cutOff.endTime}" onclick="WdatePicker({dateFmt:'HH:mm:ss',isShowClear:false});"/>
			</div>
			<div class="form-group">
	             <label class="lab"><span class='red'>*</span>状态：</label>
	                	<select class="select78" name='delFlag' select_required="1">
	                		<option value="">请选择</option>
	                		<c:forEach items="${fns:getDictList('com_use_flag')}" var="item">
	                        	<option value="${item.value }" <c:if test="${cutOff.delFlag==item.value}">selected</c:if>>${item.label }</option>
	                        </c:forEach>
	                	</select>
	         </div>
			<div class="form-group" >
				<label class="lab">选择门店：</label>
				<i id="selectStoresBtn" class="icon-search" style="cursor:pointer;"></i>
			</div>
			<div class="form-group" >
				<label class="lab">已选择&nbsp;<span id="storeNumSpn" style="font-size:24px;color:#ffb752;">0</span>&nbsp;门店：</label>
				<div class="col-sm-6"><input id="storesIdList" type="hidden" name="storesIdList" class="required"/>
					<table id="storeTable" class="table table-bordered table-condensed text-center">
						<thead>
						<tr>
							<td>部门</td>
							<td>操作</td>
						</tr>
						</thead>
					</table>
				</div>
			</div>
			<div class="clearfix form-actions">
				<div class="tright pr30">
					<input id="btnSaveSubmit" class="btn cf_btn-primary" type="button" value="保 存"/>
				</div>
			</div>
		 </form:form>
	 </div>
    </div>
  </div>
  <div id="modal-sub" class="modal fade subwindow" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close closeButton" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
				</div>
			</div>
		</div>
	</div>
</body>
</html>