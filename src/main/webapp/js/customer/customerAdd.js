
$(function(){
	
	/**
	 * 获得页面的iframe
	 */
	var iframe = getCurrentIframeContents();
	
	//省市区联动绑定
	fortune.initCity(iframe,'addrProvince','addrCity','addrDistrict');
	//设置沟通日期为当前时间
	curDate = new Date();
	year = curDate.getFullYear();
	month = curDate.getMonth() + 1;
	day = curDate.getDate();
	if(month < 10){
		month = "0"+month;
	}
	if(day<10){
		day = "0"+day;
	}
	$( "#askDate" ).val(year+'-'+month+'-'+day);
	
	/**
	 * 绑定事件
	 */
	$(":input[type=text],textarea").not(":disabled").not("[readonly]").each(function(){
		evts = $._data($(this)[0], "events");
		if(evts == undefined){
			$(this).keyup(function(){
				val = $(this).val();
				val = val.replace(/\s/g,'');
				$(this).val(val);
			});
		}
	});
	
	$('#btnSave',iframe).click(function(){
		
		$form = $(this).closest('form');
		if(!$form.validate().form()){
			//验证不成功
			return false;
		}
		
		if( ! ($('#askBeginDate').val() ? true : false ) == ( $('#askEndDate').val() ? true : false )) { 
			BootstrapDialog.alert("请选择完整的沟通时间");
			return ;
		}
		
		if($('#askBeginDate').val() != '' && $('#askEndDate').val() != ''){
			beginDate = $('#askBeginDate').val().substring(0,10);
			endDate = $('#askEndDate').val().substring(0,10);
			beginDate = new Date(beginDate);
			endDate = new Date(endDate);
			askDate = new Date($('#askDate').val());
			
			if(askDate.getFullYear() == beginDate.getFullYear() && askDate.getFullYear() == endDate.getFullYear()
					&& askDate.getMonth() == beginDate.getMonth() && askDate.getMonth() == endDate.getMonth()
					&& askDate.getDate() == beginDate.getDate() && askDate.getDate() == endDate.getDate()){
			}else{
				BootstrapDialog.alert("沟通时间必须和沟通日期保持在同一天");
				return;
			}
		}
		
		inputMoney = $("#askInputMoney",iframe).val();
		if((inputMoney != '') && (!(/^\d+$/.test(inputMoney)))){
			BootstrapDialog.alert("咨询意向金额只能为数字");
			return false;
		}
		
		//验证姓名，只能为中文
//		var cusName = $("#askInputMoney",iframe).val();
//		if((cusName ) && (!(/^\d+$/.test(inputMoney)))){
//			BootstrapDialog.alert("客户姓名只能是中文");
//			return false;
//		}
		
		contents_getJsonForSync(ctx+"/customer/investment/add", $('#inputForm').serialize(), 'post', 
			function (data) {
				if(data==null||data.message==null||data.message==""){
	        		BootstrapDialog.alert("添加客户成功");
	        		goPage(ctx + "/customer/investment");
	        	}else if(data.message=="phone_exsit"){
					BootstrapDialog.alert("手机号码已经存在！");
				}else if(data.message=="1"){
	        		BootstrapDialog.alert("该手机号有多个理财经理，请先通知业务经行交割!");
	        	}else{
	        		backTool(data.message);
	        	}
        	},
        	function (data) {
	        	BootstrapDialog.alert("系统错误，请联系管理员！");
	        },null);
		
	    /*$.ajax({
	        type: "post",
	        url: ctx+"/customer/investment/add",
	        dataType: "text",
	        data: $('#inputForm').serialize(),
	        success: function (data) {
	        	if(data==null||data==""){
	        		BootstrapDialog.alert("添加客户成功");
	        		goPage(ctx + "/customer/investment");
	        	}else if(data=="1"){
	        		BootstrapDialog.alert("该手机号有多个理财经理，请先通知业务经行交割!");
	        	}else{
	        		backTool(data);
	        	}
	        },
	        error: function (data) {
	        	BootstrapDialog.alert("系统错误，请联系管理员！");
	        }
	    });*/
	});
});

	
	function backTool(empCode){
		var url="/customerTransfer/goCustomerTransfer";
		var arg={'empCode':empCode};
		var sub = SubDialogHandle.create(url,arg,null,null).loadSubWindow();
		$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
	}
	
	function Verification(){
		inputMobilephone = $("#custMobilephone").val();
		if((inputMobilephone != '') && (!(/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/.test(inputMobilephone)))){
			BootstrapDialog.alert("请输入正确的手机号码！");
			return false;
		}
		$.ajax({
	        type: "post",
	        url: ctx+"/customer/customerAidController/check",
	        dataType: "text",
	        data: $('#inputForm').serialize(),
	        success: function (data) {
	        	if(data==null||data==""){
	        		BootstrapDialog.alert("校验成功，该客户可添加！");
	        	}else if(data=="1"){
	        		BootstrapDialog.alert("该手机号归属多个客户经理，请联系财富数据部!");
	        	}else{
	        		backTool(data);
	        	}
	        },
	        error: function (data) {
	        	BootstrapDialog.alert("系统错误，请联系管理员！");
	        }
	    });		
	}
	
	function isChanger(verifyPinType) {
		if (verifyPinType == 1) {
			if ("" != $("input[name='customer.custMobilephone']")
					.val()) {
				$("#phoneVerify").show();
			} else {
				$("#phoneVerify").hide();
			}

		} 
	}