<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增系统用户</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<link href="<%=basePath%>js/validform/validform_ext.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/validform/validform_v5.3.2_min.js"></script>
<script src="<%=basePath%>js/validform/validform_ext.js"></script>
<link rel="stylesheet" href="<%=basePath%>js/editor/themes/default/default.css" />
<script charset="utf-8" src="<%=basePath%>js/editor/kindeditor-min.js"></script>
<script charset="utf-8" src="<%=basePath%>js/editor/lang/zh_CN.js"></script>
<style type="text/css">
    .select{height:40px;line-height:40px;}
    .select input{margin-left:10px;margin-right:5px;}
</style>
</head>

<body>
<form id="mForm">
	<input id="id" name="id" type="hidden"/>
	<input id="action" name="action" type="hidden"/>
	<div class="place">
    <span>位置：</span>
        <ul class="placeul">
            <li><a href="<%=basePath%>jsp/firstpage.jsp">首页</a></li>
            <li><a href="javascript:;">系统管理</a></li>
            <li><a href="<%=basePath%>jsp/system_user/list.jsp">系统用户管理</a></li>
            <li><a href="javascript:;" class="location_add">新增系统用户</a></li>
        </ul>
    </div>
    
    <div class="formbody">
    <div class="formtitle"><span class="title_add">新增系统用户</span></div>
        <ul class="forminfo">
            <li><label>用户名：</label>
                <dl><input id="user_name" name="user_name" type="text" class="dfinput" datatype="*2-20" nullmsg="请填写用户名" errormsg="请填写用户名" /></dl>
            </li>
            <li><label>真实姓名：</label>
                <dl><input id="user_realname" name="user_realname" type="text" class="dfinput" datatype="*2-10" nullmsg="请填写真实姓名" errormsg="请填写真实姓名" /></dl>
            </li>
            <li><label>性别：</label>
                <dl class="select"><input type="radio" name="user_sex" value="1" datatype="*" nullmsg="请选择性别" errormsg="请选择性别"/>男<input type="radio" name="user_sex" value="0"/>女</dl>
            </li>
            <li><label>所属部门：</label>
                <dl><input id="user_dept" name="user_dept" type="text" class="dfinput" datatype="*2-20" nullmsg="请填写所属部门" errormsg="请填写所属部门" /></dl>
            </li>
            <li><label>电话：</label>
                <dl><input id="user_phone" name="user_phone" type="text" class="dfinput" datatype="*8-20" nullmsg="请填写电话" errormsg="请填写电话" /></dl>
            </li>
            <li><label>邮箱：</label>
                <dl><input id="user_email" name="user_email" type="text" class="dfinput" datatype="*5-20" nullmsg="请填写邮箱" errormsg="请填写邮箱" /></dl>
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
		$(".location_add").html("编辑系统用户");
		$(".title_add").html("编辑系统用户");
		
		$.post("<%=basePath%>system_user/query.json",{"id":id},function(data){
			$("#user_name").attr("readonly","readonly");
			$("#user_name").val(data.user_name);
			$("#user_realname").val(data.user_realname);
			$("input[name='user_sex'][value="+data.user_sex+"]").attr("checked",true);
			$("#user_dept").val(data.user_dept);
			$("#user_phone").val(data.user_phone);
			$("#user_email").val(data.user_email);
		});
	}
});

$(".btn").click(function(){
	if(checkValidform.check()){
		var url = "<%=basePath%>system_user/add.json";
		if($("#action").val() == "edit"){
			url = "<%=basePath%>system_user/modify.json";
		}
		var frm=$('#mForm').serialize();
		$.post(url,frm,function(data){
			if(data.success){
				if($("#action").val() == "edit"){
					layer.confirm('保存成功！是否返回列表页？', {icon: 1}, function(index){
						location.href="<%=basePath%>jsp/system_user/list.jsp";
					});
				}else{
					layer.confirm(data.message, {icon: 1}, function(index){
						location.href="<%=basePath%>jsp/system_user/list.jsp";
					});
				}
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