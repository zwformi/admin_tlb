/**
 * author zgf date 2016/9/18
 */

function getStringmap(typename, filedname) {
	var list = {};
	$.ajax({
		url : '/stringmap.json',
		type : "post",
		data : {
			'objecttypename' : typename,
			'filedname' : filedname
		},
		dataType : "json",
		async:false,
		success : function(data) {
			if (!!data) {
				for (i in data) {
					list[data[i].value] = data[i].name;
				}
			}

		}
	});
	return list;

}

//自动加载页面上所有的对应stringmap的select内容，方法为group为typename，id为fieldname
function loadSelect(flag){
	
	$("select").each(function(){
			var list = getStringmap($(this).attr("group"), $(this).attr("id"));   
		//动态绑定子项
		for(var i in list){
			if (flag ==1){ //是否清空
				$(this).empty();
			}
			$(this).append("<option value='"+i+"'>"+list[i]+"</option>");
			}

		});
}

