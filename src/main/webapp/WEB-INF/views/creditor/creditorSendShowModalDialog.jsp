<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/creditor/creditorSendList.js" type="text/javascript"></script>
  <div>
     <input type="hidden" id="sta" value="${creditorTradeEx.matchingFileStatus}"/>
     <input type="hidden" id="sendStatus" value="${creditorTradeEx.matchingFileSendStatus}"/>
     <input type="hidden" id="mcid" value="${creditorTradeEx.matchingId}"/>
     <input type="hidden" id="lCode" value="${creditorTradeEx.lendCode}" />
      <input type="hidden" id="matPayStatus" value="${creditorTradeEx.matchingPayStatus}" />
     <div id="1" style="display:none">
         <button class="btn btn_sc ml10 mt10" id="filePreview">预览（债权转让及受让协议）</button><br /><br />
         <button class="btn btn_sc ml10" id="fileDownloadWord">下载协议word</button><br/><br/>
         <button class="btn btn_sc ml10" id="fileDownloadPdf">下载协议pdf</button><br/><br/>
     </div>
     <div id="2" style="display:none">
         <button class="btn btn_sc ml10" id="sendEmail">发送邮件</button><br/><br/>
     </div>
     <div id="3" style="display:none">
         <button class="btn btn_sc ml10 mt10" id="synthesisFile">重新合成</button><br/><br/> 
     </div>
     <div id="4" >
         <button class="btn btn_sc ml10" id="cancelCreditor">撤销重匹</button>
     </div>
   </div>
   <script type="text/javascript">
        // 1 是已结算 
	    var status = $("#sta").val();
		var sendStatus = $("#sendStatus").val();
		var matchingPayStatus = $("#matPayStatus").val();
		if(status == '1'){
			document.getElementById("1").style.display='';
			document.getElementById("2").style.display='';
		}else {
			document.getElementById("3").style.display='';
		}
		/* if(matchingPayStatus == "0" || matchingPayStatus == ""){
			document.getElementById("4").style.display='';
		} */
		
   </script>