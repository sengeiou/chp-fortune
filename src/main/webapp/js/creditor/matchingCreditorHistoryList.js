$(function(){
	var iframe = getCurrentIframeContents();
	//查询
	$('#search',iframe).click(function(){
		if($('#searchForm').validate().form()){
			//默认第一页
			page(1);
		}
		
	});
})