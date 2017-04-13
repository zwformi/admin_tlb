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
    .hid{
         display:none;
    }
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
            <form id="tool_form">
                <ul class="toolbar">
                	

                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_search")){%>
                    <li class='select' style="margin-left: 20px;clear:both;"><lable>名称:</lable><input type="text" class="dfinput" name="keyword"/></li>
                    <li class='select' style="margin-left: 20px;"><lable>排序ID:</lable><input style="width:50px;" type="text" class="dfinput" name="sortid1" onkeyup="value=value.replace(/[^\d]/g,'')"/>~<input style="width:50px;" type="text" class="dfinput" name="sortid2" onkeyup="value=value.replace(/[^\d]/g,'')"/></li>
                    <li class='select' style="margin-left: 20px;"><lable>商品ID:</lable><input style="width:50px;" type="text" class="dfinput" name="product_id1" onkeyup="value=value.replace(/[^\d]/g,'')"/>~<input style="width:50px;" type="text" class="dfinput" name="product_id2" onkeyup="value=value.replace(/[^\d]/g,'')"/></li>
                    <li class='select' style="margin-left: 20px;"><lable>政采云ID:</lable><input style="width:50px;" type="text" class="dfinput" name="zcy_id1" onkeyup="value=value.replace(/[^\d]/g,'')"/>~<input style="width:50px;" type="text" class="dfinput" name="zcy_id2" onkeyup="value=value.replace(/[^\d]/g,'')"/></li>
                    <li class='select' style="margin-left: 20px;">
                    <select name="is_zcy">
                    <option value=''>请选择政采云状态</option>
                    <option value='0'>未同步</option>
                    <option value='1'>已修改，未同步</option>
                    <option value='2'>已同步</option>
                    <option value='3'>已申请上架</option>
                    <option value='4'>已上架</option>
                    <option value='5'>已下架</option>
                    <option value='6'>已冻结</option>
                    <option value='7'>审核不通过</option>
                    <option value='8'>解冻审核中</option>
                    </select>
                    </li>
                    <li class="search"><input id="type" name="type" type="hidden" value = ""><span><img src="<%=basePath%>images/t06.png" /></span>查询</li>
                    <%}%>
                    
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
                	 <%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_add")){%>
                	
                	<li id="zcymanager" style="width: 130px; text-align: center;margin:0 0">
                	<span><img src="<%=basePath%>images/img.png" /></span>
                	    政采云操作
                	    <i><img style="vertical-align: middle;" src="<%=basePath%>images/t45.png"></i> 
                               <ul style="position: absolute;width: 130px;margin-top: 1px;" class="hid"> 					  
						       <%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_editimage")){%>
			                	<li style="float: left; text-align: center;position: relative;width: 100%;margin:0 0;border-radius: 0;border:solid 1px #d3dbde;border-top:none;" id="asyzcy"><span><img src="<%=basePath%>images/img.png" /></span>政采云同步</li>
			                	<%}%>
						        <%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_editimage")){%>
			                	<li style="float: left; text-align: center;position: relative;width: 100%;margin:0 0;border-radius: 0;border:solid 1px #d3dbde;border-top:none;" id="asyputon"><span><img src="<%=basePath%>images/img.png" /></span>商品上架</li>
			                	<%}%>
			                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_editimage")){%>
			                	<li style="float: left; text-align: center;position: relative;width: 100%;margin:0 0;border-radius: 0;border:solid 1px #d3dbde;border-top:none;" id="asypulloff"><span><img src="<%=basePath%>images/img.png" /></span>商品下架</li>
			                	<%}%>
			                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_editimage")){%>
			                	<li style="float: left; text-align: center;position: relative;width: 100%;margin:0 0;border-radius: 0;border:solid 1px #d3dbde;border-top:none;" id="asystatus"><span><img src="<%=basePath%>images/img.png" /></span>商品状态同步</li>
			                	<%}%>
						        </ul>
                	</li>              	
                	<%}%>
                </ul>
                </form>
            </div>
			
			<div class="tablediv">
            <table class="tablelist" id="product_table">
                <thead>
                    <tr>
                    	<th><input type="checkbox" id="checkall"></input></th>
                    	<th>序号</th>
                    	<th>商品ID</th>
                    	<th>政采云ID</th>
                        <th>商品名称</th>
                        <th>商品图片</th>
                        <th>平台价</th>
                        <th>政采云价</th>
                        <th>总数量</th>
                        <th>库存</th>
                        <th>政采云状态</th>
                        <th>图灵买状态</th>
                        <th>排序ID</th>
                    </tr>
                </thead>
    			<tbody id="content"></tbody>
    			<script id="template" type="text/x-jquery-tmpl">
    			<tr>
					<td width="5%"><input name="check" type="checkbox" value="${id}" status ="${is_zcy}"/></td>
					<td width="5%">${$index}</td>
                    <td width="5%">${id}</td>
					<td width="5%"><a href="http://www.zcy.gov.cn/items/${zcy_id}" target="_blank">${zcy_id}</a> </td>
    				<td width="28%"><div style="width:250px;display:block;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="${name}">${name}</div></td>
    				<td width="8%" height="110px"><img src="<%=basePath%>${img_url}" height="100px" style="max-width:100px;" onerror="this.src='<%=basePath%>images/no_pic.png'"/></td>
					<td width="5%">${platform_price_area}</td>
					<td width="5%">${zcy_price_area}</td>
					<td width="6%">${count}</td>
					<td width="5%">${overplus}</td>
					<td width="12%">{{if is_zcy == 1}}<font style="color:green;">已修改,未同步</font>{{else is_zcy == 2}}<font style="color:green;">已同步</font>{{else is_zcy == 3}}<font style="color:orange;">已申请上架</font>{{else  is_zcy == 4}}<font style="color:green;">已上架</font>{{else  is_zcy == 5}}<font style="color:red;">已下架</font>{{else  is_zcy == 6}}<font style="color:red;">已冻结</font>{{else  is_zcy == 7}}<font style="color:red;">审核不通过</font>{{else  is_zcy == 8}}<font style="color:orange;">解冻审核中</font>{{else}}<font style="color:red">未同步</font>{{/if}}</td>
					<td width="10%">{{if delete_flag == 0}}<font style="color:#4aba2c">已上架</font>{{/if}}{{if delete_flag != 0}}<font style="color:red">已下架</font>{{/if}}</td>
					<td width="5%">${sortid}</td>
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
    	var pageSize = 100;     //每页显示条数初始化，修改显示条数，修改这里即可
    	
    	
    	
    	$(document).ready(function () {
    	
    		//全选
				$("input[type='checkbox']#checkall").click(function(){
				var status = $(this).prop('checked');	
				$('#content tr td input[name="check"]').each(function(){
						$(this).prop('checked',status);
				})
				
				
				})
    	
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
           		pageIndex = 0;
	        	initData();
	        });
	        //政采云管理下拉选择
	        $("#zcymanager").on("click",function(e){
	          e.stopPropagation();
	          if($(this).find("ul").hasClass("hid"))
	          {
	             $(this).find("ul").removeClass("hid");
	             $(this).find("i").find("img").attr("src","<%=basePath%>images/t46.png");
	          }
	          else
	          {
	             $(this).find("ul").addClass("hid");
	            $(this).find("i").find("img").attr("src","<%=basePath%>images/t45.png");
	          }	          	        
	        });
	        
	       $("#zcymanager").mouseenter(function(){
	     	}).mouseleave(function(){
	     	    var ul_list=$(this).find("ul");
	     	    var ul_i=$(this).find("i");
	 			$(document).on('click',function(){
	 			    if(!$(ul_list).hasClass("hid"))
	               {
	     			$(ul_list).addClass("hid");
	     			$(ul_i).find("img").attr("src","<%=basePath%>images/t45.png");
	     			}
	     		})    	
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
						$("#asystatus").click();
						layer.alert(result);
						initData();
	        		
	        	});
				}else{
	        		layer.alert("请选择待同步数据", {icon: 7});
	       		 }
	        });
	        //上架
	        $("#asyputon").click(function(){
	        	if($("input[name='check']:checked").length != 0){
	        		layer.load();
	        		var id="";
	        		$("input[name='check']:checked").each(function(){
						id=id+this.value+",";
				    });
				    if(id!=""){
				   		id=id.substring(0,id.length-1);
				   	}
	        		$.post("<%=basePath%>zcy/updateState.json",{"id":id,"status":"1"},function(data){
	        			var result = data.result;
						layer.alert(result);
						initData();
	        		});
	        	}else{
	        		layer.alert("请选择需要上架的商品", {icon:7});
	        	}
	        });
	        //下架
	        $("#asypulloff").click(function(){
	        	if($("input[name='check']:checked").length != 0){
	        		layer.load();
	        		var id="";
	        		$("input[name='check']:checked").each(function(){
						id=id+this.value+",";
				    });
				    if(id!=""){
				   		id=id.substring(0,id.length-1);
				   	}
	        		$.post("<%=basePath%>zcy/updateState.json",{"id":id,"status":"-1"},function(data){
	        			var result = data.result;
						layer.alert(result);
						initData();
	        		});
	        	}else{
	        		layer.alert("请选择需要下架的商品", {icon:7});
	        	}
	        });
	        
	       	//商品状态同步
	        $("#asystatus").click(function(){
	        	var msg = "";
	        	var id_str = "";
	        	layer.load();
	        	if($("input[name='check']:checked").length != 0){

	        		$("input[name='check']:checked").each(function(){
	        		var id="";
	        		var status = 0;
	        						  
						id=id+this.value+",";
						status = $(this).attr("status");

				    if(id!=""&&status>1){
				   		id_str += id;
	        		}else{
				   		msg += id ;
				   	}
				   	 });
				   	if(id_str!=""){
				   	id_str = id_str.substring(0,id_str.length-1);
				   	$.post("<%=basePath%>zcy/getProductStatus/"+id_str+".json",function(data){
	        			var result = data.resultMsg;
	        			if(msg!=""){
	        				msg = msg.substring(0,msg.length-1);
	        				msg = "商品" + msg + "未经上传！<br/>";
	        			}
	        			msg += result;
						layer.alert(msg);
						initData();
	        		});
	        		}else{
	        			layer.closeAll('loading')
	        			layer.alert("请选择已上传过的商品！",{icon:7});
	        		}
	        	}else{
	        		layer.alert("请选择需要获取状态的商品", {icon:7});
	        	}
	        });
	        
        });
       
        function initData(){
        	//显示加载层
        	layer.load();
        	//调整筛选条件
        	var sortid1 = $("input[name='sortid1']")
        	var sortid2 = $("input[name='sortid2']")
        	var product_id1 = $("input[name='product_id1']")
        	var product_id2 = $("input[name='product_id2']")
        	var zcy_id1 = $("input[name='zcy_id1']")
        	var zcy_id2 = $("input[name='zcy_id2']")
        	if(!sortid1.val()&&!!sortid2.val() )
        		sortid1.val(sortid2.val());
        	if(!!sortid1.val()&&!sortid2.val() )
        		sortid2.val(sortid1.val());
        	if(!product_id1.val()&&!!product_id2.val() )
        		product_id1.val(product_id2.val());
        	if(!!product_id1.val()&&!product_id2.val() )
        		product_id2.val(product_id1.val());
        	if(!zcy_id1.val()&&!!zcy_id2.val() )
        		zcy_id1.val(zcy_id2.val());
        	if(!!zcy_id1.val()&&!zcy_id2.val() )
        		zcy_id2.val(zcy_id1.val());	
        	//调整数字大小	
        		
        	var postdata = $('#tool_form').serialize();
            $.post("<%=basePath%>product/queryCount.json",postdata,function(data){
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
        	var postdata = $('#tool_form').serialize();
        	postdata+="&pageIndex="+index+"&pageSize="+pageSize;
        	$.post("<%=basePath%>product/queryList.json",postdata,function(data){
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