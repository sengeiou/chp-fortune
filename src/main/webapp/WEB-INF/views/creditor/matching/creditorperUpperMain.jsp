<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf }/js/creditor/batchEnableDisable.js" type="text/javascript"></script>
<script src="${ctxWebInf }/js/creditor/matching/creditorperUpperMain.js" type="text/javascript"></script>
<title></title>
</head>
<body>
	<div class="body_r">
	    <div class="box1 mb10">
	    	<form action="${ctx}/creditor/CreditorperUpper/list" method="post" id="searchForm">
		       	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		        
		            <tr>
		            	<td>
		            		<label class="lab">借款类型：</label>
		            			<select class="select78" name="dictLoanType" multiple="multiple">
		            				<c:forEach items="${loanTypeMap}" var="item"> 
		            				 <option value="${item.value }" 
		            				 <c:if test="${fns:multiOption(creditorperUpper.dictLoanType,item.value )}">selected</c:if> >${item.key }</option>  
        							 </c:forEach>  
		            			</select>	
		            	</td>
		                <td>
		                	<label class="lab">职业情况：</label>
		                	<select class="select78" name="proofType" multiple="multiple">
	            				<c:forEach items="${proMap}" var="item"> 
		            				 <option value="${item.value }" <c:if test="${fns:multiOption(creditorperUpper.proofType,item.value )}">selected</c:if> >${item.key }</option>  
        							  </c:forEach>  
	            			</select>	
		                </td>
		              
		            </tr>	            	
		          
		        </table>
	        	<div class="tright pr30">
	        		<button class="btn cf_btn-primary search">搜索</button>
	        		<input class="btn cf_btn-primary" type="reset" value="清除">
	        	</div>
	        </form>
	    </div>
	    <p class="mb10 ml10">
	    	<form method="post">
	    		<auth:hasPermission key="cf:borrowerconfig:add">
		    		<input type="button" class="btn btn_sc ml10 toAdd mb10" value="新增配置"/>
		    	</auth:hasPermission>
		    	<auth:hasPermission key="cf:borrowerconfig:del">
		    		<input type="button" class="btn btn_sc toDelete mb10" value="删除"/>
		    	</auth:hasPermission>
		    	<auth:hasPermission key="cf:borrowerconfig:enable">
		    		<input type="button" class="btn btn_sc toEnable mb10" value="启用"/>
		    	</auth:hasPermission>
		    	<auth:hasPermission key="cf:borrowerconfig:disable">
		    		<input type="button" class="btn btn_sc toDisable mb10" value="停用"/>
		    	</auth:hasPermission>
		    	<div class="dataList">
			    	<%@ include file="/WEB-INF/views/creditor/matching/creditorperUpperList.jsp"%>
			    </div>
		    </form>
		</p>	    
	</div>
</body>
</html>
