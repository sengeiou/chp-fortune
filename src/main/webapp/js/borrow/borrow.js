$(document).ready(function() {
	// 提交检索条件
	$('#search').click(function(){
		$form = $('#searchForm');
		if ($form.validate().form()) {
			$("#pageNo").val("");
			$form.submit();
		}
	});
	// 点击多选框计算金额
	var sumMoney=0;
	var sumMoneyOneByOne=0;
	var sumNum = 0;
	$("input[type='checkbox']#checkOne").change(function(){
		 if ($(this).prop("checked")){
			sumMoney = parseFloat(restoreFormatCurrency($(this).parent('#creditValueIdCheckbox').nextAll("#loanCreditValueOneByOne").val()))+sumMoney;
			sumMoneyOneByOne = parseFloat(restoreFormatCurrency($(this).parent('#creditValueIdCheckbox').nextAll("#loanCreditValue").text()))+sumMoneyOneByOne;
			sumNum = sumNum+1;
			 $("#money").text(formatCurrency(sumMoney));
			 $("#moneyOneByOne").text(formatCurrency(sumMoneyOneByOne));
			 $("#count").text(sumNum);
		 } else{
			sumMoney = parseFloat(jsSub(sumMoney,restoreFormatCurrency($(this).parent('#creditValueIdCheckbox').nextAll("#loanCreditValueOneByOne").val())));
			sumMoneyOneByOne = parseFloat(jsSub(sumMoneyOneByOne,restoreFormatCurrency($(this).parent('#creditValueIdCheckbox').nextAll("#loanCreditValue").text())));
			sumNum = sumNum-1;
			if(sumNum==0){
				$("#money").text($("#hiddenMoney").val());
				$("#moneyOneByOne").text($("#hiddenMoneyOneByOne").val());
				 $("#count").text($("#hiddenCount").val());
			}else{
				$("#money").text(formatCurrency(sumMoney));
				$("#moneyOneByOne").text(formatCurrency(sumMoneyOneByOne));
				$("#count").text(sumNum);
			}
		 }
	});
	// 全选时计算
	$("input[type='checkbox'].checkAll").change(function(){
		sumMoney=0;
		sumMoneyOneByOne=0;
		sumNum = 0;
		if($(this).prop("checked")){
			$("#checkOne:checked").each(function(){
				sumMoney = parseFloat(sumMoney) + parseFloat(restoreFormatCurrency($(this).parent('#creditValueIdCheckbox').nextAll("#loanCreditValueOneByOne").val()));
				sumMoneyOneByOne = parseFloat(sumMoneyOneByOne) + parseFloat(restoreFormatCurrency($(this).parent('#creditValueIdCheckbox').nextAll("#loanCreditValue").text()));
				sumNum++;
				$("#money").text(formatCurrency(sumMoney));
				$("#moneyOneByOne").text(formatCurrency(sumMoneyOneByOne));
				$("#count").text(sumNum);
			})	
		}else{
			$("#money").text(formatCurrency($("#hiddenMoney").val()));
			$("#moneyOneByOne").text(formatCurrency($("#hiddenMoneyOneByOne").val()));
			 $("#count").text($("#hiddenCount").val());
		}
	});
	
	// 债权上传
	$("#borrowUpload").click(function(){
		// 调用子窗口的参数
		var url, argment, callback;
		url = "/borrow/goBorrowUpload";
		argment = {};
		close_callback = function(ifame){};
		load_callback = function(iframe){
			$("#btnAttachment").remove();
			$('.attachment_div').show();
			var myzone = DropzoneHandle.initDropZone('#my-awesome-dropzone',null,
					{"url":ctx+'/borrow/borrowUpload',
						"acceptedFiles":".xlsx,.xls",
						"dictInvalidFileType":"请上传Excel文件",
						"init":function(){
							this.on("success", function(file, result){
							if(result!=""){
							if(result=="获取Excel文件异常,请核对后在提交"){
								$(".dz-success-mark").remove();
								BootstrapDialog.alert(result);	
								return;
							}else{
								var info="";
								 var res = eval("("+result+")");
								 info="<p>总共：<span style='color:#ff0000;'>&nbsp;"+res.num+"&nbsp;</span>笔债权<span style='color:#ff0000;'>&nbsp;"+res.numValue+"&nbsp;</span>元，成功上传<span style='color:#ff0000;'>&nbsp;"+res.sucNum+"&nbsp;</span>笔<span style='color:#ff0000;'>&nbsp;"+res.sucValue+"&nbsp;</span>元</p>";
								 for(var i in res.listUsers){
									 info =info+res.listUsers[i]+"<br>";
							        }
								BootstrapDialog.alert(info);
							}
							}
							$("a.dz-remove").remove();
						});
					}});
			$("[type='button'].close").click(function(){
				backBorrowList();
			});
		};
		var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
		//自定义出发关闭回调函数的动作
		$('#subClose').off('click').on('click',function(){
			sub.closeSubWindow();
		});
	});
});

// 可用债权批量分配
function borrowBatchAlot(){
	var batchBorrowInfo = "";
	var loanCreditValue=0;
	var loanCreditValueTemp;
	var loanType;
	var num = $("#checkOne:checked").length;
	if(num == 0){
		BootstrapDialog.alert("请选择分配的债权");	
		return;
	}
	$("#checkOne:checked").each(function(){
		loanCreditValueTemp = parseFloat(restoreFormatCurrency($(this).parent('#creditValueIdCheckbox').nextAll("#loanCreditValue").text()));
		loanType = $(this).parent('#creditValueIdCheckbox').nextAll("#loanType").text();
		if(loanCreditValueTemp == 0.0 || loanType == '房借'){
			return false;
		}
		loanCreditValue =loanCreditValue + loanCreditValueTemp;
		batchBorrowInfo = this.value+","+batchBorrowInfo;
	})
	if(loanCreditValueTemp == 0.0 || loanType == '房借'){
		 BootstrapDialog.alert("你选择的数据中存在不可分配的,请核对后再进行操作");	
		 return;
	}
	 BootstrapDialog.confirm("你共选中"+num+"条,批量分配的债权价值为￥"+formatCurrency(loanCreditValue),function(result){
		 if(result){
			 contents_getJsonForSync(
				ctx+"/borrow/borrowBatchAllot",
				{"batchBorrowInfo":batchBorrowInfo},
				'post',
				function(data){
	    			if (data == '') {
	    				$("#searchForm").submit();
					} else {
						BootstrapDialog.confirm(data,function(result){
							if(result){
								$("#searchForm").submit();
							}
						});
					}
	    		});
		 }
	 });
}

/**
 * 查看分配记录
 * 
 * @param id
 */
function his(creditValueId) {
	// 调用子窗口的参数
	var url, argment, callback;
	url = "/his/borrowAolltHis";
	argment = {'creditValueId':creditValueId};
	close_callback = function(ifame){};
	load_callback = function(iframe){};
	var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
	//自定义出发关闭回调函数的动作
	$('#subClose').off('click').on('click',function(){
		sub.closeSubWindow();
	});
}
	// 返回到可用债权列表
	function backBorrowList(){
		window.location =ctx+"/borrow/backBorrowList";
	} 

/**
 * 可用债权分配
 * @param creditValueId
 */
function goAllot(creditValueId){
	var borrowCreditValue =0;
	var iframe = getCurrentIframeContents;
	if(parseFloat($("#loanCreditValue").text())==0){
		BootstrapDialog.alert("可用金额为0,不可分配");
		return false;
	}
	// 调用子窗口的参数
	var url, argment, callback;
	url = '/borrow/goAllot';
	argment = {'creditValueId':creditValueId};
	close_callback = function(ifame){};
	load_callback = function(iframe){
		// 监听拆分金额的事件
		$('#allot').bind('input propertychange', function() {
			var allotMoney = $("#allot",iframe).val();
			borrowCreditValue = $("#cv",iframe).val();
			$form = $('#allotForm',iframe);
			if(!$form.validate().form()){
				//验证不成功
				return false;
			}
			$("#surplus",iframe).val(jsSub(borrowCreditValue,allotMoney));
		});
		/*$("#allot",iframe).on('blur',function() {
			var allotMoney = $("#allot",iframe).val();
			borrowCreditValue = $("#cv",iframe).val();
			$form = $('#allotForm',iframe);
			if(!$form.validate().form()){
				//验证不成功
				return false;
			}
			$("#surplus",iframe).val(jsSub(borrowCreditValue,allotMoney));
		});*/
		// 绑定分配按钮
	    $('.allot',iframe).off('click').on('click',function(){
	    	$form = $('#allotForm',iframe);
	    	if($form.validate().form()){
		    	contents_getJsonForSync(
		    		ctx+"/borrow/borrowAllot",	
		    		$("#allotForm").serialize(),
		    		"POST",
		    		function(result){
		    			if (result == '') {
		    				window.location =ctx+"/borrow/goBorrowLook?creditValueId="+creditValueId;
						} else {
							BootstrapDialog.alert(result);
				    		 return;
						}
		    		});
			}
	    /*	if($("#surplus").val()<0){
	    		 BootstrapDialog.alert("分配金额大于可用债权价值");
	    		 return;
	    	}*/
	     });
	    // 绑定取消按钮
	    $('.qx',iframe).off('click').on('click',function(){
	    	   window.location =ctx+"/borrow/goBorrowLook?creditValueId="+creditValueId;
		 	   sub.closeSubWindow(iframe);//关闭事件
		 });
	}
	var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
	//自定义出发关闭回调函数的动作
	$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
	
}

/**
 * 分配记录分页
 * @param n
 * @param s
 * @returns {Boolean}
 */
function subPage(n,s){
	if (n)
		$("#pageNo").val(n);
	if (s)
		$("#pageSize").val(s);
	var url = $("#searchForm").attr('action');
	contents_getJsonForHtml(
		url,
		$("#searchForm").serialize(),
		'post',
		function(result){
			$('#allotHisListDiv').html(result);
		}
	);
	return false;
}

/**
 * 债权查看列表分页
 * @param n
 * @param s
 * @returns {Boolean}
 */
function borrowLookPage(n,s){
	if (n)
		$("#borrowLookPageNo").val(n);
	if (s)
		$("#borrowLookPageSize").val(s);
	var url = $("#borrowLookForm").attr('action');
	contents_getJsonForHtml(
		url,
		$("#borrowLookForm").serialize(),
		'post',
		function(result){
			$('#borrowLookListDiv').html(result);
		}
	);
	return false;
}
// 导出excel
function outExcel(){
	var i=0;
	var data = new Array();
	$("#checkOne:checked").each(function(){
		data[i++] = $(this).val();
	})
	if(data.length>0){
		BootstrapDialog.confirm('您确定要导出选中的数据吗？', function(result){
			if(result){
				$("#creditValueIdList").val(data);
				var url=ctx+"/borrow/outExcel";
				postFormReplaceGet($("#searchForm"), url);
			}
		});
	}else{
		$("#creditValueIdList").val("");
		if (!$('#searchForm').validate().form()) {
			BootstrapDialog.confirm('你的输入条件出错,可能会导出全部数据',function(result){
				if(result){
					var url=ctx+"/borrow/outExcel";
					window.location=url;
				}
			});
		}else{
			BootstrapDialog.confirm('您确定要导出excel吗？', function(result){
				if(result){
					var url=ctx+"/borrow/outExcel";
					postFormReplaceGet($("#searchForm"), url);
				}
			});
		}
	}
}

/**
 * 导出债权Excel
 */
function exportBorrow(){
	var i=0;
	var data = new Array();
	$("#checkOne:checked").each(function(){
		data[i++] = $(this).val();
	})
	if(data.length>0){
		BootstrapDialog.confirm('您确定要导出选中的数据吗？', function(result){
			if(result){
				$("#creditValueIdList").val(data);
				var url=ctx+"/borrow/exportBorrow";
				postFormReplaceGet($("#searchForm"), url);
			}
		});
	}else{
		$("#creditValueIdList").val("");
		if (!$('#searchForm').validate().form()) {
			BootstrapDialog.confirm('你的输入条件出错,可能会导出全部数据',function(result){
				if(result){
					var url=ctx+"/borrow/exportBorrow";
					window.location=url;
				}
			});
		}else{
			BootstrapDialog.confirm('您确定要导出excel吗？', function(result){
				if(result){
					var url=ctx+"/borrow/exportBorrow";
					postFormReplaceGet($("#searchForm"), url);
				}
			});
		}
	}
	
}








//向大金融推送债权
function pushBorrow(){
	$("#pushBorrowButtun").attr("disabled","disabled");
	var i=0;
	var data = new Array();
	$("#checkOne:checked").each(function(){
		data[i++] = $(this).val();
	})
	if(data.length>0){	
		$("#creditValueIdList").val(data);
	}else{
		$("#creditValueIdList").val("");
		if(
			($("input[name='borrowerName']").val()==null || $("input[name='borrowerName']").val()=="")  &&
			($("input[name='borrowCreditValueFrom']").val()==null || $("input[name='borrowCreditValueFrom']").val()=="")  &&
			($("input[name='borrowCreditValueTo']").val()==null || $("input[name='borrowCreditValueTo']").val()=="")  &&
			($("input[name='borrowMonthsSurplusFrom']").val()==null || $("input[name='borrowMonthsSurplusFrom']").val()=="")  &&
			($("input[name='borrowMonthsSurplusTo']").val()==null || $("input[name='borrowMonthsSurplusTo']").val()=="")  &&
			($("select[name='borrowBakcmoneyDay']").val()==null || $("select[name='borrowBakcmoneyDay']").val()=="")  &&
			($("input[name='borrowMonthRate']").val()==null || $("input[name='borrowMonthRate']").val()=="")  &&
			($("input[name='borrowBackmoneyFirdayFrom']").val()==null || $("input[name='borrowBackmoneyFirdayFrom']").val()=="")  &&
			($("input[name='borrowBackmoneyFirdayTo']").val()==null || $("input[name='borrowBackmoneyFirdayTo']").val()=="")  &&
			($("select[name='borrowerJob']").val()==null || $("select[name='borrowerJob']").val()=="")  &&
			($("select[name='borrowType']").val()==null || $("select[name='borrowType']").val()=="")  &&
			($("select[name='borrowTrusteeFlag']").val()==null || $("select[name='borrowTrusteeFlag']").val()=="")  &&
			($("select[name='dicLoanDistinguish']").val()==null || $("select[name='dicLoanDistinguish']").val()=="") && 
			($("input[name='loanBackmoneyLastdayFrom']").val()==null || $("input[name='loanBackmoneyLastdayFrom']").val()=="") && 
			($("input[name='loanBackmoneyLastdayTo']").val()==null || $("input[name='loanBackmoneyLastdayTo']").val()=="")  
		){
			$("#pushBorrowButtun").removeAttr("disabled");
			BootstrapDialog.alert("请输入查询条件");
			return;
		}
	}
	$("#pushHiddenMoney").val($("#money").text());
	$("#pushHiddenMoneyOneByOne").val($("#moneyOneByOne").text());
	$("#pushHiddenCount").val($("#count").text());
	contents_getJsonForSync(
		ctx+"/borrow/prepareCheckPushBorrow",	
		$("#searchForm").serialize(),
		"POST",
		function(result){
			//返回0000代表成功
			if (result.code == '0000') {
					BootstrapDialog.confirm(result.message, function(result){
						if(result){
							contents_getJsonForSync(
									ctx+"/borrow/insertCheckPushBorrow",	
									$("#searchForm").serialize(),
									"POST",
									function(result){
										if (result.code == '0000') {
												BootstrapDialog.alert(result.message,function() { 
												$("#searchForm").submit();
											});
										}else{
											$("#pushBorrowButtun").removeAttr("disabled");
											BootstrapDialog.alert(result.message);
								    		 return;
										}
									});
							
						}else{
							$("#pushBorrowButtun").removeAttr("disabled");
							return;
							}
					});
			} else {
				$("#pushBorrowButtun").removeAttr("disabled");
				BootstrapDialog.alert(result.message);
	    		 return;
			}
		});

}

