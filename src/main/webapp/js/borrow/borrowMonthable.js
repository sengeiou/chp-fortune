	var obj = null;
	/**
	 * 提交检索条件
	 */
	$(document).ready(function() {
		$('#search').click(function(){
			$form = $('#searchForm');
			if ($form.validate().form()) {
				$("#pageNo").val("");
				$form.submit();
			}
		});
		
		// 点击多选框计算金额
		var sumMoney=0;
		var sumNum = 0;
		$("input[type='checkbox']#checkOne").change(function(){
			 if ($(this).prop("checked")){
				sumMoney =parseFloat(restoreFormatCurrency($(this).parent('#creditMonableIdCheckbox').nextAll("#loanAvailabeValue").text()))+parseFloat(sumMoney);
				sumNum = sumNum+1;
				 $("#money").text(formatCurrency(sumMoney));
				 $("#count").text(sumNum);
			 } else{
				sumMoney = parseFloat(jsSub(sumMoney,restoreFormatCurrency($(this).parent('#creditMonableIdCheckbox').nextAll("#loanAvailabeValue").text())));
				sumNum = sumNum-1;
				if(sumNum==0){
					$("#money").text($("#hiddenMoney").val());
					 $("#count").text($("#hiddenCount").val());
				}else{
					$("#money").text(formatCurrency(sumMoney));
					$("#count").text(sumNum);
				}
			 }
		});
		// 全选时计算
		$("input[type='checkbox'].checkAll").change(function(){
			sumMoney=0;
			sumNum = 0;
			if($(this).prop("checked")){
				$("#checkOne:checked").each(function(){
					sumMoney = parseFloat(sumMoney) +parseFloat(restoreFormatCurrency($(this).parent('#creditMonableIdCheckbox').nextAll("#loanAvailabeValue").text()));
					sumNum++;
					$("#money").text(formatCurrency(sumMoney));
					 $("#count").text(sumNum);
				})	
			}else{
				$("#money").text(formatCurrency($("#hiddenMoney").val()));
				 $("#count").text($("#hiddenCount").val());
			}
			
		});
	});

	// 月满盈可用债权批量回池
	function borrowMonthableBatchBackTool(){
		var batchBorrowMonthableInfo = '';
		var loanCreditValue=0;
		var loanAvailabeValueTemp;
		var loanType;
		var num = $("#checkOne:checked").length;
		if(num == 0){
			BootstrapDialog.alert("请选择回池的债权");	
			return;
		}
		$("#checkOne:checked").each(function(){
			loanAvailabeValueTemp = parseFloat($(this).parent('#creditMonableIdCheckbox').nextAll("#loanAvailabeValue").text())
			loanType = $(this).parent('#creditMonableIdCheckbox').nextAll("#loanType").text();
			if(loanAvailabeValueTemp == 0.0 || loanType == '车借'){
				return false;
			}
			batchBorrowMonthableInfo = $(this).val()+","+batchBorrowMonthableInfo;
		})
		if(loanAvailabeValueTemp == 0.0 || loanType == '车借'){
			 BootstrapDialog.alert("你选择的数据中存在不可回池的,请核对后再进行操作");	
			 return;
		}
        BootstrapDialog.confirm('您确定要批量回池所选中的记录吗？', function(result){
            if(result){
                contents_getJsonForSync(
                 ctx+"/borrowMonthable/borrowMonthableBatchBackTool",
                 {"batchBorrowMonthableInfo":batchBorrowMonthableInfo},
                 'post',
                 function(data){
 	    			if (data == '') {
 	    				$("#searchForm").submit();
 					} else {
						BootstrapDialog.confirm(data,function(result){
							if(result){
								$("#searchForm").submit();
							}
						});
					}
 	    		});
            }
        });
	}
	
	// 查看月满盈可用债权信息
	function goPage(creditMonableId){
		// 调用子窗口的参数
    	var url, argment, callback;
    	url = "/borrowMonthable/goBorrowMonthableLook";
    	argment = {'creditMonableId':creditMonableId};
    	load_callback = function(iframe){
    		borrowMonthableBackTool();
    	};
    	close_callback = function(arg){};
        var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
	}	
	
	// 月满盈可用债权查看分页
	function subPage(n,s){
		if (n)
			$("#subPageNo").val(n);
		if (s)
			$("#subPageSize").val(s);
		var url = $("#subSearchForm").attr('action');
		contents_getJsonForHtml(
			url,
			$("#subSearchForm").serialize(),
			'post',
			function(result){
				$('#borrowMonthableLookListDiv').html(result);
				borrowMonthableBackTool();
			}
		);
		return false;
	}
	
	// 月满盈可用债权回池
	function borrowMonthableBackTool(){
		$(".backTool").click(function(){
			var creditMonableId = $("#creditMonableId").val();
			contents_getJsonForHtml(
					ctx+"/borrowMonthable/goBorrowMonthableBackTool", 
					{"creditMonableId":creditMonableId},
					'post',
					function(html){
						$('#modal-sub .modal-body').html(html);
						$('.borrowMonthableBackTool').click(function(){
							// 月满盈可用债权回池
			    			contents_getJsonForSync(
			    		    		$("#borrowMonthableBackToolForm").attr('action'),	
			    		    		$("#borrowMonthableBackToolForm").serialize(),
			    		    		"POST",
			    		    		function(result){
			    		    			if (result == '') {
			    		    				window.location = ctx+"/borrowMonthable/backBorrowMonthableList"
			    						} else {
			    							BootstrapDialog.alert(result);
			    				    		return;
			    						}
			    		    		});
						});
						$('.borrowMonthableBackToolQX').click(function(){
							window.location = ctx+"/borrowMonthable/backBorrowMonthableList"
						});
					},
 					function(data) {
						BootstrapDialog.alert("获取回池信息失败,请核对后再进行操作");
			    		 return;
					});
		});
	}
	// 导出excel
	function outExcel(){
        	var i=0;
        	var data = new Array();
        	$("#checkOne:checked").each(function(){
        		data[i++] = $(this).val();
        	})
        	if(data.length>0){
        		BootstrapDialog.confirm('您确定要导出选中的数据吗？', function(result){
        			if(result){
        				$("#creditMonableIdList").val(data);
        				var url=ctx+"/borrowMonthable/outExcel";
        				window.location=url+"?"+$("#searchForm").serialize();
        			}
        		});
        	}else{
        		$("#creditMonableIdList").val("");
        		if (!$('#searchForm').validate().form()) {
            		BootstrapDialog.confirm('你的输入条件出错,可能会导出全部数据',function(result){
            			if(result){
            				var url=ctx+"/borrowMonthable/outExcel";
            				window.location=url;
            			}
            		});
            	}else{
            		BootstrapDialog.confirm('您确定要导出excel吗？', function(result){
            			if(result){
            				var url=ctx+"/borrowMonthable/outExcel";
            				window.location=url+"?"+$("#searchForm").serialize();
            			}
            		});
            	}
        	}
	} 
	
