

/**
 * 导出Excel
 */
function exportExcel(url){
	var count = $("#dataAmount").val();
	if (0==count) {
		BootstrapDialog.alert("无可操作数据！");
		return;
	}
	BootstrapDialog.confirm('确定导出Excel？', function(result){
        if(result){
        	// var d=$("#searchForm").serialize();
            // window.location.href=ctx+"/myDone/redemption/"+url+"?"+d;
			postFormReplaceGet($("#searchForm"),ctx+"/myDone/redemption/"+ url);
        }
    });
}