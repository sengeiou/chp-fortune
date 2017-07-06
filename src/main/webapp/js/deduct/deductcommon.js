
/**
 * 判断是否选择记录
 * @returns {Boolean}
 */
function isChecked(){
	 if($("input[name='checkbox']:checked").size()==0){
//		 $.jBox.tip("您没有选中一条记录");
		 BootstrapDialog.alert('您没有选中一条记录');
		 return true;
	 }
	 return false;
}

/**
 * 获取选中ids
 */
function getIds(){
	 var ids="";
	 $("input[name='checkbox']:checked").each(function(){
		 if($(this).val()!='on')
			 ids=ids+$(this).val()+",";
	 }) 
	 return ids;
}

/**
 * 获取选中ids
 */
function getUncheckIds(){
	 var ids="";
	 $("input[name='checkbox']").each(function(){
		 if($(this).val()!='on')
			 ids=ids+$(this).val()+",";
	 }) 
	 return ids;
}

/**
 *  函数回调
 */
function callFunction(result,par){
	 if(result=='ok'){
//		 $.jBox.tip("批量操作成功");
		 BootstrapDialog.alert('批量操作成功');
	 }else{
//		 $.jBox.tip("批量操作失败");
		 BootstrapDialog.alert('批量操作失败');
	 }
	setInterval(function(){
		location.reload();
	},1000);
	 
}

/**
 * 
 */
function errorCal(){
	BootstrapDialog.alert('系统出错,请稍后……');
//	$.jBox.info("系统出错,请稍后……");
}
///**
// *  分页js
// */
//function page(n,s){
//	$("#pageNo").val(n);
//	$("#pageSize").val(s);
//	$("#searchForm").submit();
//  	return false;
//}
/**
 * 跳转
 * @param url
 */
function go(url){
	goPage(url);
}

function pageAjax(n, s) {
	$("#pageNo1").val(n);
	$("#pageSize1").val(s);
	var url,data,type;
	url = '/deductSuccess/fullFlowMark';
	data = $("#searchForm1").serialize();
	type = 'post'
	contents_getJsonForHtml(url, data, type, function(result){
		//覆盖列表+page
		$('div#dataList').html(result);
		initAfterLoad();
	},function(){
		
	},params);
}