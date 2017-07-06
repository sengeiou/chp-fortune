/**
 * 提交检索条件
 */
$(document).ready(function() {
	$('#search').click(function(){
		$form = $('#searchForm');
		if ($form.validate().form()) {
			$("#pageNo").val(""); 			
			$form.submit();
		}
	});
});

/**
 * 跳转到查看页面
 * @param creditValueId
 */
function goLook(code){
	// 调用子窗口的参数
	var url, argment, callback;
	url = '/borrowFreeze/goBorrowFreezeLook';
	argment = {'creditValueId':code};
	close_callback = function(arg){};// 关闭
	var sub = SubDialogHandle.create(url,argment,null,close_callback).loadSubWindow();
}

// 子窗口的查询
function subPage(n,s){
	if (n)
		$("#subPageNo").val(n);
	if (s)
		$("#subPageSize").val(s);
	var url = $("#subSearchForm").attr('action');
	contents_getJsonForHtml(
		url,
		$("#subSearchForm").serialize(),
		'post',
		function(result){
			$('#borrowFreezeLookListDiv').html(result);
		}
	);
	return false;
}
/**
 * 导出Excel
 */
function outExcel(){
	$form = $('#searchForm');
	if (!$form.validate().form()) {
		BootstrapDialog.confirm('你的输入条件出错,可能会导出全部数据',function(result){
			if(result){
				var url=ctx+"/borrowFreeze/outExcel";
				window.location=url;
			}
		});
	}else{
		
		BootstrapDialog.confirm('您确定要导出excel吗？', function(result){
			if(result){
				var url=ctx+"/borrowFreeze/outExcel";
				window.location=url+"?"+$("#searchForm").serialize();
			}
		});
	}
}


