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
<link href="<%=basePath%>js/validform/validform_ext.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="<%=basePath%>js/editor/themes/default/default.css" />
<link href="<%=basePath%>js/uploader/webuploader_ext.css"
	rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<script src="<%=basePath%>js/jquery.pagination.js"></script>
<script src="<%=basePath%>js/jquery.tmpl.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/validform/validform_v5.3.2_min.js"></script>
<script src="<%=basePath%>js/validform/validform_ext.js"></script>
<script charset="utf-8" src="<%=basePath%>js/editor/kindeditor-min.js"></script>
<script charset="utf-8" src="<%=basePath%>js/editor/lang/zh_CN.js"></script>
<script src="<%=basePath%>js/uploader/webuploader.min.js"></script>
<script src="<%=basePath%>js/uploader/webuploader_ext.js"></script>
<link rel="stylesheet" href="iconfont.css"/>
<link rel="stylesheet" href="<%=basePath %>js/colorpicker/js/jquery.bigcolorpicker.css"/>
<script src="<%=basePath%>js/colorpicker/js/jquery.bigcolorpicker.min.js"></script>
<script src="<%=basePath%>js/laydate/laydate.js"></script>
<%-- <script src="<%=basePath %>js/addpage.js"></script> --%>
<style type="text/css">
.laydate-icon {
	width: 325px !important;
	height: 32px !important;
	border-top: solid 1px #a7b5bc !important;
	border-left: solid 1px #a7b5bc !important;
	border-right: solid 1px #ced9df !important;
	border-bottom: solid 1px #ced9df !important;
}

.select {
	height: 40px;
	line-height: 40px;
}

.select input {
	margin-left: 30px;
	margin-right: 5px;
}

#ms_div {
	display: none;
}

#ms_div2 {
	display: none;
}

.form_margin{
	line-height:50px;
	white-space:nowarp;
	word-break:keep-all;
	padding:50px;

}

.tablelist  tr td input{
	width:90%;
	height:90%;
	text-align:center;
	border:1px solid #3EAFE0;
}

.tablelist tbody tr td input:disabled{
	border:0 ;
	background-color:transparent;
}
.tablelist tr td select{
	width:90%;
	height:90%;
	text-align:center;
	border:1px solid #3EAFE0;
}
.tablelist tr td a{
	width:60px;
	height:30px;
	text-align:center;
}
.tablelist tr td a img{
 width:15px;
}
.tablelist td {
    line-height: 35px;
    text-indent: 0px;
    border-right: dotted 1px #c7c7c7;
}
 #icon-show{
    	
    	color:#fff;
    	width:80px;
    	line-height:80px;
    	height:80px;
    	margin:10px;
    	font-size:60px;
    	text-align:center;
    	border-radius:20px;
    	background:red;
    }
</style>
</head>

<body>
	<hr>
		<form id="mForm">
			<input id="module_id" name="module_id" type="hidden" /> 
			<input id="icon" name="icon" type="hidden" />
			<input id="is_combination" name="is_combination" value="0" type="hidden" /> 
			<input id="action" name="action" type="hidden" />
			<div class="place">
				<span>位置：</span>
				<ul class="placeul">
					<li><a href="<%=basePath%>jsp/firstpage.jsp">首页</a></li>
					<li><a href="javascript:;">图灵云模块</a></li>
					<li><a href="<%=basePath%>jsp/yun/list.jsp">模块管理</a></li>
					<li><a href="javascript:;" class="location_add">新增模块</a></li>
				</ul>
			</div>

			<div class="formbody">
				<div class="formtitle">
					<span class="title_add">新增模块</span>
				</div>
				<ul class="forminfo">
			<table class="forminfo form_margin" width="100%">
	
			<tr>
				<td width="4%"><label>所属类型：</label></td>
				<td width="46%">
				<dl>
					<select id="type_id" name="type_id"></select>
				</dl></td>
				
				
	
			</tr>
			
			<tr>
				<td><label>模块名称：</label></td>
				<td>
					<dl>
						<input id="name" name="name" type="text" class="dfinput"
							datatype="*2-100" nullmsg="请填写模块名称" />
				</dl></td>
				
		
			</tr>
				<tr>
				<td><label>默认目标路径：</label></td>
				<td>
					<dl>
						<input id="url" name="url" type="text" class="dfinput"
							datatype="*2-100" nullmsg="请填写默认目标路径" />
				</dl></td>
				
		
			</tr>
						
			<tr>		
				<td><label>排序ID：</label></td>
				<td><dl>
						<input id="sort_code" name="sort_code" title="数字越大越靠前" type="text" class="dfinput"
							datatype="n1-6" nullmsg="请填写排序ID" />
				</dl></td>
				
			
			</tr>
				
			
			<tr>
				<td><label>模块市场价：</label></td>
				<td><dl>
						<input id="price_old" name="price_old" type="text" class="dfinput"
							datatype="n1-6" nullmsg="请填写市场价" />
				</dl></td>
				
				
			</tr>
			<tr>
			<td><label>模块销售价：</label></td>
				<td><dl>
						<input id="price_new" name="price_new" type="text" class="dfinput"
							datatype="n1-6" nullmsg="请填写销售价" />
				</dl></td>
			</tr>
			<tr>		
				<td><label>图标颜色：</label></td>
				<td><dl>
						<input id="bgcolor" name="bgcolor"  type="text" class="dfinput"
							datatype="/^#?([a-f]|[A-F]|[0-9]){3}(([a-f]|[A-F]|[0-9]){3})?$/" nullmsg="请选择图标颜色" />
				</dl></td>
				
			
			</tr>
			<tr>
			<td><label>使用图标</label></td>
           
          
	<td>
     <div width="70%" style="float:left; border:1px solid #a7b5bc">
             <iframe id="iframe-icons" name="iframe" width="720px" height="360px" frameborder="1px solid #000" scrolling="scroll" vspace="0" hspace="0" src="<%=basePath%>jsp/yun/demo.jsp"></iframe>
             
     </div> 

                <div style="border:2px solid #a7b5bc;margin-left:86px;width:100px;height:100px;display:inline-block;"><dl><span background="red" id="icon-show" class="iconfont"></span><span></span></dl></div>
           </td></tr>
			<tr>
				<td><label>商品状态：</label></td>
				<td><dl class="select">
						<input type="radio" name="delete_flag" id="del_0" value="0" checked="checked" />上架
						<input type="radio" name="delete_flag" id="del_1" value="1" />下架
				</dl></td>
			</tr>
			<tr>
			
				<td><label>是否默认开通：</label></td>
				
				<td><dl class="select">
						<input type="radio" name="default_flag" id="def_0" value="0" checked="checked" />不默认开通
						<input type="radio" name="default_flag" id="def_1" value="1" />默认开通
				</dl></td>
			</tr>
			
				
				</table>
					</table>
				
					<li><label>模块详情：</label>
						<dl>
							<textarea id="introduction" name="introduction"></textarea>
						</dl></li>

					<li><input type="button" class="btn" value="确认保存" /></li>
				</ul>
			</div>
		</form>
		<script>

		//初始化上传
		var editor;
		var intro;
	    var checkValidform;
	    var id = "${param.id}";
	    var action = "${param.action}";
	    var postdata ={};
	    var url = "stringmap.json";
	    var pColor=new Array();
	    var pSize=new Array();
	    var pMemories=new Array();
	    var pVersions=new Array();
		var html_version = '';
		var html_color = '';
		var html_size = '';
		var html_memory = '';
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
				$(this).addClass("choose");
				//截取与拼接iconfont的class
				var fontclass = $(this).find('i').attr("class");
				fontclass=fontclass.split("icon ")[1];
				$("#icon").val(fontclass);
				$("#icon-show").removeClass().addClass('iconfont').addClass(fontclass);
	
	});
		
});
});
			
				$("#bgcolor").bigColorpicker(function(){
					$("#bgcolor").val($("#bigHexColorText").val());
					$("#icon-show").css("background",$("#bgcolor").val());

				},"p");
				
				
			//初始化表单验证
			checkValidform = initValidform("mForm");
			//初始化上传控件
			$(".upload-img").InitUploader({
				sendurl : "<%=basePath%>sys/uploadPic_product.json", swf: "<%=basePath%>js/webuploader/uploader.swf" });

    	loadtype();
    	
    		//初始化编辑器样式
    KindEditor.ready(function(K) {
        editor = KindEditor.create('#introduction', {
            width: '90%',
            height: '400px',
            resizeType: 1,
            uploadJson: '<%=basePath%>sys/editorUpload.json',
            fileManagerJson: '<%=basePath%>sys/editorFileManage.json',
            allowFileManager: true
        });

	});
    	
    	 if(action == "edit"){
    		$("#module_id").val(id);
    		$("#action").val(action);
    		$(".location_add").html("编辑商品信息");
    		$(".title_add").html("编辑商品信息");
    		$.post("<%=basePath%>yun_module/query.json",{"module_id":id},function(data){
    			if(data.error!=true){
    			var m = data.module;
    			$("#name").val(m.name);
    			$("#type_id").val(m.type_id);
    			$("#url").val(m.url);
    			$("#sort_code").val(m.sort_code);
    			$("#icon-show").removeClass().addClass(m.icon);
    			$("#icon-show").css("background",m.bgcolor);
    			$("#del_"+m.delete_flag).attr("checked","checked");
    			$("#def_"+m.default_flag).attr("checked","checked");
    			$("#default_flag").val(m.default_flag);
    			$("#price_old").val(m.price_old);
    			$("#price_new").val(m.price_new);
    			$("#delete_flag").val(m.delete_flag);
   				$("#bgcolor").val(m.bgcolor);
   				editor.html(m.introduction);
   				intro = m.introduction;
    			}else{
    				layer.msg(data.message);}
    			});
    			}
    
	
   

		
    $(".btn").click(function(){
    	if(checkValidform.check()){
    			var url = "<%=basePath%>yun_module/add.json";
    		if($("#action").val() == "edit"){
    			url = "<%=basePath%>yun_module/update.json";
    		}
    		$("#introduction").val(editor.html());
    		$("#icon").val($("#icon-show").attr("class"));
    		var frm=$('#mForm').serialize();
    		$.post(url,frm,function(data){
    			if(data.count>0){
    				
    				layer.confirm('保存成功！是否返回模块列表？', {icon: 1}, function(index){
    					location.href="<%=basePath%>/jsp/yun/list.jsp";
    				});
    				if($("#action").val() != "edit"){
    					$('#mForm')[0].reset();
    					editor.html("");
    				}
    			}else{
    				layer.alert(data.message, {icon: 2});
    			}
    		});
    	}
    		
    		
    });
   	     	});
   	      	//加载所属大类，填充
       function loadtype(){
    		$.ajax({
    	        type: "POST",
    	        url: "<%=basePath%>yunType/types.json",
    	        async: false, //设为false就是同步请求
    	        cache: false,
    	        success: function (data) {
    	        	var type_ids = $("#type_id");
    	        	//清空下拉框
    	        	type_ids.empty();
    	        	//动态绑定子项
    	        	type_ids.append("<option value='0' selected>请选择所属类型</option>");
    	        	for(var i=0;i<data.length;i++){
    	        		if(data[i].parentid==0){
    				        type_ids.append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
    				        buildNode(1,data[i].child,data[i].id);
    			        }	
    			    }
    	        }
    	    });
    	}
    	
    	//递归循环下拉信息
    		function buildNode(len, data, parent_id) {
 
    			if (data != undefined) {
    				var prefix = "└";
    				for (var i = 0; i < len; i++) {
    					prefix += "—";
    				}
    				$.each(data, function(i, item) {
    					if (0 < item.child.length) {
    						
    						$('#type_id').append(
    								"<option value='"+ item.id +"'>" + prefix
    										+ item.name + "</option>");
    						buildNode(len + 1, item.child, aaa);
    					} else {
    						$('#type_id').append(
    								"<option value='"+item.id +"'>"
    										+ prefix + item.name + "</option>");
    					}
    				});
    			}
    		} 
    		

			//正则判断
			function checkInput(type,str){
				var number = /^[1-9]\d*$/;
				var decimal =/^\d+(\.\d+)?$/ ;

				if(type=="number")
				return number.test(str);
				if(type=="decimal")
				return decimal.test(str);
			}
		
		//替换@为$,解决jstl与tmpl的冲突问题	
    		$.fn.tpl = function(data){
    	       $.template('template', $(this).html().replace(/@/g,"$"));
    	       return $.tmpl('template', data);
    	   }
    	
	</script>
</body>
</html>