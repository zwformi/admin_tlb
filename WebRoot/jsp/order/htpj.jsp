<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8"/>
<title>订单评价---图灵买tbl_order_evaluate</title>
<link rel="stylesheet" href="<%=basePath%>css/style.css"/>
<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>js/raty/jquery.raty.min.js"></script>
<script type="text/javascript">
	 $(function() {
      	$.fn.raty.defaults.path = '<%=basePath%>js/raty/img';
      	//加载内容
      	 initData();
      	 $('#score_div').raty();
     });
     
     function initData(){
        	//显示加载层
        	layer.load();
        	pageSelectCallback();
        }
        function pageSelectCallback() {
        	var id=$("#order_id").val();
        	$.post("<%=basePath%>order/querypj.json",{"id":id},function(data){
	           	var EVALUATE = data.EVALUATE;
	           	$('#score_div').raty({readOnly: true, score: EVALUATE.score });
	           $("#content").html(EVALUATE.content);
	            //关闭加载层
	        	layer.closeAll('loading');
        	});
        }
</script>
<style type="text/css">
	body{min-width:400px;}
</style>
</head>

<body><input type="hidden" value="${param.id}" id="order_id"/>
	<table cellspacing="0" border=0 cellpadding="0" width="100%">
		<tr style="height:50px;line-height:50px;">
			<td style="width:50px;text-align:right;">评分：</td>
			<td id="score_div" style="padding-top:7px;"></td>
		</tr>
		<tr>
			<td style="vertical-align: top;text-align:right;">评语：</td>
			<td width="370" id="content">
				正在加载...
			</td>
		</tr>
	</table>
</body>
</html>
