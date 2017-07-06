$(document).ready(function() {
	// 提交检索条件
	$('#search').click(function(){
		$form = $('#searchForm');
		if ($form.validate().form()) {
			$("#pageNo").val("");
			$form.submit();
		}
	});
	// 点击多选框计算金额
	var sumMoney=0;
	var sumNum = 0;
	$("input[type='checkbox']#checkOne").change(function(){
		 if ($(this).prop("checked")){
			sumMoney = parseFloat(restoreFormatCurrency($(this).parent('#creditValueIdCheckbox').nextAll("#loanCreditValue").text()))+sumMoney;
			sumNum = sumNum+1;
			 $("#money").text(formatCurrency(sumMoney));
			 $("#count").text(sumNum);
		 } else{
			sumMoney = parseFloat(jsSub(sumMoney,restoreFormatCurrency($(this).parent('#creditValueIdCheckbox').nextAll("#loanCreditValue").text())));
			sumNum = sumNum-1;
			if(sumNum==0){
				$("#money").text($("#hiddenMoney").val());
				 $("#count").text($("#hiddenCount").val());
			}else{
				$("#money").text(formatCurrency(sumMoney));
				$("#count").text(sumNum);
			}
		 }
	});
	// 全选时计算
	$("input[type='checkbox'].checkAll").change(function(){
		sumMoney=0;
		sumNum = 0;
		if($(this).prop("checked")){
			$("#checkOne:checked").each(function(){
				sumMoney = parseFloat(sumMoney) + parseFloat(restoreFormatCurrency($(this).parent('#creditValueIdCheckbox').nextAll("#loanCreditValue").text()));
				sumNum++;
				$("#money").text(formatCurrency(sumMoney));
				 $("#count").text(sumNum);
			})	
		}else{
			$("#money").text(formatCurrency($("#hiddenMoney").val()));
			 $("#count").text($("#hiddenCount").val());
		}
	});
	
	
});





function pushBorrow(){
	$("#pushBorrowButtun").attr("disabled","disabled");
	var i=0;
	var data = new Array();
	$("#checkOne:checked").each(function(){
		data[i++] = $(this).val();
	})
	if(data.length>0){	
		$("#creditValueIdList").val(data);
	}else{
		$("#creditValueIdList").val("");
		if(
				($("input[name='borrowerName']").val()==null || $("input[name='borrowerName']").val()=="")  &&
				($("input[name='borrowCreditValueFrom']").val()==null || $("input[name='borrowCreditValueFrom']").val()=="")  &&
				($("input[name='borrowCreditValueTo']").val()==null || $("input[name='borrowCreditValueTo']").val()=="")  &&
				($("input[name='borrowMonthsSurplusFrom']").val()==null || $("input[name='borrowMonthsSurplusFrom']").val()=="")  &&
				($("input[name='borrowMonthsSurplusTo']").val()==null || $("input[name='borrowMonthsSurplusTo']").val()=="")  &&
				($("select[name='borrowBakcmoneyDay']").val()==null || $("select[name='borrowBakcmoneyDay']").val()=="")  &&
				($("input[name='borrowMonthRate']").val()==null || $("input[name='borrowMonthRate']").val()=="")  &&
				($("input[name='borrowBackmoneyFirdayFrom']").val()==null || $("input[name='borrowBackmoneyFirdayFrom']").val()=="")  &&
				($("input[name='borrowBackmoneyFirdayTo']").val()==null || $("input[name='borrowBackmoneyFirdayTo']").val()=="")  &&
				($("select[name='borrowerJob']").val()==null || $("select[name='borrowerJob']").val()=="")  &&
				($("select[name='borrowType']").val()==null || $("select[name='borrowType']").val()=="")  &&
				($("select[name='borrowTrusteeFlag']").val()==null || $("select[name='borrowTrusteeFlag']").val()=="")  &&
				($("select[name='dicLoanDistinguish']").val()==null || $("select[name='dicLoanDistinguish']").val()=="") && 
				($("input[name='pushBorrowTimeFrom']").val()==null || $("input[name='pushBorrowTimeFrom']").val()=="") && 
				($("input[name='pushBorrowTimeTo']").val()==null || $("input[name='pushBorrowTimeTo']").val()=="")  &&
				($("select[name='pushBorrowStatus']").val()==null || $("select[name='pushBorrowStatus']").val()=="")  &&
				($("input[name='loanBackmoneyLastdayFrom']").val()==null || $("input[name='loanBackmoneyLastdayFrom']").val()=="") &&  
				($("input[name='loanBackmoneyLastdayTo']").val()==null || $("input[name='loanBackmoneyLastdayTo']").val()=="")  
		){
			$("#pushBorrowButtun").removeAttr("disabled");
			BootstrapDialog.alert("请输入查询条件");
			return;
		}
		 
	}
	$("#pushHiddenMoney").val($("#money").text());
	$("#pushHiddenMoneyOneByOne").val($("#moneyOneByOne").text());
	$("#pushHiddenCount").val($("#count").text());
	contents_getJsonForSync(
		ctx+"/borrow/checkPushBorrowList",	
		$("#searchForm").serialize(),
		"POST",
		function(result){
			//返回0000代表成功
			if (result.code == '0000') {
					BootstrapDialog.confirm(result.message, function(result){
						if(result){
							contents_getJsonForSync(
									ctx+"/borrow/pushBorrowListDJR",	
									$("#searchForm").serialize(),
									"POST",
									function(result){
										if (result.code == '0000') {
												BootstrapDialog.alert(result.message,function() { 
												$("#searchForm").submit();
											});
										}else{
											$("#pushBorrowButtun").removeAttr("disabled");
											BootstrapDialog.alert(result.message);
								    		 return;
										}
									});
							
						}else{
							$("#pushBorrowButtun").removeAttr("disabled");
							return;
							}
					});
			} else {
				$("#pushBorrowButtun").removeAttr("disabled");
				BootstrapDialog.alert(result.message);
	    		 return;
			}
		});

}







function releaseBorrow(){
	$("#releaseBorrowbuttun").attr("disabled","disabled");
	var i=0;
	var data = new Array();
	$("#checkOne:checked").each(function(){
		data[i++] = $(this).val();
	})
	if(data.length>0){	
		$("#creditValueIdList").val(data);
	}else{
		$("#creditValueIdList").val("");
		if(
				($("input[name='borrowerName']").val()==null || $("input[name='borrowerName']").val()=="")  &&
				($("input[name='borrowCreditValueFrom']").val()==null || $("input[name='borrowCreditValueFrom']").val()=="")  &&
				($("input[name='borrowCreditValueTo']").val()==null || $("input[name='borrowCreditValueTo']").val()=="")  &&
				($("input[name='borrowMonthsSurplusFrom']").val()==null || $("input[name='borrowMonthsSurplusFrom']").val()=="")  &&
				($("input[name='borrowMonthsSurplusTo']").val()==null || $("input[name='borrowMonthsSurplusTo']").val()=="")  &&
				($("select[name='borrowBakcmoneyDay']").val()==null || $("select[name='borrowBakcmoneyDay']").val()=="")  &&
				($("input[name='borrowMonthRate']").val()==null || $("input[name='borrowMonthRate']").val()=="")  &&
				($("input[name='borrowBackmoneyFirdayFrom']").val()==null || $("input[name='borrowBackmoneyFirdayFrom']").val()=="")  &&
				($("input[name='borrowBackmoneyFirdayTo']").val()==null || $("input[name='borrowBackmoneyFirdayTo']").val()=="")  &&
				($("select[name='borrowerJob']").val()==null || $("select[name='borrowerJob']").val()=="")  &&
				($("select[name='borrowType']").val()==null || $("select[name='borrowType']").val()=="")  &&
				($("select[name='borrowTrusteeFlag']").val()==null || $("select[name='borrowTrusteeFlag']").val()=="")  &&
				($("select[name='dicLoanDistinguish']").val()==null || $("select[name='dicLoanDistinguish']").val()=="") && 
				($("input[name='pushBorrowTimeFrom']").val()==null || $("input[name='pushBorrowTimeFrom']").val()=="") && 
				($("input[name='pushBorrowTimeTo']").val()==null || $("input[name='pushBorrowTimeTo']").val()=="")  &&
				($("select[name='pushBorrowStatus']").val()==null || $("select[name='pushBorrowStatus']").val()=="")  &&
				($("input[name='loanBackmoneyLastdayFrom']").val()==null || $("input[name='loanBackmoneyLastdayFrom']").val()=="") &&  
				($("input[name='loanBackmoneyLastdayTo']").val()==null || $("input[name='loanBackmoneyLastdayTo']").val()=="")   
		){
			$("#releaseBorrowbuttun").removeAttr("disabled");
			BootstrapDialog.alert("请输入查询条件");
			return;
		}
		 
	}
	$("#pushHiddenMoney").val($("#money").text());
	$("#pushHiddenMoneyOneByOne").val($("#moneyOneByOne").text());
	$("#pushHiddenCount").val($("#count").text());
	contents_getJsonForSync(
		ctx+"/borrow/checkReleaseBorrow",	
		$("#searchForm").serialize(),
		"POST",
		function(result){
			//返回0000代表成功
			if (result.code == '0000') {
					BootstrapDialog.confirm(result.message, function(result){
						if(result){
							contents_getJsonForSync(
									ctx+"/borrow/updatePushBorrowadd",	
									$("#searchForm").serialize(),
									"POST",
									function(result){
										if (result.code == '0000') {
												BootstrapDialog.alert(result.message,function() { 
												$("#searchForm").submit();
											});
										}else{
											$("#releaseBorrowbuttun").removeAttr("disabled");
											BootstrapDialog.alert(result.message);
								    		 return;
										}
									});
							
						}else{
							$("#releaseBorrowbuttun").removeAttr("disabled");
							return;
							}
					});
			} else {
				$("#releaseBorrowbuttun").removeAttr("disabled");
				BootstrapDialog.alert(result.message);
	    		 return;
			}
		});

}



