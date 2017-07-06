<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!-- 表示被改变的列表或表单内容  必传-->
<%@ attribute name="showValue" type="java.lang.String" required="true"%>
<!-- 表示 权限是否可以看到信息  必传-->
<%@ attribute name="show" type="java.lang.Boolean" required="true"%>
<!-- 表示被隐藏字段显示的长度，默认为内容长度 -->
<%@ attribute name="count" type="java.lang.String" required="false"%>
<!-- 表示隐藏表单的id -->
<%@ attribute name="formId" type="java.lang.String" required="false"%>
<span id="customerId${ showValue}"></span>
<c:choose>
	<c:when test="${show && formId==null}"> 
    	 <script type="text/javascript">
    	 	var showValue='${ showValue}';
    		$("#customerId"+showValue).html('${ showValue}');
    	 </script>
    </c:when>
	<c:when test="${show && formId!=null}"> 
    	 <script type="text/javascript">
    	 	var showValue='${ showValue}';
    		$("#customerId"+showValue).val(showValue);
    	 </script>
    </c:when>
    
	<c:when test="${!show  && count!=null }">
		<script type="text/javascript">
			var	formId='${formId}' 
			if (formId!=null && formId.length>0){
				var i='${ count}';
				var hi="";
				for(var j=0;j<i;j++){
					hi=hi+"*";
				}
				$("#custCodeId"+formId).val(hi);
			}else {
				var i='${ count}';
				var id='${ showValue}';
				var hi="";
				for(var j=0;j<i;j++){
					hi=hi+"*";
				}
				$("#customerId"+id).html(hi);
			}
			
		</script>
	</c:when>

	<c:when test="${!show && count==null}">
		<script type="text/javascript">
			var	formId='${formId}' 
			if (formId!=null && formId.length>0){
				var i='${ showValue}';
				var hi="";
				for(var j=0;j<i.length;j++){
					hi=hi+"*";
				}
				$("#custCodeId"+formId).val(hi);
			}else {
				var i='${ showValue}';
				var hi="";
				for(var j=0;j<i.length;j++){
					hi=hi+"*";
				}
				$("#customerId"+i).html(hi);
			}
		</script>
	</c:when>
</c:choose>
