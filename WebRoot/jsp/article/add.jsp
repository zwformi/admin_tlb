<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增单页</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<link href="<%=basePath%>js/validform/validform_ext.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/validform/validform_v5.3.2_min.js"></script>
<script src="<%=basePath%>js/validform/validform_ext.js"></script>
<link rel="stylesheet" href="<%=basePath%>js/editor/themes/default/default.css" />
<script charset="utf-8" src="<%=basePath%>js/editor/kindeditor-min.js"></script>
<script charset="utf-8" src="<%=basePath%>js/editor/lang/zh_CN.js"></script>
<link href="<%=basePath%>js/uploader/webuploader_ext.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/uploader/webuploader.min.js"></script>
<script src="<%=basePath%>js/uploader/webuploader_ext.js"></script>
<script>
	var editor;
	KindEditor.ready(function(K) {
        editor = KindEditor.create('#content', {
            width: '90%',
            height: '400px',
            resizeType: 1,
            uploadJson: '<%=basePath%>sys/editorUpload.json',
            fileManagerJson: '<%=basePath%>sys/editorFileManage.json',
            allowFileManager: true
        });
	});
</script>
</head>

<body>
<form id="mForm">
	<input id="id" name="id" type="hidden"/>
	<input id="action" name="action" type="hidden"/>
	<div class="place">
    <span>位置：</span>
        <ul class="placeul">
            <li><a href="<%=basePath%>jsp/firstpage.jsp">首页</a></li>
            <li><a href="javascript:;">基础数据</a></li>
            <li><a href="<%=basePath%>jsp/article/list.jsp">单页管理</a></li>
            <li><a href="javascript:;" class="location_add">新增单页</a></li>
        </ul>
    </div>
    
    <div class="formbody">
    <div class="formtitle"><span class="title_add">新增单页</span></div>
        <ul class="forminfo">
            <li><label>单页名称：</label>
                <dl><input id="title" name="title" type="text" class="dfinput" datatype="*2-30" nullmsg="请填写单页名称" /></dl>
            </li>
            <li><label>所属模块：</label>
                <dl><select id="typeid" name="typeid">
                	<option value="1" selected="selected">产品服务</option>
                	<option value="3">解决方案</option>
                	<option value="2">关于图灵买</option>
                	<option value="99">公司大事记</option>
                	<option value="4">其他</option>
                </select></dl>
            </li>
            <li><label>排序ID：</label>
                <dl><input id="sortid" name="sortid" type="text" class="dfinput" datatype="n1-6" nullmsg="请填写排序ID" /> <a style="color:#999">数字越大越靠前</a></dl>
            </li>
            <li><label>单页内容：</label>
                <dl><textarea id="content" name="content"></textarea></dl>
            </li>
            <li>
                <input type="button" class="btn" value="确认保存"/>
            </li>
        </ul>
    </div>
</form>
<script>
var checkValidform;
$(function() {
	//初始化表单验证
	checkValidform = initValidform("mForm");
	
	var id = "${param.id}";
	var action = "${param.action}";
	if(action == "edit"){
		$("#id").val(id);
		$("#action").val(action);
		$(".location_add").html("编辑单页");
		$(".title_add").html("编辑单页");
		
		$.post("<%=basePath%>article/query.json",{"id":id},function(data){
			$("#title").val(data.title);
			$("#typeid").val(data.typeid);
			$("#sortid").val(data.sortid);
			editor.html(data.content);
		});
	}
});

$(".btn").click(function(){
	if(checkValidform.check()){
		var url = "<%=basePath%>article/add.json";
		if($("#action").val() == "edit"){
			url = "<%=basePath%>article/modify.json";
		}
		$("#content").val(editor.html());
		var frm=$('#mForm').serialize();
		$.post(url,frm,function(data){
			if(data.success){
				layer.confirm('保存成功！是否返回列表页？', {icon: 1}, function(index){
					location.href="<%=basePath%>jsp/article/list.jsp";
				});
				if($("#action").val() != "edit"){
					$('#mForm')[0].reset();
				}
			}else{
				layer.alert(data.message, {icon: 2});
			}
		});
	}
});

</script>
</body>
</html>