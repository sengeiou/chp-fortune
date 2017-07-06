function page(n,s){
	if(n) $("#pageNo").val(n);
	if(s) $("#pageSize").val(s);
	$("#searchForm").attr("action", ctx + "/contract/statisticsList");
	$("#searchForm").submit();
	return false;
}
function goPage(pageURL){
	window.location = pageURL;
}

$(document).ready(function() { 
	
	//清空参数
	$('[opt=clean]').on('click',function(e){
		var form = $(this).closest('form');
		$(form).find(':text').val('');
		$(form).find('select').val('');
		$(form).find('select').trigger('change');
	});
	
	//导出Excle
	$('[opt=export]').on('click',function(e){
	    BootstrapDialog.confirm('确认要导出数据吗？',function(result){
	        if(result){
	        	 window.location=ctx+'/contract/statisticsList/exportOrders?'+$("#searchForm").serialize();
	        }
	        return;
	    });
	});
}); 