$(document).ready(function() {
	
	
	//清空参数
	$('#contCode').on('keyup',function(e){
		 
		var path = ctx;
		
		var v = $(this).val();
        if(v.length == 12){
        	
        	contents_getJsonForSync(path+'/contract/changeApplyList/getContract',{contCode:v},'post',
   	        	 //成功取得后的处理
   				 function(data){
        		   console.log(data);
        		   if(data.result =='false'){
        			   if(data.v == -1){
        				   BootstrapDialog.alert('未找到对应的合同信息，无法调拨');
        				   return;
        			   }
        			   if(data.v == 0 || data.v == 2){
        				   BootstrapDialog.alert('此合同 非理财经理名下的库存合同，无法调拨');
        				   return;
        			   }
        		   }else{
        			    $("#contVersion").val(data.contVersion);
        			    $("#contVersion").trigger('change');
        			    $("#dictContStatus").val(data.dictContStatus);
        			    $("#dictContStatus").trigger('change');
        			    $("#contIncomeDay").val(data.contIncomeDay);
        			    $("#contTranferDay").val(data.contTranferDay); 
        			    $("#applyBy").val(data.userName);
        			    $("#applyTo").val(data.mangerName);
        		   }
   	        	 },
   	        	 //取得失败后的处理
   	        	 function(error){
   	        		 BootstrapDialog.alert('系统错误');
   	        	 }
             ); 
        	
        }

	});
	
	//调拨合同
	$('[opt=transfer]').on('click',function(e){
		var path = ctx;
		if(!$("#searchForm").validate().form()){
			//验证不成功
			return false;
	    }
		contents_getJsonForSync(path+'/contract/changeApplyList/transferToManager',$("#searchForm").serialize(),'post',
    	 //成功取得后的处理
		 function(data){
			 if(data.result =='false'){
  			   if(data.v == -1){
  				   BootstrapDialog.alert('未找到对应的合同信息，无法调拨');
  				   return;
  			   }
  			   if(data.v == 1){
				   BootstrapDialog.alert('此合同已经属于当前理财经理，无法调拨');
				   return;
			   }
  			   if(data.v == 0 || data.v == 2){
  				   BootstrapDialog.alert('此合同非理财经理名下的已分配合同，无法调拨');
  				   return;
  			   }
  		   }else{
  			   window.location =path+'/contract/changeApplyList';
  		   }
    	 },
    	 //取得失败后的处理
    	 function(error){
    		 BootstrapDialog.alert('系统错误');
    	 }
        ); 
		
		
	});
	
	

	
	
});