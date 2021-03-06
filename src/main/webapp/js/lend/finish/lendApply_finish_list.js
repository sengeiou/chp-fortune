function _makingFile(lendCode,applyId){
	contents_getJsonForSync(
		ctx+"/lend/apply/makingFile",
		{'lendCode':lendCode,'applyId':applyId},
		'post',
		function(res){
			BootstrapDialog.alert(res.message,function(){$("#btnSubmit").click();});
		}
	);
}

function _exportExcel(){
	var s = $("#ids").length;
	if(s == 0){
		BootstrapDialog.alert("页面中没有数据可以导出!");
	}else{
		var uri = "lend/finish/exportExcel";
		var iframe = getCurrentIframeContents();
		var forms = $(iframe).find('#searchForm').serialize();
	
		// 获取选择中的记录
		inst = $('[name="ids"]:checked').map(function() {
			return this.value;
		}).get().join(',');
		
		var len = $('[name="ids"]:checked').length;
		if(len==0){
			$obj = $("#searchForm");
			var s = $obj.attr("action");
			$obj.attr("action", ctx + "/"+uri);
			$obj.submit();
			$obj.attr("action", s);
			
		}else{
			var params = {"applyIds":inst};
			var action = ctx+"/"+uri;
			postParamReplaceGet(params,action);
		}
	}
}

function dateCheck(src){
	$(src).valid();
}