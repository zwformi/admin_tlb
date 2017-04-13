<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增分类</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<link href="<%=basePath%>js/validform/validform_ext.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/validform/validform_v5.3.2_min.js"></script>
<script src="<%=basePath%>js/validform/validform_ext.js"></script>
<link href="<%=basePath%>js/uploader/webuploader_ext.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/uploader/webuploader.min.js"></script>
<script src="<%=basePath%>js/uploader/webuploader_ext.js"></script>
<script>
    $(function () {
    	//初始化上传控件
        $(".upload-img").InitUploader({ sendurl: "<%=basePath%>sys/uploadPic.json", swf: "<%=basePath%>js/webuploader/uploader.swf" });
    });
</script>
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
            <li><a href="<%=basePath%>jsp/product_type/list.jsp">分类管理</a></li>
            <li><a href="javascript:;" class="location_add">新增分类</a></li>
        </ul>
    </div>
    
    <div class="formbody">
    <div class="formtitle"><span class="title_add">新增分类</span></div>
        <ul class="forminfo">
            <li><label>分类名称：</label>
                <dl><input id="name" name="name" type="text" class="dfinput" datatype="*2-30" nullmsg="请填写分类名称" /></dl>
            </li>
            <li><label>分类等级：</label>
                <dl><select id="product_level" name="product_level">
                <option value='0' >请选择分类级别</option>
                <option value='1' >一级大类</option>
                 <option value='2' >二级小类</option>
                  <option value='3' >三级类</option>
                </select>
                </dl>
            </li>
            
            <li class="parent"><label>所属一级分类：</label>
                <dl><select id="parentId" name="parentId"></select></dl>
            </li>
            <li class="sub"><label>所属二级分类：</label>
                <dl><select id="subParentId" name="subParentId"></select></dl>
            </li>
            <li><label>分类图片：</label>
                <dl><input id="img_url" name="img_url" type="text" class="dfinput upload-path" /><div class="upload-box upload-img" style="top:-3px; left:-1px"></div></dl>
            </li>
            <li><label>排序ID：</label>
                <dl><input id="sortId" name="sortId" type="text" class="dfinput" datatype="n1-6" nullmsg="请填写排序ID" /> <a style="color:#999">数字越大越靠前</a></dl>
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
	$(".parent").hide();
	$(".sub").hide();
	
	//初始化表单验证
	checkValidform = initValidform("mForm");
	
	var id = "${param.id}";
	var action = "${param.action}";
	var from = "${param.location}";
	if(action == "edit"){
		$("#id").val(id);
		$("#action").val(action);
		$(".location_add").html("编辑分类");
		$(".title_add").html("编辑分类");
		
		$.post("<%=basePath%>product_type/query.json",{"id":id},function(data){
			$("#name").val(data.name);
			$("#img_url").val(data.img_url);
			$("#product_level").val(data.product_level);
			var level = data.product_level;
			if(level == 1){
				$(".parent").hide();
				$(".sub").hide();
			}else if(level == 2){
				$(".sub").hide();
				loadtype();
				$(".parent").show();
			}else if(level ==3){
				$(".parent").show();
				$(".sub").show();
				loadtype();
				$("#parentId").val(data.parentId);
				loadType2();
				$("#subParentId").val(data.sub_parentId);
				
			}else{
				
			}
			$("#parentId").val(data.parentId);
			$("#sortId").val(data.sortId);
			$("#sub_parentId").val(data.sub_parentId);
			
		});
	}


$("#product_level").change(function(){
	var level = $("#product_level").val();
	if(level == 1){
		$(".parent").hide();
		$(".sub").hide();
	}else if(level == 2){
		$(".sub").hide();
		$(".parent").show();
		loadtype();
	}else if(level ==3){
		$(".parent").show();
		$(".sub").show();
		loadType2();
	}else{
		$(".parent").hide();
		$(".sub").hide();
	}
});
$(".btn").click(function(){
	var level = $("#product_level").val();
	var parentId = $("#parentId").val();
	var sub_parentId = $("#subParentId").val();
	
	if(checkValidform.check()){
		//3种校验
	if(level ==1){
		$(".parent").remove();
		$(".sub").remove();
	}else if(level ==2){
		
		if(parentId ==0){
			layer.alert("请选择一级大类！",{icon:7});
			return;
		}else{
			$(".sub").remove();
		}
	}else if(level ==3){
		if(parentId ==0||sub_parentId==0){
			layer.alert("请选择二级小类与一级大类！",{icon:7});
			return;
		}	
	}else{
		layer.alert("请选择分类级别！",{icon:7});
		return;
	}
		var url = "<%=basePath%>product_type/add.json";
		if($("#action").val() == "edit"){
			url = "<%=basePath%>product_type/modify.json";
		}
		
		var frm=$('#mForm').serialize();
		var href = "<%=basePath%>jsp/product_type/list.jsp";
		if(from=="tree"){
			href = "<%=basePath%>jsp/product_tree/list.jsp";
		
		}
		$.post(url,frm,function(data){
			if(data.success){
				layer.confirm('保存成功！是否返回列表页？', {icon: 1}, function(index){
					location.href=href;
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

});
//加载分类
function loadtype(){
		$.ajax({
    	        type: "POST",
    	        url: "<%=basePath%>product_type/queryList.json",
    	        async: false, //设为false就是同步请求
    	        cache: false,
    	        success: function (data) {
    	        	types = data;
    	        	//清空下拉框
    	        	//3个下拉框，分别填充,先填充第一个,3个都必须填充
  					$("#parentId").empty().append("<option value='0' selected>请选择所属类别</option>");
  					for(var i=0;i<data.length;i++){
  					if(types[i].product_level==1){
  						$("#parentId").append("<option value='"+types[i].id+"'>"+types[i].name+"</option>");
  					}
    	        	}
    	        	
    	       
    	}
    	});
}

	$("#parentId").change(function(){
		loadType2();
	
	});

function loadType2(){
    		var parentid = $("#parentId").val();
    		$("#subParentId").empty().append("<option value='0' selected>请选择所属类型</option>");
  			//根据parent值加载
  			for(var i=0;i<types.length;i++){
  					if(types[i].product_level==2&&types[i].parentId==parentid){
  						$("#subParentId").append("<option value='"+types[i].id+"'>"+types[i].name+"</option>");
  					}		
    		}
    		}
</script>
</body>
</html>