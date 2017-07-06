
/**
 * 划扣
 */
jQuery(document).ready(function($){
	// 表单提交校验
	$("input[type='submit']").click(function() {
		$form = $('#searchForm', getCurrentIframeContents());
		if ($form.length > 0 && !$form.validate().form()) {
			// 验证不成功
			return false;
		}
		$form = $('#deductApproveForm', getCurrentIframeContents());
		if ($form.length > 0 && !$form.validate().form()) {
			// 验证不成功
			return false;
		}
		$form = $('#deductResult', getCurrentIframeContents());
		if ($form.length > 0 && !$form.validate().form()) {
			// 验证不成功
			return false;
		}
		$(this).closest('form').submit();
		return false;
	});

	// 消息提醒-->导出excl
	$("#outExcel").click(function() {
		BootstrapDialog.confirm("您确定要导出excel吗？",function(result) {
			if (result) {
				// 获取选择中的记录
				$("#lendCodes").val(getIds());
				var url = ctx + "/remindBefore10Days/outExcel";
				if (remindFlag == 'das') {
					url = ctx + "/remindDays/outExcel";
				} else if (remindFlag == '30das') {
					url = ctx + "/remindBefore30Days/outExcel";
				}
				postFormReplaceGet($("#searchForm"),url);
			}
		});
	});

	// 划扣申请-->批量发送首期债权
	$("#bathsendfile").click(function() {
		if (isChecked())
			return;
		BootstrapDialog.confirm("您确定要发送首期债权吗？",function(result) {
			if (result) {
				var ids = getIds();
				var url = ctx + "/deductApply/batchSendFile";
				contents_getJsonForSync(url, {
					'ids' : ids
				}, "post", callFunction, errorCal, null);
			}
		});
	})

	// 划扣申请-->办理-->发送首期债权
	$("#sendfile").click(function() {
		var ids = $(this).attr("applycode");
		BootstrapDialog.confirm("您确定要发送首期债权吗？",function(result) {
			if (result) {
				var parameter
				if(ids == null || ids == ''){
					parameter = $("#searchForm").serialize();
				}else{
					parameter = {ids:ids};
				}
				var url = ctx + "/deductApply/batchSendFile";
				contents_getJsonForSync(url,parameter, "post", function(result){
					 if(result=='ok'){
						 BootstrapDialog.alert('发送成功');
					 }else{
						 BootstrapDialog.alert(result);
					 }
					 $("#searchForm").submit();
				}, errorCal, null);
				
			}
		});
	})

	// -划扣申请-->批量划扣申请
	$("#bathdeductapply").click(function() {
		if (isChecked())
			return;
		BootstrapDialog.confirm("您确定要批量划扣申请吗？", function(result) {
			if (result) {
				var ids = getIds();
				var parameter
				if(ids == null || ids == ''){
					parameter = $("#searchForm").serialize();
				}else{
					parameter = {ids:ids};
				}
//				var parameter = $("#searchForm").serialize()+"&ids="+ids;
				var url = ctx + "/deductApply/batchDeductApply";
				contents_getJsonForSync(url,parameter, "post", callFunction, errorCal, null);
			}
		});
	});

	// 划扣审批-->批量划扣审批
	$("#bathApprove").click(function() {
		if (isChecked())
			return;
		BootstrapDialog.confirm("您确定要批量划扣审批吗？", function(result) {
			if (result) {
				var ids = getIds();
//				var parameter = $("#searchForm").serialize()+"&ids="+ids;
				var parameter
				if(ids == null || ids == ''){
					parameter = $("#searchForm").serialize();
				}else{
					parameter = {ids:ids};
				}
				var url = ctx + "/deductApprove/batchDeductApprove";
				contents_getJsonForSync(url,parameter, "post", callFunction, errorCal, null);
			}
		});
	});

	// 划扣审批-->导出excel
	$("#expExcel").click(
		function() {
			BootstrapDialog.confirm("您确定要导出excel吗？", function(result) {
				if (result) {
					var ids = getIds();
					var url = ctx + "/deductApprove/expExcel";
//						window.location = url + "?applyCodes=" + ids + "&"
//								+ $("#searchForm").serialize();
					var object = {};
					if(ids != null && ids != ''){
						object.applyCodes=ids;
						postParamReplaceGet(object,url);
					}else{
						postFormReplaceGet($("#searchForm"),url);
					}
					
				}
		});
	});
	
	$("input[name='approveResult']").click(function(){
		var val = $(this).val();
		if(val == '8' || val == '9'){
			$("input[name='approveAdvice']").attr("required",true);
		}else{
			$("input[name='approveAdvice']").attr("required",false);
		}
		
	})

	// 划扣结算-->线上划扣
	$("#upLineDeduct").click(function() {
		if (isChecked()) {
			return;
		}
		var ids = getIds();
		BootstrapDialog.confirm("您确定要批量线上划扣吗？", function(result) {
			if(result){
//				var inputIds = '<input name="ids" type="hidden" value="'
//					+ ids + '"/>'
//				$("#searchForm").append(inputIds);
//				$("#searchForm").attr("action",
//						ctx + "/deductBalance/onLineDeduct");
//				// 提交表单
//				$("#searchForm").submit();
//				var parameter = $("#searchForm").serialize()+"&ids="+ids;
				var parameter
				if(ids == null || ids == ''){
					parameter = $("#searchForm").serialize();
				}else{
					parameter = {ids:ids};
				}
				var url =ctx + "/deductBalance/onLineDeduct";
				contents_getJsonForSync(url, parameter, "post", callFunction, errorCal, null);
			}
		})
//		// 调用子窗口的参数
//    	var url, argment, callback;
//    	url = '/deductBalance/onLineDeductShow';
//    	argment = null;
//    	load_callback = function(arg){
//    	};
//    	close_callback = function(arg){
//    	};
//        var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
//        //自定义出发关闭回调函数的动作
//        $('#subClose').off('click').on('click',function(){
//        	// 获取选中的id
//			var ids = getIds();
//			var inputIds = '<input name="ids" type="hidden" value="'
//					+ ids + '"/>'
//			$("#searchForm").append(inputIds);
//			// 复制划扣弹出框内的table放进当前页面的form表单
//			$("#onLineTable").appendTo("#searchForm");
//			$("#searchForm").attr("action",
//					ctx + "/deductBalance/onLineDeduct");
//			// 提交表单
//			$("#searchForm").submit();
//        	sub.closeSubWindow();
//        });
	})

	// 划扣结算-->线下划扣
	$("#downLineDeduct").click(function() {
			if (isChecked()) {
				return;
			}
			var ids = getIds();
			$(".modal-title").text("线下划扣");
			$("#subClose").html("关闭");
			// 调用子窗口的参数
			var url, argment, callback;
			url = '/deductBalance/offLineDeductShow';
			argment = null;
			load_callback = function(arg){
//				alert(ids);
				$("input[name='lendCodes']").val(ids);
			};
			close_callback = function(arg){
			};
			var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
			//自定义出发关闭回调函数的动作
			$('#subClose').off('click').on('click',function(){sub.closeSubWindow();window.reload();});
	})

	// 划扣结算-->协议库对接
	$("#protocolLibrary").click(function() {
		if (isChecked()) {
			return;
		}
		BootstrapDialog.confirm("您确定要协议库对接吗？", function(result) {
			if (result) {
				var ids = getIds();
//				var parameter = $("#searchForm").serialize()+"&ids="+ids;
				var parameter
				if(ids == null || ids == ''){
					parameter = $("#searchForm").serialize();
				}else{
					parameter = {ids:ids};
				}
				var url = ctx + "/deductBalance/protocolLibrary";
				contents_getJsonForSync(url, parameter, "post", function(result,par){
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

	// 划扣结算-->批量修改状态
	$("#bathDeductBalance").click(function() {
		BootstrapDialog.confirm("您确定要批量修改划扣状态吗？",function(result) {
			if (result) {
				$(".modal-title").text("批量修改状态");
				$("#subClose").html("提交");
				var url, argment, callback;
				url = '/deductBalance/showBatchModel';
				argment = null;
				load_callback = function(arg){
					$("input[name='theDayId']").val();
					$("input[name='applyCodeSub']").val(getIds());
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
					url = ctx + '/deductBalance/batchDeductStatus';
					contents_getJsonForSync(url, $("#searchForm1").serialize(), "post", callFunction, errorCal, null);
					sub.closeSubWindow();
				});
			}
		})
		
//		BootstrapDialog.confirm("您确定要批量修改当前列表划扣状态为失败操作吗？",function(result) {
//				if (result) {
//					var ids = getUncheckIds();
//					var inputIds = '<input name="deductPlatFormID" type="hidden" value="'
//							+ ids + '"/>'
//					$("#searchForm").append(inputIds);
//					$("#searchForm").attr("action",ctx + "/deductBalance/batchDeductStatus");
//					// 提交表单
//					$("#searchForm").submit();
//				}
//			})
	})

	// 划扣结算-->预约划扣
	$("#bathDeductTheDayBalance").click(function(){
		BootstrapDialog.confirm("您确定要预约划扣吗？",function(result) {
			if (result) {
				$(".modal-title").text("预约划扣");
				$("#subClose").html("提交");
				var url, argment, callback;
				url = '/deductBalance/theDayDeduct';
				argment = {"id":getIds()/*,"applyDeductTypeName":array[0]*/};
				load_callback = function(arg){
					$("#deductCountMoney").val($("#deductMoney").text());
					$("#deductCount").val($("#lendCount").text());
				};
				close_callback = function(arg){
				};
				var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
				//自定义出发关闭回调函数的动作
				$('#subClose').off('click').on('click',function(){
					url = ctx+"/deductBalance/saveDeductBespoke";
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
	
	// 划扣结算-->取消预约划扣
	$("#cancelDeductBespokeBalance").click(function(){
		BootstrapDialog.confirm("您确定要取消预约划扣吗？",function(result) {
			if (result) {
				$(".modal-title").text("取消预约划扣");
				$("#subClose").html("提交");
				var url, argment, callback;
				url = '/deductBalance/goCancelDeductBespoke';
				argment = {};
				load_callback = function(arg){};
				close_callback = function(arg){};
				var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
				//自定义出发关闭回调函数的动作
				$('#subClose').off('click').on('click',function(){
					contents_getJsonForSync(ctx+"/deductBalance/cancelDeductBespoke",{"id":getCheckLendCode()},"post",callFunction, errorCal, null);
					sub.closeSubWindow();
				});
			}
		})
	});
	
	// 划扣结算--释放债权
	$("#updateRevoke").click(function() {
		BootstrapDialog.confirm("您确定要释放债权撤销该笔出借吗？", function(result) {
			if (result) {
				var ids = $("#updateRevoke").attr("name");
				var url = ctx + "/deductBalance/updateRevoke";
				contents_getJsonForSync(url, {'ids' : ids}, "post", callFunction, errorCal, null);
			}
		})
	})
	
	// 划扣失败-->线上划扣
	$("#upLineDeductFail").click(function(){
		if (isChecked()) {
			return;
		}
		var ids = getIds();
		BootstrapDialog.confirm("您确定要批量线上划扣吗？", function(result) {
			if(result){
//				var inputIds = '<input name="ids" type="hidden" value="'
//					+ ids + '"/>'
//				$("#searchForm").append(inputIds);
//				$("#searchForm").attr("action",
//						ctx + "/deductFail/onLineDeduct");
//				// 提交表单
//				$("#searchForm").submit();
//				var parameter = $("#searchForm").serialize()+"&ids="+ids;
				var parameter
				if(ids == null || ids == ''){
					parameter = $("#searchForm").serialize();
				}else{
					parameter = {ids:ids};
				}
				var url =ctx + "/deductFail/onLineDeduct";
				contents_getJsonForSync(url, parameter, "post", callFunction, errorCal, null);
			}
		})
//		var submit = function(v, h, f) {
//			// 复制划扣弹出框内的table放进当前页面的form表单
//			h.find("#onLineTable").clone(true).appendTo(
//					"#searchForm");
//			// 获取选中的id
//			var ids = getIds();
//			var inputIds = '<input name="deductPlatFormID" type="hidden" value="'
//					+ ids + '"/>'
//			$("#searchForm").append(ids);
//			$("#searchForm").attr("action",
//					ctx + "/deductFail/onLineDeduct");
//			// 提交表单
//			$("#searchForm").submit();
//		}
//		$.jBox.open("get:" + ctx + "/deductFail/onLineDeductShow", "线上划扣", 450,
//				300, {submit : submit,persistent : true
//		});
//		bDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
        //自定义出发关闭回调函数的动作
//        $('#subClose').off('click').on('click',function(){
//        	$('#subClose').click(function(){
//    			// 获取选中的id
//				var ids = getIds();
//				var inputIds = '<input name="ids" type="hidden" value="'
//						+ ids + '"/>'
//				$("#searchForm").append(inputIds);
//				// 复制划扣弹出框内的table放进当前页面的form表单
//				$("#onLineTable").appendTo("#searchForm");
//				$("#searchForm").attr("action",
//						ctx + "/deductBalance/onLineDeduct");
//				// 提交表单
//				$("#searchForm").submit();
//    		});
//    		
//        	sub.closeSubWindow();
//        });
	})

	// 划扣失败--线下划扣
	$("#downLineDeductFail").click(function() {
				if (isChecked()) {
					return false;
				}
//				var ids = getIds();
//				var submit = function(v, h, f) {
//					// h.find("#offLineTable").clone(true).appendTo("#searchForm");
//					// var radio=$(this).find("input[name=tp]:checked").val();
//					// // 默认是导入
//					// $("#searchForm").attr("action",ctx+"/deductFail/exportOffLineDeduct");
//					// if(radio=='1'){
//					// $("#searchForm").attr("enctype","multipart/form-data");
//					// $("#searchForm").attr("action",ctx+"/deductFail/importOffLineDeduct");
//					// }
//					// 提交表单
//					$("#searchForm").submit();
//				}
//				$.jBox.open("iframe:" + ctx + "/deductFail/offLineDeductShow",
//						"线下划扣", 400, 300, {
//							submit : submit,
//							ajaxData : {
//								'lendCodes' : ids,
//								'searchForm' : $("#searchForm").serialize()
//							}
//						});
				
			// 调用子窗口的参数
			var url, argment, callback;
			url = '/deductFail/offLineDeductShow';
			argment = null;
			$(".modal-title").text("线下划扣");
			$("#subClose").html("关闭");
			load_callback = function(arg){
				var ids = getIds();
				$("input[name='lendCodes']").val(ids);
			};
			close_callback = function(arg){
			};
			var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
			//自定义出发关闭回调函数的动作
			$('#subClose').off('click').on('click',function(){sub.closeSubWindow();window.reload();});
			})

	// 划扣失败--批量修改状态
	$("#bathDeductFail").click(function() {
		BootstrapDialog.confirm("您确定要批量修改状态吗？",function(result) {
			
			if (result) {
				$(".modal-title").text("批量修改状态");
				$("#subClose").html("提交");
				var url, argment, callback;
				url = '/deductFail/showBatchModel';
				argment = null;
				load_callback = function(arg){
					$("input[name='theDayId']").val();
					$("input[name='applyCodeSub']").val(getIds());
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
					url = ctx + '/deductFail/batchDeductStatus';
					contents_getJsonForSync(url, $("#searchForm1").serialize(), "post", callFunction, errorCal, null);
					sub.closeSubWindow();
				});
			}
			
//			if (result) {
//				var ids = getIds();
//				var inputIds = '<input name="deductPlatFormID" type="hidden" value="' + ids + '"/>'
//				$("#searchForm").append(inputIds);
//				$("#searchForm").attr("action",ctx + "/deductFail/batchDeductStatus");
//				// 提交表单
//				$("#searchForm").submit();
//			}
		})
	})

	// 划扣失败-->协议库对接
	$("#protocolLibraryFail").click(function() {
		if (isChecked()) {
			return;
		}
		BootstrapDialog.confirm("您确定要协议库对接吗？", function(result) {
			if (result) {
				var ids = getIds();
//				var parameter = $("#searchForm").serialize()+"&ids="+ids;
				var parameter
				if(ids == null || ids == ''){
					parameter = $("#searchForm").serialize();
				}else{
					parameter = {ids:ids};
				}
				var url = ctx + "/deductFail/protocolLibrary";
				contents_getJsonForSync(url, parameter, "post", function(result,par){
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
	
	// 划扣失败--导出协议库
	$("#drawButtonProtocolLibrary").click(function() {
		BootstrapDialog.confirm("您确定要导出协议库操作吗？",  function(result) {
			if (result) {
				var ids = getIds();
				var parameter = {};
				var url = ctx + "/deductFail/exportProtocolExcel";
				if(ids == null || ids ==''){
					postFormReplaceGet($("#searchForm"),url)
				}else{
					parameter.ids=ids;
					postParamReplaceGet(parameter,url)
				}
			}
		})
	})

	// 划扣失败--导出划扣失败列表
	$("#failExportExcel").click(function() {
//		if (isChecked()) {
//			return false;
//		}
		BootstrapDialog.confirm("您确定要导出划扣失败列表吗？",function(result) {
			if (result) {
				var ids = getIds();
				var parameter = {};
				var url = ctx + "/deductFail/exportExcel";
				if(ids == null || ids ==''){
					postFormReplaceGet($("#searchForm"),url)
				}else{
					parameter.ids=ids;
					postParamReplaceGet(parameter,url)
				}
			}
		})
	})

	// 划扣失败--释放债权
	$("#batchReleaseCreditor").click(function() {
		if (isChecked()) {
			return false;
		}
		BootstrapDialog.confirm("您确定要批量释放债权吗？", function(result) {
			if (result) {
				var ids = getIds();
				var url = ctx + "/deductFail/batchReleaseCreditor";
				contents_getJsonForSync(url, {
					'ids' : ids
				}, "post", callFunction, errorCal, null);
			}
		})
	})
	
	// 划扣失败-->预约划扣
	$("#bathDeductTheDayFail").click(function(){
		BootstrapDialog.confirm("您确定要预约划扣吗？",function(result) {
			if (result) {
				$(".modal-title").text("预约划扣");
				$("#subClose").html("提交");
				var url, argment, callback;
				url = '/deductFail/theDayDeduct';
				argment = {"id":getIds()/*,"applyDeductTypeName":array[0]*/};
				load_callback = function(arg){
					$("#deductCountMoney").val($("#deductMoney").text());
					$("#deductCount").val($("#lendCount").text());
				};
				close_callback = function(arg){
				};
				var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
				//自定义出发关闭回调函数的动作
				$('#subClose').off('click').on('click',function(){
					url = ctx+"/deductFail/saveDeductBespoke";
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

	// 划扣失败-->取消预约划扣
	$("#cancelDeductBespokeFail").click(function(){
		BootstrapDialog.confirm("您确定要取消预约划扣吗？",function(result) {
			if (result) {
				$(".modal-title").text("取消预约划扣");
				$("#subClose").html("提交");
				var url, argment, callback;
				url = '/deductFail/goCancelDeductBespoke';
				argment = {};
				load_callback = function(arg){};
				close_callback = function(arg){};
				var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
				//自定义出发关闭回调函数的动作
				$('#subClose').off('click').on('click',function(){
					contents_getJsonForSync(ctx+"/deductFail/cancelDeductBespoke",{"id":getCheckLendCode()},"post",callFunction, errorCal, null);
					sub.closeSubWindow();
				});
			}
		})
	});
	
	// 已划扣-->批量发送收款确认书
	$("[id='batchSendFilePdf']").click(function() {
		if (isChecked()) {
			return false;
		}
		var type = $(this).attr("type");
		BootstrapDialog.confirm("您确定要批量发送收款确认书操作吗？",function(result) {
			if (result) {
//				alert(type)
				var ids = getIds();
				var url = ctx + "/deductSuccess/sendEmailFile";
				contents_getJsonForSync(url,{"lendCode":ids,"type":type},"post",callFunction, errorCal, null);
			}
		})
	})
	
	// 已划扣-->发送收款确认书
	$("[id='sendFilePdf']").click(function() {
		var type = $(this).attr("type");
		var lendCode = $(this).attr("applycode");
		BootstrapDialog.confirm("您确定要发送收款确认书操作吗？",function(result) {
			if (result) {
				var url = ctx + "/deductSuccess/sendEmailFile";
				contents_getJsonForSync(url,{"lendCode":lendCode,"type":type},"post",callFunction, errorCal, null);
			}
		})
	})
	
	// 已划扣-->批量下载收款确认书
	$("[id='batchFileDownload']").click(function() {
		var type = $(this).attr("type");
		if (isChecked()) {
			return false;
		}
		BootstrapDialog.confirm("您确定要批量下载收款确认书操作吗？",function(result) {
			if (result) {
				var ids = getIds();
				var object = {};
				var url = ctx + "/deductSuccess/batchFileDownload";
//				window.location.href = url;
				object.ids=ids;
				object.type=type;
				postParamReplaceGet(object,url)
			}
		})
	})

	// 已划扣-->导出excel
	$("#exportExcel").click(function() {
//		if (isChecked()) {
//			return false;
//		}
		BootstrapDialog.confirm("您确定要导出excel操作吗？", function(result) {
			if (result) {
				var ids = getIds();
				var object = {};
				var url = ctx + "/deductSuccess/exportExcel";
				if(ids == null || ids == ''){
					postFormReplaceGet($("#searchForm"),url);
				}else{
					object.ids = ids;
					postParamReplaceGet(object,url);
				}
			}
		})
	})

	// 已划扣-->导出回访表 
	$("#exportTableExcel").click(function() {
//		if (isChecked()) {
//			return false;
//		}
		BootstrapDialog.confirm("您确定要导出回访表 操作吗？",  function(result) {
			if (result) {
				var ids = getIds();
				var url = ctx + "/deductSuccess/exportTableExcel";
				var obj = {};
				if(ids == null || ids == ''){
					postFormReplaceGet($("#searchForm"),url);
				}else{
					obj.ids = ids;
					postParamReplaceGet(obj,url);
				}
			}
		})
	})

	// 已划扣-->文件合成
	$("#FilSynthesis").click(function() {
		if (isChecked()) {
			return false;
		}
		BootstrapDialog.confirm("您确定要文件合成操作吗？", function(result) {
			if (result) {
				var ids = getIds();
				var url = ctx + "/deductSuccess/filSynthesis";
				contents_getJsonForSync(url, {
					'applyCode' : ids
				}, "post", callFunction, errorCal, null);
			}
		})
	})

	
	// 已划扣-->导出金帐户
	$("#exporExcelGold").click(function() {
//		if (isChecked()) {
//			return false;
//		}
		BootstrapDialog.confirm("您确定要导出金帐户操作吗？", function(result) {
			if (result) {
				var ids = getIds();
				var url = ctx + "/deductSuccess/exporExcelGold";
				var obj = {};
				if(ids == null || ids == ''){
					postFormReplaceGet($("#searchForm"),url);
				}else{
					obj.ids = ids;
					postParamReplaceGet(obj,url);
				}
			}
		})
	})
	
	// 已划扣-->办理-->全程流痕
	$("#fullFlowMark").click(function() {
			// 调用子窗口的参数
			var url, argment, callback;
			url = '/deductSuccess/fullFlowMark';
			argment = {applyCode:$(this).attr("name")};
			load_callback = function(arg){
			};
			close_callback = function(arg){
			};
			var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
			//自定义出发关闭回调函数的动作
			$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});	
	})
});

/**
 * 判断是否选择记录
 * @returns {Boolean}
 */
function isChecked(){
	 if($("input[name='checkbox']:checked:not(.checkAll)").size()==0){
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
	 $("input[name='checkbox']:checked:not(.checkAll)").each(function(){
		 if($(this).val()!='on')
			 ids=ids+$(this).val()+",";
	 }) 
	 return ids;
}

/**
 * 获取选择中的出借编号
 * @returns {String}
 */
function getCheckLendCode(){
	var ids="";
	 $("input[name='checkbox2']:checked:not(.checkAll)").each(function(){
		 if($(this).val()!='on')
			 ids=ids+$(this).attr("lendcode")+",";
	 }) 
	 return ids;
}

/**
 * 获取选中ids
 */
function getUncheckIds(){
	 var ids="";
	 $("input[name='checkbox']:not(.checkAll)").each(function(){
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
		 BootstrapDialog.alert('操作成功',function(){
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
	url = ctx + '/deductSuccess/fullFlowMark';
	data = $("#searchForm1").serialize();
	type = 'post'
	contents_getJsonForHtml(url, data, type, function(result){
		//覆盖列表+page
		$('#diabox_c').html(result);
//		initAfterLoad();
	},function(){
		
	},null);
	return false;
}

function show1(){
	var radio = $("input[type=radio]:checked").val();
	if(radio == '5'){
//		$("#actualDeductMoney").attr("type","text");
//		$("#failMoney").attr("type","hidden");
		$("#moneyLabel").html("<span class='red'>*</span>划扣成功金额：");
		$("#T1").attr("style","display:none;");
		$("#actualDeductMoney").attr("readonly","readonly");
		$("#confirmOpinion").attr("required",false);
	}else{
//		$("#actualDeductMoney").attr("type","hidden");
//		$("#failMoney").attr("type","text");
		$("#moneyLabel").html("<span class='red'>*</span>划扣失败金额：");
		$("#confirmOpinion").attr("required",true);
		$("#T1").attr("style","display:block");
		$("#actualDeductMoney").removeAttr("readonly");
	}
}