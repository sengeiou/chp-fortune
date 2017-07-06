// 已回息退回
$(document).ready(function() {
	// 已回息退回
	$("#ftn").click(function(){
	
		if($("#disp").is(":hidden")){ 
			$("#disp").show();
			$("#sno").removeAttr("style");
		}else{
			$("#disp").hide();
			$("#sno").hide();
		}
	})
	
	
	// 已回息退回验证
	var iframe = getCurrentIframeContents();
	$('#sno',iframe).click(function(){
		$form = $(iframe).find('#return',getCurrentIframeContents());
		var forms = $(iframe).find('#return').serialize();
		$selectRs = $("select#cemine");// 获取退回原因下拉元素
		$textA = $("textarea#tar");// 获取退回原因其他原因下拉元素
		
		if($form.validate().element($selectRs)){
			var s = $("#cemine").val();
			if(s=="6"){
				if($form.validate().element($textA)){
					loadingMarkShow();
					limitCommit(forms,'finishBackInterest/return', 'finishBackInterest/loadFinishBackInterestList');
				}
				return false;
			}else{
				loadingMarkShow();
				//验证成功
				limitCommit(forms,'finishBackInterest/return', 'finishBackInterest/loadFinishBackInterestList');
			}
		}
		return false
	})
	
	
	// 批量退回
	$('#brh',iframe).click(function(){
		var s = $("#ids").length;
		var data = "";
		if(s == 0){
			BootstrapDialog.alert("页面中没有数据可以提交！！");
		}else{
			$form = $(iframe).find('#brch');
			var forms = $(iframe).find('#searchForm').serialize();
			var backiId = $('[name="ids"]:checked').map(function() {
				return this.value;
			}).get().join(',');
			var s = $("#cemine").val();
			var tass = $("#tar").val();
			
			$selectRs = $("select#cemine");// 获取退回原因下拉元素
			$textA = $("textarea#tar");// 获取退回原因其他原因下拉元素
			if($form.validate().element($selectRs)){
				//验证成功
				if(s=="6"){
					if($form.validate().element($textA)){
						forms = forms +"&checkExamine="+s+"&backiId="+backiId+"&textAre="+tass;
						loadingMarkShow();
						summer(forms,data);
					}
					return false;
				}else{
					//验证成功
					forms = forms +"&checkExamine="+s+"&backiId="+backiId;
					loadingMarkShow();
					summer(forms);
				}
			}
			return false;
		}
	})
});

// 已回息退回
function summer(forms){
	url = ctx+"/finishBackInterest/branchReturn";
	successCal = function(result){
		loadingMarkHide();
       	 if(result=='true'){
       		 BootstrapDialog.alert("操作成功",function(){loadingMarkShow();$('#search').click()});
       	 }else{
       		 BootstrapDialog.alert(result,function(){loadingMarkShow();$('#search').click()});
       	 }
	};
	errorCal = function (error){
		loadingMarkHide();
		BootstrapDialog.alert("服务器忙碌中······");
	};
	contents_getJsonForSync(url, forms, 'post', successCal,errorCal,null);
}

/*
 * 批量退回弹窗(已回息退回)
 */
function returnWindow(){
		
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
						url:ctx+"/finishBackInterest/searchAheadBackidsFrom/",
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
			    url:ctx+"/finishBackInterest/searchAheadBackid/",
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
				BootstrapDialog.confirm("确认批量退回吗?存在已操作过提前赎回的数据，出借编号为："+lendCode,function(result){
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

//单条进行处理时进行提示
function singleFinish(backid){
	$.ajax({
	    url:ctx+"/finishBackInterest/searchAheadBackid/",
	    type:"post",
	    async:false,  
	    data:{"backiId":backid},
	    success:function(obj){
	    	var objreu = JSON.parse(obj);
	    	var lendCode=objreu.message;
	    	if(lendCode.length!=0){
				BootstrapDialog.confirm("此数据已操作过提前赎回",function(result){
					if(result){
						window.location.href=ctx+'/finishBackInterest/goFinishBackInterestPage?backiId='+backid;
					}
				});	
			}else{
				window.location.href=ctx+'/finishBackInterest/goFinishBackInterestPage?backiId='+backid;
			}
	    }
	});
}
