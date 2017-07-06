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
	}else{
		myzone = DropzoneHandle.initDropZone('#my-awesome-dropzone',[],null);
	}
		
	//全程留痕
	$('#fullTraceBtn').click(function () {
		// 调用子窗口的参数
		var applyCode = $(this).val();
		var url, argment, callback;
		url = '/lendApplyLook/fullTrace';
		argment = {'applyCode':applyCode};
		load_callback = function(arg){};
		close_callback = function(arg){};
		var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
		//自定义出发关闭回调函数的动作
		$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});	
	});
});



/**
 * 提交优先回款申请
 */
$("#applybtn").click(function(){
	var file = $("div .dz-details").length;
	if(file==0){
		BootstrapDialog.alert("请先上传文件");
		return;
	}
	contents_getJsonForSync(
		ctx+'/myApply/redemption/applyCheck', 
		$("form").serialize(), 
		'post',
		function(result){
			if (result=='true') {
				loadingMarkShow();
				$("#applyForm").attr("method", "post").submit();
				$("#applybtn").attr("disabled","disabled");
			} else {
				BootstrapDialog.alert(result);
				return;
			}
		},
		function(){},null);
});

	
	/*
	$(function(){
		var myzone;
		// 初始化上传控件
		// || $(".redeem_relaunch").length>0
		if($(".redeem_form").length>0){
			//附件加载
			contents_getJsonForSync(
					ctx+'/common/file/getAttachmentAjax', 
					//{'code':$('[name="lendCode"]').val(),'tableId':$('[name="redemptionId"]').val(),'tableName':'t_tz_redemption'}, 
					{'code':$('[name="lendCode"]').val(),'tableId':$('[name="lendCode"]').val(),'tableName':'t_tz_redemption'}, 
					'post',
					function(result){
						if ($(".redeem_relaunch").length>0) {
							myzone = DropzoneHandle.initDropZone('#my-awesome-dropzone',result,{});
						} else {
							myzone = DropzoneHandle.initDropZone('#my-awesome-dropzone',result,{
								hideDrop:true,
								addRemoveLinks:false
							});
						}
					},
					function(){
						BootstrapDialog.alert("加载附件发生错误");
					},null);
		}else{
			myzone = DropzoneHandle.initDropZone('#my-awesome-dropzone',[],null);
		}
		
		*/	
	
	