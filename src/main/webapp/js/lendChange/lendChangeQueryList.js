$(function(){
/**
 * 导出Excel
 */
$("#expExcel").click(function() {
				 
				BootstrapDialog.confirm("您确定要导出excel吗？", function(result) {
					if (result) {
						var ids = getIds();
						var url = ctx + "/lendChangeExpExcel/expExcel";
						 postFormReplaceGet($("#searchForm"),url + "?applyIds=" + ids);
					}
				});
			});

});

/**
 * 判断是否选择记录
 * @returns {Boolean}
 */
function isChecked(){
	 if($("input[type='checkbox']:checked:not(.checkAll)").size()==0){
//		 $.jBox.tip("您没有选中一条记录");
		 BootstrapDialog.alert('您没有选中一条记录');
		 return true;
	 }
	 return false;
}

/**
 * 获取选中ids
 */
function getIds(){
	 var ids="";
	 $("input[type='checkbox']:checked:not(.checkAll)").each(function(){
		 if($(this).val()!='on')
			 ids=ids+$(this).val()+",";
	 }) 
	 return ids;
}