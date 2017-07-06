<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript" src="${ctxWebInf}/js/deduct/pop.js"></script>
<div>
<%-- 	<form id="onLineDeductForm"  action="${ctx}/deductBalance/onLineDeduct" method="post"> --%>
<div>
	<input class="btn btn_sc ml10 mt10" type="button"  value="添加划扣平台" id="addhkpt"/>
</div>
		<table id="onLineTable" class="table1">
			<tr>
				<td>
					<label class='lab'>划扣平台：</label>
					<select name="deductPlatFormID" class="select78">
						<option value="">请选择</option>
                		<c:forEach items="${dicts.tz_deduct_plat}" var="d">
                			<option value="${d.value}" 
                				<c:if test="${d.value eq deductPoolExt.dictApplyDeductType}">
                					selected
                				</c:if> 
                			>${d.label}</option>
                		</c:forEach>
                	</select>
				</td>
				<td>
					<c:forEach items="${dicts.com_deduct_type}" var="d">
						<input type="radio" value="${d.value}" name="deductInterfaceType[0]">${d.label}&nbsp;&nbsp;&nbsp;
					</c:forEach>
<!-- 					<input type="radio" value="2" name="deductInterfaceType[0]">批量&nbsp;&nbsp;&nbsp; -->
<!-- 					<input type="radio" value="1" name="deductInterfaceType[0]">实时&nbsp;&nbsp;&nbsp; -->
               </td>
            </tr>
            <!--隐藏的tr,功能实现的需要  -->
            <tr id="template" style="display:none">
				<td>
					<label class='lab'>划扣平台：</label>
					<select class="select78 selectClass">
						<option value="">请选择</option>
                		<c:forEach items="${dicts.tz_deduct_plat}" var="item">
                			<option value="${item.value}" 
                				<c:if test="${item.value eq dictApplyDeductType}">
                					selected
                				</c:if> 
                			>${item.label}</option>
                		</c:forEach>
                	</select>
				</td>
				<td>
					<c:forEach items="${dicts.com_deduct_type}" var="d">
						<input type="radio" value="${d.value}" name="deductInterfaceType[0]">${d.label}&nbsp;&nbsp;&nbsp;
					</c:forEach>
<!-- 					<input type="radio" value="2"  id="batchRadio">批量&nbsp;&nbsp;&nbsp; -->
<!-- 					<input type="radio" value="1"  id="timelyRadio">实时&nbsp;&nbsp;&nbsp; -->
               </td>
            </tr>
        </table>
<!--       </form> -->
</div>