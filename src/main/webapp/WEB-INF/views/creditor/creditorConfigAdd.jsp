<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf}/js/creditor/creditorConfigAdd.js" type="text/javascript"></script>
<title>新增匹配</title>
</head>
<body>
<div class="body_r">
    <div class="box1 mb10">
      <form:form id="searchForm">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
           <tr>
                <td>
                    <label class="lab">账单日：</label>
                        <select class="select78" name="configCreditDay" select_required="1">
                            <option value="">请选择</option>
                            <c:forEach items="${dicts.tz_bill_day}" var="item">
                                 <option value="${item.value }" <c:if test="${creditorConfig.configCreditDay==item.value}">selected</c:if>>${item.label }</option>
                            </c:forEach>
                        </select>
                </td>
                <td>
                    <label class="lab">还款日：</label>
                        <select class="select78" name="configRepayDay" select_required="1">
                            <option value="">请选择</option>
                            <c:forEach items="${dicts.tz_repay_day}" var="item">
                               <option value="${item.value }" <c:if test="${creditorConfig.configRepayDay==item.value}">selected</c:if>>${item.label }</option>
                            </c:forEach>
                        </select>
                </td>
                <td>
                    <label class="lab">账单类型：</label>
                        <select class="select78" name="dictConfigStatus" select_required="1">
                            <option value="">请选择</option>
                            <c:forEach items="${dicts.tz_bill_state}" var="item">
                               <option value="${item.value }" <c:if test="${creditorConfig.dictConfigStatus==item.value}">selected</c:if>>${item.label }</option>
                            </c:forEach>
                        </select>
                </td>
           </tr>
            <tr>
                <td>
                    <label class="lab">状态：</label>
                        <select class="select78" name="dictConfigZdr" select_required="1">
                            <option value="">请选择</option>
                            <c:forEach items="${dicts.com_use_flag}" var="item">
                               <option value="${item.value }" <c:if test="${creditorConfig.dictConfigZdr==item.value}">selected</c:if>>${item.label }</option>
                            </c:forEach>
                        </select>
                </td>
            </tr>
        </table>
       </form:form>
    </div>
    <div class="tright mt20 pr30">
            <input type="submit" class="btn cf_btn-primary" id="submit" value="提交" />
            <input type="reset" class="btn cf_btn-primary" onClick="window.history.back(-1);" value="返回" />
        </div>
</div>
</body>
</html>