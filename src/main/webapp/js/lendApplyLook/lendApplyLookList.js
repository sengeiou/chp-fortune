/**
 *  申请审批查看列表
 */

 $(function(){
	 var sumMoney = 0;
	 var index = 0;
	 // 复选框计算金额
	 $('input[type="checkbox"]#checkOne').change(function(){
		 if($(this).prop('checked')){
			 sumMoney = parseFloat($(this).parent("#checkboxTd").nextAll("#applyLendMoney").attr('v')) + parseFloat(sumMoney);
			 index++;
			 $("#title_msg").text("选中金额：");
			 $("#sumMoney").text(fmoney(sumMoney,2));
		 }else{
			 sumMoney = Number(sumMoney).toFixed(5) - Number($(this).parent("#checkboxTd").nextAll("#applyLendMoney").attr('v')).toFixed(5);
			 index--;
			 if(index==0){
				 $("#title_msg").text("总金额：");
				 $("#sumMoney").text(fmoney($("#hiddenMoney").val(),2));
			 }else{
				 $("#title_msg").text("选中金额：");
				 $("#sumMoney").text(fmoney(sumMoney,2));
			 }
		 }
	 });
	 
	 function fmoney(s, n) {  
	    n = n > 0 && n <= 20 ? n : 2;  
	    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";  
	    var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];  
	    t = "";  
	    for (i = 0; i < l.length; i++) {  
	        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");  
	    }  
	    return t.split("").reverse().join("") + "." + r;  
	 }  
	 
	 // 全选时计算金额
	 $('input[type="checkbox"].checkAll').change(function(){
		 sumMoney = 0;
		if($(this).prop("checked")){
			$("#checkOne:checked").each(function(){
				 sumMoney = parseFloat($(this).parent("#checkboxTd").nextAll("#applyLendMoney").attr('v')) + parseFloat(sumMoney);
			});
			$("#title_msg").text("选中金额：");
			$("#sumMoney").text(fmoney(sumMoney,2));
		}else{
			$("#title_msg").text("总金额：");
			$("#sumMoney").text(fmoney($("#hiddenMoney").val(),2)); 
		}
	 });
	 var iframe = getCurrentIframeContents();
	 //导出扣减excel事件
	 $('#exportDeductExcel').click(function() {
		BootstrapDialog.confirm('您确定要导出选中的数据吗？', function(result) {
			if (result) {
				$("#lendCodeHidden").val(getIds());
				var url = ctx + "/lendApplyLook/exportDeductExcel";
				postFormReplaceGet($("#searchForm"),url);
			}
		});
	});
	 //导出出借excel事件
	 $('#exportLendExcel').click(function(){
		 var i=0;
			var data = new Array();
			$("#checkOne:checked").each(function(){
				data[i++] = $(this).val();
			})
			if(data.length>0){
				BootstrapDialog.confirm('您确定要导出选中的数据吗？', function(result){
					if(result){
						$("#lendCodeHidden").val(data);
						 var url = ctx + "/lendApplyLook/exportLendExcel";
						 postFormReplaceGet($("#searchForm"),url);
					}
				});
			}else{
				$("#lendCodeHidden").val("");
				BootstrapDialog.confirm("您确定要导出出借excel吗？", function(result) {
		            if (result) {
		                var url = ctx + "/lendApplyLook/exportLendExcel";
		                postFormReplaceGet($("#searchForm"),url);
		            }
		        });
			}
	        
	 });

		$('#btnCaAgain').click(function(){
			param="";
			 $("#tableList").find("input[type=checkbox]#checkOne:checked").each(function(){
				 val = $(this).val();
				 if(val && val.length>0){
					 param = param + "&lendCodes=" + val;
				 }
			 });
			 if(param!=""){
				 
				 contents_getJsonForSync(
							ctx + "/lend/approve/caAgain?"+param, 
							{}, 
							"post", 
							function (result) {
						        
								BootstrapDialog.alert(result);
					        },
					        function (data, errorThrown, options, error) {
					        	BootstrapDialog.alert("合同申请书签章，请求异常！");
					        	return false;
					        },
					        null
					);
			 }else{
				 return false;
			 }
		});
				
	 
	 //查询
//	 $('#searchBtn').click(function(){
//		 $('#searchForm').submit();
//	 });
	 //依照测试问题将出借金额默认为空
	 if($('[name="applyLendMoneyFrom"]').val()==0.0){
		 $('[name="applyLendMoneyFrom"]').val('');
	 }
	 if($('[name="applyLendMoneyTo"]').val()==0.0){
		 $('[name="applyLendMoneyTo"]').val('');
	 }
	 
 });
 
function submitSql(n, s){
	if (n)
		$("#pageNo",$("#searchForm")).val(n);
	if (s)
		$("#pageSize",$("#searchForm")).val(s);
	 $('#searchForm').submit();
}
/**
 * 获取选中ids
 */
function getIds(){
	 var ids="";
	 $("input[name='checkbox']:checked:not(.checkAll)").each(function(){
		 if($(this).val()!='on')
			 ids=ids+$(this).val()+",";
	 }) 
	 return ids;
}

function _todjr(lendCode){
	var url = ctx + "/lend/finish/todjr?lendCode=" + lendCode;
	$.colorbox({
		href : url,
		iframe : true,
		overlayClose : false,
		width : "600",
		height : "415"
	});
}

/**
 * 跳转到优先回款详情页
 * @param lendCode
 * @param userManagerId
 * @param priorityId
 */
function  switchPriority(lendCode, userManagerId , priorityId){
	location.href=ctx+'/lendApplyLook/goPriorityBackPage?code='+lendCode+'&userManagerId='+userManagerId+'&priorityId='+priorityId;
}
