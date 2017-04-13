<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增商品</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>js/validform/validform_ext.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="<%=basePath%>js/editor/themes/default/default.css" />
<link href="<%=basePath%>js/uploader/webuploader_ext.css"
	rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<script src="<%=basePath%>js/jquery.pagination.js"></script>
<script src="<%=basePath%>js/jquery.tmpl.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/validform/validform_v5.3.2_min.js"></script>
<script src="<%=basePath%>js/validform/validform_ext.js"></script>
<script charset="utf-8" src="<%=basePath%>js/editor/kindeditor-min.js"></script>
<script charset="utf-8" src="<%=basePath%>js/editor/lang/zh_CN.js"></script>
<script src="<%=basePath%>js/uploader/webuploader.min.js"></script>
<script src="<%=basePath%>js/uploader/webuploader_ext.js"></script>
<script src="<%=basePath%>js/laydate/laydate.js"></script>
<script src="<%=basePath%>js/htmlConverter.js"></script>
<%-- <script src="<%=basePath %>js/addpage.js"></script> --%>
<style type="text/css">
.laydate-icon {
	width: 325px !important;
	height: 32px !important;
	border-top: solid 1px #a7b5bc !important;
	border-left: solid 1px #a7b5bc !important;
	border-right: solid 1px #ced9df !important;
	border-bottom: solid 1px #ced9df !important;
}

.type_ids{
	width:113px !important;

}
.select {
	height: 40px;
	line-height: 40px;
}

.select input {
	margin-left: 30px;
	margin-right: 5px;
}

#ms_div ,#special_product{
	display: none;
}

#ms_div2 {
	display: none;
}

.form_margin{
	line-height:50px;
	white-space:nowarp;
	word-break:keep-all;
	padding:50px;

}

.tablelist  tr td input{
	width:90%;
	height:90%;
	text-align:center;
	border:1px solid #3EAFE0;
}

.tablelist tbody tr td input:disabled{
	border:0 ;
	background-color:transparent;
}
.tablelist tr td select{
	width:90%;
	height:90%;
	text-align:center;
	border:1px solid #3EAFE0;
}
.tablelist tr td a{
	width:60px;
	height:30px;
	text-align:center;
}
.tablelist tr td a img{
 width:15px;
}
.tablelist td {
	height:35px;
    line-height: 35px;
    text-indent: 0px;
    border-right: dotted 1px #c7c7c7;
}
.search{
	background:#fff;
	width:491px;
	height:auto;
	position:absolute;
	display:none;
	border:1px solid #d9d9d9;
}
.search li:hover{
	background:#d5d5d5;
	cursor:pointer;

}
#xs-block{
	width:90%;
	 height:auto;
	  float:left; 
	  margin:10px 0;
	  display:inline;
	  }
.attrs{
	width:90%;
	 height:auto;
	  float:left; 
	  margin:10px 0;
	  display:inline;
	  background:#fff;
	  padding:20px 0;
	  }
.control-group{
	float:left;
	width:50%;
	display:inline;
	height:50px;
	line-height:50px;
	}

.group-content select{
	width:70%;
	display:inline;
	height:32px;
	margin:9px 10px 9px 0;
}
.group-content{
	width:48%;
	display:inline-block;
	margin-right:10px;
	height:50px;
	line-height:50px;

}

.input-text{
	width:70%; 
	height:32px;
		display:inline;
	 line-height:32px; 
	 border-top:solid 1px #a7b5bc; 
	 border-left:solid 1px #a7b5bc; 
	 border-right:solid 1px #ced9df; 
	 border-bottom:solid 1px #ced9df;
	  background:#fff; 
	  text-indent:10px;
		margin:9px 10px 9px 0;
	  }

.control-group .group-title{
	width:37%;
	float:left;
	display:inline;
	line-height:50px;
	margin:0 20px;
}
.required{
	color:red;
}
.attr_type{
	width:86px; height:100%; float:left; display:inline;margin-top:20px;
}
.locations{
    width: 113px !important;
}
</style>
</head>

<body>
	<hr>
		<form id="mForm">
			<input id="id" name="id" type="hidden" /> <input id="action"
				name="action" type="hidden" />
			<div class="place">
				<span>位置：</span>
				<ul class="placeul">
					<li><a href="<%=basePath%>jsp/firstpage.jsp">首页</a></li>
					<li><a href="javascript:;">图灵买商品</a></li>
					<li><a href="<%=basePath%>jsp/product/list.jsp">商品管理</a></li>
					<li><a href="javascript:;" class="location_add">新增商品</a></li>
				</ul>
			</div>

			<div class="formbody">
				<div class="formtitle">
					<span class="title_add">新增商品</span>
				</div>
				<ul class="forminfo">
			<table class="forminfo form_margin" width="100%">
	
			<tr>
				<td width="4%"><b class="required">*</b><label>所属类型：</label></td>
				<td width="46%">
				<dl>
					<select id="parent_type_id" class="type_ids" name="parent_type_id">
						<option value='0' selected>请选择所属类型</option>
					</select>
					<select id="sub_type_id" class="type_ids" name="sub_type_id">
						<option value='0' selected>请选择所属类型</option>
					</select>
					<select id="type_id" class="type_ids" name="type_id">
						<option value='0' selected>请选择所属类型</option>
					</select>
				</dl></td>
				
				<td width="4%"><b class="required">*</b><label>所属品牌：</label></td>
				<td width="46%">	
				<dl>
					<select id="brand_id" name="brand_id"></select>
				</dl></td>
	
			</tr>
			
			<tr>
				<td><b class="required">*</b><label>商品名称：</label></td>
				<td>
					<dl>
						<input id="name" name="name" type="text" class="dfinput"
							datatype="*2-100" nullmsg="请填写商品名称" />
				</dl></td>
				
				<td><b class="required">*</b><label>商品型号：</label></td>
				<td><dl>
						<input id="xh" name="xh" type="text" class="dfinput"
							datatype="*2-100" nullmsg="请填写商品型号" />
				</dl></td>
			</tr>
			
			<tr>
				<td><b class="required">*</b><label>商品配置：</label></td>
				<td><dl>
						<input id="pz" name="pz" type="text" class="dfinput"
							datatype="*2-100" nullmsg="请填写商品配置" />
					</dl></td>
					
				<td><label>商品副标题：</label></td>
				<td><dl>
						<input id="sub_title" name="sub_title" type="text" class="dfinput"
							datatype="*0-100" />
				</dl></td>
			</tr>
			<tr>



					
			<tr>		
				<td><b class="required">*</b><label>排序ID：</label></td>
				<td><dl>
						<input id="sortid" name="sortid" title="数字越大越靠前" type="text" class="dfinput"
							datatype="n1-6" nullmsg="请填写排序ID" />
				</dl></td>
				
				<td><b class="required">*</b><label>商品数量：</label></td>
				<td><dl>
						<input id="count" name="count" type="text" class="dfinput"
							datatype="n1-6" nullmsg="请填写商品数量" />
				</dl></td>
			</tr>
			
			<tr>
				<td><b class="required">*</b><label>商品市场价：</label></td>
				<td><dl>
						<input id="price_old" name="price_old" type="text" class="dfinput"
							datatype="n1-6" nullmsg="请填写市场价" />
				</dl></td>
				
				<td><b class="required">*</b><label>生产厂商：</label></td>
				<td><dl>
						<input id="firm" name="firm" type="text" class="dfinput"
							datatype="*1-100" nullmsg="请填写生产厂商" />
				</dl></td>
			</tr>
						<tr>
				<td><label>售后服务：</label></td>
				<td><dl>
						<input id="service" name="service" type="text" class="dfinput"
							datatype="*0-100" nullmsg="请填写售后服务" />
				</dl></td>
				
				<td><b class="required">*</b><label>商品销售价：</label></td>
				<td><dl>
						<input id="price_new" name="price_new" type="text" class="dfinput"
							datatype="n1-6" nullmsg="请填写销售价" />
				</dl></td>
			</tr>
			
							<tr>
				<td><label>商品图片：</label></td>
				<td ><dl>
						<label></label><a id="editimage" style="cursor:pointer; ">点击管理图片</a>
				</dl></td>
			</tr>	
			<tr>
				<td><label>微信是否可见：</label></td>
				<td><dl class="select">
						<input type="radio" name="is_wx" value="0" checked="checked" />不可见
						<input type="radio" name="is_wx" value="1" />可见
				</dl></td>
				
				<td><label>PC是否可见：</label></td>
				<td><dl class="select">
						<input type="radio" name="is_pc" value="0" />不可见
						<input type="radio" name="is_pc" value="1" checked="checked" />可见
				</dl></td>
			</tr>
				<tr>
				<td><label>是否需要安装：</label></td>
				<td><dl class="select">
						<input type="radio" name="need_install" value="0" checked="checked" />不需要
						<input type="radio" name="need_install" value="1" />需要
				</dl></td>

			</tr>

			<tr>
				<td><label>是否促销：</label></td>
				<td><dl class="select">
						<input type="radio" name="is_cx" value="0" checked="checked" />非促销
						<input type="radio" name="is_cx" value="1" />促销
				</dl></td>
			
				<td><label>是否秒杀：</label></td>
				<td><dl class="select">
						<input type="radio" name="is_ms" value="0" checked="checked" />否
						<input type="radio" name="is_ms" value="1" />是
				</dl></td>
			</tr>
				
			<tr id="ms_div">
				<td><label>秒杀开始时间：</label></td>
				<td><dl>
						<input id="ms_begintime" name="ms_begintime" type="text"
							class="dfinput" />
				</dl></td>
				
				<td><label>秒杀口令：</label></td>
				<td><dl>
						<input id="ms_kl" name="ms_kl" type="text" class="dfinput" />
				</dl></td>
			</tr>
				
			<tr>
				<td><label>是否热卖推荐：</label></td>
				<td><dl class="select">
						<input type="radio" name="is_red" value="0" checked="checked" />不推荐
						<input type="radio" name="is_red" value="1" />推荐
				</dl></td>
				<td><label>商品状态：</label></td>
				<td><dl class="select">
						<input type="radio" name="delete_flag" value="0" checked="checked" />上架
						<input type="radio" name="delete_flag" value="1" />下架
				</dl></td>
			</tr>
			<tr>
				<td><label>是否云产品：</label></td>
				<td><dl class="select">
						<input type="radio" name="is_yun" value="0" checked="checked" />否
						<input type="radio" name="is_yun" value="1" />是
				</dl></td>
			<td><label>是否内购：</label></td>
			<td><dl class="select">
						<input type="radio" name="special_code" value="0" checked="checked" />否
						<input type="radio" name="special_code" value="1" />是
				</dl></td>
			</tr>			
			<tr>
				<td><b class="required">*</b><label>境内或境外：</label></td>
				<td><dl class="select">
						<input type="radio" name="limit" value="0" checked="checked" />境内
						<input type="radio" name="limit" value="1" />境外
				</dl></td>
				<td><b class="required">*</b><label>产地：</label></td>
				<td width="46%">
				<dl>
					<select id="provinceId" class="locations" name="provinceId">
						<option value='0' selected>请选择所属省</option>
						<option value='310000'>上海市</option>
						<option value='410000'>河南省</option>
						<option value='440000'>广东省</option>
					</select>
					<select id="cityId" class="locations" name="cityId">
						<option value='0' selected>请选择所属市</option>
					</select>
					<select id="regionId" class="locations" name="regionId">
						<option value='0' selected>请选择所属区</option>
					</select>
					<select id="countryId" name="countryId">
					<option value='0'>请选择国家</option>
					<option value='228'>美国</option>
					
				</dl></td>

			</tr>
				
				</table>
					</table>
						<li id="special_product"><label>内购商品管理：</label>
					
						<dl>
							<table class="tablelist"
								style="clear:none;width:90%;text-align:center;valign:middle">
								<thead>
									<tr>
										<th>公司</th>
										<th>内购价</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="specail_products"></tbody>
								<script id="template1" type="text/x-jquery-tmpl">
    			<tr>
    					<td owning_company="@{owning_company}">@{gsmc}</td>
					<td><input type="text" disabled="disabled;" value="@{product_price}"/></td>
					<td val="@{id}" owning_company="@{owning_company}"> <a href="javascript:void(0)" onclick="editSpecial(this)" title="修改"><img src="<%=basePath%>images/t02.png" width="15"/></a> &nbsp;&nbsp; <a href="javascript:void(0)" onclick="delSpecial(this)" title="删除"><img src="<%=basePath%>images/t03.png" width="15"/></a></td>
    			</tr>
    			</script>
								<tfoot id="myfoot1">
									<tr>
										<td width="400">&nbsp;</td>
										<td width="200"></td>
										<td width="200"><a href="javascript:void(0)"
											onclick="addSpecial()"><img title="添加" 
												src="<%=basePath %>images/t01.png" width="15" /></a></td>
									</tr>
								</tfoot>

							</table>
						</dl></li>
					<li id="product_info"><label>配置信息：</label>
						<dl>
							<table class="tablelist"
								style="clear:none;width:90%;text-align:center;valign:middle">
								<thead>
									<tr>
										<th>商品版本</th>
										<th>商品颜色</th>
										<th>商品内存</th>
										<th>商品尺寸</th>
										<th>销售价格</th>
										<th>市场价格</th>
										<th>成本价格</th>
										<th>库存数量</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="product_items"></tbody>
								<script id="template" type="text/x-jquery-tmpl">
    			<tr>
					<td val="@{productVersions}">@{pVersions[productVersions-1]}</td>
    				<td val="@{productColor}">@{pColor[productColor-1]}</td>
    				<td val="@{productMemories}">@{pMemories[productMemories-1]}</td>
					<td val="@{productSize}">@{pSize[productSize-1]}</td>
					<td><input type="text" disabled="disabled;" value="@{productSalePrice}"/></td>
					<td><input type="text" disabled="disabled;" value="@{productMarketPrice}"/></td>
					<td><input type="text" disabled="disabled;" value="@{productCostPrice}"/></td>
					<td><input type="text" disabled="disabled;" value="@{stocks}"/></td>
					<td val="@{productItemsId}"> <a href="javascript:void(0)" onclick="editItem(this)" title="修改"><img src="<%=basePath%>images/t02.png" width="15"/></a> &nbsp;&nbsp; <a href="javascript:void(0)" onclick="delItem(this)" title="删除"><img src="<%=basePath%>images/t03.png" width="15"/></a></td>
    			</tr>
    			</script>
								<tfoot id="myfoot">
									<tr>
										<td width="200">&nbsp;</td>
										<td width="200"></td>
										<td width="200"></td>
										<td width="200"></td>
										<td width="200"></td>
										<td width="200"></td>
										<td width="200"></td>
										<td width="200"></td>
										<td width="200"><a href="javascript:void(0)"
											onclick="addItem()"><img title="添加" 
												src="<%=basePath %>images/t01.png" width="15" /></a></td>
									</tr>
								</tfoot>

							</table>
						</dl></li>
							</form>
							<form id="attrForm">	
						<li><div id ="attr-block">
						</div></li>
							<li>
						<div class="attr_type" >销售属性</div><div  id="xs-block"   >
					
						</div></li>
						</form>
					<li><label>商品详情：</label>
						<dl>
							<textarea id="content" name="content"></textarea>
						</dl></li>
						

					<li><input type="button" class="btn" value="确认保存" /></li>
				</ul>
			</div>
		<script>

		//初始化上传
		var editor;
	    var checkValidform;
	    var id = "${param.id}";
	    var action = "${param.action}";
	    var postdata ={};
	    var url = "stringmap.json";
	    var pColor=new Array();
	    var pSize=new Array();
	    var pMemories=new Array();
	    var pVersions=new Array();
		var html_version = '';
		var html_color = '';
		var html_size = '';
		var html_memory = '';
		var types;
		var skus ;
		//销售属性的数目
		var xssx_num = 0;
		//销售属性的group
		var xssx_group=new Array;
		
		$(function() {
		$("#countryId").hide();
		    	//第2步，在第一个选框值发生变化后
    	$("#provinceId").change(function(){

    		var province = $("#provinceId").val();
    		loadCity($("#provinceId").val());
    		loadRegion($("#cityId").val());
    	});
    	
    	//第3步，在第二个选框值发生变化后
    	$("#provinceId").change(function(){
    		loadCity($("#provinceId").val());
    	});
    	
    	$("#cityId").change(function(){
    		loadRegion($("#cityId").val());
    	});
		
// 		function loadRegion(){
//     		var provinceId= $("#provinceId").val();
//     		var cityId = $("#cityId").val();
//     		$("#regionId").empty().append("<option value='0' selected>请选择所属区域</option>");
  	//		根据parent值加载
//   			for(var i=0;i<types.length;i++){
//   					if(types[i].product_level==3&&types[i].parentId==parentid&&types[i].sub_parentId==subid){
//   						$("#regionId").append("<option value='"+types[i].id+"'>"+types[i].name+"</option>");
//   					}		
//     		}
//     		}
    		
    		function loadType2(){
    		var parentid = $("#parent_type_id").val();
    		$("#sub_type_id").empty().append("<option value='0' selected>请选择所属类型</option>");
  			$("#type_id").empty().append("<option value='0' selected>请选择所属类型</option>");
  			
  			//根据parent值加载
  			for(var i=0;i<types.length;i++){
  					if(types[i].product_level==2&&types[i].parentId==parentid){
  						$("#sub_type_id").append("<option value='"+types[i].id+"'>"+types[i].name+"</option>");
  					}		
    		}
    		}
    	
			    $("body").on("click", ".addChoice", function (){
    			$(this).parent().append("<div id=\"addGroup\"><input style=\"width:300px;\" class=\"new input-text\" type=\"text\" value=\"\"><a class=\"saveChoice\">保存</a><a class=\"resetChoice\">取消</a></div>");
    			$(this).remove();
    		});
    		
    		  $("body").on("click", ".saveChoice", function (){
    			var $this = $(this);
    			var group = $this.parent().parent().attr('id');
    			var val = $this.parent().find('.new').val();
    			$(this).parent().parent().append("<input class='xs_check' group='"+group+"' name='check' type='checkbox' id='"+val+"'>"+val+"</input>");
    			$this.parent().parent().append("<a class=\"addChoice\">增加</a>");
				$this.parent().remove();
						
    		});	
    		  $("body").on("click", ".resetChoice", function (){
    			var $this = $(this);
    			$this.parent().parent().append("<a class=\"addChoice\">增加</a>");
				$this.parent().remove();
						
    		});	
		
		$("body").on("click", "#suk_attrs input[type=checkbox]", function ()
            {	
            	makeSkuTable();
            });
		
			//初始化表单验证
			checkValidform = initValidform("mForm");
			//初始化上传控件
			$(".upload-img").InitUploader({
				sendurl : "<%=basePath%>sys/uploadPic_product.json", swf: "<%=basePath%>js/webuploader/uploader.swf" });
    	//初始化下拉框
    	postdata.objecttypename="Product_Items";
    	postdata.filedname="Product_Color";
    	loadData(url,"product_color",postdata);
    	postdata.filedname="Product_Memories";
    	loadData(url,"product_memory",postdata);
    	postdata.filedname="Product_Size";
    	loadData(url,"product_size",postdata);
    	postdata.filedname="Product_Versions";
    	loadData(url,"product_version",postdata);
    	loadbrand();
    	loadtype();
    	loadProductItem();
    	loadSpecialProducts();
    	$('#product_info').hide();
    	if(action == "edit"){
    		$("#id").val(id);
    		$("#action").val(action);
    		$(".location_add").html("编辑商品信息");
    		$(".title_add").html("编辑商品信息");
    		$.post("<%=basePath%>product/query.json",{"id":id},function(data){
    			$("#name").val(data.name);
    			$("#xh").val(data.xh);
    			$("#pz").val(data.pz);
    			$("#sub_title").val(data.sub_title);
    			$("#img_url").val(data.img_url);
    			$("#count").val(data.count);
    			$("#price_old").val(data.price_old);
    			$("#price_new").val(data.price_new);
    			$("#sortid").val(data.sortid);
    			$("#special_code").val(data.special_code);
    			$("#provinceId").val(data.provinceId);
    			loadCity(data.provinceId);
    			$("#cityId").val(data.cityId);
    			loadRegion(data.cityId);
    			$("#regionId").val(data.regionId);
    			$("#firm").val(data.firm);
    			$("#limit").val(data.limit);
    			$("#need_install").val(data.need_install);
    			$("#countryId").val(data.countryId);
    			//type_id进行特殊处理，拆分
    			var type_ids = data.type_ids.split(",");
    			$("#parent_type_id").val(type_ids[1]);
    			loadType2();
    			$("#sub_type_id").val(type_ids[2]);
    			loadType3();
    			$("#type_id").val(type_ids[3]);
    			loadAttrs(type_ids[3]);
    			$("#brand_id").val(data.brand_id);
    			$("input[name='is_cx'][value="+data.is_cx+"]").attr("checked",true); 
    			$("input[name='is_ms'][value="+data.is_ms+"]").attr("checked",true); 
    			$("input[name='is_wx'][value="+data.is_wx+"]").attr("checked",true); 
    			$("input[name='is_pc'][value="+data.is_pc+"]").attr("checked",true); 
    			$("input[name='is_red'][value="+data.is_red+"]").attr("checked",true); 
    			$("input[name='delete_flag'][value="+data.delete_flag+"]").attr("checked",true); 
    			$("input[name='is_yun'][value="+data.is_yun+"]").attr("checked",true); 
    			$("input[name='special_code'][value="+data.special_code+"]").attr("checked",true); 
    			$("input[name='limit'][value="+data.limit+"]").attr("checked",true); 
    			$("input[name='need_install'][value="+data.need_install+"]").attr("checked",true); 
    			$("#service").val(data.service);
			    			if(data.limit==1){
			       		$("#countryId").show();
			       		$("#provinceId").hide();
			       		$("#cityId").hide();
			       		$("#regionId").hide();
			       }else{
			       		$("#countryId").hide();
			       		$("#provinceId").show();
			       		$("#cityId").show();
			       		$("#regionId").show();
			       }
    			if(data.is_ms==1){
    				$("#ms_begintime").val(data.fmt_ms_begintime);
    				$("#ms_div").show();
    				$("#ms_kl").val(data.ms_kl);
    				$("#ms_div2").show();
    			}
    			if(data.special_code==1){
    				$("#special_product").show();		
    			}
    			editor.html(data.content);
    		});
    	}else{
    	$('#product_info').hide();
    	}
    	

    });
    
    
    //初始化编辑器样式
    KindEditor.ready(function(K) {
        editor = KindEditor.create('#content', {
            width: '90%',
            height: '400px',
            resizeType: 1,
            uploadJson: '<%=basePath%>sys/editorUpload.json',
            fileManagerJson: '<%=basePath%>sys/editorFileManage.json',
            allowFileManager: true
        });
	});
    

    
    //是否秒杀点击事件
    $(":radio[name='is_ms']").click(function(){
    	if($(this).val()==1){
       		$("#ms_div").show();
       		$("#ms_div2").show();
       }else{
       		$("#ms_div").hide();
       		$("#ms_div2").hide();
       }
    });
    
//         境内境外
    $(":radio[name='limit']").click(function(){
    	if($(this).val()==1){
       		$("#countryId").show();
       		$("#provinceId").hide();
       		$("#cityId").hide();
       		$("#regionId").hide();
       }else{
       		$("#countryId").hide();
       		$("#provinceId").show();
       		$("#cityId").show();
       		$("#regionId").show();
       }
    });

	    //是否内购点击事件
    $(":radio[name='special_code']").click(function(){
    	if($(this).val()==1){
       		$("#special_product").show();
       }else{
       		$("#special_product").hide();
       }
    });
	
    $(".btn").click(
    function(){
    	
    	doOnsubmit();

    	
    });
    

    
    //提交事件
    function doOnsubmit(){
			checkSku();
			//校验必填项
     		var arr = $('div[required] .group-content');
     		var flag = true;
			arr.each(function(){
						if(!!!$(this).find("*:first").val()){
							var _text = $(this).siblings('label').text().replace("：","").replace("*",""); 
							layer.alert(_text+" 为必填项");
							flag = false;
							return ;
						}
							
				});    			 

    		
    	if(checkValidform.check() && flag){
    		if($(":radio[name='is_ms']:checked ").val()==1){
    			if($("#ms_begintime").val()==""){
    				layer.alert("请设定秒杀开始时间！");
    				return;
    			}
    			if($("#ms_kl").val()==""){
    				layer.alert("请设定秒杀口令！");
    				return;
    			}
    		}
    		if($("#parent_type_id").val()==0||$("#sub_type_id").val()==0||$("#type_id").val()==0){
    				layer.alert("请选择所属类型！");
    				return;
    		}
    		if($("#brand_id").val()==0||$("#brand_id").val()==""){
    				layer.alert("请选择品牌！");
    				return;
    		}

    		var url = "<%=basePath%>product/add.json";
    		if($("#action").val() == "edit"){
    			url = "<%=basePath%>product/modify.json";
    		}
    		$("#content").val(editor.html());
    		var frm=$('#mForm').serialize();
    		var attrs=$('#attrForm').serialize();
    		$.post(url,frm,function(data){
    			//解绑点击事件
    			$(".btn").unbind();
    			if(data.count>0){
    				if(action!="edit"){
    					attrs+="&product_id="+data.id;
    					$.post("<%=basePath%>product/addAttrs.json",attrs,function(attrs){
    						$.post("<%=basePath%>productsku/add.json",{"skus":JSON.stringify(skus),"product_id":data.id},function(attrs){
    						
    						layer.confirm('保存成功！是否进入编辑页面？', {icon: 1}, function(index){
    						location.href="<%=basePath%>jsp/product/add.jsp?action=edit&id="+data.id;
							});
    					});
    						});
    				}

    				else{
    			
    					attrs+="&product_id="+id;
    					$.post("<%=basePath%>product/modifyAttrs.json",attrs,function(attrs){
    							$.post("<%=basePath%>productsku/update.json",{"skus":JSON.stringify(skus),"product_id":id},function(attrs){
    						layer.confirm('保存成功！是否返回产品列表？', {icon: 1}, function(index){
    					location.href="<%=basePath%>/jsp/product/list.jsp";
    					});
    				});
    					
    					});
    				
    				}
    				
    				if($("#action").val() != "edit"){
    					$('#mForm')[0].reset();
    					editor.html("");
    					$("#attr-block").empty().hide();
    				}
    			}else{
    				layer.alert(data.message, {icon: 2});
    			}
					$(".btn").click(
					    function(){
					    	$(this).unbind();
					    	doOnsubmit();
					    });
    		});
    	}
    	
		}
    
    
    //加载类型
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
  					$("#parent_type_id").empty().append("<option value='0' selected>请选择所属类型</option>");
  					for(var i=0;i<data.length;i++){
  					if(types[i].product_level==1){
  						$("#parent_type_id").append("<option value='"+types[i].id+"'>"+types[i].name+"</option>");
  					}
    	        	}
    	        	
    	       
    	}
    	});
    	}
    	function loadType3(){
    		var parentid = $("#parent_type_id").val();
    		var subid = $("#sub_type_id").val();
    		$("#type_id").empty().append("<option value='0' selected>请选择所属类型</option>");
  			//根据parent值加载
  			for(var i=0;i<types.length;i++){
  					if(types[i].product_level==3&&types[i].parentId==parentid&&types[i].sub_parentId==subid){
  						$("#type_id").append("<option value='"+types[i].id+"'>"+types[i].name+"</option>");
  					}		
    		}
    		}
    		
    		function loadType2(){
    		var parentid = $("#parent_type_id").val();
    		$("#sub_type_id").empty().append("<option value='0' selected>请选择所属类型</option>");
  			$("#type_id").empty().append("<option value='0' selected>请选择所属类型</option>");
  			
  			//根据parent值加载
  			for(var i=0;i<types.length;i++){
  					if(types[i].product_level==2&&types[i].parentId==parentid){
  						$("#sub_type_id").append("<option value='"+types[i].id+"'>"+types[i].name+"</option>");
  					}		
    		}
    		}
    	
    	//第2步，在第一个选框值发生变化后
    	$("#parent_type_id").change(function(){
    		loadType2();
    		
    		
    	});
    	
    	//第3步，在第二个选框值发生变化后
    	$("#sub_type_id").change(function(){
    		loadType3();
    	});
    	
    	$("#type_id").change(function(){
    		loadAttrs($("#type_id").val());


    	});
    	
    	//加载品牌	
    	function loadbrand(){
    		$.ajax({
    	        type: "POST",
    	        url: "<%=basePath%>product/brand.json",
    	        async: false, //设为false就是同步请求
    	        cache: false,
    	        success: function (data) {
    	        	var brand_id = $("#brand_id");
    	        	//清空下拉框
    	        	brand_id.empty();
    	        	//动态绑定子项
    	        	brand_id.append("<option value='0' selected>请选择所属品牌</option>");
    	        	for(var i=0;i<data.length;i++){
    			        brand_id.append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
    			    }
    	        }
    	    });
    	}
    	//加载配置、颜色、尺寸等信息
    	function loadData(url,element_id,postdata){
    		$.ajax({
    		type:"post",
    		url:"<%=basePath%>" + url,
    						data : postdata,
    						success : function(data) {
    							for (i in data) {
    								if(element_id=="product_color") {

    								pColor.push(data[i].name);
    								html_color +=  "<option value='"+data[i].value+"'>"
    												+ data[i].name + "</option>";
    								}
    								if(element_id=="product_memory"){
    								pMemories.push( data[i].name);

    								html_memory +=  "<option value='"+data[i].value+"'>"
    												+ data[i].name + "</option>";
    								}
    								if(element_id=="product_size") {
    								pSize.push(data[i].name);
    								html_size +=  "<option value='"+data[i].value+"'>"
    												+ data[i].name + "</option>";
    								}
    								if(element_id=="product_version") {
    								pVersions.push(data[i].name);
    								html_version +=  "<option value='"+data[i].value+"'>"
    												+ data[i].name + "</option>";
    								}	
    														
    							}
    						}
    					});
    		}
			//删除item
			function delItem(e){
				var target = $(e).parent("td");
				var item_id = target.attr("val");
				if(item_id!=null&&item_id!=""){
/* 					if(confirm("确认删除？"))
					doDel(); */
					//询问框
					layer.confirm('确定执行删除操作？', {
					  btn: ['确定','取消'] //按钮
					}, function(){
					  doDel();
					}, function(){
					  
					});
			}
					
				function doDel(){
						$.ajax({
						url:"<%=basePath%>productitem/delitem.json",
						type:"get",
						data:{"product_items_id":item_id},
						dataType:"json",
						success:function(data){
						if(data.res_code>0)
						layer.msg('删除成功', {icon: 1});
						loadProductItem();
						}
						});
				}
			
			}
			
			//修改item
			function editItem(e){
				var flag = true;
				var tagA = $(e);
				var target = $(e).parent("td");
				var item_id = target.attr("val");
				var inputlist = $(target).parent("tr").find("input");
				var postdata={};
				if(item_id!=null&&item_id!=""){
						if(tagA.attr("title")=="保存"){
						for( var i=0; i<inputlist.length;i++){
						    var num = inputlist.eq(i).val();
							if(i==3){
								if(!checkInput("number",num)){
								flag =false;
								break;
								}
							}else{
							if(!checkInput("decimal",num)){
								flag =false;
								break;
								}
							}
						} 
						
						if(flag){
						var salePrice =  inputlist.eq(0).val();
						var marketPrice =  inputlist.eq(1).val();
						var costPrice =  inputlist.eq(2).val();
						var stocks =  inputlist.eq(3).val();
						postdata.product_items_id=item_id;
						postdata.price_sale=salePrice;
						postdata.price_market=marketPrice;
						postdata.price_cost=costPrice;
						postdata.stocks = stocks;
						doEdit(postdata);
						}else{
						layer.msg('参数格式不正确！',{icon:2});
						loadProductItem();
						}

						}
						
					}	
					
					
				function doEdit(postdata){
						$.ajax({
						url:"<%=basePath%>productitem/update.json",
						type:"post",
						data:postdata,
						dataType:"json",
						success:function(data){
						if(data.res_code>0)
						layer.msg('修改成功', {icon: 1});
						else
						layer.msg('修改失败', {icon: 2});
						loadProductItem();
						},
						error:function(){
						alert("修改出现错误！");
						}
						});
				} 
				if(tagA.attr("title")=="保存"){
					for(i in inputlist)
					inputlist.eq(i).attr("disabled","disabled");
					tagA.attr("title","修改");
					tagA.find('img').attr("src","<%=basePath%>images/t02.png");
					}else{
					for(i in inputlist)
					inputlist.eq(i).removeAttr("disabled");
					tagA.attr("title","保存");
					tagA.find('img').attr("src","<%=basePath%>images/check_ok.png");
					}
			}
			
			//增加item
			function addItem(){
				var _html = 
				'<tr>/'+
					'<td><select>'+html_version+'</select></td>/'+
    				'<td><select>'+html_color+'</select></td>/'+
    				'<td><select>'+html_memory+'</select></td>/'+
					'<td><select>'+html_size+'</select></td>/'+
					'<td><input value=""></input></td>/'+
					'<td><input value=""></input></td>/'+
					'<td><input value=""></input></td>/'+
					'<td><input value=""></input></td>/'+
					'<td><a href="javascript:void(0)" onclick="onSubmit(this)" title="保存"><img src="<%=basePath%>images/check_ok.png" width="15" style="padding-top:10px;"/></a> &nbsp; &nbsp; <a href="javascript:void(0);" onclick="removeTr(this)"><img src="<%=basePath%>images/t03.png" width="15" title="取消" style="padding-top:10px;"/></a></td>/'+
    			'</tr>/';
				$("#myfoot").prepend(_html);
				  //加载表格样式,公用
    	          loadTableStyle();
			}
			//移除item
			function removeTr(e){
			var target = $(e).parent().parent();
			target.remove();
			}
			
			//提交item
			function onSubmit(e){
				var flag = true;
				var target = $(e).parent().parent();
				var version = target.find("select").eq(0).val();
				var color = target.find("select").eq(1).val();
				var memory = target.find("select").eq(2).val();
				var size = target.find("select").eq(3).val();
				var salePrice = target.find("input").eq(0).val();
				var marketPrice = target.find("input").eq(1).val();
				var costPrice = target.find("input").eq(2).val();
				var stocks = target.find("input").eq(3).val();
 				for( var i=0; i<target.find("input").length;i++){
				    var num = target.find("input").eq(i).val();
					if(i==3){
						if(!checkInput("number",num)){
						flag =false;
						break;
						}
					}else{
					if(!checkInput("decimal",num)){
						flag =false;
						break;
					}

					}
				} 
				if(flag){
				var postdata={};
				postdata.product_id=id;
				postdata.product_version=version;
				postdata.product_size=size;
				postdata.product_color=color;
				postdata.product_memory=memory;
				postdata.price_cost=costPrice;
				postdata.price_market=marketPrice;
				postdata.price_sale=salePrice;
				postdata.stocks=stocks;
				checkRepeat(postdata,target);
				}else{
				layer.msg('请检查输入', {icon: 2});
				}
			}
			//正则判断
			function checkInput(type,str){
				var number = /^[1-9]\d*$/;
				var decimal =/^\d+(\.\d+)?$/ ;
				var decimal2 = /^\d{0,8}\.{0,1}(\d{1,2})?$/;
				if(type=="number")
				return number.test(str);
				if(type=="decimal")
				return decimal.test(str);
				if(type=="decimal2")
				return decimal2.test(str);
			}
			//提交item
			function postItem(postdata){
				$.ajax({
				url:"<%=basePath%>productitem/add.json",
				type:"post",
				data:postdata,
				dataType:"json",
				success:function(data){
					if(data.res_code>0){
					layer.msg('添加成功', {icon: 1});
					loadProductItem();
					}
				}
				});
			}
			//item查重
			function checkRepeat(postdata,target){
				$.ajax({
				url:"<%=basePath%>productitem/checkproduct.json",
				type:"post",
				data:postdata,
				dataType:"json",
				success:function(data){
					if(data.res_code<0){
					postItem(postdata);
					target.remove();
					}else{
					layer.msg('提交的信息存在重复！',{icon:2});
					}
					
					}
				});
			}
			
			function loadCity(province){
				if(province=='0'){
				$("#cityId").empty().append("<option value='0' selected>请选择市</option>");
				}else if(province=='440000'){
    			$("#cityId").empty().append("<option value='0' selected>请选择市</option>").append("<option value='440300'>深圳市</option>");
    			}else if(province=='410000'){
    			$("#cityId").empty().append("<option value='0' selected>请选择市</option>").append("<option value='410100'>郑州市</option>");
    		}else if(province=='310000'){
    			$("#cityId").empty().append("<option value='0' selected>请选择市</option>").append("<option value='310100'>上海市</option>");
    		}
			}
			
			function loadRegion(city){
				if(city=='0'){
				$("#regionId").empty().append("<option value='0' selected>请选择区</option>");
				}else if(city=='440300'){
    			$("#regionId").empty().append("<option value='0' selected>请选择区</option>").append("<option value='440301'>市辖区</option>");
    			}else if(city=='410100'){
    			$("#regionId").empty().append("<option value='0' selected>请选择区</option>").append("<option value='0'>市辖区</option>");
    		}else if(city=='310100'){
    			$("#regionId").empty().append("<option value='0' selected>请选择区</option>").append("<option value='0'>黄浦区</option>").append("<option value='310103'>卢湾区</option>").append("<option value='310104'>徐汇区</option>");
    		}
			}
			
			//内购查重
			function checkRepeatSpecial(postdata,target){
				$.ajax({
				url:"<%=basePath%>product/special_products.json",
				type:"post",
				data:postdata,
				dataType:"json",
				success:function(data){
					if(data.length==0){
					postSpecial(postdata);
					target.remove();
					}else{
					layer.msg('该公司已添加！',{icon:2});
					}
					
					}
				});
			}
			
			//提交内购
			function postSpecial(postdata){
				$.ajax({
				url:"<%=basePath%>product/addSpecial.json",
				type:"post",
				data:postdata,
				dataType:"json",
				success:function(data){
					if(data.id>0){
					layer.msg('添加成功', {icon: 1});
					loadSpecialProducts();
					}
				}
				});
			}
			
			
    		//产品图片管理
            $("#editimage").click(function(){

            		layer.open({
					    type: 2,
					    area: ['900px', '500px'],
					    fix: false, //不固定
					    maxmin: true,
					    content: "list_image.jsp?product_id="+id+"&item_id=0",
					    title:'产品图片管理'
					});
            
            	
            });
    		//替换@为$,解决jstl与tmpl的冲突问题	
    		$.fn.tpl = function(data){
    	       $.template('template', $(this).html().replace(/@/g,"$"));
    	       return $.tmpl('template', data);
    	   }
    		//顺换填充配置列表信息
    		function loadProductItem(){
    			//清空数据
    			$("#product_items").html('');
    			if(id!=null&&id!="")
    			$.ajax({
    				url:"<%=basePath%>productitem/list.json",
    				type:"post",
    				data:{"product_id":id},
    				dataType:"json",
    				success:function(data){
    					//$("#product_items").html($("#template").tpl(data));
    					//加载表格样式,公用
    	            	loadTableStyle();
    				}
    			});
    		}
    		
    		function loadAttrs(cataId){
    		var html_ = "";
    			$.post('<%=basePath%>/product/query_attr/'+cataId+'.json',{},function(data){
				var list = data.list;
				if(list==null){
					$("#attr-block").hide();
				
				}else{
				
				$("#attr-block").show();
				//3组拼装
				var _jscs = "<div class=\"attr_type\" >技术参数：</div><div  class=\"attrs\" >";
				var _fwxx = "<div class=\"attr_type\" >服务信息：</div><div  class=\"attrs\" >";
				var _qt = "<div class=\"attr_type\" >其他：</div><div  class=\"attrs\" >";
				var js_i = 0;
				var fw_i = 0;
				var qt_i = 0;
				var _xs = "<div class=\"attr_type\" >销售信息：</div><div id=\"suk_attrs\" class=\"attrs select\" >";
				

				for(key in list){
					var _data = list[key];
					//分组
					if(_data.isSukCandidate==0){
					if(_data.group=="技术参数"){
						_jscs += convertData(_data);
						js_i = 1;
					}else if(_data.group=="服务信息"){
				 		_fwxx += convertData(_data);
						fw_i = 1;
					}else if(_data.group=="其他"){
						_qt += convertData(_data);
						qt_i = 1;
					}
					}else if(_data.isSukCandidate==1){
						xssx_group[xssx_num] = _data.attrName;
						xssx_num ++;
						_xs += "<div id=\""+_data.attrName+"\">"+_data.attrName+"：";
						
						var _xs_val = _data.attrVals.split(",");
						for(var i=0;i<_xs_val.length;i++){
							_xs += "<input class='xs_check' group='"+_data.attrName+"' name='check' type='checkbox' id='"+_xs_val[i]+"' >"+_xs_val[i]+"";
							
						}
						_xs+="<a class=\"addChoice\">增加</a>";
			
						_xs +="</div>";
						xs_i = 1;
					}
					}
					$("#attr-block").empty();
					if(js_i >0){
						$("#attr-block").append(_jscs+"</div>");
					}
					if(fw_i >0){
						$("#attr-block").append(_fwxx+"</div>");
					}
					if(qt_i >0){
						$("#attr-block").append(_qt+"</div>");
					}
					if(xs_i>0){
						$("#attr-block").append(_xs+"</div>");
					}
					makeSkuTable();		
				}
				initAttrData()
				if(action == "edit")
					initSkuData(id);
				},'json');	
    		}
    		
			
    		
    		    		//顺换填充配置列表信息
    		function loadSpecialProducts(){
    			//清空数据
    			$("#special_products").html('');
    			if(id!=null&&id!="")
    			$.ajax({
    				url:"<%=basePath%>product/special_products.json",
    				type:"post",
    				data:{"id":id},
    				dataType:"json",
    				success:function(data){
    					//$("#specail_products").html($("#template1").tpl(data));
    					//加载表格样式,公用
    	            	loadTableStyle();
    				}
    			});
    		}
    		
 			 //修改item
			function editSpecial(e){
				var flag = true;
				var tagA = $(e);
				var target = $(e).parent("td");
				var special_id = target.attr("val");
				var inputlist = $(target).parent("tr").find("input");
				var postdata={};
				if(special_id!=null&&special_id!=""){
						if(tagA.attr("title")=="保存"){
						for( var i=0; i<inputlist.length;i++){
						    var num = inputlist.eq(i).val();
							if(i==1){
								if(!checkInput("number",num)){
								flag =false;
								break;
								}
							}else{
							if(!checkInput("decimal",num)){
								flag =false;
								break;
								}
							}
						} 
						
						if(flag){
						var salePrice =  inputlist.eq(0).val();
						postdata.product_price=salePrice;
						postdata.special_id=special_id;
						postdata.product_id = id;
						doEdit(postdata);
						}else{
						layer.msg('参数格式不正确！',{icon:2});
						loadProductItem();
						}

						}
						
					}	
					
					function doEdit(postdata){
						$.ajax({
						url:"<%=basePath%>product/edit_special_products.json",
						type:"post",
						data:postdata,
						dataType:"json",
						success:function(data){
						if(data>0)
						layer.msg('修改成功', {icon: 1});
						else
						layer.msg('修改失败', {icon: 2});
						loadSpecialProducts();
						},
						error:function(){
						alert("修改出现错误！");
						}
						});
				} 
				if(tagA.attr("title")=="保存"){
					for(i in inputlist)
					inputlist.eq(i).attr("disabled","disabled");
					tagA.attr("title","修改");
					tagA.find('img').attr("src","<%=basePath%>images/t02.png");
					}else{
					for(i in inputlist)
					inputlist.eq(i).removeAttr("disabled");
					tagA.attr("title","保存");
					tagA.find('img').attr("src","<%=basePath%>images/check_ok.png");
					}
			}
    		
    		//删除item
			function delSpecial(e){
				var target = $(e).parent("td");
				var special_id = target.attr("val");
				if(special_id!=null&&special_id!=""){
					//询问框
					layer.confirm('确定执行删除操作？', {
					  btn: ['确定','取消'] //按钮
					}, function(){
					  doDel();
					}, function(){
					  
					});
			}
					
				function doDel(){
						$.ajax({
						url:"<%=basePath%>product/del_special_product.json",
						type:"POST",
						data:{"special_id":special_id},
						dataType:"json",
						success:function(data){
						if(data>0){
						layer.msg('删除成功', {icon: 1});
						loadSpecialProducts();
						}else{
						layer.msg('删除失败',{icon:3});
						}
						}
						});
				}
			
			}
			
    		
    		//加载公司，组成下拉表
    		function loadCompany(){
    			$.ajax({
    				url:"<%=basePath%>user/querySpecialCompany.json",
    				type:"get",
    				
						data:{"keyword":keyword},
						dataType:"json",
						success:function(data){
						if(data!=null){
							
						}
						
    			}
    			
    			});
    			}
    		
    		//增加special
			function addSpecial(){
				var _html = 
				'<tr owning_company=0>/'+
					'<td ><input owning_company=0 value="" oninput="changeCompany(this)" onclick="newSpecial(this)" onblur="closeSearch(this)"></input><div class="search"></div></td>/'+
					'<td><input value=""></input></td>/'+
					'<td><a href="javascript:void(0)" onclick="onSubmitSpecial(this)" title="保存"><img src="<%=basePath%>images/check_ok.png" width="15" style="padding-top:10px;"/></a> &nbsp; &nbsp; <a href="javascript:void(0);" onclick="removeTr(this)"><img src="<%=basePath%>images/t03.png" width="15" title="取消" style="padding-top:10px;"/></a></td>/'+
    			'</tr>/';
				$("#myfoot1").prepend(_html);
				  //加载表格样式,公用
    	          loadTableStyle();
			}
			
			function changeCompany(e){
				$this = $(e);
				var company = $this.val();
				var search = $(e).siblings('.search');
				if(company!=""){
				//根据公司value值加载
				$.ajax({
    				url:"<%=basePath%>user/querySpecialCompany.json",
    				type:"POST",
						data:{"keyword":company},
						dataType:"json",
						success:function(data){
						if(data!=null&&data!=""){
							search.empty().append('<ul></ul>');
							for(var i=0;i<data.length;i++){
								var _html ='<li id="'+data[i].id+'"><a href="#">'+data[i].gsmc+'</li>';
								search.append(_html);
								  $('<li>').addClass('aaa').html(data[i].gsmc).appendTo(search.find('ul'));
												
								}
							 $(document).on('click','div[class=search]',function(e){
							 

										});
						
						}else{
							search.empty().append("请更换关键字查询");
						}
						
    			}
    			
    			});
    			
				}
				
							function inputCompany(e){
				$this = $(e);
				$(e).siblings('input').val($this.html());
			}
				}
				
						//提交item
			function onSubmitSpecial(e){
				var flag = true;
				var target = $(e).parent().parent();
				var company = target.find("input").eq(0).val();
				var price = target.find("input").eq(1).val();
				var owning_company = target.attr("owning_company");
				if(checkInput("decimal",price)){
				var postdata={};
				postdata.id=id;
				postdata.owning_company=owning_company;
				postdata.company=company;
				postdata.product_price=price;
				checkRepeatSpecial(postdata,target);
				}else{
				layer.msg('请检查输入', {icon: 2});
				}
			}
			
			
			function newSpecial(e){
				$this = $(e);
				var company = $this.val();
				var search = $(e).siblings('.search');
				search.show();
				if(company!=""){
		
				//根据公司value值加载
				$.ajax({
    				url:"<%=basePath%>user/querySpecialCompany.json",
    				type:"POST",
						data:{"keyword":company},
						dataType:"json",
						success:function(data){
						if(data!=null){
							search.empty().append('<ul></ul>');
							for(var i=0;i<data.length;i++){
							
							search.find('ul').append('<li class="check" id="'+data[i].id+'"><a href="#">'+data[i].gsmc+'</li>');
							   $('<li>').addClass('aaa').html(data[i].gsmc).appendTo(search.find('ul'));
							
							}
						
						
						}else{
							search.empty().append("请更换关键字查询");
						}
						
    			}
    			
    			});
    		$(document).on('click','li.aaa',function(e){


										});
    			}else{
    				search.empty().append('请输入要添加的公司');
    			}
				
				
			function inputCompany(e){
				$this = $(e);
				$(e).siblings('input').val($this.html());
			}
			}
			
			function closeSearch(e){
				$(e).siblings('.search').hide();
			}
			
			
			//移除item
			function removeTr(e){
			var target = $(e).parent().parent();
			target.remove();
			}
			
			function setSkuTable(data){
				var row="";
			var t = "";
				var th="";	
				//拼装th
					if(data[0].attrName1!=null){
						th += "<th>"+data[0].attrName1+"</th>";
					}
					if(data[0].attrName2!=null){
						th += "<th>"+data[0].attrName2+"</th>";
					}
					if(data[0].attrName3!=null){
						th += "<th>"+data[0].attrName3+"</th>";
					}
					if(data[0].attrName4!=null){
						th += "<th>"+data[0].attrName4+"</th>";
					}
					th += '<th>市场价</th><th>销售价</th><th>平台自营价</th><th>库存</th>';
		
				for(var i=0;i<data.length;i++){
						var row= "";
					if(data[i].attrName1!=null){
						row += '<td group="'+data[i].attrName1+'">'+data[i].attrVal1+'</td>'
					}
					if(data[i].attrName2!=null){
						row += '<td group="'+data[i].attrName2+'">'+data[i].attrVal2+'</td>'
					}
					if(data[i].attrName3!=null){
						row += '<td group="'+data[i].attrName3+'">'+data[i].attrVal3+'</td>'
					}
					if(data[i].attrName4!=null){
						row += '<td group="'+data[i].attrName4+'">'+data[i].attrVal4+'</td>'
					}

	
					 row = '<tr id='+data[i].id+'>' + row + '<td group="originPrice"><input type="text" value='+data[i].originPrice+'></td><td group="price"><input type="text" value='+data[i].price+'></td><td group="platformPrice"><input type="text" value='+data[i].platformPrice+'></td><td group="stocks"><input type="text" value='+data[i].stocks+'></td></tr>';
					t+=row;
				}
					 $("#xs-block").empty().html('<table class="tablelist" style="clear:none;text-align:center;valign:middle"><thead>'+th +'</thead><tbody>'+ t + '</tbody></table>');
				}
					
			
			
			
    	function makeSkuTable(){
    			
                var t = "";
                var x = 0, y = 1;
                var th="";
                $("#suk_attrs>div").each(function (i, d)
                {
                    var i = $(d).find("input[type=checkbox]:checked").length;
                    if(i>0)
                    {
                        $(d).attr("checkedItem",true);
                        x++;
                        y = y * i;
                        th += '<th>'+$(d).attr('id')+'</th>';
                    }else{
                    	$(d).attr("checkedItem",false);
                    }
                });
                	th += '<th>市场价</th><th>销售价</th><th>平台自营价</th><th>库存</th>';
                for (i = 0; i < y; i++)
                {
                	var nextCheckedCount = 1;
                    var tds = "";
                    for (j = x-1; j >=0; j--)
                    {
                        var currentGroup  = $("#suk_attrs>div[checkedItem=true]").eq(j);
                        var currentGroupChecked  = currentGroup.find("input[type=checkbox]:checked");
                        nextCheckedCount = nextCheckedCount * (j == (x - 1) ? 1 : $("#suk_attrs>div[checkedItem=true]").eq(j + 1).find("input[type=checkbox]:checked").length);
                        var checkedBox = currentGroupChecked.eq(Math.floor(i / nextCheckedCount) % currentGroupChecked.length);
                        //tds = '<td id="td' + i.toString() + j.toString() + '">' + checkedBox.attr("id") + '</td>' + tds;
                        tds = '<td group="'+currentGroup.attr('id')+'" id="td' + i.toString() + j.toString() + '">' + checkedBox.attr('id') + '</td>'+tds;
                    }
                    t += '<tr>' + tds + '<td group="originPrice"><input type="text"></td><td group="price"><input type="text"></td><td group="platformPrice"><input type="text"></td><td group="stocks"><input type="text"></td></tr>';
                }
                $("#xs-block").html('<table class="tablelist" style="clear:none;text-align:center;valign:middle"><thead>'+th +'</thead><tbody>'+ t + '</tbody></table>');
    		}
    		
    		!function() {
    			//绑定元素
    			laydate({
    				istime : true,
    				format : 'YYYY-MM-DD hh:mm:ss',
    				elem : '#ms_begintime'
    			});
    		}();
    		
    		function initAttrData(){
    				//填充参数信息
    			$.post("/product/query_attrs.json",{"id":id},function(attrs){
    				for(var i = 0;i<attrs.length;i++){
    					var element = $('div[attr_id='+attrs[i].attr_id+'] .group-content:first *:first');
    					element.val(attrs[i].attrVal);
    				}
    				});
    		}
    		
    		    function checkSku(){
    		var flag = true;
    	var skuGroup = new Array;
    	$("#xs-block table tbody tr").each(function(j){
    		if($(this).html()!=""){
    		var sku = {};
    		sku.id = $(this).attr('id');
    		$(this).find('td').each(function(i){
    			
    			
    			var td = $(this);
    			if(td.attr('group')=='price'){
    				if(!checkInput("decimal2",td.find('input').val()||td.find('input').val()=="")){
						flag=false;
								}
    				sku.price=td.find('input').val();
    			}else if(td.attr('group')=='platformPrice'){
    				if(!checkInput("decimal2",td.find('input').val()||td.find('input').val()=="")){
						flag=false;
								}
    				sku.platformPrice=td.find('input').val();
    			}else if(td.attr('group')=='originPrice'){
    				if(!checkInput("decimal2",td.find('input').val()||td.find('input').val()=="")){
						flag=false;
								}
    				sku.originPrice=td.find('input').val();
    			}else if(td.attr('group')=='stocks'){
    				if(!checkInput("number",td.find('input').val()||td.find('input').val()=="")){
						flag=false;
								}
    				sku.stocks=td.find('input').val();
    			}else{

    				var attrKey = td.attr('group');
    				var attrVal = td.text();
    				if(sku.attrName1==undefined){
    					sku.attrName1 = attrKey;
    					sku.attrVal1 = attrVal;
    				}else if(sku.attrName2==undefined){
    					sku.attrName2 = attrKey;
    					sku.attrVal2 = attrVal;
    				}else if(sku.attrName3==undefined){
    					sku.attrName3 = attrKey;
    					sku.attrVal3 = attrVal;
    				}else if(sku.attrName4==undefined){
    					sku.attrName4 = attrKey;
    					sku.attrVal4 = attrVal;
    				}
    			}
	
    		});
    		skuGroup.push(sku);
   			}	
   				if(flag==false){
   					alert("请检查输入");
   					
   				}else{
   					skus = skuGroup;
   					
   				}
				
    	});
    
    }
	    //填充sku数据
    function initSkuData(product_id){
    	$.post("<%=basePath%>productsku/list.json",{"product_id":id},function(data){
    	setSkuTable(data);
    	$("#suk_attrs").prev().remove();
    	$("#suk_attrs").remove();
    	var list = data;
    	$("#xs-block tr").each(function(){
    		for(key in list){
    			var data = list[key];
    			/* tr. */
    		}
    	
    	})
    	},'json')
    
    
    }
    
    
    
	</script>
</body>
</html>