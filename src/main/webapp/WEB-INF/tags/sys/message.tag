<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="content" type="java.lang.String" required="true" description="消息内容"%>
<c:if test="${not empty content and content != ''}">
<script>
jQuery(document).ready(function($){
	BootstrapDialog.alert('${content}');
});

</script>
</c:if>

