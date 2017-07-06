
$(function(){
	
	
	$("input[type='submit']").bind('click', function() {
		var m=$("#menuId").val();
		var f= $("form").serialize();
		$.cookie(m+"_search", f); 
	})
	
	 
})
/*
function getData(str){
	
	var m=$("#menuId").val();
	 if($("#q").val()=="true"){
			var array=getData($.cookie(m));
			for(var i in array){
				$("input[name='"+i+"']").val()
			}
		}
	  var array = new Object();
	  var strs = str.split("&");
	       for(var i=0;i<strs.length;i++)
	      {
	          var sTemp = strs[i].split("=");
	          array[sTemp[0]]=(sTemp[1]);
	      }
	    return array;
}*/