<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

	<div class='body_r'>
	<form id="winForm" method="post">
		    <div class="box1 mb10">
		        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		            <tr>
		                <td colspan="2">
		                	<label class="lab"><span class="red">*</span>审批结果：</label>
		                				<input type="hidden" id="backiId" value="${str}"/>
		                				<input type="radio" class="ml10 mr6" id="cet" name="checkExaminetype" value="1"  onchange="showTextArea()" required>通过
		                				<input type="radio" class="ml10 mr6" id="cet" name="checkExaminetype" value="2"  onchange="showTextArea()" >不通过
		                </td>
		                <td id="dt" style="display: none">
							<label class="lab"><span class="red">*</span>回息日期：</label>
	            			<input type="text" id="backMoneyDay" name="backMoneyDay" class="Wdate cf_input_text178" onfocus="WdatePicker()" value="<fmt:formatDate value='${backMoneyDay}'  pattern="yyyy-MM-dd"/>" required/>
	            			<input type="hidden" id="hMaxDate" value="${fns:getFormatDate(hMaxDate,'yyyy-MM-dd')}"/>
	                		<input type="hidden" id="diffDays" value="${diffDays}"/>
	            		</td>		                
		                <td class="backReason" style="display: none">
		                	<label class="lab"><span class="red">*</span>原因：</label>
			                	<select class="select78" id="cemine" name="checkExamine" onchange="javascript:showTextArea();" select_required="1">
									<option value="">请选择</option>
									<c:forEach items="${dicts.tz_backsms_reason}" var='item'>
										<option value="${item.value }">${item.label }</option>
									</c:forEach>
								</select>
							<textarea id="tar" name="textAre" style="display:none;" textarea_required="1" required></textarea>
		            	</td>
		            </tr>
		        </table>
	    	</div>
	    	<div class="mola" style="display: none">
	    		<div class="modal-header">
	    			<h5 class="modal-title">中间人信息</h5>
	    		</div>
	    		<table cellspacing="0" cellpadding="0" border="0"  class="table table-striped table-bordered table-condensed" width="100%">
	    			<thead>
		    			<tr>
		    				<th></th>
		    				<th>中间人</th>
		    				<th>证件号码</th>
		    				<th>开户行</th>
		    				<th>银行账号</th>
		    			</tr>
	    			</thead>
	    			<c:forEach items="${list}" var="item" varStatus="status">
		    			<tr>
		    				<td>
		    				<input type="radio" class="ml10 mr6" id="plat" name="platformId" value="${item.platformId}_${item.id}"  <c:if test="${status.index==0}">required</c:if>/>
		    				<!-- 屏蔽客户姓名/手机号/身份证号 -->
		    				<td>***</td>
		    				<%-- <td>${item.name}</td> --%>
		    				<td>***</td>
		    				<%-- <td>${item.certNo}</td> --%>
		    				<td>${item.bank}</td>
		    				<td>${item.bankCode}</td>
		    			</tr>
	    			</c:forEach>
	    		</table>
	    	</div>
   		</form>
	</div>

</body>