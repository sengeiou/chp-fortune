var visitConfig = {};

Optimized_Mode = false;

visitConfig.information = 1;// 信息页面默认访问一次
visitConfig.investment = 0;// 投资信息页面访问0次
visitConfig.advisory = 0;// 咨询页面访问0次
visitConfig.modify = 0;// 修改历史页面访问0次

$(document).ready(function() {
	$('a[data-toggle="tab"]').on('show.bs.tab', function(e) {
		tabName = e.target;
		pageName = tabName.toString().split('#')[1];
		custCode = $("#custCode").val();

		switch (pageName) {
		case "information": {
			if (visitConfig.information == 0) {
				visitConfig.information = 1;
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
		case "investment": {
			if (visitConfig.investment == 0) {
				visitConfig.investment = 1;
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
		case "modify": {
			if (visitConfig.modify == 0) {
				visitConfig.modify = 1;
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

function showTab(custCode, pageName) {
	contents_getJsonForHtml(
		ctx + "/customer/investment/handle", 
		{
			custCode : custCode,
			pageName : pageName
		}, 
		"post", 
		 function(result) {
			$("#" + pageName).html(result);
		},null,null
	);
}

//咨询列表Tab翻页
function ajaxAdvisoryPage(n, s) {
	if (n)
		$("#pageNo",$("#advisoryForm")).val(n);
	if (s)
		$("#pageSize",$("#advisoryForm")).val(s);
	data = $("#advisoryForm").serialize();
	contents_getJsonForHtml(ctx+"/customer/investment/handle", data, "post", function(html){
		$(".advisoryTabDiv").html(html);
	},null,null);
	return false;
}

//投资列表Tab翻页
function ajaxInvestmentPage(n, s) {
	if (n)
		$("#pageNo",$("#investmentForm")).val(n);
	if (s)
		$("#pageSize",$("#investmentForm")).val(s);
	data = $("#investmentForm").serialize();
	contents_getJsonForHtml(ctx+"/customer/investment/handle", data, "post", function(html){
		$(".investmentTabDiv").html(html);
	},null,null);
	return false;
}

//客户修改列表Tab翻页
function ajaxChangeTracesPage(n, s) {
	if (n)
		$("#pageNo",$("#changeTracesForm")).val(n);
	if (s)
		$("#pageSize",$("#changeTracesForm")).val(s);
	data = $("#changeTracesForm").serialize();
	contents_getJsonForHtml(ctx+"/customer/investment/handle", data, "post", function(html){
		$(".modifyTabDiv").html(html);
	},null,null);
	return false;
}


function goBackWithParams() {
	var searchParam = $('#searchFormParams').serialize();
	//var tt = document.referrer + (document.referrer.indexOf('?') > 0 ? '' : '?');
	
	window.location.href= ctx+"/customer/investment/list?" + searchParam;
}


