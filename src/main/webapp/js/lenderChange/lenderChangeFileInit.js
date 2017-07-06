/**
 * 初始化
 */
$(function() {
	
	var pageType=$("#pageType").val();
	
	if("lenderChange_launch"==pageType){
		DropzoneHandle.initDropZone('#my-awesome-dropzone',[],{"url":ctx+'/common/file/uploadAjax?customerFile='+$("#customerCode").val()});
	}else if("lenderChangeBackForm"==pageType){
		// 附件加载
		contents_getJsonForSync(ctx + '/common/file/getAttachmentAjax', {
			'code' : $('[name="custCode"]').val(),
			'tableId' : $('[name="changeId"]').val(),
			'tableName' : 't_tz_changer'
		}, 'post', function(result) {
			DropzoneHandle.initDropZone('#my-awesome-dropzone', result, {
				"url":ctx+'/common/file/uploadAjax?customerFile='+$("#customerCode").val()
			});
		}, function() {
			BootstrapDialog.alert("加载附件发生错误");
		}, null);
	}else{
		
		// 附件加载
		contents_getJsonForSync(ctx + '/common/file/getAttachmentAjax', {
			'code' : $('[name="custCode"]').val(),
			'tableId' : $('[name="changeId"]').val(),
			'tableName' : 't_tz_changer'
		}, 'post', function(result) {
			DropzoneHandle.initDropZone('#my-awesome-dropzone', result, {
				hideDrop : true,
				addRemoveLinks : false
			});
		}, function() {
			BootstrapDialog.alert("加载附件发生错误");
		}, null);

		$("input[type=text]").attr("readonly", "readonly");
		$("input[type=checkbox]").attr("disabled", true);
		$("input[type=radio][name!=auditResult]").attr("disabled", true);

		$("#btnSubmit").click(function() {
			checkVal = $("input[name=auditResult]:checked").val();
			if (checkVal == undefined) {
				BootstrapDialog.alert("请选择审批结果");
				return;
			}
			if (checkVal == "2") {
				// 不通过，判断审批意见
				checkContent = $("#auditCheckExamine").val().replace(/\s/g, '');
				if (checkContent == "") {
					BootstrapDialog.alert("请填写审批意见");
					return;
				}
			}
			BootstrapDialog.confirm('确定提交吗？', function(result) {
				if (result) {
					$("#searchForm").submit();
					BootstrapDialog.alert('提交成功');

				} else {
				}
				return;
			});
		});
	}	
});
