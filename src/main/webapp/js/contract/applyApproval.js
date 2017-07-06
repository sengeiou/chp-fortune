
	function goPage(){
		window.location = ctx+'/contract/applyApprovalList';
	}


$(document).ready(function() {      
	 
	 var path = ctx;
	 
	 $('[opt=save]').on('click',function(e){
		 
		 if(!$("#applyForm").validate().form()){
				//验证不成功
				return false;
		 }
	
		 contents_getJsonForSync(path+'/contract/applyApproval',$("#applyForm").serialize(),'post',
	        	 //成功取得后的处理
				 function(data){
			         if(data){
			        	 window.location = path+'/contract/applyApprovalList';
			         }
	        	 },
	        	 //取得失败后的处理
	        	 function(error){
	        		 BootstrapDialog.alert('系统错误');
	        	 }
	      );
	 });
});
	