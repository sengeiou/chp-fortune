$(function() {
	curDate = new Date();
	var applyLendDay = new Date($("#applyLendDay").val());
	curDate = curDate.getTime()>=applyLendDay.getTime()?curDate:applyLendDay;
	year = curDate.getFullYear();
	month = curDate.getMonth() + 1;
	day = curDate.getDate();
	if (month < 10) {
		month = "0" + month;
	}
	if (day < 10) {
		day = "0" + day;
	}
	$("#redemptionTime").val(year + '-' + month + '-' + day);
	$("#linitDay").val(year + '-' + month + '-' + day);
	
	var startDay = $("#applyLendDay").val();
//	var startDay = year + '-' + month + '-' + day;
//	if(curDate.getTime()-(1*24*3600*1000)>=applyLendDay.getTime()){
//		startDay = getDate(year + '-' + month + '-' + day, -1);
//	}
	var endDay = getDate(year + '-' + month + '-' + day, 1);
	$("#linitDay").attr("onfocus","WdatePicker({minDate:\'"+startDay+"'\,maxDate:\'"+endDay+"'\})");
});


function getDate(startDate,days){
	var startDateTime = new Date(startDate).getTime();
	var daysTime = days*24*3600*1000;
	var endDate = new Date(startDateTime+daysTime);
	 var Year= endDate.getFullYear(); // ie火狐下都可以 
	 var Month= endDate.getMonth()+1; 
	 var Day = endDate.getDate(); 
	 var currentDate = ""
	 currentDate += Year + "-"; 
	if (Month >= 10 ){ 
	 currentDate += Month + "-"; 
	 } else { 
	 currentDate += "0" + Month + "-"; 
	 } 
	if (Day >= 10 ){ 
	 currentDate += Day ; 
	 }else{ 
	 currentDate += "0" + Day ; 
	 } 
	return currentDate;
}

/**
 * 全部赎回时，隐藏赎回金额输入框
 */
function redeemAll() {
	$("#rtAmount").val(null).attr("style", "display:none;");
}

/**
 * 部分赎回时，显示赎回金额输入框
 */
function redeemPart() {
	$("#rtAmount").removeAttr("style");
}

/**
 * 客户回馈选否时，不允许输入回馈金额
 */
function noFeedback() {
	var feedback = $("#feedback").val();
	if (feedback=='2') {
		$("#feedbackMoney").val(null).attr("readonly", "readonly");
	} else {
		$("#feedbackMoney").removeAttr("readonly");
	}
}


function downloadApply(lendCode) {
	var edition = $("#applyAgreementEdition").val(); // 合同版本
	var is161Contract = $("#is161Contract").val(); // 合同版本
	var customerName = $("#customerName").val(); // 客户姓名
	var d = lendCode; // 合同版本
	if (is161Contract=='1'){
		d=d+"&applyAgreementEdition="+edition;
	}
	d=d+"&customerName="+customerName;
	window.location.href = ctx+"/myApply/redemption/downloadApplyForm?lendCode="+d;
}
