<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="false" description="ID"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="名称"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="检索回显值"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="class"%>
<%@ attribute name="multiple" type="java.lang.String" required="false" description="多选标识"%>
<%@ attribute name="other" type="java.lang.String" required="false" description="其他"%>
<%@ attribute name="exclude" type="java.lang.String" required="false" description="排除产品"%>

<select <c:if test="${not empty id }">id='${id }'</c:if> class="select78 selectpicker bla bla bli ${cssClass}" data-actions-box="true" name="${name}" <c:if test="${not empty multiple and multiple}">multiple="multiple"</c:if> class="${cssClass}" <c:if test="${not empty other}">${other }</c:if>>
	<c:if test="${empty multiple}"><option value="">请选择</option></c:if>
	<c:forEach items="${fns:productOption()}" var="item">
		<c:choose>
			<c:when test="${not empty exclude and item.productCode eq exclude}"></c:when>
			<c:otherwise>
				<option value="${item.productCode }" 
				<c:if test="${fns:multiOption(value,item.productCode)}">selected</c:if>>${item.productName }</option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</select>