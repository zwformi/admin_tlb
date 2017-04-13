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
<title>模板管理</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/pagination.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/dblclick.js"></script> 
<style type="text/css">
	html,body{height:100%;}
    tbody .no {width: 80px; text-align: center; text-indent: 0px;}
    .tablelist td{text-align:center;text-indent:0px;}
    .tablelist td textarea{width:200px;height:50px;margin: 5px;padding:2px;border:1px solid #0f0f0f ;}
</style>
</head>

<body>
	<div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="<%=basePath%>jsp/firstpage.jsp">首页</a></li>
            <li><a href="javascript:;">图灵买商品</a></li>
            <li><a href="javascript:;">分类管理</a></li>
        </ul>
    </div>
    <form action="" id="template_form">
    <div class="formbody">
        <div class="formtitle"><span>模板管理</span></div>
        <div class="rightinfo">
            <div class="tools">
                <ul class="toolbar">
                	<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_product_type_search")){%>
	               		 <li class='select' style="margin-left: 10px;">
	               		 类目：
							<select id="parent_type_id" style="width:100px;" class="type_ids" name="parent_type_id">
								<option value='' selected>=全部分类=</option>
							</select>
							<select id="sub_type_id" style="width:100px;" class="type_ids" name="sub_type_id">
								<option value='' selected>=请选择=</option>
							</select>
							<select id="type_id" style="width:100px;" class="type_ids" name="category_id">
								<option value='' selected>=请选择=</option>
							</select>
						</li>
                    <li class='select' style="margin-left: 10px;">搜索：<input name="attrName" type="text" class="dfinput" /></li>
                    <li id="advanced"  style="margin-left: 10px;"><span></span>查询条件 ▼
                    <ul style="position:absolute;z-index:100;padding:5px;display:none;background:#fff;border:1px solid #dfdfdf;width:190px;height:170px;">
	                    <li class='select' style="clear:both;padding-left:5px;">&nbsp;&nbsp;&nbsp;是否必填：<select style="width:100px;"  name="isRequired"><option value="">==请选择==</option><option value="1">是</option><option value="0">否</option></select></li>
	                    <li class='select' style="clear:both;padding-left:5px;">&nbsp;&nbsp;&nbsp;是否重要：<select style="width:100px;"  name="isImportant"><option value="">==请选择==</option><option value="1">是</option><option value="0">否</option></select></li>
	                    <li class='select' style="clear:both;padding-left:5px;">&nbsp;&nbsp;&nbsp;销售属性：<select  style="width:100px;" name="isSukCandidate"><option value="">==请选择==</option><option value="1">是</option><option value="0">否</option></select></li>
	                    <li class='select' style="clear:both;padding-left:5px;">用户自定义：<select  style="width:100px;" name="isUserDefined"><option value="">==请选择==</option><option value="1">是</option><option value="0">否</option></select></li>
					</ul>
					</li>
                    <li class="search"><span><img src="<%=basePath%>images/t06.png" /></span>查询</li>
                    <li class="edit" id="edit" ><span><img src="<%=basePath%>images/check_ok.png" /></span>编辑</li>
                    <li class="save" id="save" style="display:none;"><span><img src="<%=basePath%>images/check_ok.png" /></span>保存</li>
                    <li class="reset" id="reset"  style="display:none!important;"><span><img src="<%=basePath%>images/t14.png" /></span>恢复到编辑前</li>
                    <%}%>

                </ul>
            </div>
			<div class="tablediv">
            <table class="tablelist">
                <thead>
				</div>
                    <tr>
                    	<th width="10%">政采云三级类目</th>
                        <th width="10%">组别</th>                       
                        <th width="10%">属性名</th>
                        <th width="20%">枚举值</th>
                        <th width="10%">字段类型</th>
                        <th width="10%">是否必填</th>
                        <th width="10%">是否重要</th>
                        <th width="10%">销售属性</th>
                        <th width="10%">用户自定义</th>
                    </tr>
                </thead>
    			<tbody id="content"></tbody>
    			<script id="template" type="text/x-jquery-tmpl">
    			<tr>
					<td>${category_id}</td>
    				<td>${group}</td>					
    				<td>${attrName}</td>
					<td><textarea readonly="readonly" origin_text="${attrVals}" id="${id}" >${attrVals}</textarea></td>
                    <td>${valueType}</td>
                    <td>{{if isRequired==1 }}是{{else isRequired!=1 }}否{{/if}}</td>
                    <td>{{if isImportant==1 }}是{{else isImportant!=1 }}否{{/if}}</td>
                    <td>{{if isSukCandidate==1 }}是{{else isSukCandidate!=1 }}否{{/if}}</td>
                    <td>{{if isUserDefined==1 }}是{{else isUserDefined!=1 }}否{{/if}}</td>

    			</tr>
    			</script>
            </table>
			</div>
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
    </form>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/layer/layer.js"></script>
	<script src="<%=basePath%>js/jquery.pagination.js"></script>
	<script src="<%=basePath%>js/jquery.tmpl.js"></script>
	<script src="<%=basePath%>js/common.js"></script>
    <script type="text/javascript">
        var pageIndex = 0;     //页面索引初始值
    	var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    	var type = "";
    	$(function() {
    		loadtype()
            initData(); 
            //查询
            $(".search").click(function(){
            	pageIndex=0;
	        	initData();
	        });
	        
        });
        
        function initData(){
        	$("#edit").show();
        	$("#reset").hide();
        	$("#save").hide();
        	//显示加载层
        	layer.load();
        	var _postdata = $('#template_form').serialize();
            $.post("<%=basePath%>product/queryTempForCount.json",_postdata,function(data){
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
	                callback: pageSelectCallback,
	                callback_before:checkBeforeFun
	            });
            });
        }
        
        function checkBeforeFun(index,jq){
        	var _flag = true;
        	
        	if(!checkChange()){
					if(!confirm("数据已修改，但未保存\n是否切换目录?")){
					_flag = false;
					}
        	}
        	return _flag;
        }
        
        
        function pageSelectCallback(index, jq) {

        	$("#edit").show();
        	$("#reset").hide();
        	$("#save").hide();
        	var _i = layer.load();
        	pageIndex=index;
        	$("#index").html(index+1);
			var _postdata = $('#template_form').serialize()+"&pageSize="+pageSize+"&pageIndex="+pageIndex;        	
        	$.post("<%=basePath%>/product/queryTempList.json",_postdata,function(data){
	            //关闭加载层
	        	layer.close(_i);
        		if(data.resultCode==1){
	            $("#content").html($("#template").tmpl(data.list));
	            //加载表格样式,公用
	            loadTableStyle();
        		}else
        		$("#content").empty();
        		
	        	dblClick('<%=basePath%>');
        	});
        }
        
      //加载类型--1级
    	function loadtype(){
    		$.ajax({
    	        type: "POST",
    	        url: "<%=basePath%>product_type/queryList.json",
    	        async: false, //设为false就是同步请求
    	        cache: false,
    	        success: function (data) {
    	        	types = data;
    	        	//清空下拉框
    	        	//3个下拉框，分别填充,先填充第一个,3个都必须填充
  					$("#parent_type_id").empty().append("<option value='' selected>=全部分类=</option>");
  					for(var i=0;i<data.length;i++){
  					if(types[i].product_level==1){
  						$("#parent_type_id").append("<option value='"+types[i].id+"'>"+types[i].name+"</option>");
  					}
    	        	}
    	        	
    	       
    	}
    	});
    	}
    	
    	  function loadType2(){
    		var parentid = $("#parent_type_id").val();
    		$("#sub_type_id").html("<option value='' selected>"+(parentid==""?"=全部分类=":"=请选择=")+"</option>");
  			$("#type_id").html("<option value='"+(parentid==""?"":-1)+"' selected>"+(parentid==""?"=全部分类=":"无数据")+"</option>");
  			//根据parent值加载
  			for(var i=0;i<types.length;i++){
  					if(types[i].product_level==2&&types[i].parentId==parentid){
  						$("#sub_type_id").append("<option value='"+types[i].id+"'>"+types[i].name+"</option>");
  					}		
    		}    		
    		}
    	
    	 function loadType3(){
    		var parentid = $("#parent_type_id").val();
    		var subid = $("#sub_type_id").val();
    		$("#type_id").empty();
  			//根据parent值加载
  			for(var i=0;i<types.length;i++){
  					if(types[i].product_level==3&&types[i].parentId==parentid&&types[i].sub_parentId==subid){
  						$("#type_id").append("<option value='"+types[i].id+"'>"+types[i].name+"</option>");
  					}		
    		}
     		if($("#type_id").html()==null || $("#type_id").html()=="")
    		$("#type_id").empty().append("<option value='-1' selected>=无数据=</option>"); 
    		}
    		

    			//第2步，在第一个选框值发生变化后
    	$("#parent_type_id").change(function(){
    		loadType2();
    		
    		
    		
    	});
    	
    	//第3步，在第二个选框值发生变化后
    	$("#sub_type_id").change(function(){
    		loadType3();
	    	initData();
    	});
    	
    	
    	var _type_id_val ;	
    	$("select#type_id").mouseenter(function(){
    		_type_id_val = $(this).val();
    		
    	})
    	
    	
    	//切换目录事件
    	$("#type_id").change(function(){
    		if(!checkChange()){    		
			layer.confirm("数据已修改，但未保存<br>是否切换目录?", {icon:3,shade:0.5},function(index){
			layer.close(index);
			pageIndex = 0;
			initData();
    		
			}, function(index){
			$("#type_id").val(Number(_type_id_val));
			layer.close(index);
			
			})
    		}else{
    		pageIndex = 0;
			initData();
    		}
    	});
    	
    	$("#edit").click(function(){
    		$('table.tablelist tr td textarea').css({'border':'1px green solid'}).removeAttr("readonly");
    		$(this).hide();
    		$("#reset").show();
    		$("#save").show();
    		layer.msg("已切换到编辑模式.",{icon:1});
    	
    	});
    	
    	$("#reset").click(function(){
    	layer.confirm('是否舍弃更改恢复到编辑前？', {icon:3,shade:0.5},function(i){
    	
    		$('table.tablelist tr td textarea').each(function(){
    			if($(this).attr("origin_text")!=$(this).val()){
    				$(this).val($(this).attr("origin_text"))
    			}
    		})
    		layer.msg("文本已重置.",{icon:1});
			layer.close(i)    	
    	
    	}, function(i){layer.close(i)})
    	});
    	
    	$("#save").click(function(){
	    	var postdata = [];
    		$('table.tablelist tr td textarea').each(function(){
    			var _origin = $(this).attr("origin_text");
    			var _text = $(this).val();
    			if(_origin!=_text){
    				var _str = strInput=_text.replace(/\r/ig,"").replace(/\n/ig,"").replace(/[ ]/g, ""); 
    				postdata.push({"attr_id":$(this).attr("id"),"attrVals":_str.trim()});		
    			}
    		
    		});
    			if(postdata.length==0)
    			{
    			layer.msg("保存成功！")
    			$('table.tablelist tr td textarea').each(function() {
    				$(this).css({'border':'1px #000 solid'});
    				$(this).attr("readonly","readonly")
    					
    			});
    			$(this).hide();
    			$("#edit").show();
    			$("#reset").hide();
    			return ;
    			}
    			var _i = layer.load();
    			toSave(postdata,0);
         			function toSave(list,_index){
    				console.log(_index)
    				if(_index<list.length){
    				var _data = list[_index];
    				$.post('<%=basePath%>product/updateAttrVal.json',_data,function(data){
    					if(data.resultCode==1){
    						var e =$("textarea#"+data.attr_id);
    						e.attr("origin_text", e.text()); 
    						toSave(list, ++_index);
    					}else{
    					layer.close(_i);
    					layer.msg("保存第"+(_index+1)+"项数据时出错");
    					}
    				
    				},'json')
    				
    				}else{
    				layer.close(_i);
    				$(this).hide();
    				$("#edit").show();
    				layer.msg("保存成功！");
    				initData();
    				}
    				
    			
    			}
    	});
    	$('#advanced').click(function(){
    		var _e = $(this).find("ul:first");
    		if(_e.css('display')=="none"){
    		$(this).find("ul:first").show("fast");
    		}else{
    		$(this).find("ul:first").hide("fast");
    		}
    		
    		
    	})
    	
		$("#advanced").find("ul").click(function(event){
		event.stopPropagation();
		})
    	
    	$("#advanced").mouseenter(function(){
		    	
    	$("body").unbind('click');
    	}).mouseleave(function(){
			$("body").on('click',function(){
    			$("#advanced").find("ul:first").hide("fast");
    		})    	
    	})
    	
    	$("#advanced ul").mouseenter(function(){
		    	
    	$("body").unbind('click');
    	}).mouseleave(function(){
			$("body").on('click',function(){
    			$("#advanced").find("ul:first").hide("fast");
    		})    	
    	})
    	
    	
    	function checkChange(){
    		var _flag = true;
    	    $('table.tablelist tr td textarea').each(function(){
    			if($(this).val()!=$(this).attr("origin_text")){
    				_flag=false;
    				return ;    			
    			}
    		})
    		return _flag;
    	}
    	
    	
    	
    	
    </script>
</body>
</html>