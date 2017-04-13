<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>需求详情</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	body{min-width:400px;}
	#content input{padding:5px;}
	.checknull{border:1px solid #f0a66f;}
</style>
</head>

<body>
    <div class="formbody">
   	 		<ul class="forminfo">
	            <li><label>需求单号：</label>
	                <label><dl id="order_number">--</dl></label>
	            </li>
	            <li><label>需求状态：</label>
	                <label><dl id="order_state">--</dl></label>
	            </li>
    			<li><label>时间进度：</label>
	                <label><dl id="time" style="width:200px;">--</dl></label>
	            </li>
	            <li><label>需求标题：</label>
	                <label><dl id="title" style="width:200px;">--</dl></label>
	            </li>
	            <li><label>需求描述：</label>
	                <label><dl id="xqms" style="width:300px;">--</dl></label>
	            </li>
	            <li><label>需求附件：</label>
	                <label><dl id="xqfj">--</dl></label>
	            </li>
	            <li id="xqfj2_p"><label>需求附件2：</label>
	                <label><dl id="xqfj2">--</dl></label>
	            </li>
	            <li id="xqfj3_p"><label>需求附件3：</label>
	                <label><dl id="xqfj3">--</dl></label>
	            </li>
	            <li id="offer_file"><label>报价附件：</label>
	                <label><dl id="offer_file-1" style="width:300px;">--</dl></label>
	            </li>
	            <li><label>收货人姓名：</label>
	                <label><dl id="xm" style="width:300px;">--</dl></label>
	            </li>
	            <li><label>收货人电话：</label>
	                <label><dl id="dh" style="width:300px;">--</dl></label>
	            </li>
	            <li><label>收货邮编：</label>
	                <label><dl id="yb" style="width:300px;">--</dl></label>
	            </li>
	            <li><label>收货地址：</label>
	                <label><dl id="dz" style="width:300px;">--</dl></label>
	            </li>
	        </ul>
            <table class="tablelist">
                <thead>
                    <tr>
                    	<th width="230">商品名称</th>
                        <th width="60">类型</th>
                        <th>品牌</th>
                        <th width="80">型号</th>
                        <th width="80">配置</th>
                        <th width="40">数量</th>
                        <th>标准价</th>
                        <th>报价</th>
                        <th>小计</th>
                    </tr>
                </thead>
    			<tbody id="content">
    				
    			</tbody>
            </table>
            <div id="savebj" style="text-align:right;height:30px;line-height:30px;font-size:14px;font-weight:bold;">
 					报价总额：￥<font color="red" id="total" style="font-size:14px;font-weight:bold;">0.00</font>
 				</div>
    </div>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/layer/layer.js"></script>
	<script src="<%=basePath%>js/common.js"></script>
	<script src="<%=basePath%>js/stringmap.js"></script>
    <script type="text/javascript">
    	var id = "${param.id}";
    	var order_number="";
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    	$(document).ready(function () {
            initData();
        });
        
        function initData(){
        	//显示加载层
        	layer.load();
        	pageSelectCallback();
        }
        function pageSelectCallback() {
        	$.post("<%=basePath%>orderxuqiu/query.json",{"id":id},function(data){
        	var XUQIU = data.XUQIU;
        	order_number = XUQIU.order_number;
        	$("#order_number").html(XUQIU.order_number);
        	$("#xqms").html(XUQIU.content);
        	$("#title").html(XUQIU.title);
        	$("#xm").html(XUQIU.xm);
        	$("#dh").html(XUQIU.dh);
        	$("#dz").html(XUQIU.dz);
        	$("#yb").html(XUQIU.yb);
        	var time_str="";
        	
    		if(XUQIU.order_state==4||XUQIU.order_state==5||XUQIU.order_state==6||XUQIU.order_state==7||XUQIU.order_state==8 ){time_str+="需求发布："+XUQIU.fmt_order_time;}
           	if(XUQIU.order_state==5||XUQIU.order_state==6||XUQIU.order_state==7||XUQIU.order_state==8){time_str+="<br/>系统报价："+XUQIU.fmt_bj_time;}
           	if(XUQIU.order_state==7||XUQIU.order_state==8 ){time_str+="<br/>接受报价："+XUQIU.fmt_khqr_time;}
           	if(XUQIU.order_state==8 ){time_str+="<br/>合同生成："+XUQIU.fmt_htsc_time;}
           	
        	$("#time").html(time_str);
        	
        	$("#order_state").html(getStringmap('order_info','order_state')[XUQIU.order_state]);
        	if((XUQIU.demand_file==""||XUQIU.demand_file==null)&&(XUQIU.demand_file2==""||XUQIU.demand_file2==null)&&(XUQIU.demand_file3==""||XUQIU.demand_file3==null)){$("#xqfj").html("无附件");}
        	if(XUQIU.demand_file==""||XUQIU.demand_file==null){}else{
        		$("#xqfj").html("<a href=\"http://www.tulingbuy.com"+XUQIU.demand_file+"\" target=\"_blank\">查看</a>");
        	}
        	if(XUQIU.demand_file2==""||XUQIU.demand_file2==null){$("#xqfj2_p").remove();}else{
        		$("#xqfj2").html("<a href=\"http://www.tulingbuy.com"+XUQIU.demand_file2+"\" target=\"_blank\">查看</a>");
        	}
        	if(XUQIU.demand_file3==""||XUQIU.demand_file3==null){$("#xqfj3_p").remove();}else{
        		$("#xqfj3").html("<a href=\"http://www.tulingbuy.com"+XUQIU.demand_file3+"\" target=\"_blank\">查看</a>");
        	}
        	if(XUQIU.offer_file==""||XUQIU.offer_file==null||XUQIU.order_source!=2){$("#offer_file").remove();}else{
        	$("#offer_file-1").html("<a href=\"http://www.tulingbuy.com"+XUQIU.offer_file+"\" target=\"_blank\">查看</a>");
        	}
        	var ORDERDETAILSXUQIULIST = data.ORDERDETAILSXUQIULIST;
        		var _html="";
        		var total_=0;
	           for(var i=0;i<ORDERDETAILSXUQIULIST.length;i++){
	           		var ORDERDETAILSXUQIU = ORDERDETAILSXUQIULIST[i];
	           		var xj = (parseFloat(ORDERDETAILSXUQIU.product_unit_price_bj)*Number(ORDERDETAILSXUQIU.product_count)).toFixed(2);
	           		var dj = ORDERDETAILSXUQIU.product_unit_price_bj==0?"无":(parseFloat(ORDERDETAILSXUQIU.product_unit_price_bj)).toFixed(2);
	           		var bzj = ORDERDETAILSXUQIU.product_unit_price==0?"无":(parseFloat(ORDERDETAILSXUQIU.product_unit_price)).toFixed(2);
	           		_html += "<tr>"+
	           				"	<td>"+ORDERDETAILSXUQIU.product_name+"</td>"+
	           				 "	<td>"+ORDERDETAILSXUQIU.type_str+"</td>"+
	           				 "	<td>"+ORDERDETAILSXUQIU.brand_str+"</td>"+
	           				 "	<td>"+ORDERDETAILSXUQIU.xh+"</td>"+
	           				 "	<td>"+ORDERDETAILSXUQIU.pz+"</td>"+
	           				 "	<td class=\"count\">"+ORDERDETAILSXUQIU.product_count+"</td>"+
	           				 "	<td width=\"50\">"+bzj+"</td>"+
	           				 "	<td width=\"50\">"+dj+"</td>"+
	           				 "	<td  width=\"80\" class=\"xiaoji\">￥"+xj+"</td>"+
	           				 "</tr>";
	           				 total_ +=parseFloat(xj);
	           }
	           $("#total").html(total_.toFixed(2));
	           if(ORDERDETAILSXUQIULIST.length==0){
	           	_html+="<tr id=\"nodata\"><td>需求未整理，无商品清单</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>";
	           }
	           $("#content").html(_html);
	           parent.layer.iframeAuto(index);
	            //关闭加载层
	        	layer.closeAll('loading');
        	});
        }
        
    </script>
</body>
</html>