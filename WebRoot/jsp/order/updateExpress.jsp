<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>更新物流信息</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<link href="<%=basePath%>js/validform/validform_ext.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/validform/validform_v5.3.2_min.js"></script>
<script src="<%=basePath%>js/validform/validform_ext.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/laydate/laydate.js"></script>
<style type="text/css">
	body{min-width:400px;}
	#content input{padding:5px;width:87%;}
	.tablelist label{
	width:100px;
	}
		
</style>
</head>

<body>
    <div class="formbody">
    
    		
			<form id="mForm">
			
            <table class="tablelist" style="width:100%">
           <input id="id" name="id" type="hidden" value="<%=request.getParameter("id")%>"/> 

             <div class="express1">
    		
            			<tr>
				<td><label>类型：</label></td>
				<td><dl>
						<input id="type" name="type" type="text" class="dfinput"
							datatype="*2-100" nullmsg="请填写类型" />
					</dl></td>
					
				
			</tr>
						<tr>
				<td><label>情况说明：</label></td>
				<td><dl>
						<input id="text_sm" name="text_sm" type="text" class="dfinput"
							datatype="*2-100" nullmsg="请填写物流状况" />
					</dl></td>
					
				
			</tr>
				<tr>
				<td><label>操作时间：</label></td>
				<td><dl>
						<input id="optime" name="optime" type="text" class="dfinput" />
				</dl></td>
					
				
			</tr>
			
               </div>

            
            </table>
    
             <div style="text-align:center;"> <input type="button" id="save" class="btn"  value="保存"/></div>
    </form>
    </div>
	
<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
   !function() {
    			//绑定元素
    			laydate({
    				istime : true,
    				format : 'YYYY-MM-DD hh:mm:ss',
    				elem : '#optime'
    			});
    		}();
    var checkValidform ;
    $(document).ready(function () {

         //初始化表单验证
		 checkValidform = initValidform("mForm");
		//提交
	$("#save").click(function(){
	if(checkValidform.check()){
		var url = "<%=basePath%>order/addPost.json";
		
		var frm=$('#mForm').serialize();
		$.post(url,frm,function(data){
			if(data.count>0){
				layer.msg("保存成功",{time:1000},function(){
        					parent.layer.close(index);
        				});
				
			}else{
				layer.alert("保存失败！", {icon: 2});
			}
		});
	}
});


});

			
    </script>
</body>
</html>