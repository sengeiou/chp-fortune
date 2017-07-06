function page(){
	$("#searchForm").attr("action","${ctx}/contract/changeApprovalList");
	$("#searchForm").submit();
	return false;
}
function goPage(pageURL){
	window.location = pageURL;
}
//审核结果单选按钮
function checkradio(){
	var parms=document.getElementsByName("radio");
	var i;
	for( i=0;i<parms.length;i++){
		if(parms[i].checked);
		}
	alert("您选择了"+ parms[i].value); 
}

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
	
	//变更申请审核
	$('[opt=changeApply]').on('click',function(e){
		
		 if(!$("#searchForm").validate().form()){
			//验证不成功
			return false;
	     }
		 checkVal = $("input[name=changeCheckResult]:checked").val();
		 if (checkVal == "2") {
				// 不通过，判断审批意见
				checkContent = $("#changeCheckDesc").val().replace(/\s/g, '');
				if (checkContent == "") {
					BootstrapDialog.alert("请填写审批意见");
					return;
				}
			}
		
		contents_getJsonForSync(path+'/contract/changeApplyList/updateApplyChange',$("#searchForm").serialize(),'post',
	        	 //成功取得后的处理
				 function(data){
			           if(data.result == 'false'){
			        	   if(data.v == 0){
			        		   BootstrapDialog.alert('参数错误');
			        	   }else if(data.v == 1){
			        		   BootstrapDialog.alert('申请已被审核，无法重复审核');
			        	   }else if(data.v == 2){
			        		   BootstrapDialog.alert('合同信息有误，无法审核');
			        	   }else if(data.v == 3){
			        		   BootstrapDialog.alert('合同非库存状态，审核通过');
			        	   }
			           }else{
			        	   window.location = path+'/contract/changeApprovalList?'+$.cookie('f905e27741b347a48613ebb1d0ad84df_search');
			           }
		         },
	        	 //取得失败后的处理
	        	 function(error){
	        		 BootstrapDialog.alert('系统错误');
	        	 }
         ); 
		
	});
	
	if($("#dictChangeType").val()!='3'){
	
	//附件加载
	contents_getJsonForSync(
			ctx+'/common/file/getAttachmentAjax', 
			{'code':$('[name="contCode"]').val(),'tableId':$('[name="id"]').val(),'tableName':'t_tz_contract_changest'}, 
			'post',
			function(result){
				if(result.length>0){
					DropzoneHandle.initDropZone('#my-awesome-dropzone',result,{
						hideDrop:true,
						addRemoveLinks:false
					});
				}else{
					DropzoneHandle.initDropZone('#my-awesome-dropzone',[],{
						hideDrop:true,
						addRemoveLinks:false
					});
				}
				
			},
			function(){
				BootstrapDialog.alert("加载附件发生错误");
			},null);
	
	}
});
