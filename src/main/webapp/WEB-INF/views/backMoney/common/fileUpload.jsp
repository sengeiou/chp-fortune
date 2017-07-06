<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<form id="uploadForm" method="post">
		<table class="f14 table4" cellpadding="0" cellspacing="0" border="0" width="100%">
			<c:if test="${showPlatform=='1' }">
				<!-- 回款确认上传时显示 -->
				<tr id="upl">
					<td><label class="lab">回款平台：</label> 
						<select class="select78" id='platformId' name='platformId'>
							<option value="">请选择</option>
							<c:forEach items="${dicts.tz_backMoney_plat}" var='item'>
								<!-- 仅支持富友、中金、通联 -->
								<c:if test="${fns:multiOption(platformOption,item.value) }">
									<option value="${item.value }">
										${item.label }
									</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
				</tr>
			</c:if>
			
			<!-- 回款申请上传时显示 -->
			<c:if test="${showPlatform=='2' }">
				<tr id="upl">
					<td><label class="lab">请选择需要上传的渠道标识：</label> 
						<input type="radio" name="listFlag" value="2" onclick="removeRadioType()" />大金融
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="listFlag" value="3" onclick="removeRadioType()" />金信
					</td>
				</tr>
			</c:if>
			<tr id="upl">
				<td><label class="lab">选择文件：</label>
				<input id="fileImport" type="file" name="file" value="浏览文件" /></td>
			</tr>
		</table>
	</form>

