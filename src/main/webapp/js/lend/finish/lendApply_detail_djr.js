$(function() {
	contents_getJsonForSync(ctx + '/common/file/getAttachmentAjax', {
		'code' : $('#applyCode').val(),
		'tableId' : '',
		'tableName' : 'to_djr'
	}, 'post', function(result) {
		DropzoneHandle.initDropZone('#my-awesome-dropzone', result, {
			hideDrop : true,
			addRemoveLinks : false
		});
	}, function() {
		BootstrapDialog.alert("加载附件发生错误");
		return;
	}, null);
});

/**
 * 全程留痕
 */
function showFullTrace(lendCode) {
	// 调用子窗口的参数
	var url, argment, callback;
	url = '/myDone/backMoney/fullTrace';
	argment = {'applyCode' : lendCode,'operateAffiliated':'201612161601_djr'};
	load_callback = function(arg) {};
	close_callback = function(arg) {};
	var sub = SubDialogHandle.create(url, argment, load_callback,close_callback).loadSubWindow();
	// 自定义出发关闭回调函数的动作
	$('#subClose').off('click').on('click', function() {
		sub.closeSubWindow();
	});
}

/**
 * 全程留痕翻页
 * 
 * @param n
 * @param s
 */
function pageAjax(n, s, m) {
	if (n)
		$("#pageNo1").val(n);
	if (s)
		$("#pageSize1").val(s);
	var url, data, type;
	url = ctx + "/myDone/backMoney/fullTrace";
	data = $("#traceForm").serialize();
	type = 'post'
	contents_getJsonForHtml(url, data, type, function(result) {
		// 覆盖列表+page
		$('div#dataList').html(result);
		initAfterLoad();
	}, function() {
	});
}