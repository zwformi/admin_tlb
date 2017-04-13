<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="true"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员列表</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/pagination.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
tbody .no {
	width: 80px;
	text-align: center;
	text-indent: 0px;
}
</style>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="<%=basePath%>jsp/firstpage.jsp">首页</a></li>
			<li><a href="javascript:;">网站会员</a></li>
			<li><a href="javascript:;">会员管理</a></li>
		</ul>
	</div>

	<div class="formbody">
		<div class="formtitle">
			<span>会员列表</span>
		</div>
		<div class="rightinfo">
			<div class="tools">
				<ul class="toolbar">
					<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_user_detail")){%>
					<li id="detail"><span><img
							src="<%=basePath%>images/t10.png" /></span>详细资料</li>
					<%}%>
					<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_user_check_ok")){%>
					<li class="check_ok"><span><img
							src="<%=basePath%>images/check_ok.png" /></span>启用</li>
					<%}%>
					<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_user_check_no")){%>
					<li class="check_no"><span><img
							src="<%=basePath%>images/check_no.png" /></span>禁用</li>
					<%}%>
					<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_user_add_spacial")){%>
					<li id="add_special" onclick="modifySpecialCode(1);"><span><img
							src="<%=basePath%>images/check_ok.png" /></span>启用内购</li>
					<%}%>
					<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_user_remove_spacial")){%>
					<li id="remove_special"  onclick="modifySpecialCode(0);"><span><img
							src="<%=basePath%>images/check_no.png" /></span>禁用内购</li>
					<%}%>

					<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_user_allot")){%>
					<li id="allot"><span><img
							src="<%=basePath%>images/t11.png" /></span>分配客服</li>
					<%}%>
					<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_user_allot2")){%>
					<li id="allot2"><span><img
							src="<%=basePath%>images/t12.png" /></span>分配销售</li>
					<%}%>
					<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_user_search")){%>
					<li class='select' style="margin-left: 20px;"><input
						type="text" class="dfinput" /></li>
					<li class="search"><span><img
							src="<%=basePath%>images/t06.png" /></span>查询</li>
					<%}%>
					<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_user_export")){%>
					<li id="export"><span><img
							src="<%=basePath%>images/t09.png" /></span>导出</li>
					<%}%>
				</ul>
			</div>

			<table class="tablelist">
				<thead>
					<tr>
						<th width="3%"><input type="checkbox" onclick="clickAll(this);" /></th>
						<th width="8%">头像</th>
						<th width="10%">姓名</th>
						<th width="5%">性别</th>
						<th width="15%">电话</th>
						<th width="20%">公司名称</th>
						<th width="10%">状态</th>
						<th width="10%">内购状态</th>
						<th width="20%">注册时间</th>
					</tr>
				</thead>
				<tbody id="content"></tbody>
				<script id="template" type="text/x-jquery-tmpl">
    			<tr>
					<td width="35"><input name="check" type="checkbox" value="${user_id}" owning_company="${owning_company}" /><input name="status" type="hidden" value="${status}"></td>
					<td height="55px" align="center"><img src="http://www.tulingbuy.com${headimgurl_page}" height="40px" width="40px" style="border-radius:10px"/></td>
					<td>${xm}</td>
					<td>{{if gender == null}}-{{/if}}{{if gender!= null}}${genderTrans}{{/if}}</td>
					<td>${phone}</td>
    				<td>{{if gsmc == null}}未绑定公司{{else}}${gsmc}{{/if}}</td>
    				<td>
						{{if status == 0}}待绑定微信{{/if}}
						{{if status == 1}}<span style="color:green">正常</span>{{/if}}
						{{if status == 2}}<span style="color:red">禁用</span>{{/if}}
						
    				</td>
    				<td>
						{{if special_code == 0}}未开启{{/if}}
						{{if special_code == 1}}<span style="color:green">已开启</span>{{/if}}
						
    				</td>
					<td width="170px">${fmt_create_time}</td>
    			</tr>
    			</script>
			</table>

			<div class="pagin">
				<div class="message">
					共<i class="blue" id="total"></i>条记录，当前显示第&nbsp;<i class="blue"
						id="index"></i>&nbsp;页
				</div>
				<ul class="paginList">
					<li id="pagination" class="pagination"></li>
				</ul>
			</div>
		</div>

	</div>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/layer/layer.js"></script>
	<script src="<%=basePath%>js/stringmap.js"></script>
	<script>
	layer.config({
	    extend: '<%=basePath%>js/layer/extend/layer.ext.js'
	});
	</script>
	<script src="<%=basePath%>js/jquery.pagination.js"></script>
	<script src="<%=basePath%>js/jquery.tmpl.js"></script>
	<script src="<%=basePath%>js/common.js"></script>
	<script type="text/javascript">
        var pageIndex = 0;     //页面索引初始值
    	var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    	
    	$(document).ready(function () {
            initData();

            //会员详细信息
            $("#detail").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择待查看会员", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var id = $("input[name='check']:checked").val();
            		layer.open({
					    type: 2,
					    area: ['780px', '405px'],
					    fix: false, //不固定
					    maxmin: true,
					    content: "detail.jsp?action=edit&id="+id,
					    title:'会员详细资料'
					});
            	}else{
            		layer.alert("只能选择一条数据", {icon: 7});
            	}
            });

			//启用
            $(".check_ok").click(function(){
            	if($("input[name='check']:checked").length != 0){
            		layer.confirm('确认恢复该会员登录？', {icon: 3}, function(index){
            			var id="";
		            	var err_count = 0;
		            	var ids = [];
		            	$("input[name='check']:checked").each(function(){
		            		ids.push(this.value);
							if($(this).siblings().val() != 2){
								layer.close(index);
								layer.msg("操作失败！请选择已禁用数据",{icon: 2,time: 5000});
								err_count++;
							}
				        });
				    	
				    	if(err_count == 0){

				    		 updatedata(ids,0,1)
				    	
				        }
            		});
            	}else{
            		layer.alert("请选择数据", {icon: 7});
            	}
            });
            
            //禁用
            $(".check_no").click(function(){
            	if($("input[name='check']:checked").length != 0){
            		layer.confirm('确认禁止该会员登录？', {icon: 3}, function(index){
            			var id="";
		            	var err_count = 0;
		            	var ids = [];
		            	$("input[name='check']:checked").each(function(){
		            		ids.push(this.value);
							if($(this).siblings().val() == 2){
								layer.close(index);
								layer.msg("操作失败！请不要选择已禁用数据",{icon: 2,time: 5000});
								err_count++;
							}
				        });
				    	
				    	if(err_count == 0){
				    		 updatedata(ids,0,2)
				    	
				        }
            		});
            	}else{
            		layer.alert("请选择数据", {icon: 7});
            	}
            });
            


            //分配客服
            $("#allot").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择待分配会员", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var id = $("input[name='check']:checked").val();
            		layer.open({
					    type: 2,
					    area: ['780px', '405px'],
					    fix: false, //不固定
					    maxmin: true,
					    content: "../user_client/select_list.jsp?action=edit&id="+id,
					    title:'客服人员列表'
					});
            	}else{
            		layer.alert("只能选择一条数据", {icon: 7});
            	}
            });

            //分配销售
            $("#allot2").click(function(){
            	if($("input[name='check']:checked").length == 0){
            		layer.alert("请选择待分配会员", {icon: 7});
            	}else if($("input[name='check']:checked").length == 1){
            		var id = $("input[name='check']:checked").val();
            		layer.open({
					    type: 2,
					    area: ['780px', '405px'],
					    fix: false, //不固定
					    maxmin: true,
					    content: "../salesman/select_list.jsp?action=edit&id="+id,
					    title:'销售人员列表'
					});
            	}else{
            		layer.alert("只能选择一条数据", {icon: 7});
            	}
            });
			
            //查询
            $(".search").click(function(){
	        	initData(0);
	        });

          	//导出
            $("#export").click(function(){
            	var keyword = $(".dfinput").val();
            	window.open("<%=basePath%>user/export.json?keyword="+keyword);
	        });
        });
        
        function initData(index){
        	if(!!index)
        		pageIndex = index;
        	//显示加载层
        	layer.load();
        	var keyword = $(".dfinput").val();
            $.post("<%=basePath%>user/queryCount.json",{"keyword":keyword},function(data){
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
	                callback: pageSelectCallback
	            });
            });
        }
        
        function pageSelectCallback(index, jq) {
        	$("#index").html(index+1);
        	pageIndex = index;
        	var keyword = $(".dfinput").val();
        	$.post("<%=basePath%>user/queryList.json",{"keyword":keyword,"pageIndex":index,"pageSize":pageSize},function(data){
				  //需要转换的字段
	          	var genderList = getStringmap('user_info','gender');
	          	
	              for(var i =0;i<data.length;i++){
            		for(key in data[i]){
            		
            		 try {
      					  if (typeof(eval(eval(key+"List"))) == "object") {
           					 data[i][key+"Trans"] = eval(key+"List")[data[i][key]];
        				}
    				} catch(e) {
    					continue;
    				}

            		}
            	}
				
				
	            $("#content").html($("#template").tmpl(data));
	            //加载表格样式,公用
	            loadTableStyle();
	            //关闭加载层
	        	layer.closeAll('loading');
        	});
        }
        //全选
        function clickAll(e){	
			$("#content input[owning_company]").each(function() {
					if (!!$(e).prop('checked'))
						$(this).prop('checked', 'checked');
					else
						$(this).removeAttr('checked');
				})

		}
		//启用内购方法
		function modifySpecialCode(special_code){
		
			var _index = layer.confirm("确认执行"+(special_code==1?"禁用":"启用")+"内购用户操作?",{btn:['确定','取消']},
			function(){
			
				
				var _inputlist = $("#content input[owning_company]:checked");
				if(!!_inputlist==false || _inputlist.length==0){
						layer.alert("请至少选择一位用户进行操作");
						return ;			
				}
				var flag = true;
				for(key in _inputlist){
					if(key>0){
						if(_inputlist.eq(key).attr("owning_company")!=_inputlist.eq(key-1).attr("owning_company"))
							flag = false;
					}
				}
				
				if(flag){
					postUser(_inputlist,0,special_code)
				}else{
					layer.alert("只能批量操作同一家公司的员工..");
				}
			
				layer.close(_index)
			},function(){layer.close(_index)})
			
		}
		
		//监听回车
		$(".dfinput").keypress(function (e) { //这里给function一个事件参数命名为e，叫event也行，随意的，e就是IE窗口发生的事件。 
			var key = e.which; //e.which是按键的值 
			if (key == 13) {
				 
				initData(0);
			} 
			}); 
		
		//递归提交
		function postUser(datalist,index,special_code){
			if(datalist.length>index){
				var inputinfo = datalist.eq(index);
				var userinfo = {};
				console.log(inputinfo)
				userinfo.user_id = inputinfo.attr("value");
				userinfo.owning_company = inputinfo.attr("owning_company");
				userinfo.special_code = special_code;
				$.post('/user/operate_sepcial_code.json',userinfo,function(data){
					if(data.success){
						postUser(datalist,++index,special_code);
					}else{
						initData();
						layer.alert("执行第"+(++index)+"项操作时出现错误..<br>[原因]"+data.message);						
					}
					
				},'json');
			
			}
			 else{
			 initData();
			 layer.msg("操作执行成功！");	
			 }
			 
			
		}
		
		function updatedata(list, _index, status) {
		    if (list.length > _index) {
		         $.post("<%=basePath%>user/modify.json", {
		            "user_id": list[_index],
		            "typeid": status
		        },
		        function(data) {
		
		            if (data.success) updatedata(list, ++_index, status);
		            else {
		                layer.alert("操作出现错误..");
		                initData();
		            }
		
		        },
		        'json');
		    } else {
		        initData();
		    }
		}
	</script>
</body>
</html>