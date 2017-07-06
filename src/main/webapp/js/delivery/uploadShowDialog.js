$(function(){
	  var iframe = getCurrentIframeContents();
	  $("#uploadButton",iframe).click(function(){
		  var formData = new FormData($('form')[2]);
		  var files = $('[name="file"]',$('form')[2]);
		  if(files[0].value == ""){
			  BootstrapDialog.alert("请选择需要上传的文件！");
		  }else{
			  contents_getJsonForFileUpload(
					  this, 
					  ctx+'/delivery/customer/upload', 
					  formData, 
					  function(result){
		            	   if(result == "NG"){
		            		   BootstrapDialog.alert("上传的文件没有数据！");
		            	   }else if(result == 0){
		            		   BootstrapDialog.alert("上传失败！");
		            	   }else if(result > 0){
		            		   BootstrapDialog.alert("成功上传"+result+"条数据！",function(){
		                		   location.reload();
		                	   });
		            	   }
		               },
		               function(){
		            	   BootstrapDialog.alert("系统出错,请稍后……");
		               },true);
		  }
	   })
})