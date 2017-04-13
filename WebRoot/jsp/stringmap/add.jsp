<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增字典数据</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<link href="<%=basePath%>js/validform/validform_ext.css"
	rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/validform/validform_v5.3.2_min.js"></script>
<script src="<%=basePath%>js/validform/validform_ext.js"></script>
<link rel="stylesheet"
	href="<%=basePath%>js/editor/themes/default/default.css" />
<script charset="utf-8" src="<%=basePath%>js/editor/kindeditor-min.js"></script>
<script charset="utf-8" src="<%=basePath%>js/editor/lang/zh_CN.js"></script>
<link href="<%=basePath%>js/uploader/webuploader_ext.css"
	rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/uploader/webuploader.min.js"></script>
<script src="<%=basePath%>js/uploader/webuploader_ext.js"></script>
</head>

<body>
	<form id="mForm">
		<input id="id" name="id" type="hidden" /> 
		<input id="action"
			name="action" type="hidden" />
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="<%=basePath%>jsp/firstpage.jsp">首页</a></li>
				<li><a href="javascript:;">基础数据</a></li>
				<li><a href="<%=basePath%>jsp/dmb/list.jsp">字典数据管理</a></li>
				<li><a href="javascript:;" class="location_add">新增字典数据</a></li>
			</ul>
		</div>

		<div class="formbody">
			<div class="formtitle">
				<span class="title_add">新增字典数据</span>
			</div>
			<ul class="forminfo">
				<li><label>表名：</label>
					<dl>
						<select id="objecttypename" name="objecttypename">
						</select>
					</dl> <!--  <dl><input id="objecttypename" name="name" type="text" class="dfinput" datatype="*2-30" nullmsg="请填写字典值" /></dl> -->
				</li>
				<li><label>字段名：</label>
					<dl>
						<select id="filedname" name="filedname">
						</select>
					</dl></li>
				<li><label>中文名：</label>
					<dl>
						<input id="name" name="name" type="text" class="dfinput"
							datatype="*2-20" nullmsg="请填写中文名" />
					</dl></li>
				<li><label>拼音：</label>
					<dl>
						<input id="pinyin" name="pinyin" type="text" class="dfinput" />
					</dl></li>
				<li><label>代号：</label>
					<dl>
						<input id="value" name="value" type="text" class="dfinput"
							datatype="n1-5" nullmsg="请填写代号" />
					</dl></li>

				<li><input type="button" class="btn" value="确认保存" /></li>
			</ul>
		</div>
	</form>
	<script>
var checkValidform;

$(function() {
	var filednames = {};
	var objecttypenames = {};
	var id = "${param.id}";
	var action = "${param.action}";
	if(!!id && !!action)
	{
		$('#id').val(id);	
		$('#action').val(action);	
		}
	//初始化表单验证
	checkValidform = initValidform("mForm");
	loadTables(true);
	function loadTables(flag){
	$.post('<%=basePath%>stringmap/table.json',function(data){
	objecttypenames = data.data;
	var temp_html = '';
	for (key in objecttypenames){
		temp_html += '<option value="'+objecttypenames[key].table_name.substring(4)+'">'+objecttypenames[key].table_name+'&nbsp;&nbsp;&nbsp;['+objecttypenames[key].table_comment.split(';')[0]+']</option>';	
	
	}
	$('#objecttypename').html(temp_html);
	if(flag){
		loadColumn(flag);
	}
});
	
}


	function loadColumn(flag){
		var table = $('#objecttypename option:selected').val();
			table = "tbl_"+table;
		$.post('<%=basePath%>stringmap/column.json',{"tablename":table},function(data){
				var temp_html='';
				filednames = data.data;
				if(data.resCode==1){
				for( key in filednames){
					temp_html += '<option value="'+filednames[key].column_NAME+'">'+filednames[key].column_NAME+'&nbsp;&nbsp;&nbsp;['+filednames[key].column_COMMENT+']</option>';	
				}
				}
				$('#filedname').html(temp_html);
				if(flag)
					initEditData();
			});
	}


		
	 $("#objecttypename").bind("change",function(){
			loadColumn();
	  });



   
	$(".btn").click(function(){
	if(checkValidform.check()){
		var url = "<%=basePath%>stringmap/modify.json";
		var frm=$('#mForm').serialize();
		$.post(url,frm,function(data){
			if(data.resCode==1){
				layer.confirm('保存成功！是否返回列表页？', {icon: 1}, function(index){
					location.href="<%=basePath%>jsp/stringmap/list.jsp";
				});
				if($("#action").val() != "edit"){
					$('#mForm')[0].reset();
				}
			}else{
				layer.alert(data.resMsg, {icon: 2});
			}
		});  
	}
});


 	


	function initEditData(){
		if(action == "edit"){
			$.post("<%=basePath%>stringmap/query.json",{"id":id},function(data){
				var _data = data.data;
				var objecttypename = _data.objectTypeName;
				var filedname = _data.filedName;
				var pinyin = (_data.pinyin==null?"":_data.pinyin);
				var name = _data.name;
				var value = _data.value;
				$('#objecttypename').val(objecttypename);
				$('#name').val(name);
				$('#pinyin').val(pinyin);
				$('#value').val(value);
				loadColumn();
				 setTimeout(function() {
				 	$('#filedname').val(filedname);
				 }, 100);
				 
				 
				 
			});
		} 
	}
	
});




</script>
</body>
</html>