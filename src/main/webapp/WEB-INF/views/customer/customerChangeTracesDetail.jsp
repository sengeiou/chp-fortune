<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
    <div>
	<table id="changeDetail" class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td style="width:50%;height:5px;padding:0px;margin:0px;text-align:center" height="10px";>变更前</td>
			<td style="width:50%;min-height:0px;text-align:center;">变更后</td>
		</tr>
		<tr>
			<td>
				<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<!-- 屏蔽客户姓名/手机号/身份证号 -->
						<td>
							<label class="lab"><span class='red'>*</span>客户姓名：</label>
							<input type="text" value="***" readonly="readonly">
						</td>
						<%-- <td>
							<label class="lab"><span class='red'>*</span>客户姓名：</label>
							<input type="text" value="${before.custName }" readonly="readonly">
						</td> --%>
						<td>
							<label class="lab"><span class='red'>*</span>性别：</label>
							<c:forEach items="${dicts.sex }" var="item">
								<input type="radio" value="${item.value }" <c:if test="${before.dictCustSex==item.value}">checked=checked</c:if> readonly="readonly">${item.label }
							</c:forEach>  
						</td>
					</tr>
					<tr>
						<td>
							<label class="lab"><span class='red'>*</span>客户来源：</label>
							<input type="text" value="${fns:dictName(dicts.tz_customer_src,before.dictCustSource, '-')}" readonly="readonly">
						</td>
						<td>
							<label class="lab"><span class='red'>*</span>移动电话：</label>
							<!-- 屏蔽客户姓名/手机号/身份证号 -->
							<input type="text" value="***" readonly="readonly">
							<%-- <input type="text" value="${before.custMobilephone }" readonly="readonly"> --%>
						</td>
					</tr>
					<tr>
						<td>
							<!-- 屏蔽客户姓名/手机号/身份证号 -->
							<label class="lab"><span class='red'>*</span>固定电话：</label>
							<input type="text" value="***" readonly="readonly">
							<%-- <input type="text" value="${before.custPhone }" readonly="readonly"> --%>
						</td>
						<td>
							<label class="lab"><span class='red'>*</span>电子邮箱：</label>
							<input type="text" value="${before.custEmail }" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td>
							<label class="lab">工作单位：</label>
							<input type="text" value="${before.custUnitname }" readonly="readonly">
						</td>
						<td>
							<label class="lab">行业：</label>
							<input type="text" value="${before.custIndustry }" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td>
							<label class="lab">职务：</label>
							<input type="text" value="${before.custPost }" readonly="readonly">
						</td>
						<td>
							<label class="lab">传真：</label>
							<input type="text" value="${before.custFax }" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td>
							<label class="lab">地址：</label>
							<input type="text" value="${before.addr }" readonly="readonly">
						</td>
						<td>
							<label class="lab">邮编：</label>
							<input type="text" value="${before.addrPostalCode }" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<label class="lab"><span class='red'>*</span>所在城市：</label>
							<input type="text" value="${fns:getProvinceLabel(before.addrProvince) }" readonly="readonly">
							<input type="text" value="${fns:getCityLabel(before.addrCity) }" readonly="readonly">
							<input type="text" value="${fns:getDistrictLabel(before.addrDistrict) }" readonly="readonly">
						</td>
					</tr>
				</table>
			</td>
			<td>
				<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<!-- 屏蔽客户姓名/手机号/身份证号 -->
						<td>
							<label class="lab"><span class='red'>*</span>客户姓名：</label>
							<input type="text" value="***" readonly="readonly">
						</td>
						<%-- <td>
							<label class="lab"><span class='red'>*</span>客户姓名：</label>
							<input type="text" value="${after.custName }" readonly="readonly">
						</td> --%>
						<td>
							<label class="lab"><span class='red'>*</span>性别：</label>
							<c:forEach items="${dicts.sex }" var="item">
								<input type="radio" value="${item.value }" <c:if test="${after.dictCustSex==item.value}">checked=checked</c:if> readonly="readonly">${item.label }
							</c:forEach>
<%-- 							<input type="radio" value="1" <c:if test="${after.dictCustSex=='1'}">checked=checked</c:if> readonly="readonly">男 --%>
<%-- 							<input type="radio" value="2" <c:if test="${after.dictCustSex=='2'}">checked=checked</c:if> readonly="readonly">女 --%>
						</td>
					</tr>
					<tr>
						<td>
							<label class="lab"><span class='red'>*</span>客户来源：</label>
							<input type="text" value="${fns:dictName(dicts.tz_customer_src,after.dictCustSource, '-')}" readonly="readonly">
						</td>
						<td>
							<label class="lab"><span class='red'>*</span>移动电话：</label>
							<!-- 屏蔽客户姓名/手机号/身份证号 -->
							<input type="text" value="***" readonly="readonly">
							<%-- <input type="text" value="${after.custMobilephone }" readonly="readonly"> --%>
						</td>
					</tr>
					<tr>
						<td>
							<!-- 屏蔽客户姓名/手机号/身份证号 -->
							<label class="lab"><span class='red'>*</span>固定电话：</label>
							<input type="text" value="***" readonly="readonly">
							<%-- <input type="text" value="${after.custPhone }" readonly="readonly"> --%>
						</td>
						<td>
							<label class="lab"><span class='red'>*</span>电子邮箱：</label>
							<input type="text" value="${after.custEmail }" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td>
							<label class="lab">工作单位：</label>
							<input type="text" value="${after.custUnitname }" readonly="readonly">
						</td>
						<td>
							<label class="lab">行业：</label>
							<input type="text" value="${after.custIndustry }" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td>
							<label class="lab">职务：</label>
							<input type="text" value="${after.custPost }" readonly="readonly">
						</td>
						<td>
							<label class="lab">传真：</label>
							<input type="text" value="${after.custFax }" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td>
							<label class="lab">地址：</label>
							<input type="text" value="${after.addr }" readonly="readonly">
						</td>
						<td>
							<label class="lab">邮编：</label>
							<input type="text" value="${after.addrPostalCode }" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<label class="lab"><span class='red'>*</span>所在城市：</label>
							<input type="text" value="${fns:getProvinceLabel(after.addrProvince) }" readonly="readonly">
							<input type="text" value="${fns:getCityLabel(after.addrCity) }" readonly="readonly">
							<input type="text" value="${fns:getDistrictLabel(after.addrDistrict) }" readonly="readonly">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
