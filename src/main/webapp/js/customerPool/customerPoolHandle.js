var visitConfig = {};

Optimized_Mode = false;

visitConfig.distribute = 1;// 信息页面默认访问一次
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
				showTab(custCode, pageName);
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
		}//switch
	});
	
});

function showTab(custCode, pageName, id) {
	$.ajax({
		url : ctx + "/customerPoolController/handle",
		type : "post",
		dataType : "html",
		data : {
			id : id,
			custCode : custCode,
			pageName : pageName
		},
		success : function(result) {
			$("#" + pageName).html(result);
		}
	});
}

//咨询列表Tab翻页
function ajaxAdvisoryPage(n, s) {
	if (n)
		$("#pageNo",$("#advisoryForm")).val(n);
	if (s)
		$("#pageSize",$("#advisoryForm")).val(s);
	data = $("#advisoryForm").serialize();
	contents_getJsonForHtml(ctx+"/customerPoolController/handle", data, "post", function(html){
		$(".advisoryTabDiv").html(html);
	},null,null);
	return false;
}



