$(document).ready(function() { 

	//清空参数
	$('[opt=clean]').on('click',function(e){
		var form = $(this).closest('form');
		$(form).find(':text').val('');
		$(form).find('select').val('');
		$(form).find('select').trigger('change');
	});
	
	 var path = ctx;

	//全选操作
	 $(document).on('click','.table [opt=all]',function(){ 
	    if($(this).prop('checked'))
	    	$('[opt=records]').prop('checked',true);
	    else
	    	$('[opt=records]').prop('checked',false);
		
	});
		
	//导出Excle
	$('[opt=export]').on('click',function(e){
	    BootstrapDialog.confirm('确认要导出数据吗？',function(result){
            if(result){
	            if(!$('[opt=records]:checked').length){
	           		 window.location=path+'/contract/changeAlreadyList/exportOrders?'+$("#searchForm").serialize();
	   	        }else{
	   	        	var ids = ''; 
	   				$('[opt=records]:checked').each(function(index){ 
	   				   if(index == 0){
	   					   ids = $(this).data('id');
	   				   }else{
	   				       ids += ','+$(this).data('id');
	   				   }
	   				}) 
	   				window.location=path+'/contract/changeAlreadyList/checkoutOrders?ids='+ids;
	   	        }
            }
            return;
        });
	});
	
}); 