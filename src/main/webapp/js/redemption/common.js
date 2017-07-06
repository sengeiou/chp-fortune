
/**
 * 提交检索条件
 */
$(document).ready(function() {
	$('#search').click(function(){
		$form = $('#searchForm');
		if ($form.validate().form()) {
			$form.submit();
		}
	});
});


/**
 * 检索
 */
/*function rSearch() {
	$("#searchForm").attr("method","post").submit();
}*/
/**
 * 单个checkBox状态变化
 * @param src操作元素
 */
function checkOne(src){
	if($("#dataCheck:checked").length==$("input[name='redemptionId']").length){
		$("#checkAll").attr("checked",true);
	}else{
		$("#checkAll").attr("checked",false);
	}
}

/**
 * 数据校验
 * @param src
 */
function dateCheck(src){
	$(src).valid();
}

/**
 * 批量推送
 */
function multiPush1(){
	var count = $("input[name='redemptionId']:checked").length;
	if (0==count) {
		BootstrapDialog.alert("请选择要操作的数据");
		return;
	}
	BootstrapDialog.confirm('共'+count+'条数据，确定推送？', function(result){
	     if(result){
	     	var d=$("#searchForm").serialize();
	         window.location.href=ctx+"/myApply/redemption/multiPush?"+d;
	     }
	 });
}