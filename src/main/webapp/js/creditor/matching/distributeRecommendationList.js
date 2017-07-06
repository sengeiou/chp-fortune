$(document).ready(function() { 
	
	 var path = ctx;
	
	//换单
	$(document).on('click','.mb10 [opt=change]',function(){
		
	    BootstrapDialog.confirm('确定换单么？',function(result){
            if(result){
         		 contents_getJsonForSync(path+'/matchingcreditor/distributeRecommendationList/changeOrder',{},'post',
		        	 //成功取得后的处理
					 function(data){
				         if(data == 0){
				        	BootstrapDialog.alert('换单成功');
				        	window.location =path+'/matchingcreditor/distributeRecommendationList';
				         }
		        	 },
		        	 //取得失败后的处理
		        	 function(error){
		        		 BootstrapDialog.alert('系统错误');
		        	 }
 		          );
            }
            return;
	     });
		
	});
	
	//立即派发
	$(document).on('click','.mb10 [opt=distributeRightNow]',function(){		
		if($('[data-type="1"]').length > 0){
			BootstrapDialog.alert("已含有一条首期数据");
			return false;
		}
	    BootstrapDialog.confirm('确定立即派发么？',function(result){
            if(result){
         		 contents_getJsonForSync(path+'/matchingcreditor/distributeRecommendationList/distributeOrder',{},'post',
		        	 //成功取得后的处理
					 function(data){
				         if(data.code == 0){
				        	 BootstrapDialog.alert("请先进行文件配置");
				         }else{
				        	 BootstrapDialog.alert("已派发"+data.count+"条数据");
				        	 window.location = path+'/matchingcreditor/distributeRecommendationList';
				         }
		        	 },
		        	 //取得失败后的处理
		        	 function(error){
		        		 BootstrapDialog.alert('系统错误');
		        	 }
 		          );
            }
            return;
	     });
		
	});
	
	//首次派发
	$(document).on('click','.mb10 [opt=firstDistribute]',function(){
		contents_getJsonForSync(
				path+'/matchingcreditor/distributeRecommendationList/getUserSendCount',
				{},
				'post',
				function(data){
					  BootstrapDialog.confirm_my = new BootstrapDialog({
					            title: '非首期账单数量',
					            message: "<form id='sel'><span>非首期账单派发数量:"+data+"</span></form>",
					            closable: false,
					            data: {},
					            buttons:[{
					                cssClass: 'cf_btn-primary',
					                label: '提交',
					                action: function(dialogItSelf) {
					                	dialogItSelf.close();
					                	contents_getJsonForSync(path+'/matchingcreditor/distributeRecommendationList/distributeOrderFirstTime',{},'post',
					        		        	 //成功取得后的处理
					        					 function(data){
					        				         if(data.code == 0){
					        				        	 BootstrapDialog.alert('当前用户并未设置派发数量');
					        				         }
					        				         if(data.code == 1){
					        				        	 BootstrapDialog.alert('已派发'+data.updateCnt+'条债权');
					        				        	 window.location =path+'/matchingcreditor/distributeRecommendationList';
					        					     }
					        		        	 },
					        		        	 //取得失败后的处理
					        		        	 function(error){
					        		        		 BootstrapDialog.alert('系统错误');
					        		        	 }
					         		    );
					                }
					            },
					            {
					                label: '取消',
					                action: function(dialogItSelf) {
					                	dialogItSelf.close();
					                    return false;
					                }
					            }] 
					        }).open();
				})
		
	}); 
	
	
	//再次派发
	$(document).on('click','.mb10 [opt=distributeAgain]',function(){
	    BootstrapDialog.confirm('确定再次派发么？',function(result){
            if(result){
            	contents_getJsonForSync(
        				path+'/matchingcreditor/distributeRecommendationList/getUserSendCount',
        				{},
        				'post',
        				function(data){
        					var num = (parseInt(data)*10/100).toFixed(0);
        					  BootstrapDialog.confirm_my = new BootstrapDialog({
        					            title: '再次派发数量',
        					            message: "<form id='sel'><span>再次派发数量:</span><input type='text' name='againCnt' onkeyup='onkeyuptonum(this)' value='"+num+"'/></form>",
        					            closable: false,
        					            data: {},
        					            buttons:[{
        					                cssClass: 'cf_btn-primary',
        					                label: '提交',
        					                action: function(dialogItSelf) {
        					                	dialogItSelf.close();
        					                	//check
        					                	contents_getJsonForSync(path+'/matchingcreditor/distributeRecommendationList/distributeOrderAgain',$('#sel').serialize(),'post',
        					                		 //成功取得后的处理
        					       					 function(data){
        					       				         if(data.code == 0){
        					       				        	 if(data.remind == 1){
        					       				        		 BootstrapDialog.alert('可分派非首期债权不足,已派发'+data.updateCnt+'条债权');
            					       				        	 window.location =path+'/matchingcreditor/distributeRecommendationList';	 
        					       				        	 }else{
        					       				        	 BootstrapDialog.alert('已派发'+data.updateCnt+'条债权');
        					       				        	 window.location =path+'/matchingcreditor/distributeRecommendationList';
        					       				        	 }
        					       				         }
        					       		        	 },
        					       		        	 //取得失败后的处理
        					       		        	 function(error){
        					       		        		 BootstrapDialog.alert('系统错误');
        					       		        	 }
        					         		    );
        					                }
        					            },
        					            {
        					                label: '取消',
        					                action: function(dialogItSelf) {
        					                	dialogItSelf.close();
        					                    return false;
        					                }
        					            }] 
        					        }).open();
        				})
            	
            	
//            	
//            	
//         		 contents_getJsonForSync(path+'/matchingcreditor/distributeRecommendationList/distributeOrderAgain',{},'post',
//		        	 //成功取得后的处理
//					 function(data){
//				         if(data.code == 0){
//				        	 BootstrapDialog.alert('已派发'+data.updateCnt+'条债权');
//				        	 window.location =path+'/matchingcreditor/distributeRecommendationList';
//				         }
//		        	 },
//		        	 //取得失败后的处理
//		        	 function(error){
//		        		 BootstrapDialog.alert('系统错误');
//		        	 }
// 		          );
            }
            return;
	     });
		
	});
	
	//弃单
	$(document).on('click','.mb10 [opt=dropOrder]',function(){ 
		
		if(!$('[data-type="2"]:checked').length){
			 BootstrapDialog.alert("请选择一条非首期记录");
			 return;
       }
		
		var ids = ''; 
		var flag = 0;
		$('[data-type="2"]:checked').each(function(index){
		   if(index == 0){
			   ids = $(this).data('id');
		   }else{
		       ids += ','+$(this).data('id');
		   }
		});
		
	    BootstrapDialog.confirm('确定弃单么？',function(result){
            if(result){
         		 contents_getJsonForSync(path+'/matchingcreditor/distributeRecommendationList/dropOrder',{matchingId:ids},'post',
		        	 //成功取得后的处理
					 function(data){
				         if(data == 0){
				        	window.location =path+'/matchingcreditor/distributeRecommendationList?drop=1';
				         }
		        	 },
		        	 //取得失败后的处理
		        	 function(error){
		        		 BootstrapDialog.alert('系统错误');
		        	 }
 		          );
            }
            return;
	     });
		
	});
	
});
function onkeyuptonum(elem){
	elem.value=elem.value.replace(/\D/g,'');
}

/**
 * 导出excel
 */
function outExcel(){
	 BootstrapDialog.confirm('您确定要导出excel吗？', function(result){
         if(result){
        	 var ids = getIds();
        	 var url=ctx+"/matchingcreditor/outExcel?matchingId="+ids+"&&matchingUserflag=1";
			 window.location=url;
         }
     });
}
//获取选中ids
function getIds(){
	 var ids="";
	 $("input[type='checkbox']:checked").each(function(){
		 if($(this).data('id')!=null){
			 ids= ids+$(this).data('id')+";";
		 } 
	 }) 
	 return ids;
}
