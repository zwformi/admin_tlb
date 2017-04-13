<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="true" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<head>
<style type="text/css">
    .main{display:none;padding:0px;margin:0px;width:50%;height:30%;position:absolute;top:25%;left:25%;background:#fcfcfc;border:1px solid #dcdcdc;
    -webkit-background-clip: content;
    box-shadow: 1px 1px 50px rgba(0, 0, 0, .3);
    -webkit-animation-fill-mode: both;
    animation-fill-mode: both;
    -webkit-animation-duration: .3s;
    animation-duration: .3s;}
    .main .progress {
    margin-left:20px;
	margin-right:20px;
    margin-top:10%;
    height: 20px;
    background: #ebebeb;
    border-left: 1px solid transparent;
    border-right: 1px solid transparent;
    border-radius: 10px;
}
.progress .blue {
	text-align:left;
    /* background: #0099cc; */
    border-color: #459fd6 #3094d2 #277db2;
    height: 20px;
    border-radius: 10px;
    transition: width 1s;
	-moz-transition: width 1s;	/* Firefox 4 */
	-webkit-transition: width 1s;	/* Safari 和 Chrome */
	-o-transition: width 1s;	/* Opera */
	animation:myfirst 5s infinite ;
	-moz-animation:myfirst 5s infinite ; /* Firefox */
	-webkit-animation:myfirst 5s infinite ; /* Safari and Chrome */
	-o-animation:myfirst 5s infinite ; /* Opera */
}

@keyframes myfirst
{
0%   {background:#0099cc;}
50%  {background:#90EE90;}
100% {background:#0099cc;}
}

@-moz-keyframes myfirst /* Firefox */
{
0%   {background:#0099cc;}
50%  {background:#90EE90;}
100% {background:#0099cc;}
}

@-webkit-keyframes myfirst /* Safari and Chrome */
{
0%   {background:#0099cc;}
50%  {background:#90EE90;}
100% {background:#0099cc;}
}

@-o-keyframes myfirst /* Opera */
{
0%   {background:#0099cc;}
50%  {background:#90EE90;}
100% {background:#0099cc;}
}
.progress .blue span{float:right;margin-right:10px;line-height: 20px;	color:#fff;}
.main .content{padding:20px;text-align:center;}
span{display:inline-block;}
</style>
</head>
    <div class="main" id="progress_main">
    <div class="progress">
      <span class="blue" id="progress_view" style="width: 1%;"><span id="progress_percent">1%</span></span>
    </div>
    <div class="content">
    	<p id="content">当前正在操作&nbsp;&nbsp;[&nbsp;&nbsp;<span id="progress_index" >1</span>/<span id="progress_total">5</span>&nbsp;&nbsp;]&nbsp;&nbsp;...</p>
    </div>
    </div>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/layer/layer.js"></script>
	<script src="<%=basePath%>js/common.js"></script>
    <script type="text/javascript">
    </script>
