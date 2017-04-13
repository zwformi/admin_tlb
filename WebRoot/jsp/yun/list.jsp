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
<title>模块列表</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/pagination.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/dblclick.js"></script> 
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/layer/layer.js"></script>
	<script src="<%=basePath%>js/jquery.pagination.js"></script>
	<script src="<%=basePath%>js/jquery.tmpl.js"></script>
	<script src="<%=basePath%>js/common.js"></script>
	<link rel="stylesheet" href="iconfont.css"/>
<style type="text/css">
    tbody .no {width: 80px; text-align: center; text-indent: 0px;}
    .tablelist td{padding:20px 4px;left:0; text-indent:0;line-height:20px;text-align:center;}
     .iconfont{
		color:#fff;
    	width:80px;
    	line-height:80px;
    	height:80px;
    	margin:10px;
    	font-size:50px;
    	text-align:center;
    	border-radius:10px;
    	background:red;
    }
</style>
</head>

<body>
<form id="mForm" style="height:100%">
	<div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="<%=basePath%>jsp/firstpage.jsp">首页</a></li>
            <li><a href="javascript:;">图灵云模块</a></li>
            <li><a href="javascript:;">模块管理</a></li>
        </ul>
    </div>
    <div class="formbody">
        <div class="formtitle"><span>模块列表</span></div>
        <div class="rightinfo">
            <div class="tools">
                <ul class="toolbar">
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_module_add")){%>
                	<li id="add"><span><img src="<%=basePath%>images/t01.png" /></span>新增</li>
                	<%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_module_edit")){%>
                    <li class="edit"><span><img src="<%=basePath%>images/t02.png" /></span>编辑</li>
                    <%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_module_delete")){%>
                    <li class="delete"><span><img src="<%=basePath%>images/t03.png" /></span>下架</li>
                    <%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_module_search")){%>           

				<ul class="toolbar">

                   <li class='select'>
                   	<select id="type_id" name="type_id" group="type_id" style="width:125px;">
                   		
                   	</select>
                   	<input type="text" class="dfinput" name="name"  id="name" placeholder = "模块名称" title="模块名称" style="width:200px;"/> 
                   </li>
                   <li class="search"><span><img src="<%=basePath%>images/t06.png" /></span>查询</li>

              	</ul>
          		
          		
            <%}%>
                </ul>
            </div>
			<div class="tablediv">
            <table class="tablelist">
                <thead>
                    <tr>
                    	<th></th>
                    	<th>排序ID</th>
                    	<th>所属类别</th>
                        <th>模块名称</th>
                        <th>图标</th>
                        <th>原价</th>
                        <th>现价</th>
                        <th>默认分配路径</th>
                        <th>默认</th>
                        <th>状态</th>
                    </tr>
                </thead>
    			<tbody id="content"></tbody>
    			<script id="template" type="text/x-jquery-tmpl">
    			<tr>
					<td width="35"><input name="check" type="checkbox" value="${module_id}" /></td>
					<td width="50">${sort_code}</td>
					<td width="80">${type_name}</td>
    				<td><div style="width:250px;display:block;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="${name}">${name}</div></td>
    				<td width="80" height="110px" align="center"><span class="iconfont ${icon}" style="background:${bgcolor}"></span></td>
					<td width="80"><font style="color:#888888;text-decoration:line-through">￥${price_old}</td>
					<td width="80">￥${price_new}</td>
					<td width="200">${url}</td>
					<td width="60">{{if default_flag == 0}}不开通{{/if}}{{if default_flag == 1}}开通{{/if}}</td>
					<td width="60">{{if delete_flag == 0}}<font style="color:#4aba2c">已上架</font>{{/if}}{{if delete_flag != 0}}<font style="color:red">已下架</font>{{/if}}</td>
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
		<input type="hidden" id="pageIndex" name="pageIndex" value="0">
		<input type="hidden" id="pageSize" name="pageSize" value="0">
    </div>
     </form>  
	</body>
    <script type="text/javascript">
        var pageIndex = 0;     //页面索引初始值
    	var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    	
		
    	
    	$(document).ready(function () {

            initData();
            loadtype();
            
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
            
       
 //           删除
            $(".delete").click(function(){
            	if($("input[name='check']:checked").length != 0){
            		layer.confirm('确认下架 ？', {icon: 3}, function(index){
	            		var id="";
		            	$("input[name='check']:checked").each(function(){
							id=id+this.value+",";
				        });
            			if(id!=""){
				    		id=id.substring(0,id.length-1);
				    	}
						$.post("<%=basePath%>yun_module/delete.json",{"module_id":id},function(data){
				    		layer.close(index);
				    		initData();
				        });
					});
            	}else{
            		layer.alert("请选择待下架模块", {icon: 7});
            	}
            });
            
            //查询
            $(".search").click(function(){
	        	initData();
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

        function initData(){   
        	//显示加载层
        	layer.load();
        	var frm=$('#mForm').serialize();
            $.post("<%=basePath%>yun_module/queryCount.json",frm,function(data){
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
        	//追加pageIndex以及pageSize
        	$("#pageIndex").val(index);
        	$("#pageSize").val(pageSize);
        	var frm=$('#mForm').serialize();
        	$.post("<%=basePath%>yun_module/queryList.json",frm,function(data){
        		if(data.error==true){
        			layer.msg("查询出错！");
        		}else{
	            $("#content").html($("#template").tmpl(data.list));
	            //加载表格样式,公用
	            loadTableStyle();
	            }
	            //关闭加载层
	        	layer.closeAll('loading');
	        	//双击事件加载
	        	dblClick('<%=basePath%>');
	        	
        	});
        }
        });
     
    </script>

</html>