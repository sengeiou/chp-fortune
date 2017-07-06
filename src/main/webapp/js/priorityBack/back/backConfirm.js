$(function(){
	//上传插件的加载
	// 附件加载
	if($("#lendCode").length>0){
		 var code = $("#lendCode").val();
		 var tableId = $("#priorityId").val();
		 var tableName = "tz.t_tz_priority_back_pool";
		contents_getJsonForSync(
			ctx+'/common/file/getAttachmentAjax',
			{'code':code,
			'tableId':tableId,
			'tableName':tableName}, 
			'post',
			function(result){
					DropzoneHandle.initDropZone('#my-awesome-dropzone',result,{
//						hideDrop:true,
//						addRemoveLinks:false
					});
			},
			function(){
				BootstrapDialog.alert("加载附件发生错误");
			},null);
			
	}else{
		myzone = DropzoneHandle.initDropZone('#my-awesome-dropzone',[],null);
	}
});

//提交
function confirmSubmit(){
	contents_getJsonForSync(
			ctx+'/priorityBack/ApplyConfirm', 
			$("form").serialize(), 
			'post',
			function(result){
				if (result.message=='') {
					loadingMarkShow();
					window.location.href=ctx+"/priorityBack/backList";
				} else {
					BootstrapDialog.alert(result.message);
				}
			},
			function(){},null);

}

//撤销
function  revocationApply(){
	contents_getJsonForSync(
			ctx+'/priorityBack/revocationApply', 
			$("form").serialize(), 
			'post',
			function(result){
				if (result.message=='') {
					loadingMarkShow();
					window.location.href=ctx+"/priorityBack/backList";
				} else {
					BootstrapDialog.alert(result.message);
				}
			},
			function(){},null);
}