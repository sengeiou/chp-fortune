
/**
 * jisuanmoney
 */
jQuery(document).ready(function($){	
	// 金额计算
	$("input[name=checkbox]:not(.checkAll)").click(function(){
		var totalCount  = 0;
		var totalLendMoney = 0;
		var totalDeductMoney = 0;
		var $t = $(this);
		totalCount = + $("#totalCount").val();
		totalLendMoney = + $("#totalLendMoney").val();
		totalDeductMoney = + $("#totalDeductMoney").val();
		if($t.attr("checked")=='checked'){
			totalCount = totalCount+1;
			var lm = $t.parent().find("#lendMoneyH").val();
			var dm = $t.parent().find("#deductMoneyH").val();
			totalLendMoney = + lm + totalLendMoney;
			totalDeductMoney = + dm + totalDeductMoney;
			$("#totalCount").val(totalCount);
			$("#totalLendMoney").val(totalLendMoney);
			$("#totalDeductMoney").val(totalDeductMoney);
		}else{
			totalCount = totalCount - 1;
			var lm = $t.parent().find("#lendMoneyH").val();
			var dm = $t.parent().find("#deductMoneyH").val();
			totalLendMoney = - lm + totalLendMoney;
			totalDeductMoney = - dm + totalDeductMoney;
			$("#totalCount").val(totalCount);
			$("#totalLendMoney").val(totalLendMoney);
			$("#totalDeductMoney").val(totalDeductMoney);
		}
		toHtml(totalCount,totalLendMoney,totalDeductMoney);
	});
	
	// 全选金额计算
	$("#checkAll").click(function(){
		var totalCount  = 0;
		var totalLendMoney = 0;
		var totalDeductMoney = 0;
		if($(this).attr("checked")=='checked'){
			$("input[name='checkbox']:checked:not(.checkAll)").each(function(){
				var $t = $(this);
				totalCount = totalCount+1;
				var lm = $t.parent().find("#lendMoneyH").val();
				var dm = $t.parent().find("#deductMoneyH").val();
				totalLendMoney = + lm + totalLendMoney;
				totalDeductMoney = + dm + totalDeductMoney;
				$("#totalCount").val(totalCount);
				$("#totalLendMoney").val(totalLendMoney);
				$("#totalDeductMoney").val(totalDeductMoney);
			})
		}else{
			$("#totalCount").val(0);
			$("#totalLendMoney").val(0);
			$("#totalDeductMoney").val(0);
		}
		toHtml(totalCount,totalLendMoney,totalDeductMoney);
	})
});
/**
 * 金额计算
 * @param totalCount
 * @param totalLendMoney
 * @param totalDeductMoney
 */
function toHtml(totalCount,totalLendMoney,totalDeductMoney){
	totalLendMoney = parseFloat(totalLendMoney).toFixed(2);
	totalDeductMoney = parseFloat(totalDeductMoney).toFixed(2); 
	if(totalCount==0 && totalLendMoney == 0 && totalDeductMoney ==0){
		$("#lendCount").text($("#oSumCount").val());
		$("#lendMoney").text($("#oLendMoney").val());
		$("#deductMoney").text($("#oDeductMoney").val());
	}else{
		$("#lendCount").text(totalCount);
		$("#lendMoney").text(totalLendMoney);
		$("#deductMoney").text(totalDeductMoney);
	}
}