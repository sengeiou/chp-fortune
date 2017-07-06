<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<div class="body_r">
    <div class="box1 mb10">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab">计划划扣日期：</label> ${deductDate }</td>
            </tr>
			<tr>
                <td><label class="lab">待出借审批：</label> ${waitApprove }</td>
            </tr>
            <tr>
                <td><label class="lab">审批通过：</label> ${passApprove }</td>
            </tr>
             <tr>
                <td><label class="lab">文件合成失败：</label> ${failDocument}</td>
            </tr>
            <tr>
                <td><label class="lab">审批不通过：</label> ${failApprove }</td>
            </tr>
            <tr>
                <td><label class="lab">总和：</label> ${totalApprove }</td>
            </tr>
        </table>	
    </div>
</div>

