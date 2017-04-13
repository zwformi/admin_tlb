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
<title>角色列表</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/pagination.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	body{min-width:300px;}
    tbody .no {width: 80px; text-align: center; text-indent: 0px;}
</style>
</head>

<body>
	<input id="id" name="id" type="hidden"/>

    <div class="formbody">
        <div class="rightinfo">
            <div class="tools">
                <ul class="toolbar">
                    <li class='select'><input type="text" class="dfinput" /></li>
                    <li class="search"><span><img src="<%=basePath%>images/t06.png" /></span>查询</li>
                    <li id="save" style="margin-left: 20px;"><span><img src="<%=basePath%>images/t09.png" /></span>保存</li>
                </ul>
            </div>

			<div id="tip" style="margin:5px"></div>

            <table class="tablelist">
                <thead>
                    <tr>
                    	<th></th>
                        <th>角色名称</th>
                        <th>备注</th>
                    </tr>
                </thead>
    			<tbody id="content"></tbody>
    			<script id="template" type="text/x-jquery-tmpl">
    			<tr>
					<td width="35"><input name="check" type="checkbox" value="${role_id}" /></td>
    				<td>${role_name}</td>
					<td>${remarks}</td>
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

            $("#id").val(getUrlParam("id"));

    		$.post("<%=basePath%>system_role/query2.json",{"id":getUrlParam("id")},function(data){
				if(data == null || data.role_name == undefined){
	    			$("#tip").html("<span style='color:red;font-size:14px'>未分配角色</span>");
				}else{
					$("#tip").html("<span style='color:green;font-size:14px'>已分配角色："+data.role_name);
				}
    		});
            
            //保存
            $("#save").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择角色", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var role_id = $("input[name='check']:checked").val();

                    $.post("<%=basePath%>system_user/modify2.json",{"id":$("#id").val(),"role_id":role_id},function(data){
            			if(data.success){
            				layer.alert("保存成功！", {icon: 1}, function(index){
            				    layer.close(index);

                				//当你在iframe页面关闭自身时
                				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                				parent.layer.close(index); //再执行关闭 
            				});
            			}else{
            				layer.alert(data.message, {icon: 2});
            			}
            		});
            		
            	}else{
            		layer.alert("只能选择一条数据", {icon: 7});
            	}
	        });
            
            //查询
            $(".search").click(function(){
	        	initData();
	        });

        });

    	//获取url中的参数
        function getUrlParam(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]); return null; //返回参数值
        }
        
        function initData(){
        	//显示加载层
        	layer.load();
        	var keyword = $(".dfinput").val();
            $.post("<%=basePath%>system_role/queryCount.json",{"keyword":keyword},function(data){
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
        	$.post("<%=basePath%>system_role/queryList.json",{"keyword":keyword,"pageIndex":index,"pageSize":pageSize},function(data){
	            $("#content").html($("#template").tmpl(data));
	            //加载表格样式,公用
	            loadTableStyle();
	            //关闭加载层
	        	layer.closeAll('loading');
        	});
        }
    </script>
</body>
</html>