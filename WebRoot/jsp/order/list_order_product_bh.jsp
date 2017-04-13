<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>合同配货</title>
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
                        <th>备货情况</th>
                    </tr>
                </thead>
    			<tbody id="content">
    				
    			</tbody>
            </table>
            <div id="savebh" style="text-align:right;">
            		<input id="order_number" type="hidden"/>
 					<input type="button" id="bcbhqk" class="btn" value="保存"/>
 				</div>
    </div>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/layer/layer.js"></script>
	<script src="<%=basePath%>js/common.js"></script>
    <script type="text/javascript">
    	var id = "${param.id}";
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
        }
        function pageSelectCallback() {
        	$.post("<%=basePath%>order/query.json",{"id":id},function(data){
        	var ORDER = data.ORDER;
        	$("#order_number").val(ORDER.order_number);
        	var ORDERDETAILSLIST = data.ORDERDETAILSLIST;
        		var _html="";
	           for(var i=0;i<ORDERDETAILSLIST.length;i++){
	           		var ORDERDETAILS = ORDERDETAILSLIST[i];
	           		_html += "<tr>"+
	           				"	<td>"+ORDERDETAILS.product_name+"</td>"+
	           				 "	<td>"+ORDERDETAILS.type_str+"</td>"+
	           				 "	<td>"+ORDERDETAILS.brand_str+"</td>"+
	           				 "	<td>"+ORDERDETAILS.xh+"</td>"+
	           				 "	<td>"+ORDERDETAILS.pz+"</td>"+
	           				 "	<td class=\"count\">"+ORDERDETAILS.product_count+"</td>"+
	           				 "	<td width=\"50\"><input name=\"\" class=\"bhqk\" did=\""+ORDERDETAILS.id+"\" value=\""+ORDERDETAILS.bhqk+"\"/></td>"+
	           				 "</tr>";
	           }
	           if(ORDERDETAILSLIST.length==0){
	           	_html+="<tr id=\"nodata\"><td>合同内商品清单为空</td><td></td><td></td><td></td><td></td><td></td><td></td></tr>";
	           }
	           $("#content").html(_html);
	           parent.layer.iframeAuto(index);
	            //关闭加载层
	        	layer.closeAll('loading');
        	});
        }
        
        //确定事件
           $("#bcbhqk").on("click",function(){
           		var bhsmarr = "";
           		$(".bhqk").each(function(){
           			var text_ = $(this).val();
           			if(text_==""){
	        			$(this).val("未说明");
	        		}else{
	        			text_ = text_.replace("\'","‘");
	        			text_ = text_.replace("\"","”");
	        			text_ = text_.replace("\-","－");
	        			text_ = text_.replace("\:","：");
	        			$(this).val(text_);
	        		}
	        		bhsmarr+=$(this).attr("did")+"-"+$(this).val()+":";
           		});
           		var order_number = $("#order_number").val();
           		var param = {"order_number":order_number,"bhsmarr":bhsmarr};
            	$.post("<%=basePath%>order/order_bh.json",param,function(data){
        			if(data.count>0){
        				layer.msg("保存成功",{time:1000},function(){
        					parent.layer.close(index);
        				});
        			}else{
        				layer.msg("保存失败");
        			}
        		});
           	
        });
    </script>
</body>
</html>