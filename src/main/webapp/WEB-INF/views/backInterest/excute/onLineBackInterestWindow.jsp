<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<div class='body_r'>
	<table class="f14 table4" cellpadding="0" cellspacing="0" border="0" width="100%">
		<div class='box1 mb10'>
			<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<td><label class="lab">回息平台：</label>
						<select class="select78" id="plt" name="platformId">
								<option value="">请选择</option>
								<c:forEach items="${dicts.tz_backInterest_plat}" var='item'>
									<c:if test="${flag==1 and item.label == '金帐户'}">
										<option value="${item.value }">${item.label }</option>
									</c:if>
									<c:if test="${flag==2 and item.label != '金帐户' and item.label != '网银'
									and item.label != '富友平台'}">
										<option value="${item.value }">${item.label }</option>
									</c:if>
								</c:forEach>
						</select></td>
				</tr>
			</table>
		</div>
	</table>
</div>
</body>
</html>
