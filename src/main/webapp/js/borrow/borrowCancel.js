// 全局变量
var money=0;
var num=0;
	$(document).ready(function() {
		//债权撤销列表点击检索
		$('#search').click(function(){
			$form = $('#searchForm');
			if ($form.validate().form()) {
				$("#pageNo").val("");
				$form.submit();
			}
		});
		var sumMoney=0;
		var sumNum = 0;
		var sumCurrentCreditLinesMoney = 0;
		// 债权撤销列表点击多选框计算金额
		$("input[type='checkbox']#checkOne").change(function(){
			if($(this).prop("checked")){
				sumCurrentCreditLinesMoney = parseFloat(restoreFormatCurrency($($(this).parent('td').nextAll()[6]).text()))+parseFloat(sumCurrentCreditLinesMoney);
				sumMoney = parseFloat(restoreFormatCurrency($($(this).parent('td').nextAll()[4]).text()))+parseFloat(sumMoney);
				sumNum = sumNum+1;
				 $("#moneySpan").text(formatCurrency(sumMoney));
				 $("#numSpan").text(sumNum);
				 $("#sumCCLinesMoneySpan").text(formatCurrency(sumCurrentCreditLinesMoney));
			}else{
				sumCurrentCreditLinesMoney = parseFloat(sumCurrentCreditLinesMoney) - parseFloat(restoreFormatCurrency($($(this).parent('td').nextAll()[6]).text()));
				sumMoney = parseFloat(sumMoney) - parseFloat(restoreFormatCurrency($($(this).parent('td').nextAll()[4]).text()));
				sumNum = sumNum-1;
				if(sumNum==0){
					$("#moneySpan").text($("#hiddenMoney").val());
					 $("#numSpan").text($("#hiddenCount").val());
					 $("#sumCCLinesMoneySpan").text($("#hiddenCCLinesMoney").val());
				}else{
					$("#sumCCLinesMoneySpan").text(formatCurrency(sumCurrentCreditLinesMoney));
					$("#moneySpan").text(formatCurrency(sumMoney));
					$("#numSpan").text(sumNum);
				}
			}
		});
		
		// 债权撤销列表点击全选计算金额
		$("input[type='checkbox'].checkAll").change(function(){
			sumMoney=0;
			sumNum = 0;
			sumCurrentCreditLinesMoney = 0;
			if($(this).prop("checked")){
				$("#checkOne:checked").each(function(){
					sumCurrentCreditLinesMoney = parseFloat(restoreFormatCurrency($($(this).parent('td').nextAll()[6]).text()))+parseFloat(sumCurrentCreditLinesMoney);
					sumMoney = parseFloat(restoreFormatCurrency($($(this).parent('td').nextAll()[4]).text()))+parseFloat(sumMoney);
					sumNum++;
					$("#sumCCLinesMoneySpan").text(formatCurrency(sumCurrentCreditLinesMoney));
					$("#moneySpan").text(formatCurrency(sumMoney));
					 $("#numSpan").text(sumNum);
				})	
			}else{
				$("#moneySpan").text(formatCurrency($("#hiddenMoney").val()));
				 $("#numSpan").text($("#hiddenCount").val());
				 $("#sumCCLinesMoneySpan").text($("#hiddenCCLinesMoney").val());
			}
		});
		
		// 办理 跳转到债权替换详情页
		$(".btn_edit").click(function(){
			$('<form></form>')
			.attr('action',ctx+'/borrowCancel/replace/'+$(this).attr('lendCode_matchingId')+'?search='+$("#hiddenSearch").val())
			.attr('method','post')
			.appendTo(this).submit();
		});
		
		
		// 删除提前结清的债权列表
		$('.btnRemove').click(function(){
			$table= $(this).closest('div.body_new').find('.tb_freez');
			var creditValueIds= "";
			var dictLoanTypeArray= "";
		   	 $(".trClass",$table).each(function(){
		   		 var creditValueId = $(this).find('input[name="creditValueId"]').val();
		   		 creditValueIds =  creditValueId+","+creditValueIds;
		   		 var dictLoanType = $(this).find('input[name="dictLoanType"]').val();
		   		 dictLoanTypeArray =  dictLoanType+","+dictLoanTypeArray;
		   	 });
		   	 $("#oldCreditValueIds").val(creditValueIds);
		   	 $("#oldDictLoanTypeArray").val(dictLoanTypeArray);
		   	 $('.trClass').remove();
		});
		
		// 保存新的债权
		var dictLoanFreeFlag;
//		$(".cancel").click(function(){
//			
//			window.location.href=ctx+'/borrowCancel/list?search='+$("#hiddenSearch").val();
//		})
		$(".saveNew").click(function(){
			$table= $(this).closest('div.body_new').find('.tb_freez');
			var creditLines = 0;
			$(".trClass",$table).each(function(){
				dictLoanFreeFlag = $(this).find("input[name='dictLoanFreeFlag']").val();
				if(dictLoanFreeFlag != '1'){
					return false;
				}
				creditLines = parseFloat(restoreFormatCurrency($(this).find('.th2', 'td').val()))+parseFloat(creditLines);
			 });
			if(dictLoanFreeFlag != '1'){
				BootstrapDialog.alert("匹配中存在不可用的债权,请移除后重新匹配");
				return false;
			}
			//过滤已带回债权的重复债权人-----------
			var loanIdcardArray;
			var loanIdcardStr="";
		   	 $(".trClass",$table).each(function(){
		   		 var loanIdcard = $(this).find('input[name="loanIdcard"]').val();
		   		loanIdcardStr =  loanIdcard+","+loanIdcardStr;
		   	 });
		   	loanIdcardStr = loanIdcardStr.substring(0,loanIdcardStr.length-1);
		   	loanIdcardArray = loanIdcardStr.split(",");
		   	 for(var i = 0; i < loanIdcardArray.length-1; i++){
		   		 for(var j = i+1; j < loanIdcardArray.length; j++){
		   			 if(loanIdcardArray[i]==loanIdcardArray[j]){
		   				BootstrapDialog.alert("以下债权存在相同的债权人，请重新选择");
		   				return false;
		   			 };
		   		 }
		   	 }
		   	 
			//-----------------
			var money = parseFloat(restoreFormatCurrency($(".nowSpan").text()));
			creditLines = parseFloat(creditLines).toFixed(2);
			if (money!=creditLines) {
				BootstrapDialog.alert("推荐金额只能等于本期待推荐金额");
				return false;
			}
			var obj = getTables($table);
			contents_getJsonForStringify(
				ctx + "/borrowCancel/save",
				JSON.stringify(obj),
				'post',
				function(msg){
						   //BootstrapDialog.alert(msg);
					   if(msg == ""){
						   window.location.href=ctx+"/borrowCancel/list?search="+$("#hiddenSearch").val()
					   }else{
						   BootstrapDialog.alert(msg);
						   return false;
					   }
				   }
			);
		})
	});

	// 可用债权查询弹出框
	function recommendBorrow(){
		var creditLines=0;
		$(".trClass").each(function(){
			creditLines = restoreFormatCurrency($(this).find('.th2', 'td').val())+parseFloat(creditLines);
		 });
		var money = restoreFormatCurrency($(".nowSpan").text());
		if (money == parseFloat(creditLines)||money < parseFloat(creditLines)) {
			BootstrapDialog.alert("债权已经匹满");
			return false;
		}
		// 已选择的的债权
		var creditValueIds="";
		var creditValueIdArray =  $("#bqtjzqlb input[name='creditValueId']");//可用债权ID
		if(typeof(creditValueIdArray) != undefined){
			for (var i=0; i<creditValueIdArray.length;i++){
				creditValueIds = creditValueIds+$(creditValueIdArray[i]).val()+"-";
			}
		}
		//如果本期推荐债权列表为同一债权来源类型，就按此债权来源类型过滤，否则，不过滤
		var dictLoanTypeArray =  $("#oldDictLoanTypeArray").val()//债权来源
		dictLoanTypeArray = dictLoanTypeArray.substring(0,dictLoanTypeArray.length-1);
		dictLoanTypeArray = dictLoanTypeArray.split(',');
		var fangJie = 0;
		var xinJie = 0;
		var dictLoanType = null;
		if(typeof(dictLoanTypeArray) != undefined){
			for (var i=0; i<dictLoanTypeArray.length;i++){
				 if(dictLoanTypeArray[i] == "2"){
					 fangJie++;
				 }if(dictLoanTypeArray[i] == "0"){
					 xinJie++;
				 }
			}
		}
		if(fangJie > 0 && xinJie == 0){
			dictLoanType = 2;
		}else if(fangJie == 0 && xinJie > 0){
		    dictLoanType = 0;
		}

		// 调用子窗口的参数
		var lendCode = $("#lendCode").text();
		var matchingId = $("#matchingId").text();
		var url, argment, callback;
		url ='/borrowCancel/goMatchingBorrowLook';
		argment = {'lendCode':lendCode,'money':money,'matchingId':matchingId,'dictLoanType':dictLoanType,'creditValueIds':creditValueIds};
		load_callback = function(iframe){
			// 窗口弹出之后,注测checkbox事件
			subInitEvent();
			// 点击搜索
			$(".matchingBorrowBut").click(function(){
				var productMatchingRateUpper = $("#productMatchingRateUpper").val();
				var productMatchingRateLower = $("#productMatchingRateLower").val();
				var borrowMonthRate = $("input[name='borrowMonthRate']").val();
				if(!isNaN(productMatchingRateUpper)&&!isNaN(productMatchingRateLower)){
					if(!(parseFloat(borrowMonthRate) <= parseFloat(productMatchingRateUpper) && parseFloat(borrowMonthRate) >= parseFloat(productMatchingRateLower))){
						BootstrapDialog.alert("月利率应在"+parseFloat(productMatchingRateLower)+"-"+parseFloat(productMatchingRateUpper)+"之间");
						return false;
					}
				}
				var formData = $("#searchForm").serialize();
				contents_getJsonForHtml(
						ctx+"/borrowCancel/matchingBorrowLook",
						formData,
						"POST",
						function(html){
							$('#listDiv').html(html);
							initAfterLoad();
							subInitEvent();
						});
				});
			
			// 显示既有债权人
			$("input[type='checkbox'].borrowClass").change(function(){
				if ($(this).prop("checked")){
					//var matchingId = $("#lendCode").text();
					contents_getJsonForHtml(
					   ctx+ "/borrowCancel/findExistingBorrow",
					   {"lendCode":lendCode},
					   'POST',
					   function(html){
						   $('#listDiv').html(html);  
							initAfterLoad();
							subInitEvent();
					   }
					);
				}else{
					var formData = $("#searchForm").serialize();
					contents_getJsonForHtml(
							ctx+"/borrowCancel/matchingBorrowLook",
							formData,
							"POST",
							function(html){
							   $('#listDiv').html(html);
								initAfterLoad();
								subInitEvent();
							}
					);
				}
			});
			
		    $('.leadBackBut',iframe).off('click').on('click',function(){
		 	   sub.closeSubWindow(iframe);//关闭事件
		 	   return false;
		     });
		};
		
		// 关闭
		close_callback = function(iframe){
			var creditValueIds = "";
			$('input[name="creditValueId"]:checked').each(function(){
				creditValueIds = $(this).val()+","+creditValueIds;
			 });
			if(creditValueIds == ""){
				BootstrapDialog.alert("请选择债权后再带回");
				return false;
			}
			var creditLines=$('.creditLines',iframe).val();
			var $table = $('.table2',iframe);
			$.ajax({
			     url:ctx+"/borrowCancel/creditValueIds",
				 type:'post',
				 data:{'creditValueIds':creditValueIds,'creditLines':creditLines},
				 dataType:'json',
				 success : function(result) {
					var array = eval(result);
					for (var i = 0; i < array.length; i++) {
						$tr = $('<tr class="trClass"></tr>');
						$tr.append("<td style='display:none' class ='th1'>"
								+ "<input type='hidden' name='creditValueId' value=\""+array[i].creditValueId+":"+array[i].verTime+"\"/> " 
								+"<input type='hidden' name='dictLoanFreeFlag' value=\""+array[i].dictLoanFreeFlag+"\"/>" 
								+"<input type='hidden' name='loanIdcard' value=\""+array[i].loanIdcard+"\"/>" 
								+"</td>");
						$tr.append($('<td >' + array[i].loanName + '</td>'));
						$tr.append($('<td >' + array[i].dictLoanType + '</td>'));
						$tr.append($('<td >' + array[i].loanJob + '</td>'));
						$tr.append($('<td >' + array[i].loanMiddleMan + '</td>'));
						$tr.append($('<td >' + array[i].loanPurpose + '</td>'));
						$tr.append($('<td >' + array[i].loanBackmoneyFirdayString + '</td>'));
						$tr.append($('<td >' + array[i].loanBackmoneyLastdayString + '</td>'));
						$tr.append($('<td >' + array[i].loanMonths + '</td>'));
						$tr.append($('<td >' + array[i].loanMonthsSurplus + '</td>'));
						$tr.append($('<td >' + array[i].loanQuotaString + '</td>'));
						$tr.append($('<td >' + array[i].loanCreditValueString + '</td>'));
						$tr.append($('<td >' + array[i].loanMonthRateString + '</td>'));
						$tr.append($('<td > <input class ="th2" type="text" name="creditLines" value="'+ array[i].creditLinesString + '"/>' + '</td>'));
						$(".tb_freez").append($tr);
					}
				}
			});   
		}
		var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
	};
	
	// 获得本期推荐债权列表信息
	function getTables(table){
		var lendCode = $("#lendCode").text();
		var matchingId = $("#matchingId").text();
		//var phaseMateId = $("#phaseMateId").text();
		var primaryCreditLines = restoreFormatCurrency($(".primarySpan").text());
		var oldCreditValueIds = $("#oldCreditValueIds").val();
		var obj = new Object();
		obj.lendCode=lendCode;
		obj.matchingId = matchingId;
		obj.primaryCreditLines = primaryCreditLines;
		//obj.phaseMateId = phaseMateId;
		obj.oldCreditValueIds=oldCreditValueIds;
		var data = new Array();
		var index = 0;
		$(".trClass",table).each(function(){
		 	var org = new Object();
		    var creditValueId = $(this).find('input[name="creditValueId"]').val();
			org.creditValueId = creditValueId;
			var creditLines =  restoreFormatCurrency($(this).find('.th2', 'td').val());
			org.creditLines = creditLines;
			data[index++] = org;
		 });
		obj.data=data;
		return obj;
	}
	
	//查看可用债权弹框内事件
	function subInitEvent(){
		var subSumMoney = 0;
		var subSumNum = 0;
		// 点击多选框计算金额
		$("input[type='checkbox']#subCheckOne").change(function(){
			if($(this).prop("checked")){
				subSumMoney = parseFloat($($(this).parent('td').nextAll()[13]).text())+parseFloat(subSumMoney);
				subSumNum = subSumNum + 1;
				 $("#subBorrowMoney").val(subSumMoney.toFixed(2));
				 $("#subBorrowNum").val(subSumNum);
			}else{
				subSumMoney = parseFloat(subSumMoney) - parseFloat($($(this).parent('td').nextAll()[13]).text());
				subSumNum = subSumNum - 1;
				if(subSumNum == 0){
					$("#subBorrowMoney").val("0.00");
					 $("#subBorrowNum").val("0");
				}else{
					$("#subBorrowMoney").val(subSumMoney.toFixed(2));
					$("#subBorrowNum").val(subSumNum);
				}
			}
		});
		
		// 点击全选计算金额
		$("input[type='checkbox'].checkAll").change(function(){
			subSumMoney = 0;
			subSumNum = 0;
			if($(this).prop("checked")){
				$("#subCheckOne:checked").each(function(){
					subSumMoney = parseFloat($($(this).parent('td').nextAll()[13]).text())+parseFloat(subSumMoney);
					subSumNum ++;
					$("#subBorrowMoney").val(subSumMoney.toFixed(2));
					 $("#subBorrowNum").val(subSumNum);
				})	
			}else{
				$("#subBorrowMoney").val("0.00");
				 $("#subBorrowNum").val("0");
			}
		});
	}

/**
 * 弹出子窗口分页方法
 * @param n
 * @param s
 * @returns {Boolean}
 */
function subPage(n,s){
	if (n)
		$("#pageNo").val(n);
	if (s)
		$("#pageSize").val(s);
	var url = $("#searchForm").attr('action');
	contents_getJsonForHtml(
		url,
		$("#searchForm").serialize(),
		'post',
		function(result){
			$('#listDiv').html(result);
			var creditValueIds = $(".creditValueIds").val();
			var creditValueIdsArray = creditValueIds.split(",");
			$('input[name="creditValueId"]').each(function(){
				for (var i = 0; i < creditValueIdsArray.length; i++) {
					if (creditValueIdsArray[i] == $(this).val()) {
						$(this).attr("checked","checked")
					}
				}
			});
			initAfterLoad();
			subInitEvent();
		}
	);
	return false;
}

/**
 * 导出excel
 */
function outExcel(){
	 BootstrapDialog.confirm('您确定要导出excel吗？', function(result){
         if(result){
        	 var ids = getIds();
        	 $("#matchingId").val(ids);
        	 var url=ctx+"/borrowCancel/outExcel";
			 window.location=url+"?"+$("#searchForm").serialize();
         }
     });
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
