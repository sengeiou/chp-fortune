$(function(){
	
	/**
	 * 获得页面的iframe
	 */
	var iframe = getCurrentIframeContents();
	
	//省市区联动绑定
	fortune.initCity(iframe,'addrProvince','addrCity','addrDistrict');
	$('#btnSubmit').click(function(){
		$form = $('#searchForm',iframe);
		if(!$form.validate().form()){
			//验证不成功
			return false;
		}else{
			BootstrapDialog.confirm('确定提交吗？', function(result){
	            if(result){
	            	    $("#searchForm").submit();
	            		BootstrapDialog.alert('提交成功');
	            	
	            }else{
	            }
	            return;
	        });
		}
	});
})

/**
 * 分页
 * @param n
 * @param s
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
 * 
 * @param pageURL
 */
function goPage(pageURL) {
	window.location = pageURL;
}

/**
 * 查看变更历史
 * 
 * @param applyId
 */
function openHistoryDetails(applyId) {
	var url="/lendChange/openLendChangeHistoryDetail";
	var arg = {'applyId':applyId};
	var sub = SubDialogHandle.create(url,arg,null,null).loadSubWindow();
	$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});

}

/**
 * 页面伸缩
 */
function show() {
	if (document.getElementById("T1").style.display == 'none') {
		document.getElementById("showMore").className = 'xialadown';
		document.getElementById("T1").style.display = '';
		document.getElementById("T2").style.display = '';
	} else {
		document.getElementById("showMore").className = 'xiala';
		document.getElementById("T1").style.display = 'none';
		document.getElementById("T2").style.display = 'none';
	}
}
/**
 * 跳转到变更历史列表
 * @param applyCode
 */
function goLendChangeHistory(applyCode) {
	
	var url=ctx+"/lendChange/goLendChangeHistory?applyCode="+ applyCode;
	
	window.location = url;
}

/**
 * 清空筛选条件(除去下拉框或者机构)
 */
function clearInput(){
	  $("input[type='text']").val(null);
}
/**
 * 下载模板
 * @param custCode
 */
function templates(custCode){
	var url=ctx+"/lendChange/exportExcel?custCode=" + custCode;
	window.location = url;
}

