/**
 * 出借审批js
 */

$(function() {
	fortune.initCity(null,'addrProvince','addrCity','addrDistrict');
	
	// 点击数据列行前多选框
	$(".borrowMonthableListCheckbox").click(function(){
		is_checked = $(this).is(":checked");
		// 选中
		if(is_checked){
			$("#msg1").html("已选择条数：");
			$("#msg2").html("已选择记录总金额：");
			sumLendMoney();
		}else{
			length = $(".borrowMonthableListCheckbox:checked").length;
			if(length == 0){
				$("#msg1").html("当前页面总数据条数：");
				$("#msg2").html("当前页面总出借金额：");
				$("#totalCount").text($("#totalCount").attr("totalCount"));
				$("#totalMoney").text($("#totalMoney").attr("totalMoney"));
			}else{
				$("#msg1").html("已选择条数：");
				$("#msg2").html("已选择记录总金额：");
				sumLendMoney();
			}
			
		}
	});
	
	$("#checkAll").click(function(){
		is_checkedAll = $(this).is(":checked");
		if(is_checkedAll){
			$("#msg1").html("已选择条数：");
			$("#msg2").html("已选择记录总金额：");
			sumLendMoney();
		}else{
			length = $(".borrowMonthableListCheckbox:checked").length;
			if(length == 0){
				$("#msg1").html("当前页面总数据条数：");
				$("#msg2").html("当前页面总出借金额：");
				$("#totalCount").text($("#totalCount").attr("totalCount"));
				$("#totalMoney").text($("#totalMoney").attr("totalMoney"));
			}else{
				$("#msg1").html("已选择条数：");
				$("#msg2").html("已选择记录总金额：");
				sumLendMoney();
			}
		}
	});
	
	/**
	 * 统计出借金额
	 */
	function sumLendMoney(){
		sum = 0.0;
		count = 0;
		$(".borrowMonthableListCheckbox:checked").each(function(){
			$lendMoneyTd = $(this).closest("tr").find("td[lend_money]");
			lendMoney = $lendMoneyTd.attr("lend_money");
			sum = (sum*100000 + lendMoney*100000)/100000;
			count++;
		});
		$("#totalCount").text(count)
		
		$("#totalMoney").text(fmoney(sum,2));
	}
	//数字格式化方法
	function fmoney(s, n) {  
	    n = n > 0 && n <= 20 ? n : 2;  
	    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";  
	    var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];  
	    t = "";  
	    for (i = 0; i < l.length; i++) {  
	        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");  
	    }  
	    return t.split("").reverse().join("") + "." + r;  
	}  
	
	
});