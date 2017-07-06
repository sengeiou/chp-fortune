/**
 * 提交检索条件
 */
$(document).ready(function() {
	// 当前页面的iframe
	var iframe = getCurrentIframeContents();
	
	// 点击搜索 
	$('#search').click(function(){
		$form = $('#searchForm');
		if ($form.validate().form()) {
			$("#pageNo").val("");
			$form.submit();
		}
	});
	
	// js校验
	jQuery.validator.addMethod("money_validator", function(value, element, arg) {
    	var greaterThan = arg.replace(/\//g, '');
        if (greaterThan == "") {
            return true;
        }
        if (parseFloat(value) < parseFloat(greaterThan)) {
            return false;
        }
    	return true;
	    },"请输入大于或等{0}的值"
    );
	// 监听拆分金额的事件
	$('#splitMoney').bind('input propertychange', function() { 
		/*$form = $("#searchForm");
		var fromData = $form.serializeObject();
			if ($("#splitMoney").val() == '') {
				fromData.splitMoney = 0;
			}
			fromData.surplusSplitMoney = null;*/
			borrowMonthReckonAjax("#splitMoney");
	
	});
	// 监听拆分利率的事件
	$('#splitRate').bind('input propertychange', function() { 
		/*$form = $("#searchForm");
		var fromData = $form.serializeObject();
		var splitRate = $("#splitRate").val();
		var monthRate = $("#monthRate").text();
		if (splitRate - monthRate > 0) {
			BootstrapDialog.alert("拆分利率有误,不能大于产品月利率");
			return false;
		}
		fromData.surplusSplitMoney = null;*/
		borrowMonthReckonAjax('#splitRate');
	
	});
	// 监听拆分后可用金额的事件
	$('#surplusSplitMoney').bind('input propertychange', function() { 
		/*$form = $("#searchForm");
		var fromData = $form.serializeObject();
		fromData.splitMoney = null;*/
		borrowMonthReckonAjax('#surplusSplitMoney');
	});

	// 拆分
	$("#splitSubmit",iframe).click(function(){
		$form = $("#searchForm",iframe); 
		// 拆分利率
		var splitRate = $("#splitRate",iframe).val();
		var monthRate = $("#monthRate",iframe).text();
		if(splitRate - monthRate > 0){
			BootstrapDialog.alert("拆分利率有误,不能大于产品月利率");
			return false;
		}
		if ($form.validate().form()) {
			contents_getJsonForSync(
		    		$("#searchForm").attr("action"),	
		    		$("#searchForm").serialize(),
		    		"POST",
		    		function(result){
		    			if (result == '') {
		    				window.location =ctx+"/borrowMonth/backBorrowMonthList";
						} else {
							BootstrapDialog.alert(result);
				    		 return;
						}
		    		});
		}
	});
	
	// 返回到月满盈债权列表
	$(".backBorrowMonthList",iframe).click(function(){
		window.location = ctx+"/borrowMonth/backBorrowMonthList"
	});
	// 对利率为0的控制
	if(parseFloat($("#monthRate",iframe).text())==0.0){
		$("#splitSubmit").attr('disabled',true);
		BootstrapDialog.alert("产品月利率为0,会导致拆分时数据错误");
		return true;
	}

});

/**
 * 查看拆分历史
 * @param id
 */
function splitHis(creditMonId) {
	// 调用子窗口的参数
	var url, argment, callback;
	var iframe = getCurrentIframeContents;
	url = "/his/borrowMonthSplitHis";
	argment = {'creditMonId':creditMonId};
	load_callback = function(iframe){
	}
	var sub = SubDialogHandle.create(url,argment,load_callback,null).loadSubWindow();
	//自定义出发关闭回调函数的动作
	$('.closeButton').off('click').on('click',function(){
		var creditMonId = $("#creditMonId",iframe).val();
		window.location = ctx+"/borrowMonth/borrowMonthBackToolCancel?creditMonId="+creditMonId;
		sub.closeSubWindow();
	});
}	

/**
 * 拆分记录分页
 * @param n
 * @param s
 * @returns {Boolean}
 */
function subPage(n,s){
	if (n)
		$("#pageNo").val(n);
	if (s)
		$("#pageSize").val(s);
	var url = $("#subSearchForm").attr('action');
	contents_getJsonForHtml(
		url,
		$("#subSearchForm").serialize(),
		'post',
		function(result){
			$('#splitHisListDiv').html(result);
		}
	);
	return false;
}

// 月满盈债权回池
function backTool(creditMonId){
	if(parseFloat($("#loanAvailabeValue").text())==0.0){
		BootstrapDialog.alert("可用金额为0,不可回池");
		return false;
	}
	// 调用子窗口的参数
	var url, argment, callback;
	var iframe = getCurrentIframeContents;
	url = '/borrowMonth/goBorrowMonthBackTool';
	argment = {'creditMonId':creditMonId};
	load_callback = function(iframe){
		$("#back",iframe).on('blur',function() {
			var backMoney = $("#back",iframe).val();
			var borrowCreditValue = $("#cv",iframe).val();
			$form = $('#subSearchForm',iframe);
			if($form.validate().form()){
				$("#surplus",iframe).val(jsSub(borrowCreditValue,backMoney));
			}
		});
		// 绑定回池
	    $('.backTool',iframe).off('click').on('click',function(){
	    	$form = $("#subSearchForm");
	    	if ($form.validate().form()) {
		    	contents_getJsonForSync(
		    		$form.attr("action"),	
		    		$("#subSearchForm").serialize(),
		    		"POST",
		    		function(result){
		    			if (result == '') {
		    				window.location =ctx+"/borrowMonth/goBorrowMonthLook?creditMonId="+creditMonId;
						} else {
							BootstrapDialog.alert(result);
				    		 return;
						}
		    		});
			}
	     });
	    // 绑定取消事件
	    $('.qx',iframe).off('click').on('click',function(){
	    	var creditMonId = $("#creditMonId",iframe).val();
	    	window.location = ctx+"/borrowMonth/borrowMonthBackToolCancel?creditMonId="+creditMonId;
		 });
	};
	close_callback = function(iframe){}
	var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
	//自定义出发关闭回调函数的动作
	$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
}
	
// 计算月满盈债权拆分
function borrowMonthReckonAjax(elem){
	$form = $("#searchForm"); 
	var fromData = $form.serializeObject();
	// 拆分金额的请求
	if(elem == '#splitMoney'){
		if ($(elem).val() == '') {
			fromData.splitMoney = 0;
		}
		fromData.surplusSplitMoney = "";
	}
	// 拆分利率
	else if(elem == '#splitRate'){
		var splitRate = $(elem).val();
		var monthRate = $("#monthRate").text();
		if (splitRate - monthRate > 0) {
			BootstrapDialog.alert("拆分利率有误,不能大于产品月利率");
			return false;
		}
		fromData.surplusSplitMoney = "";
	}
	// 拆分后可用金额
	else if(elem == '#surplusSplitMoney'){
		fromData.splitMoney = "";
	}
		// 校验表单后,ajax请求
		contents_getJsonForSync(
				ctx + "/borrowMonth/borrowMonthSplitReckonByBlur",
				fromData,
				'POST',
				function(data) {
					if(data==null){
						BootstrapDialog.alert("你的操作有误,或是你的拆分利率有误");
					}
					// 拆分金额的请求
					if(elem == '#splitMoney'){
						if(data.surplusSplitMoney=='0'){
							$("#splitMoney").val((data.splitMoney).toFixed(2));
							$("#surplusSplitMoney").val(data.surplusSplitMoney);
						}else{
							$("#surplusSplitMoney").val((data.surplusSplitMoney).toFixed(2));
						}
					}
					// 拆分利率
					else if(elem == '#splitRate'){
						$("#surplusSplitMoney").val((data.surplusSplitMoney).toFixed(2));
					}
					// 拆分后可用金额
					else if(elem == '#surplusSplitMoney'){
						$("#splitMoney").val((data.splitMoney).toFixed(2));
					}
					// 添加时间戳
					$("#verTime").val(data.verTime);
				});
}

// 导出excel
function outExcel(){
	$form = $('#searchForm');
	if (!$form.validate().form()) {
		BootstrapDialog.confirm('你的输入条件出错,可能会导出全部数据',function(result){
			if(result){
				var url=ctx+"/borrowMonth/outExcel";
				window.location=url;
			}
		});
	}else{
		
		BootstrapDialog.confirm('您确定要导出excel吗？', function(result){
			if(result){
				var url=ctx+"/borrowMonth/outExcel";
				window.location=url+"?"+$("#searchForm").serialize();
			}
		});
	}
} 
