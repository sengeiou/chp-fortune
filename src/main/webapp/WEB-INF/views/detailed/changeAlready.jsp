<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default"/>
<title>状态变更已办</title>
<script src="${ctxWebInf}/js/contract/changeAlready.js" type="text/javascript" ></script>	
</head>
<body>

	<form:form id="searchForm" modelAttribute="cb" action="${ctx}/contract/changeAlready" method="post" >
      <div class="box1 mb10">
       <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
             <tr>
             	<td>
                	<label class='lab'>合同编号：</label>
                	<input type="hidden" id="contCode" name="contCode" type="text"  value="${cb.contCode}">
                	<input type="text" type="text" disabled="disabled" value="${cb.contCode}" class='cf_input_text178'>
                </td>
                <td>
                	<label class='lab'>合同版本：</label>
                	<select class="select78" id="contVersion" name="contVersion"  disabled="disabled" value="${cb.contVersion}">
						<c:forEach items="${fns:getDictList('tz_contract_vesion')}" var="item">
							 <option value="${item.value}" <c:if test="${cb.contVersion==item.value}">selected</c:if>>${item.label}</option>
						</c:forEach>
				   </select>
                </td>
                <td>
					<label class='lab'>合同状态：</label>
					<select class="select78" id="dictContStatus" name="dictContStatus" disabled="disabled" value="${cb.dictContStatus}">
						<c:forEach items="${fns:getDictList('tz_contract_state')}" var="item">
							 <option value="${item.value}" <c:if test="${cb.dictContStatus==item.value}">selected</c:if>>${item.label}</option>
						</c:forEach>
				   </select>
				</td>
            </tr>
            <tr>
             	<td>
                	<label class='lab'>所属门店：</label>
                	<input type="hidden" id="changeInStoresId" name="changeInStoresId" disabled="disabled" value="${cb.changeInStoresId}" class='cf_input_text178'>
                	<input type="text" id="name" name="name" type="text" disabled="disabled" value="${cb.name}" class='cf_input_text178'>
                </td>
                <td>
                	<label class='lab'>入库日期：</label>
                	<input type="text" id="contIncomeDay" name="contIncomeDay" type="text" disabled="disabled" class='cf_input_text178' value="<fmt:formatDate value="${cb.contIncomeDay}" pattern="yyyy-MM-dd"/>" readonly="readonly"/>
                </td>
                <td>
					<label class='lab'>申请人：</label>
					<input type="text" id="changeApply" name="changeApply" type="text" disabled="disabled" value="${cb.changeApply}" class='cf_input_text178'>
				</td>
            </tr>
            <tr>
                <td>
                	<label class='lab'>申请说明：</label>
                	<input type="text" id="changeExplain" name="changeExplain" tabindex="textarea" disabled="disabled" value="${cb.changeExplain}" class='cf_input_text178'></input>
                </td>
             </tr>
             <tr>
                <td>
                	<label class='lab'>理财经理：</label>
                	<select class="select78" id="contBelongEmpid" name="contBelongEmpid" value="${cb.contBelongEmpid}" disabled="disabled">
	                    <option value="">请选择</option>
						<c:forEach items="${list}" var="item">
							 <option value="${item.userId}" <c:if test="${cb.contBelongEmpid==item.userId}">selected</c:if>>${item.userName}</option>
						</c:forEach>
				   </select>
                </td>
                <td>
	                	<label class='lab'>下载附件：</label>
	                	<input class="btn btn_sc SunIAS" type="button" value="扫描控件" /></td>
                </td>
                <td>
                	<label class='lab'>转借门店：</label>
                	<input type="hidden" id="changeOutStoresId" name="changeOutStoresId" type="text" disabled="disabled" value="${cb.changeOutStoresId}" class='cf_input_text178'>
                	<input type="text" id="name" name="name" type="text" disabled="disabled" value="${cb.name}" class='cf_input_text178'>
                </td>
            </tr>
        </table>
      </form:form>
      </div>
	<ul class="tright pr30">
		<td>
           	 <input type="button" value="返回" onclick="window.location.href='${ctx}/contract/changeAlreadyList';" class='btn cf_btn-primary'>
        </td>
    </ul>
    
     <div id="scan" style="display:none;">
        <iframe name="scaniframe" width="99%" height=930 frameborder=0 scrolling=auto></iframe>
	 </div>
	 
</body>
</html>