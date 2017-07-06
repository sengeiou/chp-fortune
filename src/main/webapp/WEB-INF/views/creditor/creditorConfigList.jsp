<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf }/js/creditor/batchEnableDisable.js" type="text/javascript"></script>
<script src= "${ctxWebInf}/js/creditor/creditorConfigList.js" type="text/javascript"></script>
<title>错期匹配列表</title>
</head>
<body>
<div class="body_r">
    <div class="box1 mb10">
       <form:form id="searchForm" modelAttribute="creditorConfig" action="${ctx}/creditor/creditorConfig/list" method="post">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
           <tr>
               <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                <td>
                    <label class="lab">账单日：</label>
                        <select class="select78" id="configCreditDay" name="configCreditDay">
                            <option value="">请选择</option>
                            <c:forEach items="${dicts.tz_bill_day}" var="item">
                                 <option value="${item.value }" <c:if test="${creditorConfig.configCreditDay==item.value}">selected</c:if>>${item.label }</option>
                            </c:forEach>
                        </select>
                </td>
                <td>
                    <label class="lab">还款日：</label>
                        <select class="select78" id="configRepayDay" name="configRepayDay">
                            <option value="">请选择</option>
                            <c:forEach items="${dicts.tz_repay_day}" var="item">
                               <option value="${item.value }" <c:if test="${creditorConfig.configRepayDay==item.value}">selected</c:if>>${item.label}</option>
                            </c:forEach>
                        </select>
                </td>
                <td>
                    <label class="lab">账单类型：</label>
                        <select class="select78" id="dictConfigStatus" name="dictConfigStatus">
                            <option value="">请选择</option>
                            <c:forEach items="${dicts.tz_bill_state}" var="item">
                               <option value="${item.value }" <c:if test="${creditorConfig.dictConfigStatus==item.value}">selected</c:if>>${item.label}</option>
                            </c:forEach>
                        </select>
                </td>
           </tr>
            <tr>
                <td>
                    <label class="lab">状态：</label>
                        <select class="select78" id="dictConfigZdr" name="dictConfigZdr">
                            <option value="">请选择</option>
                            <c:forEach items="${dicts.com_use_flag}" var="item">
                               <option value="${item.value }" <c:if test="${creditorConfig.dictConfigZdr==item.value}">selected
                               </c:if>>${item.label}</option>
                            </c:forEach>
                        </select>
                </td>
            </tr>
			<tr>
				<td colspan="3" class="tright pr30" >
				  <input type="submit" class="btn cf_btn-primary" value="搜索"></button>
				  <input type="reset" class="btn cf_btn-primary"  id="reset" value="清除"></button>
				</td>
			</tr>    
		</table> 
	   </form:form> 
    </div>

    <div class="mb10 pt10">
    	<auth:hasPermission key="cf:creditorConfig:add">
        	<button class="btn btn_sc ml10" onclick="goPage('${ctx}/creditor/creditorConfig/turnPage');">新增匹配</button>
        </auth:hasPermission>
        <auth:hasPermission key="cf:creditorConfig:del">
        	<button class="btn btn_sc" id="removeButton">删除</button>
        </auth:hasPermission>
        <auth:hasPermission key="cf:creditorConfig:enable">
        	<button class="btn btn_sc toEnable">启用</button>
        </auth:hasPermission>
        <auth:hasPermission key="cf:creditorConfig:disable">
        	<button class="btn btn_sc toDisable">停用</button>
        </auth:hasPermission>
    </div>	
	<div class="box5">
	<form method="post">
	    <table class="table table-striped table-bordered table-condensed data-list-table" width="100%">
	        <thead>
	        <tr>
	        	<th><input type="checkbox" class=" ml10 mr10 checkAll">全选</th>
	            <th>序号 </th>
	            <th>账单日</th>
	            <th>还款日</th>
	            <th>账单类型</th>
	            <th>状态</th>
	            <th>操作</th>
	        </tr>
	        </thead>
	       <c:forEach items="${page.list}" var="item" varStatus="status">
	        <tr>
	        	<td><input type='checkbox' name="id" value="${item.id }"/></td>
	            <td>${status.count}</td>
	            <td>${item.configCreditDay}</td>
	            <td>${item.configRepayDay}</td>
	            <td>${fns:dictName(dicts.tz_bill_state,item.dictConfigStatus,'-')}</td>
	            <td id="userFlag" <c:if test="${fns:dictName(dicts.com_use_flag,item.dictConfigZdr,'-')=='启用'}">style="font-weight: bold;color: black;"</c:if>>${fns:dictName(dicts.com_use_flag,item.dictConfigZdr,'-')}</td>
	            <td>
	            	<auth:hasPermission key="cf:creditorConfig:transact">
		                <c:choose>
		                    <c:when test="${item.dictConfigZdr == 1}">
		                        <input type="button" value="停用" class="cf_btn_edit" onclick="handle('${item.id}','${item.dictConfigZdr}');"/>
		                    </c:when>
		                    <c:when test="${item.dictConfigZdr == 0}">
		                        <input type="button" value="启用" class="cf_btn_edit" onclick="handle('${item.id}','${item.dictConfigZdr}')"/>
		                    </c:when>
		                </c:choose>
		            </auth:hasPermission>
	            </td>
	        </tr>
	       </c:forEach> 
	    </table>
    </form>
    </div>
    <div class="pagination">${page}</div>
</div>
</body>
</html>