<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div>
	<form method="post">
		<table class="f14 table4" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td><label class="lab">回款平台：</label> 
				<select class="select78" id='platformId' name='platformId'>
					<option value="">请选择</option>
					<c:forEach items="${dicts.tz_backMoney_plat}" var='item'>
						<c:if test="${jzhFlag=='1' && item.value==jzhValue}">
							<!-- 金账户数据时，仅显示金账户 -->
							<option value="${item.value }">
								${item.label }
							</option>
						</c:if>
						<c:if test="${jzhFlag!='1'}">
							<c:if test="${fns:multiOption(notJzhValue,item.value) }">
								<!-- 非金账户数据时，显示中金通联 -->
								<option value="${item.value }">
									${item.label }
								</option>
							</c:if>
						</c:if>
					</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>
					<label class="lab">是否批量：</label> 
					<input type="radio" name="interfaceType" value="1" checked="checked">批量 
					<input type="radio" name="interfaceType" value="0" disabled="disabled">实时
				</td>
			</tr>
		</table>
	</form>
</div>
