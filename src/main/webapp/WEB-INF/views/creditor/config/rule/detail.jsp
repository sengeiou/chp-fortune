<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf }/js/creditor/config/rule.js" type="text/javascript"></script>
<title></title>
</head>
<body>
<div class="body_r">
    <div class="box1 mb10">
    	<form action="${ctx}/creditor/config/rule/save" method="post" id="addForm">
	        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="70%" style="padding-left:100px;">
	             <tr>
	             	<td style="float:right;width:120px">
	             		<label class="lab"><span class='red'>*</span>名称：</label>
	             	</td>
	                <td style="width:80%" colspan="2">	                	
	                	<input type="text" class="cf_input_text178" name='ruleName' value="${entity.ruleName}" required maxLength="50">
	                	<input type="hidden" name='id' value="${entity.id}">
	                </td>
	             </tr>
	             <tr>
	             	<td style="float:right;width:120px">
	             		<label class="lab"><span class='red'>*</span>账单类型：</label>
	             	</td>
	                <td style="width:80%" colspan="2">
	                	<select class="select78" name="billType" select_required="1">
            				<option value="">请选择</option>
            				<c:forEach items="${fns:getDictList('tz_bill_state')}" var="item">
	                        	<option value="${item.value }" <c:if test="${entity.billType==item.value}">selected</c:if>>${item.label }</option>
	                        </c:forEach>
            			</select>
	                </td>
	             </tr>    
	             <tr>
	             	<td style="float:right;width:120px">
	             		<label class="lab">本期推荐金额：</label>
	             	</td>
	                <td style="width:80%" colspan="2">	                	
	                	<input type="text" class="cf_input_text178" id='lowerLimit' name='lowerLimit' onkeyup="this.value=this.value.replace(/\D/g,'')">&nbsp;-&nbsp;
	                	<input type="text" class="cf_input_text178" id='upperLimit' name='upperLimit' onkeyup="this.value=this.value.replace(/\D/g,'')" from-checkInt="#lowerLimit">
	                </td>
	             </tr>    
	            <tr class="proportiTrTemplate" style="display:none">
	            	<td style="float:right;width:120px">
	             		<label class="lab"></label>
	             	</td>
	            	<td>
			            <table class='ADD' style='width:100%;height:auto;border:1px solid #ccc;margin-bottom:10px;float:left'>
				             <tr>
				                <td>
				                	<label><span class='red'>*</span>投资比例：</label>
				                	<input type="text" class="input_text50 proporti" value="" maxlength="3"  required digits="1" greaterThan="0">                   
				                </td>  	                              
				            </tr>	          
				            <tr>
				                <td>
				                	<p>
					                	<label>  下限比例：</label>
					                    <input type="text" class="input_text50 lower" value="" required digits="1" maxlength="3" greaterThan="0"/>
				                	</p>
				                	<div>
				                		<span class='add addLower'></span>
				                	</div>
				                	
				                </td>
				            </tr>        	
			            </table>
		            </td>
		            <td style="vertical-align: top;">
		            	<span class='mark add_proporti'></span>    
		            </td>
		         </tr>
		         <tr class="proportiTr">
	            	<td style="float:right;width:120px">
	             		<label class="lab"></label>
	             	</td>
	            	<td>
			            <table class='ADD' style='width:100%;height:auto;border:1px solid #ccc;margin-bottom:10px;float:left'>
				             <tr>
				                <td>
				                	<label><span class='red'>*</span>　投资比例：</label>
				                	<input type="text" class="input_text50 proporti" value="" maxlength="3"  required digits="1" greaterThan="0">                   
				                </td>  	                              
				            </tr>	          
				            <tr>
				                <td>
				                	<p>
					                	<label>　下限比例：</label>
					                    <input type="text" class="input_text50 lower" maxlength="3"  value="" required digits="1"/>
				                	</p>
				                	<div>
				                		<span class='add addLower'></span>
				                	</div>
				                	
				                </td>
				            </tr>        	
			            </table>
		            </td>
		            <td style="vertical-align: top;">
		            	<span class='add mark'></span>    
		            </td>
		         </tr>
		         <tr>
	         		<td style="float:right;width:120px">
	             		<label class="lab">说明：</label>
	             	</td>
	                <td colspan="2">
	                	<textarea class="textarea_refuse" name='remark' value="${entity.remark}" maxlength="200" placeholder="最多只能填写200个字符"></textarea>
					</td>
	            </tr>
	             <tr>
	            	<td style="float:right;width:120px">
	             		<label class="lab"><span class='red'>*</span>状态：</label>
	             	</td>
	                <td colspan="2">	                	
	                	<select class="select78" name='useFlag' select_required="1">
	                		<option value="">请选择</option>
	                		<c:forEach items="${fns:getDictList('com_use_flag')}" var="item">
	                        	<option value="${item.value }" <c:if test="${entity.useFlag==item.value}">selected</c:if>>${item.label }</option>
	                        </c:forEach>
	                	</select>
	                </td>
	            </tr>
	        </table>       
	    </form>
	    <form id="backForm" action="${ctx}/creditor/config/rule/back" method="post"></form>
    </div>
         <div class="tright pr30">
	        	<input class="btn cf_btn-primary btnSave" type="button" value="保存" />
	        	<input class="btn cf_btn-primary btnBack" type="button" value="返回" />
	     </div>
</body>
</html>
