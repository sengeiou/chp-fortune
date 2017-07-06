<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="approval" type="java.lang.String" required="false" description="审批页面"%>

<input id="btnAttachment" class="btn btn_sc ml10" type="button" value="附件">

<div class="attachment_div" style="display:none">
	<div class='dropzone dropzone-pic' id="my-awesome-dropzone" style="margin-top:10px;float:left;">    
	</div>
	<div class="dropzone dropzone-previews 
		<c:if test="${not empty approval and approval}">approval</c:if>
		<c:if test="${empty approval or !approval}">putin</c:if>
		 id="my-awesome-dropzone" style='margin-top:10px;float:left;overflow-y:hidden;'></div>
	
	<div class="dropzone_result"></div>
</div>
<script>
jQuery(document).ready(function($){
	Dropzone.autoDiscover = false;
	
	$('#btnAttachment').off('click').on('click',function(){
		if($('.attachment_div').is(':visible')){
			$('.attachment_div').hide();
		}else{
			$('.attachment_div').show();
		}
	});
	<c:if test="${not empty approval and approval}">
	$(document).bind("click",function(e){ 
		var target = $(e.target); 
		if(target.closest(".attachment_div").length == 0 && !target.is('#btnAttachment')){ 
			$('.attachment_div').hide();
		} 
	});
	</c:if>
	
});
</script>