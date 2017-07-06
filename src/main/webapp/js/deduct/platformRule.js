
/**
 * 新增规则
 */
function addRule(){
		
	// 调用子窗口的参数
 	var url, argment, callback;
 	url = "/deduct/platform/toAddRule";
 	argment = null;
	var iframe = getCurrentIframeContents();
 	load_callback = function(iframe){ };
 	close_callback = function(arg,frame){
 		var d = $(iframe).find('#addForm').serialize();
 		var params = d + '&' + $(frame).find('form').serialize();
		window.location.href = ctx + "/deduct/platform/addRule?" + d;
 	};
   var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
   //自定义出发关闭回调函数的动作
   $('#subClose',iframe).off('click').on('click',function(){
 	  sub.closeSubWindow();
   });
}

/**
 * 启用规则
 */
function startUse(){
	var count = $("input[type='checkbox']:checked").length;
	if (count<1) {
		BootstrapDialog.alert("没有可以操作的数据！");
		return;
	}
	
	BootstrapDialog.confirm("共"+count+"条数据，是否继续操作？点击OK继续",
		function(r){
			if (r) {
				$("#searchForm")
				  .attr("action",ctx + "/deduct/platform/startUse")
				  .attr("method","post")
				  .submit();
			}
	});
}

/**
 * 停用规则
 */
function stopUse(){
	var count = $("input[type='checkbox']:checked").length;
	if (count<1) {
		BootstrapDialog.alert("没有可以操作的数据！");
		return;
	}
	
	BootstrapDialog.confirm("共"+count+"条数据，是否继续操作？点击OK继续",
		function(r){
			if (r) {
				$("#searchForm")
				  .attr("action",ctx + "/deduct/platform/stopUse")
				  .attr("method","post")
				  .submit();
			}
	});
}

/**
 * 编辑规则
 */
function editRule(id){
	
	// 调用子窗口的参数
	var url, argment, callback;
	url = "/deduct/platform/toEditRule";
	argment = {'id':id};
	var iframe = getCurrentIframeContents();
	load_callback = function(iframe){ };
	close_callback = function(arg,frame){
		var d = $(iframe).find('#editForm').serialize();
		var params = d + '&' + $(frame).find('form').serialize();
		window.location.href = ctx + "/deduct/platform/editRule?" + d;
	};
	var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
	//自定义出发关闭回调函数的动作
	$('#subClose',iframe).off('click').on('click',function(){
		sub.closeSubWindow();
	});
}

/**
 * 编辑规则
 */
function examine(){
	
	// 调用子窗口的参数
	var url, argment, callback;
	url = "/deduct/platform/toExamine";
	argment = null;
	var iframe = getCurrentIframeContents();
	load_callback = function(iframe){ };
	close_callback = function(arg,frame){
		var d = $(iframe).find('form').serialize();
		var params = d + '&' + $(frame).find('form').serialize();
		window.location.href = ctx + "/deduct/platform/ruleExamine?" + d;
	};
	var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
	//自定义出发关闭回调函数的动作
	$('#subClose',iframe).off('click').on('click',function(){
		sub.closeSubWindow();
	});
}