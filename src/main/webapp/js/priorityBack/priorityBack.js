$(function(){
	//上传插件的加载
	if($("#priorityId").length>0){
		 var code = $("#applyCode").val();
		 var tableId = $("#priorityId").val();
		contents_getJsonForSync(
			ctx+'/common/file/getAttachmentAjax', 
			{'code':code,
			'tableId':tableId,
			'tableName':'tz.t_tz_priority_back_pool'}, 
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
		myzone = DropzoneHandle.initDropZone('#my-awesome-dropzone',null,
			{"url":ctx+'/common/file/uploadAjax?customerFile='+$("#applyCode").val()});
	}
	
	//优先回款申请
	$("#applybtn").click(function(){
		var file = $("div .dz-details").length;
		if(file==0){
			BootstrapDialog.alert("请先上传文件");
			return;
		}
		
		var date=$("#priorityForm").serialize();
		contents_getJsonForSync(
				ctx+'/lendApplyLook/applyPriorityBack', 
				$("#priorityForm").serialize(), 
				'post',
				function(result){
					if (result.message=='') {
						loadingMarkShow();
						window.location.href=ctx+"/lendApplyLook/loadLendApplyLookList";
					} else {
						BootstrapDialog.alert(result.message);
					}
				},
				function(){},null);
		
	});
	
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
 * 全程留痕分页
 * @param n
 * @param s
 * @returns {Boolean}
 */
function subPage(n,s){
	if (n)
		$("#pageNo1").val(n);
	if (s)
		$("#pageSize1").val(s);
	var url = $("#traceForm").attr('action');
	contents_getJsonForHtml(
		url,
		$("#traceForm").serialize(),
		'post',
		function(result){
			$('#traceList').html(result);
		}
	);
	return false;
}	


