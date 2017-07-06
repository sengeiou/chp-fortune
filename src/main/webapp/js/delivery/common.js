/**
 * 页面输入自动获取搜索结果
 */
var source = function(request, response, elem){
  	 contents_getJsonForSync(ctx + '/delivery/customer/findInfo',{"code":request.term},'POST',
       	 // 成功取得后的处理
			 function(json){                 
                var val = [];
       		 if(json != null && json.length > 0){
       			 $.each(json,function(){
       				val.push({
       				 "label": '登录名：'+ (this.loginName == null ? '' : this.loginName) +' | 理财经理：'+ (this.nfManagerName == null ? '' : this.nfManagerName)  +' | 团队经理：'+ (this.nTeamManagerName == null ? '' : this.nTeamManagerName) + ' | 营业部：'+ (this.nOrgName == null ? '' : this.nOrgName),
                     "value": this.nfManagerCode,
                     "nfManagerName":this.nfManagerName,
                     "nTeamManagerName":this.nTeamManagerName,
                     "teamName":this.teamName,
                     "nOrgName":this.nOrgName,
                     "nfManagerId":this.nfManagerId,
                    });
       			 });
       		 }else{
       			 val.push({
                        "label": "您所查询的信息不存在！",
                        "value": "",
                        "id": ""
                    });
       		 }
       		 response(val, request.term);
       	 },
       	 // 取得失败后的处理
       	 function(error){}
  	 );
   };
   var selects = function(event, ui, elem){
  	 if (ui && typeof ui.item == "object"){
  		 $("#nfManagerCode").val(ui.item.value);
  		 $("#nfManagerName").val(ui.item.nfManagerName);
  		 $("#nTeamManagerName").val(ui.item.nTeamManagerName);
  		 $("#nTeamName").val(ui.item.teamName)
  		 $("#nOrgName").val(ui.item.nOrgName);
  		 $("#nfManagerId").val(ui.item.nfManagerId);
  	 }
   }
   $(document).ready(function() {
	   autocompleteSearch($('.auto'),source,selects);
	 });