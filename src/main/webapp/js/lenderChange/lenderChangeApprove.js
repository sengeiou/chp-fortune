/**
 * 区分变更
 */ 
$(function(){
		
	$.each($("#chengeTable tr:gt(1)"),function(i,n){
		
		for ( var i = 0; i <2; i++) {
			var j=3;
			if($(n).children().length==3){
				j=2;
			 }
			try {
				
				var inputObj=$( $(n).children().get(i)).find("input");
				var inputObjChange=$( $(n).children().get(i+j)).find("input")
				if( inputObj.attr("type").toLowerCase()=="radio"||inputObj.attr("type").toLowerCase()=="checkbox" ){
		
					if( inputObj.attr("checked")!=inputObjChange.attr("checked")){
			
						inputObjChange.siblings().addClass("spanRed")	
					 }	
				}else{
					
					if( inputObj.val().replace(/\s/ig,'')!=inputObjChange.val().replace(/\s/ig,'') || $(inputObjChange).hasClass('diff')){
						
						$( $(n).children().get(i+j)).html(
					 		 "<span style='color:red '>"+$( $(n).children().get(i+j)).html()+"</span>"
					 	)
					 	$( $(n).children().get(i+j)).find("input")[0].style="color:red"
					 }
					
				}		
 
			 } catch (e) {}
		}
	}) 	 
})
