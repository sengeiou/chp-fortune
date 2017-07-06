/**
 * 撤销
 */
function  multiRevocation(){
	var count = $("input[name='priorityId']:checked").length;
	var totalCount = $("#hDataAmount").val();
	if (count<1) {
		count = totalCount;
		if (count<1) {
			BootstrapDialog.alert("没有可以操作的数据！");
			return;
		}
	}
	var data = {};
	if ($("input[name='priorityId']:checked").length<1) {
		// 无勾选
		data = $("form").serialize();
	} else {
		// 有勾选
		data = {"prioIds":priorityIds()};
	}
	//对选择数据的状态就行校验
	contents_getJsonForSync(
			ctx + "/myPriority/backMoney/checkoutState",
			data, 
			"post", 
			function(result){
				if(result.message.length==0){
					BootstrapDialog.confirm("请确认：是否要撤销优先回款申请? ",function(r){
						if (r) {
							contents_getJsonForSync(
									ctx + "/myPriority/backMoney/multiRevocation",
									data, 
									"post", 
									function(result){
										BootstrapDialog.alert(result.message,function(){
											window.location.href=ctx+'/myPriority/backMoney/applyList';
										});
									},
									null,null
							);
						}
					});
				}else{
					BootstrapDialog.alert("请重新选择要撤销的数据，本次撤销的数据中存在其它状态");
				}
			},
			null,null
	);
}


function priorityIds(){
	if (0==$("input[name='priorityId']:checked").length) {
		return "";
	} else {
		return $("input[name='priorityId']:checked").map(function(){
		      return $(this).val();
	    }).get().join(",");
	}
}

/**
 * 导出excel
 */
function  exportExcel(){
	var count = $("input[name='priorityId']:checked").length;
	var totalCount = $("#hDataAmount").val();
	if (count<1) {
		count = totalCount;
		if (count<1) {
			BootstrapDialog.alert("没有可以操作的数据！");
			return;
		}
	}
	var data = {};
	var cou = $("input[name='priorityId']:checked").length;
	if (cou<1) {
		// 无勾选
		data = $("#searchForm").serialize();
	} else {
		// 有勾选
		data = {"prioIds":priorityIds()};
	}
	
	BootstrapDialog.confirm('确定导出？', function(result){
         if(result){
        	 postParamReplaceGet(data, ctx+"/myPriority/backMoney/exportExcel");
         }
     });

}