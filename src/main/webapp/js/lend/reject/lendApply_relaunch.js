$(function(){
	var iframe = getCurrentIframeContents();
	//申请日期
	if($("#applyDate",iframe).val() == ''){
		$("#applyDate",iframe).val(new Date().yyyymmdd());
	}
	var myzone;
	// 初始化上传控件
	if($(".lendApply_relaunch").length>0 || $(".lendApply_detail").length>0){
		//附件加载
		var pageType=$("#pageType").val();   
		var obj={};
		// 非重新发起页
		if("lendApply_relaunch"!=pageType){
			obj={
				hideDrop:true,
				addRemoveLinks:false
			}
		}
		contents_getJsonForSync(
				ctx+'/common/file/getAttachmentAjax', 
				{'code':$('[name="lendApply.applyCode"]').val(),'tableId':$('[name="id"]').val(),'tableName':'t_tz_loan_apply'}, 
				'post',
				function(result){
					myzone = DropzoneHandle.initDropZone('#my-awesome-dropzone',result,obj);
				},
				function(){
					BootstrapDialog.alert("加载附件发生错误");
				},null);
	}else{
		
		if ($('[name="lendApply.applyCode"]').val()=='') {
			myzone = DropzoneHandle.initDropZone('#my-awesome-dropzone',null,{"url":ctx+'/common/file/uploadAjax?customerFile='+$("#customerCode").val()});
		} else {
			contents_getJsonForSync(
					ctx+'/common/file/getAttachmentAjax', 
					{'code':$('[name="lendApply.applyCode"]').val(),'tableId':$('[name="lendId"]').val(),'tableName':'t_tz_loan_apply'}, 
					'post',
					function(result){
						myzone = DropzoneHandle.initDropZone('#my-awesome-dropzone',result,{"url":ctx+'/common/file/uploadAjax?customerFile='+$("#customerCode").val()});
					},
					function(){
						BootstrapDialog.alert("加载附件发生错误");
					},null);
		}
	}
	
	
	//触发付款方式事件
	proccessPayTypeChange();
	
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
	 * 付款方式变更事件处理
	 */
	function proccessPayTypeChange(element) {
		$("#selectLend").hide();  				//【选择出借】按钮
		$("#selectLend4ZZ").hide();		//自转【选择出借】按钮
		$("#selectedLendTable").hide();  	//内部转账table
		$('[name="lendApply.deductTypeId"]').parent().show();  	//划扣平台项
		$('[name="lendApply.deductTypeId"]').attr('select_required', '1'); 	//划扣平台项，必填
		
		$('#deductDate').removeAttr("disabled");  	//计划划扣日期、
		$('#lendDate').removeAttr("disabled"); 		//计划出借日期
		
		$("#payType",iframe).prop("disabled", true).selectpicker('refresh');	//禁止选择付款方式
		
		val =$("#payType",iframe).val();
		if(val=='1'){
			//划扣
			$("#lendMoney").removeAttr("readonly");
			$("#deductMoney").val($("#lendMoney").val());
			$("#transferMoney").removeAttr("required");
			$("#transferMoney").val("");
			$(".bankZone").show();
			$(".goldAccount").hide();
			$("#launchFlow").show();
		}else if(val=='2' || val == '6'){
			//内部转账或者成功部分内转
			$("#lendMoney").attr("readonly","readonly");
			//$("#lendMoney").val('0');
			$("#deductMoney").val('0');
			$("#transferMoney").attr("required","required");
			$("#selectLend").show();
			$("#selectedLendTable").show();
			$(".bankZone").show();
			$(".goldAccount").hide();
			$("#launchFlow").show();
			
			//禁用计划划扣日期、计划出借日期
			$('#deductDate').attr("disabled", false);
			$('#lendDate').attr("disabled",true);
			//隐藏划扣平台
			$('[name="lendApply.deductTypeId"]').val('').selectpicker('refresh');
			$('[name="lendApply.deductTypeId"]').parent().hide();
			$('[name="lendApply.deductTypeId"]').removeAttr('select_required');
			
		}else if(val=='4'){
			//资金托管
			$("#lendMoney").removeAttr("readonly");
			$("#deductMoney").val($("#lendMoney").val());
			$("#transferMoney").removeAttr("required");
			$("#transferMoney").val("");
			$(".bankZone").hide();
			$(".goldAccount").show();
			if($("#launchFlow").attr('lendStatus')=='0'){
				$("#launchFlow").hide();
			}
			
			//隐藏划扣平台，并清空
			$('[name="lendApply.deductTypeId"]').val('').selectpicker('refresh');
			$('[name="lendApply.deductTypeId"]').parent().hide();
			$('[name="lendApply.deductTypeId"]').removeAttr('select_required');
			// 判断是否为金账户
			//contents_getJsonForSync(ctx+"/lend/apply/isGoldAccount",{'customerCode':$("#customerCode").val()},"post",function(res){
				//$("#trusteeshipStatus").val(res.status);
			//},null);
		}else if(val=='7'){
			//自转
			$("#transferMoney").attr("readonly","readonly");
			$("#lendMoney").attr("readonly","readonly");
			$("#deductMoney").attr("readonly","readonly");
			
			$("#selectLend4ZZ").show();
			$("#selectedLendTable").show();
			
			$(".bankZone").show();
			$(".goldAccount").hide();
			$("#launchFlow").show();
			
			$("#generateNo").show();
			$("#generateContractNo").hide();
			
			//禁用计划划扣日期、计划出借日期
			$('#deductDate').attr("disabled",true);
			$('#lendDate').attr("disabled",true);
			$('[data-id="lend_product"]').attr("disabled",true);
			//隐藏划扣平台
			$('[name="lendApply.deductTypeId"]').val('').selectpicker('refresh');
			$('[name="lendApply.deductTypeId"]').parent().hide();
			$('[name="lendApply.deductTypeId"]').removeAttr('select_required');
			setLendDate($('#payType').val());
		}
		if($(".bankZone").is(":hidden")){
			$(".bankZone").find("input[type=radio]:checked").removeAttr("checked");
		}
		if($(".goldAccount").is(":hidden")){
			$(".goldAccount").find("input[type=radio]:checked").removeAttr("checked");
		}
	}
	
	
	/**
	 * 划扣方式切换事件
	 */
	$("#payType",iframe).change(function(){
		proccessPayTypeChange();
		$("#selectedLendTable").find("tr:gt(0)").remove();   			//内部转账table 清空
	});
	
	/**
	 * 选择出借按钮
	 */
	$("#selectLend").click(function(e){
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
        $('#subClose').off('click').on('click',function(){sub.closeSubWindow();
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
        			transferMoneyBlur(e);
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
				 if(data.isExist == 'true') {
					 $("#deductDate").val(data.deductDate);
					 $("#lendDate").val(data.lendDate);
					 
					 $("#lend_product").val(data.productCode).selectpicker('refresh');
					 if("87" == data.productCode){//信和宝
						 $('[data-id="lend_product"] span[class="filter-option pull-left"]').eq(0).html("信和宝");
					 }else if("91" == data.productCode){//信和宝A
						 $('[data-id="lend_product"] span[class="filter-option pull-left"]').eq(0).html("信和宝A");
					 }else if("92" == data.productCode){//信和宝B
						 $('[data-id="lend_product"] span[class="filter-option pull-left"]').eq(0).html("信和宝B");
					 }else if("93" == data.productCode){//信和宝C
						 $('[data-id="lend_product"] span[class="filter-option pull-left"]').eq(0).html("信和宝C");
					 }else if("85" == data.productCode){//年年盈
						 $('[data-id="lend_product"] span[class="filter-option pull-left"]').eq(0).html("年年盈");
					 }else if("89" == data.productCode){//金信盈
						 $('[data-id="lend_product"] span[class="filter-option pull-left"]').eq(0).html("金信盈");
					 }else if("90" == data.productCode){//金信宝
						 $('[data-id="lend_product"] span[class="filter-option pull-left"]').eq(0).html("金信宝");
					 }
					 
					 
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
	
	
function showGenerate(){
	$("#generateNo").hide();
	$("#generateContractNo").show();
}

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
					$("#ContractCode").val(data.contractNumber);
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
					$("#ContractCode").val("");
					alert(data.contractNumber);
				}
			});
		}
		
		
		
	});


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
	 * 页面初始化数据
	 */
	if($("#salesDiscount").val() == ''){
		$("#lend_product",iframe).change();
	}
	
	//绑定修改银行账户信息的按钮
	function bindModifyBank(element) {
		var btnVal =  $(element).parent().parent().find("input[name=modifyBank]").val();
		if('修改' === btnVal) {
			 $(element).parent().parent().find("input[name=modifyBank]").val('保存');
			 $(element).parent().parent().find('#bankDistrictSelect').prop("disabled", false).selectpicker('refresh'); //区县
			 $(element).parent().parent().find('#bankCitySelect').prop("disabled", false).selectpicker('refresh');  //市
			 
			 $(element).parent().parent().find('[name="bankNameId"]').prop("disabled", false).selectpicker('refresh');   //银行类别
			 $(element).parent().parent().find('[name="branchAddress"]').attr("disabled", false);  //支行
			 $(element).parent().parent().find('[name="bankAccountNum"]').attr("disabled", false);  //卡号
		} else if('保存' === btnVal) {
			addCustomerAccount(element);  //修改或添加
		}
	}
	
	/**
	 * 重新发起出借申请流程
	 */
	$("#reLaunchFlow",iframe).click(function(){
		files = myzone.getAcceptedFiles();
		if(files.length==0){
			BootstrapDialog.alert("请先上传文件");
			return;
		}
		var contractNumber = $("#ContractCode").val();
		var procductCode = $("#lend_product").val();
		if(!contractNumber){
			BootstrapDialog.alert("合同编号为空");
			return;
		}else if(contractNumber.toString().substring(0,2) != procductCode && $("#payType").val() != '7') {
			BootstrapDialog.alert("合同编号与产品编号不符。");
			return;
		}
		contents_getJsonForSync(
				ctx+"/cutOff/ajaxCutoffInfo",
				{'applyDeductDay':$("#deductDate").val(),'applyDate':$("#applyDate").val()},
				'post',
				function(result){
					if(result!=""){
						BootstrapDialog.alert(result);
						return; 
					}else{
						if($("#payType").val()=="1"){
							deductPlatCheck($("#reLaunchFlow"));
						}else{
							reLaunchFlow();
						}
					}
				});
	});
	
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
	
	// 点击撤销按钮
	$("#cancelFlow").click(function(){
		BootstrapDialog.confirm("确定要撤销出借吗？", function(f){
			if(f){
				contents_getJsonForSync(
						ctx+"/lend/reject/canCelLendApply",
						{'lendCode':$("#applyCode").val(), 'wobNum':$("[name='wobNum']").val(),'token':$("[name='token']").val()},
						'post',
						function(result){
							BootstrapDialog.alert(result.msg, function(){
								if(result.result=="true"){
									window.location.href = ctx+"/lend/reject/fetchReturnedTaskItems";
									return;
								}
							});
						}
				);
			}
		});
	});
});

/**
 * 重新发起出借申请流程
 */
function reLaunchFlow(){
	// 合同选项验证
	if($('[name="lendApply.check1"]:checked').length==0){
		BootstrapDialog.alert("请选择第五条 甲方回款风险的处置方式");
		return;
	}
	if($('[name="lendApply.check2"]:checked').length==0){
		BootstrapDialog.alert("请选择第九条 甲方的资金出借方式");
		return;
	}
	if($('#readContract:checked').length==0){
		BootstrapDialog.alert("请确认协议所有条款");
		return;
	}

	formatMoney();
	
	lendMoney = $("#lendMoney").val();
	lendMoney = lendMoney.replace(/,/g,'');
	if(lendMoney+0 < 50000){
		BootstrapDialog.alert("出借金额不能小于5万");
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
	if($("#payType").val() == '2' || $("#payType").val() == '6'){
		$("#transferLendId").val(transferCodes.join(';'));
	}
	if(validateBank()){
		if(!$("#inputForm").validate().form()){
			return;
		}
		formSubmit();
		return;
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
	contents_getJsonForSync(
		$("#inputForm").attr('action'),
		$("#inputForm").serialize(),
		'post',
		function(result){
			if(result.message!=null && result.message!=''){
				BootstrapDialog.alert("提交失败："+result.message);
				return; 
			}else{
				BootstrapDialog.alert("再次申请成功",function(){
					loadingMarkShow();
					window.location.href= ctx+"/lend/reject/fetchReturnedTaskItems";
				});
				
			}
	});
}

// 通过计划划扣日期获得出借日期
function onpickedFun(){
	if($("#payType").val()=="1"){
		deductPlatCheck(null);
	}
}
// 出借申请划扣验证
function deductPlatCheck(arg){
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
									reLaunchFlow();
								}
							});
						}else if(time>0){
							BootstrapDialog.confirm('你输入的出借日期大于实际出借日期'+data.lendDate+',可以作为出借日期.你确定将此日期作为计划出借日吗',function(result){
								if(result){
									reLaunchFlow();
								}
							});
						}else {
							reLaunchFlow();
						}
					}
				});
	}
}
