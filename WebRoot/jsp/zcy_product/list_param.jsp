<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>产品参数列表</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	body{min-width:400px;}
	#content input{padding:5px;width:87%;}
	.checknull{border:1px solid #f0a66f;}
</style>
</head>

<body>
    <div class="formbody">
            <table class="tablelist">
                <thead>
                    <tr>
                        <th>参数名称</th>
                        <th>参数值</th>
                        <th>排序ID</th>
                        <th width="36">操作</th>
                    </tr>
                </thead>
    			<tbody id="content">
    				
    			</tbody>
    			<tfoot>
    				<tr>
    					<td></td><td></td><td></td>
    					<td id="add">
	    					<img src="<%=basePath%>images/t01.png" width="15"/>&nbsp;
	    				</td>
    				</tr>
    			</tfoot>
            </table>
    </div>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/layer/layer.js"></script>
	<script src="<%=basePath%>js/common.js"></script>
    <script type="text/javascript">
    	var product_id = "${param.product_id}";
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    	$(document).ready(function () {
            initData();
            
            //新增-界面
           $(document).on("click","#add",function(){
            	$("#nodata").remove();
            	var _html = "<tr>"+
	           				 "	<td><input value=\"\"/></td>"+
	           				 "	<td><input value=\"\"/></td>"+
	           				 "	<td><input value=\"\"/></td>"+
	           				 "	<td class=\"save\" title=\"保存\" paramid=\"\"><img src=\"<%=basePath%>/images/check_ok.png\" width=\"15\" style=\"padding-top:10px;\"/>&nbsp;</td>"+
	           				 "</tr>";
	           	$("#content").append(_html);
            	parent.layer.iframeAuto(index);//页面自动高度
            });
            
            //删除
            $("#content").on("click",".delete",function(){
            	var id = $(this).attr("paramid");
            	parent.layer.confirm('确认删除该参数吗 ？', {icon: 3}, function(index){
					$.post("<%=basePath%>product/deleteParam.json",{"id":id,"product_id":product_id},function(data){
			    		parent.layer.close(index);
			    		initData();
			        });
				});
            });
            
            //新增保存
           $("#content").on("click",".save",function(){
           		var $this = $(this);
           		var $par_name = $(this).prev().prev().prev().find("input");
           		var $par_value = $(this).prev().prev().find("input");
           		var $sortid = $(this).prev().find("input");
           		var isok= true;
           		if($par_name.val()==""){
           			$par_name.addClass("checknull");
           			isok = false;
           		}else{$par_name.removeClass("checknull");}
           		if($par_value.val()==""){
           			$par_value.addClass("checknull");
           			isok = false;
           		}else{$par_value.removeClass("checknull");}
           		if($sortid.val()==""){
           			$sortid.addClass("checknull");
           			isok = false;
           		}else{
					if(!isPositiveNum($sortid.val())){
						parent.layer.alert("请输入合法的数字");
						$sortid.addClass("checknull");
						isok = false;
					}else{$sortid.removeClass("checknull");}
				}
				if(isok){
	            	$.post("<%=basePath%>product/addParam.json",{"product_id":product_id,"sortid":$sortid.val(),"par_name":$par_name.val(),"par_value":$par_value.val()},function(data){
	        			if(data.id>0){
	        				$this.attr("title","删除");
	        				$this.removeClass("save");
	        				$this.html("<img src=\"<%=basePath%>/images/t03.png\" width=\"15\"/>&nbsp;");
	        				$this.attr("paramid",data.id);
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
        	$.post("<%=basePath%>product/queryParamList.json",{"product_id":product_id},function(data){
        		var _html;
	           for(var i=0;i<data.length;i++){
	           		var productParam = data[i];
	           		_html += "<tr>"+
	           				 "	<td><input value=\""+productParam.par_name+"\"/></td>"+
	           				 "	<td><input value=\""+productParam.par_value+"\"/></td>"+
	           				 "	<td><input value=\""+productParam.sortid+"\"/></td>"+
	           				 "	<td class=\"delete\"  title=\"删除\" paramid=\""+productParam.id+"\"><img src=\"<%=basePath%>/images/t03.png\" width=\"15\"/>&nbsp;</td>"+
	           				 "</tr>";
	           }
	           if(data.length==0){
	           	_html+="<tr id=\"nodata\"><td>没有任何的参数</td><td></td><td></td><td></td></tr>";
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