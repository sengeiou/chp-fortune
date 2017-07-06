$(document).ready(function(){
	var iframe = getCurrentIframeContents();
	
	//省市区联动绑定
	fortune.initCity(iframe,'addrProvince','addrCity','addrDistrict');
	
	$("input[type=button]",iframe).on("click", function(){
		id = this.id;
		switch(id){
		case "customerModify" : {
			//点击修改按钮
			$("#customerSave").show();
			$("#customerModify").hide();
			
			$("input[type!=button]").attr('disabled', false);
			$("#custMobilephone").attr('disabled', 'disabled');
			$("select").each(function(){
				$(this).prop("disabled",false);
				$(this).selectpicker('refresh');
			});
			$("radio").attr('disabled', false);
			
			break;
		};
		case "customerSave" : {
			phone = $("#custMobilephone").val().replace(/\s/g,'');
			if(phone != $("#currentCustomerPhone").val()){
				// 手机号码有改动
				contents_getJsonForSync(ctx+"/customer/customerAidController/ajaxPhoneExsit", {'phone':phone}, 'post', function(res){
					if(res.result == 'false'){
						BootstrapDialog.alert(res.message);
						return;
					}else{
						updateCustomer();
					}
				});
			}else{
				updateCustomer();
			}
			break;
			
		};
		case "customerTransferLend" : {
			//开户
			custCode = $("#custCode").val();
			//window.location.href = ctx + "/customer/transfer/goTransferLend?customerCode=" + custCode;
			window.location.href = ctx + "/customer/investment/openTransferLend?custCode=" + custCode;
			break;
		};
		case "eliminateGoldAccount" : {
			// 消除金账户
			custCode = $("#custCode").val();
			contents_getJsonForSync(ctx+"/customer/investment/eliminateGoldAccount", {'customerCode':custCode}, 'post', function(res){
				if(res.result == 'true'){
					BootstrapDialog.alert(res.message);
					return;
				}else{
					BootstrapDialog.alert("销户成功");
				}
			});
			break;
		};
		}//switch
	});
	
	// 输入移动手机号码后检验手机号是否重复
	$("#custMobilephone",iframe).blur(function(e){
		phone = $(this).val().replace(/\s/g,'');
		if(phone == ''){
			return;
		}
		if(phone == $("#currentCustomerPhone").val()){
			return;
		}
		contents_getJsonForSync(ctx+"/customer/customerAidController/ajaxPhoneExsit", {'phone':phone}, 'post', function(res){
			if(res.result == 'false'){
				BootstrapDialog.alert(res.message);
				return;
			}
		});
	});
	
	// 修改客户信息
	function updateCustomer(){
		if(!$("#inputForm").validate().form()){
			return;
		}
		
		contents_getJsonForSync(ctx + "/customer/investment/update", $("#inputForm").serialize(), 'post', 
			function(res){
				BootstrapDialog.alert(res.msg);
				//点击保存按钮
				$("#customerSave").hide();
				$("#customerModify").show();
				$("input[type!=button]").attr('disabled', true);
				$("select").each(function(){
					$(this).prop("disabled",true);
					$(this).selectpicker('refresh');
				});
				$("radio").attr('disabled', true);
				$("#currentCustomerPhone",iframe).val($("#custMobilephone",iframe).val());
			},null,null);
		
		/*$.ajax({
			url:ctx + "/customer/investment/update",
			type:"post",
			dataType:"text",
			data:$("#inputForm").serialize(),
			success:function(res){
				BootstrapDialog.alert("操作成功！");
				//点击保存按钮
				$("#customerSave").hide();
				$("#customerModify").show();
				$("input[type!=button]").attr('disabled', true);
				$("select").each(function(){
					$(this).prop("disabled",true);
					$(this).selectpicker('refresh');
				});
				$("radio").attr('disabled', true);
				$("#currentCustomerPhone",iframe).val($("#custMobilephone",iframe).val());
			}
		});*/
	}
	
});


