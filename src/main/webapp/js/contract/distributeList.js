	function page(n,s){
		if(n) $("#pageNo").val(n);
		if(s) $("#pageSize").val(s);
		$("#searchForm").attr("action", ctx+ "/contract/distributeList");
		$("#searchForm").submit();
		return false;
	}
	function goPage(pageURL){
		window.location = pageURL;
	}
	
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
		            		 window.location=path+'/contract/distributeList/exportExcel?'+$("#searchForm").serialize();
		    	        }else{
		    	        	var ids = ''; 
		    				$('[opt=records]:checked').each(function(index){ 
		    				   if(index == 0){
		    					   ids = $(this).data('id');
		    				   }else{
		    				       ids += ','+$(this).data('id');
		    				   }
		    				}) 
		    				window.location=path+'/contract/distributeList/checkoutExcel?ids='+ids;
		    	        }
	              }
	              return;
	        });
		});
		
		//导入Excle
		$('[opt=import]').on('click',function(e){
			
		   var iframe = getCurrentIframeContents();
		 	load_callback = function(iframe){};
		  	close_callback = function(arg,frame){};
		  	var timestamp = (new Date()).valueOf();
		  	var arg={'goAheadPage':'upload','n':timestamp};
		    var sub = SubDialogHandle.create('/contract/goPage',null,load_callback,close_callback).loadSubWindow();
		    //自定义出发关闭回调函数的动作
		    $('#subClose',iframe).off('click').on('click',function(){ 
		    	if(!$("#upload").validate().form()){
					//验证不成功
					return false;
			    }
		    	$("#upload").submit();
		    });
		    $('#subClose').html("提交");
		});
		
		//派发
		$('[opt=distribute]').on('click',function(e){
			
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
			
		     BootstrapDialog.confirm('确定派发么？',function(result){
	              if(result){
	           		 contents_getJsonForSync(path+'/contract/distributeList/distributeContract',{ids:ids},'post',
	   			        	 //成功取得后的处理
	   						 function(data){
	   					         if(data){
	   					        	window.location =path+'/contract/distributeList';
	   					         }else{
	   					        	 BootstrapDialog.alert('合同编号可能已被使用无法派发');
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
		
		//查看派发记录
		$('[opt=showDis]').on('click',function(e){
		    var iframe = getCurrentIframeContents();
		 	load_callback = function(iframe){};
		  	close_callback = function(arg,frame){};
		  	var timestamp = (new Date()).valueOf();
		  	var arg={'applyId':$(this).data('id')};
		    var sub = SubDialogHandle.create('/contract/distributeSubSet',arg,load_callback,close_callback).loadSubWindow();
		    //自定义出发关闭回调函数的动作
		    $('#subClose',iframe).off('click').on('click',function(){ 
		    	$('.close').click();
		    });
		    $('#subClose').html("关闭");
		});
		
	}); 