/**
 * JS常量设置
 * 例子：
 *   定义：$.define('FORTUNE','fortune');
 *   获取：_.FORTUNE
 * 
 * @author 朱杰
 */
jQuery(document).ready(function($){
  
  $.extend({
    
    // 定义entry的值为value
    define : function(entry,value){
      
      var prefix = $.__pluginCommon.definePrefix;
      
      if(!(prefix in window))window[prefix] = new Object();
      
      if(entry in window[prefix]){
        alert('常量重复');
        return(false);
      }else{
        window[prefix][entry] = value;
        return(true);
      }
    },
    
    // entry的常量是否存在
    defined : function(entry){
      
      var prefix = $.__pluginCommon.definePrefix;
      
      if((prefix in window) && (entry in window[prefix])){
        return(true);
      }
      
      return(false);
    },
    
    // 前缀设定
    __pluginCommon : {
      definePrefix : '_'
    }
    
  });
});
