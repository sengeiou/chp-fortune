
/**
 *  出借审批查询列表 js
 */
	
$(function() {
	var iframe = getCurrentIframeContents();
	//绑定统计按钮事件
	$('#statistics', iframe).click(function(){
				var url =  '/lendApplyApprovalLook/getApplyStatistics';
				var argment = null;
				$(".modal-title",iframe).html("统计");
				var load_callback = function() {
					$('#subSubmit', iframe).hide();  //隐藏弹出框的提交按钮
				};
				var close_callback = function(){};
				
				var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
				$('#subClose').off('click').on('click',function(){  sub.closeSubWindow();});
	 });
	//导出Excel按钮事件
	$('#exportExcel').click(function(){
        BootstrapDialog.confirm("您确定要导出excel吗？", function(result) {
            if (result) {
                var url = ctx + "/lendApplyApprovalLook/exportExcel";
                postFormReplaceGet($("#searchForm"),url);
            }
        });
	 });
	
	 function trimStr(str) {
		 if(str) {
			 return str.replace(/(^\s*)|(\s*$)/g, '');
		 } else {
			 return '';
		 }
	 }
	
	 //导出审核通过明细按钮事件
	 $('#exportApprovalPassDetail').click(function(){
	        BootstrapDialog.confirm("您确定要导出审核通过明细excel吗？", function(result) {
	            if (result) {
	                var url = ctx + "/lendApplyApprovalLook/exportApprovalPassDetailExcel";
	                var formCon = 'customerCode=' +  $('[name="code"]').val() +
					'&customerName=' + $('[name="name"]').val() +
					'&lendCode=' + $('[name="lendCode"]').val() +
					'&orgCode=' + $('[name="storeId"]').val() +
					'&productCode=' + $('[name="productCode"]').val() +
					'&lendStatus=' + $('[name="lendStatus"]').val() +
					'&applyPay=' + $('[name="payType"]').val() +
					'&applyLendDayFrom=' + $('[name="lendStart"]').val() +
					'&applyLendDayTo=' + $('[name="lendEnd"]').val() +
					'&applyDeductDayFrom=' + $('[name="deductStart"]').val() +
					'&applyDeductDayTo=' + $('[name="deductEnd"]').val() +
					'&dictApplyDeductType=' + trimStr($('[name="dictApplyDeductType"]').val()) +
					'&accountBank=' + trimStr($('[name="accountBank"]').val());
	                window.location = url + "?"  + formCon;
	            }
	        });
	 });

});