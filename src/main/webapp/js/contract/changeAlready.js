$(document).ready(function() { 

	var path = ctx;
	
	$('.SunIAS').on('click',function(e){
		var scan = $("#scan");
   		if(scan.css('display') == 'none'){
   			contents_getJsonForSync(path+'/contract/getContractSunIASURL?contCode='+$("#contCode").val(),{},'post',
   	        	 //成功取得后的处理
   				 function(data){
   			       if(data.result == 'false'){
   			    	   BootstrapDialog.alert('未找到对应的合同信息');
   			    	   return;
   			       }else{
	   			    	scan.css('display','block')
	   		   			scaniframe.location.href=data.url;
	   		   			$('[name=scaniframe]').contentWindow.location.reload(true);
   			       }
   	        	 },
   	        	 //取得失败后的处理
   	        	 function(error){
   	        		 BootstrapDialog.alert('系统错误');
   	        	 }
           ); 
   		}else{
   			scan.css('display','none');
   		}
		
	});
	
}); 
