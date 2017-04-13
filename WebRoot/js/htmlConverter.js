/**
 * zgf
 */

function convertData(json) {
	if (json.isSukCandidate == "1") {
		return '';
	}
	var data_type = json.valueType;
	var num = "";
	if(data_type.indexOf("NUMBER")>-1){
		num =  "data_type=\"NUMBER\" pattern=\"^\d+(\.\d{0,2})?$\" data-group=\"\"";
	}
	var _html = '';
	_html += '<div '+num+' attr_id = '
			+ json.id
			+' attr_name='
			+json.attrName
			+ ' class="control-group" '
			+ (json.isRequired == "1" ? 'required="true" picklist="'
					+ json.attrVals + '"' : '') + '>';
	_html += '<label class="group-title line-title" '
			+ (json.isRequired == "1" ? 'style="color:#0099cc;font-weight:bold;"'
					: '')
			+ '>'
			+ (json.isRequired == "1" ? '<span style="float:left;">*&nbsp;&nbsp;</span>'
					: '') + json.attrName + '：</label>';
	_html += '<div class="top_div"><div class="group-content" style="position: absolute;width:30%" attr_id='+json.id+'>';
	var _type = json.valueType;
	var _candidate = json.sukCandidate;
	if(json.isUserDefined==1){
//		_html += '<input type="text"  class="input-text" name="'
//			+ json.attrName + ',' + json.id
//			+ '" maxlength="200" placeholder="请输入' + (json.valueType.indexOf('NUMBER')!=-1?'数字" data_type="NUMBER" pattern="^\d+(\.\d{0,2})?$" hint="数字"':json.attrName)
//			+ '" data-key="weight"  value="">';
//	
		_html+="<span class=\"noselectric select2-hidden-accessible\">\
            <input type=\"text\" class=\"input-text\" value=\"\"  placeholder=\"请选择\"  name='"+json.attrName+","+json.id+"' style=\"cursor: pointer;margin:0px 0px 2px 0px;width: 70%;\" data-key=\"\" "+num+">"
            +(_type.indexOf("_") > 0 ? _type.split("_")[1] : "")
    		+ (json.isSukCandidate == "1" ? '<span style="display: inline-block;color:#0099cc;font-weight:bold;">◀ 销售</span>': '')
    		+		
            "</span>\
            ";
	_html+="<div class=\"dropdown-content hid\" style=\"position: absolute;background: #fff;border: 1px solid #ccc;width: 70%;z-index: 10;\">\
			 <div class=\"scroll-wrapper\">\
			 		 <div class=\"scrollbar-dynamic scroll-content scroll-content2\" style=\"\">\
			 				<ul class=\"dropdown-menu\">";
	if((!!json.attrVals))
		{
		//json.attrVals="";
		var vals = (json.attrVals).split(",");
			for (var i = 0; i < vals.length; i++) {
				if(vals[i]!="")
		_html+="<li class=\"js-dropdown-item\" onclick=\"addToinput(this)\" style=\"margin-bottom:0px;border-bottom: 1px solid #ccc;text-align: center;cursor: pointer;line-height: 35px;\" data-val='"+vals[i]+"'><span>"+vals[i]+"<span onclick=\"deleteitem(event)\" style=\"float: right;margin-right: 13px;\"><a style=\"color:#fff\">X</a></span><\span></li>";
		}
		
		}

	_html+="</ul></div></div>";
	_html+="<div class=\"dropdown-append input-group-append\" style=\"line-height: 35px;\">\
			<span style=\"display: inline-block;width: 70%;\">\
			<input type=\"text\" "+num+" class=\"js-dropdown-item-append-text js-attr-val-input\" style=\"display: inline-block;width: 100%;outline: none;line-height: 35px;font-size: 14px;padding: 0px 0px 0px 10px;\"/>\
			</span>\
	        <span class=\"input-group-btn pull-right\" style=\"display: inline-block;width:28.5%;text-align: center;background:#0099cc\">\
	           <span class=\"btn-info js-dropdown-item-append\" style=\"height:35px;outline: none;cursor: pointer;color:#fff\">添加</span>\
	         </span>";

	_html += "</div></div></div></div></div></div>";
	}else if (!!json.attrVals) {// picklist类型
		_html+="<span class=\"noselectric select2-hidden-accessible\">\
            <input type=\"text\" "+num+" class=\"input-text\" value=\"\"  placeholder=\"请选择\" readonly  name='"+json.attrName+","+json.id+"' style=\"cursor: pointer;margin:0px 0px 2px 0px;width: 70%;\" data-key=\"\" data-group=\"\">"
            +(_type.indexOf("_") > 0 ? _type.split("_")[1] : "")
    		+ (json.isSukCandidate == "1" ? '<span style="display: inline-block;color:#0099cc;font-weight:bold;">◀ 销售</span>': '')
    		+		
            "</span>\
            ";
	_html+="<div class=\"dropdown-content hid\" style=\"position: absolute;background: #fff;border: 1px solid #ccc;width: 70%;z-index: 10;\">\
			 <div class=\"scroll-wrapper\">\
			 		 <div class=\"scrollbar-dynamic scroll-content scroll-content2\" style=\"\">\
			 				<ul class=\"dropdown-menu\">";

	if((!!json.attrVals))
	{
	//json.attrVals="";
	var vals = (json.attrVals).split(",");
	_html+="<li class=\"js-dropdown-item\" onclick=\"addToinput(this)\" style=\"margin-bottom:0px;border-bottom: 1px solid #ccc;text-align: center;cursor: pointer;line-height: 35px;\" data-val=''><span style=\"color:#000\">===请选择===<\span></li>";
	
	for (var i = 0; i < vals.length; i++) {
		if(vals[i]!="")
	_html+="<li class=\"js-dropdown-item\" onclick=\"addToinput(this)\" style=\"margin-bottom:0px;border-bottom: 1px solid #ccc;text-align: center;cursor: pointer;line-height: 35px;\" data-val='"+vals[i]+"'><span>"+vals[i]+"<\span></li>";
	}
	
	}
	_html+="</ul></div></div>";

	_html += "</div></div></div></div></div>";
//		_html += '<span><select class="noselectric select2-hidden-accessible" id="js-item-brand" name="'
//				+ json.attrName
//				+ ','
//				+ json.id
//				+ '" required="" tabindex="-1" aria-hidden="true">'
//		var vals = (json.attrVals).split(",");
//		_html += '<option value="" selected>===请选择===</option>';
//		for (var i = 0; i < vals.length; i++) {
//			_html += '<option value="' + vals[i] + '">' + vals[i] + '</option></span>'
//		}
//
//		_html += '</select>';
//		
//		_html += (_type.indexOf("_") > 0 ? _type.split("_")[1] : "")
//		+ (json.isSukCandidate == "1" ? '<span style="display: inline-block;color:#0099cc;font-weight:bold;">◀ 销售</span>'
//				: '') + '</div></div>';
		
	} else if (json.attrName.indexOf("是否") != -1) {
			
		
		_html+="<span class=\"noselectric select2-hidden-accessible\">\
            <input type=\"text\" class=\"input-text\" value=\"\" readonly placeholder=\"请选择\"  name='"+json.attrName+","+json.id+"' style=\"cursor: pointer;margin:0px 0px 2px 0px;width: 70%;\" data-key=\"\" data-group=\"\">"
            +(_type.indexOf("_") > 0 ? _type.split("_")[1] : "")
    		+ (json.isSukCandidate == "1" ? '<span style="display: inline-block;color:#0099cc;font-weight:bold;">◀ 销售</span>': '')
    		+		
            "</span>\
            ";
	_html+="<div class=\"dropdown-content hid\" style=\"position: absolute;background: #fff;border: 1px solid #ccc;width: 70%;z-index: 10;\">\
			 <div class=\"scroll-wrapper\">\
			 		 <div class=\"scrollbar-dynamic scroll-content scroll-content2\" style=\"\">\
			 				<ul class=\"dropdown-menu\">";
	_html+="<li class=\"js-dropdown-item\" onclick=\"addToinput(this)\" style=\"margin-bottom:0px;border-bottom: 1px solid #ccc;text-align: center;cursor: pointer;line-height: 35px;\" data-val=''><span style=\"color:#000\">===请选择===<\span></li>";
	_html+="<li class=\"js-dropdown-item\" onclick=\"addToinput(this)\" style=\"margin-bottom:0px;border-bottom: 1px solid #ccc;text-align: center;cursor: pointer;line-height: 35px;\" data-val='是'><span style=\"color:#000\">是<\span></li>";
	_html+="<li class=\"js-dropdown-item\" onclick=\"addToinput(this)\" style=\"margin-bottom:0px;border-bottom: 1px solid #ccc;text-align: center;cursor: pointer;line-height: 35px;\" data-val='否'><span style=\"color:#000\">否<\span></li>";

	_html+="</ul></div></div>";

	_html += "</div></div></div></div></div>";
//		_html += '<span><select class="noselectric select2-hidden-accessible" id="js-item-brand" name="'
//				+ json.attrName
//				+ ','
//				+ json.id
//				+ '" required="" tabindex="-1" aria-hidden="true">'
//		_html += '<option value="" selected>===请选择===</option>';
//		_html += '<option value="是">是</option>';
//		_html += '<option value="否">否</option>';
//		_html += '</select></span>';
//		
//		_html += (_type.indexOf("_") > 0 ? _type.split("_")[1] : "")
//		+ (json.isSukCandidate == "1" ? '<span style="display: inline-block;color:#0099cc;font-weight:bold;">◀ 销售</span>'
//				: '') + '</div></div>';

	} else {
		_html += '<span><input type="text"  class="input-text" name="'
				+ json.attrName + ',' + json.id
				+ '" maxlength="200" placeholder="请输入' + (json.valueType.indexOf('NUMBER')!=-1?'数字" data_type="NUMBER" pattern="^\d+(\.\d{0,2})?$" hint="数字"':json.attrName)
				+ '" data-key="weight"  value=""></span>';
		
		_html += (_type.indexOf("_") > 0 ? _type.split("_")[1] : "")
		+ (json.isSukCandidate == "1" ? '<span style="display: inline-block;color:#0099cc;font-weight:bold;">◀ 销售</span>'
				: '') + '</div></div></div>';
	}


	return _html;

}


