
$(function(){
});

/**
 * 营业部经理初审弹出
 * @param applyId
 * @param wobNum
 * @param token
 */
function launchTool(applyId,wobNum,token,custCode,n){
	$.ajax({
		type : "post",
		url : ctx + "/donationApprove/checkHaveReview",
		dataType : "text",
		data : {"applyId":applyId,"wobNum":wobNum,"token":token,"custCode":custCode},
		success : function(data) {
			if (data == 'IS_SINGLE') {
				BootstrapDialog.alert("此客户为已成单客户，不能进行转赠");
				$(n).parent().parent().remove();
			}else if(data == 'NOT_REVIEW'){
				BootstrapDialog.alert("无复审人员，该笔转赠已退回理财经理");
				$(n).parent().parent().remove();
			}else{
				var url="/bpm/flowController/openForm";
				var arg={'applyId':applyId,'wobNum':wobNum,'dealType':'0','token':token};
				var sub = SubDialogHandle.create(url,arg,null,null).loadSubWindow();
				$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
			}
		},
		error : function(data) {
			BootstrapDialog.alert("系统错误，请联系管理员！");
		}
	});
}
	
function Verification(){
	if($("#managerCodebf").val()==$("#managerCodeaf").val()){
		BootstrapDialog.alert("该客户的理财经理已是您，不能发起抢单！");
	}else{
		$("#GrabForm").submit();
	}
}