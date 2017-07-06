<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	
	<form id="brch">
		<div class='body_r'>
			<table class="f14 table4" cellpadding="0" cellspacing="0" border="0" width="100%">
				<div class='box1 mb10'>
					<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
						<input type="hidden" id="backiId" name="backiId" value="${code}"/>
			         	<td>
							<label class="lab"><span class="red">*</span>回息退回原因：</label>
								<select class="select180" id="cemine" name="checkExamine" onchange="javascript:showTextArea();" select_required="1">
									<option value="">请选择</option>
									<c:forEach items="${fns:getDictList('tz_backsms_reason')}" var='item'>
										<option value="${item.value }">${item.label }</option>
									</c:forEach>
								</select><textarea id="tar" name="textAre" style="display: none;" textarea_required="1" required></textarea>
						</td>
			        </table>
				</div>
			</table>
		</div>
	</form>
</body>