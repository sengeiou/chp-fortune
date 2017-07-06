// 提交回息审批验证
function approval(purl,surl){
	
	iframe = getCurrentIframeContents();
	var item = $('[name="checkExaminetype"]:checked').val();
	var forms = $(iframe).find('#applyForm').serialize();
	
	$form = $('#applyForm', iframe);
	$selectRs = $("select#cemine");// 获取退回原因下拉元素
	$textA = $("textarea#tar");// 获取退回原因其他原因下拉元素
	$radioChk = $("input#rar");//获取单选按钮元素
	
	if($form.validate().element($radioChk)){
		if(item=="1"){// 通过
			$("#cemine").removeAttr("select_required");
			$("#tar").removeAttr("textarea_required");
			$("#tar").removeAttr("required");
			loadingMarkShow();
			//验证成功
			limitCommit(forms,purl,surl);
		}
		if(item=="2"){// 不通过
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
			return false
		}
	}
	return false;
}

// 批量回息审批方法
function batchApproval(purl,surl){
	
	iframe = getCurrentIframeContents();
	var forms = $(iframe).find('#searchForm').serialize();
	var s = $("#hi").val();
	var data = null;
	$form = $('#winForm',getCurrentIframeContents());
	
	if(s=="1"){
		// 获取页面上需要提交的数据
		var checkExamine = $('#cemine').val();
		var backiId = $('[name="ids"]:checked').map(function() {
			return this.value;
		}).get().join(',');
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
						$("#backMoneyActualDay").removeAttr("required");
							//异步提交批量回息审批
							forms = forms +"&checkExaminetype="+item+"&backiId="+backiId;
							loadingMarkShow();
							submits(purl,surl,forms);
						
					}
					if(item=="2"){// 不通过
						$("#plt").removeAttr("required");
						$("#backMoneyActualDay").removeAttr("required");
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
	}
}

/*
 * 批量待回息审批弹窗
 */
function stayBatchBreathlWindow(){
	
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
						url:ctx+"/backInterestApproval/searchAheadBackidsFrom/",
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
			    url:ctx+"/backInterestApproval/searchAheadBackid/",
			    type:"post",
			    async:false,  
			    data:data,
			    success:function(obj){
			    	var objreu = JSON.parse(obj);
			    	lendCode=objreu.message;
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
			});
		}
	}
}

//待回息审批单条进行处理时进行提示
function singlecommit(backid){
	$.ajax({
	    url:ctx+"/backInterestApproval/searchAheadBackid/",
	    type:"post",
	    async:false,  
	    data:{"backiId":backid},
	    success:function(obj){
	    	var objreu = JSON.parse(obj);
	    	var lendCode=objreu.message;
	    	if(lendCode.length!=0){
				BootstrapDialog.confirm("此数据已操作过提前赎回",function(result){
					if(result){
						window.location.href=ctx+'/backInterestApproval/goConfirm?code='+backid;
					}
				});	
			}else{
				window.location.href=ctx+'/backInterestApproval/goConfirm?code='+backid;
			}
	    }
	});
}
