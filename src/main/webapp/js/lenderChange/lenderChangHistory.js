
/**
 * 区分是否变更
 */
$(function(){
		$.each($("#chengeTable tr:gt(1)"),function(i,n){
			for ( var i = 0; i <2; i++) {
				var j=3;
				if($(n).children().length==3){
					j=2
				 }
				 if($( $(n).children().get(i)).text().replace(/\s/ig,'')!=$( $(n).children().get(i+j)).text().replace(/\s/ig,'')){
				 	$( $(n).children().get(i+j)).html(
				 		 "<span style='color:red '>"+$( $(n).children().get(i+j)).html()+"</span>"
				 	)
				 }else{
					 if( $( $(n).children().get(i)).find("input").attr("checked")!=$( $(n).children().get(i+j)).find("input").attr("checked")){
							
						 $( $(n).children().get(i+j)).find("input").siblings().addClass("spanRed")	
						 }
				 }
			}
		}) 	 
	 })

	 
/**
 * 查看变更历史
 * @param applyId
 */
function openHistoryDetail(applyId){
	var url="/lenderChange/apply/openLenderChangeHistoryDetail";
	var arg={'applyId':applyId};
	var sub = SubDialogHandle.create(url,arg,null,null).loadSubWindow();
	$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
}