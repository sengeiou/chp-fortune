var minApplyAmount;
$(function(){
	var iframe = getCurrentIframeContents();
	minApplyAmount = $('#minApplyAmount').val();
	if(! minApplyAmount) {
		BootstrapDialog.alert("页面初始化失败，请刷新");
	} else {
		minApplyAmount = parseFloat(minApplyAmount);
	}
	//申请日期
	if($("#applyDate",iframe).val() == ''){
		$("#applyDate",iframe).val(new Date().yyyymmdd());
	}
	
	var myzone;
	// 初始化上传控件
	myzone = DropzoneHandle.initDropZone('#my-awesome-dropzone',null,{"url":ctx+'/common/file/uploadAjax?customerFile='+$("#customerCode").val()});
	
	/**
	 * 绑定事件
	 */
	$(":input[type=text],textarea").not(":disabled").not("[readonly]").each(function(){
		evts = $._data($(this)[0], "events");
		if(evts == undefined){
			$(this).keyup(function(){
				val = $(this).val();
				val = val.replace(/\s/g,'');
				$(this).val(val);
			});
		}
	});
	
	/**
	 * 加载后调用页面初始化，
	 */
	init();
	
	/**
	 * 页面初始化，
	 */
	function init() {
		//隐藏合同版本选项
		$('#ContractVesion').closest('td').hide();
	}
	
	
	// 改变计划出借金额时，修改划扣
	function changeDeductMoney(element) {
		
		val = $(element).val();
		val = val.replace(/[^\d\.]|^\.(0)*/g,'');
		val = val.replace(/^0*([1-9])/,"$1");
		if(val==''){
			val='0';
		}
		$(element).val(val);
		
		var payType = $('#payType').val();
		if(payType && (payType =='1' || payType == '4')) {  //1划扣、4托管
			$("#deductMoney").val(val);
		}
	}
	
	/**
	 * 出借金额和划扣金额保持一致
	 */
	$("#lendMoney",iframe).keyup(function(){
		changeDeductMoney(this);
	});
	
	$("#lendMoney",iframe).change(function(){
		changeDeductMoney(this);
	});
	
	$("#lendMoney",iframe).blur(function(){
		changeDeductMoney(this);
	});
	
	
	
	/**
	 * 选择出借按钮
	 */
	$("#selectLend").click(function(e){
		$('#subClose', iframe).show();
		$('#chooseBankConfirm', iframe).hide();
		$('#chooseBankCancel', iframe).hide();
	
		$(".modal-title",iframe).html("选择出借");
    	// 调用子窗口的参数
    	var url, argment, callback;
    	customerCode = $("#customerCode").val();
    	applyCode = $("#applyCode").val();
    	payType = $("#payType").val();
    	if(applyCode==undefined){
    		applyCode = '';
    	}
    	url = "/lend/apply/selectLend";
    	argment = {'customerCode':customerCode,'applyCode':applyCode,'payType':payType};
    	load_callback = function(arg){
    	};
    	close_callback = function(arg){
    		table = $("#showSelectLendTable");
        	boxs = $(":checkbox:checked",table);
        	if(boxs.length>0){
        		$("#lendMoney").val(0);
    			$("#transferMoney").val(0);
        	}
    	};
        var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
        //自定义出发关闭回调函数的动作
        $('#subClose').off('click').on('click',function(){
        	sub.closeSubWindow();
        	table1 = $("#selectedLendTable");
        	table2 = $("#showSelectLendTable");
        	boxs = $(":checkbox:checked",table2);
        	if(boxs.length>0){
        		table1.find("tr:gt(0)").remove();
        		trs = $(":checkbox:checked",table2).closest("tr");
        		trs.find("td:eq(0)").show();
        		trs.find("td:eq(1)").remove();
        		trs.find("td:eq(1)").remove();
        		trs.find("td").show();
        		trs.find("td").show();
        		table1.append(trs);
        		// 移除操作
        		trs.find("a").click(function(){
        			$(this).closest('tr').remove();
        			calculate();
        		});
        		// 输入框绑定事件
        		trs.find("input[type=text]").blur(function(e){
        			transferMoneyBlur(e);
        		});
        		calculate();
        	}
        });
	});
	
	
	/**
	 * 选择出借按钮
	 */
	$("#selectLend4ZZ").click(function(e){
		$('#subClose', iframe).show();
		$('#chooseBankConfirm', iframe).hide();
		$('#chooseBankCancel', iframe).hide();
	
		$(".modal-title",iframe).html("选择出借");
    	// 调用子窗口的参数
    	var url, argment, callback;
    	customerCode = $("#customerCode").val();
    	url = "/lend/apply/toLend4ZZ";
    	argment = {'customerCode':customerCode};
    	load_callback = function(iframe){
    		$(".LendListBut").click(function(){
    			var _applyCode = $("#_applyCode").val();
    			var _certNum = $("#_certNum").val();
    			if((_applyCode == '' && _certNum != '') || 
    					(_applyCode != '' && _certNum == '')){
    				BootstrapDialog.alert("请同时录入出借编号及证件号码！");
    				return;
    			}
    			
    			if ($("#searchForm",iframe).validate().form()) {
    				contents_getJsonForHtml(
						ctx+"/lend/apply/selectLend4ZZ",
						$("#searchForm",iframe).serialize(),
						"POST",
						function(html){
							$('#listDiv').html(html);
						}
					);
    			}
    		});
    	};
    	close_callback = function(arg){
    		table = $("#showSelectLendTable");
        	boxs = $(":checkbox:checked",table);
        	if(boxs.length>0){
        		$("#lendMoney").val(0);
    			$("#transferMoney").val(0);
        	}
    	};
        var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
        //自定义出发关闭回调函数的动作
        $('#subClose').off('click').on('click',function(){
        	sub.closeSubWindow();
        	table1 = $("#selectedLendTable");
        	table2 = $("#showSelectLendTable");
        	boxs = $(":checkbox:checked",table2);
        	if(boxs.length>0){
        		table1.find("tr:gt(0)").remove();
        		trs = $(":checkbox:checked",table2).closest("tr");
        		trs.find("td:eq(0)").show();
        		trs.find("td:eq(1)").remove();
        		trs.find("td:eq(1)").remove();
        		trs.find("td").show();
        		trs.find("td").show();
        		table1.append(trs);
        		// 移除操作
        		trs.find("a").click(function(){
        			$(this).closest('tr').remove();
        			calculate();
        			removeSelectValue();
        		});
        		// 输入框绑定事件
        		trs.find("input[type=text]").blur(function(e){
        			transferMoneyBlur1(e);
        		});
        		
        		var lendMoney = trs.find("td:eq(4)").text().trim();//选中项的出借金额
        		lendMoney = lendMoney.replace(/,/g,'');
        		trs.find("input[type=text]").val(lendMoney);
        		trs.find("input[type=text]").attr("readonly","readonly");
        		trs.find("input[type=text]").trigger("blur");
        		
        		
        		var lendCode = trs.find("td:eq(2)").text();//选中项的出借编号
        		showSelectValue(lendCode);
        		
        		calculate();
        	}
        });
	});
	
	//自转-移除时 清除回显数据
	function removeSelectValue(){
		 $("#deductDate").val('');
		 $("#lendDate").val('');
		 
		 $("#lend_product").val('').selectpicker('refresh');
		 
		 $("#deductMoney").val('0');
		 $("#lendMoney").val('0');
		 
		 $("#transferMoney").val('0');
		 
		 $("#contractNumber").val('');
		 $("#protocoEdition").val('');
		 $("#ContractVesion").val('');
	}
	
	//根据自转的父出借编号,设置 新出借的值
	function showSelectValue(lendCode){
		$.ajax({
			url : ctx + "/lend/apply/getLoanInfo",
			type : 'post',
			async : false,
			data: {
            	'lendCode' : lendCode
			},
			dataType : 'json',
			success:function(data){
				console.log(data);
				 if(data.isExist == 'true') {
					 $("#deductDate").val(data.deductDate);
					 $("#lendDate").val(data.lendDate);
					 
					 
					 if("87" == data.productCode){//信和宝
						 $("<option value='87'>信和宝</option>").appendTo("#lend_product");
					 }else if("91" == data.productCode){//信和宝A
						 $("<option value='91'>信和宝A</option>").appendTo("#lend_product");
					 }else if("92" == data.productCode){//信和宝B
						 $("<option value='92'>信和宝B</option>").appendTo("#lend_product");
					 }else if("93" == data.productCode){//信和宝C
						 $("<option value='93'>信和宝C</option>").appendTo("#lend_product");
					 }else if("85" == data.productCode){//年年盈
						 $("<option value='85'>年年盈</option>").appendTo("#lend_product");
					 }else if("89" == data.productCode){//金信盈
						 $("<option value='89'>金信盈</option>").appendTo("#lend_product");
					 }else if("90" == data.productCode){//金信宝
						 $("<option value='90'>金信宝</option>").appendTo("#lend_product");
					 }
					 $("#lend_product").val(data.productCode).selectpicker('refresh');
					 
					 
					 
					 $("#deductMoney").val('0');
					 var lendApply_lendMoney = data.lendMoney;
					 lendApply_lendMoney = lendApply_lendMoney.replace(/,/g,'');
					 $("#lendMoney").val(lendApply_lendMoney);
					 
					 var lendApply_transferMoney = data.lendMoney;
					 lendApply_transferMoney = lendApply_transferMoney.replace(/,/g,'');
					 
					 $("#transferMoney").val(lendApply_transferMoney);
					 
					 $("#contractNumber").val(data.contractNumber);
					 $("#protocoEdition").val(data.protocoEdition);
					 $("#ContractVesion").val(data.protocoEdition);
					 
					 
	        	  }
			},
			error : function() {
				BootstrapDialog.alert("");
			}
		});
	}
	
	
	/**
	 * 生成按钮
	 */
	
	/**
	 if(!$("#inputForm").validate().form() || !validateBank()){
			return;
		}
	 */
	/**
	 if(accountId) {
			$.ajax({
				url : ctx + "/lend/lendApplyAid/isBankAccountValidate",
				type : 'post',
				async : false,
				data: {
	            	'accountId' : accountId
				},
				dataType : 'json',
				success:function(data){
					 if(data.isExist == 'true') {
						 isExist = true;
		        	   } else {
		        		   isExist = false;
		        	   }
				},
				error : function() {
					BootstrapDialog.alert("");
					isExist == '';
				}
			});
		}
	 */
	
	
	$('#lend_product').change(function(){ 
		var procductCode = $("#lend_product").val();
		if(procductCode){
			$("#generateNo").hide();
			$("#generateContractNo").show();
			$("#generateContractNo").removeAttr("disabled");
		}else{
			$("#generateNo").show();
			$("#generateContractNo").hide();
		}
	}) 

	
	
	$("#generateContractNo").click(function(e){
		
		var productCode = $("#lend_product  option:selected").val();
		if (productCode == "88") {
			$("#protocoEdition").val("2.4");
			
		}else if (productCode != "88" && productCode) {
			$("#protocoEdition").val("1.7");
		}
		if(productCode) {
			$.ajax({
				url : ctx + "/lend/lendApplyAid/generateContractNumber",
				type : 'post',
				async : false,
				data: {
	            	'productCode' : productCode
				},
				dataType : 'json',
				success:function(data){
					$("#contractNumber").val(data.contractNumber);
					$("#generateContractNo").attr("disabled","disabled");  
					 if(data.contractNumber) {
						 contractNumber = contractNumber;
		        	   } else {
		        		   contractNumber = contractNumber;
		        	   }
				},
				error : function() {
					BootstrapDialog.alert("生成合同编号失败！");
					contractNumber == '';
					$("#contractNumber").val("");
					alert(data.contractNumber);
				}
			});
		}  
	});
	
	
	/**
		 对Date的扩展，将 Date 转化为指定格式的String   
		 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
		 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
		 例子：   
		 (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
		 (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
	 */
	Date.prototype.format = function(fmt) { //
		  var o = {   
		    "M+" : this.getMonth()+1,                 //月份   
		    "d+" : this.getDate(),                    //日   
		    "h+" : this.getHours(),                   //小时   
		    "m+" : this.getMinutes(),                 //分   
		    "s+" : this.getSeconds(),                 //秒   
		    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
		    "S"  : this.getMilliseconds()             //毫秒   
		  };   
		  if(/(y+)/.test(fmt))   
		    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
		  for(var k in o)   
		    if(new RegExp("("+ k +")").test(fmt))   
		  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
		  return fmt;   
	}  
		
	/**
	 * 计算内转金额
	 */
	function calculate(){
		total = 0;
		$("#selectedLendTable").find("td").find("input[type=text]").each(function(ele){
			//total += parseFloat($(this).val());
			total += ($(this).val()*100000)/100000;
		});
		$("#lendMoney").val(total);
		$("#transferMoney").val(total);
		
		setLendDate($('#payType').val());
		
	}
	
	/**
	 * 输入内转金额，失去焦点时出发
	 */
	function transferMoneyBlur(e){
		var lendMoney = parseFloat(($(e.target).closest('tr').find("td:eq(3)").html().trim()).replace(/,/g,''));
		var inputMoney = parseFloat($(e.target).val());
		var actualBackMoney = parseFloat(($(e.target).parent().prev().prev().html().trim()).replace(/,/g,''));
		var validateMoney = parseFloat(($(e.target).parent().prev().html().trim()).replace(/,/g,''));
		
		if(inputMoney%10000 != 0){
			BootstrapDialog.alert("内转金额必须是一万元整数倍数值");
			$(e.target).val(0);
			inputMoney = 0;
		}
		
		if(inputMoney != '' && (inputMoney > validateMoney)){
			BootstrapDialog.alert("内转金额不能大于可内转金额");
			$(e.target).val(0);
			$(e.target).parent().next().html(validateMoney);
		}else{
			$(e.target).parent().next().html((actualBackMoney * 100000 - inputMoney * 100000) / 100000);
		}
		calculate();
	}
	
	/**
	 * 输入内转金额，失去焦点时出发
	 */
	function transferMoneyBlur1(e){
		var lendMoney = parseFloat(($(e.target).closest('tr').find("td:eq(3)").html().trim()).replace(/,/g,''));
		var inputMoney = parseFloat($(e.target).val());
		var actualBackMoney = parseFloat(($(e.target).parent().prev().prev().html().trim()).replace(/,/g,''));
		var validateMoney = parseFloat(($(e.target).parent().prev().html().trim()).replace(/,/g,''));
		
		if(inputMoney != '' && (inputMoney > validateMoney)){
			BootstrapDialog.alert("内转金额不能大于可内转金额");
			$(e.target).val(0);
			$(e.target).parent().next().html(validateMoney);
		}else{
			$(e.target).parent().next().html((actualBackMoney * 100000 - inputMoney * 100000) / 100000);
		}
		calculate();
	}
	
	/**
	 * 输入内转金额，失去焦点时出发
	 */
	$("#selectedLendTable").find("input[type=text]").blur(function(e){
		transferMoneyBlur(e);
	});
	
	/**
	 * 点击移除
	 */
	$("#selectedLendTable").find("a").click(function(e){
		$(e.target).closest("tr").remove();
		calculate();
	});

	/**
	 * 发起出借申请流程
	 */
	$("#launchFlow",iframe).click(function(){
		files = myzone.getAcceptedFiles();
		trusteeshipStatus = $("#trusteeshipStatus").attr("trusteeshipStatus");
		if(files.length==0){
			BootstrapDialog.alert("请先上传文件");
			return;
		}

		if(!$("#inputForm").validate().form()){
			return;
		}
		var contractNumber = $("#contractNumber").val();
		var procductCode = $("#lend_product").val();
		if(!contractNumber){
			BootstrapDialog.alert("合同编号为空");
			return;
		}else if(contractNumber.toString().substring(0,2) != procductCode && $("#payType").val() != '7') {
			BootstrapDialog.alert("合同编号与产品编号不符。");
			return;
		}
		
		// 合同选项验证
		if($('[name="lendApply.check1"]:checked').length==0){
			BootstrapDialog.alert("请选择第五条 甲方回款风险的处置方式");
			return;
		}
		if($('[name="lendApply.check2"]:checked').length==0){
			BootstrapDialog.alert("请选择第九条 甲方的资金出借方式");
			return;
		}
		if($('.ido:checked').length==0){
			BootstrapDialog.alert("请确认协议所有条款");
			return;
		}
		var payBankId;
		var receiveBankId;
		var curBankDivObj;
		if($("#payType").val() == '4') {  //资金托管，取金账户信息
			curBankDivObj = $('#goldBankAccountTable');
			payBankId = $('#goldBankAccountTable').find("input[name='paymentBankId']").val();
			receiveBankId = $('#goldBankAccountTable').find("input[name='receiveBankId']").val();
		} else {  //非资金托管，取普通银行卡信息
			curBankDivObj = $('#chooseBankAccount');
			payBankId = $('#chooseBankAccount').find("input[name='paymentBankId']").val();
			receiveBankId = $('#chooseBankAccount').find("input[name='receiveBankId']").val();
		}
		
		if(payBankId == '' || payBankId == undefined || receiveBankId == '' || receiveBankId == undefined){
			BootstrapDialog.alert("请选择银行信息");
			return;
		}
		
		if(($("#payType").val() != '4') && ! isBankAccountValidate()) {  //检验选择的卡是否存在
			BootstrapDialog.alert("选择的银行不存在");
			return;
		}	
	
		// 资金托管且未开户
		if(!($("#payType").val() == '4' && !($("#trusteeshipStatus").attr('trusteeshipStatus') == '2')) ){
			// 截单处理
			contents_getJsonForSync(
					ctx+"/cutOff/ajaxCutoffInfo",
					{'applyDeductDay':$("#deductDate").val()},
					'post',
					function(result){
						if(result!=""){
							BootstrapDialog.alert(result);
							return; 
						}else{
							if($("#payType").val()=="1"){
								deductPlatCheck($("#launchFlow"));
							}else{
								launchFlow();
							}
						}
					});
		}else{
			launchFlow();
		}
	});
	
	//检查卡的有效性
	function isBankAccountValidate() {
		var tr 
		if($("#payType").val() == '4') {  //资金托管，取金账户信息
			tr = $('#goldBankAccountTable').find("tr:eq(0)");
		} else {
			tr = $('#chooseBankAccount').find("tr:eq(0)");
		}
		
		var accountId = tr.find('[name=paymentBankId]').val();
		
		var isExist = false;
		if(accountId) {
			$.ajax({
				url : ctx + "/lend/lendApplyAid/isBankAccountValidate",
				type : 'post',
				async : false,
				data: {
	            	'accountId' : accountId
				},
				dataType : 'json',
				success:function(data){
					 if(data.isExist == 'true') {
						 isExist = true;
		        	   } else {
		        		   isExist = false;
		        	   }
				},
				error : function() {
					BootstrapDialog.alert("获取卡号验证信息失败");
					isExist == '';
				}
			});
		}
		return isExist;
	}
	
	// 通过划扣平台和划扣银行获得实际的计划出借日
	$("select[name='lendApply.deductTypeId']",iframe).change(function(){
		if($("#payType").val()=="1"){
			deductPlatCheck(null);
		}
	});
	// 通过出借金额获得出借日期
	autocompleteSearch($('#lendMoney'),
		//绑定数据源
		function(request, response, elem){
			if($("#payType").val()=="1"){
				deductPlatCheck(null);
			
			}
		},
		function(event, ui, elem){}
	); 
});

/**
 * 发起出借申请流程
 */
function launchFlow(){
	formatMoney();
	
	var lendMoney = $("#lendMoney").val();
	if(!lendMoney) {
		BootstrapDialog.alert("输入计划出借金额");
		return
	}
	
	if(parseInt(lendMoney) < minApplyAmount){   //判断最小出借金额
		BootstrapDialog.alert("出借金额不能小于" + minApplyAmount);
		return;
	}
	
	flag = true;
	code = null;
	tranferMoney = null;
	realBackMoney = null;
	backType = null;
	transferCodes = new Array();
	
	$("#selectedLendTable").find("td").find("input[type=text]").each(function(){
		code = $(this).closest('tr').find('td:eq(2)').html().trim();
		tranferMoney = $(this).val();
		realBackMoney = $(this).parent().prev().prev().html().trim().replace(/,/g,'');
		backType = $(this).closest('tr').find('td:eq(14)').attr('backType');
		
		transferCodes.push(code + "," + tranferMoney + "," + realBackMoney + "," + backType);
		if(this.value+0<=0 || this.value=='' || realBackMoney == '' || realBackMoney == undefined){
			flag = false;
		}
	});
	if(!flag){
		BootstrapDialog.alert("内转金额不能小于等于0或者需要确定被内转出借实际回款金额再提交");
		return;
	}
	if($("#payType").val() == '2' || $("#payType").val() == '6' || $("#payType").val() == '7'){
		$("#transferLendId").val(transferCodes.join(';'));
	}
	
	if(validateBank()){
		
		
		if(!$("#inputForm").validate().form()){
			return;
		}
				// 检查金账户开户状态
				trusteeshipStatus = $("#trusteeshipStatus").attr("trusteeshipStatus");
				applyPayType = $("#payType").val();
				if(applyPayType == '4'){
					// 付款方式：资金托管
					if(trusteeshipStatus == '0'){
						autoOpenAccount = $("#autoOpenAccount").val();
						if(autoOpenAccount == 'true'){
							// 自动开户
							BootstrapDialog.confirm("当前是自动开户状态，是否需要金账户开户", function(f){
								if(f){
									contents_getJsonForSync(
											ctx+"/lend/apply/autoOpenAccount",
											{'custCode':$("#customerCode").val()},
											'post',
											function(result){
												if(result.message!=''&&result.message.length>0){
													//自动开户不成功
													BootstrapDialog.confirm(result.message + "，是否需要手动开户",function(f){
														if(f){
														
//																$("#inputForm").submit();
																formSubmit();
																return;
															
														}
													});
												}else{
													//自动开户成功
													BootstrapDialog.confirm("金账户开户成功，是否继续提交出借申请", function(f){
														if(f){
															
																formSubmit();
																return;
															
														}
													});
												}
											}
									);
								}
							});
						}else{
							// 手动开户
							// 托管状态：未开户
							BootstrapDialog.confirm('确定提交金账户开户申请吗',function(flag){
								if(flag){
									
										formSubmit();
										return;
									}
								
							});
						}
					}else if(trusteeshipStatus == '1'){
						// 金账户申请中
						BootstrapDialog.alert("金账户申请中，不能做付款方式为【资金托管】的出借申请");
						return;
					}else if(trusteeshipStatus == '3'){
						// 金账户开户失败
//						$("#inputForm").submit();
						formSubmit();
						return;
						//BootstrapDialog.alert("金账户开户失败，不能做付款方式为【资金托管】的出借申请");
					}else if(trusteeshipStatus == '4'){
						// 金账户销户中
						BootstrapDialog.alert("金账户销户中，不能做付款方式为【资金托管】的出借申请");
						return;
					}else if(trusteeshipStatus == '5'){
						// 金账户已销户
						BootstrapDialog.alert("原金账户已销户，不能做付款方式为【资金托管】的出借申请，请做金账户变更在申请出借");
						return;
					}else if(trusteeshipStatus == '2'){
						
//							$("#inputForm").submit();
							formSubmit();
						}
					}
				else{
					
//						$("#inputForm").submit();
						formSubmit();
						return;
			}
	}
}

function formSubmit() {
	//处理选择的银行账户
	var moveBankDivObj;
	if($("#payType").val() == '4') {  //资金托管，将普通银行信息移出form
		$('#chooseBankAccount').empty();
	} else {  //非资金托管，将金账户信息移出form
		 $('#goldBankAccountTable').empty();
	}
	//提交之前，去掉“计划划扣日期”、“计划出借日期的”的disabled属性
	if($("#payType").val() == '2' || $("#payType").val() == '6' || $("#payType").val() == '7') {  //内部转账、成功部分内转
		$('#deductDate').removeAttr('disabled');
		$('#lendDate').removeAttr('disabled');
		$('#transferMoney').attr('disabled', true);
	}
	loadingMarkShow();
	$("#inputForm").submit();
}

// 通过计划划扣日期获得出借日期
function onpickedFun(){
	if($("#payType").val()=="1"){
		deductPlatCheck(null);
	}
}
// 出借申请划扣验证
function deductPlatCheck(arg){
	
	var curBankDivObj;
	if($("#payType").val() == '4') {  //资金托管，取金账户信息
		curBankDivObj = $('#goldBankAccountTable');
	} else {  //非资金托管，取普通银行卡信息
		curBankDivObj = $('#chooseBankAccount');
		var provinceCode = curBankDivObj.find("[name='bankProvince']").val();
		var platId = $("select[name='lendApply.deductTypeId']").val();
		var bankId = curBankDivObj.find("[name='bankNameId']") .val();
		var deductDate = $("#deductDate").val();
		var lendMoney = $("#lendMoney").val();
		if(deductDate!="" && lendMoney!=0 && platId!="" && bankId!="" && bankId!=undefined && provinceCode!=undefined){
			contents_getJsonForSync(
					ctx+"/lend/lendApplyAid/ajaxGetlendDate",
					{"platId":platId,"provinceCode":provinceCode,"bankId":bankId,"deductDate":deductDate,"lendMoney":lendMoney},
					"post",
					function(data){
						if(data.exception!=""){
							$("#lendDate").val("");
							$("select[name='lendApply.deductTypeId']").selectpicker('deselectAll');
							$("#lendDateHidden").val("");
							BootstrapDialog.alert(data.exception);
							return false;
						}
						if(arg==null){
							$("#lendDate").val(data.lendDate);
						}else{
							$("#applyDeductDays").val(data.applyDeductDays);
							var time = validTime($("#lendDate").val(),data.lendDate);
							if(time<0){
								BootstrapDialog.confirm('你输入的出借日期小于实际出借日期'+data.lendDate,function(result){
									if(result){
										launchFlow();
									}
								});
							}else if(time>0){
								BootstrapDialog.confirm('你输入的出借日期大于实际出借日期'+data.lendDate+',可以作为出借日期.你确定将此日期作为计划出借日吗',function(result){
									if(result){
										launchFlow();
									}
								});
							}else {
								launchFlow();
							}
						}
					});
		}
	}
	
}
