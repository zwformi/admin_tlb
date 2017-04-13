<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增维修站</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<link href="<%=basePath%>js/validform/validform_ext.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/validform/validform_v5.3.2_min.js"></script>
<script src="<%=basePath%>js/validform/validform_ext.js"></script>
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
            <li><a href="<%=basePath%>jsp/maintenance_station/list.jsp">维修站管理</a></li>
            <li><a href="javascript:;" class="location_add">新增维修站</a></li>
        </ul>
    </div>
    
    <div class="formbody">
    <div class="formtitle"><span class="title_add">新增维修站</span></div>
        <ul class="forminfo">
            <li><label>维修站名称：</label>
                <dl><input id="name" name="name" type="text" class="dfinput" datatype="*2-30" nullmsg="请填写维修站名称" /></dl>
            </li>
            <li><label>维修站地址：</label>
                <dl><input id="address" name="address" type="text" class="dfinput" datatype="*2-30" nullmsg="请填写维修站名称" /></dl>
            </li>
            <li><label>联系人：</label>
                <dl><input id="lxr" name="lxr" type="text" class="dfinput" datatype="*2-30" nullmsg="请填写联系人" /></dl>
            </li>
            <li><label>联系电话：</label>
                <dl><input id="phone" name="phone" type="text" class="dfinput" datatype="*2-30" nullmsg="请填写联系电话" /></dl>
            </li>
            <li><label>所属类别：</label>
                <dl><select id="type_id" name="type_id"></select></dl>
            </li>
            <li><label>排序ID：</label>
                <dl><input id="sort_id" name="sort_id" type="text" class="dfinput" datatype="n1-6" nullmsg="请填写排序ID" /> <a style="color:#999">数字越大越靠前</a></dl>
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
	loadtype();
	
	//初始化表单验证
	checkValidform = initValidform("mForm");
	
	var id = "${param.id}";
	var action = "${param.action}";
	if(action == "edit"){
		$("#id").val(id);
		$("#action").val(action);
		$(".location_add").html("编辑维修站");
		$(".title_add").html("编辑维修站");
		
		$.post("<%=basePath%>maintenance_station/query.json",{"id":id},function(data){
			$("#name").val(data.name);
			$("#address").val(data.address);
			$("#lxr").val(data.lxr);
			$("#phone").val(data.phone);
			$("#type_id").val(data.type_id);
			$("#sort_id").val(data.sort_id);
		});
	}
});

$(".btn").click(function(){
	if(checkValidform.check()){
		var url = "<%=basePath%>maintenance_station/add.json";
		if($("#action").val() == "edit"){
			url = "<%=basePath%>maintenance_station/modify.json";
		}
		var frm=$('#mForm').serialize();
		$.post(url,frm,function(data){
			if(data.success){
				layer.confirm('保存成功！是否返回列表页？', {icon: 1}, function(index){
					location.href="<%=basePath%>jsp/maintenance_station/list.jsp";
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

//加载分类
function loadtype(){
	$.ajax({
        type: "POST",
        url: "<%=basePath%>maintenance_station/queryListBySelect.json",
        async: false, //设为false就是同步请求
        cache: false,
        success: function (data) {
	    	var type_id = $("#type_id");
	    	//清空下拉框
	    	type_id.empty();
	    	//动态绑定子项
	    	for(var i=0;i<data.length;i++){
	    		type_id.append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
		    }
        }
    });
}
</script>
</body>
</html>