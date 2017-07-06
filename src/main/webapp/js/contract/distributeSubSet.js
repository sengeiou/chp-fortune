
$(document).ready(function() { 

	 var path = ctx;

	//签收
	$('[opt=sign]').bind('click',function(e){
		var id = $(this).data('id');
		var index = $(this).data('index');
		 BootstrapDialog.confirm('确定签收？',function(result){
             if(result){
          		 contents_getJsonForSync(path+'/contract/distribute/signContract',{id:id},'post',
  			        	 //成功取得后的处理
  						 function(data){
          			         if(data == -1){
          			        	 BootstrapDialog.alert('参数错误，无法签收。');
          			        	 return;
          			         }
          			         if(data == 1){
        			        	 BootstrapDialog.alert('未派发，无法签收。');
        			        	 return;
        			         }
          			         if(data == 2){
	      			        	 BootstrapDialog.alert('已签收，无法重复签收。');
	      			        	 return;
      			             }
          			         if(data == 0){
          			        	BootstrapDialog.alert('签收成功');
          			        	$('[opt=signStatus_'+index+']').html("已签收");
	      			        	 return;
    			             }
          			         
  			        	 },
  			        	 //取得失败后的处理
  			        	 function(error){
  			        		 BootstrapDialog.alert('系统错误');
  			        	 }
  		          );
             }
             return;
        });
	});
	
}); 