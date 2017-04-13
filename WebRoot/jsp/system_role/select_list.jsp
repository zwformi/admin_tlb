<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限配置</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<script src="<%=basePath%>js/ztree/jquery.ztree.core-3.5.min.js"></script>
<script src="<%=basePath%>js/ztree/jquery.ztree.excheck-3.5.min.js"></script>
<style type="text/css">
	body{min-width:400px;}
	dl,dt,dd,span{margin:0;padding:0;display:inline}
	ul.ztree {margin-top:10px;border:1px solid #d5d5d5;background:#f0f6e4;width:220px;height:260px;overflow-y:scroll;}
</style>
<script type="text/javascript">
	var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
	
	$(document).ready(function(){
		$.post("<%=basePath%>system_role/queryRoleNode.json",{"id":${param.id}},function(data){
			$.fn.zTree.init($("#nodeTree"), setting, data);
		});

        //保存
        $("#save").click(function(){
        	layer.confirm('确认更新权限配置 ？', {icon: 3}, function(index){
        		var treeObj=$.fn.zTree.getZTreeObj("nodeTree");
                var nodes=treeObj.getCheckedNodes(true);
                var node_id="";
                for(var i=0;i<nodes.length;i++){
                    if(node_id!=""){
                    	node_id+=",";
                    }
                    node_id+=nodes[i].id;
                }

                if(node_id != ""){
            		//显示加载层
                	layer.load();
                	
                    $.post("<%=basePath%>system_role/modifyRoleNode.json",{"role_id":${param.id},"node_id":node_id},function(data){
                    	//关闭加载层
        	        	layer.closeAll('loading');
                    	if(data.success){
            				layer.alert("保存成功！", {icon: 1}, function(index){
            				    layer.close(index);

                				//当你在iframe页面关闭自身时
                				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                				parent.layer.close(index); //再执行关闭 
            				});
            			}else{
            				layer.alert(data.message, {icon: 2});
            			}
    		        });
                }else{
                	layer.alert("请至少选择一条数据", {icon: 7});
                }
			});
        });
	});
</script>
</head>

<body>
    <div class="formbody">
        <ul class="forminfo">
            <li>
                <dl>
                	<ul id="nodeTree" class="ztree"></ul>
                </dl>
            </li>
            <li>
                <input id="save" type="button" class="btn" value="确认保存"/>
            </li>
        </ul>
    </div>
</body>
</html>