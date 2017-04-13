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
<title>订单合同列表</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/pagination.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<script src="<%=basePath%>js/jquery.pagination.js"></script>
<script src="<%=basePath%>js/jquery.tmpl.js"></script>
<script src="<%=basePath%>js/stringmap.js"></script>
<script src="<%=basePath%>js/common.js"></script>

<style type="text/css">
    tbody .no {width: 80px; text-align: center; text-indent: 0px;}
  	.tablelist td{padding:20px 4px;left:0; text-indent:0;line-height:20px;text-align:center;}
</style>
</head>



<body>
 
	<div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="<%=basePath%>jsp/firstpage.jsp">首页</a></li>
            <li><a href="javascript:;">订单合同管理</a></li>
        </ul>
    </div>

    
    <div  class="formbody">
        <div class="formtitle" style="margin:17px"><span>订单合同列表</span></div>
        <div class="rightinfo">
        	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_order_search")){%>
             <div class="tools">
				<ul class="toolbar">
                   <li class='select'>
                   	<select id="order_state" group="order_info" style="width:125px;">
                   		<option value="" selected>请选择交付状态</option>
                   	</select>
                   	<select id="order_source" group="order_info" style="width:125px;">
                   		<option value="" selected>请选择合同来源</option>
                   	</select>
                   	<input type="text" class="dfinput"   id="order_number" placeholder = "合同编号" title="合同编号" style="width:200px;"/>           
                    	<input type="text" class="dfinput"   id="ywyxm" placeholder = "销售人姓名" title="销售人姓名" style="width:125px;"/>
                    	<input type="text" class="dfinput" placeholder="公司名称" title="公司名称" id="gsmc" style="width:200px;" />
                 
                   </li>
                   <li class="search"><span><img src="<%=basePath%>images/t06.png" /></span>查询</li>
              	</ul>
           </div>
           <%}%>
            <div class="tools count">
                <ul class="toolbar">
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_order_query")){%>
                	<li class="query"><span><img src="<%=basePath%>images/t05.png" /></span>查看合同</li>
                	<%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_order_detail")){%>
                	<li id="detail"><span><img src="<%=basePath%>images/t10.png" /></span>客户资料</li>
                	<%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_order_delete")){%>
                    <li class="delete"><span><img src="<%=basePath%>images/t03.png" /></span>删除</li>
                    <%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_order_site")){%>
                    <li class="site"><span><img src="<%=basePath%>images/t21.png" /></span>配货</li>
                    <%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_order_send")){%>
                    <li class="send"><span><img src="<%=basePath%>images/t20.png" /></span>发货</li>
                    <%}%>
                    <%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_order_express")){%>
                    <li class="express"><span><img src="<%=basePath%>images/t47.png" /></span>更新物流</li>
                    <%}%>
                    <%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_order_ss")){%>
                    <li class="ss"><span><img src="<%=basePath%>images/t31.png" /></span>实施</li>
                    <%}%>
                    <%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_order_ckpj")){%>
                	<li class="pj"><span><img src="<%=basePath%>images/t32.png" /></span>查看评价</li>
                	<%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_order_dkhcz")){%>
                	<li class="dkhcz"><span><img src="<%=basePath%>images/t33.png" /></span>代客户操作</li>
                	<%} %>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_order_yskzt")){%>
                	<li class="yskzt"><span><img src="<%=basePath%>images/t40.png" /></span>应收款状态</li>
                	<%} %>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_order_fjsc")){%>
                	<li class="fjsc"><span><img src="<%=basePath%>images/t41.png" /></span>附件上传</li>
                	<%} %>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_order_dkhkt")){%>
                	<li class="dkhkt"><span><img src="<%=basePath%>images/t48.png" /></span>云开通</li>
                	<%} %>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_order_export")){%>
               		<li id="export"><span><img src="<%=basePath%>images/t09.png" /></span>导出</li>
                	<%}%>
                </ul>
           </div>
            <table class="tablelist">
                <thead>
                    <tr>
                    	<th style="width:3%"></th>
                        <th style="width:17%">合同编号</th>
                        <th style="width:11%">客户名称</th>
                        <th style="width:11%">最终用户名称</th>
                        <th style="width:10%">交付状态</th>
                        <th style="width:6%">合同来源</th>
                        <th style="width:5%">合同金额</th> 
                        <th style="width:5%">合同类型</th>
                         <th style="width:5%">销售人员</th>
                        <th style="width:20%">相关时间</th>
                        <th style="width:6%">回款状况</th>
                        
                    </tr>
                </thead>
    			<tbody id="content"></tbody>
    			<script id="template" type="text/x-jquery-tmpl">
    			<tr>
					<td width="35"><input name="check" type="checkbox" order_state="${order_state}" order_source="${order_source}"  order_number="${order_number}" shipping_methods="${shipping_methods}" yskzt="${yskzt}" value="${id}" user_id="${user_id}"/></td>
    				<td>${order_number}</td>
					<td style="text-align:left;"><span>{{if gsmc == null||gsmc == ""}}未绑定公司{{else}}${gsmc}{{/if}}</span></td>
					<td style="text-align:left;"><span>{{if gsmc_final == null||gsmc_final == ""}}未设置{{else}}${gsmc_final}{{/if}}</span>
						</td>
					<td>
						 <font style="color:
						{{if order_state ==0}}red
						{{else order_state ==8}}#56920e
						{{else order_state ==9}}#808080
						{{else}}#efbe29
						{{/if}}
					"><font>${order_stateTrans}</font></td>
					<td>
						<font style="color:{{if order_source == 0}}#68bb17
										{{else order_source == 4}}blue
										{{else}}red
										{{/if}}">${order_sourceTrans}</font>	
					</td>
					<td>￥${payment_money}</td>
					<td>{{if order_type ==0}}无{{else}}${order_typeTrans}{{/if}}</td>
					<td>{{if ywyxm == null||ywyxm == ""}}无 {{else}}${ywyxm}{{/if}}</td>
					<td style="text-align:left;">签订时间：${fmt_signing_date}<br/>验收时间：${fmt_reception_date}</td>
					<td>{{if rec_paycondition == 0}}无{{else}}${rec_payconditionTrans}{{/if}}</td>
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
    <script type="text/javascript">

    	var order_state = window.location.search.split('=')[1];
    	var order_source = window.location.search.split('=')[2];
    	layer.config({
		    extend: '<%=basePath%>js/layer/extend/layer.ext.js'
		});
        var pageIndex = 0;     //页面索引初始值
    	var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    	
    	
    	$(document).ready(function () {
            initData();
            
          $(".ship").each(function(i){
          
          	this.innerHtml+=shipList['1'];
          	});
            
          	
        	loadSelect(0);
        	
        	//导出
            $("#export").click(function(){
            var order_number = $("#order_number").val();
        	var order_state= $("#order_state").val();
        	var order_source= $("#order_source").val();
        	var ywyxm = $("#ywyxm").val();
        	var gsmc = $("#gsmc").val();
            $.post("<%=basePath%>order/queryCount.json",{"order_number":order_number,"order_state":order_state,"ywyxm":ywyxm,"gsmc":gsmc,"order_source":order_source},function(data){
         
            	window.open("<%=basePath%>user/exportCompany.json?gsmc="+gsmc+"&fddbr="+fddbr+"&bgdz="+bgdz);
	        });
        	});
            // 查看合同详情
            $(".query").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert(shipList["1"]);
            	}else if($("input[name='check']:checked").length == 1){
            		var id = $("input[name='check']:checked").val();
            		var os = $("input[name='check']:checked").attr("order_source");
            	
            		layer.open({
					    type: 2,
					    area: ['800px', '510px'],
					    fix: false, //不固定
					    maxmin: true,
					    content: "/jsp/order/list_order_product.jsp?id="+id+"&order_source="+os,
					    title:'合同详情'
					});
            	}else{
            		layer.alert("只能选择一份合同进行查看", {icon: 7});
            	}
            });
            //删除
            $(".delete").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择需要删除的合同", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		layer.confirm('确认要删除本合同吗？<br/>删除请点击“确定”，否则点击“取消”', {icon: 3}, function(index){
	            		var id = $("input[name='check']:checked").val();
	            		$.post("<%=basePath%>order/delete.json",{"id":id},function(data){
					    	if(data.error){
	            				layer.msg(data.message);
	            			}else{
					    		initData();
					    		layer.msg("删除订单合同成功");
					    	}
					    });
				    });
            	}else{
            		layer.alert("只能选择一份合同进行删除", {icon: 7});
            	}
            });
            
            
            //配货
            $(".site").click(function(){ 
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择需要配货的订单合同", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var order_state = $("input[name='check']:checked").attr("order_state");
            		var order_source = $("input[name='check']:checked").attr("order_source");
            	if(order_source==4){
            		layer.alert("云商品不能配货！",{icon:7});
            	}else if(order_state!=4){
            			layer.alert("请选择配货中的合同！", {icon: 7});
            		}else{
	            		var id = $("input[name='check']:checked").val();
	            		layer.open({
						    type: 2,
						    area: ['800px', '500px'],
						    fix: false, //不固定
						    maxmin: true,
						    content: "/jsp/order/list_order_product_bh.jsp?id="+id,
						    title:'配货情况'
						});
					}
            	}else{
            		layer.alert("只能选择一份订单合同配货", {icon: 7});
            	}
            });
            
            //发货
            $(".send").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择需要发货的订单合同", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var order_state = $("input[name='check']:checked").attr("order_state");
            		var shipping_methods = $("input[name='check']:checked").attr("shipping_methods");
            		var order_source = $("input[name='check']:checked").attr("order_source");
            		if(order_source==4){
            		layer.alert("云商品不能发货！",{icon:7});
            		}else if(shipping_methods!=1&&shipping_methods!=3){
            			layer.alert("非物流商品，无法发货！",{icon: 7});
            		}else if(order_state!=4){
            			layer.alert("非配货状态的商品无法发货！", {icon: 7});
            		}else{
            			var id = $("input[name='check']:checked").val();
	            		layer.open({
						    type: 2,
						    area: ['600px', '300px'],
						    fix: false, //不固定
						    maxmin: true,
						    content: "/jsp/order/sendOutCondition.jsp?id="+id,
						    title:'发货情况',
						    end:function(){
						    	initData();
						    }
						    
						});
					}
            			
            			
				 
            	}else{
            		layer.alert("只能选择一份订单合同发货", {icon: 7});
            	}
            });
            
            //开始实施
            $(".ss").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择需要开始实施的订单合同", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var order_state = $("input[name='check']:checked").attr("order_state");
            		if(order_state!=61){
            			layer.alert("只能选择待实施的合同操作实施！", {icon: 7});
            		}else{
            			layer.prompt({title: '实施情况', formType: 2,value:'工程师姓名：&#xd手机：&#xd预计上门时间：'}, function(text){
		            		layer.confirm('确认要执行实施操作吗？<br/>开始实施请点击“确定”，否则点击“取消”', {icon: 3}, function(index){
			            		var id = $("input[name='check']:checked").val();
			            		$.post("<%=basePath%>order/ss.json",{"id":id,"text_sm":text},function(data){
							    	if(data.error){
			            				layer.msg(data.message);
			            			}else{
							    		initData();
							    		layer.msg("合同操作实施成功");
							    	}
							    });
						    });
					    });
				    }
            	}else{
            		layer.alert("只能选择一份订单合同进行实施", {icon: 7});
            	}
            });
            
             //查看评价
            $(".pj").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择需要查看评价的订单合同", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var order_state = $("input[name='check']:checked").attr("order_state");
            		if(order_state!=8){
            			layer.alert("该合同客户暂未评价！", {icon: 7});
            		}else{
            			var id = $("input[name='check']:checked").val();
						layer.open({
						    type: 2,
						    title: '服务评价',
						    shadeClose: true,
						    shade: 0.2,
						    area: ['500px', '300px'],
						    content: '/jsp/order/htpj.jsp?id='+id //iframe的url
						});
				    }
            	}else{
            		layer.alert("只能选择一份订单合同查看评价", {icon: 7});
            	}
            });
            
             //更新物流
            $(".express").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择需要更新物流信息的订单合同", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){ 
            		var order_state = $("input[name='check']:checked").attr("order_state");
            		var order_source = $("input[name='check']:checked").attr("order_source");
            	if(order_source==4){
            		layer.alert("云商品不能更新物流！",{icon:7});
            	}else if(order_state!=4&&order_state!=5){
            			layer.alert("只能选择配送中，已发货，待收货的合同操作填写添加物流状态！", {icon: 7});
            	}else{
            		
            			var id = $("input[name='check']:checked").val();
						layer.open({
						    type: 2,
						    title: '更新物流',
						    shadeClose: true,
						    shade: 0.2,
						    area: ['600px', '400px'],
						    content: '/jsp/order/updateExpress.jsp?id='+id //iframe的url
						});
						}
				    
            	}else{
            		layer.alert("只能选择一份订单合同更新物流信息", {icon: 7});
            	}
            });
            
             //代客户操作
            $(".dkhcz").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择需要操作的订单合同", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var order_state = $("input[name='check']:checked").attr("order_state");
            		var shipping_methods = $("input[name='check']:checked").attr("shipping_methods");
            		var order_source = $("input[name='check']:checked").attr("order_source");
            		if(order_source==4){
            			layer.alert("云商品不能代客户操作！",{icon:7});
            		}else  if(shipping_methods==2&&order_state!=4&&order_state!=5&&order_state!=62){
            			layer.alert("【自提】的订单中，只有交付状态为【配货中】【实施中】的订单合同才能代用户操作！",{icon:7});
            		}else if(shipping_methods==1&&order_state!=5&&order_state!=62){
            			layer.alert("【快递或物流】的订单中，只有交付状态为【已发货，待签收】或【实施中】的订单合同才能代用户操作！", {icon: 7});
            		}else if(shipping_methods==0){
            			layer.alert("未指定配送方式！", {icon: 7});
            		}else{
            			var id = $("input[name='check']:checked").val();
						layer.open({
						    type: 2,
						    title: '代客户操作',
						    shadeClose: true,
						    shade: 0.1,
						    area: ['480px', '270px'],
						    content: '/jsp/order/list_image.jsp?order_id='+id+'&shipping_methods='+shipping_methods //iframe的url
						});   
            	}
            	}else{
            		layer.alert("只能选择一份订单合同操作", {icon: 7});
            	}
            
            });
            
            //应收款状态
            $(".yskzt").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择需要维护应收款状态的订单合同", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var dqyskzt =$("input[name='check']:checked").attr("yskzt");
           			layer.prompt({title: '应收款状态', formType: 2,value:dqyskzt}, function(text){
	            		layer.confirm('确认要保存吗？', {icon: 3}, function(index){
		            		var id = $("input[name='check']:checked").val();
		            		$.post("<%=basePath%>order/yskzt.json",{"id":id,"text_sm":text},function(data){
						    	if(data.error){
		            				layer.msg(data.message);
		            			}else{
						    		initData();
						    		layer.msg("维护应收款状态成功");
						    	}
						    });
					    });
				    });
            	}else{
            		layer.alert("只能选择一份订单合同维护应收款状态", {icon: 7});
            	}
            });
            
             //附件上传
            $(".fjsc").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择需要上传附件的订单合同", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            			var id = $("input[name='check']:checked").val();
						layer.open({
						    type: 2,
						    title: '上传合同相关附件',
						    shadeClose: true,
						    shade: 0.1,
						    area: ['480px', '230px'],
						    content: '/jsp/order/list_file.jsp?order_id='+id //iframe的url
						});
            	}else{
            		layer.alert("只能选择一份订单合同上传附件", {icon: 7});
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
            
            //代客户开通
            $(".dkhkt").click(function(){
            	var kt = 0;
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择需要开通的云订单合同", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var id = $("input[name='check']:checked").val();
            			var order_state = $("input[name='check']:checked").attr("order_state");
            			var order_source = $("input[name='check']:checked").attr("order_source");
            			var order_number = $("input[name='check']:checked").attr("order_number");
            			if(order_source!=4){
            				layer.alert("只有云商品才能选择开通！订单"+order_number+"不是云商品！",{icon:7});
            			}else if(order_state!=1){
           					layer.alert("云商品"+order_number+"不属于已付款状态，不能开通！", {icon: 7});
           				}else{
           					layer.open({
					    type: 2,
					    area: ['780px', '405px'],
					    fix: false, //不固定
					    maxmin: true,
					    content: "/jsp/order/open.jsp?id="+id,
					    title:'代客户开通'
					});
            	}}else{
            		layer.alert("只能选择一条数据", {icon: 7});
            	}
            });

//              			if(flag==0){
            			
            			
//             			$.post("<%=basePath%>order/dkhkt.json",{"ids":ids},function(data){
            			
//             			if(data.count==ids.length-1){
//             				layer.alert("开通成功");
//             				initData();
//             			}else{
//             				layer.alert(data.message);	
//             				}
//             			});
//             			}
//             		}	    
            	
//             });
            
            
            
            //查询
            $(".search").click(function(){
	        	initData();
	        });
	        
        });
        
        function initData(){
        	//显示加载层
        	layer.load();
        
        	var order_number = $("#order_number").val();
        	var order_state= $("#order_state").val();
        	var order_source= $("#order_source").val();
        	var ywyxm = $("#ywyxm").val();
        	var gsmc = $("#gsmc").val();
            $.post("<%=basePath%>order/queryCount.json",{"order_number":order_number,"order_state":order_state,"ywyxm":ywyxm,"gsmc":gsmc,"order_source":order_source},function(data){
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
        
        
      			
     	function buttonHide(){
            	
        	//可用宽度
        	var toolbar_w = $(".count").find('ul').width();
        	var toolbar_h = $(".count").find('ul').height()+6;  
        	
        	if(toolbar_h>40){ //按钮到达第二行
        		//遍历
        		var max_w = 0;
        		var count = 0;
        		 $('.count').find('li').each(function(i){
        			max_w =max_w + $(this).width()+17; //margin+padding+border
        			if(max_w>toolbar_w){
        					if(i>count){
        						$(this).addClass('hide').hide();
        					}else{
        						var t = $(this).width()+17-(max_w-toolbar_w)-75;
        						if(t>0){  //前一个就可以放下
         							$(this).before("<li class=\"more\"><span><img src=\"<%=basePath%>images/t46.png\" /></span>更多</li>");
        							$(this).addClass('hide').hide();
        						}else{  
        							$(this).prev().before("<li class=\"more\"><span><img src=\"<%=basePath%>images/t46.png\" /></span>更多</li>");
        							$(this).addClass('hide').hide();
        							$(this).prev().addClass('hide').hide();
        						}
        				}
        			}else{
        				count++;
        			} 
        			});              
        	}	
        		}
        		
   		
        
        function pageSelectCallback(index, jq) {
        	buttonHide();
        	//更多按钮显示
            $(".more").click(function(){
   				var text = $(this).text();
   					if(text.indexOf("更多")>-1){
   						$('.count').find('li.hide').each(function(i){
   						
   						$(this).stop(true,true).slideDown();
   						});
   						$(".more").html("<span><img src=\"<%=basePath%>images/t45.png\" /></span>收起");
   					}else{
   						$('.count').find('li.hide').each(function(i){
   						$(this).stop(true,true).slideUp();
   						});
   						$(".more").html("<span><img src=\"<%=basePath%>images/t46.png\" /></span>更多");
   					}
   			});
        	$("#index").html(index+1);
        	var order_number = $("#order_number").val();
        	var order_state= $("#order_state").val();
        	var order_source= $("#order_source").val();
        	var ywyxm = $("#ywyxm").val();
        	var gsmc = $("#gsmc").val();
        	$.post("<%=basePath%>order/queryList.json",{"order_number":order_number,"order_state":order_state,"ywyxm":ywyxm,"gsmc":gsmc,"order_source":order_source,"pageIndex":index,"pageSize":pageSize},function(data){
	              //需要转换的字段
	          	var order_stateList = getStringmap('order_info','order_state');
	          	var order_sourceList = getStringmap('order_info','order_source');
	          	var shipping_methodsList = getStringmap('order_info','shipping_methods');
	          	var order_typeList = getStringmap('order_info','order_type');
	          	var rec_payconditionList = getStringmap('order_info','rec_paycondition');
	          	
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
         
      	          	//导出
            $("#export").click(function(){
       	var order_number = $("#order_number").val();
        	var order_state= $("#order_state").val();
        	var order_source= $("#order_source").val();
        	var ywyxm = $("#ywyxm").val();
        	var gsmc = $("#gsmc").val();
            	window.open("<%=basePath%>order/exportOrderList.json?order_number="+order_number+"&&order_state="+order_state+"&&ywyxm="+ywyxm+"&&gsmc="+gsmc+"&&order_source="+order_source);
	        });
  
        }
    </script>
</body>
</html>