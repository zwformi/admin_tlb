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
<title>服务单列表</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/pagination.css" rel="stylesheet" type="text/css" />
<style type="text/css">
    tbody .no {width: 80px; text-align: center; text-indent: 0px;}
</style>
</head>

<body>
	<div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="<%=basePath%>jsp/firstpage.jsp">首页</a></li>
            <li><a href="javascript:;">服务单管理</a></li>
        </ul>
    </div>
    
    <div class="formbody">
        <div class="formtitle"><span>服务单列表</span></div>
        <div class="rightinfo">
            <div class="tools">
                <ul class="toolbar">
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_service_query")){%>
                	<li class="query"><span><img src="<%=basePath%>images/t05.png" /></span>查看服务信息</li>
                	<%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_service_detail")){%>
                	<li id="detail"><span><img src="<%=basePath%>images/t10.png" /></span>客户资料</li>
                	<%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_service_delete")){%>
                    <li class="delete"><span><img src="<%=basePath%>images/t03.png" /></span>删除</li>
                    <%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_service_over")){%>
                    <li class="over"><span><img src="<%=basePath%>images/check_ok.png" /></span>服务完成</li>
                    <%}%>
                    <%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_service_comment")){%>
                    <li class="comment"><span><img src="<%=basePath%>images/t44.png" /></span>服务备注</li>
                    <%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_service_search")){%>
                    <li class='select' style="margin-left: 20px;">
                    	<select id="state" group="service">
                    		<option value="-1" selected>请选择服务状态</option>
                    		
                    	</select>
                    	<input type="text" class="dfinput" />
                    </li>
                    <li class="search"><span><img src="<%=basePath%>images/t06.png" /></span>查询</li>
                    <%}%>
                </ul>
            </div>
			<div class="tablediv">
            <table class="tablelist">
                <thead>
                    <tr>
                    	<th></th>
                        <th>服务编号</th>
                        <th>订单编号</th>
                        <th>联系人</th>
                        <th>联系电话</th>
                        <th>发布时间</th>
                        <th>状态</th>
                        <th width="150">服务备注</th>
                    </tr>
                </thead>
    			<tbody id="content"></tbody>
    			<script id="template" type="text/x-jquery-tmpl">
    			<tr>
					<td width="35"><input name="check" type="checkbox" comment="${comment}" value="${service_number}" user_id="${user_id}"/></td>
    				<td>${service_number}</td>
					<td>${order_number}</td>
					<td>${lxr}</td>
					<td>${phone}</td>
					<td>${fmt_addr_time}</td>
					<td>
						<font style="color:red">${stateTrans}</font>
					</td>
					<td>${comment}</td>
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
	<script src="<%=basePath%>js/stringmap.js"></script>
	<script src="<%=basePath%>js/layer/layer.js"></script>
	<script src="<%=basePath%>js/jquery.pagination.js"></script>
	<script src="<%=basePath%>js/jquery.tmpl.js"></script>
	<script src="<%=basePath%>js/common.js"></script>
    <script type="text/javascript">
    	layer.config({
		    extend: '<%=basePath%>js/layer/extend/layer.ext.js'
		});
        var pageIndex = 0;     //页面索引初始值
    	var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    	
    	$(document).ready(function () {
            initData();
            loadSelect(0);
            // 查看服务详情
            $(".query").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择需要查看的服务单", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var service_number = $("input[name='check']:checked").val();
            		layer.open({
					    type: 2,
					    area: ['650px', '400px'],
					    fix: true, //不固定
					    maxmin: false,
					    content: "/jsp/service/detail.jsp?service_number="+service_number,
					    title:'合同详情'
					});
            	}else{
            		layer.alert("只能选择一份服务单进行查看", {icon: 7});
            	}
            });
            //删除
            $(".delete").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择需要删除的服务单", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		layer.confirm('确认要删除本服务单吗？<br/>删除请点击“确定”，否则点击“取消”', {icon: 3}, function(index){
	            		var service_number = $("input[name='check']:checked").val();
	            		$.post("<%=basePath%>service/delete.json",{"service_number":service_number},function(data){
					    	if(data.error){
	            				layer.msg(data.message);
	            			}else{
					    		initData();
					    		layer.msg("删除成功！");
					    	}
					    });
				    });
            	}else{
            		layer.alert("只能选择一份服务单进行删除", {icon: 7});
            	}
            });
            
            //服务完成
            $(".over").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择待完成的服务单", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		layer.confirm('该服务单确定已服务完成吗？<br/>已完成请点击“确定”，否则点击“取消”', {icon: 3}, function(index){
	            		var service_number = $("input[name='check']:checked").val();
	            		$.post("<%=basePath%>service/state.json",{"service_number":service_number},function(data){
					    	if(data.error){
	            				layer.msg(data.message);
	            			}else{
					    		initData();
					    		layer.msg("操作成功！");
					    	}
					    });
				    });
            	}else{
            		layer.alert("只能选择一份服务单", {icon: 7});
            	}
            });
            
            //服务备注
            $(".comment").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择需要备注的服务单", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var comment_str =$("input[name='check']:checked").attr("comment");
            		if(comment_str==""){comment_str="工程师姓名：&#xd联系电话：&#xd预计服务时间：";}
           			layer.prompt({title: '服务单备注', formType: 2,value:comment_str}, function(text){
	            		layer.confirm('确认确定要保存吗？', {icon: 3}, function(index){
		            		var service_number = $("input[name='check']:checked").val();
		            		$.post("<%=basePath%>service/comment.json",{"service_number":service_number,"comment":text},function(data){
						    	if(data.error){
		            				layer.msg(data.message);
		            			}else{
						    		initData();
						    		layer.msg("修改服务单备注成功");
						    	}
						    });
					    });
				    });
            	}else{
            		layer.alert("只能选择一份服务单进行备注", {icon: 7});
            	}
            });
            
            
            //查询
            $(".search").click(function(){
	        	initData();
	        });
	        
	        //会员详细信息
            $("#detail").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择待查看会员", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var id = $("input[name='check']:checked").attr("user_id");
            		layer.open({
					    type: 2,
					    area: ['780px', '405px'],
					    fix: false, //不固定
					    maxmin: true,
					    content: "/jsp/user/detail.jsp?action=edit&id="+id,
					    title:'会员详细资料'
					});
            	}else{
            		layer.alert("只能选择一条数据", {icon: 7});
            	}
            });
	        
        });
        
        function initData(){
        	//显示加载层
        	layer.load();
        	var keyword = $(".dfinput").val();
        	var state= $("#state").val();
            $.post("<%=basePath%>service/queryCount.json",{"keyword":keyword,"state":state},function(data){
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
        	var state= $("#state").val();
        	$.post("<%=basePath%>service/queryList.json",{"keyword":keyword,"state":state,"pageIndex":index,"pageSize":pageSize},function(data){
	            var stateList = getStringmap('service','state');
	            	for(var i =0;i<data.length;i++){
            		for(key in data[i]){
            		
            		 try {
      					  if (typeof(eval(eval(key+"List"))) == "object") {
           				
           					 data[i][key+"Trans"] = eval(key+"List")[data[i][key]];
        				}
    				} catch(e) {
    					continue;
    				}

            		}
            		}
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