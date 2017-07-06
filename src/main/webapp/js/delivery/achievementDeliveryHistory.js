
$(function(){
	var iframe = getCurrentIframeContents();
	$('#search',iframe).click(function(){
		 $form = $('#searchForm');
		 $("#pageNo").val("");
		 $form.submit();
	 })
	// 导出excel
	$("#outExcel",iframe).click(function(){
		 BootstrapDialog.confirm('确定要导出EXCEL吗？', function(result){
	         if(result){
	        	 var url = ctx+"/delivery/achievement/history/outExcel";
	        	 postFormReplaceGet($("#searchForm"),url);
	         }else{
	        	 return;
	         }
	     });
	})
})