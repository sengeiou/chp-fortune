$(function() {
	var iframe = getCurrentIframeContents();
	$('#search', iframe).click(function() {
		$form = $('#searchForm');
		$("#pageNo").val("");
		$form.submit();
	})
	// 导出excel
	$(".outExcel", iframe).click(function() {
		var loginName = $("#loginName").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var newEmpCode = $("#newEmpCode").val();
		var newEmpName = $("#newEmpName").val();
		var newOrgId = $("#newOrgIdId").val();
		var customerCode = $("#customerCode").val();
		var osType = $('#osType option:selected').val();
		var deliveryType = $('#deliveryType option:selected').val();
		var empCode = $("#empCode").val();
		var empName = $("#empName").val();
		var orgId = $("#orgIdId").val();
		$('#difference').val($(this).attr('difference'));
		if (loginName == "" && startTime == "" && endTime == ""
				&& newEmpCode == "" && newEmpName == ""
				&& newOrgId == "" && empCode == ""
				&& empName == "" && orgId == ""
				&& customerCode == "" && osType == undefined
				&& deliveryType == undefined) {
			BootstrapDialog.alert("请输入搜索条件进行导出！");
		} else {
			BootstrapDialog.confirm('确定要导出Excel吗？',function(result) {
				if (result) {
					var url = ctx+"/delivery/tripleCustomer/history/outExcel";
					postFormReplaceGet($("#searchForm"),url);
				} else {
					return;
				}
			});
		}
	})
})