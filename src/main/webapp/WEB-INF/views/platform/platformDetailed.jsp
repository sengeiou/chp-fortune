<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf}/js/platform/platform.js" type="text/javascript"></script>
<title></title>
</head>
<body>
<div>
<div>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input class="btn btn_sc ml10 mt10 mb10" type="button"  value="添加划扣平台" id="addhkpt"/>
</div>
   <div class='box1'>
	<form id="addOrEdit"  action="${ctx}/platformRule/save" method="post">
		<table id="onLineTable" class='table1'>
            <c:choose>
            	<c:when test="${get eq 'get' }">
            		     <c:forEach items="${platformGotoRule.platformIdList}" var="platformIdList" varStatus="status">
            				<c:if test="${status.index == 0 }">
            					<tr>
									<td>
										<label class='lab'>第一平台：</label>
										<select name="platformId"  class="select78" disabled="disabled">
											<option value="">请选择</option>
					                		<c:forEach items="${fns:getDictList('tz_deduct_plat')}" var="d">
					                			<option value="${d.value}" 
					                				<c:if test="${d.value eq platformIdList}">
					                					selected
					                				</c:if> 
					                			>${d.label}</option>
					                		</c:forEach>
					                	</select>
									</td>
					            </tr>
            				</c:if>
            				<c:if test="${status.index != 0 }">
            					<tr>
									<td>
										<label class='lab'>自动跳转平台：</label>
										<select name="platformIdList" class="select78">
											<option value="">请选择</option>
					                		<c:forEach items="${fns:getDictList('tz_deduct_plat')}" var="d">
					                			<option value="${d.value}" 
					                				<c:if test="${d.value eq platformIdList}">
					                					selected
					                				</c:if> 
					                			>${d.label}</option>
					                		</c:forEach>
					                	</select>
									</td>
					            </tr>
            				</c:if>
            			</c:forEach>
            	</c:when>
            	<c:otherwise>
            		<tr>
						<td>
							<label class='lab'>第一平台：</label>
							<select name="platformId"  class="select78">
								<option value="">请选择</option>
		                		<c:forEach items="${fns:getDictList('tz_deduct_plat')}" var="d">
		                			<option value="${d.value}" 
		                				<c:if test="${d.value eq platformIdList}">
		                					selected
		                				</c:if> 
		                			>${d.label}</option>
		                		</c:forEach>
		                	</select>
						</td>
		            </tr>
            	</c:otherwise>
            </c:choose>
            <tr style="display: none;">
				<td>
					<input type="hidden" class="cf_input_text178" name="id" value="${platformGotoRule.id}">
					<label class='lab'>出借平台：</label>
					<input type="text" required class="cf_input_text178" name="platformRuleName"  readonly="readonly" value="${platformGotoRule.platformRuleName}">
				</td>
            </tr>
            <tr>
				<td>
					<label class='lab'>平台规则：</label>
					<input type="text" class="cf_input_text178" readonly="readonly" value="${platformGotoRule.platformRuleTitle}" name="platformRuleText">
					<input type="hidden" class="cf_input_text178" value="${platformGotoRule.platformRule}" name="platformRule">
				</td>
            </tr>
            <tr>
				<td>
					<label class='lab'>规则状态：</label>
					<label>停用</label>
					<input type="radio" required  name="status" <c:if test="${platformGotoRule.status eq '0'}">checked</c:if>  value="0">
					<label>启用</label>
					<input type="radio" required  name="status" <c:if test="${platformGotoRule.status eq '1'}">checked</c:if> value="1">
				</td>
            </tr>
            <!--隐藏的tr,功能实现的需要  -->
            <tr id="template" style="display:none">
				<td>
					<label class='lab'>自动跳转平台：</label>
					<select class="select78 selectClass" id="hiddenSelect">
						<option value="">请选择</option>
                		<c:forEach items="${fns:getDictList('tz_deduct_plat')}" var="item">
                			<option value="${item.value}" 
                				<c:if test="${item.value eq dictApplyDeductType}">
                					selected
                				</c:if> 
                			>${item.label}</option>
                		</c:forEach>
                	</select>
				</td>
            </tr>
        </table>
	 </form>
	  </div>
	 	<div class="tright pr30 pt10">
   		    <input type="button" class="btn cf_btn-primary" id="submitButton" value="提交"/>
	    	<input type="reset" class="btn cf_btn-primary"  value="返回" onclick="go();" />
		</div>
</div>
</body>
</html>