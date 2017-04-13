<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="true" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客服人员列表</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/pagination.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/dblclick.js"></script> 
<style type="text/css">
    tbody .no {width: 80px; text-align: center; text-indent: 0px;}
</style>
</head>

<body>
	<div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="<%=basePath%>jsp/firstpage.jsp">首页</a></li>
            <li><a href="javascript:;">基础数据</a></li>
            <li><a href="javascript:;">专属客服管理</a></li>
        </ul>
    </div>
    
    <div class="formbody">
        <div class="formtitle"><span>客服人员列表</span></div>
        <div class="rightinfo">
            <div class="tools">
                <ul class="toolbar">
                <%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_user_client_add")){%>
                	<li id="add"><span><img src="<%=basePath%>images/t01.png" /></span>新增</li>
                <%}%>
                <%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_user_client_edit")){%>
                    <li class="edit"><span><img src="<%=basePath%>images/t02.png" /></span>编辑</li>
                <%}%>
                <%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_user_client_delete")){%>
                    <li class="delete"><span><img src="<%=basePath%>images/t03.png" /></span>删除</li>
                <%}%>
                <%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_user_client_search")){%>
                    <li class='select' style="margin-left: 20px;"><input type="text" class="dfinput" /></li>
                    <li class="search"><span><img src="<%=basePath%>images/t06.png" /></span>查询</li>
                <%}%>
                </ul>
            </div>

            <table class="tablelist">
                <thead>
                    <tr>
                    	<th></th>
                    	<th>客服工号</th>
                        <th>客服姓名</th>
                        <th>联系电话</th>
                        <th>QQ号码</th>
                        <th>固定电话</th>
                        <th>邮箱</th>
                    </tr>
                </thead>
    			<tbody id="content"></tbody>
    			<script id="template" type="text/x-jquery-tmpl">
    			<tr>
					<td width="35"><input name="check" type="checkbox" value="${id}" /></td>
    				<td>${gh}</td>
					<td>${name}</td>
					<td>${phone}</td>
    				<td>${qq}</td>
					<td>${telphone}</td>
					<td>${email}</td>
    			</tr>
    			</script>
            </table>

            <div class="pagin">
                <div class="message">
                共<i class="blue" id="total"></i>条记录，当前显示第&nbsp;<i class="blue" id="index"></i>&nbsp;页
                </div>
                <ul class="paginList">
                    <li id="pagination" class="pagination"></li>
                </ul>
            </div>
        </div>
		
    </div>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/layer/layer.js"></script>
	<script src="<%=basePath%>js/jquery.pagination.js"></script>
	<script src="<%=basePath%>js/jquery.tmpl.js"></script>
	<script src="<%=basePath%>js/common.js"></script>
    <script type="text/javascript">
        var pageIndex = 0;     //页面索引初始值
    	var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    	
    	$(document).ready(function () {
            initData();
            
            //新增
            $("#add").click(function(){
            	location.href = "add.jsp";
            });
            
            //编辑
            $(".edit").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择待编辑数据", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var id = $("input[name='check']:checked").val();
            		location.href = "add.jsp?action=edit&id="+id;
            	}else{
            		layer.alert("只能选择一条数据", {icon: 7});
            	}
            });
            
            //删除
            $(".delete").click(function(){
            	if($("input[name='check']:checked").length != 0){
            		layer.confirm('确认删除信息 ？', {icon: 3}, function(index){
	            		var id="";
		            	$("input[name='check']:checked").each(function(){
							id=id+this.value+",";
				        });
            			if(id!=""){
				    		id=id.substring(0,id.length-1);
				    	}
						$.post("<%=basePath%>user_client/delete.json",{"id":id},function(data){
				    		layer.close(index);
				    		initData();
				        });
					});
            	}else{
            		layer.alert("请选择待删除数据", {icon: 7});
            	}
            });
            
            //查询
            $(".search").click(function(){
	        	initData();
	        });
	        
        });
        
        function initData(){
        	//显示加载层
        	layer.load();
        	var keyword = $(".dfinput").val();
            $.post("<%=basePath%>user_client/queryCount.json",{"keyword":keyword},function(data){
            	$("#total").html(data.count);
            	//分页事件
	            $("#pagination").pagination(data.count, {
	                prev_text: "上一页",
	                next_text: "下一页",
	                //每页显示的条目数
	                items_per_page: pageSize,
	                //当前选中的页面
	                current_page: pageIndex,
	                //两侧显示的首尾分页的条目数。可选参数，默认是0
	                num_edge_entries: 2,
	                //连续分页主体部分显示的分页条目数。可选参数，默认是10
	                num_display_entries: 8,
	                //分页选择回调
	                callback: pageSelectCallback
	            });
            });
        }
        
        function pageSelectCallback(index, jq) {
        	$("#index").html(index+1);
        	var keyword = $(".dfinput").val();
        	$.post("<%=basePath%>user_client/queryList.json",{"keyword":keyword,"pageIndex":index,"pageSize":pageSize},function(data){
	            $("#content").html($("#template").tmpl(data));
	            //加载表格样式,公用
	            loadTableStyle();
	            //关闭加载层
	        	layer.closeAll('loading');
	        	dblClick('<%=basePath%>');
        	});
        }
    </script>
</body>
</html>