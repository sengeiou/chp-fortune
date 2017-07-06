<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="false" description="ID"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="名称"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="检索回显值"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="class"%>
<%@ attribute name="groupDisabled" type="java.lang.String" required="false" description="一级菜单可选"%>
<%@ attribute name="multiple" type="java.lang.String" required="false" description="是否多选"%>
<%@ attribute name="other" type="java.lang.String" required="false" description="其他"%>

<select class="select78 ${cssClass}" name="${name}" <c:if test="${not empty multiple and multiple}">multiple="multiple"</c:if> class="${cssClass}" <c:if test="${not empty other}">${other }</c:if>>
	<c:if test="${empty multiple or multiple == false}"><option value="">请选择</option></c:if>
	<c:forEach items="${fns:cityOption()}" var="item">
		<option value="${item.id }" 
			<c:if test="${fns:multiOption(value,item.id)}">selected</c:if> 
			<c:if test="${item.areaType == 1 }">class="optionGroup"
				<c:if test="${not empty groupDisabled and groupDisabled}">disabled="disabled"</c:if>
			</c:if>
			<c:if test="${item.areaType != 1 }">class="optionChild"</c:if>>${item.areaName }</option>
	</c:forEach>
</select>