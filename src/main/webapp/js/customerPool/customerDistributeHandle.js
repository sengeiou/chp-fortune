var visitConfig = {};

Optimized_Mode = false;

visitConfig.information = 0;// 信息页面默认访问一次
visitConfig.distribute = 0;// 信息页面默认访问一次
visitConfig.advisory = 0;// 投资信息页面访问0次

$(document).ready(function() {
	$('a[data-toggle="tab"]').on('show.bs.tab', function(e) {
		tabName = e.target;
		pageName = tabName.toString().split('#')[1];
		custCode = $("#custCode").val();
		id = $("#id").val();

		switch (pageName) {
		case "distribute": {
			if (visitConfig.distribute == 0) {
				visitConfig.distribute = 1;
				showTab(custCode, pageName,id);
				break;
			} else {
				break;
			}
		}
			;
		case "advisory": {
			if (visitConfig.advisory == 0) {
				visitConfig.advisory = 1;
				showTab(custCode, pageName);
				break;
			} else {
				if(!Optimized_Mode){
					showTab(custCode, pageName);
				}
				break;
			}
		}
			;
		case "information": {
			if (visitConfig.information == 0) {
				flag = '1';
				visitConfig.information = 1;
				showTab(custCode, pageName,flag);
				break;
			} else {
				if(!Optimized_Mode){
					showTab(custCode, pageName);
				}
				break;
			}
		}
			;
		}//switch
	});
	
});

function showTab(custCode, pageName, id, flag) {
	$.ajax({
		url : ctx + "/customerPoolController/handleCustomerDistibute",
		type : "post",
		dataType : "html",
		data : {
			id : id,
			custCode : custCode,
			pageName : pageName,
			flag : flag
		},
		success : function(result) {
			$("#" + pageName).html(result);
//			if(pageName == 'information') {
//				$("#containerDiv").html(result);
//			} else {
//				$("#" + pageName).html(result);
//			}
		}
	});
}


