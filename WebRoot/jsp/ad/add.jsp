<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增广告图片</title>
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
<script>
    $(function () {
    	//初始化上传控件
        $(".upload-img").InitUploader({ sendurl: "<%=basePath%>sys/uploadPic.json", swf: "<%=basePath%>js/webuploader/uploader.swf", dir: "image", filetypes: "jpg,png,gif,jpeg" });
    });
</script>
<style type="text/css">
    .laydate-icon {width: 325px !important; height: 32px !important;border-top:solid 1px #a7b5bc !important; border-left:solid 1px #a7b5bc !important; border-right:solid 1px #ced9df !important; border-bottom:solid 1px #ced9df !important;}
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
            <li><a href="javascript:;">基础数据</a></li>
            <li><a href="<%=basePath%>jsp/ad/list.jsp">横幅广告管理</a></li>
            <li><a href="javascript:;" class="location_add">新增广告图片</a></li>
        </ul>
    </div>
    
    <div class="formbody">
    <div class="formtitle"><span class="title_add">新增广告图片</span></div>
        <ul class="forminfo">
            <li><label>所属页面：</label>
                <dl><select id="typeid" name="typeid">
                <option value="1">首页</option>
                <option value="2">登录页</option>
                <option value="3">会员中心</option>
                <option value="4">图灵云首页</option>
                </select></dl>
            </li>
            <li><label>广告名称：</label>
                <dl><input id="name" name="name" type="text" class="dfinput" datatype="*2-20" nullmsg="请填写广告名称" /></dl>
            </li>
            <li><label>广告图片：</label>
                <dl><input id="img_url" name="img_url" type="text" class="dfinput upload-path" datatype="*10-100" nullmsg="请选择广告图片" /><div class="upload-box upload-img" style="top:-3px; left:-1px"></div></dl>
            </li>
            <li><label>广告链接：</label>
                <dl><input id="url" name="url" type="text" class="dfinput" datatype="*1-500" nullmsg="请填写广告链接" value="#" /></dl>
            </li>
            <li><label>排序ID：</label>
                <dl><input id="sortid" name="sortid" type="text" class="dfinput dfinput50" datatype="n" nullmsg="请填写排序ID" /></dl>
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
		$(".location_add").html("编辑广告图片");
		$(".title_add").html("编辑广告图片");
		
		$.post("<%=basePath%>ad/query.json",{"id":id},function(data){
			$("#typeid").val(data.typeid);
			$("#name").val(data.name);
			$("#img_url").val(data.img_url);
			$("#url").val(data.url);
			$("#sortid").val(data.sortid);
		});
	}
});

$(".btn").click(function(){
	if(checkValidform.check()){
		var url = "<%=basePath%>ad/add.json";
		if($("#action").val() == "edit"){
			url = "<%=basePath%>ad/modify.json";
		}
		var frm=$('#mForm').serialize();
		$.post(url,frm,function(data){
			if(data.success){
				layer.confirm('保存成功！是否返回列表页？', {icon: 1}, function(index){
					location.href="<%=basePath%>jsp/ad/list.jsp";
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
<script src="<%=basePath%>js/laydate/laydate.js"></script>
</body>
</html>