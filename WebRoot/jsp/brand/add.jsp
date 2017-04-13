<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增品牌</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<link href="<%=basePath%>js/validform/validform_ext.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/validform/validform_v5.3.2_min.js"></script>
<script src="<%=basePath%>js/validform/validform_ext.js"></script>
</head>

<body>
<form id="mForm">
	<input id="id" name="id" type="hidden"/>
	<input id="action" name="action" type="hidden"/>
	<div class="place">
    <span>位置：</span>
        <ul class="placeul">
            <li><a href="<%=basePath%>jsp/firstpage.jsp">首页</a></li>
            <li><a href="javascript:;">图灵买商品</a></li>
            <li><a href="<%=basePath%>jsp/brand/list.jsp">品牌管理</a></li>
            <li><a href="javascript:;" class="location_add">新增品牌</a></li>
        </ul>
    </div>
    
    <div class="formbody">
    <div class="formtitle"><span class="title_add">新增品牌</span></div>
        <ul class="forminfo">
            <li><label>品牌名称：</label>
                <dl><input id="name" name="name" type="text" class="dfinput" datatype="*2-30" nullmsg="请填写品牌名称" /></dl>
            </li>
            <li><label>排序ID：</label>
                <dl><input id="sortid" name="sortid" type="text" class="dfinput" datatype="n1-6" nullmsg="请填写排序ID" /> <a style="color:#999">数字越大越靠前</a></dl>
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
		$(".location_add").html("编辑品牌");
		$(".title_add").html("编辑品牌");
		
		$.post("<%=basePath%>brand/query.json",{"id":id},function(data){
			$("#name").val(data.name);
			$("#sortid").val(data.sortid);
		});
	}
});

$(".btn").click(function(){
	if(checkValidform.check()){
		var url = "<%=basePath%>brand/add.json";
		if($("#action").val() == "edit"){
			url = "<%=basePath%>brand/modify.json";
		}
		var frm=$('#mForm').serialize();
		$.post(url,frm,function(data){
			if(data.success){
				layer.confirm('保存成功！是否返回列表页？', {icon: 1}, function(index){
					location.href="<%=basePath%>jsp/brand/list.jsp";
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