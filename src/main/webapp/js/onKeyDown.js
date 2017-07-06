$(function(){
	//键盘回车事件
	function keydown(e) {
	    var e=e||event;  
	    if(e.keyCode==13){
	    	 $("#searchForm").submit();
	    }
	}
	$("input[type='text']").onkeydown=keydown;
});

/*
第二种方式
function keydown(e) {
    var e=e||event;  
    if(e.keyCode==13){
    	 $("#searchForm").submit();
    }
}

window.onload=function(){
	var inputs = document.getElementsByTagName("input");
	for(var i=0;i<inputs.length;i++){
		if(inputs[i].type=="text"){
			inputs[i].onkeydown=keydown;
		}
	}
}
*/