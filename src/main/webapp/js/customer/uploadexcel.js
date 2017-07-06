value="";
//修改在职状态 
 function updateWorkinfState(){
	$.ajax({
		type : "get",
		url : ctx + "/customer/investment/updateWorkingState",
		dataType : "text",
		success : function(data) {
			BootstrapDialog.alert(data);
			value=data;
			//$("#updateWorkingState").attr("disabled", true); 
		},
		error : function(data) {
			BootstrapDialog.alert("系统错误，请联系管理员！");
		}
	});
}


/**
 * 上传excel表格
 */
 function  uploadexcel(){
	 if (""==value) {
		 BootstrapDialog.alert("请先修改在职状态");
		return;
	}
 	// 调用子窗口的参数
  	var url, argment, callback;
 	url =  "/customer/investment/uploadinvoke";
 	argment = null;
	var iframe = getCurrentIframeContents();
  	load_callback = function(iframe){
  		$('.modal-title').text("上传客户姓名和证件号");
		$('#subClose').val("提交");
	};
  	close_callback = function(arg,frame){};
  	var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
    //自定义出发关闭回调函数的动作
    $('#subClose',iframe).off('click').on('click',function(){
    	var file = $("#fileImport").val();
    	if(file ==null || file ==''){
    		BootstrapDialog.alert("请选择文件！");
			return;
    	} else if(!ifExcel(file)){
    		return;
    	}
    	
    	var d = new FormData($("#uploadForm")[0]);
  		var element = $("#subClose");
  		
  		// 异步上传
  		contents_getJsonForFileUpload(
  				element, 
  				ctx+"/customer/investment/uploadexcel",
  				d, 
  				function(result){
  					value="";
  					if(""!=result.rtn){
  						BootstrapDialog.alert(result.rtn,
						function(){
							loadingMarkShow();
							$('#btnSubmit').click()
						});
  					}else{
  						BootstrapDialog.alert("操作完成，上传客户"+result.num+"笔，此次匹配客户"+result.count+"笔，未匹配客户"+eval(result.num-result.count)+"笔",
						function(){
							loadingMarkShow();
							$('#btnSubmit').click()
						});
  					}
  				},
  				function(){
  					value="";
  					BootstrapDialog.alert("服务器繁忙");
  					return;
  				},
  				false);
    	sub.closeSubWindow();
   });
}

 /**
  * 校验文件类型是否为Excel
  * @param file
  * @returns {Boolean}
  */
 function checkIfExcel(file){
 	var suffix = file.value.substr(file.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
 	if (suffix != '.xls' && suffix !='.xlsx') {
 		BootstrapDialog.alert("文件格式错误，请上传Excel文件");
 		return false;
 	}
 	return true;
 }

 /**
  * 文件名验证
  * @param filename
  * @returns {Boolean}
  */
 function ifExcel(filename){
 	var suffix = filename.substr(filename.lastIndexOf(".")).toLowerCase();//获得文件后缀名
 	if (suffix != '.xls' && suffix !='.xlsx') {
 		BootstrapDialog.alert("文件格式错误，请上传Excel文件");
 		return false;
 	}
 	return true;
 }