<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<meta name="decorator" content="default" />
<title>可用债权配置</title>
<script type="text/javascript" src="${ctxWebInf}/js/obligatory/obligatory.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>

</head>
	<body>
		<div class="body_new">
			<form id="credit">
				<table class="f14 mt10" cellpadding="0" cellspacing="0" border="0" width="100%">
					<input type="hidden" name="creditValueId" value="${par}"/>
					<tr>
						<td><label class="lab">账单类型：</label>
							<input type='checkBox' class="ml10 mr6" onclick="javascript:checkAll();"/>全选/不全选&nbsp;
							<input type='checkBox' id="ct" class="ml10 mr6" name="configType" value="1"/>首期
							<input type='checkBox' id="ct" name="configType" class="ml10 mr6" value="2"/>非首期</td>
					</tr>
					<tr>
						<td><label class='lab'><span class="red">*</span>城市：</label>
							<select id="configProvince" name="configProvince"  select_required="1">
								<option value="" selected="selected">请选择</option>
								<c:forEach items="${provinceList }" var="item">
								   <option value="${item.id}">${item.areaName }</option>
								</c:forEach>
							</select>
							<select id="configCity" name="configCity"  multiple="multiple" select_required="1">
							</select></td>
					</tr>
					<tr>
						<td><label class="lab"><span class="red">*</span>营业部：</label>
							<select id="og" name="configYy" multiple="multiple" select_required="1">
							</select>
						</td>
					</tr>
				</table>
					<div class='tright pr30'>
					       <input type="button" id="cdit" value="提交" class="btn cf_btn-primary"/>
	                       <input type="button" value="关闭" class="btn cf_btn-primary" onclick="window.location.href='${ctx}/creditLocation/goAdd'"/>
					 </div>
			</form>
		</div>
	</body>
</html>