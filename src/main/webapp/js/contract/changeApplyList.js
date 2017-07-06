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
			$("#checkCount").html($("[opt=records]:checked").length);
	});
		
	//导出Excle
	$('[opt=export]').on('click',function(e){
	    BootstrapDialog.confirm('确认要导出数据吗？',function(result){
            if(result){
             	if(!$('[opt=records]:checked').length){
           		 window.location=path+'/contract/changeApplyList/exportOrder?'+$("#searchForm").serialize();
   	        }else{
   	        	var ids = ''; 
   				$('[opt=records]:checked').each(function(index){ 
   				   if(index == 0){
   					   ids = $(this).data('id');
   				   }else{
   				       ids += ','+$(this).data('id');
   				   }
   				}) 
   				window.location=path+'/contract/changeApplyList/exportOrder?ids='+ids;
   	        }
            }
            return;
});
	});
		
	//导入
	$('[opt=import]').on('click',function(e){
		
		   var iframe = getCurrentIframeContents();		   
		 	load_callback = function(iframe){};
		  	close_callback = function(arg,frame){};
		  	var timestamp = (new Date()).valueOf();
		  	var arg={'goAheadPage':'upload','n':timestamp};
		    var sub = SubDialogHandle.create('/contract/goPage',arg,load_callback,close_callback).loadSubWindow();
		    //自定义出发关闭回调函数的动作
		    $('#submitBtn',iframe).off('click').on('click',function(){
		    	flag = true;
	        	$("input[type=file]").each(function(){
	        		if(this.value==''){
	        			BootstrapDialog.alert("请选择文件");
	        			flag = false;
	        			return false;
	        		}else{
	        			index = this.value.indexOf(".");
	        			endStr = this.value.substring(index+1,this.value.length);
	        			if(!(endStr.toLowerCase()=='xls'||endStr.toLowerCase()=='xlsx')){
	        				BootstrapDialog.alert("请选择Excel文件");
	        				flag = false;
	        				return false;
	        			}
	        		}
	        	});
	        	if(!flag){
	        		return;
	        	}
		    	if(!$("#upload").validate().form()){
					//验证不成功
					return false;
			    }
		    	
		    	var form = $(".modal-body").find("[id=upload]");
	        	var formData = new FormData(form[0]);
	        	contents_getJsonForFileUpload(
	   				 this, 
	   				 ctx+'/contract/changeApplyList/importExcel', 
	   				 formData,
	   				 function(res){
	   					 BootstrapDialog.alert(res,function(){
	   						//window.location.reload();
	   						sub.closeSubWindow();
	   						$("#search").click();
	   					 });
	                 },
	                 function(){
	                	 BootstrapDialog.alert("上传失败")
	                 },
	                 true
	            );
		    });
		    
		    $('#subClose',iframe).off('click').on('click',function(){
		    	sub.closeSubWindow();
		    });
		    
	});
	
	//合同分配
	$('[opt=distribute]').on('click',function(e){
		
		if(!$('[opt=records]:checked').length){
			 BootstrapDialog.alert("请选择至少一条记录");
			 return;
        }
		
		var ids = ''; 
		var flag = 0;
		$('[opt=records]:checked').each(function(index){ 
		   if($(this).data('belong') != 1 || $(this).data('cs') != 0){
			   BootstrapDialog.alert("只能对未分配的库存合同进行分配");
			   flag = 1;
			   return false;
		   }
		   if(index == 0){
			   ids = $(this).data('id');
		   }else{
		       ids += ','+$(this).data('id');
		   }
		});
		if(flag)return;
		contents_getJsonForSync(path+'/contract/changeApplyList/listManager',{},'post',
	        	 //成功取得后的处理
				 function(data){
			        var select = $("<form id='sel'><span>理财经理：<select class='select78' select_required='1' id='userId'><option value='-1' class='customval'>请选择</option></select></span></form>");
                    $.each(data.list,function(index,obj){
                    	$(select).find('select').append("<option value='"+obj.userId+"'>"+obj.userName+"</option>"); 
			        });
			        BootstrapDialog.show({
			            title: "合同分配",
			            message: function() {
			                return $(select).show();
			            },
			            closable: true,
			            buttons:[{
			                cssClass: 'btn cf_btn-primary',
			                label: '提交',
			                action: function(dialogItSelf) {
			                	if(!$("#sel").validate().form()){
			            			//验证不成功
			            			return false;
			            	     }
			                	if($("#userId").val() == '-1'){
			                		 BootstrapDialog.alert('请选择理财经理');
			                		 return false;
			                	}
			                	dialogItSelf.close();
			                	contents_getJsonForSync(path+'/contract/changeApplyList/distributeToManager',{ids:ids,userId:$("#userId").val()},'post',
			           	        	 //成功取得后的处理
			           				 function(data){
			           			       if(data){
			           			    	  $("#searchForm").submit();
			           			       }else{
			           			    	  BootstrapDialog.alert('理财经理不存在');
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
	
	//合同调回
	$('[opt=recall]').on('click',function(e){
		
		if(!$('[opt=records]:checked').length){
			 BootstrapDialog.alert("请选择至少一条记录");
			 return;
        }
		
		var ids = ''; 
		var flag = 0;
		$('[opt=records]:checked').each(function(index){
			if($(this).data('belong') == 1 || $(this).data('cs') != 1 || $(this).data('changeApply') ){
			   BootstrapDialog.alert("合同必须为已分配 归属不为门店且未提交状态变更");
			   flag = 1;
			   return false;
			}
		   if(index == 0){
			   ids = $(this).data('id');
		   }else{
		       ids += ','+$(this).data('id');
		   }
		});
		
		if(flag)return;
		
	    BootstrapDialog.confirm('确定合同调回么？',function(result){
              if(result){
           		 contents_getJsonForSync(path+'/contract/changeApplyList/recallContract',{ids:ids},'post',
		        	 //成功取得后的处理
					 function(data){
				         if(data){
				        	window.location =path+'/contract/changeApplyList';
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
	
	//合同调拨
	$('[opt=transfer]').on('click',function(e){
		
		if($('[opt=records]:checked').length != 1){
			 BootstrapDialog.alert("请选择一条记录");
			 return;
        }
		
		var flag = 0;
		var ids = ''; 
		$('[opt=records]:checked').each(function(index){
			if($(this).data('belong') == 1 || $(this).data('cs') != 1 || $(this).data('changeApply') ){
				   BootstrapDialog.alert("合同必须为已分配 归属不为门店且未提交状态变更");
				   flag = 1;
				   return false;
			}
		   if(index == 0){
			   ids = $(this).data('id');
		   }else{
		       ids += ','+$(this).data('id');
		   }
		});
		if(flag)return;
		
		window.location.href=path+'/contract/transferContract?contCode='+ids;
		
	});
	$("#checkCount").html($("[opt=records]:checked").length);
}); 

/**
 * 单个checkBox状态变化
 * @param src操作元素
 */
function checkOne(src){
	
	$("#checkCount").html($("[opt=records]:checked").length);
}

/**
 * 打开合同明细
 */
function openDetail(contractNumber){
	var argment={};
	argment.contractNumber=contractNumber;
	 var url="/contract/changeApplyList/lendQueryContract";
	 sub = SubDialogHandle.create(url,argment,null,null).loadSubWindow();
	$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
}


/**
 * 全程留痕分页
 * @param n
 * @param s
 * @returns {Boolean}
 */
function subPage(n,s){
	if (n)
		$("#pageNo1").val(n);
	if (s)
		$("#pageSize1").val(s);
	var url = $("#traceForm").attr('action');
	contents_getJsonForHtml(
		url,
		$("#traceForm").serialize(),
		'post',
		function(result){
			$('#traceList').html(result);
		}
	);
	return false;
}

// 全程留痕
function fullTraceBtn(contractNumber) {
	
	var url, argment, callback;
	url = '/contract/changeApplyList/fullTrace' ;
	argment = {'contractNumber':contractNumber};
	load_callback = function(arg){};
	close_callback = function(arg){};
	var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
	//自定义出发关闭回调函数的动作
	$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});	
};
