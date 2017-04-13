<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>需求报价</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	body{min-width:400px;}
	#content input{padding:5px;}
	.checknull{border:1px solid #f0a66f;}
</style>
</head>

<body>
    <div class="formbody">
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
            <div id="savebj" style="text-align:right;">
 					报价总额：￥<font color="red" id="total">0.00</font>&nbsp;&nbsp;<input type="button" id="qdbj" class="btn" value="确认报价"/>
 				</div>
    </div>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/layer/layer.js"></script>
	<script src="<%=basePath%>js/common.js"></script>
    <script type="text/javascript">
    	var id = "${param.id}";
    	var order_number="";
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    	$(document).ready(function () {
            initData();
        });
        function isPositiveNum(s){//是否为正整数
			var re = /^[0-9]*[1-9][0-9]*$/ ;
			return re.test(s)
		} 
        
        function initData(){
        	//显示加载层
        	layer.load();
        	pageSelectCallback();
        	
        	//输入单价事件
        	$(document).on("blur",".price",function(){
        		if($(this).val()!=""){
        			if(isNaN($(this).val())){
        				parent.layer.alert("请输入有效的价格");
        				$(this).val("");
        				$(this).parent().parent().find(".xiaoji").html("￥0.00");
        				$(this).addClass("checknull");
        			}else{
        				$(this).removeClass("checknull");
        				var product_unit_price =$(this).val();
        				$(this).val((parseFloat(product_unit_price)).toFixed(2));
        				var product_count =$(this).parent().parent().find(".count").html();
        				$(this).parent().parent().find(".xiaoji").html("￥"+(parseFloat(product_unit_price)*Number(product_count)).toFixed(2));
        				
        			}
        		}
   				var total_=0;
   				$(".price").each(function(){
        			if($(this).val()!=""){
     					total_+=parseFloat($(this).val())*Number($(this).attr("count"));
      				}
        		});
   				$("#total").html(total_.toFixed(2));
        	});
        }
        function pageSelectCallback() {
        	$.post("<%=basePath%>orderxuqiu/query.json",{"id":id},function(data){
        	var XUQIU = data.XUQIU;
        	order_number = XUQIU.order_number;
        	var ORDERDETAILSXUQIULIST = data.ORDERDETAILSXUQIULIST;
        		var _html="";
        		var total_=0;
	           for(var i=0;i<ORDERDETAILSXUQIULIST.length;i++){
	           		var ORDERDETAILSXUQIU = ORDERDETAILSXUQIULIST[i];
	           		var dj_bj = ORDERDETAILSXUQIU.product_unit_price_bj==0?"":(parseFloat(ORDERDETAILSXUQIU.product_unit_price_bj)).toFixed(2);
	           		var bzj = ORDERDETAILSXUQIU.product_unit_price==0?"无":(parseFloat(ORDERDETAILSXUQIU.product_unit_price)).toFixed(2);
	           		var xj = (parseFloat(ORDERDETAILSXUQIU.product_unit_price_bj)*Number(ORDERDETAILSXUQIU.product_count)).toFixed(2);
	           		_html += "<tr>"+
	           				"	<td>"+ORDERDETAILSXUQIU.product_name+"</td>"+
	           				 "	<td>"+ORDERDETAILSXUQIU.type_str+"</td>"+
	           				 "	<td>"+ORDERDETAILSXUQIU.brand_str+"</td>"+
	           				 "	<td>"+ORDERDETAILSXUQIU.xh+"</td>"+
	           				 "	<td>"+ORDERDETAILSXUQIU.pz+"</td>"+
	           				 "	<td class=\"count\">"+ORDERDETAILSXUQIU.product_count+"</td>"+
	           				 "	<td>"+bzj+"</td>"+
	           				 "	<td width=\"50\"><input class=\"price\" did=\""+ORDERDETAILSXUQIU.id+"\" count=\""+ORDERDETAILSXUQIU.product_count+"\" style=\"width:50px;\" value=\""+dj_bj+"\"/></td>"+
	           				 "	<td  width=\"80\" class=\"xiaoji\">￥"+xj+"</td>"+
	           				 "</tr>";
	           				 total_ +=parseFloat(xj);
	           }
	           $("#total").html(total_.toFixed(2));
	           if(ORDERDETAILSXUQIULIST.length==0){
	           	_html+="<tr id=\"nodata\"><td>需求未整理，无法报价</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>";
	           }
	           $("#content").html(_html);
	           parent.layer.iframeAuto(index);
	            //关闭加载层
	        	layer.closeAll('loading');
        	});
        }
        
        //确定报价
           $("#qdbj").on("click",function(){
           		var flag= true;
           		var bjarr = "";
           		$(".price").each(function(){
           			if($(this).val()!=""){
	        			if(isNaN($(this).val())){
	        				parent.layer.alert("请输入有效的价格");
	        				$(this).val("");
	        				$(this).parent().parent().find(".xiaoji").html("￥0.00");
	        				$(this).addClass("checknull");
	        				flag = false;
	        			}else{
	        				$(this).removeClass("checknull");
	        				bjarr+=$(this).attr("did")+"-"+$(this).val()+"-"+$(this).attr("count")+":";
	        			}
	        		}else{
	        			flag = false;
	        			$(this).addClass("checknull");
	        		}
           		});
           		
				if(flag){
					var param = {"order_number":order_number,"total": $("#total").html(),"bjarr":bjarr};
	            	$.post("<%=basePath%>orderxuqiu/orderxuqiu_bj.json",param,function(data){
	        			if(data.count>0){
	        				layer.msg("报价成功");
	        				parent.initData();
	        			}else{
	        				layer.msg("报价失败");
	        			}
	        		});
        		}
        });
    </script>
</body>
</html>