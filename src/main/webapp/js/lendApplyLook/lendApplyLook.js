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
			},null
		);	
	}
	
	//全程留痕
	$('#fullTraceBtn').click(function () {
		// 调用子窗口的参数
		var applyCode = $(this).val();
		var url, argment, callback;
		url = '/lendApplyLook/fullTrace';
		argment = {'applyCode':applyCode,'operateAffiliated':'all'};
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