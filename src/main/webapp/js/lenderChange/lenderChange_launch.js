$(function(){
	
	/**
	 * 获得页面的iframe
	 */
	var iframe = getCurrentIframeContents();
	
	//省市区联动绑定
	fortune.initCity(iframe,'addrProvince','addrCity','addrDistrict');
	// 紧急联系人地址
	fortune.initCity(iframe,'emerProvince','emerCity','emerDistrict');
	$('#btnSubmit').click(function(){
		var f=false;
		/*$.each($("input[name='addAttachmentId']"), function(n,p) {
			var v=$(p).val();
			$.each($("input[name='deleteAttachmentId']"), function(n1,p1) {
				
				if(v==$(p1).val()){
					v='';
				}
			})
			 if(v!=''){
				
			    f=true;
			    return false;	
			   }			
		});	
		if(!f){
			BootstrapDialog.alert('附件不能为空');
			return false;
		}*/
		
		$.each($("a[class='dz-ex-download']"), function(n,p) {
			
			if($(p).html()!=""){
				f=true;
			}
		});
		if(!f){
			BootstrapDialog.alert('请上传附件');
			return false;
		}
		
		
		$form = $('#searchForm',iframe);
		if(!$form.validate().form()){
			//验证不成功
			return false;
		}else{
			
			var f=isNullverifyCode();
			if(f==-1){
					return false;
			}
			else if(f==1){
				checkPin('1',
					function(){
						goSubmit();
					},function(){
						BootstrapDialog.alert('手机验证码不正确或超时');
						return false;
					}
				);
			}
			else if(f==2){
				checkPin('2',
					function(){
						goSubmit();
					},function(){
						BootstrapDialog.alert('邮箱验证码不正确或超时');
						return false;
					}
				);			
			}
			else if(f==3){
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
			else{
				goSubmit();
			}
		}
	});	
	if($("#pageType").val()=='lenderChangeBackForm'){
		isChanger('1')
		isChanger('2')
	}
})

function goSubmit(){
	var msg="";
	if($("[name='customer.changerTypeVal']:checked").val()==6){
		msg='金账户销户,只修改金账户账号！';
	}
	
	BootstrapDialog.confirm(msg+'确定提交吗？', function(result){
        if(result){
        	 var url=ctx+"/lenderChange/apply/launchFlow";
        	if($("#pageType").val()=='lenderChangeBackForm'){
        		url=ctx+"/lenderChange/reject/newLaunchFlow";
        	}
		     var $tempForm =$('#searchForm');
		     $tempForm.attr('action',url).attr('target','');
		     $tempForm.off("submit"); 
        	$.ajax({
                type: "post",
                url: ctx+"/customerTransfer/check",
                dataType: "text",
                data: $('#searchForm').serialize(),
                success: function (data) {
                	if(data=="1"){
                		BootstrapDialog.confirm('该手机号可以变更，但是会删除该手机号原有的客户信息,确定提交吗？', function(result){
                			if(result){
            
                			    $tempForm.submit();
            	            	BootstrapDialog.alert('提交成功');
                			}else{
                				
                			}
                		});	
                	}else if(data=="4"){
                		$("#searchForm").submit();
    	            	BootstrapDialog.alert('提交成功');
                }else{
                	BootstrapDialog.alert('该手机号已存在客户，不可变更！');
                }
                },
                error: function (data) {
                	BootstrapDialog.alert("系统错误，请联系管理员！");
                }
            });
        	
        	
        }else{
        }
        return;
    });
}
/**
 * 验证是否可以销户
 */
 function isAccountOff(custCode){
	
	 $.ajax({
		url:ctx+"/lenderChange/apply/isAccountOff?custCode="+custCode,
		 success:function(res){
			 
			 if(res>0){
				 $("#accountOff").hide();
			 }
		 }
	 })
 }

/**
 * 分页
 * @param n 当前页
 * @param s 分页大小
 * @returns {Boolean}
 */
function page(n,s){
	if(n) $("#pageNo").val(n);
	if(s) $("#pageSize").val(s);
	$("#searchForm").submit();
	return false;
}

/**
 * 跳转页面
 * @param pageURL
 */
function goPage(pageURL){
	window.location = pageURL;
}



/**
 * 跳转到变更历史
 * @param custCode
 */
function goLenderChangeHistory(custCode) {

	var url=ctx+"/lenderChange/apply/goLenderChangeHistory?changeCode="+ custCode;
	
	window.location = url;
}


function templates(custCode){
	var url=ctx+"/lenderChange/apply/exportExcel?custCode=" + custCode;
	window.location = url;
}
/**
 * 金账户销户模板
 * @param custCode
 */
function templatesGoldCancel(){
	var url=ctx+"/lenderChange/apply/exportGoldCancelExcel";
	window.location = url;
}

function checkPhone(){
	$.ajax({
        type: "post",
        url: ctx+"/customerTransfer/check",
        dataType: "text",
        data: $('#searchForm').serialize(),
        success: function (data) {
        	if(data=="1"){
        		BootstrapDialog.alert("该手机号可以变更，但是会删除该手机号原有的客户信息！");
        	}else if(data=="2"){
        		BootstrapDialog.alert("该手机号在CHP中已有客户，并且客户状态已不为咨询沟通状态，不可变更！");
        	}else if(data=="3"){
        		BootstrapDialog.alert("该手机号在CHP中已有客户，并且理财经理不是您，不可变更！");
        	}else if(data=="4"){
        		BootstrapDialog.alert("该手机号在CHP中并无客户记录，可以变更！");
        	}
        },
        error: function (data) {
        	BootstrapDialog.alert("系统错误，请联系管理员！");
        }
    });		
}   


function sqsPreview()     
    
  {       
   
	 var url=ctx+"/lenderChange/apply/sqsPreview";
     var $tempForm =$('#searchForm');
     $tempForm.attr('action',url).attr('target',"ff");
     $tempForm.on("submit",function(){ openWindow(); });  
     $tempForm.submit();     
     
}     
    
   
function openWindow()       
    
{       
    
	window.open('about:blank','ff','height=500,width=550,toolbar=yes,resizable=yes,scrollbars=yes')
    
}       

