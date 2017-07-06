<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf }/js/trusteeship/trusteeshipAccount.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
<title></title>
</head>
<body>
	<div class="body_r">
	    <div class="box1 mb10">      
	    	<form action="${ctx}/trusteeship/account/list" method="post" id="searchForm">
		        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		             <tr>
		             	<td>
		             		<label class="lab">出借编号：</label>
		                	<input type="text" name="lendCode" class="cf_input_text178" value="${search.lendCode }">
		             	</td>
		             	<td>
			                <label class="lab">客户姓名：</label>
			                <input type="text" name=customerName class="cf_input_text178" value="${search.customerName }">
		                </td>
		                <td>
			                <label class="lab">客户编号：</label>
			                <input type="text" name=customerCode class="cf_input_text178" value="${search.customerCode }">
		                </td>
		            </tr>
		            <tr>
		             	<td>
		             		<label class="lab">证件号码：</label>
		                	<input type="text" name="customerCertNum" class="cf_input_text178" value="${search.customerCertNum }">
		             	</td>
		             	<td>
			                <label class="lab">开户银行：</label>
			                <select name="bankId" multiple="multiple">
								<c:forEach items="${fns:getDictList('tz_open_bank') }" var="bank">
									<option value="${bank.value }" <c:if test="${fns:multiOption(search.bankId,bank.value)}">selected</c:if> >${bank.label }</option>
								</c:forEach>
							</select>
		                </td>
		                <td>
			                <label class="lab">托管状态：</label>
			                <select name="applyHostedStatus">
			                	<option value="">请选择</option>
								<c:forEach items="${fns:getDictList('tz_trust_state') }" var="item">
									<c:if test="${item.label == '开户申请中' or item.label == '开户失败'}">
										<option value="${item.value }" <c:if test="${search.applyHostedStatus == item.value}">selected</c:if> >${item.label }</option>
									</c:if>
								</c:forEach>
							</select>
		                </td>
		            </tr>
		        </table>	
	        	<div class="tright pr30">
	        		<button class="btn cf_btn-primary search">搜索</button>
	        		<input class="btn cf_btn-primary" type="reset" value="清除">
	        	</div>
	        </form>
	    </div>
	    <p class="mb10 ml10">
	    	<form method="post">
	    		<auth:hasPermission key="cf:trusteeshipcreate:exportexcel">
			    	<input type="button" class="btn btn_sc ml10 mb10 output" value="Excel导出"/>
			    </auth:hasPermission>
			    <auth:hasPermission key="cf:trusteeshipcreate:intoexcel">
			    	<input type="button" class="btn btn_sc mb10 input" value="Excel导入"/>
			    </auth:hasPermission>
			    <auth:hasPermission key="cf:trusteeshipcreate:exportprotocol">
			    	<input type="button" class="btn btn_sc mb10 protocol" value="导出Excel协议库 "/>
			    </auth:hasPermission>
			    <auth:hasPermission key="cf:trusteeshipcreate:batchaccount">
			    	<input type="button" class="btn btn_sc mb10 bulk_account" value="批量开户"/>
			    </auth:hasPermission>
			    <auth:hasPermission key="cf:trusteeshipcreate:join">
			    	<input type="button" class="btn btn_sc mb10 toAdd  protocol_docking" value="协议库对接"/>
			    </auth:hasPermission>
			    <auth:hasPermission key="cf:trusteeshipcreate:hand">
			    	<input type="button" class="btn btn_sc mb10 auto_account" value="${autoFlag }"/>
			    	当前状态：<l class="auto_account">${autoFlag }</l>
			    </auth:hasPermission>
		    	<div class="dataList">
			    	<%@ include file="/WEB-INF/views/trusteeship/account/list.jsp"%>
			    </div>
		    </form>
		</p>	    
	</div>
	<div id="modal-sub" class="modal fade subwindow">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
					<input type="button" value="导入" class="btn cf_btn-primary" id="subImport"/>
					<input type="button" value="关闭" class="btn cf_btn-primary" id="subClose"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
