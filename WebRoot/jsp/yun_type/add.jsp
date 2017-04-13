<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增云模块类别</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<link href="<%=basePath%>js/validform/validform_ext.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/validform/validform_v5.3.2_min.js"></script>
<script src="<%=basePath%>js/validform/validform_ext.js"></script>
<link href="<%=basePath%>js/uploader/webuploader_ext.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/uploader/webuploader.min.js"></script>
<script src="<%=basePath%>js/uploader/webuploader_ext.js"></script>
<link rel="stylesheet" href="<%=basePath %>js/colorpicker/js/jquery.bigcolorpicker.css"/>
<script src="<%=basePath%>js/colorpicker/js/jquery.bigcolorpicker.min.js"></script>
<link rel="stylesheet" href="<%=basePath %>jsp/yun/iconfont.css"/>
<script>

</script>
<style type="text/css">
	.choose i{
		border:2px solid blue;
		background:blue;
	
	}
    #icon-show{
    	
    	color:#fff;
    	width:80px;
    	line-height:80px;
    	height:80px;
    	margin:10px;
    	font-size:40px;
    	text-align:center;
    	border-radius:20px;
    	background:red;
    }
</style>
</head>

<body>
<form id="mForm">
	<input id="type_id" name="type_id" type="hidden"/>
	<input id="icon" name="icon" type="hidden"/>
	<input id="action" name="action" type="hidden"/>
	<div class="place">
    <span>位置：</span>
        <ul class="placeul">
            <li><a href="<%=basePath%>jsp/firstpage.jsp">首页</a></li>
            <li><a href="javascript:;">图灵云模块</a></li>
            <li><a href="<%=basePath%>jsp/yun/list_yuntype.jsp">类别管理</a></li>
            <li><a href="javascript:;" class="location_add">新增类别</a></li>
        </ul>
    </div>
    
    <div class="formbody">
    <div class="formtitle"><span class="title_add">新增分类</span></div>
        <ul class="forminfo">
            <li><label>类别名称：</label>
                <dl><input id="name" name="name" type="text" class="dfinput" datatype="*2-30" nullmsg="请填写分类名称" /></dl>
            </li>
            <li><label>所属类别：</label>
                <dl><select id="parent_id" name="parent_id"></select></dl>
            </li>
            <li><label>排序ID：</label>
                <dl><input id="sort_code" name="sort_code" type="text" class="dfinput" datatype="n1-6" nullmsg="请填写排序ID" /> <a style="color:#999">数字越大越靠前</a></dl>
            </li>
            <li><label>上架状态：</label>
				<dl class="select">
						<input type="radio" name="delete_flag" id="del_0" value="0" checked="checked" />上架
						<input type="radio" name="delete_flag" id="del_1" value="1" />下架
				</dl>
			</li>
			<tr>
			
            <li>		
				<label>图标颜色：</label>
				<dl>
					<input id="bgcolor" name="bgcolor"  type="text" class="dfinput"
							datatype="/^#?([a-f]|[A-F]|[0-9]){3}(([a-f]|[A-F]|[0-9]){3})?$/" nullmsg="请选择图标颜色" />
				</dl>
				
			
			</li>
            <li><label>使用图标：</label>
           <div width="70%" style="float:left; border:1px solid #a7b5bc">
             <iframe id="iframe-icons" name="iframe" width="720px" height="360px" frameborder="1px solid #000" scrolling="scroll" vspace="0" hspace="0" src="<%=basePath%>jsp/yun/demo.jsp"></iframe>
             
     </div> 

                <div style="border:2px solid #a7b5bc;margin-left:86px;width:100px;height:100px;display:inline-block;"><dl><span background="red" id="icon-show" class="iconfont icon-woyaoweixiu"></span><span></span></dl></div>
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

	//处理iframe
	$("#iframe-icons").load(function() {
 		 var content = $("#iframe-icons").contents();
	//移除不必要的部分

		var html_=$("#iframe-icons").contents().find("ul").html();
		content.find(".main").html(html_).addClass('icon_lists').addClass('clear');
		content.find(".helps").remove();
		content.find('li').each(function(){
			$(this).find(".code").remove();
			$(this).find(".name").remove();
			$(this).find(".fontclass").hide();
			$(this).click(function(){
				//截取与拼接iconfont的class
				$(this).addClass("choose");
				var fontclass = $(this).find('i').attr("class");
				fontclass=fontclass.split("icon ")[1];
				$("#icon").val(fontclass);
				$("#icon-show").removeClass().addClass('iconfont').addClass(fontclass);
	
	});
		
});
});

	
	
 	loadtype();
	$("#bgcolor").bigColorpicker(function(){
					$("#bgcolor").val($("#bigHexColorText").val());
					$("#icon-show").css("background",$("#bgcolor").val());

				},"p");
				
	
	//初始化表单验证
	checkValidform = initValidform("mForm");
	
	var id = "${param.id}";
	var action = "${param.action}";
	if(action == "edit"){
		$("#type_id").val(id);
		$("#action").val(action);
		$(".location_add").html("编辑分类");
		$(".title_add").html("编辑分类");
		
		$.post("<%=basePath%>yunType/query.json",{"id":id},function(data){
			$("#del_"+data.delete_flag).attr("checked","checked");
			$("#icon").val(data.icon);
			$("#name").val(data.name);
			$("#bgcolor").val(data.bgcolor);
			$("#parent_id").val(data.parent_id);
			$("#sort_code").val(data.sort_code);
			$("#icon-show").css("background",data.bgcolor);
			$("#icon-show").removeClass().addClass(data.icon);
		});
	}
});


$(".btn").click(function(){
	if(checkValidform.check()){
		var url = "<%=basePath%>yunType/addType.json";
		if($("#action").val() == "edit"){
			url = "<%=basePath%>yunType/editType.json";
		}
		$("#icon").val($("#icon-show").attr("class"));
		var frm=$('#mForm').serialize();
		$.post(url,frm,function(data){
			if(data.count>0){
				layer.confirm('保存成功！是否返回列表页？', {icon: 1}, function(index){
					location.href="<%=basePath%>jsp/yun_type/list_yuntype.jsp";
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

	//加载所属大类，填充
       function loadtype(){
    		$.ajax({
    	        type: "POST",
    	        url: "<%=basePath%>yunType/types.json",
    	        async: false, //设为false就是同步请求
    	        cache: false,
    	        success: function (data) {
    	        	var type_ids = $("#parent_id");
    	        	//清空下拉框
    	        	type_ids.empty();
    	        	//动态绑定子项
    	        	type_ids.append("<option value='0' selected>一级大类</option>");
    	        	for(var i=0;i<data.length;i++){
    	        		if(data[i].parentid==0){
    				        type_ids.append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
    				     
    			        }	
    			    }
    	        }
    	    });
    	}


</script>

           
</body>
</html>