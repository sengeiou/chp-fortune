//电销功能模块，查找理财经理

/**
 * 打开查找理财经理的对话框
 */
$(document).ready(function(){
	
	var iframe = getCurrentIframeContents();
	$("#saveDistribute", iframe).click(function() {
		var url = ctx + '/customerPoolController/distributeManager';
		var argment = null;
		var form = $("#distributeForm",iframe);
		
		if(! form.validate().form()) {
			return; 
		}
		
//		var isTeleManager = $("#isTeleManager",iframe).val();
//		
//		if(isTeleManager == '1' && !$("#managerId", iframe).val() ) {
//			BootstrapDialog.alert('请分配理财经理');
//			return false;
//		}
//		if(isTeleManager == '0' && !$("#userId", iframe).val() ) {
//			BootstrapDialog.alert('请分配电销理财专员');
//			return false;
//		}

		if( !$("#managerId", iframe).val() ) {
			BootstrapDialog.alert('请分配理财经理');
			return false;
		}
		if( !$("#userId", iframe).val() ) {
			BootstrapDialog.alert('请分配电销理财专员');
			return false;
		}
		loadingMarkShow();
		
		form.submit();
		
				
	});

});