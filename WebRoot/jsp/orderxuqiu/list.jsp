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
<title>需求列表</title>
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
            <li><a href="javascript:;">需求管理</a></li>
        </ul>
    </div>

    <div class="formbody">
        <div class="formtitle" style="margin:0px"><span>需求列表</span></div>
        <div class="rightinfo">
        	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_demand_search")){%>
            <div class="tools">
				<ul class="toolbar">
                    <li class='select'>
                    	<select id="order_state" group="order_info_xuqiu" style="width:125px;">
                    		<option value="" selected>请选择需求状态</option>
                    	</select>
                    	<select id="order_source" group="order_info_xuqiu"  style="width:125px;">
                    		<option value="" selected>请选择需求来源</option>
                    	</select>      
                    	<input type="text" class="dfinput"   id="order_number" placeholder = "需求单号" title="需求单号" style="width:200px;"/>           
                    	<input type="text" class="dfinput"   id="ywyxm" placeholder = "联系人姓名" title="联系人姓名" style="width:125px;"/>
                    	<input type="text" class="dfinput" placeholder="公司名称" id="gsmc" title="公司名称" style="width:200px;" />
                    </li>
                    <li class="search"><span><img src="<%=basePath%>images/t06.png" /></span>查询</li>
                </ul>
            </div>
            <%}%>
            
            <div class="tools">
                <ul class="toolbar">
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_demand_query")){%>
                	<li class="query"><span><img src="<%=basePath%>images/t05.png" /></span>查看需求</li>
                	<%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_demand_detail")){%>
                	<li id="detail"><span><img src="<%=basePath%>images/t10.png" /></span>客户资料</li>
                	<%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_demand_edit")){%>
                    <li class="edit_xuqiu"><span><img src="<%=basePath%>images/t07.png" /></span>需求整理</li>
                    <%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_demand_bj")){%>
                    <li class="bj_xuqiu"><span><img src="<%=basePath%>images/t04.png" /></span>报价</li>
                    <%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_demand_createorder")){%>
                    <li class="createorder"><span><img src="<%=basePath%>images/t08.png" /></span>生成合同</li>
                    <%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_demand_cancel")){%>
                    <li class="cancel_xuqiu"><span><img src="<%=basePath%>images/t25.png" /></span>关闭</li>
                    <%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_demand_delete")){%>
                    <li class="delete"><span><img src="<%=basePath%>images/t03.png" /></span>删除</li>
                    <%}%>
                </ul>
            </div>

            <table class="tablelist">
                <thead>
                    <tr>
                    	<th style="width:3%"></th>
                        <th style="width:19%">需求单号</th>
                        <th style="width:14%">需求标题</th>
                        <th style="width:8%">需求来源</th>
                        <th style="width:18%">公司名称</th>
                        <th style="width:8%">状态</th>
                        <th style="width:8%">报价金额</th>
                        <th style="width:14%">下单时间</th>
                        <th style="width:10%">联系人</th>
                    </tr>
                </thead>
    			<tbody id="content"></tbody>
    			
    			<script id="template" type="text/x-jquery-tmpl">
    			<tr>
					<td width="35"><input name="check" type="checkbox" value="${id}" order_state="${order_state}" offer_file="${offer_file}" order_number="${order_number}" order_source="${order_source}" user_id="${user_id}"/></td>
    				<td>${order_number}</td>
					<td>{{if title != null}}${title}{{/if}}
						{{if title == null}}--{{/if}}
					</td>
					<td><font style="color:
										{{if order_source == 4}}blue
										{{else order_source == 0}}#56920e
										{{else}}red
										{{/if}}">
						${order_sourceTrans}
					</td>
					<td>{{if gsmc == null||gsmc == ""}}未绑定公司
					{{else}}
					${gsmc}
					{{/if}}
					</td>
					<td><font style="color:{{if order_state == 5}}#d7c77e
										{{else order_state == 6}}#999999
										{{else order_state == 4}}red
										{{else order_state == 8}}#56920e
										{{else}}#cdec91
										{{/if}}">${order_stateTrans}</font>
						
		
					</td>
					<td>{{if order_state == 4}}--
						{{else order_state == 6}}--
						{{else}}
						￥${payment_money}{{/if}}
					</td>
					<td>
						${fmt_order_time}
					</td>
					<td>
						${ywyxm}
					</td>
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
	<script src="<%=basePath %>js/stringmap.js"></script>
    <script type="text/javascript">
        var pageIndex = 0;     //页面索引初始值
    	var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    	var id=10;
    	 function placeholder(nodes,pcolor) {
      if(nodes.length && !("placeholder" in document_createElement_x("input"))){
          for(i=0;i<nodes.length;i++){
              var self = nodes[i],
                  placeholder = self.getAttribute('placeholder') || '';     
              self.onfocus = function(){
                  if(self.value == placeholder){
                     self.value = '';
                     self.style.color = "";
                  }               
              }
              self.onblur = function(){
                  if(self.value == ''){
                      self.value = placeholder;
                      self.style.color = pcolor;
                  }              
              }                                       
              self.value = placeholder;  
              self.style.color = pcolor;              
          }
      }
    }
    	
    	$(document).ready(function () {
            initData();


			// 查看需求详情
            $(".query").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择需要查看的需求", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var id = $("input[name='check']:checked").val();
            		layer.open({
					    type: 2,
					    area: ['800px', '500px'],
					    fix: false, //不固定
					    maxmin: true,
					    content: "/jsp/orderxuqiu/details.jsp?id="+id,
					    title:'需求详情'
					});
            	}else{
            		layer.alert("只能选择一份需求进行查看", {icon: 7});
            	}
            });
            //需求整理
            $(".edit_xuqiu").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选选择需要整理的需求", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var order_state = $("input[name='check']:checked").attr("order_state");
            		if(order_state==7||order_state==8){
            			layer.alert("客户已接受报价或已生成合同的需求无法重新整理！", {icon: 7});
            		}else{
	            		var id = $("input[name='check']:checked").val();
	            		var order_source = $("input[name='check']:checked").attr("order_source");
	            		var order_number = $("input[name='check']:checked").attr("order_number");
	            		var offer_file = $("input[name='check']:checked").attr("offer_file");
	            		console.log("<%=basePath%>/jsp/orderxuqiu/list_orderxuqiu_product.jsp?id="+id+"&order_source="+order_source+"&order_number="+order_number+"&offer_file="+offer_file+"");
	            		layer.open({
						    type: 2,
						    area: ['800px', '600px'],
						    fix: false, //不固定
						    maxmin: true,
						    content: "<%=basePath%>/jsp/orderxuqiu/list_orderxuqiu_product.jsp?id="+id+"&order_source="+order_source+"&order_number="+order_number+"&offer_file="+offer_file+"",
						    title:'需求整理',
						   cancel: function(index){ 
						   		initData();
   								 layer.close(index);
								}    
						});
						
					}
            	}else{
            		layer.alert("只能选择一个需求进行整理", {icon: 7});
            	}
            });
            
           	// 需求报价
            $(".bj_xuqiu").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选选择需要报价的需求", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var order_state = $("input[name='check']:checked").attr("order_state");
            		if(order_state==7||order_state==8){
            			layer.alert("客户已接受报价或已生成合同的需求无法报价！", {icon: 7});
            		}else{
	            		var id = $("input[name='check']:checked").val();
	            		layer.open({
						    type: 2,
						    area: ['800px', '500px'],
						    fix: false, //不固定
						    maxmin: true,
						    content: "/jsp/orderxuqiu/list_orderxuqiu_product_bj.jsp?id="+id,
						    title:'需求报价'
						});
					}
            	}else{
            		layer.alert("只能选择一个需求进行报价", {icon: 7});
            	}
            });
            	// 需求关闭
            $(".cancel_xuqiu").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择需要关闭的需求", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var order_state = $("input[name='check']:checked").attr("order_state");
            		if(order_state==8){
            			layer.alert("已生成合同的需求无法关闭！", {icon: 7});
            		}else{
	            		layer.confirm('确认要关闭本需求吗？<br/>关闭请点击“确定”，否则点击“取消”', {icon: 3}, function(index){
		            		var id = $("input[name='check']:checked").val();
		            		$.post("<%=basePath%>orderxuqiu/cancel.json",{"id":id},function(data){
		            			if(data.error){
		            				layer.msg(data.message);
		            			}else{
						    		initData();
		            				layer.msg("关闭需求成功");
						    	}
						    });
					    });
				    }
				    
            	}else{
            		layer.alert("只能选择一份需求进行关闭", {icon: 7});
            	}
            });
            
            //创建订单合同
            $(".createorder").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选选择需要创建订单合同的需求", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var id = $("input[name='check']:checked").val();
            		var order_state = $("input[name='check']:checked").attr("order_state");
            		var order_source = $("input[name='check']:checked").attr("order_source");
            		if(order_state==5||order_state==7){
            			location.href="/jsp/order/add.jsp?id="+id+"&order_source="+order_source;
            		}else{
            			layer.alert("只有报价状态为[已报价]或[客户已接受报价]的需求才能生成订单合同！", {icon: 7});
            		}
            	}else{
            		layer.alert("只能选择一个需求进行创建订单合同", {icon: 7});
            	}
            });
            
            //删除
            $(".delete").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择需要删除的需求", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var order_state = $("input[name='check']:checked").attr("order_state");
            		if(order_state==8){
            			layer.alert("已生成合同的需求无法删除！", {icon: 7});
            		}else{
	            		layer.confirm('确认要删除本需求吗？<br/>删除请点击“确定”，否则点击“取消”', {icon: 3}, function(index){
		            		var id = $("input[name='check']:checked").val();
		            		$.post("<%=basePath%>orderxuqiu/delete.json",{"id":id},function(data){
		            			if(data.error){
		            				layer.msg(data.message);
		            			}else{
						    		initData();
						    		layer.msg("删除需求成功");
						    	}
						    });
					    });
				    }
            	}else{
            		layer.alert("只能选择一份需求进行删除", {icon: 7});
            	}
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
            
            //查询
            $(".search").click(function(){
	        	initData();
	        });
	        
        });
        
        function initData(){
        	//显示加载层
        	layer.load();
        	var keyword = $("#order_number").val();
        	var order_state= $("#order_state").val();
        	var order_source= $("#order_source").val();
            $.post("<%=basePath%>orderxuqiu/queryCount.json",{"order_number":keyword,"order_state":order_state,"order_source":order_source},function(data){
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
        
         loadSelect(0);

        
        function pageSelectCallback(index, jq) {
        	$("#index").html(index+1);
        	var keyword = $("#order_number").val();
        	var order_state= $("#order_state").val();
        	var order_source= $("#order_source").val();
        	var ywyxm = $("#ywyxm").val();
        	var gsmc = $("#gsmc").val();
        	$.post("<%=basePath%>orderxuqiu/queryList.json",{"order_number":keyword,"order_state":order_state,"order_source":order_source,"ywyxm":ywyxm,"gsmc":gsmc,"pageIndex":index,"pageSize":pageSize},function(data){
	          	console.log(data);
	          	
	          	//需要转换的字段
	          	var order_stateList = getStringmap('order_info_xuqiu','order_state');
	          	var order_sourceList = getStringmap('order_info_xuqiu','order_source');
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