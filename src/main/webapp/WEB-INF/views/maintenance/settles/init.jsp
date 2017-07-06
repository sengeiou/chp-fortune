<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/maintenance/maintenance.js"></script>
</head>
<body>
    <div class="box1 mb10">
    <form id="searchForm"  action="${ctx}/maintenance/settle/settlesHeader" method="post">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td>
                	<label class="lab">出借编号：</label>
                	<input type="text" name="lendCode" required="required" value="${SettlesView.lendCode}" class="cf_input_text178">
                </td>
                <td>
                	<label class="lab">账单日：</label>
<%--                 	<input type="text" name="billday" value="${SettlesView.billday}" class="cf_input_text178"> --%>
	               <select class="select78"	id='' name='billday' multiple="multiple" required="required">
									<c:forEach items="${dicts.tz_bill_day}" var='item'>
										<option value="${item.value }" <c:if test="${fns:multiOption(SettlesView.billday,item.value )}">selected</c:if>>
											${item.label }
										</option>
									</c:forEach>
				   </select>
                </td>
				<td>
					<label class="lab">报告日期：</label>
					<input type="text" name="billDate" id="billDate" required="required"
			    		value="${fns:getFormatDate(SettlesView.billDate,'yyyy-MM-dd')}" 
			    		class="Wdate cf_input_text178" onfocus="WdatePicker()">
				</td>
            </tr>
            <tr> 
			    <td colspan="3">
			    	<label class="lab">备注：</label>
						<textarea disabled="disabled" rows="18" cols="150">
							报告日，输入日期格式，如：2015-07-15
							以下举例说明：
							
							一、
							如一笔出借的出借日期为：2015-06-17
							该笔出借的账单日为15日，同时进行月份+1，得到的报告日为：2015-07-15
							则报告日输入：2015-07-15，账单日选择15日，然后进行[结算]
							
							二、
							如果一笔出借的账单日为：30日
							如：2015-05-31，月份+1，得到的报告日为：2015-06-30
							如：2015-06-02，月份不+1，得到的报告日为：2015-06-30
							则报告日输入：2015-06-30，账单日选择30日，然后进行[结算]
							
							PS：如果账单日为30日，同时推算出的报告日所在月份为2月，那么报告日为：2015-02-28(29)
							账单日选择30日，然后进行[结算]

                		</textarea>
			    </td>
            </tr>
        </table>
        <div class='tright pr30 pt10 pb5'>
            <input class="btn cf_btn-primary" type="submit" value="结算"/>
            <input type="reset" class="btn cf_btn-primary" value="清除">
        </div>
	   </form>
	   </div>
    <sys:message content="${message}"/>
</body>
</html>
