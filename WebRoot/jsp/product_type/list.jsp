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
<title>分类列表</title>
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
            <li><a href="javascript:;">分类管理</a></li>
        </ul>
    </div>
    
    <div class="formbody">
        <div class="formtitle"><span>分类列表</span></div>
        <div class="rightinfo">
            <div class="tools">
                <ul class="toolbar">
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_type_add")){%>
                	<li id="add"><span><img src="<%=basePath%>images/t01.png" /></span>新增</li>
                	<%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_type_edit")){%>
                    <li class="edit"><span><img src="<%=basePath%>images/t02.png" /></span>编辑</li>
                    <%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_type_delete")){%>
                    <li class="delete"><span><img src="<%=basePath%>images/t03.png" /></span>删除</li>
                    <%}%>
                    <%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_type_delete")){%>
                   <li class="zcy"><span><img src="<%=basePath%>images/t06.png" /></span>政采云同步</li>
                	<%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_type_delete")){%>
                   <li class="updatetype"><span><img src="<%=basePath%>images/t14.png" /></span>更新类目</li>
                	<%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_type_delete")){%>
                   <li class="updateTemplate"><span><img src="<%=basePath%>images/t14.png" /></span>批量更新模板</li>
                	<%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_type_search")){%>
                    <li class='select' style="margin-left: 20px;"><input type="text" class="dfinput" /></li>
                    <li class="search"><span><img src="<%=basePath%>images/t06.png" /></span>查询</li>
                    <%}%>

                </ul>
            </div>
			<div class="tablediv">
            <table class="tablelist">
                <thead>
				</div>
                    <tr>
                    	<th></th>
                        <th>排序ID</th>                       
                        <th>所属类别</th>
                        <th>所属二级类别</th>
                        <th>分类名称</th>
                    </tr>
                </thead>
    			<tbody id="content"></tbody>
    			<script id="template" type="text/x-jquery-tmpl">
    			<tr>
					<td width="35"><input name="check" type="checkbox" value="${id}" /></td>
    				<td width="50">${sortId}</td>					
    				<td>${parentName}</td>
					<td>${subParentName}</td>
                    <td>${name}</td>
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
	<jsp:include page="/jsp/progress/progress.jsp"></jsp:include>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/layer/layer.js"></script>
	<script src="<%=basePath%>js/jquery.pagination.js"></script>
	<script src="<%=basePath%>js/jquery.tmpl.js"></script>
	<script src="<%=basePath%>js/common.js"></script>
    <script type="text/javascript">
        var pageIndex = 0;     //页面索引初始值
    	var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    	var type = "";
    	$(document).ready(function () {
    	
    	layer.config({
		    extend: 'extend/layer.ext.js'
		});
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
            
                        //zcy
            $(".zcy").click(function(){
            	var _index = layer.load();
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择待同步数据", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var id = $("input[name='check']:checked").val();
            		layer.load();
            		 $.post("<%=basePath%>zcy/updateAttrTemplateByName/"+id+".json",function(data){
         				//layer.alert(JSON.stringify(data));
         				layer.closeAll('loading');
         				layer.alert(data.result);
	
            	
	        });
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
						$.post("<%=basePath%>product_type/delete.json",{"id":id},function(data){
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
            	pageIndex=0;
	        	initData();
	        	         				
	        });
	        
        });
        
        	$('.updatetype').click(function(){
        	
				//prompt层
				layer.prompt({'title': '请输入政采云类目id', 'formType': 3}, function(_id, index){
				  var reg = new RegExp("^[0-9]*[1-9][0-9]*$");
				  if(reg.test(_id)){
				  layer.close(index);
				  layer.confirm('同步类目操作可能会更改或删除部分旧有数据，是否继续?', function(index){
				  		layer.close(index);
				  		var _i = layer.load();
				  		$.post('/product_type/updateTypeFromZcy/'+_id+'.json',function(data){
				  			layer.close(_i);
				  			if(data.resultCode==1){
				  				layer.msg("同步类目-"+_id+"成功!");
				  				initData();
				  			}else{
				  			
				  				layer.alert("同步类目-"+_id+"失败<br>"+data.resultMsg);
				  			
				  			}
				  		
				  		
				  		})
				  
				  }, function(index){layer.close(index);})
				  
				  
				  }else{
				  	layer.alert('请填写数字！');
				  }
				  
				});
        	
        	})
        	$('.updateTemplate').click(function(){
        	
				//prompt层
				layer.prompt({'title': '请输入政采云类目id', 'formType': 3}, function(_id, index){
				  var reg = new RegExp("^[0-9]*[1-9][0-9]*$");
				  if(reg.test(_id)){
				  layer.close(index);
				  layer.confirm('更新模板可能会更改或删除部分旧有模板数据，是否继续?', function(index){
				  		layer.close(index);
				  		var _i = layer.load();
				  		var flag = false;
				  		$.post('/product_type/getCategoryIds/'+_id+'.json',function(data){
				  			
				  			if(data.resultCode==1){
									var _list = data.list;
									updateTemplate(0,_list)
									function updateTemplate(index,list){
										if(!flag){
											flag = true;
											$("#progress_view").css({'width':0});
											$("#progress_main").show('fast');
											$("#progress_percent").hide();
											$("#progress_index").text(index+1);
									        $("#progress_total").text(list.length);										
										}
										if(index == list.length){
											layer.close(_i);
											setTimeout(function(){
											$("#progress_main").hide('fast');
											layer.alert('模板批量更新成功！');
											},'1000')
											
															
										}else{
											    $.post("<%=basePath%>zcy/updateAttrTemplateByName/"+list[index].id+".json",function(data){
															 if((data.result).indexOf("成功")!=-1){
															 
															 if(Number($("#progress_view").css('width').replace('px',''))<60)
									         					$("#progress_percent").hide();
									         					else
									         					$("#progress_percent").show();
									         					
									         				updateTemplate(++index,list)
									         				var _width= ((index/list.length)*100).toFixed(2)+'%';
									         				
									         				$("#progress_view").css({'width':_width});
									         				$("#progress_percent").text(_width);
									         				$("#progress_index").text(index);
									         				$("#progress_total").text(list.length);
									         				
															/*  layer.msg('成功操作'+index+'/'+list.length+'项') */
															}else{
																layer.close(_i);
																layer.alert('id：'+list[index].id+'模板同步失败，同步终止<br>'+data.result);
																$("#progress_main").hide('fast');
																return;
															} 
										
									            	
										        });
										
										}
									
									}
				  			}else{
				  				layer.close(_i)
				  				layer.alert("同步大类"+_id+"失败<br>"+data.resultMsg);
				  			
				  			}
				  		
				  		
				  		})
				  
				  }, function(index){layer.close(index);})
				  
				  
				  }else{
				  	layer.alert('请填写数字！');
				  }
				  
				});
        	
        	})
        	
        
        var loadindex ;
        function initData(){
        	//显示加载层
        	loadindex = layer.load();
        	var keyword = $(".dfinput").val();
            $.post("<%=basePath%>product_type/queryCount.json",{"keyword":keyword},function(data){
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
        	$.post("<%=basePath%>product_type/queryList.json",{"keyword":keyword,"pageIndex":index,"pageSize":pageSize},function(data){
	            $("#content").html($("#template").tmpl(data));
	            //加载表格样式,公用
	            loadTableStyle();
	            //关闭加载层
	        	layer.close(loadindex);
	        	dblClick('<%=basePath%>');
        	});
        }
    </script>
</body>
</html>