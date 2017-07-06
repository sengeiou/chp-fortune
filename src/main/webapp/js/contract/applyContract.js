	$(document).ready(function() {      
		 
		//清空参数
		$('[opt=clean]').on('click',function(e){
			var form = $(this).closest('form');
			$('#applyNo').val('');
			$('#applyContacts').val('');
			$('#applyTel').val('');
			$('#applyShippingAddr').val('');
			$('#applyExplain').val('');
			$(form).find('select').val('');
			$(form).find('select').trigger('change');
		});
		
		 var path = ctx;
		
		 //保存申请记录
		 $('[opt=save]').on('click',function(e){
			 if(!$("#applyForm").validate().form()){
					//验证不成功
					return false;
			 }
			 contents_getJsonForSync(path+'/contract/contractApply/addContractApply',$("#applyForm").serialize(),'post',
		        	 //成功取得后的处理
					 function(data){
				         if(data == '-1'){
				        	 BootstrapDialog.alert("参数异常");
				         }
				         if(data == '0'){
				        	 BootstrapDialog.alert("此版本合同本月已经申请过，无法重复申请");	
				         }
				         if(data == '1'){
				        	 BootstrapDialog.alert("申请成功，等待审核");	
				        	 window.location=path+'/contract/applyAlreadyList';
				         }
		        	 },
		        	 //取得失败后的处理
		        	 function(error){
		        		 BootstrapDialog.alert('系统错误');
		        	 }
	          );
		 });

		 //保存申请记录
		 $('[opt=contversion]').on('change',function(e){
			 var cur = this;
			 if($(cur).val() ==''){
				 $('#applyInventory').val('');
		         $('#applyDistNo').val('');
		         $('#applyAlreadyuse').val('');
		         return;
			 }
			 contents_getJsonForSync(path+'/contract/contractApply/changVersion',{contVersion:$(cur).val()},'post',
		        	 //成功取得后的处理
					 function(data){
				          $('#applyInventory').val(data.applyInventory)
				          $('#applyDistNo').val(data.applyDistNo);
				          $('#applyAlreadyuse').val(data.applyAlreadyuse);
		        	 },
		        	 //取得失败后的处理
		        	 function(error){
		        		 BootstrapDialog.alert('系统错误');
		        	 }
	          );
		 });
		 
		 
	});