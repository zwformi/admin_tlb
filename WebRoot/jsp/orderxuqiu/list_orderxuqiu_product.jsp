<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>需求整理列表</title>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/layer/layer.js"></script>
	<script src="<%=basePath%>js/common.js"></script>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>js/uploader/webuploader_ext.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/uploader/webuploader.min.js"></script>
<script src="<%=basePath%>js/uploader/webuploader_order.js"></script>
<style type="text/css">
	body{min-width:400px;min-height:300px;}
	.update_file{margin-bottom:0px;padding:10px;width:600px;}
	.btn{margin-right:20px;}
	#content input{padding:5px;}
	.checknull{border:1px solid #f0a66f;}
</style>
</head>

<body>
    <div class="formbody">
    		<ul class="forminfo">
    			<li><label>需求发布时间：</label>
	                <label><dl id="order_time" style="width:200px;">--</dl></label>
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
	           <li id="offerupload" style="height:50px">
	           <label>报价附件上传：</label>
	              <div class="update_file">
    		
    		<input id="file_url" name="file_url" type="text" class="dfinput upload-path" value="${param.offer_file}"readonly="readonly"/>
    		<div class="upload-box upload" style="top:-3px; left:-1px"></div>
    	</div>
<!--     	<div id="savebh" style="text-align:right;"> -->
<!--     		<input type="hidden" id="order_id" value="${param.order_id}"/> -->
<!-- 		<input type="button" id="save" class="btn" value="保存"/> -->
<!-- 	</div> -->
	            </li>
	        </ul>
	        
            <table class="tablelist">
                <thead>
                    <tr>
                    	<th>商品名称</th>
                        <th>类型</th>
                        <th>品牌</th>
                        <th>型号</th>
                        <th>配置</th>
                        <th>数量</th>
                        <th width="36">操作</th>
                    </tr>
                </thead>
    			<tbody id="content">
    				
    			</tbody>
    			<tfoot>
    				<tr>
    					<td></td><td></td><td></td><td></td><td></td><td></td>
    					<td id="add">
	    					<img src="<%=basePath%>images/t01.png" width="15"/>&nbsp;
	    				</td>
    				</tr>
    			</tfoot>
            </table>
			
			
    </div>


    <script type="text/javascript">
    	var id = "${param.id}";
    	var order_number="${param.order_number}";
    	var type="";
    	if("${!empty param.offer_file}"=="true"){
    		$(".btn").hide();
    	}
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    	$(document).ready(function () {
            initData();
    	//初始化上传控件
         $(".upload").InitUploader({ sendurl: "<%=basePath%>sys/uploadOffer.json?order_number="+order_number, swf: "<%=basePath%>js/webuploader/uploader.swf" });
      	var order_source = "${param.order_source}";
      	if(order_source==2){
      		//上传附件
     		$("#offerupload").css("margin-bottom","70px");
      	}else{
      		$("#offerupload").remove();
      	}      

      		//报价附件提交
      		$(".btn").click(function(){
      			
      			var file_url = $("#file_url").val();
      			if(file_url==""){
      				layer.alert("请上传报价附件！");
      			}else{
      				$.post("<%=basePath%>orderxuqiu/makeup.json",{"offer_file":file_url,"order_number":order_number},function(data){
			    		if(data.count==1){
			    			layer.msg("保存成功",{time:1000},function(){
        					initData();
			    		});
			    	}
			    	});
      			}
      		});
      
      		
            //新增-界面
           $(document).on("click","#add",function(){
            	$("#nodata").remove();
            	var _html = "<tr>"+
            				 "	<td><input style=\"width:150px;\" value=\"\"/></td>"+
	           				 "	<td><input style=\"width:70px;\" value=\"\"/></td>"+
	           				 "	<td><input style=\"width:70px;\" value=\"\"/></td>"+
	           				 "	<td><input style=\"width:80px;\" value=\"\"/></td>"+
	           				 "	<td><input style=\"width:120px;\" value=\"\"/></td>"+
	           				 "	<td><input style=\"width:40px;\" value=\"\"/></td>"+
	           				 "	<td class=\"save\" title=\"保存\" paramid=\"\"><img src=\"<%=basePath%>/images/check_ok.png\" width=\"15\" style=\"padding-top:10px;\"/>&nbsp;</td>"+
	           				 "</tr>";
	           	$("#content").append(_html);
            	parent.layer.iframeAuto(index);//页面自动高度
            });
            
            //删除
            $("#content").on("click",".delete",function(){
            	var did = $(this).attr("id");
            	parent.layer.confirm('确认删除该商品信息吗 ？', {icon: 3}, function(index){
					$.post("<%=basePath%>orderxuqiu/deleteorderxuqiu.json",{"id":did,"order_number":order_number},function(data){
			    		parent.layer.close(index);
			    		initData();
			        });
				});
            });
            
            //新增保存
           $("#content").on("click",".save",function(){
           		var $this = $(this);
           		var $product_name  = $(this).prev().prev().prev().prev().prev().prev().find("input");
           		var $type_str  = $(this).prev().prev().prev().prev().prev().find("input");
           		var $brand_str = $(this).prev().prev().prev().prev().find("input");
           		var $xh = $(this).prev().prev().prev().find("input");
           		var $pz = $(this).prev().prev().find("input");
           		var $product_count = $(this).prev().find("input");
           		var isok= true;
           		if($product_name.val()==""){
           			$product_name.addClass("checknull");
           			isok = false;
           		}else{$product_name.removeClass("checknull");}
           		if($type_str.val()==""){
           			$type_str.addClass("checknull");
           			isok = false;
           		}else{$type_str.removeClass("checknull");}
           		if($brand_str.val()==""){
           			$brand_str.addClass("checknull");
           			isok = false;
           		}else{$brand_str.removeClass("checknull");}
           		if($xh.val()==""){
           			$xh.addClass("checknull");
           			isok = false;
           		}else{$xh.removeClass("checknull");}
           		if($pz.val()==""){
           			$pz.addClass("checknull");
           			isok = false;
           		}else{$pz.removeClass("checknull");}
           		if($product_count.val()==""){
           			$product_count.addClass("checknull");
           			isok = false;
           		}else{
					if(!isPositiveNum($product_count.val())){
						parent.layer.alert("请输入合法的数字");
						$product_count.addClass("checknull");
						isok = false;
					}else{$product_count.removeClass("checknull");}
				}
				if(isok){
	            	$.post("<%=basePath%>orderxuqiu/addorderxuqiu.json",{"order_number":order_number,"product_name":$product_name.val(),"type_str":$type_str.val(),"brand_str":$brand_str.val(),"xh":$xh.val(),"pz":$pz.val(),"product_count":$product_count.val()},function(data){
	        			if(data.id>0){
	        				$this.attr("title","删除");
	        				$this.removeClass("save");
	        				$this.html("<img src=\"<%=basePath%>/images/t03.png\" width=\"15\"/>&nbsp;");
	        				$this.attr("id",data.id);
	        				$this.addClass("delete");
	        				layer.msg("保存成功");
	        			}else{
	        				layer.msg("保存失败");
	        			}
	        		});
        		}
            });
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
        	$.post("<%=basePath%>orderxuqiu/query.json",{"id":id},function(data){
        	var XUQIU = data.XUQIU;
        	order_number = XUQIU.order_number;
        	$("#xqms").html(XUQIU.content);
        	$("#order_time").html(XUQIU.fmt_order_time);
        	$("#file_url").val(XUQIU.offer_file);
        	if((XUQIU.demand_file==""||XUQIU.demand_file==null)&&(XUQIU.demand_file2==""||XUQIU.demand_file2==null)&&(XUQIU.demand_file3==""||XUQIU.demand_file3==null)){$("#xqfj").html("无附件");}
        	if(XUQIU.demand_file1==""||XUQIU.demand_file1==null){}else{
        		$("#xqfj").html("<a href=\"http://www.tulingbuy.com"+XUQIU.demand_file+"\" target=\"_blank\">查看</a>");
        	}
        	if(XUQIU.demand_file2==""||XUQIU.demand_file2==null){$("#xqfj2_p").remove();}else{
        		$("#xqfj2").html("<a href=\"http://www.tulingbuy.com"+XUQIU.demand_file2+"\" target=\"_blank\">查看</a>");
        	}
        	if(XUQIU.demand_file3==""||XUQIU.demand_file3==null){$("#xqfj3_p").remove();}else{
        		$("#xqfj3").html("<a href=\"http://www.tulingbuy.com"+XUQIU.demand_file3+"\" target=\"_blank\">查看</a>");
        	}
        	var ORDERDETAILSXUQIULIST = data.ORDERDETAILSXUQIULIST;
        	var _html="";
	           for(var i=0;i<ORDERDETAILSXUQIULIST.length;i++){
	           		var ORDERDETAILSXUQIU = ORDERDETAILSXUQIULIST[i];
	           		_html += "<tr>"+
	           				"	<td><input style=\"width:150px;\" value=\""+ORDERDETAILSXUQIU.product_name+"\"/></td>"+
	           				 "	<td><input style=\"width:70px;\" value=\""+ORDERDETAILSXUQIU.type_str+"\"/></td>"+
	           				 "	<td><input style=\"width:70px;\" value=\""+ORDERDETAILSXUQIU.brand_str+"\"/></td>"+
	           				 "	<td><input style=\"width:80px;\" value=\""+ORDERDETAILSXUQIU.xh+"\"/></td>"+
	           				 "	<td><input style=\"width:120px;\" value=\""+ORDERDETAILSXUQIU.pz+"\"/></td>"+
	           				 "	<td><input style=\"width:40px;\" value=\""+ORDERDETAILSXUQIU.product_count+"\"/></td>"+
	           				 "	<td class=\"delete\"  title=\"删除\" id=\""+ORDERDETAILSXUQIU.id+"\"><img src=\"<%=basePath%>/images/t03.png\" width=\"15\"/>&nbsp;</td>"+
	           				 "</tr>";
	           }
	           if(ORDERDETAILSXUQIULIST.length==0){
	           	_html+="<tr id=\"nodata\"><td>需求未整理，请及时整理以便客户确认</td><td></td><td></td><td></td><td></td><td></td><td></td></tr>";
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