<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="pageToken" type="java.lang.String" required="true" description="页面token"%>
<div class="column_ctrl">
&nbsp;<b>选择列：</b><input type='checkbox' id = 'columnAllSelect'/>全选
</div>
<script>

jQuery(document).ready(function($){
	var ignore=["","全选","序号","出借编号","客户姓名","操作"];
	$ctrls = $('.column_ctrl');
	var $columns;
	var $table;
	$.each($ctrls,function(){
		var ctrl = this;
		var $tables=$(ctrl).parent().find("table.data-list-table");		
		
		$.each($tables,function(){
			var savedStatus = $.cookie('lendApplyLook_lendApplyLookList');
			$table = $(this);
			
			//$(ctrl).empty();
			
			$columns = $table.find('th');
			//该页面cookie不存在
			if(savedStatus==null){
				savedStatus={};
				$.each($columns,function(){
					savedStatus[$(this).text()]=true;
				});
				$.cookie('lendApplyLook_lendApplyLookList', JSON.stringify(savedStatus));
			}else{
				savedStatus = JSON.parse(savedStatus);
			}
			$.each($columns,function(index){
				var columnName=$(this).text().trim();
				if($.inArray(columnName, ignore) == -1){
					var checkbox = $("<input type='checkbox' class='columnTag'/>"+columnName);
					//注册点击事件
					$(checkbox).on('click',function(){
						var isChecked = $(this).prop('checked');
						var $nTable = $(this).closest('.column_ctrl').parent().find('table.data-list-table');
						$.each($nTable.find('tr'),function(){
							if(isChecked){
								$(this).find('th:eq('+index+'),td:eq('+index+')').show();
							}else{
								$(this).find('th:eq('+index+'),td:eq('+index+')').hide();
							}	
						});
						//cookie更新
						savedStatus[columnName]=$(checkbox).prop('checked');
						$.cookie('lendApplyLook_lendApplyLookList', JSON.stringify(savedStatus));
						/** 表头固定对应 **
						$.each($nTable,function(){
							if($(this).is('div.fixed-table-body table')){
								$(this).bootstrapTable('resetView');
							}
						});
						*/
					});
					$(ctrl).append(checkbox);
					
					//初始化
					if(savedStatus[columnName]){
						//显示
						$(checkbox).prop('checked',true);
						$.each($table.find('tr'),function(){
							$(ctrl).find('th:eq('+index+'),td:eq('+index+')').show();
						});	
					}else{
						//隐藏
						$(checkbox).prop('checked',false);
						$.each($table.find('tr'),function(){
							$(this).find('th:eq('+index+'),td:eq('+index+')').hide();
						});	
					}
				}
			});
			$("#columnAllSelect").click(function(){
				if(this.checked){
					 $.each($columns,function(index){ 
						 var columnName=$(this).text().trim();
						 if($.inArray(columnName, ignore) == -1){
							 $.each($table.find('tr'),function(){
							 	$(this).find('th:eq('+index+'),td:eq('+index+')').show();
						 	 })
						 	savedStatus[columnName]=true;
						 }
					 }); 
					 $("input.columnTag").attr("checked", true);				 
				}else{
					$.each($columns,function(index){ 
						 var columnName=$(this).text().trim();
						 if($.inArray(columnName, ignore) == -1){
							 $.each($table.find('tr'),function(){
								 $(this).find('th:eq('+index+'),td:eq('+index+')').hide();
							 })
							 savedStatus[columnName]=false;
						 }
					 })
					 $("input.columnTag").attr("checked", false);				
				}
				//cookie更新
				$.cookie('lendApplyLook_lendApplyLookList', JSON.stringify(savedStatus));
			})
			
		});

	});
	
});
</script>