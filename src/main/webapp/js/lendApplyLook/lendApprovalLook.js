/*
 *出借申请审批查看列表js 
*/

$(function(){
	//上传插件的加载
	// 附件加载
	if($("#applyCode").length>0){
		 var code = $("#applyCode").val();
		 var tableId = $("#tableId").val();
			contents_getJsonForSync(
					ctx+'/common/file/getAttachmentAjax', 
					{'code':$("#applyCode").val(),
					'tableId':$("#tableId").val(),
					'tableName':'t_tz_loan_apply'}, 
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
	}
});