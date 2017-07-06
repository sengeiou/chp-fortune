$(function(){
	var iframe = getCurrentIframeContents();
	
	/**
	 * 检验合同编号是否合法
	 */
	$("#ContractCode", iframe).blur(function(e){
		val = $(this).val();
		if(val == ""){
			BootstrapDialog.alert("请输入合同编号");
			return;
		}
		if($("#originalContractCode")[0]){
			//对象存在，表示数据来源为重新发起申请
			if(val == $("#originalContractCode").val()){
				//如果合同没变化，则不用校验
				return;
			}
		}
	});
	
	/**
	 * 根据产品选择销售折扣率
	 */
	$("#lend_product",iframe).change(function(){
		val = $(this).val();
		if(val==''){
			$("#salesDiscount").val('');
			return;
		}
		// 设置信和宝类型
		if('87' == val){
			// 选择信和宝
			$("input[id=xinhebaoType]").closest('tr').show();
			$("input[id=xinhebaoType]").attr('required','1');
		}else{
			$("input[id=xinhebaoType]").closest('tr').hide();
			$("input[id=xinhebaoType]").removeAttr('required');
			$("input[id=xinhebaoType]").removeAttr('checked');
		}
		lendDate = $("#lendDate").val();
		//根据产品类型，修改合同版本，年年金 2.4、 其他的为1.7
		var versionText = '';
		if($(this).find('option:selected').text() == '年年金') {  // 年年金 2.4
			versionText = '2.4';
		} else {  //其他的为1.7
			versionText = '1.7';
		}
		var versionVal = '';
		var versionOptions = $('#ContractVesion option:contains("'+ versionText + '")');
		if(versionOptions.length > 1) {
			versionOptions.each(function(index, item) {
				if($(item).text() == versionText) {
					versionVal = $(item).val();
					return false;  //break
				}
			});
		} else {
			versionVal = versionOptions.val();
		}
		$('#ContractVesion').val(versionVal).selectpicker('refresh');
//		// 异步获取销售折扣率以及产品版本
//		contents_getJsonForSync(ctx+"/lend/lendApplyAid/getProductDiscountrateAndVersion", {'productCode':val,'lendDate':lendDate}, "post", function(res){
//			//取销售折扣率
//			$("#salesDiscount").val(res.rate);
//			//产品版本
//			p_version = res.p_version;
//			$("#ContractVesion").empty();
//			
//			p_versionArray = p_version.split(';');
//			$.each(p_versionArray, function(i,item){
//				codeAndLabel = item.split(',');
//				$("#ContractVesion").append("<option value="+codeAndLabel[0]+">"+codeAndLabel[1]+"</option>");
//			});
//			
//			$("#ContractVesion").trigger("change");
//			$("#ContractVesion").attr("disabled", false);
//			$("#ContractVesion").selectpicker('refresh');
//		});
	});
	
	/**
	 * 划扣方式切换事件
	 */
	$("#payType",iframe).change(function(){
		$("#selectLend").hide();  				//【选择出借】按钮
		$("#selectLend4ZZ").hide();		//自转【选择出借】按钮
		$("#selectedLendTable").hide();  	//内部转账table
		$("#selectedLendTable").find("tr:gt(0)").remove();   			//内部转账table 清空
		$('[name="lendApply.deductTypeId"]').parent().show();  	//划扣平台项
		$('[name="lendApply.deductTypeId"]').attr('select_required', '1'); 	//划扣平台项，必填
		
		$('#deductDate').removeAttr("disabled");  	//计划划扣日期、
		$('#lendDate').removeAttr("disabled"); 		//计划出借日期
		$('[data-id="lend_product"]').removeAttr("disabled"); 	
		
		$("#lendMoney").attr("divisible","10000");
		$("#transferMoney").attr("divisible","10000");
		
		val = $(this).val();
		if(val=='1'){
			//划扣
			$("#lendMoney").removeAttr("readonly");
			$("#deductMoney").val($("#lendMoney").val());
			$("#transferMoney").removeAttr("required");
			$("#transferMoney").val(0);
			$(".bankZone").show();
			$(".goldAccount").hide();
			$("#launchFlow").show();
		}else if(val=='2'){   //内部转账
			
			$("#transferMoney").attr("required","required");
			$("#lendMoney").attr("readonly","readonly");
			$("#lendMoney").val('0');
			$("#deductMoney").val('0');
			
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
			setLendDate($('#payType').val());
		}else if(val == '6'){   //成功部分内转
			
			$("#transferMoney").attr("required","required");
			$("#lendMoney").attr("readonly","readonly");
			$("#lendMoney").val('0');
			$("#deductMoney").val('0');
			
			$("#selectLend").show();
			$("#selectedLendTable").show();
			
			$(".bankZone").show();
			$(".goldAccount").hide();
			$("#launchFlow").show();
			
			//可用计划划扣日期、计划出借日期
			$('#deductDate').attr("disabled", false);
			$('#lendDate').attr("disabled",true);
			//隐藏划扣平台
			$('[name="lendApply.deductTypeId"]').val('').selectpicker('refresh');
			$('[name="lendApply.deductTypeId"]').parent().hide();
			$('[name="lendApply.deductTypeId"]').removeAttr('select_required');
			setLendDate($('#payType').val());
		}else if(val=='4'){
			//资金托管
			$("#lendMoney").removeAttr("readonly");
			$("#deductMoney").val($("#lendMoney").val());
			$("#transferMoney").removeAttr("required");
			$("#transferMoney").val(0);
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
			
			$("#lendMoney").attr("divisible","0");
			$("#transferMoney").attr("divisible","0");
			
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
	});
});

/**
 * 检验银行信息
 */
function validateBank(){
	payBanks = $("input[name=paymentBankId]").val();
	receiveBanks = $("input[name=receiveBankId]").val();
	if(! payBanks ||  ! receiveBanks){
		BootstrapDialog.alert('请选择付款银行或回款银行');
		return false;
	}
	
	return true;
}

function formatMoney(){
	lendApply_lendMoney = $("#lendMoney").val();
	lendApply_lendMoney = lendApply_lendMoney.replace(/,/g,'');
	$("#lendMoney").val(lendApply_lendMoney);
	
	lendApply_deductMoney = $("#deductMoney").val();
	lendApply_deductMoney = lendApply_deductMoney.replace(/,/g,'');
	$("#deductMoney").val(lendApply_deductMoney);
	
	lendApply_transferMoney = $("#transferMoney").val();
	lendApply_transferMoney = lendApply_transferMoney.replace(/,/g,'');
	$("#transferMoney").val(lendApply_transferMoney);
}

// js判断时间
function validTime(lendDate,lendDateHidden){
    var arr1 = lendDate.split("-");
    var arr2 = lendDateHidden.split("-");
    var date1=new Date(parseInt(arr1[0]),parseInt(arr1[1])-1,parseInt(arr1[2]),0,0,0); 
    var date2=new Date(parseInt(arr2[0]),parseInt(arr2[1])-1,parseInt(arr2[2]),0,0,0);
    return date1.getTime()-date2.getTime()
}

/**
 * 验证具体支行
 * @param custCode
 */
function checkbranch(src){
	branch = src.value;
	if(branch.indexOf("私人银行")>=0){
		return true;
	}else if(branch.indexOf("银行")>=0){
		BootstrapDialog.alert('具体支行除私人银行外均不可使用‘银行’字段！');
		return false;
	}
}


/**
 * 内转，成功内转的出借日期设定
 * @param custCode
 */
function setLendDate(payType){
	//获取最大的到期日
	var maxExpireDay;
	var maxLendDay;
	if($("#payType").val() == '2'){
		//内部转账
		$("#selectedLendTable").find('tr:gt(0)').find('td:eq(5)').each(function(index,item) {
			var dateItem = $.trim($(item).html());  //取出td标签中的日期
			if(! dateItem) {
				return true;
			}
			var dateItemArray = dateItem.replace("年","-").replace("月","-").replace("日","").split(/-|:| /);
	        var tempDate = new Date(dateItemArray[0], dateItemArray[1] - 1, dateItemArray[2]);
			if(! maxExpireDay) {
				maxExpireDay = tempDate;
			} else {
				maxExpireDay = tempDate > maxExpireDay ? tempDate : maxExpireDay;
			}
		});
		//将得到的到期日，赋值到计划划扣日期，+1天作为计划出借日期
		if(maxExpireDay) {
			var rsObj = calculateLastestDay(maxExpireDay);
			if(rsObj) {
				$('#deductDate').attr('value', rsObj.deductDate.format('yyyy-MM-dd')); 	 	//计划划扣日期
				$('#lendDate').attr('value', rsObj.lendDate.format('yyyy-MM-dd'));    			//计划出借日期
			}
		} else {
			$('#deductDate').attr('value', ''); 	 	//计划划扣日期
			$('#lendDate').attr('value', '');    			//计划出借日期
		}
	}else if($("#payType").val() == '6'){
		//部分成功内转
		$("#selectedLendTable").find('tr:gt(0)').find('td:eq(4)').each(function(index,item) {
			var dateItem = $.trim($(item).html());  //取出td标签中的日期
			if(! dateItem) {
				return true;
			}
			var dateItemArray = dateItem.replace("年","-").replace("月","-").replace("日","").split(/-|:| /);
	        var tempDate = new Date(dateItemArray[0], dateItemArray[1] - 1, dateItemArray[2]);
			if(! maxLendDay) {
				maxLendDay = tempDate;
			} else {
				maxLendDay = tempDate > maxLendDay ? tempDate : maxLendDay;
			}
		});
		//将得到的到期日，赋值到计划划扣日期，+1天作为计划出借日期
		if(maxLendDay) {
			$('#deductDate').attr('value', maxLendDay.format('yyyy-MM-dd')); 	 	//计划划扣日期
			$('#lendDate').attr('value', maxLendDay.format('yyyy-MM-dd'));    			//计划出借日期
		} else {
			$('#deductDate').attr('value', ''); 	 	//计划划扣日期
			$('#lendDate').attr('value', '');    			//计划出借日期
		}
	}
}

/**
 * 计算最近的日期
 * 若到期日是周六、日，则将划扣日设置为周五，计划出借日为到期日+1
 * 返回： {deductDate: , lendDate:  }
 */
function calculateLastestDay(endDay) {
	if(endDay) {
		var oneDayMill = 24 * 60 * 60 * 1000;
		var  day = endDay.getDay();   //星期几 0 - 6  代办 日 - 六
		var subDayTemp = 0;   //差值
		var rsObj = {};
		if(day == 0) { //星期日
			subDayTemp = 2;
		} else if(day == 6) {  //星期六
			subDayTemp = 1;
		}
		if(subDayTemp > 0) {
			rsObj.deductDate =  new Date(endDay.getTime() - subDayTemp * oneDayMill);
		}else{
			rsObj.deductDate=new Date(endDay.getTime());
		}
		rsObj.lendDate = new Date(endDay.getTime() + oneDayMill);
		return rsObj;
	} else {
		return null;
	}
	
}
