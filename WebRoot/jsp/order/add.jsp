<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String order_source = request.getParameter("order_source")==null?"":request.getParameter("order_source");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增合同</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<link href="<%=basePath%>js/validform/validform_ext.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/validform/validform_v5.3.2_min.js"></script>
<script src="<%=basePath%>js/validform/validform_ext.js"></script>
<link href="<%=basePath%>js/uploader/webuploader_ext.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/uploader/webuploader.min.js"></script>
<script src="<%=basePath%>js/uploader/webuploader_order.js"></script>
<script charset="utf-8" src="<%=basePath%>js/editor/kindeditor-min.js"></script>
<script src="<%=basePath %>js/stringmap.js"></script>
<script src="<%=basePath%>js/jquery.tmpl.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/laydate/laydate.js"></script>
	<script>
    $(function () {
    	//初始化上传控件
        $(".upload-img").InitUploader({ sendurl: "<%=basePath%>sys/uploadPic_orderfile.json", swf: "<%=basePath%>js/webuploader/uploader.swf" });
    });
</script>
<style type="text/css">
	body{min-width:800px;}
	.checknull{border:1px solid #f0a66f;}
	#erp_numGroup{margin-left:86px;}
	#erp_numGroup dl a{margin-left:10px;}
	#erp_numGroup dl{height:35px;}
	#erp_numGroup dl input{height:34px;border:1px solid #a7b5bc;line-height:34px;top:-1px;}
</style>
</head>

<body>
<form id="mForm" method="post">
	<input id="id" name="id" type="hidden"/>
	<input id="action" name="action" type="hidden"/>
	<div class="place">
    <span>位置：</span>
        <ul class="placeul">
            <li><a href="<%=basePath%>jsp/firstpage.jsp">首页</a></li>
            <li><a href="<%=basePath%>jsp/orderxuqiu/list.jsp">订单合同管理</a></li>
            <li><a href="javascript:;" class="location_add">新增订单合同</a></li>
        </ul>
    </div>
    <div class="formbody">
    <div class="formtitle"><span class="title_add">新增订单合同</span></div>
        <ul class="forminfo">
        	<%if(!"4".equals(order_source)){%>
            <li><label>收货人姓名：</label>
                <dl><input id="shr_xm" name="shr_xm" type="text" class="dfinput" datatype="*2-6" nullmsg="请填写收货人姓名" /> </dl>
            </li>
            <li><label>收货人电话：</label>
                <dl><input id="shr_dh" name="shr_dh" type="text" class="dfinput" datatype="*7-11" nullmsg="请填写收货人电话" /> </dl>
            </li>
             <li><label>收货邮编：</label>
                <dl><input id="shr_yb" name="shr_yb" type="text" class="dfinput" datatype="*1-20" nullmsg="请填写收货人邮编" /> </dl>
            </li>
            <li><label>收货地址：</label>
                <dl><input id="shr_dz" name="shr_dz" type="text" class="dfinput" datatype="*2-50" nullmsg="请填写收货地址" /></dl>
            </li>
             <li><label>ERP发货单号：</label>
             	<div id="erp_numGroup">
             	<dl><input class="erp_number1" type="text" datatype="/^\s*$/|/XSFH[0-9]{8}$/" errormsg="请填写正确的ERP单号" /> <a id="add_erp"  title="新增" ><img src="<%=basePath%>/images/t01.png" width="15" style="padding-top:10px;"/></a>
              	</dl>
             	</div>
                
            </li>
             <li><label>运送方式：</label>
                <dl><select id="shipping_methods" name="shipping_methods" group="order_info">
                </select> </dl>
            </li>
            <li><label>是否安装：</label>
                <dl><select id="installation_service" name="installation_service" group="order_info">
                </select> </dl>
            </li>
            <%}%>
             <li><label>合同签订日期:</label>
                <dl><input id="signing_date" name="signing_date" type="text" class="dfinput" /> </dl>
            </li>
             <li><label>最终用户名称：</label>
                <dl><input id="gsmc_final" name="gsmc_final" type="text" class="dfinput"  /> </dl>
            </li>
             <li><label>合同类型：</label>
               <dl><select id="order_type" name="order_type" group="order_info"/></select></dl>
            </li>
             <li><label>验收时间：</label>
                <dl><input id="reception_date" name="reception_date" type="text" class="dfinput"/> </dl>
            </li>
             <li><label>回款状况：</label>
                <dl><select id="rec_paycondition" name="rec_paycondition" group="order_info"/></select></dl>
            </li>
            <li><label>交付状态：</label>
                <dl><select id="order_state" name="order_state" group="order_info" >
                </select> </dl>
            </li>
            <li><label>合同附件：</label>
                <dl><input id="demand_file" name="demand_file" type="text" class="dfinput upload-path" readonly="readonly"/><div class="upload-box upload-img" style="top:-3px; left:-1px"></div></dl>
            </li> 
             <li><label>备注：</label>
                <dl><input id="remark" name="remark" type="text" class="dfinput" /> </dl>
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
                        <th>报价</th>
                        <th>合同价</th>
                        <th>小计</th>
                        <th width="36">操作</th>
                    </tr>
                </thead>
    			<tbody id="content">
    				
    			</tbody>
    			<tfoot>
    				<tr>
    					<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
    					<td id="add">
	    					<img src="<%=basePath%>images/t01.png" width="15"/>&nbsp;
	    				</td>
    				</tr>
    			</tfoot>
            </table>
             <ul class="forminfo">
             	<li style="text-align:right;">
             		<input id="xuqiu_ordernumber" name="xuqiu_ordernumber" value="" type="hidden"/>
             		<input id="user_id" name="user_id" value="" type="hidden"/>
      				<input id="order_source" name="order_source" type="hidden" value="" />
                    <input type="button" class="btn" id="scht" value="生成合同"/>
	            </li>
             </ul>
    </div>
</form>
<script type="text/javascript">
    	var id = "${param.id}";
    	var checkValidform;
    	$(document).ready(function () {
            initData();
   
           
            //初始化表单验证
			checkValidform = initValidform("mForm");
			loadSelect(0);
            //新增-界面
           $(document).on("click","#add",function(){
            	$("#nodata").remove();
            	var _html = "<tr class=\"products\">"+
            				 "	<td><input name=\"product_id\"style='display:none'value=0><input name=\"product_name\" class=\"dfinput\" style=\"width:80%;\" value=\"\"/></td>"+
	           				 "	<td><input name=\"type_str\" class=\"dfinput\" style=\"width:80%;\" value=\"\"/></td>"+
	           				 "	<td><input name=\"brand_str\" class=\"dfinput\" style=\"width:80%;\" value=\"\"/></td>"+
	           				 "	<td><input name=\"xh\" class=\"dfinput\" style=\"width:80%;\" value=\"\"/></td>"+
	           				 "	<td><input name=\"pz\" class=\"dfinput\" style=\"width:80%;\" value=\"\"/></td>"+
	           				 "	<td><input name=\"product_count\" class=\"dfinput number\" style=\"width:80%;\" value=\"\"/></td>"+
	           				 "	<td>--<input name=\"product_unit_price_bj\" type=\"hidden\" value=\"0\"/></td>"+
	           				 "	<td>￥<input name=\"product_unit_price\" class=\"dfinput xiaoshu\" style=\"width:60%;\" value=\"\"/></td>"+
	           				 "	<td  width=\"80\" class=\"xiaoji\">￥0.00</td>"+
	           				 "	<td class=\"delete\"  title=\"删除\" ><img src=\"<%=basePath%>/images/t03.png\" width=\"15\" style=\"padding-top:10px;\"/>&nbsp;</td>"+
	           				 "</tr>";
	           	$("#content").append(_html);
            });
            
            //删除
            $("#content").on("click",".delete",function(){
            	$(this).parent().remove();
            	
            });
			
			
			
			
			
			
			//erp新增
		   $(document).on("click","#add_erp",function(){
				var html_ = "<dl><input class=\"erp_number1\" type=\"text\" datatype=\"/XSFH[0-9]{8}$/\" errormsg=\"请填写正确的ERP单号\"/>"+
                "<a class=\"del_erp\"   title=\"删除\" ><img src=\"<%=basePath%>/images/t03.png\" width=\"15\" style=\"padding-top:10px;\"/></a></dl>";
				$("#erp_numGroup").append(html_);
			});
			
			 
            //erp删除
            $(document).on("click",".del_erp",function(){
            	$(this).parent().remove();
            	
            });
			
			
			//生成合同
           $("#scht").on("click",function(){
           		var erp = "";
	         	 //整理拼接erp
	           	$(".erp_number1").each(function(){
	           		if($(this).val!="")
	           			erp+=$(this).val()+",";
	           	});
	           	erp_number = erp.substring(0,erp.length-1);
	           	$("#mForm").append("<input id = \"erp_number\" name=\"erp_number\" type=\"hidden\" value="+erp_number+">");
	        
           		var flag= true;
           		if(checkValidform.check()){
	           		$("tr.products").each(function(){
	           			$tr=$(this);
	           			$tr.find("input").each(function(){
	           				if($(this).val()==""){
	           					$(this).addClass("checknull");
	           					flag=false;
	           				}else{
	           					if($(this).is(".number")){//校验整正数，数量
	           						if(!isPositiveNum($(this).val())){
	           							$(this).addClass("checknull");
	           							flag=false;
	           						}else{
	           							$(this).removeClass("checknull");
	           						}
	           					}else if($(this).is(".xiaoshu")){//校验有效数据，单价
	           						if(isNaN($(this).val())){
	           							$(this).addClass("checknull");
	           							flag=false;
	           						}else{
	           							$(this).removeClass("checknull");
	           						}
	           					}
	           				}
	           			});
	           		});
	           		if(flag){
	           			
	           			
	           			
	           			//执行生成合同
 	           			$("#mForm").attr("action","<%=basePath%>order/add.html");
 	           			$("#mForm").submit();
 	           		}
	           	}
        	});
        });
        
        function isPositiveNum(s){//是否为正整数
			var re = /^[0-9]*[1-9][0-9]*$/ ;
			return re.test(s);
		} 
        
        function initData(){
        	//显示加载层
        	layer.load();
        	//加载用户信息和商品清单信息
        	pageSelectCallback();
        	
        	
        }
        function pageSelectCallback() {
        	$.post("<%=basePath%>orderxuqiu/query.json",{"id":id},function(data){
        	var XUQIU = data.XUQIU;
        	$("#xuqiu_ordernumber").val(XUQIU.order_number);
        	$("#order_source").val(XUQIU.order_source);
        	$("#user_id").val(XUQIU.user_id);
        	if(XUQIU.dz!=null&&XUQIU.dz!=""){
        		
        		$("#shr_dz").val(XUQIU.dz);
	        	$("#shr_xm").val(XUQIU.xm);
	        	$("#shr_dh").val(XUQIU.dh);
	        	$("#shr_yb").val(XUQIU.yb);
        	}else if(data.USERADDRESS.address!=null&&data.USERADDRESS.address!=""){
        		console.log(XUQIU);
	        	$("#shr_dz").val(data.USERADDRESS.address);
	        	$("#shr_xm").val(data.USERADDRESS.consignee_name);
	        	$("#shr_dh").val(data.USERADDRESS.phone);
	        	$("#shr_yb").val(data.USERADDRESS.post_code);
        	}
        	var ORDERDETAILSXUQIULIST = data.ORDERDETAILSXUQIULIST;
        	var _html="";
	           for(var i=0;i<ORDERDETAILSXUQIULIST.length;i++){
	           		var ORDERDETAILSXUQIU = ORDERDETAILSXUQIULIST[i];
	           		var xj = (parseFloat(ORDERDETAILSXUQIU.product_unit_price_bj)*Number(ORDERDETAILSXUQIU.product_count)).toFixed(2);
	           		var dj = ORDERDETAILSXUQIU.product_unit_price_bj==0?"":(parseFloat(ORDERDETAILSXUQIU.product_unit_price_bj)).toFixed(2);
	           		_html += "<tr class=\"products\">"+
	           				"	<td><input name=\"product_id\"style='display:none'value=\""+ORDERDETAILSXUQIU.product_id+"\"><input name=\"product_name\" class=\"dfinput\" style=\"width:80%;\" value=\""+ORDERDETAILSXUQIU.product_name+"\"/></td>"+
	           				 "	<td><input name=\"type_str\" class=\"dfinput\" style=\"width:80%;\" value=\""+ORDERDETAILSXUQIU.type_str+"\"/></td>"+
	           				 "	<td><input name=\"brand_str\" class=\"dfinput\" style=\"width:80%;\" value=\""+ORDERDETAILSXUQIU.brand_str+"\"/></td>"+
	           				 "	<td><input name=\"xh\" class=\"dfinput\" style=\"width:80%;\" value=\""+ORDERDETAILSXUQIU.xh+"\"/></td>"+
	           				 "	<td><input name=\"pz\" class=\"dfinput\" style=\"width:80%;\" value=\""+ORDERDETAILSXUQIU.pz+"\"/></td>"+
	           				 "	<td><input name=\"product_count\" class=\"dfinput number\" style=\"width:80%;\" value=\""+ORDERDETAILSXUQIU.product_count+"\"/></td>"+
	           				 "	<td>￥"+dj+"<input name=\"product_unit_price_bj\" type=\"hidden\" value=\""+dj+"\"/></td>"+
	           				 "	<td>￥<input name=\"product_unit_price\" class=\"dfinput xiaoshu\" style=\"width:60%;\" value=\""+dj+"\"/></td>"+
	           				 "	<td  width=\"80\" class=\"xiaoji\">￥"+xj+"</td>"+
	           				 "	<td class=\"delete\"  title=\"删除\" ><img src=\"<%=basePath%>/images/t03.png\" width=\"15\" style=\"padding-top:10px;\"/>&nbsp;</td>"+
	           				 "</tr>";
	           }
	           if(ORDERDETAILSXUQIULIST.length==0){
	           	_html+="<tr id=\"nodata\"><td>本需求单还没有相关商品信息，无法生成合同</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>";
	           }
	           $("#content").html(_html);
	           
	            var order_source= "<%=order_source%>";
            //默认值设置
            if(XUQIU.order_source!=4)
            {
            
            	$("#shipping_methods").val("1");
            	$("#order_state").val("4");
            	$("#installation_service").val("0");
            	$("#order_state option[value='1']").remove();
            	$("#order_state option[value='2']").remove();
            
            }else{
            	$("#order_state").val("2");
            	$("#order_state option[value='4']").remove();
            	$("#order_state option[value='5']").remove();
            	
            }
	           
	            //关闭加载层

	        	layer.closeAll('loading');
        	});
        	
        }
        
           	!function() {
    			//绑定元素
    			laydate({
    				istime : true,
    				format : 'YYYY-MM-DD hh:mm:ss',
    				elem : '#reception_date'
    			});
    		}();
    		
    		!function() {
    			//绑定元素
    			laydate({
    				istime : true,
    				format : 'YYYY-MM-DD hh:mm:ss',
    				elem : '#signing_date'
    			});
    		}();
    </script>
</body>
</html>