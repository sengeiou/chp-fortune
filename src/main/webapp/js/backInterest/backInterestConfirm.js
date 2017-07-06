// 批量退回至待回息列表
function backAll(){
	
	iframe = getCurrentIframeContents();
	var forms = $(iframe).find('#searchForm').serialize();
			
	$form = $('#brch',getCurrentIframeContents());
	$selectRs = $("select#cemine");// 获取退回原因下拉元素
	$textA = $("textarea#tar");// 获取退回原因其他原因下拉元素
	
	var backiId = $('[name="ids"]:checked').map(function() {
		return this.value;
	}).get().join(',');
	var checkExamine = $('#cemine').val();
	var textAre = $('#tar').val();
	var purl = "backInterestConfrim/returnExcuteInterest";
	var surl = "backInterestConfrim/loadBackInterestConfrimList";
	
		if($form.validate().element($selectRs)){
			
			var s = $("#cemine").val();
			if(s=="6"){
				if($form.validate().element($textA)){
					forms = forms +"&backiId="+backiId+"&textAre="+textAre;
					loadingMarkShow();
					submits(purl,surl,forms);
				}
				return false;
			}else{
				forms = forms +"&backiId="+backiId+"&checkExamine="+checkExamine;
				loadingMarkShow();
				submits(purl,surl,forms);
			}
		}
		return false;
}

// 单条退回至待回息列表显示窗
function backOne(){
	if($("#bf").is(":hidden")){
		$('#bf').show();
	}else{
		$('#bf').hide();
	}
}

// 详情页提交退回至待回息列表
function sendOne(purl,surl){
	
	iframe = getCurrentIframeContents();
	var forms = $(iframe).find('#backFirst').serialize();
	var s = $("#backResult").val();
	if(s=="3"){
		BootstrapDialog.alert("此数据为处理中状态，不能进行任何操作！！！");
	}else{
		$form = $('#backFirst',getCurrentIframeContents());
		$BackSe =  $("textarea#tack");
		if($form.validate().element($BackSe)){
			loadingMarkShow();
			limitCommit(forms,purl,surl);
		}
		return false;
	}
}

// 待回息确认列表上传回盘结果
function uploadResultWindow(){
	
	iframe = getCurrentIframeContents();
	$(".modal-title",iframe).html("上传回盘结果");
	$("#hi").val("4");
	//弹出框参数
	var url, argment, callback;
	url = "/backInterestCommon/uploadResultWinow";
	argment = null;
	load_callback = function(arg){
		
	};
	close_callback = function(arg,frame){

	};
    
	var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
	$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
	
}

// 待回息确认列表上传回息结果
function uploadResult(){
	$form = $('#uploadResult',getCurrentIframeContents());
	$plat =  $("select#plat");// 上传平台
	var fil = $("#uplodfi").val();// 上传的文件
	
	if($form.validate().element($plat)){
		if(fil != null && fil != ""){
			var xy = fil.lastIndexOf(".");
			var tyt = fil.substr((xy+1));
			if("xls"==tyt || "xlsx"==tyt){
				
				form = $(".modal-body").find("[id=uploadResult]");
		    	var formData = new FormData(form[0]);
		    	loadingMarkShow();
		    	upResult(formData);
			}else{
				BootstrapDialog.alert("只支持xls和xlsx格式文件！请重新选择");
			}
			
		}else{
			BootstrapDialog.alert("您没有上传文件，请选择文件！");
		}
	}
	return false;
}
// 上传回盘结果
function upResult(formData){
	
	url = ctx+"/backInterestConfrim/uploadResult";
	data = formData;
	successCal = function(result){
		loadingMarkHide();
		if(result=='true'){
			BootstrapDialog.alert("操作成功",function(){loadingMarkShow();$('#search').click()});
       	 }else{
       		 BootstrapDialog.alert(result,function(){loadingMarkShow();$('#search').click()});
       	 }
	}
	errorCal = function (){
		loadingMarkHide();
		BootstrapDialog.alert("服务器忙碌中······");
	};
	contents_getJsonForFileUpload("", url, formData, successCal, errorCal, true);
}

// 回息确认详情页校验
function confrims(purl,surl){
	
	iframe = getCurrentIframeContents();
	$form = $('#applyForm',iframe);
	var forms = $(iframe).find('#applyForm').serialize();
	
	$radioChk = $("input#rar");//获取单选按钮元素
	var s = $("#backResult").val();
	if(s=="3"){
		BootstrapDialog.alert("此数据为处理中状态，不能进行任何操作！！！");
	}else{
		if($form.validate().element($radioChk)){
			
			var item = $('[name="checkExaminetype"]:checked').val();
			$inpDay = $("input#bkma");//获取回息日期输入元素
			$selectRs = $("select#cemine");// 获取退回原因下拉元素
			$textA = $("textarea#tar");// 获取退回原因其他原因下拉元素
			
			if(item=="1"){// 通过
				$("#cemine").removeAttr("select_required");
				$("#tar").removeAttr("textarea_required");
				$("#tar").removeAttr("required");
				if($form.validate().element($inpDay)){
					loadingMarkShow();
					//验证成功
					limitCommit(forms,purl,surl);
				}
				return false;
			}
			if(item=="2"){// 不通过
				$("#bkma").removeAttr("required");
				if($form.validate().element($selectRs)){
					var s = $("#cemine").val();
					if(s=="6"){
						if($form.validate().element($textA)){
							loadingMarkShow();
							limitCommit(forms,purl,surl);
						}
						return false;
					}else{
						loadingMarkShow();
						//验证成功
						limitCommit(forms,purl,surl);
					}
				}
				return false;
			}
		}
		return false;
	}
}

// 回息确认列表批量确认
function branchConfrim(purl,surl){
	
	iframe = getCurrentIframeContents();
	var forms = $(iframe).find('#searchForm').serialize();
	var s = $("#hi").val();
	var data = null;
	
	if(s=="1"){
		
		$form = $('#winForm',getCurrentIframeContents());
		// 获取页面上需要提交的数据
		var checkExamine = $('#cemine').val();
		var backiId = $('[name="ids"]:checked').map(function() {
			return this.value;
		}).get().join(',');
		var date = {"backiId":backiId};
		var backMoneyDay = $('#backMoneyDay').val();
		var item = $('[name="checkExaminetype"]:checked').val();
		var textAre = $("#tar").val();
			
		$radioChk = $("input#cet");//获取单选按钮元素
		BootstrapDialog.confirm("确认提交吗?",function(result){
			if(result){
				if($form.validate().element($radioChk)){
			
					$selectRs = $("select#cemine");// 获取退回原因下拉元素
					$textA = $("textarea#tar");// 获取退回原因其他原因下拉元素
					
					if(item=="1"){// 通过
						$("#cemine").removeAttr("select_required");
						$("#tar").removeAttr("textarea_required");
						$("#tar").removeAttr("required");
						$("#plt").removeAttr("required");
						$bmad = $("input#backMoneyDay");// 获取回息日期元素
						if($form.validate().element($bmad)){
							//验证成功
							//异步提交批量回息申请确认
							forms = forms +"&checkExaminetype="+item+"&backiId="+backiId+"&backMoneyDay="+backMoneyDay;
							loadingMarkShow();
							submits(purl,surl,forms);
						}
						return false;
					}
					if(item=="2"){// 不通过
						$("#plt").removeAttr("required");
						$("#backMoneyDay").removeAttr("required");
						if($form.validate().element($selectRs)){
							var s = $("#cemine").val();
							if(s=="6"){
								if($form.validate().element($textA)){
									forms = forms +"&checkExaminetype="+item+"&backiId="+backiId+"&textAre="+textAre;
									loadingMarkShow();
									submits(purl,surl,forms);
								}
								return false;
							}else{
								//验证成功
								forms = forms +"&checkExaminetype="+item+"&backiId="+backiId+"&checkExamine="+checkExamine;
								loadingMarkShow();
								submits(purl,surl,forms);
							}
						}
						return false;
					}
				}
				return false;
			}
		});
	}else if(s=="3"){
		backAll();
	}else if(s=="4"){// 回息确认列表上传回息结果
		uploadResult();
	}
}

/*
 * 批量退回弹窗(待回息确认批量退回至待回息)
 */
function returnWindow(){
		
	var inst="";//保存回息ID
	
	iframe = getCurrentIframeContents();
	$(".modal-title",iframe).html("批量退回至待回息");
	$("#hi").val("3");
	//弹出框参数
	var url, argment, callback;
	argment = null;
	load_callback = function(arg){
		
	};
	close_callback = function(arg){
	};
	
	var s = $("#ids").length;
	if(s == 0){
		BootstrapDialog.alert("页面中没有数据可以提交！！");
	}else{
		if($('[name="ids"]:checked').length == 0){
			BootstrapDialog.confirm("您没有选中任何一条数据，系统将默认选中本列表中全部数据并提交,是否确认提交全部？",function(result){
				if(result){
					url = "/finishBackInterest/returnWindow";
					var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
					//自定义出发关闭回调函数的动作
		            $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
				}
			});
		}else{
			BootstrapDialog.confirm("确认批量回息退回吗?",function(result){
				if(result){
					url = "/finishBackInterest/returnWindow";
					var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
					//自定义出发关闭回调函数的动作
		            $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
				}
			});			
		}
	}
}

/*
 * 批量退回弹窗对提前赎回的数据进行提示(待回息确认批量退回至待回息)
 */
function returnBackWindow(){
		
	var inst="";//保存回息ID
	
	iframe = getCurrentIframeContents();
	$(".modal-title",iframe).html("批量退回至待回息");
	$("#hi").val("3");
	//弹出框参数
	var url, argment, callback;
	argment = null;
	load_callback = function(arg){
		
	};
	close_callback = function(arg){
	};
	
	var s = $("#ids").length;
	if(s == 0){
		BootstrapDialog.alert("页面中没有数据可以提交！！");
	}else{
		if($('[name="ids"]:checked').length == 0){
			BootstrapDialog.confirm("您没有选中任何一条数据，系统将默认选中本列表中全部数据并提交,是否确认提交全部？",function(result){

				if(result){
					$.ajax({
						url:ctx+"/backInterestConfrim/searchAheadBackidsFrom/",
						type:"post",
						data:$("#searchForm").serialize(),
						dataType:"json",
						async:false,
						success:function(obj){
							var backid=obj.message;
							if(backid.length==0){
								url = "/finishBackInterest/returnWindow";
								var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
								//自定义出发关闭回调函数的动作
					            $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
							}else{
								BootstrapDialog.confirm("确认批量提交吗?存在已操作过提前赎回的数据，出借编号为："+backid,function(result){
									if(result){
										url = "/finishBackInterest/returnWindow";
										var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
										//自定义出发关闭回调函数的动作
							            $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
									}
								});
							}
						},
						error:function(obj){
							BootstrapDialog.alert("服务器忙碌中······");
						}
					});
				}
			});
		}else{
			var	data = {"backiId":$("input[name='ids']:checked").map(function(){
			      return $(this).val();
		    }).get().join(",")};
			var lendCode="";
			$.ajax({
			    url:ctx+"/backInterestConfrim/searchAheadBackid/",
			    type:"post",
			    async:false,  
			    data:data,
			    success:function(obj){
			    	var objreu = JSON.parse(obj);
			    	lendCode=objreu.message;
			    }
			});
			if(lendCode==""){
				BootstrapDialog.confirm("确认批量回息退回吗?",function(result){
					if(result){
						url = "/finishBackInterest/returnWindow";
						var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
						//自定义出发关闭回调函数的动作
			            $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
					}
				});	
			}else{
				BootstrapDialog.confirm("确认批量回息退回吗?存在已操作过提前赎回的数据，出借编号为："+lendCode,function(result){
					if(result){
						url = "/finishBackInterest/returnWindow";
						var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
						//自定义出发关闭回调函数的动作
			            $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
					}
				});	
			}
		}
	}
}

/*
 * 批量退回至待回息，查询当前列表中数据有没有回盘结果为处理中的
 */
function findBackResultCount(){
	
	iframe = getCurrentIframeContents();
	backiId = $('[name="ids"]:checked').map(function() {
		return this.value;
	}).get().join(',');
	inst = encodeURI(encodeURI(backiId));
	var forms = $(iframe).find('#searchForm').serialize();
	
	data = {"backiId":backiId};
	url = ctx+"/backInterestConfrim/findBackResult?&"+forms;
	timeout = 100000;
	dataType= "json";
	successCal = function(result){
       	 if(result>0){
       		 BootstrapDialog.alert("批量提交数据中含有回盘结果为“处理中”的数据,不能进行批量操作");
       	 }else{
       		returnBackWindow();
       	 }
	};
	errorCal = function (){
		BootstrapDialog.alert("服务器忙碌中······");
	};
	contents_getJsonForSync(url, data, 'post', successCal,errorCal,null);
}


/*
 * 批量回息确认弹窗
 */
function batchConfrimWindow(){
	
	var s = $("#ids").length;
	if(s == 0){
		BootstrapDialog.alert("页面中没有数据可以操作！！");
	}else{
		iframe = getCurrentIframeContents();
		$(".modal-title",iframe).html("批量回息操作");
		$("#hi").val("1");
		var args = {};
		if ($("input[name='ids']:checked").length<1) {
			// 无勾选
			args = $("#searchForm").serializeObject();
			if(args!=null && args!=""){
				for(var o in args){ 
					if(args[o] instanceof Array){
						args[o]=args[o].toString();
					}
			      
				} 
			}
		} else {
			// 有勾选
			args = {"backiId":$("input[name='ids']:checked").map(function(){
			      return $(this).val();
		    }).get().join(",")};
		}
		// 调用子
		//弹出框参数
		var url, argment, callback;
    	argment = args;
    	load_callback = function(arg){
    		
    	};
    	close_callback = function(arg){
    	};
        
		if($('[name="ids"]:checked').length == 0){
			BootstrapDialog.confirm("您没有选择任何一条数据，系统将默认选中全部数据并提交,是否确认提交全部？",function(result){
				if(result){
					$.ajax({
						url:ctx+"/backInterestConfrim/searchAheadBackidsFrom/",
						type:"post",
						data:$("#searchForm").serialize(),
						dataType:"json",
						async:false,
						success:function(obj){
							var backid=obj.message;
							if(backid.length==0){
								url = "/backInterestConfrim/batchWindow";
								var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
								//自定义出发关闭回调函数的动作
								
					            $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
							}else{
								BootstrapDialog.confirm("确认批量提交吗?存在已操作过提前赎回的数据，出借编号为："+backid,function(result){
									if(result){
										url = "/backInterestConfrim/batchWindow";
										var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
										//自定义出发关闭回调函数的动作
							            $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
									}
								});
							}
						},
						error:function(obj){
							BootstrapDialog.alert("服务器忙碌中······");
						}
					});
				}
			});
		}else{
			var	data = {"backiId":$("input[name='ids']:checked").map(function(){
			      return $(this).val();
		    }).get().join(",")};
			$.ajax({
				url:ctx+"/backInterestConfrim/searchAheadBackid/",
			    type:"post",
			    async:false,  
			    data:data,
			    dataType:"json",
			    success:function(obj){
			    	var lendCode="";
		    		lendCode=obj.message;
		    		if(lendCode==""){
						BootstrapDialog.confirm("确认批量提交吗?",function(result){
							if(result){
								url = "/backInterestConfrim/batchWindow";
								var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
								//自定义出发关闭回调函数的动作
					            $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
							}
						});	
					}else{
						BootstrapDialog.confirm("确认批量提交吗?存在已操作过提前赎回的数据，出借编号为："+lendCode,function(result){
							if(result){
								url = "/backInterestConfrim/batchWindow";
								var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
								//自定义出发关闭回调函数的动作
					            $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
							}
						});	
					}	
			    }
			});	
		}
	}
}

//回息确认单条进行处理时进行提示
function singleConfrim(backid){
	$.ajax({
	    url:ctx+"/backInterestConfrim/searchAheadBackid/",
	    type:"post",
	    async:false,
	    dataType:"json",
	    data:{"backiId":backid},
	    success:function(obj){
	    	var lendCode=obj.message;
	    	if(lendCode.length!=0){
				BootstrapDialog.confirm("此数据已操作过提前赎回",function(result){
					if(result){
						window.location.href=ctx+'/backInterestConfrim/goConfrimPage?code='+backid;
					}
				});	
			}else{
				window.location.href=ctx+'/backInterestConfrim/goConfrimPage?code='+backid;
			}
	    }
	});
}