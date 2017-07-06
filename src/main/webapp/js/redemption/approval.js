/**
 * 显示特批金额输入框，特批备注输入框
 */
function showSp() {
	$("#T1").removeAttr("style");
	$("#T2").removeAttr("style");
	var v = $("#redemptionMoney").val();
	$("#checkSpmoney").val(v);
}

/**
 * 隐藏特批金额输入框，特批备注输入框
 */
function hideSp() {
	$("#T1").attr("style", "display:none;");
	$("#T2").attr("style", "display:none;");
	$("#checkSpmoney").val(0);
}

/**
 * 提交审批结果
 */
function redeemApproval() {
	var bmd = $("#backMoneyDay").val(); // 回款日期
	var cs = $("input[name='checkSp']:checked").val(); // 是否特批
	var csm = $("#checkSpmoney").val(); // 特批实际回款金额
	var csr = $("#checkSpremarks").val(); // 特批批注
	var cet = $("input[name='checkExaminetype']:checked").val(); // 审批结果
	var ce = $("#checkExamine").val(); // 不通过原因
	var redemptionBMoney = $("#redemptionBMoney").val(); // 回款金额
	var redemptionDeMoney = $("#redemptionDeMoney").val(); // 服务费
	var residualAmount = $("#residualAmount").val(); // 剩余金额
	var redemptionType = $("#redemptionType").val(); // 赎回类型
	var fb = $("#feedback").val(); // 客户是否回馈
	var fbm = $("#feedbackMoney").val(); // 回馈金额
	
	if(cet==null || cet==''){
		BootstrapDialog.alert("请选择审批结果！");
		return;
	}
	
	if (cet=='2') {
		// 审批不通过时增加判断
		if (ce==null || ce =='') {
			BootstrapDialog.alert("请输入退回原因！");
			return;
		}
	} else {
		// 部分赎回时判断
		if (redemptionType=='1') {
			if (cs!='1') {
				// 不是特批时增加判断
				if (redemptionBMoney=='') {
					BootstrapDialog.alert("请输入回款金额！");
					return;
				}
				if (Number(redemptionBMoney)<0) {
					BootstrapDialog.alert("回款金额不得小于0！");
					return;
				}
				if (redemptionDeMoney=='') {
					BootstrapDialog.alert("请输入服务费！");
					return;
				}
				if (Number(redemptionDeMoney)<0) {
					BootstrapDialog.alert("服务费不得小于0！");
					return;
				}
			}
			if (residualAmount=='') {
				BootstrapDialog.alert("请输入剩余金额！");
				return;
			}
			if (Number(residualAmount)<0) {
				BootstrapDialog.alert("剩余金额不得小于0！");
				return;
			}
			
		}
		
		if (bmd==null || bmd =='') {
			BootstrapDialog.alert("请输入回款日期！");
			return;
		}
		if (cs=='1') {
			// 特批时增加判断
			if (csm==null || csm =='') {
				BootstrapDialog.alert("请输入特批实际回款金额！");
				return;
			}
			if (csr==null || csr =='') {
				BootstrapDialog.alert("请输入特批批注！");
				return;
			}
			if (fb=='1') {
				if (Number(csm) < Number(fbm)) {
					BootstrapDialog.alert("特批金额不得小于客户回馈金额！");
					return;
				}
			}
		}
	}
	
	if (redemptionType=='1' && cet!='2') {
		// 审批通过，部分赎回时剩余金额验证
		contents_getJsonForSync(
				ctx+'/myApply/redemption/dispatchFlowCheck', 
				$("#approvalForm").serialize(), 
				'post',
				function(result){
					if (result=='true') {
						loadingMarkShow();
						$("#approvalForm").attr("method", "post").submit();
					} else {
						BootstrapDialog.alert(result);
						return;
					}
				},
				function(){},null);
	} else {
		loadingMarkShow();
		$("#approvalForm").attr("method", "post").submit();
	}
}

// 计算多少天后的日期
function getTargetDate(startDate,days){
	var startDateTime = new Date(startDate).getTime();
	var daysTime = days*24*3600*1000;
	var endDate = new Date(startDateTime+daysTime);
	 var Year= endDate.getFullYear();//ie火狐下都可以 
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

//根据请选择回款期限规定回款日期区间
function getBackMoneyDay(){

	var type = $("input[name='redemptionReceType']").val();
	// 到期日期
	var linitDay = getTargetDate($("input[name='linitDay']").val(),1);
	if(type=='1' || type == '4'){//30日内
		var endDate = getTargetDate(linitDay,29);
		$("#backMoneyDay").attr("onfocus","WdatePicker({minDate:\'"+linitDay+"'\,maxDate:\'"+endDate+"'\})");
	}else if(type=='2'|| type == '5'|| type=='9'){//30日后60日内
		var linitDayTemp = getTargetDate(linitDay,30)
		var endDate = getTargetDate(linitDay,59);
		$("#backMoneyDay").attr("onfocus","WdatePicker({minDate:\'"+linitDayTemp+"'\,maxDate:\'"+endDate+"'\})");
	}else if(type == '3' || type == '6'){// 60日后
		var linitDayTemp = getTargetDate(linitDay,60);
		$("#backMoneyDay").attr("onfocus","WdatePicker({minDate:\'"+linitDayTemp+"'\})");
	}else if(type == '7'){// 7日内
		var endDate = getTargetDate(linitDay,6);
		$("#backMoneyDay").attr("onfocus","WdatePicker({minDate:\'"+linitDay+"'\,maxDate:\'"+endDate+"'\})");
	}else if(type == '8'){// 7日后30日内
		var linitDayTemp = getTargetDate(linitDay,7)
		var endDate = getTargetDate(linitDay,29);
		$("#backMoneyDay").attr("onfocus","WdatePicker({minDate:\'"+linitDayTemp+"'\,maxDate:\'"+endDate+"'\})");
	}else if(type == '10'){// 60日后90日内
		var linitDayTemp = getTargetDate(linitDay,60)
		var endDate = getTargetDate(linitDay,89);
		$("#backMoneyDay").attr("onfocus","WdatePicker({minDate:\'"+linitDayTemp+"'\,maxDate:\'"+endDate+"'\})");
	}else{
		// 收取成功转让金额5%的转让服务费
		curDate = new Date();
		var applyLendDay = new Date($("#applyLendDay").val());
		if (curDate.getTime()<applyLendDay.getTime()) {
			curDate = applyLendDay;
		}
		year = curDate.getFullYear();
		month = curDate.getMonth() + 1;
		day = curDate.getDate();
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}
		var cDate = year + '-' + month + '-' + day;
		var linitDay= getTargetDate(cDate,1)
		
		var endd = $("#applyExpireDay").val();
		$("#backMoneyDay").attr("onfocus","WdatePicker({minDate:\'"+linitDay+"'\,maxDate:\'"+endd+"'\})");
	}

}

$(function(){
	// 当页面加载完成后, 根据请选择回款期限规定回款日期区间
	getBackMoneyDay();
	// 当页面加载完成后点击到期日期, 根据请选择回款期限规定回款日期区间
	$("input[name='linitDay']").blur(function(){
		getBackMoneyDay();
		$("#backMoneyDay").val(null);
	});
	
})


