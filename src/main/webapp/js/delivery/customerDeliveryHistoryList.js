   
$(function(){
	var iframe = getCurrentIframeContents();
	// 导出excel
	$("#outExcel",iframe).click(function(){
		 BootstrapDialog.confirm('确定要导出EXCEL吗？', function(result){
	         if(result){
	        	 var url = ctx+"/delivery/customer/history/outExcel?"+$("#searchForm").serialize();
				 window.location=url;
	         }else{
	        	 return;
	         }
	     });
	})
})