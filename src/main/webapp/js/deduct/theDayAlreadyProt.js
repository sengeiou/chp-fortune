
/**
 * 分天划扣
 */
jQuery(document).ready(function($){
	
	// 表单提交校验
	$("input[type='submit']").click(function() {
		$form = $('#searchForm', getCurrentIframeContents());
		if (!$form.validate().form()) {
			// 验证不成功
			return false;
		}
	});

	// 分天划扣-->线上划扣
	$("#upLineDeduct").click(function() {
		if (isChecked()) {
			return;
		}
		var ids = getIds();
		BootstrapDialog.confirm("您确定要批量线划扣吗？", function(result) {
			if(result){
				var inputIds = '<input name="ids" type="hidden" value="'
					+ ids + '"/>'
				$("#searchForm").append(inputIds);
				$("#searchForm").attr("action",
						ctx + "/theDayAlreadPor/onLineDeduct");
				// 提交表单
				$("#searchForm").submit();
			}
		})
//		$(".modal-title").text("线上划扣");
//		// 调用子窗口的参数
//    	var url, argment, callback;
//    	url = '/theDayAlreadPor/onLineDeductShow';
//    	argment = null;
//    	load_callback = function(arg){
//    		
//    	};
//    	close_callback = function(arg){
//    		
//    	};
//        var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
//        //自定义出发关闭回调函数的动作
//        $('#subClose').off('click').on('click',function(){
//        	// 获取选中分天划扣id
//			var ids = getIds();
//			var inputIds = '<input name="ids" type="hidden" value="'
//					+ ids + '"/>'
//			$("#searchForm").append(inputIds);
//			// 复制划扣弹出框内的table放进当前页面的form表单
//			$("#onLineTable").appendTo("#searchForm");
//			$("#searchForm").attr("action",
//					ctx + "/theDayAlreadPor/onLineDeduct");
//			// 提交表单
//			$("#searchForm").submit();
//        	sub.closeSubWindow();
//        });
	})

	// 分天划扣-->线下划扣
	$("#downLineDeduct").click(function() {
			if (isChecked()) {
				return;
			}
			// 获取选中分天划扣id
			var ids = getIds();
			$(".modal-title").text("线下划扣");
			$("#subClose").val("关闭");
			// 调用子窗口的参数
			var url, argment, callback;
			url = '/theDayAlreadPor/offLineDeductShow';
			argment = null;
			load_callback = function(arg){
				$("input[name='lendCodes']").val(ids);
			};
			close_callback = function(arg){
			};
			var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
			//自定义出发关闭回调函数的动作
			$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
	})

	// 分天划扣-->协议库对接
	$("#protocolLibrary").click(function() {
		if (isChecked()) {
			return;
		}
		BootstrapDialog.confirm("您确定要协议库对接吗？", function(result) {
			if (result) {
				// 获取出借编号
				var ids = getCheckDeductId();
				var url = ctx + "/theDayAlreadPor/protocolLibrary";
				contents_getJsonForSync(url, {
					'ids' : ids
				}, "post", function(result,par){
					 if(result=='ok'){
						 BootstrapDialog.alert('对接协议成功',function(){
							 $("#searchForm").submit();
						 });
					 }else{
						 if(result == null || result == 'ng'){
							 result = '操作失败';
						 }
						 BootstrapDialog.alert(result,function(){
							 $("#searchForm").submit();
						 });
					 }
				}, errorCal, null);
			}
		});
	})

	// 分天划扣-->批量修改状态
	$("#bathDeductBalance").click(function() {
		if (isChecked()) {
			return;
		}
		BootstrapDialog.confirm("您确定要批量修改划扣状态吗？",function(result) {
				if (result) {
					$(".modal-title").text("批量修改状态");
					$("#subClose").val("提交");
					var url, argment, callback;
					url = '/theDayAlreadPor/showBatchModel';
					argment = null;
					load_callback = function(arg){
						$("input[name='theDayId']").val(getIds());
						$("input[name='applyCodeSub']").val(getCheckLendCode());
					};
					close_callback = function(arg){
					};
					var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
					//自定义出发关闭回调函数的动作
					$('#subClose').off('click').on('click',function(){
						$form = $('#searchForm1', getCurrentIframeContents());
						if (!$form.validate().form()) {
							// 验证不成功
							return false;
						}
						var ids = getIds();
						var inputIds = '<input name="deductPlatFormID" type="hidden" value="'
								+ ids + '"/>'
						$("#searchForm1").append(inputIds);
						// 异步提交表单
						url = ctx + '/theDayAlreadPor/theayBatchStatus';
						contents_getJsonForSync(url, $("#searchForm1").serialize(), "post", callFunction, errorCal, null);
						sub.closeSubWindow();
					});
				}
			})
	})
	
	// 预约划扣
	$("#bathDeductTheDay").click(function(){
		if (isChecked()) {
			return;
		}
		BootstrapDialog.confirm("您确定要预约划扣吗？",function(result) {
			if (result) { 
				$(".modal-title").text("预约划扣");
				$("#subClose").val("提交");
				var url, argment, callback;
				url = '/theDayAlreadPor/theDayDeduct';
				argment = {"id":getIds()};
				load_callback = function(arg){
					$("#deductCountMoney").val($("#deductMoney").text());
					$("#deductCount").val($("#lendCount").text());
				};
				close_callback = function(arg){
				};
				var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
				//自定义出发关闭回调函数的动作
				$('#subClose').off('click').on('click',function(){
					url = ctx+"/theDayAlreadPor/saveDeductBespoke";
					$form = $('#offLineForm', getCurrentIframeContents());
					if ($form.length > 0 && !$form.validate().form()) {
						// 验证不成功
						return false;
					}
					contents_getJsonForSync(url, $("#offLineForm").serialize(), "post", callFunction, errorCal, null);
					sub.closeSubWindow();
				});
			}
		})
	})
	
	// 取消预约伐扣
	$("#cancelDeductBespoke").click(function(){
		BootstrapDialog.confirm("您确定要取消预约划扣吗？",function(result) {
			if (result) {
				$(".modal-title").text("取消预约划扣");
				$("#subClose").val("提交");
				var url, argment, callback;
				url = '/theDayAlreadPor/goCancelDeductBespoke';
				argment = {};
				load_callback = function(arg){};
				close_callback = function(arg){};
				var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
				//自定义出发关闭回调函数的动作
				$('#subClose').off('click').on('click',function(){
					contents_getJsonForSync(ctx+"/theDayAlreadPor/cancelDeductBespoke",{"id":getCancelIds()},"post",callFunction, errorCal, null);
					sub.closeSubWindow();
				});
			}
		})
	});
	
	
	
	// 已划扣-->办理-->全程流痕
	$("[id='fullFlowMark']").click(function() {
			// 调用子窗口的参数
			var url, argment, callback;
			url = '/deductSuccess/fullFlowMark';
			argment = {applyCode:$(this).attr("name")};
			$(".modal-title").text("全程留痕");
			$("#subClose").val("关闭");
			load_callback = function(arg){
			};
			close_callback = function(arg){
			};
			var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
			//自定义出发关闭回调函数的动作
			$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});	
	})
	
	// 确认预约失败
	$("#confirmFail").click(function() {
		if (isChecked()) {
			return;
		}
		BootstrapDialog.confirm("您确定要确认失败吗？",function(result) {
			if(result){
				var ids = getCheckDeductId();
				var url = ctx+ '/theDayAlreadPor/confirmFail';
				contents_getJsonForSync(url, {'ids':ids}, "post", callFunction, errorCal, null);
			}
		})
	})
});

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
 * 获取取消预约划扣ids
 */
function getCancelIds(){
	 var ids="";
	 $("input[name='checkbox2']:checked:not(.checkAll)").each(function(){
		ids=ids+$(this).val()+",";
	 })
	 return ids;
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
 * 获取选择中的划扣申请id
 * @returns {String}
 */
function getCheckDeductId(){
	var ids="";
	 $("input[name='checkbox']:checked").each(function(){
		 if($(this).val()!='on')
			 ids=ids+$(this).attr("deductId")+",";
	 }) 
	 return ids;
}

/**
 * 获取选择中的出借编号
 * @returns {String}
 */
function getCheckLendCode(){
	var ids="";
	 $("input[name='checkbox']:checked").each(function(){
		 if($(this).val()!='on')
			 ids=ids+$(this).attr("lendcode")+",";
	 }) 
	 return ids;
}

/**
 *  函数回调
 */
function callFunction(result,par){
	 if(result=='ok'){
//		 $.jBox.tip("批量操作成功");
		 BootstrapDialog.alert('批量操作成功',function(){
			 $("#searchForm").submit();
		 });
	 }else{
//		 $.jBox.tip("批量操作失败");
		 BootstrapDialog.alert('批量操作失败原因：'+result,function(){
			 $("#searchForm").submit();
		 });
	 }	 
}

function pageAjax(n, s) {
	$("#pageNo1").val(n);
	$("#pageSize1").val(s);
	var url,data,type;
	url = ctx + '/deductSuccess/fullFlowMark';
	data = $("#searchForm1").serialize();
	type = 'post'
	contents_getJsonForHtml(url, data, type, function(result){
		//覆盖列表+page
		$('#diabox_c').html(result);
//		initAfterLoad();
	},function(){
		
	},null);
}

/**
 * 
 */
function errorCal(){
	BootstrapDialog.alert('系统出错,请稍后……');
}
/**
 * 跳转
 * @param url
 */
function go(url){
//	window.location.href=url;
	goPage(url);
}
