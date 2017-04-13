<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开通</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>js/validform/validform_ext.css"
	rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/stringmap.js"></script>
<script src="<%=basePath%>js/validform/validform_v5.3.2_min.js"></script>
<script src="<%=basePath%>js/validform/validform_ext.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/laydate/laydate.js"></script>
<script src="<%=basePath%>js/stringmap.js"></script>
<style type="text/css">
	body{min-width:400px;}
	#content input{padding:5px;width:87%;}
	.tablelist label{width:100px;}
	.tablelist td{left:0; text-indent:0;text-align:center;}
	.url{text-align:left !important;text-indent:10px !important;}
</style>
</head>

<body>
    <div class="formbody">
    
    		
			<form id="mForm">
             <table class="tablelist">
                <thead>
                    <tr>
                    	<th width="5%">序号</th>
                        <th  width="25%">模块名称</th>
                        <th  width="10%">所属类型</th>
                        <th  width="30%">分配路径</th>
                        <th  width="10%">开通状态</th>
                        <th  width="10%">下架标记</th>
                        <th  width="10%">操作</th>
                    </tr>
                </thead>
    			<tbody id="content">
    				
    			</tbody>
            </table>
                 </form>
    </div>
	
 <script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引		
			//初始化表单验证
// 	var checkValidform ;
	var id ="${param.id}";
	
			
$(document).ready(function () {
	initData();
	  
    	   
      });
    	
    	function initData(){
        	//显示加载层
        	layer.load();
       
            $.post("<%=basePath%>order/query_yun_detail.json",{"id":id},function(data){
            	var modulelist = data.MODULELIST;
            	var html_="";
            	for(var i=0;i<modulelist.length;i++){
            		var is_open = modulelist[i].is_open;
            		var is_delete = modulelist[i].delete_flag;
            		var delete_ = "";
            		if(is_delete==0){
            			delete_ = "<a href=\"javascript:void(0)\" onclick=\"del(this)\" title=\"下架\"><img src=\"<%=basePath%>images/t07.png\" width=\"15\"/></a>";
            		}else if(is_delete==1){
            			delete_ = "<a href=\"javascript:void(0)\" onclick=\"del(this)\" title=\"上架\"><img src=\"<%=basePath%>images/t07.png\" width=\"15\"/></a>";
            		}
            		
            		html_ +=  "<tr is_open=\""+is_open+"\" id=\""+modulelist[i].id+"\"><td>"+(i+1)+"</td><td>"+modulelist[i].name+"</td><td>"+modulelist[i].type_name+"</td><td class=\"url\">"+modulelist[i].url+"</td><td class=\"is_open\">"+(is_open==0?"未开通":(is_open==1?"已开通":"待开通"))+"</td><td>"+(modulelist[i].delete_flag==0?"正常":"已下架")+"</td><td><a href=\"javascript:void(0)\" onclick=\"edit(this)\" title=\"修改\"><img src=\"<%=basePath%>images/t02.png\" width=\"15\"/></a>&nbsp;&nbsp;&nbsp;"+delete_+"</td></tr>";

            	}
            	$("#content").html(html_);
             	 loadTableStyle();
            	 
            	
	          layer.closeAll('loading');
            });
        }  
    	    
		function edit(e) {
    	    
    	    var detail = $(e).parent().parent();
    	    var id = detail.attr("id");
			var is_open = detail.attr("is_open");
			var url = detail.find(".url").html();
           	var is_kaitong = "";
    	    
    	    if($(e).attr("title")=="修改"){	//修改的情况
    			$(e).html("<img src=\"<%=basePath%>images/check_ok.png\" width=\"15\"/>");
    			$(e).attr("title","保存");
    		
            if(is_open==0){
            	
            }else if(is_open==1){
            	
            }else if(is_open==2){
            	is_kaitong = "<select><option value='1'>开通</option><option value='2' selected>待开通</option></select>";
           		 detail.find(".is_open").html(is_kaitong);
           		 detail.find(".is_open").find("select").css("border","1px solid #a7b5bc").css("height","80%");
           		
            }  
           	 detail.find(".url").html("<input type=\"text\" value=\""+url+"\">");
           	 detail.find(".url").find("input").css("border","1px solid #a7b5bc").css("width","85%");

    	  
			}else{	//保存的情况
				layer.load();
				if(is_open==2){	//待开通的状况
					is_open = detail.find("select").val();
				}else{	//已经开通及其他的状况
					is_open = null;
				}
				url = detail.find(".url").find("input").val();
				console.log({"id":id,"is_open":is_open,"url":url});
				$.post("<%=basePath%>order/dkhkt.json",{"id":id,"is_open":is_open,"url":url},function(data){
            		if(data.count==1){
            			layer.closeAll('loading');
            			layer.msg("修改成功！");
						$(e).html("<img src=\"<%=basePath%>images/t02.png\" width=\"15\"/>");
    					$(e).attr("title","修改");
    					initData();
            		}else{
            			layer.msg("操作失败！");
            			}
            	});							
			}
    		
    	}
    	
    	function del(e){
    		var detail = $(e).parent().parent();
    	    var id = detail.attr("id");
    	    var delete_flag = $(e).attr("title");
    	    var title = "";
			if(delete_flag=="下架"){
				delete_flag=0;
				title = "上架";
			}else if(delete_flag=="上架"){
				delete_flag=1;
				title = "下架";
			}else{
				layer.msg("非法操作！");
			}
           	$.post("<%=basePath%>order/dkhkt.json",{"id":id,"delete_flag":delete_flag},function(data){
            	if(data.count==1){
            		layer.msg("修改成功！");
            		$(e).attr("title",title);
            		initData();
            	}
           	
    		});
		}
    	
    </script>
</body>
</html>