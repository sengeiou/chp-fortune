<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf }/js/creditor/batchEnableDisable.js" type="text/javascript"></script>
<script src="${ctxWebInf }/js/creditor/config/rule.js" type="text/javascript"></script>
<title></title>
</head>
<body>
<div class="body_r">
    <div class="box1 mb10">
	    <form action="${ctx}/creditor/config/rule" method="post" id="searchForm">
	        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
	             <tr>
	                <td>
	                	<label class="lab">账单类型：</label>
	                	<select class="select78" name="billType">
            				<option value="">请选择</option>
            				<c:forEach items="${dicts.tz_bill_state}" var="item">
	                        	<option value="${item.value }" <c:if test="${search.billType==item.value}">selected</c:if>>${item.label }</option>
	                        </c:forEach>
            			</select>
	                </td>
	            </tr>
	        </table>
	        <div class="tright pr30">
	        	<button class="btn cf_btn-primary search">搜索</button>
	        	<input type="reset" value="清除" class="btn cf_btn-primary"/>
	        </div>
	    </form>
	    </div>
	   <div>
	    <form method="post">
	    	<auth:hasPermission key="cf:macthingconfig:add">
		    	<input class="btn btn_sc ml10 toAdd mb10" type="button" value="新增配置" />
		    </auth:hasPermission>
		    <auth:hasPermission key="cf:macthingconfig:del">
		    	<input class="btn btn_sc toDelete mb10" type="button" value="删除" />
		    </auth:hasPermission>
		     <auth:hasPermission key="cf:macthingconfig:enable">
		    	<input class="btn btn_sc toEnable mb10" type="button" value="启用" />
	        </auth:hasPermission>
	        <auth:hasPermission key="cf:macthingconfig:disable">
		    	<input class="btn btn_sc toDisable mb10" type="button" value="停用" />
	        </auth:hasPermission>
	    	 <div class="dataList">
		    <%@ include file="/WEB-INF/views/creditor/config/rule/ruleList.jsp"%>
		 </div>
		 </div>
	    </form>
	    </div>
</body>
</html>


