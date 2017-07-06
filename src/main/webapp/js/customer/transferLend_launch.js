$(function(){
	var iframe = getCurrentIframeContents();
	fortune.initCity(iframe,'emer_addrProvince','emer_addrCity','emer_addrDistrict');
	var myzone;
	
	// 预览申请书
	$('#btnPreview',iframe).click(function(){
		
		var custCode=$("input[name='customer.custCode']").val();
		var str='customer_code='+custCode+'-format=pdf';
		window.open(ctx+'/common/file/reportServerfilePreview?type=khsq&str='+str,'预览','height=500,width=550,toolbar=yes,resizable=yes,scrollbars=yes')
	});

	/*//附件加载
	contents_getJsonForSync(
			ctx+'/common/file/getAttachmentAjax', 
			{'code':$('[name="customer.custCode"]').val(),'tableId':$('[name="customer.id"]').val(),'tableName':'t_tz_customer'}, 
			'post',
			function(result){
				myzone = DropzoneHandle.initDropZone('#my-awesome-dropzone',result,{});
			},
			function(){
				BootstrapDialog.alert("加载附件发生错误");
			},null);
	*/
	$("#loanSign").change(function(){
		val = $(this).val();
		if(val=='2'){
			//签订委托协议为否时
			$("#loanAgreementType").removeAttr("select_required");
			$("#loanAgreementEdition").removeAttr("select_required");
			$("#agreemrntSignDate").removeAttr("required");
			$("#loanAgreementType").attr("disabled","disabled");
			$("[data-id=loanAgreementType]").attr("disabled","disabled");
			$("[data-id=loanAgreementEdition]").attr("disabled","disabled");
			$("#agreemrntSignDate").attr("disabled","disabled");
		}else{
			$("#loanAgreementType").attr("select_required","1");
			$("#loanAgreementEdition").attr("select_required","1");
			$("#agreemrntSignDate").attr("required",'1');
			$("#loanAgreementType").removeAttr("disabled");
			$("[data-id=loanAgreementType]").removeAttr("disabled");
			$("[data-id=loanAgreementEdition]").removeAttr("disabled");
			$("#agreemrntSignDate").removeAttr("disabled");
		}
	});
	
	if($("#custCertType").val()=='0'){
		
		orc(iframe);
	}
	
	
	// 校验表单
	$('#btnSubmit',iframe).click(function(){
		$form = $(this).closest('form');
		if(!$form.validate({
			
		}).form() ){
			return false;
		}else{			
			birthDay = $("#custBirthday").val();
			birthYear = birthDay.substring(0,4)
			date = new Date();
			curYear = date.getFullYear();
			
			if(curYear-birthYear<18){
				BootstrapDialog.alert("不能对未满18周岁的客户进行开户操作");
				return;
			}
			
			var idcard = /(^\d{15}$|^\d{18}$|^\d{17}(\d|X|x)$)/;
			certType = $("#custCertType").val();
			certNum = $("#custCertNum").val();
			if(certType == '0'){
				
				if(!checkIdCard()) { //身份证号校验
					return false;
				}
				
				//校验身份证生日跟输入的生日是否一致
				var birth = certNum.substring(6, 14);
				var birthInput = $('#custBirthday', iframe).val().replace(/-/g, ''); //  g全局替换
				if(birth != birthInput) {
					BootstrapDialog.alert("选择的出生日期与身份证号码中的与不一致");
					return false;
				}
			}

			emerType = $("#emerType").val();
			emerCertNum = $("#emerCertNum").val();
			if(emerType == '0'){
				if(!(idcard.test(emerCertNum))){
					BootstrapDialog.alert("请输入正确的紧急联系人身份证号码");
					
					return;
				}
			}
			
/*			var emerCertNumbirth = emerCertNum.substring(6, 14);
			var emerCertNumbirthInput = $('#emerBirthday', iframe).val().replace(/-/g, ''); //  g全局替换
			if(emerCertNumbirth!=null && emerCertNumbirth.length()>0){
			if(emerCertNumbirth != emerCertNumbirthInput) {
				BootstrapDialog.alert("选择紧急联系人的出生日期与身份证号码中的与不一致");
				return false;
			}
			}*/
			var f=isNullverifyCode();
			if(f==-1){
					return false;
			}
			if(f==1){
				checkPin('1',
					function(){
						goSubmit();
					},function(){
						BootstrapDialog.alert('手机验证码不正确或超时');
						return false;
					}
				);
			}
			if(f==2){
				checkPin('2',
					function(){
						goSubmit();
					},function(){
						BootstrapDialog.alert('邮箱验证码不正确或超时');
						return false;
					}
				);			
			}
			if(f==3){
				checkPin('1',
					function(){
						checkPin('2',
							function(){
								goSubmit();
							},function(){
								BootstrapDialog.alert('邮箱验证码不正确或超时');
								return false;
							}
						);
					},function(){
						BootstrapDialog.alert('手机验证码不正确或超时');
						return false;
					}
				);
			}			
		}
	});
	
	function goSubmit(){
		if (certType == "0") {  //身份证
			contents_getJsonForSync(
				ctx+"/customer/investment/policeVerify",
				{"custRealname":$("#custName",iframe).val(),
				 "custCertNum" : $("#custCertNum",iframe).val(),
				 "sex" : $("#sex",iframe).val(),
				 "attaTableId":$('[name="customer.id"]').val()
				},
				"post",
				function(data){
					if(data.message!=''){
						BootstrapDialog.alert(data);
						$(this).removeAttr("disabled");
						return;
					}else{
						// 表单提交
						formData = $("#inputForm").serialize();
						contents_getJsonForSync(ctx+"/customer/investment/isExsitOfIdCard", formData, "post", 
								function(res){
									if(res.chp_exsit != null && res.chp_exsit=='true'){
										BootstrapDialog.alert("系统内存在该证件信息，不能用该证件开户");
										return false;
									}
									if(res.exsit == 'true'){
										BootstrapDialog.confirm("存在相同身份证号码客户，确定提交吗？", function(f){
											if(f){
												contents_getJsonForSync(ctx+"/customer/investment/transferLend", formData, "post", function(result){
													if(result.message == 'true'){
														window.location.href = ctx+"/customer/investment/list";
													}else{
														BootstrapDialog.alert("开户失败");
													}
												},null,null);
											}else{
												$(this).removeAttr("disabled");
											}
										});
									}else{
										//$("#inputForm").submit();
										contents_getJsonForSync(ctx+"/customer/investment/transferLend", formData, "post", function(result){
											if(result.message == 'true'){
												window.location.href = ctx+"/customer/investment/list";
											}else{
												BootstrapDialog.alert("开户失败");
											}
										},null,null);
									}
								},
								null,null
						);
					}//else
				});
		}else{
//			$("#inputForm").submit();
			// 表单提交
			formData = $("#inputForm").serialize();
			contents_getJsonForSync(ctx+"/customer/investment/transferLend", formData, "post", function(result){
				if(result.message == 'true'){
					window.location.href = ctx+"/customer/investment/list";
				}else{
					BootstrapDialog.alert("开户失败");
				}
			},null,null);
		}
	}
	
	//身份证校验
	function checkIdCard() {
		var idcard = /(^\d{15}$|^\d{18}$|^\d{17}(\d|X|x)$)/;
		var certType = $("#custCertType").val();
		var certNum = $("#custCertNum").val();
		var flag = true;
		if(certType == '0') {  //身份证类型
			if(!(idcard.test(certNum))){
				BootstrapDialog.alert("请输入正确的身份证号码");
				return;
			}
			
			if($("#emerCertNum").val() == certNum || $("#emerMobilephone").val() == $("#custMobilephone").val()){
				BootstrapDialog.alert("客户身份证号码或移动号码不能与紧急联系人一致");
				return;
			}
			
			//校验正确性，存在性
			contents_getJsonForSync(
					ctx + '/customer/customerAidController/checkCardId', 
					{
						cardId : certNum,
						cardType : certType,
						custCode : $('[name="customer.custCode"]').val()
					}, 
					'post', 
					function(data){
						if(data.result == 'true') {
							if(data.checkRs == '1') {
								BootstrapDialog.alert("请输入正确的身份证号码");
								flag = false;
							} else if (data.checkRs == '01') {
								BootstrapDialog.alert("身份证号码已存在");
								$("#custCertNum").val('');
								flag = false;
							} 
						}
					},
					function() {
						BootstrapDialog.alert("校验身份证时，失败");
						flag == false;
					},null
			);
			
			return flag;
		}
		return flag;
	}

	
	//客户获取生日
	$("#custCertNum",iframe).blur(function(e){
		//身份证校验
		if(!checkIdCard()) {
			return;
		}
	
		if($("#custCertType").val() == '0'){
			//如果选择身份证类型
			idCard = $(this).val();
			birthDay = "";
			if(idCard.length==18){
				birthDay=idCard.substring(6,14);
		    }else if(idCard.length==15){
		    	birthDay=idCard.substring(6,12);
		        birthDay="19"+birthDay;
		    }
			birthDay=birthDay.substring(0,4)+"-"+birthDay.substring(4,6)+"-"+birthDay.substring(6,8);
			$("#custBirthday").val(birthDay);
		}
	});
	
	//紧急联系人获取生日
	$("#emerCertNum",iframe).blur(function(e){
		if($("#emerType").val() == '0'){
			//如果选择身份证类型
			idCard = $(this).val();
			birthDay = "";
			if(idCard.length==18){
				birthDay=idCard.substring(6,14);
		    }else if(idCard.length==15){
		    	birthDay=idCard.substring(6,12);
		        birthDay="19"+birthDay;
		    }
			birthDay=birthDay.substring(0,4)+"-"+birthDay.substring(4,6)+"-"+birthDay.substring(6,8);
			$("#emerBirthday").val(birthDay);
		}
	});
	
	// 点击长期有效，则失效日期要隐藏
	$("#custCertPermanent",iframe).click(function(){
		checked = $(this).attr("checked");
		if(checked == 'checked'){
			$("#custCertFailuredate").val("");
			$("#custCertFailuredate").closest("td").hide();
			$("#custCertFailuredate").removeAttr("required");
		}else{
			$("#custCertFailuredate").closest("td").show();
			$("#custCertFailuredate").attr("required",'1');
		}
	});

	
	/**
	 * 证件类型发生变化时触发事件,当为身分证时填冲按钮
	 */
	$("#custCertType",iframe).change(function(){
		if ($(this).find(':selected').text() == "身份证") {  //身份证
			orc(iframe);
		}else {
		   $("#butSpan", iframe).attr("style", "display:none;");
     	   $("#custCertIssuedate",iframe).val('');
     	   $("#custCertFailuredate",iframe).val('');
		   $("#custRealname",iframe).removeAttr("readonly");
		   $("#custCertNum",iframe).removeAttr("readonly");
		   $("#custCertOrg",iframe).removeAttr("readonly");
		   $("#custBirthday",iframe).attr("onfocus","WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:new Date()})");
	   	   $("#sex",iframe).removeAttr("readonly");
	   	   $("#custCertIssuedate",iframe).attr("onfocus","WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:new Date()})");
	   	   $("#custCertFailuredate",iframe).attr("onfocus","WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:new Date()})");
		}
		if($(this).val() == "4"){//士兵证
			$("#custCertIssuedate").closest("tr").hide();
			$("#custCertIssuedate").removeAttr("required");
			$("#custCertFailuredate").removeAttr("required");
		}else{
			$("#custCertIssuedate").closest("tr").show();
			$("#custCertIssuedate").attr("required",'1');
			$("#custCertFailuredate").attr("required",'1');
		}
		if($(this).val() == "3"){//军官证
			$("#custCertNum").attr("militaryId","1");
		}else{
			$("#custCertNum").removeAttr("militaryId");
		}
	});
});






// 截取字符串
function Datesubstr(date){
	var year = date.substr(0,4);
	var month = date.substr(4,2);
	var d = date.substr(6,2);
	return year+"-"+month+"-"+d;
}
/**
 * orc上传方法
 * @param iframe
 */
function orc(iframe){
	$("#butSpan", iframe).attr("style", "");
	$('#uploadbutton',iframe).off('click').on('click',function(){
		if($("#uploadfile").val() == ""){
			BootstrapDialog.alert("请选择上传文件");
			 return;
		}
		if($("#uploadfile").val()==$("#uploadfileHiddrn").val()){
			 BootstrapDialog.alert("不可上传相同的文件");
			 return;
		}
		$("#uploadfileHiddrn").val($("#uploadfile").val());
		 var formData = new FormData($('#inputForm')[0]);
		 contents_getJsonForFileUpload(
				 this,
				 ctx+'/customer/investment/uploadOcr',
				 formData,
				 function(result){
	            	   if(result.errorCode != null){
	            		   BootstrapDialog.alert(result.errorCode);
	            		   $("#uploadfileHiddrn").val("");
	            		   return;
	            	   }
	            	   BootstrapDialog.alert("上传成功！");
	            	   $("#custRealname",iframe).val(result.name);
	            	   $("#custRealname",iframe).attr("readonly","true");
	            	   $("#custCertNum",iframe).val(result.cardnum);
	            	   $("#custCertNum",iframe).attr("readonly","true");
	            	   $("#custCertOrg",iframe).val(result.office);
	            	   $("#custCertOrg",iframe).attr("readonly","true");
	            	   var birthday = result.cardnum.substring(6, 10)+"-"+result.cardnum.substring(10, 12) +"-"+ result.cardnum.substring(12, 14);
	            	   $("#custBirthday",iframe).val(birthday);
	            	   $("#custBirthday",iframe).attr("onfocus","");
	            	   var sex = result.sex;
	            	   if(sex = '男'){
	            		   $("#sex",iframe).val(0);
	            	   }else{
	            		   $("#sex",iframe).val(1);
	            	   }
	            	   $("#sex",iframe).attr("readonly","true");
	            	   var validdate = result.validdate.split("-");
	            	   $("#custCertIssuedate",iframe).val(Datesubstr(validdate[0]));
	            	   $("#custCertFailuredate",iframe).val( Datesubstr(validdate[1]));
	            	   $("#custCertIssuedate",iframe).attr("onfocus","");
	            	   $("#custCertFailuredate",iframe).attr("onfocus","");
	            	   
	               },null,false
				 );		   
	});
	 
   

}