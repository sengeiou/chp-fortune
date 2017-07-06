function goPage(){
		window.location = ctx+'/contract/distributeList';
}

$(document).ready(function() { 

	 var path = ctx;

	//签收
	$('[opt=sign]').on('click',function(e){
		 BootstrapDialog.confirm('确定签收？',function(result){
             if(result){
          		 contents_getJsonForSync(path+'/contract/distribute/signContract',{id:$("#disId").val()},'post',
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
  					         if(data == 0){
  					        	window.location =path+'/contract/distributeList';
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