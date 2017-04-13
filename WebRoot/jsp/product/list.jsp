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
<title>商品列表</title>
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
            <li><a href="javascript:;">图灵买商品</a></li>
            <li><a href="javascript:;">商品管理</a></li>
        </ul>
    </div>
    
    <div class="formbody">
        <div class="formtitle"><span>商品列表</span></div>
        <div class="rightinfo">
            <div class="tools">
                <ul class="toolbar">
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_add")){%>
                	<li id="add"><span><img src="<%=basePath%>images/t01.png" /></span>新增</li>
                	<%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_edit")){%>
                    <li class="edit"><span><img src="<%=basePath%>images/t02.png" /></span>编辑</li>
                    <%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_delete")){%>
                    <li class="delete"><span><img src="<%=basePath%>images/t03.png" /></span>删除</li>
                    <%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_editparam")){%>
                	<li id="editparam"><span><img src="<%=basePath%>images/param.png" /></span>商品参数管理</li>
                	<%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_editimage")){%>
                	<li id="editimage"><span><img src="<%=basePath%>images/img.png" /></span>商品图片管理</li>
                	<%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_editimage")){%>
                	<li id="asyzcy"><span><img src="<%=basePath%>images/img.png" /></span>政采云同步</li>
                	<%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_search")){%>
                    <li class='select' style="margin-left: 20px;"><input type="text" class="dfinput" /></li>
                    <li class="search"><span><img src="<%=basePath%>images/t06.png" /></span>查询</li>
                    <%}%>
                </ul>
            </div>
			<input id="type" type="hidden" value = "">
			<div class="tablediv">
            <table class="tablelist" id="product_table">
                <thead>
                    <tr>
                    	<th></th>
                    	<th>排序ID</th>
                        <th>商品名称</th>
                        <th>商品图片</th>
                        <th>原价</th>
                        <th>现价</th>
                        <th>总数量</th>
                        <th>库存</th>
                        <th>微信是否可见</th>
                        <th>政采云同步</th>
                        <th>状态</th>
                    </tr>
                </thead>
    			<tbody id="content"></tbody>
    			<script id="template" type="text/x-jquery-tmpl">
    			<tr>
					<td width="5%"><input name="check" type="checkbox" value="${id}" /></td>
					<td width="5%">${sortid}</td>
    				<td width="28%"><div style="width:250px;display:block;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="${name}">${name}</div></td>
    				<td width="8%" height="110px"><img src="<%=basePath%>${img_url}" height="100px" style="max-width:100px;" onerror="this.src='<%=basePath%>images/no_pic.png'"/></td>
					<td width="5%"><font style="color:#888888;text-decoration:line-through">￥${price_old}</td>
					<td width="5%">￥${price_new}</td>
					<td width="6%">${count}</td>
					<td width="5%">${overplus}</td>
					<td width="12%">{{if is_wx == 1}}<font style="color:#4aba2c">微信可见</font>{{/if}}{{if is_wx != 1}}<font style="color:red">微信不可见</font>{{/if}}</td>
					<td width="12%">${is_zcy}</td>
					<td width="10%">{{if delete_flag == 0}}<font style="color:#4aba2c">已上架</font>{{/if}}{{if delete_flag != 0}}<font style="color:red">已下架</font>{{/if}}</td>
    			</tr>
    			</script>
            </table>
            </div>

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
			$("#type").val("<%=request.getParameter("type")==null?"":request.getParameter("type")%>");
			initData();
            
            //新增
            $("#add").click(function(){
            	location.href = "add.jsp";
            });
            
            //编辑
            $(".edit").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选选择编辑数据", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var id = $("input[name='check']:checked").val();
            		location.href = "add.jsp?action=edit&id="+id;
            	}else{
            		layer.alert("只能选择一条数据", {icon: 7});
            	}
            });
            //产品参数管理
            $("#editparam").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选选择需要编辑参数的商品", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var id = $("input[name='check']:checked").val();
            		layer.open({
					    type: 2,
					    area: ['600px', '500px'],
					    fix: false, //不固定
					    maxmin: true,
					    content: "list_param.jsp?product_id="+id,
					    title:'产品参数管理'
					});
            	}else{
            		layer.alert("只能选择一个商品", {icon: 7});
            	}
            });
            
            //产品图片管理
            $("#editimage").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选选择需要编辑图片的商品", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var id = $("input[name='check']:checked").val();
            		layer.open({
					    type: 2,
					    area: '900px',
					    shade:[0.8, '#393D49'],
					    offset:'10px',
					    scrollbar:true,
					    fix: true, //不固定
					    maxmin: true,
					    content: "list_image.jsp?product_id="+id+"&item_id=0",
					    title:'产品图片管理'
					});
            	}else{
            		layer.alert("只能选择一个商品", {icon: 7});
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
						$.post("<%=basePath%>product/delete.json",{"id":id},function(data){
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
	        //政采云同步
	        $("#asyzcy").click(function(){
	        if($("input[name='check']:checked").length != 0){
	        layer.load();
	        var id="";
		            	$("input[name='check']:checked").each(function(){
							id=id+this.value+",";
				        });
            			if(id!=""){
				    		id=id.substring(0,id.length-1);
				    	}
	        //这里写验证是不是需要同步
	        	$.post("<%=basePath%>zcy/syncProduct.json",{"id":id},function(data){
						var result = data.result;
						layer.alert(result);
						initData();
	        		
	        	});
				}else{
	        		layer.alert("请选择待同步数据", {icon: 7});
	       		 }
	        });
	        
        });
       
        function initData(){
        	//显示加载层
        	layer.load();
        	var keyword = $(".dfinput").val();
        	var type=$("#type").val();
            $.post("<%=basePath%>product/queryCount.json",{"keyword":keyword,"type":type},function(data){
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
        	pageIndex=index;
        	$("#index").html(index+1);
        	var keyword = $(".dfinput").val();
        	var type=$("#type").val();
        	$.post("<%=basePath%>product/queryList.json",{"keyword":keyword,"pageIndex":index,"pageSize":pageSize,"type":type},function(data){
	            $("#content").html($("#template").tmpl(data));
	            //加载表格样式,公用
	            loadTableStyle();
	            //关闭加载层
	        	layer.closeAll('loading');
	        	//双击事件加载
	        	dblClick('<%=basePath%>');
	        	
        	});
        }
    </script>
</body>
</html>