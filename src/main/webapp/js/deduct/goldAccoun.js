jQuery(document).ready(function($){
	// 金帐户结算-->导出excl
	$("#outExcel").click(function() {
		BootstrapDialog.confirm("您确定要导出excel吗？",function(result) {
			if (result) {
				// 获取选择中的记录
				var ids = getIds();
				var url = ctx + "/goldAccoun/outExcel";
				var obj = {};
				if(ids == null || ids == ''){
					postFormReplaceGet($("#searchForm"),url);
				}else{
					obj.applyCodes = ids;
					postParamReplaceGet(obj,url);
				}
//				window.location = url + "?" + $("#searchForm").serialize()+"&applyCodes="+ids;
			}
		});
	});
	
	// 金帐户结算-->导入结果
	$("#goldUploadFile").click(function() {
		BootstrapDialog.confirm("您确定要上传回执结果吗？",function(result) {
			if (result) {
				var ids = getIds();
				$(".modal-title").html("金帐户导入回执结果");
				
				// 调用子窗口的参数
				var url, argment, callback;
				url = '/goldAccoun/importExcelPop';
				argment = null;
				load_callback = function(arg){
				};
				close_callback = function(arg){
				};
				var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
				//自定义出发关闭回调函数的动作
				$('#subClose').off('click').on('click',function(){
					sub.closeSubWindow();
					location.reload();
					loadingMarkHide();
				});
			}
		});
	})
	
	// 金帐户结算-->划拨
	$("#transfer").click(function() {
		if (isChecked()) {
			return false;
		}
		BootstrapDialog.confirm("您确定要执行金帐户划拨吗？",function(result) {
			if (result) {
				var ids = getIds();
				var url = ctx + "/goldAccoun/transfer";
				// 调用子窗口的参数
				if(ids != null || ids != ""){
					contents_getJsonForSync(url, {'applyCodes' : ids}, "post", callFunction, errorCal, null);
				}else{
					contents_getJsonForSync(url, $("#searchForm").serialize(), "post", callFunction, errorCal, null);
				}
			}
		});
	})
	
	// 金帐户失败-->导出excl
	$("#outExcelFali").click(function() {
		BootstrapDialog.confirm("您确定要导出excel吗？",function(result) {
			if (result) {
				// 获取选择中的记录
				var ids = getIds();
//				alert(ids);
				var url = ctx + "/goldAccounFali/outExcel";
				var obj = {};
				if(ids == null || ids == ''){
					postFormReplaceGet($("#searchForm"),url);
				}else{
					obj.applyCodes = ids;
					postParamReplaceGet(obj,url);
				}
//				window.location = url + "?" + $("#searchForm").serialize()+"&applyCodes="+ids;
			}
		});
	});
	
	// 金帐户失败-->导入结果
	$("#goldUploadFileFail").click(function() {
		BootstrapDialog.confirm("您确定要上传回执结果吗？",function(result) {
			if (result) {
				var ids = getIds();
				$(".modal-title").html("金帐户导入回执结果");
				
				// 调用子窗口的参数
				var url, argment, callback;
				url = '/goldAccounFali/importExcelPop';
				argment = null;
				load_callback = function(arg){
				};
				close_callback = function(arg){
				};
				var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
				//自定义出发关闭回调函数的动作
				$('#subClose').off('click').on('click',function(){
					sub.closeSubWindow();
					location.reload();
					loadingMarkHide();
				});
			}
		});
	})
	
	// 金帐户失败-->划拨
	$("#transferFail").click(function() {
		BootstrapDialog.confirm("您确定要执行金帐户划拨吗？",function(result) {
			if (result) {
				var url = ctx + "/goldAccounFali/transfer";
				var ids = getIds();
				// 调用子窗口的参数
				if(ids != null || ids != ""){
					contents_getJsonForSync(url, {'applyCodes' : ids}, "post", callFunction, errorCal, null);
				}else{
					contents_getJsonForSync(url, $("#searchForm").serialize(), "post", callFunction, errorCal, null);
				}
			}
		});
	})
	
	// 金帐户失败-->释放债权
	$("#failRelease").click(function() {
		BootstrapDialog.confirm("您确定要释放债权吗？",function(result) {
			if (result) {
				var url = ctx + "/goldAccounFali/batchReleaseCreditor"
				var ids = getIds();
				// 调用子窗口的参数
				if(ids != null || ids != ""){
					contents_getJsonForSync(url, {'ids' : ids}, "post", callFunction, errorCal, null);
				}else{
					contents_getJsonForSync(url, $("#searchForm").serialize(), "post", callFunction, errorCal, null);
				}
			}
		});
	})
	
//	// 划扣失败--释放债权
//	$("#failRelease").click(function() {
//		BootstrapDialog.confirm("您确定要批量释放债权吗？", function(result) {
//			if (result) {
//				var ids = getIds();
//				var url = ctx + "/goldAccounFali/batchReleaseCreditor";
//				contents_getJsonForSync(url, {
//					'ids' : ids
//				}, "post", callFunction, errorCal, null);
//			}
//		})
//	})
	
})


/**
 * 判断是否选择记录
 * @returns {Boolean}
 */
function isChecked(){
	 if($("input[name='checkbox']:checked:not(.checkAll)").size()==0){
//		 $.jBox.tip("您没有选中一条记录");
		 BootstrapDialog.alert('您没有选中一条记录');
		 return true;
	 }
	 return false;
}

/**
 * 获取选中ids
 */
function getIds(){
	 var ids="";
	 $("input[name='checkbox']:checked:not(.checkAll)").each(function(){
		 if($(this).val()!='on')
			 ids=ids+$(this).val()+",";
	 }) 
	 return ids;
}

/**
 *  函数回调
 */
function callFunction(result,par){
	 if(result=='ok'){
		 BootstrapDialog.alert('批量操作成功',function(){
			 location.reload();
		 });
	 }else{
		 BootstrapDialog.alert('批量操作失败:<br>'+result,function(){
			 location.reload();
		 });
	 }
}

/**
 *  函数回调失败
 */
function errorCal(){
	BootstrapDialog.alert('系统出错,请稍后……');
//	$.jBox.info("系统出错,请稍后……");
}