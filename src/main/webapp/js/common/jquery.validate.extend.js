/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: zh_CN
 */
(function($) {
	
	$.validator.setDefaults({
        ignore: ':not(select:hidden, input:visible, textarea:visible)',

        errorPlacement: function (error, element) {
            if (element.is('select')) {
                error.insertAfter($(element).next());
            } else if(element.is('.check_must')){
            	//营业部插件
            	error.insertAfter(element);
            } else if(element.is('[type=radio],[type=checkbox]')){
            	if(element.parent().is('td')){
            		error.appendTo(element.parent());
            	}
            } else {
                error.insertAfter(element);
            }
        }
    });
	
	//半角字母（英文大写･小写）
    jQuery.validator.addMethod("alphabet", function(value, element) {
        return this.optional(element) || /^([a-zA-z\s]+)$/.test(value);
        }, "请输入半角英文"
    );
	
    //半角字母（英文大写･小写）
    jQuery.validator.addMethod("alphabet2", function(value, element) {
    	//屏蔽客户姓名/手机号/身份证号
    	if(value == '***'){
    		return true;
    	}
        return this.optional(element) || /^([a-zA-z\s]+)$/.test(value);
        }, "请输入半角英文"
    );

    //半角字母（英文大写･小写）或者数字
    jQuery.validator.addMethod("alphanum", function(value, element) {
        return this.optional(element) || /^([a-zA-Z0-9]+)$/.test(value);
        }, "请输入半角英数字"
    );

    //邮编（例:100-000）
    jQuery.validator.addMethod("postnum", function(value, element) {
        return this.optional(element) || /^\d{3}\d{3}$/.test(value);
        }, "请输入正确的邮政编码（例:100000）"
    );

    //手机号
    jQuery.validator.addMethod("mobilenum", function(value, element) {
        return this.optional(element) || /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/.test(value);
        }, "请输入正确的手机号"
    );
    
    //手机号
    jQuery.validator.addMethod("mobilenum2", function(value, element) {
    	//屏蔽客户姓名/手机号/身份证号
    	if(value == '***'){
    		return true;
    	}
    	
        return this.optional(element) || /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/.test(value);
        }, "请输入正确的手机号"
    );
   
    //下拉框必须
    jQuery.validator.addMethod("select_required", function(value, element) {
        return value != '' && value != -1;
        }, $.validator.messages.required
    );
    //邮箱
    jQuery.validator.addMethod("email", function(value, element) {
        return this.optional(element) || /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(value);
        }, "请输入正确的邮箱地址"
    );
    //身份证
    jQuery.validator.addMethod("id_card", function(value, element) {
    	var idcard = /(^\d{15}$|^\d{18}$|^\d{17}(\d|X|x)$)/;
        return this.optional(element) || (idcard.test(value));
        }, "请输入正确的身份证号"
    );
    //日期FROM・TO验证
    //指定日付以后
    jQuery.validator.addMethod("datemin", function(value, element, arg) {
        // blur调用时做验证
        if (/^\d{4}\/\d{1,2}\/\d{1,2}$/.test(value)) {
            // UNIX形式变换后比较
            return Math.round(new Date(value) / 1000) >= arg;
        } else {
            return true;
        }
        }, function(argment) {
            var date = new Date(argment * 1000);
            return date.getFullYear() + "/" + ("0" + (date.getMonth() + 1)).slice(-2) + "/" + ("0" + date.getDate()).slice(-2) + "以降の日付を入力してください。";
        }
    );
    //指定日付以前
    jQuery.validator.addMethod("datemax", function(value, element, arg) {
        // blur调用时做验证
        if (/^\d{4}\/\d{1,2}\/\d{1,2}$/.test(value)) {
            // UNIX形式变换后比较
            return Math.round(new Date(value) / 1000) <= arg;
        } else {
            return true;
        }
        }, function(argment) {
            var date = new Date(argment * 1000);
            return date.getFullYear() + "/" + ("0" + (date.getMonth() + 1)).slice(-2) + "/" + ("0" + date.getDate()).slice(-2) + "以前の日付を入力してください。";
        }
    );
    //与from-check日期比较
    jQuery.validator.addMethod("from-checkDate", function(value, element, arg) {
	    	if (value == "") {
	            return true;
	        }
	        var toValue = value.replace(/\//g, '');
	        var fromValue = $(arg).val().replace(/\//g, '');
	        if (fromValue == "") {
	            return true;
	        }
	        if(jQuery.browser.mozilla) {  //火狐浏览器
	        	var fromValueA = fromValue.replace("年","-").replace("月","-").replace("日","").split(/-|:| /);
	        	var fromDate = new Date(fromValueA[0], fromValueA[1] - 1, fromValueA[2], fromValueA[3], fromValueA[4], fromValueA[5]);
	        	var toValueA = toValue.replace("年","-").replace("月","-").replace("日","").split(/-|:| /);
	        	var toDate = new Date(toValueA[0], toValueA[1] - 1, toValueA[2], toValueA[3], toValueA[4], toValueA[5]);
	        	if(fromDate > toDate) {
	        		return false;
	        	}
	        } else {
	        	if (new Date(fromValue) > new Date(toValue)) {
		            return false;
		        }
	        }
	        return true;
	    },"From~To的日期不正确"
    );
    //与from-check数值比较
    jQuery.validator.addMethod("from-checkInt", function(value, element, arg) {
	    	if (value == "") {
	            return true;
	        }
	        var toValue = value.replace(/\//g, '');
	        var fromValue = $(arg).val().replace(/\//g, '');
	        if (fromValue == "") {
	            return true;
	        }
	        if (parseInt(fromValue) > parseInt(toValue)) {
	            return false;
	        }
	        return true;
	    },"From~To的值不正确"
    );
    //固定电话
    jQuery.validator.addMethod("telephone", function(value, element, arg) {
    	var telephone_regexp = /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)/;
        return this.optional(element) || (telephone_regexp.test(value));
	    },"请输入正确的固定电话"
    );
    //固定电话输入1至12位数字
    jQuery.validator.addMethod("telephonenum", function(value, element, arg) {
    	var telephone_regexp = /^\d{1,12}$/;
        return this.optional(element) || (telephone_regexp.test(value));
	    },"请输入正确的固定电话1到12位数字"
    );
    //固定电话输入1至12位数字
    jQuery.validator.addMethod("telephonenum2", function(value, element, arg) {
    	//屏蔽客户姓名/手机号/身份证号
    	if(value == '***'){
    		return true;
    	}
    	var telephone_regexp = /^\d{1,12}$/;
        return this.optional(element) || (telephone_regexp.test(value));
	    },"请输入正确的固定电话1到12位数字"
    );
    //联系方式
    jQuery.validator.addMethod("contact", function(value, element, arg) {
    	var telephone_regexp = /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)/;
    	var mobile_regexp =/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/;
    	return this.optional(element) || telephone_regexp.test(value)|| mobile_regexp.test(value);
	    },"请输入正确的联系方式"
    );
    //银行账户
    jQuery.validator.addMethod("bankAccount", function(value, element, arg) {
    	var reg = /^\d{16,19}$/;
    	return this.optional(element) || reg.test(value);
	    },"银行账号必须规范（16到19位数字）"
    );
    //校验中文
    jQuery.validator.addMethod("chinese", function(value, element, arg) {
    	var reg = /^[\u4e00-\u9fa5]+$/;
    	return this.optional(element) || reg.test(value);
	    },"请输入中文"
    );
    //校验中文
    jQuery.validator.addMethod("chinese2", function(value, element, arg) {
    	//屏蔽客户姓名/手机号/身份证号
    	if(value == '***'){
    		return true;
    	}
    	var reg = /^[\u4e00-\u9fa5]+$/;
    	return this.optional(element) || reg.test(value);
	    },"请输入中文"
    );
    //校验英文
    jQuery.validator.addMethod("english", function(value, element, arg) {
    	var reg = /^[a-z|A-Z]+$/;
    	return this.optional(element) || reg.test(value);
	    },"请输入英文"
    );
    //校验中英文
    jQuery.validator.addMethod("ChEn", function(value, element, arg) {
    	var reg = /^[\u4e00-\u9fa5|a-z|A-Z]+$/;
    	return this.optional(element) || reg.test(value);
	    },"请输入中英文"
    );
    // 校验不带逗号的数字，如：【9,000】校验失败
    jQuery.validator.addMethod("numberWithoutComma", function(value, element, arg) {
    	var reg = /^([0-9.]+)$/;
    	return this.optional(element) || reg.test(value);
    },"请输入合法的数字"
    );
    //大于
    jQuery.validator.addMethod("greaterThan", function(value, element, arg) {
    	var greaterThan = arg.replace(/\//g, '');
        if (greaterThan == "") {
            return true;
        }
        if (parseFloat(value) < parseFloat(greaterThan)) {
            return false;
        }
    	return true;
	    },"请输入大于{0}的值"
    );
    jQuery.validator.addMethod("between", function(value, element, arg) {
    	var args = arg.split("-");
    	value = value.replace(',','');
        if (parseFloat(args[0]) > parseFloat(value)) {
            return false;
        }else if(parseFloat(args[1]) < parseFloat(value)){
        	return false;
        }
    	return true;
	    },"请输入范围在{0}的值"
    );
    //校验具体支行输入规则（中文或者中文+数字，且最后一位不能是数字）
    jQuery.validator.addMethod("checkbranch", function(value, element, arg) {
    	var reg = /[\u4e00-\u9fa5]+(?!.[1-9]\d)$/;
    	return this.optional(element) || reg.test(value);
	    },"具体支行只能为中文或者中文+数字，最后一位不能为数字"
    );
    // 设置金额必须是一万的倍数
    jQuery.validator.addMethod("divisible", function(value, element, arg) {
    	if(arg-0==0){
    		return true;
    	}else{
    		return value%arg==0;
    	}
	    },"数值只能是{0}的整数倍"
    );
    //军官证
    jQuery.validator.addMethod("militaryId", function(value, element, arg) {
    	var reg = /南字第(\d{8})号|北字第(\d{8})号|沈字第(\d{8})号|兰字第(\d{8})号|成字第(\d{8})号|济字第(\d{8})号|广字第(\d{8})号|海字第(\d{8})号|空字第(\d{8})号|参字第(\d{8})号|政字第(\d{8})号|后字第(\d{8})号|装字第(\d{8})号/; 
    	value =  value.replace(/(^\s*)|(\s*$)/g, "");
    	return this.optional(element) || reg.test(value);
	    },"请输入正确的军官证件号"
    );
    //判断数值类型，包括整数和浮点数
    jQuery.validator.addMethod("isNumber", function(value, element) {       
         return this.optional(element) || /^[-\+]?\d+$/.test(value) || /^[-\+]?\d+(\.\d+)?$/.test(value);       
    }, "匹配数值类型，包括整数和浮点数");
}(jQuery));
