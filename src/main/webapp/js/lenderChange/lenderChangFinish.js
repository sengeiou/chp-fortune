
/**
 * 分页
 * @param n 当前页
 * @param s 分页大小
 * @returns {Boolean}
 */
function page(n,s){
	if(n) $("#pageNo").val(n);
	if(s) $("#pageSize").val(s);
	$("#searchForm").attr("action","${ctx}/lenderChange/finish");
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
