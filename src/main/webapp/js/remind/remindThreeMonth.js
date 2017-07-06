$(function(){
	/** 消息提醒-->导出excl	*/
	$("#outExcel").click(function() {
		BootstrapDialog.confirm("您确定要导出excel吗？",function(result) {
			if (result) {
				// 获取选择中的记录
				$("#lendCodes").val(getIds());
				var url = ctx + "/remindBeforeThreeMonth/outExcel";
				var data = {};
				data = $("form").serialize();
				postFormReplaceGet($("#searchForm"),url);
			}
		});
	});
	
	/** 获取选中ids */
	function getIds(){
		 var ids="";
		 $("input[name='checkbox']:checked:not(.checkAll)").each(function(){
			 if($(this).val()!='on')
				 ids=ids+$(this).val()+",";
		 }) 
		 return ids;
	}
});

/** 金额计算 */
jQuery(document).ready(function($){	
	$("input[name=checkbox]:not(.checkAll)").click(function(){
		var totalLMoney = 0;// 累计出借金额
		if($("#dataCheck:checked").length==$("input[name='checkbox']").length){
			$("#checkAll").attr("checked",true);
		}else{
			$("#checkAll").attr("checked",false);
		}
		
		if (0==$("#dataCheck:checked").length) {
			totalLMoney = $("#oLendMoney").val();
		} else {
			// 遍历计算
			$("#dataCheck:checked").each(function(){
				var lm = $(this).parent().find("input[name='lendMoneyH']").val();
				totalLMoney = + lm + totalLMoney;
			});
		}
		$("#lendMoney").html(fmtMoney(totalLMoney));
	});
	
	// 全选金额计算
	$("#checkAll").click(function(){
		var totalLMoney = 0;// 累计出借金额
		if (0==$("#dataCheck:checked").length) {
			totalLMoney = $("#oLendMoney").val();
		} else {
			// 遍历计算
			$("#dataCheck:checked").each(function(){
				var lm = $(this).parent().find("input[name='lendMoneyH']").val();
				totalLMoney = + lm + totalLMoney;
			});
		}
		$("#lendMoney").html(fmtMoney(totalLMoney));
	})
});

/**
 * 数字格式化为带千位分隔符的格式，两位小数
 * @param s
 * @returns
 */
function fmtMoney(s){
	return formatNum(s, 2);
}