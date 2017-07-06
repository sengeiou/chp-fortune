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
	
	//变更类型
	$('#dictChangeType').on('change',function(e){
         var v = $(this).val();
         if(v == 1){   //遗失
        	 $("#financial_li").show();
        	 $("#file_li").hide();
        	 $("#store_li").hide();
         }
         else if(v == 2){   //作废
        	 $("#store_li").hide();
        	 $("#financial_li").show();
        	 $("#file_li").show();
         } 
         else if(v == 3){   //转借
        	 $("#financial_li").hide();   //理财经理
        	 $("#file_li").hide();  			//上传文件
        	 $("#store_li").show();
         }else{
        	 $("#financial_li").hide();
        	 $("#file_li").hide();
        	 $("#store_li").hide();
         }
        
	});
	
	var updateFileObj;
	
	//变更申请
	$('[opt=changeApply]').on('click',function(e){
		
		var changeType = $('#dictChangeType').val();
		if(changeType && changeType=='2') {  	//废弃
			files = updateFileObj.getAcceptedFiles();
			if(files.length==0){
				BootstrapDialog.alert("请先上传文件");
				return;
			}
		}
		
	
		if(!$("#searchForm").validate().form()){
			//验证不成功
			return false;
	     }
		
		contents_getJsonForSync(path+'/contract/changeApplyList/applyChange',$("#searchForm").serialize(),'post',
	        	 //成功取得后的处理
				 function(data){
			       if(data.result =='false'){
			    	   if(data.v == -1){
			    		   BootstrapDialog.alert('未找到对应的合同信息');
			    		   return;
			    	   }else if(data.v == 0){
			    		   BootstrapDialog.alert('此合同的变更申请已被提交过，无法重复提交申请');
			    		   return;
			    	   }else if(data.v == 1){
			    		   BootstrapDialog.alert('此合同遗失作废，无法提交申请');
			    		   return;
			    	   }else if(data.v == 2){
			    		   BootstrapDialog.alert('此合同签错作废，无法提交申请');
			    		   return;
			    	   }else if(data.v == 3){
			    		   BootstrapDialog.alert('此合同已出借，无法提交申请');
			    		   return;
			    	   }else if(data.v == 4){
			    		   BootstrapDialog.alert('非库存合同，无法提交申请');
			    		   return;
			    	   }else if(data.v == 5){
			    		   BootstrapDialog.alert('此合同已经属于该门店，无法提交申请');
			    		   return;
			    	   }
			    	   
			       }else{
			    	   BootstrapDialog.alert('此合同的变更申请已提交，等待审核中');
			    	   
			    	   var url=ctx+"/contract/changeApplyList?"+$.cookie('d989c49074044ee2b23dc33d74965749_search');
			    		window.location = url;
			    	   return;
			       }
	        	 },
	        	 //取得失败后的处理
	        	 function(error){
	        		 BootstrapDialog.alert('系统错误');
	        	 }
         ); 
		
	});
	
	//初始化附件控件
	updateFileObj =  DropzoneHandle.initDropZone('#my-awesome-dropzone',null,{
			hideDrop:false,
			addRemoveLinks:true
		});
	
});


function page(){
	$("#searchForm").attr("action","${ctx}/contract/changeApplyList");
	$("#searchForm").submit();
	return false;
}
function goPage(pageURL){
	window.locttion = pageURL;
}