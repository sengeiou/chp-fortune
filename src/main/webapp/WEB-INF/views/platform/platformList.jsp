<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf}/js/platform/platform.js" type="text/javascript"></script>
<script type="text/javascript">
</script>
</head>
<body>
<div class="body_r">
	<form id="searchForm" method="post" action="${ctx}/platformRule/list">
	    <div class="box1 mb10">
	        	    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
	            <tr> 
				    <td>
				    	<label class="lab">规则名：</label>
				    	<input type="text" name="platformRuleName" class="cf_input_text178" value="${platformGotoRule.platformRuleName }" id="platformRuleName">
				    </td>
	                <td>
	                	<label class="lab">平台ID：</label>
	                	<input type="text"  name="platformId" class="cf_input_text178" value="${platformGotoRule.platformId }" id="platformId">
	                </td>
				   	<td>
				   	  <lable></lable>
				   	</td>
	            </tr>
	        </table>
	        <div class='tright pr30 pt10'>
	            <input class="btn cf_btn-primary" type="submit" value="搜索"/>
	            <input type="reset" class="btn cf_btn-primary" value="清除">
	        </div>
	        </div>
	   
    </form>
    <sys:message content="${message}"/>
    <p class="mb10">
    	<auth:hasPermission key="cf:platformgorule:add">
			<button class="btn btn_sc ml10" id="add">新增</button>
		</auth:hasPermission>
    <sys:columnCtrl pageToken="trusteeship_account_list"></sys:columnCtrl>
    <div class='box5'>
	    <table class="table table-striped table-bordered table-condensed data-list-table" width="100%">
	        <thead>
	        <tr>
	            <th>序号</th>
	            <th>平台ID</th>
	            <th>出借平台</th>
	            <th>平台规则</th>
	            <th>状态</th>
	            <th>操作</th>
	        </tr>
	        </thead>
	        <c:forEach items="${page.list }" var="platformEntity" varStatus="status">
		        <tr>
		           <td>${status.index}</td>
		           <td>${platformEntity.platformId}</td>
		           <td>${platformEntity.platformRuleName}</td>
		           <td>${platformEntity.platformRuleTitle}</td>
		           <td>
			          		<c:if test="${platformEntity.status eq '0'}">停用</c:if>
			          		<c:if test="${platformEntity.status eq '1'}">启用</c:if>
		           </td>
		           <td>
		           		<auth:hasPermission key="cf:platformgorule:edit">
				           	<a href="${ctx}/platformRule/get?id=${platformEntity.id }">
							    <button class="cf_btn_edit" onclick="">修改</button>
					        </a>
					    </auth:hasPermission>
					    <auth:hasPermission key="cf:platformgorule:del">
							<input id="delete" type="button" url="${ctx}/platformRule/save?id=${platformEntity.id }&status=3" value="删除" class="cf_btn_edit" style="cursor:pointer;">
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
