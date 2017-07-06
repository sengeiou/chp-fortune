
/*
 * 上传回息金额弹窗
 */
function uploadMoney(){
	
	var s = $("#ids").length;
	if(s == 0){
		BootstrapDialog.alert("页面中没有数据可以操作！！");
	}else{
		iframe = getCurrentIframeContents();
		$(".modal-title",iframe).html("上传回息金额");
		$("#hi").val("3");
		//弹出框参数
		var url, argment, callback;
		url = "/backReturnInterestApplyConfrim/uploadMoneyReturnWindow";
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
	
	url = ctx+"/backReturnInterestApplyConfrim/uploadMoneyReturn";
	data = formData;
	successCal = function(result){
		loadingMarkHide();
		if(result=="true"){
			BootstrapDialog.alert("操作成功",function(){loadingMarkShow();window.location.href=ctx+"/backReturnInterestApplyConfrim/loadBackReturnInterestApplyConfrimList"});
		}else{
			BootstrapDialog.alert(result,function(){loadingMarkShow();window.location.href=ctx+"/backReturnInterestApplyConfrim/loadBackReturnInterestApplyConfrimList"});
       	 }
	};
	errorCal =  function(error){
		loadingMarkHide();
   	 	BootstrapDialog.alert("服务器忙碌中······");
    }
	contents_getJsonForFileUpload("", url, data, successCal, errorCal, true);
}