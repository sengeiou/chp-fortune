
$(function(){
	  var iframe = getCurrentIframeContents();
	   // 子页面上传
	    $("#uploadButton",iframe).click(function(){
		  var formData = new FormData($('form')[2]);
		  var files = $('[name="file"]',$('form')[2]);
		  var str = files[0].value;
		  
		  if(files[0].value == ""){
			  BootstrapDialog.alert("请选择需要上传的文件！");
		  }else if(str.substr(str.length-4) != 'xlsx'){
			  BootstrapDialog.alert("文件格式错误请重新选择！");
		  }else{
			  contents_getJsonForFileUpload(
					  this, 
					  ctx+'/delivery/tripleCustomer/upload', 
					  formData, 
					  function(result){
						  var re = JSON.parse(result);
		            	   if(re.size > 0){
		            		   $("#modal-sub").modal('hide');
		            		   BootstrapDialog.alert("成功上传："+re.size+"条交割数据,批次号为："+re.num);
		            	   }else{
		            		   BootstrapDialog.alert("上传的文件没有数据！");
		            	   }
		               },
		               function(){
		            	   BootstrapDialog.alert("系统出错,请稍后……");
		               },true);
		  }
	   })
})