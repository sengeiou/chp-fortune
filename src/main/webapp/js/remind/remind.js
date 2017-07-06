JQuery(document).ready(function($){
	
	// 金额计算
	$("input[type=checkbox]:not(.checkAll)").click(function(){
		var totalLendMoney = 0;
		var $t = $(this);
		totalLendMoney = + $("#totalLendMoney").val();
		if($t.attr("checked")=='checked'){
			var lm = $t.parent().find("#lendMoneyH").val();
			totalLendMoney = + lm + totalLendMoney;
			$("#totalLendMoney").val(totalLendMoney);
		}else{
			var lm = $t.parent().find("#lendMoneyH").val();
			totalLendMoney = - lm + totalLendMoney;
			$("#totalLendMoney").val(totalLendMoney);
		}
		toHtml(totalCount,totalLendMoney,totalDeductMoney);
	});
	
})