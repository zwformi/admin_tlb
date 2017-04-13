<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title></title>
	<style type="text/css">
		html, body {margin:0; padding: 0px;}
		body { background: url(<%=basePath%>images/righttop.gif); position: relative; }
		#body {border-top: 1px solid #B7D5DF; height: 100%;}
		#copyright {height: 24px; line-height: 24px; font-size: 12px; font-family: '微软雅黑'; text-align: center;color: #3994c7; font-weight: bold;}
		#copyright div {float: right; height: 24px; line-height: 24px; margin-right: 20px; position: absolute; right: 0px; top: 0px;}
	</style>
</head>
<body>
	<div id="body"></div>
	<div id="copyright">Copyright © tulingbuy.com<!-- <div>POWER BY YUEMA</div> --></div>
</body>
</html>