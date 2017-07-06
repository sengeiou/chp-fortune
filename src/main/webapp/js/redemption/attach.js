
$(function(){
	var myzone;
	// 初始化上传控件
	if($(".redeem_form").length>0 || $(".redeem_relaunch").length>0){
		//附件加载
		contents_getJsonForSync(
				ctx+'/common/file/getAttachmentAjax', 
				{'code':$('[name="lendCode"]').val(),'tableId':$('[name="redemptionId"]').val(),'tableName':'t_tz_redemption'}, 
				'post',
				function(result){
					if ($(".redeem_relaunch").length>0) {
						myzone = DropzoneHandle.initDropZone('#my-awesome-dropzone',result,{});
					} else {
						myzone = DropzoneHandle.initDropZone('#my-awesome-dropzone',result,{
							hideDrop:true,
							addRemoveLinks:false
						});
					}
				},
				function(){
					BootstrapDialog.alert("加载附件发生错误");
				},null);
	}else{
		myzone = DropzoneHandle.initDropZone('#my-awesome-dropzone',[],null);
	}
	

	/**
	 * 提交申请
	 */
	$("#applybtn").click(function(){
		var rt = $("input[name='redemptionType']:checked").val(); // 赎回类型
		var rrt = $("input[name='redemptionReceType']:checked").val(); // 回款期限
		var alm = $("#applyLendMoneyd").val(); // 出借金额
		var linitDay = $("#linitDay").val(); // 到期日期
		var ra = $("#rtAmount").val(); // 赎回金额 
		var fb = $("#feedback").val(); // 客户是否回馈
		var fbm = $("#feedbackMoney").val(); // 回馈金额
		var edition = $("#applyAgreementEdition").val(); // 合同版本
		var is161Contract = $("#is161Contract").val(); // 是否为1.6.1合同版本
		if (rt=='1') {
			// 部分赎回时增加判断
			if (ra=='') {
				BootstrapDialog.alert("请输入赎回金额！");
				return;
			}
			var sub = Number(alm) - Number(ra);
			if (Number(ra).toFixed(2)<0.01) {
				BootstrapDialog.alert("请输入正确的赎回金额！");
				return;
			}
			if (sub.toFixed(2)<0.01) {
				BootstrapDialog.alert("赎回金额必须小于出借金额！");
				return;
			}
			
		}
		
		if (is161Contract=='1'){
			// 1.6.1合同版本才需要判断
			if (fb==null || fb=='') {
				BootstrapDialog.alert("请选择是否客户回馈！");
				return;
			}
			if (fb=='1') {
				if (fbm==null || fbm=='') {
					BootstrapDialog.alert("请输入客户回馈金额！");
					return;
				}
				if (Number(fbm).toFixed(2)<0.01) {
					BootstrapDialog.alert("请输入正确的回馈金额！");
					return;
				}
			}
		}
		
		if (rrt ==  null || rrt == '') {
			BootstrapDialog.alert("请选择回款期限！");
			return;
		}
		if (is161Contract!='1'){
			if (linitDay ==  null || linitDay == '') {
				BootstrapDialog.alert("请指定赎回到期日期！");
				return;
			}
		}
		/*files = myzone.getAcceptedFiles();
		if(files.length==0){
			BootstrapDialog.alert("请先上传文件");
			return;
		}*/
		var file = $("div .dz-details").length;
		if(file==0){
			BootstrapDialog.alert("请先上传文件");
			return;
		}
		
		if (rt=='1') {
			// 部分赎回时剩余金额验证
			contents_getJsonForSync(
					ctx+'/myApply/redemption/applyCheck', 
					$("form").serialize(), 
					'post',
					function(result){
						if (result=='true') {
							loadingMarkShow();
							$("#applyForm").attr("method", "post").submit();
							$("#applybtn").attr("disabled","disabled");
						} else {
							BootstrapDialog.alert(result);
							return;
						}
					},
					function(){},null);
		} else {
			// 全部赎回，直接提交
			loadingMarkShow();
			$("#applyForm").attr("method", "post").submit();
			$("#applybtn").attr("disabled","disabled");
		}
	});
});