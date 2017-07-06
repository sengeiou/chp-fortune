<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
        <h4 class="f14 ml10">确定要将该笔债权释放到月满盈债权池吗？</h4>
    <form id="borrowMonthableBackToolForm" method="post" action="${ctx}/borrowMonthable/borrowMonthableBackTool">
    	<input type="hidden" name="creditMonableId" value="${borrowMonthableBackTool.creditMonableId}">
    	<input type="hidden" name="creditMonId" value="${borrowMonthableBackTool.creditMonId}"> 
    	<input type="hidden" name="borrowMonthableVerTime" value="${borrowMonthableBackTool.borrowMonthableVerTime }"/>
    	<input type="hidden" name="borrowMonthVerTime" value="${borrowMonthableBackTool.borrowMonthVerTime }"/>
    	<div class='box6'>
    	<table class="table1">
        <tr>
           <td>
             <lable class='lab'>释放前金额：</lable><input type="text" id="beforeReleaseBorrow" name=beforeReleaseBorrow 
             	readonly="true" class="input_text140 mr10" 
             	value="${borrowMonthableBackTool.beforeReleaseBorrow }">
           </td>
           <td>                  
             <lable class='lab'>释放后金额：</lable><input type="text" name= afterReleaseBorrow 
             	readonly="true" class="input_text140 mr10" 
             	value="${borrowMonthableBackTool.afterReleaseBorrow }">
           </td>                    
        </tr>
        <tr>
            <td>
             <lable class='lab'>释放前利率：</lable><input type="text" name =beforeReleaseBorrowRate 
             	readonly="true" class="input_text140 mr10" 
             	value="${borrowMonthableBackTool.beforeReleaseBorrowRate }">
            </td>
            <td>                 
             <lable class='lab'> 释放后利率：</lable><input type="text" name = afterReleaseBorrowRate 
             	readonly="true" class="input_text140 mr10" 
             	value="${borrowMonthableBackTool.afterReleaseBorrowRate }">
            </td>                  
        </tr>
     </table>
     </div>
    <div class="tright pb10 pt10 pr30">
    	<input class="btn cf_btn-primary borrowMonthableBackTool" type="button" value="确定"></button>
    	<input class="btn cf_btn-primary borrowMonthableBackToolQX" type="button" value="取消"></button>
    </div>
 </form>
