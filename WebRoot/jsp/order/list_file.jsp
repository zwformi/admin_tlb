<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上传合同相关附件</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	body{min-width:400px;}
	.update_file{margin-bottom:0px;padding:10px;border-bottom:2px solid #edece7;background-color:#fff;}
	.textsm{color:red;margin-top:10px;}
</style>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/layer/layer.js"></script>
	<script src="<%=basePath%>js/common.js"></script>
	<link href="<%=basePath%>js/uploader/webuploader_ext.css" rel="stylesheet" type="text/css" />
	<script src="<%=basePath%>js/uploader/webuploader.min.js"></script>
	<script src="<%=basePath%>js/uploader/webuploader_order.js"></script>
<script>
    $(function () {
    	//初始化上传控件
        $(".upload-img").InitUploader({ sendurl: "<%=basePath%>sys/uploadPic_orderfile.json", swf: "<%=basePath%>js/webuploader/uploader.swf" });
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
         //确定事件
           $("#save").on("click",function(){
           		if($("#file_type").val()==0){
           			layer.msg("请选择附件类型！");
           		}else if($("#file_url").val()==""){
           			layer.msg("请选择需要上传的附件！");
           		}else if($("#order_id").val()==""){
           			layer.msg("页面错误，操作无法执行！");
           		}else{
           			layer.confirm('确认要上传该附件吗？<br/>继续请点击“确定”，否则点击“取消”', {icon: 3}, function(index){
		           		var param = {"order_id":$("#order_id").val(),"file_type":$("#file_type").val(),"file_url":$("#file_url").val()};
		            	$.post("<%=basePath%>order/order_file_upload.json",param,function(data){
		        			if(data.error){
		        				parent.layer.msg(data.message);
		        			}else{
			        			if(data.count>0){
			        				parent.initData();
			        				parent.layer.close(index);
			        				layer.msg("附件上传成功");
			        			}else{
			        				parent.layer.msg("附件上传失败");
			        			}
		        			}
		        		});
		        	});
           		}
        });
    });
</script>
</head>

<body>
    <div class="formbody">
    	<div class="update_file">附件类型：
    		<select id="file_type" style="width:150px;height:30px;border:1px solid #efefef;">
    			<option value="0">请选择附件类型</option>
    			<option value="1">签收单附件</option>
    			<option value="2">确认实施单附件</option>
    		</select>
    	</div>
    	<div class="update_file">
    		选择相关附件进行上传：<br/>
    		<input id="file_url" name="file_url" type="text" class="dfinput upload-path" readonly="readonly"/>
    		<div class="upload-box upload-img" style="top:-3px; left:-1px"></div>
    	</div>
    	<div id="savebh" style="text-align:right;">
    		<input type="hidden" id="order_id" value="${param.order_id}"/>
		<input type="button" id="save" class="btn" value="保存"/>
	</div>
    </div>
</body>
</html>