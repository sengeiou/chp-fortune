
/*
 * 上传回息金额弹窗
 */
function uploadIsInterestWindow(){
	
	var s = $("#ids").length;
	if(s == 0){
		BootstrapDialog.alert("页面中没有数据可以操作！！");
	}else{
		iframe = getCurrentIframeContents();
		//弹出框参数
		var url, argment, callback;
		url = "/backReturnInterestApply/uploadIsInterestReturnWindow";
		argment = null;
		load_callback = function(arg){
			
		};
		close_callback = function(arg,frame){
	
		};
	    
		var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
		$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});

	}
}
/*
上传回息金额
*/
function upload(formData){
	
	url = ctx+"/backReturnInterestApply/uploadIsInterestReturn";
	data = formData;
	successCal = function(result){
		loadingMarkHide();
		if(result=="true"){
			BootstrapDialog.alert("操作成功",function(){loadingMarkShow();window.location.href=ctx+"/backReturnInterestApply/loadBackReturnInterestApplylist"});
		}else{
			BootstrapDialog.alert(result,function(){loadingMarkShow();window.location.href=ctx+"/backReturnInterestApply/loadBackReturnInterestApplylist"});
       	 }
	};
	errorCal =  function(error){
		loadingMarkHide();
   	 	BootstrapDialog.alert("系统出错联系管理员······");
    }
	contents_getJsonForFileUpload("", url, data, successCal, errorCal, true);
}



//回息申请确认多功能方法(批量回息申请确认和上传回息金额)
function uploadIsInterest(){
	// 上传回息金额
		
	var s = $("#fe").val();
	if(s != null && s != ""){
		form = $(".modal-body").find("[id=uploadMoney]");
    	var formData = new FormData(form[0]);
    	loadingMarkShow();
    	upload(formData);
    	
	}else{
		BootstrapDialog.alert("你没有选择文件！请选择文件！");
	}

}