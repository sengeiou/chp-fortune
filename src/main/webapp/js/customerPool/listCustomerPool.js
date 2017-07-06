/**
 * 公共池列表js
 */

$(function() {
	fortune.initCity(null,'addrProvince','addrCity','addrDistrict');
	//获取选中行的id
	function getIds(){
	     var ids="";
	     $("input[name='id'][type='checkbox' ]:checked").each(function(){
	         if($(this).val()!='on')
	             ids=ids+$(this).val()+",";
	     }) 
	     return ids;
	}
	//导出excel
	$('#exportCustomerPool').click(function() {
		BootstrapDialog.confirm("您确定要导出excel吗？", function(result) {
            if (result) {
                var ids = getIds();
                var url = ctx + "/customerPoolController/exportCustomerPool";
                window.location = url + "?checkedCodes=" + ids + "&"
                        + $("#searchForm").serialize();
            }
        });
	});
	
});