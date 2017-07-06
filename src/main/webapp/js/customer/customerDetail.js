$(function(){
	var iframe = getCurrentIframeContents();
	var $form = $("#customerForm",iframe);
	
	/**
	 * 修改按钮绑定事件
	 */
	$("#updateButton",iframe).on('click',function(){
		$(this).hide();
		$("#saveButton",iframe).show();
		
		$("select[updateable]",$form).each(function(){
			$(this).prop("disabled",false);
			$(this).selectpicker('refresh');
		});
		$("radio[updateable]",$form).attr('disabled', false);
		$("input[updateable]",$form).attr('disabled', false);
	});
	
	/**
	 * 退回电销
	 */
	$("#sendBack2TeleSale",iframe).on('click',function(){
		BootstrapDialog.confirm("您确定要退回电销吗？", function(result) {
            if (result) {
            	loadingMarkShow();
            	var custCode = $('#custCode').val();
        		var url = ctx + '/customerPoolController/sendBack2CustomerPool';
        		contents_getJsonForSync(
        				url,
        				{'custCode' : custCode},
        				'post',
        				function(rs) {
        					if(rs.result == 'true') {
        						BootstrapDialog.alert('成功退回电销');
//        						$("#sendBack2TeleSale",iframe).hide();
        						window.location.href= ctx+"/customer/investment/list";
        					} else {
        						BootstrapDialog.alert('退回电销时出现异常');
        					}
        				}
        		);
            }
        });
	});
	
	/**
	 * 保存按钮绑定事件
	 */
	$("#saveButton",iframe).on('click',function(){
		$("#saveButton",iframe).attr('disabled','disabled');
		//$("#updateButton",iframe).show();
		
		phone = $("#custMobilephone",$form).val().replace(/\s/g,'');
		if(phone != $("#currentCustomerPhone",$form).val()){
			// 手机号码有改动
			contents_getJsonForSync(ctx+"/customer/customerAidController/ajaxPhoneExsit", {'phone':phone}, 'post', function(res){
				if(res.result == 'false'){
					BootstrapDialog.alert(res.message);
					return;
				}else{
					updateForm();
				}
			});
		}else{
			updateForm();
		}
		
	});
	
	/**
	 * 修改客户信息
	 */
	function updateForm(){
		if(!$form.validate().form()){
			$("#saveButton",iframe).removeAttr("disabled");
			return;
		}
		
		contents_getJsonForSync(ctx + "/customer/investment/update", $form.serialize(), 'post', 
				function(res){
					BootstrapDialog.alert(res.msg);
					//点击保存按钮
					$("#updateButton",iframe).show();
					$("#saveButton",iframe).hide();
					
					$("select",$form).each(function(){
						$(this).prop("disabled",true);
						$(this).selectpicker('refresh');
					});
					$("radio",$form).attr('disabled', true);
					$("input:visible",$form).attr('disabled', true);
					$("#currentCustomerPhone",iframe).val($("#custMobilephone",iframe).val());
				},null,null);
		
		/*$.ajax({
			url:ctx + "/customer/investment/update",
			type:"post",
			dataType:"text",
			data:$form.serialize(),
			success:function(res){
				BootstrapDialog.alert("操作成功！");
				$("#updateButton",iframe).show();
				$("#saveButton",iframe).hide();
				
				$("select",$form).each(function(){
					$(this).prop("disabled",true);
					$(this).selectpicker('refresh');
				});
				$("radio",$form).attr('disabled', true);
				$("input:visible",$form).attr('disabled', true);
				$("#currentCustomerPhone",iframe).val($("#custMobilephone",iframe).val());
			}
		});*/
	}
	
});