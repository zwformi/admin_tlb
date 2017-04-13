<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<style>
    .p {margin-top: 0px; margin-left: 30px;}
    .p b {color: #3994c7;}
    .welinfo span {color: #3994c7;}
</style>
<script type="text/javascript">
$(function(){	
	if(${loginUser.login_times}==1)
	{
	     //parent.document.getElementById("rightFrame").src="editpassword.jsp";  
	}
})
</script>
</head>

<body>

	<div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="#">首页</a></li>
        </ul>
    </div>
    
    <div class="mainindex">
        <div class="welinfo">
            <span><img src="<%=basePath%>images/sun.png" alt="天气" /></span>
            <b><span style="margin-left: 10px;">${loginUser.user_realname}</span>您好，欢迎使用图灵买后台管理系统</b>
        </div>
        <div class="welinfo p">
            <i>您此次是第<b> ${loginUser.login_times}</b>&nbsp;&nbsp;次登录</i>
        </div>
        <div class="welinfo p">
            <i>您上次登录的时间：<b><fmt:formatDate value="${loginUser.last_logindate}" type="both" /></b></i>
        </div>
        <div class="welinfo p">
            <i>您上次登录的IP：<b>${loginUser.login_ip}</b></i>
        </div>
    </div>
</body>

</html>