$(function() {
	var interval = setInterval(function(){
		
		$.ajax({
	        type: 'post',
	        url:  ctx+"/notifyController/findNotify",
	        dataType: "json",
	        data:null,
	        timeout: 10000,
	        success: function(data) {
				if(data!=null){
					if(data.auth=="false"){
						clearInterval(interval);
						return false;
					}
					notifyMe(data)
				}
			}
	    })},5*60*1000);
});

function notifyMe(data) {
	 if (Notification.permission !== 'denied') {
	   Notification.requestPermission(function (permission) {
	      if (permission === "granted") {
	    	  var notification = new Notification("通知",{
	  	        body: data.body,
	  	        icon: ctxWebInf+"/img/xinhe.jpg",
	  	        tag:'notificationTag'
	  	    }); 
	    	if(data.phoneUrl!=""&&data.cardUrl!=""){
	    		notification.onclick = function(){
	    			window.open(ctx+data.phoneUrl);
		    		window.open(ctx+data.phoneUrl);
		    	  }
	    	}else if(data.phoneUrl!=""){
	    		notification.onclick = function(){
		    		  window.open(ctx+data.phoneUrl);
		    	  }
	    	}else if(data.cardUrl!=""){
	    		notification.onclick = function(){
		    		  window.open(ctx+data.cardUrl);
		    	  }
	    	}
	      }
	    });
	  }

	}
