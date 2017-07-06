
$(document).ready(function() { 

	//清空参数
	$('[opt=clean]').on('click',function(e){
		var form = $(this).closest('form');
		$(form).find(':text').val('');
		$(form).find('select').val('');
		$(form).find('select').trigger('change');
	});
	
	 var path = ctx;

	//导出Excle
	$('[opt=export]').on('click',function(e){
	    BootstrapDialog.confirm('确认要导出数据吗？',function(result){
            if(result){
            	if(!$('[opt=records]:checked').length){
           		 window.location=path+'/contract/applyApprovalList/exportOrders?'+$("#searchForm").serialize();
	   	        }else{
	   	        	var ids = ''; 
	   				$('[opt=records]:checked').each(function(index){ 
	   				   if(index == 0){
	   					   ids = $(this).data('id');
	   				   }else{
	   				       ids += ','+$(this).data('id');
	   				   }
	   				}) 
	   				window.location=path+'/contract/applyApprovalList/checkoutOrders?ids='+ids;
	   	        }
            }
            return;
        });
	});
		
	//全选操作
	$(document).on('click','.table [opt=all]',function(){ 
	    if($(this).prop('checked'))
	    	$('[opt=records]').prop('checked',true);
	    else
	    	$('[opt=records]').prop('checked',false);
		
	});
	
	//批量通过
	$('[opt=pass]').on('click',function(e){
        
		if(!$('[opt=records]:checked').length){
			 BootstrapDialog.alert("请选择至少一条记录");
			 return;
        }
		
		var ids = ''; 
		
		$('[opt=records]:checked').each(function(index){ 
		   if(index == 0){
			   ids = $(this).data('id');
		   }else{
		       ids += ','+$(this).data('id');
		   }
		})   

        BootstrapDialog.confirm('确定批量通过吗？',function(result){
              if(result){
           		 contents_getJsonForSync(path+'/contract/contractApply/passApplication',{ids:ids},'post',
   			        	 //成功取得后的处理
   						 function(data){
	           			     if(data == '1'){
	           			    	 BootstrapDialog.alert('选择记录中包括已审核记录');
					         }
   					         if(data == '0'){
                                 $("#searchForm").submit();
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
	
	//批量拒绝
	$('[opt=reject]').on('click',function(e){
		
		if(!$('[opt=records]:checked').length){
			 BootstrapDialog.alert("请选择至少一条记录");
			 return;
        }
		
         var ids = ''; 
		
		$('[opt=records]:checked').each(function(index){ 
		   if(index == 0){
			   ids = $(this).data('id');
		   }else{
		       ids += ','+$(this).data('id');
		   }
		})   

		
        BootstrapDialog.show({
            title: "批量不通过",
            message: function() {
            	var timestamp = (new Date()).valueOf();
            	var container = $("<div></div>").load(path+'/contract/goPage?goAheadPage=reject&n='+timestamp);
                return container;
            },
            closable: true,
            buttons:[{
                cssClass: 'btn-danger',
                label: '提交',
                action: function(dialogItSelf) {
                	dialogItSelf.close();
                	var desc = $("#dictApplyRefuseDemo").find('option:selected').text();
                	var v = $("#dictApplyRefuseDemo").find('option:selected').val();
                	if(v==-1){
                		desc = $("#input_dictApplyRefuseDemo").val();
                	}
                	contents_getJsonForSync(path+'/contract/contractApply/rejectApplication',{ids:ids,dictApplyRefuseDemo:desc},'post',
      			        	 //成功取得后的处理
      						 function(data){
		                		  if(data == '1'){
			           			    	 BootstrapDialog.alert('选择记录中包括已审核记录');
							         }
      					         if(data == '0'){
      					        	 $("#searchForm").submit();
      					         }
      			        	 },
      			        	 //取得失败后的处理
      			        	 function(error){
      			        		 BootstrapDialog.alert('系统错误');
      			        	 }
      		          );
                }
            },
            {
                label: '取消',
                action: function(dialogItSelf) {
                	dialogItSelf.close();
                    return false;
                }
            }]
        });
		
	});
	
	
}); 

//清除
function qingchu(){
	document.getElementById("contStoresId").value="";
	document.getElementById("orgName").value="";
	document.getElementById("applyDay").value="";
	document.getElementById("contVersion").value="";
	document.getElementById("applyStatus").value="";
}

function goPage(pageURL){
	window.location = pageURL;
}	