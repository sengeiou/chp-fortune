<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div >
	<div >
	<form id="offlineForm" method="post">
		<table class="f14 table4" cellpadding="0" cellspacing="0" border="0"
			width="100%">
			<tr>
				<td><label class="lab">操作：</label> 
				<input type="radio" name="tp" checked="checked" value="0">导出 
				<input type="radio" name="tp" value="1">上传 </td>
			</tr>
			<tr>
				<td><label class="lab">回款平台：</label> <select class="select78"
							id='platformId' name='platformId'>
								<option value="">请选择</option>
								<c:forEach items="${dicts.tz_backMoney_plat}" var='item'>
									<c:if test="${jzhFlag=='1' && item.value==jzhValue}">
										<!-- 金账户数据时，仅显示金账户 -->
										<option value="${item.value }">
											${item.label }
										</option>
									</c:if>
									<c:if test="${jzhFlag!='1' && item.value!=jzhValue && item.value!=6}">
										<!-- 非金账户数据时，排除金账户 , 6->卡联 -->
										<option value="${item.value }">
											${item.label }
										</option>
									</c:if>
								</c:forEach>
						</select></td>
			</tr>
			<tr id="et" style="display:none;">
				<td><label class="lab">文件格式：</label> 
					<select name="exportType" class="input_text78">
						<option value="0">Excel</option>
						<option value="1">txt</option>
					</select>
				</td>
			</tr>
			<tr id="wySplit" style="display:none;">
				<td><label class="lab">大于5万是否拆分：</label> 
					<input type="radio" name="isSplit" value="1">是
					<input type="radio" name="isSplit" checked="checked" value="2">否 
				</td>
			</tr>
			<tr id="upl" style="display:none;">
				<td><label class="lab">选择文件：</label>
				<input id="fileImport" type="file" name="file" value="浏览文件" onchange="checkIfExcel(this);"/>
			</tr>
		</table>
	</form>
	<!-- <div class="tcenter pb10 pt10 pr30">
		<button class="btn cf_btn-primary mr10"
			onclick="javascript:window.close()">确认</button>
		<button class="btn_tj" onclick="javascript:window.close()">取消</button>
	</div> -->
</div>
