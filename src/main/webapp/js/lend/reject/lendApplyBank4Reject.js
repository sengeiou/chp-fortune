/**
 *出借申请银行卡 
 */

 $(function() {
	 var iframe = getCurrentIframeContents();
	 //绑定选择银行按钮
	 $('#openSelectBankDialog').click(function() {
		 $("#bankProvinceHidden").val($('input[name="bankProvince"]').val());
		 $("#bankNameIdHidden").val($('input[name="bankNameId"]').val());
		 var url = '/lend/lendApplyAid/getBankSelectPage';
			var argment = {};
			argment.customerCode = $('#customerCode').val();
			$(".modal-title",iframe).html("选择银行");
			$('#subClose', iframe).hide();
			$('#chooseBankConfirm', iframe).show();
			$('#chooseBankCancel', iframe).show();
			
			
			
			var load_callback = function() {
				$('#chooseBankConfirm', iframe).unbind("click");  //避免多次绑定，先解绑
				//带回确认的银行
				$('#chooseBankConfirm', iframe).click(function() {
					checkConfirm();
					sub.closeSubWindow();
					if($("#bankProvinceHidden").val() != $('input[name="bankProvince"]').val() || $("#bankNameIdHidden").val() != $('input[name="bankNameId"]').val()){
						$("#bankProvinceHidden").val($('input[name="bankProvince"]').val());
						$("#bankNameIdHidden").val($('input[name="bankNameId"]').val());
						deductPlatCheck(null);
					}
				});
				
				//取消按钮，关闭对话框
				$('#chooseBankCancel', iframe).click(function() {
					sub.closeSubWindow();
				});
				
				//添加银行
				$('#addBankInWindow', iframe).click(function() {
					addBankAccount(); 
				});
				//绑定保存事件
				$("input[name=modifyBank]").click(function(e){
					bindModifyBank(this);
				});
				
				//绑定删除
				$("input[name=deleteBank]").click(function(e){
					deleteBankAccount(this);
				});
				
				
				// 省市区联动绑定
				fortune.initCity(iframe,'bankProvinceSelect','bankCitySelect','bankDistrictSelect');
				fortune.fyInitCity(iframe,'fy_bankProvinceSelect','fy_bankCitySelect');
				
			};
			var close_callback = function(){};
			
			var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
			$('#subClose').off('click').on('click',function(){  sub.closeSubWindow();});

			
			//带回
			function checkConfirm() {
				var checkObj = $('[name="selectBanckId"]:checked');
				if(!checkObj || checkObj.length == 0) {
					BootstrapDialog.alert("请选择带回项");
					return false;
				}
				var checkRow = checkObj.closest('tr');
				//赋值
				var trObj = checkRow;
				
				var str = '<tr>' +
				                	'<input type="hidden" name="paymentBankId" value="' + checkObj.val() + '" />' +
									'<input type="hidden" name="receiveBankId" value="' + checkObj.val() + '" />' +
									'<input type="hidden" name="bankProvince" value="' + $(trObj).find('[name=bankProvince]').val() + '" />' +
									'<input type="hidden" name="bankNameId" value="' + $(trObj).find('[name=bankNameId]').val() + '" />' +
								'<td>' +
									'<input type="hidden" name="bankNameId" value="' + $(trObj).find('[name=bankNameId]').val() + '" />' +
									$(trObj).find('[name=bankNameId]').find(':selected').text() +  ' ' + 
									$(trObj).find('[name=branchAddress]').val() + 
								'</td>' +
								'<td>' +
									$(trObj).find('[name=bankProvince]').find(':selected').text() +  ' ' + 
									($(trObj).find('[name=bankCity]').val() ? $(trObj).find('[name=bankCity]').find(':selected').text() : '') +  ' ' + 
									($(trObj).find('[name=bankDistrict]').val() ? $(trObj).find('[name=bankDistrict]').find(':selected').text() : '') +  ' ' + 
								'</td>' +
								'<td>' +
									$(trObj).find('[name=bankCard_passbook]').find(':selected').text() +  ' ' + 
								'</td>' +
								'<td>' +
									$(trObj).find('[name=bankAccountName]').val() + 
								'</td>' +
								'<td>' +
									$(trObj).find('[name=bankAccountNum]').val() + 
								'</td>' +
							'</tr>';
				$("#chooseBankAccount").empty().append(str);
			}
			
			//新增银行
			function addBankAccount() {
				$tr = $("#template").clone(true).removeAttr('id').show();
				$tr.find('select').selectpicker();
				$("#bankTBodyInWindow").prepend($tr);
				$tr.find("input[name=modifyBank]").val('保存').unbind();
				
				$tr.find("input[name=modifyBank]").click(function(e){
					bindModifyBank(this);
				});
			}
			
			//删除
			function deleteBankAccount(element) {
				param = {};
		    	bankParam = {};
		    	var $trObj = $(element).closest('tr');
		    	bankParam.id = $trObj.find("[name=paymentBankId]").val();
		    	// id为空直接删除
		    	if(bankParam.id == '' || 'undefined' == bankParam.id){
		    		$trObj.remove();
		    		return;
		    	}else{
		    		contents_getJsonForSync(ctx + "/lend/lendApplyAid/checkBankUsed",{'bankId':bankParam.id},"post",function(res){
		    			if(res.result=='false'){
		    				BootstrapDialog.alert(res.message);
		    				return;
		    			}else{
		    				BootstrapDialog.confirm('确定删除吗', function(result){
		    		            if(result){
		    		            	bankParam.customerCode = $("#customerCode").val();
		    		            	param.account = bankParam;
		    		            	param.operating = "delete";
		    		            	param = JSON.stringify(param);
		    		            	
		    		            	contents_getJsonForStringify(ctx + "/lend/lendApplyAid/bank",param,"post",function(){
		    		            		$trObj.remove();
		    		            		BootstrapDialog.alert('删除成功');
		    		            	});
		    		            }else{
		    		            }
		    		            return;
		    		        });
		    			}
		    		},null);
		    	}
			}
			
			//绑定修改银行账户信息的按钮
			function bindModifyBank(element) {
				var btnVal =  $(element).parent().parent().find("input[name=modifyBank]").val();
				if('修改' === btnVal) {
					 $(element).parent().parent().find("input[name=modifyBank]").val('保存');
					 $(element).parent().parent().find('#bankProvinceSelect').prop("disabled", false).selectpicker('refresh');  //省
					 $(element).parent().parent().find('#bankDistrictSelect').prop("disabled", false).selectpicker('refresh');   //市
					 $(element).parent().parent().find('#bankCitySelect').prop("disabled", false).selectpicker('refresh');	//区县
					 $(element).parent().parent().find('[name="bankNameId"]').prop("disabled", false).selectpicker('refresh');   //银行类别
					 $(element).parent().parent().find('[name="branchAddress"]').attr("disabled", false);  //支行
					 $(element).parent().parent().find('[name="bankAccountNum"]').attr("disabled", false);  //卡号
					 
				} else if('保存' === btnVal) {
					addCustomerAccount(element);  //修改或添加
					
				}
			}
			
			//添加客户银行卡（含修改）
			function addCustomerAccount(element) {
				var $trObj = $(element).closest('tr');
				var cardNo = $trObj.find('[name="bankAccountNum"]').val();
				var custCode = $('#customerCode').val();
				if(! cardNo) {
					BootstrapDialog.alert("请输入卡号");
					return false;
				}
				///检查银行卡是否已存在  
				var accountId =  $trObj.find("[name=paymentBankId]").val();
				if(checkCardNoExist(cardNo, custCode, accountId) == true) {
					BootstrapDialog.alert("输入的卡号已存在，无法保存");
					return;
				}
				
				bankOperation(element,function(res){
					if(res.result == 'true'){
						BootstrapDialog.alert('保存成功');
						$trObj.find("[name=paymentBankId]").val(res.id);
						$trObj.find("[name=receiveBankId]").val(res.id);
						$trObj.find("[name=selectBanckId]").val(res.id);
						
						if($("#payType").val() != '4'){
							// 如果不是资金托管
							//$(element).parent().parent().find("[name=saveBank]").hide();
						}
						
						$trObj.find("input").filter(function(index){return $(this).attr("type")=='text'}).attr("disabled",true);
						$trObj.find("select").each(function(){
							$(this).prop("disabled",true);
							$(this).selectpicker('refresh');
						});
						
						$trObj.find("input[name=modifyBank]").val('修改'); //将银行信息列表后的“保存”——> “修改”
					}
				}, null);
			}
		 
			/**
			 * 对银行增删改
			 */
			function bankOperation(element, successCall, errorCall){
				bankParam = wrapParam(element);
				if(!validateParam(bankParam)){
					return;
				}
				bankParam.customerCode = $("#customerCode").val();
				param = {};
				param.account = bankParam;
				
				param.applyCode = $("#applyCode").val();
				
				if(bankParam.id) {
					param.operating = "update";
				} else {
					param.operating = "add";
				}
				
				if($("#payType").val() == '4'){
					// 如果是资金托管
					param.trusteeFlag = 'true';
				}else{
					param.trusteeFlag = 'false';
				}
				
				param = JSON.stringify(param);
				
				contents_getJsonForStringify(ctx + "/lend/lendApplyAid/bank",param,"post",successCall,errorCall);
				
			}
			
			/**
			 * 检验银行输入参数
			 */
			function validateParam(param){
				if(param.accountBankId == '' || param.accountBankId == null ){
					BootstrapDialog.alert("请选择银行");
					return false;
				}
				if(param.accountBranch == '' || param.accountBranch == null ){
					BootstrapDialog.alert("请输入支行地址");
					return false;
				}
				if(param.accountAddrProvince == '' || param.accountAddrProvince == '-1' || param.accountAddrProvince == null ){
					BootstrapDialog.alert("请选择省份");
					return false;
				}
				if(!(/[\u4e00-\u9fa5]+(?!.[1-9]\d)$/.test(param.accountBranch) ) ){
					BootstrapDialog.alert("具体支行只能为中文或者中文+数字，最后一位不能为数字");
					return false;
				}
				if(param.accountAddrCity == '' || param.accountAddrCity == '-1' || param.accountAddrCity == null ){
					BootstrapDialog.alert("请选择城市");
					return false;
				}
				// 判断付款方式，如果是资金托管就不需要校验市区是否选择
				if($("#payType").val() != '4'){
					if(param.accountAddrDistrict == '' || param.accountAddrDistrict == '-1' || param.accountAddrDistrict == null ){
						BootstrapDialog.alert("请选择区");
						return false;
					}
				}
				if(param.accountNo == '' || param.accountNo == null ){
					BootstrapDialog.alert("请输入银行账号");
					return false;
				}
				if(!(/^\d{16,19}$/.test(param.accountNo))){
					BootstrapDialog.alert("银行账号必须规范（16到19位数字）");
					return false;
				}
				return true;
			}
	
			/**
			 * 封装银行参数
			 */ 
			function wrapParam(element){
				id = $(element).parent().parent().find("[name=paymentBankId]").val();
				bankNameId = $(element).parent().parent().find("[name=bankNameId]").val();
				branchAddress = $(element).parent().parent().find("[name=branchAddress]").val();
				bankProvince = $(element).parent().parent().find("[name=bankProvince]").val();
				bankCity = $(element).parent().parent().find("[name=bankCity]").val();
				bankDistrict = $(element).parent().parent().find("[name=bankDistrict]").val();
				bankCard_passbook = $(element).parent().parent().find("[name=bankCard_passbook]").val();
				bankAccountName = $(element).parent().parent().find("[name=bankAccountName]").val();
				bankAccountNum = $(element).parent().parent().find("[name=bankAccountNum]").val();
				return {
					id:id,
					accountBankId:bankNameId,
					accountBranch:branchAddress,
					accountAddrProvince:bankProvince,
					accountAddrCity:bankCity,
					accountAddrDistrict:bankDistrict,
					accountCardOrBooklet:bankCard_passbook,
					accountName:bankAccountName,
					accountNo:bankAccountNum
				}
			}
			
			// 检查银行卡是否已存在
			function checkCardNoExist(cardNo, custCode, accountId) {
				var isTrustAccountAdd = "0"
				if($("#payType").val() == '4'){ // 如果是资金托管
					isTrustAccountAdd = '1';
				}
				var isExist = false;
				$.ajax({
					url : ctx + "/lend/lendApplyAid/checkExistBankCard",
					type : 'post',
					async : false,
					data: {
						'cardNo' : cardNo, 
		            	'custCode' : custCode,
		            	'accountId' : accountId,
		            	'isTrustAccountAdd' : isTrustAccountAdd
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
				
				return isExist;
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
	 });
	 // ==========================================================
	 
	  //----------------------------------绑定金账户管理按钮 ----------------------------------------------
	 $('#openGoldBankManageDialog').click(function() {
		 var url = '/lend/lendApplyAid/getGoldAccountManagePage';
			var argment = {};
			argment.customerCode = $('#customerCode').val();
			$(".modal-title",iframe).html("金账户管理");
			$('#subClose', iframe).hide();
			$('#chooseBankConfirm', iframe).show();
			$('#chooseBankCancel', iframe).show();
			
			var load_callback = function() {
				$('#chooseBankConfirm', iframe).unbind("click");  //避免多次绑定，先解绑
				//带回确认的银行
				$('#chooseBankConfirm', iframe).click(function() {
					checkConfirm();
				});
				
				//取消按钮，关闭对话框
				$('#chooseBankCancel', iframe).click(function() {
					sub.closeSubWindow();
				});
				
				//绑定保存事件
				$("input[name=modifyBank]").click(function(e){
					bindModifyBank(this);
				});
				
				//绑定删除
				$("input[name=deleteBank]").click(function(e){
					deleteBankAccount(this);
				});
				
				//是否为新增， 更改”修改---> 保存“
				var tempTr = $("#bankTBodyInWindow").find('tr:eq(0)');
				if(tempTr.find('[name=isAdd]').val() == '1') {
					bindModifyBank($("#bankTBodyInWindow").find('tr:eq(0)'));
				}
				
				// 省市区联动绑定
				fortune.fyInitCity(iframe,'fy_bankProvinceSelect','fy_bankCitySelect');
			};
			var close_callback = function(){};
			
			var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
			$('#subClose').off('click').on('click',function(){  sub.closeSubWindow();});
			
			
			//带回
			function checkConfirm() {
				var checkObj = $('[name="selectBanckId"]:checked');
				if(!checkObj || checkObj.length == 0) {
					BootstrapDialog.alert("请选择带回项");
					return false;
				}
				
				if(! checkObj.val()) { //选中的项，没有paymentBankId，（没保存）
					BootstrapDialog.alert("选择的带回项有异常，请检查是否保存。");
					return false;
				}
				
				var checkRow = checkObj.closest('tr');
				//赋值
				var trObj = checkRow;
				
				var str = '<tr>' +
				                	'<input type="hidden" name="paymentBankId" value="' + checkObj.val() + '" />' +
									'<input type="hidden" name="receiveBankId" value="' + checkObj.val() + '" />' +
									'<input type="hidden" name="bankProvince" value="' + $(trObj).find('[name=bankProvince]').val() + '" />' +
									'<input type="hidden" name="bankNameId" value="' + $(trObj).find('[name=bankNameId]').val() + '" />' +
								'<td>' +
								 '<input type="hidden" name="bankNameId" value="' + $(trObj).find('[name=bankNameId]').val() + '" />' +
									$(trObj).find('[name=bankNameId]').find(':selected').text() +  ' ' + 
									$(trObj).find('[name=branchAddress]').val() + 
								'</td>' +
								'<td>' +
									$(trObj).find('[name=bankProvince]').find(':selected').text() +  ' ' + 
									$(trObj).find('[name=bankCity]').find(':selected').text() +  ' ' + 
									$(trObj).find('[name=bankDistrict]').find(':selected').text() +  ' ' + 
								'</td>' +
								'<td>' +
									$(trObj).find('[name=bankCard_passbook]').find(':selected').text() +  ' ' + 
								'</td>' +
								'<td>' +
									$(trObj).find('[name=bankAccountName]').val() + 
								'</td>' +
								'<td>' +
									$(trObj).find('[name=bankAccountNum]').val() + 
								'</td>' +
							'</tr>';
				$("#goldBankAccountTable").empty().append(str);
				sub.closeSubWindow();
				$('#subClose', iframe).show();
				$('#chooseBankConfirm', iframe).hide();
				$('#chooseBankCancel', iframe).hide();
			}
			
			//新增银行
			function addBankAccount() {
				$tr = $("#template").clone(true).removeAttr('id').show();
				$tr.find('select').selectpicker();
				$("#bankTBodyInWindow").prepend($tr);
				$tr.find("input[name=modifyBank]").val('保存').unbind();
				
				$tr.find("input[name=modifyBank]").click(function(e){
					bindModifyBank(this);
				});
			}
			
			//删除
			function deleteBankAccount(element) {
				param = {};
		    	bankParam = {};
		    	var $trObj = $(element).closest('tr');
		    	bankParam.id = $trObj.find("[name=paymentBankId]").val();
		    	// id为空直接删除
		    	if(bankParam.id == '' || 'undefined' == bankParam.id){
		    		$trObj.remove();
		    		return;
		    	}else{
		    		contents_getJsonForSync(ctx + "/lend/lendApplyAid/checkBankUsed",{'bankId':bankParam.id},"post",function(res){
		    			if(res.result=='false'){
		    				BootstrapDialog.alert(res.message);
		    				return;
		    			}else{
		    				BootstrapDialog.confirm('确定删除吗', function(result){
		    		            if(result){
		    		            	bankParam.customerCode = $("#customerCode").val();
		    		            	param.account = bankParam;
		    		            	param.operating = "delete";
		    		            	param = JSON.stringify(param);
		    		            	
		    		            	contents_getJsonForStringify(ctx + "/lend/lendApplyAid/bank",param,"post",function(){
		    		            		$trObj.remove();
		    		            		BootstrapDialog.alert('删除成功');
		    		            	});
		    		            }else{
		    		            }
		    		            return;
		    		        });
		    			}
		    		},null);
		    	}
			}
			
			//绑定修改银行账户信息的按钮
			function bindModifyBank(element) {
				var btnVal =  $(element).parent().parent().find("input[name=modifyBank]").val();
				if('修改' === btnVal) {
					 $(element).parent().parent().find("input[name=modifyBank]").val('保存');
					 $(element).parent().parent().find('[name=bankProvince]').prop("disabled", false).selectpicker('refresh');  //省
					 $(element).parent().parent().find('[name=bankCity]').prop("disabled", false).selectpicker('refresh');   //市
					 $(element).parent().parent().find('[name="bankNameId"]').prop("disabled", false).selectpicker('refresh');   //银行类别
					 $(element).parent().parent().find('[name="bankCard_passbook"]').prop("disabled", false).selectpicker('refresh');   //银行类别
					 $(element).parent().parent().find('[name="branchAddress"]').attr("disabled", false);  //支行
					 $(element).parent().parent().find('[name="bankAccountNum"]').attr("disabled", false);  //卡号
					 
					 
				} else if('保存' === btnVal) {
					addCustomerAccount(element);  //修改或添加
					
				}
			}
			
			//添加客户银行卡（含修改）
			function addCustomerAccount(element) {
				var $trObj = $(element).closest('tr');
				var cardNo = $trObj.find('[name="bankAccountNum"]').val();
				var custCode = $('#customerCode').val();
				if(! cardNo) {
					BootstrapDialog.alert("请输入卡号");
					return false;
				}
				///检查银行卡是否已存在  
				var accountId =  $trObj.find("[name=paymentBankId]").val();
				if(checkCardNoExist(cardNo, custCode, accountId) == true) {
					BootstrapDialog.alert("输入的卡号已存在，无法保存");
					return;
				}
				
				bankOperation(element,function(res){
					if(res.result == 'true'){
						BootstrapDialog.alert('保存成功');
						$trObj.find("[name=paymentBankId]").val(res.id);
						$trObj.find("[name=receiveBankId]").val(res.id);
						$trObj.find("[name=selectBanckId]").val(res.id);
						
						if($("#payType").val() != '4'){
							// 如果不是资金托管
							//$(element).parent().parent().find("[name=saveBank]").hide();
						}
						
						$trObj.find("input").filter(function(index){return $(this).attr("type")=='text'}).attr("disabled",true);
						$trObj.find("select").each(function(){
							$(this).prop("disabled",true);
							$(this).selectpicker('refresh');
						});
						
						$trObj.find("input[name=modifyBank]").val('修改'); //将银行信息列表后的“保存”——> “修改”
					}
				}, null);
			}
		 
			/**
			 * 对银行增删改
			 */
			function bankOperation(element, successCall, errorCall){
				bankParam = wrapParam(element);
				if(!validateParam(bankParam)){
					return;
				}
				bankParam.customerCode = $("#customerCode").val();
				param = {};
				param.account = bankParam;
				
				param.applyCode = $("#applyCode").val();
				
				if(bankParam.id) {
					param.operating = "update";
				} else {
					param.operating = "add";
				}
				
				if($("#payType").val() == '4'){
					// 如果是资金托管
					param.trusteeFlag = 'true';
				}else{
					param.trusteeFlag = 'false';
				}
				
				param = JSON.stringify(param);
				
				contents_getJsonForStringify(ctx + "/lend/lendApplyAid/bank",param,"post",successCall,errorCall);
				
			}
			
			/**
			 * 检验银行输入参数
			 */
			function validateParam(param){
				if(param.accountBankId == '' || param.accountBankId == null ){
					BootstrapDialog.alert("请选择银行");
					return false;
				}
				if(param.accountBranch == '' || param.accountBranch == null ){
					BootstrapDialog.alert("请输入支行地址");
					return false;
				}
				if(param.accountAddrProvince == '' || param.accountAddrProvince == '-1' || param.accountAddrProvince == null ){
					BootstrapDialog.alert("请选择省份");
					return false;
				}
				if(!(/[\u4e00-\u9fa5]+(?!.[1-9]\d)$/.test(param.accountBranch) ) ){
					BootstrapDialog.alert("具体支行只能为中文或者中文+数字，最后一位不能为数字");
					return false;
				}
				if(param.accountAddrCity == '' || param.accountAddrCity == '-1' || param.accountAddrCity == null ){
					BootstrapDialog.alert("请选择城市");
					return false;
				}
				// 判断付款方式，如果是资金托管就不需要校验市区是否选择
				if($("#payType").val() != '4'){
					if(param.accountAddrDistrict == '' || param.accountAddrDistrict == '-1' || param.accountAddrDistrict == null ){
						BootstrapDialog.alert("请选择区");
						return false;
					}
				}
				if(param.accountNo == '' || param.accountNo == null ){
					BootstrapDialog.alert("请输入银行账号");
					return false;
				}
				if(!(/^\d{16,19}$/.test(param.accountNo))){
					BootstrapDialog.alert("银行账号必须规范（16到19位数字）");
					return false;
				}
				return true;
			}
	
			/**
			 * 封装银行参数
			 */ 
			function wrapParam(element){
				id = $(element).parent().parent().find("[name=paymentBankId]").val();
				bankNameId = $(element).parent().parent().find("[name=bankNameId]").val();
				branchAddress = $(element).parent().parent().find("[name=branchAddress]").val();
				bankProvince = $(element).parent().parent().find("[name=bankProvince]").val();
				bankCity = $(element).parent().parent().find("[name=bankCity]").val();
				bankCard_passbook = $(element).parent().parent().find("[name=bankCard_passbook]").val();
				bankAccountName = $(element).parent().parent().find("[name=bankAccountName]").val();
				bankAccountNum = $(element).parent().parent().find("[name=bankAccountNum]").val();
				return {
					id:id,
					accountBankId:bankNameId,
					accountBranch:branchAddress,
					accountAddrProvince:bankProvince,
					accountAddrCity:bankCity,
					accountCardOrBooklet:bankCard_passbook,
					accountName:bankAccountName,
					accountNo:bankAccountNum
				}
			}
			
			// 检查银行卡是否已存在
			function checkCardNoExist(cardNo, custCode, accountId) {
				var isTrustAccountAdd = "0"
				if($("#payType").val() == '4'){ // 如果是资金托管
					isTrustAccountAdd = '1';
				}
				var isExist = false;
				$.ajax({
					url : ctx + "/lend/lendApplyAid/checkExistBankCard",
					type : 'post',
					async : false,
					data: {
						'cardNo' : cardNo, 
		            	'custCode' : custCode,
		            	'accountId' : accountId,
		            	'isTrustAccountAdd' : isTrustAccountAdd
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
				
				return isExist;
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

	 });


 });