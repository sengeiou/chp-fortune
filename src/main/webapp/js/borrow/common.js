/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: zh_CN
 */
$(function(){
    //与from-check数值比较
    jQuery.validator.addMethod("from-checkFloat", function(value, element, arg) {
	    	if (value == "") {
	            return true;
	        }
	        var toValue = value.replace(/\//g, '');
	        var fromValue = $(arg).val().replace(/\//g, '');
	        if (fromValue == "") {
	            return true;
	        }
	        if (parseFloat(fromValue) > parseFloat(toValue)) {
	            return false;
	        }
	        return true;
	    },"From~To的值不正确"
    );
    
    // 判断大于0的整数
    jQuery.validator.addMethod("positive_integer_number",function(value,element,arg){
    	if(value==''){
    		return true;
    	}
    	var re = /^(0|[1-9][0-9]*)$/ ;  
    	
    	if(!re.test(value)){
    		return false;
    	}
    	  return true;

    },"请输入大于等于0的整数");
    
    // 清除按钮控制字段不被清除
    $("#formReset").click(function(){
    	var pageSize = $.cookie("pageSize");
    	var pageNo = $.cookie("pageNo");
        $("#pageNo").val(pageNo);
       $("#pageSize").val(pageSize);
    }); 
    
})

/**
	 * 解决js浮点减法
	 * @param param1
	 * @param param2
	 * @returns
	 */
	function jsSub(param1,param2){
	    var r1,r2,m,n;
	    try {
	    	r1=param1.toString().split(".")[1].length
	    } catch (e) {
			r1=0
		}
	    try {
	    	r2=param2.toString().split(".")[1].length
	    }catch(e){
	    	r2=0
	    }
	    m=Math.pow(10,Math.max(r1,r2));
	    //动态控制精度长度
	    n=(r1>=r2)?r1:r2;
	    return ((param1*m-param2*m)/m).toFixed(n);
	}	

// ajax 待结果返回后执行
function contents_ajax(url, data, type, successCal) {
    $.ajax({
        type: type,
        url: url,
        dataType: "json",
        data: data,
        async: false,
        timeout: 300000,
        beforeSend: function(){
        	if(loadingMarkShow.loadingDiv != null && loadingMarkShow.loadingDiv.length > 0){
        		return;
        	}
        	loadingMarkShow();
        },
        success: successCal
    });
}

/**
 * 格式化货币
 * @param num
 * @returns {String}
 */
function formatCurrency(num) {  
    num = num.toString().replace(/\$|\,/g,'');  
    if(isNaN(num)){
    	num = "0";  
    }  
    sign = (num == (num = Math.abs(num)));  
    num = Math.floor(num*100+0.50000000001);  
    cents = num%100;  
    num = Math.floor(num/100).toString();  
    if(cents<10){
    	cents = "0" + cents;  
    }  
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++){
    	num = num.substring(0,num.length-(4*i+3))+','+  
    	num.substring(num.length-(4*i+3));  
    }  
    return (((sign)?'':'-')  + num + '.' + cents);  
}

/**
 * 还原货币格式化函数
 * @param num
 * @returns
 */
function restoreFormatCurrency(num){  
    var num1=num.replace('￥','').replace(',','').replace(/,/g,'');   
   return num1;  
}  




