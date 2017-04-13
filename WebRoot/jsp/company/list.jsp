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
<title>公司列表</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/pagination.css" rel="stylesheet" type="text/css" />
<style type="text/css">
    tbody .no {width: 80px; text-align: center; text-indent: 0px;}
</style>
</head>

<body>
	<div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="<%=basePath%>jsp/firstpage.jsp">首页</a></li>
            <li><a href="javascript:;"></a></li>
            <li><a href="javascript:;">公司管理</a></li>
        </ul>
    </div>
    
    <div class="formbody">
        <div class="formtitle"><span>公司列表</span></div>
        <div class="rightinfo">
            <div class="tools">
                <ul class="toolbar">
                <%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_company_search")){%>
                    <li class='select'>
						<input type="text" class="dfinput"   id="gsmc" placeholder = "公司名称" title="公司名称" style="width:200px;"/>           
                    	<input type="text" class="dfinput"   id="fddbr" placeholder = "法定代表人" title="法定代表人" style="width:125px;"/>
                    	<input type="text" class="dfinput" placeholder="办公地址" title="办公地址" id="bgdz" style="width:200px;" />
					</li>
                    <li class="search"><span><img src="<%=basePath%>images/t06.png" /></span>查询</li>
                <%}%>
                <%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_user_add_spacial")){%>
					<li id="add_special" onclick="modifySpecialCode(1);"><span><img
							src="<%=basePath%>images/check_ok.png" /></span>启用内购</li>
					<%}%>
					<%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_user_remove_spacial")){%>
					<li id="remove_special"  onclick="modifySpecialCode(0);"><span><img
							src="<%=basePath%>images/check_no.png" /></span>禁用内购</li>
					<%}%>
                <%if(com.yunrer.common.AuthUtil.getAttribute(session, "b_company_export")){%>
                <li id="export"><span><img src="<%=basePath%>images/t09.png" /></span>导出</li>
                <%}%>
                </ul>
            </div>

            <table class="tablelist">
                <thead>
                    <tr>
                    	<th width="3%"><input type="checkbox" onclick="clickAll(this);" /></th>
                        <th width="20%">公司名称</th>
                        <th width="9%">统一社会信用代码</th>
                        <th width="17%">注册资金</th>
                        <th width="6%">法定代表人</th>
                        <th width="25%">办公地址</th>
                        <th width="6%">人数</th>
                        <th width="6%">内购状态</th>
                    </tr>
                </thead>
    			<tbody id="content"></tbody>
    			<script id="template" type="text/x-jquery-tmpl">
    			<tr>
					<td width="35"><input name="check" type="checkbox" value="${user_id}" id=${id} /><input name="status" type="hidden" value="${status}"></td>
						<td>${gsmc}</td>
						<td>${yyzzzch}</td>
						<td>${zczj}</td>
					<td>${fddbr}</td>
					<td>${bgdz}</td>					
					<td>{{if staffs_number ==null||staffs_number==0}}0{{else}}${staffs_number}{{/if}}</td>
					<td>{{if special_code ==1}}<span style="color:green">已开启</span>{{else}}未开启{{/if}}</td>

    			</tr>
    			</script>
            </table>

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
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/layer/layer.js"></script>
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
			
            //查询
            $(".search").click(function(){
	        	initData();
	        });

          	//导出
            $("#export").click(function(){
            	var gsmc = $("#gsmc").val();
        	var fddbr = $("#fddbr").val();
        	var bgdz = $("#bgdz").val();
            	window.open("<%=basePath%>user/exportCompany.json?gsmc="+gsmc+"&fddbr="+fddbr+"&bgdz="+bgdz);
	        });
        });
        
        function initData(_index){
        	if(!!_index)
        		pageIndex = index;
        	//显示加载层
        	layer.load();
        	var gsmc = $("#gsmc").val();
        	var fddbr = $("#fddbr").val();
        	var bgdz = $("#bgdz").val();
            $.post("<%=basePath%>user/querycompanyCount.json",{"gsmc":gsmc,"fddbr":fddbr,"bgdz":bgdz},function(data){
            	$("#total").html(data.count);
            	
            	if(data.count ==0){
            		$("#content").html("<td colspan=\"8\"><div  style=\"width:100%;text-align:center;margin:auto\">未找到检索数据~~</div></td>")
            		layer.closeAll();
            		
            	}
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
        	pageIndex = index;
        	$("#index").html(index+1);
        	var gsmc = $("#gsmc").val();
        	var fddbr = $("#fddbr").val();
        	var bgdz = $("#bgdz").val();
          	$.post("<%=basePath%>user/querycompanyList.json",{"gsmc":gsmc,"fddbr":fddbr,"bgdz":bgdz,"pageIndex":index,"pageSize":pageSize},function(data){
	            $("#content").html($("#template").tmpl(data.list));
	            //加载表格样式,公用
	            loadTableStyle();
	            //关闭加载层
	        	layer.closeAll('loading');
        	});
        }
               //全选
        function clickAll(e){	
			$("#content input[id]").each(function() {
					if (!!$(e).prop('checked'))
						$(this).prop('checked', 'checked');
					else
						$(this).removeAttr('checked');
				})

		}
		
		//回车搜索
		$(".dfinput").keypress(function(e){
			if(e.which == 13){
				initData(0);
			}
		})
		
				//启用内购方法
		function modifySpecialCode(special_code){
		
			var _index = layer.confirm("确认执行"+(special_code==1?"关闭":"启用")+"公司内购权限操作?",{btn:['确定','取消']},
			function(){
			
				layer.close(_index)
				
				var _inputlist = $("#content input[id]:checked");
				if(!!_inputlist==false || _inputlist.length==0){
						layer.alert("请至少选择一家公司进行操作");
						return ;			
				}
				postUser(_inputlist,0,special_code)
			},function(){layer.close(_index)})
			
		}
		
				//递归提交
		function postUser(datalist,index,special_code){
			if(datalist.length>index){
				var inputinfo = datalist.eq(index);
				var company = {};
				company.id = inputinfo.attr("id");
				company.special_code = special_code;
				$.post('/user/modifycompany.json',company,function(data){
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
		
    </script>
</body>
</html>