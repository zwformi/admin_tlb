<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发货</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>js/validform/validform_ext.css"
	rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/stringmap.js"></script>
<script src="<%=basePath%>js/validform/validform_v5.3.2_min.js"></script>
<script src="<%=basePath%>js/validform/validform_ext.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/laydate/laydate.js"></script>
<script src="<%=basePath%>js/stringmap.js"></script>
<style type="text/css">
	body{min-width:400px;}
	#content input{padding:5px;width:87%;}
	.tablelist label{
	width:100px;
	}

	.hide{
		display:none;
	}	
</style>
</head>

<body>
    <div class="formbody">
    
    		
			<form id="mForm">
            <table class="tablelist" style="width:100%">
            <input id="id" name="id" type="hidden" value="<%=request.getParameter("id")%>"/> 
             <input id="post_company_str" name="post_company_str" type="hidden" value=""/> 
            <tr><td>配送方式:</td>
            <td style="width:70%">
           <input id="post1" type="radio" name="postType" value="1" checked="checked">快递</input>
           <input id="post2" type="radio" name="postType" value="2">配送</input>
           </td>
			</tr>
             
    		
            	<tr  class="send1">
				<td><label>快递公司：</label></td>
				<td><dl>
						<select id="post_company" name="post_company"></select>
					</dl></td>
					
				
			</tr>
				<tr  class="send1">
				<td><label>快递单号：</label></td>
				<td><dl>
						<input id="post_number" name="post_number" type="text" class="dfinput"  onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"
							datatype="*2-100" nullmsg="请填写快递单号" />
					</dl></td>
       
			 <tr class="send2" ><td width="30%">发货信息：</td>
			 <td  >
			 <textarea cols="3" rows="10" id="text_sm" name="text_sm" style="width:85%;border:1px solid #666">车牌号：&#xd联系人：&#xd手机：&#xd预计到货时间：</textarea>
           </td> 
           </tr>
           
          
            </table>
           <div style="text-align:center;"><input type="button" id="save" class="btn" value="保存"/></div>
             </form>
    </div>
	
 <script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引		
			//初始化表单验证
// 	var checkValidform ;
	
	!function() {
    			//绑定元素
    			laydate({
    				istime : true,
    				format : 'YYYY-MM-DD hh:mm:ss',
    				elem : '#optime'
    			});
    		}();		
			
$(document).ready(function () {

		$("tr.send2").hide();
	    $("input:radio[name='postType']").change(function (){
	       var val=$('input:radio[name="postType"]:checked').val();
 	    	if(val==1){
	    $("tr.send1").show();
		$("tr.send2").hide();
	    	}else if(val==2){
	    $("tr.send1").hide();
		$("tr.send2").show();
	    	}
	    
	    
           
		
 	});
	var companyList = getStringmap('order_info','post_company');
	
	
	$("#save").click(function(){
				//验证
	if($("input:radio[name='postType']:checked").val()==1){	//快递时验证快递单号是否正确	
		var postType=$("input:radio[name='postType']").val();
		var post_company=$("#post_company").val();
		var post_number=$("#post_number").val();
		if(post_company==0||post_company==""){layer.msg("请选择快递公司");
		}else{
//		$.post("<%=basePath%>postinfo/check.json",{"post_company":post_company,"post_number":post_number},function(data){
			
//			if(data.rescode==0){
//		         layer.msg(data.resMsg);
//		    }else{		    		
				$("#post_company_str").val(companyList[$("#post_company").val()]);
				var url = "<%=basePath%>order/send.json";
		
				var frm=$('#mForm').serialize();
				layer.confirm('确认要发货吗？<br/>如果是请点击“确定”，否则点击“取消”', {icon: 3}, function(index){
					$.post(url,frm,function(data){
						if(data.count>0){
							layer.msg("保存成功",{time:1000},function(){
							
        					parent.layer.closeAll();
        				});
						}else{
							layer.alert(data.message, {icon: 2});
						}
					});
				});
//			}
//		});
	}
	}else{
		var text_sm = $("text_sm").val();
		var url = "<%=basePath%>order/send.json";
		layer.confirm('确认要发货吗？<br/>如果是请点击“确定”，否则点击“取消”', {icon: 3}, function(index){
					var frm=$('#mForm').serialize();
					$.post(url,frm,function(data){
						if(data.count>0){
							layer.msg("保存成功",{time:1000},function(){
							
        					parent.layer.closeAll();
        				});
						}else{
							layer.alert(data.message, {icon: 2});
						}
					});
	});
	}
	});
			
           		
		
			
    	loadCompany();
    			//加载快递公司	
    	function loadCompany(){
    		
    	        	var postCompany = $("#post_company");
    	        	//清空下拉框
    	        	postCompany.empty();
    	        	//动态绑定子项
    	        	postCompany.append("<option value='0' selected>请选择所属快递</option>");
    	        	for(var i in companyList){
    			        postCompany.append("<option value='"+i+"'>"+companyList[i]+"</option>");
    			    }
    	       
    	    }
    	    });
    	    
    	
    </script>
</body>
</html>