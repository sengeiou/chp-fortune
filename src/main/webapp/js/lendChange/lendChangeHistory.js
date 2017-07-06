/**
 * 区分变更
 */ 
$(function(){
		
		$.each($("#chengeTable tr:gt(1)"),function(i,n){
			for ( var i = 0; i <2; i++) {
				var j=3;
			
				 if($( $(n).children().get(i)).find("input").val().replace(/\s/ig,'')!=$( $(n).children().get(i+j)).find("input").val().replace(/\s/ig,'')){
				 	$( $(n).children().get(i+j)).html(
				 		 "<span style='color:red '>"+$( $(n).children().get(i+j)).html()+"</span>"
				 	)
				 	$( $(n).children().get(i+j)).find("input")[0].style="color:red"
				 }
			}
		}) 	 
	 })
