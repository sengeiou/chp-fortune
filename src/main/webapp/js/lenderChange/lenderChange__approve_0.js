
/**
 * 初始化
 */
$(function() {

	var cbJson = '${workItem.bv.changInfo.changeBegin}';
	var changeBegin = $.parseJSON(cbJson);
	$.each(changeBegin, function(p1, p2) {
		if (document.getElementById("cb_" + p2.name) != null) {
			
		  if (document.getElementById("cb_" + p2.name).nodeName == "SELECT") {
				
			  $("#cb_" + p2.name).html("<option selected='true' value='" + p2.value + "'>"+ p2.value + "</option>");

		  }else {

				$("#cb_" + p2.name).val(p2.value);
			}
		}
		 if (document.getElementById(p2.name) != null) {

			if (document.getElementById(p2.name).nodeName == "SELECT") {

				$("#" + p2.name).html("<option selected='selected'>" + p2.value + "</option>");

			}else {

				$("#" + p2.name).val(p2.value);
			}

		}

	})

	var caJson = '${workItem.bv.changInfo.changeAfter}';
	var changeAfter = $.parseJSON(caJson);
	$.each(changeAfter, function(p1, p2) {

		if (document.getElementById("ca_" + p2.name) != null) {

			if (document.getElementById("ca_" + p2.name).nodeName == "SELECT") {
				$("#ca_" + p2.name).html("<option selected='selected'>" + p2.value	+ "</option>");
				// $("#"+p2.name).val(p2.value);
			} else {

				$("#ca_" + p2.name).val(p2.value);
				// $("#"+p2.name).val(p2.value);
			}

		}

	})
})
