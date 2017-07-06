/**
 * 金账户开户页面
 */
$(function(){
	iframe = getCurrentIframeContents();
	
	//导出Excel
	$('.output').click(function(){
		customerCodes = getSelectCodes();
		formParam = $("#searchForm").serialize();
		window.location.href = ctx+"/trusteeship/account/exportExcel?"+formParam+"&"+customerCodes;
	});
	//导入Excel
	$('.input').click(function(){
		$(".modal-title").html("导入文件");
		$(".modal-footer").show();
    	// 调用子窗口的参数
    	var url, argment;
    	url = "/trusteeship/account/toImportPage";
    	argment = {};
    	load_callback = function(arg){
    	};
    	close_callback = function(arg){
    	};
        var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
        //关闭操作回调函数
        $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
        $('#subImport').off('click').on('click',function(){
        	flag = true;
        	$("input[type=file]").each(function(){
        		if(this.value==''){
        			BootstrapDialog.alert("请选择文件");
        			flag = false;
        			return false;
        		}else{
        			index = this.value.indexOf(".");
        			endStr = this.value.substring(index+1,this.value.length);
        			if(!(endStr.toLowerCase()=='xls'||endStr.toLowerCase()=='xlsx')){
        				BootstrapDialog.alert("请选择Excel文件");
        				flag = false;
        				return false;
        			}
        		}
        	});
        	if(!flag){
        		return;
        	}
        	
        	form = $(".modal-body").find("[id=inputForm]");
        	var formData = new FormData(form[0]);
        	contents_getJsonForFileUpload(
   				 this, 
   				 ctx+'/trusteeship/account/importExcel', 
   				 formData,
   				 function(res){
   					 BootstrapDialog.alert(res,function(){
   						//window.location.reload();
   						sub.closeSubWindow();
   						$(".search").click();
   					 });
                 },
                 function(){
                	 BootstrapDialog.alert("上传失败")
                 },
                 true
            );
        });
	});
	//导出Excel协议库
	$('.protocol').click(function(){
		
		customerCodes = getSelectCodes();
		formParam = $("#searchForm").serialize();
		window.location.href = ctx+"/trusteeship/account/exportProtocolExcel?"+formParam+ customerCodes;
		
	});
	
	function getSelectCodes(){
		 param = "";
		 $(".dataList").find("input[type=checkbox][name='select[]']:checked").each(function(){
			 val = $(this).val();
			 customerCode = val.split(";")[0];
			 if(customerCode && customerCode.length>0){
				 param = param + "&customerCodes=" + customerCode;
			 }
		 });
		 return param;
	 }
	
	//批量开户
	$('.bulk_account').click(function(){
		dataList = $(".dataList").find("input[type=checkbox][name='select[]']:checked");
		if(dataList.length==0){
			BootstrapDialog.confirm('所有检索条件数据开户？',function(result){
				if(result){
					bulk_account();
				}
			});
			return false;
		}else{
			bulk_account();
		}
	});
	/**
	 * 开户事件
	 */
	function bulk_account(){
		contents_getJsonForHtml(
				ctx + "/trusteeship/account/openAccount", 
				$('#searchForm').serialize() + '&'+getSelectCodes(),
				'post', 
				function(result){
					result = eval(result);
					if(result != null && result != ''){
						BootstrapDialog.alert(result,function(){$('.search').click()});
					}else{
						BootstrapDialog.alert('开户成功',function(){$('.search').click()});					
					}				
				},
				function(){
					BootstrapDialog.alert('开户失败',function(){$('.search').click()});	
				},
				null
			);
	}
	//协议库对接
	$('.protocol_docking').click(function(){
		dataList = $(".dataList").find("input[type=checkbox][name='select[]']:checked");
		if(dataList.length==0){
			BootstrapDialog.confirm('协议库对接所有检索条件数据？',function(result){
				if(result){
					protocol_docking();
				}
			});
		}else{
			protocol_docking();
		}
	});
	
	/**
	 * 协议库对接事件
	 */
	function protocol_docking(){
		contents_getJsonForHtml(
			ctx + "/trusteeship/account/excuteProtocol", 
			$(this).closest('form').serialize()+getSelectCodes(),
			'post', 
			function(result){
				result = eval(result);
				if(result != null && result != ''){
					//更新失败，提示后台的错误消息
					BootstrapDialog.alert(result);
				}else{
					BootstrapDialog.alert('协议库对接成功',function(){$('.search').click()});					
				}
				
			},
			function(){
				BootstrapDialog.alert('协议库对接失败',function(){$('.search').click()});
			},
			null
		 );
	}
	
	/**
	 * 自动开户/手动开户
	 */
	$(".auto_account").click(function(e){
		contents_getJsonForSync(ctx + "/trusteeship/account/setAutoOpenAccount", {'status':''}, 'post', function(res){
			if(res.autoFlag == 'true'){
				$("input.auto_account").val("自动开户");
				$("l.auto_account").text("自动开户");
			}else{
				$(".auto_account").val("手动开户");
				$("l.auto_account").text("手动开户");
			}
			BootstrapDialog.alert(res.message);
		});
	});
	/**
	 * 修改开户状态
	 */
	$('.btn_chgStatus').click(function(){
		$(".modal-title").html("修改状态");
		$(".modal-footer").hide();
    	// 调用子窗口的参数
    	var url, argment;
    	url = "/trusteeship/account/toChangeStatusPage";
    	var lendCustomerCode = $(this).closest('tr').find('[name="select[]"]').val();
    	argment = {'lendCustomerCode':lendCustomerCode};
    	load_callback = function(arg){
    		//提交
    		$('#subChgStatus').off('click').on('click',function(){
    			$form = $(this).closest('form');
    			if($form.validate().form()){
    				//验证成功
    				if($('[name="chgStatus"]:checked').val()=="2" && $('[name="trusteeshipRetMsg"]').val()==''){
        				BootstrapDialog.alert("请输入失败原因");
        				return false;
        			}
        			contents_getJsonForSync(ctx + "/trusteeship/account/changeStatus", $form.serialize(), 'post', 
    					function(result){
	        				if(result != null && result != ''){
		        				BootstrapDialog.alert(result,function(){$('.search').click()});
	        				}
        				}
        			);
    			}
    			return false;
            });
    	};
    	close_callback = function(arg){
    	};
        var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
        //关闭操作回调函数
        $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
		return false;
	});
	
});
/**
 * 全程留痕
 */
function fullMark(lendCode){
	$(".modal-title").html("全程留痕");
	$(".modal-footer").hide();
	var url="/trusteeship/account/fullMark?applyCode=" + lendCode;
	var sub = SubDialogHandle.create(url,null,null,null).loadSubWindow();
	$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
}