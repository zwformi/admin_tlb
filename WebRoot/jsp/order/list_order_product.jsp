<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>合同详情</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>js/validform/validform_ext.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/validform/validform_v5.3.2_min.js"></script>
<script src="<%=basePath%>js/validform/validform_ext.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<script src="<%=basePath%>js/stringmap.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<link href="<%=basePath%>js/uploader/webuploader_ext.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/uploader/webuploader.min.js"></script>
<script src="<%=basePath%>js/uploader/webuploader_order_save.js"></script>
<script src="<%=basePath%>js/laydate/laydate.js"></script>
<style type="text/css">
	body{min-width:400px;}
	#content input{padding:5px;}
	.checknull{border:1px solid #f0a66f;}
	#edit{float:right;position:absolute;top:20px;right:40px;}
	#edit_btn{width:60px;}
	input{ height:30px;margin:1px 0;width:398px;background:#F9F9F9;font-family:微软雅黑;color:#000;display:inline-block;}
	.editing{background:#fff;border:1px solid #d9d9d9;}
	.del_btn{cursor:pointer;text-decoration:underline;}
	select{width:400px !important;}
	dl{width:600px;}
	#erp_number input{height:34px;border:1px solid #a7b5bc;line-height:34px;top:-1px;}
	
</style>
</head>

<body>

    <div class="formbody">
    	<form id="mForm">
    		<ul class="forminfo">
   	 			<div id="edit" >
						<input type="button" id="edit_btn" class="btn" value="修改"/>
	</div>		
	            <li><label>合同编号：</label>
	                <label><dl id="order_number">--</dl></label>
	            </li>
	            <li><label>交付状态：</label>
	                <label><dl id="order_state" >--</dl></label>
	            </li>
				<div class="normal">
	            <li><label>配送方式：</label>
	                <label><dl id="shipping_methods">--</dl></label>
	            </li>
	            <li><label>安装服务：</label>
	                <label><dl id="installation_service">--</dl></label>
	            </li>
	            <li><label>ERP发货单号：</label>
	                <label><div id="erpGroup"><input id="erp_number" value="--" disabled="disabled"></dl></div>
	            </li>
	            <li><label>收货人姓名：</label>
	                <label><dl><input id="shr_xm" value="--" disabled="disabled" type="text" datatype="*2-6" nullmsg="请填写收货人姓名" ></dl></label>
	            </li>
	            <li><label>收货人电话：</label>
	                <label><dl><input id="shr_dh" value="--" disabled="disabled" type="text" datatype="*7-11" nullmsg="请填写收货人电话" ></dl></label>
	            </li>
	            <li><label>收货邮编：</label>
	                <label><dl><input id="shr_yb" value="--" disabled="disabled" type="text" datatype="p" nullmsg="请填写收货人邮编" ></dl></label>
	            </li>
	            <li><label>收货人地址：</label>
	                <label><dl><input id="shr_dz" value="--"  disabled="disabled"  datatype="*2-50" nullmsg="请填写收货人地址"></dl></label>
	            </li>
	            </div>
	           	<li><label>应收款状态：</label>
	                <label><dl><input id="yskzt" value="--" disabled="disabled"></dl></label>
	            </li>
	            <li><label>生效时间：</label>
	                <label><dl id="order_time" style="width:200px;">--</dl></label>
	            </li>
	            <li><label>合同来源：</label>
	                <label><dl id="order_source">--</dl></label>
	            </li>
	            <li><label>合同总额：</label>
	                <label><dl><span style="position:absolute;line-height:32px;">￥</span><input id="payment_money" style="width:389px;margin-left:10px;" value="--" disabled="disabled" type="text" datatype="*1-11" nullmsg="请填写合同总额"></dl></label>
	            </li>
	            <li><label>合同附件：</label>
	                <label><dl style="width:500px;"><span id="demand_file" style="float:left;">--</span>
    					<div class="upload-box upload-img" style="float:left;margin-left:10px;"></div>
    					</dl>
	                </label>
	            </li>
	            

	                <input id="order_id" value="--" type="hidden" value="">

	            <li><label>付款方式：</label>
	                <label><dl><input id="fkfs" value="--" disabled="disabled"></dl></label>
	            </li>
	            <li><label>合同签订日期：</label>
	                <label><dl><input id="signing_date" value="--" disabled="disabled"></dl></label>
	            </li>
	            <li><label>最终用户名称：</label>
	                <label><dl><input id="gsmc_final" value="--" disabled="disabled"></dl></label>
	            </li>
	            <li><label>合同类型：</label>
	                <label><dl id="order_type">--</dl></label>
	            </li>
	            <li><label>验收时间：</label>
	                <label><dl><input id="reception_date" value="--" disabled="disabled"></dl></label>
	            </li>
	            <li><label>回款状况：</label>
	                <label><dl id="rec_paycondition">--</dl></label>
	            </li>
	            <li><label>备注：</label>
	                <label><dl><input id="remark" value="--" disabled="disabled"></dl></label>
	            </li>
	            <div class="xuqiu">
	            <li><label>需求来源编号：</label>
	                <label><dl id="xuqiu_ordernumber">--</dl></label>
	            </li>
	            </div>
	            <div class="ms">
	            <li><label>秒杀人姓名：</label>
	                <label><dl id="ms_xm">--</dl></label>
	            </li>
	            <li><label>秒杀人电话：</label>
	                <label><dl id="ms_phone">--</dl></label>
	            </li>
	            </div>
	            
	            <li><label>合同进度：</label>
	            	<label><dl id="ht_jd" style="width:500px;">...</dl></label>
	            </li>
	            <input name="id" id="id" value="${param.id}" type="hidden"/>
	        </ul>
	        </form>
	        <div class="normal">
            <table class="tablelist">
                <thead>
                    <tr>
                    	<th width="230">商品名称</th>
                        <th width="60">类型</th>
                        <th width="80">品牌</th>
                        <th width="80">型号</th>
                        <th width="80">配置</th>
                        <th width="40">数量</th>
                        <th width="50">报价</th>
                        <th width="50">合同价</th>
                        <th width="80">小计</th>
                    </tr>
                </thead>
    			<tbody id="content">
    				
    			</tbody>
            </table>
    </div>
     <div class="yun">
            <table class="tablelist">
                <thead>
                    <tr>
                    	<th width="230">模块名称</th>
                        <th  width="60">类别</th>
                        <th width="200">目标路径</th>
                        <th  width="50">报价</th>
                        <th  width="50">合同价</th>
                        <th  width="50">开通状态</th>
                    </tr>
                </thead>
    			<tbody id="content">
    				
    			</tbody>
            </table>
    </div>


    <script type="text/javascript">
		var stateList = getStringmap('order_info','order_state');
    	var sourceList = getStringmap('order_info','order_source');
    	var typeList = getStringmap('order_info','order_type');
    	var rec_payConditionList = getStringmap('order_info','rec_payCondition');
    	var id = "${param.id}";
    	var order_source = "${param.order_source}";
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    	$(document).ready(function () {
	    	
	    	
	    	//初始化上传控件
	    	initData();
	    	$("#order_id").val(id);
	        $(".upload-img").InitUploader({ sendurl: "<%=basePath%>sys/uploadPic_orderfile_save.json", swf: "<%=basePath%>js/webuploader/uploader.swf" });
            
        });
        function isPositiveNum(s){//是否为正整数
			var re = /^[0-9]*[1-9][0-9]*$/ ;
			return re.test(s);
		} 
		
		$("#edit_btn").click(function(){
        	//切换编辑
        	
        	if($("#edit_btn").val()=="修改"){
        		
        		$("#edit_btn").val("完成");
        		$("input:disabled").addClass("editing").attr("disabled",false);
        		var order_type = $("#order_type").html();
        		var rec_paycondition = $("#rec_paycondition").html();
        		$("#reception_date").val($("#reception_date").val()=="暂无"?"":$("#reception_date").val());
				$("#signing_date").val($("#signing_date").val()=="暂无"?"":$("#signing_date").val());
        		$("#order_type").html("<select id=\"order_type\"  group=\"order_info\"><option value='-1'>请选择合同类型</select>");
        		$("#rec_paycondition").html("<select id=\"rec_paycondition\"  group=\"order_info\"><option value='-1'>请选择回款状况</select>");
        		$(".del_btn").show();
        		$("#order_id").val(id);
        		loadSelect(0);
        		$("#add_erp").parent().show();
        		$("#add_erp").click(function(){
        			var erp_ =  "<dl><input class=\"erp_number1 editing\" type=\"text\" datatype=\"/^\s*$/|/XSFH[0-9]{8}$/\" value=\"\" errormsg=\"请填写正确的ERP单号\" style=\"width:370px;\" /><a  class=\"del_erp\"  title=\"删除\" ><img src=\"<%=basePath%>/images/t03.png\" width=\"15\" style=\"padding-left:10px;\"/></a></dl>";
        			$("#erpGroup").append(erp_);
        			$(".del_erp").show().click(function(){
        			$(this).parent().remove();
        		});
        		});
        		$(".del_erp").show().click(function(){
        			$(this).parent().remove();
        		});
        		$("#rec_paycondition select option:contains('"+rec_paycondition+"')").attr("selected",true);
        		$("#order_type select option:contains('"+order_type+"')").attr("selected",true);
        		$(".upload-img").show();
        			$("a.del_btn").click(function(){
        				var smid = $(this).parent().attr("smid");
        				$this = $(this);
        				$.post("<%=basePath%>order/delSm.json",{"id":smid},function(data){
        					if(data.count==1){
        					layer.closeAll('loading');
        					layer.msg("删除成功！");
         					$this.parent().remove();
        				}else{
        					layer.closeAll('loading');
        					layer.msg("删除失败！");
        		}
	        	
    			});
        			
        			});
 
        	}else{
        		var erp_num="";
        		if($(".erp_number1")!=""){
        		$(".erp_number1").each(function(){
        			//处理erp_number
        			$this = $(this);
        			erp_num += $this.val()+",";
        			});
        			erp_num = erp_num.substring(0,erp_num.length-1);  
        			
        			
        			var postdata ="{" ;
        		//修改提交
        		}
        		//填充所有的input
        		$("input,select").each(function(){
        			$this = $(this);
        			postdata += "\""+$this.attr("id")+"\":\""+$this.val()+"\",";
        		
        		});
        		postdata = postdata+"erp_number:\""+erp_num+"\"}";
        		
          		postdata = eval('(' + postdata + ')');
				
				console.log(postdata);
        		delete postdata["edit_btn"];
        		
          		editSubmit(postdata);
	
        		
        		
        	}
        
        
        })
        
        function editSubmit(postdata){
        	//校验
        	
        	
        
        
        	if(checkValidform.check()){
        		layer.load();
        		$.post("<%=basePath%>order/update.json",postdata,function(data){
        		if(data.count==1){
        			layer.closeAll('loading');
        			layer.msg("修改成功！");
        			$("#edit_btn").val("修改");
        			$(".editing").attr("disabled",true).removeClass("editing");
        			$(".del_btn").hide();
        			$("#order_type").html("--");
        			$("#rec_paycondition").html("--");
        			$(".Validform_checktip").remove();
         			initData();
        		}else{
        			layer.closeAll('loading');
        			layer.msg("修改失败！");
        		}
	        	
    			});
        
        
        	
        	}else{
	        	return false;
        	}
        	
        }
      
        
        function initData(){
        	//显示加载层
        	layer.load();
        	pageSelectCallback();
        	
        		//初始化表单验证
			checkValidform = initValidform("mForm");

        }
        function pageSelectCallback() {
        	$.post("<%=basePath%>order/query.json",{"id":id,"order_source":order_source},function(data){
	        	var ORDER = data.ORDER;
	        	$("#order_number").html(ORDER.order_number);
	        	var state_=ORDER.order_state;
	        	var state_html =stateList[state_];
	        	
	        	
	        	

	        	//处理erp_number
	        	var erp_html_ = "";
	        	if(ORDER.erp_number!=null&&ORDER.erp_number!=""){
	        	var erp = ORDER.erp_number.split(",");
	        	erp_html_ += "<dl style=\"display:none;height:30px;line-height:30px;\"><a id=\"add_erp\" title=\"添加\" ><img src=\"<%=basePath%>/images/t01.png\" width=\"15\" style=\"padding:10px 0 0 10px;\"/></a></dl>"
	        
	        	for(var i=0;i<erp.length;i++){
	        		erp_html_+="<dl><input class=\"erp_number1\" type=\"text\" datatype=\"/^\s*$/|/XSFH[0-9]{8}$/\" value=\""+erp[i]+"\" errormsg=\"请填写正确的ERP单号\" disabled=\"disabled\" style=\"width:370px;\" /><a style=\"display:none\" class=\"del_erp\"  title=\"删除\" ><img src=\"<%=basePath%>/images/t03.png\" width=\"15\" style=\"padding-left:10px;\"/></a></dl>";
	        	}
	        	}
	        		$("#erpGroup").empty().append(erp_html_);
	        	$("#shipping_methods").html(getStringmap('order_info','shipping_methods')[ORDER.shipping_methods]);
	        	$("#installation_service").html(getStringmap('order_info','installation_service')[ORDER.installation_service]);	    
	        	$("#order_state").html(state_html);
	        	$("#order_time").html(ORDER.fmt_order_time);
	        	var source_=ORDER.order_source;
	        	var source_html =sourceList[source_];
	        	$("#order_id").val(ORDER.id);
	        	$("#order_source").html(source_html);
	        	$("#payment_money").val(ORDER.payment_money.toFixed(2));
	        	var file_html="--"
	        	if(ORDER.demand_file==""||ORDER.demand_file==null){
	        		file_html = "暂未上传";
	        		$(".upload-box").hide();
	        	}else{
	        		file_html = "<a href=\""+ORDER.demand_file+"\" target=\"_blank\" style=\"color:blue;\">查看合同附件</a>";
	        		$(".upload-box").hide();
	        	}
	        	$("#demand_file").html(file_html);
	        	$("#shr_dz").val(ORDER.shr_dz);
	        	$("#shr_yb").val(ORDER.shr_yb);
	        	$("#shr_xm").val(ORDER.shr_xm);
	        	$("#shr_dh").val(ORDER.shr_dh);
	        	$("#fkfs").val(ORDER.fkfs);
	        	$("#yskzt").val(ORDER.yskzt);
	        	$("#xuqiu_ordernumber").html(ORDER.xuqiu_ordernumber);
	        	
	        	$("#signing_date").val(ORDER.fmt_signing_date);
	        	$("#gsmc_final").val(ORDER.gsmc_final);
	        	var type_ = ORDER.order_type;
	        	var type_html = typeList[type_];
	        	
				$("#order_type").html(type_html);
				$("#reception_date").val(ORDER.fmt_reception_date);
				var rec_payCon_ = ORDER.rec_paycondition; 
				var rec_html = rec_payConditionList[rec_payCon_];
				$("#rec_paycondition").html(rec_html);
				
				$("#remark").val(ORDER.remark);
				
	        	$("#ms_xm").html(ORDER.ms_xm);
	        	$("#ms_phone").html(ORDER.ms_phone);
	        	if(ORDER.order_source!=1) {
	        		$("#ms_xm").parent().parent().remove();
	        		$("#ms_phone").parent().parent().remove();
	        		}
	        	
	        	var ORDERSMLIST = data.ORDERSMLIST;
	        	var html_jd="";
	        	for(var i=0;i<ORDERSMLIST.length;i++){
		           	var ORDERSM = ORDERSMLIST[i];
		           	html_jd+="<span smid="+ORDERSM.id+">"+ORDERSM.fmt_add_time+"-->【"+ORDERSM.type+"】"+ORDERSM.text_sm+"&nbsp;&nbsp;&nbsp;&nbsp;";
		       		
		       		if(ORDERSM.id!=null){
		       			html_jd+="<a class=\"del_btn\" href=\"javascript:void(0)\" >删除</a></span>";
		       		}
		       	 
		        }
		        $("#ht_jd").html(html_jd);
		        $(".del_btn").hide();
		        
		        //显隐控制
		        if(ORDER.xuqiu_ordernumber==""||ORDER.xuqiu_ordernumber==null){//需求单的情况
		        	$(".xuqiu").remove();
		        }
		        if(source_==4){	//云的情况
	        		$(".normal").remove();
	        		$(".yun").show();
	        		$(".ms").remove();
	        			//展示合同商品清单
	        		var MODULELIST = data.MODULELIST;
	        		var _html="";
	        		 if( MODULELIST.length==0){
		           			_html+="<tr id=\"nodata\"><td>合同内清单为空</td><td></td><td></td><td></td><td></td></tr>";
		          			}else{
		           for(var i=0;i<MODULELIST.length;i++){
		           		var MODULE = MODULELIST[i];
		           		console.log(MODULE);
		           		var dj = MODULE.module_price==0?"":(parseFloat(MODULE.module_price)).toFixed(2);
		           		var bj = MODULE.module_price_bj==0?"无":(parseFloat(MODULE.module_price_bj)).toFixed(2);
		           		var kt = MODULE.is_open==0?"已开通":"未开通";
		           		_html += "<tr>"+
		           				"	<td>"+MODULE.name+"</td>"+
		           				 "	<td>"+MODULE.type_name+"</td>"+
		           				 "	<td>"+MODULE.url+"</td>"+
		           				 "	<td >￥"+bj+"</td>"+
		           				 "	<td >￥"+dj+"</td>"+
		           				 "</tr>";
		           		
		           }
		           		
		           }
	        		$(".yun").find("#content").html(_html);
				}else{ 
				
				$(".yun").remove();
						//展示合同商品清单
	        		var ORDERDETAILSLIST = data.ORDERDETAILSLIST;
	        		var _html="";
		           for(var i=0;i<ORDERDETAILSLIST.length;i++){
		           		var ORDERDETAILS = ORDERDETAILSLIST[i];
		           		var xj = (parseFloat(ORDERDETAILS.product_unit_price)*Number(ORDERDETAILS.product_count)).toFixed(2);
		           		var dj = ORDERDETAILS.product_unit_price==0?"":(parseFloat(ORDERDETAILS.product_unit_price)).toFixed(2);
		           		var bj = ORDERDETAILS.product_unit_price_bj==0?"无":(parseFloat(ORDERDETAILS.product_unit_price_bj)).toFixed(2);
		           		_html += "<tr>"+
		           				"	<td>"+ORDERDETAILS.product_name+"</td>"+
		           				 "	<td>"+ORDERDETAILS.type_str+"</td>"+
		           				 "	<td>"+ORDERDETAILS.brand_str+"</td>"+
		           				 "	<td>"+ORDERDETAILS.xh+"</td>"+
		           				 "	<td>"+ORDERDETAILS.pz+"</td>"+
		           				 "	<td class=\"count\">"+ORDERDETAILS.product_count+"</td>"+
		           				 "	<td >￥"+bj+"</td>"+
		           				 "	<td >￥"+dj+"</td>"+
		           				 "	<td class=\"xiaoji\">￥"+xj+"</td>"+
		           				 "</tr>";
		           				 
		          		 if(ORDERDETAILSLIST.length==0){
		           			_html+="<tr id=\"nodata\"><td>合同内清单为空</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>";
		          			}
		          			$(".normal").find("#content").html(_html);
		           }
				
					if(source_!=1){	//秒杀的情况
						$(".normal").show();
	        			$(".yun").remove();
	        			$(".ms").remove();	
					}
				}
				
				
				
		        
		           
		           parent.layer.iframeAuto(index);
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