

/**
 * 分页
 * @param n 当前页
 * @param s 分页大小
 * @returns {Boolean}
 */
function page(n,s){
	if(n) $("#pageNo").val(n);
	if(s) $("#pageSize").val(s);
	$("#searchForm").submit();
	return false;
}

/**
 * 跳转页面
 * @param pageURL
 */
function goPage(pageURL){
	window.location = pageURL;
}

/**
 * 查看变更历史
 * @param applyId
 */
function openHistoryDetail(applyId){
	var url="/lenderChange/openLenderChangeHistoryDetail";
	var arg = {'applyId':applyId};
	var sub = SubDialogHandle.create(url,arg,null,null).loadSubWindow();
	$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
}

/**
 * 跳转到变更历史
 * @param custCode
 */
function goLenderChangeHistory(custCode) {

	var url=ctx+"/lenderChange/goLenderChangeHistory?changeCode="+ custCode;
	
	window.location = url;
}



