<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>文件制作人员配置表</title>
<script src="${ctxWebInf }/js/creditor/matching/distributeConfigList.js" type="text/javascript">
</script>
</head>
<body>
<div class="body_r">
    <div class="box1 mb10">
       <form id="searchForm" action="${ctx}/matchingcreditor/distributeConfigList" method="post" >
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
          <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
             <tr>
                <td><label class="lab">姓名：</label><input type="text" class="cf_input_text178" name="name" id="name" value="${sendCountConfigView.name}"></td>
				<td><label class='lab'>在岗状态：</label>
                       <select class="select78" name="status" id="status"> 
                         <option value="">请选择</option>
                         <option value="1" <c:if test="${sendCountConfigView.status=='1'}">selected</c:if>>在岗</option>
                         <option value="0" <c:if test="${sendCountConfigView.status=='0'}">selected</c:if>>离岗</option>
                       </select>
				</td>
            </tr>
          </table>
	      <div class="tright pr30 mt20">
	             <input type="submit" id="search" value="搜索" class="btn cf_btn-primary" />
	             <input type="reset" value="清除" class="btn cf_btn-primary" onclick="javascript:void(0);" />
	       </div>
      </div>
      
      <p class="mb10">
      	<auth:hasPermission key="cf:docconfig:add">
			<input type="button" opt="add" value="新增配置表" class="btn btn_sc ml10"/>
		</auth:hasPermission>
		<auth:hasPermission key="cf:docconfig:del">
			<input type="button" opt="del" value="删除" class="btn btn_sc"/>
		</auth:hasPermission>
		<auth:hasPermission key="cf:docconfig:batchzaigang">
			<input type="button" opt="statusIn" value="批量在岗" class="btn btn_sc"/>
		</auth:hasPermission>
		<auth:hasPermission key="cf:docconfig:batchligang">
			<input type="button" opt="statusOut" value="批量离岗" class="btn btn_sc"/>
		</auth:hasPermission>
      </p>  
     </form>
    <div id="content-body">
	<div id="reportTableDiv" class="span10">
    <table class="table table-striped table-bordered table-condensed data-list-table">
        <thead>
        <tr>
            <th><input type='checkbox' class="checkAll"/></th>
            <th>用户编号</th>
            <th>姓名</th>
            <th>派单数量</th>
            <th>在岗状态</th>
			<th>处理</th>
        </tr>
        </thead>
        <c:forEach items="${page.list}" var="item">
	        <tr>
	            <td><input type='checkbox' opt="records" data-id="${item.id }" data-status = "${item.status}"/></td>
	            <td>${item.userId }</td>
	            <td>${item.name }</td>
                <td>${item.leaderSendCount }</td>
                <td>
	                <c:if test="${item.status == 1}">在岗
	                </c:if>
	                <c:if test="${item.status == 0}">离岗
	                </c:if>
                </td>
                <td>
                	<auth:hasPermission key="cf:docconfig:batchedit">
                		<a href="#" opt="editSendCount" data-id="${item.id }" data-count="${item.leaderSendCount}" class='cf_btn_edit'>编辑</a>
                	</auth:hasPermission>
                </td>
	        </tr>
        </c:forEach>
    </table>
    </div>
    </div>
      <div class="pagination">${page}</div>
</div>
<div style="display:none">
	<form id='template'>
		<span>合同制作专员：
			<select class='select78' select_required='1' name='userId'>
				<option value="">请选择</option>
			</select>
		</span>
		<span>在岗状态：
			<select class='select78' select_required='1' name='status'>
				<option value="">请选择</option>
				<option value="1" <c:if test="${sendCountConfigView.status=='1'}">selected</c:if>>在岗</option>
                <option value="0" <c:if test="${sendCountConfigView.status=='0'}">selected</c:if>>离岗</option>
			</select>
		</span>
	</form>
</div>
</body>
</body>
</html>
