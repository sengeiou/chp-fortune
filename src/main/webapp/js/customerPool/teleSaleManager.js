//电销功能模块，查找理财经理

/**
 * 打开查找理财经理的对话框
 */

 var searchTelManagerOut;
 var searchManagerOut;
$(document).ready(function(){
	
	var iframe = getCurrentIframeContents();
	var sub;
	$("#chooseManager",iframe).click(
			function() {
				var url = '/customerPoolController/getManagerList';
				var argment = null;
				$(".modal-title",iframe).html("选择理财经理");
				
				var load_callback = function() {
					
					$('#subSubmit', iframe).hide();  //隐藏弹出框的提交按钮
					
					//绑定搜索按钮事件
					$("#searchManager", iframe).click(function(){
						searchManager();
					});
					
					$("#clearManagerCondition").click(function(){
						$('#searchManagerForm').find(':text').val('');
						$('#searchManagerForm').find('select').val('');
					});
					
					//带回确认的理财经理信息
					$('#checkConfirm', iframe).click(
							function() {
								var checkObj = $('#managerSearchTele').find(':checked');
								if(!checkObj || checkObj.length == 0) {
									BootstrapDialog.alert("请选择带回项");
									return false;
								}
								$('#managerId').val(checkObj.val());
								$('#managerName').val(checkObj.attr('userName'));
								sub.closeSubWindow();
							}
					);
				};
				var close_callback = function(){};
				
				sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
				$('#subClose').off('click').on('click',function(){  sub.closeSubWindow();});
				
				//查找
				var searchManager = function() {
					var url = ctx + '/customerPoolController/getManagerList';
					var userCode = $('#searchManagerForm').find('#userCodeCon').val();
					var userName = $('#searchManagerForm').find('#userNameCon').val();
					var orgName = $('#searchManagerForm').find('#orgNameCon').val();
					var delFlagTemp = $('#searchManagerForm').find('#delFlagTemp').val();
					var pageNo = $('#searchManagerForm').find('#pageNo').val();
					var pageSize =  $('#searchManagerForm').find('#pageSize').val();
					contents_getJsonForHtml(
							url, 
							{"userCode" : userCode, 
								'userName' : userName,
								'orgName' : orgName,
								'delFlagTemp' : delFlagTemp,
								'pageNo' : pageNo,
								'pageSize' : pageSize
							},
							'post',
							function(html){
								$('#modal-sub .modal-body', iframe).html(html);
								load_callback();
							}
					);
				}
				
				searchManagerOut = searchManager;
			}
	);
	
	
	/**
	 * 打开查找电销专员对话框
	 */
	var sub4Tele;
	$("#chooseUser",iframe).click(
			function() {
				var url = '/customerPoolController/getTeleManagerList';
				var argment = null;
				$(".modal-title",iframe).html("选择电销专员");
				//定义 对话框关闭回调方法
				var close_callback = function(){
					
				};
				//定义 加载对话框回调方法
				var load_callback4Tel = function() {
					$('#subSubmit', iframe).hide();  //隐藏弹出框的提交按钮
					
					$("#searchTelManager").click(function(){
						 searchTelManager();
					});
					
					$("#clearTelCondition").click(function(){
						$('#searchTelManagerForm').find(':text').val('');
						$('#searchTelManagerForm').find('select').val('');
					});
					
					
					//带回确认的电销人员信息
					$('#checkConfirm4TelManager', iframe).click(function() {
						var checkObj = $('#telManagerSearchTele', iframe).find(':checked');
						if(!checkObj || checkObj.length == 0) {
							BootstrapDialog.alert("请选择带回项");
							return false;
						}
						
						$('#userId', iframe).val(checkObj.val());
						$('#userName', iframe).val(checkObj.attr('userName'));
						sub4Tele.closeSubWindow();
					});
				};
				sub4Tele = SubDialogHandle.create(url,argment,load_callback4Tel,close_callback).loadSubWindow();
				$('#subClose').off('click').on('click',function(){  sub4Tele.closeSubWindow();});
				
				//搜索电销人员
				var searchTelManager = function() {
					var url = ctx + '/customerPoolController/getTeleManagerList';
					var userCode = $("#searchTelManagerForm").find('#userCodeCon', iframe).val();
					var userName = $("#searchTelManagerForm").find('#userNameCon', iframe).val();
					var orgName = $("#searchTelManagerForm").find('#orgNameCon', iframe).val();
					var pageNo = $('#searchTelManagerForm').find('#pageNo').val();
					var pageSize =  $('#searchTelManagerForm').find('#pageSize').val();
					contents_getJsonForHtml(
							url, 
							{"userCode" : userCode, 
								'userName' : userName,
								'orgName' : orgName,
								'pageNo' : pageNo,
								'pageSize' : pageSize
							},
							'post',
							function(html){
								$('#modal-sub .modal-body', iframe).html(html);
								load_callback4Tel();
							}
					);
				}
				searchTelManagerOut = searchTelManager;
			}
	);

});

function pageGetManagerList(n, s) {
	if (n)
		$("#pageNo",$("#searchManagerForm")).val(n);
	if (s)
		$("#pageSize",$("#searchManagerForm")).val(s);
//	data = $("#searchManagerForm").serialize();
	searchManagerOut();
	return false;
}

function pageGetTeleManagerList(n, s) {
	if (n)
		$("#pageNo",$("#searchTelManagerForm")).val(n);
	if (s)
		$("#pageSize",$("#searchTelManagerForm")).val(s);
//	data = $("#searchTelManagerForm").serialize();
	searchTelManagerOut();
	return false;
}




