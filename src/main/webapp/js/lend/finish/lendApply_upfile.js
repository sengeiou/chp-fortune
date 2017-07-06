var myzone
$(function(){

	contents_getJsonForSync(
			ctx+'/common/file/getAttachmentAjax', 
			{'code':$('[name="applyCode"]').val(),'tableId':$('[name="id"]').val(),'tableName':'t_tz_loan_apply'}, 
			'post',
			function(result){
				myzone = DropzoneHandle.initDropZone('#my-awesome-dropzone',result,{
					hideDrop:false,
					addRemoveLinks:true
				}
);
			},
			function(){
				BootstrapDialog.alert("加载附件发生错误");
			},null);
})
	
	

