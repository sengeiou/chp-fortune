//select 可编辑特性
$.fn.editable = function()  
{  
	var select = $(this);
	select.each(function(i,t){
		  $(t).on('change',function(e){
			  var op = $(this);
			  var v = op.val();
	           //编辑框
	           if(-1 == v){
	        	   var focus = false;
	        	   var select_id = select.attr('id');
	        	   var ed = $("<input type=\"text\" id=\"input_"+select_id+"\"/>");
	        	   select.after(ed);
	        	   select.hide();
	               ed.focus(function () {
	            	    focus = true;
	               }).blur(function () {
	                   if (focus) {
	                	  select.hide();
	                   }
	               }).keyup(function () {
	                   var queryCondition = ed.val().toLowerCase();
	                   if (queryCondition.length == 0) {
	                	   ed.remove();
	                	   select.show();  
	                   }
	               }).next().mousedown(function () {
	                   focus = false;
	               });
	           }
	      });
	});    
}  ;
