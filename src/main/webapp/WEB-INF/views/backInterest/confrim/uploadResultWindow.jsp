<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

	<div class='body_r'>
		<form id="uploadResult" method="post">
			<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
              	<td><label class="lab"><span class="red">*</span>回息平台：</label>
               		<select class="select78" id='plat' name="platformId" select_required="1">
               			<option value="">请选择</option>
						<c:forEach items="${fns:getDictList('tz_backInterest_plat')}" var='item'>
							<c:if test="${item.value == '0' or item.value=='2' or item.value=='3'}">
								<option value="${item.value }"> ${item.label } </option>
							</c:if>
						</c:forEach>
					</select>
				</td>
              	<td>
               		<label class="lab"><span class="red">*</span>上传文件：</label>
	         		<input type="file" id="uplodfi" name="file"/>
	         	</td>
	        </table>
		</form>
	</div>
</body>