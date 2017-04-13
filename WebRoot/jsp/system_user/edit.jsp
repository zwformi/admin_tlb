<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改密码</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<link href="<%=basePath%>js/validform/validform_ext.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/validform/validform_v5.3.2_min.js"></script>
<script src="<%=basePath%>js/validform/validform_ext.js"></script>
<link rel="stylesheet" href="<%=basePath%>js/editor/themes/default/default.css" />
<script charset="utf-8" src="<%=basePath%>js/editor/kindeditor-min.js"></script>
<script charset="utf-8" src="<%=basePath%>js/editor/lang/zh_CN.js"></script>
</head>

<body>
<form id="mForm">
	<div class="place">
    <span>位置：</span>
        <ul class="placeul">
            <li><a href="<%=basePath%>jsp/firstpage.jsp">首页</a></li>
            <li><a href="javascript:;">修改密码</a></li>
        </ul>
    </div>
    
    <div class="formbody">
    <div class="formtitle"><span class="title_add">修改密码</span></div>
        <ul class="forminfo">
            <li><label>用户名：</label>
                <dl style="line-height:32px"><a id="user_name">${loginUser.user_name }</a></dl>
            </li>
            <li><label>原始密码：</label>
                <dl><input id="oldPassword" name="oldPassword" type="password" class="dfinput" datatype="*6-20" nullmsg="请填写原始密码" errormsg="密码范围在6~20位之间"/></dl>
            </li>
            <li><label>新密码：</label>
                <dl><input id="newPassword" name="newPassword" type="password" class="dfinput" datatype="*6-20" nullmsg="请填写新密码" errormsg="密码范围在6~20位之间"/></dl>
            </li>
            <li><label>确认密码：</label>
                <dl><input id="confirmPassword" name="confirmPassword" type="password" class="dfinput" datatype="*6-20" recheck="newPassword" nullmsg="请再输入一次密码" errormsg="您两次输入的密码不一致"/></dl>
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
});

$(".btn").click(function(){
	if(checkValidform.check()){
		var url = "<%=basePath%>system_user/editPassword.json";
		var frm=$('#mForm').serialize();
		$.post(url,frm,function(data){
			if(data.success){
				layer.alert('保存成功！', {icon: 1}, function(index){
					top.window.location.href="<%=basePath%>login.html";
				    layer.close(index);
				});
				$('#mForm')[0].reset();
			}else{
				layer.alert(data.message, {icon: 2});
			}
		});
	}
});
</script>
</body>
</html>