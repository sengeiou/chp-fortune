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
	
	totalAMoney = $("#hTotalActualMoney").val();
	totalBMoney = $("#hTotalBackMoney").val();
	totalBSMoney = $("#hTotalSupplementedMoney").val();
	totalLMoney = $("#hTotalLendMoney").val();
	$("#TotalActualMoney").html(fmtMoney(totalAMoney));
	$("#TotalBackMoney").html(fmtMoney(totalBMoney));
	$("#TotalSupplementedMoney").html(fmtMoney(totalBSMoney));
	$("#TotalLendMoney").html(fmtMoney(totalLMoney));
});


function getBackmIds(){
	if (0==$("input[name='backmId']:checked").length) {
		return "";
	} else {
		return $("input[name='backmId']:checked").map(function(){
		      return $(this).val();
	    }).get().join(",");
	}
	
}
/**
 * 全选、反选
 */
function checkAll(){
	var totalAMoney = 0;// 累计实际回款金额
	var totalBXHMoney = 0;// 累计补息后回款金额
	var totalBMoney = 0;// 累计应回款金额
	var totalLMoney = 0;// 累计出借金额
	var totalCount = 0;// 累计计数
	
	if (0==$("#dataCheck:checked").length) {
		totalAMoney = $("#hTotalActualMoney").val();
		totalBXHMoney = $("#hTotalSupplementedMoney").val();
		totalBMoney = $("#hTotalBackMoney").val();
		totalLMoney = $("#hTotalLendMoney").val();
		totalCount = $("#hDataAmount").val();
	} else {
		// 遍历计算
		$("#dataCheck:checked").each(function(){
			var am = $(this).parent().parent().find("input[name='itemBackActualbackMoney']").val();
			var bxhm = $(this).parent().parent().find("input[name='itemSupplementedMoney']").val();
			var bm = $(this).parent().parent().find("input[name='itemBackMoney']").val();
			var lm = $(this).parent().parent().find("input[name='itemApplyLendMoney']").val();
			totalAMoney = + am + totalAMoney;
			totalBXHMoney = + bxhm + totalBXHMoney;
			totalBMoney = + bm + totalBMoney;
			totalLMoney = + lm + totalLMoney;
			totalCount = $("#dataCheck:checked").length;
		});
	}
	$("#TotalActualMoney").html(fmtMoney(totalAMoney));
	$("#TotalSupplementedMoney").html(fmtMoney(totalBXHMoney));
	$("#TotalBackMoney").html(fmtMoney(totalBMoney));
	$("#TotalLendMoney").html(fmtMoney(totalLMoney));
	$("#dataAmount").html(totalCount);
}

/**
 * 单个checkBox状态变化
 * @param src操作元素
 */
function checkOne(src){
	var totalAMoney = 0;// 累计实际回款金额
	var totalBXHMoney = 0;// 累计补息后回款金额
	var totalBMoney = 0;// 累计应回款金额
	var totalLMoney = 0;// 累计出借金额
	var totalCount = 0;// 累计计数

	
	if($("#dataCheck:checked").length==$("input[name='backmId']").length){
		$("#checkAll").attr("checked",true);
	}else{
		$("#checkAll").attr("checked",false);
	}
	
	if (0==$("#dataCheck:checked").length) {
		totalAMoney = $("#hTotalActualMoney").val();
		//补息后回款金额
		totalBXHMoney = $("#hTotalSupplementedMoney").val();
		totalBMoney = $("#hTotalBackMoney").val();
		totalLMoney = $("#hTotalLendMoney").val();
		totalCount = $("#hDataAmount").val();
	} else {
		// 遍历计算
		$("#dataCheck:checked").each(function(){
			var am = $(this).parent().parent().find("input[name='itemBackActualbackMoney']").val();
			//补息后回款金额
			var bxhm = $(this).parent().parent().find("input[name='itemSupplementedMoney']").val();
			var bm = $(this).parent().parent().find("input[name='itemBackMoney']").val();
			var lm = $(this).parent().parent().find("input[name='itemApplyLendMoney']").val();
			totalAMoney = + am + totalAMoney;
			//补息后回款金额
			totalBXHMoney = + bxhm + totalBXHMoney;
			totalBMoney = + bm + totalBMoney;
			totalLMoney = + lm + totalLMoney;
			totalCount = $("#dataCheck:checked").length;
		});
	}
	$("#TotalActualMoney").html(fmtMoney(totalAMoney));
	$("#TotalSupplementedMoney").html(fmtMoney(totalBXHMoney));
	$("#TotalBackMoney").html(fmtMoney(totalBMoney));
	$("#TotalLendMoney").html(fmtMoney(totalLMoney));
	$("#dataAmount").html(totalCount);
}

/**
 * 退回原因选择[其它]时，显示文本框，
 * 手动输入原因
 */
function addReasonChoice(){
	var cet = $("[name='checkExaminetype']:checked").val();
	if ('2'==cet) {
		$("#TcheckExamine").removeAttr("style");
	}else{
		$("#TcheckExamine").attr("style","display: none;");
	}
}

/**
 * 退回原因选择[其它]时，显示文本框，
 * 手动输入原因
 */
function addReason(){
	var val = $("[name='checkExamine']").val();
	if ('37'==val) {
		$("#checkReason").removeAttr("style");
	}else{
		$("#checkReason").attr("style","display: none;");
	}
}

/**
 * 详情页：
 * 页面加载后判定是否显示其它退回原因输入框
 */
$(function(){
	var val = $("#checkExamine").val();
	if ('37'==val) {
		$("#checkReason").removeAttr("style");
	}
})

/**
 * 全程留痕
 */
function showFullTrace(lendCode) {
			
		// 调用子窗口的参数
		var url, argment, callback;
		url = '/myDone/backMoney/fullTrace';
		argment = {'applyCode':lendCode};
		load_callback = function(arg){};
		close_callback = function(arg){};
		var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
		//自定义出发关闭回调函数的动作
		$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});	
}

/**
 * 全程留痕翻页
 * @param n
 * @param s
 */
function pageAjax(n, s, m) {
	if (n)
		$("#pageNo1").val(n);
	if (s)
		$("#pageSize1").val(s);
	var url,data,type;
	url = ctx +"/myDone/backMoney/fullTrace";
	data = $("#traceForm").serialize();
	type = 'post'
	contents_getJsonForHtml(
			url, 
			data, 
			type, 
			function(result){
				//覆盖列表+page
				$('div#dataList').html(result);
				initAfterLoad();
			},
			function(){});
}

/**
 * 数据校验
 * @param src
 */
function dateCheck(src){
	$(src).valid();
}


/**
 * 校验文件类型是否为Excel
 * @param file
 * @returns {Boolean}
 */
function checkIfExcel(file){
	var suffix = file.value.substr(file.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
	if (suffix != '.xls' && suffix !='.xlsx') {
		BootstrapDialog.alert("文件格式错误，请上传Excel文件");
		return false;
	}
	return true;
}

/**
 * 文件名验证
 * @param filename
 * @returns {Boolean}
 */
function ifExcel(filename){
	var suffix = filename.substr(filename.lastIndexOf(".")).toLowerCase();//获得文件后缀名
	if (suffix != '.xls' && suffix !='.xlsx') {
		BootstrapDialog.alert("文件格式错误，请上传Excel文件");
		return false;
	}
	return true;
}

/**
 * 数字格式化为带千位分隔符的格式，两位小数
 * @param s
 * @returns
 */
function fmtMoney(s){
	return formatNum(s, 2);
}

