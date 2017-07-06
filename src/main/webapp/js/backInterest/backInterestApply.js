/**
 * 批量回息申请
 */
function batchApply(){
	
	iframe = getCurrentIframeContents();
	var forms = $(iframe).find('#searchForm').serialize();
	var s = $("#ids").length;
	if(s == 0){
		BootstrapDialog.alert("页面中没有数据可以提交！！");
	}else{
		var backiId="";//保存回息编号
		if($('[name="ids"]:checked').length == 0){
			// 页面未选中任何一条
			BootstrapDialog.confirm("您没有选择任何一条数据，系统将默认选全部数据并提交,是否确认提交全部？",function(result){
				if(result){
					loadingMarkShow();
					url = ctx+"/backInterestApply/batchApply";
					type = 'post';
					successCal = function(result){
						loadingMarkHide();
						if(result=='true'){
							 BootstrapDialog.alert("操作成功",function(){loadingMarkShow();$('#search').click()});
						}else{
							BootstrapDialog.alert(result,function(){loadingMarkShow();$('#search').click()});
						}
			        }
					errorCal = function (){
						loadingMarkHide();
						BootstrapDialog.alert("服务器忙碌中······");
					};
					contents_getJsonForSync(url, forms, type, successCal,errorCal,null);
				}
			});
		}else{
			// 页面有未选中
			BootstrapDialog.confirm("确认批量回息申请吗?",function(result){
				if(result){
					loadingMarkShow();
					backiId = $('[name="ids"]:checked').map(function() {
						return this.value;
					}).get().join(',');
					
					//异步提交批量回息申请
					url = ctx+'/backInterestApply/batchApply';
					data = {"backiId":backiId};
					type = 'post';
					successCal = function(result){
						loadingMarkHide();
						if(result=='true'){
							 BootstrapDialog.alert("操作成功",function(){loadingMarkShow();$('#search').click()});
						}else{
							BootstrapDialog.alert(result,function(){loadingMarkShow();$('#search').click()});
						}
					};
					errorCal = function (){
						loadingMarkHide();
						BootstrapDialog.alert("服务器忙碌中······");
					};
					contents_getJsonForSync(url, data, type, successCal,errorCal,null);
				}
			});
		}
	}
}

// 提交回息申请验证
function apply(purl,surl){
	iframe = getCurrentIframeContents();
	var forms = $(iframe).find('#applyForm').serialize();
	$form = $('#applyForm',iframe);
	if($form.validate().form()){
		
		//验证成功
		loadingMarkShow();
		limitCommit(forms,purl,surl);
	}
	return false;
}
