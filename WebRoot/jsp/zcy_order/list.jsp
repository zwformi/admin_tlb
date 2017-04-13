<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="true" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
SimpleDateFormat sdf = new SimpleDateFormat("YYYYMM");
Calendar calendar = Calendar.getInstance();
calendar.setTime(new Date());
calendar.add(Calendar.MONTH, -2);
Date m3 = calendar.getTime();
String mon3 = sdf.format(m3);
calendar.add(Calendar.MONTH, -9);
Date m12 = calendar.getTime();
String mon12 = sdf.format(m12);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>政采云订单管理</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/pagination.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<script src="<%=basePath%>js/jquery.pagination.js"></script>
<script src="<%=basePath%>js/jquery.tmpl.js"></script>
<script src="<%=basePath%>js/stringmap.js"></script>
<script src="<%=basePath%>js/common.js"></script>

<style type="text/css">
	body{background:#fff;}
    tbody .no {width: 80px; text-align: center; text-indent: 0px;}
  	.tablelist td{padding:20px 4px;left:0; text-indent:0;line-height:20px;text-align:center;}
  	.table{width:100%;border: 1px solid #ccd0d6;border-collapse: collapse; border-spacing: 0;}
  	.table>thead {border-bottom: 1px solid #e7e9ea;vertical-align: bottom;background-color: #f7f7f7;color: #666;}
  	.table thead>tr {background-color: #f7f7f7;color: #666!important;font-size: 12px!important;height:40px;line-height:40px;}
  	.table tbody>tr {background-color: #f2f6f9;border: 1px solid #e7e9ea;border-left: 1px solid #e7e9ea;color: #44454d;}
  	.table tbody tr>td { text-align: center; border-width: 0 0 1px;padding-top: 10px; padding-bottom:10px;   padding-left: 5px;padding-right: 5px;} 
  	
</style>
</head>



        
<body>
	<div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="<%=basePath%>jsp/firstpage.jsp">首页</a></li>
            <li><a href="javascript:;">政采云订单管理</a></li>
        </ul>
    </div>

    
    <div  class="formbody">
    <br>
        <div class="formtitle" style="margin:17px"><span>订单合同列表</span></div>
        <div class="rightinfo">
        	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_order_search")){%>

          
<div class="tools">
<form id ="dataForm">
      <ul class="toolbar" >
      <li class="select">
        <label>时间：</label>
          <select name="orderTime">
            <option value="<%=(mon3+"010000")%>">近三个月订单</option>
            <option value="<%=(mon12+"010000")%>">今年内订单</option>
          </select>
          </li>
          <li  class="select">
        <label>状态：</label>
          	<select name="orderState">
              <option value="-1">全部状态</option>
              <option value="0">待供应商接单</option>
              <option value="1">待发货</option>
              <option value="2">部分发货</option>
              <option value="3">全部发货</option>
              <option value="4">待验收</option>
              <option value="5">已验收</option>
              <option value="6">启动结算</option>
              <option value="7">已完成</option>
              <option value="-4">订单待取消</option>
              <option value="-2">供应商拒单</option>
              <option value="-5">订单取消</option>
              <option value="10">退换货中</option>
          </select>
        </li>
      <li id="dosearch">
          &nbsp;&nbsp;搜索
      </li> 

</ul>
</form>
     <%}%>
			  <table class="table table-striped order-form-list-table">
			      <thead>
			        <tr>
			          
			          
			          <th width="14%">订单编号</th>
			          <th width="10%">采购单位</th>
			          <th width="10%">采购人</th>
			          <th width="9%">订单类型</th>
			          <th width="10%">订单金额(元)</th>
			          <th width="11%">时间</th>
			          
			          <th width="9%">验收单</th>
			          <th width="8%">状态</th>
			          <th width="15%">操作</th>
			          
			        </tr>
			      </thead>
			      <tbody id="content"></tbody>
    			<script id="template" type="text/x-jquery-tmpl">
    			<tr data-id="${order.id}" data-shipmentid="" data-ordertype="${order.orderType}" class="order-tr first-level-tr">
                <td><a href="detail.jsp?id=${order.id}" class="order-name">${order.id}</a></td>
                <td>
                  <a href="javascript:;" class="js-get-phone" data-user-id="100018511" data-user-type="1">${order.purchaserOrganizationName}</a>
                </td>
              	<td>${order.purchaserName}</td>
              	<td>{{if order.orderType==0}}普通订单{{/if}}</td>
              	<td><span class="price"><span class="order-subtotal">${order.fee/100}</span></span></td>
             	<td><span class="order-span"><span class="">${order.createdAt}</span></span></td>             
              	<td>
				  <a href="javascript:;" class="js-show-acceptance-file js-check-user" data-isowner="0" data-status="5" data-files="[{&quot;id&quot;:441,&quot;orderId&quot;:&quot;1511823000000014237&quot;,&quot;type&quot;:2,&quot;userId&quot;:100058825,&quot;fileName&quot;:&quot;2017021017281128642955.png&quot;,&quot;filePath&quot;:&quot;http://demo-item.img-cn-hangzhou.aliyuncs.com/2017021017281128642955.png&quot;,&quot;createdAt&quot;:1486718938000,&quot;updatedAt&quot;:1486718938000,&quot;userName&quot;:&quot;葛晾&quot;,&quot;orgName&quot;:&quot;浙江浙大网新图灵信息科技有限公司&quot;}]" data-id="100058825" data-type="2">验收单</a>  
              	</td>
              	<td>{{if order.status==0}}待供应商接单(等待处理){{else order.status==1}}已接收,待发货{{else order.status==2}}部分发货{{else order.status==3}}全部发货{{else order.status==4}}待验收(确认收货){{else order.status==5}}已验收(待安装){{else order.status==6}}(启动结算)待验收{{else order.status==7}}已完成{{else order.status==-4}}订单待取消{{else order.status==-2}}供应商拒单{{else order.status==-5}}订单取消{{else order.status==10}}退换货中{{else}}状态未知{{/if}}</td>
              	<td data-record="false" data-status="5" data-isneedr="false" data-hasrecive="">
                 <a href="detail.jsp?id=${order.id}" class="order-name">查看详情</a><br>
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
            $("#dosearch").click(function(){
            	pageIndex=0;
	        	initData();
	        });
	        
        });
        
        function initData(){
        	//显示加载层
        	layer.load();

            $.post("<%=basePath%>zcy/getorderlist.json",{'start':$("select[name='orderTime']").val(),'status':$("select[name='orderState']").val(),'pageNo':1,'pageSize':1},function(data){
            	$("#total").html(data.total);
            	//分页事件
	            $("#pagination").pagination(data.total, {
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
        	var _i = layer.load();
        	pageIndex = index;

        	
        	$("#index").html(index+1);
        	$.post("<%=basePath%>zcy/getorderlist.json",{'start':$("select[name='orderTime']").val(),'status':$("select[name='orderState']").val(),'pageNo':(pageIndex+1),'pageSize':pageSize},function(data){
        		layer.close(_i);				
				//console.log(data.data);                
                var redl=data.data.replace(/(id\":)([0-9]+?)(,)/ig,"$1\"$2\"$3");
                var datalist = JSON.parse(redl);
	            $("#content").html($("#template").tmpl(datalist));
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