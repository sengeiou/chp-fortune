<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<script type="text/javascript">
	$(function() { 
		curDate = new Date();
		year = curDate.getFullYear();
		month = curDate.getMonth() + 1;
		day = curDate.getDate();
		if(month < 10){
		month = "0"+month;
		}
		if(day<10){
		day = "0"+day;
		}
		$( "#backDay" ).val(year+'-'+month+'-'+day);
		
		var hMaxDate = $( "#hMaxDate" ).val()
		// 有默认值时，显示默认值
		if (hMaxDate ==null || hMaxDate== '') {
		} else {
			$( "#backDay" ).val(hMaxDate);
		}
	}); 
</script>
    <form id="popForm" method="post">
       <table id="returnTable" class="f14 table4" cellpadding="0" cellspacing="0" border="0" width="100%">
            <c:if test="${showFlag=='5' }">
            	<!-- 批量回款申请确认、回款确认时显示部分 -->
	            <tr> 
					<td width="31%"><label class="lab"><span class="red">*</span>回款日期：</label>
	                	<input type="text" id="backDay" name="backDay" class="Wdate cf_input_text178" onfocus="WdatePicker()"/>
	                	<input type="hidden" id="hMaxDate" value="${fns:getFormatDate(hMaxDate,'yyyy-MM-dd')}"/>
	                	<input type="hidden" id="diffDays" value="${diffDays}"/>
	                </td>
	            </tr>
            </c:if>
            <c:if test="${showFlag!='history' }">
            	<!-- 已办列表不显示该内容 -->
	            <tr> 
					<td width="31%"><label class="lab"><span class="red">*</span>审批结果：</label>
	                	<input name="checkExaminetype" type="radio" class="ml10 mr6" value="1" checked="checked">通过
	                	<input name="checkExaminetype" type="radio" class="ml10 mr6" value="2">不通过
	                </td>
	            </tr>
            </c:if>
            <c:if test="${showBX=='1' }">
	            <tr> 
					<td width="31%"><label class="lab"><span class="red">*</span>是否补息：</label>
	                	<input name="isSupplemented" type="radio" class="ml10 mr6" value="1">是
	                	<input name="isSupplemented" type="radio" class="ml10 mr6" value="2" checked="checked">否
	                </td>
	            </tr>
            </c:if>
            <tr id='checkExamine' <c:if test="${showFlag!='history' }">style="display: none;"</c:if>> 
				<td>
					<label class="lab">退回原因：</label> 
					<select class="select180 mr34" name='checkExamine' onchange="addReason();">
							<option value="">请选择</option>
							<c:forEach items="${dicts.tz_back_reason}" var='item'>
								<option value="${item.value }">
									${item.label }
								</option>
							</c:forEach>
						</select>
				</td>
            </tr>
            <tr id="checkReason" style="display: none;">
				<td>
					<label class="lab">&nbsp;</label> 
					<input type="text" class="cf_input_text178" name="checkReason" maxlength='500'>
				</td>
            </tr>
        </table>
    </form>

