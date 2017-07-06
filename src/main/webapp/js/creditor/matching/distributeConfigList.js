$(document).ready(function() {
	
	 $('#search').click(function(){
		 $form = $('#searchForm');
		 $("#pageNo").val("");
		 $form.submit();
	 })
		
	//新增
	$(document).on('click','[opt=add]',function(){ 
		
		contents_getJsonForSync(ctx+'/matchingcreditor/listMakeContractStaff',{},'post',
	        	 //成功取得后的处理
				 function(data){
					 var form = $('#template').clone(false).removeAttr("id");
					 $.each(data.list,function(index,obj){
		             	$(form).find('select[name="userId"]').append("<option value='"+obj.userId+"'>"+obj.userCode+obj.name+"</option>"); 
				     });
		             
		             BootstrapDialog.show({
				            title: "新建人员配置",
				            message: function() {
				                return $(form).show();
				            },
				            closable: true,
				            buttons:[{
				                cssClass: 'cf_btn-primary',
				                label: '提交',
				                action: function(dialogItSelf) {
				                	var submitForm = dialogItSelf.$modalBody.find('form');
				                	if($(submitForm).find('[name="userId"]').val()=='' || $(submitForm).find('[name="status"]').val()==''){
				                		BootstrapDialog.alert('请选择参数');
				                		return false;
				                	}
				                	dialogItSelf.close();
				                	contents_getJsonForSync(ctx+'/matchingcreditor/addContractStaff',$(submitForm).serialize(),'post',
					           	        	 //成功取得后的处理
					           				 function(data){
					           			       if(data == '0'){
					           			    	  $("#searchForm").submit();
					           			       }else if (data == '1'){
					           			    	  BootstrapDialog.alert('请选择有效的员工');
					           			       }else if (data == '2'){
					           			    	  BootstrapDialog.alert('该员工已被添加');
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
		             
		         },
	        	 //取得失败后的处理
	        	 function(error){
	        		 BootstrapDialog.alert('系统错误');
	        	 }
		 ); 
		
	});
	
	//删除
	$(document).on('click','[opt=del]',function(){ 
		
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
		
		var status = '';
		$('[opt=records]:checked').each(function(index){ 
		       status += $(this).data('status')+',';
		})  
		var t = '';
		var len = status.split(",");
		for(var i=0;i<len.length;i++){
			if(len[i] == 1){
				t = len[i];
				break;
			}else{
				t = 0;
			}
		}
		if(t == 1){
			BootstrapDialog.alert("存在在岗数据不能执行删除操作请重新选择！");
		}else{
			 BootstrapDialog.confirm('确定删除吗？',function(result){
	             if(result){
	          		 contents_getJsonForSync(ctx+'/matchingcreditor/delContractStaff',{ids:ids},'post',
	  			        	 //成功取得后的处理
	  						 function(data){
	  					         if(data == '0'){
	                                $("#searchForm").submit();
	  					         }
	  					         if(data == '1'){
	  					        	BootstrapDialog.alert('参数错误'); 
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
		}
	});
	
	//编辑
	$(document).on('click','[opt=editSendCount]',function(){ 	
		   
		   var id = $(this).data('id');
			   
		   var v = $(this).data('count');
		   
		   var input = $("<form id='sel'><table class='table1'><tr><td><lable class='lab'>非首期账单派发数量：</lable><input type='text'  class='cf_input_text178' id='sendCount' name='sendCount' required digits='1' maxlength='9' value="+v+"></input></td></tr><tr><td><lable class='lab'>在岗状态：</lable><select name='status'  class='select78' id='zl_status'><option value='1'>在岗</option><option value='0'>离岗</option></select></td></tr></table></form>");
		 
		   BootstrapDialog.show({
	            title: "录入非首期账单数量",
	            message: function() {
	                return $(input).show();
	            },
	            closable: true,
	            buttons:[{
	                cssClass: 'cf_btn-primary',
	                label: '提交',
	                action: function(dialogItSelf) {
	                	if(!$("#sel").validate().form()){
	            			//验证不成功
	            			return false;
	            	    }
	                	var a = $("#sendCount").val();
	                	if(a==0){
	                		BootstrapDialog.alert("派发数量不能为0");
	                	}else{
	                		contents_getJsonForSync(ctx+'/matchingcreditor/updateSendCount',{sendCount:$("#sendCount").val(),id:id,status:$("#zl_status").val()},'post',
			           	        	 //成功取得后的处理
			           				 function(data){
			           			       if(data == '0'){
			           			    	  $("#searchForm").submit();
			           			       }else if (data == '1'){
			           			    	  BootstrapDialog.alert('参数无效');
			           			       }
			           	        	 },
			           	        	 //取得失败后的处理
			           	        	 function(error){
			           	        		 BootstrapDialog.alert('系统错误！');
			           	        	 }
			                 ); 
	                	}
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
	
	//批量在岗
	$(document).on('click','[opt=statusIn]',function(){
		if($('input[opt="records"]:checked').length==0){
			BootstrapDialog.alert('请选择数据');
			return false;
		}
		var ids=[];
		$.each($('input[opt="records"]:checked'),function(){
			ids.push($(this).attr('data-id'));
		});
		contents_getJsonForSync(ctx+'/matchingcreditor/updateStatus',{selectIds:ids,status:"1"},'post',
        	 //成功取得后的处理
			 function(data){
				$("#searchForm").submit();
        	 },
        	 //取得失败后的处理
        	 function(error){
        		 BootstrapDialog.alert('系统错误');
        	 }
        ); 
	});
	
	//批量离岗
	$(document).on('click','[opt=statusOut]',function(){
		if($('input[opt="records"]:checked').length==0){
			BootstrapDialog.alert('请选择数据');
			return false;
		}
		var ids=[];
		$.each($('input[opt="records"]:checked'),function(){
			ids.push($(this).attr('data-id'));
		});
		contents_getJsonForSync(ctx+'/matchingcreditor/updateStatus',{selectIds:ids,status:"0"},'post',
	        	 //成功取得后的处理
				 function(data){
					$("#searchForm").submit();
	        	 },
	        	 //取得失败后的处理
	        	 function(error){
	        		 BootstrapDialog.alert('系统错误');
	        	 }
	        ); 
	});
});