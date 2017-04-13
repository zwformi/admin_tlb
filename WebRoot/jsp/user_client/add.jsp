<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增客服人员</title>
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
            <li><a href="<%=basePath%>jsp/user_client/list.jsp">专属客服管理</a></li>
            <li><a href="javascript:;" class="location_add">新增客服人员</a></li>
        </ul>
    </div>
    
    <div class="formbody">
    <div class="formtitle"><span class="title_add">新增客服人员</span></div>
        <ul class="forminfo">
        	<li><label>客服工号：</label>
                <dl><input id="gh" name="gh" type="text" class="dfinput" datatype="*2-50" nullmsg="请填写客服工号" /></dl>
            </li>
            <li><label>客服姓名：</label>
                <dl><input id="name" name="name" type="text" class="dfinput" datatype="*2-20" nullmsg="请填写客服姓名" /></dl>
            </li>
            <li><label>联系电话：</label>
                <dl><input id="phone" name="phone" type="text" class="dfinput" datatype="*7-20" nullmsg="请填写联系电话" /></dl>
            </li>
            <li><label>QQ号码：</label>
                <dl><input id="qq" name="qq" type="text" class="dfinput" datatype="n5-11" nullmsg="请填写QQ号码" /></dl>
            </li>
            <li><label>固定电话：</label>
                <dl><input id="telphone" name="telphone" type="text" class="dfinput" datatype="*4-20" nullmsg="请填写固定电话" /></dl>
            </li>
            <li><label>邮箱：</label>
                <dl><input id="email" name="email" type="text" class="dfinput" datatype="*4-100" nullmsg="请填写邮箱地址" /></dl>
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
		$(".location_add").html("编辑客服人员");
		$(".title_add").html("编辑客服人员");
		
		$.post("<%=basePath%>user_client/query.json",{"id":id},function(data){
			$("#gh").val(data.gh);
			$("#name").val(data.name);
			$("#phone").val(data.phone);
			$("#qq").val(data.qq);
			$("#telphone").val(data.telphone);
			$("#email").val(data.email);
		});
	}
});

$(".btn").click(function(){
	if(checkValidform.check()){
		var url = "<%=basePath%>user_client/add.json";
		if($("#action").val() == "edit"){
			url = "<%=basePath%>user_client/modify.json";
		}
		var frm=$('#mForm').serialize();
		$.post(url,frm,function(data){
			if(data.success){
				layer.confirm('保存成功！是否返回列表页？', {icon: 1}, function(index){
					location.href="<%=basePath%>jsp/user_client/list.jsp";
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