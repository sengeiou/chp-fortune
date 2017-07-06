$(function(){
	//上传插件的加载
	// 附件加载
	if($("#lendCode").length>0){
		 var code = $("#lendCode").val();
		 var tableId = $("#priorityId").val()+","+$("#id").val();
		 var tableName = "tz.t_tz_priority_back_pool,t_tz_loan_apply";
		contents_getJsonForSync(
			ctx+'/myPriority/backMoney/getAttachmentAjax', 
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


/**
 * 撤销
 */
function  revocationApply(priorityId){
	BootstrapDialog.confirm("请确认: 是否要撤销此申请?",function(result){
		if(result){
			contents_getJsonForSync(
				ctx+'/myPriority/backMoney/revocationApply', 
				{"priorityId":priorityId}, 
				'post',
				function(result){
					if(result.message==""||result.message==null){
						window.location.href=ctx+'/myPriority/backMoney/applyList';
					}else{
						BootstrapDialog.alert(result.message);
					}
				},
				null,null);
		}else{
			$("#revocationResult").click();
		}
	});
}

function showFullTrace(applyCode){
	
	var url, argment, callback;
	url = '/lendApplyLook/fullTrace';
	argment = {'applyCode':applyCode};
	load_callback = function(arg){};
	close_callback = function(arg){};
	var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
	//自定义出发关闭回调函数的动作
	$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});	
}

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




