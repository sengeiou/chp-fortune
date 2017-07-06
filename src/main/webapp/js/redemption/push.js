$(function() {
	curDate = new Date();
	var linitDay = new Date($("#linitDay").val());
	curDate = curDate.getTime()>=linitDay.getTime()?curDate:linitDay;
	year = curDate.getFullYear();
	month = curDate.getMonth() + 1;
	day = curDate.getDate();
	if (month < 10) {
		month = "0" + month;
	}
	if (day < 10) {
		day = "0" + day;
	}
	var startd = year + '-' + month + '-' + day;
	var endd = $("#applyExpireDay").val();
	
	$("#backMoneyDay").attr("onfocus","WdatePicker({minDate:\'"+startd+"'\,maxDate:\'"+endd+"'\})");
});


/**
 * 特殊赎回推送
 */
function pushRedeem(){
	var backMoneyDay = $("#backMoneyDay").val(); // 客户是否回馈
	var residualAmount = $("#residualAmount").val(); // 客户回馈金额
	var redemptionType = $("#redemptionType").val(); // 赎回类型
	if (backMoneyDay ==  null || backMoneyDay == '') {
		BootstrapDialog.alert("请选择回款日期！");
		return;
	}
	curDate = new Date();
	var bd = new Date(backMoneyDay);
	if (curDate.getTime()>=bd.getTime()) {
		BootstrapDialog.alert("回款日期必须大于当前日期！");
		return;
	}
	if (redemptionType=='1') {
		// 部分赎回时判断
		if (residualAmount ==  null || residualAmount == '') {
			BootstrapDialog.alert("请选输入继续出借金额！");
			return;
		}
		if (Number(residualAmount)<50000){
			BootstrapDialog.alert("继续出借金额不得少于50000元！");
			return;
		}
	}
	loadingMarkShow();
	$("#pushForm").submit();
}
