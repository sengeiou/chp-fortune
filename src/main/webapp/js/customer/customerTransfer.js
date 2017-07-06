
$(function(){
});

function goPageWithParams(pageURL) {
	var searchParam = $('#searchForm').serialize();
	window.location.href = pageURL + "&" + searchParam;
}
	
function Totranser(custCode){
	$.ajax({
		type : "post",
		url : ctx + "/customerTransfer/checkCustomerTransfer?custCode=" + custCode,
		dataType : "text",
		data : $('#inputForm').serialize(),
		success : function(data) {
			if (data == 'NOT_SATISFIED') {
				BootstrapDialog.alert("此客户存在金信或者大金融,不能进行转赠");
			}else if(data == 'IS_SINGLE'){
				BootstrapDialog.alert("此客户为已成单客户,不能进行转赠");
			}else if(data == 'NOT_FIRST_INSTANCE'){
				BootstrapDialog.alert("无初审人员");
			}else if(data == 'NOT_REVIEW'){
				BootstrapDialog.alert("无复审人员");
			}else{
				backToolLaunch(data);
			}
		},
		error : function(data) {
			BootstrapDialog.alert("系统错误，请联系管理员！");
		}
	});
}


	function backToolLaunch(custCode){
		var url="/bpm/flowController/openLaunchForm";
		var arg={'flowCode':'grab123','custCode':custCode};
		load_function=function(arg){
			$('.subSearch').click(function(){
				managerCode = $("#managerCode").val().replace(/\s/g,'');
				if(managerCode == ""){
					BootstrapDialog.alert("请输入理财经理工号！");
					return;
				}
				managerName = $("#managerName").val().replace(/\s/g,'');
				if(managerName == ""){
					BootstrapDialog.alert("请输入理财经理姓名！");
					return;
				}
				var url,data,type;
				url = ctx+'/customerTransfer/searchCustomerManager';
				data = $("#searchForm1").serialize();
				type = 'post'
				contents_getJsonForHtml(url, data, type, 
						function(result){$('.listDiv').html(result);
					},function(){},null);
			});
		};
		var sub = SubDialogHandle.create(url,arg,load_function,null).loadSubWindow();
		$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
	}
	
	function Verification(){
		if($("#managerCodebf").val()==$("#managerCodeaf").val()){
			BootstrapDialog.alert("该客户的理财经理已是您，不能发起抢单！");
		}else{
			$("#GrabForm").submit();
		}
		
	}
	
	