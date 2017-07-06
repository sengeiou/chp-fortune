$(document).ready(function() {    
	$('.go').click(function(){
		var $form = $(this).closest('form');
		contents_getJsonForSync(
			ctx+"/common/test/go", 
			$form.serialize(), 
			"post", 
			function(data){
				$form.find('.result').empty();
				$.each(data,function(i,v){
					$li=$("<div class='column' data-id="+i+" style='width: 40%;text-align: right;float:left'>"+i+":         </div>");
					$lv=$("<div data-id='"+i+"_value' style='text-align: left;float:left'>&nbsp;&nbsp;&nbsp;"+v+"</div>");
					$form.find('.result').append($li).append($lv).append($('<br/>'));
					
				})
			},null,null);
	});
	$('.compare').click(function(){
		$('#form1 div.column').each(function(){
			key=$(this).attr('data-id');
			value1=$('#form1 div[data-id="'+key+'_value"]').text();
			value2=$('#form2 div[data-id="'+key+'_value"]').text();
			if(value1!=value2){
				$('#form2 div[data-id="'+key+'"]').css('background-color','yellow');
				$('#form2 div[data-id="'+key+'_value"]').css('background-color','yellow');
				$('#form1 div[data-id="'+key+'"]').css('background-color','rgb(170, 228, 241)');
				$('#form1 div[data-id="'+key+'_value"]').css('background-color','rgb(170, 228, 241)');
			}
		});
	});
	$('.clear').click(function(){
		var $form = $(this).closest('form');
		$form.find('.result').empty();
	});
});