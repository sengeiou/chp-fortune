/**
 * 根据勾选条数改变显示的总金额和总条数
 */
function changeMoney(src){
	var money = 0;
	var totalMoney = 0;
	var totalCount = 0;
	if(0 == $("#checkBoxOne:checked").length){
		totalMoney = $("#tMoney").val();
		totalCount = $("#count").val();
	}else{
		$("#checkBoxOne:checked").each(function(){
			 if($(this).val()!='on'){
				 money = $(this).parent().parent().find("input[name='money']").val();
				 totalMoney = + money + totalMoney;
				 totalCount = $("#checkBoxOne:checked").length;
			 }
		 }) 
	}
	 $("#totalMoney").html(parseFloat(totalMoney).toFixed(2));
	 $("#totalCount").html(totalCount);
}

/**
 * 页面办理事件
 * @param matchingFileSendStatus
 * @param loanBillRecv
 */
function handle(matchingId,matchingFileStatus,matchingFileSendStatus,lendCode,matchingPayStatus){
	var url, argment, callback;
	url = '/creditor/creditorSend/showDialog';
	argment = {'matchingId':matchingId,'matchingFileStatus':matchingFileStatus,'matchingFileSendStatus':matchingFileSendStatus,'lendCode':lendCode,'matchingPayStatus':matchingPayStatus};
	load_callback = function(arg){
	};
	close_callback = function(arg){
	};
	var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
	//自定义出发关闭回调函数的动作
	$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
}
// 获取选中ids
function getIds(){
	 var ids="";
	 $("input[type='checkbox']:checked").each(function(){
		 if($(this).val()!='on')
			 ids=ids+$(this).val()+";";
	 }) 
	 return ids;
}
//下载回调函数
function uploadCallFunction(result,par){
	if(result == "NG"){
		 BootstrapDialog.alert('文件不存在！');
	 }else{
		flag=1;
	 }
}
//文件合成回调
function pcCallFunction(result,par){
	 if(result == "OK"){
		 BootstrapDialog.alert('操作成功！',function(){
			 location.reload();
		 });
	 }else{
		 BootstrapDialog.alert('操作失败！');
	 };
}
// 发送协议和发送邮件函数回调
function callFunction(result,par){
	 if(result == "OK"){
		 BootstrapDialog.alert('发送成功！',function(){
			 loadingMarkShow();
			 location.reload();
		 });
	 }else{
		 BootstrapDialog.alert('发送失败！');
	 };
}
// 撤销回调函数
function cancelCallFunction(result,par){
	if(result.message == "OK"){
		 BootstrapDialog.alert('撤销成功！',function(){
			 location.reload();
		 });
	 }else{
		 BootstrapDialog.alert('撤销失败:'+result.message);
	 };
}
function errorCal(){
	BootstrapDialog.alert("系统出错,请稍后……");
	
}

$(function(){
	var iframe = getCurrentIframeContents();
	 $('#search',iframe).off('click').on('click',function(){
		 $form = $('#searchForm');
		 $("#pageNo").val("");
		 $form.submit();
	 })
	  // 批量发送协议
	  $('#sendProtocol',iframe).off('click').on('click',function(){
		  var ids = getIds();
		  if(ids.length == 0){
			  BootstrapDialog.alert("请选择需要发送的内容！"); 
			  return;
		  }else{
			  BootstrapDialog.confirm('确定要发送协议吗？', function(result){
			         if(result){
			        	 var url=ctx+"/creditor/creditorSend/sendProtocol";
						 contents_getJsonForSync(
								 url,
								 {'ids':ids},
								 "post",
								 function(result,par){
									 if(result == "OK"){
										 BootstrapDialog.alert('发送成功！',function(){
											 loadingMarkShow();
											 location.reload();
										 });
									 }else{
										 BootstrapDialog.alert('发送失败:'+result,function(){
											 location.reload();
										 });
									 };
								 },
								 errorCal,
								 null
								 );
			         }else{
			        	 return;
			         }
			     });
		  }
	  })
	  // 批量下载协议word
	  $("#batchDownloadWord",iframe).off('click').on('click',function(){
		  var falg= 0;
		  var ids = getIds();
		  if(ids.length == 0){
			  BootstrapDialog.alert("请选择需要下载的内容！"); 
			  return;
		  }else{
			  BootstrapDialog.confirm('确定要下载协议WORD吗？', function(result){
			         if(result){
			        	 var attaFileType = "doc";
			        	 var url = ctx+"/creditor/creditorSend/batchDownload";
			        	 var object = {};
						 object.ids=ids;
						 object.attaFileType=attaFileType;
						 postParamReplaceGet(object,url);
			        	 //window.location.href=url;
			         }else{
			        	 return;
			         }
			     });
		  }
	  })
	  // 批量下载协议pdf
	  $("#batchDownloadPdf",iframe).off('click').on('click',function(){
		  var ids = getIds();
		  if(ids.length == 0){
			  BootstrapDialog.alert("请选择需要下载的内容！"); 
			  return;
		  }else{
			  BootstrapDialog.confirm('确定要下载协议PDF吗？', function(result){
			         if(result){
			        	 var attaFileType = "pdf";
			        	 var url = ctx+"/creditor/creditorSend/batchDownload";
			        	 var object = {};
						 object.ids=ids;
						 object.attaFileType=attaFileType;
						 postParamReplaceGet(object,url);
			        	 //window.location.href=url;
			         }else{
			        	 return;
			         }
			     });
		  }
	  })
	  // 批量导出excel
	  $("#outExcel",iframe).off('click').on('click',function(){
			  BootstrapDialog.confirm('确定要导出EXCEL吗？', function(result){
			         if(result){
			        	 var ids = getIds();
			        	 $("#matchingId").val(ids);
			        	 var url = ctx+"/creditor/creditorSend/outExcel";
						// window.location=url;
						 postFormReplaceGet($("#searchForm"),url);
			         }else{
			        	 return;
			         }
			     });
	  })
	  // 批量合成文件
	   $("#handelDocument",iframe).off('click').on('click',function(){
		   var ids = getIds();
			  if(ids.length == 0){
				  BootstrapDialog.alert("请选择需要合成的内容！"); 
				  return;
			  }else{
				  BootstrapDialog.confirm('确定要合成文件吗？', function(result){
				         if(result){
				        	 var url = ctx+"/creditor/creditorSend/batchSynthesisFile";
							 contents_getJsonForSync(
									 url,
									 {'ids':ids},
									 "post",
									 pcCallFunction,
									 errorCal,
									 null
									 );
				         }else{
				        	 return;
				         }
				     });
			  }
	  })
	   // 文件预览
	   $("#filePreview",iframe).off('click').on('click',function(){
		   var matchingId = $("#mcid").val();
		   var attaFileType = "pdf";
		   var url=ctx+"/creditor/creditorSend/filePreview?matchingId="+matchingId+"&attaFileType="+attaFileType;
		   window.location.href=url;
	   })
	   // 发送邮件 
	   $("#sendEmail",iframe).off('click').on('click',function(){
   			 loadingMarkShow();
		     var ids = $("#mcid").val();
		     var url = ctx+"/creditor/creditorSend/sendProtocol";
			 contents_getJsonForSync(
					 url,
					 {'ids':ids},
					 "post",
					 callFunction,
					 errorCal,
					 null
					 );
	   })
	   // 债权撤销
	   $("#cancelCreditor",iframe).off('click').on('click',function(){			 
		   var matchingId = $("#mcid").val();
		   var lendCode = $("#lCode").val();
		   
		   var url = ctx+"/creditor/creditorSend/cancelCreditor";
		   contents_getJsonForSync(
				   url,
				   {'matchingId':matchingId, 'lendCode':lendCode},
				   "post",
				   cancelCallFunction,
				   errorCal,
				   null
				   );
	   })
	   // 单个下载协议word
	   $("#fileDownloadWord",iframe).off('click').on('click',function(){
		   var matchingId = $("#mcid").val();
		   var attaFileType = "doc";
		   var url = ctx+"/creditor/creditorSend/fileDownload?matchingId="+matchingId+"&attaFileType="+attaFileType;
		   window.location.href=url;
	   })
	   // 单个下载协议pdf
	   $("#fileDownloadPdf",iframe).off('click').on('click',function(){
		   var matchingId = $("#mcid").val();
		   var attaFileType = "pdf";
		   var url = ctx+"/creditor/creditorSend/fileDownload?matchingId="+matchingId+"&attaFileType="+attaFileType;
		   window.location.href=url;
	   })
	   // 单个合成文件
	   $("#synthesisFile",iframe).off('click').on('click',function(){
			 location.reload();
		   var matchingId = $("#mcid").val();
		   var lendCode = $("#lCode").val();
		   var url = ctx+"/creditor/creditorSend/synthesisFile";
		   contents_getJsonForSync(
				   url,
				   {'matchingId':matchingId,'lendCode':lendCode},
				   "post",
				   pcCallFunction,
				   errorCal,
				   null
				   );
	   })
		// 全选时计算
		$("input[type='checkbox'].checkAll").change(function(){
			var money = 0;
			var totalMoney = 0;
			var totalCount = 0;
			if($("#checkAll").prop("checked")){
				$("#checkBoxOne:checked").each(function(){
						 money = $(this).parent().parent().find("input[name='money']").val();
						 totalMoney = parseFloat(money) + parseFloat(totalMoney);
						 totalMoney = totalMoney.toFixed(2);
						 totalCount = $("#checkBoxOne:checked").length;
				 }) 
			}else{
				totalMoney = $("#tMoney").val();
				totalCount = $("#count").val();
			}
			
			 $("#totalMoney").html(totalMoney);
			 $("#totalCount").html(totalCount);
});   
 })