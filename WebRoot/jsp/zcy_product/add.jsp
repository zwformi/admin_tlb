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
<link href="<%=basePath%>js/validform/validform_ext.css" rel="stylesheet" type="text/css" />
<%-- <link rel="stylesheet"
	href="<%=basePath%>js/editor/themes/default/default.css" /> --%>
<link href="<%=basePath%>js/uploader/webuploader_ext.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<script src="<%=basePath%>js/jquery.pagination.js"></script>
<script src="<%=basePath%>js/jquery.tmpl.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/validform/validform_v5.3.2_min.js"></script>
<script src="<%=basePath%>js/validform/validform_ext.js"></script>
<%-- <script charset="utf-8" src="<%=basePath%>js/editor/kindeditor-min.js"></script>
<script charset="utf-8" src="<%=basePath%>js/editor/lang/zh_CN.js"></script> --%>

<script src="<%=basePath%>js/ueedit/ueditor.config.js"></script>
<script src="<%=basePath%>js/ueedit/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>js/ueedit/lang/zh-cn/zh-cn.js"></script>

<script src="<%=basePath%>js/uploader/webuploader.min.js"></script>
<script src="<%=basePath%>js/uploader/webuploader_ext.js"></script>
<script src="<%=basePath%>js/laydate/laydate.js"></script>
<script src="<%=basePath%>js/htmlConverter.js?date=<%=new Date()%>"></script>
<script src="<%=basePath%>js/search_zh.js"></script>
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
	  	padding:20px 0px 20px 40px;
	  }
.control-group{
	float:left;
	width:50%;
	display:inline;
	height:50px;
	/* line-height:50px; */
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
/* 	line-height:50px; */

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
/* 	color:red; */
	color: #0099cc;
}
.attr_type{
	width:86px; height:100%; float:left; display:inline;margin-top:20px;
}
.locations{
    width: 113px !important;
}
.error_input{
	border:1px solid red !important;
}
.addChoice,.saveChoice,.resetChoice,.delChoice{
	padding-left:20px;cursor:pointer;
}

.dropdown-menu li span:hover{
  background:#0099cc;
  color:#fff;
}
.hid{
display:none;
}
.scroll-wrapper {
    overflow: hidden!important;
    padding: 0!important;
    position: relative;
}
.scroll-wrapper>.scroll-content {
    border: 0!important;
    box-sizing: content-box!important;
    height: auto;
    left: 0;
    margin: 0;
    max-height: none!important;
    max-width: none!important;
    overflow-x: hidden!important;
    overflow-y: auto!important;
    padding: 0;
    position: relative!important;
    top: 0;
    width: auto!important;
}
.scroll-content2::-webkit-scrollbar  
{  
    width: 8px;  
    height: 16px;  
    background-color: #F5F5F5;  
}  
  
/*定义滚动条轨道 内阴影+圆角*/  
.scroll-content2::-webkit-scrollbar-track  
{  
    /*border-radius: 3px;*/  
    background-color: #fff;  
}  
  
/*定义滑块 内阴影+圆角*/  
.scroll-content2::-webkit-scrollbar-thumb  
{  
 
    background-color: #ccc;  
} 
#suk_attrs div span{
	display:inline-block;
	margin:0 5px;
	border:1px solid #fff;
}
.delChoice{
	color:#fff;
	padding-left:10px;padding-right:10px;font-size:20px;
}
.xs_check{
	margin:5px !important;
}
.req_label{
	color: #0099cc;
    font-weight: bold;
}
.hover{
background:#00abff;
color:#fff;
}
</style>
</head>

<body>
	<hr>
		<form id="mForm">
			<input id="id" name="id" type="hidden" /> 
			    <input id="action" name="action" type="hidden" />
				<input  name="zcy_price_area" type="hidden" /> 
				<input  name="platform_price_area" type="hidden" />
				<input  name="count" type="hidden" />
			<div class="place">
				<span>位置：</span>
				<ul class="placeul">
					<li><a href="<%=basePath%>jsp/firstpage.jsp">首页</a></li>
					<li><a href="javascript:;">图灵买商品</a></li>
					<li><a href="<%=basePath%>jsp/zcy_product/list.jsp">商品管理</a></li>
					<li><a href="javascript:;" class="location_add">新增商品</a></li>					
				</ul>
				<ul style="float:right">
				   <li><input type="button" class="btn" value="确认保存" style="margin-top: 2px;margin-right: 10px; width: 100px;background: #00abff;"/></li>
				</ul>
			</div>
			<div class="formbody">
				<div class="formtitle">
					<span class="title_add">新增商品</span>
				</div>				
				<ul class="forminfo">
				<table class="forminfo form_margin" width="100%">	
					<tr>
						<td width="4%"><b class="required">*&nbsp;&nbsp;</b><label class="req_label">所属类型：</label>
						</td>
						<td width="46%">
							<dl style="width: 345px;line-height: 32px;">								
								<!-- 一级类目 -->
								<!-- <select id="parent_type_id" class="type_ids" name="parent_type_id">
									<option value='0' selected>请选择所属类型</option>
								</select> -->
								<span class="select2" style="width:32%;display: inline-block;position: relative;vertical-align: middle;">
					              	<span class="selection">
					              		<span id="parent_type_id" style="border: 1px solid #ccd0d6;outline: none;width: 100%;position: relative;height: 34px;box-sizing: border-box;cursor: pointer;display: block;">
						                    <span class="select2-js-item-brand-container" style="padding-left: 8px;padding-right: 20px;line-height: 32px;width: 90%;outline: none;text-overflow: ellipsis;white-space: nowrap;overflow:hidden" z_id="">请选择所属类型</span>
						                    <input type="hidden" class="select2-input" name="parent_type_id"/>
						                    <span class="select2-selection__arrow" style="position: absolute;top: 14px;right: 5px;width: 20px;"><b style="border-color: #888 transparent transparent;border-style: solid;border-width: 5px 4px 0;height: 0;left: 50%;margin-left: -4px;margin-top: -2px;position: absolute;top: 50%;width: 0;"></b></span>
					              		</span>
					              	</span>
					          		<span class="dropdown-wrapper" aria-hidden="true">           			
					          		</span>
					            </span>
								<!-- 二级类目 -->
								<!-- <select id="sub_type_id" class="type_ids" name="sub_type_id">
									<option value='0' selected>请选择所属类型</option>
								</select> -->
								<span class="select2" style="width:32%;display: inline-block;position: relative;vertical-align: middle;">
					              	<span class="selection">
					              		<span id="sub_type_id" style="border: 1px solid #ccd0d6;outline: none;width: 100%;position: relative;height: 34px;box-sizing: border-box;cursor: pointer;display: block;">
						                    <span class="select2-js-item-brand-container" style="padding-left: 8px;padding-right: 20px;line-height: 32px;width: 90%;outline: none;text-overflow: ellipsis;white-space: nowrap;overflow: hidden;" z_id="">请选择所属类型</span>
						                    <input type="hidden" class="select2-input" name="sub_type_id"/>
						                    <span class="select2-selection__arrow" style="position: absolute;top: 14px;right: 5px;width: 20px;"><b style="border-color: #888 transparent transparent;border-style: solid;border-width: 5px 4px 0;height: 0;left: 50%;margin-left: -4px;margin-top: -2px;position: absolute;top: 50%;width: 0;"></b></span>
					              		</span>
					              	</span>
					          		<span class="dropdown-wrapper" aria-hidden="true">           			
					          		</span>
					            </span>
								<!-- 三级类目 -->
								<!-- <select id="type_id" class="type_ids" name="type_id"> 
									<option value='0' selected>请选择所属类型</option>
								</select> -->
								<span class="select2" style="width:33%;display: inline-block;position: relative;vertical-align: middle;">
					              	<span class="selection">
					              		<span id="type_id" style="border: 1px solid #ccd0d6;outline: none;width: 100%;position: relative;height: 34px;box-sizing: border-box;cursor: pointer;display: block;">
						                    <span class="select2-js-item-brand-container" style="padding-left: 8px;padding-right: 20px;line-height: 32px;width: 90%;outline: none;text-overflow: ellipsis;white-space: nowrap;overflow: hidden;" z_id="">请选择所属类型</span>
						                    <input type="hidden" class="select2-input" name="type_id"/>
						                    <span class="select2-selection__arrow" style="position: absolute;top: 14px;right: 5px;width: 20px;"><b style="border-color: #888 transparent transparent;border-style: solid;border-width: 5px 4px 0;height: 0;left: 50%;margin-left: -4px;margin-top: -2px;position: absolute;top: 50%;width: 0;"></b></span>
					              		</span>
					              	</span>
					          		<span class="dropdown-wrapper" aria-hidden="true">           			
					          		</span>
					            </span>
							</dl>
						</td>			 
						<td width="4%"><b class="required">*&nbsp;&nbsp;</b><label class="req_label">所属品牌：</label>
						</td>
						<td width="46%">	
							<dl style="width: 345px;line-height: 32px;">
								<!-- <select id="brand_id" name="brand_id"></select> -->
								<span class="select2" style="width:100%;display: inline-block;position: relative;vertical-align: middle;">
					              	<span class="selection">
					              		<span id="select2-selection" style="border: 1px solid #ccd0d6;outline: none;width: 100%;position: relative;height: 34px;box-sizing: border-box;cursor: pointer;display: block;">
						                    <span class="select2-js-item-brand-container" style="padding-left: 8px;padding-right: 20px;line-height: 32px;width: 90%;outline: none;text-overflow: ellipsis;white-space: nowrap;overflow: hidden;" z_id="" >请选择品牌</span>
						                    <input type="hidden" class="select2-input" name="brand_id"/>
						                    <span class="select2-selection__arrow" style="position: absolute;top: 14px;right: 5px;width: 20px;"><b style="border-color: #888 transparent transparent;border-style: solid;border-width: 5px 4px 0;height: 0;left: 50%;margin-left: -4px;margin-top: -2px;position: absolute;top: 50%;width: 0;"></b></span>
					              		</span>
					              	</span>
					          		<span class="dropdown-wrapper" aria-hidden="true">           			
					          		</span>
					             </span>
							</dl>
						</td>	 
					</tr>                        			
					<tr>
						<td><b class="required">*&nbsp;&nbsp;</b><label class="req_label">商品名称：</label>
						</td>
						<td>
							<dl>
								<input id="name" name="name" type="text" class="dfinput" datatype="*2-100" nullmsg="请填写商品名称" />
						    </dl>
						</td>
						<td><b class="required">*&nbsp;&nbsp;</b><label class="req_label">商品型号：</label>
						</td>
						<td><dl>
								<input id="xh" name="xh" type="text" class="dfinput"  datatype="*2-100" nullmsg="请填写商品型号" />
						   </dl>
						</td>
					</tr>			
					<tr>
						<td><label>商品配置：</label>
						</td>
						<td><dl>
								<input id="pz" name="pz" type="text" class="dfinput" datatype="*0-100"  />
							</dl>
						</td>						
						<td><label>商品副标题：</label>
						</td>
						<td><dl>
								<input id="sub_title" name="sub_title" type="text" class="dfinput" datatype="*0-100" />
						   </dl>
						</td>
					</tr>
				<!-- <tr> -->
					<tr>
						<td><b class="required">*&nbsp;&nbsp;</b><label class="req_label">生产厂商：</label>
						</td>
						<td><dl>
								<input id="firm" name="firm" type="text" class="dfinput" datatype="*1-100" nullmsg="请填写生产厂商" />
						    </dl>
						</td>
						<td><label>售后服务：</label>
						</td>
						<td><dl>
								<input id="service" name="service" type="text" class="dfinput" datatype="*0-100" nullmsg="请填写售后服务" />
						    </dl>
						</td>
					</tr>
			   <!--
					<tr>
	 				<td><b class="required">*&nbsp;&nbsp;</b><label class="req_label">商品市场价：</label></td>
					<td><dl>
							<input id="price_old" name="price_old" type="text" class="dfinput"
								datatype="/^[1-9]+(\.\d+)?$|^0(\.\d+)?$|^[1-9]+[0-9]*(\.\d+)?$/" nullmsg="请填写市场价" />
					</dl></td>
					
	 				<td><b class="required">*&nbsp;&nbsp;</b><label class="req_label">商品销售价：</label></td>
					<td><dl>
							<input id="price_new" name="price_new" type="text" class="dfinput"
								datatype="/^[1-9]+(\.\d+)?$|^0(\.\d+)?$|^[1-9]+[0-9]*(\.\d+)?$/" nullmsg="请填写销售价" />
					</dl></td> 
				</tr>
				-->
					<tr>
						<td><label>商品图片：</label>
						</td>
						<td ><dl>
								<label></label>
								<a id="editimage" style="cursor:pointer; ">点击管理图片</a>
								<a id="imghint" style="cursor:pointer;" href="javascript:;">请先保存商品后添加图片！</a>
						</dl>
						</td>
					</tr>	
					<tr>
						<td><label>微信是否可见：</label>
						</td>
						<td><dl class="select">
								<input type="radio" name="is_wx" value="0" checked="checked" />不可见
								<input type="radio" name="is_wx" value="1" />可见
						</dl>
						</td>
						<td><label>PC是否可见：</label>
						</td>
						<td><dl class="select">
								<input type="radio" name="is_pc" value="0" />不可见
								<input type="radio" name="is_pc" value="1" checked="checked" />可见
						</dl>
						</td>
					</tr>
					<tr>
						<td><label>是否需要安装：</label>
						</td>
						<td><dl class="select">
								<input type="radio" name="needInstall" value="0" checked="checked" />不需要
								<input type="radio" name="needInstall" value="1" />需要
						   </dl>
						</td>	
					</tr>
					<tr>
						<td><label>是否促销：</label>
						</td>
						<td><dl class="select">
								<input type="radio" name="is_cx" value="0" checked="checked" />非促销
								<input type="radio" name="is_cx" value="1" />促销
						   </dl>
						</td>			
						<td><label>是否秒杀：</label>
						</td>
						<td><dl class="select">
								<input type="radio" name="is_ms" value="0" checked="checked" />否
								<input type="radio" name="is_ms" value="1" />是
						   </dl>
						</td>
					</tr>				
					<tr id="ms_div">
						<td><label>秒杀开始时间：</label>
						</td>
						<td><dl>
								<input id="ms_begintime" name="ms_begintime" type="text" class="dfinput" />
						    </dl>
						</td>			
						<td><label>秒杀口令：</label>
						</td>
						<td><dl>
								<input id="ms_kl" name="ms_kl" type="text" class="dfinput" />
						   </dl>
						</td>
					</tr>				
					<tr>
						<td><label>是否热卖推荐：</label>
						</td>
						<td><dl class="select">
								<input type="radio" name="is_red" value="0" checked="checked" />不推荐
								<input type="radio" name="is_red" value="1" />推荐
						   </dl>
						</td>
						<td><label>商品状态：</label>
						</td>
						<td><dl class="select">
								<input type="radio" name="delete_flag" value="0" checked="checked" />上架
								<input type="radio" name="delete_flag" value="1" />下架
						    </dl>
						</td>
					</tr>
					<tr>
						<td><label>是否云产品：</label>
						</td>
						<td><dl class="select">
								<input type="radio" name="is_yun" value="0" checked="checked" />否
								<input type="radio" name="is_yun" value="1" />是
						   </dl>
						</td>
					<!-- <td> -->
					</tr>			
					<tr>
						<td><b class="required">*&nbsp;&nbsp;</b><label class="req_label">境内或境外：</label></td>
						<td><dl class="select">
								<input type="radio" name="limit" value="0" checked="checked" />境内
								<input type="radio" name="limit" value="1" />境外
						   </dl>
						</td>
						<td><b class="required">*&nbsp;&nbsp;</b><label class="req_label">产地：</label>
						</td>
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
							<option value=''>请选择国家</option>
							<option value='228'>美国</option>
							</select>
						</dl>
						</td>
					</tr>
					<tr>		
						<td><b class="required">*&nbsp;&nbsp;</b><label class="req_label">排序ID：</label></td>
						<td><dl>
								<input id="sortid" name="sortid" title="数字越大越靠前" type="text" class="dfinput" onkeyup="this.value=this.value.replace(' ','')"	datatype="n2-8" nullmsg="请填写排序ID" />
								<input type="hidden" id="editorC" name="content"/>
						</dl>
						</td>
					</tr>				
			</table>
		<!-- </table> -->
		<!-- </form> -->
		   <form id="attrForm">	
							<li><div id ="attr-block"></div>   
							</li>
							<li>
								<div class="attr_type">销售属性</div>
								<div id="xs-block"><table class="tablelist" style="clear:none;text-align:center;valign:middle"><thead><tr><th>市场价</th><th>图灵云价格</th><th>政采云价格</th><th>库存</th></tr></thead><tbody><tr><td group="originPrice"><input type="text"></td><td group="platformPrice"><input type="text"></td><td group="price"><input type="text"></td><td group="stocks"><input type="text"></td></tr></tbody></table></div>
							</li>
		  </form>
		  <li><label>商品详情：</label>
			<dl style="display: inline-block;width: 90%;">
				<!-- <textarea id="content" name="content"></textarea> -->
				<textarea id="editor"  style="height:500px;width:100%"></textarea>
			</dl>
		  </li>
		  
		  </ul>
		  
		  </div>
	    </form>
		<script>

		//初始化上传
		//初始化编辑器样式
       

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
		var depositRate = 0;//折扣率
		//销售属性的数目
		var xssx_num = 0;
		//销售属性的group
		var xssx_group=new Array;
		
/* 			   editor = new UE.ui.Editor({
                 //关闭元素路径
			    elementPathEnabled:false,
			    zIndex:1,
			    //是否自动长高
			    autoHeightEnabled: true,
			    //toolbar的位置不动
			    autoFloatEnabled: true,
			    topOffset:42,
			    textarea : 'editorValue'
            });
            editor.render("editor"); */
       var editor = UE.getEditor('editor', {
		    //关闭元素路径
		    elementPathEnabled:false,
		    zIndex:1,
		    //是否自动长高
		    autoHeightEnabled: true,
		    //toolbar的位置不动
		    autoFloatEnabled: true,
		    topOffset:42
		});    
       
		window.onload=function(){
        	getDepositRate();
			document.onkeypress = function(){ 
				if(event.keyCode == 13) {event.stopPropagation();event.preventDefault();}
			}
			$(".forminfo input").keypress(function(){
				if(event.keyCode == 13){event.stopPropagation();event.preventDefault();}
			});
		}
		

		$("body").on("mouseover mouseout","#suk_attrs div span",function(e){
			$this = $(this);
			 if(event.type == "mouseover"){
			 	$this.css("background","#dddddd");
  				//鼠标悬浮
 				}else if(event.type == "mouseout"){
 				$this.css("background","#fff");
  				//鼠标离开
 				}
		});
		

		function getDepositRate(){
			$.post("<%=basePath%>zcy/getDepositRate.json","",function(data){
				if(data.resultCode==1){
					depositRate = data.depositRate;
				$("#xs-block input").keyup(function(event){
                	var b=event.target;
   					checkDepositRate(this);
	   			});
				}
				else{
					layer.msg("获取折扣率失败！");
				}
			});
		}
		
		$("#editimage").hide();
		
$(function() {
		loadCountry()
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
		   		
    	
		    $("body").on("click", ".addChoice", function (){
   			$(this).parent().append("<div id=\"addGroup\"><input style=\"width:300px;\" class=\"new input-text\" type=\"text\" value=\"\"><a class=\"saveChoice\">保存</a><a class=\"resetChoice\">取消</a></div>");
   			$(this).remove();
    		});
    		
			$("body").on("click", ".delChoice", function (){
    			var $this = $(this);
    			var attr_id = $this.parent().parent().attr('name').split(",")[1];
    			var check = $this.prev();
    			var val = $this.prev().attr('id').trim();
    			if(check.prop("checked")){
    				layer.alert("该选项已选中，请取消选择后再删除！");
    			}else{	
    				
    				layer.confirm('确定要删除吗？', function(){
    					 $.post("<%=basePath%>/product/delAttrVals.json",{"attrVals":val,"attr_id":attr_id},function(data){
			         	layer.closeAll();
			         	if(data.resultCode!=1){
			            	layer.alert(data.resultMsg);   
			           	}else{   			
			    			$this.parent().remove();   
			           	}      
			         });   
				});	
    			}	 
    		});
			
			
			
    		
    		  $("body").on("click", ".saveChoice", function (){
    			var $this = $(this);
    			var group = $this.parent().parent().attr('id');
    			var data_type = $this.parent().parent().attr('data_type1');
    			var attr_id = $this.parent().parent().attr('name').split(",")[1];
    			var val = $this.parent().find('.new').val().trim();
    			var flag = true;
    			if(val==""){
    				flag = false;
    				return;
    			}else if(data_type=="NUMBER"){
    				//校验数字
    				if(!checkInput("demOrNum",val)){
    					layer.alert("请输入数字！");
    					flag=false;
    					return;
    				}
    			
    			}
    				if(flag==true){
    				 $.post("<%=basePath%>/product/addAttrVals.json",{"attrVals":val,"attr_id":attr_id},function(data){
			         	if(data.resultCode!=1){
			            	layer.alert(data.resultMsg);
			                $this.parent().find('.new').val("");
			           	}else{
			                $this.parent().parent().append("<span><input class='xs_check' group='"+group+"' name='check' type='checkbox' id='"+val.trim()+"' def='"+val.trim()+"'>"+val.trim()+"</input><a class=\"delChoice\">×</a></span>");
    						$this.parent().parent().append("<a class=\"addChoice\">增加</a>");
							$this.parent().remove();   
			                   
			                   }
			                   
			                    });   
			        }
    					
    		});	
    		
    		$("body").on("click", ".resetChoice", function (){
    			var $this = $(this);
    			$this.parent().parent().append("<a class=\"addChoice\">增加</a>");
				$this.parent().remove();
						
    		});	
		
			$("body").on("click", "#suk_attrs input[type=checkbox]", function ()
            {	
            	var _name = $(this).parent().attr("name");
            	$("div.attrs  .group-content *[name='"+_name+"']").attr("disabled","disabled");
            	//获取此时表中所有数据
	   			tableData = getTableData();//表中数据暂存到变量中
            	makeSkuTable();
            	fillInTable();
            });
		
		//初始化表单验证
		checkValidform = initValidform("mForm");
		//初始化上传控件
	   $(".upload-img").InitUploader({
		sendurl : "<%=basePath%>sys/uploadPic_product.json",
	    swf: "<%=basePath%>js/webuploader/uploader.swf" 
		});
		
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
    		$("#editimage").show();
    		$("#imghint").hide();
    		$("#id").val(id);
    		$("#action").val(action);
    		$("title").html("编辑商品信息")
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
    			loadProvince(data.provinceId);
    			/* $("#provinceId").val(data.provinceId); */
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
    			setparenttypeid(type_ids[1]);
    			loadType2();
    			setsubtypeid(type_ids[2]);
    			loadType3();
    			settypeid(type_ids[3]);
    			
     			$("#parent_type_id").off('click');
     			$("#parent_type_id").css("cursor","not-allowed");
    			$("#sub_type_id").off('click'); 
    			$("#sub_type_id").css("cursor","not-allowed");
    			$("#type_id").off('click'); 
    			$("#type_id").css("cursor","not-allowed");
    			setbrand(data.brand_id);

    			$("input[name='is_cx'][value="+data.is_cx+"]").attr("checked",true); 
    			$("input[name='is_ms'][value="+data.is_ms+"]").attr("checked",true); 
    			$("input[name='is_wx'][value="+data.is_wx+"]").attr("checked",true); 
    			$("input[name='is_pc'][value="+data.is_pc+"]").attr("checked",true); 
    			$("input[name='is_red'][value="+data.is_red+"]").attr("checked",true); 
    			$("input[name='delete_flag'][value="+data.delete_flag+"]").attr("checked",true); 
    			$("input[name='is_yun'][value="+data.is_yun+"]").attr("checked",true); 
    			$("input[name='special_code'][value="+data.special_code+"]").attr("checked",true); 
    			$("input[name='limit'][value="+data.limit+"]").attr("checked",true); 
    			$("input[name='needInstall'][value="+data.needInstall+"]").attr("checked",true); 
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
    			/* editor.html(data.content); */
    			setselfdefindcontent(data.content);
    		});
    	}else{
    	$('#product_info').hide();
    	loadProvince()
    	}
    	

    });
 //function($){}()结束   
    

<%--     KindEditor.ready(function(K) {
        editor = KindEditor.create('#content', {
            width: '90%',
            height: '400px',
            resizeType: 1,
            uploadJson: '<%=basePath%>sys/editorUpload.json',
            fileManagerJson: '<%=basePath%>sys/editorFileManage.json',
            allowFileManager: true
        });
	}); --%>
    
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
       		$("#provinceId").val("");
       		$("#cityId").val("");
       		$("regionId").val("");
       		$("#provinceId").hide();
       		$("#cityId").hide();
       		$("#regionId").hide();
       }else{
       		$("#countryId").val("");
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
    	 var flag = true;
    	 if(action!="edit"){
    				//检测销售属性必填项有没选择
		 $("#suk_attrs>div").each(function (i,d)
                {	
                	if($(d).attr('required')=='required'){
                		  var i = $(d).find("input[type=checkbox]:checked").length;

                		  if(i<1){
                		  	layer.alert($(d).attr('id')+"为必选项！");
                		  	flag=false;
                		  }
                	} 
    			});
    	 }
    	 
    	 	
    	 	
		 	
			//校验必填项
     		var arr = $('div[required] .group-content');
			var _str='';
			arr.each(function(){
						/* if(!checkValidAttr($(this).find("*:first"))) flag=false; */
			             var self;	
			              self=$(this).find("span").find("*:first").eq(0);
						if(!$(self).val()){
							if($(self).attr("disabled")==null){
							var _text = $(this).parent().siblings('label').text().replace("：","").replace("*",""); 
							_str += _text+",";
							$(self).addClass("error_input");
							flag = false;
							}
						}else{
							$(self).removeClass("error_input");
						}
				});    			 
							if(!!_str) _str=_str.substring(0,_str.length-1)+"为必填项。";
							
													 
		//校验数字
		var num_str = "";
		var num_arr = $("div[data_type='NUMBER']");
		num_arr.each(function(){
			var num_input = $(this).find('input:first');
			var pattern = /^[0-9]+(\.[0-9]+)?$/

			if(!!num_input.val()  &&(!pattern.test(num_input.val()))){

				console.log(num_input)
				var _text = $(this).find('label').text().replace("：","").replace("*",""); 		
				num_input.addClass("error_input");
				num_str +=_text+",";
				flag = false;
			}	
		}) 
		if(!!num_str)  num_str=num_str.substring(0,num_str.length-1)+"应填写数字。";
		
		//校验销售属性输入
		var flag2 = checkSku();
		if(flag2==false){
		 		flag = false;
		 		_str += "请检查销售属性输入！";
		}
		//校验销售属性选择
		var flag3 = checkSuk_Attrs();
		if(flag3 == false){
			flag = false;
			_str += "销售信息勾选（每个必填分类至少需选择一项）。";
		}
		
		if(!flag){
		layer.alert(_str+num_str); 
		}
			
    	if(checkValidform.check() && flag){
    		if($(":radio[name='is_ms']:checked ").val()==1){
    			if($("#ms_begintime").val()==""){
    				layer.alert("请设定秒杀开始时间！");
    				flag=false;
    			}
    			if($("#ms_kl").val()==""){
    				layer.alert("请设定秒杀口令！");
    				flag=false;
    			}
    		}
    		if($("input[name='parent_type_id']").val().length==0||$("input[name='sub_type_id']").val().length==0||$("input[name='type_id']").val().length==0){
    				layer.alert("请选择所属类型！");
    				flag=false;
    		}
    		if($("input[name='brand_id']").val()==""){
    				layer.alert("请选择品牌！");
    				flag=false;
    		}
			//检测地区
    		if($(":radio[name='limit']:checked ").val()==1){
    			if($("#countryId").val()==0){
    				layer.alert("请选择国家");
    				flag=false;
    			}
    		}else{
    			if($("#provinceId").val()==0){
    				layer.alert("请选择省份");
    				flag=false;
    			}
    			if($("#cityId").val()==0){
    				layer.alert("请选择城市");
    				flag=false;
    			}
    			if($("#regionId").val()==0){
    				layer.alert("请选择区域");
    				flag=false;
    			}
    		}		
    		if(flag){//校验成功
    			var url = "<%=basePath%>product/add.json";
    		if($("#action").val() == "edit"){
    			url = "<%=basePath%>product/modify.json";
    		}
    		//库存
    		var stocks = getPriceandCount("stocks","sum");
    		$("input[name='count']").val(stocks);
    		//图灵买价格区间
    		var platform_min = getPriceandCount("platformPrice","min");
    		var platform_max = getPriceandCount("platformPrice","max");
    		if(platform_min!=platform_max)
    		$("input[name='platform_price_area']").val(platform_min+"~"+platform_max);
    		else
    		$("input[name='platform_price_area']").val(platform_min);
    		//政采云价格区间
    		var zcy_min = getPriceandCount("price","min");
    		var zcy_max = getPriceandCount("price","max");
    		if(zcy_min!=zcy_max)
    		$("input[name='zcy_price_area']").val(zcy_min+"~"+zcy_max);
    		else
    		$("input[name='zcy_price_area']").val(zcy_min);
    		
    		if(editor.hasContents()){
    		  editor.sync(); 
    		  var kk=editor.getContent();       
              $("#editorC").val(kk);  		 
    		  };
    		var frm=$('#mForm').serialize();
    		var attrs=$('#attrForm').serialize();
    		var _index = layer.load();
    		$.post(url,frm,function(data){
    			//解绑点击事件
    			$(".btn").unbind();
    			if(data.count>0){
    			    //添加
    				if(action!="edit"){
    					attrs+="&product_id="+data.id;
    					$.post("<%=basePath%>product/addAttrs.json",attrs,function(attrs){
    						$.post("<%=basePath%>productsku/add.json",{"skus":JSON.stringify(skus),"product_id":data.id},function(attrs){
    						layer.close(_index);
    						layer.confirm('保存成功！是否进入编辑页面？', {icon: 1}, function(index){
    						location.href="add.jsp?action=edit&id="+data.id;
							});
    					});
    						});
    				}
                    //修改
    				else{
    			
    					attrs+="&product_id="+id;
    					$.post("<%=basePath%>product/modifyAttrs.json",attrs,function(attrs){
    							$.post("<%=basePath%>productsku/update.json",{"skus":JSON.stringify(skus),"product_id":id},function(attrs){
    							layer.close(_index);
    						layer.confirm('保存成功！是否返回产品列表？', {icon: 1}, function(index){
    					location.href="list.jsp";
    					});
    				});
    					
    					});
    				
    				}
    				
    				if($("#action").val() != "edit"){
    					$('#mForm')[0].reset();
    					/* editor.html(""); */
    					setselfdefindcontent("");
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
  					options={};
			        options.ob=$("#parent_type_id");			       
			        var list=[];
			        for(var i=0;i<types.length;i++){  					
	  					if(types[i].product_level==1){
                           list.push(types[i]);
	  					}
    	        	}
			        options.keyname="name";	
					options.keyid="id";
			        options.width=200;
			        options.list=list;		        
			        Search.prototype.s_backFun="loadType2()";
			        var SR = new Search(options);			        		        
			        SR.init();
    	        	
    	       
    	}
    	});
    	}
    	//设置一级类目内容
    	function setparenttypeid(id){
    	    if(id.length==0)
    	   {
	    	    $("#parent_type_id").find(".select2-js-item-brand-container").html("请选择所属类型");
    	   }
    	   else
    	   {
	    	   var parenttypeidlist=$("#parent_type_id").parents(".select2").find("li");
	    	   parenttypeidlist.each(function (i) {
	    	     if($(this).attr("zid")==id)
	    	     {
	    	        $("#parent_type_id").find(".select2-js-item-brand-container").html($(this).text());
	    	        return false;
	    	     }
	    	   }) 
    	   }
    	   $("#parent_type_id").find("input[class='select2-input']").val(id);
    	}
    	//设置二级类目内容
    	function setsubtypeid(id){
    	   if(id.length==0)
    	   {
    	        $("#sub_type_id").find(".select2-js-item-brand-container").html("请选择所属类型");
    	   }
    	   else
    	   {
	    	     var subtypeidlist=$("#sub_type_id").parents(".select2").find("li");
	    	     subtypeidlist.each(function (i) {
	    	     if($(this).attr("zid")==id)
	    	     {
	    	        $("#sub_type_id").find(".select2-js-item-brand-container").html($(this).text());
	    	        return false;
	    	     }
	    	   })	    	   
    	   }
    	   $("#sub_type_id").find("input[class='select2-input']").val(id);
    	}
    	//设置三级类目内容
    	function settypeid(id){
    	   if(id.length==0)
    	   {
	    	    $("#type_id").find(".select2-js-item-brand-container").html("请选择所属类型");
    	   }
    	   else
    	   {
    	       var settypelist=$("#type_id").parents(".select2").find("li");
	    	   settypelist.each(function (i) {
	    	     if($(this).attr("zid")==id)
	    	     {	    	        
	    	        $("#type_id").find(".select2-js-item-brand-container").html($(this).text());
	    	        return false;
	    	     }
	    	   })	    	  
    	   }
    	    $("#type_id").find("input[class='select2-input']").val(id);
    	}
    	
    	function setbrand(id){
    	   var brandlist=$("#select2-selection").parents(".select2").find("li");
    	   brandlist.each(function (i) {
    	     if($(this).attr("zid")==id)
    	     {
    	        $("#select2-selection").find("input[class='select2-input']").val(id);
    	        $("#select2-selection").find(".select2-js-item-brand-container").html($(this).text());
    	        return false;
    	     }
    	   })
    	}
    		
       function loadType2(){
            //alert("加载类目2");
            setsubtypeid(""); 	
			settypeid("");
			$("#type_id").off('click'); 
    	    $("#type_id").css("cursor","not-allowed");
    		var parentid = $("input[name='parent_type_id']").val();
  			options={};
			options.ob=$("#sub_type_id");
			var list=[];
  			//根据parent值加载
  			for(var i=0;i<types.length;i++){
  					if(types[i].product_level==2&&types[i].parentId==parentid){
  					    list.push(types[i]);
  					}		
    		}
    		  			options.keyname="name";	
					    options.keyid="id";
    		  			options.width=200;
    		  			options.list=list;
			            Search.prototype.s_backFun="loadType3()";
				        var SR = new Search(options);				        
				        SR.init();
    		}
    	 function loadType3(){
    	    //alert("加载类目3");
    	    settypeid(""); 
    	    $("#type_id").css("cursor","pointer");
    		var parentid = $("input[name='parent_type_id']").val();
    		var subid = $("input[name='sub_type_id']").val();
    		options={};
			options.ob=$("#type_id");
			var list=[];
  			//根据parent值加载
  			for(var i=0;i<types.length;i++){
  					if(types[i].product_level==3&&types[i].parentId==parentid&&types[i].sub_parentId==subid){
  						list.push(types[i]);
  					}		
    		}
    		//Search.prototype.s_keyname="Name";
    		options.keyname="name";	
		    options.keyid="id";
    		options.width=200;
    		options.list=list;
    		Search.prototype.s_backFun="";
	        var SR = new Search(options); 
	        SR.init();
    		
    		}
    	
    	//加载品牌	
    	function loadbrand(){
    		$.ajax({
    	        type: "POST",
    	        url: "<%=basePath%>product/brand.json",
    	        async: false, //设为false就是同步请求
    	        cache: false,
    	        success: function (data) {
    			    options={};
					options.ob=$("#select2-selection");	
					options.keyname="name";	
					options.keyid="id";		
					options.list=data;
					Search.prototype.s_backFun="";	
					var Sr = new Search(options);                    
                    Sr.init();
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
/* 				var number = /^[0-9]\d*$/;
				var decimal =/^\d+(\.\d+)?$/ ;
				var decimal2 = /^\d{0,8}\.{0,1}(\d{1,2})?$/; */
				var number = /^[0-9]+(\.[0-9]+)?$/;
				var decimal =/^[0-9]+(\.[0-9]+)?$/ ;
				var decimal2 = /^\d{0,8}\.{0,1}(\d{1,2})?$/;
				var demOrNum = /^[0-9]+(\.[0-9]+)?$/;
				if(type=="number")
				return number.test(str);
				if(type=="decimal")
				return decimal.test(str);
				if(type=="decimal2")
				return decimal2.test(str);
				if(type=="demOrNum")
				return demOrNum.test(str);
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
			
			function loadProvince(province){
				$.post('/product/getAreaList.json',{"parentCode":0,"level":1},function(data){
					$("#provinceId").empty().append("<option value='0' selected>请选择所属省</option>");
					var result = data.result;
					for(var i=0;i<result.length;i++){
						$("#provinceId").append("<option value='"+result[i].code+"'>"+result[i].name+"</option>");
					}
					if(!!province)
						$("#provinceId").val(province);
				})
			}
			function setselfdefindcontent(content){
	        //判断ueditor 编辑器是否创建成功
	        editor.ready( function () {
	        // editor准备好之后才可以使用
	        editor.setContent(content); 
	        });     
	        }
			
			
			function loadCountry(){
				$.post('/product/getAreaList.json',{"level":0,"parentCode":0},function(data){
					$("#countryId").empty().append("<option value='' selected>请选择国家</option>");
					var result = data.result;
					for(var i=0;i<result.length;i++){
						$("#countryId").append("<option value='"+result[i].id+"'>"+result[i].country_name+"</option>");
					}
				})
			}
			
			
			function loadCity(province){
// 				if(province=='0'){
// 				$("#cityId").empty().append("<option value='0' selected>请选择所属市</option>");
//     			}else if(province=='410000'){
//     			$("#cityId").empty().append("<option value='0' selected>请选择所属市</option>").append("<option value='410100'>郑州市</option>");
//     		}else if(province=='310000'){
//     			$("#cityId").empty().append("<option value='0' selected>请选择所属市</option>").append("<option value='310100'>上海市</option>");
//     		}else if(province=='440000'){
//     			$("#cityId").empty().append("<option value='0' selected>请选择所属市</option>").append("<option value='440300'>广州市</option>");
//     		}
			$.ajax({
				url:"/product/getAreaList.json",
				type:"post",
				data:{
					"parentCode":province,"level":2
				},
				async:false,
				dataType:"json",
				success : function(data){
					$("#cityId").empty().append("<option value='0' selected>请选择所属市</option>");
					var result = data.result;
					for(var i=0;i<result.length;i++){
						$("#cityId").append("<option value='"+result[i].code+"'>"+result[i].name+"</option>");
					}
				}
			})
			}
			
			function loadRegion(city){
// 				if(city=='0'){
// 				$("#regionId").empty().append("<option value='0' selected>请选择所属区</option>");
// 				}else if(city=='440300'){
//     			$("#regionId").empty().append("<option value='0' selected>请选择所属区</option>").append("<option value='440303'>罗湖区</option>").append("<option value='440304'>福田区</option>");
//     			}else if(city=='410100'){
//     			$("#regionId").empty().append("<option value='0' selected>请选择所属区</option>").append("<option value='410102'>中原区</option>").append("<option value='410103'>二七区</option>");
//     		}else if(city=='310100'){
//     			$("#regionId").empty().append("<option value='0' selected>请选择所属区</option>").append("<option value='310101'>黄浦区</option>").append("<option value='310103'>卢湾区</option>").append("<option value='310104'>徐汇区</option>");
//     		}
			$.ajax({
				url:"/product/getAreaList.json",
				type:"post",
				data:{
					"parentCode":city,"level":3
				},
				async:false,
				dataType:"json",
				success : function(data){
					$("#regionId").empty().append("<option value='0' selected>请选择所属区</option>");
					var result = data.result;
					for(var i=0;i<result.length;i++){
						$("#regionId").append("<option value='"+result[i].code+"'>"+result[i].name+"</option>");
					}
				}
			})
// 			$.post('/product/getAreaList.json',{"parentCode":city,"level":3},function(data){
// 				$("#regionId").empty().append("<option value='0' selected>请选择所属区</option>");
// 					var result = data.result;
// 					for(var i=0;i<result.length;i++){
// 						$("#regionId").append("<option value='"+result[i].code+"'>"+result[i].name+"</option>");
// 					}
// 				})
			}
			
			//校验Attrs
			function checkValidAttr(input){
				$input = $(input);
				var _text = $input.siblings('label').text().replace("：","").replace("*",""); 
				var type =$input.attr('data_type');
				if(type=='NUMBER'){
					if(!checkInput("numberorNull",$input.val())){
						$input.addClass('error_input');
						alert($input.attr('name').split(',')[0]+"应为数字！");
						$input.empty();
						return false;
					}else{
						$input.removeClass('error_input');
						return true;
					};
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
				var xs_i = 0;
				var _xs = "<div class=\"attr_type\" >销售信息：</div><div id=\"suk_attrs\" class=\"attrs select\" >";
				$("#attr-block").empty();
				for(key in list){
					var _data = list[key];
					//分组
/* 					if(_data.isSukCandidate==0){ */
						var _group = $("#attr-block div.attrs[name='"+_data.group+"']");
						if(_group==null || _group.length==0){
							var _temp = '<div class="attr_type">'+_data.group+'：</div>'+'<div class="attrs" name="'+_data.group+'">';
							$("#attr-block").append(_temp);
							_group = $("#attr-block div.attrs[name='"+_data.group+"']")
							_group.append(convertData(_data));
						}else{
							_group.append(convertData(_data)); 
							
						}
/* 						}
					else */
					 if(_data.isSukCandidate==1){
						xssx_group[xssx_num] = _data.attrName;
						xssx_num ++;
						_xs += "<div "+(_data.isRequired=="1"?"isRequired='1'":"")+" id=\""+_data.attrName.trim()+"\" group=\""+_data.attrName.trim()+"\" name='"+_data.attrName.trim()+","+_data.id+"'";
						if(_data.valueType.indexOf("NUMBER")>-1){
								_xs += "data_type1=\"NUMBER\"";
						}
						_xs += ">"+(_data.isRequired=="1"?"<span style='color:#0099cc;'>*</span>":"")+_data.attrName+"：";
						if(!!_data.attrVals){
						var _xs_val = _data.attrVals.split(",");
						for(var i=0;i<_xs_val.length;i++){
							_xs += "<span><input class='xs_check' group='"+_data.attrName+"' name='check' type='checkbox' id='"+_xs_val[i].trim()+"' def='"+_xs_val[i].trim()+"'>"+_xs_val[i].trim()+"</input>";
							
							if(_data.isUserDefined==1){
								_xs += "<a class=\"delChoice\">×</a>";
							}
							
							_xs +="</span>";

						}
						
						}
						if(_data.isUserDefined==1){
							_xs+="<a class=\"addChoice\">增加</a>";
						}
						
			
						_xs +="</div>";
						if(xs_i==0) xs_i = 1;
					}
					}
					if(xs_i>0){
						$("#attr-block").append(_xs+"</div>");
					}
					makeSkuTable();		
				}
				initAttrData()
				if(action == "edit")
					initSkuData(id);
					
				//子集目录显隐
			  $(".noselectric").find("input").on("click",function(e){
			  	e.stopPropagation()
			    if($(this).parents(".noselectric").next(".dropdown-content").hasClass("hid"))
			    {
			      $(this).parents(".noselectric").next(".dropdown-content").removeClass("hid");	
			      $(this).on("keydown",function(){
                      if(!$(this).parents(".noselectric").next(".dropdown-content").hasClass("hid"))
                      {
                          $(this).parents(".noselectric").next(".dropdown-content").addClass("hid");
                      }
                      })	
                  var lilength=$(this).parents(".noselectric").next(".dropdown-content").find(".dropdown-menu").find("li").length; 
                  if(lilength>5)
                  {
                     $(this).parents(".noselectric").next(".dropdown-content").find(".scrollbar-dynamic").css("height","180px");
                  }  	    
			    }
			    else
			    {
			      $(this).parents(".noselectric").next(".dropdown-content").addClass("hid");
			    }
                 
                 
                 /* group本身其他弹出框隐藏 */
			    var AllDropdown=$(this).parents(".control-group").siblings(".control-group").find(".group-content").find(".dropdown-content");
			    AllDropdown.each(function (i) {
			               if(!$(this).hasClass("hid"))
			               {
			                  $(this).addClass("hid");
			               }
			    });
			    /* group同级全部隐藏 */
			    var sb_attrs=$(this).parents(".attrs").siblings(".attrs").find(".group-content").find(".dropdown-content");
			    sb_attrs.each(function (i) {
			               if(!$(this).hasClass("hid"))
			               {
			                  $(this).addClass("hid");
			               }
			    });
	    
			     $(".pull-right").find("span").on("click",function(){
			       var input=$(this).parents(".dropdown-append").find("input");
			       var inputval=input.val().trim(); 
			       if($.trim(inputval)=="")
			       {
			         /* layer.alert("请输入要添加的参数"); */
			         return;
			       }
			       else
			       {
			       
			       //校验是否是数字
			       var data_type = input.attr("data_type");
			       if(data_type=="NUMBER"){
			       		if(!checkInput("demOrNum",inputval)){
			       		layer.alert("请输入数字!");
			       		$(input).val("");
			       		return;
			       		}
			       }
			       var ul= $(this).parents(".dropdown-content").find("ul");
			        var ullist= $(this).parents(".dropdown-content").find("ul").find("li");
			        var inputF=$(this).parents(".group-content").find("input").eq(0);
			        var dropdown=$(this).parents(".dropdown-content");
			        var flag=false;
			        ullist.each(function (i) {
			                    if($(this).attr("data-val")==inputval)
			                    {
			                      flag=true;
			                      return false;			                    
			                    }
			        
			        });
			        if(flag){
			                   layer.alert("该参数已经存在,请重新添加");
			        }
			        else
			        {           			                    
			                    var fistli= ullist.eq(0);
			                    var litemplate="<li class=\"js-dropdown-item\" onclick=\"addToinput(this)\" style=\"margin-bottom:0px;border-bottom: 1px solid #ccc;text-align: center;cursor: pointer;line-height: 35px;\" data-val='"+inputval+"'><span>"+inputval+"<span onclick=\"deleteitem(event)\" style=\"float: right;margin-right: 13px;\"><a style=\"color:#fff\">X</a></span><\span></li>"    
			                    if(ullist.length!=0)
			                    {
			                      fistli.before(litemplate);
			                    }
			                    else
			                    {
			                      ul.append(litemplate);
			                    }
			                    $(input).val("");
			                    
			                    var attr_id=$(this).parents(".group-content").eq(0).attr("attr_id"); 
			                    //保存该参数到数据库
			                    $.post("<%=basePath%>/product/addAttrVals.json",{"attrVals":inputval,"attr_id":attr_id},function(data){
			                        //console.log(JSON.stringify(data));
			                    });                   
			                    $(inputF).val(inputval);
			                    $(dropdown).addClass("hid");
			                    
			        } 
			         
			       }
			     
			     });
			     
  		$(".dropdown-content").mouseenter(function(){
     		$(this).unbind('click');
    		    	
     	}).mouseleave(function(){
 			$(document).on('click',function(){
     			$(".dropdown-content").addClass("hid");
     		})    	
     	})
    	
     	
     	$(".dropdown-content input").mouseenter(function(e){
     		e.stopPropagation();
     		$(document).unbind('click');   
     	}).mouseleave(function(){
     	
     		$(document).on('click',function(){
     			$(".dropdown-content").addClass("hid");
     		}) 
     	})
			  
			  })				
				},'json');	
    		}
    		

    		
			function addToinput(sl){
			   var val=$(sl).attr("data-val");
			      var input=$(sl).parents(".group-content").find(".noselectric").find("input");
			      $(input).val(val);
			      $(sl).parents(".dropdown-content").addClass("hid");			
			}
			
			function deleteitem(e){
			   e.stopPropagation();
			   var self=e.target;
			   var pli=$(self).parents(".js-dropdown-item").eq(0);
			   var paramer=$(pli).attr("data-val");
			   var attr_id=$(pli).parents(".group-content").attr("attr_id");
			   //alert(paramer);
			   //alert(attr_id);
			   
			   layer.confirm('您确定要删除"'+paramer+'"参数吗？', {
					  btn: ['确定','取消']
					}, function(){
					  pli.remove();					  
					  var index = layer.index;
                      layer.close(index); //关闭当前弹层
               //数据库中删除该参数       
                       $.post("<%=basePath%>/product/delAttrVals.json",{"attrVals":paramer,"attr_id":attr_id},function(data){
			                    //console.log(JSON.stringify(data));
			                    });
					}, function(){
					   return;
					}); 
			      
			
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
				e = e.target;
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
				if(data==null || data.length==0)
				{
				return ;
				}
					var row="";
					var t = "";
					var th="";	
					//拼装th
					if(data[0].attrName1!=null){
						th += "<th name='"+data[0].attrName1.trim()+"'>"+data[0].attrName1.trim()+"</th>";
					}
					if(data[0].attrName2!=null){
						th += "<th name='"+data[0].attrName2.trim()+"'>"+data[0].attrName2.trim()+"</th>";
					}
					if(data[0].attrName3!=null){
						th += "<th name='"+data[0].attrName3.trim()+"'>"+data[0].attrName3.trim()+"</th>";
					}
					if(data[0].attrName4!=null){
						th += "<th name='"+data[0].attrName4.trim()+"'>"+data[0].attrName4.trim()+"</th>";
					}
					if(data[0].attrName5!=null){
						th += "<th name='"+data[0].attrName5.trim()+"'>"+data[0].attrName5.trim()+"</th>";
					}
					if(data[0].attrName6!=null){
						th += "<th name='"+data[0].attrName6.trim()+"'>"+data[0].attrName6.trim()+"</th>";
					}
					if(data[0].attrName7!=null){
						th += "<th name='"+data[0].attrName7.trim()+"'>"+data[0].attrName7.trim()+"</th>";
					}
					if(data[0].attrName8!=null){
						th += "<th name='"+data[0].attrName8.trim()+"'>"+data[0].attrName8.trim()+"</th>";
					}
					th += '<th>市场价</th><th>图灵云价格</th><th>政采云价格</th><th>库存</th>';
		
				for(var i=0;i<data.length;i++){
						var row= "";
					if(data[i].attrName1!=null){
						row += '<td group="'+data[i].attrName1.trim()+'">'+data[i].attrVal1.trim()+'</td>'
					}
					if(data[i].attrName2!=null){
						row += '<td group="'+data[i].attrName2.trim()+'">'+data[i].attrVal2.trim()+'</td>'
					}
					if(data[i].attrName3!=null){
						row += '<td group="'+data[i].attrName3.trim()+'">'+data[i].attrVal3.trim()+'</td>'
					}
					if(data[i].attrName4!=null){
						row += '<td group="'+data[i].attrName4.trim()+'">'+data[i].attrVal4.trim()+'</td>'
					}
					if(data[i].attrName5!=null){
						row += '<td group="'+data[i].attrName5.trim()+'">'+data[i].attrVal5.trim()+'</td>'
					}
					if(data[i].attrName6!=null){
						row += '<td group="'+data[i].attrName6.trim()+'">'+data[i].attrVal6.trim()+'</td>'
					}
					if(data[i].attrName7!=null){
						row += '<td group="'+data[i].attrName7.trim()+'">'+data[i].attrVal7.trim()+'</td>'
					}
					if(data[i].attrName8!=null){
						row += '<td group="'+data[i].attrName8.trim()+'">'+data[i].attrVal8.trim()+'</td>'
					}

	
					row = '<tr id='+data[i].id+'>' + row + '<td group="originPrice"><input type="text" value='+data[i].originPrice+'></td><td group="platformPrice"><input type="text" value='+data[i].platformPrice+'></td><td group="price"><input type="text" value='+data[i].price+'></td><td group="stocks"><input type="text" value='+data[i].stocks+'></td></tr>';
					t+=row;
				}
					 $("#xs-block").empty().html('<table class="tablelist" style="clear:none;text-align:center;valign:middle"><thead>'+th +'</thead><tbody>'+ t + '</tbody></table>');
				$("#xs-block input").keyup(function(event){
					var b=event.target;
   					checkDepositRate(b);
	   			});	
	   			
	   			//禁用销售属性
				var _list = $(" table.tablelist th[name]");
				
			 	for( key in _list ){
			 		var _element = $('div[attr_name="'+_list.eq(key).attr("name")+'"] .group-content:first:first');
					_element.attr("disabled","disabled");
				}
				
				setCheckBox();
				
			}
		
			function setCheckBox() {
				//TODO 通过销售属性来重新渲染出销售信息的checkbox
				var arr_id = []; //保存销售信息中的类别
				$("#suk_attrs div").each(function() {
					arr_id.push($(this).attr("id").trim());
				});
				var th_name = []; //保存销售属性中的列名
				$("#xs-block thead tr th").each(function() {
					if (!!$(this).attr("name")) {
						th_name.push($(this).attr("name").trim());
					}
				});
				var param_arr = [];
				$("#xs-block tbody tr td[group]").each(function() {
					for (var i = 0; i < th_name.length; i++) {
						if ($(this).attr("group") == th_name[i] && isInArray($(this).text(), param_arr)) {
							//param_arr.push("#" + $(this).attr("group").trim() + " #" + $(this).text().trim());
							param_arr.push("#suk_attrs div[group='" + $(this).attr("group").trim() + "'] input[def='" + $(this).text().trim() + "']");
						}
					}
				});
				for (var j = 0; j < param_arr.length; j++) {
					//$("#" + param_arr[j]).prop("checked", true);
					$(param_arr[j]).prop("checked", true);
				}
			}
		
			function isInArray(a, arr) {
				var flag = true;
				for (var i = 0; i < arr.length; i++) {
					if (a == arr[i]) {
						flag = false;
					}
				}
				return flag;
			}
			
			//判断 并填充table
			var tableData = {};
			function getTableData(){
				var reg = /^[0-9]+(.[0-9]{1,2})?$/;
    			var reg2 = /^[0-9]*$/;
    			var val=0;
				var obj = "{";
				$("#xs-block tbody tr").each(function(i){
					if(i!=0){
						obj += ",";
					}
					var key_arr = [];//保存列表数据的key
					var value_arr = [];//保存列表数据的值
					var key='"key';
					var value='['; 
					$(this).find("td[id]").each(function(){
						//key_arr.push($(this).text());
						key = key + "/" + $(this).attr("group") + "/" + $(this).text();
					});
					$(this).find("input").each(function(j){
						//value_arr.push($(this).val());
						if(!reg.test($(this).val()) && !reg2.test($(this).val())){
							val = '"' + $(this).val() + '"';
						}
						else{
							val = $(this).val();
						}
						if(j!=0){value = value + "," + (!!val ? val : 0);}
						else{value = value + (!!val ? val : 0);}
					});
					key += '"';
					value += ']';
					obj += key+":"+value;
				});
				obj += "}";
				return JSON.parse(obj);
			}
			
			function fillInTable(){
				var newTableData = getTableData();
				for(var o in tableData){
					var i=0;
					for(var n in newTableData){
						if(o==n){

							var value = tableData[o];
							newTableData[n] = value;
							
						}
						i++;//当前table中的tr序号   .eq(i),从0开始
					}
				}
				tableData = newTableData;
				dealWithTableData(tableData);
			}
			
			function dealWithTableData(tableData){
				var i=0;
				for(var obj in tableData){
					var arr = tableData[obj];
					$("#xs-block tbody tr").each(function(j){
						if(i==j){
							$(this).find("td[group='originPrice'] input").val(arr[0] != 0 ? arr[0] : "");
							$(this).find("td[group='platformPrice'] input").val(arr[1] != 0 ? arr[1] : "");
							$(this).find("td[group='price'] input").val(arr[2] != 0 ? arr[2] : "");
							$(this).find("td[group='stocks'] input").val(arr[3] != 0 ? arr[3] : "");
						}
					});
					i++;
				}
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
                        th += '<th name="'+ $(d).attr('id').trim() +'">'+$(d).attr('id').trim()+'</th>';
                    }else{
                    	$(d).attr("checkedItem",false);
                    }
                });
                	th += '<th>市场价</th><th>图灵云价格</th><th>政采云价格</th><th>库存</th>';
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
                        tds = '<td group="'+currentGroup.attr('id').trim()+'" id="td' + i.toString() + j.toString() + '">' + checkedBox.attr('id').trim() + '</td>'+tds;
                    }
                    t += '<tr>' + tds + '<td group="originPrice"><input type="text"></td><td group="platformPrice"><input type="text"></td><td group="price"><input type="text"></td><td group="stocks"><input type="text"></td></tr>';
                }
                $("#xs-block").html('<table class="tablelist" style="clear:none;text-align:center;valign:middle"><thead>'+th +'</thead><tbody>'+ t + '</tbody></table>');
                $("#xs-block input").keyup(function(event){
                	var b=event.target;
   					checkDepositRate(this);
	   			});
/* 	   			$("#xs-blcok table tbody tr td[group=platformPrice] input").keyup(function(){
	   				//自动填充销售价格
	   				fullInPrice();
	   			}); */
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
    				//console.log(JSON.stringify(attrs));
    				for(var i = 0;i<attrs.length;i++){
    					var element = $('div[attr_name="'+attrs[i].attrKey+'"] .group-content span *:first');
    					element.val(attrs[i].attrVal);
    				}
    				});
    		}
    		
    	function checkSku(){
    		var _flag = true;
    		var skuGroup = new Array;
	    	$("#xs-block table tbody tr").each(function(j){
	    		if($(this).html()!=""){
	    		var sku = {};
	    		sku.id = $(this).attr('id');
	    		$(this).find('td').each(function(i){
	    			var td = $(this);
	    			if(td.attr('group')=='price'){
	    				if(!checkInput("decimal2",td.find('input').val()||td.find('input').val()=="")){
							td.find('input').addClass('error_input');
							_flag=false;
						}else{
							td.find('input').removeClass('error_input');
						}
	    				sku.price=td.find('input').val();
	    			}else if(td.attr('group')=='platformPrice'){
	    				if(!checkInput("decimal2",td.find('input').val()||td.find('input').val()=="")){
								td.find('input').addClass('error_input');
							_flag=false;
						}else{
							td.find('input').removeClass('error_input');
						}
	    				sku.platformPrice=td.find('input').val();
	    			}else if(td.attr('group')=='originPrice'){
	    				if(!checkInput("decimal2",td.find('input').val()||td.find('input').val()=="")){
							td.find('input').addClass('error_input');
							_flag=false;
						}else{
							td.find('input').removeClass('error_input');
						}
	    				sku.originPrice=td.find('input').val();
	    			}else if(td.attr('group')=='stocks'){
	    				if(!checkInput("number",td.find('input').val()||td.find('input').val()=="")){
								td.find('input').addClass('error_input');
							_flag=false;
						}else{
							td.find('input').removeClass('error_input');
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
		    			}else if(sku.attrName5==undefined){
	    					sku.attrName5 = attrKey;
	    					sku.attrVal5 = attrVal;
	    				}else if(sku.attrName6==undefined){
	    					sku.attrName6 = attrKey;
	    					sku.attrVal6 = attrVal;
	    				}else if(sku.attrName7==undefined){
	    					sku.attrName7 = attrKey;
	    					sku.attrVal7 = attrVal;
	    				}else if(sku.attrName8==undefined){
	    					sku.attrName8 = attrKey;
	    					sku.attrVal8 = attrVal;
    				}
	    			}
		
	    		});
    			skuGroup.push(sku);
   				}
   				if(_flag==false){
   					layer.alert("请检查销售属性输入");
   					
   				}else{
   					skus = skuGroup;
   					
   				}
				
    		});
     		var flag2 = checkDepositRate();
    		
    		return (_flag==false || flag2==false)==true ? false : true;
    		
    }
    
    function checkSuk_Attrs(){
 		if($("#suk_attrs").length>0){//销售信息存在时，每个类目必须选一个
   			var flag3 = true;
   			$("#suk_attrs div[isRequired]").each(function(){
   				if($(this).find("input.xs_check:checked").length==0){
   					flag3 = false;
   				}
   			});
     	}
     	return flag3;
    }

    
    function checkDepositRate(a){
    	var flag2 = true;
    	if($(a).parent().attr("group")=="platformPrice"){
    		var reg = /^[0-9]+(.[0-9]{1,2})?$/;
    		var reg2 = /^[0-9]*$/;
    		if(reg.test($(a).val())==true||reg2.test($(a).val())==true){
    			$(a).removeClass("error_input");
    		}
    		else{
    			$(a).addClass("error_input");
    			layer.tips("输入有误，图灵云价格应为整数或两位小数", $(a),{tips: [3, '#0FA6D8']});
    			return false;
    		}
    		var Pri = parseFloat($(a).val()) * depositRate;
    		Pri = Pri.toFixed(3)+"";
    		Pri = Pri.substring(0,Pri.length-1);
    		if(!!$(a).val()){
    			$(a).parent().siblings("[group=price]").children().val(Pri);
    		}
    		else{
    			$(a).parent().siblings("[group=price]").children().val("");
    		}
    	}
    	
    	$("#xs-block table tbody input").each(function(){
    		if(!!$(this).val()==false){//为空
    			flag2 = false;
    			$(this).addClass("error_input");
    		}
    		else{
    			$(this).removeClass("error_input");
    		}
   		});
    	
    	$("#xs-block table tbody tr").each(function(){
    		if(!!$(this).find("td[group=price] input").eq(0).val() && !!$(this).find("td[group=platformPrice] input").eq(0).val()){
    			var reg = /^[0-9]+(.[0-9]{1,2})?$/;
	    		var reg2 = /^[0-9]*$/;
	    		var PlaPri = $(this).find("td[group=platformPrice] input").eq(0).val();
	    		if(reg.test(PlaPri)==true||reg2.test(PlaPri)==true){
	    			$(this).find("td[group=platformPrice] input").eq(0).removeClass("error_input");
	    		}else{
	    			$(this).find("td[group=platformPrice] input").eq(0).addClass("error_input");
	    		}
	    		
	    		var price = parseFloat($(this).find("td[group=price] input").eq(0).val());
	    		var platformPrice = parseFloat($(this).find("td[group=platformPrice] input").eq(0).val());
	    		var price_max = parseFloat(platformPrice * depositRate);
	    		price_max = price_max.toFixed(3)+"";
	    		price_max = parseFloat(price_max.substring(0,price_max.length-1));
	    		if(price<price_max || price==price_max){
	    			//符合
	    			$(this).find("td[group=price] input").eq(0).removeClass("error_input");
	    		}else{
	    			flag2 = false;
	    			if(reg.test(price_max) || reg2.test(price_max)){
	    				//再次判断前面输入的是否为数字，并作出相应提示
	    				layer.tips("请输入<="+price_max+"的政采云价格,或提高图灵云价格", $(this).find("td[group=price] input").eq(0),{tips: [3, '#0FA6D8']});
	    				$(this).find("td[group=price] input").eq(0).addClass("error_input");
	    			}
	    			else{
	    				layer.tips("输入有误，图灵云价格应为整数或两位小数", $(this).find("td[group=platformPrice] input").eq(0),{tips: [3, '#0FA6D8']});
	    				$(this).find("td[group=price] input").eq(0).addClass("error_input");
	    			}
	    		}
    		}
    	});
    	
    	
    	return flag2;
    }
    
	    //填充sku数据
    function initSkuData(product_id){
    	$.post("<%=basePath%>productsku/list.json",{"product_id":id},function(data){
    	setSkuTable(data);
    	var list = data;
    	$("#xs-block tr").each(function(){
    		for(key in list){
    			var data = list[key];
    		}
    	
    	})
    	},'json')
    
    
    }
    
	function getPriceandCount( group,rule){
		var num = null;
		$("#xs-block tr td[group='"+group+"'] input").each(function(){
			var _val = Number($(this).val());
		if(rule=="max") {if(!num||_val>num) num = _val;}
		if(rule=="min") {if(!num||_val<num) num = _val;}
		if(rule=="sum") { if(!num) num = _val; else num += _val;}
		})
		return num||0;
	}

	</script>
</body>
</html>