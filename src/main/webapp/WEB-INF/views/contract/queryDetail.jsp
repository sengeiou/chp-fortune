<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
   		<table class="table1" cellpadding="0" cellspacing="0" border="0" width='100%'>
   			<tr>
               <td>
	                <label class="lab">合同生成日期：</label>
	                <input type="text"class="cf_input_text178" readonly="readonly" id="orgName" value="${fns:getFormatDate(lend.applyDate,'yyyy-MM-dd')}">
                </td>
                <td>
	                <label class="lab">补充协议编号：</label>
	                <input type="text" class="cf_input_text178" readonly="readonly" id="applyNo" value="${lend.applyProtocolNo}">
                </td>
				<td>
					<label class='lab'>理财经理：</label>
					<input type="text" class="cf_input_text178" readonly="readonly" id="applyDistNo" value="${lend.managerName}">
				</td>
            </tr>
            <tr>
				<td>
					<label class="lab">产品：</label>
					<input type="text" class="cf_input_text178" readonly="readonly" id="applyDistNo" value="${fns:getProductLabel(lend.productCode)}">
				  
				</td>
				<td>
					<label class="lab">出借时间：</label>
					<input type="text" class="cf_input_text178" readonly="readonly" id="applyInventory" value="${fns:getFormatDate(lend.lendDate,'yyyy-MM-dd')}">
				</td>
				<td>
					<label class="lab">出借到期时间：</label>
						<input type="text" class="cf_input_text178" readonly="readonly" id="applyInventory" value="${fns:getFormatDate(lend.expireDate,'yyyy-MM-dd')}">
				</select>
				</td>
            </tr>
            <tr>
                <td colspan="3">
	                <label class="lab">出借状态：</label>
	                <input type="text" class="cf_input_text178" readonly="readonly" id="applyBy" value="${fns:dictName(dicts.tz_lend_state,lend.lendStatus,'') }">
                </td>
            </tr>
        </table>