/**
 * 呼叫中心，门店客户列表
 */


$(function() {
//	alert($);
	//弹出窗口
	var iframe = getCurrentIframeContents();
	$('.distributeClass').click(function() {
		var custId = $(this).attr("custId");
		var sub;
		$(".modal-title").html("分配理财经理");
//		$('#subSubmit').show();
		
		var url = '/callCenterController/getStoreDistributePage';
		var argment = { 
				'id' : custId
		};
		
		var load_callback = function() {
			$('#subSubmit').show();  //隐藏弹出框的提交按钮
			//提交
			$('#subSubmit', iframe).click(function(){
				var idP = $('#storeDistributeForm').find('#id').val();
				var managerId =  $('#storeDistributeForm').find('#managerId').val();
				var mobilePhone = $('#storeDistributeForm').find('#customerMobilephone').val();
				if(! managerId) {
					BootstrapDialog.alert('请选择理财经理');
					return false;
				}
				var data={};
				data.id = idP;
				data.managerId = managerId;
				data.customerMobilephone = mobilePhone;
				$.ajax({
			        type: 'post',
			        url:  ctx + "/callCenterController/distributeManager",
			        dataType: "json",
			        data: data,
			        async: false,
			        success: function (obj) {
			        	if(obj.result == 'true') {
			        		BootstrapDialog.alert(obj.msg);
			        		sub.closeSubWindow();
			        		refreshPage();
			        	} else {
			        		BootstrapDialog.alert(obj.msg);
			        		refreshPage();
			        	}
			        },
			        error: function (data, errorThrown, options, error) {
			        	BootstrapDialog.alert('分配失败');
			        }
			    });
				
				
			});
		};
		var close_callback = function(){};
		sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
		$('#subClose').off('click').on('click',function(){  sub.closeSubWindow();});
		
		
	});
	 //刷新
	var refreshPage = function() {
		$('#searchForm').submit();
	}
});