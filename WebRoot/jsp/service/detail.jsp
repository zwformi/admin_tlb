<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>服务单详情</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	body{min-width:400px;}
	#content input{padding:5px;}
	.checknull{border:1px solid #f0a66f;}
</style>
</head>

<body>
    <div class="formbody">
    		<ul class="forminfo">
	            <li><label>服务编号：</label>
	                <label><dl id="service_number">--</dl></label>
	            </li>
	            <li><label>关联订单编号：</label>
	                <label><dl id="order_number">--</dl></label>
	            </li>
	            <li><label>联系人：</label>
	                <label><dl id="lxr">--</dl></label>
	            </li>
	            <li><label>手机号：</label>
	                <label><dl id="phone" style="width:200px;">--</dl></label>
	            </li>
	            <li><label>问题描述：</label>
	                <label><dl id="content" style="width:400px;">--</dl></label>
	            </li>
	            <li><label>发布时间：</label>
	                <label><dl id="add_time" style="width:200px;">--</dl></label>
	            </li>
	            <li><label>服务状态：</label>
	                <label><dl id="state">--</dl></label>
	            </li>
	            <li><label>联系地址：</label>
	                <label><dl id="address" style="width:400px;">--</dl></label>
	            </li>
	            <li><label>服务商品信息：</label>
	                <label><dl id="product_sh" style="width:400px;">无关联商品</dl></label>
	            </li>
	            <li><label>服务备注：</label>
	                <label><dl id="comment" style="width:500px;">--</dl></label>
	            </li>
	        </ul>
    </div>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/layer/layer.js"></script>
	<script src="<%=basePath%>js/common.js"></script>
    <script type="text/javascript">
    	var service_number = "${param.service_number}";
    	$(document).ready(function () {
            initData();
        });
        
        function initData(){
        	//显示加载层
        	layer.load();
        	pageSelectCallback();
        }
        function pageSelectCallback() {
        	$.post("<%=basePath%>service/query.json",{"service_number":service_number},function(data){
        	var ORDERDETAILS = data.ORDERDETAILS;
        	if(null!=ORDERDETAILS){
        		$("#product_sh").html("商品名称："+ORDERDETAILS.product_name+"<br/>商品类型："+ORDERDETAILS.type_str+"<br/>品牌："+ORDERDETAILS.brand_str+"<br/>型号："+ORDERDETAILS.xh+"<br/>配置："+ORDERDETAILS.pz);
        	}
        	var USERSERVICE = data.USERSERVICE;
        	$("#service_number").html(USERSERVICE.service_number);
        	$("#order_number").html(USERSERVICE.order_number);
        	
        	$("#lxr").html(USERSERVICE.lxr);
        	$("#phone").html(USERSERVICE.phone);
        	$("#content").html(USERSERVICE.content);
        	$("#add_time").html(USERSERVICE.fmt_add_time);
        	
        	var state_=USERSERVICE.state;
        	var state_html ="--";
        	if(state_==0){state_html="待服务"}
        	if(state_==1){state_html="服务完成"}
        	$("#state").html(state_html);
        	$("#address").html(USERSERVICE.address);
        	$("#comment").html(USERSERVICE.comment);
	            //关闭加载层
	        	layer.closeAll('loading');
        	});
        }
        
    </script>
</body>
</html>