<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>树形表</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<script src="<%=basePath%>js/ztree/jquery.ztree.core-3.5.min.js"></script>
<script src="<%=basePath%>js/ztree/jquery.ztree.exedit.min.js"></script>
<style type="text/css">
	body{min-width:400px;}
	dl,dt,dd,span{margin:0;padding:0;display:inline}
	ul.ztree {margin-top:10px;border:1px solid #d5d5d5;background:#f0f6e4;width:100%;height:500px;;max-height:530px;bottom:19px;overflow-y:scroll;}
	.left_tree{float:left;width:260px;}
	.right_form{border:1px solid #d5d5d5;height:570px;margin: 20px 5px auto 280px;}
	.toolbar{width:100%;}
</style>
<script type="text/javascript">
	var setting = {
		search :false,	//搜索标记
		view: {
		fontCss: setFontCss
	},
		edit: {
			
			drag: {
				isCopy: false,
				isMove: false
			},
		enable: true,
		renameTitle: "编辑类别",
		showRemoveBtn: setRemoveBtn,
		removeTitle: "删除类别",
		showRenameBtn: setRenameBtn
	},
		callback: {
 			beforeEditName: editNode,
			onClick: nodeOnClick,
 			beforeRemove: removeNodeBef,
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "parentId",
				rootPId: "-1"
			}
		},
	};
	
	function setRemoveBtn(treeId, treeNode) {
		return ("<%=(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_type_delete"))%>"&&treeNode.id!=0);
	}
	
	function setRenameBtn(treeId, treeNode) {
		return ("<%=(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_type_edit"))%>"&&treeNode.id!=0);
	}
	
	function nodeOnClick(event, treeId, treeNode){
		 $("#iframe-product").attr("src","<%=basePath%>jsp/product/list.jsp?type="+treeNode.id);	
		
};
	function setFontCss(treeId, treeNode) {	//查询样式设置
	return (treeNode.search == true) ? {color:"#09c"} : {color:""};
	}
	
	function initData(){
	
		$.ajax({  
         type : "post",  
          url : "<%=basePath%>product_type/queryForTree.json",  
          data : {id:"${param.id}"}, 
          async : false,  
          success : function(data){  
            var data_top = {name:"产品类型",id:"0",parentId:"0"};
			data.push(data_top);
			data1 = data;
			$.fn.zTree.init($("#nodeTree"), setting,data);
			var treeObj = $.fn.zTree.getZTreeObj("nodeTree");
			treeObj.expandNode(treeObj.getNodes()[0], true, false, true);
			 $("#iframe-product").contents().find(".tablelist").css("width","85%");		
			 return true;
          }  
     }); 
	
		
	}
	
	
	
	$(document).ready(function(){
		initData();
		
		 //新增
            $("#add").click(function(){
            	location.href = "<%=basePath%>jsp/product_type/add.jsp?location=tree";
            });
            
            //查询
            $(".search").click(function(){
				//查询节点并展开所有对应节点
	        	var search_word = $(".select").find("input").val();
	        	if(search_word!=""){
	        	
	        	var treeObj = $.fn.zTree.getZTreeObj("nodeTree");
	        	treeObj.destroy();	//重新加载列表
	        	initData();
	        	treeObj = $.fn.zTree.getZTreeObj("nodeTree");
				var nodes = treeObj.getNodesByParamFuzzy("name", search_word, treeObj.getNodes()[0]);
				//遍历获取所有的父节点并展开
				for(var i=0;i<nodes.length;i++){
					var parent = nodes[i].getParentNode();
					if(nodes[i].level==1){
						treeObj.expandNode(nodes[i], true, false, true);
					}else if(nodes[i].level==2){
						treeObj.expandNode(nodes[i], true, false, true);
						if(parent.open==false){
						treeObj.expandNode(parent, true, false, true);
						}
					}else if(nodes[i].level==3){
						if(parent.open==false){
						treeObj.expandNode(parent, true, false, true);
						}
						if(parent.getParentNode().open==false){
						treeObj.expandNode(parent.getParentNode(), true, false, true);
					}
					}
					nodes[i].search = true;            // 设置查询结果
					treeObj.updateNode(nodes[i]);              // 更新该节点  
					
					
				}
	        	
	        	
	        	}
	        	
				
				
	        });

	});
	
	 		//编辑
            function editNode(treeId, treeNode){
            	
            		location.href = "<%=basePath%>jsp/product_type/add.jsp?action=edit&id="+treeNode.id+"&location=tree";
            		return false;
           }
           
           //删除
            function removeNodeBef(treeId, treeNode){
            	var flag = false;
				var treeObj = $.fn.zTree.getZTreeObj("nodeTree");
            	layer.confirm('确认删除么？',{icon:3},function(index){
            		$.post("<%=basePath%>product_type/delete.json",{"id":treeNode.id},function(data){
				    		layer.close(index);

				    		if(data.sucess='success'){
				    			layer.msg("删除成功！");
				    			 treeObj.removeNode(treeNode,false);
				    			flag=true;
				    		}else{
				    			layer.msg("删除失败！");
				    			flag=false;
				    		}
				        });
            	
            	});
            	return flag; 	
            	};
            

</script>
</head>

<body>
	<div style="margin:10px;">
    <div class="left_tree">
        	 	<ul class="toolbar">
                 <%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_type_add")){%>
                	<li style="width:15%" id="add"><span><img src="<%=basePath%>images/t01.png" /></span></li>
                	<%}%>
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_type_search")){%>
                    <li class='select' style="width:52%"><input type="text"  class="dfinput" style="width:100%" /></li>
                    <li class="search" style="width:15%"><span><img src="<%=basePath%>images/t06.png" /></span></li>
                    <%}%>
        
                </ul>	


                <dl>
                	<ul id="nodeTree" class="ztree"></ul>
                </dl>


        </ul>
    </div>
    <div class="right_form">
	<iframe id="iframe-product" name="iframe-product" width="100%"  height="100%" src="<%=basePath%>jsp/product/list.jsp">
    </div>
    </div>
</body>
</html>