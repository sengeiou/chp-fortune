<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf }/js/creditor/batchEnableDisable.js" type="text/javascript"></script>
<script src="${ctxWebInf }/js/creditor/config/autoMatching.js" type="text/javascript"></script>
<title></title>
</head>
<body>
	<div class="body_r">
	    <div class="box1 mb10">      
	    	<form action="${ctx}/creditor/config/automatching" method="post" id="searchForm">
	    		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		             <tr>
		             	<td>
		             		<label class="lab">营业部：</label>
		             		<sys:orgTree id="org" name="businessDepartment" value="${search.businessDepartment}" labelName="businessDepartmentName" labelValue="${search.businessDepartmentName}"/>
		             	</td>
			             <td>
							<label class="lab">所在城市：</label>
							<sys:cityselect id="provinceCity" name="provinceCity" value="${search.provinceCity}" 
							multiple="true"/>
						</td>
		                <td>
		                	<label class="lab">产品：</label>
		                	<sys:productselect name="productCode" value="${search.productCode}" multiple="true" exclude="86"/>
		                </td>
		                
		            </tr>
		            <tr>
		            	<td>
		            		<label class="lab">账单类型：</label>
		            			<select class="select78" name="billType" multiple="multiple">
		            				<c:forEach items="${dicts.tz_bill_state}" var="item">
			                        	<option value="${item.value }" <c:if test="${fns:multiOption(search.billType,item.value)}">selected</c:if>>${item.label }</option>
			                        </c:forEach>
		            			</select>	
		            	</td>
		                <td>
		                	<label class="lab">账单日：</label>
		                	<select class="select78" name="billDayEx" multiple="multiple">
	            				<c:forEach items="${dicts.tz_bill_day}" var="item">
		                        	<option value="${item.value }" <c:if test="${fns:multiOption(search.billDayEx,item.value)}">selected</c:if>>${item.label }</option>
		                        </c:forEach>
	            			</select>	
		                </td>
		                <td>
		                	<label class="lab">状态：</label>
		                	<select class="select78" name="status" multiple="multiple">
		                		<c:forEach items="${dicts.com_use_flag}" var="item">
		                        	<option value="${item.value }" <c:if test="${fns:multiOption(search.status,item.value)}">selected</c:if>>${item.label }</option>
		                        </c:forEach>
		                	</select>
		                </td>	              
		            </tr>
		        </table>
	        	<div class="tright pr30 pb5 pt10">
	        		<button class="btn cf_btn-primary search">搜索</button>
	        		<input class="btn cf_btn-primary" type="reset" value="清除">
	        	</div>
	        </form>
	    </div>
	    <p class="mb10">
	    	<form method="post">
	    			<input type="button" style="display:none" class="btn btn_sc ml10 mb10 toAuto" value="自动匹配"/>
	    		<%-- <auth:hasPermission key="cf:automacthingconfig:add"> --%>
			    	<input type="button" class="btn btn_sc ml10 mb10 toAdd" value="新增匹配"/>
			 <%--    </auth:hasPermission> --%>
			  <%--   <auth:hasPermission key="cf:automacthingconfig:del"> --%>
			    	<input type="button" class="btn btn_sc mb10 toDelete" value="删除"/>
			 <%--    </auth:hasPermission> --%>
				<auth:hasPermission key="cf:automacthingconfig:enable">
					<input type="button" class="btn btn_sc mb10 toEnable" value="启用"/>
				</auth:hasPermission>
				<auth:hasPermission key="cf:automacthingconfig:disable">
					<input type="button" class="btn btn_sc mb10 toDisable" value="停用"/>
				</auth:hasPermission>
		    	<div class="dataList">
			    	<%@ include file="/WEB-INF/views/creditor/config/autoMatching/list.jsp"%>
			    </div>
		    </form>
		</p>	    
	</div>
</body>
</html>
