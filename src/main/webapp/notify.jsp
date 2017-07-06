<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<button id="testBut" value="测试">测试</button>
</body>
<script type="text/javascript">
document.querySelector("#testBut").addEventListener('click', notifyMe, false);
 
function notify() {
     if (window.webkitNotifications) {
         if (window.webkitNotifications.checkPermission() == 0) {
             var notification_test = window.webkitNotifications.createNotification("http://images.cnblogs.com/cnblogs_com/flyingzl/268702/r_1.jpg", '标题', '内容'+new Date().getTime());
             notification_test.display = function() {}
             notification_test.onerror = function() {}
             notification_test.onclose = function() {}
             notification_test.onclick = function() {this.cancel();}
             
             notification_test.replaceId = 'Meteoric';
  
             notification_test.show();
                         
             var tempPopup = window.webkitNotifications.createHTMLNotification(["http://www.baidu.com/", "http://www.soso.com"][Math.random() >= 0.5 ? 0 : 1]);
            tempPopup.replaceId = "Meteoric_cry";
             tempPopup.show();
         } else {
             window.webkitNotifications.requestPermission(notify);
         }
     } 
 }
function notifyMe() {
	 if (Notification.permission !== 'denied') {
	   Notification.requestPermission(function (permission) {
	      if (permission === "granted") {
	    	  var notification = new Notification("通知",{
	  	        body: "AAAAAA",
	  	        icon: "././img/xinhe.jpg",
	  	       	tag:"tag"
	  	    }); 
	      }
	    });
	  }

	}
</script>
</html>