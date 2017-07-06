/**
 * 提交检索条件
 */
$(document).ready(function() {
	$('#search').click(function(){
		$form = $('#searchForm');
		if ($form.validate().form()) {
			$form.submit();
		}
	})
	//全选时改变显示的 总笔数,初始出借总金额,本期推荐总金额
	$("input[type='checkbox'].checkAll").change(function(){
		var lendMoney = 0;
		var lendMoneyTotal = 0;
		var borrowQuota = 0;
		var borrowQuotaTotal = 0;
		var numberTotal = 0;
		
		if($("#checkAll").prop("checked")){
			$("#checkBoxOne:checked").each(function(){
				 if($(this).val()!='on'){
					 lendMoney = $(this).parent().parent().find("input[name='lendMoney']").val();
					 lendMoneyTotal = + lendMoney + lendMoneyTotal;
					 
					 borrowQuota = $(this).parent().parent().find("input[name='borrowQuota']").val();
					 borrowQuotaTotal = + borrowQuota + borrowQuotaTotal;
					 
					 numberTotal = $("#checkBoxOne:checked").length;
				 }
			 }) 
		}else{
			lendMoneyTotal = $("#lendMoneyCount").val();
			borrowQuotaTotal = $("#borrowQuoCount").val();
			numberTotal = $("#numberCount").val();
		}
		 $("#lendMoneyTotal").html(fmoney(lendMoneyTotal,2));
		 $("#borrowQuotaTotal").html(fmoney(borrowQuotaTotal,2));
		 $("#numberTotal").html(numberTotal);
	});
});

/**
 * 导出excel
 */
function outExcel(){
	 BootstrapDialog.confirm('您确定要导出excel吗？', function(result){
         if(result){
        	 var ids = getIds();
        	 $("#matchingId").val(ids);
        	 var url=ctx+"/matchingcreditor/outExcel";
			 window.location=url+"?"+$("#searchForm").serialize();
         }
     });
}
function ppjl(){
	$("h4").text("匹配记录信息");
	var url, argment, callback;
  	url = "/matchingcreditor/getppll";
  	argment = null;
  	load_callback = function(iframe){};
  	//回调函数
  	close_callback = function(iframe){
  	};
      var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
   
}
function checkmaxtMoney(){
	var startApplyLendMoneyFrom = $("#startApplyLendMoneyFrom").val();
	if($("#startApplyLendMoneyFrom").val() ==undefined){
		startApplyLendMoneyFrom = 0 ;
	}
	var startApplyLendMoneyTo = $("#startApplyLendMoneyTo").val();
	if(Number(startApplyLendMoneyFrom)-Number(startApplyLendMoneyTo)>0){
		alert('初始出借金额最大值不能小于最小值');
		 $("#startApplyLendMoneyTo").val('');
	}
}
//获取选中ids
function getIds(){
	 var ids="";
	 $("input[type='checkbox']:checked").each(function(){
		 if($(this).val()!='on')
			 ids=ids+$(this).val()+";";
	 }) 
	 return ids;
}
/**
 * 根据勾选条数改变显示的 总笔数,初始出借总金额,本期推荐总金额
 */
function changeMoney(src){
	var lendMoney = 0;
	var lendMoneyTotal = 0;
	var borrowQuota = 0;
	var borrowQuotaTotal = 0;
	var numberTotal = 0;
	
	if(0 == $("#checkBoxOne:checked").length){
		lendMoneyTotal = $("#lendMoneyCount").val();
		borrowQuotaTotal = $("#borrowQuoCount").val();
		numberTotal = $("#numberCount").val();
	}else{
		$("#checkBoxOne:checked").each(function(){
			 if($(this).val()!='on'){
				 lendMoney = $(this).parent().parent().find("input[name='lendMoney']").val();
				 lendMoneyTotal = + lendMoney + lendMoneyTotal;
				 
				 borrowQuota = $(this).parent().parent().find("input[name='borrowQuota']").val();
				 borrowQuotaTotal = + borrowQuota + borrowQuotaTotal;
				 
				 numberTotal = $("#checkBoxOne:checked").length;
			 }
		 }) 
	}
	 $("#lendMoneyTotal").html(fmoney(lendMoneyTotal,2));
	 $("#borrowQuotaTotal").html(fmoney(borrowQuotaTotal,2));
	 $("#numberTotal").html(numberTotal);
}
//格式化金额
function fmoney(s, n)
{
   n = n > 0 && n <= 20 ? n : 2;
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";//更改这里n数也可确定要保留的小数位
   var l = s.split(".")[0].split("").reverse(),
   r = s.split(".")[1];
   t = "";
   for(i = 0; i < l.length; i++ )
   {
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
   }
   return t.split("").reverse().join("") + "." + r;
}