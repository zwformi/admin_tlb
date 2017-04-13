<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
<meta name="renderer" content="webkit" />
<title>图灵买</title>
<link rel="shortcut icon" href="favicon.ico"/>
<link href="<%=basePath %>css/style.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="<%=basePath %>js/jquery.min.js"></script>
<script language="javascript" src="<%=basePath %>js/jquery.cookie.js"></script>

<script language="javascript">
$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    	$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })

    if($.cookie("rememberPassword") == "true") {
    	$("#rememberPassword").attr("checked", true);
    	$("#userName").val($.cookie("userName"));
    	$("#userPassword").val($.cookie("userPassword"));
    } 
});

var parent_url = window.parent.location.href;
if(parent_url.indexOf("index.jsp") != -1){
	window.parent.location.href = "login.html";
}
function forget()
{
   alert("目前不提供自动找回功能，请将用户名、联系电话等信息发送邮件至系统管理员邮箱：4769747@qq.com，由系统管理员重置密码");
}
</script>

<script language="javascript">
function doLogin(){
	if($("#userName").val()==""){
		alert("请输入用户名!");
		$("#userName").focus();
		return;
	}
	if($("#userPassword").val()==""){
		alert("请输入密码!");
		$("#userPassword").focus();
		return;
	}
	if($("#rememberPassword").attr("checked")){
		var userName = $("#userName").val(); 
		var userPassword = $("#userPassword").val(); 
		$.cookie("rememberPassword", "true", { expires: 7 }); // 存储一个带7天期限的 cookie
		$.cookie("userName", userName, { expires: 7 }); // 存储一个带7天期限的 cookie
		$.cookie("userPassword", userPassword, { expires: 7 }); // 存储一个带7天期限的 cookie
	}else{
		$.cookie("rememberPassword", "false", { expires: -1 });
		$.cookie("userName", '', { expires: -1 });
		$.cookie("userPassword", '', { expires: -1 });
	}
	var frm=$('#loginForm').serialize();
	
	$.post("<%=basePath%>login.json",frm,function(data){
		if(data.success){
			top.location.href="<%=basePath%>jsp/index.jsp";
		}else{
			//alert(data.message);
			top.location.href="<%=basePath%>jsp/index.jsp";
		}
	});
}
document.onkeydown=function(event){
	e = event ? event :(window.event ? window.event : null);
	if(e.keyCode==13){
		//回车登录
		doLogin();
	}
}
</script>

</head>

<body style="background-color:#1c77ac; background-image:url(<%=basePath%>images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
<form id="loginForm">
    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
    </div>
    
	<div class="logintop">    
    <span>欢迎登录图灵买后台管理系统</span>    
    <ul>
    <li></li>
    <li></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    <ul>
    <li><input id="userName" name="userName" type="text" class="loginuser" /></li>
    <li><input id="userPassword" name="userPassword" type="password" class="loginpwd" /></li>
    <li><input type="button" class="loginbtn" value="登录" onclick="doLogin()"/><label><input id="rememberPassword" type="checkbox" value="1" />记住密码</label> <label><a href="javascript:;" onclick="forget()">忘记密码？</a></label> </li>
    </ul>
    </div>
    
    </div>
    
    <div class="loginbm">Copyright © tulingbuy.com</div>
</form>
</body>
</html>