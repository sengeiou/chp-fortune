$(function(){
	
	//导出客户信息
	$("#exportCustomerDetail").click(function(){
		url = "exportCustomerDetail";
		var data = {};
		data = $("form").serialize();		
		postFormReplaceGet($("#searchForm"),ctx+"/customer/investment/"+ url);
	
	});
});