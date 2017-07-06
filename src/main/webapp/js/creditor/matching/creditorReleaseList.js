$(document).ready(function() {
	// 提交检索条件
	$('#search').click(function(){
		$form = $('#searchForm');
		if ($form.validate().form()) {
			$("#pageNo").val("");
			$form.submit();
		}
	});
	// 点击多选框计算金额
	var sumMoney=0;
	var sumMoneyL2=0;
	var sumNum = 0;
	$("input[type='checkbox']#checkOne").change(function(){
		 if ($(this).prop("checked")){
			sumMoney = parseFloat(restoreFormatCurrency($(this).parent('#releaseIdCheckbox').nextAll("#loanCreditValue").text()))+sumMoney;
			sumMoneyL2 = parseFloat(parseFloat(restoreFormatCurrency($(this).parent('#releaseIdCheckbox').nextAll("#loanCreditValue").text())).toFixed(2))+sumMoneyL2;
			sumNum = sumNum+1;
			 $("#money").text(formatCurrency(sumMoney));
			 $("#moneyL2").text(formatCurrency(sumMoneyL2));
			 $("#count").text(sumNum);
		 } else{
			sumMoney = parseFloat(jsSub(sumMoney,restoreFormatCurrency($(this).parent('#releaseIdCheckbox').nextAll("#loanCreditValue").text())));
			sumMoneyL2 = parseFloat(parseFloat(jsSub(sumMoneyL2,restoreFormatCurrency($(this).parent('#releaseIdCheckbox').nextAll("#loanCreditValue").text()))).toFixed(2));
			sumNum = sumNum-1;
			if(sumNum==0){
				$("#money").text($("#hiddenMoney").val());
				$("#moneyL2").text($("#hiddenMoneyL2").val());
				 $("#count").text($("#hiddenCount").val());
			}else{
				$("#money").text(formatCurrency(sumMoney));
				$("#moneyL2").text(formatCurrency(sumMoneyL2));
				$("#count").text(sumNum);
			}
		 }
	});
	// 全选时计算
	$("input[type='checkbox'].checkAll").change(function(){
		sumMoney=0;
		sumMoneyL2=0;
		sumNum = 0;
		if($(this).prop("checked")){
			$("#checkOne:checked").each(function(){
				sumMoney = parseFloat(sumMoney) + parseFloat(restoreFormatCurrency($(this).parent('#releaseIdCheckbox').nextAll("#loanCreditValue").text()));
				var addMoney = parseFloat(parseFloat(restoreFormatCurrency($(this).parent('#releaseIdCheckbox').nextAll("#loanCreditValue").text())).toFixed(2));
				sumMoneyL2 = parseFloat(sumMoneyL2) + addMoney;
				sumNum++;
				$("#money").text(formatCurrency(sumMoney));
				$("#moneyL2").text(formatCurrency(sumMoneyL2));
				 $("#count").text(sumNum);
			})	
		}else{
			$("#money").text(formatCurrency($("#hiddenMoney").val()));
			$("#moneyL2").text(formatCurrency($("#hiddenMoneyL2").val()));
			 $("#count").text($("#hiddenCount").val());
		}
	});
});

/**
 * 检测是否存在条件过滤
 */
function checkExistFilter(){
	var loanName = $("input[name='loanName']").val();  //借款人
	var releaseCreditValueFrom = $("input[name='releaseCreditValueFrom']").val();  //释放金额-起始
	var releaseCreditValueTo = $("input[name='releaseCreditValueTo']").val();  //释放金额-结束
	var loanMonthsSurplusFrom = $("input[name='loanMonthsSurplusFrom']").val();  //剩余期数-起始
	var loanMonthsSurplusTo = $("input[name='loanMonthsSurplusTo']").val();  //剩余期数-结束
	var loanBackmoneyDay = $("select[name='loanBackmoneyDay']").val();  //还款日
	var loanMonthRate = $("input[name='loanMonthRate']").val();  //月利率
	var loanBackmoneyFirdayFrom = $("input[name='loanBackmoneyFirdayFrom']").val();  //首次还款日-起始
	var loanBackmoneyFirdayTo = $("input[name='loanBackmoneyFirdayTo']").val();  //首次还款日-结束
	var dicLoanDistinguish = $("select[name='dicLoanDistinguish']").val();  //债权区分
	var dictLoanType = $("select[name='dictLoanType']").val();  //债权来源
	var loanTrusteeFlag = $("select[name='loanTrusteeFlag']").val();  //债权标识
	var applyExpireDayFrom = $("input[name='applyExpireDayFrom']").val();  //出借到期日-起始
	var applyExpireDayTo = $("input[name='applyExpireDayTo']").val();  //出借到期日-结束
	var releaseState = $("select[name='releaseState']").val();  //转出状态
	var releaseTimeFrom = $("input[name='releaseTimeFrom']").val();  //转出日期-起始
	var releaseTimeTo = $("input[name='releaseTimeTo']").val();  //转出日期-结束
	var releaseFlag = $("select[name='releaseFlag']").val();  //转出标识
	var creditValueIdFlag = $("select[name='creditValueIdFlag']").val();  //借款ID
	var customerCertType = $("select[name='customerCertType']").val();  //证件类型
	var tjFlag = $("select[name='tjFlag']").val();  //提前结清标识
	var loanFreezeDayFrom = $("input[name='loanFreezeDayFrom']").val();  //提前结清日期-起始
	var loanFreezeDayTo = $("input[name='loanFreezeDayTo']").val();  //提前结清日期-结束
	var zqState = $("select[name='zqState']").val();  //债权情况
	/*console.log("loanName:"+loanName+",releaseCreditValueFrom:"+releaseCreditValueFrom+",releaseCreditValueTo:"+releaseCreditValueTo
		+",loanMonthsSurplusFrom:"+loanMonthsSurplusFrom+",loanMonthsSurplusTo:"+loanMonthsSurplusTo+",loanBackmoneyDay:"+loanBackmoneyDay
			+ ",loanMonthRate:"+loanMonthRate + ",loanBackmoneyFirdayFrom:"+loanBackmoneyFirdayFrom + ",loanBackmoneyFirdayTo:"+loanBackmoneyFirdayTo
				+ ",dicLoanDistinguish:"+dicLoanDistinguish + ",dictLoanType:"+dictLoanType + ",loanTrusteeFlag:"+loanTrusteeFlag
					+ ",applyExpireDayFrom:"+applyExpireDayFrom + ",applyExpireDayTo:"+applyExpireDayTo  + ",releaseState:"+releaseState
						+ ",releaseTimeFrom:"+releaseTimeFrom + ",releaseTimeTo:"+releaseTimeTo  + ",releaseFlag:"+releaseFlag
							+ ",creditValueIdFlag:"+creditValueIdFlag + ",customerCertType:"+customerCertType);
	*/
	var result = false;
	result = !(loanName == '' && releaseCreditValueFrom == '' && releaseCreditValueTo == ''
		&& loanMonthsSurplusFrom == '' && loanMonthsSurplusTo == '' && loanBackmoneyDay == null
			&& loanMonthRate == '' && loanBackmoneyFirdayFrom == '' && loanBackmoneyFirdayTo == ''
				&& dicLoanDistinguish == null && dictLoanType == null && loanTrusteeFlag == null
					&& applyExpireDayFrom == '' && applyExpireDayTo == '' && releaseState == null
						&& releaseTimeFrom == '' && releaseTimeTo == '' && releaseFlag == null
							&& creditValueIdFlag == null && customerCertType == '' && tjFlag == null
							&& loanFreezeDayFrom == '' && loanFreezeDayTo == '' && zqState == null);
	return result;
}

/**
 * 释放
 */
function releaseCreditor(){
	
	var selectCount = $('[name="releaseId"]:checked').length;
//	if(selectCount == 0){
//		BootstrapDialog.alert("请选择需要释放的债权");
//		return ;
//	}
	
	var existFilter = checkExistFilter();  //检测是否存在条件过滤 
	if(selectCount <=0 && !existFilter){
		BootstrapDialog.alert("请输入查询条件");
		return;
	}
	
	var zeroCount = 0; //剩余期数为0的 选中项 个数
	var zeroMoney = 0; //剩余期数为0的 选中项 总释放金额
	var zeroMoneyL2 = 0; //剩余期数为0的 选中项 总释放金额[先四舍五入后求和]
	var ids = ""; //选中项 id
	var selectMoney = 0; //选中项 金额
	var selectMoneyL2 = 0; //选中项 金额[先四舍五入后求和]
	var existOtherState = false; //是否存在转出状态为 '操作中'或'已操作'的
	
	if(selectCount > 0){
		$('[name="releaseId"]:checked').each(function(){
			var idtext = this.value;
			var idtextSplit = idtext.split("_");
			var _releaseid = idtextSplit[0]; //id
			var _releaseCreditValue = idtextSplit[1];//释放金额
			var _loanMonthsSurplus = idtextSplit[2];//剩余期数
			var _releaseState = idtextSplit[4];//转出状态
			if(_releaseState == '1' || _releaseState == '2'){ //转出状态为 1:操作中 2:已操作
				existOtherState = true;
			}
			if(_loanMonthsSurplus == '0'){
				zeroCount = zeroCount + 1;
				zeroMoney = parseFloat(zeroMoney) + parseFloat(_releaseCreditValue);
				zeroMoneyL2 = parseFloat(zeroMoneyL2) + parseFloat(parseFloat(_releaseCreditValue).toFixed(2));
			}
			if(ids.length > 0){
				ids = ids + ",";
			}
			ids = ids +_releaseid;
			
			selectMoney = parseFloat(selectMoney) + parseFloat(_releaseCreditValue);
			selectMoneyL2 = parseFloat(selectMoneyL2) + parseFloat(parseFloat(_releaseCreditValue).toFixed(2));
		});
	}else{
		var _url = ctx+"/creditor/creditorRelease/checkReleaseCreditor";
		
		_contents_getJsonForSync(
			_url,
			$("#searchForm").serialize(),
			"post",
			function(data){
				if(data!=null && data.result!=null && data.result=='success'){
					existOtherState = data.existOtherState;
					selectCount = data.selectCount;
					selectMoney = data.selectMoney;
					selectMoneyL2 = data.selectMoneyL2;
					zeroCount = data.zeroCount;
					zeroMoney = data.zeroMoney;
					zeroMoneyL2 = data.zeroMoneyL2;
				}else{
					BootstrapDialog.alert(data.result);
				}
			},
			null
		);
	}
	
	if(existOtherState){
		BootstrapDialog.alert("【转出状态】是操作中或已操作的数据不允许释放");
		return;
	}
	
	var dialogText = "";
	if(zeroCount > 0){
		dialogText = "请确认：释放的"+selectCount+"笔债权中有"+zeroCount+"笔"+zeroMoney.toFixed(2)+"元总金额(L2)"+zeroMoneyL2.toFixed(2)+"元释放后无效，是否确认释放?";
	}else{
		dialogText = "请确认：是否释放"+selectCount+"笔债权,对应释放金额"+selectMoney.toFixed(2)+"元总金额(L2)"+selectMoneyL2.toFixed(2)+"元?";
	}
	BootstrapDialog.confirm(dialogText,function(result){
		if(result){
			// 执行提交
	    	var submitUrl = ctx+"/creditor/creditorRelease/releaseCreditor";
	    	var submitData = "";
	    	if(ids != ''){
	    		submitData = {ids : ids};
	    	}else{
	    		submitData = $("#searchForm").serialize();
	    	}
			contents_getJsonForSync(submitUrl,submitData,"post",function(data){
				if(data!=null && data.result!=null && data.result=='success'){
					BootstrapDialog.alert("【释放】操作成功!");
					$("#searchForm").submit();
				}else{
					BootstrapDialog.confirm(data.message,function(result){
						$("#searchForm").submit();
					});
				}
			},null);
		}
	});
}

/**
 * 转出
 */
function transferOut(){
	var selectCount = $('[name="releaseId"]:checked').length;
//	if(selectCount == 0){
//		BootstrapDialog.alert("请选择需要转出的债权");
//		return ;
//	}
	
	var existFilter = checkExistFilter();  //检测是否存在条件过滤 
	if(selectCount <=0 && !existFilter){
		BootstrapDialog.alert("请输入查询条件");
		return;
	}
	
	var nullCount = 0; //借款ID为空的 选中项 个数
	var nullMoney = 0; //借款ID为空的 选中项 总释放金额
	var nullMoneyL2 = 0; //借款ID为空的 选中项 总释放金额[先四舍五入后求和]
	var tjCount = 0; //提前结清债权的 选中项 个数
	var tjMoney = 0; //提前结清债权的 选中项 总释放金额
	var tjMoneyL2 = 0; //提前结清债权的 选中项 总释放金额[先四舍五入后求和]
	var zqMinusCount = 0; //债权情况为负的 选中项 个数
	var zqMinusMoney = 0; //债权情况为负的 选中项 总释放金额
	var zqMinusName = ''; //债权情况为负的 借款人姓名
	var ids = ""; //选中项 id(排除 借款ID为空 后的)
	var selectMoney = 0;// 选中项总金额
	var selectMoneyL2 = 0;// 选中项总金额[先四舍五入后求和]
	var existOtherState = false; //是否存在转出状态不为 '未操作'的
	
	if(selectCount > 0){
		$('[name="releaseId"]:checked').each(function(){
			var idtext = this.value;
			var idtextSplit = idtext.split("_");
			var _releaseid = idtextSplit[0]; //id
			var _releaseCreditValue = idtextSplit[1];//释放金额
			var _loanMonthsSurplus = idtextSplit[2];//剩余期数
			var _loanCode = idtextSplit[3];//借款ID
			var _releaseState = idtextSplit[4];//转出状态
			var _tjFlag = idtextSplit[5];//提前结清标识
			var _zqState = idtextSplit[6];//债权情况
			var _loanName = idtextSplit[7];//借款人姓名
			
			if(_releaseState == '0'){ //转出状态为 0:未操作
				
			}else{
				existOtherState = true;
			}
			
			//债权情况不为空 且 小于0
			if(_zqState != '' && parseFloat(_zqState) < 0){
				zqMinusCount = zqMinusCount + 1;
				zqMinusMoney = parseFloat(zqMinusMoney) + parseFloat(_releaseCreditValue);
				if(zqMinusName.length <= 0){
					zqMinusName = _loanName;
				}else{
					zqMinusName = zqMinusName + ',' + _loanName;
				}
			}
			
			if(_loanCode == ''){ //借款ID为空
				nullCount = nullCount + 1;
				nullMoney = parseFloat(nullMoney) + parseFloat(_releaseCreditValue);
				nullMoneyL2 = parseFloat(nullMoneyL2) + parseFloat(parseFloat(_releaseCreditValue).toFixed(2));
			}else if(_tjFlag == 'TJ'){
				tjCount = tjCount + 1;
				tjMoney = parseFloat(tjMoney) + parseFloat(_releaseCreditValue);
				tjMoneyL2 = parseFloat(tjMoneyL2) + parseFloat(parseFloat(_releaseCreditValue).toFixed(2));
			}else {
				if(ids.length > 0){
					ids = ids + ",";
				}
				ids = ids +_releaseid;
			}
			selectMoney = parseFloat(selectMoney) + parseFloat(_releaseCreditValue);
			selectMoneyL2 = parseFloat(selectMoneyL2) + parseFloat(parseFloat(_releaseCreditValue).toFixed(2));
		});
	}else{
		var _url = ctx+"/creditor/creditorRelease/checkTransferOut";
		_contents_getJsonForSync(
			_url,
			$("#searchForm").serialize(),
			"post",
			function(data){
				if(data!=null && data.result!=null && data.result=='success'){
					existOtherState = data.existOtherState;
					selectCount = data.selectCount;
					selectMoney = data.selectMoney;
					selectMoneyL2 = data.selectMoneyL2;
					nullCount = data.nullCount;
					nullMoney = data.nullMoney;
					nullMoneyL2 = data.nullMoneyL2;
					
					tjCount = data.tjCount;
					tjMoney = data.tjMoney;
					tjMoneyL2 = data.tjMoneyL2;
					
					zqMinusCount = data.zqMinusCount;
					zqMinusMoney = data.zqMinusMoney;	
					zqMinusName = data.zqMinusName;
				}else{
					BootstrapDialog.alert(data.result);
				}
			},
			null
		);
	}
	
	if(existOtherState){
		BootstrapDialog.alert("【转出状态】只有是未操作的数据才可以转出");
		return;
	}
	
	var isContinue = true; //债权情况校验是否通过
	if(zqMinusCount > 0){
		var dt = "此次推送债权"+zqMinusMoney+"元，"+zqMinusCount+"笔，为负，其中借款人"+zqMinusName+"债权为负，是否转出？";
		BootstrapDialog.confirm(dt,function(result){
			if(result){
				isContinue = true;
				if(tjCount > 0){
					BootstrapDialog.alert("此次推送债权中含有提前结清的债权"+tjCount+"笔,对应总金额"+tjMoney.toFixed(2)+"元总金额(L2)"+tjMoneyL2.toFixed(2)+"元.请重新选择数据!");
					return ;
				}
				//弹出选择框
				$(".modal-title").text("接收平台");
				var url, argment, callback;
				url = '/creditor/creditorRelease/showSelectPlatWindow';
				argment = null;
				load_callback = function(arg){};
				close_callback = function(arg){};
				var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
				//自定义出发关闭回调函数的动作
				$('#subClose').off('click').on('click',function(){
					
					var targetPlat = $("input[name='targetPlat']:checked").val();
					if(targetPlat == '' || targetPlat == undefined){
						BootstrapDialog.alert("请选择接收平台");
						return ;
					}
					var targetPlatText = '';
					if(targetPlat == '1'){
						targetPlatText = '金信';
					}else if(targetPlat == '2'){
						targetPlatText = '大金融';
					}
					
					var dialogText = "";
					//借款ID为空的 选中项
					if(nullCount > 0){
						dialogText = "请确认：向 "+targetPlatText+" 转出债权"+selectCount+"笔,对应转出总金额"+selectMoney.toFixed(2)+"元总金额(L2)"+selectMoneyL2.toFixed(2)+"元.借款ID为空的债权"+nullCount+"笔,对应总金额"+nullMoney.toFixed(2)+"元总金额(L2)"+nullMoneyL2.toFixed(2)+"元";
					}else{
						dialogText = "请确认：向 "+targetPlatText+" 转出债权"+selectCount+"笔,对应转出总金额"+selectMoney.toFixed(2)+"元总金额(L2)"+selectMoneyL2.toFixed(2)+"元";
					}
					BootstrapDialog.confirm(dialogText,function(result){
						if(result){
							// 执行提交
					    	var submitUrl = ctx+"/creditor/creditorRelease/transferOut";
					    	var submitData = "";
					    	if(ids != ''){
					    		submitData = {targetPlat : targetPlat,ids : ids};
					    	}else{
					    		submitData = $("#searchForm").serialize();
					    		submitData = submitData + "&targetPlat="+targetPlat;
					    	}
							contents_getJsonForSync(submitUrl,submitData,"post",function(data){
								if(data!=null && data.result!=null && data.result=='success'){
									BootstrapDialog.alert("【转出】操作成功!");
									sub.closeSubWindow();
									$("#searchForm").submit();
								}else{
									BootstrapDialog.alert(data.result);
								}
							},null);
						}
					});
					
				});
			}else{
				isContinue = false;
			}
		});
	}else{
		if(tjCount > 0){
			BootstrapDialog.alert("此次推送债权中含有提前结清的债权"+tjCount+"笔,对应总金额"+tjMoney.toFixed(2)+"元总金额(L2)"+tjMoneyL2.toFixed(2)+"元.请重新选择数据!");
			return ;
		}
		//弹出选择框
		$(".modal-title").text("接收平台");
		var url, argment, callback;
		url = '/creditor/creditorRelease/showSelectPlatWindow';
		argment = null;
		load_callback = function(arg){};
		close_callback = function(arg){};
		var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
		//自定义出发关闭回调函数的动作
		$('#subClose').off('click').on('click',function(){
			
			var targetPlat = $("input[name='targetPlat']:checked").val();
			if(targetPlat == '' || targetPlat == undefined){
				BootstrapDialog.alert("请选择接收平台");
				return ;
			}
			var targetPlatText = '';
			if(targetPlat == '1'){
				targetPlatText = '金信';
			}else if(targetPlat == '2'){
				targetPlatText = '大金融';
			}
			
			var dialogText = "";
			//借款ID为空的 选中项
			if(nullCount > 0){
				dialogText = "请确认：向 "+targetPlatText+" 转出债权"+selectCount+"笔,对应转出总金额"+selectMoney.toFixed(2)+"元总金额(L2)"+selectMoneyL2.toFixed(2)+"元.借款ID为空的债权"+nullCount+"笔,对应总金额"+nullMoney.toFixed(2)+"元总金额(L2)"+nullMoneyL2.toFixed(2)+"元";
			}else{
				dialogText = "请确认：向 "+targetPlatText+" 转出债权"+selectCount+"笔,对应转出总金额"+selectMoney.toFixed(2)+"元总金额(L2)"+selectMoneyL2.toFixed(2)+"元";
			}
			BootstrapDialog.confirm(dialogText,function(result){
				if(result){
					// 执行提交
			    	var submitUrl = ctx+"/creditor/creditorRelease/transferOut";
			    	var submitData = "";
			    	if(ids != ''){
			    		submitData = {targetPlat : targetPlat,ids : ids};
			    	}else{
			    		submitData = $("#searchForm").serialize();
			    		submitData = submitData + "&targetPlat="+targetPlat;
			    	}
					contents_getJsonForSync(submitUrl,submitData,"post",function(data){
						if(data!=null && data.result!=null && data.result=='success'){
							BootstrapDialog.alert("【转出】操作成功!");
							sub.closeSubWindow();
							$("#searchForm").submit();
						}else{
							BootstrapDialog.alert(data.result);
						}
					},null);
				}
			});
			
		});
	}
	
	if(!isContinue){
		return ;
	}
	
	
}

/**
 * 确认转出
 */
function confirmTransferOut(){
	var selectCount = $('[name="releaseId"]:checked').length;
//	if(selectCount == 0){
//		BootstrapDialog.alert("请选择需要确认转出的债权");
//		return ;
//	}
	
	var existFilter = checkExistFilter();  //检测是否存在条件过滤 
	if(selectCount <=0 && !existFilter){
		BootstrapDialog.alert("请输入查询条件");
		return;
	}
	
	var ids = ""; //选中项 id
	var selectMoney = 0;// 选中项总金额
	var selectMoneyL2 = 0;// 选中项总金额[先四舍五入后求和]
	var tjCount = 0; //提前结清债权的 选中项 个数
	var tjMoney = 0; //提前结清债权的 选中项 总释放金额
	var tjMoneyL2 = 0; //提前结清债权的 选中项 总释放金额[先四舍五入后求和]
	var zqMinusCount = 0; //债权情况为负的 选中项 个数
	var zqMinusMoney = 0; //债权情况为负的 选中项 总释放金额
	var zqMinusName = ''; //债权情况为负的 借款人姓名
	var existOtherState = false; //是否存在 转出状态为'未操作'或 '已操作'的
	
	if(selectCount > 0){
		$('[name="releaseId"]:checked').each(function(){
			var idtext = this.value;
			var idtextSplit = idtext.split("_");
			var _releaseid = idtextSplit[0]; //id
			var _releaseCreditValue = idtextSplit[1];//释放金额
			var _loanMonthsSurplus = idtextSplit[2];//剩余期数
			var _loanCode = idtextSplit[3];//借款ID
			var _releaseState = idtextSplit[4];//转出状态
			var _tjFlag = idtextSplit[5];//提前结清标识
			var _zqState = idtextSplit[6];//债权情况
			var _loanName = idtextSplit[7];//借款人姓名
			
			//债权情况不为空 且 小于0
			if(_zqState != '' && parseFloat(_zqState) < 0){
				zqMinusCount = zqMinusCount + 1;
				zqMinusMoney = parseFloat(zqMinusMoney) + parseFloat(_releaseCreditValue);
				if(zqMinusName.length <= 0){
					zqMinusName = _loanName;
				}else{
					zqMinusName = zqMinusName + ',' + _loanName;
				}
			}
			
			if(_releaseState == '0' || _releaseState == '2'){ //转出状态为 0:未操作 2:已操作
				existOtherState = true;
			}
			if(_tjFlag == 'TJ'){
				tjCount = tjCount + 1;
				tjMoney = parseFloat(tjMoney) + parseFloat(_releaseCreditValue);
				tjMoneyL2 = parseFloat(tjMoneyL2) + parseFloat(parseFloat(_releaseCreditValue).toFixed(2));
			}else {
				if(ids.length > 0){
					ids = ids + ",";
				}
				ids = ids +_releaseid;
			}
			selectMoney = parseFloat(selectMoney) + parseFloat(_releaseCreditValue);
			selectMoneyL2 = parseFloat(selectMoneyL2) + parseFloat(parseFloat(_releaseCreditValue).toFixed(2));
			
		});
	}else{
		var _url = ctx+"/creditor/creditorRelease/checkConfirmTransferOut";
		_contents_getJsonForSync(
			_url,
			$("#searchForm").serialize(),
			"post",
			function(data){
				if(data!=null && data.result!=null && data.result=='success'){
					existOtherState = data.existOtherState;
					selectCount = data.selectCount;
					selectMoney = data.selectMoney;
					selectMoneyL2 = data.selectMoneyL2;
					
					tjCount = data.tjCount;
					tjMoney = data.tjMoney;
					tjMoneyL2 = data.tjMoneyL2;
					
					zqMinusCount = data.zqMinusCount;
					zqMinusMoney = data.zqMinusMoney;	
					zqMinusName = data.zqMinusName;
				}else{
					BootstrapDialog.alert(data.result);
				}
			},
			null
		);
	}
	
	if(existOtherState){
		BootstrapDialog.alert("未操作或已操作的数据不能确认转出，请重新选择数据。");
		return ;
	}
	
	
	var isContinue = false; //债权情况校验是否通过
	if(zqMinusCount > 0){
		var dt = "此次推送债权"+zqMinusMoney+"元，"+zqMinusCount+"笔，为负，其中借款人"+zqMinusName+"债权为负，是否确认转出？";
		BootstrapDialog.confirm(dt,function(result){
			if(result){
				isContinue = true;
				if(tjCount > 0){
					BootstrapDialog.alert("此次推送债权中含有提前结清的债权"+tjCount+"笔,对应总金额"+tjMoney.toFixed(2)+"元总金额(L2)"+tjMoneyL2.toFixed(2)+"元.请重新选择数据!");
					return ;
				}
				var dialogText = "请确认：成功转出债权"+selectCount+"笔，对应转出总金额"+selectMoney.toFixed(2)+"元总金额(L2)"+selectMoneyL2.toFixed(2)+"元";
				BootstrapDialog.confirm(dialogText,function(result){
					if(result){
						// 执行提交
				    	var submitUrl = ctx+"/creditor/creditorRelease/confirmTransferOut";
				    	var submitData = "";
				    	if(ids != ''){
				    		submitData = {ids : ids};
				    	}else{
				    		submitData = $("#searchForm").serialize();
				    	}
						contents_getJsonForSync(submitUrl,submitData,"post",function(data){
							if(data!=null && data.result!=null && data.result=='success'){
								BootstrapDialog.alert("【确认转出】操作成功!");
								$("#searchForm").submit();
							}else{
								BootstrapDialog.alert(data.result);
							}
						},null);
					}
				});
			}else{
				isContinue = false;
			}
		});
	}else{
		if(tjCount > 0){
			BootstrapDialog.alert("此次推送债权中含有提前结清的债权"+tjCount+"笔,对应总金额"+tjMoney.toFixed(2)+"元总金额(L2)"+tjMoneyL2.toFixed(2)+"元.请重新选择数据!");
			return ;
		}
		var dialogText = "请确认：成功转出债权"+selectCount+"笔，对应转出总金额"+selectMoney.toFixed(2)+"元总金额(L2)"+selectMoneyL2.toFixed(2)+"元";
		BootstrapDialog.confirm(dialogText,function(result){
			if(result){
				// 执行提交
		    	var submitUrl = ctx+"/creditor/creditorRelease/confirmTransferOut";
		    	var submitData = "";
		    	if(ids != ''){
		    		submitData = {ids : ids};
		    	}else{
		    		submitData = $("#searchForm").serialize();
		    	}
				contents_getJsonForSync(submitUrl,submitData,"post",function(data){
					if(data!=null && data.result!=null && data.result=='success'){
						BootstrapDialog.alert("【确认转出】操作成功!");
						$("#searchForm").submit();
					}else{
						BootstrapDialog.alert(data.result);
					}
				},null);
			}
		});
	}
	
	if(!isContinue){
		return ;
	}
	
	
}

//提前结清
function earlySettlement(){
	var selectCount = $('[name="releaseId"]:checked').length;
//	if(selectCount == 0){
//		BootstrapDialog.alert("请选择需要确认转出的债权");
//		return ;
//	}
	
	var existFilter = checkExistFilter();  //检测是否存在条件过滤 
	if(selectCount <=0 && !existFilter){
		BootstrapDialog.alert("请输入查询条件");
		return;
	}
	
	var ids = ""; //选中项 id
	var selectMoney = 0;// 选中项总金额
	var selectMoneyL2 = 0;// 选中项总金额[先四舍五入后求和]
	
	var existOtherState = false; //是否存在转出状态为 '操作中'或'已操作'的
	var existOtherFlag = false; //是否存在提前结清标识 不为 TJ的 
	if(selectCount > 0){
		$('[name="releaseId"]:checked').each(function(){
			var idtext = this.value;
			var idtextSplit = idtext.split("_");
			var _releaseid = idtextSplit[0]; //id
			var _releaseCreditValue = idtextSplit[1];//释放金额
			var _loanMonthsSurplus = idtextSplit[2];//剩余期数
			var _loanCode = idtextSplit[3];//借款ID
			var _releaseState = idtextSplit[4];//转出状态
			var _tjFlag = idtextSplit[5];//提前结清标识
			
			if(_releaseState == '1' || _releaseState == '2'){ //转出状态为 1:操作中  2:已操作
				existOtherState = true;
			}else if(_tjFlag != 'TJ'){
				existOtherFlag = true;
			}else{
				if(ids.length > 0){
					ids = ids + ",";
				}
				ids = ids +_releaseid;
				selectMoney = parseFloat(selectMoney) + parseFloat(_releaseCreditValue);
				selectMoneyL2 = parseFloat(selectMoneyL2) + parseFloat(parseFloat(_releaseCreditValue).toFixed(2));
			}
			
		});
	}else{
		var _url = ctx+"/creditor/creditorRelease/checkEarlySettlement";
		_contents_getJsonForSync(
			_url,
			$("#searchForm").serialize(),
			"post",
			function(data){
				if(data!=null && data.result!=null && data.result=='success'){
					existOtherState = data.existOtherState;
					selectCount = data.selectCount;
					selectMoney = data.selectMoney;
					selectMoneyL2 = data.selectMoneyL2;
					
					var tjCount = data.tjCount;
					if(tjCount > 0){
						existOtherFlag = true;
					}
				}else{
					BootstrapDialog.alert(data.result);
				}
			},
			null
		);
	}
	
	
	if(existOtherState){
		BootstrapDialog.alert("操作中或已操作的数据不能结清，请重新选择数据。");
		return;
	}
	if(existOtherFlag){
		BootstrapDialog.alert("只有 提前结清标识为 TJ的才能 结清，请重新选择数据。");
		return;
	}
	
	var dialogText = "请确认："+selectCount+"笔，对应转出总金额"+selectMoney.toFixed(2)+"元总金额(L2)"+selectMoneyL2.toFixed(2)+"元, 已结清";
	BootstrapDialog.confirm(dialogText,function(result){
		if(result){
			// 执行提交
	    	var submitUrl = ctx+"/creditor/creditorRelease/earlySettlement";
	    	var submitData = "";
	    	if(ids != ''){
	    		submitData = {ids : ids};
	    	}else{
	    		submitData = $("#searchForm").serialize();
	    	}
			contents_getJsonForSync(submitUrl,submitData,"post",function(data){
				if(data!=null && data.result!=null && data.result=='success'){
					BootstrapDialog.alert("【结清】操作成功!");
					$("#searchForm").submit();
				}else{
					BootstrapDialog.alert(data.result);
				}
			},null);
		}
	});
}

//只保留2位小数，如：2，会在2后面补上00.即2.00 
function toDecimal2(x) { 
  var f = parseFloat(x); 
  if (isNaN(f)) { 
    return false; 
  } 
  var f = Math.round(x*100)/100; 
  var s = f.toString(); 
  var rs = s.indexOf('.'); 
  if (rs < 0) { 
    rs = s.length; 
    s += '.'; 
  } 
  while (s.length <= rs + 2) { 
    s += '0'; 
  } 
  return s; 
} 

/**
 * 同步获取/提交
 * @param url 
 * @param data Object对象
 * @param type get/post
 * @param successCal 成功后执行回调函数
 * @param errorCal 失败后执行回调函数
 * @param params 回调函数需呀为的参数
 * @returns {Boolean}
 */
function _contents_getJsonForSync(url, data, type, successCal,errorCal,params) {
	var unix = new Date().getTime();
    $.ajax({
        type: type,
        url: url,
        dataType: "json",
        data: data,
        async: false,
        timeout: 1800000,
        beforeSend: function(){
        	if(loadingMarkShow.loadingDiv != null && loadingMarkShow.protectDiv != null && $(loadingMarkShow.protectDiv).is(':visible')){
        		unix=null;
        		return;
        	}
        	loadingMarkShow(unix);
        },
        success: function (obj) {
        	loadingMarkHide(unix);
        	successCal(obj,params);        	
        },
        error: function (data, errorThrown, options, error) {
        	loadingMarkHide(unix);
        	if(errorCal != null && typeof errorCal == "function"){
        		errorCal(data.responseText,params);
        	}else{
        		
        	}
        }
    });
    return false;
}
