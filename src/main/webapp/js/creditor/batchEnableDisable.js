var iframe = getCurrentIframeContents();
function toEditSta(name,url,checkboxName){
	if(checkboxName==null){
		checkboxName = 'select[]'
	}
	var selectLenght = $("input[name='" + checkboxName + "']:checked").length;
	if(selectLenght == 0){
		BootstrapDialog.alert("请选择要"+name+"的配置！");
	}else{
		BootstrapDialog.confirm('你确定'+name+'以下'+selectLenght+'项配置',function(result){
			if(result){
				contents_getJsonForHtml(
					ctx + url, 
					$(iframe).find('form').serialize(), //改iframe所有form
					'post', 
					function(result){
						if(result=='SUCCESS'){
							$("#searchForm").submit();
						}else{
							BootstrapDialog.alert(result);
							return;
						}	
					},
					function(){
						BootstrapDialog.alert('更新失败');
					},
					null
				);
				return false;
			}
		});
	}
}