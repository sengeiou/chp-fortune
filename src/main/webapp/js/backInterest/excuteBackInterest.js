//线上回息弹窗
function onlineWindow(){
	var s = $("#ids").length;
	var b = $('[name="trusteeshipFlag"]:checked').val();
	if(s == 0){
		BootstrapDialog.alert("页面中没有数据可以操作！！");
	}else{
		iframe = getCurrentIframeContents();
		$(".modal-title",iframe).html("线上回息");
		// 此行代码用来提交时识别提交的走向
		$("#hi").val("4");
		//弹出框参数
		var url, argment, callback;
		url = "/excuteBackInterest/onLineBackInterestWinow";
		argment = {'flag':b};
		load_callback = function(arg){
			
		};
		close_callback = function(arg,frame){
	
		};
	    
		var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
		$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
	}
}

// 线上回息提交
function onLineComit(){
	
	loadingMarkShow();
	var iframe = getCurrentIframeContents();
	var forms = $(iframe).find('#searchForm').serialize();
	var pt = $("#plt  option:selected").val();
	var dt = $("#dt  option:selected").val();
	
	// 获取选择中的记录
	var inst = $('[name="ids"]:checked').map(function() {
		return this.value;
	}).get().join(',');
	
	url = ctx + "/excuteBackInterest/lineComit";
	timeout = 1000000;
	forms = forms +"&platformId="+pt+"&backiId="+inst;
	successCal = function(result){
		loadingMarkHide();
		if(result=='true'){
			BootstrapDialog.alert("操作成功",function(){loadingMarkShow();$('#search').click()});
		}else{
			BootstrapDialog.alert(result,function(){loadingMarkShow();$('#search').click()});
		}
	};
	errorCal = function (){
		loadingMarkHide();
		BootstrapDialog.alert("服务器忙碌中······");
	};
	contents_getJsonForSync(url, forms, 'post', successCal,errorCal,null);
}

//线下回息弹窗
function lineWindow(){
	var s = $("#ids").length;
	var b = $('[name="trusteeshipFlag"]:checked').val();
	if(s == 0){
		BootstrapDialog.alert("页面中没有数据可以操作！！");
	}else{
		iframe = getCurrentIframeContents();
		$(".modal-title",iframe).html("线下回息");
	
		// 此行代码用来提交时识别提交的走向
		$("#hi").val("2");
		//弹出框参数
		var url, argment, callback;
		url = "/excuteBackInterest/lineBackInterestWinow";
		argment = {'flag':b};
		load_callback = function(arg){
			fortune.showType(iframe,'blt');
		};
		close_callback = function(arg,frame){
	
		};
		var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
		$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
	}
}

// 导出/上传
function comit(){
	var inOrOut = $("input[name='tp']:checked").val();
	var iframe = getCurrentIframeContents();
	var d = $(iframe).find('#searchForm').serialize();
	var pt = $("#blt  option:selected").val();
	var type = $("#ftp").val();
	$form = $('#winForm',getCurrentIframeContents());
	
	$radioChk = $("input#tp");//获取单选按钮元素
	if($form.validate().element($radioChk)){
		if('1'==inOrOut){
			$selectPt = $("select#blt");// 获取平台下拉元素
			$exportType = $("select#ftp");// 获取导出文件类型
			
			if($form.validate().element($selectPt)){
				if($form.validate().element($exportType)){
					
					var len = $('[name="ids"]:checked').length;
					if(len==0){
						$newObj = $("#searchForm");
						$input1 = $("<input type='hidden' name='exportType' value='"+type+"'/>");
						$input2 = $("<input type='hidden' name='exportPlat' value='"+pt+"'/>");
						$newObj.append($input1);
						$newObj.append($input2);
						var s = $newObj.attr("action");
						$newObj.attr("action", ctx+"/excuteBackInterest/lineExport");
						$newObj.submit();
						$newObj.attr("action", s);
						$("input[name=exportType]").remove();
						$("input[name=exportPlat]").remove();
						
					}else{
						var inst = $('[name="ids"]:checked').map(function() {
							return this.value;
						}).get().join(',');
						var params = {"backiId":inst,"exportType":type,"exportPlat":pt};
						var action = ctx+"/excuteBackInterest/lineExport";
						postParamReplaceGet(params,action);
						
					}
				}
				return false;
			}
			return false;
		}else {
			
			var s = $("#line").val();// 上传的文件
			if(s != null && s != ""){
				var xls = "";// excel文件后缀名
				var xlsx = "";//excel文件后缀名
				var seri = $("#ftp").val();// 上传文件格式
				if(seri==0){
					xlsx = "xlsx";
					xls = "xls";
				}else if(seri ==1){
					xls = "txt";
				}else{
					BootstrapDialog.alert("请上传txt或者xlsx文件！！");
				}
				var xy = s.lastIndexOf(".");
				var tyt = s.substr((xy+1));
				if(xls==tyt || xlsx==tyt){
					form = $(".modal-body").find("[id=winForm]");
			    	var formData = new FormData(form[0]);
			    	loadingMarkShow();
			    	contents_getJsonForFileUpload(
			    			"", 
			    			ctx+"/excuteBackInterest/uploadFile", 
			    			formData,
			    			function(result){
			    				loadingMarkHide();
			    				if(result=='true'){
			    					BootstrapDialog.alert("操作成功",function(){loadingMarkShow();$('#search').click()});
				    	       	 }else{
				    	       		 BootstrapDialog.alert(result,function(){loadingMarkShow();$('#search').click()});
				    	       	 }
			    			},
				             function(){
			    				loadingMarkHide();
				            	BootstrapDialog.alert("服务器忙碌中······");
				             },
				             true
			        );
				}else{
					BootstrapDialog.alert("上传的文件类型和指定的上传文件类型不一致，请重新选择");
				}
				
			}else{
				BootstrapDialog.alert("你没有选择文件！请选择文件！");
			}
		}
	}
	return false;
}

//执行回息详情页控制审批不通过原因中其他选项的附加文本框
function showbk(){
	//通过不通过
	if($('[name="checkExaminetype"]:checked').val()=="1"){
		$('.backReason').hide();
		$("#bak").show();
	}else{
		$('.backReason').show();
		$("#bak").hide();
	}
	// 其他退回原因
	var value= $("#cemine").val();
	if(value=="6"){
		$("#tar").removeAttr("style");
	}else{
		$("#tar").attr("style","display:none;");
	}
}

// 执行回息详情页校验
function excuteComit(purl,surl){
		
	iframe = getCurrentIframeContents();
	$form = $('#applyForm', iframe);
	var forms = $(iframe).find('#applyForm').serialize();
	var data = null;
	$radioChk = $("input#rar");//获取单选按钮元素
	$form = $('#applyForm',iframe);
	
	// 获取页面上需要提交的数据
	var checkExamine = $('#cemine').val();// 退回原因
	var backiId = $('#backiId').val();// 批量审批数据的ID
	var item = $('[name="checkExaminetype"]:checked').val();// 结果
	var textAre = $("#tar").val();// 审批通过其他原因
	var platformId = $('[name="platformId"]:checked').val();// 执行回息审批通过选择平台信息
	var verTime = $("#verTime").val();// 更新时间
	var lendCode = $('[name="lendCode"]').val();
	if($form.validate().element($radioChk)){
			
		var item = $('[name="checkExaminetype"]:checked').val();
		$selectRs = $("select#cemine");// 获取退回原因下拉元素
		$textA = $("textarea#tar");// 获取退回原因其他原因下拉元素
		$rebak = $("input#bak");// 获取银行radio元素
		
		if(item=="1"){// 通过
			$("#cemine").removeAttr("select_required");
			$("#tar").removeAttr("textarea_required");
			$("#tar").removeAttr("required");
			if($form.validate().element($rebak)){
				loadingMarkShow();
				data = {"backiId":backiId, 
						"checkExaminetype":item, 
						"platformId":platformId,
						"verTime":verTime,
						"lendCode":lendCode
				};
				//验证成功
				limitCommit(data,purl,surl);
			}
			return false;
		}
		if(item=="2"){// 不通过
			$("#bak").removeAttr("required");
			if($form.validate().element($selectRs)){
				var s = $("#cemine").val();
				if(s=="6"){
					if($form.validate().element($textA)){
						loadingMarkShow();
						data = {
								"checkExaminetype":item,
								"backiId":backiId,
								"textAre":textAre,
								"verTime":verTime,
								"lendCode":lendCode
							};
						limitCommit(data,purl,surl);
					}
					return false;
				}else{
					loadingMarkShow();
					data = {
						"checkExaminetype":item,
						"backiId":backiId,
						"checkExamine":checkExamine,
						"verTime":verTime,
						"lendCode":lendCode
					};
					//验证成功
					limitCommit(data,purl,surl);
				}
			}
			return false;
		}
	}
	return false;
}

// 批量执行回息
function branchExcuteBackInterest(purl,surl){
	
	iframe = getCurrentIframeContents();
	var forms = $(iframe).find('#searchForm').serialize();
	var s = $("#hi").val();
	var data = null;

	if(s==1){
		$form = $('#winForm',getCurrentIframeContents());
		
		// 获取页面上需要提交的数据
		var checkExamine = $('#cemine').val();// 退回原因
		var backiId = $('[name="ids"]:checked').map(function() {
			return this.value;
		}).get().join(',');// 批量审批数据的ID
		var item = $('[name="checkExaminetype"]:checked').val();// 结果
		var textAre = $("#tar").val();// 审批通过其他原因
		var platformId = $('[name="platformId"]:checked').val();// 执行回息审批通过选择平台信息
		
		$radioChk = $("input#cet");//获取单选按钮元素
		$radioPlt = $("input#plat");//获取平台单选元素
		BootstrapDialog.confirm("确认提交吗?",function(result){
			if(result){
				if($form.validate().element($radioChk)){
			
					if(item=="1"){// 通过
						$("#cemine").removeAttr("select_required");
						$("#tar").removeAttr("textarea_required");
						$("#tar").removeAttr("required");
						$("#backMoneyActualDay").removeAttr("required");

						if($form.validate().element($radioPlt)){
							//异步提交批量回息审批
							data = {
									"checkExaminetype":item,
									"backiId":backiId,
									"platformId":platformId
							};
							forms = forms +"&checkExaminetype="+item+"&backiId="+backiId+"&platformId="+platformId;
							loadingMarkShow();
							submits(purl,surl,forms);
						}
						return false;
					}
					if(item=="2"){// 不通过
						$("#plat").removeAttr("required");
						$("#backMoneyActualDay").removeAttr("required");
						
						$selectRs = $("select#cemine");// 获取退回原因下拉元素
						$textA = $("textarea#tar");// 获取退回原因其他原因下拉元素
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
		
	}else if(s==2){// 执行回息导出和导入
		//comit();
		//调用已回款且提前赎回的操作的检验
		exportbutton();
	}else{// 线上回息提交
		onLineComit();
	}
}


fortune.showType = function(iframe,blt){
	$("[id="+blt+"]",iframe).change(function() {
		var s = $("#blt").find("option:selected").text();
		$bko = $("select#ftp");
		if(s=="中金平台"){
			$bko.empty();
			$bko.append("<option value=''>请选择</option>");
			$bko.append("<option value=1>txt</option>");
			$bko.append("<option value=0>Excel</option>");
			$bko.trigger("change");
			$bko.attr("disabled", false);
			$bko.selectpicker('refresh');
		}else{
			$bko.empty();
			$bko.append("<option value=''>请选择</option>");
			$bko.append("<option value=0>Excel</option>");
			$bko.trigger("change");
			$bko.attr("disabled", false);
			$bko.selectpicker('refresh');
		}
		
	});
	
};

//线下回息 对已回款且操作过提前赎回的数据进行校验
function  exportbutton(){
	var inOrOut = $("input[name='tp']:checked").val();
	var inOrOutblt = $("[name='platformId'] :selected").val();
	var inOrOutFile = $("[name='fileType'] :selected").val();
	if(inOrOut==1&&inOrOutblt!=''&&inOrOutFile!=''){
		if($('[name="ids"]:checked').length == 0){
			$.ajax({
				url:ctx+"/excuteBackInterest/searchAheadBackidsFrom/",
				type:"post",
				data:$("#searchForm").serialize(),
				dataType:"json",
				async:false,
				success:function(obj){
					var backid=obj.message;
					if(backid.length!=0){
						BootstrapDialog.confirm("确认线下回息吗?存在已操作过提前赎回的数据，出借编号为："+backid,function(result){
							if(result){
								comit();
							}else{
								$('#subClose').click();
							}
						});
					}else{
						comit();
					}
				},
				error:function(obj){
					BootstrapDialog.alert("服务器忙碌中······");
				}
			});
		}else{
			var	data = {"backiId":$("input[name='ids']:checked").map(function(){
			      return $(this).val();
		    }).get().join(",")};
			$.ajax({
				url:ctx+"/excuteBackInterest/searchAheadBackid/",
			    type:"post",
			    async:false,  
			    data:data,
			    dataType:"json",
			    success:function(obj){
			    	var lendCode=obj.message;
		    		if(lendCode!=""){
		    			BootstrapDialog.confirm("确认批量提交吗?存在已操作过提前赎回的数据，出借编号为："+lendCode,function(result){
								//自定义出发关闭回调函数的动作
		    				if(result){
		    					comit();
		    				}else{
		    					$('#subClose').click();
		    				}
						});	
					}else{
						comit();
					}
			    }
			});	
		}
	}
}


/*
 * 批量执行回息弹窗
 */
function batchExcuteWindow(){
	
	var s = $("#ids").length;
	if(s == 0){
		BootstrapDialog.alert("页面中没有数据可以操作！！");
	}else{
		iframe = getCurrentIframeContents();
		$(".modal-title",iframe).html("批量回息操作");
		$("#hi").val("1");
		//弹出框参数
		var url, argment, callback;
    	argment = null;
    	load_callback = function(arg){
    		
    	};
    	close_callback = function(arg){
    	};
        
		if($('[name="ids"]:checked').length == 0){
			BootstrapDialog.confirm("您没有选择任何一条数据，系统将默认选中全部数据并提交,是否确认提交全部？",function(result){
				if(result){
					$.ajax({
						url:ctx+"/excuteBackInterest/searchAheadBackidsFrom/",
						type:"post",
						data:$("#searchForm").serialize(),
						dataType:"json",
						async:false,
						success:function(obj){
							var backid=obj.message;
							if(backid.length==0){
								url = "/backInterestCommon/batchWindow";
								var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
								//自定义出发关闭回调函数的动作
					            $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
							}else{
								BootstrapDialog.confirm("确认批量提交吗?存在已操作过提前赎回的数据，出借编号为："+backid,function(result){
									if(result){
										url = "/backInterestCommon/batchWindow";
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
			    url:ctx+"/excuteBackInterest/searchAheadBackid/",
			    type:"post",
			    async:false,  
			    data:data,
			    success:function(obj){
			    	var objreu = JSON.parse(obj);
			    	lendCode=objreu.message;
			    	
			    }
			});
			if(lendCode==""){
				BootstrapDialog.confirm("确认批量提交吗?",function(result){
					if(result){
						url = "/backInterestCommon/batchWindow";
						var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
						//自定义出发关闭回调函数的动作
			            $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
					}
				});	
			}else{
				BootstrapDialog.confirm("确认批量提交吗?存在已操作过提前赎回的数据，出借编号为："+lendCode,function(result){
					if(result){
						url = "/backInterestCommon/batchWindow";
						var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
						//自定义出发关闭回调函数的动作
			            $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
					}
				});	
			}	
		}
	}
}

//执行回息单条进行处理时进行提示
function singleExcute(backid){
	$.ajax({
	    url:ctx+"/excuteBackInterest/searchAheadBackid/",
	    type:"post",
	    async:false,  
	    data:{"backiId":backid},
	    success:function(obj){
	    	var objreu = JSON.parse(obj);
	    	var lendCode=objreu.message;
	    	if(lendCode.length!=0){
				BootstrapDialog.confirm("此数据已操作过提前赎回",function(result){
					if(result){
						window.location.href=ctx+'/excuteBackInterest/goExecuteBackInterestPage?code='+backid;
					}
				});	
			}else{
				window.location.href=ctx+'/excuteBackInterest/goExecuteBackInterestPage?code='+backid;
			}
	    }
	});
}
