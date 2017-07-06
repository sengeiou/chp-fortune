
 /**
  * 验证数据后，发起申请
  */
 function applySubmit(){
	 var backmId = $("#backmId").val();
	 contents_getJsonForSync(
		ctx+'/myApply/backMoney/dataExamine', 
		{'backmId':backmId}, 
		'post',
		function(result){
			if (result=='1') {
				contents_getJsonForSync(
						ctx+'/myApply/backMoney/apply', 
						$("form").serialize(), 
						'post',
						function(result){
							if (result.message=='') {
								loadingMarkShow();
								window.location.href=ctx+"/myApply/backMoney/applyList";
							} else {
								BootstrapDialog.alert(result.message);
							}
						},
						function(){},null);
			} else {
				BootstrapDialog.alert(result);
			}
		},
		function(){},null);
 }
 
/**
 * 批量回款申请
 */
function multiApply(){
	var count = $("input[name='backmId']:checked").length;
	if (count<1) {
		BootstrapDialog.alert("请勾选需要操作的数据！");
		return;
	}
	
	var backmId = getBackmIds();
	BootstrapDialog.confirm("共"+count+"条数据，是否继续操作？",
			function(r){
		if (r) {
			contents_getJsonForSync(
					ctx + "/myApply/backMoney/multiApply",
					{"backmId":backmId},
					'post',
					function(result){
						BootstrapDialog.alert(result.message,function() {
							loadingMarkShow();
							$('#search').click()});
					},
					function(error){
						BootstrapDialog.alert(error);
					}
			);
			
		}
	});
}

/**
 * 导出Excel
 * @param url
 */
function exportExcel(url){
	var count = $("#hDataAmount").val();
	if (0==count) {
		BootstrapDialog.alert("无可操作数据！");
		return;
	}
	
	var data = {};
	var itemCount = $("input[name='backmId']:checked").length;
	if (itemCount<1) {
		// 无勾选
		data = $("#searchForm").serialize();
	} else {
		// 有勾选
		data = {"backmId":getBackmIds()};
	}
	contents_getJsonForSync(
			ctx+"/myApply/backMoney/"+ url+"Check",
			data, 
			'post',
			function(result){
				if (result=='0') {
					BootstrapDialog.alert("没有符合条件的数据");
				} else {
					BootstrapDialog.confirm('共'+result+'条符合条件的数据，确定导出？', function(result){
						if(result){
//				            window.location.href=ctx+"/myApply/backMoney/"+ url+"?"+d;
							if (itemCount<1) {
								postFormReplaceGet($("#searchForm"),ctx+"/myApply/backMoney/"+ url);
							} else {
								postParamReplaceGet(data, ctx+"/myApply/backMoney/"+ url);
							}
				        }
				     });
				}
			},
			function(){},null);
}

/**
 * 上传修改渠道标识
 */
function updateChannel(){
		// 调用子窗口的参数
	  	var url, argment, callback;
	  	url = "/myApply/backMoney/toUploadChannel";
	  	argment = null;
		var iframe = getCurrentIframeContents();
	  	load_callback = function(iframe){
	  		$('.modal-title').text("上传渠道标识");
	  		//单选按钮设置禁用属性
	  		$("#fileImport").attr({"disabled":"disabled"});
		};
		close_callback = function(arg,iframe){};
	    var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
	    //自定义出发关闭回调函数的动作
	    $('#subClose',iframe).off('click').on('click',function(){
     		var filename = $("#fileImport").val();
	    	if(filename ==null || filename ==''){
	    		BootstrapDialog.alert("请选择文件！");
	    		return;
	    	} else if(!ifExcel(filename)){
	    		return;
	    	}
	    	
	    	//点击提交按钮的时候进行校验
	    	var listFlag = $("input[name='listFlag']:checked").val();
	  		if(listFlag==""||listFlag==null){
	  			BootstrapDialog.alert("请选择上传渠道标识");
				return;
	  		}
	  		
	    	var d = new FormData($("#uploadForm")[0]);
	  		var element = $("#subClose");
	  		
	  		// 异步上传
	  		contents_getJsonForFileUpload(
  				element, 
  				ctx+"/myApply/backMoney/updateChannel", 
  				d, 
  				function(result){
  					BootstrapDialog.alert(result,function(){
  						loadingMarkShow();
  						$('#search').click()});
  				},
  				function(){
  					BootstrapDialog.alert("服务器繁忙");
  					return;
  				},
  				false);
	    	sub.closeSubWindow();
	    });    	
		  
}

//单选按钮删除 禁用属性
function  removeRadioType(){
	$("#fileImport").removeAttr("disabled");
}
 