$(function(){
	//上传插件的加载
	// 附件加载
	if($("#lendCode").length>0){
		 var code = $("#lendCode").val();
		 var tableId = $("#priorityId").val()+","+$("#id").val();
		 var tableName = "tz.t_tz_priority_back_pool,t_tz_loan_apply";
		contents_getJsonForSync(
			ctx+'/myPriority/examine/getAttachmentAjax', 
			{'code':code,
			'tableId':tableId,
			'tableName':tableName}, 
			'post',
			function(result){
					DropzoneHandle.initDropZone('#my-awesome-dropzone',result,{
						hideDrop:true,
						addRemoveLinks:false
					});
			},
			function(){
				BootstrapDialog.alert("加载附件发生错误");
			},null);
			
	}else{
		myzone = DropzoneHandle.initDropZone('#my-awesome-dropzone',[],null);
	}
});


function confirmSubmit(){
	var checkExaminetype = $("input[name='checkExaminetype']:checked").val();
	var checkExamine = $("textarea[name='checkExamine']").val();
	
	
	if(checkExaminetype==null){
		BootstrapDialog.alert("请选择审批结果");
		return;
	}
	if(checkExaminetype == 2 && checkExamine.length < 1 ){
		BootstrapDialog.alert("请填写审批意见");
		return;
	}
	
	contents_getJsonForSync(
			ctx+'/myPriority/examine/examineConfirm', 
			$("form").serialize(), 
			'post',
			function(result){
				if (result.message=='') {
					loadingMarkShow();
					window.location.href=ctx+"/myPriority/examine/examineList";
				} else {
					BootstrapDialog.alert(result.message);
				}
			},
			function(){},null);

}