var phoneInterValObj; // timer变量，控制时间
var emailInterValObj; // timer变量，控制时间
var count = 60; // 间隔函数，1秒执行
var phoneCurCount;// 当前剩余秒数
var emailCurCount;// 当前剩余秒数
function sendMessage(verifyPinType) {

	var customerId = $("input[name='customer.custCode']").val();
	var value = "";
	if (verifyPinType == 1) {
		value = $("input[name='customer.custMobilephone']").val()
		phoneCurCount = count;
	} else if (verifyPinType == 2) {
		value = $("input[name='customer.custEmail']").val()
		emailCurCount = count;
	}

	var id = "";
	if (verifyPinType == '1') {
		id = "btnSendCodePhone";
		// 设置button效果，开始计时
		$("#" + id).attr("disabled", "true");
		$("#" + id).val(phoneCurCount + "秒");
		phoneInterValObj = window.setInterval(SetRemainTimePhone, 1000); // 启动计时器，1秒执行一次
	} else if (verifyPinType == 2) {
		id = "btnSendCodeEmail";
		// 设置button效果，开始计时
		$("#" + id).attr("disabled", "true");
		$("#" + id).val(emailCurCount + "秒");
		emailInterValObj = window.setInterval(SetRemainTimeEmail, 1000); // 启动计时器，1秒执行一次
	}

	// 向后台发送处理数据
	$.ajax({
		type : "POST", // 用POST方式传输
		dataType : "json",
		data : {
			"verifyPinType" : verifyPinType,
			"customerId" : customerId,
			"value" : value
		},
		url : ctx + '/verify/sendVerify', // 目标地址
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		},
		success : function(msg) {
			if (msg != "") {
				$("#" + id).val("发送异常！重新发送");
				if (verifyPinType == 1) {
					phoneCurCount = 0;
				} else {
					emailCurCount = 0;
				}
			}
		}
	});
}

// timer处理函数
function SetRemainTimePhone() {
	if (phoneCurCount == 0) {
		window.clearInterval(phoneInterValObj);// 停止计时器
		$("#btnSendCodePhone").removeAttr("disabled");// 启用按钮
		$("#btnSendCodePhone").val("重新发送验证码");
	} else {
		phoneCurCount--;
		$("#btnSendCodePhone").val(phoneCurCount + "秒");
	}
}

// timer处理函数
function SetRemainTimeEmail() {
	if (emailCurCount == 0) {
		window.clearInterval(emailInterValObj);// 停止计时器
		$("#btnSendCodeEmail").removeAttr("disabled");// 启用按钮
		$("#btnSendCodeEmail").val("重新发送验证码");
	} else {
		emailCurCount--;
		$("#btnSendCodeEmail").val(emailCurCount + "秒");
	}
}

function checkPin(verifyPinType,successFunc,alertFunc) {

	var customerId = $("input[name='customer.custCode']").val();
	var pin = "";
	var f=1;
	if (verifyPinType == 1) {
		pin = $("#phonePin").val();
	} else if (verifyPinType == 2) {
		pin = $("#emailPin").val();
	}
	contents_getJsonForSync(
			ctx + '/verify/checkPin',
			{
				"verifyPinType" : verifyPinType,
				"customerId" : customerId,
				"pin" : pin
			},
			"POST", 
			function(msg) {
				if(msg=='2'){
					alertFunc();
				}else{
					successFunc(msg);
				}
			},null,null
	);
}

function isChanger(verifyPinType) {
	if (verifyPinType == 2) {
		if ($("#emailInit").val() != $("input[name='customer.custEmail']")
				.val()) {
			$("#emailVerify").show();
		} else {
			$("#emailVerify").hide();
		}

	} else {
		if ($("input[name='customer.custMobilephoneChanger']").val() != $(
				"input[name='customer.custMobilephone']").val()) {
			$("#phoneVerify").show();
			$("#phoneCheck").show();
		} else {
			$("#phoneVerify").hide();
			$("#phoneCheck").hide();
		}
	}

}

// 验证验证码是否为空
function isNullverifyCode() {

	var f = 0;
	if ($("input[name='customer.custMobilephone']").val() != '***' && $("input[name='customer.custMobilephoneChanger']").val() != $(
			"input[name='customer.custMobilephone']").val()) {

		if ($("#phonePin").val() == '') {
			BootstrapDialog.alert('手机验证码不能为空');
			return -1;
		} else {
			f = 1;
		}
	}

	if ($("#emailInit").val() != $("input[name='customer.custEmail']").val()) {
		if ($("#emailPin").val() == '') {
			BootstrapDialog.alert('邮箱验证码不能为空');

			return -1;
		} else {

			if (f == 1) {
				f = 3
			} else {
				f = 2;
			}

		}
	}
	return f;
}
