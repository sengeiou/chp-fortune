<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

	<style type="text/css">
		.spanRed{color:red}
	</style>
	<script type="text/javascript" src="${ctxWebInf}/js/lenderChange/lenderChangHistory.js"></script>
     <div >
		<table class="f14 " cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td style="width:50%;">
					<table style="width:100%;text-align: content: ;" id="chengeTable">
                        <tr>
							<td class='tcenter' style="width:25%;" colspan="2">
								<label class="lab"><span class='red'>信息变更之前：</span></label>
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td class='tcenter' style="width:25%;" colspan="2">
								<label class="lab"><span class="red">信息变更之后：</span><br /></label>
							</td>
						</tr>
                        <tr><td colspan="5" style='height:0px;'><p  style='width:100%;border-bottom:1px solid #e5c4a1;'></p></td></tr>
						<tr> 
							<td style="width:25%;">
								<label class="lab"><span class="red">*</span>英文名称：</label>***
								<%-- <label class="lab"><span class="red">*</span>英文名称：</label>${bv.lenderBegin.customer.custEname} --%>
							</td>
							<!-- 屏蔽客户姓名/手机号/身份证号 -->
							<td style="width:25%;">
								<label class="lab"><span class="red">*</span>移动电话：</label>***
								<%-- <label class="lab"><span class="red">*</span>移动电话：</label>${bv.lenderBegin.customer.custMobilephone} --%>
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td style="width:25%;">
								<label class="lab"><span class="red">*</span>英文名称：</label>***
								<%-- <label class="lab"><span class="red">*</span>英文名称：</label>${bv.lenderAfter.customer.custEname} --%>
							</td>
							<td style="width:25%;">
								<label class="lab"><span class="red">*</span>移动电话：</label>***
								<%-- <label class="lab"><span class="red">*</span>移动电话：</label>${bv.lenderAfter.customer.custMobilephone} --%>
							</td>
						</tr>
						<tr>
							<td>
								<!-- 屏蔽客户姓名/手机号/身份证号 -->
								<label class="lab"><span class="red">*</span>固定电话：</label>***
								<%-- <label class="lab"><span class="red">*</span>固定电话：</label>${bv.lenderBegin.customer.custPhone} --%>
							</td>
							<td>
								<label class="lab"><span class="red">*</span>电子邮箱：</label>${bv.lenderBegin.customer.custEmail}
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td>
								<!-- 屏蔽客户姓名/手机号/身份证号 -->
								<label class="lab"><span class="red">*</span>固定电话：</label>***
								<%-- <label class="lab"><span class="red">*</span>固定电话：</label>${bv.lenderAfter.customer.custPhone} --%>
							</td>
							<td>
								<label class="lab"><span class="red">*</span>电子邮箱：</label>${bv.lenderAfter.customer.custEmail}
							</td>
						</tr>
						<tr>
							<td>
								<label class="lab"><span class="red">*</span>发证机关：</label>${bv.lenderBegin.customer.custCertOrg}</td>
                			<td>
                				<label class="lab">签发日期：</label><fmt:formatDate value='${bv.lenderBegin.customer.custCertIssuedate}'/>
               				</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td>
								<label class="lab"><span class="red">*</span>发证机关：</label>${bv.lenderAfter.customer.custCertOrg}</td>
                			<td>
                				<label class="lab">签发日期：</label><fmt:formatDate value='${bv.lenderAfter.customer.custCertIssuedate}'/>
               				</td>
						</tr>
						<tr>
							<td>
								<label class="lab">
								 	长期： 
								</label>
								 <input type="checkbox" name="cb_custCertPermanent" value="1" <c:out value="${bv.lenderBegin.customer.custCertPermanent==1?'checked':'' }"/> disabled="disabled" >
               			 	</td>
                			<td>
                				<label class="lab">婚姻状况：</label>
                				${fns:dictName(dicts.tz_marital_state,bv.lenderBegin.customer.custMarriage,'-')}
                			</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td>
								<label class="lab">
								长期：
								</label>
								 <input type="checkbox" name="ca_custCertPermanent" value="1" <c:out value="${bv.lenderAfter.customer.custCertPermanent==1?'checked':'' }"/> disabled="disabled" > 			 
               			 	</td>
                			<td>
                				<label class="lab">婚姻状况：</label>
                				${fns:dictName(dicts.tz_marital_state,bv.lenderAfter.customer.custMarriage,'-')}
                			</td>
						</tr>
						<tr>
							<td colspan="2">
								<label class="lab"><span class="red">*</span>所在地区：</label>
                             	${fns:getProvinceLabel(bv.lenderBegin.customer.address.addrProvince)}&nbsp;
								${fns:getCityLabel(bv.lenderBegin.customer.address.addrCity)}&nbsp;
								${fns:getDistrictLabel(bv.lenderBegin.customer.address.addrDistrict)}
							</td>
			
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td colspan="2">
							    <label class="lab"><span class="red">*</span>所在地区：</label>
								${fns:getProvinceLabel(bv.lenderAfter.customer.address.addrProvince)}&nbsp;
								${fns:getCityLabel(bv.lenderAfter.customer.address.addrCity)}&nbsp;
								${fns:getDistrictLabel(bv.lenderAfter.customer.address.addrDistrict)}
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<label class="lab"><span class="red">*</span>邮政编码：</label>${bv.lenderBegin.customer.address.addrPostalCode}
							</td>
							
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td colspan="2">
								<label class="lab"><span class="red">*</span>邮政编码：</label>${bv.lenderAfter.customer.address.addrPostalCode}
							</td>
						</tr>
						<tr>
							<td colspan="2">
							    <label class="lab" >通讯地址：</label>${bv.lenderBegin.customer.address.addr}
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td colspan="2">
							    <label class="lab" >通讯地址：</label>${bv.lenderAfter.customer.address.addr}
							</td>
						</tr>
						<tr>
							<td>
								<!-- 屏蔽客户姓名/手机号/身份证号 -->
								<label class="lab"><span class="red">*</span>紧急联系人：</label>***
								<%-- <label class="lab"><span class="red">*</span>紧急联系人：</label>${bv.lenderBegin.emer.emerName} --%>
							</td>
							<td>
								<label class="lab"><span class="red">*</span>联系人移动电话：</label>***
								<%-- <label class="lab"><span class="red">*</span>联系人移动电话：</label>${bv.lenderBegin.emer.emerMobilephone} --%>
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td>
								<!-- 屏蔽客户姓名/手机号/身份证号 -->
								<label class="lab"><span class="red">*</span>紧急联系人：</label>***
								<%-- <label class="lab"><span class="red">*</span>紧急联系人：</label>${bv.lenderAfter.emer.emerName} --%>
							</td>
							<td>
								<label class="lab"><span class="red">*</span>联系人移动电话：</label>***
								<%-- <label class="lab"><span class="red">*</span>联系人移动电话：</label>${bv.lenderAfter.emer.emerMobilephone} --%>
							</td>
						</tr>
						<tr>
							<td>
								<label class="lab"><span class="red">*</span>与出借人关系：</label>${bv.lenderBegin.emer.emerRelationship}
							</td>
							<td>
								<!-- 屏蔽客户姓名/手机号/身份证号 -->
								<label class="lab"><span class="red">*</span>联系人固定电话：</label>***
								<%-- <label class="lab"><span class="red">*</span>联系人固定电话：</label>${bv.lenderBegin.emer.emerPhone} --%>
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td>
								<label class="lab"><span class="red">*</span>与出借人关系：</label>${bv.lenderAfter.emer.emerRelationship}
							</td>
							<td>
								<!-- 屏蔽客户姓名/手机号/身份证号 -->
								<label class="lab"><span class="red">*</span>联系人固定电话：</label>***
								<%-- <label class="lab"><span class="red">*</span>联系人固定电话：</label>${bv.lenderAfter.emer.emerPhone} --%>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<label class="lab"><span class="red">*</span>联系人所在地区：</label>
                            	${fns:getProvinceLabel(bv.lenderBegin.emer.address.addrProvince)}&nbsp;
								${fns:getCityLabel(bv.lenderBegin.emer.address.addrCity)}&nbsp;
								${fns:getDistrictLabel(bv.lenderBegin.emer.address.addrDistrict)}
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td colspan="2">
								<label class="lab"><span class="red">*</span>联系人所在地区：</label>
                                ${fns:getProvinceLabel(bv.lenderAfter.emer.address.addrProvince)}&nbsp;
								${fns:getCityLabel(bv.lenderAfter.emer.address.addrCity)}&nbsp;
								${fns:getDistrictLabel(bv.lenderAfter.emer.address.addrDistrict)}
							</td>
						</tr>	
						<tr>
							<td>
                            	<label class="lab"><span class="red">*</span>联系人性别：</label><input type="radio" value="1"  name="cb_emerSex" style="width: 15px"  disabled="disabled"<c:out value="${bv.lenderBegin.emer.emerSex==1?'checked':'' }"/> />男&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  name="cb_emerSex" value="2" style="width: 15px" disabled="disabled" <c:out value="${bv.lenderBegin.emer.emerSex==2?'checked':'' }"/> />女
							</td>
							<td>
								<label class="lab"><span class="red">*</span>账单收取方式：</label>${fns:dictName(dicts.tz_billtaken_mode,bv.lenderBegin.loanInfo.loanBillRecv,'-')}
							</td>
								<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td>
                            	<label class="lab"><span class="red">*</span>联系人性别：</label><input type="radio" value="1"  disabled="disabled" name="ca_emerSex" style="width: 15px;" <c:out value="${bv.lenderAfter.emer.emerSex==1?'checked':'' }"/> /><span>男</span>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  name="ca_emerSex" value="2" style="width: 15px;" disabled="disabled"<c:out value="${bv.lenderAfter.emer.emerSex==2?'checked':'' }"/> /><span>女</span>
							</td>
							<td>
								<label class="lab"><span class="red">*</span>账单收取方式：</label>${fns:dictName(dicts.tz_billtaken_mode,bv.lenderAfter.loanInfo.loanBillRecv,'-')}
							</td>
						</tr> 
					</table>
				</td>				      
            </tr>
        </table>
    </div>
