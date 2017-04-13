<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>产品图片列表</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.imgboxs::-webkit-scrollbar{
	background-color:#D3D3D3;
	border-radius:4px;
	width:10px;
}

	.imgboxs::-webkit-scrollbar-thumb{
	
	background-color:#0087BA;
	border-radius:4px;
}
	
	body{
	min-height:500px;
	min-width:600px;
	}
	.cover_image{
		border:2px solid #0087BA !important;  
		
	}
	.formbody{
		width:650px;
		margin-left:200px;
	}
	 #mask {
    position: fixed;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    background: rgba(0, 0, 0, .5);
    z-index: 99999;
    display:none;
}
	.update_img{z-index:77;position:fixed;top:0;width:650px;padding:10px;border-bottom:2px solid #edece7;background-color:#fff;}
	.imgboxs{height:250px;overflow-y:auto;margin-top:70px;padding-top:10px;}
	.imgboxs .noimg{border:1px solid #fdefbf;padding:10px;background-color:#fff8e1}
	.imgboxs .imgbox{width:179px;height:150px;float:left;margin:5px 5px;border:2px solid #edece7;text-align:center;background-color:#fff;position: relative}
	.imgboxs .imgbox img{max-width:100%;max-height:150px;}
	.imgboxs .imgbox .operation{white-space:nowrap; text-align:center;position:absolute;top:0px;left:0px;width:100%;height:25px;line-height:25px;border:1px solid #000;color:#fff;background-color:#323231;
	filter:alpha(opacity=40); 
	-moz-opacity:0.40; 
	opacity:0.40; }
	/* product_menu */
	.product_menu
	{
	OVERFLOW-Y: auto; 
	OVERFLOW-X:hidden;
	width:180px;
	height:500px;
	}
	.product_menu ul li a{
    display:block;
    margin:5px 0;
    padding: 0 10px;
    font-size:12px;
    color:#fff;
    background-color:#38B0DE;
    width:170px;
    min-height:35px;
    line-height:35px;
    text-decoration:none;
    text-align:left;
}
	.cover{
		width:49%;
		float:left;
		cursor:pointer;
	}
		.cover2{
		width:49%;
		float:left;
	}
	.delete{
		float:right;
		cursor:pointer;
		width:49%;
	}
	
	
.product_menu ul li a:hover,.product_menu ul li a.current{
color:#fff;background:#0088bb;
}　
</style>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/layer/layer.js"></script>
	<script src="<%=basePath%>js/common.js"></script>
	<link href="<%=basePath%>js/uploader/webuploader_ext.css" rel="stylesheet" type="text/css" />
	<script src="<%=basePath%>js/uploader/webuploader.min.js"></script>
	<script src="<%=basePath%>js/uploader/webuploader_product.js"></script>
	<script src="<%=basePath%>js/stringmap.js"></script>
<script>
    $(function () {
    	//初始化上传控件
        $(".upload-img").InitUploader({ sendurl: "<%=basePath%>sys/uploadPic_product_save.json", swf: "<%=basePath%>js/webuploader/uploader.swf" });
    });
</script>
</head>

<body>
<div class="product_menu" style="float:left;">
    <ul>
    </ul>
</div>
    <div class="formbody" >
    	<div class="update_img" style="">
    	<br>
    	选择商品图片进行上传：<div class="upload-box upload-img" style="top:-3px; left:-1px;"></div>
    	<input id="product_items_id" type="hidden" value="${(param.item_id==null||empty param.item_id)?0:param.item_id}">
    	<input id="product_id" type="hidden" value="${param.product_id}">
    	</div>

    	<div class="imgboxs" >
    	</div>
    </div>
    <script type="text/javascript">
    	var item_id = "${param.item_id}";
    	var product_id = "${param.product_id}";
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        var itemlist={};
		var p_color = getStringmap("Product_Items", "product_Color");
		var p_size = getStringmap("Product_Items", "product_Size");
		var p_version = getStringmap("Product_Items", "product_Versions");
		var p_memory = getStringmap("Product_Items", "product_Memories");

    	$(document).ready(function () {
            initData(); 
             loadItems();           
            $(".imgboxs").on("mouseenter",".imgbox",function(){
            	if($(this).attr("coverImage")==1){
            	$(this).append("<div class=\"operation\"><div class=\"cover2\">默认</div>|<div class=\"delete\">删除</div></div>");
            	}else{
            	$(this).append("<div class=\"operation\"><div class=\"cover\">设为默认</div>|<div class=\"delete\">删除</div></div>");
            	
            	}
            });
            $(".imgboxs").on("mouseleave",".imgbox",function(){
             	$(this).find(".operation").remove();
            });
            //删除
            $(".formbody").on("click",".delete",function(){
            	var id = $(this).parent().parent().attr("imgid");
            	var cover_img = $(this).parent().parent().attr("coverImage");
            	var product_id = $("#product_id").val();
            	var hint = "";
            	if(cover_img==1){
            		hint ="该商品为默认封面图片，删除可能造成显示错误，";
            	}
            		hint +="确认删除该图片吗 ？";
            		parent.layer.confirm(hint, {icon: 3}, function(index){
						$.post("<%=basePath%>product/deleteImage.json",{"id":id,"product_id":product_id},function(data){
			    			if(data.count==1){
			    		parent.layer.alert("操作成功！");
			    		parent.layer.close(index);
			    		initData();
			    		}else{
			    		parent.layer.alert("操作失败!");
			    
			    		}
			        	});
					});
					
            });
            //设为默认
            $(".formbody").on("click",".cover",function(){
            	var id = $(this).parent().parent().attr("imgid");
            	var item = $("#product_items_id").val();
            	var cover_old = $("div[coverImage=1]").eq(0).attr("imgid");
            	var product_id = $("#product_id").val();
            	parent.layer.confirm('确认设为默认图片吗？', {icon: 3}, function(index){
					$.post("<%=basePath%>product/coverImg.json",{"id":id,"product_items_id":item,"cover_old":cover_old,"product_id":product_id},function(data){
			    		if(data.count==1){
			    		parent.layer.alert("操作成功！");
			    		parent.layer.close(index);
			    		initData();
			    		$(this).parent().parent().find(".cover_image div").eq(0).removeClass("cover_image");
			    		$(this).parent().parent().addClass("cover_image");
			    		}else{
			    		parent.layer.alert("操作失败!");
			    
			    		}
			        });
				});
            });
            //图片查看
            $(".formbody").on("click",".imgbox",function(){
            	
            });
            
        });
        
        function loadItems(){
        
        	$.post('<%=basePath%>productitem/list.json',{"product_id":product_id},function(data){
        	var tag_ul = $(".product_menu").find("ul:first");
        		var _html="";
        		var qz=0;
        		for(var i in data){
						 if(i == 0)
    					 _html+='<li><a href="javascript:void(0)" onclick="topz(this)" class="current" item_id="0">商品共有图片</a></li>'; 
    					 
        				 _html+='<li><a  href="javascript:void(0)" item_id="'+data[i].productItemsId+'" onclick="topz(this)" item_id="'+data[i].productItemsId+'">'
													+(p_size[data[i].productSize+""])+"/"
        											+(p_color[data[i].productColor+""])+"/"
        											+(p_version[data[i].productVersions+""])+"/"
        											+(p_memory[data[i].productMemories+""])
							' </a></li>';
	
        		}
        		tag_ul.html(_html);
        		//加载完成后模拟点击第一个
        		var tagA =  $('.product_menu').find('a:first');
        		topz(tagA,tagA.item_id);
        	});
        }
        
        function topz(e){
        	var item_id = $(e).attr('item_id');
        	$('.product_menu').find('a[class="current"]').removeClass('current');
        	$(e).addClass('current');
        	$.post("<%=basePath%>product/queryImageList.json",{"product_id":product_id,"product_items_id":item_id},function(data){
        		var _html="";
	           for(var i=0;i<data.length;i++){
	           		var productImage = data[i];

	           		_html += "<div class=\"imgbox\"  coverImage=\""+productImage.coverImage+"\" url=\"<%=basePath%>"+productImage.img_url+"\" imgid=\""+productImage.id+"\">";   		
	           	_html +="<img onclick=\"openLarge(this)\" src=\"<%=basePath%>"+productImage.img_url+"\" onerror=\"this.src='<%=basePath%>images/no_pic.png'\"/></div>";
	          
	          				}	   
	           if(data.length==0){
	           		_html+="<div class=\"noimg\">没有任何的照片</div>";
	           }else{
	           		_html+="<div id=\"cleardiv\" style=\"clear:both;\"></div>";
	           }
	           $(".imgboxs").html(_html);
	           parent.layer.iframeAuto(index);
	          	$("#product_items_id").val(item_id); 
	          	$(".upload-box").eq(0).html("");
	          	  $(".upload-img").InitUploader({ sendurl: "<%=basePath%>sys/uploadPic_product_save.json", swf: "<%=basePath%>js/webuploader/uploader.swf" });
// 	            关闭加载层	
				$("div[coverImage=1]").eq(0).addClass("cover_image");
				$(".imgboxs").eq(0).css("height",$(document.body).height()-110);
	        	layer.closeAll('loading');
        	});
        }
        function openLarge(obj){
			window.open($(obj).parent().attr("url"));

};
        
        function initData(){
        	//显示加载层
        	layer.load();
        	topz($('.product_menu').find('a[class="current"]'));
        }


        
        
    </script>
</body>
</html>